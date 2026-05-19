package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.simplecore.erp.gui.components.searchbox.JSearchBox;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui.RestrictionsList;

public class RestrictionsTable_CellEditor extends DefaultCellEditor {

    private JFrame frame;

    public RestrictionsTable_CellEditor(JFrame frame) {

        super(new JTextField());
        this.frame = frame;
        setClickCountToStart(1);

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        JSearchBox searchBox = new JSearchBox();
        searchBox.getButton().addActionListener((e) -> {

            RestrictionsList lr = new RestrictionsList(frame);
            lr.setCampos(searchBox.getTextBox(), null);
            lr.setVisible(true);

        });

        if (value != null) {
            searchBox.getTextBox().setText(value.toString());
            setValue(searchBox.getTextBox().getText());
        }else{
            setValue(null);
        }
        
        searchBox.getTextBox().setEditable(false);
        searchBox.getTextBox().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setValue(searchBox.getTextBox().getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setValue(null);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setValue(searchBox.getTextBox().getText());
            }

        });

        searchBox.getTextBox().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                table.setValueAt(searchBox.getTextBox().getText(), row, column);
            }

        });

        return searchBox;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    private String value;

}
