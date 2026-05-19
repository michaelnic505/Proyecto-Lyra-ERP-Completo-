package com.simplecore.erp.modules.logistics.plantmaintenance.strategies.legacy.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.components.tables.interfaces.TableButtonListener;
import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;


public class MaintenanceStrategies extends javax.swing.JPanel {
    
    private String username;
    public MaintenanceStrategies(String username) {
        this.username = username;
        initComponents();    
        addEvents();     
        loadStrategiesList();
    }


    private void addEvents(){
        
        setTableModel();
        buttonNewEntries();
        buttonDetails();
        buttonChange();
        exitButton();
        
    }
    
    private void setTableModel() {

        String[] identifiers_ES = {null,
            "Estrategia", "Denominación"
        };
        String[] identifiers_EN = {null,
            "Strategy", "Description"
        };

        String[] cols = null;

            cols = identifiers_EN;

        DynamicTableModel model = new DynamicTableModel(200, cols);
        
        for(int i = 0; i < model.getRowCount();i++){
            model.setCellEditable(i, 1, false);
            model.setCellEditable(i, 2, false);
        }
        
        tableStrategies.setModel(model);
        tableStrategies.setSelectedRowsList(selectedRows);
        
        avoidSelectColumn0();
        setColumnsWidths();
        setTableButtonListener();
        resetSelection();
        

    }

