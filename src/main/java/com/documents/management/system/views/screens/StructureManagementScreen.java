package com.documents.management.system.views.screens;

import com.documents.management.system.common.GlobalVariables;
import com.documents.management.system.views.dialogs.QuitAppDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StructureManagementScreen extends JFrame {

    public StructureManagementScreen() {
        setTitle("Gerenciamento de Estruturas");
        setSize(GlobalVariables.SCREEN_WIDTH.getValue(), GlobalVariables.SCREEN_HEIGHT.getValue());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                QuitAppDialog.create(StructureManagementScreen.this);
            }
        });

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Gerenciamento de Estruturas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton updateIndexButton = new JButton("Atualizar índice invertido");
        updateIndexButton.setFont(new Font("Arial", Font.PLAIN, 12));
        buttonsPanel.add(updateIndexButton);

        JButton visualizeAVLButton = new JButton("Visualizar árvore AVL de palavras");
        visualizeAVLButton.setFont(new Font("Arial", Font.PLAIN, 12));
        buttonsPanel.add(visualizeAVLButton);

        JButton viewBTreeButton = new JButton("Ver B-Tree de metadados");
        viewBTreeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        buttonsPanel.add(viewBTreeButton);

        JButton forceRecompressionButton = new JButton("Forçar recompressão Huffman");
        forceRecompressionButton.setFont(new Font("Arial", Font.PLAIN, 12));
        buttonsPanel.add(forceRecompressionButton);

        JButton treeOrGraphButton = new JButton("Árvore ou gráfico textual (para CLI)");
        treeOrGraphButton.setFont(new Font("Arial", Font.PLAIN, 12));
        buttonsPanel.add(treeOrGraphButton);

        JButton logsButton = new JButton("Logs de ações");
        logsButton.setFont(new Font("Arial", Font.PLAIN, 12));
        buttonsPanel.add(logsButton);

        panel.add(buttonsPanel);

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