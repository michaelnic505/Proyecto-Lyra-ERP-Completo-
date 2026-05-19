package com.simplecore.erp.client.gui.components.tables.interfaces;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class TableForParametersModel extends DefaultTableModel {

    

    public TableForParametersModel(String[] indentifiers) {
        setColumnIdentifiers(indentifiers);
    }

    public TableForParametersModel() {
    }

    private ArrayList<Integer> rowsNed;
    private ArrayList<Integer> colsNed;

    public void setCellNoEditable(ArrayList<Integer> rows,ArrayList<Integer> cols) {
        this.rowsNed = rows;
        this.colsNed = cols;
    }

    public ArrayList<Integer> getArrayNoEditable(){
        return rowsNed;
    }
    

    @Override
    public boolean isCellEditable(int row, int column) {

        if (rowsNed != null && colsNed != null) {
            for (int i = 0; i < rowsNed.size(); i++) {
                for (int c = 0; c < colsNed.size(); c++) {
                    if (row == rowsNed.get(i) && column == colsNed.get(c)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void clear(){
        for(int i = 0; i<getRowCount();i++){
            for(int c = 1; c < getColumnCount();c++){
                setValueAt(null, i, c);
            }
        }
        fireTableDataChanged();
    }

    
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
}
