package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils;

import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Modelo_Criticidad {

    public static void setLanguage(JTable tabla) {
        
        LyraTableModel model = new LyraTableModel();
        
        String[] ES = {"Código", "Descripción de la criticidad"};
        String[] EN = {"Code", "Criticality Description"};
        String[] FR = {"Code", "Description de la criticité"};
        String[] PT = {"Código", "Descrição da criticidade"};
        String[] RU = {"Код", "Описание критичности"};
        
            model.setColumnIdentifiers(EN);
        tabla.setModel(model);
        
    }

}
