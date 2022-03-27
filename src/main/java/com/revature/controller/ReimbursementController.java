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
    
    public ReimbursementController() {
        this.jwtService = new JWTService();
        this.reimbursementService = new ReimbursementService();
    }

//    private final Handler getAllReimbursements = (ctx) -> {
//        // get path param and query params
//        String clientId = ctx.pathParam("client_id");
//        String amountGreatThanParam = ctx.queryParam("amountGreatThan");
//        String amountLessThanParam = ctx.queryParam("amountLessThan");
//
//        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursements(clientId, amountGreatThanParam, amountLessThanParam);
//
//        // reimbursement may be an empty list when the client doesn't have any reimbursement
//        // then we will print out a message if the client doesn't have any reimbursement
//        if(reimbursements.isEmpty()){
//            ctx.json("Could not find any reimbursement.");
//        } else {
//            ctx.json(reimbursements);
//        }
//    };

    // ============================== for employees ============================== //
    private final Handler getReimbursementsByUserId = (ctx) -> {

//        if(ctx.cookie("jwt") != null) {
//            String jwt = ctx.cookie("jwt");
//            Jws<Claims> token = this.jwtService.parseJwt(jwt);
//
//            if(!token.getBody().get("user_role").equals(2)) {
//                throw new UnauthorizedResponse("This endpoint is used by employees only.");
//            }
//
//            String userId = ctx.pathParam("user_id");
//            int intUserId = InfoValidator.isValidId(userId);
//            if (!token.getBody().get("user_id").equals(intUserId)) {
//                throw new UnauthorizedResponse("You can only obtain your reimbursement request.");
//            }
//
//            List<Reimbursement> reimbursements = this.reimbursementService.getAllReimbursementsByUserId(intUserId);
//            ctx.json(reimbursements);
//        } else {
//            ctx.result("You have to login.");
//        }

        if(ctx.header("Authorization") != null) {
            String jwt = ctx.header("Authorization").split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);

            if(!token.getBody().get("user_role").equals(2)) {
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
        String userId = ctx.pathParam("user_id");

        String amount = ctx.formParam("reimbursementAmount");
        String description = ctx.formParam("reimbursementDescription");
        String typeId = ctx.formParam("reimbursementTypeId");
        UploadedFile receiptFile = ctx.uploadedFile("reimbursementReceipt");

        ReimbursementDTO createdReimbursementDTO = reimbursementService.createReimbursement(userId, amount, description, typeId, receiptFile);
        ctx.json(createdReimbursementDTO);
    };

    private final Handler updateReimbursement = (ctx) -> {
        String userId = ctx.pathParam("user_id");
        String reimbId = ctx.pathParam("reimb_id");

        String amount = ctx.formParam("reimbursementAmount");
        String description = ctx.formParam("reimbursementDescription");
        String typeId = ctx.formParam("reimbursementTypeId");
        UploadedFile receiptFile = ctx.uploadedFile("reimbursementReceipt");

        ReimbursementDTO updatedReimbursementDTO = reimbursementService.updateReimbursement(userId, reimbId, amount, description, typeId, receiptFile);
        ctx.json(updatedReimbursementDTO);
    };

    private final Handler deleteReimbursement = (ctx) -> {
        String userId = ctx.pathParam("user_id");
        String reimbId = ctx.pathParam("reimb_id");

        boolean deleted = reimbursementService.deleteReimbursement(userId, reimbId);
        ctx.json(deleted);
    };

    private final Handler uploadFile = (ctx) -> {

    };

    // ============================== for managers ============================== //
    private final Handler getAllReimbursements = (ctx) -> {

    };

    private final Handler authorizeReimbursement = (ctx) -> {

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

        app.post("/upload", uploadFile);

        // ============================== for managers ============================== //
        // get all reimbursements
        app.get("/reimbursements", getAllReimbursements);

        // update a pending reimbursement (approve or deny request)
        app.get("/reimbursements/{reimbursement_id}", authorizeReimbursement);
    }
}
