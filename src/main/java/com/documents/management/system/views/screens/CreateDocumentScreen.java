package com.documents.management.system.views.screens;

import com.documents.management.system.common.GlobalVariables;
import com.documents.management.system.views.dialogs.QuitAppDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateDocumentScreen extends JFrame {
    public CreateDocumentScreen() {
        setTitle("Cadastrar novo documento");
        setSize(GlobalVariables.SCREEN_WIDTH.getValue(), GlobalVariables.SCREEN_HEIGHT.getValue());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                QuitAppDialog.create(CreateDocumentScreen.this);
            }
        });

        setLayout(new GridBagLayout());
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        panel.add(new JLabel("Cadastrar novo documento"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(new JLabel("Nome do documento:"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(new JTextField(20));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(new JLabel("Conteúdo do documento:"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(new JTextArea(10, 20));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        buttonPanel.add(new JButton("Cadastrar"));
        buttonPanel.add(new JButton("Cancelar"));

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(buttonPanel);

        add(panel, new GridBagConstraints());

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
