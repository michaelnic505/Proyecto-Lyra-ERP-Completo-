package com.simplecore.erp.client.gui.workspace.modules.logistics.masterdata.enterpriseareas.create;

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

public class EnterpriseArea extends JPanel implements TransactionPanel, RowSelectionListener{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;
    private final String societyClassCode;

    public EnterpriseArea(String societyClassCode) {
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
        responsiblePersonLabel = new corex.suite.JLabelHQUnderlined();
        responsiblePersonMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        geographicLocationLabel = new corex.suite.JLabelHQUnderlined();
        geographicLocationMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollStorageCapacity = new javax.swing.JScrollPane();
        storageCapacityPanel = new javax.swing.JPanel();
        storageCapacityFieldPanel = new corex.suite.JPanelRoundedGradient();
        storageCapacityLabel = new corex.suite.JLabelHQUnderlined();
        storageCapacityMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        manageMaterialTypeLabel = new corex.suite.JLabelHQUnderlined();
        managedMaterialTypeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollSecurityAccess = new javax.swing.JScrollPane();
        securityAccessPanel = new javax.swing.JPanel();
        securityAccessFieldPanel = new corex.suite.JPanelRoundedGradient();
        materialReceptionAreaMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        materialReceptionAreaLabel = new corex.suite.JLabelHQUnderlined();
        materialDispatchAreaLabel = new corex.suite.JLabelHQUnderlined();
        materialDispatchAreaMatchCode = new javax.swing.JTextField();
        scrollSecurityMantenance = new javax.swing.JScrollPane();
        securityMaintenancePanel = new javax.swing.JPanel();
        securityMaintenanceFieldPanel = new corex.suite.JPanelRoundedGradient();
        associatedCostCenterLabel = new corex.suite.JLabelHQUnderlined();
        associatedCostCenterMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        areaTypeLabel = new corex.suite.JLabelHQUnderlined();
        areaTypeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        associatedDispatchLabel = new corex.suite.JLabelHQUnderlined();
        associatedDispatchMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollOperationalLinks = new javax.swing.JScrollPane();
        operationalLinksPanel = new javax.swing.JPanel();
        operationalLinksFieldPanel = new corex.suite.JPanelRoundedGradient();
        maintenanceIndicatorLabel = new corex.suite.JLabelHQUnderlined();
        maintenanceIndicatorMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        descriptionLabel = new corex.suite.JLabelHQUnderlined();
        descriptionTextField = new javax.swing.JTextField();
        areaIdLabel = new corex.suite.JLabelHQUnderlined();
        areaIdTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Enterprise Area:");
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

        responsiblePersonLabel.setText("Responsible Person");
        responsiblePersonLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        geographicLocationLabel.setText("Geographic Location");
        geographicLocationLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout generalDataFieldPanelLayout = new javax.swing.GroupLayout(generalDataFieldPanel);
        generalDataFieldPanel.setLayout(generalDataFieldPanelLayout);
        generalDataFieldPanelLayout.setHorizontalGroup(
            generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(responsiblePersonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(geographicLocationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(responsiblePersonMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(geographicLocationMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(341, Short.MAX_VALUE))
        );
        generalDataFieldPanelLayout.setVerticalGroup(
            generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(responsiblePersonMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsiblePersonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(geographicLocationMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(geographicLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
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

        storageCapacityPanel.setBackground(new java.awt.Color(238, 244, 254));
        storageCapacityPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        storageCapacityFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        storageCapacityFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        storageCapacityFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        storageCapacityLabel.setText("Storage Capacity");
        storageCapacityLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        manageMaterialTypeLabel.setText("Managed Material Type");
        manageMaterialTypeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout storageCapacityFieldPanelLayout = new javax.swing.GroupLayout(storageCapacityFieldPanel);
        storageCapacityFieldPanel.setLayout(storageCapacityFieldPanelLayout);
        storageCapacityFieldPanelLayout.setHorizontalGroup(
            storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storageCapacityFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storageCapacityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageMaterialTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storageCapacityMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(managedMaterialTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(354, Short.MAX_VALUE))
        );
        storageCapacityFieldPanelLayout.setVerticalGroup(
            storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storageCapacityFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(storageCapacityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storageCapacityMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manageMaterialTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(managedMaterialTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout storageCapacityPanelLayout = new javax.swing.GroupLayout(storageCapacityPanel);
        storageCapacityPanel.setLayout(storageCapacityPanelLayout);
        storageCapacityPanelLayout.setHorizontalGroup(
            storageCapacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
            .addGroup(storageCapacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(storageCapacityPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(storageCapacityFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        storageCapacityPanelLayout.setVerticalGroup(
            storageCapacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
            .addGroup(storageCapacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, storageCapacityPanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(storageCapacityFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(190, Short.MAX_VALUE)))
        );

        scrollStorageCapacity.setViewportView(storageCapacityPanel);

        MULTITAB.addTab("Storage & Capacity", scrollStorageCapacity);

        securityAccessPanel.setBackground(new java.awt.Color(238, 244, 254));
        securityAccessPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        securityAccessFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        securityAccessFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        securityAccessFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        materialReceptionAreaLabel.setText("Security Indicator");
        materialReceptionAreaLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        materialDispatchAreaLabel.setText("Restricted Access Indicator");
        materialDispatchAreaLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout securityAccessFieldPanelLayout = new javax.swing.GroupLayout(securityAccessFieldPanel);
        securityAccessFieldPanel.setLayout(securityAccessFieldPanelLayout);
        securityAccessFieldPanelLayout.setHorizontalGroup(
            securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityAccessFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(materialDispatchAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materialReceptionAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(materialDispatchAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materialReceptionAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(156, 156, 156))
        );
        securityAccessFieldPanelLayout.setVerticalGroup(
            securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityAccessFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(materialReceptionAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materialReceptionAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materialDispatchAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materialDispatchAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        javax.swing.GroupLayout securityAccessPanelLayout = new javax.swing.GroupLayout(securityAccessPanel);
        securityAccessPanel.setLayout(securityAccessPanelLayout);
        securityAccessPanelLayout.setHorizontalGroup(
            securityAccessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, securityAccessPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(securityAccessFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        securityAccessPanelLayout.setVerticalGroup(
            securityAccessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityAccessPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(securityAccessFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        scrollSecurityAccess.setViewportView(securityAccessPanel);

        MULTITAB.addTab("Security & Access", scrollSecurityAccess);

        securityMaintenancePanel.setBackground(new java.awt.Color(238, 244, 254));
        securityMaintenancePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        securityMaintenanceFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        securityMaintenanceFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        securityMaintenanceFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        associatedCostCenterLabel.setText("Associated Cost Center");
        associatedCostCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        areaTypeLabel.setText("Area Type");
        areaTypeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        associatedDispatchLabel.setText("Associated Dispatch Area");
        associatedDispatchLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout securityMaintenanceFieldPanelLayout = new javax.swing.GroupLayout(securityMaintenanceFieldPanel);
        securityMaintenanceFieldPanel.setLayout(securityMaintenanceFieldPanelLayout);
        securityMaintenanceFieldPanelLayout.setHorizontalGroup(
            securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityMaintenanceFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(associatedCostCenterLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(areaTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(associatedDispatchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
                .addGap(0, 0, 0)
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(associatedDispatchMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(areaTypeMatchCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(associatedCostCenterMatchCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(319, Short.MAX_VALUE))
        );
        securityMaintenanceFieldPanelLayout.setVerticalGroup(
            securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityMaintenanceFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(associatedCostCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(associatedCostCenterMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(areaTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(areaTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(associatedDispatchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(associatedDispatchMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout securityMaintenancePanelLayout = new javax.swing.GroupLayout(securityMaintenancePanel);
        securityMaintenancePanel.setLayout(securityMaintenancePanelLayout);
        securityMaintenancePanelLayout.setHorizontalGroup(
            securityMaintenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityMaintenancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(securityMaintenanceFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        securityMaintenancePanelLayout.setVerticalGroup(
            securityMaintenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityMaintenancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(securityMaintenanceFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        scrollSecurityMantenance.setViewportView(securityMaintenancePanel);

        MULTITAB.addTab("Operational Links", scrollSecurityMantenance);

        operationalLinksPanel.setBackground(new java.awt.Color(238, 244, 254));
        operationalLinksPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        operationalLinksFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        operationalLinksFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        operationalLinksFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        maintenanceIndicatorLabel.setText("Maintenance Indicator");
        maintenanceIndicatorLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout operationalLinksFieldPanelLayout = new javax.swing.GroupLayout(operationalLinksFieldPanel);
        operationalLinksFieldPanel.setLayout(operationalLinksFieldPanelLayout);
        operationalLinksFieldPanelLayout.setHorizontalGroup(
            operationalLinksFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operationalLinksFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maintenanceIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(maintenanceIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
        );
        operationalLinksFieldPanelLayout.setVerticalGroup(
            operationalLinksFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operationalLinksFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(operationalLinksFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(maintenanceIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maintenanceIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout operationalLinksPanelLayout = new javax.swing.GroupLayout(operationalLinksPanel);
        operationalLinksPanel.setLayout(operationalLinksPanelLayout);
        operationalLinksPanelLayout.setHorizontalGroup(
            operationalLinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operationalLinksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(operationalLinksFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        operationalLinksPanelLayout.setVerticalGroup(
            operationalLinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operationalLinksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(operationalLinksFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        scrollOperationalLinks.setViewportView(operationalLinksPanel);

        MULTITAB.addTab("Operational Links", scrollOperationalLinks);

        descriptionLabel.setText("Description");
        descriptionLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        descriptionTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        descriptionTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        areaIdLabel.setText("Area ID");
        areaIdLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        areaIdTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        areaIdTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

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
                            .addComponent(areaIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(areaIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(425, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(areaIdTextField)
                    .addComponent(areaIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private corex.suite.JLabelHQUnderlined areaIdLabel;
    private javax.swing.JTextField areaIdTextField;
    private corex.suite.JLabelHQUnderlined areaTypeLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode areaTypeMatchCode;
    private corex.suite.JLabelHQUnderlined associatedCostCenterLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode associatedCostCenterMatchCode;
    private corex.suite.JLabelHQUnderlined associatedDispatchLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode associatedDispatchMatchCode;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private corex.suite.JLabelHQUnderlined descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private corex.suite.JPanelRoundedGradient generalDataFieldPanel;
    private javax.swing.JPanel generalDataPanel;
    private corex.suite.JLabelHQUnderlined geographicLocationLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode geographicLocationMatchCode;
    private javax.swing.JToolBar jToolBar1;
    private corex.suite.JLabelHQUnderlined maintenanceIndicatorLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode maintenanceIndicatorMatchCode;
    private corex.suite.JLabelHQUnderlined manageMaterialTypeLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode managedMaterialTypeMatchCode;
    private corex.suite.JLabelHQUnderlined materialDispatchAreaLabel;
    private javax.swing.JTextField materialDispatchAreaMatchCode;
    private corex.suite.JLabelHQUnderlined materialReceptionAreaLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode materialReceptionAreaMatchCode;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private corex.suite.JPanelRoundedGradient operationalLinksFieldPanel;
    private javax.swing.JPanel operationalLinksPanel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private corex.suite.JLabelHQUnderlined responsiblePersonLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode responsiblePersonMatchCode;
    private javax.swing.JScrollPane scrollGeneralData;
    private javax.swing.JScrollPane scrollOperationalLinks;
    private javax.swing.JScrollPane scrollSecurityAccess;
    private javax.swing.JScrollPane scrollSecurityMantenance;
    private javax.swing.JScrollPane scrollStorageCapacity;
    private corex.suite.JPanelRoundedGradient securityAccessFieldPanel;
    private javax.swing.JPanel securityAccessPanel;
    private corex.suite.JPanelRoundedGradient securityMaintenanceFieldPanel;
    private javax.swing.JPanel securityMaintenancePanel;
    private corex.suite.JPanelRoundedGradient storageCapacityFieldPanel;
    private corex.suite.JLabelHQUnderlined storageCapacityLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode storageCapacityMatchCode;
    private javax.swing.JPanel storageCapacityPanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables



}
