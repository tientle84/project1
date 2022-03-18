package com.revature.model;

import java.util.Objects;

public class ReimbursementType {
    private int reimbursementTypeId;
    private String reimbursementType;

    public ReimbursementType() {}

    public ReimbursementType(int reimbursementTypeId, String reimbursementType) {
        this.reimbursementTypeId = reimbursementTypeId;
        this.reimbursementType = reimbursementType;
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
        ReimbursementType that = (ReimbursementType) o;
        return reimbursementTypeId == that.reimbursementTypeId && Objects.equals(reimbursementType, that.reimbursementType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursementTypeId, reimbursementType);
    }

    @Override
    public String toString() {
        return "ReimbursementType{" +
                "reimbursementTypeId=" + reimbursementTypeId +
                ", reimbursementType='" + reimbursementType + '\'' +
                '}';
    }
}
