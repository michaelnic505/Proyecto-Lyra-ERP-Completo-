package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy;

import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Modelo_Tipo_Equipos {

    public static void setLanguage(JTable tabla) {
        
        LyraTableModel model = new LyraTableModel();
        
        String[] ES = {"Código", "Descripción del tipo"};
        String[] EN = {"Code", "Type Description"};
        String[] FR = {"Code", "Description du type"};
        String[] PT = {"Código", "Descrição do tipo"};
        String[] RU = {"Код", "Описание типа"};
        
            model.setColumnIdentifiers(EN);
        tabla.setModel(model);
        
    }

}
