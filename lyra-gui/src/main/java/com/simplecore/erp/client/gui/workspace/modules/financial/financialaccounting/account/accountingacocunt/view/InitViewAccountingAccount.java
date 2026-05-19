package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.change.*;
import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.controllers.workspace.TaskPanel;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.JPanel;

public class InitViewAccountingAccount extends JPanel implements TransactionPanel,TaskPanel, RowSelectionListener{

    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;
    private AccountingAccountDTO accountingAccount;
    private int modelId;
    private int classId;
    
    public InitViewAccountingAccount() {
        initComponents();
        initEvents();
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.notificator = new SystemMessages();
    }
    
    private ActiveSession activeSession;
    private AccountNumberMatchController matchController;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String transactionCode;
    //lista de columnas de campos que seran recolectados al realizar selecion en el auxiliar window
    private final List<Integer> columnsToReturn = List.of(0, 1, 2, 3);
    
    @Override
    public void initialize(String transactionCode,ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.transactionCode = transactionCode;
        this.output = output;
        this.input = input;
        this.activeSession = session;
        this.matchController = new AccountNumberMatchController(accountNumberMatchCode, this, columnsToReturn);
        this.matchController.initialize(activeSession, output, input,accountDescriptionLabel);
    }

    private void initEvents() {
        setButtonCommandConfigure();
    }
    private void setButtonCommandConfigure(){
        nextButton.addActionListener(e->gotoNextPanel());
    }
    private void gotoNextPanel() {
        matchController.refreshView();
        
        accountingAccount = matchController.getAccountingAccount();
        if (accountingAccount == null) {
            notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
            return;
        }
        modelId = matchController.getModelId();
        classId = matchController.getClassId();

        ViewAccountingAccount viewAccount = new ViewAccountingAccount(modelId, classId, accountingAccount);
        viewAccount.initialize(transactionCode,activeSession, output, input);
        PanelManager.goToPanel(viewAccount);

    }

    @Override//metodo implementado para recolectar campos selecionados en el auxiliar window
    public void onRowSelected(Object[] selectedData) {
        matchController.setValuesInComponents(selectedData);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        panelTitle = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        moduleTitleLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        buttonsPanel = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        jToolBar1 = new javax.swing.JToolBar();
        nextButton = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        bodyPanel = new corex.suite.JPanelRoundedGradient();
        accountNumberMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        accountNumberLb = new corex.suite.JLabelHQUnderlined();
        accountDescriptionLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Change Accounting Account");
        moduleTitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 951, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
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

        nextButton.setIcon(new CustomSVGIcon("/icons/svg/next.svg",new Dimension(24,24)));
        nextButton.setFocusable(false);
        nextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(nextButton);

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(794, Short.MAX_VALUE))
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

        accountNumberMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountNumberLb.setText("Account Number");
        accountNumberLb.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountDescriptionLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accountNumberLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(accountNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accountDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(accountNumberLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accountNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(343, Short.MAX_VALUE))
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountDescriptionLabel;
    private corex.suite.JLabelHQUnderlined accountNumberLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode accountNumberMatchCode;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices nextButton;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isTaskRunning() {
        return false;
    }

    @Override
    public ActionListener getOnTaskComplete() {
        return null;
    }
    
    @Override
    public String getTransactionCode() {
        return transactionCode;
    }

    @Override
    public FormState getFormState() {
        return null;
    }
}
