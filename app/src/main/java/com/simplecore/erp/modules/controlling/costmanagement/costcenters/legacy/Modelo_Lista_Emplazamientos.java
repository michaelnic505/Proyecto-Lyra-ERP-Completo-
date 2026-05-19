package com.simplecore.erp.modules.controlling.costmanagement.costcenters.legacy;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Modelo_Lista_Emplazamientos {

    public static void setLanguage(JTable tabla) {

        DefaultTableModel model = new DefaultTableModel();

        String[] ES = {"Emplazamiento", "Descripción Emp", "Área", "Descripción Área", "Sociedad", "Descripción Sociedad"};
        String[] EN = {"Emplazament", "Description of Emp", "Area", "Area Description", "Company", "Company Description"};
        String[] FR = {"Emplacement", "Description de l'Emp", "Zone", "Description de la zone", "Entreprise", "Description de l'entreprise"};
        String[] PT = {"Localização", "Descrição de Emp", "Área", "Descrição da Área", "Empresa", "Descrição da Empresa"};
        String[] RU = {"Местоположение", "Описание ЭМП", "Район", "Описание региона", "Компания", "Описание компании"};
        String[] AL = {"Standort", "Beschreibung Standort", "Bereich", "Beschreibungsbereich", "Gesellschaft", "Beschreibung Unternehmen"};

        model.setColumnIdentifiers(EN);

        tabla.setModel(model);

    }

}
