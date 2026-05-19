package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceplans.legacy.p02_modification_of_maintenance_plan;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class P02_Modelo_Lista_Criticidad_Mtto {

    
    public static void setColumnIdentifiers(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Cod. Criticidad"," Descripción Criticidad"};
        String[] columnasEN = {"Criticality Code", "Criticality Description"};
        String[] columnasFR = {"Code de criticité", "Description de la criticité"};
        String[] columnasPT = {"Código de criticidade", "Descrição de criticidade"};
        String[] columnasRU = {"Код критичности", "Описание критичности"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);

    }
}
