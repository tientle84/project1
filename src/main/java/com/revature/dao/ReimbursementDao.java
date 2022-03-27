package com.revature.dao;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.revature.dto.ReimbursementDTO;
import com.revature.model.*;
import com.revature.utility.ConnectionUtility;
import com.revature.utility.UploadFile;
import io.javalin.http.UploadedFile;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDao {
    public List<Reimbursement> getAllReimbursements() throws SQLException {
        List<Reimbursement> reimbursements = new ArrayList<>();

//        try(Connection connection = ConnectionUtility.getConnection()) {
//            String sql = "SELECT * FROM ers_reimbursement";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()) {
//                int id = resultSet.getInt("reimb_id");
//                double amount = resultSet.getDouble("reimb_amount");
//                String submitted = resultSet.getString("reimb_submitted");
//                String resolved = resultSet.getString("reimb_resolved");
//                String description = resultSet.getString("reimb_description");
//                String receipt = resultSet.getString("reimb_receipt");
//                int author = resultSet.getInt("reimb_author");
//                int resolver = resultSet.getInt("reimb_resolver");
//                int statusId = resultSet.getInt("reimb_status_id");
//                int typeId = resultSet.getInt("reimb_type_id");
//
//                Reimbursement reimbursement = new Reimbursement(id, amount, submitted, resolved, description, receipt, author, resolver, statusId, typeId);
//                reimbursements.add(reimbursement);
//            }
//        }

        return reimbursements;
    }

    public ReimbursementDTO createReimbursement(int userId, double amount, String description, int typeId, UploadedFile receiptFile) throws SQLException, IOException {
        String uploadedReceiptUrl = UploadFile.uploadFile(receiptFile);

        if(uploadedReceiptUrl != null) {
            try (Connection connection = ConnectionUtility.getConnection()) {
                String sql = "insert into ers_reimbursement " +
                        "(reimb_submitted, reimb_amount, reimb_description, reimb_receipt, reimb_author, reimb_type_id) " +
                        "values (CURRENT_DATE, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDouble(1, amount);
                preparedStatement.setString(2, description);
                preparedStatement.setString(3, uploadedReceiptUrl);
                preparedStatement.setInt(4, userId);
                preparedStatement.setInt(5, typeId);

                preparedStatement.executeUpdate();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                resultSet.next();
                int generatedId = resultSet.getInt(1);
                return new ReimbursementDTO(generatedId, amount, "", "", description, uploadedReceiptUrl, "", "", "", typeId, "");
            }
        }

        return null;
    }

//    public ReimbursementDTO createReimbursement(int userId, ReimbursementDTO reimbursementDTO) throws SQLException {
//        try (Connection connection = ConnectionUtility.getConnection()) {
//            String sql = "insert into ers_reimbursement " +
//                    "(reimb_submitted, reimb_amount, reimb_description, reimb_receipt, reimb_author, reimb_type_id) " +
//                    "values (CURRENT_DATE, ?, ?, ?, ?, ?)";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setDouble(1, reimbursementDTO.getReimbursementAmount());
//            preparedStatement.setString(2, reimbursementDTO.getReimbursementDescription());
//            preparedStatement.setString(3, reimbursementDTO.getReimbursementReceipt());
//            preparedStatement.setInt(4, userId);
//            preparedStatement.setInt(5, reimbursementDTO.getReimbursementTypeId());
//
//            preparedStatement.executeUpdate();
//
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//
//            resultSet.next();
//            int generatedId = resultSet.getInt(1);
//            return new ReimbursementDTO(generatedId, reimbursementDTO.getReimbursementAmount(), "", "", reimbursementDTO.getReimbursementDescription(), reimbursementDTO.getReimbursementReceipt(), "", "", "", reimbursementDTO.getReimbursementTypeId(), "");
//        }
//    }

    public ReimbursementDTO updateReimbursement(int userId, int reimbId, double amount, String description, int typeId, UploadedFile receiptFile) throws SQLException, IOException {
        String uploadedReceiptUrl = "";
        if(receiptFile != null) {
            uploadedReceiptUrl = UploadFile.uploadFile(receiptFile);
        }

        try (Connection connection = ConnectionUtility.getConnection()) {
            String sql = "update ers_reimbursement set " +
                    "reimb_amount = ?, " +
                    "reimb_description = ?, ";

            if(receiptFile != null) {
                sql += "reimb_type_id = ?, reimb_receipt = ? " +
                        "where reimb_author = ? and reimb_id = ?";
            } else {
                sql += "reimb_type_id = ? " +
                        "where reimb_author = ? and reimb_id = ?";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, typeId);
            if(receiptFile != null) {
                preparedStatement.setString(4, uploadedReceiptUrl);
                preparedStatement.setInt(5, userId);
                preparedStatement.setInt(6, reimbId);
            } else {
                preparedStatement.setInt(4, userId);
                preparedStatement.setInt(5, reimbId);
            }

            preparedStatement.executeUpdate();
        }

        return new ReimbursementDTO(reimbId, amount, "", "", description, uploadedReceiptUrl, "", "", "", typeId, "");
    }

    public  List<ReimbursementDTO> getAllReimbursementsByUserId(int userId) throws SQLException {
        try (Connection connection = ConnectionUtility.getConnection()) {
            List<ReimbursementDTO> reimbursementsDTO = new ArrayList<>();

            String sql = "select reimb_id, reimb_submitted, reimb_amount, reimb_resolved, reimb_description, reimb_receipt, " +
                    "concat(eu.user_first_name, ' ', eu.user_last_name) as au_fullname,  " +
                    "concat(eu2.user_first_name, ' ', eu2.user_last_name) as re_fullname, " +
                    "ers.reimb_status, ert.reimb_type_id, ert.reimb_type  " +
                    "from ers_reimbursement er  " +
                    "left join ers_users eu on eu.ers_users_id = er.reimb_author " +
                    "left join ers_users eu2 on eu2.ers_users_id = er.reimb_resolver  " +
                    "left join ers_reimbursement_status ers on ers.reimb_status_id  = er.reimb_status_id  " +
                    "left join ers_reimbursement_type ert on ert.reimb_type_id = er.reimb_type_id " +
                    "where er.reimb_author = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("reimb_id");
                double amount = resultSet.getDouble("reimb_amount");
                String submitted = resultSet.getString("reimb_submitted");
                String resolved = resultSet.getString("reimb_resolved");
                String description = resultSet.getString("reimb_description");
                String receipt = resultSet.getString("reimb_receipt");
                String authorFullName = resultSet.getString("au_fullname").trim();
                String resolverFullName = resultSet.getString("re_fullname").trim();
                String status = resultSet.getString("reimb_status");
                int typeId = resultSet.getInt("reimb_type_id");
                String type = resultSet.getString("reimb_type");

                ReimbursementDTO reimbursementDTO = new ReimbursementDTO(id, amount, submitted, resolved, description, receipt, authorFullName, resolverFullName, status, typeId, type);
                reimbursementsDTO.add(reimbursementDTO);
            }

            return reimbursementsDTO;
        }
    }

