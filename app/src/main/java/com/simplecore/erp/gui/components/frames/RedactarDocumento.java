/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simplecore.erp.gui.components.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RedactarDocumento {
    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame ventana = new JFrame("Redactar Documento");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600, 400);
        ventana.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        
        // Crear un área de texto con filas y columnas
        JTextArea areaTexto = new JTextArea(10, 30);  // 10 filas, 30 columnas
        areaTexto.setWrapStyleWord(true);  // Ajustar el texto automáticamente al tamaño del área
        areaTexto.setLineWrap(true);       // Hacer que el texto se ajuste al llegar al borde
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Crear un panel de desplazamiento para el área de texto
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        
        // Crear un botón para guardar el documento
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Funcionalidad para guardar el documento
                JFileChooser fileChooser = new JFileChooser();
                int opcion = fileChooser.showSaveDialog(ventana);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    try {
                        String contenido = areaTexto.getText();
                        String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                        java.nio.file.Files.write(java.nio.file.Paths.get(ruta), contenido.getBytes());
                        JOptionPane.showMessageDialog(ventana, "Documento guardado correctamente.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ventana, "Error al guardar el documento.");
                    }
                }
            }
        });
        
        // Crear un panel y agregar el área de texto y el botón
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnGuardar, BorderLayout.SOUTH);
        
        // Agregar el panel a la ventana
        ventana.add(panel);
        
        // Hacer visible la ventana
        ventana.setVisible(true);
    }
}
