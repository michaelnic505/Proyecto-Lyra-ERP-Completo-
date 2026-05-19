package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Modelo_Grupos_Planificadores {

    public static void setLanguage(JTable tabla) {
        
        LyraTableModel model = new LyraTableModel();
        
        String[] ES = {"Código", "Descripción"};
        String[] EN = {"Code", "Description"};
        String[] FR = {"Code", "Description"};
        String[] PT = {"Código", "Descrição"};
        
            model.setColumnIdentifiers(EN);
        tabla.setModel(model);
        
    }

}
