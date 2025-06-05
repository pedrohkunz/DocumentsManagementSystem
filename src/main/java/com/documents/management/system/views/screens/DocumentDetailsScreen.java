package com.documents.management.system.views.screens;

import com.documents.management.system.common.GlobalVariables;
import com.documents.management.system.views.components.LabeledField;
import com.documents.management.system.views.dialogs.QuitAppDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DocumentDetailsScreen extends JFrame {

    public DocumentDetailsScreen() {
        setTitle("Detalhes do Documento");
        setSize(GlobalVariables.SCREEN_WIDTH.getValue(), GlobalVariables.SCREEN_HEIGHT.getValue());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                QuitAppDialog.create(DocumentDetailsScreen.this);
            }
        });

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Detalhes do Documento");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextField nameField = new JTextField(20);
        nameField.setEditable(false);
        panel.add(LabeledField.create("Nome:", nameField));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField dateField = new JTextField(20);
        dateField.setEditable(false);
        panel.add(LabeledField.create("Data:", dateField));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField originalSizeField = new JTextField(20);
        originalSizeField.setEditable(false);
        panel.add(LabeledField.create("Tamanho original:", originalSizeField));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField compressedSizeField = new JTextField(20);
        compressedSizeField.setEditable(false);
        panel.add(LabeledField.create("Tamanho comprimido:", compressedSizeField));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel contentLabel = new JLabel("Conteúdo do texto (descomprimido):");
        contentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(contentLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextArea contentArea = new JTextArea(5, 20);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setPreferredSize(new Dimension(500, 100));
        contentScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(contentScrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton deleteButton = new JButton("Excluir");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
        buttonsPanel.add(deleteButton);

        JButton recompressButton = new JButton("Recomprimir");
        recompressButton.setFont(new Font("Arial", Font.PLAIN, 12));
        buttonsPanel.add(recompressButton);

        JButton visualizeIndexesButton = new JButton("Visualizar índices");
        visualizeIndexesButton.setFont(new Font("Arial", Font.PLAIN, 12));
        buttonsPanel.add(visualizeIndexesButton);

        panel.add(buttonsPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(_ -> {
            new MainMenuScreen();
            dispose();
        });
        backButtonPanel.add(backButton);
        backButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(backButtonPanel);

        add(panel, new GridBagConstraints());

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}