package com.revature.controller;

import com.revature.dto.ReimbursementDTO;
import com.revature.service.JWTService;
import com.revature.service.ReimbursementService;
import com.revature.utility.InfoValidator;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.UploadedFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.List;

public class ReimbursementController implements Controller {
    private JWTService jwtService;
    private ReimbursementService reimbursementService;
    private String authorization = "Authorization";
    private String userRole = "user_role";
    
    public ReimbursementController() {
        this.jwtService = JWTService.getInstance();
        this.reimbursementService = new ReimbursementService();
    }

    // ============================== for employees ============================== //
    private final Handler getReimbursementsByUserId = (ctx) -> {
        if(ctx.header(authorization) != null) {
            String jwt = ctx.header(authorization).split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);

            if(!token.getBody().get(userRole).equals(2)) {
                throw new UnauthorizedResponse("This endpoint is used by employees only.");
            }

            String userId = ctx.pathParam("user_id");
            int intUserId = InfoValidator.isValidId(userId);
            if (!token.getBody().get("user_id").equals(intUserId)) {
                throw new UnauthorizedResponse("You can only obtain your reimbursement request.");
            }

            List<ReimbursementDTO> reimbursements = this.reimbursementService.getAllReimbursementsByUserId(intUserId);
            ctx.json(reimbursements);
        } else {
            ctx.result("You have to login.");
        }
    };

    private final Handler createReimbursement = (ctx) -> {
        if(ctx.header(authorization) != null) {
            String jwt = ctx.header(authorization).split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);

            if(!token.getBody().get(userRole).equals(2)) {
                throw new UnauthorizedResponse("This endpoint is used by employees only.");
            }

            String userId = ctx.pathParam("user_id");

            String amount = ctx.formParam("reimbursementAmount");
            String description = ctx.formParam("reimbursementDescription");
            String typeId = ctx.formParam("reimbursementTypeId");
            UploadedFile receiptFile = ctx.uploadedFile("reimbursementReceipt");

            ReimbursementDTO createdReimbursementDTO = reimbursementService.createReimbursement(userId, amount, description, typeId, receiptFile);
            ctx.json(createdReimbursementDTO);
        } else {
            ctx.result("You have to login.");
        }
    };

    private final Handler updateReimbursement = (ctx) -> {
        if(ctx.header(authorization) != null) {
            String jwt = ctx.header(authorization).split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);

            if(!token.getBody().get(userRole).equals(2)) {
                throw new UnauthorizedResponse("This endpoint is used by employees only.");
            }

            String userId = ctx.pathParam("user_id");
            String reimbId = ctx.pathParam("reimb_id");

            String amount = ctx.formParam("reimbursementAmount");
            String description = ctx.formParam("reimbursementDescription");
            String typeId = ctx.formParam("reimbursementTypeId");
            UploadedFile receiptFile = ctx.uploadedFile("reimbursementReceipt");

            ReimbursementDTO updatedReimbursementDTO = reimbursementService.updateReimbursement(userId, reimbId, amount, description, typeId, receiptFile);
            ctx.json(updatedReimbursementDTO);
        } else {
            ctx.result("You have to login.");
        }
    };

    private final Handler deleteReimbursement = (ctx) -> {
        if(ctx.header(authorization) != null) {
            String jwt = ctx.header(authorization).split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);

            if(!token.getBody().get(userRole).equals(2)) {
                throw new UnauthorizedResponse("This endpoint is used by employees only.");
            }

            String userId = ctx.pathParam("user_id");
            String reimbId = ctx.pathParam("reimb_id");

            boolean deleted = reimbursementService.deleteReimbursement(userId, reimbId);
            ctx.json(deleted);
        } else {
            ctx.result("You have to login.");
        }
    };

    // ============================== for managers ============================== //
    private final Handler getAllReimbursements = (ctx) -> {
        if(ctx.header(authorization) != null) {
            String jwt = ctx.header(authorization).split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);

            if(!token.getBody().get(userRole).equals(1)) {
                throw new UnauthorizedResponse("This endpoint is used by managers only.");
            }

            List<ReimbursementDTO> reimbursements = this.reimbursementService.getAllReimbursements();
            ctx.json(reimbursements);
        } else {
            ctx.result("You have to login.");
        }
    };

    private final Handler authorizeReimbursement = (ctx) -> {
        if (ctx.header(authorization) != null) {
            String jwt = ctx.header(authorization).split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);

            if (!token.getBody().get(userRole).equals(1)) {
                throw new UnauthorizedResponse("This endpoint is used by managers only.");
            }

            String reimbursementId = ctx.pathParam("reimbursement_id");
            String authorizedStatusId = ctx.queryParam("authorizedStatusId");
            int resolverId = token.getBody().get("user_id", Integer.class);

            if (authorizedStatusId == null) {
                throw new IllegalArgumentException("You need to provide the authorized status.");
            }

            int updatedRow = this.reimbursementService.authorizeReimbursement(reimbursementId, authorizedStatusId, resolverId);
            ctx.json(updatedRow);
        } else {
            ctx.json("You have to login.");
        }
    };

    @Override
    public void mapEndpoints(Javalin app) {
        // ============================== for employees ============================== //
        // get all reimbursements by user id
        app.get("/users/{user_id}/reimbursements", getReimbursementsByUserId);

        // create a new reimbursement
        app.post("/users/{user_id}/reimbursements", createReimbursement);

        // update a pending reimbursement (change information only)
        app.put("/users/{user_id}/reimbursements/{reimb_id}", updateReimbursement);

        // delete a pending reimbursement
        app.delete("/users/{user_id}/reimbursements/{reimb_id}", deleteReimbursement);

        // ============================== for managers ============================== //
        // get all reimbursements
        app.get("/reimbursements", getAllReimbursements);

        // update a pending reimbursement (approve or deny request)
        app.patch("/reimbursements/{reimbursement_id}", authorizeReimbursement);
    }
}
