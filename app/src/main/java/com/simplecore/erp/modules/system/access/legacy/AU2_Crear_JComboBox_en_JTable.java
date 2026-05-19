
package com.simplecore.erp.modules.system.access.legacy;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AU2_Crear_JComboBox_en_JTable {

public static void agregarComboenTabla(JTable tabla, DefaultTableModel modelo, int columna){
    
        tabla.setModel(modelo);       
        JComboBox combo = new JComboBox();
        combo.addItem(true);
        combo.addItem(false);
        tabla.getColumnModel().getColumn(columna).setCellEditor(new DefaultCellEditor(combo));
    
    
    }
    
}



