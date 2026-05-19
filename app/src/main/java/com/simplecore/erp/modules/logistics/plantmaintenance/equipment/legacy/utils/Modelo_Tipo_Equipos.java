package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils;

import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Modelo_Tipo_Equipos {

    public static void setLanguage(JTable tabla) {
        
        LyraTableModel model = new LyraTableModel();
        
        String[] ES = {"ID","Descripción"};        
        String[] EN = {"Code","Description"};        
        String[] FR = {"Code", "Description"};        
        String[] PT = {"Código","Descrição"};        
        String[] RU = {"Код", "Описание"};        
        String[] DE = {"Code","Beschreibung"};
        

            model.setColumnIdentifiers(EN);
        tabla.setModel(model);
        
    }

}
