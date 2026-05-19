
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.modification;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class M02_Modelo_Lista_Tipos {
    
    

    public static void set(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Cod.Tipo Mat.","Descripción"};
        String[] columnasEN = {"Mat Type Code","Description"};
        String[] columnasFR = {"Code type de tapis", "Description"};
        String[] columnasPT = {"Código do tipo de material","Descrição"};
        String[] columnasRU = {"Код типа материала", "Описание"};
  
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
