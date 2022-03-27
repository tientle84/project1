package com.revature.dao;

import io.javalin.http.UploadedFile;

public class ReimbursementReqDTO {
    private String userId;
    private double reimbursementAmount;
    private String reimbursementDescription;
    private String reimbursementTypeId;
    private UploadedFile reimbursementReceipt;

    private String reimbursementType;

}
