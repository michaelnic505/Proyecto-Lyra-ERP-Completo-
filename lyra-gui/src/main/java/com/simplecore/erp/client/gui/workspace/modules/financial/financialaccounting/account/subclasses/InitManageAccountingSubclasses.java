package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.subclasses;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.models.change.*;
import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.controllers.workspace.TaskPanel;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.windows.auxiliar.AuxiliarWindow;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.auxiliarwindows.AccountModelSearchWindow;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.i18n.WindowKeys;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountModelListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountModelRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountModelListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountModelRetrieveResponse;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class InitManageAccountingSubclasses extends JPanel implements TransactionPanel,TaskPanel ,RowSelectionListener{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;
    
    public InitManageAccountingSubclasses() {
        initComponents();
        initEvents();
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.notificator = new SystemMessages();
    }
    
    private String transactionCode;
    @Override
    public void initialize(String transactionCode,ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.transactionCode = transactionCode;
        this.activeSession = session;
        this.output = output;
        this.input = input;
    }

    private void initEvents() {
        matchCodeSocietyEvents();
        setButtonCommandConfigure();
    }

    private void matchCodeSocietyEvents(){
        accountModelMatchCode.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    accountModelMatchCode.getTextField().setText(null);
                    accountModelDescriptionLabel.setText(null);
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(accountModelMatchCode.getTextField().getText()==null||accountModelMatchCode.getTextField().getText().isEmpty()){
                        return;
                    }
                    String accountModelName = accountModelMatchCode.getTextField().getText().toUpperCase();
                    AccountModelRetrieveResponse accountModel = getAcountModelByName(accountModelName);
                    if (accountModel == null) {
                        setNullInFields();
                        return;
                    }
                    if (!accountModel.isFoundIt()) {
                        notificator.showErrorMsg(accountModel.getMessage() + accountModelName);
                        return;
                    }
                    setValuesInFields(accountModel);
                }
            }
        });
        accountModelMatchCode.getButton().addActionListener(e->{
            openAccountModelList();
        });
    }
    
    private void setValuesInFields(AccountModelRetrieveResponse accountModel) {
        accountModelID = accountModel.getModelId();
        accountModelName = accountModel.getModelName();
        accountModelDescription = accountModel.getModelDescription();
        accountModelSystemState = accountModel.getSystemState();
        accountModelMatchCode.getTextField().setText(accountModelName);
        accountModelDescriptionLabel.setText(accountModelDescription);
    }

    private AccountModelRetrieveResponse getAcountModelByName(String modelName) {
        try {
            String sessionId = activeSession.getSessionId();
            int userId = activeSession.getUserId();
            output.writeObject(new AccountModelRetrieveRequest(sessionId, userId, modelName));
            output.flush();
            Object response = input.readObject();
            if (response instanceof AccountModelRetrieveResponse accountModel) {
                return accountModel;
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(InitManageAccountingSubclasses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private int accountModelID;
    private String accountModelName;
    private String accountModelDescription;
    private String accountModelSystemState;
    
    @Override
    public void onRowSelected(Object[] selectedData) {
        accountModelID = (int) selectedData[0];
        accountModelName = (String) selectedData[1];
        accountModelSystemState = (String) selectedData[2];
        accountModelDescription = (String) selectedData[3];
        
        accountModelMatchCode.getTextField().setText(accountModelName);
        accountModelDescriptionLabel.setText(accountModelDescription);
    }

    List<Integer> columnsToReturn = List.of(0, 1, 2, 3);
    private void openAccountModelList() {
        AccountModelSearchWindow auxWindow = new AccountModelSearchWindow(Workspace.getFrame(), accountModelMatchCode.getButton(),
                getTableColumnsName(), getDataSource(), this, columnsToReturn);
        
        auxWindow.setWindowTitle(windowTranslator.getTranslation(WindowKeys.FI_ACCOUNT_MODEL_AUXILIAR_WINDOW_TITLE.getKey()));
        auxWindow.setTitlePane(0,windowTranslator.getTranslation(WindowKeys.FI_ACCOUNT_MODEL_AUXILIAR_WINDOW_TITLE_PANE.getKey()));
        auxWindow.setVisible(true);
    }

    private String[] getTableColumnsName() {
        String id = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_ID.getKey());
        String modelName = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_MODEL_NAME.getKey());
        String systemState = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_MODEL_STATE.getKey());
        String modelDescription = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_MODEL_DESCRIPTION.getKey());
        String createdAt = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_MODEL_CREATEDAT.getKey());
        String createdBy = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_MODEL_CREATEDBY.getKey());
        

        return new String[]{
            id,
            modelName,
            systemState,
            modelDescription,
            createdAt,
            createdBy
        };
    }
    private Object[][] getDataSource() {
        try {
            output.writeObject(new AccountModelListRetrieveRequest(activeSession.getSessionId(), activeSession.getUserId()));
            output.flush();
            Object response = input.readObject();
            if (response instanceof AccountModelListRetrieveResponse accountListResponse) {
                return accountListResponse.getDataSource();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AuxiliarWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void setButtonCommandConfigure(){
        proceedButton.setIcon(new CustomSVGIcon("/icons/svg/ok_button_icon.svg",new Dimension(24,24)));
        proceedButton.addActionListener(e->goToTaskPanel());
    }
    private void goToTaskPanel() {
        if (accountModelID == -1 || accountModelName == null) {
            setNullInFields();
            return;
        }
        setValuesInFields(getAcountModelByName(accountModelName));
        ManageAccountSubclass subclassesModel = new ManageAccountSubclass(accountModelID, accountModelName,
                accountModelDescription,accountModelSystemState);
        subclassesModel.initialize(transactionCode,activeSession, output, input);
        PanelManager.goToPanel(subclassesModel);
    }

    private void setNullInFields() {
        accountModelID = -1;
        accountModelName = null;
        accountModelDescription = null;
        accountModelSystemState = null;
        accountModelMatchCode.getTextField().requestFocus();
        accountModelMatchCode.getTextField().setText(null);
        accountModelDescriptionLabel.setText(null);
        notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
    }


    
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
        accountModelLabel = new corex.suite.JLabelHQUnderlined();
        accountModelMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        accountModelDescriptionLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Manage Accounting Subclasses Model");
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

        accountModelLabel.setText("Account Model");
        accountModelLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountModelMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        accountModelDescriptionLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accountModelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(accountModelMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accountModelDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(accountModelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(accountModelMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(accountModelDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(343, Short.MAX_VALUE))
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountModelDescriptionLabel;
    private corex.suite.JLabelHQUnderlined accountModelLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode accountModelMatchCode;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
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
