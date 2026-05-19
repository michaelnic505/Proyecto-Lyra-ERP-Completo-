package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils;


import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Modelo_Lista_Areas {
    
    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Área","Descripción","Centro de costos","Descripción C.C.","Nivel","Descripción nivel","Área superior","Descripción área superior"};
        String[] columnasEN = {"Area","Description","Cost Center","C.C. Description","Level","Level Description","Upper Area","Upper Area Description"};
        String[] columnasFR = {"Zone", "Description", "Centre de coûts", "Description C.C.", "Niveau", "Description du niveau", "Zone supérieure", "Description de la zone supérieure"};
        String[] columnasPT = {"Área","Descrição","Centro de Custo","Descrição C.C.","Nível","Descrição do Nível","Área Superior","Descrição da Área Superior"};
        String[] columnasRU = {"Область", "Описание", "Центр затрат", "Описание CC", "Уровень", "Описание уровня", "Верхняя область", "Описание верхней области"};
     
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
