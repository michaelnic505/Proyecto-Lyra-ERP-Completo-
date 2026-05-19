package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.create;

import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.awt.Dimension;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JPanel;

public class ControllingCompany extends JPanel implements TransactionPanel, RowSelectionListener{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;
    private final String societyClassCode;

    public ControllingCompany(String societyClassCode) {
        initComponents();
        initEvents();
        this.societyClassCode = societyClassCode;
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
        setButtonCommandConfigure();
    }

    @Override
    public void onRowSelected(Object[] selectedData) {

    }

    private String descriptionClass(String key) {
        return tableTranslator.getTranslation("society." + key + ".text");
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
        MULTITAB = new javax.swing.JTabbedPane();
        scrollGeneralData = new javax.swing.JScrollPane();
        generalDataPanel = new javax.swing.JPanel();
        generalDataFieldPanel = new corex.suite.JPanelRoundedGradient();
        validityDateLabel = new corex.suite.JLabelHQUnderlined();
        validityMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        currencyLb = new corex.suite.JLabelHQUnderlined();
        costingTypeLabel = new corex.suite.JLabelHQUnderlined();
        currencyMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costingMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollCostAccounting = new javax.swing.JScrollPane();
        costAccountingPanel = new javax.swing.JPanel();
        costAccountingFieldPanel = new corex.suite.JPanelRoundedGradient();
        defaultProfitCenterLabel = new corex.suite.JLabelHQUnderlined();
        defaultProfitCtrMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        controlCostCtrMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        controlCostCenterLabel = new corex.suite.JLabelHQUnderlined();
        costingKeyLabel = new corex.suite.JLabelHQUnderlined();
        costingKeyMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        defaultCostCenterLabel = new corex.suite.JLabelHQUnderlined();
        defaultCostCtrMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costCenterKeyLabel = new corex.suite.JLabelHQUnderlined();
        costCenterKeyMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollAssigmentDistribution = new javax.swing.JScrollPane();
        assigmentDistributionPanel = new javax.swing.JPanel();
        assignmentDistributionFieldPanel = new corex.suite.JPanelRoundedGradient();
        costDistributionStrategyMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        profitCenterAccountingAssigmentLabel = new corex.suite.JLabelHQUnderlined();
        profitCenterAccountingAssigMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        assignedCompanyCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        assignedCompanyCodeLabel = new corex.suite.JLabelHQUnderlined();
        costAssigmentMethodLabel = new corex.suite.JLabelHQUnderlined();
        costAssigmentMethodMatchCode = new javax.swing.JTextField();
        costDistributionStrategyLabel = new corex.suite.JLabelHQUnderlined();
        scrollOrderValuation = new javax.swing.JScrollPane();
        orderValuationPanel = new javax.swing.JPanel();
        orderValuationFieldPanel = new corex.suite.JPanelRoundedGradient();
        controlOrderNumberLabel = new corex.suite.JLabelHQUnderlined();
        controlOrderNumberMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costOrderNumberLabel = new corex.suite.JLabelHQUnderlined();
        costOrderNumberMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        valuationTypeLabel = new corex.suite.JLabelHQUnderlined();
        valuationTypeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        companyNameLabel = new corex.suite.JLabelHQUnderlined();
        companyNameTextField = new javax.swing.JTextField();
        companyIdLabel = new corex.suite.JLabelHQUnderlined();
        companyIdTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moduleTitleLabel.setText("Controlling Company: ");
        moduleTitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
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
                .addContainerGap(803, Short.MAX_VALUE))
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

