package com.documents.management.system.views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GenericButton {
    private static final Dimension BUTTONS_SIZE = new Dimension(
        300,
        50
    );

    public static JButton create(String text, Color textColor, ActionListener onClick) {
        JButton button = new JButton(text);

        button.setMaximumSize(BUTTONS_SIZE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMargin(new Insets(10, 10, 10, 10));

        button.setForeground(textColor);
        button.setFocusable(false);

        button.addActionListener(onClick);

        return button;
    }
}
