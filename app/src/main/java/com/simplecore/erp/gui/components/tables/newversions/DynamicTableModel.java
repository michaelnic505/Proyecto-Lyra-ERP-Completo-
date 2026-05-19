package com.simplecore.erp.gui.components.tables.newversions;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * DynamicTableModel.java
 *
 * Una implementación flexible y dinámica de un modelo de tabla para gestionar datos tabulares en Java Swing.
 * Esta clase proporciona funcionalidades para agregar, eliminar y editar filas y celdas dinámicamente,
 * permitiendo un control detallado sobre la editabilidad de las celdas.
 *
 * Desarrollado por: Michael F. Sanchez
 * Fecha: [01-09-2025]
 * 
 * © Michael F. Sanchez. Todos los derechos reservados. El uso, modificación o distribución no autorizada
 * sin el consentimiento previo por escrito está estrictamente prohibido.
 * 
 * Propósito:
 * Esta clase fue diseñada para simplificar la gestión de datos en componentes JTable, ofreciendo una 
 * estructura altamente personalizable que se adapta a una variedad de casos de uso.
 * 
 * Contacto:
 * Para consultas o más información, por favor contacte a Michael F. Sanchez en [correo o enlace de contacto, opcional].
 */

public class DynamicTableModel extends AbstractTableModel {

    private String[] columnNames; // Almacena los nombres de las columnas
    private List<Object[]> data = new ArrayList<>(); // Almacena los datos de la tabla
    private List<boolean[]> editableCells = new ArrayList<>(); // Indica qué celdas son editables

    /**
     * Constructor que inicializa el modelo con un número de filas y nombres de
     * columnas.
     *
     * @param rows Número de filas iniciales.
     * @param columnNames Arreglo con los nombres de las columnas.
     */
    public DynamicTableModel(int rows, String[] columnNames) {
        this.columnNames = columnNames;
        initializeData(rows, columnNames.length);
        initializeEditableCells(rows, columnNames.length, (row, col) -> true);
    }

    /**
     * Inicializa los datos de la tabla con cadenas vacías.
     *
     * @param rows Número de filas.
     * @param columns Número de columnas.
     */
    private void initializeData(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            Object[] row = new Object[columns];
            Arrays.fill(row, null); // Llena la fila con cadenas vacías
            data.add(row);
        }
    }

    /**
     * Inicializa la condición de editabilidad de las celdas.
     *
     * @param rows Número de filas.
     * @param columns Número de columnas.
     * @param editabilityCondition Condición para determinar si una celda es
     * editable.
     */
    private void initializeEditableCells(int rows, int columns, BiPredicate<Integer, Integer> editabilityCondition) {
        editableCells.clear();
        for (int i = 0; i < rows; i++) {
            boolean[] rowEditability = new boolean[columns];
            for (int j = 0; j < columns; j++) {
                rowEditability[j] = editabilityCondition.test(i, j);
            }
            editableCells.add(rowEditability);
        }
    }

    @Override
    public int getRowCount() {
        return data.size(); // Devuelve el número de filas en la tabla
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; // Devuelve el número de columnas en la tabla
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex]; // Devuelve el valor de una celda específica
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex] = value; // Asigna un nuevo valor a la celda
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica el cambio
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex]; // Devuelve el nombre de la columna
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editableCells.get(rowIndex)[columnIndex]; // Indica si una celda es editable
    }

    /**
     * Establece la editabilidad de una celda específica.
     *
     * @param row Índice de la fila.
     * @param col Índice de la columna.
     * @param editable Indica si la celda es editable.
     */
    public void setCellEditable(int row, int col, boolean editable) {
        editableCells.get(row)[col] = editable;
        fireTableCellUpdated(row, col); // Notifica el cambio
    }

    /**
     * Establece una condición general de editabilidad para todas las celdas.
     *
     * @param editabilityCondition Condición de editabilidad.
     */
    public void setEditableCondition(BiPredicate<Integer, Integer> editabilityCondition) {
        initializeEditableCells(data.size(), getColumnCount(), editabilityCondition);
        fireTableDataChanged(); // Notifica que los datos han cambiado
    }

    /**
     * Actualiza los datos del modelo con una nueva lista de datos.
     *
     * @param newData Lista con los nuevos datos.
     */
    public void setData(List<Object[]> newData) {
        if (newData.stream().anyMatch(row -> row.length != getColumnCount())) {
            throw new IllegalArgumentException("All rows must have the same number of columns as the table.");
        }
        this.data = newData;
        initializeEditableCells(newData.size(), getColumnCount(), (row, col) -> false);
        fireTableDataChanged(); // Notifica los cambios
    }

    /**
     * Agrega una nueva fila al modelo.
     *
     * @param newRow Arreglo con los valores de la nueva fila.
     */
    public void addRow(Object[] newRow) {
        if (newRow.length != getColumnCount()) {
            throw new IllegalArgumentException("New row must have the same number of columns as the table.");
        }
        data.add(newRow);
        boolean[] newEditability = new boolean[getColumnCount()];
        editableCells.add(newEditability);
        fireTableRowsInserted(data.size() - 1, data.size() - 1); // Notifica la inserción
    }

    /**
     * Agrega una fila vacía al modelo.
     */
    public void addRow() {
        Object[] emptyRow = new Object[getColumnCount()];
        Arrays.fill(emptyRow, null); // Llena la fila con cadenas vacías
        addRow(emptyRow);
    }

    /**
     * Elimina una fila específica del modelo.
     *
     * @param row Índice de la fila a eliminar.
     */
    public void removeRow(int row) {
        if (row < 0 || row >= getRowCount()) {
            throw new IndexOutOfBoundsException("Row index " + row + " is out of bounds. Valid range: 0 to " + (getRowCount() - 1));
        }
        data.remove(row);
        editableCells.remove(row);
        fireTableRowsDeleted(row, row); // Notifica la eliminación
    }

    /**
     * Limpia el contenido de la tabla, estableciendo todas las celdas como
     * null, pero manteniendo la cantidad de filas.
     */
    public void clearTable() {
        for (int i = 0; i < data.size(); i++) {
            Arrays.fill(data.get(i), null); // Establece todas las celdas como null
        }
        fireTableDataChanged(); // Notifica los cambios en la tabla
    }
}
