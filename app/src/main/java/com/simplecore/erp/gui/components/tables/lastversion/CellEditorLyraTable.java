package com.simplecore.erp.gui.components.tables.lastversion;

import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class CellEditorLyraTable extends DefaultCellEditor {

    TableEventSimpleButton evt;

    public CellEditorLyraTable(TableEventSimpleButton evt) {
        super(new JCheckBox());
        this.evt = evt;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        TableSimpleButton button = new TableSimpleButton();
        button.eventoBotonSelection(evt, row);

        return button;
    }

}
