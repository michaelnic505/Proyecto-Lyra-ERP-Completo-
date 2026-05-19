
package com.simplecore.erp.gui.dbconnections;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import com.simplecore.erp.gui.components.labels.JLabelHQ;



public class ListCellRendererLyra extends JLabelHQ implements ListCellRenderer<Object>{   
    
   
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        setText(value.toString());
        setIcon(new ImageIcon(getClass().getResource("/icons/connectionsdb/carpeta.png")));
        
        if(isSelected){
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }else{
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        
        setOpaque(true);
                
        return this;
    }
    
    
}
