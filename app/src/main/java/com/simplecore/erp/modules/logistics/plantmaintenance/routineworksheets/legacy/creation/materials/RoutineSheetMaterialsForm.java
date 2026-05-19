package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.materials;

import com.simplecore.erp.gui.components.tables.interfaces.TableButtonListener;
import com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons;
import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import com.simplecore.erp.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.utils.documentfilters.DocumentFilterVarchar;
import com.simplecore.erp.utils.documentfilters.NumDoubleLimitFilter;
import com.simplecore.erp.i18n.LanguageManager;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.celleditors.MaterialCodeLookupEditor;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.FilterDocumentEditor;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class RoutineSheetMaterialsForm extends javax.swing.JPanel {

    private LanguageManager languageManager;

    public RoutineSheetMaterialsForm(LanguageManager languageManager) {

        this.languageManager = languageManager;
        initComponents();
        addComponentsEvents();

    }

    private void addComponentsEvents() {
        exitButton();
        saveButton();
        
        backModelButton();
        nextModelButton();
        
        firstModelButton();
        lastModelButton();
    }

    public void setTableModel(DynamicTableModel model) {

        sparePartsTable.setModel(model);
        sparePartsTable.setSelectedRowsList(selectedRows);

        setMaterialSearchBox(sparePartsTable);
        addMaterialCodeListener(model, sparePartsTable);
        addMaterialCalculationListener(model);
        
        avoidSelectColumn0();
        setTableButtonListener();
        setColumnWidths();
        resetSelection();

        setTableMaxFieldsLengths(sparePartsTable);
        activateCellEditorOnKeyPress(sparePartsTable);
        
        setOperationData();
        
    }

    private void setColumnWidths() {
        int[][] columnWidths = {
            {1, 100}, // Position
            {2, 150}, // Material
            {3, 300}, // Component Denomination
            {4, 80}, // Quantity
            {5, 120}, // Unit of Measure
            {6, 100}, // Unit Cost
            {7, 120}, // Warehouse
            {8, 100}, // Total Amount
            {9, 150} // Classification
        };

        Arrays.stream(columnWidths)
                .forEach(col -> sparePartsTable.getColumnModel().getColumn(col[0]).setPreferredWidth(col[1]));
    }


    private void resetSelection() {
        sparePartsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (sparePartsTable.columnAtPoint(e.getPoint()) != 0) {
                    selectedRows.clear();
                }
            }

        });
    }

    private void avoidSelectColumn0() {
        sparePartsTable.getColumnModel().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedColumn = sparePartsTable.getSelectedColumn();
                if (selectedColumn == 0) {
                    // Si se selecciona la columna 0, cambiamos la selección a la columna 1
                    sparePartsTable.changeSelection(sparePartsTable.getSelectedRow(), 1, false, false);
                }
            }
        });
    }

    ArrayList<Integer> selectedRows = new ArrayList<>();
    private void setTableButtonListener() {
        sparePartsTable.addTableButtonListener(new TableButtonListener() {
            @Override
            public void actionPerformed(int row) {

                if (selectedRows.contains(row)) {
                    selectedRows.remove(Integer.valueOf(row));
                } else {
                    selectedRows.add(row);
                }
                sparePartsTable.clearSelection();
                for (int selectedRow : selectedRows) {
                    sparePartsTable.addRowSelectionInterval(selectedRow, selectedRow);
                    sparePartsTable.addColumnSelectionInterval(1, sparePartsTable.getColumnCount() - 1);
                }
                sparePartsTable.getDefaultEditor(Object.class).stopCellEditing();

            }
        });
    }

    private static final int POSITION_MAX_LENGTH = 10; // Posición
    private static final int MATERIAL_MAX_LENGTH = 20; // Material
    private static final int COMPONENT_DENOMINATION_MAX_LENGTH = 30; // Denominación del componente
    private static final int QUANTITY_MAX_LENGTH = 5;  // Cantidad
    private static final int UNIT_OF_MEASURE_MAX_LENGTH = 15; // Unidad de medida
    private static final int UNIT_COST_MAX_LENGTH = 10; // Costo unitario
    private static final int WAREHOUSE_MAX_LENGTH = 20; // Almacén
    private static final int TOTAL_AMOUNT_MAX_LENGTH = 15; // Monto total
    private static final int CLASSIFICATION_MAX_LENGTH = 25; // Clasificación
    private static final int DECIMALS_LENGTH = 3;//Decimals

    private void setTableMaxFieldsLengths(DynamicTableButtons table) {

        // Set alphanumeric columns
        setColumnEditor(table, 1, new DocFilterVarcharWithoutSpace(POSITION_MAX_LENGTH).setUpperCase(true));
        //setColumnEditor(table, 2, new DocFilterVarcharWithoutSpace(MATERIAL_MAX_LENGTH).setUpperCase(true));
        setColumnEditor(table, 3, new DocumentFilterVarchar(COMPONENT_DENOMINATION_MAX_LENGTH).setUpperCase(true));
        setColumnEditor(table, 4, new NumDoubleLimitFilter(QUANTITY_MAX_LENGTH, DECIMALS_LENGTH));
        setColumnEditor(table, 5, new DocFilterVarcharWithoutSpace(UNIT_OF_MEASURE_MAX_LENGTH).setUpperCase(true));
        setColumnEditor(table, 6, new NumDoubleLimitFilter(UNIT_COST_MAX_LENGTH, DECIMALS_LENGTH));
        setColumnEditor(table, 7, new DocFilterVarcharWithoutSpace(WAREHOUSE_MAX_LENGTH).setUpperCase(true));
        setColumnEditor(table, 8, new NumDoubleLimitFilter(TOTAL_AMOUNT_MAX_LENGTH, DECIMALS_LENGTH));
        setColumnEditor(table, 9, new DocFilterVarcharWithoutSpace(CLASSIFICATION_MAX_LENGTH).setUpperCase(true));

    }
    private void setColumnEditor(DynamicTableButtons table, int columnIndex, DocumentFilter filter) {
        FilterDocumentEditor cellEditor = new FilterDocumentEditor(filter);

        // Assign the editor to the corresponding column
        table.getColumnModel().getColumn(columnIndex).setCellEditor(cellEditor);
    }
    private void setMaterialSearchBox(DynamicTableButtons table) {

        JTextField textField = new JTextField();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(
                new DocFilterVarcharWithoutSpace(MATERIAL_MAX_LENGTH).setUpperCase(true));

        MaterialCodeLookupEditor materialLookupEditor = new MaterialCodeLookupEditor(textField);
        table.getColumnModel().getColumn(2).setCellEditor(materialLookupEditor);
    }
    private void addMaterialCodeListener(DynamicTableModel model,DynamicTableButtons table) {
        MaterialCodeListener materialCodeListener = new MaterialCodeListener(model,table);
        model.addTableModelListener(materialCodeListener);
    }
    private void addMaterialCalculationListener(DynamicTableModel model) {
        MaterialsCalculationListener materialCalculationListener = new MaterialsCalculationListener(model);
        model.addTableModelListener(materialCalculationListener);
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
                    if (col == 2 ) {
                        char keyChar = e.getKeyChar();
                        if (Character.isLetterOrDigit(keyChar) || Character.isSpaceChar(keyChar)) {
                            // Activar el editor solo si aún no está activo
                            if (!table.isEditing()) {
                                table.editCellAt(row, col);
                            }
                        }
                    } // Si es una columna con un editor numérico
                    else if (col == 4 ) {
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
    public void setOperationNumber(String operNum) {
        opNumTf.setText(operNum);
    }
    public void setOperationDescription(String description) {
        operationDescriptionTf.setText(description);
    }

    private int currentIndex;
    public void setFirstIndex(int index) {
        this.currentIndex = index;
    }

    private Map<String, DynamicTableModel> mapModels;
    public void setMapModel(Map<String, DynamicTableModel> mapModels) {
        this.mapModels = mapModels;
    }

    private ArrayList<String> selectedOperations;
    public void setOperationsList(ArrayList<String> selectedOperations) {
        this.selectedOperations = selectedOperations;
    }

    private ArrayList<String> selectedOperationsDescription;
    public void setOperationsDescriptionList(ArrayList<String> selectedOperationsDescription) {
        this.selectedOperationsDescription = selectedOperationsDescription;
    }
 
    private void setOperationData() {
        String currentOperation = selectedOperations.get(currentIndex);
        opNumTf.setText(currentOperation);

        // Usar currentIndex para obtener la descripción correspondiente
        String description = selectedOperationsDescription.get(selectedOperations.indexOf(currentOperation));
        operationDescriptionTf.setText(description);
    }


    private void backModel() {
        if (currentIndex > 0) {
            currentIndex--;
            updateTableModel();
        }
    }
    private void nextModel() {
        if (currentIndex < selectedOperations.size() - 1) {
            currentIndex++;
            updateTableModel();
        }
    }

    private void backModelButton() {
        backButton.addActionListener(e -> {
            backModel();
        });
    }
    private void nextModelButton() {
        nextButton.addActionListener(e -> {
            nextModel();
        });
    }

    private void firstModel() {
        currentIndex = 0;  // Mover al primer modelo
        updateTableModel();
    }
    private void lastModel() {
        currentIndex = selectedOperations.size() - 1;  // Mover al último modelo
        updateTableModel();
    }

    private void firstModelButton() {
        firstButton.addActionListener(e -> firstModel());
    }
    private void lastModelButton() {
        lastButton.addActionListener(e -> lastModel());
    }

    private void updateTableModel() {
        String currentOperation = selectedOperations.get(currentIndex);
        DynamicTableModel newModel = mapModels.get(currentOperation);

        if (newModel != null) {
            sparePartsTable.setModel(newModel);
            setColumnWidths();
            setOperationData();
            setMaterialSearchBox(sparePartsTable);
            addMaterialCodeListener(newModel, sparePartsTable);
            addMaterialCalculationListener(newModel);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        exitButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        saveButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        removeRowButton2 = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        addRowButton2 = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        lastButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        firstButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        backButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        nextButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        jScrollPane1 = new javax.swing.JScrollPane();
        sparePartsTable = new com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons();
        routineTitleLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineCodeTitleLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineNameLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineCounterLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        jPanel_Rounded_Corners_Degradado1 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        activitiesSummaryLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        numberCounterLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        positionNameLb = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        opNumTf = new javax.swing.JTextField();
        operationDescriptionTf = new javax.swing.JTextField();

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

        removeRowButton2.setBackground(new java.awt.Color(226, 210, 144));
        removeRowButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        removeRowButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/remove.png"))); // NOI18N
        removeRowButton2.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        addRowButton2.setBackground(new java.awt.Color(226, 210, 144));
        addRowButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addRowButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/add.png"))); // NOI18N
        addRowButton2.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        lastButton.setBackground(new java.awt.Color(226, 210, 144));
        lastButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lastButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/double_right_arrow.png"))); // NOI18N
        lastButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        firstButton.setBackground(new java.awt.Color(226, 210, 144));
        firstButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        firstButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/double_left_arrow.png"))); // NOI18N
        firstButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        backButton.setBackground(new java.awt.Color(226, 210, 144));
        backButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/left_arrow.png"))); // NOI18N
        backButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        nextButton.setBackground(new java.awt.Color(226, 210, 144));
        nextButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/rigth_arrow.png"))); // NOI18N
        nextButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(firstButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(addRowButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(removeRowButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addRowButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeRowButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        sparePartsTable.setBackground(new java.awt.Color(202, 216, 237));
        sparePartsTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sparePartsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        sparePartsTable.setAlineacion(2);
        sparePartsTable.setCellSelectionEnabled(true);
        sparePartsTable.setColorColumna1(new java.awt.Color(255, 255, 255));
        sparePartsTable.setColorColumnas(new java.awt.Color(255, 255, 255));
        sparePartsTable.setColumnNoEditableColor(new java.awt.Color(202, 216, 237));
        sparePartsTable.setFont(new java.awt.Font("Roboto Light", 0, 13)); // NOI18N
        sparePartsTable.setGridColor(new java.awt.Color(175, 175, 175));
        sparePartsTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        sparePartsTable.setShowGrid(true);
        sparePartsTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(sparePartsTable);

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
        activitiesSummaryLb.setText("Component Assignment");
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

        positionNameLb.setText("Operation");
        positionNameLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        opNumTf.setEditable(false);

        operationDescriptionTf.setEditable(false);

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(positionNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(opNumTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(operationDescriptionTf))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(routineTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(routineCodeTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(routineNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(routineCounterLb, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numberCounterLb, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(5, 5, 5)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(opNumTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(positionNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operationDescriptionTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
    private com.simplecore.erp.gui.components.labels.JButtonHQ addRowButton2;
    private com.simplecore.erp.gui.components.labels.JButtonHQ backButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ exitButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ firstButton;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.simplecore.erp.gui.components.labels.JButtonHQ lastButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ nextButton;
    private com.simplecore.erp.gui.components.labels.JLabelHQ numberCounterLb;
    private javax.swing.JTextField opNumTf;
    private javax.swing.JTextField operationDescriptionTf;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined positionNameLb;
    private com.simplecore.erp.gui.components.labels.JButtonHQ removeRowButton2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineCodeTitleLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineCounterLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineNameLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineTitleLb;
    private com.simplecore.erp.gui.components.labels.JButtonHQ saveButton;
    private com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons sparePartsTable;
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
