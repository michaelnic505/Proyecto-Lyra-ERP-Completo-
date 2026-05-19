
package com.simplecore.erp.gui.components.tablecelleditors;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CellEditorNumbers extends DefaultCellEditor {

    public CellEditorNumbers() {
        super(new JTextField());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        
        table.setSurrendersFocusOnKeystroke(true);

        Component comp = super.getTableCellEditorComponent(table, value, isSelected, row, column);

        comp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                }
            }

        });

        return comp;
    }
    
    
    

}
