package com.carrental.model.enums;

public enum RoleEmployees {
    MANAGER("Manager"),
    EMPLOYEE("Employee"),
    ADMIN("Admin");

    private final String displayName;

    RoleEmployees(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
