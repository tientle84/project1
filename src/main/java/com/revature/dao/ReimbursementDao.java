package com.revature.dao;

import com.revature.dto.ReimbursementDTO;
import com.revature.utility.ConnectionUtility;
import com.revature.utility.UploadFile;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDao {
    public List<ReimbursementDTO> getAllReimbursements() throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()) {
            List<ReimbursementDTO> reimbursementsDTO = new ArrayList<>();
            String sql = "select reimb_id, reimb_submitted, reimb_amount, reimb_resolved, reimb_description, reimb_receipt, " +
                    "concat(eu.user_first_name, ' ', eu.user_last_name) as au_fullname,  " +
                    "concat(eu2.user_first_name, ' ', eu2.user_last_name) as re_fullname, " +
                    "ers.reimb_status, ert.reimb_type_id, ert.reimb_type  " +
                    "from ers_reimbursement er  " +
                    "left join ers_users eu on eu.ers_users_id = er.reimb_author " +
                    "left join ers_users eu2 on eu2.ers_users_id = er.reimb_resolver  " +
                    "left join ers_reimbursement_status ers on ers.reimb_status_id  = er.reimb_status_id  " +
                    "left join ers_reimbursement_type ert on ert.reimb_type_id = er.reimb_type_id";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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

                ReimbursementDTO reimbursementDTO = new ReimbursementDTO(id, amount, submitted, resolved, description, receipt, authorFullName, 0, resolverFullName, 0, status, typeId, type);
                reimbursementsDTO.add(reimbursementDTO);
            }

            return reimbursementsDTO;
        }
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
                return new ReimbursementDTO(generatedId, amount, "", "", description, uploadedReceiptUrl, "", 0, "", 0, "", typeId, "");
            }
        }

        return null;
    }

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

        return new ReimbursementDTO(reimbId, amount, "", "", description, uploadedReceiptUrl, "", 0, "", 0, "", typeId, "");
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

                ReimbursementDTO reimbursementDTO = new ReimbursementDTO(id, amount, submitted, resolved, description, receipt, authorFullName, 0, resolverFullName, 0 ,status, typeId, type);
                reimbursementsDTO.add(reimbursementDTO);
            }

            return reimbursementsDTO;
        }
    }

    public ReimbursementDTO getReimbursementById(int reimbursementId) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, reimbursementId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                double amount = resultSet.getDouble("reimb_amount");
                String submitted = resultSet.getString("reimb_submitted");
                String resolved = resultSet.getString("reimb_resolved");
                String description = resultSet.getString("reimb_description");
                String receipt = resultSet.getString("reimb_receipt");
                int author = resultSet.getInt("reimb_author");
                int resolver = resultSet.getInt("reimb_resolver");
                int statusId = resultSet.getInt("reimb_status_id");
                int typeId = resultSet.getInt("reimb_type_id");

                return new ReimbursementDTO(reimbursementId, amount, submitted, resolved, description, receipt, "", 0, "", 0 , "", typeId, "");
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

    public int authorizeReimbursement(int intReimbursementId, int intAuthorizedStatusId, int resolverId) throws SQLException {
        try (Connection connection = ConnectionUtility.getConnection()) {
            String sql = "update ers_reimbursement set " +
                    "reimb_status_id = ?, " +
                    "reimb_resolver = ?, " +
                    "reimb_resolved = CURRENT_DATE " +
                    "where reimb_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, intAuthorizedStatusId);
            preparedStatement.setInt(2, resolverId);
            preparedStatement.setInt(3, intReimbursementId);

            return preparedStatement.executeUpdate();
        }
        //return new ReimbursementDTO(intReimbursementId, 0, "", "", "", "", "", resolverId, "", intAuthorizedStatusId, "", 0, "");
    }
}
