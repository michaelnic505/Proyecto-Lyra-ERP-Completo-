package com.simplecore.erp.client.gui.components.tables.lastverion;

import javax.swing.table.DefaultTableModel;

public class LyraTableModel extends DefaultTableModel {

    int[] indexColumns;
    int indexRows = -1;

    @Override
    public void addRow(Object[] rowData) {

        int cant = rowData.length;
        Object[] newRowData = new Object[cant + 1];

        for (int i = 0; i < newRowData.length; i++) {
            if (i == 0) {
                newRowData[i] = null;
            } else {
                newRowData[i] = rowData[i - 1];
            }
        }

        super.addRow(newRowData);
    }

    public void setColumnNoEditable(int[] index) {
        this.indexColumns = index;
    }

    public void setRowEditable(int index) {
        this.indexRows = index;
    }

    @Override
    public boolean isCellEditable(int row, int column) {

        if (row == indexRows) {
            if (indexColumns != null) {

                for (int i = 0; i < indexColumns.length; i++) {
                    if (column == indexColumns[i]) {
                        return false;
                    }
                }
            }
            return true;
        }

        if (column == 0) {
            return true;
        }

        return false;
    }

    @Override
    public void setColumnIdentifiers(Object[] newIdentifiers) {

        int cant = newIdentifiers.length;
        Object[] newIdentifier = new Object[cant + 1];

        for (int i = 0; i < newIdentifier.length; i++) {
            if (i == 0) {
                newIdentifier[i] = "";
            } else {
                newIdentifier[i] = newIdentifiers[i - 1];
            }
        }

        super.setColumnIdentifiers(newIdentifier);
    }

    Class[] classes;

    public void setClass(Class[] classes) {
        this.classes = classes;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        if (classes != null) {
            return classes[columnIndex];
        }

        return super.getColumnClass(columnIndex);

    }

}
