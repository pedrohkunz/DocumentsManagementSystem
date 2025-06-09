package com.documents.management.system.views.screens;

import com.documents.management.system.common.GlobalVariables;
import com.documents.management.system.controllers.DocumentController;
import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;
import com.documents.management.system.views.components.LabeledField;
import com.documents.management.system.views.dialogs.QuitAppDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class SearchDocumentsScreen extends JFrame {
    private DocumentController documentController = new DocumentController();
    private JPanel resultsPanel;

    public SearchDocumentsScreen() {
        setTitle("Buscar documentos por palavra-chave");
        setSize(GlobalVariables.SCREEN_WIDTH.getValue(), GlobalVariables.SCREEN_HEIGHT.getValue());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                QuitAppDialog.create(SearchDocumentsScreen.this);
            }
        });

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Buscar Documentos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel searchMethod = new JLabel("Como você gostaria de buscar os documentos?");
        searchMethod.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(searchMethod);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JRadioButton searchInBTree = new JRadioButton("BTree");
        searchInBTree.setSelected(true);
        searchInBTree.setAlignmentX(Component.LEFT_ALIGNMENT);

        JRadioButton searchInAVLTree = new JRadioButton("AVLTree");
        searchInAVLTree.setSelected(false);
        searchInAVLTree.setAlignmentX(Component.LEFT_ALIGNMENT);

        // JRadioButton searchWithRelevanceIndex = new JRadioButton("");
        // searchWithRelevanceIndex.setSelected(false);
        // searchWithRelevanceIndex.setAlignmentX(Component.LEFT_ALIGNMENT);

        ButtonGroup group = new ButtonGroup();
        group.add(searchInBTree);
        group.add(searchInAVLTree);
        //group.add(searchWithRelevanceIndex);

        panel.add(searchInBTree);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(searchInAVLTree);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        //panel.add(searchWithRelevanceIndex);
        //panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextField searchField = new JTextField(20);
        panel.add(LabeledField.create("Palavra-chave:", searchField));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton searchButton = new JButton("Pesquisar");
        searchButton.addActionListener(_ -> updateResults(searchField.getText().trim(), group));
        panel.add(searchButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setAutoscrolls(rootPaneCheckingEnabled);
        resultsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        updateResults("", group);
        panel.add(resultsPanel);

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

    private void updateResults(String keyword, ButtonGroup group) {
        resultsPanel.removeAll();

        String selectedSearchMethod = null;
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                selectedSearchMethod = button.getText();
                break;
            }
        }

        CustomLinkedList<Document> documents = documentController.searchDocuments(keyword, selectedSearchMethod);
        List<String[]> documentList = new ArrayList<>();
        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.get(i);
            documentList.add(new String[]{doc.getTitle(), doc.getContent()});
        }

        if (documentList.isEmpty()) {
            JLabel noDocumentsLabel = new JLabel("Nenhum documento encontrado.");
            noDocumentsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noDocumentsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            resultsPanel.add(noDocumentsLabel);
        } else {
            JLabel resultsLabel = new JLabel("Arquivos que possuem a palavra-chave no conteúdo:");
            resultsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            resultsPanel.add(resultsLabel);

            String[] columnNames = {"Nome do Documento", "Conteúdo"};
            String[][] data = documentList.toArray(new String[0][0]);
            JTable table = new JTable(data, columnNames);
            table.setFillsViewportHeight(true);
            table.setRowHeight(30);
            table.setEnabled(false);
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
            table.setFont(new Font("Arial", Font.PLAIN, 12));

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(100, 100));
            scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

            resultsPanel.add(scrollPane);
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
}