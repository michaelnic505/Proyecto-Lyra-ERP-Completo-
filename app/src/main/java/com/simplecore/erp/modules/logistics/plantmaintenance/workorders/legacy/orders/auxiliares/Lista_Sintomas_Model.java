package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;


import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Lista_Sintomas_Model {
    
    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Código","Descripción","Contexto"};
        String[] columnasEN = {"Code","Description","Context"};
        String[] columnasFR = {"Code", "Description", "Contexte"};
        String[] columnasPT = {"Código","Descrição","Contexto"};
     
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
