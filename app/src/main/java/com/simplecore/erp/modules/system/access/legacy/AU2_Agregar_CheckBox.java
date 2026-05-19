package com.simplecore.erp.modules.system.access.legacy;

import javax.swing.JTable;
import javax.swing.table.TableColumn;


public class AU2_Agregar_CheckBox {
    
    public static void addCheckBox(int columna, JTable Tabla){
     
        TableColumn column = Tabla.getColumnModel().getColumn(columna);
        
        column.setCellEditor(Tabla.getDefaultEditor(Boolean.class));
        column.setCellRenderer(Tabla.getDefaultRenderer(Boolean.class));
    }
    
    public boolean isSelected(int row, int column, JTable Tabla){
        return Tabla.getValueAt(row, column)!=null;
    }
    
    
}
