package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.activity;

import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.procedures.MaintProcedures;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class ProceduresModelListener implements TableModelListener {

    private static final Logger logger = Logger.getLogger(ProceduresModelListener.class.getName());
    private final TableModel model; // Table model to hold data
    private final JTable table; // The JTable instance
    private boolean updating = false; // Flag to prevent infinite recursion

    // Constructor to initialize model and table
    public ProceduresModelListener(TableModel model, JTable table) {
        this.model = model;
        this.table = table;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        // Skip if we are updating programmatically to prevent recursion
        if (isUpdating()) {
            return;
        }

        int row = e.getFirstRow(); // Get the row index
        int column = e.getColumn(); // Get the column index

        // If the change occurred in the procedure key column (index 2)
        if (column == 2) {
            Object pKey = model.getValueAt(row, column); // Get the value in the cell
            if (pKey != null) {
                String procedureKey = pKey.toString(); // Convert to string
                // If procedure key is empty, clear the value in the cell
                if (procedureKey.isEmpty()) {
                    setUpdating(true);
                    model.setValueAt(null, row, 2); // Clear the procedure key cell
                    setUpdating(false);
                    return;
                }
                handleProcedureKey(row, procedureKey); // Handle the valid procedure key
            }
        }
    }

    // Check if the flag 'updating' is set to prevent recursion
    private synchronized boolean isUpdating() {
        return updating;
    }

    // Set the 'updating' flag to indicate whether an update is in progress
    private synchronized void setUpdating(boolean updating) {
        this.updating = updating;
    }

    // Handle the procedure key by verifying its validity and updating the description
    private void handleProcedureKey(int row, String procedureKey) {
        try {
            // If the procedure key is invalid, show an error and clear the cell
            if (!MaintProcedures.keyExists(procedureKey)) {
                showErrorAndClearCell(row);
                return;
            }

            setUpdating(true);

            // If the table is in editing mode, stop the cell editor to commit changes
            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }

            // Show a confirmation dialog asynchronously
            SwingUtilities.invokeLater(() -> {
                int response = showConfirmationDialog();
                // If user accepts, update the procedure description
                if (response == JOptionPane.YES_OPTION) {
                    updateProcedureDescription(row, procedureKey);
                }
            });

            setUpdating(false);
        } catch (Exception ex) {
            // Log any exceptions encountered during procedure key handling
            logger.log(Level.SEVERE, "Error processing procedureKey: " + procedureKey, ex);
        }
    }

    // Show an error notification and clear the cell if the procedure key is not valid
    private void showErrorAndClearCell(int row) {
        new SystemMessages(NOT.msg(NOT.PROCEDURE_KEY_NOT_FOUND), TypeMessage.ERROR);
        setUpdating(true);
        model.setValueAt(null, row, 2); // Clear the invalid procedure key in the cell
        setUpdating(false);
    }

    // Display a confirmation dialog asking the user if they want to use the procedure
    private int showConfirmationDialog() {
        return JOptionPane.showConfirmDialog(
                null,
                NOT.msg(NOT.WOULD_YOU_LIKE_USE_PROCEDURE_TITLE),
                NOT.msg(NOT.TITLE),
                JOptionPane.YES_NO_OPTION
        );
    }

    // Update the short description for the procedure key in the specified row
    private void updateProcedureDescription(int row, String procedureKey) {
        String shortDescription = MaintProcedures.getShortDescriptionByKey(procedureKey); // Retrieve the description
        model.setValueAt(shortDescription, row, 3); // Update the description in the third column
    }
}
