
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;




public class CheckBoxTableCellEditor extends DefaultCellEditor{
    
    public CheckBoxTableCellEditor() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        Component comp = super.getTableCellEditorComponent(table, value, isSelected, row, column);

        comp.setBackground(table.getSelectionBackground());
        
        if (row % 2 == 0) {
            comp.setBackground(((LyraTable)table).getColorRow1());
        }else{
            comp.setBackground(((LyraTable)table).getColorRow2());
        }

        
        return comp;
    }
    
    
}
