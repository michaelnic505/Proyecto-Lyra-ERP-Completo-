package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.assignment;

import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountClassesRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountClassesRetrieveResponse;
import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class AccountRangeAssignment extends JPanel implements TransactionPanel{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final SystemMessages notificator;

    public AccountRangeAssignment() {
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.notificator = new SystemMessages();
        initComponents();
        initEvents();
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
        setButtonCommandConfigure();
    }
    private String[] getTableColumnNames(){
        String id = tableTranslator.getTranslation(TableKeys.ACCOUNT_CLASSES_TC_ID.getKey());
        String classCode = tableTranslator.getTranslation(TableKeys.ACCOUNT_CLASSES_TC_CLASS_CODE.getKey());
        String className = tableTranslator.getTranslation(TableKeys.ACCOUNT_CLASSES_TC_CLASS_NAME.getKey());
        String classKey = tableTranslator.getTranslation(TableKeys.ACCOUNT_CLASSES_TC_CLASS_KEY.getKey());
        
        return new String[]{id,classCode,className,classKey};
    }
    
    private String[][] requestAccountClassesList(){
        try {
            output.writeObject(new AccountClassesRetrieveRequest(activeSession.getSessionId(),activeSession.getUserId()));
            output.flush();
            Object response = input.readObject();
            if(response instanceof AccountClassesRetrieveResponse accountClassesResponse){
                return accountClassesResponse.getAccountClasses();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountRangeAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    private String descriptionClass(String key) {
        return tableTranslator.getTranslation(key);
    }

    private void setButtonCommandConfigure(){
        proceedButton.setIcon(new CustomSVGIcon("/icons/svg/green_flag.svg",new Dimension(24,24)));
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
        generalDataFieldPanel1 = new corex.suite.JPanelRoundedGradient();
        validityDateLabel1 = new corex.suite.JLabelHQUnderlined();
        validityMatchCode1 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        currencyLb1 = new corex.suite.JLabelHQUnderlined();
        costingTypeLabel1 = new corex.suite.JLabelHQUnderlined();
        currencyMatchCode1 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costingMatchCode1 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costingTypeLabel2 = new corex.suite.JLabelHQUnderlined();
        currencyMatchCode2 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costingMatchCode2 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        validityDateLabel2 = new corex.suite.JLabelHQUnderlined();
        validityMatchCode2 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        currencyLb2 = new corex.suite.JLabelHQUnderlined();
        costingTypeLabel3 = new corex.suite.JLabelHQUnderlined();
        currencyMatchCode3 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costingMatchCode3 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costingTypeLabel4 = new corex.suite.JLabelHQUnderlined();
        currencyMatchCode4 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costingMatchCode4 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        validityDateLabel3 = new corex.suite.JLabelHQUnderlined();
        validityMatchCode3 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        validityDateLabel4 = new corex.suite.JLabelHQUnderlined();
        currencyLb3 = new corex.suite.JLabelHQUnderlined();
        validityMatchCode4 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        currencyLb4 = new corex.suite.JLabelHQUnderlined();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelHQFraming1 = new corex.suite.JLabelHQFraming();
        companyNameTextField = new javax.swing.JTextField();
        companyNameLabel = new corex.suite.JLabelHQUnderlined();
        companyIdTextField = new javax.swing.JTextField();
        companyIdLabel = new corex.suite.JLabelHQUnderlined();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moduleTitleLabel.setText("Create Model - Account Subclasses");
        moduleTitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
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
                .addContainerGap(766, Short.MAX_VALUE))
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

        generalDataFieldPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        generalDataFieldPanel1.setColor1(new java.awt.Color(247, 247, 255));
        generalDataFieldPanel1.setColor2(new java.awt.Color(247, 247, 255));

        validityDateLabel1.setText("From");
        validityDateLabel1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        currencyLb1.setText("From");
        currencyLb1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costingTypeLabel1.setText("From");
        costingTypeLabel1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costingTypeLabel2.setText("From");
        costingTypeLabel2.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        validityDateLabel2.setText("From");
        validityDateLabel2.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        currencyLb2.setText("From");
        currencyLb2.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costingTypeLabel3.setText("To");
        costingTypeLabel3.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costingTypeLabel4.setText("To");
        costingTypeLabel4.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        validityDateLabel3.setText("To");
        validityDateLabel3.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        validityDateLabel4.setText("To");
        validityDateLabel4.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        currencyLb3.setText("To");
        currencyLb3.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        currencyLb4.setText("To");
        currencyLb4.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel1.setText("Assets");

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel2.setText("Liabilities");

        jLabel3.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel3.setText("Equity");

        jLabel4.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel4.setText("Revenue");

        jLabel5.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel5.setText("Costs");

        jLabel6.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel6.setText("Expenses");

        jLabelHQFraming1.setText(" Account Types ");
        jLabelHQFraming1.setColorBordes(new java.awt.Color(140, 168, 192));
        jLabelHQFraming1.setColorRelleno(new java.awt.Color(206, 223, 239));
        jLabelHQFraming1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        javax.swing.GroupLayout generalDataFieldPanel1Layout = new javax.swing.GroupLayout(generalDataFieldPanel1);
        generalDataFieldPanel1.setLayout(generalDataFieldPanel1Layout);
        generalDataFieldPanel1Layout.setHorizontalGroup(
            generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalDataFieldPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(costingTypeLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validityDateLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(currencyLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currencyMatchCode2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costingMatchCode2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validityMatchCode2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(costingTypeLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validityDateLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(currencyLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currencyMatchCode1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costingMatchCode1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validityMatchCode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(costingTypeLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validityDateLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(currencyLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currencyMatchCode4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costingMatchCode4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validityMatchCode3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(costingTypeLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validityDateLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(currencyLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currencyMatchCode3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costingMatchCode3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validityMatchCode4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(71, 71, 71))
            .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                .addComponent(jLabelHQFraming1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        generalDataFieldPanel1Layout.setVerticalGroup(
            generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                .addComponent(jLabelHQFraming1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(currencyLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyMatchCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyMatchCode3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(costingTypeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingMatchCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingTypeLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingMatchCode3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(validityMatchCode4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validityMatchCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validityDateLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validityDateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(currencyLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyMatchCode2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyMatchCode4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(costingTypeLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingMatchCode2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingTypeLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingMatchCode4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validityDateLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validityMatchCode2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validityDateLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validityMatchCode3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        companyNameTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        companyNameTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        companyNameLabel.setText("Name");
        companyNameLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        companyIdTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        companyIdTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        companyIdLabel.setText("Company ID");
        companyIdLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generalDataFieldPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(companyNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(companyIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(companyIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(companyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(companyIdTextField)
                    .addComponent(companyIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(generalDataFieldPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private corex.suite.JLabelHQUnderlined companyIdLabel;
    private javax.swing.JTextField companyIdTextField;
    private corex.suite.JLabelHQUnderlined companyNameLabel;
    private javax.swing.JTextField companyNameTextField;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costingMatchCode1;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costingMatchCode2;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costingMatchCode3;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costingMatchCode4;
    private corex.suite.JLabelHQUnderlined costingTypeLabel1;
    private corex.suite.JLabelHQUnderlined costingTypeLabel2;
    private corex.suite.JLabelHQUnderlined costingTypeLabel3;
    private corex.suite.JLabelHQUnderlined costingTypeLabel4;
    private corex.suite.JLabelHQUnderlined currencyLb1;
    private corex.suite.JLabelHQUnderlined currencyLb2;
    private corex.suite.JLabelHQUnderlined currencyLb3;
    private corex.suite.JLabelHQUnderlined currencyLb4;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode currencyMatchCode1;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode currencyMatchCode2;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode currencyMatchCode3;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode currencyMatchCode4;
    private corex.suite.JPanelRoundedGradient generalDataFieldPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private corex.suite.JLabelHQFraming jLabelHQFraming1;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private javax.swing.JPanel topPanel;
    private corex.suite.JLabelHQUnderlined validityDateLabel1;
    private corex.suite.JLabelHQUnderlined validityDateLabel2;
    private corex.suite.JLabelHQUnderlined validityDateLabel3;
    private corex.suite.JLabelHQUnderlined validityDateLabel4;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode validityMatchCode1;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode validityMatchCode2;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode validityMatchCode3;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode validityMatchCode4;
    // End of variables declaration//GEN-END:variables

}
