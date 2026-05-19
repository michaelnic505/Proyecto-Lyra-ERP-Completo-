/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simplecore.erp.gui.components.tables;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CargaDeArchivos {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Carga de Archivos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new BorderLayout());

            // Panel principal
            JPanel panelPrincipal = new JPanel();
            panelPrincipal.add(new JLabel("Cargando archivos desde el directorio..."));
            frame.add(panelPrincipal, BorderLayout.CENTER);

            // Barra de estado
            JPanel barraDeEstado = new JPanel(new BorderLayout());
            barraDeEstado.setBorder(BorderFactory.createEtchedBorder());

            JLabel etiquetaEstado = new JLabel("Listo");
            barraDeEstado.add(etiquetaEstado, BorderLayout.WEST);

            JProgressBar barraProgreso = new JProgressBar();
            barraProgreso.setValue(0);
            barraProgreso.setStringPainted(true);
            barraDeEstado.add(barraProgreso, BorderLayout.EAST);

            frame.add(barraDeEstado, BorderLayout.SOUTH);

            // Mostrar ventana
            frame.setVisible(true);

            // Simular carga de archivos
            new Thread(() -> {
                // Ruta del directorio (ajusta esta ruta a una existente en tu sistema)
                File directorio = new File("C:\\Users\\user\\Downloads"); // Cambia esta ruta

                if (!directorio.exists() || !directorio.isDirectory()) {
                    SwingUtilities.invokeLater(() -> etiquetaEstado.setText("Directorio no encontrado."));
                    return;
                }

                // Obtener lista de archivos
                File[] archivos = directorio.listFiles();
                if (archivos == null || archivos.length == 0) {
                    SwingUtilities.invokeLater(() -> etiquetaEstado.setText("No hay archivos para procesar."));
                    return;
                }

                int totalArchivos = archivos.length;

                // Procesar cada archivo
                for (int i = 0; i < totalArchivos; i++) {
                    File archivo = archivos[i];
                    // Simular tiempo de procesamiento
                    try {
                        Thread.sleep(500); // Simula el tiempo de procesamiento por archivo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int progreso = (i + 1) * 100 / totalArchivos;
                    final String nombreArchivo = archivo.getName();

                    SwingUtilities.invokeLater(() -> {
                        barraProgreso.setValue(progreso);
                        etiquetaEstado.setText("Cargando: " + nombreArchivo + " (" + progreso + "%)");
                    });
                }

                // Finalizar
                SwingUtilities.invokeLater(() -> etiquetaEstado.setText("Carga completada. Total de archivos: " + totalArchivos));
            }).start();
        });
    }
}