    private void resetSelection() {
        tableStrategies.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (tableStrategies.columnAtPoint(e.getPoint()) != 0) {
                    selectedRows.clear();
                }
            }

        });
    }

    private void avoidSelectColumn0() {
        tableStrategies.getColumnModel().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedColumn = tableStrategies.getSelectedColumn();
                if (selectedColumn == 0) {
                    // Si se selecciona la columna 0, cambiamos la selección a la columna 1
                    tableStrategies.changeSelection(tableStrategies.getSelectedRow(), 1, false, false);
                }
            }
        });
    }
    ArrayList<Integer> selectedRows = new ArrayList<>();

    private void setTableButtonListener() {
        tableStrategies.addTableButtonListener(new TableButtonListener() {
            @Override
            public void actionPerformed(int row) {

                if (selectedRows.contains(row)) {
                    selectedRows.remove(Integer.valueOf(row));
                } else {
                    selectedRows.add(row);
                }
                tableStrategies.clearSelection();
                for (int selectedRow : selectedRows) {
                    tableStrategies.addRowSelectionInterval(selectedRow, selectedRow);
                    tableStrategies.addColumnSelectionInterval(1, tableStrategies.getColumnCount() - 1);
                }
                tableStrategies.getDefaultEditor(Object.class).stopCellEditing();

            }
        });
    }
    private void setColumnsWidths() {
        
        tableStrategies.getColumnModel().getColumn(1).setMinWidth(20);
        tableStrategies.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableStrategies.getColumnModel().getColumn(2).setMinWidth(100);
        tableStrategies.getColumnModel().getColumn(2).setPreferredWidth(310);
    
    }
    private void validRowsCount() {

        int cant = 0;
        int datos = tableStrategies.getRowCount();

        for (int i = 0; i < datos; i++) {

            if (tableStrategies.getValueAt(i, 1) != null && tableStrategies.getValueAt(i, 2) != null) {
                cant += 1;
            }

        }
        labelConteoRegistro.setText(cant + " " + NOT.msg(NOT.ENTRIES_FOUND));

    }
    
    public void loadStrategiesList() {

        DynamicTableModel model =  (DynamicTableModel) tableStrategies.getModel();
        
        try {
            String query = "SELECT STRATEGYCODE, STRATEGYDESCRIPTION FROM "
                    +DatabaseTables.MAINTENANCE_STRATEGIES.tableName();
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = conexion.prepareStatement(query);
            
            st.executeQuery();
            
            model.clearTable();
            
            ResultSet rs = st.getResultSet();
            
            while(rs.next()){
                
                String code = rs.getString(1);
                String description = rs.getString(2);

                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 1) == null && model.getValueAt(i, 2) == null) {
                        model.setValueAt(code, i, 1);
                        model.setValueAt(description, i, 2);
                        break;
                    }
                }

                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceStrategies.class.getName()).log(Level.SEVERE, null, ex);
        }

        validRowsCount();
    }
    
    private void buttonNewEntries() {
        buttonNewEntries.addActionListener((e) -> {

            if (!MaintStratPerms.canCreate(username)) {
                new SystemMessages(NOT.msg(NOT.ACCESS_DENIED), TypeMessage.WARNING);
                return;
            }
            strategyCreate();

        });
    }
    private void buttonDetails() {
        buttonDetails.addActionListener(e -> {
            int selectedRow = tableStrategies.getSelectedRow();
            if (selectedRow != -1) {
                Object code = tableStrategies.getModel().getValueAt(selectedRow, 1);
                if (code != null) {
                    if (!code.toString().isEmpty()) {
                        if (!MaintStratPerms.canView(username)) {
                            new SystemMessages(NOT.msg(NOT.ACCESS_DENIED), TypeMessage.WARNING);
                            return;
                        }
                        strategyDetails(code.toString());
                    }

                }
            }

        });
    }
    private void buttonChange() {
        buttonChange.addActionListener(e -> {

            int selectedRow = tableStrategies.getSelectedRow();

            if (selectedRow != -1) {
                Object code = tableStrategies.getModel().getValueAt(selectedRow, 1);
                if (code != null) {
                    if (!code.toString().isEmpty()) {
                     
                        if (!MaintStratPerms.canChange(username)) {
                            new SystemMessages(NOT.msg(NOT.ACCESS_DENIED), TypeMessage.WARNING);
                            return;
                        }
                        strategyChange(code.toString());

                    }
                }
            }
        });
    }

    private void strategyCreate() {
        
        StrategyHeader ne = new StrategyHeader();
        ne.setPanelAnterior(this);
        ne.getSaveButton().setEnabled(false);
        ne.setTaskType(StrategyHeader.Task.CREATE);

        PanelLoader.loadPanel(ne, mainContainerPanel);
    
    }
    private void strategyDetails(String strategyCode) {

        StrategySQLDataRetriever sr = new StrategySQLDataRetriever();
        sr.getHeaderData(strategyCode);

        StrategyHeader ne = new StrategyHeader();
        ne.setPanelAnterior(this);
        ne.getSaveButton().setEnabled(false);
        
        ne.setTaskType(StrategyHeader.Task.VIEW);
        
        ne.getStrategyCodeField().setText(sr.getStrategyCode());
        ne.getStrategyCodeField().setEditable(false);
        
        ne.getStrategyDescriptionField().setText(sr.getStrategyDescription());
        ne.getStrategyDescriptionField().setEditable(false);
        
        ne.getStrategyUnitField().setText(sr.getStrategyUnit());
        ne.getStrategyUnitField().setEditable(false);
        
        ne.getApertureHorizonField().setText(String.valueOf(sr.getApertureHorizon()));
        ne.getApertureHorizonField().setEditable(false);
        
        ne.getDelayFactorInConclusionField().setText(String.valueOf(sr.getDelayFactorDelayedConclusion()));
        ne.getDelayFactorInConclusionField().setEditable(false);
        
        ne.getToleranceOnLateCompletionField().setText(String.valueOf(sr.getToleranceOnLateCompletion()));
        ne.getToleranceOnLateCompletionField().setEditable(false);
        
        ne.getDelayFactorInConclusionField2().setText(String.valueOf(sr.getDelayFactorEarlyConclusion()));
        ne.getDelayFactorInConclusionField2().setEditable(false);
        
        ne.getToleranceOnLateCompletionField2().setText(String.valueOf(sr.getToleranceOnEarlyCompletion()));
        ne.getToleranceOnLateCompletionField2().setEditable(false);
        
        PanelLoader.loadPanel(ne, mainContainerPanel);
    }
    private void strategyChange(String strategyCode) {
        
        StrategySQLDataRetriever sr = new StrategySQLDataRetriever();
        sr.getHeaderData(strategyCode);

        StrategyHeader ne = new StrategyHeader();
        ne.setPanelAnterior(this);
        ne.getSaveButton().setEnabled(true);

        ne.setTaskType(StrategyHeader.Task.CHANGE);

        ne.getStrategyCodeField().setText(sr.getStrategyCode());
        ne.getStrategyCodeField().setEditable(false);

        ne.getStrategyDescriptionField().setText(sr.getStrategyDescription());
        ne.getStrategyDescriptionField().setEditable(true);

        ne.getStrategyUnitField().setText(sr.getStrategyUnit());
        ne.getStrategyUnitField().setEditable(false);

        ne.getApertureHorizonField().setText(String.valueOf(sr.getApertureHorizon()));
        ne.getApertureHorizonField().setEditable(true);

        ne.getDelayFactorInConclusionField().setText(String.valueOf(sr.getDelayFactorDelayedConclusion()));
        ne.getDelayFactorInConclusionField().setEditable(true);

        ne.getToleranceOnLateCompletionField().setText(String.valueOf(sr.getToleranceOnLateCompletion()));
        ne.getToleranceOnLateCompletionField().setEditable(true);

        ne.getDelayFactorInConclusionField2().setText(String.valueOf(sr.getDelayFactorEarlyConclusion()));
        ne.getDelayFactorInConclusionField2().setEditable(true);

        ne.getToleranceOnLateCompletionField2().setText(String.valueOf(sr.getToleranceOnEarlyCompletion()));
        ne.getToleranceOnLateCompletionField2().setEditable(true);

        PanelLoader.loadPanel(ne, mainContainerPanel);
    }

   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        buttonExit = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        buttonProceed = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        buttonDetails = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        buttonNewEntries = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        buttonDelete = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        buttonChange = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        buttonPosition = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        labelConteoRegistro = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableStrategies = new com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelBotonera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotonera.setColor1(new java.awt.Color(202, 216, 237));
        panelBotonera.setColor2(new java.awt.Color(202, 216, 237));

        buttonExit.setBackground(new java.awt.Color(226, 210, 144));
        buttonExit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        buttonProceed.setBackground(new java.awt.Color(226, 210, 144));
        buttonProceed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonProceed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N

        buttonDetails.setBackground(new java.awt.Color(226, 210, 144));
        buttonDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/view_lens.png"))); // NOI18N

        buttonNewEntries.setBackground(new java.awt.Color(226, 210, 144));
        buttonNewEntries.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonNewEntries.setText("New entries");
        buttonNewEntries.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        buttonDelete.setBackground(new java.awt.Color(226, 210, 144));
        buttonDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/delete_red.png"))); // NOI18N

        buttonChange.setBackground(new java.awt.Color(226, 210, 144));
        buttonChange.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/modify_pencil .png"))); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(buttonProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(buttonNewEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(buttonDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(buttonChange, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 589, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonChange, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNewEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        buttonPosition.setBackground(new java.awt.Color(226, 210, 144));
        buttonPosition.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonPosition.setText("Position");
        buttonPosition.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        buttonPosition.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelConteoRegistro.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        labelConteoRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        tableStrategies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableStrategies.setColorColumnas(new java.awt.Color(255, 255, 255));
        tableStrategies.setGridColor(new java.awt.Color(145, 145, 145));
        tableStrategies.setShowGrid(true);
        tableStrategies.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(tableStrategies);

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(buttonPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(labelConteoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(555, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(buttonPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelConteoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonChange;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonDelete;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonDetails;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonExit;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonNewEntries;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonPosition;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonProceed;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelConteoRegistro;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons tableStrategies;
    // End of variables declaration//GEN-END:variables
 
    
    private JPanel panelAnterior;
    public void setPanelAnterior(JPanel panel){
        panelAnterior = panel;
    }
    
    private void exitButton() {
        buttonExit.addActionListener((e) -> {
            PanelLoader.loadPanel(treeMenus, mainContainerPanel);
        });
    }  
    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }
    
}
