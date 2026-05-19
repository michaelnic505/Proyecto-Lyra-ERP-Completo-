package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Modelo_Lista_CentroCostos {


    public static void setLanguage(JTable tabla) {
        
        DefaultTableModel model = new DefaultTableModel();
        
        String[] ES = {"Centro Coste","Descripción","Emplazamiento","Descripción Emplazamiento","Área","Descripción Área","Sociedad","Denominación Sociedad"};
        String[] EN = {"Cost Center","Description","Location","Location Description","Area","Area Description","Company","Company Name"};
        String[] FR = {"Centre de coûts", "Description", "Emplacement", "Description de l'emplacement", "Zone", "Description de la zone", "Société", "Nom de la société"};
        String[] PT = {"Centro de Custo","Descrição","Localização","Descrição da Localização","Área","Descrição da Área","Empresa","Nome da Empresa"};
        String[] RU = {"Центр затрат", "Описание", "Местоположение", "Описание местоположения", "Область", "Описание области", "Компания", "Название компании"};
        String[] AL = {"Kostenstelle", "Beschreibung", "Standort", "Standortbeschreibung", "Bereich", "Bereichsbeschreibung", "Firma", "Firmenname"};
        
            model.setColumnIdentifiers(EN);
     
        tabla.setModel(model);
        
    }

}
