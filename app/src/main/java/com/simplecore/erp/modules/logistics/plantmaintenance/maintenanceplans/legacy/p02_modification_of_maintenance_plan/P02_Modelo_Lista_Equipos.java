package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceplans.legacy.p02_modification_of_maintenance_plan;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class P02_Modelo_Lista_Equipos {

    
    public static void ListaEquipos(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Código Equipo", "Descripción Equipo","Tipo","Descripción Tipo","Criticidad","Descripción Criticidad","Área","Descripción Área","Centro Costos","Descripción Centro Costos","Estatus","Descripción Estatus","Marca","Modelo","Serie","Fecha Fabricación","Equipo Superior"};
        String[] columnasEN = {"Equipment Code", "Description Equipment","Type","Description Type","Criticality","Description Criticality","Area","Description Area","Cost Center","Description Center Costs","Status","Description Status","Brand","Model","Series","Fabrication date","Senior Equipment"};
        String[] columnasFR = {"Code de l'équipement", "Descriptif Équipement","Type","Description Type","Criticité","Description Criticité","Superficie","Zone de description","Centre de coûts","Description Centre Coûts","Statut","Description Statut","Marque","Modèle","Série","Date de fabrication","Équipe senior"};
        String[] columnasPT = {"Código do Equipamento", "Descrição Equipamento","Tipo","Descrição Tipo","Criticamente","Descrição Criticidade","Área","Descrição Área","Centro de custo","Descrição Centro de Custos","Status","Descrição Estado","Marca","Modelo","Series","Data de fabricação","Equipe Sênior"};
        String[] columnasRU = {"Код оборудования", "Описание оборудования","Парень","Описание Тип","Критичность","Описание Критичность","Площадь","Описание Район","Центр затрат","Описание Центра Затраты","Положение дел","Описание Статус","Бренд","Модель","Ряд","Дата изготовления","Старшая команда"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);

    }
}
