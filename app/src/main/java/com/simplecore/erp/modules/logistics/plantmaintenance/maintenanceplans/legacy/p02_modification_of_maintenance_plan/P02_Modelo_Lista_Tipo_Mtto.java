package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceplans.legacy.p02_modification_of_maintenance_plan;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class P02_Modelo_Lista_Tipo_Mtto {

    
    public static void setColumnIdentifiers(JTable tabla) {

        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasES = {"Código Tipo Ord."," Descripción Tipo Orden"};
        String[] columnasEN = {"Order Type Code", "Order Type Description"};
        String[] columnasFR = {"Code du type de commande", "Description du type de commande"};
        String[] columnasPT = {"Código do tipo de pedido", "Descrição do tipo de pedido"};
        String[] columnasRU = {"Код типа заказа", "Описание типа заказа"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);

    }
}
