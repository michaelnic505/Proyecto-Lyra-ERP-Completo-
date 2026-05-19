
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.creation;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class M01_Modelo_Lista_Estatus {
    
    

    public static void set(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Cod.Estatus","Descripción"};
        String[] columnasEN = {"Status Code","Description"};
        String[] columnasFR = {"Code d'état", "Description"};
        String[] columnasPT = {"Código de Status","Descrição"};
        String[] columnasRU = {"Код состояния", "Описание"};
      
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
