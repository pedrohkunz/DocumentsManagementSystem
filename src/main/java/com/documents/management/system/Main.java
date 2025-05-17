package com.documents.management.system;

import com.documents.management.system.views.screens.MainMenu;

public class Main {
    public static void main(String[] args) {
        try {
            new MainMenu();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}