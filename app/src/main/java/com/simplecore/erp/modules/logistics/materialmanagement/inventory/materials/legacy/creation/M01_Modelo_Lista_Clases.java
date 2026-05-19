
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.creation;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class M01_Modelo_Lista_Clases {
    

    public static void set(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Cod.Clase","Descripción"};
        String[] columnasEN = {"Class Code","Description"};
        String[] columnasFR = {"Code de classe", "Description"};
        String[] columnasPT = {"Código da Classe","Descrição"};
        String[] columnasRU = {"Код класса", "Описание"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
