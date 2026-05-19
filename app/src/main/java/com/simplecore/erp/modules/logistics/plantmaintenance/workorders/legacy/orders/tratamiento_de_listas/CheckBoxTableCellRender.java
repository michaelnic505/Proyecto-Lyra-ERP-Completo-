
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;



public class CheckBoxTableCellRender extends JCheckBox implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (row % 2 == 0) {
            setBackground(((LyraTable) table).getColorRow1());
        } else {
            setBackground(((LyraTable) table).getColorRow2());
        }

        
        return this;
    }



}
