
package com.simplecore.erp.modules.system.access.utils;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class ToggleButtonCellEditor extends DefaultCellEditor{

    public ToggleButtonCellEditor() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        Toggle toggle = new Toggle();

        
        return toggle;
        
    }

}
