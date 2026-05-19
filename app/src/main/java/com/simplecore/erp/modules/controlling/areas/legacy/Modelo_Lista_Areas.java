package com.simplecore.erp.modules.controlling.areas.legacy;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Modelo_Lista_Areas {

    public static void setLanguage(JTable tabla) {
        
        DefaultTableModel model = new DefaultTableModel();
        
        String[] ES = {"Id Área", "Descripción área","Id Sociedad","Descripción sociedad"};
        String[] EN = {"Area Id", "Area Description", "Company Id", "Company Description"};
        String[] FR = {"ID de zone", "Description de la zone", "ID de société", "Description de la société"};
        String[] PT = {"ID da área", "Descrição da área", "ID da sociedade", "Descrição da sociedade"};
        String[] RU = {"Идентификатор области", "писание области", "Идентификатор общества", "Описание общества"};
        String[] AL = {"Bereichs-ID", "Bereichsbeschreibung", "Gesellschafts-ID", "Gesellschaftsbeschreibung"};
        
            model.setColumnIdentifiers(EN);
     
        tabla.setModel(model);
        
    }

}
