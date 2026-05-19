package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.operaciones_orden_texto_explicativo;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;


public class TextoExplicativo_Editor extends DefaultCellEditor {

    TableTextoExpEvent event;
    
    public TextoExplicativo_Editor(TableTextoExpEvent event) {
        
        super(new JComboBox());
        this.event = event;
        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        LyraTable ta = (LyraTable) table;

        TextoExplicativo te = new TextoExplicativo();
        te.eventosBotonTextoExp(event, row);

        if (isSelected) {

            te.setBackground(ta.getColorSelection());

        } else {
            if (row % 2 == 0) {
                te.setBackground(ta.getColorRow1());
            } else {
                te.setBackground(ta.getColorRow2());
            }
        }

        return te;

    }

}
