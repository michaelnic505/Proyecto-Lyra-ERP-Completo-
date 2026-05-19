package com.simplecore.erp.client.gui.components.tables.newversions;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class DynamicTable extends JTable {

    public DynamicTable() {

        this.getTableHeader().setDefaultRenderer(new Headers());
        this.setDefaultRenderer(Object.class, new Cells());
        this.setRowHeight(22);
        setBorder(BorderFactory.createEtchedBorder());

        DynamicTableModel mod = new DynamicTableModel(4, new String[]{"Column 1", "Column 2", "Column 3", "Column 4"});
        setModel(mod);

        SwingUtilities.invokeLater(() -> synchronizeHeaderBackground());
    }

    private Color columnBackground = new Color(0, 102, 160);
    private Color columnForeground = new Color(248, 248, 248);
    private Font columnFont = new Font("Roboto Light", Font.PLAIN, 12);
    private int columnTextAlignment = SwingConstants.CENTER;
    private Color columnHasFocusColor = new Color(0, 131, 206);
    private Color hasFocusForeground = new Color(51, 51, 51);

    

    /*Column Renders*/
    private class Headers extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            comp.setBackground(getColumnBackground());
            comp.setForeground(getColumnForeground());
            comp.setFont(getColumnFont());

            setBorder(BorderFactory.createLineBorder(getColumnForeground(), 1, true));
            setHorizontalAlignment(getColumnTextAlignment());

            if (hasFocus) {
                comp.setBackground(getColumnHasFocusColor());
                comp.setForeground(getHasFocusForeground());
            }

            return comp;
        }

    }
    
    private void synchronizeHeaderBackground() {
        this.getTableHeader().setBackground(this.getBackground());
    }


    public Color getColumnBackground() {
        return columnBackground;
    }

    public Color getColumnForeground() {
        return columnForeground;
    }

    public Font getColumnFont() {
        return columnFont;
    }

    public int getColumnTextAlignment() {
        return columnTextAlignment;
    }

    public Color getColumnHasFocusColor() {
        return columnHasFocusColor;
    }

    public Color getHasFocusForeground() {
        return hasFocusForeground;
    }

    public void setColumnBackground(Color columnBackground) {
        this.columnBackground = columnBackground;
    }

    public void setColumnForeground(Color columnForeground) {
        this.columnForeground = columnForeground;
    }

    public void setColumnFont(Font columnFont) {
        this.columnFont = columnFont;
    }

    public void setColumnTextAlignment(int columnTextAlignment) {
        this.columnTextAlignment = columnTextAlignment;
    }

    public void setColumnHasFocusColor(Color columnHasFocusColor) {
        this.columnHasFocusColor = columnHasFocusColor;
    }

    public void setHasFocusForeground(Color hasFocusForeground) {
        this.hasFocusForeground = hasFocusForeground;
    }

    /* Cells Renders*/
    private Color uniqueCellBackground = new Color(172, 226, 255);
    private Color uniqueColumnSelection = new Color(255, 242, 156);

    private Color cellBackground = new Color(235, 248, 250);
    private Color cellSelection = new Color(255, 200, 43);

    private Color notEditableUniqueCell = new Color(151,198,224);
    private Color notEditableUniqueCellSelection = new Color(117,210,221);

    private Color notEditableCell = new Color(235, 235, 235);
    private Color notEditableCellSelection = new Color(193,193,193);

    private Color foregroundSelection = new Color(56, 56, 56);
    private int uniqueColumn = 0;

    private class Cells extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Determinar si la celda es editable
            boolean isEditable = table.isCellEditable(row, column);

            // Establecer colores por defecto
            Color bgColor = (column == getUniqueColumn()) ? (isEditable ? getUniqueCellBackground() : getNotEditableUniqueCell())
                    : (isEditable ? getCellBackground() : getNotEditableCell());
            Color fgColor = getForegroundSelection();  // Siempre el mismo color de texto

            // Si la celda está seleccionada, cambiar el fondo y texto
            if (isSelected) {
                bgColor = (column == getUniqueColumn()) ? (isEditable ? getUniqueColumnSelection() : getNotEditableUniqueCellSelection())
                        : (isEditable ? getCellSelection() : getNotEditableCellSelection());
            }

            // Establecer el fondo y primer plano
            comp.setBackground(bgColor);
            comp.setForeground(fgColor);

            // Añadir borde
            MatteBorder border = new MatteBorder(0, 0, 1, 1, table.getGridColor());
            ((JComponent) comp).setBorder(border);

            return comp;
        }
    }



    public Color getUniqueCellBackground() {
        return uniqueCellBackground;
    }

    public Color getCellBackground() {
        return cellBackground;
    }

    public Color getUniqueColumnSelection() {
        return uniqueColumnSelection;
    }

    public Color getCellSelection() {
        return cellSelection;
    }

    public Color getNotEditableUniqueCell() {
        return notEditableUniqueCell;
    }

    public Color getForegroundSelection() {
        return foregroundSelection;
    }

    public Color getNotEditableCell() {
        return notEditableCell;
    }

    public void setUniqueCellBackground(Color uniqueCellBackground) {
        this.uniqueCellBackground = uniqueCellBackground;
    }

    public void setCellBackground(Color cellBackground) {
        this.cellBackground = cellBackground;
    }

    public void setUniqueColumnSelection(Color uniqueColumnSelection) {
        this.uniqueColumnSelection = uniqueColumnSelection;
    }

    public void setCellSelection(Color cellSelection) {
        this.cellSelection = cellSelection;
    }

    public void setNotEditableUniqueCell(Color notEditableUniqueCell) {
        this.notEditableUniqueCell = notEditableUniqueCell;
    }

    public void setForegroundSelection(Color foregroundSelection) {
        this.foregroundSelection = foregroundSelection;
    }

    public void setNotEditableCell(Color notEditableCell) {
        this.notEditableCell = notEditableCell;
    }

    public int getUniqueColumn() {
        return uniqueColumn;
    }

    public void setUniqueColumn(int uniqueColumn) {
        this.uniqueColumn = uniqueColumn;
    }

    public Color getNotEditableCellSelection() {
        return notEditableCellSelection;
    }

    public void setNotEditableCellSelection(Color notEditableCellSelection) {
        this.notEditableCellSelection = notEditableCellSelection;
    }

    public Color getNotEditableUniqueCellSelection() {
        return notEditableUniqueCellSelection;
    }

    public void setNotEditableUniqueCellSelection(Color notEditableUniqueCellSelection) {
        this.notEditableUniqueCellSelection = notEditableUniqueCellSelection;
    }


}
