package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceplans.legacy.p02_modification_of_maintenance_plan;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class P02_Modelo_Lista_Clases {
    
    public static void set(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Cod.Clase","Descripción"};
        String[] columnasEN = {"Class Code","Description"};
        String[] columnasFR = {"Code de classe", "Description"};
        String[] columnasPT = {"Código da Classe","Descrição"};
        String[] columnasRU = {"Код класса", "Описание"};
        
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
