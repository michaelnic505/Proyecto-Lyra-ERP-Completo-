
package com.simplecore.erp.client.gui.components.poppup;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class JTableEditorConfig {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JTable - Activar Editor con Teclado");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Datos y nombres de columnas
            Object[][] data = {
                {"Juan", 25},
                {"Ana", 30},
                {"Carlos", 28}
            };
            String[] columnNames = {"Nombre", "Edad"};

            // Crear modelo de tabla
            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);

            // ⚙️ Configuración de la tabla

            table.setSurrendersFocusOnKeystroke(true);

            // Configurar editor para la columna "Edad"
            JTextField textField = new JTextField();
            DefaultCellEditor cellEditor = new DefaultCellEditor(textField);
            cellEditor.setClickCountToStart(1); // Permite edición con un solo clic
            table.getColumnModel().getColumn(1).setCellEditor(cellEditor);

            // Agregar KeyListener para activar el editor al presionar una tecla
            table.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();

                    if (row != -1 && col != -1) {
                        char keyChar = e.getKeyChar();
                        if (Character.isLetterOrDigit(keyChar) || Character.isSpaceChar(keyChar)) {
                            // Activar el editor antes de que se procese la tecla
                            table.editCellAt(row, col);

                            // Pasar el foco al editor para que maneje la entrada correctamente
                            Component editor = table.getEditorComponent();
                            if (editor instanceof JTextField textField) {
                                textField.requestFocus();
                                System.out.println("📢 Se ha entrado en el editor de la celda: [" + row + ", " + col + "]");
                            }
                        }
                    }
                }
            });

            frame.add(new JScrollPane(table));
            frame.setVisible(true);
        });
    }
}
