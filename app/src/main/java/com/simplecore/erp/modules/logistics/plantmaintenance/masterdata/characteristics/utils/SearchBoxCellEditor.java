
package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils;

import java.awt.Component;
import java.awt.Point;
import javax.swing.DefaultCellEditor;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.simplecore.erp.gui.components.searchbox.JSearchBox;



public class SearchBoxCellEditor extends DefaultCellEditor{
    
    public SearchBoxCellEditor() {
      
        super(new JTextField());
        setClickCountToStart(1);
        
    }

    String lan;
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

       
        
        JSearchBox sb = new JSearchBox();
        sb.getTextBox().setEditable(false);
        
        if (value != null) {
            sb.getTextBox().setText(value.toString());
            lan = sb.getTextBox().getText();
        }

        JPopupMenu menu = new JPopupMenu();

        JMenuItem es = new JMenuItem("ES - Español");
        JMenuItem en = new JMenuItem("EN - English");

        menu.add(es);
        menu.add(en);

        sb.getButton().addActionListener((e) -> {

            Component b = (Component) e.getSource();
            Point p = b.getLocationOnScreen();

            menu.show(table, 0, 0);
            menu.setLocation(p.x, p.y + b.getHeight());

        });

        es.addActionListener((e) -> {
           
            sb.getTextBox().setText("ES");
            lan = sb.getTextBox().getText();
            table.getCellEditor().stopCellEditing();
            table.setValueAt(lan, row, 1);
        
        });
        en.addActionListener((e) -> {
     
            sb.getTextBox().setText("EN");
            lan = sb.getTextBox().getText();
            table.getCellEditor().stopCellEditing();
            table.setValueAt(lan, row, 1);
       
        });

        return sb;
    }

    
    
}
