package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.dto.ReimbursementDTO;
import com.revature.exception.ReimbursementNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReimbursementServiceTest {
    @Test
    void testGetAllReimbursements() throws SQLException {
        UserDao mockedUser = mock(UserDao.class);
        ReimbursementDao mockedReimb = mock(ReimbursementDao.class);
        ReimbursementService reimbursementService = new ReimbursementService(mockedUser, mockedReimb);

        List<ReimbursementDTO> fakeReimbs = new ArrayList<>();
        fakeReimbs.add(new ReimbursementDTO(1, 150, "2021-05-03", "", "description 1", "http://", "John Doe", 0, "", 1, "Pending", 1, "Lodging"));
        fakeReimbs.add(new ReimbursementDTO(2, 200, "2021-05-03", "", "description 2", "http://", "John Doe", 0, "", 1, "Pending", 2, "Travel"));
        fakeReimbs.add(new ReimbursementDTO(3, 120, "2021-05-03", "", "description 3", "http://", "John Doe", 0, "", 1, "Pending", 3, "Food"));

        when(mockedReimb.getAllReimbursements()).thenReturn(fakeReimbs);

        List<ReimbursementDTO> actual = reimbursementService.getAllReimbursements();

        List<ReimbursementDTO> expected = new ArrayList<>(fakeReimbs);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetAllReimbursementsByUserId() throws SQLException {
        UserDao mockedUser = mock(UserDao.class);
        ReimbursementDao mockedReimb = mock(ReimbursementDao.class);
        ReimbursementService reimbursementService = new ReimbursementService(mockedUser, mockedReimb);

        List<ReimbursementDTO> fakeReimbs = new ArrayList<>();
        fakeReimbs.add(new ReimbursementDTO(1, 150, "2021-05-03", "", "description 1", "http://", "John Doe", 0, "", 1, "Pending", 1, "Lodging"));
        fakeReimbs.add(new ReimbursementDTO(2, 200, "2021-05-03", "", "description 2", "http://", "John Doe", 0, "", 1, "Pending", 2, "Travel"));
        fakeReimbs.add(new ReimbursementDTO(3, 120, "2021-05-03", "", "description 3", "http://", "John Doe", 0, "", 1, "Pending", 3, "Food"));

        when(mockedReimb.getAllReimbursementsByUserId(eq(1))).thenReturn(fakeReimbs);

        List<ReimbursementDTO> actual = reimbursementService.getAllReimbursementsByUserId(1);

        List<ReimbursementDTO> expected = new ArrayList<>(fakeReimbs);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCreateReimbursement() throws SQLException, IOException {
        UserDao mockedUser = mock(UserDao.class);
        ReimbursementDao mockedReimb = mock(ReimbursementDao.class);
        ReimbursementService reimbursementService = new ReimbursementService(mockedUser, mockedReimb);

        ReimbursementDTO fakeReimb = new ReimbursementDTO(1, 150, "2021-05-03", "", "description 1", "http://", "John Doe", 0, "", 1, "Pending", 1, "Lodging");

        when(mockedReimb.createReimbursement(1, 150, "description 1", 1, null)).thenReturn(fakeReimb);

        ReimbursementDTO actual = reimbursementService.createReimbursement("1", "150", "description 1", "1", null);

        Assertions.assertEquals(fakeReimb, actual);
    }

    @Test
    void testUpdateReimbursement() throws SQLException, IOException {
        UserDao mockedUser = mock(UserDao.class);
        ReimbursementDao mockedReimb = mock(ReimbursementDao.class);
        ReimbursementService reimbursementService = new ReimbursementService(mockedUser, mockedReimb);

        ReimbursementDTO fakeReimb = new ReimbursementDTO(1, 150, "2021-05-03", "", "description 1", "http://", "John Doe", 0, "", 1, "Pending", 1, "Lodging");

        when(mockedReimb.updateReimbursement(1, 10, 150, "description 1", 1, null)).thenReturn(fakeReimb);

        ReimbursementDTO actual = reimbursementService.updateReimbursement("1", "10", "150", "description 1", "1", null);

        Assertions.assertEquals(fakeReimb, actual);
    }

    @Test
    void testDeleteReimbursement() throws SQLException {
        UserDao mockedUser = mock(UserDao.class);
        ReimbursementDao mockedReimb = mock(ReimbursementDao.class);
        ReimbursementService reimbursementService = new ReimbursementService(mockedUser, mockedReimb);

        when(mockedReimb.deleteReimbursement(1, 10)).thenReturn(true);

        boolean actual = reimbursementService.deleteReimbursement("1", "10");

        Assertions.assertEquals(true, actual);
    }

    @Test
    void testAuthorizeReimbursement() throws SQLException, ReimbursementNotFoundException {
        UserDao mockedUser = mock(UserDao.class);
        ReimbursementDao mockedReimb = mock(ReimbursementDao.class);
        ReimbursementService reimbursementService = new ReimbursementService(mockedUser, mockedReimb);

        ReimbursementDTO fakeReimb = new ReimbursementDTO(10, 150, "2021-05-03", "", "description 1", "http://", "John Doe", 0, "", 1, "Pending", 1, "Lodging");
        when(mockedReimb.getReimbursementById(eq(10))).thenReturn(fakeReimb);

        when(mockedReimb.authorizeReimbursement(eq(10), eq(2), eq(2))).thenReturn(1);

        int actual = reimbursementService.authorizeReimbursement("10", "2", 2);
        Assertions.assertEquals(1, actual);
    }

    @Test
    void testAuthorizeReimbursementFail() {
        UserDao mockedUser = mock(UserDao.class);
        ReimbursementDao mockedReimb = mock(ReimbursementDao.class);
        ReimbursementService reimbursementService = new ReimbursementService(mockedUser, mockedReimb);

        Assertions.assertThrows(ReimbursementNotFoundException.class, () -> {
            reimbursementService.authorizeReimbursement("10", "2", 2);
        });
    }
}
