package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.form;

import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.dependencies.InjectDependency;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.ChartOfAccountFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.ChartOfAccountSaveController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.model.ComboItem;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.ChartOfAccountController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.util.ChartOfAccountDTOInjector;
import com.simplecore.erp.client.services.base.AbstractFormPanel;
import com.simplecore.erp.client.services.base.AbstractSaverController;
import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChartOfAccountFormPanel extends AbstractFormPanel {
    
    private int accountModelID;
    private ActiveSession activeSession;
    private String accountModelName;
    private String accountModelDescription;
    private String chartOfAccountCode;
    private String chartOfAccountName;
    private ChartOfAccountComponentEnabler stateController;
    private ChartOfAccountDTO chartDTO;

    @InjectDependency
    private ChartOfAccountFormState chartOfAccountState;

    @InjectDependency
    private ChartOfAccountSaveController saverController;

    //Constructor used for new charts
    public ChartOfAccountFormPanel(int accountModelID,
            String accountModelName,
            String accountModelDescription, 
            String chartOfAccountCode,
            String chartOfAccountName,
            OperationType operationType) {
        super(operationType);
        initComponents();
        this.accountModelID = accountModelID;
        this.accountModelName = accountModelName;
        this.accountModelDescription = accountModelDescription;
        this.chartOfAccountCode = chartOfAccountCode;
        this.chartOfAccountName = chartOfAccountName;
        this.stateController = new ChartOfAccountComponentEnabler(this);
    }
    
    //Contructor used for change
    public ChartOfAccountFormPanel(ChartOfAccountDTO chartDTO,
            OperationType operationType){
        super(operationType);
        initComponents();
        this.chartDTO = chartDTO;
        this.stateController = new ChartOfAccountComponentEnabler(this);
    }

    private void setChartOfAccountTexts(){
        charOfAccountCodeTxtF.setText(chartOfAccountCode);
        chartOfAccountNameTxtF.setText(chartOfAccountName);
    }
    private void setModelTexts(){
        modelAccountNameValuelb.setText(accountModelName);
        modelAccountDescriptionLb.setText(accountModelDescription);
    }
    private void setAuthorText(){
        chartCreatedByTxtF.setText(activeSession.getUsername());
    }
    
    // ----- Exposed UI Components for Data Handler -----
    public int modelId(){return accountModelID;};
    public JTextField getCharOfAccountCodeTF(){return charOfAccountCodeTxtF;};
    public JTextField getChartOfAccountNameTF(){return chartOfAccountNameTxtF;};
    public JLabel getModelAccountNameValuelb(){return modelAccountNameValuelb;};
    public JLabel getModelAccountDescriptionLb(){return modelAccountDescriptionLb;};
    public MatchCode getCurrencyCodeMatchCode(){return currencyCodeMatchCode;}
    public MatchCode getCountryCodeMatchCode(){return countryCodeMatchCode;};
    public JTextField getBusinessTypeTF(){return businessTypeTxtF;};
    public JTextField getBusinessClassificationTF(){return industryClassificationTxtF;};
    public JTextField getFiscalYearTF(){return fiscalYearTxtF;};
    public JDateChooser getFiscalStartDateChooser(){return fiscalStartDateChooser;};
    public JDateChooser getFiscalEndDateChooser(){return fiscalEndDateChooser;};
    public JComboBox<ComboItem> getAccountingStandardCombo(){return accountingStandardCombo;};
    public JComboBox<ComboItem> getChartOfAccountStatusCombo(){return chartStatusCombo;};
    public MatchCode getTaxSchemaMatchCode(){return taxSchemaMatchCode;};
    public JLabel getTaxSchemaNamelb(){return taxSchemaNamelb;};  
    public JTextField getVersionTagTF(){return versionTagTxtF;};
    public JCheckBox getChartMultiCurrencyCheckbox(){return chartMultiCurrencyCheckbox;};
    public JTextField getChartOfAccountNotes(){return chartOfAccountNotesTxtF;};
    public JTextArea getChartOfAccountDescription(){return chartOfAccountDescriptionTextArea;};
    public JLabel getCountryNameLb(){return countryNameLb;};
    public JLabel getCurrencyNameLb(){return currencyNameLb;};
    public JTextField getChartCreatedByTF(){return chartCreatedByTxtF;};
    public JTextField getChartCreatedAtTF(){return chartCreatedAtTxtF;};
    public JTextField getChartUpdatedByTF(){return chartUpdatedByTxtF;};
    public JTextField getChartUpdatedAtTF(){return chartUpdatedAtTxtF;};
    public String getSysTransactionCode(){return transactionCode;};
    public ChartOfAccountDTO getChartDTO(){return chartDTO;};

    public SystemMessages notificator(){return notificator;};

    
    @Override
    protected void initGeneralController(OperationType operationType, ObjectOutputStream output, ObjectInputStream input, ActiveSession session,String transactionCode) {
        // Primero inicializar los servicios y controles, como combos, campos, etc.
        var chartController = new ChartOfAccountController(this, operationType, output, input, session);
        chartController.initialize();
        chartController.consumeDependencies();

        // Ahora inyectar los valores del DTO después de que los servicios y controles estén listos.
        if (chartDTO != null) {
            new ChartOfAccountDTOInjector(this).inject(chartDTO);
        } else {
            setChartOfAccountTexts();
            setModelTexts();
            setAuthorText();
        }
    }

    @Override
    protected void stateComponentsOnCreate() {
        stateController.applyCreateMode();
    }

    @Override
    protected void stateComponentsOnChange() {
        stateController.applyModifyMode();
    }

    @Override
    protected void stateComponentsOnView() {
        stateController.applyViewMode();
    }

    @Override
    protected FormState provideFormState() {
        return chartOfAccountState;
    }

    @Override
    protected AbstractSaverController provideSaveController() {
        return saveController;
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
        charOfAccountFieldsTabbedPane = new javax.swing.JTabbedPane();
        scrollGeneralSettings = new javax.swing.JScrollPane();
        generalSettingPanel = new javax.swing.JPanel();
        regionalPanel = new corex.suite.JPanelRoundedGradient();
        titlePanel = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        regionalBusinessTitleLb = new javax.swing.JLabel();
        currencyCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        currencyCodeLb = new corex.suite.JLabelHQUnderlined();
        currencyNameLb = new javax.swing.JLabel();
        countryCodeLb = new corex.suite.JLabelHQUnderlined();
        countryCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        businessTypeLb = new corex.suite.JLabelHQUnderlined();
        businessTypeTxtF = new javax.swing.JTextField();
        industryClassificationLb = new corex.suite.JLabelHQUnderlined();
        industryClassificationTxtF = new javax.swing.JTextField();
        countryNameLb = new javax.swing.JLabel();
        fiscalPanel = new corex.suite.JPanelRoundedGradient();
        fiscalPanelTitle = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        jLabel7 = new javax.swing.JLabel();
        fiscalStartDateLb = new corex.suite.JLabelHQUnderlined();
        accountingStandardLb = new corex.suite.JLabelHQUnderlined();
        fiscalYearLb = new corex.suite.JLabelHQUnderlined();
        fiscalYearTxtF = new javax.swing.JTextField();
        fiscalStartDateChooser = new com.toedter.calendar.JDateChooser();
        fiscalEndDateLb = new corex.suite.JLabelHQUnderlined();
        fiscalEndDateChooser = new com.toedter.calendar.JDateChooser();
        accountingStandardCombo = new javax.swing.JComboBox<>();
        scrollConfigurations = new javax.swing.JScrollPane();
        configurationPanel = new javax.swing.JPanel();
        configurationStatusPanel = new corex.suite.JPanelRoundedGradient();
        panelGradient35 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        configurationTitlte = new javax.swing.JLabel();
        chartStatusLb = new corex.suite.JLabelHQUnderlined();
        chartStatusCombo = new javax.swing.JComboBox<>();
        chartMultiCurrencyCheckbox = new javax.swing.JCheckBox();
        taxSchemaLb = new corex.suite.JLabelHQUnderlined();
        versionTagLb = new corex.suite.JLabelHQUnderlined();
        versionTagTxtF = new javax.swing.JTextField();
        taxSchemaMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        taxSchemaNamelb = new javax.swing.JLabel();
        documentationPanel = new corex.suite.JPanelRoundedGradient();
        panelGradient36 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        documentationTitleLb = new javax.swing.JLabel();
        notesLb = new corex.suite.JLabelHQUnderlined();
        scrollDescription = new javax.swing.JScrollPane();
        chartOfAccountDescriptionTextArea = new javax.swing.JTextArea();
        chartOfAccountNotesTxtF = new javax.swing.JTextField();
        chartDescriptionLb = new corex.suite.JLabelHQUnderlined();
        scrollAudit = new javax.swing.JScrollPane();
        auditInformationPanel = new javax.swing.JPanel();
        auditInformationChildPanel = new corex.suite.JPanelRoundedGradient();
        auditInformationTitlePanel = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        auditInformationTitleLb = new javax.swing.JLabel();
        chartCreatedAtLb = new corex.suite.JLabelHQUnderlined();
        chartCreatedByLb = new corex.suite.JLabelHQUnderlined();
        chartCreatedByTxtF = new javax.swing.JTextField();
        chartCreatedAtTxtF = new javax.swing.JTextField();
        updatedAtLb = new corex.suite.JLabelHQUnderlined();
        chartUpdatedAtTxtF = new javax.swing.JTextField();
        chartUpdatedByLb = new corex.suite.JLabelHQUnderlined();
        chartUpdatedByTxtF = new javax.swing.JTextField();
        chartOfAccountCodeLb = new corex.suite.JLabelHQUnderlined();
        charOfAccountCodeTxtF = new javax.swing.JTextField();
        modelAccountNameLb = new corex.suite.JLabelHQUnderlined();
        nameChartofCodeLb = new corex.suite.JLabelHQUnderlined();
        chartOfAccountNameTxtF = new javax.swing.JTextField();
        modelAccountNameValuelb = new javax.swing.JLabel();
        modelAccountDescriptionLb = new javax.swing.JLabel();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Chart Of Account");
        moduleTitleLabel.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1030, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
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
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(873, Short.MAX_VALUE))
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

        charOfAccountFieldsTabbedPane.setBackground(new java.awt.Color(202, 216, 237));
        charOfAccountFieldsTabbedPane.setForeground(new java.awt.Color(102, 102, 102));
        charOfAccountFieldsTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        charOfAccountFieldsTabbedPane.setFont(new java.awt.Font("IBM Plex Sans Medium", 1, 13)); // NOI18N

        scrollGeneralSettings.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        generalSettingPanel.setBackground(new java.awt.Color(238, 244, 254));
        generalSettingPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        regionalPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        regionalPanel.setColor1(new java.awt.Color(247, 247, 255));
        regionalPanel.setColor2(new java.awt.Color(247, 247, 255));
        regionalPanel.setPreferredSize(new java.awt.Dimension(800, 163));

        regionalBusinessTitleLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        regionalBusinessTitleLb.setText("Regional and Business Information");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addComponent(regionalBusinessTitleLb)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(regionalBusinessTitleLb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        currencyCodeMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        currencyCodeLb.setText("Currency Code");
        currencyCodeLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        currencyNameLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        countryCodeLb.setText("Country Code");
        countryCodeLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        countryCodeMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        businessTypeLb.setText("Business Type");
        businessTypeLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        businessTypeTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        industryClassificationLb.setText("Insutry Classification");
        industryClassificationLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        industryClassificationTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        countryNameLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        javax.swing.GroupLayout regionalPanelLayout = new javax.swing.GroupLayout(regionalPanel);
        regionalPanel.setLayout(regionalPanelLayout);
        regionalPanelLayout.setHorizontalGroup(
            regionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(regionalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regionalPanelLayout.createSequentialGroup()
                        .addComponent(currencyCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(currencyCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currencyNameLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(regionalPanelLayout.createSequentialGroup()
                        .addGroup(regionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(regionalPanelLayout.createSequentialGroup()
                                .addComponent(businessTypeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(businessTypeTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(regionalPanelLayout.createSequentialGroup()
                                .addComponent(industryClassificationLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(industryClassificationTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 385, Short.MAX_VALUE))
                    .addGroup(regionalPanelLayout.createSequentialGroup()
                        .addComponent(countryCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(countryCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(countryNameLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        regionalPanelLayout.setVerticalGroup(
            regionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regionalPanelLayout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(regionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(currencyCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(regionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(countryCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(regionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(businessTypeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(businessTypeTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(regionalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(industryClassificationLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(industryClassificationTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        fiscalPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        fiscalPanel.setColor1(new java.awt.Color(247, 247, 255));
        fiscalPanel.setColor2(new java.awt.Color(247, 247, 255));
        fiscalPanel.setPreferredSize(new java.awt.Dimension(800, 132));

        jLabel7.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        jLabel7.setText("Fiscal Period and Accounting Standards");

        javax.swing.GroupLayout fiscalPanelTitleLayout = new javax.swing.GroupLayout(fiscalPanelTitle);
        fiscalPanelTitle.setLayout(fiscalPanelTitleLayout);
        fiscalPanelTitleLayout.setHorizontalGroup(
            fiscalPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fiscalPanelTitleLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        fiscalPanelTitleLayout.setVerticalGroup(
            fiscalPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        fiscalStartDateLb.setText("Fiscal start date");
        fiscalStartDateLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        accountingStandardLb.setText("Accounting standard");
        accountingStandardLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalYearLb.setText("Fiscal year");
        fiscalYearLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalYearTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalStartDateChooser.setDateFormatString("yyyy.MM.dd HH:mm:ss");
        fiscalStartDateChooser.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalEndDateLb.setText("Fiscal end date");
        fiscalEndDateLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalEndDateChooser.setDateFormatString("yyyy.MM.dd HH:mm:ss");
        fiscalEndDateChooser.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        accountingStandardCombo.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        javax.swing.GroupLayout fiscalPanelLayout = new javax.swing.GroupLayout(fiscalPanel);
        fiscalPanel.setLayout(fiscalPanelLayout);
        fiscalPanelLayout.setHorizontalGroup(
            fiscalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fiscalPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(fiscalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fiscalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fiscalStartDateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalYearLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountingStandardLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(fiscalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fiscalStartDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(fiscalYearTxtF)
                    .addComponent(accountingStandardCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(fiscalEndDateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(fiscalEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        fiscalPanelLayout.setVerticalGroup(
            fiscalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fiscalPanelLayout.createSequentialGroup()
                .addComponent(fiscalPanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fiscalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fiscalYearLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalYearTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(fiscalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(fiscalStartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalEndDateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalStartDateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(fiscalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(accountingStandardLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountingStandardCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout generalSettingPanelLayout = new javax.swing.GroupLayout(generalSettingPanel);
        generalSettingPanel.setLayout(generalSettingPanelLayout);
        generalSettingPanelLayout.setHorizontalGroup(
            generalSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalSettingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(regionalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        generalSettingPanelLayout.setVerticalGroup(
            generalSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalSettingPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(regionalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fiscalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        scrollGeneralSettings.setViewportView(generalSettingPanel);

        charOfAccountFieldsTabbedPane.addTab("General Settings", scrollGeneralSettings);

        scrollConfigurations.setToolTipText("");
        scrollConfigurations.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        configurationPanel.setBackground(new java.awt.Color(238, 244, 254));
        configurationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        configurationStatusPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        configurationStatusPanel.setColor1(new java.awt.Color(247, 247, 255));
        configurationStatusPanel.setColor2(new java.awt.Color(247, 247, 255));
        configurationStatusPanel.setPreferredSize(new java.awt.Dimension(800, 114));

        configurationTitlte.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        configurationTitlte.setText("Configuration and Status");

        javax.swing.GroupLayout panelGradient35Layout = new javax.swing.GroupLayout(panelGradient35);
        panelGradient35.setLayout(panelGradient35Layout);
        panelGradient35Layout.setHorizontalGroup(
            panelGradient35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient35Layout.createSequentialGroup()
                .addComponent(configurationTitlte)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelGradient35Layout.setVerticalGroup(
            panelGradient35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(configurationTitlte, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        chartStatusLb.setText("Status");
        chartStatusLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartStatusCombo.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartMultiCurrencyCheckbox.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        chartMultiCurrencyCheckbox.setText("Multi - currency");

        taxSchemaLb.setText("Tax Schema");
        taxSchemaLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        versionTagLb.setText("Version tag");
        versionTagLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        versionTagTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        taxSchemaMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        taxSchemaNamelb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        javax.swing.GroupLayout configurationStatusPanelLayout = new javax.swing.GroupLayout(configurationStatusPanel);
        configurationStatusPanel.setLayout(configurationStatusPanelLayout);
        configurationStatusPanelLayout.setHorizontalGroup(
            configurationStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradient35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(configurationStatusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configurationStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(configurationStatusPanelLayout.createSequentialGroup()
                        .addGroup(configurationStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chartStatusLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(taxSchemaLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(configurationStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chartStatusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(configurationStatusPanelLayout.createSequentialGroup()
                                .addComponent(taxSchemaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(taxSchemaNamelb, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(configurationStatusPanelLayout.createSequentialGroup()
                        .addComponent(versionTagLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(versionTagTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(chartMultiCurrencyCheckbox)))
                .addGap(19, 19, Short.MAX_VALUE))
        );
        configurationStatusPanelLayout.setVerticalGroup(
            configurationStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configurationStatusPanelLayout.createSequentialGroup()
                .addComponent(panelGradient35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configurationStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chartStatusLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartStatusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(configurationStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(taxSchemaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taxSchemaLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taxSchemaNamelb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(configurationStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(configurationStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(versionTagTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chartMultiCurrencyCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(versionTagLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        documentationPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        documentationPanel.setColor1(new java.awt.Color(247, 247, 255));
        documentationPanel.setColor2(new java.awt.Color(247, 247, 255));
        documentationPanel.setPreferredSize(new java.awt.Dimension(800, 212));

        documentationTitleLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        documentationTitleLb.setText(" Documentation");

        javax.swing.GroupLayout panelGradient36Layout = new javax.swing.GroupLayout(panelGradient36);
        panelGradient36.setLayout(panelGradient36Layout);
        panelGradient36Layout.setHorizontalGroup(
            panelGradient36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient36Layout.createSequentialGroup()
                .addComponent(documentationTitleLb)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelGradient36Layout.setVerticalGroup(
            panelGradient36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(documentationTitleLb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        notesLb.setText("Notes");
        notesLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartOfAccountDescriptionTextArea.setColumns(20);
        chartOfAccountDescriptionTextArea.setFont(new java.awt.Font("IBM Plex Sans Light", 0, 13)); // NOI18N
        chartOfAccountDescriptionTextArea.setLineWrap(true);
        chartOfAccountDescriptionTextArea.setRows(5);
        chartOfAccountDescriptionTextArea.setWrapStyleWord(true);
        scrollDescription.setViewportView(chartOfAccountDescriptionTextArea);

        chartOfAccountNotesTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartDescriptionLb.setText("Description");
        chartDescriptionLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        javax.swing.GroupLayout documentationPanelLayout = new javax.swing.GroupLayout(documentationPanel);
        documentationPanel.setLayout(documentationPanelLayout);
        documentationPanelLayout.setHorizontalGroup(
            documentationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradient36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(documentationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(documentationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notesLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartDescriptionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(documentationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addComponent(chartOfAccountNotesTxtF))
                .addContainerGap())
        );
        documentationPanelLayout.setVerticalGroup(
            documentationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, documentationPanelLayout.createSequentialGroup()
                .addComponent(panelGradient36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(documentationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(notesLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartOfAccountNotesTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(documentationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartDescriptionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout configurationPanelLayout = new javax.swing.GroupLayout(configurationPanel);
        configurationPanel.setLayout(configurationPanelLayout);
        configurationPanelLayout.setHorizontalGroup(
            configurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configurationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(configurationStatusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(documentationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        configurationPanelLayout.setVerticalGroup(
            configurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configurationPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(configurationStatusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(documentationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        scrollConfigurations.setViewportView(configurationPanel);

        charOfAccountFieldsTabbedPane.addTab("Configuration & Details", scrollConfigurations);

        scrollAudit.setToolTipText("");
        scrollAudit.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        auditInformationPanel.setBackground(new java.awt.Color(238, 244, 254));
        auditInformationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        auditInformationChildPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        auditInformationChildPanel.setColor1(new java.awt.Color(247, 247, 255));
        auditInformationChildPanel.setColor2(new java.awt.Color(247, 247, 255));
        auditInformationChildPanel.setPreferredSize(new java.awt.Dimension(800, 114));

        auditInformationTitleLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        auditInformationTitleLb.setText("Audit Information");

        javax.swing.GroupLayout auditInformationTitlePanelLayout = new javax.swing.GroupLayout(auditInformationTitlePanel);
        auditInformationTitlePanel.setLayout(auditInformationTitlePanelLayout);
        auditInformationTitlePanelLayout.setHorizontalGroup(
            auditInformationTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(auditInformationTitlePanelLayout.createSequentialGroup()
                .addComponent(auditInformationTitleLb)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        auditInformationTitlePanelLayout.setVerticalGroup(
            auditInformationTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(auditInformationTitleLb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        chartCreatedAtLb.setText("Created at");
        chartCreatedAtLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartCreatedByLb.setText("Created by");
        chartCreatedByLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartCreatedByTxtF.setEditable(false);
        chartCreatedByTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartCreatedAtTxtF.setEditable(false);
        chartCreatedAtTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        updatedAtLb.setText("Updated at");
        updatedAtLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartUpdatedAtTxtF.setEditable(false);
        chartUpdatedAtTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartUpdatedByLb.setText("Updated by");
        chartUpdatedByLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartUpdatedByTxtF.setEditable(false);
        chartUpdatedByTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        javax.swing.GroupLayout auditInformationChildPanelLayout = new javax.swing.GroupLayout(auditInformationChildPanel);
        auditInformationChildPanel.setLayout(auditInformationChildPanelLayout);
        auditInformationChildPanelLayout.setHorizontalGroup(
            auditInformationChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(auditInformationTitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(auditInformationChildPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(auditInformationChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartCreatedAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartCreatedByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(auditInformationChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartCreatedAtTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartCreatedByTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(auditInformationChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(auditInformationChildPanelLayout.createSequentialGroup()
                        .addComponent(chartUpdatedByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(chartUpdatedByTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(auditInformationChildPanelLayout.createSequentialGroup()
                        .addComponent(updatedAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(chartUpdatedAtTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        auditInformationChildPanelLayout.setVerticalGroup(
            auditInformationChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, auditInformationChildPanelLayout.createSequentialGroup()
                .addComponent(auditInformationTitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(auditInformationChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chartCreatedAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartCreatedAtTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartUpdatedAtTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(auditInformationChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(auditInformationChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chartUpdatedByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chartUpdatedByTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(auditInformationChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chartCreatedByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chartCreatedByTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout auditInformationPanelLayout = new javax.swing.GroupLayout(auditInformationPanel);
        auditInformationPanel.setLayout(auditInformationPanelLayout);
        auditInformationPanelLayout.setHorizontalGroup(
            auditInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, auditInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(auditInformationChildPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        auditInformationPanelLayout.setVerticalGroup(
            auditInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(auditInformationPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(auditInformationChildPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
        );

        scrollAudit.setViewportView(auditInformationPanel);

        charOfAccountFieldsTabbedPane.addTab("Audit & History", scrollAudit);

        chartOfAccountCodeLb.setText("Code");
        chartOfAccountCodeLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        charOfAccountCodeTxtF.setEditable(false);
        charOfAccountCodeTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        modelAccountNameLb.setText("Model of accunt");
        modelAccountNameLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        nameChartofCodeLb.setText("Name of chart");
        nameChartofCodeLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartOfAccountNameTxtF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        modelAccountNameValuelb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        modelAccountDescriptionLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addComponent(chartOfAccountCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(charOfAccountCodeTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameChartofCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modelAccountNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chartOfAccountNameTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bodyPanelLayout.createSequentialGroup()
                                        .addComponent(modelAccountNameValuelb, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(modelAccountDescriptionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(charOfAccountFieldsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chartOfAccountCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(charOfAccountCodeTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameChartofCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartOfAccountNameTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelAccountNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelAccountNameValuelb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelAccountDescriptionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(charOfAccountFieldsTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<ComboItem> accountingStandardCombo;
    private corex.suite.JLabelHQUnderlined accountingStandardLb;
    private corex.suite.JPanelRoundedGradient auditInformationChildPanel;
    private javax.swing.JPanel auditInformationPanel;
    private javax.swing.JLabel auditInformationTitleLb;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 auditInformationTitlePanel;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private corex.suite.JLabelHQUnderlined businessTypeLb;
    private javax.swing.JTextField businessTypeTxtF;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private javax.swing.JTextField charOfAccountCodeTxtF;
    public static javax.swing.JTabbedPane charOfAccountFieldsTabbedPane;
    private corex.suite.JLabelHQUnderlined chartCreatedAtLb;
    private javax.swing.JTextField chartCreatedAtTxtF;
    private corex.suite.JLabelHQUnderlined chartCreatedByLb;
    private javax.swing.JTextField chartCreatedByTxtF;
    private corex.suite.JLabelHQUnderlined chartDescriptionLb;
    private javax.swing.JCheckBox chartMultiCurrencyCheckbox;
    private corex.suite.JLabelHQUnderlined chartOfAccountCodeLb;
    private javax.swing.JTextArea chartOfAccountDescriptionTextArea;
    private javax.swing.JTextField chartOfAccountNameTxtF;
    private javax.swing.JTextField chartOfAccountNotesTxtF;
    private javax.swing.JComboBox<ComboItem> chartStatusCombo;
    private corex.suite.JLabelHQUnderlined chartStatusLb;
    private javax.swing.JTextField chartUpdatedAtTxtF;
    private corex.suite.JLabelHQUnderlined chartUpdatedByLb;
    private javax.swing.JTextField chartUpdatedByTxtF;
    private javax.swing.JPanel configurationPanel;
    private corex.suite.JPanelRoundedGradient configurationStatusPanel;
    private javax.swing.JLabel configurationTitlte;
    private corex.suite.JLabelHQUnderlined countryCodeLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode countryCodeMatchCode;
    private javax.swing.JLabel countryNameLb;
    private corex.suite.JLabelHQUnderlined currencyCodeLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode currencyCodeMatchCode;
    private javax.swing.JLabel currencyNameLb;
    private corex.suite.JPanelRoundedGradient documentationPanel;
    private javax.swing.JLabel documentationTitleLb;
    private com.toedter.calendar.JDateChooser fiscalEndDateChooser;
    private corex.suite.JLabelHQUnderlined fiscalEndDateLb;
    private corex.suite.JPanelRoundedGradient fiscalPanel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 fiscalPanelTitle;
    private com.toedter.calendar.JDateChooser fiscalStartDateChooser;
    private corex.suite.JLabelHQUnderlined fiscalStartDateLb;
    private corex.suite.JLabelHQUnderlined fiscalYearLb;
    private javax.swing.JTextField fiscalYearTxtF;
    private javax.swing.JPanel generalSettingPanel;
    private corex.suite.JLabelHQUnderlined industryClassificationLb;
    private javax.swing.JTextField industryClassificationTxtF;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel modelAccountDescriptionLb;
    private corex.suite.JLabelHQUnderlined modelAccountNameLb;
    private javax.swing.JLabel modelAccountNameValuelb;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private corex.suite.JLabelHQUnderlined nameChartofCodeLb;
    private corex.suite.JLabelHQUnderlined notesLb;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient35;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient36;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private javax.swing.JLabel regionalBusinessTitleLb;
    private corex.suite.JPanelRoundedGradient regionalPanel;
    private javax.swing.JScrollPane scrollAudit;
    private javax.swing.JScrollPane scrollConfigurations;
    private javax.swing.JScrollPane scrollDescription;
    private javax.swing.JScrollPane scrollGeneralSettings;
    private corex.suite.JLabelHQUnderlined taxSchemaLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode taxSchemaMatchCode;
    private javax.swing.JLabel taxSchemaNamelb;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 titlePanel;
    private javax.swing.JPanel topPanel;
    private corex.suite.JLabelHQUnderlined updatedAtLb;
    private corex.suite.JLabelHQUnderlined versionTagLb;
    private javax.swing.JTextField versionTagTxtF;
    // End of variables declaration//GEN-END:variables
}
