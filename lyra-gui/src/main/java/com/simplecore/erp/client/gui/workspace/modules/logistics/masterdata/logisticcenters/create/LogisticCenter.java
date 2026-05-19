package com.simplecore.erp.client.gui.workspace.modules.logistics.masterdata.logisticcenters.create;

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

public class LogisticCenter extends JPanel implements TransactionPanel, RowSelectionListener{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;
    private final String societyClassCode;

    public LogisticCenter(String societyClassCode) {
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
        associatedCostCenterLabel = new corex.suite.JLabelHQUnderlined();
        associatedCostCenterMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        centerTypeLabel = new corex.suite.JLabelHQUnderlined();
        profitCenterLabel = new corex.suite.JLabelHQUnderlined();
        centerTypeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        profitCenterMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollCostAccounting = new javax.swing.JScrollPane();
        costAccountingPanel = new javax.swing.JPanel();
        costAccountingFieldPanel = new corex.suite.JPanelRoundedGradient();
        transportTypeIndicatorLabel = new corex.suite.JLabelHQUnderlined();
        transportTypeIndicatorMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        inventoryLocationMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        inventoryLocationLabel = new corex.suite.JLabelHQUnderlined();
        logisticsCenterAddressLabel = new corex.suite.JLabelHQUnderlined();
        logisticsCenterAddressMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        geographicLocationLabel = new corex.suite.JLabelHQUnderlined();
        geographicLocationMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollAssigmentDistribution = new javax.swing.JScrollPane();
        assigmentDistributionPanel = new javax.swing.JPanel();
        assignmentDistributionFieldPanel = new corex.suite.JPanelRoundedGradient();
        operatingHoursMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        responsiblePersonMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        responsiblePersonLabel = new corex.suite.JLabelHQUnderlined();
        responsibleAreaLabel = new corex.suite.JLabelHQUnderlined();
        responsibleAreaMatchCode = new javax.swing.JTextField();
        operatingHoursLabel = new corex.suite.JLabelHQUnderlined();
        scrollOrderValuation = new javax.swing.JScrollPane();
        orderValuationPanel = new javax.swing.JPanel();
        orderValuationFieldPanel = new corex.suite.JPanelRoundedGradient();
        rawMaterialWarehouseLabel = new corex.suite.JLabelHQUnderlined();
        rawMaterialWarehouseMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        goodsReceptionAreaLabel = new corex.suite.JLabelHQUnderlined();
        goodsReceptionAreaMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        goodsDispatchAreaLabel = new corex.suite.JLabelHQUnderlined();
        goodsDispatchAreaMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        storageCapacityLabel = new corex.suite.JLabelHQUnderlined();
        storageCapacityMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        orderValuationPanel1 = new javax.swing.JPanel();
        orderValuationFieldPanel1 = new corex.suite.JPanelRoundedGradient();
        deliveryTimeIndicatorLabel = new corex.suite.JLabelHQUnderlined();
        deliveryTimeIndicatorMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        descriptionLabel = new corex.suite.JLabelHQUnderlined();
        descriptionTextField = new javax.swing.JTextField();
        logisticCenterLabel = new corex.suite.JLabelHQUnderlined();
        logisticCenterTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Logistic Center:");
        moduleTitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1135, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE)
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
                .addContainerGap(978, Short.MAX_VALUE))
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

        associatedCostCenterLabel.setText("Associated Cost Center ");
        associatedCostCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        centerTypeLabel.setText("Type");
        centerTypeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        profitCenterLabel.setText("Profit Center");
        profitCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout generalDataFieldPanelLayout = new javax.swing.GroupLayout(generalDataFieldPanel);
        generalDataFieldPanel.setLayout(generalDataFieldPanelLayout);
        generalDataFieldPanelLayout.setHorizontalGroup(
            generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(profitCenterLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(associatedCostCenterLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(centerTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(centerTypeMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profitCenterMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(associatedCostCenterMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(336, Short.MAX_VALUE))
        );
        generalDataFieldPanelLayout.setVerticalGroup(
            generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(centerTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(centerTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(profitCenterMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profitCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(associatedCostCenterMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(associatedCostCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        transportTypeIndicatorLabel.setText("Transport Type Indicator");
        transportTypeIndicatorLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        inventoryLocationLabel.setText("Inventory Location");
        inventoryLocationLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        logisticsCenterAddressLabel.setText("Logistics Center Address");
        logisticsCenterAddressLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        geographicLocationLabel.setText("Geographic Location Code");
        geographicLocationLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout costAccountingFieldPanelLayout = new javax.swing.GroupLayout(costAccountingFieldPanel);
        costAccountingFieldPanel.setLayout(costAccountingFieldPanelLayout);
        costAccountingFieldPanelLayout.setHorizontalGroup(
            costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costAccountingFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(transportTypeIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventoryLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logisticsCenterAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(geographicLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(transportTypeIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventoryLocationMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logisticsCenterAddressMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(geographicLocationMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(354, Short.MAX_VALUE))
        );
        costAccountingFieldPanelLayout.setVerticalGroup(
            costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costAccountingFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logisticsCenterAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logisticsCenterAddressMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(geographicLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(geographicLocationMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(transportTypeIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transportTypeIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(costAccountingFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inventoryLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventoryLocationMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
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

        MULTITAB.addTab("Location & Logistics", scrollCostAccounting);

        assigmentDistributionPanel.setBackground(new java.awt.Color(238, 244, 254));
        assigmentDistributionPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        assignmentDistributionFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        assignmentDistributionFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        assignmentDistributionFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        responsiblePersonLabel.setText("Logistics Center Responsible Person");
        responsiblePersonLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        responsibleAreaLabel.setText("Responsible Area");
        responsibleAreaLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        operatingHoursLabel.setText("Operating Hours");
        operatingHoursLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout assignmentDistributionFieldPanelLayout = new javax.swing.GroupLayout(assignmentDistributionFieldPanel);
        assignmentDistributionFieldPanel.setLayout(assignmentDistributionFieldPanelLayout);
        assignmentDistributionFieldPanelLayout.setHorizontalGroup(
            assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assignmentDistributionFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operatingHoursLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsibleAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsiblePersonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operatingHoursMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsibleAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsiblePersonMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 133, 133))
        );
        assignmentDistributionFieldPanelLayout.setVerticalGroup(
            assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assignmentDistributionFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(responsiblePersonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsiblePersonMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responsibleAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsibleAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(assignmentDistributionFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(operatingHoursLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operatingHoursMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
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

        MULTITAB.addTab("Responsibility & Organization", scrollAssigmentDistribution);

        orderValuationPanel.setBackground(new java.awt.Color(238, 244, 254));
        orderValuationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        orderValuationFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        orderValuationFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        orderValuationFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        rawMaterialWarehouseLabel.setText("Raw Material Warehouse");
        rawMaterialWarehouseLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        goodsReceptionAreaLabel.setText("Goods Reception Area");
        goodsReceptionAreaLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        goodsDispatchAreaLabel.setText("Goods Dispatch Area");
        goodsDispatchAreaLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        storageCapacityLabel.setText("Storage Capacity");
        storageCapacityLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout orderValuationFieldPanelLayout = new javax.swing.GroupLayout(orderValuationFieldPanel);
        orderValuationFieldPanel.setLayout(orderValuationFieldPanelLayout);
        orderValuationFieldPanelLayout.setHorizontalGroup(
            orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(storageCapacityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(goodsDispatchAreaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(goodsReceptionAreaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rawMaterialWarehouseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                .addGap(0, 0, 0)
                .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rawMaterialWarehouseMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(goodsReceptionAreaMatchCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(goodsDispatchAreaMatchCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(storageCapacityMatchCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(330, Short.MAX_VALUE))
        );
        orderValuationFieldPanelLayout.setVerticalGroup(
            orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rawMaterialWarehouseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rawMaterialWarehouseMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(goodsReceptionAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goodsReceptionAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(goodsDispatchAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goodsDispatchAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderValuationFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(storageCapacityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storageCapacityMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout orderValuationPanelLayout = new javax.swing.GroupLayout(orderValuationPanel);
        orderValuationPanel.setLayout(orderValuationPanelLayout);
        orderValuationPanelLayout.setHorizontalGroup(
            orderValuationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderValuationFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        orderValuationPanelLayout.setVerticalGroup(
            orderValuationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderValuationFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        scrollOrderValuation.setViewportView(orderValuationPanel);

        MULTITAB.addTab("Warehousing & Distribution", scrollOrderValuation);

        orderValuationPanel1.setBackground(new java.awt.Color(238, 244, 254));
        orderValuationPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        orderValuationFieldPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        orderValuationFieldPanel1.setColor1(new java.awt.Color(247, 247, 255));
        orderValuationFieldPanel1.setColor2(new java.awt.Color(247, 247, 255));

        deliveryTimeIndicatorLabel.setText("Delivery Time Indicator");
        deliveryTimeIndicatorLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout orderValuationFieldPanel1Layout = new javax.swing.GroupLayout(orderValuationFieldPanel1);
        orderValuationFieldPanel1.setLayout(orderValuationFieldPanel1Layout);
        orderValuationFieldPanel1Layout.setHorizontalGroup(
            orderValuationFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationFieldPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deliveryTimeIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(deliveryTimeIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(378, Short.MAX_VALUE))
        );
        orderValuationFieldPanel1Layout.setVerticalGroup(
            orderValuationFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationFieldPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderValuationFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deliveryTimeIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryTimeIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout orderValuationPanel1Layout = new javax.swing.GroupLayout(orderValuationPanel1);
        orderValuationPanel1.setLayout(orderValuationPanel1Layout);
        orderValuationPanel1Layout.setHorizontalGroup(
            orderValuationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderValuationFieldPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        orderValuationPanel1Layout.setVerticalGroup(
            orderValuationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderValuationPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderValuationFieldPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Indicators & Parameters", orderValuationPanel1);

        descriptionLabel.setText("Description");
        descriptionLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        descriptionTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        descriptionTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        logisticCenterLabel.setText("Logistic center");
        logisticCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        logisticCenterTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        logisticCenterTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(logisticCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logisticCenterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(425, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logisticCenterTextField)
                    .addComponent(logisticCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(MULTITAB, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTabbedPane MULTITAB;
    private javax.swing.JPanel assigmentDistributionPanel;
    private corex.suite.JPanelRoundedGradient assignmentDistributionFieldPanel;
    private corex.suite.JLabelHQUnderlined associatedCostCenterLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode associatedCostCenterMatchCode;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private corex.suite.JLabelHQUnderlined centerTypeLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode centerTypeMatchCode;
    private corex.suite.JPanelRoundedGradient costAccountingFieldPanel;
    private javax.swing.JPanel costAccountingPanel;
    private corex.suite.JLabelHQUnderlined deliveryTimeIndicatorLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode deliveryTimeIndicatorMatchCode;
    private corex.suite.JLabelHQUnderlined descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private corex.suite.JPanelRoundedGradient generalDataFieldPanel;
    private javax.swing.JPanel generalDataPanel;
    private corex.suite.JLabelHQUnderlined geographicLocationLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode geographicLocationMatchCode;
    private corex.suite.JLabelHQUnderlined goodsDispatchAreaLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode goodsDispatchAreaMatchCode;
    private corex.suite.JLabelHQUnderlined goodsReceptionAreaLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode goodsReceptionAreaMatchCode;
    private corex.suite.JLabelHQUnderlined inventoryLocationLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode inventoryLocationMatchCode;
    private javax.swing.JToolBar jToolBar1;
    private corex.suite.JLabelHQUnderlined logisticCenterLabel;
    private javax.swing.JTextField logisticCenterTextField;
    private corex.suite.JLabelHQUnderlined logisticsCenterAddressLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode logisticsCenterAddressMatchCode;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private corex.suite.JLabelHQUnderlined operatingHoursLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode operatingHoursMatchCode;
    private corex.suite.JPanelRoundedGradient orderValuationFieldPanel;
    private corex.suite.JPanelRoundedGradient orderValuationFieldPanel1;
    private javax.swing.JPanel orderValuationPanel;
    private javax.swing.JPanel orderValuationPanel1;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private corex.suite.JLabelHQUnderlined profitCenterLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode profitCenterMatchCode;
    private corex.suite.JLabelHQUnderlined rawMaterialWarehouseLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode rawMaterialWarehouseMatchCode;
    private corex.suite.JLabelHQUnderlined responsibleAreaLabel;
    private javax.swing.JTextField responsibleAreaMatchCode;
    private corex.suite.JLabelHQUnderlined responsiblePersonLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode responsiblePersonMatchCode;
    private javax.swing.JScrollPane scrollAssigmentDistribution;
    private javax.swing.JScrollPane scrollCostAccounting;
    private javax.swing.JScrollPane scrollGeneralData;
    private javax.swing.JScrollPane scrollOrderValuation;
    private corex.suite.JLabelHQUnderlined storageCapacityLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode storageCapacityMatchCode;
    private javax.swing.JPanel topPanel;
    private corex.suite.JLabelHQUnderlined transportTypeIndicatorLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode transportTypeIndicatorMatchCode;
    // End of variables declaration//GEN-END:variables



}
