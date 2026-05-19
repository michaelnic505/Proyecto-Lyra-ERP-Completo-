package com.simplecore.erp.client.gui.workspace.modules.logistics.masterdata.locations.create;

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

public class Location extends JPanel implements TransactionPanel, RowSelectionListener{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;
    private final String societyClassCode;

    public Location(String societyClassCode) {
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
        locationTypeLabel = new corex.suite.JLabelHQUnderlined();
        locationTypeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        activeLocationIndicatorLabel = new corex.suite.JLabelHQUnderlined();
        activeLocationIndicatorMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollStorageCapacity = new javax.swing.JScrollPane();
        storageCapacityPanel = new javax.swing.JPanel();
        storageCapacityFieldPanel = new corex.suite.JPanelRoundedGradient();
        associatedLogisticCenterLabel = new corex.suite.JLabelHQUnderlined();
        associatedLogisticCenterMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        locationUnitLabel = new corex.suite.JLabelHQUnderlined();
        locationUnitMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        locationAreaLabel = new corex.suite.JLabelHQUnderlined();
        locationAreaMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        plantCodeLabel = new corex.suite.JLabelHQUnderlined();
        plantCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        countryCodeLabel = new corex.suite.JLabelHQUnderlined();
        countryCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        regionLabel = new corex.suite.JLabelHQUnderlined();
        regionMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        locationAddressLabel = new corex.suite.JLabelHQUnderlined();
        locationAddreddMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollSecurityAccess = new javax.swing.JScrollPane();
        securityAccessPanel = new javax.swing.JPanel();
        securityAccessFieldPanel = new corex.suite.JPanelRoundedGradient();
        phoneNumberMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        phoneNumberLabel = new corex.suite.JLabelHQUnderlined();
        emailLabel = new corex.suite.JLabelHQUnderlined();
        emailMatchCode = new javax.swing.JTextField();
        scrollSecurityMantenance = new javax.swing.JScrollPane();
        securityMaintenancePanel = new javax.swing.JPanel();
        securityMaintenanceFieldPanel = new corex.suite.JPanelRoundedGradient();
        locationCapacityLabel = new corex.suite.JLabelHQUnderlined();
        locationCapacityMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        creationDateLabel = new corex.suite.JLabelHQUnderlined();
        creationDateMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        descriptionLabel = new corex.suite.JLabelHQUnderlined();
        descriptionTextField = new javax.swing.JTextField();
        locationIdLabel = new corex.suite.JLabelHQUnderlined();
        locationIdTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Location:");
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

        locationTypeLabel.setText("Type");
        locationTypeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        activeLocationIndicatorLabel.setText("Active Location Indicator");
        activeLocationIndicatorLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout generalDataFieldPanelLayout = new javax.swing.GroupLayout(generalDataFieldPanel);
        generalDataFieldPanel.setLayout(generalDataFieldPanelLayout);
        generalDataFieldPanelLayout.setHorizontalGroup(
            generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(locationTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(activeLocationIndicatorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(locationTypeMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(activeLocationIndicatorMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(341, Short.MAX_VALUE))
        );
        generalDataFieldPanelLayout.setVerticalGroup(
            generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(locationTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(activeLocationIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activeLocationIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        associatedLogisticCenterLabel.setText("Associated Logistics Center");
        associatedLogisticCenterLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        locationUnitLabel.setText("Location Unit");
        locationUnitLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        locationAreaLabel.setText("Location Area");
        locationAreaLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        plantCodeLabel.setText("Plant Code");
        plantCodeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        countryCodeLabel.setText("Country Code");
        countryCodeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        regionLabel.setText("Region");
        regionLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        locationAddressLabel.setText("Location Address");
        locationAddressLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout storageCapacityFieldPanelLayout = new javax.swing.GroupLayout(storageCapacityFieldPanel);
        storageCapacityFieldPanel.setLayout(storageCapacityFieldPanelLayout);
        storageCapacityFieldPanelLayout.setHorizontalGroup(
            storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storageCapacityFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(storageCapacityFieldPanelLayout.createSequentialGroup()
                        .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(associatedLogisticCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(locationUnitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(associatedLogisticCenterMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(locationUnitMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(storageCapacityFieldPanelLayout.createSequentialGroup()
                        .addComponent(locationAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(locationAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(storageCapacityFieldPanelLayout.createSequentialGroup()
                        .addComponent(plantCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(plantCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(storageCapacityFieldPanelLayout.createSequentialGroup()
                        .addComponent(countryCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(countryCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(storageCapacityFieldPanelLayout.createSequentialGroup()
                        .addComponent(regionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(regionMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(storageCapacityFieldPanelLayout.createSequentialGroup()
                        .addComponent(locationAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(locationAddreddMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(354, Short.MAX_VALUE))
        );
        storageCapacityFieldPanelLayout.setVerticalGroup(
            storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storageCapacityFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(associatedLogisticCenterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(associatedLogisticCenterMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(locationUnitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationUnitMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(locationAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plantCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plantCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(countryCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(regionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regionMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(storageCapacityFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(locationAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationAddreddMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
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
                    .addContainerGap(94, Short.MAX_VALUE)))
        );

        scrollStorageCapacity.setViewportView(storageCapacityPanel);

        MULTITAB.addTab("Location", scrollStorageCapacity);

        securityAccessPanel.setBackground(new java.awt.Color(238, 244, 254));
        securityAccessPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        securityAccessFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        securityAccessFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        securityAccessFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        phoneNumberLabel.setText("Location Phone Number");
        phoneNumberLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        emailLabel.setText("Location Email");
        emailLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout securityAccessFieldPanelLayout = new javax.swing.GroupLayout(securityAccessFieldPanel);
        securityAccessFieldPanel.setLayout(securityAccessFieldPanelLayout);
        securityAccessFieldPanelLayout.setHorizontalGroup(
            securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityAccessFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(156, 156, 156))
        );
        securityAccessFieldPanelLayout.setVerticalGroup(
            securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityAccessFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(securityAccessFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        MULTITAB.addTab("Contact", scrollSecurityAccess);

        securityMaintenancePanel.setBackground(new java.awt.Color(238, 244, 254));
        securityMaintenancePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        securityMaintenanceFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        securityMaintenanceFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        securityMaintenanceFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        locationCapacityLabel.setText("Location Capacity");
        locationCapacityLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        creationDateLabel.setText("Creation Date");
        creationDateLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout securityMaintenanceFieldPanelLayout = new javax.swing.GroupLayout(securityMaintenanceFieldPanel);
        securityMaintenanceFieldPanel.setLayout(securityMaintenanceFieldPanelLayout);
        securityMaintenanceFieldPanelLayout.setHorizontalGroup(
            securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityMaintenanceFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(locationCapacityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(creationDateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(creationDateMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationCapacityMatchCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(319, Short.MAX_VALUE))
        );
        securityMaintenanceFieldPanelLayout.setVerticalGroup(
            securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityMaintenanceFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(locationCapacityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationCapacityMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(creationDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(creationDateMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
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

        MULTITAB.addTab("Capacity & Registration", scrollSecurityMantenance);

        descriptionLabel.setText("Description");
        descriptionLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        descriptionTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        descriptionTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        locationIdLabel.setText("Location ID");
        locationIdLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        locationIdTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        locationIdTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

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
                            .addComponent(locationIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(locationIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(425, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(locationIdTextField)
                    .addComponent(locationIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private corex.suite.JLabelHQUnderlined activeLocationIndicatorLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode activeLocationIndicatorMatchCode;
    private corex.suite.JLabelHQUnderlined associatedLogisticCenterLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode associatedLogisticCenterMatchCode;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private corex.suite.JLabelHQUnderlined countryCodeLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode countryCodeMatchCode;
    private corex.suite.JLabelHQUnderlined creationDateLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode creationDateMatchCode;
    private corex.suite.JLabelHQUnderlined descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private corex.suite.JLabelHQUnderlined emailLabel;
    private javax.swing.JTextField emailMatchCode;
    private corex.suite.JPanelRoundedGradient generalDataFieldPanel;
    private javax.swing.JPanel generalDataPanel;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode locationAddreddMatchCode;
    private corex.suite.JLabelHQUnderlined locationAddressLabel;
    private corex.suite.JLabelHQUnderlined locationAreaLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode locationAreaMatchCode;
    private corex.suite.JLabelHQUnderlined locationCapacityLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode locationCapacityMatchCode;
    private corex.suite.JLabelHQUnderlined locationIdLabel;
    private javax.swing.JTextField locationIdTextField;
    private corex.suite.JLabelHQUnderlined locationTypeLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode locationTypeMatchCode;
    private corex.suite.JLabelHQUnderlined locationUnitLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode locationUnitMatchCode;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private corex.suite.JLabelHQUnderlined phoneNumberLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode phoneNumberMatchCode;
    private corex.suite.JLabelHQUnderlined plantCodeLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode plantCodeMatchCode;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private corex.suite.JLabelHQUnderlined regionLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode regionMatchCode;
    private javax.swing.JScrollPane scrollGeneralData;
    private javax.swing.JScrollPane scrollSecurityAccess;
    private javax.swing.JScrollPane scrollSecurityMantenance;
    private javax.swing.JScrollPane scrollStorageCapacity;
    private corex.suite.JPanelRoundedGradient securityAccessFieldPanel;
    private javax.swing.JPanel securityAccessPanel;
    private corex.suite.JPanelRoundedGradient securityMaintenanceFieldPanel;
    private javax.swing.JPanel securityMaintenancePanel;
    private corex.suite.JPanelRoundedGradient storageCapacityFieldPanel;
    private javax.swing.JPanel storageCapacityPanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables



}
