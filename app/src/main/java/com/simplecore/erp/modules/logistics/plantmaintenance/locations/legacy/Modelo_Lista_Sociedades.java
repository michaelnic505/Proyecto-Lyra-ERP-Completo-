package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Modelo_Lista_Sociedades {

    public static void setLanguage(JTable tabla) {
        
        DefaultTableModel model = new DefaultTableModel();
        
        String[] ES = {"Sociedad", "Denominación","Cod. País","Nombre país"};
        String[] EN = {"Company", "Name", "Country Code", "Country Name"};
        String[] FR = {"Société", "Dénomination", "Code pays", "Nom du pays"};
        String[] PT = {"Empresa", "Denominação","Código do País","Nome do País"};
        String[] RU = {"Компания", "Наименование", "Код страны", "Название страны"};
        String[] AL = {"Firma", "Bezeichnung", "Ländercode", "Ländername"};
        
            model.setColumnIdentifiers(EN);
        tabla.setModel(model);
        
    }

}
