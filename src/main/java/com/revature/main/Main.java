package com.revature.main;

import com.revature.controller.AuthController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import com.revature.controller.ReimbursementController;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();

        });

        // info logging before every single request
        app.before(ctx -> {
            logger.info("{0} request received for {1}", ctx.method(), ctx.path());
            logger.info(ctx.fullUrl());
        });

        // info logging after every single request
        app.after(ctx -> {
            logger.info("{0} request has a response status {1}", ctx.method(), ctx.status());
        });

        mapControllers(app, new AuthController(), new ReimbursementController(), new ExceptionController());
        app.start(getHerokuAssignedPort());
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
