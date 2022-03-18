package com.revature.controller;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionController implements Controller {
//    private static Logger logger = LoggerFactory.getLogger(ClientService.class);
//
//    private ExceptionHandler clientNotFound = (e, ctx) -> {
//        logger.warn("User attempted to retrieve a client that does not exist. Exception message is " + e.getMessage());
//        ctx.status(404);
//        ctx.json(e.getMessage());
//    };
//
//    private ExceptionHandler bankAccountNotFound = (e, ctx) -> {
//        logger.warn("User attempted to retrieve an account that does not exist. Exception message is " + e.getMessage());
//        ctx.status(404);
//        ctx.json(e.getMessage());
//    };
//
//    private ExceptionHandler invalidArg = (e, ctx) -> {
//        logger.warn("User attempted to enter invalid data. Exception message is " + e.getMessage());
//        ctx.status(400);
//        ctx.json(e.getMessage());
//    };

    @Override
    public void mapEndpoints(Javalin app) {
//        app.exception(ClientNotFoundException.class, clientNotFound);
//        app.exception(BankAccountNotFoundException.class, bankAccountNotFound);
//        app.exception(IllegalArgumentException.class, invalidArg);
    }
}
