package com.example.plife.model.enums;

public enum Role {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER"),
    ROLE_COMPANY("COMPANY");

    private String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
