package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.celleditors;

import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.RegisteredWarehouseMaterials;
import com.simplecore.erp.gui.workspace.LyraWorkspace;

public class MaterialCodeLookupList extends javax.swing.JDialog {

    private TableRowSorter<DynamicTableModel> filter;

    public MaterialCodeLookupList(Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        addEvents();
        setBorders();
        setTableModel();

        SwingUtilities.invokeLater(() -> {
            autoFitWindowWidth(this, tableList, getWidth(), getHeight());
            tFilter.requestFocus();
        });

        // Use InputMap and ActionMap to close the dialog when ESC is pressed
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "closeDialog");
        getRootPane().getActionMap().put("closeDialog", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });

    }

    private void setBorders() {
        MatteBorder matteBorder = new MatteBorder(1, 1, 1, 1, new Color(55, 103, 191)); // Borde gris
        getRootPane().setBorder(matteBorder);
    }

    private void addEvents() {
        extiButton();
        selectButton();
        selectRowByDoubleClick();
    }

    private void extiButton() {
        exitButton.addActionListener((ActionEvent e) -> {
            this.dispose();
        });

    }
    
    
// Original method modified to receive two JTextFields as parameters
    private void getSelectedRowData(JTextField materialCode,JTable warehouseCode) {

        int selectedRow = tableList.getSelectedRow();

        if (selectedRow != -1) {
            int selectedModelRow = tableList.convertRowIndexToModel(selectedRow);

            Object matCode = tableList.getModel().getValueAt(selectedModelRow, 0);
            Object wareCode = tableList.getModel().getValueAt(selectedModelRow, 2);

            if (materialCode != null) {
                materialCode.setText(matCode.toString());
            }
            if(warehouseCode!=null){
                warehouseCode.setValueAt(wareCode.toString(), row, 7);
            }

            dispose();
        }
    }

    private void selectButton() {
        selectButton.addActionListener(e -> {
            getSelectedRowData(materialCodeField, warehouseField);
        });
    }

    private void selectRowByDoubleClick() {
        tableList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableList.getSelectedRow() != -1) {
                    getSelectedRowData(materialCodeField,warehouseField);
                }
            }

        });

    }

    private String[] getTableColumnsName() {
        String[] keys = {
            "column.material.code",
            "column.material.description",
            "column.warehouse.code",
            "column.warehouse.description",
            "column.unit.price",
            "column.unit.measure.id",
            "column.series",
            "column.model",
            "column.brand",
        };

        return Arrays.stream(keys)
                .map(LyraWorkspace.getTableTranslator()::getTranslation)
                .toArray(String[]::new);
    }



   private void setTableModel() {
      
        String[] columns = getTableColumnsName();

        RegisteredWarehouseMaterials query = new RegisteredWarehouseMaterials();
        List<List<Object>> matrix = query.getMaterialsList(true);

        int rows = (matrix != null) ? matrix.size() : 0;

        DynamicTableModel model = new DynamicTableModel(rows, columns);

        //Add rows data
        if (matrix != null) {
            for (int i = 0; i < rows; i++) {
                for (int c = 0; c < columns.length; c++) {
                    Object value = matrix.get(i).get(c); // Uso de Object en lugar de String
                    model.setValueAt(value, i, c);
                    model.setCellEditable(i, c, false);
                }
            }
        }
        
        tableList.setModel(model);
        setColumnsWidths();
        setTableFilter();
    }

    private void setColumnsWidths() {
        TableColumnModel columnModel = tableList.getColumnModel();

        int[][] columnWidths = {
            {0, 100}, // MATERIAL CODE
            {1, 200}, // MATERIAL DESCRIPTION
            {2, 120}, // WAREHOUSE CODE
            {3, 180}, // WAREHOUSE DESCRIPTION
            {4, 100}, // UNIT PRICE
            {5, 100}, // UNIT MEASURE ID
            {6, 120}, // SERIES
            {7, 120}, // MODEL
            {8, 120} // BRAND
        };

        Arrays.stream(columnWidths)
                .forEach(col -> columnModel.getColumn(col[0]).setPreferredWidth(col[1]));
    }

    private JTextField materialCodeField;
    public void setMaterialCodeField(JTextField materialCodeField) {
        this.materialCodeField = materialCodeField;
    }
    private JTable warehouseField;
    private int row;
    public void setWarehouseCodeField(JTable warehouseField,int row) {
        this.warehouseField = warehouseField;
        this.row=row;
    }

    private void setTableFilter() {

        tableList.setAutoCreateRowSorter(true);
        filter = new TableRowSorter<>((DynamicTableModel) tableList.getModel());
        tableList.setRowSorter(filter);

        tFilter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = tFilter.getText();
                if (text.trim().isEmpty()) {
                    filter.setRowFilter(null); // Quitar el filtro si el campo está vacío
                } else {
                    filter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
                }
            }

        });
    }

    private void autoFitWindowWidth(JDialog frame, JTable table, int defaultWidth, int defaultHeight) {

        int columnCount = table.getColumnModel().getColumnCount();
        int totalColumnWidth = table.getColumnModel().getTotalColumnWidth();

        if (totalColumnWidth > defaultWidth) {
            int extraWidthPerColumn = (int) (defaultWidth * 0.1); // 10% del ancho predeterminado
            int columnsExceeding = columnCount - (defaultWidth / table.getColumnModel().getColumn(0).getWidth());
            int newWidth = defaultWidth + (columnsExceeding * extraWidthPerColumn);
            frame.setSize(newWidth, frame.getHeight());
        }

        // Ajustar la altura de la ventana según las filas
        int rowCount = table.getRowCount();
        int rowHeight = table.getRowHeight();
        int totalRowHeight = rowCount * rowHeight;

        if (totalRowHeight > defaultHeight) {
            int extraHeightPerRow = (int) (defaultHeight * 0.05); // 5% de la altura predeterminada
            int rowsExceeding = rowCount - (defaultHeight / rowHeight);
            int newHeight = defaultHeight + (rowsExceeding * extraHeightPerRow);
            frame.setSize(frame.getWidth(), newHeight);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new com.simplecore.erp.gui.components.tables.newversions.DynamicTable();
        jPanel_Rounded_Corners_Degradado1 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        exitButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        selectButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        tFilter = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 146, 229));
        setUndecorated(true);

        tableList.setBackground(new java.awt.Color(146, 178, 193));
        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableList.setNotEditableCell(new java.awt.Color(221, 221, 221));
        tableList.setNotEditableCellSelection(new java.awt.Color(221, 174, 37));
        tableList.setNotEditableUniqueCellSelection(new java.awt.Color(221, 174, 37));
        tableList.setOpaque(false);
        jScrollPane1.setViewportView(tableList);

        jPanel_Rounded_Corners_Degradado1.setBackground(new java.awt.Color(217, 215, 200));
        jPanel_Rounded_Corners_Degradado1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado1.setColor1(new java.awt.Color(217, 215, 200));
        jPanel_Rounded_Corners_Degradado1.setColor2(new java.awt.Color(217, 215, 200));

        exitButton.setBackground(new java.awt.Color(226, 210, 144));
        exitButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close.png"))); // NOI18N

        selectButton.setBackground(new java.awt.Color(226, 210, 144));
        selectButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        selectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/ok_icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado1Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado1);
        jPanel_Rounded_Corners_Degradado1.setLayout(jPanel_Rounded_Corners_Degradado1Layout);
        jPanel_Rounded_Corners_Degradado1Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado1Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tFilter, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JButtonHQ exitButton;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.simplecore.erp.gui.components.labels.JButtonHQ selectButton;
    private javax.swing.JTextField tFilter;
    private com.simplecore.erp.gui.components.tables.newversions.DynamicTable tableList;
    // End of variables declaration//GEN-END:variables


}
