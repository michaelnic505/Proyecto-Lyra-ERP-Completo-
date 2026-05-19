
package com.simplecore.erp.modules.system.access.utils;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class ToggleButtoCellRenderer extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Toggle button = new Toggle();
        
        return button;
    }
    
    
    
    
    
}
