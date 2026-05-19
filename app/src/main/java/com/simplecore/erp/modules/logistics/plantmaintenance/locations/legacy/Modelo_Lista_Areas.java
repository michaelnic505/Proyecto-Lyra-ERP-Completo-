package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Modelo_Lista_Areas {

    public static void setLanguage(JTable tabla) {
        
        DefaultTableModel model = new DefaultTableModel();
        
        String[] ES = {"Área","Denominación","Sociedad", "Denominación sociedad"};
        String[] EN = {"Area", "Name", "Company", "Company name"};
        String[] FR = {"Zone", "Dénomination", "Entreprise", "Nom de l'entreprise"};
        String[] PT = {"Área","Denominação","Empresa", "Nome da empresa"};
        String[] RU = {"Территория", "Номинал", "Компания", "Название компании"};
        String[] AL = {"Gebiet", "Bezeichnung", "Firma", "Firmenname"};
        
            model.setColumnIdentifiers(EN);
        tabla.setModel(model);
        
    }

}
