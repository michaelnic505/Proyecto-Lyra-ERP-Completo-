package com.simplecore.erp.gui.components.tables.lastversion;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author user
 */
public class FreezeColumnTable extends JScrollPane {

    /*
	 *  Specify the number of columns to be fixed and the scroll pane
	 *  containing the table.
     */
    public FreezeColumnTable(JTable table, int colsToFreeze) {
        super(table);
        TableModel model = table.getModel();
        //create a frozen model     
        TableModel frozenModel = new DefaultTableModel(
                model.getRowCount(),
                colsToFreeze);
        //populate the frozen model     
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < colsToFreeze; j++) {
                String value = (String) model.getValueAt(i, j);
                frozenModel.setValueAt(value, i, j);
            }
        }
        //create frozen table
        JTable frozenTable = new JTable(frozenModel);
        //remove the frozen columns from the original table
        for (int j = 0; j < colsToFreeze; j++) {
            table.removeColumn(table.getColumnModel().getColumn(0));
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //format the frozen table     
        JTableHeader header = table.getTableHeader();
        frozenTable.setBackground(header.getBackground());
        frozenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        frozenTable.setEnabled(false);
        //set frozen table as row header view   
        JViewport viewport = new JViewport();
        viewport.setView(frozenTable);
        viewport.setPreferredSize(frozenTable.getPreferredSize());
        setRowHeaderView(viewport);
        setCorner(JScrollPane.UPPER_LEFT_CORNER,
                frozenTable.getTableHeader());
    }
}
