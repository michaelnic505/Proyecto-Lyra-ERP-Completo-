package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.activity;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class OperationsCalculationListener implements TableModelListener {

    private final TableModel model;
    private boolean updating = false; // Prevents infinite recursion

    // Decimal format for thousands separator and two decimals
    private static final DecimalFormat FORMATTER = new DecimalFormat("#,##0.00");

    public OperationsCalculationListener(TableModel model) {
        this.model = model;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (updating) return; // Skip if we are updating programmatically

        int row = e.getFirstRow();
        int column = e.getColumn();

        // Check if columns for quantity, duration, or unit cost are updated
        if (column == 5 || column == 6 || column == 9) {
            updateCalculations(row);
        }
    }

    private void updateCalculations(int row) {
        try {
            updating = true; // Prevent recursive calls

            // Fetch values safely
            double quantity = parseDouble(model.getValueAt(row, 5));
            double duration = parseDouble(model.getValueAt(row, 6));
            double unitCost = parseDouble(model.getValueAt(row, 9));

            // Perform calculations only if all required values are greater than zero
            if (quantity > 0 && duration > 0) {
                double work = quantity * duration;
                updateCell(row, 4, work); // Set the calculated work value

                if (unitCost > 0) {
                    double totalAmount = unitCost * work;
                    totalAmount = roundToDecimals(totalAmount, 2); // Round to 2 decimal places
                    updateFormattedCell(row, 11, totalAmount); // Set the formatted value
                }
            }

        } catch (NumberFormatException ex) {
            System.err.println("Error parsing number in row " + row + ": " + ex.getMessage());
        } finally {
            updating = false; // Reset flag
        }
    }

    /**
     * Parses an object to a double safely.
     * @param obj The object to parse.
     * @return The parsed double value or 0 if invalid.
     */
    private double parseDouble(Object obj) {
        if (obj == null || obj.toString().trim().isEmpty()) {
            return 0;  // Retorna 0 si el valor es nulo o vacío
        }
        try {
            return Double.parseDouble(obj.toString().trim().replace(",", ""));
        } catch (NumberFormatException ex) {
            return 0;  // Retorna 0 si no se puede parsear correctamente
        }
    }

    /**
     * Updates a cell only if the new value is different from the current value.
     *
     * @param row The row index.
     * @param column The column index.
     * @param newValue The new value to set.
     */
    private void updateCell(int row, int column, double newValue) {
        Object currentValue = model.getValueAt(row, column);
        if (currentValue == null || !currentValue.toString().equals(String.valueOf(newValue))) {
            model.setValueAt(newValue, row, column);
        }
    }

    /**
     * Updates a cell with a formatted value (with thousands separator).
     * @param row The row index.
     * @param column The column index.
     * @param newValue The new value to set.
     */
    private void updateFormattedCell(int row, int column, double newValue) {
        String formattedValue = FORMATTER.format(newValue);
        Object currentValue = model.getValueAt(row, column);
        if (currentValue == null || !currentValue.toString().equals(formattedValue)) {
            model.setValueAt(formattedValue, row, column);
        }
    }

    /**
     * Rounds a double value to a specified number of decimal places.
     * @param value The value to round.
     * @param places The number of decimal places.
     * @return The rounded value.
     */
    private double roundToDecimals(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Decimal places must be non-negative.");
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
