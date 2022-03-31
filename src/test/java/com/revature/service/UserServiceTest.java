package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.exception.FailedLoginException;
import com.revature.exception.FailedRegisterException;
import com.revature.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Test
    void testLogin() throws SQLException, FailedLoginException {
        UserDao mockDao = mock(UserDao.class);
        User fakeUser = new User(1, "testuser", "", "Test", "Testy", "test@gmail.com", 2);

        when(mockDao.getUserByUsernameAndPassword(eq("testuser"), eq("123456"))).thenReturn(fakeUser);

        UserService userService = new UserService(mockDao);
        User actual = userService.login("testuser", "123456");

        Assertions.assertEquals(fakeUser, actual);
    }

    @Test
    void testLoginFail() {
        UserDao mockDao = mock(UserDao.class);
        UserService userService = new UserService(mockDao);

        Assertions.assertThrows(FailedLoginException.class, () -> {
            userService.login("testuser", "123456");
        });
    }

    @Test
    void testRegister() throws SQLException, FailedRegisterException {
        UserDao mockDao = mock(UserDao.class);
        User inputUser = new User(0, "testuser", "123456", "Test", "Testy", "test@gmail.com", 2);
        User fakeUser = new User(1, "testuser", "", "Test", "Testy", "test@gmail.com", 2);

        when(mockDao.createUser(eq(inputUser))).thenReturn(fakeUser);

        UserService userService = new UserService(mockDao);
        User actual = userService.register(inputUser);

        Assertions.assertEquals(fakeUser, actual);
    }

    @Test
    void testRegisterFail() {
        UserDao mockDao = mock(UserDao.class);
        User inputUser = new User(0, "testuser", "123456", "Test", "Testy", "test@gmail.com", 2);
        UserService userService = new UserService(mockDao);

        Assertions.assertThrows(FailedRegisterException.class, () -> {
            userService.register(inputUser);
        });
    }
}
