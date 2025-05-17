package com.documents.management.system.views.screens;

import com.documents.management.system.common.GlobalVariables;
import com.documents.management.system.views.components.GenericButton;
import com.documents.management.system.views.components.GenericImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Gerenciador de documentos");
        setSize(GlobalVariables.SCREEN_WIDTH.getValue(), GlobalVariables.SCREEN_HEIGHT.getValue());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        setLayout(new GridBagLayout());
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        panel.add(GenericImage.create(
            "app_icon.png",
            200,
            200
        ));

        panel.add(GenericButton.create(
            "Cadastrar novo documento",
            Color.BLACK,
            e -> {}
        ));

        panel.add(GenericButton.create(
            "Listar documentos cadastrados",
            Color.BLACK,
            e -> {}
        ));

        panel.add(GenericButton.create(
            "Buscar por palavra-chave",
            Color.BLACK,
            e -> {}
        ));

        panel.add(GenericButton.create(
            "Ver estatísticas e ordenações",
            Color.BLACK,
            e -> {}
        ));

        panel.add(GenericButton.create(
            "Gerenciar compressão e índices",
            Color.BLACK,
            e -> {}
        ));

        panel.add(GenericButton.create(
            "Sair",
            Color.RED,
            e -> { exit(); }
        ));

        add(panel, new GridBagConstraints());

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void exit() {
        int response = JOptionPane.showOptionDialog(
            this,
            "Você realmente deseja sair?",
            "Sair",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            new Object[]{"Sim", "Não"},
            "Sim"
        );

        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}