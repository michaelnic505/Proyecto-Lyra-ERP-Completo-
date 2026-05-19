package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;

import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Orden_Costos_Model {

    public static void addRowResumentCost(LyraTable table) {
        
        LyraTableModel model = (LyraTableModel) table.getModel();

            model.addRow(new Object[]{"01","Operations", null, null, null});
            model.addRow(new Object[]{"02", "Materials and Spare Parts", null, null, null});
            model.addRow(new Object[]{"03", "External Services", null, null, null});
            
            model.addRow(new Object[]{null, "Totals", null, null, null});
        
        table.setModel(model);

    }
    
    public static void set(LyraTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Item", "Descripción concepto", "Monto total est.", "Monto total real.", "Liquidación"};
        String[] columnasEN = {"Item", "Concept description", "Total amount estimated.", "Total amount actual.", "Settlement"};
        String[] columnasFR = {"Article", "Description du concept", "Montant total estimé", "Montant total réel.", "Règlement"};
        String[] columnasPT = {"Item", "descrição do conceito", "Valor total estimado", "Valor total real.", "Povoado"};

            modelo.setColumnIdentifiers(columnasEN);

        tabla.setModel(modelo);
    }
}
