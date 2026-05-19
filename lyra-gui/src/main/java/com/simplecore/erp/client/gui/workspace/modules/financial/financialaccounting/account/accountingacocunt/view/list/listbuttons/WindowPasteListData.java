package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.listbuttons;

import com.simplecore.erp.client.gui.components.tables.newversions.DynamicTableModel;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.windows.auxiliar.DataListPastedListener;
import com.simplecore.erp.client.gui.windows.auxiliar.WindowsUtils;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.classes.AlignedColumnRenderer;
import com.simplecore.erp.client.utils.documentfilters.TextSanitizer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class WindowPasteListData extends javax.swing.JDialog {
    
    private final String[]columnNames;
    private final DataListPastedListener pastedListener;
    private final List<Object> columnsToCopy;
    private final List<Object> originalList;
    
    public WindowPasteListData(Frame parent,String[] columnNames,DataListPastedListener pastedListener,
            List<Object> columnsToCopy,List<Object> originalList) {
        super(parent, true);
        this.pastedListener = pastedListener;
        this.columnsToCopy = columnsToCopy;
        this.columnNames = columnNames;
        this.originalList = originalList;
        initComponents();
        initEvents();
        setTableModel(originalList);
        WindowsUtils.makeWindowMovable(headerPanel, this);
        WindowsUtils.makeWindowResizable(this);
        setBorders();
        setLocationRelativeTo(parent);
    }
    private int limit;
    public void setDocumentFilter(DocumentFilter docFilter,int limit) {
        this.limit = limit;
        if (docFilter != null) {
            JTextField textField = new JTextField();
            ((AbstractDocument) textField.getDocument()).setDocumentFilter(docFilter);
            
            DefaultCellEditor cellEditor = new DefaultCellEditor(textField) {
                @Override
                public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                    Component editor = super.getTableCellEditorComponent(table, value, isSelected, row, column);

                    // Agregar FocusListener al componente editor
                    editor.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusLost(FocusEvent e) {
                            // Verificar si el nuevo foco NO es parte del editor
                            if (!e.isTemporary() && !isChildComponent(editor, e.getOppositeComponent())) {
                                stopCellEditing(); // Guardar cambios
                                // cancelCellEditing();
                            }
                        }
                    });
                    return editor;
                }

                private boolean isChildComponent(Component parent, Component child) {
                    while (child != null) {
                        if (child == parent) {
                            return true;
                        }
                        child = child.getParent();
                    }
                    return false;
                }

            };
            tableList.getColumnModel().getColumn(0).setCellEditor(cellEditor);
        }
    }

    private TextSanitizer.Types sanitizationType;
    public void setInputSanitizer(TextSanitizer.Types sanitizationType){
        this.sanitizationType = sanitizationType;
    }
    private int integers = -1;
    private int decimals = -1;
    public void setInputSanitizer(TextSanitizer.Types sanitizationType,int integers,int decimals){
        this.sanitizationType = sanitizationType;
        this.integers = integers;
        this.decimals = decimals;
    }
    
    private void editOnKeyPressed() {
        tableList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int row = tableList.getSelectedRow();
                int col = tableList.getSelectedColumn();

                if (row != -1 && col != -1) { // Asegurarse de que haya una celda seleccionada
                    if (tableList.editCellAt(row, col)) {
                        Component editor = tableList.getEditorComponent(); // Obtener el editor después de entrar en modo de edición
                        if (editor != null) {
                            editor.requestFocus(); // Transferir el foco al editor
                        }
                    }
                }
            }
        });

    }


    private void setBorders() {
        MatteBorder matteBorder = new MatteBorder(1, 1, 1, 1, new Color(55, 103, 191)); // Borde gris
        getRootPane().setBorder(matteBorder);
    }

    private void initEvents() {
        extiButton();
        okButton();
        pasteButton();
        clearButton();
        setKeyStrokePasteInTable();
        editOnKeyPressed();
    }
    
    private void extiButton() {
        closeWindowButton.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
    }
    
    private void okButton() {
        selectButton.setIcon(new CustomSVGIcon("/icons/svg/ok_button_icon.svg", new Dimension(20,20)));
        selectButton.addActionListener(e -> {
            returnDataList();
        });
    }
    
    private void pasteButton(){
        pasteButton.setIcon(new CustomSVGIcon("/icons/svg/paste.svg", new Dimension(18,18)));
        pasteButton.addActionListener(e->{
            pasteFromClipboard();
        });
    }
    
    private void clearButton(){
        clearButton.setIcon(new CustomSVGIcon("/icons/svg/clear.svg", new Dimension(20,20)));
        clearButton.addActionListener(e->{
            originalList.clear();
            ((DynamicTableModel)tableList.getModel()).clearTable();
            WindowsUtils.adjustColumnWidths(tableList);
        });
    }

    public void setWindowTitle(String title) {
        windowTitleLabel.setText(title);
    }

    private void setTableModel(List<Object> originalList) {
        final int MAX_ROWS = 999;
        DynamicTableModel model = new DynamicTableModel(MAX_ROWS, columnNames);

        int rowsToFill = Math.min(originalList.size(), MAX_ROWS); // Evita el break dentro del bucle
        for (int row = 0; row < rowsToFill; row++) {
            model.setValueAt(originalList.get(row), row, 0);
        }

        tableList.setModel(model);
        WindowsUtils.adjustColumnWidths(tableList);
        AlignedColumnRenderer.setAlignedColumnRenderer(tableList, 0, SwingConstants.LEFT);
       // WindowsUtils.autoFitWindowWidth(this, tableList, getWidth(), getHeight());
    }

    /**
     * This method extracts the data from the JTable and stores it in a List of Object arrays.
     * Each row in the table will be represented by an Object array containing the column values.
     * If no data is available (i.e. all cells are null), it returns an empty collection instead.
     * 
     * After extracting the data, the method calls the listener to pass the data as a 2D Object array.
     */
    private void returnDataList() {
        // Get the table model from the tableList
        originalList.clear();
        DynamicTableModel model = (DynamicTableModel) tableList.getModel();

        // Create a list to hold the rows of data
        List<Object[]> dataPastedList = new ArrayList<>();

        // Iterate through each row in the table
        for (int row = 0; row < model.getRowCount(); row++) {
            // Create a new array for the current row's data
            Object[] columnas = new Object[model.getColumnCount()]; // Create a new array for each row

            // Iterate through each column in the row and get the value
            boolean isRowEmpty = true; // Track if the row has non-null values

            for (int column = 0; column < model.getColumnCount(); column++) {
                Object value = model.getValueAt(row, column);
                columnas[column] = value;

                // Check if any value in the row is non-null
                if (value != null) {
                    isRowEmpty = false;
                }
            }

            // If the row has any non-null value, add it to the list
            if (!isRowEmpty) {
                dataPastedList.add(columnas);
            }
        }

        // If the dataPastedList is empty, return an empty collection
        if (dataPastedList.isEmpty()) {
            pastedListener.OnDataPasted(Collections.emptyList().toArray(new Object[0][0]));
        } else {
            // Otherwise, pass the data as a 2D array to the listener (converted from List<Object[]> to Object[][])
            pastedListener.OnDataPasted(dataPastedList.toArray(new Object[0][0]));
        }

        dispose();
    }

    private void setKeyStrokePasteInTable() {
        KeyStroke pasteKey = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK);
        Action pasteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pasteFromClipboard();
            }
        };
        // Asignar el KeyStroke a la acción de pegar
        tableList.getInputMap(JComponent.WHEN_FOCUSED).put(pasteKey, "paste");
        tableList.getActionMap().put("paste", pasteAction);
    }

    private void pasteFromClipboard(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            String clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
            processClipboardData(clipboardText);
        } catch (UnsupportedFlavorException | IOException ex) {
            Logger.getLogger(WindowPasteListData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processClipboardData(String clipboardText) {
        if (clipboardText == null || clipboardText.trim().isEmpty()) {
            return;
        }
        String[] rows = clipboardText.split("\n");
        Set<String> uniqueValues = new LinkedHashSet<>();

        // Verificar que todas las filas tengan exactamente una sola columna
        for (String row : rows) {
            String trimmedRow = row.trim();

            // Verificar que la fila no esté vacía y que solo haya una columna
            String[] columns = row.split("\t");
            if (!trimmedRow.isEmpty() && columns.length == 1) {

                if (sanitizationType == null) {
                    uniqueValues.add(trimmedRow); // No aplicar ningún filtro
                } else {
                    trimmedRow = sanitizedInput(trimmedRow, sanitizationType);
                }
                if (!trimmedRow.isEmpty()) {
                    uniqueValues.add(trimmedRow);
                }
            } else if (columns.length > 1) {
                return; // Si hay más de una columna, cancela la operación
            }
        }

        if (uniqueValues.isEmpty()) {
            return; // Si no hay datos válidos, no hace nada
        }

        DynamicTableModel model = (DynamicTableModel) tableList.getModel();
        model.clearTable();

        int rowIndex = 0;
        for (String value : uniqueValues) {
            model.setValueAt(value, rowIndex++, 0);
        }

        WindowsUtils.adjustColumnWidths(tableList);
        //WindowsUtils.autoFitWindowWidth(this, tableList, getWidth(), getHeight());
    }

    private String sanitizedInput(String trimmedRow, TextSanitizer.Types type) {
        switch (type) {
            case VARCHAR_SPACED_UPPERCASE -> {
                return TextSanitizer.sanitizeVarcharWithSpaces(trimmedRow, limit, true);
            }
            case VARCHAR_SPACED -> {
                return TextSanitizer.sanitizeVarcharWithSpaces(trimmedRow, limit, false);
            }
            case VARCHAR_NO_SPACED_UPPERCASE -> {
                return TextSanitizer.sanitizeVarcharWithoutSpaces(trimmedRow, limit, true);
            }
            case VARCHAR_NO_SPACED -> {
                return TextSanitizer.sanitizeVarcharWithoutSpaces(trimmedRow, limit, false);
            }
            case NUMERIC -> {
                return TextSanitizer.sanitizeNumeric(trimmedRow, limit);
            }
            case DECIMAL -> {
                if (integers != -1 && decimals != -1) {
                    return TextSanitizer.sanitizeDoubleWithLimit(trimmedRow, integers, decimals);
                }
                return TextSanitizer.sanitizeDoubleWithLimit(trimmedRow, 6, 3);
            }
        }
        return "";
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new com.simplecore.erp.client.gui.components.panels.JPanelCornerPainted();
        headerPanel = new corex.suite.JPanelRoundedGradient();
        windowTitleLabel = new javax.swing.JLabel();
        closeWindowButton = new corex.suite.JButtonGradient();
        panelButtons = new javax.swing.JPanel();
        pasteButton = new javax.swing.JButton();
        selectButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new corex.suite.LCTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 146, 229));
        setUndecorated(true);

        headerPanel.setColor1(new java.awt.Color(0, 146, 229));
        headerPanel.setColor2(new java.awt.Color(0, 146, 229));

        windowTitleLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        windowTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        windowTitleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connectionsdb/access_title_icon.png"))); // NOI18N
        windowTitleLabel.setText("Title Window");

        closeWindowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close_window.png"))); // NOI18N
        closeWindowButton.setBorderPainted(false);
        closeWindowButton.setShowBorder(false);

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(windowTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addGap(65, 65, 65)
                .addComponent(closeWindowButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(closeWindowButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(windowTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelButtons.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelButtonsLayout = new javax.swing.GroupLayout(panelButtons);
        panelButtons.setLayout(panelButtonsLayout);
        panelButtonsLayout.setHorizontalGroup(
            panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelButtonsLayout.setVerticalGroup(
            panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        tableList.setBackground(new java.awt.Color(146, 178, 193));
        tableList.setBorder(null);
        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableList.setCellNonEditableOneCellSelection(new java.awt.Color(255, 242, 156));
        tableList.setCellOneColorOnSelection(new java.awt.Color(255, 200, 43));
        tableList.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N
        tableList.setHeaderFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        jScrollPane1.setViewportView(tableList);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private corex.suite.JButtonGradient closeWindowButton;
    private corex.suite.JPanelRoundedGradient headerPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private com.simplecore.erp.client.gui.components.panels.JPanelCornerPainted mainPanel;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JButton pasteButton;
    private javax.swing.JButton selectButton;
    private corex.suite.LCTable tableList;
    private javax.swing.JLabel windowTitleLabel;
    // End of variables declaration//GEN-END:variables
     
}
