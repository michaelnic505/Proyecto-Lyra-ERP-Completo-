
package com.simplecore.erp.gui.components.searchbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FloatingButtonExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JTextField with Floating Button");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Crear el primer JTextField con el botón flotante
            JTextField textField1 = new JTextField(20);
            JButton floatingButton = new JButton("Abrir");
            floatingButton.setFocusable(false); // El botón no debe recibir foco
            floatingButton.setVisible(false); // Inicialmente invisible

            
            // Crear un componente adicional (JLabel) para probar la posición
            JLabel label = new JLabel("Componente adicional");

            // Crear un panel sin LayoutManager
            JPanel panel = new JPanel(null); // Usamos null layout para controlar la posición manualmente
            panel.setPreferredSize(new Dimension(500, 100)); // Establecemos un tamaño para el panel

            // Añadir el primer JTextField al panel
            textField1.setBounds(10, 5, 300, 30); // Posicionamos el primer JTextField
            panel.add(textField1);

            // Añadir el JButton flotante al panel, a la derecha del JTextField
            floatingButton.setBounds(310, 5, 30, 30); // Posicionamos el JButton en el extremo derecho del primer JTextField
            panel.add(floatingButton);

            // Colocar el JLabel a 10 puntos después del final del JTextField
            label.setBounds(310, 5, 100, 30); // El JLabel se coloca 10 puntos después del JTextField
            panel.add(label);


            // Mostrar el botón cuando el primer JTextField recibe el foco
            textField1.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    floatingButton.setVisible(true); // Mostrar el botón cuando el JTextField1 recibe el foco
                }

                @Override
                public void focusLost(FocusEvent e) {
                    floatingButton.setVisible(false); // Ocultar el botón cuando el JTextField1 pierde el foco
                }
            });

            // Configurar la acción del botón
            floatingButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "Acción realizada");
            });

            // Añadir el panel al JFrame
            frame.add(panel, BorderLayout.CENTER);
            frame.setSize(500, 200); // Ajustar el tamaño del frame para mostrar todos los componentes
            frame.setLocationRelativeTo(null); // Centra la ventana
            frame.setVisible(true);
        });
    }
}
