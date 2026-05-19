package com.simplecore.erp.client.gui.components.poppup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class CustomPanelPopup {
    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Panel Emergente Personalizado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Botón para mostrar el panel emergente
        JButton showPopupButton = new JButton("Mostrar Panel");
        showPopupButton.setBounds(150, 100, 150, 30);

        // Crear un JDialog para usarlo como el panel emergente
        JDialog popupDialog = new JDialog((Frame) null, false); // No modal
        popupDialog.setUndecorated(true); // Sin bordes ni decoración
        popupDialog.setSize(250, 150);

        // Crear contenido para el panel
        JPanel panelContent = new JPanel();
        panelContent.setLayout(new GridLayout(3, 1, 10, 10));
        panelContent.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.GRAY, 2), // Borde exterior
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Espaciado interno
        ));
        panelContent.setBackground(new Color(240, 240, 240));

        // Añadir componentes al panel
        JLabel label = new JLabel("Ingrese su nombre:");
        JTextField textField = new JTextField();
        JButton submitButton = new JButton("Aceptar");

        panelContent.add(label);
        panelContent.add(textField);
        panelContent.add(submitButton);

        popupDialog.add(panelContent);
        
        submitButton.addActionListener(e->JOptionPane.showMessageDialog(null, textField.getText()));

        // Mostrar el panel emergente cerca del botón
        showPopupButton.addActionListener(e -> {
            Point location = showPopupButton.getLocationOnScreen();
            popupDialog.setLocation(location.x, location.y + showPopupButton.getHeight());
            popupDialog.setVisible(true);
        });

        // Cerrar el panel emergente al hacer clic fuera de él
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent) event;
                if (mouseEvent.getID() == MouseEvent.MOUSE_PRESSED) {
                    // Verificar si el clic fue fuera del JDialog
                    Point clickPoint = mouseEvent.getLocationOnScreen();
                    Rectangle dialogBounds = popupDialog.getBounds();
                    if (!dialogBounds.contains(clickPoint)) {
                        popupDialog.setVisible(false);
                    }
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK);

        // Añadir el botón al marco
        frame.add(showPopupButton);

        // Mostrar el marco
        frame.setVisible(true);
    }
}
