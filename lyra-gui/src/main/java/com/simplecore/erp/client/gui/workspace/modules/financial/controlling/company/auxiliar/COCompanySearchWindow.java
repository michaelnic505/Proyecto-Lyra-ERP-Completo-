package com.simplecore.erp.client.gui.workspace.modules.financial.controlling.company.auxiliar;

import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.utils.countries.*;
import com.simplecore.erp.client.gui.windows.auxiliar.*;
import com.simplecore.erp.client.gui.components.tables.BooleanCellRenderer;
import com.simplecore.erp.client.gui.components.tables.newversions.DynamicTableModel;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.RowFilter;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

public class COCompanySearchWindow extends javax.swing.JDialog {
    
    private TableRowSorter<DynamicTableModel> filter;
    private final String[] columnNames;
    private final Object[][] dataMatrix;
    private final TableSelectionListener rowSelectionListener;
    private final List<Integer> selectedColumns;

    public COCompanySearchWindow(Frame parent, String[] columnNames, Object[][]dataMatrix,
            TableSelectionListener rowSelectionListener, List<Integer> selectedColumns) {
        super(parent, true);
        this.columnNames = columnNames;
        this.dataMatrix = dataMatrix;
        this.rowSelectionListener = rowSelectionListener;
        this.selectedColumns = selectedColumns;
        initComponents();
        initEvents();
        initTableList();
        WindowsUtils.makeWindowMovable(headerPanel, this);
        WindowsUtils.makeWindowResizable(this);
        WindowsUtils.addEscToClose(this);
        setBorders();
        setLocationRelativeTo(parent);
    }

    private void setBorders() {
        MatteBorder matteBorder = new MatteBorder(1, 1, 1, 1, new Color(55, 103, 191)); // Borde gris
        getRootPane().setBorder(matteBorder);
    }


    private void initEvents() {
        extiButton();
        selectButton();
        selectRowByDoubleClick();
        searchButton();
    }
    private void extiButton() {
        closeButton.setIcon(new CustomSVGIcon("/icons/svg/close_red.svg", new Dimension(20,20)));
        closeWindowButton.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
        closeButton.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
    }
    
    private void selectButton() {
        selectButton.setIcon(new CustomSVGIcon("/icons/svg/ok_button_icon.svg", new Dimension(20,20)));
        selectButton.addActionListener(e -> {
            returnSelectedRow();
        });
    }
    
