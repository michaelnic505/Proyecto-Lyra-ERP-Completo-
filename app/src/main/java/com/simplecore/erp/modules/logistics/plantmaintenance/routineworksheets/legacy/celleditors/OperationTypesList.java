package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.celleditors;

import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.OperationTypesMaintenance;
import com.simplecore.erp.gui.workspace.LyraWorkspace;

public class OperationTypesList extends javax.swing.JDialog {

    private TableRowSorter<DynamicTableModel> filter;

    public OperationTypesList(Frame parent, boolean modal) {

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
    private void getSelectedRowData(JTextField tf, JTextField tf2) {

        int selectedRow = tableList.getSelectedRow();

        if (selectedRow != -1) {
            int selectedModelRow = tableList.convertRowIndexToModel(selectedRow);

            Object codeG = tableList.getModel().getValueAt(selectedModelRow, 0);
            Object descG = tableList.getModel().getValueAt(selectedModelRow, 1);

            if (tf != null) {
                tf.setText(codeG.toString());
            }
            if (tf2 != null) {
                tf2.setText(descG.toString());
            }
            dispose();
        }
    }

// Overloaded method for a JTextField and a JLabel
    private void getSelectedRowData(JTextField tf, JLabel label) {

        int selectedRow = tableList.getSelectedRow();

        if (selectedRow != -1) {
            int selectedModelRow = tableList.convertRowIndexToModel(selectedRow);

            Object codeG = tableList.getModel().getValueAt(selectedModelRow, 0);
            Object descG = tableList.getModel().getValueAt(selectedModelRow, 1);

            if (tf != null) {
                tf.setText(codeG.toString());
            }
            if (label != null) {
                label.setText(descG.toString());
            }
            dispose();
        }
    }


    private void selectButton() {
        selectButton.addActionListener(e -> {
            getSelectedRowData(textField1,textField2);
        });
    }

    private void selectRowByDoubleClick() {
        tableList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableList.getSelectedRow() != -1) {
                    getSelectedRowData(textField1,textField2);
                }
            }

        });

    }

    private String[] getTableColumnsName() {
        String actMaintID = LyraWorkspace.getTableTranslator().getTranslation("column.actMaintID");
        String activityMaintenanceDescription = LyraWorkspace.getTableTranslator().getTranslation("column.activityMaintenanceDescription");
        String unitCost = LyraWorkspace.getTableTranslator().getTranslation("column.unitCost");
        String unitOfMeasure = LyraWorkspace.getTableTranslator().getTranslation("column.unitOfMeasure");
        String currency = LyraWorkspace.getTableTranslator().getTranslation("column.currency");

        return new String[]{
            actMaintID,
            activityMaintenanceDescription,
            unitCost,
            unitOfMeasure,
            currency
        };
    }


   private void setTableModel() {
      
        String[] columns = getTableColumnsName();

        OperationTypesMaintenance pG = new OperationTypesMaintenance();
        List<List<Object>> matrix = pG.getOperationTypes(true);

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
        tableList.getColumnModel().getColumn(0).setPreferredWidth(80);  // IDActMtto
        tableList.getColumnModel().getColumn(1).setPreferredWidth(200); // Descripción Actividad Mantenimiento
        tableList.getColumnModel().getColumn(2).setPreferredWidth(100); // Costo Unitario
        tableList.getColumnModel().getColumn(3).setPreferredWidth(120); // Unidad Medida
        tableList.getColumnModel().getColumn(4).setPreferredWidth(80);  // Moneda

        setTableFilter();
    }

    private JTextField textField1;
    public void setJTextFieldCode(JTextField tf) {
        this.textField1 = tf;
    }

    private JTextField textField2;
    public void setJTextField2(JTextField tf2) {
        this.textField2 = tf2;
    }

    // Method to set a JLabel
    private JLabel label;
    public void setJLabel(JLabel label) {
        this.label = label;
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
            int extraWidthPerColumn = (int) (defaultWidth * 0.4); // 10% del ancho predeterminado
            int columnsExceeding = columnCount - (defaultWidth / table.getColumnModel().getColumn(0).getWidth());
            int newWidth = defaultWidth + (columnsExceeding * extraWidthPerColumn);
            frame.setSize(newWidth, frame.getHeight());
        }

        // Ajustar la altura de la ventana según las filas
        int rowCount = table.getRowCount();
        int rowHeight = table.getRowHeight();
        int totalRowHeight = rowCount * rowHeight;

        if (totalRowHeight > defaultHeight) {
            int extraHeightPerRow = (int) (defaultHeight * 0.1); // 5% de la altura predeterminada
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
