
package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.creation;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class M01_Modelo_Tabla_UM {
    

    public static void modeloTablaUnidadesMedida(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Código","Descripción Unidad Medida","Tipo"};
        String[] columnasEN = {"Code","Description Unit of Measurement","Type"};
        String[] columnasFR = {"Code","Description Unité de mesure","Type"};
        String[] columnasPT = {"Código","Descrição Unidade de Medida","Cara"};
        String[] columnasRU = {"Код","Описание Единица измерения","Парень"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
