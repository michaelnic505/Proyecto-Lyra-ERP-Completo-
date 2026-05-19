package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Modelo_Lista_Emplazamientos {

    public static void setLanguage(JTable tabla) {
        
        DefaultTableModel model = new DefaultTableModel();
        
        String[] ES = {"Emplazamiento","Denominación","Área","Denominación área","Sociedad","Denominación sociedad"};
        String[] EN = {"Location","Name","Area","Name of area","Company","Name of company"};
        String[] FR = {"Lieu", "Désignation", "Zone", "Nom de la zone", "Entreprise", "Nom de l'entreprise"};
        String[] PT = {"Localização","Designação","Área","Nome da área","Empresa","Nome da empresa"};
        String[] RU = {"Местоположение", "Наименование", "Территория", "Название территории", "Компания","Название компании"};
        String[] AL = {"Standort", "Bezeichnung", "Bereich", "Bereichsname", "Firma", "Firmenname"};
        
            model.setColumnIdentifiers(EN);
        tabla.setModel(model);
        
    }

}