//    public  List<Reimbursement> getAllReimbursementsByUserId(int userId) throws SQLException {
//        try(Connection connection = ConnectionUtility.getConnection()) {
//            List<Reimbursement> reimbursements = new ArrayList<>();
//
//            String sql = "select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, " +
//                    "eu.ers_users_id as au_id, eu.ers_username as au_username, eu.ers_password as au_password, " +
//                    "eu.user_first_name as au_firstname, eu.user_last_name as au_lastname, eu.user_email as au_email, eu.user_role_id as au_role_id, " +
//                    "eu2.ers_users_id as re_id, eu2.ers_username as re_username, eu2.ers_password as re_password, " +
//                    "eu2.user_first_name as re_firstname, eu2.user_last_name as re_lastname, eu2.user_email as re_email, eu2.user_role_id as re_role_id, " +
//                    "ers.reimb_status_id, ers.reimb_status, ert.reimb_type_id, ert.reimb_type " +
//                    "from ers_reimbursement er " +
//                    "left join ers_users eu on er.reimb_author = eu.ers_users_id " +
//                    "left join ers_users eu2 on er.reimb_resolver = eu2.ers_users_id " +
//                    "left join ers_reimbursement_status ers on er.reimb_status_id = ers.reimb_status_id  " +
//                    "left join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id " +
//                    "where er.reimb_author = ?";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, userId);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()) {
//                // reimbursement
//                int id = resultSet.getInt("reimb_id");
//                double amount = resultSet.getInt("reimb_amount");
//                String submitted = resultSet.getString("reimb_submitted");
//                String resolved = resultSet.getString("reimb_resolved");
//                String description = resultSet.getString("reimb_description");
//                String receipt = resultSet.getString("reimb_receipt");
//
//                // author
//                int authorId = resultSet.getInt("au_id");
//                String authorFirstName = resultSet.getString("au_firstname");
//                String authorLastName = resultSet.getString("au_lastname");
//                String authorUsername = resultSet.getString("au_username");
//                String authorPassword = resultSet.getString("au_password");
//                String authorEmail = resultSet.getString("au_email");
//
//                // author role
//                int authorRoleId = resultSet.getInt("au_role_id");
//                String authorRole = resultSet.getString("au_role");
//                UserRole authorUserRole = new UserRole(authorRoleId, authorRole);
//
//                User author = new User(authorId, authorFirstName, authorLastName, authorUsername, authorPassword, authorEmail, authorUserRole);
//
//                // resolver
//                int resolverId = resultSet.getInt("re_id");
//                String resolverFirstName = resultSet.getString("re_firstname");
//                String resolverLastName = resultSet.getString("re_lastname");
//                String resolverUsername = resultSet.getString("re_username");
//                String resolverPassword = resultSet.getString("re_password");
//                String resolverEmail = resultSet.getString("re_email");
//
//                // resolver role
//                int resolverRoleId = resultSet.getInt("re_role_id");
//                String resolverRole = resultSet.getString("re_role");
//                UserRole resolverUserRole = new UserRole(resolverRoleId, resolverRole);
//
//                User resolver = new User(resolverId, resolverFirstName, resolverLastName, resolverUsername, resolverPassword, resolverEmail, resolverUserRole);
//
//                // reimbursement status
//                int statusId = resultSet.getInt("reimb_status_id");
//                String status = resultSet.getString("reimb_status");
//                ReimbursementStatus reimbursementStatus = new ReimbursementStatus(statusId, status);
//
//                // reimbursement type
//                int typeId = resultSet.getInt("reimb_type_id");
//                String type = resultSet.getString("reimb_type");
//                ReimbursementType reimbursementType = new ReimbursementType(typeId, type);
//
//                Reimbursement reimbursement = new Reimbursement(id, amount, submitted, resolved, description, receipt, author, resolver, reimbursementStatus, reimbursementType);
//                reimbursements.add(reimbursement);
//            }
//
//            return reimbursements;
//        }
//    }

    public Reimbursement getReimbursementById(int intUserId, int intReimbursementId) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? AND reimb_id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, intUserId);
            preparedStatement.setInt(2, intReimbursementId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("reimb_id");
                double amount = resultSet.getDouble("reimb_amount");
                String submitted = resultSet.getString("reimb_submitted");
                String resolved = resultSet.getString("reimb_resolved");
                String description = resultSet.getString("reimb_description");
                String receipt = resultSet.getString("reimb_receipt");
                int author = resultSet.getInt("reimb_author");
                int resolver = resultSet.getInt("reimb_resolver");
                int statusId = resultSet.getInt("reimb_status_id");
                int typeId = resultSet.getInt("reimb_type_id");

                //return new Reimbursement(id, amount, submitted, resolved, description, receipt, author, resolver, statusId, typeId);
            }
        }

        return  null;
    }

    public boolean deleteReimbursement(int intUserId, int intReimbId) throws SQLException {
        try (Connection connection = ConnectionUtility.getConnection()) {
            String sql = "delete from ers_reimbursement where reimb_author = ? and reimb_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, intUserId);
            preparedStatement.setInt(2, intReimbId);

            int numberOfRecordsDeleted = preparedStatement.executeUpdate();

            if(numberOfRecordsDeleted == 1) {
                return true;
            }
        }

        return false;
    }
}
