package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils;

import com.simplecore.erp.gui.components.tables.newversions.TableForParameters;

public class ClearTables {

    public static void clearData(TableForParameters table) {

        int rows = table.getRowCount();
        int cols = table.getColumnCount();

        for (int i = 0; i < rows; i++) {
            for (int c = 1; c < cols; c++) {
                table.setValueAt(null, i, c);
            }
        }

    }

}
