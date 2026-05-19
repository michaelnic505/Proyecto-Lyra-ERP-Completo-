package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.packages;

import com.simplecore.erp.gui.components.tables.interfaces.TableButtonListener;
import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import com.simplecore.erp.i18n.LanguageManager;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class PackagesRoutineSheet extends javax.swing.JPanel {

    private LanguageManager languageManager;

    private String strategyCode;
    public PackagesRoutineSheet(LanguageManager languageManager, String strategyCode) {
        this.strategyCode=strategyCode;
        this.languageManager = languageManager;
        initComponents();
        addComponentsEvents();
    }

    private void addComponentsEvents() {
        setTableModel();
        exitButton();
        saveButton();
    }

    private String[] getTableColumns() {
        // Fixed columns
        String[] keys = {
            "table.packages.column.operation",
            "table.packages.column.denominationOperatoin"
        };

        String operation = LyraWorkspace.getTableTranslator().getTranslation(keys[0]);
        String operationDescription = LyraWorkspace.getTableTranslator().getTranslation(keys[1]);

        // List of packages (dynamic columns)
        ArrayList<String> packages = StrategyPackages.getPackageList(strategyCode);

        // Initialize an ArrayList to store the columns
        ArrayList<String> columns = new ArrayList<>();

        // Add the fixed columns
        columns.add(null);//button
        columns.add(operation);
        columns.add(operationDescription);

        // Add the dynamic columns (from the package list)
        columns.addAll(packages);

        // Convert the ArrayList to a String[] and return it
        return columns.toArray(new String[0]);
    }

    private void setTableModel() {

        int rows = 100;
        String[] columnsName = getTableColumns();

        DynamicTableModel model = new DynamicTableModel(rows, columnsName);

        for (int c = 0; c < rows; c++) {
            model.setCellEditable(c, 1, false);
            model.setCellEditable(c, 2, false);
        }

        packageTable.setModel(model);
        packageTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        packageTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        packageTable.setSelectedRowsList(selectedRows);
        avoidSelectColumn0();
        setTableButtonListener();
        resetSelection();
 
    }

    public void applyDynamicCellEditorAndRenderer() {
        // Find the last row with data (dynamic number of rows)
        int lastRowWithData = findLastRowWithData();

        // Loop through the dynamic columns (starting from column 1, assuming column 0 is fixed)
        for (int i = 3; i < packageTable.getColumnCount(); i++) { // Starting from column 2 (dynamic columns)
            packageTable.getColumnModel().getColumn(i).setCellRenderer(new CheckBoxRenderer(lastRowWithData));
            packageTable.getColumnModel().getColumn(i).setCellEditor(new CheckBoxEditor(lastRowWithData));
            packageTable.getColumnModel().getColumn(i).setPreferredWidth(50);
        }
    }

    // Method to find the last row with data
    private int findLastRowWithData() {
        int lastRow = packageTable.getRowCount() - 1;
        while (lastRow >= 0 && (packageTable.getValueAt(lastRow, 1) == null || packageTable.getValueAt(lastRow, 1).toString().trim().isEmpty())) {
            lastRow--;
        }
        return lastRow;
    }

    // CheckBoxRenderer for dynamic columns, applying only up to the last row with data
    class CheckBoxRenderer extends DefaultTableCellRenderer {
    private int lastRowWithData;

    public CheckBoxRenderer(int lastRowWithData) {
            this.lastRowWithData = lastRowWithData;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (row > lastRowWithData) {
                return new JLabel(""); // Empty cell if row is beyond the last one with data
            }

            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(value != null && (Boolean) value);

            // Set checkbox to be transparent
            checkBox.setOpaque(false); // This removes the background of the checkbox
            checkBox.setBorderPainted(false); // This removes the border of the checkbox
            checkBox.setBackground(null);

            // Align the checkbox in the center
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            checkBox.setVerticalAlignment(SwingConstants.CENTER);

            // Set the foreground color based on the selection state
            if (isSelected) {
                checkBox.setForeground(table.getSelectionForeground());
            } else {
                checkBox.setForeground(table.getForeground());
            }

            return checkBox; // Return the checkbox directly
        }
    }

    // CheckBoxEditor for dynamic columns, applying only up to the last row with data
    class CheckBoxEditor extends DefaultCellEditor {

        private int lastRowWithData;

        public CheckBoxEditor(int lastRowWithData) {
            super(new JCheckBox());
            this.lastRowWithData = lastRowWithData;
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            int row = packageTable.getSelectedRow();
            return row <= lastRowWithData;
        }

        @Override
        public Object getCellEditorValue() {
            JCheckBox checkBox = (JCheckBox) getComponent();
            return checkBox.isSelected();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (row > lastRowWithData) {
                return new JLabel(""); // Celda vacía si la fila está fuera del rango de datos
            }
            JCheckBox checkBox = (JCheckBox) getComponent();
            checkBox.setSelected(value != null && (Boolean) value);
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            checkBox.setVerticalAlignment(SwingConstants.CENTER);
            checkBox.setOpaque(false);

            // Manejar la selección cuando el usuario interactúe con el checkbox
            checkBox.addActionListener(e -> {
                Object operationIdObj = table.getValueAt(row, 1); // Suponiendo que la columna 1 tiene el ID de operación
                if (operationIdObj != null) {
                    String operationId = operationIdObj.toString();
                    String columnName = table.getModel().getColumnName(column);
                    boolean isSelected1 = checkBox.isSelected();

                    // Llamamos al método intermedio en lugar de storeSelection directamente
                    onCheckBoxSelection(operationId, columnName, isSelected1);
                }
            });

            return checkBox;
        }
    }


    private void resetSelection() {
        packageTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (packageTable.columnAtPoint(e.getPoint()) != 0) {
                    selectedRows.clear();
                }
            }

        });
    }

    private void avoidSelectColumn0() {
        packageTable.getColumnModel().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedColumn = packageTable.getSelectedColumn();
                if (selectedColumn == 0) {
                    // Si se selecciona la columna 0, cambiamos la selección a la columna 1
                    packageTable.changeSelection(packageTable.getSelectedRow(), 1, false, false);
                }
            }
        });
    }

    ArrayList<Integer> selectedRows = new ArrayList<>();
    private void setTableButtonListener() {
        packageTable.addTableButtonListener(new TableButtonListener() {
            @Override
            public void actionPerformed(int row) {

                if (selectedRows.contains(row)) {
                    selectedRows.remove(Integer.valueOf(row));
                } else {
                    selectedRows.add(row);
                }
                packageTable.clearSelection();
                for (int selectedRow : selectedRows) {
                    packageTable.addRowSelectionInterval(selectedRow, selectedRow);
                    packageTable.addColumnSelectionInterval(1, packageTable.getColumnCount() - 1);
                }
                packageTable.getDefaultEditor(Object.class).stopCellEditing();

            }
        });
    }

    private void saveButton() {
        saveButton.addActionListener(e -> {
            
        });
    }

    /*Declaracion de variables de clase*/
    private boolean isHeaderSaved;

    public void setHeaderSaved(boolean isSaved) {
        this.isHeaderSaved = isSaved;
    }
    public void setRoutine(String routine) {
        routineCodeTitleLb.setText(routine);
    }
    public void setRoutineName(String name) {
        routineNameLb.setText(name);
    }
    public void setRoutineCounter(String counter) {
        numberCounterLb.setText(counter);
    }
    
    public void setRoutineStrategy(String strategyCode){
        this.strategyCode=strategyCode;
    }

    private ArrayList<String> selectedOperations;
    public void setOperationsList(ArrayList<String> selectedOperations) {
        this.selectedOperations = selectedOperations;
    }

    private ArrayList<String> selectedOperationsDescription;
    public void setOperationsDescriptionList(ArrayList<String> selectedOperationsDescription) {
        this.selectedOperationsDescription = selectedOperationsDescription;
    }

    private Map<String, Map<String, Boolean>> operationSelections;
    public void setOperationPackagesMap(Map<String, Map<String, Boolean>> operationSelections){
        this.operationSelections=operationSelections;
    }

    // Store the selected value for a given operation and column
    private void storeSelection(String operationId, String columnName, boolean isSelected) {
        operationSelections
                .computeIfAbsent(operationId, k -> new HashMap<>()) // If operationId doesn't exist, create a new map for its columns
                .put(columnName, isSelected);  // Store the selection for the specific column
    }

    // Retrieve the stored selection for a given operation and column
    private Boolean getSelection(String operationId, String columnName) {
        Map<String, Boolean> columns = operationSelections.get(operationId);
        if (columns != null) {
            return columns.get(columnName);
        }
        return false;  // Default if no selection is stored
    }

    // When the checkbox is selected/deselected
    private void onCheckBoxSelection(String operationId, String columnName, boolean isSelected) {
        storeSelection(operationId, columnName, isSelected);
    }

    public void putSelectionValues() {
        DynamicTableModel model = (DynamicTableModel) packageTable.getModel();
        int rows = model.getRowCount();
        int cols = model.getColumnCount();

        // Corregir la inicialización del array de nombres de columnas
        String[] colsNames = new String[cols - 3];

        for (int c = 3; c < cols; c++) {
            colsNames[c - 3] = model.getColumnName(c); // Ajustar el índice del array
        }

        // Recorrer las filas correctamente
        for (int i = 0; i < rows; i++) {
            if (model.getValueAt(i, 2) != null) { // Usar 'i' en lugar de 'rows'
                Object opNum = model.getValueAt(i, 1); // Usar 'i' en lugar de 'rows'
                if (opNum != null) {
                    String opN = opNum.toString();
                    loadSelectionsIntoTable(opN, colsNames); // Llamar con los valores correctos
                }
            }
        }
    }


    private void loadSelectionsIntoTable(String operationId, String[] columnNames) {
        for (String columnName : columnNames) {  // Assuming columnNames is an array or list of column names
            Boolean selection = getSelection(operationId, columnName);
            // Assuming packageTable is the JTable that holds the operations
            // Set the checkbox or value in the specific column
            setCheckboxForColumn(operationId, columnName, selection);
        }
    }

    private void setCheckboxForColumn(String operationId, String columnName, Boolean selection) {
        // Assuming you have access to the model and the table
        int row = findRowForOperation(operationId);  // Method to find the row for the operation
        int col = findColumnForName(columnName);  // Method to find the column index for the given name
        packageTable.getModel().setValueAt(selection, row, col);  // Set the value (true/false) in the table
    }

    private int findRowForOperation(String operationId) {
        DynamicTableModel model = (DynamicTableModel) packageTable.getModel(); // Obtener el modelo de la tabla
      
        // Loop through the rows to find the row index for the given operationId
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(operationId)) {  // Assuming operationId is in the first column
                return i;
            }
        }
        return -1;  // Return -1 if not found
    }

    private int findColumnForName(String columnName) {
        DynamicTableModel model = (DynamicTableModel) packageTable.getModel(); // Obtener el modelo de la tabla

        // Loop through the columns to find the column index for the given column name
        for (int j = 1; j < model.getColumnCount(); j++) { // Usamos el modelo en lugar de la tabla
            if (model.getColumnName(j).equals(columnName)) { // Usamos getColumnName del modelo
                return j;
            }
        }
        return -1;  // Return -1 if not found
    }

    public void setOperationsToTable() {
        DynamicTableModel model = (DynamicTableModel) packageTable.getModel();

        // Iterate through the rows and assign values to columns 1 and 2
        for (int i = 0; i < selectedOperations.size(); i++) {
            // Set the value from selectedOperations in column 1 in the model
            model.setValueAt(selectedOperations.get(i), i, 1);

            // Set the value from selectedOperationsDescription in column 2 in the model
            model.setValueAt(selectedOperationsDescription.get(i), i, 2);
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        exitButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        saveButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        jScrollPane1 = new javax.swing.JScrollPane();
        packageTable = new com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons();
        routineTitleLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineCodeTitleLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineNameLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineCounterLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        jPanel_Rounded_Corners_Degradado1 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        activitiesSummaryLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        numberCounterLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelBotonera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotonera.setColor1(new java.awt.Color(202, 216, 237));
        panelBotonera.setColor2(new java.awt.Color(202, 216, 237));

        exitButton.setBackground(new java.awt.Color(226, 210, 144));
        exitButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        saveButton.setBackground(new java.awt.Color(226, 210, 144));
        saveButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        packageTable.setBackground(new java.awt.Color(202, 216, 237));
        packageTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        packageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        packageTable.setAlineacion(2);
        packageTable.setCellSelectionEnabled(true);
        packageTable.setColorColumna1(new java.awt.Color(255, 255, 255));
        packageTable.setColorColumnas(new java.awt.Color(255, 255, 255));
        packageTable.setColumnNoEditableColor(new java.awt.Color(202, 216, 237));
        packageTable.setFont(new java.awt.Font("Roboto Light", 0, 13)); // NOI18N
        packageTable.setGridColor(new java.awt.Color(175, 175, 175));
        packageTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        packageTable.setShowGrid(true);
        packageTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(packageTable);

        routineTitleLb.setText("Routine sheet");
        routineTitleLb.setFont(new java.awt.Font("Roboto Light", 0, 13)); // NOI18N
        routineTitleLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        routineCodeTitleLb.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        routineCodeTitleLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        routineCodeTitleLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        routineNameLb.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        routineNameLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        routineNameLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        routineCounterLb.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        routineCounterLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        routineCounterLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel_Rounded_Corners_Degradado1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel_Rounded_Corners_Degradado1.setColor1(new java.awt.Color(217, 232, 255));
        jPanel_Rounded_Corners_Degradado1.setColor2(new java.awt.Color(176, 188, 206));

        activitiesSummaryLb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        activitiesSummaryLb.setText("Preventive Maint. Package Ops Summary Overview");
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

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(routineTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(routineCodeTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(routineNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(routineCounterLb, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberCounterLb, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(480, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addGap(60, 60, 60))
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
    private com.simplecore.erp.gui.components.labels.JButtonHQ exitButton;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ numberCounterLb;
    private com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons packageTable;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineCodeTitleLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineCounterLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineNameLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineTitleLb;
    private com.simplecore.erp.gui.components.labels.JButtonHQ saveButton;
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
