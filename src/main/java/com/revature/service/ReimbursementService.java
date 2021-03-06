package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.dto.ReimbursementDTO;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.utility.InfoValidator;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementService {
    private final UserDao userDao;
    private final ReimbursementDao reimbursementDao;

    public ReimbursementService() {
        this.userDao = new UserDao();
        this.reimbursementDao = new ReimbursementDao();
    }

    public ReimbursementService(UserDao userMockDao, ReimbursementDao reimbursementMockDao) {
        this.userDao = userMockDao;
        this.reimbursementDao = reimbursementMockDao;
    }

    // check to see whether reimbursement exists or not (for managers usage)
    public ReimbursementDTO ifReimbursementExist(int reimbursementId) throws SQLException, ReimbursementNotFoundException {
        ReimbursementDTO reimbursementDTO = reimbursementDao.getReimbursementById(reimbursementId);

        // check if reimbursement is null, then throw exception
        if(reimbursementDTO == null) {
            throw new ReimbursementNotFoundException("Could not find the reimbursement " + reimbursementId + ".");
        } else {
            return reimbursementDTO;
        }
    }

    // this method is used by employee to get their reimbursements
    public List<ReimbursementDTO> getAllReimbursementsByUserId(int userId) throws SQLException {
        return this.reimbursementDao.getAllReimbursementsByUserId(userId);
    }

    public ReimbursementDTO createReimbursement(String userId, String amount, String description, String typeId, UploadedFile receiptFile) throws SQLException, IOException {
        int intUserId = InfoValidator.isValidId(userId);
        double doubleAmount = InfoValidator.isValidParam(amount);
        int intTypeId = InfoValidator.isValidId(typeId);

        return reimbursementDao.createReimbursement(intUserId, doubleAmount, description, intTypeId, receiptFile);
    }

    public ReimbursementDTO updateReimbursement(String userId, String reimbId, String amount, String description, String typeId, UploadedFile receiptFile) throws SQLException, IOException {
        int intUserId = InfoValidator.isValidId(userId);
        int intReimbId = InfoValidator.isValidId(reimbId);
        double doubleAmount = InfoValidator.isValidParam(amount);
        int intTypeId = InfoValidator.isValidId(typeId);

        return reimbursementDao.updateReimbursement(intUserId, intReimbId, doubleAmount, description, intTypeId, receiptFile);
    }

    public boolean deleteReimbursement(String userId, String reimbId) throws SQLException {
        int intUserId = InfoValidator.isValidId(userId);
        int intReimbId = InfoValidator.isValidId(reimbId);

        return reimbursementDao.deleteReimbursement(intUserId, intReimbId);
    }

    // this method is used for manager to get all the Reimbursements
    public List<ReimbursementDTO> getAllReimbursements() throws SQLException {
        return reimbursementDao.getAllReimbursements();
    }

    public int authorizeReimbursement(String reimbursementId, String authorizedStatusId, int resolverId) throws SQLException, ReimbursementNotFoundException {
        int intReimbursementId = InfoValidator.isValidId(reimbursementId);
        int intAuthorizedStatusId = InfoValidator.isValidId(authorizedStatusId);
        ifReimbursementExist(intReimbursementId);

        return reimbursementDao.authorizeReimbursement(intReimbursementId, intAuthorizedStatusId, resolverId);
    }
}
