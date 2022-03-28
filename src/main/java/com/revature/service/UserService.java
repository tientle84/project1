package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.exception.FailedLoginException;
import com.revature.exception.FailedRegisterException;
import com.revature.model.User;
import java.sql.SQLException;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public UserService(UserDao mockDao) {
        this.userDao = mockDao;
    }

    public User login(String username, String password) throws SQLException, FailedLoginException {
        User user = userDao.getUserByUsernameAndPassword(username, password);

        if(user == null) {
            throw new FailedLoginException("Invalid username or password.");
        }

        return user;
    }

    public User register(User user) throws SQLException, FailedRegisterException {
        User createdUser = userDao.createUser(user);

        if(createdUser == null) {
            throw new FailedRegisterException("Username or email already exists.");
        }

        return createdUser;
    }
}