        MULTITAB.setBackground(new java.awt.Color(202, 216, 237));
        MULTITAB.setForeground(new java.awt.Color(102, 102, 102));
        MULTITAB.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        MULTITAB.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        generalDataPanel.setBackground(new java.awt.Color(238, 244, 254));
        generalDataPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        generalDataFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        generalDataFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        generalDataFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        validityDateLabel.setText("Validity date");
        validityDateLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        currencyLb.setText("Currency");
        currencyLb.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costingTypeLabel.setText("Costing type");
        costingTypeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout generalDataFieldPanelLayout = new javax.swing.GroupLayout(generalDataFieldPanel);
        generalDataFieldPanel.setLayout(generalDataFieldPanelLayout);
        generalDataFieldPanelLayout.setHorizontalGroup(
            generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currencyLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(costingTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(validityDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currencyMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(costingMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(validityMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        generalDataFieldPanelLayout.setVerticalGroup(
            generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(currencyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(costingMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(validityMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validityDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout generalDataPanelLayout = new javax.swing.GroupLayout(generalDataPanel);
        generalDataPanel.setLayout(generalDataPanelLayout);
        generalDataPanelLayout.setHorizontalGroup(
            generalDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalDataFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        generalDataPanelLayout.setVerticalGroup(
            generalDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalDataFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(249, Short.MAX_VALUE))
        );

        scrollGeneralData.setViewportView(generalDataPanel);

        MULTITAB.addTab("General Data", scrollGeneralData);

        costAccountingPanel.setBackground(new java.awt.Color(238, 244, 254));
        costAccountingPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        costAccountingFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        costAccountingFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        costAccountingFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        defaultProfitCenterLabel.setText("Default Profit Center");
        defaultProfitCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        controlCostCenterLabel.setText("Control Cost Center");
        controlCostCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costingKeyLabel.setText("Costing Key");
        costingKeyLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        defaultCostCenterLabel.setText("Default Cost Center");
        defaultCostCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costCenterKeyLabel.setText("Cost Center Key");
        costCenterKeyLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout costAccountingFieldPanelLayout = new javax.swing.GroupLayout(costAccountingFieldPanel);
        costAccountingFieldPanel.setLayout(costAccountingFieldPanelLayout);
        costAccountingFieldPanelLayout.setHorizontalGroup(
            costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costAccountingFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(defaultProfitCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controlCostCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defaultCostCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCenterKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(defaultProfitCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controlCostCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingKeyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defaultCostCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCenterKeyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(354, Short.MAX_VALUE))
        );
        costAccountingFieldPanelLayout.setVerticalGroup(
            costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costAccountingFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(costingKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingKeyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(defaultCostCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defaultCostCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(defaultProfitCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defaultProfitCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlCostCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controlCostCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(costCenterKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCenterKeyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout costAccountingPanelLayout = new javax.swing.GroupLayout(costAccountingPanel);
        costAccountingPanel.setLayout(costAccountingPanelLayout);
        costAccountingPanelLayout.setHorizontalGroup(
            costAccountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
            .addGroup(costAccountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(costAccountingPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(costAccountingFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        costAccountingPanelLayout.setVerticalGroup(
            costAccountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
            .addGroup(costAccountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, costAccountingPanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(costAccountingFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(190, Short.MAX_VALUE)))
        );

        scrollCostAccounting.setViewportView(costAccountingPanel);

        MULTITAB.addTab("Cost Accounting", scrollCostAccounting);

        assigmentDistributionPanel.setBackground(new java.awt.Color(238, 244, 254));
        assigmentDistributionPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        assignmentDistributionFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        assignmentDistributionFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        assignmentDistributionFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        profitCenterAccountingAssigmentLabel.setText("Profit Center Accounting Assignment");
        profitCenterAccountingAssigmentLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        assignedCompanyCodeLabel.setText("Assigned Company Code");
        assignedCompanyCodeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costAssigmentMethodLabel.setText("Cost Assignment Method");
        costAssigmentMethodLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costDistributionStrategyLabel.setText("Cost Distribution Strategy");
        costDistributionStrategyLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout assignmentDistributionFieldPanelLayout = new javax.swing.GroupLayout(assignmentDistributionFieldPanel);
        assignmentDistributionFieldPanel.setLayout(assignmentDistributionFieldPanelLayout);
        assignmentDistributionFieldPanelLayout.setHorizontalGroup(
            assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assignmentDistributionFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(costAssigmentMethodLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(costDistributionStrategyLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profitCenterAccountingAssigmentLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(assignedCompanyCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(costDistributionStrategyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profitCenterAccountingAssigMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costAssigmentMethodMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assignedCompanyCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 133, 133))
        );
        assignmentDistributionFieldPanelLayout.setVerticalGroup(
            assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assignmentDistributionFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(assignedCompanyCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assignedCompanyCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costAssigmentMethodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costAssigmentMethodMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(costDistributionStrategyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costDistributionStrategyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profitCenterAccountingAssigmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profitCenterAccountingAssigMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout assigmentDistributionPanelLayout = new javax.swing.GroupLayout(assigmentDistributionPanel);
        assigmentDistributionPanel.setLayout(assigmentDistributionPanelLayout);
        assigmentDistributionPanelLayout.setHorizontalGroup(
            assigmentDistributionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, assigmentDistributionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(assignmentDistributionFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        assigmentDistributionPanelLayout.setVerticalGroup(
            assigmentDistributionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assigmentDistributionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(assignmentDistributionFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        scrollAssigmentDistribution.setViewportView(assigmentDistributionPanel);

        MULTITAB.addTab("Assignments & Distribution", scrollAssigmentDistribution);

        orderValuationPanel.setBackground(new java.awt.Color(238, 244, 254));
        orderValuationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        orderValuationFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        orderValuationFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        orderValuationFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        controlOrderNumberLabel.setText("Control Order Number");
        controlOrderNumberLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costOrderNumberLabel.setText("Cost Order Number");
        costOrderNumberLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        valuationTypeLabel.setText("Valuation Type");
        valuationTypeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout orderValuationFieldPanelLayout = new javax.swing.GroupLayout(orderValuationFieldPanel);
        orderValuationFieldPanel.setLayout(orderValuationFieldPanelLayout);
        orderValuationFieldPanelLayout.setHorizontalGroup(
            orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderValuationFieldPanelLayout.createSequentialGroup()
                        .addComponent(controlOrderNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(controlOrderNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orderValuationFieldPanelLayout.createSequentialGroup()
                        .addComponent(costOrderNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(costOrderNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orderValuationFieldPanelLayout.createSequentialGroup()
                        .addComponent(valuationTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(valuationTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        orderValuationFieldPanelLayout.setVerticalGroup(
            orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(controlOrderNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controlOrderNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(costOrderNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costOrderNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valuationTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valuationTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout orderValuationPanelLayout = new javax.swing.GroupLayout(orderValuationPanel);
        orderValuationPanel.setLayout(orderValuationPanelLayout);
        orderValuationPanelLayout.setHorizontalGroup(
            orderValuationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderValuationFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        orderValuationPanelLayout.setVerticalGroup(
            orderValuationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderValuationFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(241, Short.MAX_VALUE))
        );

        scrollOrderValuation.setViewportView(orderValuationPanel);

        MULTITAB.addTab("Orders & Valuation", scrollOrderValuation);

        companyNameLabel.setText("Name");
        companyNameLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        companyNameTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        companyNameTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        companyIdLabel.setText("Company ID");
        companyIdLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        companyIdTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        companyIdTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(companyNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(companyIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(companyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(companyIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(companyIdTextField)
                    .addComponent(companyIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(companyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(MULTITAB, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTabbedPane MULTITAB;
    private javax.swing.JPanel assigmentDistributionPanel;
    private corex.suite.JLabelHQUnderlined assignedCompanyCodeLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode assignedCompanyCodeMatchCode;
    private corex.suite.JPanelRoundedGradient assignmentDistributionFieldPanel;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private corex.suite.JLabelHQUnderlined companyIdLabel;
    private javax.swing.JTextField companyIdTextField;
    private corex.suite.JLabelHQUnderlined companyNameLabel;
    private javax.swing.JTextField companyNameTextField;
    private corex.suite.JLabelHQUnderlined controlCostCenterLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode controlCostCtrMatchCode;
    private corex.suite.JLabelHQUnderlined controlOrderNumberLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode controlOrderNumberMatchCode;
    private corex.suite.JPanelRoundedGradient costAccountingFieldPanel;
    private javax.swing.JPanel costAccountingPanel;
    private corex.suite.JLabelHQUnderlined costAssigmentMethodLabel;
    private javax.swing.JTextField costAssigmentMethodMatchCode;
    private corex.suite.JLabelHQUnderlined costCenterKeyLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costCenterKeyMatchCode;
    private corex.suite.JLabelHQUnderlined costDistributionStrategyLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costDistributionStrategyMatchCode;
    private corex.suite.JLabelHQUnderlined costOrderNumberLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costOrderNumberMatchCode;
    private corex.suite.JLabelHQUnderlined costingKeyLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costingKeyMatchCode;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costingMatchCode;
    private corex.suite.JLabelHQUnderlined costingTypeLabel;
    private corex.suite.JLabelHQUnderlined currencyLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode currencyMatchCode;
    private corex.suite.JLabelHQUnderlined defaultCostCenterLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode defaultCostCtrMatchCode;
    private corex.suite.JLabelHQUnderlined defaultProfitCenterLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode defaultProfitCtrMatchCode;
    private corex.suite.JPanelRoundedGradient generalDataFieldPanel;
    private javax.swing.JPanel generalDataPanel;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private corex.suite.JPanelRoundedGradient orderValuationFieldPanel;
    private javax.swing.JPanel orderValuationPanel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode profitCenterAccountingAssigMatchCode;
    private corex.suite.JLabelHQUnderlined profitCenterAccountingAssigmentLabel;
    private javax.swing.JScrollPane scrollAssigmentDistribution;
    private javax.swing.JScrollPane scrollCostAccounting;
    private javax.swing.JScrollPane scrollGeneralData;
    private javax.swing.JScrollPane scrollOrderValuation;
    private javax.swing.JPanel topPanel;
    private corex.suite.JLabelHQUnderlined validityDateLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode validityMatchCode;
    private corex.suite.JLabelHQUnderlined valuationTypeLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode valuationTypeMatchCode;
    // End of variables declaration//GEN-END:variables



}
