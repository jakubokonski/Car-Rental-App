package com.carrental.model.enums;

public enum Engine {
    ELECTRIC("Electric"),
    HYBRID("Hybrid"),
    PETROL("Petrol"),
    DIESEL("Diesel");

    private String displayName;

    Engine(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
