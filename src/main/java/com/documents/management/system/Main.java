package com.documents.management.system;

import com.documents.management.system.models.Document;
import com.documents.management.system.views.screens.MainMenuScreen;

public class Main {
    public static void main(String[] args) {
        try {
            Document.populateStructures();
            new MainMenuScreen();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}