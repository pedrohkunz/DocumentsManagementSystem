package com.documents.management.system.views.dialogs;

import javax.swing.*;
import java.awt.*;

public class QuitAppDialog {
    public static void create(Component parent) {
        int response = JOptionPane.showOptionDialog(
                parent,
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
