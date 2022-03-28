package com.revature.dto;

import java.util.Objects;

public class ReimbursementDTO {
    private int reimbursementId;
    private double reimbursementAmount;
    private String reimbursementSubmitted;
    private String reimbursementResolved;
    private String reimbursementDescription;
    private String reimbursementReceipt;
    private String authorFullName;
    private int resolverId;
    private String resolverFullName;
    private int reimbursementStatusId;
    private String reimbursementStatus;
    private int reimbursementTypeId;
    private String reimbursementType;

    public ReimbursementDTO() {}

    public ReimbursementDTO(int reimbursementId, double reimbursementAmount, String reimbursementSubmitted, String reimbursementResolved, String reimbursementDescription, String reimbursementReceipt, String authorFullName, int resolverId, String resolverFullName, int reimbursementStatusId, String reimbursementStatus, int reimbursementTypeId, String reimbursementType) {
        this.reimbursementId = reimbursementId;
        this.reimbursementAmount = reimbursementAmount;
        this.reimbursementSubmitted = reimbursementSubmitted;
        this.reimbursementResolved = reimbursementResolved;
        this.reimbursementDescription = reimbursementDescription;
        this.reimbursementReceipt = reimbursementReceipt;
        this.authorFullName = authorFullName;
        this.resolverId = resolverId;
        this.resolverFullName = resolverFullName;
        this.reimbursementStatusId = reimbursementStatusId;
        this.reimbursementStatus = reimbursementStatus;
        this.reimbursementTypeId = reimbursementTypeId;
        this.reimbursementType = reimbursementType;
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

    public String getReimbursementSubmitted() {
        return reimbursementSubmitted;
    }

    public void setReimbursementSubmitted(String reimbursementSubmitted) {
        this.reimbursementSubmitted = reimbursementSubmitted;
    }

    public String getReimbursementResolved() {
        return reimbursementResolved;
    }

    public void setReimbursementResolved(String reimbursementResolved) {
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

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public String getResolverFullName() {
        return resolverFullName;
    }

    public void setResolverFullName(String resolverFullName) {
        this.resolverFullName = resolverFullName;
    }

    public int getReimbursementStatusId() {
        return reimbursementStatusId;
    }

    public void setReimbursementStatusId(int reimbursementStatusId) {
        this.reimbursementStatusId = reimbursementStatusId;
    }

    public String getReimbursementStatus() {
        return reimbursementStatus;
    }

    public void setReimbursementStatus(String reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }

    public int getReimbursementTypeId() {
        return reimbursementTypeId;
    }

    public void setReimbursementTypeId(int reimbursementTypeId) {
        this.reimbursementTypeId = reimbursementTypeId;
    }

    public String getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(String reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementDTO that = (ReimbursementDTO) o;
        return reimbursementId == that.reimbursementId && Double.compare(that.reimbursementAmount, reimbursementAmount) == 0 && resolverId == that.resolverId && reimbursementStatusId == that.reimbursementStatusId && reimbursementTypeId == that.reimbursementTypeId && Objects.equals(reimbursementSubmitted, that.reimbursementSubmitted) && Objects.equals(reimbursementResolved, that.reimbursementResolved) && Objects.equals(reimbursementDescription, that.reimbursementDescription) && Objects.equals(reimbursementReceipt, that.reimbursementReceipt) && Objects.equals(authorFullName, that.authorFullName) && Objects.equals(resolverFullName, that.resolverFullName) && Objects.equals(reimbursementStatus, that.reimbursementStatus) && Objects.equals(reimbursementType, that.reimbursementType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursementId, reimbursementAmount, reimbursementSubmitted, reimbursementResolved, reimbursementDescription, reimbursementReceipt, authorFullName, resolverId, resolverFullName, reimbursementStatusId, reimbursementStatus, reimbursementTypeId, reimbursementType);
    }

    @Override
    public String toString() {
        return "ReimbursementDTO{" +
                "reimbursementId=" + reimbursementId +
                ", reimbursementAmount=" + reimbursementAmount +
                ", reimbursementSubmitted='" + reimbursementSubmitted + '\'' +
                ", reimbursementResolved='" + reimbursementResolved + '\'' +
                ", reimbursementDescription='" + reimbursementDescription + '\'' +
                ", reimbursementReceipt='" + reimbursementReceipt + '\'' +
                ", authorFullName='" + authorFullName + '\'' +
                ", resolverId=" + resolverId +
                ", resolverFullName='" + resolverFullName + '\'' +
                ", reimbursementStatusId=" + reimbursementStatusId +
                ", reimbursementStatus='" + reimbursementStatus + '\'' +
                ", reimbursementTypeId=" + reimbursementTypeId +
                ", reimbursementType='" + reimbursementType + '\'' +
                '}';
    }
}
