package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceplans.legacy.p02_modification_of_maintenance_plan;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class P02_Modelo_Lista_Clase_Mtto {

    public static void setColumnIdentifiers(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Código Clase "," Descripción Clase"};
        String[] columnasEN = {"Class Code", "Class Description"};
        String[] columnasFR = {"Code de classe", "Description de la classe"};
        String[] columnasPT = {"Código da Classe", "Descrição da Classe"};
        String[] columnasRU = {"Код класса", "Описание класса"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);

    }
}