    private void selectRowByDoubleClick() {
        tableList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableList.getSelectedRow() != -1) {
                    returnSelectedRow();
                }
            }

        });

    }

    private void returnSelectedRow() {
        int selectedRow = tableList.getSelectedRow();
        if (selectedRow != -1 && rowSelectionListener != null) {
            // Convertir el índice de la vista al índice del modelo
            int modelRow = tableList.convertRowIndexToModel(selectedRow);

            Object[] selectedData = new Object[selectedColumns.size()];
            for (int i = 0; i < selectedColumns.size(); i++) {
                selectedData[i] = tableList.getModel().getValueAt(modelRow, selectedColumns.get(i));
            }

            rowSelectionListener.onRowSelected(selectedData);
            dispose(); // Cerrar la ventana tras la selección
        }
    }

    public void setWindowTitle(String title) {
        windowTitleLabel.setText(title);
    }
    
    public void setTitlePane(int index,String title){
        tabbedPane.setTitleAt(index, title);
    }
    
    private void initTableList(){
        setTableModel();
    }
    
    private void setTableModel() {
        DynamicTableModel model = new DynamicTableModel(dataMatrix.length, columnNames);
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int column = 0; column < columnNames.length; column++) {
                Object value = dataMatrix[row][column];
                model.setValueAt(value, row, column);
                model.setCellEditable(row, column, false);
            }
        }

        tableList.setModel(model);
        setTableFilter();
        WindowsUtils.adjustColumnWidths(tableList);

        // Obtener el render nativo que ya tenía la tabla antes
        TableCellRenderer customRenderer = tableList.getDefaultRenderer(Object.class);

        // Aplicar el mismo render a todos los tipos de datos
        tableList.setDefaultRenderer(Object.class, customRenderer);
        tableList.setDefaultRenderer(String.class, customRenderer);
        tableList.setDefaultRenderer(Integer.class, customRenderer);
        tableList.setDefaultRenderer(Double.class, customRenderer);
        tableList.setDefaultRenderer(java.sql.Date.class, customRenderer);
        tableList.setDefaultRenderer(Boolean.class, new BooleanCellRenderer());
        
        WindowsUtils.autoFitWindowWidth(this, tableList,getWidth(), getHeight());
    }

    private void setTableFilter() {
        tableList.setAutoCreateRowSorter(true);
        filter = new TableRowSorter<>((DynamicTableModel) tableList.getModel());
        tableList.setRowSorter(filter);

        searchBoxTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = searchBoxTf.getText();
                if (text.trim().isEmpty()) {
                    filter.setRowFilter(null); // Quitar el filtro si el campo está vacío
                } else {
                    filter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
                }
            }

        });
    }

    private void searchButton() {
        toolBar.setVisible(false);
        deployButton.setIcon(new ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png")));
        deployButton.addActionListener((e) -> {
            if (toolBar.isVisible()) {
                deployButton.setIcon(new ImageIcon(getClass().getResource("/icons/auxiliarwindows/deployed.png")));
                toolBar.setVisible(false);
            } else {
                deployButton.setIcon(new ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png")));
                toolBar.setVisible(true);
            }
        });
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new com.simplecore.erp.client.gui.components.panels.JPanelCornerPainted();
        headerPanel = new corex.suite.JPanelRoundedGradient();
        windowTitleLabel = new javax.swing.JLabel();
        closeWindowButton = new corex.suite.JButtonGradient();
        tabbedPane = new corex.suite.TabbedPane();
        jPanelRoundedGradient2 = new corex.suite.JPanelRoundedGradient();
        panelButtons = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        searchBoxTf = new javax.swing.JTextField();
        closeButton = new javax.swing.JButton();
        selectButton = new javax.swing.JButton();
        deployButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new corex.suite.LCTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 146, 229));
        setUndecorated(true);

        headerPanel.setColor1(new java.awt.Color(0, 146, 229));
        headerPanel.setColor2(new java.awt.Color(0, 146, 229));

        windowTitleLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        windowTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        windowTitleLabel.setText("Title Window");

        closeWindowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close_window.png"))); // NOI18N
        closeWindowButton.setBorderPainted(false);
        closeWindowButton.setShowBorder(false);

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(windowTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(closeWindowButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(closeWindowButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(windowTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tabbedPane.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setFont(new java.awt.Font("JetBrains Mono", 0, 10)); // NOI18N
        tabbedPane.setSelectedTabColor(new java.awt.Color(0, 146, 229));

        jPanelRoundedGradient2.setColor1(new java.awt.Color(247, 247, 255));
        jPanelRoundedGradient2.setColor2(new java.awt.Color(247, 247, 255));
        jPanelRoundedGradient2.setLayout(new java.awt.BorderLayout());

        toolBar.setRollover(true);
        toolBar.add(searchBoxTf);

        javax.swing.GroupLayout panelButtonsLayout = new javax.swing.GroupLayout(panelButtons);
        panelButtons.setLayout(panelButtonsLayout);
        panelButtonsLayout.setHorizontalGroup(
            panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(deployButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelButtonsLayout.setVerticalGroup(
            panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(deployButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelRoundedGradient2.add(panelButtons, java.awt.BorderLayout.NORTH);

        tableList.setBackground(new java.awt.Color(146, 178, 193));
        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableList.setCellNonEditableOneCellSelection(new java.awt.Color(255, 242, 156));
        tableList.setCellOneColorOnSelection(new java.awt.Color(255, 200, 43));
        jScrollPane1.setViewportView(tableList);

        jPanelRoundedGradient2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Options", jPanelRoundedGradient2);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addGap(12, 12, 12))
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
    private javax.swing.JButton closeButton;
    private corex.suite.JButtonGradient closeWindowButton;
    private javax.swing.JButton deployButton;
    private corex.suite.JPanelRoundedGradient headerPanel;
    private corex.suite.JPanelRoundedGradient jPanelRoundedGradient2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.simplecore.erp.client.gui.components.panels.JPanelCornerPainted mainPanel;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JTextField searchBoxTf;
    private javax.swing.JButton selectButton;
    private corex.suite.TabbedPane tabbedPane;
    private corex.suite.LCTable tableList;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JLabel windowTitleLabel;
    // End of variables declaration//GEN-END:variables
}
