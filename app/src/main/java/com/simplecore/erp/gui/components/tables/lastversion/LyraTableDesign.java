package com.simplecore.erp.gui.components.tables.lastversion;

import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.components.tables.table3.LyraCellRender;
import com.simplecore.erp.gui.components.tables.table3.LyraCellRender2;

public class LyraTableDesign {

    public static void setWidthNoResizable(LyraTable tabla, int columna, int ancho) {

        tabla.getColumnModel().getColumn(columna).setMinWidth(ancho);
        tabla.getColumnModel().getColumn(columna).setMaxWidth(ancho);
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);

    }

    public static void setWidthResizable(LyraTable tabla, int columna, int ancho) {

        tabla.getColumnModel().getColumn(columna).setMinWidth(ancho);
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);

    }

    public static void alignCenter(LyraTable table, int column) {

        table.getColumnModel().getColumn(column).setCellRenderer(new LyraCellRender() {
            @Override
            public void setHorizontalAlignment(int alignment) {
                alignment = SwingUtilities.CENTER;
                super.setHorizontalAlignment(alignment);
            }

        });
    }

    public static void alignLeft(LyraTable table, int column) {

        table.getColumnModel().getColumn(column).setCellRenderer(new LyraCellRender() {
            @Override
            public void setHorizontalAlignment(int alignment) {
                alignment = SwingUtilities.LEFT;
                super.setHorizontalAlignment(alignment);
            }

        });
    }

    public static void alignRight(LyraTable table, int column) {

        table.getColumnModel().getColumn(column).setCellRenderer(new LyraCellRender() {
            @Override
            public void setHorizontalAlignment(int alignment) {
                alignment = SwingUtilities.RIGHT;
                super.setHorizontalAlignment(alignment);
            }

        });
    }
    
    //.........................
        public static void setWidthNoResizable(LyraTableAuxiliar tabla, int columna, int ancho) {

        tabla.getColumnModel().getColumn(columna).setMinWidth(ancho);
        tabla.getColumnModel().getColumn(columna).setMaxWidth(ancho);
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);

    }

    public static void setWidthResizable(LyraTableAuxiliar tabla, int columna, int ancho) {

        tabla.getColumnModel().getColumn(columna).setMinWidth(ancho);
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);

    }

    public static void alignCenter(LyraTableAuxiliar table, int column) {

        table.getColumnModel().getColumn(column).setCellRenderer(new LyraCellRender2() {
            @Override
            public void setHorizontalAlignment(int alignment) {
                alignment = SwingUtilities.CENTER;
                super.setHorizontalAlignment(alignment);
            }

        });
    }

    public static void alignLeft(LyraTableAuxiliar table, int column) {

        table.getColumnModel().getColumn(column).setCellRenderer(new LyraCellRender2() {
            @Override
            public void setHorizontalAlignment(int alignment) {
                alignment = SwingUtilities.LEFT;
                super.setHorizontalAlignment(alignment);
            }

        });
    }

    public static void alignRight(LyraTableAuxiliar table, int column) {

        table.getColumnModel().getColumn(column).setCellRenderer(new LyraCellRender2() {
            @Override
            public void setHorizontalAlignment(int alignment) {
                alignment = SwingUtilities.RIGHT;
                super.setHorizontalAlignment(alignment);
            }

        });
    }
    
}
