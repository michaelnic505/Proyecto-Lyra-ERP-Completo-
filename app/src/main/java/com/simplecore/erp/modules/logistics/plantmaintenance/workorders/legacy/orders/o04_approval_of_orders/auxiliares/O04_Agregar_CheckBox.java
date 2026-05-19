
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders.auxiliares;

import javax.swing.JTable;
import javax.swing.table.TableColumn;


public class O04_Agregar_CheckBox {
    
    public void addCheckBox(int columna, JTable Tabla){
     
        TableColumn column = Tabla.getColumnModel().getColumn(columna);
        column.setCellEditor(Tabla.getDefaultEditor(Boolean.class));
        column.setCellRenderer(Tabla.getDefaultRenderer(Boolean.class));
    }
    
    public boolean isSelected(int row, int column, JTable Tabla){
        return Tabla.getValueAt(row, column)!=null;
    }
    
    
}
