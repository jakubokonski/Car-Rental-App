package com.carrental.model.enums;

public enum RentalStatus {

    AVAILABLE("Available"),
    BOOKED("Booked"),
    BROKEN("Broken"),
    RENT("Rent");

    private final String displayName;

    RentalStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
