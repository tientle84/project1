package com.revature.controller;

import com.revature.exception.FailedRegisterException;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.exception.UserNotFoundException;
import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.FailedLoginException;

public class ExceptionController implements Controller {
    //    private static Logger logger = LoggerFactory.getLogger(ClientService.class);

    private ExceptionHandler<FailedLoginException> failedLoginExceptionHandler = (exception, ctx) -> {
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<FailedRegisterException> failedRegisterExceptionHandler = (exception, ctx) -> {
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<UserNotFoundException> userNotFoundHandler = (exception, ctx) -> {
        //logger.warn("User does not exist. Exception message is " + e.getMessage());
        ctx.status(404);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<ReimbursementNotFoundException> reimbursementNotFoundHandler = (e, ctx) -> {
        //logger.warn("Reimbursement does not exist. Exception message is " + e.getMessage());
        ctx.status(404);
        ctx.json(e.getMessage());
    };

    private ExceptionHandler<IllegalArgumentException> invalidArg = (exception, ctx) -> {
        //logger.warn("User attempted to enter invalid data. Exception message is " + e.getMessage());
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
