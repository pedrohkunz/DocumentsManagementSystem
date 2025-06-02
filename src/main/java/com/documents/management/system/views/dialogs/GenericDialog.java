package com.documents.management.system.views.dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GenericDialog {
    public static void create(String title, String message) {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setModal(true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        dialog.add(messageLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(_ -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(new EmptyBorder(0, 20, 10, 20));
        buttonPanel.add(okButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
