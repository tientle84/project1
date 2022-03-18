package com.revature.model;

import java.util.Objects;

public class ReimbursementStatus {
    private int reimbursementStatusId;
    private String reimbursementStatus;

    public ReimbursementStatus() {}

    public ReimbursementStatus(int reimbursementStatusId, String reimbursementStatus) {
        this.reimbursementStatusId = reimbursementStatusId;
        this.reimbursementStatus = reimbursementStatus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementStatus that = (ReimbursementStatus) o;
        return reimbursementStatusId == that.reimbursementStatusId && Objects.equals(reimbursementStatus, that.reimbursementStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursementStatusId, reimbursementStatus);
    }

    @Override
    public String toString() {
        return "ReimbursementStatus{" +
                "reimbursementStatusId=" + reimbursementStatusId +
                ", reimbursementStatus='" + reimbursementStatus + '\'' +
                '}';
    }
}
