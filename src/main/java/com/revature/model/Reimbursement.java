package com.revature.model;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Reimbursement {
    private int reimbursementId;
    private double reimbursementAmount;
    private Date reimbursementSubmitted;
    private Date reimbursementResolved;
    private String reimbursementDescription;
    private String reimbursementReceipt;
    private int reimbursementAuthor;
    private int reimbursementResolver;
    private int reimbursementStatusId;
    private int reimbursementTypeId;

    public Reimbursement() {}

    public Reimbursement(int reimbursementId, double reimbursementAmount, Date reimbursementSubmitted, Date reimbursementResolved, String reimbursementDescription, String reimbursementReceipt, int reimbursementAuthor, int reimbursementResolver, int reimbursementStatusId, int reimbursementTypeId) {
        this.reimbursementId = reimbursementId;
        this.reimbursementAmount = reimbursementAmount;
        this.reimbursementSubmitted = reimbursementSubmitted;
        this.reimbursementResolved = reimbursementResolved;
        this.reimbursementDescription = reimbursementDescription;
        this.reimbursementReceipt = reimbursementReceipt;
        this.reimbursementAuthor = reimbursementAuthor;
        this.reimbursementResolver = reimbursementResolver;
        this.reimbursementStatusId = reimbursementStatusId;
        this.reimbursementTypeId = reimbursementTypeId;
    }

    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public double getReimbursementAmount() {
        return reimbursementAmount;
    }

    public void setReimbursementAmount(double reimbursementAmount) {
        this.reimbursementAmount = reimbursementAmount;
    }

    public Date getReimbursementSubmitted() {
        return reimbursementSubmitted;
    }

    public void setReimbursementSubmitted(Date reimbursementSubmitted) {
        this.reimbursementSubmitted = reimbursementSubmitted;
    }

    public Date getReimbursementResolved() {
        return reimbursementResolved;
    }

    public void setReimbursementResolved(Date reimbursementResolved) {
        this.reimbursementResolved = reimbursementResolved;
    }

    public String getReimbursementDescription() {
        return reimbursementDescription;
    }

    public void setReimbursementDescription(String reimbursementDescription) {
        this.reimbursementDescription = reimbursementDescription;
    }

    public String getReimbursementReceipt() {
        return reimbursementReceipt;
    }

    public void setReimbursementReceipt(String reimbursementReceipt) {
        this.reimbursementReceipt = reimbursementReceipt;
    }

    public int getReimbursementAuthor() {
        return reimbursementAuthor;
    }

    public void setReimbursementAuthor(int reimbursementAuthor) {
        this.reimbursementAuthor = reimbursementAuthor;
    }

    public int getReimbursementResolver() {
        return reimbursementResolver;
    }

    public void setReimbursementResolver(int reimbursementResolver) {
        this.reimbursementResolver = reimbursementResolver;
    }

    public int getReimbursementStatusId() {
        return reimbursementStatusId;
    }

    public void setReimbursementStatusId(int reimbursementStatusId) {
        this.reimbursementStatusId = reimbursementStatusId;
    }

    public int getReimbursementTypeId() {
        return reimbursementTypeId;
    }

    public void setReimbursementTypeId(int reimbursementTypeId) {
        this.reimbursementTypeId = reimbursementTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reimbursementId == that.reimbursementId && Double.compare(that.reimbursementAmount, reimbursementAmount) == 0 && reimbursementAuthor == that.reimbursementAuthor && reimbursementResolver == that.reimbursementResolver && reimbursementStatusId == that.reimbursementStatusId && reimbursementTypeId == that.reimbursementTypeId && Objects.equals(reimbursementSubmitted, that.reimbursementSubmitted) && Objects.equals(reimbursementResolved, that.reimbursementResolved) && Objects.equals(reimbursementDescription, that.reimbursementDescription) && Objects.equals(reimbursementReceipt, that.reimbursementReceipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursementId, reimbursementAmount, reimbursementSubmitted, reimbursementResolved, reimbursementDescription, reimbursementReceipt, reimbursementAuthor, reimbursementResolver, reimbursementStatusId, reimbursementTypeId);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", reimbursementAmount=" + reimbursementAmount +
                ", reimbursementSubmitted=" + reimbursementSubmitted +
                ", reimbursementResolved=" + reimbursementResolved +
                ", reimbursementDescription='" + reimbursementDescription + '\'' +
                ", reimbursementReceipt='" + reimbursementReceipt + '\'' +
                ", reimbursementAuthor=" + reimbursementAuthor +
                ", reimbursementResolver=" + reimbursementResolver +
                ", reimbursementStatusId=" + reimbursementStatusId +
                ", reimbursementTypeId=" + reimbursementTypeId +
                '}';
    }
}
