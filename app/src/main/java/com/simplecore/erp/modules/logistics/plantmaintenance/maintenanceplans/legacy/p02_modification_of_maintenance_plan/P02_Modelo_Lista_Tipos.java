package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceplans.legacy.p02_modification_of_maintenance_plan;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class P02_Modelo_Lista_Tipos {
    

    public static void set(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Código tipo","Descripción"};
        String[] columnasEN = {"Type Code","Description"};
        String[] columnasFR = {"Code", "Description"};
        String[] columnasPT = {"Código","Descrição"};
        String[] columnasRU = {"Код", "Описание"};
        
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
