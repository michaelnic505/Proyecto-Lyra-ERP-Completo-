
package com.simplecore.erp.client.gui.windows.auxiliar;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumnModel;
import javax.swing.table.JTableHeader;

public class WindowsUtils {

    // Método para hacer que una ventana sea movible
    public static void makeWindowMovable(JComponent headerPanel, JDialog window) {
        final int[] pX = new int[1];
        final int[] pY = new int[1];

        headerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Guardamos la posición inicial del ratón
                pX[0] = e.getX();
                pY[0] = e.getY();
            }
        });

        headerPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Calculamos el desplazamiento y actualizamos la ubicación
                int deltaX = e.getX() - pX[0];
                int deltaY = e.getY() - pY[0];
                window.setLocation(window.getLocation().x + deltaX, window.getLocation().y + deltaY);
            }
        });
    }

    // Método para hacer que una ventana sea redimensionable
    public static void makeWindowResizable(JDialog window) {
        window.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                int margin = 4; // Margen de 4 píxeles para redimensionar

                // Determina el cursor según la posición del ratón
                if (isOnBottomRightCorner(p, margin, window)) {
                    window.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR)); // Cursor diagonal
                } else if (isOnRightEdge(p, margin, window)) {
                    window.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR)); // Cursor horizontal
                } else if (isOnBottomEdge(p, margin, window)) {
                    window.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR)); // Cursor vertical
                } else {
                    window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Cursor por defecto
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                // Redimensionar según el tipo de cursor activo
                switch (window.getCursor().getType()) {
                    case Cursor.E_RESIZE_CURSOR:
                        resizeWindow(p, "horizontal", window);
                        break;
                    case Cursor.S_RESIZE_CURSOR:
                        resizeWindow(p, "vertical", window);
                        break;
                    case Cursor.SE_RESIZE_CURSOR:
                        resizeWindow(p, "both", window);
                        break;
                    default:
                        break;
                }
            }

            // Métodos auxiliares para simplificar las condiciones
            private boolean isOnRightEdge(Point p, int margin, JDialog window) {
                return p.getX() >= window.getWidth() - margin && p.getX() <= window.getWidth();
            }

            private boolean isOnBottomEdge(Point p, int margin, JDialog window) {
                return p.getY() >= window.getHeight() - margin && p.getY() <= window.getHeight();
            }

            private boolean isOnBottomRightCorner(Point p, int margin, JDialog window) {
                return p.getX() >= window.getWidth() - margin && p.getY() >= window.getHeight() - margin;
            }

            private void resizeWindow(Point p, String direction, JDialog window) {
                int width = window.getWidth();
                int height = window.getHeight();
                int marginMajor, marginMinor;

                switch (direction) {
                    case "horizontal":
                        marginMajor = (int) (p.getX() - width);
                        marginMinor = (int) p.getX();
                        if (marginMajor > 0) {
                            window.setBounds(window.getX(), window.getY(), width + marginMajor, height);
                        } else if (marginMinor >= 200) {
                            window.setBounds(window.getX(), window.getY(), marginMinor, height);
                        }
                        break;

                    case "vertical":
                        marginMajor = (int) (p.getY() - height);
                        marginMinor = (int) p.getY();
                        if (marginMajor > 0) {
                            window.setBounds(window.getX(), window.getY(), width, height + marginMajor);
                        } else if (marginMinor >= 200) {
                            window.setBounds(window.getX(), window.getY(), width, marginMinor);
                        }
                        break;

                    case "both":
                        int marginMajorX = (int) (p.getX() - width);
                        int marginMajorY = (int) (p.getY() - height);
                        int marginMinorX = (int) p.getX();
                        int marginMinorY = (int) p.getY();

                        if (marginMajorX > 0 && marginMajorY > 0) {
                            window.setBounds(window.getX(), window.getY(), width + marginMajorX, height + marginMajorY);
                        } else if (marginMinorX >= 200 && marginMinorY >= 200) {
                            window.setBounds(window.getX(), window.getY(), marginMinorX, marginMinorY);
                        }
                        break;
                }
            }
        });
    }

    // Método para ajustar el ancho de las columnas de una JTable
    public static void adjustColumnWidths(JTable tableList) {
        JTableHeader header = tableList.getTableHeader();
        TableColumnModel columnModel = tableList.getColumnModel();

        int rowCount = tableList.getRowCount();
        int columnCount = tableList.getColumnCount();

        for (int col = 0; col < columnCount; col++) {
            int totalLength = 0;
            int maxLength = header.getColumnModel().getColumn(col).getHeaderValue().toString().length(); // Considerar el encabezado

            for (int row = 0; row < rowCount; row++) {
                Object value = tableList.getValueAt(row, col);
                if (value != null) {
                    int length = value.toString().length();
                    totalLength += length;
                    maxLength = Math.max(maxLength, length);
                }
            }

            int avgLength = rowCount > 0 ? totalLength / rowCount : maxLength;
            int columnWidth = Math.max(avgLength * 7, maxLength * 7); // Factor 7 px por caracter aproximado
            if (columnWidth < 50) columnWidth = 50;
            columnModel.getColumn(col).setPreferredWidth(columnWidth);
        }
    }

    public static void autoFitWindowWidth(JDialog frame, JTable table, int defaultWidth, int defaultHeight) {
        // Tamaño de pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = screenSize.width;
        int maxHeight = screenSize.height;

        // Ancho
        int totalColumnWidth = table.getColumnModel().getTotalColumnWidth();
        int newWidth = Math.max(defaultWidth, totalColumnWidth + 50); // +50 por márgenes
        if (newWidth > maxWidth) {
            newWidth = maxWidth - 50; // margen de seguridad
        }

        // Alto
        int rowCount = table.getRowCount();
        int rowHeight = table.getRowHeight();
        int totalRowHeight = rowCount * rowHeight + 80; // +80 para márgenes y bordes

        int newHeight = Math.max(defaultHeight, totalRowHeight);
        if (newHeight > maxHeight) {
            newHeight = maxHeight - 80; // margen de seguridad
        }

        // Aplicar nuevo tamaño
        frame.setSize(newWidth, newHeight);
    }

    public static void addEscToClose(final JDialog dialog) {
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        String actionKey = "ESCAPE_CLOSE_DIALOG";

        JRootPane rootPane = dialog.getRootPane();
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(escapeKeyStroke, actionKey);
        rootPane.getActionMap()
                .put(actionKey, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });
    }

}
