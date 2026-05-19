package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;


import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Lista_Responsable_Model {
    
    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Código","Nombre","Puesto"};
        String[] columnasEN = {"Code","Name","Position"};
        String[] columnasFR = {"Code", "Nom", "Position"};
        String[] columnasPT = {"Código","Nome","Posição"};
     
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
