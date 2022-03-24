package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.dto.ReimbursementDTO;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.exception.UserNotFoundException;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.utility.InfoValidator;

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

    // check to see whether user exists or not
    public User ifUserExist(int userId) throws SQLException, UserNotFoundException {
        User user = userDao.getUserById(userId);

        // check if user is null, then throw exception
        if(user == null) {
            throw new UserNotFoundException("Could not find the user with id " + userId + ".");
        } else {
            return user;
        }
    }

    // check to see whether reimbursement exists or not
    public Reimbursement ifReimbursementExist(int userId, int reimbursementId) throws SQLException, ReimbursementNotFoundException {
        Reimbursement reimbursement = reimbursementDao.getReimbursementById(userId, reimbursementId);

        // check if reimbursement is null, then throw exception
        if(reimbursement == null) {
            throw new ReimbursementNotFoundException("Could not find the reimbursement " + reimbursementId + " of the user " + userId + ".");
        } else {
            return reimbursement;
        }
    }

    public List<ReimbursementDTO> getAllReimbursementsByUserId(int userId) throws SQLException {
        return this.reimbursementDao.getAllReimbursementsByUserId(userId);
    }

    public ReimbursementDTO createReimbursement(String userId, ReimbursementDTO reimbursementDTO) throws SQLException {
        int intUserId = InfoValidator.isValidId(userId);
        return reimbursementDao.createReimbursement(intUserId, reimbursementDTO);
    }

    public ReimbursementDTO updateReimbursement(String userId, String reimbId, ReimbursementDTO reimbursementDTO) throws SQLException {
        int intUserId = InfoValidator.isValidId(userId);
        int intReimbId = InfoValidator.isValidId(reimbId);

        return reimbursementDao.updateReimbursement(intUserId, intReimbId, reimbursementDTO);
    }

    public boolean deleteReimbursement(String userId, String reimbId) throws SQLException {
        int intUserId = InfoValidator.isValidId(userId);
        int intReimbId = InfoValidator.isValidId(reimbId);

        return reimbursementDao.deleteReimbursement(intUserId, intReimbId);
    }
}
