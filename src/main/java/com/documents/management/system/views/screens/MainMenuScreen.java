package com.documents.management.system.views.screens;

import com.documents.management.system.common.GlobalVariables;
import com.documents.management.system.views.components.GenericButton;
import com.documents.management.system.views.components.GenericImage;
import com.documents.management.system.views.dialogs.QuitAppDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenuScreen extends JFrame {
    public MainMenuScreen() {
        
        setTitle("Gerenciador de documentos");
        setSize(GlobalVariables.SCREEN_WIDTH.getValue(), GlobalVariables.SCREEN_HEIGHT.getValue());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                QuitAppDialog.create(MainMenuScreen.this);
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
            Color.BLUE,
            _ -> {
                new CreateDocumentScreen();
                dispose();
            }
        ));

        panel.add(GenericButton.create(
            "Listar documentos cadastrados",
            Color.BLACK,
            _ -> {
                new ListDocumentsScreen();
                dispose();
            }
        ));

        panel.add(GenericButton.create(
            "Buscar por palavra-chave",
            Color.BLACK,
            _ -> {
                new SearchDocumentsScreen();
                dispose();
            }
        ));

        panel.add(GenericButton.create(
            "Ver estatísticas e ordenações",
            Color.BLACK,
            _ -> {
                new StatisticsAndSortScreen();
                dispose();
            }
        ));

        panel.add(GenericButton.create(
            "Sair",
            Color.RED,
            _ -> {
                QuitAppDialog.create(this);
            }
        ));

        add(panel, new GridBagConstraints());

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}