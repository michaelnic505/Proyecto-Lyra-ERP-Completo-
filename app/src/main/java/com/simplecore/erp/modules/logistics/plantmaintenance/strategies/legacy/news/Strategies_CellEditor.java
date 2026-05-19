package com.simplecore.erp.modules.logistics.plantmaintenance.strategies.legacy.news;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Strategies_CellEditor extends DefaultCellEditor{

    private PackageSecuence ev;
    
    public Strategies_CellEditor(JTextField textField, PackageSecuence ev) {
        super(textField);
        this.ev = ev;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        
        Component comp = super.getTableCellEditorComponent(table, value, isSelected, row, column);
        ev.setEditedRow(row);
        ev.setValue(value);
        
        return comp;
    }
    
}
