
package com.simplecore.erp.client.gui.connections;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import com.simplecore.erp.client.gui.components.labels.JLabelHQ;
import javax.swing.Icon;



public class FolderListCellRender extends JLabelHQ implements ListCellRenderer<Object>{   
    //new CustomSVGIcon("/icons/svg/server.svg",new Dimension(22,22))
   private Icon icon;
    public FolderListCellRender(Icon icon) {
        this.icon = icon;
    }
   
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        setText(value.toString());
        setIcon(icon);
        
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
