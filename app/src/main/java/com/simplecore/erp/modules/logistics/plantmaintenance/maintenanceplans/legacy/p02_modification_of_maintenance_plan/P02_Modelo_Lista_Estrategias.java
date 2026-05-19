package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceplans.legacy.p02_modification_of_maintenance_plan;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class P02_Modelo_Lista_Estrategias {

    
    public static void ListaEstrategias(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Codigo Estrategia", "Descripción de estrategia"};
        String[] columnasEN = {"Strategy Code", "Strategy description"};
        String[] columnasFR = {"Code de stratégie", "Description de la stratégie"};
        String[] columnasPT = {"Código de Estratégia", "Descrição da estratégia"};
        String[] columnasRU = {"Код стратегии", "Стратегия"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);

    }
}
