package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders;


import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Orden_Materiales_Model {
    
    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {
            "Pos.",
            "Código",
            "S",
            "Descripción material",
            "Cantidad",
            "UM",
            "Costo Unit.",
            "Almacén",
            "Monto total",
            "Op.",
            "Paquete"
        };
       
        
        String[] columnasEN = {
            "Pos.",
            "Code",
            "S",
            "Material description",
            "Quantity",
            "MU",
            "Unit cost",
            "Warehouse",
            "Total amount",
            "Op.",
            "Package"
        };
        
        String[] columnasFR = {"Non. Op.","Code","Description","Ne peut pas.","U.M.","Unité C.","Montant total","Emballer"};
        String[] columnasPT = {"Não. Op.","Código","Descrição","Não pode.","UM.","C.Unidade.","Montante total","Pacote"};
     
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
    
    
}
