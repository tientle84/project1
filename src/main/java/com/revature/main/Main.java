package com.revature.main;

import com.revature.controller.AuthController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import com.revature.controller.ReimbursementController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        });

//        // info logging before every single request
//        app.before(ctx -> {
//            logger.info(ctx.method() + " request received for " + ctx.path());
//            logger.info(ctx.fullUrl());
//        });
//
//        // info logging before every single request
//        app.after(ctx -> {
//            logger.info(ctx.method() + " request has a response status " + ctx.status());
//        });

        mapControllers(app, new AuthController(), new ReimbursementController(), new ExceptionController());
        app.start(getHerokuAssignedPort());
        //app.start(7777);
    }

    // get heroku port when deploying to heroku
    private static int getHerokuAssignedPort() {
        String herokuPort = System.getenv("PORT");
        if (herokuPort != null) {
            return Integer.parseInt(herokuPort);
        }
        return 7777;
    }

    public static void mapControllers(Javalin app, Controller... controllers) {
        for (Controller c : controllers) {
            c.mapEndpoints(app);
        }
    }
}
