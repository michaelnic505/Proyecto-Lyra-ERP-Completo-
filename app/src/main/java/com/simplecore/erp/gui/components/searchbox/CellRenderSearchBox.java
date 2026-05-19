
package com.simplecore.erp.gui.components.searchbox;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class CellRenderSearchBox extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JSearchBox box = new JSearchBox();
        
        return box;
    }
    
    
}
