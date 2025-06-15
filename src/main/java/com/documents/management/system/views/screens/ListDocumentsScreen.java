package com.documents.management.system.views.screens;

import com.documents.management.system.common.GlobalVariables;
import com.documents.management.system.controllers.DocumentController;
import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;
import com.documents.management.system.views.dialogs.QuitAppDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListDocumentsScreen extends JFrame {
    private DocumentController documentController = new DocumentController();

    CustomLinkedList<Document> documents = documentController.getAllDocuments();

    public ListDocumentsScreen() {
        setTitle("Listar documentos cadastrados");
        setSize(GlobalVariables.SCREEN_WIDTH.getValue(), GlobalVariables.SCREEN_HEIGHT.getValue());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                QuitAppDialog.create(ListDocumentsScreen.this);
            }
        });

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Documentos Cadastrados");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        if (documents == null || documents.isEmpty()) {
            JLabel noDocumentsLabel = new JLabel("Nenhum documento cadastrado.");
            noDocumentsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noDocumentsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(noDocumentsLabel);
        } else {
            String[] columnNames = {"Nome do Documento", "Conteúdo"};
            String[][] data = documents.toMatrix();
            
            JTable table = new JTable(data, columnNames);
            table.setFillsViewportHeight(true);
            table.setRowHeight(30);
            table.setEnabled(false);
            table.setDefaultEditor(Object.class, null);
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
            table.setFont(new Font("Arial", Font.PLAIN, 12));
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());
                    if (row >= 0) {
                        Document selectedDocument = documents.get(row);
                        new DocumentDetailsScreen(selectedDocument);
                        dispose();
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(500, 300));
            scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(scrollPane);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(_ -> {
            new MainMenuScreen();
            dispose();
        });
        buttonPanel.add(backButton);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(buttonPanel);

        add(panel, new GridBagConstraints());

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}