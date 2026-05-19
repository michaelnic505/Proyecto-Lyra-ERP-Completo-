package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;


import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Lista_Grupo_Planificador_Model {
    
    public static void set(LyraTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Código","Descripción"};
        String[] columnasEN = {"Code","Description"};
        String[] columnasFR = {"Code", "Description"};
        String[] columnasPT = {"Código","Descrição"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
