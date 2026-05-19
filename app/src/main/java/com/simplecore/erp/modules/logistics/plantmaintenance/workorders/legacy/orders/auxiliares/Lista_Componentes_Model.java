package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;


import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Lista_Componentes_Model {
    
    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Código","Descripción"};
        String[] columnasEN = {"Code","Description"};
        String[] columnasFR = {"Code", "Description"};
        String[] columnasPT = {"Código","Descrição"};
     
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
