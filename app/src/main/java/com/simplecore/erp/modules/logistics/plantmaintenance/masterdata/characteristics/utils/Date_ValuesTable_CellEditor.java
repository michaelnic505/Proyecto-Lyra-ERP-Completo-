
package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;


public class Date_ValuesTable_CellEditor extends DefaultCellEditor {
    
    private String temp;

    public Date_ValuesTable_CellEditor(String temp) {
        
        super(new JCheckBox());
        this.temp = temp;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        
        SimpleDateFormat date = new SimpleDateFormat(temp);
        
        JDateChooser dc = new JDateChooser();
        dc.setDateFormatString(temp);
        
        if(value!=null){
            try {
                dc.setDate(date.parse(value.toString()));
            } catch (ParseException ex) {
                Logger.getLogger(Date_ValuesTable_CellEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        ((JTextFieldDateEditor)dc.getDateEditor()).setEditable(false);
        
        ((JTextFieldDateEditor)dc.getDateEditor()).addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (dc.getDate() != null) {
                        String dat = date.format(dc.getDate());
                        table.setValueAt(dat, row, column);
                    } else {
                        table.setValueAt(null, row, column);
                    }
                    
                    table.getCellEditor().stopCellEditing();
                }
            }

        });

        table.getColumnModel().getColumn(column).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            @Override
            public void editingStopped(ChangeEvent e) {

                if (dc.getDate() != null) {
                    String dat = date.format(dc.getDate());
                    table.setValueAt(dat, row, column);
                }else{
                    table.setValueAt(null, row, column);
                }

            }

            @Override
            public void editingCanceled(ChangeEvent e) {
                
            }
            
        });
        
        return dc;
    }
    
}
