package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form;

import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.controllers.FinancialAccountSetupController;
import com.simplecore.erp.client.services.base.AbstractFormPanel;
import com.simplecore.erp.client.services.base.AbstractSaverController;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import corex.suite.CorpTable;
import java.awt.Dimension;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;

public class FinancialAccountSetup extends AbstractFormPanel {

    private FICompanyDTO companyDTO;
    
    public FinancialAccountSetup(OperationType operationType, FICompanyDTO companyDTO) {
        super(operationType);
        initComponents();
        this.companyDTO = companyDTO;
    }

    @Override
    protected void initGeneralController(OperationType operationType, ObjectOutputStream output, ObjectInputStream input, ActiveSession session, String transactionCOde) {
        FinancialAccountSetupController controller = new FinancialAccountSetupController(this, operationType, output, input, session);
        controller.initialize();
        controller.injectFICompanyDTO(companyDTO);
    }

    @Override
    protected void stateComponentsOnCreate() {

    }

    @Override
    protected void stateComponentsOnChange() {

    }

    @Override
    protected void stateComponentsOnView() {

    }

    @Override
    protected FormState provideFormState() {
        return null;
    }

    @Override
    protected AbstractSaverController provideSaveController() {
        return null;
    }
    
    public CorpTable getAccountsTable(){return corpTable;};
    public JButton getSetupButton(){return proceedButton;};

   // public AccountSetupContainerPanel getAccountListPanel(){return accountListPanel;};
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        panelTitle = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        moduleTitleLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        buttonsPanel = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        jToolBar1 = new javax.swing.JToolBar();
        proceedButton = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        bodyPanel = new corex.suite.JPanelRoundedGradient();
        accountSetupContainerPanel1 = new com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form.AccountSetupContainerPanel();
        corpTable = new corex.suite.CorpTable();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Financial Accounting Account Setup");
        moduleTitleLabel.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 963, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        topPanel.add(panelTitle);

        buttonsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonsPanel.setColor1(new java.awt.Color(206, 223, 239));
        buttonsPanel.setColor2(new java.awt.Color(206, 223, 239));
        buttonsPanel.setMaximumSize(null);
        buttonsPanel.setMinimumSize(new java.awt.Dimension(149, 35));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(149, 35));
        buttonsPanel.setVerifyInputWhenFocusTarget(false);

        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        proceedButton.setIcon(new CustomSVGIcon("/icons/svg/green_flag.svg",new Dimension(24,24)));
        proceedButton.setFocusable(false);
        proceedButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        proceedButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(proceedButton);

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(939, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        topPanel.add(buttonsPanel);

        add(topPanel, java.awt.BorderLayout.NORTH);

        bodyPanel.setColor1(new java.awt.Color(247, 247, 255));
        bodyPanel.setColor2(new java.awt.Color(239, 243, 247));

        corpTable.setBackground(new java.awt.Color(238, 244, 254));
        corpTable.setButtonTableWidth(16);
        corpTable.setCellNonEditableOneCellSelection(new java.awt.Color(255, 200, 43));

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(corpTable, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(accountSetupContainerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(accountSetupContainerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(corpTable, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form.AccountSetupContainerPanel accountSetupContainerPanel1;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private corex.suite.CorpTable corpTable;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
