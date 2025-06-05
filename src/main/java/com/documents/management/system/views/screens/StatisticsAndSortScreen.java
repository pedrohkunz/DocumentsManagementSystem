package com.documents.management.system.views.screens;

import com.documents.management.system.common.GlobalVariables;
import com.documents.management.system.views.dialogs.QuitAppDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StatisticsAndSortScreen extends JFrame {

    public StatisticsAndSortScreen() {
        setTitle("Estatísticas e Ordenações");
        setSize(GlobalVariables.SCREEN_WIDTH.getValue(), GlobalVariables.SCREEN_HEIGHT.getValue());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                QuitAppDialog.create(StatisticsAndSortScreen.this);
            }
        });

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Estatísticas e Ordenações");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel tableSectionLabel = new JLabel("Tabela de documentos:");
        tableSectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tableSectionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(tableSectionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        String[] columnNames = {"Nome", "Tamanho original", "Tamanho comprimido", "Data de criação"};
        String[][] placeholderData = new String[0][0];
        JTable table = new JTable(placeholderData, columnNames);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 150));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel sortSectionLabel = new JLabel("Botões para ordenar por:");
        sortSectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        sortSectionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(sortSectionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel sortButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        sortButtonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton sortByNameButton = new JButton("Nome");
        sortByNameButton.setFont(new Font("Arial", Font.PLAIN, 12));
        sortButtonsPanel.add(sortByNameButton);

        JButton sortBySizeButton = new JButton("Tamanho");
        sortBySizeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        sortButtonsPanel.add(sortBySizeButton);

        JButton sortByDateButton = new JButton("Data");
        sortByDateButton.setFont(new Font("Arial", Font.PLAIN, 12));
        sortButtonsPanel.add(sortByDateButton);

        panel.add(sortButtonsPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel chartSectionLabel = new JLabel("Gráficos simples de comparação (se GUI):");
        chartSectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        chartSectionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(chartSectionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel chartPanel = new JPanel();
        chartPanel.setPreferredSize(new Dimension(500, 150));
        chartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        chartPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(chartPanel);
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