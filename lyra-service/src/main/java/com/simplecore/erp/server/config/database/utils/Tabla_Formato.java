
package com.simplecore.erp.server.config.database.utils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Tabla_Formato {
    
    public static void tablaCellNoEditable(JTable tabla) {

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        String columnas[] = new String[modelo.getColumnCount()];
        Object[][] filas = new Object[modelo.getRowCount()][modelo.getColumnCount()];

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            columnas[i] = modelo.getColumnName(i);
        }

        for (int i = 0; i < modelo.getRowCount(); i++) {

            for (int j = 0; j < modelo.getColumnCount(); j++) {
                filas[i][j] = modelo.getValueAt(i, j);
            }
        }

        DefaultTableModel modeloBloqueado = new DefaultTableModel(filas, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla.setModel(modeloBloqueado);
        tabla.getTableHeader().setReorderingAllowed(false);
    }

    
    public static void tablaNoEditable(JTable tabla, int margen){
        
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        
        String columnas[] = new String[modelo.getColumnCount()];
        Object[][] filas = new Object[modelo.getRowCount()][modelo.getColumnCount()];
        
        
        for(int i = 0; i < tabla.getColumnCount();i++){
            columnas[i] = modelo.getColumnName(i);       
        }
        
        
        for(int i = 0; i < modelo.getRowCount();i++){
            
            for(int j = 0; j < modelo.getColumnCount();j++){
                filas[i][j] = modelo.getValueAt(i, j);
            }            
        }    
        
        DefaultTableModel modeloBloqueado = new DefaultTableModel(filas,columnas){        
            @Override
            public boolean isCellEditable(int row, int column){                
                return false;                
            }        
        };
        
        tabla.setModel(modeloBloqueado);        
        //REDIMENSIONA LAS COLUMNAS SEGUN EL LARGO DEL CAMPO MAS LARGO DE CADA CELDA             
        for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
            
            int columnaAnt = 0;
            
            for (int fila = 0; fila < tabla.getRowCount(); fila++) {
                
                int largo = String.valueOf(tabla.getValueAt(fila, columna)).length();
                if (largo > columnaAnt) {
                    columnaAnt = largo;
                    tabla.getColumnModel().getColumn(columna).setMinWidth((columnaAnt/2) * margen);
                    
                }

            }

        }
                //ESTA LINEA HACE QUE LA TABLA SEA PUEDA HACER SCROLL HORIZONTALMENTE
                tabla.setAutoResizeMode(0);  
                tabla.getTableHeader().setReorderingAllowed(false);
    }

    public static void resizeTable(JTable tabla, int margen) {

        //REDIMENSIONA LAS COLUMNAS SEGUN EL LARGO DEL CAMPO MAS LARGO DE CADA CELDA             
        for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
            int columnaAnt = 0;
            for (int fila = 0; fila < tabla.getRowCount(); fila++) {
                int largo = String.valueOf(tabla.getValueAt(fila, columna)).length();
                if (largo > columnaAnt) {
                    columnaAnt = largo;
                    tabla.getColumnModel().getColumn(columna).setMinWidth(columnaAnt * margen);
                    
                }

            }

        }
    }
    
    public static void editableColumn(JTable tabla, int columna1, int columna2) {

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        String columnas[] = new String[modelo.getColumnCount()];
        Object[][] filas = new Object[modelo.getRowCount()][modelo.getColumnCount()];

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            columnas[i] = modelo.getColumnName(i);
        }

        for (int i = 0; i < modelo.getRowCount(); i++) {

            for (int j = 0; j < modelo.getColumnCount(); j++) {
                filas[i][j] = modelo.getValueAt(i, j);
            }
        }

        DefaultTableModel modeloBloqueado = new DefaultTableModel(filas, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column >= columna1 && column <= columna2) {
                    return true;
                }
                return false;
            }
        };

        tabla.setModel(modeloBloqueado);


    }

}
