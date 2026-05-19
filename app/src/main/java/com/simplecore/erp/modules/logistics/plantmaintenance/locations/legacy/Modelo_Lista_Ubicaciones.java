package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Modelo_Lista_Ubicaciones {


    public static void setLanguage(JTable tabla) {
        
        DefaultTableModel model = new DefaultTableModel();
        
        String[] ES = {"Ubicación","Denominación","Ubica. Supe.","Denominación supe.","Nivel"};
        String[] EN = {"Location","Denomination","Location Higher","Denomination Higher","Level"};
        String[] FR = {"Emplacement", "Désignation", "Emplacement supérieur", "Nom supérieur", "Niveau"};
        String[] PT = {"Localização","Designação","Localização Superior","Nome Superior","Nível"};
        String[] RU = {"«Местоположение", "Наименование", "Высшее местоположение", "Высшее имя", "Уровень»"};
        String[] AL = {"Ort", "Bezeichnung", "Höherer Ort", "Höherer Name", "Ebene"};
        
            model.setColumnIdentifiers(EN);
        tabla.setModel(model);
        
    }

}
