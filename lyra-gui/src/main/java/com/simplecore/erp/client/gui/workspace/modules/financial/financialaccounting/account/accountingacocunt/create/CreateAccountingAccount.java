package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.create;

import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JPanel;
import com.simplecore.erp.client.controllers.workspace.TaskPanel;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.models.AcModComboItem;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterVarchar;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AbstractDocument;

public class CreateAccountingAccount extends JPanel implements TransactionPanel, TaskPanel {

    private final TranslationHelper tableTranslator;
    private final SystemMessages notificator;
    private final TranslationHelper windowTranslator;
    
    private final int modelId;
    private final String modelName;
    private final String modelDescription;

    public CreateAccountingAccount(int modelId,String modelName,String modelDescription) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelDescription = modelDescription;
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.notificator = new SystemMessages();
        initComponents();
        initEvents();
    }
    
    private ActiveSession activeSession;
    private AccountingCombosController accountingCombos;
    private AccountNumbersManager numberManager;
    private PreparedAccountingAccount preparedAccount;
    private PreparedAccountCreateRequest preparedCreateRequest;
    private String transactionCode;
    @Override
    public void initialize(String transactionCode,ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.transactionCode = transactionCode;
        this.activeSession = session;
        this.accountingCombos = new AccountingCombosController(modelId, activeSession, output, input);
        this.accountingCombos.initializeCombos(accountClassCombo, subclassCombo, parentAccountCombo, accountNumberTf);
        this.numberManager = new AccountNumbersManager(activeSession, output, input);
        this.numberManager.initializeCombos(subclassCombo, parentAccountCombo);
        this.preparedAccount = new PreparedAccountingAccount(accountNameTf, descriptionTextArea, activeSession);
        this.preparedAccount.initializeCombos(subclassCombo, parentAccountCombo);
        this.preparedCreateRequest = new PreparedAccountCreateRequest(activeSession, modelName, modelDescription, output, input);
        this.preparedCreateRequest.initializeComponents(accountNumberTf, accountModelMatchCode, modelDescriptionLabel, 
                createdByTextField, statusCombo, createdAtTextField, isClosedCheckbox);
    }

    private void initEvents() {
        parameterizeTextFields();
        initializeStatusCombo();
    }

    private void sendAccountingAccountCreateRequest() {
        try {
            String accountNumber = numberManager.getNextAccountingAccountNumber();
            if (accountNumber != null) {
                try {
                    if (!areAllComponentsFilled()) {
                        return;
                    }
                    AccountingAccountDTO newAccount = preparedAccount.prepareAccountingAccounting(accountNumber);
                    preparedCreateRequest.sendAccountingAccountCreateRequest(accountNumber, newAccount);
                    disablingComponents();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(CreateAccountingAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            enabligComponents();
            notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.ACTION_COULD_NOT_BE_EXECUTED));
        }
    }

    private void initializeStatusCombo(){
        statusCombo.addItem("");
        for(AccountStatus status: AccountStatus.values()){
            statusCombo.addItem(status.name());
        }
        statusCombo.setEnabled(false);
    }
    
    private void enabligComponents() {
        accountClassCombo.setEnabled(true);
        subclassCombo.setEnabled(true);
        parentAccountCombo.setEnabled(true);
        accountNameTf.setEditable(true);
        descriptionTextArea.setEditable(true);
    }

    private void disablingComponents() {
        accountClassCombo.setEnabled(false);
        subclassCombo.setEnabled(false);
        parentAccountCombo.setEnabled(false);
        accountNameTf.setEditable(false);
        descriptionTextArea.setEditable(false);
    }

    private boolean areAllComponentsFilled(){
        if(accountNameTf.getText().trim().isEmpty()){
            accountNameTf.requestFocus();
            return false;
        }
        if(descriptionTextArea.getText().trim().isEmpty()){
            descriptionTextArea.requestFocus();
            return false;
        }
        if(subclassCombo.getSelectedItem().toString().trim().isEmpty()){
            subclassCombo.requestFocus();
            return false;
        }
        return true;
    }

    private void parameterizeTextFields(){
        ((AbstractDocument)accountNameTf.getDocument()).setDocumentFilter(new DocumentFilterVarchar(75));
        ((AbstractDocument)descriptionTextArea.getDocument()).setDocumentFilter(new DocumentFilterVarchar(255));
        accountModelMatchCode.getTextField().setEditable(false);
        statusCombo.setEnabled(false);
    }

    private ActionListener setSaveButtonListener() {
        return (ActionEvent e) -> {
            sendAccountingAccountCreateRequest();
        };
    }
    
    @Override
    public boolean isTaskRunning() {
        return true;
    }
    @Override
    public ActionListener getOnTaskComplete() {
        return setSaveButtonListener();
    }
    @Override
    public String getTransactionCode() {
        return transactionCode;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        panelTitle = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        moduleTitleLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        buttonsPanel = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        toolbars = new javax.swing.JToolBar();
        proceedButton = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        bodyPanel = new corex.suite.JPanelRoundedGradient();
        scrollPane = new javax.swing.JScrollPane();
        panelScroll = new corex.suite.JPanelRoundedGradient();
        hierarchyPanel = new corex.suite.JPanelRoundedGradient();
        subclassLabel = new corex.suite.JLabelHQUnderlined();
        subclassCombo = new javax.swing.JComboBox<>();
        accountClassCombo = new javax.swing.JComboBox<>();
        accountClassLabel = new corex.suite.JLabelHQUnderlined();
        panelGradient32 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        jLabel2 = new javax.swing.JLabel();
        parentAccountLabel = new corex.suite.JLabelHQUnderlined();
        parentAccountCombo = new javax.swing.JComboBox<>();
        detailsPane = new corex.suite.JPanelRoundedGradient();
        accountNameLb = new corex.suite.JLabelHQUnderlined();
        accountNumberLb = new corex.suite.JLabelHQUnderlined();
        accountNameTf = new javax.swing.JTextField();
        accountNumberTf = new javax.swing.JTextField();
        scrollPaneTextArea = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        accountDescriptionLb = new corex.suite.JLabelHQUnderlined();
        statusCombo = new javax.swing.JComboBox<>();
        accountModelMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        accountModelLabel = new corex.suite.JLabelHQUnderlined();
        accountStatusLb = new corex.suite.JLabelHQUnderlined();
        detailTitlePane = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        detailsLabel = new javax.swing.JLabel();
        isClosedCheckbox = new javax.swing.JCheckBox();
        modelDescriptionLabel = new javax.swing.JLabel();
        authorPanel = new corex.suite.JPanelRoundedGradient();
        authorTitlePane = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        authorLabel = new javax.swing.JLabel();
        createdByLb = new corex.suite.JLabelHQUnderlined();
        createdByTextField = new javax.swing.JTextField();
        createdAtLb = new corex.suite.JLabelHQUnderlined();
        createdAtTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Adding Accounting Account");
        moduleTitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1022, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
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

        toolbars.setRollover(true);
        toolbars.setOpaque(false);

        proceedButton.setFocusable(false);
        proceedButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        proceedButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbars.add(proceedButton);

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(toolbars, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(865, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(toolbars, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        topPanel.add(buttonsPanel);

        add(topPanel, java.awt.BorderLayout.NORTH);

        bodyPanel.setColor1(new java.awt.Color(247, 247, 255));
        bodyPanel.setColor2(new java.awt.Color(206, 223, 239));

        panelScroll.setColor1(new java.awt.Color(247, 247, 255));
        panelScroll.setColor2(new java.awt.Color(247, 247, 255));

        hierarchyPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        hierarchyPanel.setColor1(new java.awt.Color(247, 247, 255));
        hierarchyPanel.setColor2(new java.awt.Color(247, 247, 255));

        subclassLabel.setText("Subclass");
        subclassLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        subclassCombo.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountClassCombo.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountClassLabel.setText("Class");
        accountClassLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel2.setText("Hierarchy");

        javax.swing.GroupLayout panelGradient32Layout = new javax.swing.GroupLayout(panelGradient32);
        panelGradient32.setLayout(panelGradient32Layout);
        panelGradient32Layout.setHorizontalGroup(
            panelGradient32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient32Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelGradient32Layout.setVerticalGroup(
            panelGradient32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        parentAccountLabel.setText("Parent Account");
        parentAccountLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        parentAccountCombo.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        javax.swing.GroupLayout hierarchyPanelLayout = new javax.swing.GroupLayout(hierarchyPanel);
        hierarchyPanel.setLayout(hierarchyPanelLayout);
        hierarchyPanelLayout.setHorizontalGroup(
            hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradient32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(hierarchyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(hierarchyPanelLayout.createSequentialGroup()
                        .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subclassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accountClassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subclassCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accountClassCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(hierarchyPanelLayout.createSequentialGroup()
                        .addComponent(parentAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(parentAccountCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        hierarchyPanelLayout.setVerticalGroup(
            hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hierarchyPanelLayout.createSequentialGroup()
                .addComponent(panelGradient32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(accountClassCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountClassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(subclassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subclassCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(parentAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parentAccountCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        detailsPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        detailsPane.setColor1(new java.awt.Color(247, 247, 255));
        detailsPane.setColor2(new java.awt.Color(247, 247, 255));

        accountNameLb.setText("Name");
        accountNameLb.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountNumberLb.setText("Account number");
        accountNumberLb.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountNameTf.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountNumberTf.setEditable(false);
        accountNumberTf.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setWrapStyleWord(true);
        scrollPaneTextArea.setViewportView(descriptionTextArea);

        accountDescriptionLb.setText("Description");
        accountDescriptionLb.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        statusCombo.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountModelMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountModelLabel.setText("Account model");
        accountModelLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountStatusLb.setText("Status");
        accountStatusLb.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        detailsLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        detailsLabel.setText("Accounting Account Details");

        javax.swing.GroupLayout detailTitlePaneLayout = new javax.swing.GroupLayout(detailTitlePane);
        detailTitlePane.setLayout(detailTitlePaneLayout);
        detailTitlePaneLayout.setHorizontalGroup(
            detailTitlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailTitlePaneLayout.createSequentialGroup()
                .addComponent(detailsLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        detailTitlePaneLayout.setVerticalGroup(
            detailTitlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(detailsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        isClosedCheckbox.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        isClosedCheckbox.setText("Is Closed");
        isClosedCheckbox.setEnabled(false);

        modelDescriptionLabel.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        javax.swing.GroupLayout detailsPaneLayout = new javax.swing.GroupLayout(detailsPane);
        detailsPane.setLayout(detailsPaneLayout);
        detailsPaneLayout.setHorizontalGroup(
            detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(detailTitlePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(detailsPaneLayout.createSequentialGroup()
                .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailsPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountStatusLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accountModelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountModelMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88)
                        .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isClosedCheckbox)
                            .addComponent(modelDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(detailsPaneLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detailsPaneLayout.createSequentialGroup()
                                .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(accountNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(accountNumberLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(accountNumberTf, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPaneTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(accountNameTf))))
                            .addComponent(accountDescriptionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        detailsPaneLayout.setVerticalGroup(
            detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPaneLayout.createSequentialGroup()
                .addComponent(detailTitlePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(accountNumberLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountNumberTf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(accountNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accountDescriptionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPaneTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(accountModelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accountModelMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(accountStatusLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isClosedCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        authorPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        authorPanel.setColor1(new java.awt.Color(247, 247, 255));
        authorPanel.setColor2(new java.awt.Color(247, 247, 255));

        authorLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        authorLabel.setText("Others...");

        javax.swing.GroupLayout authorTitlePaneLayout = new javax.swing.GroupLayout(authorTitlePane);
        authorTitlePane.setLayout(authorTitlePaneLayout);
        authorTitlePaneLayout.setHorizontalGroup(
            authorTitlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(authorTitlePaneLayout.createSequentialGroup()
                .addComponent(authorLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        authorTitlePaneLayout.setVerticalGroup(
            authorTitlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(authorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        createdByLb.setText("Created By");
        createdByLb.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        createdByTextField.setEditable(false);
        createdByTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        createdAtLb.setText("Created At");
        createdAtLb.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        createdAtTextField.setEditable(false);
        createdAtTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        javax.swing.GroupLayout authorPanelLayout = new javax.swing.GroupLayout(authorPanel);
        authorPanel.setLayout(authorPanelLayout);
        authorPanelLayout.setHorizontalGroup(
            authorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(authorTitlePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(authorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(createdByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(createdByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createdAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(createdAtTextField)
                .addContainerGap())
        );
        authorPanelLayout.setVerticalGroup(
            authorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, authorPanelLayout.createSequentialGroup()
                .addComponent(authorTitlePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(authorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(createdByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdAtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelScrollLayout = new javax.swing.GroupLayout(panelScroll);
        panelScroll.setLayout(panelScrollLayout);
        panelScrollLayout.setHorizontalGroup(
            panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScrollLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hierarchyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(detailsPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(authorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        panelScrollLayout.setVerticalGroup(
            panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelScrollLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(hierarchyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailsPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(authorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrollPane.setViewportView(panelScroll);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> accountClassCombo;
    private corex.suite.JLabelHQUnderlined accountClassLabel;
    private corex.suite.JLabelHQUnderlined accountDescriptionLb;
    private corex.suite.JLabelHQUnderlined accountModelLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode accountModelMatchCode;
    private corex.suite.JLabelHQUnderlined accountNameLb;
    private javax.swing.JTextField accountNameTf;
    private corex.suite.JLabelHQUnderlined accountNumberLb;
    private javax.swing.JTextField accountNumberTf;
    private corex.suite.JLabelHQUnderlined accountStatusLb;
    private javax.swing.JLabel authorLabel;
    private corex.suite.JPanelRoundedGradient authorPanel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 authorTitlePane;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private corex.suite.JLabelHQUnderlined createdAtLb;
    private javax.swing.JTextField createdAtTextField;
    private corex.suite.JLabelHQUnderlined createdByLb;
    private javax.swing.JTextField createdByTextField;
    private javax.swing.JTextArea descriptionTextArea;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 detailTitlePane;
    private javax.swing.JLabel detailsLabel;
    private corex.suite.JPanelRoundedGradient detailsPane;
    private corex.suite.JPanelRoundedGradient hierarchyPanel;
    private javax.swing.JCheckBox isClosedCheckbox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel modelDescriptionLabel;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient32;
    private corex.suite.JPanelRoundedGradient panelScroll;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private javax.swing.JComboBox<AcModComboItem> parentAccountCombo;
    private corex.suite.JLabelHQUnderlined parentAccountLabel;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JScrollPane scrollPaneTextArea;
    private javax.swing.JComboBox<String> statusCombo;
    private javax.swing.JComboBox<AcModComboItem> subclassCombo;
    private corex.suite.JLabelHQUnderlined subclassLabel;
    private javax.swing.JToolBar toolbars;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public FormState getFormState() {
        return null;
    }


}
