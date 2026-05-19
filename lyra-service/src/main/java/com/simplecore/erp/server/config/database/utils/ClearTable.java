
package com.simplecore.erp.server.config.database.utils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ClearTable {
 
     public static void clear(JTable tabla) {

         DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
         
        if (modelo.getRowCount() > 0) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                modelo.removeRow(i);
                i -= 1;
            }
            tabla.setModel(modelo);
        }
    }
    
}
