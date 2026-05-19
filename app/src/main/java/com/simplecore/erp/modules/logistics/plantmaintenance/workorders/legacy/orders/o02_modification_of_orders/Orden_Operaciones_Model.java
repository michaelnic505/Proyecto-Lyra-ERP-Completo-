package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders;


import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Orden_Operaciones_Model {
    
    public static void set(JTable tabla) {
        

        

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {
            
            "Op.",
            "T.E",
            "TE",
            "Descripción operación",
            "Trabajo",
            "Cantidad",
            "Duración",
            "Cod.Tip.Op.",
            "Tipo operación",
            "Costo Unit.",
            "Und Med.",
            "Monto total",
            "Paquete",
            "Sol.Ped.",
            "Moneda"
            
        };
        
        
        String[] columnasEN = {
            
            "Op.",
            "E.T",
            "ET",
            "Operation description",
            "Work",
            "Quantity",
            "Duration",
            "Cod.Op.Type",
            "Operation type",
            "Unit Cost",
            "Unit Meas.",
            "Total amount",
            "Package",
            "Purch.req.",
            "Currency"
            
        };

        String[] columnasFR = {};
        String[] columnasPT = {};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
