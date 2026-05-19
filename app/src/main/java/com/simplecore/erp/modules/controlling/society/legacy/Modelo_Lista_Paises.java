package com.simplecore.erp.modules.controlling.society.legacy;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Modelo_Lista_Paises {

    public static void setLanguage(JTable tabla) {
        
        DefaultTableModel model = new DefaultTableModel();
        
        String[] ES = {"Código", "Descripción","Código telefónico"};
        String[] EN = {"Code", "Description","Phone code"};
        String[] FR = {"Code", "Description","Indicatif téléphonique"};
        String[] PT = {"Código", "Descrição","Código do telefone"};
        String[] RU = {"Код", "Описание","Телефонный код"};
        String[] AL = {"Code", "Beschreibung","Telefoncode"};
        
            model.setColumnIdentifiers(EN);
     
        tabla.setModel(model);
        
    }

}
