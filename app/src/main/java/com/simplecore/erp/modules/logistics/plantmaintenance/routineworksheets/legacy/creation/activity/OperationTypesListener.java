package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.activity;

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

public class OperationTypesListener implements TableModelListener {

    private static final Logger logger = Logger.getLogger(OperationTypesListener.class.getName());
    private final TableModel model; // Table model reference
    private final JTable table; // Table instance
    private boolean updating = false; // Prevents infinite recursion

    // Constructor
    public OperationTypesListener(TableModel model, JTable table) {
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

        if (column == 7) { // Check if the change is in the operation code column
            Object opcode = model.getValueAt(row, column);
            if (opcode != null) {
                String operationCode = opcode.toString().trim();

                if (operationCode.isEmpty()) {
                    updating = true;

                    setNullValues(row);

                    updating = false;
                    return;
                }

                handleOperationCode(row, operationCode);
            }
        }
    }

    private void handleOperationCode(int row, String operationCode) {
        try {
            updating = true;

            // Fetch operation details in one go
            OperationTypesMaint.OperationDetails details = OperationTypesMaint.getOperationDetails(operationCode);
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
            logger.log(Level.SEVERE, "Error processing operationCode: " + operationCode, ex);
        } finally {
            updating = false;
        }
    }
    private void setNullValues(int row) {
        model.setValueAt(null, row, 7);
        model.setValueAt(null, row, 8);
        model.setValueAt(null, row, 9);
        model.setValueAt(null, row, 10);
        model.setValueAt(null, row, 11);
        model.setValueAt(null, row, 13);
    }

    private void clearInvalidOperation(int row) {

        new SystemMessages(NOT.msg(NOT.OPERATION_CODE_NOT_FOUND), TypeMessage.ERROR);

        setNullValues(row);

        ((DynamicTableModel) model).setCellEditable(row, 8, true);
        ((DynamicTableModel) model).setCellEditable(row, 13, true);
        ((DynamicTableModel) model).setCellEditable(row, 9, true);
        ((DynamicTableModel) model).setCellEditable(row, 10, true);

    }

    private void updateOperationDetails(int row, OperationTypesMaint.OperationDetails details) {
        
        model.setValueAt(details.description, row, 8);
        model.setValueAt(details.unitCost, row, 9);
        model.setValueAt(details.unitOfMeasure, row, 10);
        model.setValueAt(details.currency, row, 13);
        
        if (details.unitCost > 0) {
            ((DynamicTableModel) model).setCellEditable(row, 9, false);
            ((DynamicTableModel)model).setCellEditable(row, 8, false);
            ((DynamicTableModel) model).setCellEditable(row, 10, false);
            ((DynamicTableModel)model).setCellEditable(row, 13, false);
        }else{
            ((DynamicTableModel) model).setCellEditable(row, 9, true);
            ((DynamicTableModel) model).setCellEditable(row, 10, true);
            ((DynamicTableModel) model).setCellEditable(row, 13, true);
        }
            
    }
}
