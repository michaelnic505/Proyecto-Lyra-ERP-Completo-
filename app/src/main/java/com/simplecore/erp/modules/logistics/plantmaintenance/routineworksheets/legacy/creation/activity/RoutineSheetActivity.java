package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.activity;

import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.packages.RoutineSheetPackage;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.packages.RoutineSheetPackageDAO;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.components.tables.interfaces.TableButtonListener;
import com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons;
import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import java.sql.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import com.simplecore.erp.utils.documentfilters.DocFilterCurrencyCode;
import com.simplecore.erp.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.utils.documentfilters.DocumentFilterVarchar;
import com.simplecore.erp.utils.documentfilters.NumDoubleLimitFilter;
import com.simplecore.erp.i18n.LanguageManager;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.celleditors.MaintProceduresEditor;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.celleditors.OperationTypesEditor;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.FilterDocumentEditor;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.header.RoutineSheetHeader;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.header.RoutineSheetHeaderDAO;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.materials.RoutineSheetMaterial;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.materials.RoutineSheetMaterialDAO;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.materials.RoutineSheetMaterialsForm;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.packages.PackagesRoutineSheet;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class RoutineSheetActivity extends javax.swing.JPanel {

    
    private LanguageManager languageManager;

    public RoutineSheetActivity(LanguageManager languageManager) {

        this.languageManager = languageManager;
        initComponents();
        addComponentsEvents();

    }

    private void addComponentsEvents() {
        exitButton();
        saveButton();
        setTableModel();
        
        sparePartsButton();
        packagesRoutineButton();
        
        removeOperationButton();
        
        selectAllRowsButton();
        deselectAllRowsButton();
    }

    //Metodos para la gestion de la tabla    
    private String[] getTableColumnsName() {
        String[] keys = {
            null, // Primera columna para botones
            "column.operation",
            "column.procedure",
            "column.operation.description",
            "column.work",
            "column.quantity",
            "column.duration",
            "column.operation.type.code",
            "column.operation.type",
            "column.unit.cost",
            "column.unit.of.measure",
            "column.total.amount",
            "column.order.request",
            "column.currency"
        };

        return IntStream.range(0, keys.length)
                .mapToObj(i -> (i == 0) ? null : LyraWorkspace.getTableTranslator().getTranslation(keys[i]))
                .toArray(String[]::new);
    }

    private void setTableModel() {

        int rows = 100;
        String[] columnNames = getTableColumnsName();

        DynamicTableModel model = new DynamicTableModel(rows, columnNames);
        addProcedureKeyListener(model);
        addOperationTypesListener(model,operationListTable);
        addCalculationListener(model);
        setOperationNums(model,rows);

        operationListTable.setModel(model);
        operationListTable.setSelectedRowsList(selectedRows);

        avoidSelectColumn0();
        setTableButtonListener(model);
        setColumnWidths();
        resetSelection();

        setProcedureSearchBox(operationListTable);
        setOperationTypeSearchBox(operationListTable);
        setTableMaxFieldsLengths(operationListTable);
        activateCellEditorOnKeyPress(operationListTable);
        
    }
    private void setColumnWidths() {
        // Column indices and their corresponding widths
        int[][] columnWidths = {
            {1, 50},
            {2, 100},
            {3, 350},
            {4, 80},
            {5, 80},
            {6, 80},
            {7, 100},
            {8, 210},
            {9, 60},
            {10, 80},
            {11, 100},
            {12, 80},
            {13, 80}
        };

        // Set column widths using a loop
        Arrays.stream(columnWidths)
                .forEach(col -> operationListTable.getColumnModel().getColumn(col[0]).setPreferredWidth(col[1]));
    }
    private void resetSelection() {
        operationListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (operationListTable.columnAtPoint(e.getPoint()) != 0) {
                    selectedRows.clear();
                    selectedOperations.clear();
                    selectedOperationsDescriptions.clear();
                }
            }

        });
    }
    private void avoidSelectColumn0() {
        operationListTable.getColumnModel().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedColumn = operationListTable.getSelectedColumn();
                if (selectedColumn == 0) {
                    // Si se selecciona la columna 0, cambiamos la selección a la columna 1
                    operationListTable.changeSelection(operationListTable.getSelectedRow(), 1, false, false);
                }
            }
        });
    }

    private ArrayList<Integer> selectedRows = new ArrayList<>();
    private ArrayList<String> selectedOperations = new ArrayList<>();
    private ArrayList<String> selectedOperationsDescriptions = new ArrayList<>();
    
    private void assingOperationValues(DynamicTableModel model, int row) {
        String operation = (String) model.getValueAt(row, 1);
        String description = (String) model.getValueAt(row, 3);
        
        selectedOperations.add(operation);
        selectedOperationsDescriptions.add(description);
    }
    private void clearOperations() {
        selectedOperations.clear();
        selectedOperationsDescriptions.clear();
    }

    private void removeOperations(DynamicTableModel model, int row) {
        String operation = (String) model.getValueAt(row, 1);
        String description = (String) model.getValueAt(row, 3);

        if (operation != null && !operation.isEmpty() && selectedOperations.contains(operation)) {
            selectedOperations.remove(operation);
        }
        if (description != null && !description.isEmpty() && selectedOperationsDescriptions.contains(description)) {
            selectedOperationsDescriptions.remove(description);
        }
    }
    private void sortOperationsAndDescriptions() {
        if (selectedOperations.size() != selectedOperationsDescriptions.size()) {
            throw new IllegalStateException("Mismatch between operations and descriptions sizes.");
        }

        List<AbstractMap.SimpleEntry<String, String>> operationsWithDescriptions = new ArrayList<>();

        for (int i = 0; i < selectedOperations.size(); i++) {
            String operation = selectedOperations.get(i);
            String description = selectedOperationsDescriptions.get(i);

            if (!operation.matches("\\d+")) { // Ensure the operation string contains only numbers
                throw new NumberFormatException("Invalid numeric value: " + operation);
            }

            operationsWithDescriptions.add(new AbstractMap.SimpleEntry<>(operation, description));
        }

        // Sort by numeric order
        operationsWithDescriptions.sort(Comparator.comparingInt(entry -> Integer.parseInt(entry.getKey())));

        // Replace original lists with sorted data
        selectedOperations = operationsWithDescriptions.stream()
                .map(AbstractMap.SimpleEntry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));

        selectedOperationsDescriptions = operationsWithDescriptions.stream()
                .map(AbstractMap.SimpleEntry::getValue)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    private void setTableButtonListener(DynamicTableModel model) {
        operationListTable.addTableButtonListener(new TableButtonListener() {
            @Override
            public void actionPerformed(int row) {

                // Add or remove based on the current selection
                if (selectedRows.contains(row)) {
                    selectedRows.remove(Integer.valueOf(row));
                    removeOperations(model, row);
                } else {
                    selectedRows.add(row);
                }

                // Update table selection only if necessary
                if (!selectedRows.isEmpty()) {
                    operationListTable.clearSelection();
                    for (int selectedRow : selectedRows) {
                        operationListTable.addRowSelectionInterval(selectedRow, selectedRow);
                        operationListTable.addColumnSelectionInterval(1, operationListTable.getColumnCount() - 1);
                    }
                }

                // Stop cell editing if an editor is active
                TableCellEditor editor = operationListTable.getCellEditor();
                if (editor != null) {
                    editor.stopCellEditing();
                }
            }
        });
    }

    private static final int OPERATION_MAX_LENGTH = 4; //column 1
    private static final int PROCEDURE_MAX_LENGTH = 15; //column 2
    private static final int OPERATION_DESCRIPTION_MAX_LENGTH = 45;//column 3
    private static final int WORK_MAX_LENGTH = 6;//column 4
    private static final int QUANTITY_MAX_LENGTH = 6;//column 5
    private static final int DURATION_MAX_LENGTH = 6;//column 6
    private static final int OP_TYPE_CODE_MAX_LENGTH = 5;//column 7
    private static final int OP_TYPE_DESCRIPTION_MAX_LENGTH = 40;//column 8
    private static final int UNIT_COST_MAX_LENGTH = 14;//column 9
    private static final int UNIT_OF_MEASURE_MAX_LENGTH = 15;//column 10
    private static final int TOTAL_AMOUNT_MAX_LENGTH = 27; //column 11, Empresas grandes / multinacionales: 18,4 → Máximo 999,999,999,999,999.9999
    private static final int ORDER_REQUEST_MAX_LENGTH = 7;//column 13
    private static final int CURRENCY_MAX_LENGTH = 3;//column 14
    private static final int DECIMALS_LENGTH = 3;//Decimals

    private void setTableMaxFieldsLengths(DynamicTableButtons table) {

        // Set alphanumeric columns
        setColumnEditor(table, 1, new DocumentFilterVarchar(OPERATION_MAX_LENGTH).setUpperCase(true));
        setColumnEditor(table, 3, new DocumentFilterVarchar(OPERATION_DESCRIPTION_MAX_LENGTH).setUpperCase(true));
        setColumnEditor(table, 8, new DocumentFilterVarchar(OP_TYPE_DESCRIPTION_MAX_LENGTH).setUpperCase(true));
        setColumnEditor(table, 10, new DocFilterVarcharWithoutSpace(UNIT_OF_MEASURE_MAX_LENGTH).setUpperCase(true));
        setColumnEditor(table, 12, new DocFilterVarcharWithoutSpace(ORDER_REQUEST_MAX_LENGTH).setUpperCase(true));
        setColumnEditor(table, 13, new DocFilterCurrencyCode(CURRENCY_MAX_LENGTH).setUpperCase(true));

        // Set numeric columns
        setColumnEditor(table, 4, new NumDoubleLimitFilter(WORK_MAX_LENGTH, DECIMALS_LENGTH));
        setColumnEditor(table, 5, new NumDoubleLimitFilter(QUANTITY_MAX_LENGTH, DECIMALS_LENGTH));
        setColumnEditor(table, 6, new NumDoubleLimitFilter(DURATION_MAX_LENGTH, DECIMALS_LENGTH));
        setColumnEditor(table, 9, new NumDoubleLimitFilter(UNIT_COST_MAX_LENGTH, DECIMALS_LENGTH));
        setColumnEditor(table, 11, new NumDoubleLimitFilter(TOTAL_AMOUNT_MAX_LENGTH, DECIMALS_LENGTH));

    }
    private void setColumnEditor(DynamicTableButtons table, int columnIndex, DocumentFilter filter) {
        FilterDocumentEditor cellEditor = new FilterDocumentEditor(filter);

        // Assign the editor to the corresponding column
        table.getColumnModel().getColumn(columnIndex).setCellEditor(cellEditor);
    }
    private void setOperationNums(DynamicTableModel model, int rows) {

        // Loop to set values and make the specified columns non-editable
        for (int i = 0; i < rows; i++) {

            String formattedValue = String.format("%04d", i + 1 * 10);
            model.setValueAt(formattedValue, i, 1);

            // Using final variable for row index
            final int row = i; // Make 'i' effectively final by declaring it outside the lambda

            // Set specific columns as non-editable
            Arrays.asList(1, 4, 11, 12).forEach(col -> model.setCellEditable(row, col, false));
        }
    }
    private void setProcedureSearchBox(DynamicTableButtons table) {

        JTextField textField = new JTextField();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(
                new DocFilterVarcharWithoutSpace(PROCEDURE_MAX_LENGTH).setUpperCase(true));
        MaintProceduresEditor editor = new MaintProceduresEditor(textField);
        table.getColumnModel().getColumn(2).setCellEditor(editor);

    }
    private void setOperationTypeSearchBox(DynamicTableButtons table) {
        
        JTextField textField = new JTextField();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(
                new DocFilterVarcharWithoutSpace(OP_TYPE_CODE_MAX_LENGTH).setUpperCase(true));
        OperationTypesEditor editor = new OperationTypesEditor(textField);
        table.getColumnModel().getColumn(7).setCellEditor(editor);
    
    }

    private void activateCellEditorOnKeyPress(DynamicTableButtons table) {
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();

                // Asegurarse de que la fila no sea inválida y que la columna no sea la columna 1
                if (row != -1 && col != 1) {
                    // Si es una columna con un editor alfanumérico
                    if (col == 2 || col == 3 || col == 7 || col == 8 || col == 10 || col == 12 || col == 13 || col == 14) {
                        char keyChar = e.getKeyChar();
                        if (Character.isLetterOrDigit(keyChar) || Character.isSpaceChar(keyChar)) {
                            // Activar el editor solo si aún no está activo
                            if (!table.isEditing()) {
                                table.editCellAt(row, col);
                            }
                        }
                    } // Si es una columna con un editor numérico
                    else if (col == 4 || col == 5 || col == 6 || col == 9 || col == 11) {
                        char keyChar = e.getKeyChar();
                        if (Character.isDigit(keyChar) || keyChar == KeyEvent.VK_PERIOD) { // Permitir números y punto para decimales
                            // Activar el editor solo si aún no está activo
                            if (!table.isEditing()) {
                                table.editCellAt(row, col);
                            }
                        }
                    }
                }
            }
        });
    }
    
    // Method to add the TableModelListener for procedure key description
    private void addProcedureKeyListener(DynamicTableModel model) {
        // Create a listener to handle procedure key description updates
        ProceduresModelListener procedureListener = new ProceduresModelListener(model, operationListTable);
        // Add the listener to the model
        model.addTableModelListener(procedureListener);
    }
    // Method to add the TableModelListener for operation types
    private void addOperationTypesListener(DynamicTableModel model, DynamicTableButtons table) {
        // Create a listener to handle operation types updates
        OperationTypesListener operationListener = new OperationTypesListener(model, table);
        // Add the listener to the model
        model.addTableModelListener(operationListener);
    }
    // Method to add the TableModelListener for calculating the total amount
    private void addCalculationListener(DynamicTableModel model) {
        // Create a listener to handle total amount calculation
        OperationsCalculationListener calculationListener = new OperationsCalculationListener(model);
        // Add the listener to the model
        model.addTableModelListener(calculationListener);
    }
    //add Materials table
    private String[] getTableMaterialsColumnsName() {
        String[] keys = {null,
            "column.position",
            "column.material",
            "column.component.denomination",
            "column.quantity",
            "column.unit.of.measure",
            "column.unit.cost",
            "column.warehouse",
            "column.total.amount",
            "column.classification"
        };

        return IntStream.range(0, keys.length)
                .mapToObj(i -> (i == 0) ? null : LyraWorkspace.getTableTranslator().getTranslation(keys[i]))
                .toArray(String[]::new);
    }
    private DynamicTableModel createMaterialsTableModel() {
       
        String[] columns = getTableMaterialsColumnsName();
        DynamicTableModel model = new DynamicTableModel(100, columns);

        // List of columns that are not editable
        int[] nonEditableColumns = {1, 3, 5, 6, 7, 8, 9};

        // Loop to set values and make the specified columns non-editable
        for (int i = 0; i < model.getRowCount(); i++) {
            String formattedValue = String.format("%03d", i + 1 * 10);
            model.setValueAt(formattedValue, i, 1);

            // Using an IntStream to iterate over non-editable columns
            final int row = i; // Make 'i' effectively final by declaring it outside the lambda
            Arrays.stream(nonEditableColumns)
                    .forEach(col -> model.setCellEditable(row, col, false));
        }

        return model;
    }
    
    private boolean isRowFullyFilled(DynamicTableButtons table, int row) {
        DynamicTableModel model = (DynamicTableModel) table.getModel();

        // Columnas a validar
        int[] requiredColumns = {3, 5,6, 7, 9};

        for (int col : requiredColumns) {
            Object value = model.getValueAt(row, col);
            if (value == null || value.toString().trim().isEmpty()) {
                table.editCellAt(row, col);
                new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.ERROR);
                return false;
            }
        }

        return true; // Se devuelve true si todas las celdas requeridas están llenas
    }
    
    private Map<String, DynamicTableModel> operationMaterialMap = new HashMap<>();
    private void openSparePartsModule() {
        if (selectedRows.isEmpty()) {
            return;
        }
        
        clearOperations();
        for (Integer selectedRow : selectedRows) {
            if(!isRowFullyFilled(operationListTable,selectedRow)){
                return;
            }
                       
            assingOperationValues((DynamicTableModel) operationListTable.getModel(),selectedRow);
        }

        for (String operation : selectedOperations) {
            operationMaterialMap.putIfAbsent(operation, createMaterialsTableModel());
        }
        
        sortOperationsAndDescriptions();
        
        int index = selectedOperations.indexOf(0);

        RoutineSheetMaterialsForm spareParts = new RoutineSheetMaterialsForm(languageManager);
        spareParts.setLastPanel(this);
        spareParts.setOperationsList(selectedOperations);
        spareParts.setOperationsDescriptionList(selectedOperationsDescriptions);
        spareParts.setRoutine(routineCode);
        spareParts.setRoutineName(routineName);
        spareParts.setRoutineCounter(String.valueOf(routineCounter));
        spareParts.setTableModel(operationMaterialMap.get(0));
        spareParts.setMapModel(operationMaterialMap);
        spareParts.setFirstIndex(index);
        
        
        PanelLoader.loadPanel(spareParts, mainContainerPanel);
    }
    
    // Store the selection data
    private Map<String, Map<String, Boolean>> operationPackagesMap = new HashMap<>();    
    private void openPackagesModule() {
        if (selectedRows.isEmpty()) {
            return;
        }
        clearOperations();
        for (Integer selectedRow : selectedRows) {
            if (!isRowFullyFilled(operationListTable, selectedRow)) {
                return;
            }
            assingOperationValues((DynamicTableModel) operationListTable.getModel(), selectedRow);
        }
        sortOperationsAndDescriptions();

        PackagesRoutineSheet packagesRoutine = new PackagesRoutineSheet(languageManager, strategyCode);
        packagesRoutine.setLastPanel(this);
        packagesRoutine.setRoutine(routineCode);
        packagesRoutine.setRoutineName(routineName);
        packagesRoutine.setRoutineCounter(String.valueOf(routineCounter));

        packagesRoutine.setOperationsList(selectedOperations);
        packagesRoutine.setOperationsDescriptionList(selectedOperationsDescriptions);
        packagesRoutine.setOperationPackagesMap(operationPackagesMap);

        packagesRoutine.setOperationsToTable();
        packagesRoutine.applyDynamicCellEditorAndRenderer();
        packagesRoutine.putSelectionValues();

        PanelLoader.loadPanel(packagesRoutine, mainContainerPanel);
    }
    
    private void sparePartsButton(){
        spartPartsButton.addActionListener(e->openSparePartsModule());
    }
    private void packagesRoutineButton(){
        packagesButton.addActionListener(e->openPackagesModule());
    }

    private int[] getRowsWithIntentToFill(DynamicTableButtons table) {
        DynamicTableModel model = (DynamicTableModel) table.getModel();
        ArrayList<Integer> rows = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            // Check if any of the key cells contain a value (not null)
            if (model.getValueAt(i, 3) != null
                    || model.getValueAt(i, 5) != null
                    || model.getValueAt(i, 6) != null
                    || model.getValueAt(i, 7) != null
                    || model.getValueAt(i, 9) != null) {
                // If at least one cell contains data, the row has "intent to be filled"
                rows.add(i);
            }
        }

        // Convert the list of rows into an array of integers
        return rows.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean isRowFullyFilledToSelection(DynamicTableButtons table, int row) {
        DynamicTableModel model = (DynamicTableModel) table.getModel();

        // Columnas a validar
        int[] requiredColumns = {3, 5, 6, 7, 9};

        // Verificar si la fila está completamente llena
        for (int col : requiredColumns) {
            Object value = model.getValueAt(row, col);
            if (value == null || value.toString().trim().isEmpty()) {
                return false; // Si alguna celda está vacía, la fila no se considera completamente llena
            }
        }

        return true; // La fila está completamente llena
    }

    private void removeOperations() {
        if (selectedRows.isEmpty()) {
            return;
        }
        clearOperations();

        // Validate all rows before making changes
        List<Integer> validRows = new ArrayList<>();
        for (Integer selectedRow : selectedRows) {
            if (isRowFullyFilled(operationListTable, selectedRow)) {
                assingOperationValues((DynamicTableModel) operationListTable.getModel(), selectedRow);
                validRows.add(selectedRow);
            }
        }

        // If no row is valid, exit
        if (validRows.isEmpty()) {
            return;
        }

        // Remove associated data from the maps
        for (String operation : selectedOperations) {
            operationMaterialMap.remove(operation); // Safe even if the key does not exist
            operationPackagesMap.remove(operation);
        }

        // Sort rows in descending order to avoid index shifting issues
        validRows.sort(Collections.reverseOrder());

        // Remove rows from the table
        DynamicTableModel model = (DynamicTableModel) operationListTable.getModel();
        for (Integer selectedRow : validRows) {
            model.removeRow(selectedRow);
        }
        
        operationListTable.clearSelection();
    }
    private void removeOperationButton() {
        removeRowButton.addActionListener(e -> removeOperations());
    }

    private void selectAllRows() {
        int cols = operationListTable.getModel().getColumnCount();
        int rows = operationListTable.getModel().getRowCount();

        boolean rowsSelected = false; // Variable para verificar si se seleccionaron filas

        // Clear any previous selection
        operationListTable.clearSelection();

        for (int i = 0; i < rows; i++) {
            if (isRowFullyFilledToSelection(operationListTable, i)) {
                if (!selectedRows.contains(i)) {
                    selectedRows.add(i);
                    assingOperationValues((DynamicTableModel) operationListTable.getModel(), i);
                    operationListTable.addRowSelectionInterval(i, i); // Select the row
                    rowsSelected = true; // Marca que se seleccionaron filas
                }
            }
        }

        // Select columns only if rows have been selected
        if (rowsSelected) {
            operationListTable.addColumnSelectionInterval(1, cols - 1); // Select columns if rows are selected
        }
    }
    private void deselectAllRows() {
        for (Integer row : selectedRows) {
            operationListTable.removeRowSelectionInterval(row, row);
        }
        operationListTable.removeColumnSelectionInterval(1, operationListTable.getModel().getColumnCount() - 1);

        // Clear selectedRows after deselecting all rows
        selectedRows.clear();
    }

    private void selectAllRowsButton(){
        selectAllButton.addActionListener(e->selectAllRows());
    }
    private void deselectAllRowsButton(){
        deselectAllButton.addActionListener(e->deselectAllRows());
    }

    
    
    /**
     * *save section**
     */
    private void saveButton() {
        saveButton.addActionListener(e -> saveRoutine());
    }

    private boolean areRowsValid() {
        for (int row : getRowsWithIntentToFill(operationListTable)) {
            if (!isRowFullyFilled(operationListTable,row)) {
                return false;
            }
        }
        return true;
    }

    private void insertNewPackages(RoutineSheetPackageDAO packageDAO, List<String> operationNums) {
        if (!operationNums.isEmpty()) {
            for (String opNum : operationNums) {
                Set<String> packages = getPackagesByOperation(opNum);
                if (packages != null) {
                    for (String pkg : packages) {
                        RoutineSheetPackage rpkg = buildPackage(opNum, pkg);
                        packageDAO.insert(rpkg);
                    }
                }
            }
        }

    }
    private void updatePackages(RoutineSheetPackageDAO packageDAO, List<String> operationNumbers) {

        if (!operationNumbers.isEmpty()) {
            
            for (String opNum : operationNumbers) {

                Set<String> savedPackagesSet = RoutineSheetPackageDAO.getPackages(getRoutineSheetCode(), getRoutineCounter(), opNum);
                Set<String> currentPackagesSet = getPackagesByOperation(opNum);

                // Determinar qué paquetes agregar y eliminar
                Set<String> toInsert = new HashSet<>(currentPackagesSet);
                toInsert.removeAll(savedPackagesSet); // Paquetes nuevos que deben insertarse

                Set<String> toDelete = new HashSet<>(savedPackagesSet);
                toDelete.removeAll(currentPackagesSet); // Paquetes que deben eliminarse

                // Insertar nuevos paquetes
                for (String newPkg : toInsert) {
                    RoutineSheetPackage pkg = buildPackage(opNum, newPkg);
                    packageDAO.insert(pkg);
                }

                // Eliminar paquetes obsoletos
                for (String oldPkg : toDelete) {
                    packageDAO.delete(getRoutineSheetCode(), getRoutineCounter(), opNum, oldPkg);
                }
            }
            
        }

    }

    private void insertMaterials(RoutineSheetMaterialDAO materialDAO, List<String> operationNumbers) {

        if (!operationNumbers.isEmpty()) {
            for (String opNum : operationNumbers) {
                DynamicTableModel model = getMaterialModelByOperation(opNum);
                List<RoutineSheetMaterial> materialsOperation = new ArrayList<>();

                if (model != null) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        boolean validRow = true;

                        for (int j = 2; j <= 8; j++) {
                            Object value = model.getValueAt(i, j);
                            if (value == null || value.toString().trim().isEmpty()) {
                                validRow = false;
                                break;
                            }
                        }
                        if (validRow) {

                            String position = (String) model.getValueAt(i, 1);
                            addMaterialsPosition(opNum, position);//agrega las posiciones de materiales a la lista para uso de verificacion
                            RoutineSheetMaterial material = buildMaterial(model, i);
                            materialsOperation.add(material);

                        }

                    }

                    materialDAO.insertMaterials(materialsOperation);

                }
            }
        }

    }
    private void updateMaterials(RoutineSheetMaterialDAO materialDAO, List<String> operationNumbers) {

        if (!operationNumbers.isEmpty()) {
            for (String opNum : operationNumbers) {
                // Obtener los materiales guardados
                Set<String> savedMaterialPositionSet = RoutineSheetMaterialDAO.getMaterialPositionsByOperation(getRoutineSheetCode(), getRoutineCounter(), opNum);
                // Obtener los materiales actuales
                DynamicTableModel currentMaterialsByOperation = getMaterialModelByOperation(opNum);

                List<RoutineSheetMaterial> insertMaterialOperation = new ArrayList<>();
                List<RoutineSheetMaterial> updateMaterialOperation = new ArrayList<>();

                RoutineSheetMaterial materialPosition;

                // Verificar si hay materiales actuales
                if (currentMaterialsByOperation != null) {

                    boolean validRow;

                    // Iterar por cada fila de materiales
                    for (int i = 0; i < currentMaterialsByOperation.getRowCount(); i++) {
                        validRow = true;

                        // Validar que las celdas necesarias tengan datos válidos
                        for (int j = 2; j <= 8; j++) {
                            Object value = currentMaterialsByOperation.getValueAt(i, j);
                            if (value == null || value.toString().trim().isEmpty()) {
                                validRow = false;
                                break;
                            }
                        }

                        // Si la fila es válida, procesarla
                        if (validRow) {
                            String position = (String) currentMaterialsByOperation.getValueAt(i, 1);
                            double quantity = (double) currentMaterialsByOperation.getValueAt(i, 4);
                            String warehouse = (String) currentMaterialsByOperation.getValueAt(i, 7);

                            addMaterialsPosition(opNum, position);//se agrega las posiciones actuales a la lista para hacer verificacion

                            // Si ya existen materiales guardados
                            if (!savedMaterialPositionSet.isEmpty()) {
                                // Si la posición no está guardada, insertarla
                                if (!savedMaterialPositionSet.contains(position)) {

                                    RoutineSheetMaterial material = buildMaterial(currentMaterialsByOperation, i);
                                    insertMaterialOperation.add(material);

                                } else {
                                    // Si la posición está guardada, actualizarla
                                    materialPosition = new RoutineSheetMaterial.Builder()
                                            .setQuantity(quantity)
                                            .setWarehouse(warehouse)
                                            .build();

                                    updateMaterialOperation.add(materialPosition);
                                }
                            } else {
                                // Si no hay materiales guardados, agregar el nuevo
                                RoutineSheetMaterial material = buildMaterial(currentMaterialsByOperation, i);
                                insertMaterialOperation.add(material);
                            }
                        }
                    }

                    materialDAO.insertMaterials(insertMaterialOperation);
                    materialDAO.update(getRoutineSheetCode(), getRoutineCounter(), opNum, updateMaterialOperation);

                    // Obtener las posiciones actuales de la operación
                    Set<String> positions = getPositionsByOperation(opNum);
                    List<String> positionToDelete = new ArrayList<>();

                    // Si hay posiciones actuales, eliminar las obsoletas
                    if (!savedMaterialPositionSet.isEmpty() && !positions.isEmpty()) {
                        for (String pos : savedMaterialPositionSet) {
                            if (!positions.contains(pos)) {
                                positionToDelete.add(pos);
                            }
                        }
                        materialDAO.delete(getRoutineSheetCode(), getRoutineCounter(), opNum, positionToDelete);
                    }

                }
            }
        }

    }

    private void deleteOperationInBatch(RoutineSheetOperationDAO operationDAO, List<String> operationsToDelete) {
        operationDAO.deleteOperation(getRoutineSheetCode(), getRoutineCounter(), operationsToDelete);
    }
    private void deletePackageInBatch(RoutineSheetPackageDAO packageDAO, Map<String, List<String>> packagesToDelete) {
        for (Map.Entry<String, List<String>> entry : packagesToDelete.entrySet()) {
            String operationCode = entry.getKey();
            List<String> packages = entry.getValue();
            for (String pkg : packages) {
                packageDAO.delete(getRoutineSheetCode(), getRoutineCounter(), operationCode, pkg);
            }
        }
    }
    private void deleteMaterialInBatch(RoutineSheetMaterialDAO materialDAO, Map<String, List<String>> materialsToDelete) {

        List<String> materialPositionToDelete = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : materialsToDelete.entrySet()) {
            String operationCode = entry.getKey();
            List<String> materials = entry.getValue();
            for (String materialPosition : materials) {
                materialPositionToDelete.add(materialPosition);

            }
            materialDAO.delete(getRoutineSheetCode(), getRoutineCounter(), operationCode, materialPositionToDelete);
        }

    }

    private void removeObsoleteOperations(RoutineSheetOperationDAO operationDAO,
            RoutineSheetPackageDAO packageDAO,
            RoutineSheetMaterialDAO materialDAO) {
        
        // Obtener el conjunto de operaciones
        Set<String> savedOperationSet = RoutineSheetOperationDAO.getOperations(getRoutineSheetCode(), getRoutineCounter());

        // Validar que operationSet no esté vacío y salir si lo está
        if (savedOperationSet.isEmpty()) {
            return;  // Salir temprano si no hay operaciones
        }

        // Si no hay nuevas operaciones, no hacemos nada
        if (currentOperation.isEmpty()) {
            return;
        }

        List<String> operationsToDelete = new ArrayList<>();
        Map<String, List<String>> packagesToDelete = new HashMap<>();
        Map<String, List<String>> materialsToDelete = new HashMap<>();

        // Usar un bloque try-catch para manejar excepciones durante las operaciones de eliminación
        for (String operationCode : savedOperationSet) {
            // Eliminar operaciones obsoletas que no están en newOperations
            if (!currentOperation.contains(operationCode)) {
                operationsToDelete.add(operationCode);
            }

            // Eliminar paquetes asociados con la operación
            Set<String> savedPackages = RoutineSheetPackageDAO.getPackages(getRoutineSheetCode(), getRoutineCounter(), operationCode);
            if (!savedPackages.isEmpty()) {
                packagesToDelete.put(operationCode, new ArrayList<>(savedPackages));
            }

            // Eliminar materiales asociados con la operación
            Set<String> materialsOpPositions = RoutineSheetMaterialDAO.getMaterialPositionsByOperation(getRoutineSheetCode(), getRoutineCounter(), operationCode);
            if (!materialsOpPositions.isEmpty()) {
                 materialsToDelete.put(operationCode, new ArrayList<>(materialsOpPositions));
            }
        }

        deleteOperationInBatch(operationDAO, operationsToDelete);
        deletePackageInBatch(packageDAO, packagesToDelete);
        deleteMaterialInBatch(materialDAO, materialsToDelete);

    }

    private void saveRoutine() {

        //inicio de codigo mejorado
        if (!areRowsValid()) {
            return;
        }
        RoutineSheetHeaderDAO headerDAO = new RoutineSheetHeaderDAO();
        RoutineSheetOperationDAO operationDAO = new RoutineSheetOperationDAO();
        RoutineSheetPackageDAO packageDAO = new RoutineSheetPackageDAO();
        RoutineSheetMaterialDAO materialDAO = new RoutineSheetMaterialDAO();

        if (!isHeaderSaved) {
            headerDAO.insert(buildHeader());
            isHeaderSaved = true;
        }
        
        //Limpia el set de operaciones pasadas
        clearOperationSet();
        clearMaterialPositions();
        
        List<RoutineSheetOperation> operationsToInsert = new ArrayList<>();
        List<RoutineSheetOperation> operationsToUpdate = new ArrayList<>();
        
        List<String> opNumInserts = new ArrayList<>();
        List<String> opNumUpdates = new ArrayList<>();
        
        // Now save the operations, since all rows are confirmed to be filled
        for (Integer row : getRowsWithIntentToFill(operationListTable)) {
            
            // Build the operation from the row
            RoutineSheetOperation operation = buildOperationFromRow(row);
            String opNum = (String) getValueAt(row, 1);  // Simplified value extraction
            
            //Agregar las operaciones actualmente validas
            addOperation(opNum);
            
            // Insert or update the operation in the database
            if (!RoutineSheetOperationDAO.operationExists(getRoutineSheetCode(), getRoutineCounter(), opNum)) {
                operationsToInsert.add(operation);
                opNumInserts.add(opNum);
            } else {
                operationsToUpdate.add(operation);
                opNumUpdates.add(opNum);
            }
        }
        
        operationDAO.insert(operationsToInsert);
        operationDAO.update(operationsToUpdate);
        
        insertNewPackages(packageDAO, opNumInserts);
        updatePackages(packageDAO, opNumUpdates);
        
        insertMaterials(materialDAO, opNumInserts);
        updateMaterials(materialDAO, opNumInserts);
        
        removeObsoleteOperations(operationDAO,packageDAO,materialDAO);
    }

    // Helper method to build the header
    private RoutineSheetHeader buildHeader() {
        return new RoutineSheetHeader.Builder()
                .setRoutineSheet(getRoutineSheetCode())
                .setCounter(getRoutineCounter())
                .setPositionName(getRoutineName())
                .setPlanningGroup(getPlanningGroup())
                .setOperationType(getOperationType())
                .setStatus(isStatus())
                .setOperatingContext(getOperatingContext())
                .setMaintenanceStrategy(getMaintenanceStrategy())
                .setUsage(getUsage())
                .setScheduledDay(getScheduledDate())
                .build();
    }

    // Helper method to build the operation from the row
    private RoutineSheetOperation buildOperationFromRow(int row) {
        return new RoutineSheetOperation.Builder()
                .setRoutineSheet(getRoutineSheetCode())
                .setCounter(getRoutineCounter())
                .setOperation((String) getValueAt(row, 1))
                .setProcedure((String) getValueAt(row, 2))
                .setOperationDescription((String) getValueAt(row, 3))
                .setWork((double) getValueAt(row, 4))
                .setQuantity((double) getValueAt(row, 5))
                .setDuration((double) getValueAt(row, 6))
                .setOperationTypeCode((String) getValueAt(row, 7))
                .setOperationType((String) getValueAt(row, 8))
                .setUnitCost((double) getValueAt(row, 9))
                .setUnitOfMeasure((String) getValueAt(row, 9))
                .setTotalAmount((double) getValueAt(row, 10))
                .setOrderRequest((String) getValueAt(row, 11))
                .setCurrency((String) getValueAt(row, 12))
                .build();
    }

    // Helper method to extract value from the table model
    private Object getValueAt(int row, int col) {
        return operationListTable.getModel().getValueAt(row, col);
    }

    private RoutineSheetPackage buildPackage(String opNum, String pkg) {
        return new RoutineSheetPackage.Builder()
                .setRoutineSheet(getRoutineSheetCode())
                .setCounter(getRoutineCounter())
                .setOperation(opNum)
                .setPackageCode(pkg)
                .setMaintenanceStrategy(getMaintenanceStrategy())
                .build();
    }

    public Set<String> getPackagesByOperation(String operation) {
        Map<String, Boolean> packages = operationPackagesMap.get(operation);
        return (packages != null) ? packages.keySet() : Collections.emptySet();
    }

    private RoutineSheetMaterial buildMaterial(DynamicTableModel model,int row){
        return new RoutineSheetMaterial.Builder()
                .setRoutineSheet(getRoutineSheetCode())
                .setCounter(getRoutineCounter())
                .setPosition((String)model.getValueAt(row, 1))
                .setMaterial((String)model.getValueAt(row, 2))
                .setComponentDenomination((String)model.getValueAt(row, 3))
                .setQuantity((double)model.getValueAt(row, 4))
                .setUnitOfMeasure((String)model.getValueAt(row, 5))
                .setUnitCost((double)model.getValueAt(row, 6))
                .setWarehouse((String)model.getValueAt(row, 7))
                .setTotalAmount((double)model.getValueAt(row, 8))
                .setClassification((String)model.getValueAt(row, 9))
                .build();
                
    }

    public DynamicTableModel getMaterialModelByOperation(String operation) {
        return operationMaterialMap.getOrDefault(operation, null);
    }

    private Set<String>currentOperation = new HashSet<>();
    private void addOperation(String operation){
        currentOperation.add(operation);
    }
    private void clearOperationSet(){
        currentOperation.clear();
    }

    private Map<String, String> newMaterialPositions = new HashMap<>();
    private void addMaterialsPosition(String operation, String position) {
        newMaterialPositions.putIfAbsent(operation, position);
    }
    private void clearMaterialPositions(){
        newMaterialPositions.clear();
    }

    public Set<String> getPositionsByOperation(String operation) {
        String position = newMaterialPositions.get(operation);
        return (position != null) ? Collections.singleton(position) : Collections.emptySet();
    }

    /*Declaracion de variables de clase*/
    private String routineCode;
    private String routineName;
    private int routineCounter;
    private String planningGroup;
    private String operationType;
    private String strategyCode;
    private boolean status;
    private int operatingContext;
    private int usage;
    private Date scheduledDate;
    private boolean isHeaderSaved;
    
    //Setters methods
    public void setRoutineSheetCode(String routine){
        this.routineCode=routine;
        routineCodeTitleLb.setText(routine);
    }
    public void setRoutinePositionName(String name){
        this.routineName=name;
        routineNameLb.setText(name);
    }
    public void setRoutineCounter(String counter){
        this.routineCounter=Integer.parseInt(counter);
        numberCounterLb.setText(counter);
    }
    public void setMaintenanceStrategy(String strategyCode) {
        this.strategyCode = strategyCode;
    }
    public void setHeaderSaved(boolean isSaved) {
        this.isHeaderSaved = isSaved;
    }
    public void setPlanningGroup(String planningGroup) {
        this.planningGroup = planningGroup;
    }
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setOperatingContext(int operatingContext) {
        this.operatingContext = operatingContext;
    }
    public void setUsage(int usage) {
        this.usage = usage;
    }
    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
    
    //Getters Methods
    public String getRoutineSheetCode(){
        return routineCode;
    }
    public String getRoutineName(){
        return routineName;
    }
    public int getRoutineCounter(){
        return routineCounter;
    }
    public String getPlanningGroup() {
        return planningGroup;
    }
    public String getOperationType() {
        return operationType;
    }
    public boolean isStatus() {
        return status;
    }
    public String getMaintenanceStrategy(){
        return strategyCode;
    }
    public int getOperatingContext() {
        return operatingContext;
    }
    public int getUsage() {
        return usage;
    }
    public Date getScheduledDate(){
        return scheduledDate;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        exitButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        saveButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        packagesButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        removeRowButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        addRowButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        selectAllButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        spartPartsButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        deselectAllButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        jScrollPane1 = new javax.swing.JScrollPane();
        operationListTable = new com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons();
        routineTitleLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineCodeTitleLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineNameLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineCounterLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        jPanel_Rounded_Corners_Degradado1 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        activitiesSummaryLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        numberCounterLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        jLabel_HQ1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        entryAtTf = new javax.swing.JTextField();
        jLabel_HQ2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        entryToTf = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelBotonera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotonera.setColor1(new java.awt.Color(202, 216, 237));
        panelBotonera.setColor2(new java.awt.Color(202, 216, 237));

        exitButton.setBackground(new java.awt.Color(226, 210, 144));
        exitButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close.png"))); // NOI18N

        saveButton.setBackground(new java.awt.Color(226, 210, 144));
        saveButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N

        packagesButton.setBackground(new java.awt.Color(226, 210, 144));
        packagesButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        packagesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/puzzle.png"))); // NOI18N
        packagesButton.setText("Packages");
        packagesButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        removeRowButton.setBackground(new java.awt.Color(226, 210, 144));
        removeRowButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        removeRowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/remove.png"))); // NOI18N
        removeRowButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        addRowButton.setBackground(new java.awt.Color(226, 210, 144));
        addRowButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addRowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/add.png"))); // NOI18N
        addRowButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        selectAllButton.setBackground(new java.awt.Color(226, 210, 144));
        selectAllButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        selectAllButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/select_all.png"))); // NOI18N
        selectAllButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        spartPartsButton.setBackground(new java.awt.Color(226, 210, 144));
        spartPartsButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        spartPartsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/puzzle.png"))); // NOI18N
        spartPartsButton.setText("Spare parts");
        spartPartsButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        deselectAllButton.setBackground(new java.awt.Color(226, 210, 144));
        deselectAllButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        deselectAllButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/deselect_all.png"))); // NOI18N
        deselectAllButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(removeRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(deselectAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(packagesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(spartPartsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(packagesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spartPartsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(selectAllButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deselectAllButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(addRowButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(removeRowButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        operationListTable.setBackground(new java.awt.Color(202, 216, 237));
        operationListTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        operationListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        operationListTable.setAlineacion(2);
        operationListTable.setCellSelectionEnabled(true);
        operationListTable.setColorColumna1(new java.awt.Color(255, 255, 255));
        operationListTable.setColorColumnas(new java.awt.Color(255, 255, 255));
        operationListTable.setColumnNoEditableColor(new java.awt.Color(202, 216, 237));
        operationListTable.setFont(new java.awt.Font("Roboto Light", 0, 13)); // NOI18N
        operationListTable.setGridColor(new java.awt.Color(175, 175, 175));
        operationListTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        operationListTable.setShowGrid(true);
        operationListTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(operationListTable);

        routineTitleLb.setText("Routine sheet");
        routineTitleLb.setFont(new java.awt.Font("Roboto Light", 0, 13)); // NOI18N
        routineTitleLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        routineCodeTitleLb.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        routineCodeTitleLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        routineCodeTitleLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        routineNameLb.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        routineNameLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        routineNameLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        routineCounterLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        routineCounterLb.setText("RoutCounter");
        routineCounterLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        routineCounterLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel_Rounded_Corners_Degradado1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel_Rounded_Corners_Degradado1.setColor1(new java.awt.Color(217, 232, 255));
        jPanel_Rounded_Corners_Degradado1.setColor2(new java.awt.Color(176, 188, 206));

        activitiesSummaryLb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        activitiesSummaryLb.setText("Activities summary");
        activitiesSummaryLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado1Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado1);
        jPanel_Rounded_Corners_Degradado1.setLayout(jPanel_Rounded_Corners_Degradado1Layout);
        jPanel_Rounded_Corners_Degradado1Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(activitiesSummaryLb, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado1Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(activitiesSummaryLb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        numberCounterLb.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        numberCounterLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        numberCounterLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel_HQ1.setText("Entries");
        jLabel_HQ1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jLabel_HQ2.setText("/");
        jLabel_HQ2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(routineTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(routineCodeTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(routineNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(routineCounterLb, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(numberCounterLb, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
                        .addComponent(jLabel_HQ1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entryAtTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_HQ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entryToTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(449, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(routineTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(routineCodeTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(routineNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(routineCounterLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberCounterLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_HQ1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entryAtTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_HQ2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entryToTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotonera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JLabelHQ activitiesSummaryLb;
    private com.simplecore.erp.gui.components.labels.JButtonHQ addRowButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ deselectAllButton;
    private javax.swing.JTextField entryAtTf;
    private javax.swing.JTextField entryToTf;
    private com.simplecore.erp.gui.components.labels.JButtonHQ exitButton;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ2;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ numberCounterLb;
    private com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons operationListTable;
    private com.simplecore.erp.gui.components.labels.JButtonHQ packagesButton;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private com.simplecore.erp.gui.components.labels.JButtonHQ removeRowButton;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineCodeTitleLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineCounterLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineNameLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineTitleLb;
    private com.simplecore.erp.gui.components.labels.JButtonHQ saveButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ selectAllButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ spartPartsButton;
    // End of variables declaration//GEN-END:variables
 
    private JPanel lastPanel;
    public void setLastPanel(JPanel panel) {
        lastPanel = panel;
    }
    private void exitButton() {
        exitButton.addActionListener((e) -> {

            PanelLoader.loadPanel(lastPanel, mainContainerPanel);

        });
    }
    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }

}
