package com.documents.management.system.common;

import java.io.File;
import java.time.LocalDateTime;

public class Utils {
    public static String getDocumentsDirectory() {
        String userHome = System.getProperty("user.home");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return userHome + File.separator + "Documents";
        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
            return userHome + File.separator + "Documents";
        } else {
            return userHome;
        }
    }

    public static String formatDate(LocalDateTime createdAt) {
        return String.format(
            "%02d/%02d/%04d",
            createdAt.getDayOfMonth(),
            createdAt.getMonthValue(),
            createdAt.getYear()
        );
    }
}
