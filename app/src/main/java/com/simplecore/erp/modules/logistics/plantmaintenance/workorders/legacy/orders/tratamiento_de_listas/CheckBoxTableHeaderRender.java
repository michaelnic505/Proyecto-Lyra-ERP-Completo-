package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.TableCellRenderer;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;

public class CheckBoxTableHeaderRender extends JCheckBox implements TableCellRenderer {

    private final LyraTable table;
    private final int column;

    public CheckBoxTableHeaderRender(LyraTable table, int column) {
        this.table = table;
        this.column = column;
        init();
    }

    private void init() {
        setHorizontalAlignment(SwingConstants.CENTER);
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int col = table.columnAtPoint(e.getPoint());
                  
                    if (col == column) {
                        putClientProperty(FlatClientProperties.SELECTED_STATE, null);
                        setSelected(!isSelected());
                        selectedTableRow(isSelected());
                        table.getTableHeader().repaint();
                    }
                }
            }

        });

        table.getModel().addTableModelListener((tame) -> {
            if (tame.getColumn() == column) {
                checkRow();
            }
        });

    }

    private void checkRow() {

        boolean initValue = table.getRowCount()==0? false:(boolean)table.getValueAt(0, column);
        
        for(int i = 1; i < table.getRowCount();i++){
            boolean b = (boolean)table.getValueAt(i, column);
            if(initValue!=b){
                putClientProperty(FlatClientProperties.SELECTED_STATE, FlatClientProperties.SELECTED_STATE_INDETERMINATE);
                table.getTableHeader().repaint();
                return;
            }
        }
        
        putClientProperty(FlatClientProperties.SELECTED_STATE, null);
        setSelected(initValue);
        table.getTableHeader().repaint();

    }

    private void selectedTableRow(boolean selected) {
        for (int i = 0; i < table.getRowCount(); i++) {
            table.setValueAt(selected, i, column);
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        setBackground(((LyraTable) table).getColumnColor());
        setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        setBorderPainted(true);
        setHorizontalAlignment(SwingConstants.CENTER);

        return this;
    }

}
