package com.revature.model;

import java.util.Objects;

public class UserRole {
    private int userRoleId;
    private int userRole;

    public UserRole() {}

    public UserRole(int userRoleId, int userRole) {
        this.userRoleId = userRoleId;
        this.userRole = userRole;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole1 = (UserRole) o;
        return userRoleId == userRole1.userRoleId && userRole == userRole1.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleId, userRole);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleId=" + userRoleId +
                ", userRole=" + userRole +
                '}';
    }
}
