package com.simplecore.erp.modules.controlling.areas.legacy;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Modelo_Lista_Sociedades {

    public static void setLanguage(JTable tabla) {
        
        DefaultTableModel model = new DefaultTableModel();
        
        String[] ES = {"Sociedad", "Descripción","País","Nombre país"};
        String[] EN = {"Company", "Description", "Country", "Country name"};
        String[] FR = {"Entreprise", "Description", "Pays", "Nom du pays"};
        String[] PT = {"Empresa", "Descrição","País","Nome do país"};
        String[] RU = {"Компания", "Описание", "Страна", "Название страны"};
        String[] AL = {"Firma", "Beschreibung", "Land", "Ländername"};
        
            model.setColumnIdentifiers(EN);
     
        tabla.setModel(model);
        
    }

}
