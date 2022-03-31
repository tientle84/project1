package com.revature.service;

import com.revature.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JWTServiceTest {
    @Test
    void testCreateJWT() {
        JWTService jwtService = new JWTService();

        User fakeUser = new User(1, "testuser", "", "Test", "Testy", "test@gmail.com", 2);
        String jwt = jwtService.createJWT(fakeUser);

        Assertions.assertNotNull(jwt);
    }

    @Test
    void testParseJwt() {
        JWTService jwtService = new JWTService();
        String jwt = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0IiwidXNlcl9pZCI6MjYsInVzZXJfcm9sZSI6Mn0.oh38e4ZllkO-tT-fsKdeNTF1euDrIhRQ8yc6CGzk5eq8ESUGzVz_ovzZKET_2pUe";

        Jws<Claims> token = jwtService.parseJwt(jwt);
        int actual = token.getBody().get("user_role", Integer.class);
        Assertions.assertEquals(2, actual);
    }
}
