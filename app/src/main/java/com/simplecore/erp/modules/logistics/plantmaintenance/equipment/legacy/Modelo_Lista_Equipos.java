package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy;


import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Modelo_Lista_Equipos {
    

    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Código Eq.","Descripción de equipo","Tipo","Descripción de tipo","Criticidad","Descripción de criticidad","Área","Descripción de área","Centro Costo","Descripción de centro costo","Estatus","Descripción de estatus","Marca","Modelo","Serie","Fecha Fab.","Equipo Superior"};
        String[] columnasEN = {"Code Eq.","Equipment Description","Type","Type Description","Criticality","Criticality Description","Area","Area description","Cost Center","Cost center description","Status","Status Description","Brand","Model","Series","Manufacturing Date","Senior Team"};
        String[] columnasFR = {"Code Éq.","Description de l'équipement","Type",	"Type Description","Criticité",	"Criticité Description","Zone",	"Descriptif de la zone","Centre de coûts","Description du centre de coûts","Statut","Description de l'état","Marque","Modèle","Série","Date de fabrication","Équipe senior"};
        String[] columnasPT = {"Código Eq.","Descrição dos equipamentos","Cara","Tipo Descrição","Criticamente","Descrição da criticidade","Área","Descrição da área","Centro de custo","Descrição do centro de custo",	"Status","Descrição de status",	"Marca","Modelo","Series","Data de fabricação",	"Equipe Sênior"};
        String[] columnasRU = {"Код","Описание оборудования","Тип","Тип Описание","Критичность","Критичность Описание",	"Район","Описание района","Центр затрат","Описание центра затрат","Положение дел","Описание статуса","Бренд","Модель","Серии","Дата изготовления","Старшая команда"};
        
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
