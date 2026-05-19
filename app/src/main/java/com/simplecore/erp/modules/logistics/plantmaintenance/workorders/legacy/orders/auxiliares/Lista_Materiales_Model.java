package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;


import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Lista_Materiales_Model {
    
    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Código","Descripción material","Almacén","Descripción almacén","Precio Unit.","UM","Serie","Modelo","Marca"};
        String[] columnasEN = {"Code","Material description","Warehouse","Warehouse description","Unit price","UM","Series","Model","Brand"};
        String[] columnasFR = {"Code","Material description","Warehouse","Warehouse description","Unit price","UM","Series","Model","Brand"};
        String[] columnasPT = {"Code","Material description","Warehouse","Warehouse description","Unit price","UM","Series","Model","Brand"};
     
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
