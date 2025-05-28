package com.documents.management.system.views.components;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabeledField {
    public static JPanel create(String labelText, JComponent inputComponent) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputComponent.setAlignmentX(Component.LEFT_ALIGNMENT);

        container.add(label);
        container.add(Box.createRigidArea(new Dimension(0, 5)));
        container.add(inputComponent);

        return container;
    }
}
