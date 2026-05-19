package com.simplecore.erp.gui.components.tables.newversions;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import com.simplecore.erp.gui.components.tables.interfaces.ButtonForTableParameters;
import com.simplecore.erp.gui.components.tables.interfaces.TableButtonListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class DynamicTableButtons extends JTable {

    public Color getColorButtonSelected() {
        return colorButtonSelected;
    }

    public void setColorButtonSelected(Color colorButtonSelected) {
        this.colorButtonSelected = colorButtonSelected;
    }

    public Color getColorSeleccionColumnasNoEditables() {
        return colorSeleccionColumnasNoEditables;
    }

    public void setColorSeleccionColumnasNoEditables(Color colorSeleccionColumnasNoEditables) {
        this.colorSeleccionColumnasNoEditables = colorSeleccionColumnasNoEditables;
    }

    public Color getColumnNoEditableColor() {
        return columnNoEditableColor;
    }

    public Color getColumnEditableColor() {
        return columnEditableColor;
    }

    public void setColumnNoEditableColor(Color columnNoEditableColor) {
        this.columnNoEditableColor = columnNoEditableColor;
    }

    public void setColumnEditableColor(Color columnEditableColor) {
        this.columnEditableColor = columnEditableColor;
    }



    public Color getColumnsHasFocus() {
        return columnsHasFocus;
    }

    public Color getForegroundHasFocus() {
        return foregroundHasFocus;
    }

    public void setColumnsHasFocus(Color columnsHasFocus) {
        this.columnsHasFocus = columnsHasFocus;
    }

    public void setForegroundHasFocus(Color foregroundHasFocus) {
        this.foregroundHasFocus = foregroundHasFocus;
    }



    /*Variables del Header*/
    private Color columnsBackground = new Color(0, 102, 160);
    private Color columns_0 = new Color(0, 131, 206);
    private Color columnForeground = new Color(248, 248, 248);
    private Font columnFont = new Font("Roboto Light", Font.PLAIN, 12);
    private int alineacion = SwingConstants.CENTER;
    private int column_0_Width = 20;
    private Color columnsHasFocus = new Color(0, 131, 206);
    private Color foregroundHasFocus = new Color(51, 51, 51);

    /*Variables de celdas*/
    private Color colorColumna1 = new Color(172, 226, 255);
    private Color colorColumnas = new Color(235, 248, 250);
    private Color colorSeleccionColumna1 = new Color(255, 242, 156);
    private Color colorSeleccionColumnas = new Color(255, 200, 43);
    private Color colorSeleccionColumnasNoEditables = new Color(221, 174, 37);
    private Color colorSeleccionFuente = new Color(56, 56, 56);
    private Color columnNoEditableColor = new Color(235, 235, 235);
    private Color columnEditableColor = new Color(255, 255, 255);

    //CONSTRUCTOR DE LA CLASE TABLE
    
    
    
    public DynamicTableButtons() {

        this.getTableHeader().setDefaultRenderer(new Headers());
        this.setDefaultRenderer(Object.class, new Cells());
        this.setRowHeight(22);

        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setBorder(BorderFactory.createEtchedBorder());
        this.getTableHeader().setReorderingAllowed(false);

        DynamicTableModel mod = new DynamicTableModel(4, new String[]{null, "Column 1", "Column 2", "Column 3", "Column 4"});
        setModel(mod);
        SwingUtilities.invokeLater(() -> synchronizeHeaderBackground());
        
    }

    private void synchronizeHeaderBackground() {
        this.getTableHeader().setBackground(this.getBackground());
    }

    private class Headers extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            comp.setBackground(getColumnsBackground());
            comp.setForeground(getColumnForeground());
            comp.setFont(getColumnFont());

            setBorder(BorderFactory.createLineBorder(columnForeground, 1, true));
            setHorizontalAlignment(getAlineacion());

            if (hasFocus) {
                comp.setBackground(getColumnsHasFocus());
                comp.setForeground(getForegroundHasFocus());
            }

            if (column == 0) {
                comp.setBackground(getColumns_0());
                getColumnModel().getColumn(column).setMaxWidth(getColumn_0_Width());
                getColumnModel().getColumn(column).setMinWidth(getColumn_0_Width());
                getColumnModel().getColumn(column).setPreferredWidth(getColumn_0_Width());
                getColumnModel().getColumn(column).setIdentifier(" ");
            }

            return comp;
        }

    }
    
    
    ArrayList<Integer> selectedRows;
    private Color colorButtonSelected = new Color(146,191,236);
    private Color colorButton = new Color(255, 255, 255);
    public void setSelectedRowsList(ArrayList<Integer> selectedRows){
        this.selectedRows = selectedRows;
    }
    
    
    private class Cells extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Configuración del color de fondo según la columna
            comp.setBackground(column == 1 ? getColorColumna1() : getColorColumnas());

            // Configuración para celdas seleccionadas
            if (isSelected) {
                comp.setBackground(!table.isCellEditable(row, column)
                        ? getColorSeleccionColumnasNoEditables()
                        : (column == 1 ? getColorSeleccionColumna1() : getColorSeleccionColumnas()));
                comp.setForeground(getColorSeleccionFuente());
            } else {
                // Configuración para celdas no seleccionadas
                comp.setBackground(!table.isCellEditable(row, column)
                        ? getColumnNoEditableColor()
                        : getColorColumnas());
            }

            // Aplicar borde a todas las celdas
            MatteBorder border = new MatteBorder(0, 0, 1, 1, table.getGridColor());
            ((JComponent) comp).setBorder(border);

            // Configuración específica para la columna 0
            if (column == 0) {
                
                ButtonForTableParameters button = new ButtonForTableParameters();
                button.setBackground(getColumns_0());
                button.setBorder(border); // Aplicar borde también al botón
                
                if(!selectedRows.isEmpty()){
                    if(selectedRows.contains(row)){
                        button.getButton().setBackground(getColorButtonSelected());
                    }else{
                        button.getButton().setBackground(getColorButton());
                    }
                }
                
                return button;
            }

            return comp;
        }
    }
    

    public Color getColorButton() {
        return colorButton;
    }

    public void setColorButton(Color colorButton) {
        this.colorButton = colorButton;
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

    public Color getColorSeleccionFuente() {
        return colorSeleccionFuente;
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

    public void setColorSeleccionFuente(Color colorSeleccionFuente) {
        this.colorSeleccionFuente = colorSeleccionFuente;
    }

    public int getColumn_0_Width() {
        return column_0_Width;
    }

    public void setColumn_0_Width(int column_0_Width) {
        this.column_0_Width = column_0_Width;
    }

    public Color getColumns_0() {
        return columns_0;
    }

    public void setColumns_0(Color columns_0) {
        this.columns_0 = columns_0;
    }

    public Color getColumnsBackground() {
        return columnsBackground;
    }

    public Color getColumnForeground() {
        return columnForeground;
    }

    public Font getColumnFont() {
        return columnFont;
    }

    public int getAlineacion() {
        return alineacion;
    }

    public void setColumnsBackground(Color columnsBackground) {
        this.columnsBackground = columnsBackground;
    }

    public void setColumnForeground(Color columnForeground) {
        this.columnForeground = columnForeground;
    }

    public void setColumnFont(Font columnFont) {
        this.columnFont = columnFont;
    }

    public void setAlineacion(int alineacion) {
        this.alineacion = alineacion;
    }
    
    
    
    
    
    /**
     * ************************CLASE DEL EDITOR***********************
     */
    
    //VARIABLES DEL EDITOR
    
    private TableButtonListener e;
    private class CellEditor extends DefaultCellEditor {

        public CellEditor() {
            super(new JTextField());
            setClickCountToStart(1);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            ButtonForTableParameters button = new ButtonForTableParameters();
            button.eventoBotonSelection(e, row);
            button.getButton().setBackground(getColorButton());
            
            button.getButton().addMouseListener(new MouseAdapter(){
                @Override
                public void mouseExited(MouseEvent e) {
                    fireEditingStopped();
                }
                
            });
            
            return button;
        }

    }

    public void addTableButtonListener(TableButtonListener e) {
        this.e = e;
        getColumnModel().getColumn(0).setCellEditor(new CellEditor());
    }

}
