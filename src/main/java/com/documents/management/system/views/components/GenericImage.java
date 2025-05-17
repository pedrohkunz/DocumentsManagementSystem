package com.documents.management.system.views.components;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GenericImage {
    public static JLabel create(String path, int width, int height) {
        URL resource = GenericImage.class.getClassLoader().getResource(path);
        if (resource == null) {
            throw new RuntimeException("Image not found: " + path);
        }

        ImageIcon originalIcon = new ImageIcon(resource);
        Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(resizedImage);

        JLabel label = new JLabel(icon);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        return label;
    }
}
