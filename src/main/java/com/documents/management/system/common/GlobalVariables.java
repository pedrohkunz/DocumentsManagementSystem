package com.documents.management.system.common;

public enum GlobalVariables {
    SCREEN_WIDTH(1200),
    SCREEN_HEIGHT(800),;

    private final int value;

    GlobalVariables(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
