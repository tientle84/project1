package com.revature.model;
import java.util.Objects;

public class Reimbursement {
    private int reimbursementId;
    private double reimbursementAmount;
    private String reimbursementSubmitted;
    private String reimbursementResolved;
    private String reimbursementDescription;
    private String reimbursementReceipt;
    private User reimbursementAuthor;
    private User reimbursementResolver;
    private ReimbursementStatus reimbursementStatus;
    private ReimbursementType reimbursementType;

    public Reimbursement() {}

    public Reimbursement(int reimbursementId, double reimbursementAmount, String reimbursementSubmitted, String reimbursementResolved, String reimbursementDescription, String reimbursementReceipt, User reimbursementAuthor, User reimbursementResolver, ReimbursementStatus reimbursementStatus, ReimbursementType reimbursementType) {
        this.reimbursementId = reimbursementId;
        this.reimbursementAmount = reimbursementAmount;
        this.reimbursementSubmitted = reimbursementSubmitted;
        this.reimbursementResolved = reimbursementResolved;
        this.reimbursementDescription = reimbursementDescription;
        this.reimbursementReceipt = reimbursementReceipt;
        this.reimbursementAuthor = reimbursementAuthor;
        this.reimbursementResolver = reimbursementResolver;
        this.reimbursementStatus = reimbursementStatus;
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

    public User getReimbursementAuthor() {
        return reimbursementAuthor;
    }

    public void setReimbursementAuthor(User reimbursementAuthor) {
        this.reimbursementAuthor = reimbursementAuthor;
    }

    public User getReimbursementResolver() {
        return reimbursementResolver;
    }

    public void setReimbursementResolver(User reimbursementResolver) {
        this.reimbursementResolver = reimbursementResolver;
    }

    public ReimbursementStatus getReimbursementStatus() {
        return reimbursementStatus;
    }

    public void setReimbursementStatus(ReimbursementStatus reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }

    public ReimbursementType getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(ReimbursementType reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reimbursementId == that.reimbursementId && Double.compare(that.reimbursementAmount, reimbursementAmount) == 0 && Objects.equals(reimbursementSubmitted, that.reimbursementSubmitted) && Objects.equals(reimbursementResolved, that.reimbursementResolved) && Objects.equals(reimbursementDescription, that.reimbursementDescription) && Objects.equals(reimbursementReceipt, that.reimbursementReceipt) && Objects.equals(reimbursementAuthor, that.reimbursementAuthor) && Objects.equals(reimbursementResolver, that.reimbursementResolver) && Objects.equals(reimbursementStatus, that.reimbursementStatus) && Objects.equals(reimbursementType, that.reimbursementType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursementId, reimbursementAmount, reimbursementSubmitted, reimbursementResolved, reimbursementDescription, reimbursementReceipt, reimbursementAuthor, reimbursementResolver, reimbursementStatus, reimbursementType);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", reimbursementAmount=" + reimbursementAmount +
                ", reimbursementSubmitted='" + reimbursementSubmitted + '\'' +
                ", reimbursementResolved='" + reimbursementResolved + '\'' +
                ", reimbursementDescription='" + reimbursementDescription + '\'' +
                ", reimbursementReceipt='" + reimbursementReceipt + '\'' +
                ", reimbursementAuthor=" + reimbursementAuthor +
                ", reimbursementResolver=" + reimbursementResolver +
                ", reimbursementStatus=" + reimbursementStatus +
                ", reimbursementType=" + reimbursementType +
                '}';
    }
}
