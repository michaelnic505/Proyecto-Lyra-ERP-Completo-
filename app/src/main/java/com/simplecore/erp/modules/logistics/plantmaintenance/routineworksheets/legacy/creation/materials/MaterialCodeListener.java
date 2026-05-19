package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.materials;

import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.RegisteredWarehouseMaterials;

public class MaterialCodeListener implements TableModelListener {

    private static final Logger logger = Logger.getLogger(MaterialCodeListener.class.getName());
    private final TableModel model; // Table model reference
    private final JTable table; // Table instance
    private boolean updating = false; // Prevents infinite recursion

    // Constructor
    public MaterialCodeListener(TableModel model, JTable table) {
        this.model = model;
        this.table = table;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (updating) {
            return; // Avoid recursion
        }

        int row = e.getFirstRow();
        int column = e.getColumn();

        if (column == 2) { // Check if the change is in the operation code column
            Object matCode = model.getValueAt(row, column);
            Object warehCode = model.getValueAt(row, 7);
            if (matCode != null&&warehCode!=null) {
                String materialCode = matCode.toString().trim();
                String warehouseCode = warehCode.toString().trim();

                if (materialCode.isEmpty()) {
                    updating = true;

                    setNullValues(row);

                    updating = false;
                    return;
                }
                handleOperationCode(row, materialCode,warehouseCode);
            }
        }
    }

    private void handleOperationCode(int row, String materialCode, String warehouseCode) {
        try {
            updating = true;

            // Fetch operation details in one go
            RegisteredWarehouseMaterials.MaterialDetails details = 
                    RegisteredWarehouseMaterials.getMaterialDetails(materialCode,warehouseCode);
            if (details == null) {
                clearInvalidOperation(row);
                return;
            }

            // Ensure the cell editor stops editing before modifying the table
            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }

            updateOperationDetails(row, details);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error processing materialCode: " + materialCode, ex);
        } finally {
            updating = false;
        }
    }
    private void setNullValues(int row) {
        
        model.setValueAt(null, row, 2);
        model.setValueAt(null, row, 3);
        model.setValueAt(null, row, 5);
        model.setValueAt(null, row, 6);
        model.setValueAt(null, row, 7);

    }

    private void clearInvalidOperation(int row) {

        new SystemMessages(NOT.msg(NOT.MATERIAL_DOES_NOT_EXIST), TypeMessage.ERROR);
        setNullValues(row);

    }

    private void updateOperationDetails(int row, RegisteredWarehouseMaterials.MaterialDetails details) {
        
        model.setValueAt(details.description, row, 3);
        model.setValueAt(details.unitOfMeasure, row, 5);
        model.setValueAt(details.unitCost, row, 6);
        model.setValueAt(details.warehouseCode, row, 7);
        
        ((DynamicTableModel)model).setCellEditable(row, 2, false);
            
    }
}
