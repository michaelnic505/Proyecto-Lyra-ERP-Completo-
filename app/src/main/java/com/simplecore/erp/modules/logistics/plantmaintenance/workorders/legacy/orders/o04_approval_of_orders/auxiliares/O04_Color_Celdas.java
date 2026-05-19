package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders.auxiliares;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class O04_Color_Celdas extends JTable {

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
       
            Component componente = super.prepareRenderer(renderer, rowIndex, columnIndex);
            componente.setBackground(Color.WHITE);
            componente.setForeground(Color.BLACK);

        if (getColumnClass(columnIndex).equals(Boolean.class)) {

            componente.setBackground(Color.decode("#49be25"));
            componente.setForeground(Color.decode("#041014"));
//            String texto = getValueAt(rowIndex, columnIndex).toString();
//            
//            if (texto.equals("true")) {
//                componente.setBackground(Color.decode("#49be25"));
//                componente.setForeground(Color.decode("#041014"));
//            } else if (texto.equals("false")) {
//                componente.setBackground(Color.decode("#9925be"));
//                componente.setForeground(Color.decode("#ffffff"));
//            }

        }

        return componente;
    }

}
