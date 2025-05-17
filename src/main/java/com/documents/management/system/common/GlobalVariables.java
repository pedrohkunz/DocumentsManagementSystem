package com.documents.management.system.common;

public enum GlobalVariables {
    SCREEN_WIDTH(800),
    SCREEN_HEIGHT(600);

    private final int value;

    GlobalVariables(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
