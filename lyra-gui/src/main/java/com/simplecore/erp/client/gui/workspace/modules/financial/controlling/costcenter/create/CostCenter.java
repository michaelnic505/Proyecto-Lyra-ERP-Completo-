package com.simplecore.erp.client.gui.workspace.modules.financial.controlling.costcenter.create;

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

public class CostCenter extends JPanel implements TransactionPanel, RowSelectionListener{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;
    private final String societyClassCode;

    public CostCenter(String societyClassCode) {
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
        proceedButton.setIcon(new CustomSVGIcon("/icons/svg/ok_button_icon.svg",new Dimension(24,24)));
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
        generalDataFielPanel = new corex.suite.JPanelRoundedGradient();
        currencyLb = new corex.suite.JLabelHQUnderlined();
        costingTypeLabel = new corex.suite.JLabelHQUnderlined();
        currencyMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costingMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollCostAccounting = new javax.swing.JScrollPane();
        costAccountingPanel = new javax.swing.JPanel();
        costAccoutingFieldPanel = new corex.suite.JPanelRoundedGradient();
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
        costCenterKeyLabel1 = new corex.suite.JLabelHQUnderlined();
        costCenterKeyMatchCode1 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costCenterKeyLabel2 = new corex.suite.JLabelHQUnderlined();
        costCenterKeyMatchCode2 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollAssigmentDistribution = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanelRoundedGradient3 = new corex.suite.JPanelRoundedGradient();
        costDistributionStrategyMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        profitCenterAccountingAssigmentLabel = new corex.suite.JLabelHQUnderlined();
        profitCenterAccountingAssigMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        assignedCompanyCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        assignedCompanyCodeLabel = new corex.suite.JLabelHQUnderlined();
        costAssigmentMethodLabel = new corex.suite.JLabelHQUnderlined();
        costAssigmentMethodMatchCode = new javax.swing.JTextField();
        costDistributionStrategyLabel = new corex.suite.JLabelHQUnderlined();
        profitCenterAccountingAssigmentLabel1 = new corex.suite.JLabelHQUnderlined();
        profitCenterAccountingAssigMatchCode1 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        profitCenterAccountingAssigmentLabel2 = new corex.suite.JLabelHQUnderlined();
        profitCenterAccountingAssigMatchCode2 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanelRoundedGradient4 = new corex.suite.JPanelRoundedGradient();
        controlOrderNumberLabel = new corex.suite.JLabelHQUnderlined();
        controlOrderNumberMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costOrderNumberLabel = new corex.suite.JLabelHQUnderlined();
        costOrderNumberMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costCenterNameLabel = new corex.suite.JLabelHQUnderlined();
        companyNameTextField = new javax.swing.JTextField();
        costCenterIdLabel = new corex.suite.JLabelHQUnderlined();
        companyIdTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Cost Center:");
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

        MULTITAB.setBackground(new java.awt.Color(202, 216, 237));
        MULTITAB.setForeground(new java.awt.Color(102, 102, 102));
        MULTITAB.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        MULTITAB.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        scrollGeneralData.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        generalDataPanel.setBackground(new java.awt.Color(238, 244, 254));
        generalDataPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        generalDataFielPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        generalDataFielPanel.setColor1(new java.awt.Color(247, 247, 255));
        generalDataFielPanel.setColor2(new java.awt.Color(247, 247, 255));

        currencyLb.setText("Company ID");
        currencyLb.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costingTypeLabel.setText("Currency");
        costingTypeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout generalDataFielPanelLayout = new javax.swing.GroupLayout(generalDataFielPanel);
        generalDataFielPanel.setLayout(generalDataFielPanelLayout);
        generalDataFielPanelLayout.setHorizontalGroup(
            generalDataFielPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFielPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFielPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currencyLb, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(costingTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(generalDataFielPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currencyMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(costingMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        generalDataFielPanelLayout.setVerticalGroup(
            generalDataFielPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFielPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFielPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(currencyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFielPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(costingMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout generalDataPanelLayout = new javax.swing.GroupLayout(generalDataPanel);
        generalDataPanel.setLayout(generalDataPanelLayout);
        generalDataPanelLayout.setHorizontalGroup(
            generalDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalDataFielPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        generalDataPanelLayout.setVerticalGroup(
            generalDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalDataFielPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );

        scrollGeneralData.setViewportView(generalDataPanel);

        MULTITAB.addTab("General Data", scrollGeneralData);

        costAccountingPanel.setBackground(new java.awt.Color(238, 244, 254));
        costAccountingPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        costAccoutingFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        costAccoutingFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        costAccoutingFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        defaultProfitCenterLabel.setText("Functional Area");
        defaultProfitCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        controlCostCenterLabel.setText("Profit Center");
        controlCostCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costingKeyLabel.setText("Business Area");
        costingKeyLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        defaultCostCenterLabel.setText("Controlling Area");
        defaultCostCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costCenterKeyLabel.setText("Hierarchy Area");
        costCenterKeyLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costCenterKeyLabel1.setText("Location");
        costCenterKeyLabel1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costCenterKeyLabel2.setText("Department");
        costCenterKeyLabel2.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout costAccoutingFieldPanelLayout = new javax.swing.GroupLayout(costAccoutingFieldPanel);
        costAccoutingFieldPanel.setLayout(costAccoutingFieldPanelLayout);
        costAccoutingFieldPanelLayout.setHorizontalGroup(
            costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costAccoutingFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(costAccoutingFieldPanelLayout.createSequentialGroup()
                        .addGroup(costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(defaultProfitCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(controlCostCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costingKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(defaultCostCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costCenterKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(defaultProfitCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(controlCostCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costingKeyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(defaultCostCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costCenterKeyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(costAccoutingFieldPanelLayout.createSequentialGroup()
                        .addComponent(costCenterKeyLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(costCenterKeyMatchCode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(costAccoutingFieldPanelLayout.createSequentialGroup()
                        .addComponent(costCenterKeyLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(costCenterKeyMatchCode2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(364, Short.MAX_VALUE))
        );
        costAccoutingFieldPanelLayout.setVerticalGroup(
            costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costAccoutingFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(costingKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costingKeyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(defaultCostCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defaultCostCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(defaultProfitCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defaultProfitCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlCostCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controlCostCtrMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(costCenterKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCenterKeyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(costCenterKeyLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCenterKeyMatchCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(costAccoutingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(costCenterKeyLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCenterKeyMatchCode2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout costAccountingPanelLayout = new javax.swing.GroupLayout(costAccountingPanel);
        costAccountingPanel.setLayout(costAccountingPanelLayout);
        costAccountingPanelLayout.setHorizontalGroup(
            costAccountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costAccountingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(costAccoutingFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        costAccountingPanelLayout.setVerticalGroup(
            costAccountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costAccountingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(costAccoutingFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        scrollCostAccounting.setViewportView(costAccountingPanel);

        MULTITAB.addTab("Organizational Data", scrollCostAccounting);

        jPanel4.setBackground(new java.awt.Color(238, 244, 254));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanelRoundedGradient3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        jPanelRoundedGradient3.setColor1(new java.awt.Color(247, 247, 255));
        jPanelRoundedGradient3.setColor2(new java.awt.Color(247, 247, 255));

        profitCenterAccountingAssigmentLabel.setText("Allocation Indicator ");
        profitCenterAccountingAssigmentLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        assignedCompanyCodeLabel.setText("Cost Center Category");
        assignedCompanyCodeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costAssigmentMethodLabel.setText("Budget Control Indicator");
        costAssigmentMethodLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costDistributionStrategyLabel.setText("Statistical Key Figure ");
        costDistributionStrategyLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        profitCenterAccountingAssigmentLabel1.setText("Cost Distribution Method");
        profitCenterAccountingAssigmentLabel1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        profitCenterAccountingAssigmentLabel2.setText("Internal Order Assignment");
        profitCenterAccountingAssigmentLabel2.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanelRoundedGradient3Layout = new javax.swing.GroupLayout(jPanelRoundedGradient3);
        jPanelRoundedGradient3.setLayout(jPanelRoundedGradient3Layout);
        jPanelRoundedGradient3Layout.setHorizontalGroup(
            jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRoundedGradient3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRoundedGradient3Layout.createSequentialGroup()
                        .addGroup(jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(profitCenterAccountingAssigmentLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profitCenterAccountingAssigmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costDistributionStrategyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costAssigmentMethodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(assignedCompanyCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(costDistributionStrategyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profitCenterAccountingAssigMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costAssigmentMethodMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(assignedCompanyCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profitCenterAccountingAssigMatchCode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelRoundedGradient3Layout.createSequentialGroup()
                        .addComponent(profitCenterAccountingAssigmentLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(profitCenterAccountingAssigMatchCode2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(127, 127, 127))
        );
        jPanelRoundedGradient3Layout.setVerticalGroup(
            jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRoundedGradient3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(assignedCompanyCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assignedCompanyCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costAssigmentMethodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costAssigmentMethodMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(costDistributionStrategyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costDistributionStrategyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profitCenterAccountingAssigmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profitCenterAccountingAssigMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profitCenterAccountingAssigmentLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profitCenterAccountingAssigMatchCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRoundedGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profitCenterAccountingAssigmentLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profitCenterAccountingAssigMatchCode2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelRoundedGradient3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelRoundedGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        scrollAssigmentDistribution.setViewportView(jPanel4);

        MULTITAB.addTab("Accounting & Costing", scrollAssigmentDistribution);

        jPanel5.setBackground(new java.awt.Color(238, 244, 254));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanelRoundedGradient4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        jPanelRoundedGradient4.setColor1(new java.awt.Color(247, 247, 255));
        jPanelRoundedGradient4.setColor2(new java.awt.Color(247, 247, 255));

        controlOrderNumberLabel.setText("Responsible Person");
        controlOrderNumberLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costOrderNumberLabel.setText("Validity Date (From - To)");
        costOrderNumberLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanelRoundedGradient4Layout = new javax.swing.GroupLayout(jPanelRoundedGradient4);
        jPanelRoundedGradient4.setLayout(jPanelRoundedGradient4Layout);
        jPanelRoundedGradient4Layout.setHorizontalGroup(
            jPanelRoundedGradient4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRoundedGradient4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRoundedGradient4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRoundedGradient4Layout.createSequentialGroup()
                        .addComponent(controlOrderNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(controlOrderNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRoundedGradient4Layout.createSequentialGroup()
                        .addComponent(costOrderNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(costOrderNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(376, Short.MAX_VALUE))
        );
        jPanelRoundedGradient4Layout.setVerticalGroup(
            jPanelRoundedGradient4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRoundedGradient4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRoundedGradient4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(controlOrderNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controlOrderNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanelRoundedGradient4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(costOrderNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costOrderNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelRoundedGradient4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelRoundedGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel5);

        MULTITAB.addTab("Validity & Control", jScrollPane4);

        costCenterNameLabel.setText("Description");
        costCenterNameLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        companyNameTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        companyNameTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        costCenterIdLabel.setText("Cost Center ID");
        costCenterIdLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

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
                            .addComponent(costCenterNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costCenterIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(companyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(companyIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(companyIdTextField)
                    .addComponent(costCenterIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(companyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCenterNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(MULTITAB)
                .addContainerGap())
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTabbedPane MULTITAB;
    private corex.suite.JLabelHQUnderlined assignedCompanyCodeLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode assignedCompanyCodeMatchCode;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private javax.swing.JTextField companyIdTextField;
    private javax.swing.JTextField companyNameTextField;
    private corex.suite.JLabelHQUnderlined controlCostCenterLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode controlCostCtrMatchCode;
    private corex.suite.JLabelHQUnderlined controlOrderNumberLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode controlOrderNumberMatchCode;
    private javax.swing.JPanel costAccountingPanel;
    private corex.suite.JPanelRoundedGradient costAccoutingFieldPanel;
    private corex.suite.JLabelHQUnderlined costAssigmentMethodLabel;
    private javax.swing.JTextField costAssigmentMethodMatchCode;
    private corex.suite.JLabelHQUnderlined costCenterIdLabel;
    private corex.suite.JLabelHQUnderlined costCenterKeyLabel;
    private corex.suite.JLabelHQUnderlined costCenterKeyLabel1;
    private corex.suite.JLabelHQUnderlined costCenterKeyLabel2;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costCenterKeyMatchCode;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costCenterKeyMatchCode1;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costCenterKeyMatchCode2;
    private corex.suite.JLabelHQUnderlined costCenterNameLabel;
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
    private corex.suite.JPanelRoundedGradient generalDataFielPanel;
    private javax.swing.JPanel generalDataPanel;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private corex.suite.JPanelRoundedGradient jPanelRoundedGradient3;
    private corex.suite.JPanelRoundedGradient jPanelRoundedGradient4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode profitCenterAccountingAssigMatchCode;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode profitCenterAccountingAssigMatchCode1;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode profitCenterAccountingAssigMatchCode2;
    private corex.suite.JLabelHQUnderlined profitCenterAccountingAssigmentLabel;
    private corex.suite.JLabelHQUnderlined profitCenterAccountingAssigmentLabel1;
    private corex.suite.JLabelHQUnderlined profitCenterAccountingAssigmentLabel2;
    private javax.swing.JScrollPane scrollAssigmentDistribution;
    private javax.swing.JScrollPane scrollCostAccounting;
    private javax.swing.JScrollPane scrollGeneralData;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables



}
