package com.revature.service;
import com.revature.model.User;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTService {
    private Key key;

    public JWTService() {
        byte[] secret = "Zi9VakZYVZi9VakZYVFZHRkhVVWVVR2ZlQT09FZHRkhVVWVVR2ZlQT09".getBytes();
        key = Keys.hmacShaKeyFor(secret);
    }

    // sign a JWT with the key
    public String createJWT(User user) {
        String jwt = Jwts.builder().setSubject(user.getUsername())
                .claim("user_id", user.getUserId())
                .claim("user_role", user.getRoleId())
                .signWith(key)
                .compact();

        return jwt;
    }

    // validate a JWT with the key
    public Jws<Claims> parseJwt(String jwt) {
        try {
            Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);

            return token;
        } catch(JwtException e) {
            throw new UnauthorizedResponse("JWT is invalid.");
        }
    }
}
