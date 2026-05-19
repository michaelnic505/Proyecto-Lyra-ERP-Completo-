package com.simplecore.erp.modules.logistics.plantmaintenance.strategies.legacy.news;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.utils.documentfilters.DocumentFilterNumeric;
import com.simplecore.erp.utils.documentfilters.DocumentFilterVarchar;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui.UnitOfMeasureList;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.UnitsOfMeasurement;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class StrategyHeader extends javax.swing.JPanel {
    
    public StrategyHeader() {
        
        initComponents();
        addEvents();
        
    }

    private void addEvents() {
        
        exitButton();
        buttonSaveUpdates();
        proceedToPackages();
        
        openMeasUnitList();
        setMeasUnitDescriptionOnEnter();
        setTextFieldsLimits();
        
    }
    
    private void openMeasUnitList() {
        strategyUnit.getButton().addActionListener((e) -> {
            
            UnitOfMeasureList um = new UnitOfMeasureList(getSuperFrame());
            um.setCampos(strategyUnit.getTextBox(), mUnitDescripcionLB);
            um.setVisible(true);
            
        });
    }
    private void setMeasUnitDescriptionOnEnter() {
        
        strategyUnit.getTextBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    
                    if (strategyUnit.getTextBox().getText().isEmpty()) {
                        strategyUnit.getTextBox().setText(null);
                        mUnitDescripcionLB.setText(null);
                        return;
                    }
                    
                    setMeasUnitDescription();
                    
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    strategyUnit.getTextBox().setText(null);
                    mUnitDescripcionLB.setText(null);
                }
            }
            
        });
    }

    private void setMeasUnitDescription() {
        
        String u = strategyUnit.getTextBox().getText();
        String findU = UnitsOfMeasurement.getUnitOfMeasByCode(u);
        
        if (findU != null) {
            strategyUnit.getTextBox().setText(findU);
            mUnitDescripcionLB.setText(UnitsOfMeasurement.getDescriptionByCode(findU));
        } else {
            strategyUnit.getTextBox().setText(null);
            mUnitDescripcionLB.setText(null);
            
        }
    }
    private void setTextFieldsLimits() {
        
        ((AbstractDocument) estrategyTB.getDocument()).setDocumentFilter(new DocumentFilterVarchar(11).setUpperCase(true));
        ((AbstractDocument) strategyDescriptionTB.getDocument()).setDocumentFilter(new DocumentFilterVarchar(45).setUpperCase(true));
        ((AbstractDocument) strategyUnit.getTextBox().getDocument()).setDocumentFilter(new DocumentFilterVarchar(5));
        
        ((AbstractDocument) aperHorizonTB.getDocument()).setDocumentFilter(new DocumentFilterNumeric(3));
        ((AbstractDocument) delayFactorDelayedConclusionTB.getDocument()).setDocumentFilter(new DocumentFilterNumeric(3));
        ((AbstractDocument) toleranceOnLateConclusionTB.getDocument()).setDocumentFilter(new DocumentFilterNumeric(3));
        ((AbstractDocument) delayFactorOnEarlyConclusionTB.getDocument()).setDocumentFilter(new DocumentFilterNumeric(3));
        ((AbstractDocument) toleranceOnEarlyConclusionTB.getDocument()).setDocumentFilter(new DocumentFilterNumeric(3));
        
    }

    private void buttonSaveUpdates(){
        saveButton.addActionListener(e->{
            
            if(hasAllFieldsFilled()){
                saveHeaderUpdates();
            }
            
        });
    }
    private void saveHeaderUpdates(){
        
        String strategyCode = estrategyTB.getText().trim();
        String strategyDecription = strategyDescriptionTB.getText().trim();
        int aperturaHorizon = Integer.parseInt(aperHorizonTB.getText().trim());
        int delayFactorDelayedConclusion = Integer.parseInt(delayFactorDelayedConclusionTB.getText().trim());
        int toleranceOnDelayedConclusion = Integer.parseInt(toleranceOnLateConclusionTB.getText().trim());
        int delayFactorEarlyConclusion = Integer.parseInt(delayFactorOnEarlyConclusionTB.getText().trim());
        int toleranceOnEarlyConclusion = Integer.parseInt(toleranceOnEarlyConclusionTB.getText().trim());
        
        
        MaintenanceStrategyHeader msh = new MaintenanceStrategyHeader();
        msh.setStrategyCode(strategyCode);
        msh.setStrategyDescription(strategyDecription);
        msh.setApertureHorizon(aperturaHorizon);
        msh.setDelayFactorDelayedConclusion(delayFactorDelayedConclusion);
        msh.setToleranceOnLateCompletion(toleranceOnDelayedConclusion);
        msh.setDelayFactorEarlyConclusion(delayFactorEarlyConclusion);
        msh.setToleranceOnEarlyCompletion(toleranceOnEarlyConclusion);
        
        msh.executeHeaderUpdateSQL();
        
        new SystemMessages(NOT.msg(NOT.DATA_SAVED), TypeMessage.SUCCESS);
    }
    
    private boolean hasAllFieldsFilled() {

        JTextField[] fields = new JTextField[]{
            strategyDescriptionTB,
            aperHorizonTB,
            delayFactorDelayedConclusionTB,
            toleranceOnLateConclusionTB,
            delayFactorOnEarlyConclusionTB,
            toleranceOnEarlyConclusionTB
        };

        boolean foundEmptyField = false;

        for (JTextField field : fields) {
          
            if (field.getText().isEmpty()) {
                new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.WARNING);
                field.requestFocus();
                foundEmptyField = true;
                break;
            }
        
        }
        if (foundEmptyField) {
            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.WARNING);
            return false;
        }

        return true;
    }


    enum Task {
        CREATE,
        CHANGE,
        VIEW
    }
    private Task typeTask;
    public void setTaskType(Task type) {
        this.typeTask = type;
    }

    
    private void proceedToPackages() {
        
        buttonProceed.addActionListener((e) -> {
            
            if (mUnitDescripcionLB.getText() == null) {
                setMeasUnitDescription();
            }
            
            if (estrategyTB.getText().isEmpty()) {
                new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                estrategyTB.requestFocus();
                return;
            }
            if (estrategyTB.getText().contains(" ")) {
                new SystemMessages(NOT.msg(NOT.CONTAINS_SPACES), TypeMessage.WARNING);
                estrategyTB.requestFocus();
                return;
            }
            if (strategyDescriptionTB.getText().isEmpty()) {
                new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                strategyDescriptionTB.requestFocus();
                return;
            }
            if (strategyUnit.getTextBox().getText().isEmpty()) {
                new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                strategyUnit.getTextBox().requestFocus();
                return;
            }
            
            
            switch (typeTask) {
                case CREATE -> {
                    strategyCreate();
                }
                case CHANGE -> {
                    strategyChange();
                }
                case VIEW -> {
                    strategyView();
                }
            }

        });
        
    }
    private void strategyCreate() {
        if (!strategyCodeExists(estrategyTB.getText())) {
            
            estrategyTB.setEditable(false);
            strategyDescriptionTB.setEditable(false);
            
            StrategyPackagesTable pk = new StrategyPackagesTable();
            pk.setPanelAnterior(this);
            
            pk.setTaskType(StrategyPackagesTable.Task.CREATE);
            
            pk.getStrategyTF().setText(estrategyTB.getText());
            pk.getStrategyDescriptionTB().setText(strategyDescriptionTB.getText());
            pk.setStrategyUnit(strategyUnit.getTextBox().getText());
            
            pk.setStrategyCode(estrategyTB.getText());
            pk.setStrategyDescription(strategyDescriptionTB.getText());
            
            pk.setStrategyUnit(strategyUnit.getTextBox().getText());
            
            strategyUnit.getTextBox().setEditable(false);
            strategyUnit.getButton().setEnabled(false);
            
            if (aperHorizonTB.getText().isEmpty()) {
                aperHorizonTB.setText("80");
            }
            aperHorizonTB.setEditable(false);
            pk.setApertureHorizon(Integer.parseInt(aperHorizonTB.getText()));
            
            if (delayFactorDelayedConclusionTB.getText().isEmpty()) {
                delayFactorDelayedConclusionTB.setText("100");
            }
            delayFactorDelayedConclusionTB.setEditable(false);
            
            pk.setDelayFactorDelayedConclusion(Integer.parseInt(delayFactorDelayedConclusionTB.getText()));
            if (toleranceOnLateConclusionTB.getText().isEmpty()) {
                toleranceOnLateConclusionTB.setText("0");
            }
            toleranceOnLateConclusionTB.setEditable(false);
            
            pk.setToleranceOnLateCompletion(Integer.parseInt(toleranceOnLateConclusionTB.getText()));
            if (delayFactorOnEarlyConclusionTB.getText().isEmpty()) {
                delayFactorOnEarlyConclusionTB.setText("100");
            }
            delayFactorOnEarlyConclusionTB.setEditable(false);
            
            pk.setDelayFactorEarlyConclusion(Integer.parseInt(delayFactorOnEarlyConclusionTB.getText()));
            if (toleranceOnEarlyConclusionTB.getText().isEmpty()) {
                toleranceOnEarlyConclusionTB.setText("0");
            }
            toleranceOnEarlyConclusionTB.setEditable(false);
            
            pk.setToleranceOnEarlyCompletion(Integer.parseInt(toleranceOnEarlyConclusionTB.getText()));
            
            pk.disableEditingForReservedFields();
            
            PanelLoader.loadPanel(pk, mainContainerPanel);
            
        } else {
            new SystemMessages(NOT.msg(NOT.STRATEGY_ALREADY_EXISTS), TypeMessage.ERROR);
            
        }
    }
    private void strategyChange() {
        
        StrategyPackagesTable pk = new StrategyPackagesTable();
        pk.setPanelAnterior(this);
        
        pk.setTaskType(StrategyPackagesTable.Task.CHANGE);
        
        pk.getSaveButton().setEnabled(true);

        pk.getStrategyTF().setText(estrategyTB.getText());
        pk.getStrategyDescriptionTB().setText(strategyDescriptionTB.getText());
        pk.setStrategyCode(estrategyTB.getText());

        pk.loadPackagesList();
        pk.setStrategyUnit(strategyUnit.getTextBox().getText());
        
        pk.disableEditingForReservedFields();
    
        PanelLoader.loadPanel(pk, mainContainerPanel);
    }
    private void strategyView() {
        
        StrategyPackagesTable pk = new StrategyPackagesTable();
        pk.setPanelAnterior(this);
        
        pk.setTaskType(StrategyPackagesTable.Task.VIEW);
        
        pk.getSaveButton().setEnabled(false);
        
        pk.getStrategyTF().setText(estrategyTB.getText());
        pk.getStrategyDescriptionTB().setText(strategyDescriptionTB.getText());
        pk.setStrategyCode(estrategyTB.getText());
        
        pk.loadPackagesList();

        pk.setStrategyUnit(strategyUnit.getTextBox().getText());
        
        pk.disableEditingForReservedFields();
        pk.disableCellEditing();
        
        
        
        strategyUnit.getTextBox().setEditable(false);
        strategyUnit.getButton().setEnabled(false);
        
        aperHorizonTB.setEditable(false);
        delayFactorDelayedConclusionTB.setEditable(false);
        toleranceOnLateConclusionTB.setEditable(false);
        delayFactorOnEarlyConclusionTB.setEditable(false);
        toleranceOnEarlyConclusionTB.setEditable(false);
        
        PanelLoader.loadPanel(pk, mainContainerPanel);
        
    }

    public JButton getSaveButton(){
        return saveButton;
    }
    public JTextField getStrategyCodeField() {
        return estrategyTB;
    }
    public JTextField getStrategyDescriptionField() {
        return strategyDescriptionTB;
    }
    public JTextField getStrategyUnitField() {
        return strategyUnit.getTextBox();
    }
    public JTextField getApertureHorizonField() {
        return aperHorizonTB;
    }
    public JTextField getDelayFactorInConclusionField() {
        return delayFactorDelayedConclusionTB;
    }
    public JTextField getToleranceOnLateCompletionField() {
        return toleranceOnLateConclusionTB;
    }
    public JTextField getDelayFactorInConclusionField2() {
        return delayFactorOnEarlyConclusionTB;
    }
    public JTextField getToleranceOnLateCompletionField2() {
        return toleranceOnEarlyConclusionTB;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        buttonExit = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        buttonProceed = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        saveButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        measDocNum = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        measDocNum1 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        strategyDescriptionTB = new javax.swing.JTextField();
        measDocNum2 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        aperHorizonTB = new javax.swing.JTextField();
        measDocNum3 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        measDocNum4 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        delayFactorDelayedConclusionTB = new javax.swing.JTextField();
        measDocNum5 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        toleranceOnLateConclusionTB = new javax.swing.JTextField();
        measDocNum6 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        delayFactorOnEarlyConclusionTB = new javax.swing.JTextField();
        measDocNum7 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        toleranceOnEarlyConclusionTB = new javax.swing.JTextField();
        buttonPosition = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        estrategyTB = new javax.swing.JTextField();
        strategyUnit = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        mUnitDescripcionLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        porc = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        porc1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        porc2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        porc3 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        porc4 = new com.simplecore.erp.gui.components.labels.JLabelHQ();

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
        buttonProceed.setText("Packages");
        buttonProceed.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        saveButton.setBackground(new java.awt.Color(226, 210, 144));
        saveButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 879, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        measDocNum.setText("Strategy");
        measDocNum.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measDocNum1.setText("Description");
        measDocNum1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        strategyDescriptionTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measDocNum2.setText("Strategy unit");
        measDocNum2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        aperHorizonTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measDocNum3.setText("Aperture horizon");
        measDocNum3.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measDocNum4.setText("Delay factor in delayed conclusion");
        measDocNum4.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        delayFactorDelayedConclusionTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measDocNum5.setText("Tolerance on late completion (%)");
        measDocNum5.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        toleranceOnLateConclusionTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measDocNum6.setText("Delay factor in early conclusion");
        measDocNum6.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        delayFactorOnEarlyConclusionTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measDocNum7.setText("Tolerance on early conclusion (%)");
        measDocNum7.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        toleranceOnEarlyConclusionTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        buttonPosition.setBackground(new java.awt.Color(226, 210, 144));
        buttonPosition.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonPosition.setText("PkgSeq");
        buttonPosition.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        buttonPosition.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        estrategyTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        estrategyTB.setMinimumSize(new java.awt.Dimension(64, 22));

        mUnitDescripcionLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mUnitDescripcionLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        porc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        porc.setText("%");
        porc.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        porc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        porc1.setText("%");
        porc1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        porc2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        porc2.setText("%");
        porc2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        porc3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        porc3.setText("%");
        porc3.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        porc4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        porc4.setText("%");
        porc4.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(buttonPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoLayout.createSequentialGroup()
                                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panelFondoLayout.createSequentialGroup()
                                        .addComponent(measDocNum2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)
                                        .addComponent(strategyUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFondoLayout.createSequentialGroup()
                                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(measDocNum6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(measDocNum5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(measDocNum4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                            .addComponent(measDocNum3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(measDocNum7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(delayFactorOnEarlyConclusionTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, Short.MAX_VALUE)
                                                .addComponent(toleranceOnLateConclusionTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(delayFactorDelayedConclusionTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(aperHorizonTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                            .addComponent(toleranceOnEarlyConclusionTB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(porc1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                        .addComponent(porc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(porc2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(porc3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(porc4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(mUnitDescripcionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelFondoLayout.createSequentialGroup()
                                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(measDocNum, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(measDocNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estrategyTB, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(strategyDescriptionTB, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(448, 448, 448))))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(measDocNum, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estrategyTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(measDocNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(strategyDescriptionTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(measDocNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(strategyUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mUnitDescripcionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(measDocNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aperHorizonTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(measDocNum4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delayFactorDelayedConclusionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porc1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(measDocNum5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toleranceOnLateConclusionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porc2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(measDocNum6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delayFactorOnEarlyConclusionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porc3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(measDocNum7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toleranceOnEarlyConclusionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porc4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
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
    private javax.swing.JTextField aperHorizonTB;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonExit;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonPosition;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonProceed;
    private javax.swing.JTextField delayFactorDelayedConclusionTB;
    private javax.swing.JTextField delayFactorOnEarlyConclusionTB;
    private javax.swing.JTextField estrategyTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ mUnitDescripcionLB;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measDocNum;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measDocNum1;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measDocNum2;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measDocNum3;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measDocNum4;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measDocNum5;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measDocNum6;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measDocNum7;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private com.simplecore.erp.gui.components.labels.JLabelHQ porc;
    private com.simplecore.erp.gui.components.labels.JLabelHQ porc1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ porc2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ porc3;
    private com.simplecore.erp.gui.components.labels.JLabelHQ porc4;
    private com.simplecore.erp.gui.components.labels.JButtonHQ saveButton;
    private javax.swing.JTextField strategyDescriptionTB;
    protected static com.simplecore.erp.gui.components.searchbox.JSearchBox strategyUnit;
    private javax.swing.JTextField toleranceOnEarlyConclusionTB;
    private javax.swing.JTextField toleranceOnLateConclusionTB;
    // End of variables declaration//GEN-END:variables

    private JPanel panelAnterior;
    public void setPanelAnterior(JPanel panel) {
        panelAnterior = panel;
    }
    public JPanel getPanelAnterior() {
        return panelAnterior;
    }

    private void exitButton() {
        buttonExit.addActionListener((e) -> {
            exit();
        });
    }
    public void exit() {
        ((MaintenanceStrategies)panelAnterior).loadStrategiesList();
        PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
    }
    
    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }
    private boolean strategyCodeExists(String strategyCode) {
        
        boolean r = false;
        try {
            
            String query = "SELECT STRATEGYCODE FROM MAINTENANCE_STRATEGIES WHERE STRATEGYCODE = ?";
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = conexion.prepareStatement(query);
            
            st.setString(1, strategyCode);
            st.executeQuery();
            
            ResultSet rs = st.getResultSet();
            r = rs.next();
            
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(StrategyHeader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
}
