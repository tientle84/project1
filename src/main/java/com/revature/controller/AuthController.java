package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Cookie;
import io.javalin.http.Handler;
import io.javalin.http.SameSite;

public class AuthController implements Controller{
    private JWTService jwtService;
    private UserService userService;

    public AuthController() {
        this.jwtService = new JWTService();
        this.userService = new UserService();
    }

    private Handler login = (ctx) -> {
        LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
        User user = userService.login(loginDTO.getUsername(), loginDTO.getPassword());

        // If FailedLoginException did not occur, then we will move on to the subsequent lines of code
        String jwt = jwtService.createJWT(user);

        ctx.header("Access-Control-Expose-Headers", "*");
        ctx.header("Token", jwt);

//        Cookie cookie = new Cookie("jwt", jwt, "/", 86400, false , 0 ,true, "", "", SameSite.LAX);
//        ctx.cookie(cookie);
        ctx.json(user);
    };

    private  Handler register = (ctx) -> {
        User user = ctx.bodyAsClass(User.class);
        User createdUser = userService.register(user);

        ctx.json(createdUser);
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.post("/login", login);
        app.post("/register", register);
    }
}
