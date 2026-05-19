package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceplans.legacy.p02_modification_of_maintenance_plan;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class P02_Modelo_Lista_PM {

    
    public static void ListaHojasRuta(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Hoja ruta","Cabecera de hoja ruta","Tipo plan","Estrategia","Cod. est.","Estatus"};
        String[] columnasEN = {"Route sheet","Route sheet header","Plan type","Strategy","Std. code","Status"};
        String[] columnasFR = {"Feuille de route","En-tête de feuille de route","Type de plan","Stratégie","Code std.","Statut"};
        String[] columnasPT = {"Folha de rota","Cabeçalho da folha de rota","Tipo de plano","Estratégia","Código padrão","Status"};
        String[] columnasRU = {"Маршрутный лист", "Заголовок маршрутного листа", "Тип плана", "Стратегия", "Стандартный код", "Статус"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);

    }
}
