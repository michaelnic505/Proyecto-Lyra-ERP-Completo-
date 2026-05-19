package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.operaciones_orden_texto_explicativo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;


public class TextoExplicativo_Render extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        
        LyraTable ta = (LyraTable) table;
        
        TextoExplicativo te = new TextoExplicativo();
        
        if (isSelected) {

                te.setBackground(ta.getColorSelection());

            } else {
                if (row % 2 == 0) {
                    te.setBackground(ta.getColorRow1());
                } else {
                    te.setBackground(ta.getColorRow2());
                }
            }

        return te;

    }

}
