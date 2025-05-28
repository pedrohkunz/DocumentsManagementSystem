package com.documents.management.system.views.screens;

import com.documents.management.system.common.GlobalVariables;
import com.documents.management.system.views.components.LabeledField;
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
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Cadastrar novo documento");
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        JTextField nameField = new JTextField(20);
        panel.add(LabeledField.create("Nome do documento:", nameField));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextArea contentArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(contentArea);
        panel.add(LabeledField.create("Conteúdo do documento:", scrollPane));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(new JButton("Cadastrar"));
        buttonPanel.add(new JButton("Cancelar"));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(buttonPanel);

        add(panel, new GridBagConstraints());

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
