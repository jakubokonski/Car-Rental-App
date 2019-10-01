package com.carrental.model.enums;

public enum Colour {

    RED("Red"),
    BLACK("Black"),
    WHITE("White"),
    SILVER("Silver");

    private final String displayName;

    Colour(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
