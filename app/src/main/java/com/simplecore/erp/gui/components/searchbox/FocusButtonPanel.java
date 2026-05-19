package com.simplecore.erp.gui.components.searchbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FocusButtonPanel extends JPanel {

    private JTextField textField;
    private JButton button;

    public FocusButtonPanel() {
        setLayout(new BorderLayout()); // Usamos BorderLayout para el texto y el botón

        // Crear el JTextField
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30)); // Tamaño preferido inicial

        // Crear el JButton
        button = new JButton("✓");
        button.setPreferredSize(new Dimension(30, 30)); // Botón cuadrado
        button.setVisible(false); // El botón comienza invisible

        // Acción cuando el JTextField gana foco
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                button.setVisible(true); // Mostrar el botón
            }

            @Override
            public void focusLost(FocusEvent e) {
                button.setVisible(false); // Ocultar el botón
            }
        });

        // Añadir los componentes al panel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); // No hay separación entre componentes
        panel.add(textField);
        panel.add(button);

        // Añadir el panel con el texto y el botón al JPanel principal
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void doLayout() {
        super.doLayout();
        // Cuando el panel se redimensione, asegúrese de que el JTextField y el JButton
        // se ajusten adecuadamente
        int width = getWidth();
        int height = getHeight();

        textField.setSize(new Dimension(width - 30, height)); // El JTextField ocupa todo el ancho disponible menos el botón
        button.setSize(new Dimension(30, height)); // El botón mantiene su tamaño cuadrado
    }

}
