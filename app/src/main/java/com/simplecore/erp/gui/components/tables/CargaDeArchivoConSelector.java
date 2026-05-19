/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simplecore.erp.gui.components.tables;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CargaDeArchivoConSelector {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Carga de Archivo con Selector de Directorio");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new BorderLayout());

            // Panel principal
            JPanel panelPrincipal = new JPanel(new FlowLayout());
            JLabel labelDirectorio = new JLabel("Directorio:");
            JTextField campoDirectorio = new JTextField(25);
            campoDirectorio.setEditable(false);
            JButton botonSeleccionarDirectorio = new JButton("Seleccionar Directorio");

            JLabel labelNombreArchivo = new JLabel("Nombre del archivo:");
            JTextField campoTexto = new JTextField(20);
            JButton botonCargar = new JButton("Cargar Archivo");

            panelPrincipal.add(labelDirectorio);
            panelPrincipal.add(campoDirectorio);
            panelPrincipal.add(botonSeleccionarDirectorio);
            panelPrincipal.add(labelNombreArchivo);
            panelPrincipal.add(campoTexto);
            panelPrincipal.add(botonCargar);

            frame.add(panelPrincipal, BorderLayout.CENTER);

            // Barra de estado
            JPanel barraDeEstado = new JPanel(new BorderLayout());
            barraDeEstado.setBorder(BorderFactory.createEtchedBorder());

            JLabel etiquetaEstado = new JLabel("Esperando...");
            barraDeEstado.add(etiquetaEstado, BorderLayout.WEST);

            JProgressBar barraProgreso = new JProgressBar();
            barraProgreso.setValue(0);
            barraProgreso.setStringPainted(true);
            barraDeEstado.add(barraProgreso, BorderLayout.EAST);

            frame.add(barraDeEstado, BorderLayout.SOUTH);

            // Mostrar ventana
            frame.setVisible(true);

            // Acción para seleccionar el directorio
            botonSeleccionarDirectorio.addActionListener(e -> {
                JFileChooser selector = new JFileChooser();
                selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int opcion = selector.showOpenDialog(frame);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    File directorioSeleccionado = selector.getSelectedFile();
                    campoDirectorio.setText(directorioSeleccionado.getAbsolutePath());
                    etiquetaEstado.setText("Directorio seleccionado: " + directorioSeleccionado.getName());
                }
            });

            // Acción para cargar el archivo
            botonCargar.addActionListener(e -> {
                String directorio = campoDirectorio.getText().trim();
                String nombreArchivo = campoTexto.getText().trim();

                if (directorio.isEmpty()) {
                    etiquetaEstado.setText("Por favor, selecciona un directorio.");
                    return;
                }

                if (nombreArchivo.isEmpty()) {
                    etiquetaEstado.setText("Por favor, ingresa un nombre de archivo.");
                    return;
                }

                File archivo = new File(directorio, nombreArchivo);
                if (!archivo.exists() || !archivo.isFile()) {
                    etiquetaEstado.setText("Archivo no encontrado: " + nombreArchivo);
                    return;
                }

                // Simular procesamiento del archivo
                etiquetaEstado.setText("Procesando: " + nombreArchivo);
                new Thread(() -> {
                    barraProgreso.setValue(0);
                    try {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(50); // Simula procesamiento
                            final int progreso = i;
                            SwingUtilities.invokeLater(() -> barraProgreso.setValue(progreso));
                        }
                        SwingUtilities.invokeLater(() -> etiquetaEstado.setText("Archivo procesado: " + nombreArchivo));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            });
        });
    }
}
