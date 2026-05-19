
package com.simplecore.erp.server.config.database.utils;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Tabla_No_Editar {
    
    
    public static void tablaNoEditable(JTable tabla){
        
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        
        String columnas[] = new String[modelo.getColumnCount()];
        Object[][] datos = new Object[modelo.getRowCount()][modelo.getColumnCount()];
        
        for(int i = 0; i < tabla.getColumnCount();i++){
            columnas[i] = modelo.getColumnName(i);        
        }
        
        for(int i = 0; i < modelo.getRowCount();i++){
            
            for(int j = 0; j < modelo.getColumnCount();j++){
                datos[i][j] = modelo.getValueAt(i, j);
            }            
        }   
        
        DefaultTableModel modeloBloqueado = new DefaultTableModel(datos,columnas){        
            @Override
            public boolean isCellEditable(int row, int column){                
                return false;                
            }        
        };
        tabla.setModel(modeloBloqueado);        
  
    }
    
}
