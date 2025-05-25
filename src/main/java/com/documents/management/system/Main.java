package com.documents.management.system;

import com.documents.management.system.views.screens.MainMenuScreen;

public class Main {
    public static void main(String[] args) {
        try {
            new MainMenuScreen();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}