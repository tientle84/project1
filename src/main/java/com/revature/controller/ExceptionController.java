package com.revature.controller;

import com.revature.exception.FailedLoginException;
import com.revature.exception.FailedRegisterException;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.exception.UserNotFoundException;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionController implements Controller {
    private static Logger userLogger = LoggerFactory.getLogger(UserService.class);
    private static Logger reimbLogger = LoggerFactory.getLogger(ReimbursementService.class);

    private ExceptionHandler<FailedLoginException> failedLoginExceptionHandler = (exception, ctx) -> {
        userLogger.warn("Login failed. Exception message is " + exception.getMessage());
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<FailedRegisterException> failedRegisterExceptionHandler = (exception, ctx) -> {
        userLogger.warn("Register failed. Exception message is " + exception.getMessage());
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<UserNotFoundException> userNotFoundHandler = (exception, ctx) -> {
        userLogger.warn("User does not exist. Exception message is " + exception.getMessage());
        ctx.status(404);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<ReimbursementNotFoundException> reimbursementNotFoundHandler = (exception, ctx) -> {
        reimbLogger.warn("Reimbursement does not exist. Exception message is " + exception.getMessage());
        ctx.status(404);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<IllegalArgumentException> invalidArg = (exception, ctx) -> {
        reimbLogger.warn("User attempted to enter invalid data. Exception message is " + exception.getMessage());
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.exception(FailedLoginException.class, failedLoginExceptionHandler);
        app.exception(FailedRegisterException.class, failedRegisterExceptionHandler);
        app.exception(UserNotFoundException.class, userNotFoundHandler);
        app.exception(ReimbursementNotFoundException.class, reimbursementNotFoundHandler);
        app.exception(IllegalArgumentException.class, invalidArg);
    }
}
