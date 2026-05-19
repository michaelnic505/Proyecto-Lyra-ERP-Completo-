package com.simplecore.erp.gui.components.tables.table3;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class LyraCellRender2 extends DefaultTableCellRenderer {

    public Color getColorSeleccionFuente() {
        return colorSeleccionFuente;
    }

    public void setColorSeleccionFuente(Color colorSeleccionFuente) {
        this.colorSeleccionFuente = colorSeleccionFuente;
    }

    public Color getColorColumna1() {
        return colorColumna1;
    }

    public Color getColorColumnas() {
        return colorColumnas;
    }

    public Color getColorSeleccionColumna1() {
        return colorSeleccionColumna1;
    }

    public Color getColorSeleccionColumnas() {
        return colorSeleccionColumnas;
    }

    public void setColorColumna1(Color colorColumna1) {
        this.colorColumna1 = colorColumna1;
    }

    public void setColorColumnas(Color colorColumnas) {
        this.colorColumnas = colorColumnas;
    }

    public void setColorSeleccionColumna1(Color colorSeleccionColumna1) {
        this.colorSeleccionColumna1 = colorSeleccionColumna1;
    }

    public void setColorSeleccionColumnas(Color colorSeleccionColumnas) {
        this.colorSeleccionColumnas = colorSeleccionColumnas;
    }

    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 0) {
            comp.setBackground(getColorColumna1());

        } else {
            comp.setBackground(getColorColumnas());
        }

        if (isSelected) {
            if (column == 0) {
                comp.setBackground(getColorSeleccionColumna1());
                comp.setForeground(getColorSeleccionFuente());
            } else {
                comp.setBackground(getColorSeleccionColumnas());
                comp.setForeground(getColorSeleccionFuente());
            }
        }

        return comp;
    }

    
    
    private Color colorColumna1 = new Color(172, 226, 255);
    private Color colorColumnas = new Color(235, 248, 250);
    private Color colorSeleccionColumna1 = new Color(255, 242, 156);
    private Color colorSeleccionColumnas = new Color(255, 200, 43);
    private Color colorSeleccionFuente = new Color(56,56,56);
}
