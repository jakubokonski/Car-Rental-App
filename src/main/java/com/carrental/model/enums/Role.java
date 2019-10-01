package com.carrental.model.enums;

public enum Role {
    OWNER("Owner", true),
    MANAGER("Manager"),
    EMPLOYEE("Employee"),
    ADMIN("Admin");

    private final String displayName;
    private boolean isOwner;

    Role(String displayName) {
        this.displayName = displayName;
    }

    Role(String displayName, final boolean isOwner) {
        this(displayName);
        this.isOwner = isOwner;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isOwner() {
        return isOwner;
    }

}
