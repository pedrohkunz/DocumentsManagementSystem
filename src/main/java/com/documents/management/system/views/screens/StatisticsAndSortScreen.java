package com.documents.management.system.views.screens;

import com.documents.management.system.common.GlobalVariables;
import com.documents.management.system.controllers.DocumentController;
import com.documents.management.system.engine.factories.SortAlgorithmFactory;
import com.documents.management.system.engine.factories.SortAlgorithmInterface;
import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;
import com.documents.management.system.views.dialogs.QuitAppDialog;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StatisticsAndSortScreen extends JFrame {
    private final DocumentController documentController = new DocumentController();
    private CustomLinkedList<Document> documentsList = documentController.getAllDocuments();
    private JTable table;

    private final JRadioButton selectionSortRadio = new JRadioButton("SelectionSort", true);
    private final JRadioButton quickSortRadio = new JRadioButton("QuickSort");
    private final JRadioButton mergeSortRadio = new JRadioButton("MergeSort");
    private final JRadioButton heapSortRadio = new JRadioButton("HeapSort");
    private final JRadioButton optimizedSortRadio = new JRadioButton("Escolha otimizada");

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

        String[] columnNames = {"Nome", "Conteúdo", "Tamanho original", "Tamanho comprimido", "Data de criação"};
        String[][] placeholderData = documentsList.toMatrix();
        table = new JTable(placeholderData, columnNames);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel algorithmLabel = new JLabel("Escolha o algoritmo de ordenação:");
        algorithmLabel.setFont(new Font("Arial", Font.BOLD, 14));
        algorithmLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(algorithmLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        ButtonGroup algorithmGroup = new ButtonGroup();
        algorithmGroup.add(selectionSortRadio);
        algorithmGroup.add(quickSortRadio);
        algorithmGroup.add(mergeSortRadio);
        algorithmGroup.add(heapSortRadio);
        algorithmGroup.add(optimizedSortRadio);

        JPanel algorithmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        algorithmPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        algorithmPanel.add(selectionSortRadio);
        algorithmPanel.add(quickSortRadio);
        algorithmPanel.add(mergeSortRadio);
        algorithmPanel.add(heapSortRadio);
        algorithmPanel.add(optimizedSortRadio);
        panel.add(algorithmPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel sortSectionLabel = new JLabel("Ordenar por:");
        sortSectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        sortSectionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(sortSectionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel sortButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        sortButtonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton sortByNameButton = new JButton("Nome");
        sortByNameButton.setFont(new Font("Arial", Font.PLAIN, 12));
        sortByNameButton.addActionListener(_ -> {
            SortAlgorithmInterface sortAlgorithm = getSelectedAlgorithm();
            documentsList = sortAlgorithm.sortByName(documentsList);
            refreshTable();
        });
        sortButtonsPanel.add(sortByNameButton);

        JButton sortBySizeButton = new JButton("Tamanho");
        sortBySizeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        sortBySizeButton.addActionListener(_ -> {
            SortAlgorithmInterface sortAlgorithm = getSelectedAlgorithm();
            documentsList = sortAlgorithm.sortByFileSize(documentsList);
            refreshTable();
        });
        sortButtonsPanel.add(sortBySizeButton);

        JButton sortByDateButton = new JButton("Data");
        sortByDateButton.setFont(new Font("Arial", Font.PLAIN, 12));
        sortByDateButton.addActionListener(_ -> {
            SortAlgorithmInterface sortAlgorithm = getSelectedAlgorithm();
            documentsList = sortAlgorithm.sortByCreatedAt(documentsList);
            refreshTable();
        });
        sortButtonsPanel.add(sortByDateButton);

        panel.add(sortButtonsPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel chartPanel = createPerformanceChart();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        chartPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(chartPanel);

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

    private SortAlgorithmInterface getSelectedAlgorithm() {
        if (selectionSortRadio.isSelected()) {
            return SortAlgorithmFactory.createSortAlgorithm("SelectionSort");
        } else if (quickSortRadio.isSelected()) {
            return SortAlgorithmFactory.createSortAlgorithm("QuickSort");
        } else if (mergeSortRadio.isSelected()) {
            return SortAlgorithmFactory.createSortAlgorithm("MergeSort");
        } else if (heapSortRadio.isSelected()) {
            return SortAlgorithmFactory.createSortAlgorithm("HeapSort");
        }
        
        return SortAlgorithmFactory.createOptimizedSortAlgorithm(documentsList);
    }

    private void refreshTable() {
        String[][] updatedData = documentsList.toMatrix();
        for (int row = 0; row < updatedData.length; row++) {
            for (int col = 0; col < updatedData[row].length; col++) {
                table.setValueAt(updatedData[row][col], row, col);
            }
        }
    }

    private JPanel createPerformanceChart() {
        String[] algorithms = {"SelectionSort", "QuickSort", "MergeSort", "HeapSort"};
        String[] criteria = {"Nome", "Tamanho", "Data"};

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String algorithm : algorithms) {
            SortAlgorithmInterface sortInstance = SortAlgorithmFactory.createSortAlgorithm(algorithm);
            for (String criterion : criteria) {
                CustomLinkedList<Document> listCopy = documentsList;
                long start = System.nanoTime();

                switch (criterion) {
                    case "Nome":
                        sortInstance.sortByName(listCopy);
                        break;
                    case "Tamanho":
                        sortInstance.sortByFileSize(listCopy);
                        break;
                    case "Data":
                        sortInstance.sortByCreatedAt(listCopy);
                        break;
                }

                long duration = System.nanoTime() - start;
                dataset.addValue(duration / 1_000_000.0, algorithm, criterion);
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
            "Tempo de Execução dos Algoritmos",
            "Critério",
            "Tempo (ms)",
            dataset
        );

        return new ChartPanel(barChart);
    }

}
