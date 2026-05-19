package com.simplecore.erp.client.gui.workspace.modules.logistics.masterdata.maintenanceplant.create;

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

public class MaintenancePlant extends JPanel implements TransactionPanel, RowSelectionListener{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;
    private final String societyClassCode;

    public MaintenancePlant(String societyClassCode) {
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
        maintenancePlanAddressLabel = new corex.suite.JLabelHQUnderlined();
        maintenancePlantAdressMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        responsiblePersonLabel = new corex.suite.JLabelHQUnderlined();
        responsiblePersonMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        geographicLocationCodeLabel = new corex.suite.JLabelHQUnderlined();
        geographicLocationCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollAdministrativeData = new javax.swing.JScrollPane();
        administrativeDataPanel = new javax.swing.JPanel();
        administrativeDataFieldPanel = new corex.suite.JPanelRoundedGradient();
        transportTypeIndicatorLabel = new corex.suite.JLabelHQUnderlined();
        plantTypeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        managedMaterialTypeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        inventoryLocationLabel = new corex.suite.JLabelHQUnderlined();
        logisticsCenterAddressLabel = new corex.suite.JLabelHQUnderlined();
        costCenterCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        geographicLocationLabel = new corex.suite.JLabelHQUnderlined();
        profitCenterCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollOperationalAreas = new javax.swing.JScrollPane();
        operationAreasPanel = new javax.swing.JPanel();
        operationAreasFieldPanel = new corex.suite.JPanelRoundedGradient();
        storageCapacityMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        materialReceptionAreaMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        materialReceptionAreaLabel = new corex.suite.JLabelHQUnderlined();
        materialDispatchAreaLabel = new corex.suite.JLabelHQUnderlined();
        materialDispatchAreaMatchCode = new javax.swing.JTextField();
        storageCapacityLabel = new corex.suite.JLabelHQUnderlined();
        operatingHoursLabel = new corex.suite.JLabelHQUnderlined();
        operationHoursMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollSecurityMantenance = new javax.swing.JScrollPane();
        securityMaintenancePanel = new javax.swing.JPanel();
        securityMaintenanceFieldPanel = new corex.suite.JPanelRoundedGradient();
        specialStorageIndicatorLabel = new corex.suite.JLabelHQUnderlined();
        specialStorageIndicatorMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        maintenanceAreaLabel = new corex.suite.JLabelHQUnderlined();
        maintenanceAreaMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        securityIndicatorLabel = new corex.suite.JLabelHQUnderlined();
        securityIndicatorMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        descriptionLabel = new corex.suite.JLabelHQUnderlined();
        descriptionTextField = new javax.swing.JTextField();
        maintenancePlanLabel = new corex.suite.JLabelHQUnderlined();
        maintenancePlantTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Maintenance Plant:");
        moduleTitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
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
                .addContainerGap(744, Short.MAX_VALUE))
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

        maintenancePlanAddressLabel.setText("Maintenance Plant Address");
        maintenancePlanAddressLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        responsiblePersonLabel.setText("Responsible Person");
        responsiblePersonLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        geographicLocationCodeLabel.setText("Geographic Location Code");
        geographicLocationCodeLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout generalDataFieldPanelLayout = new javax.swing.GroupLayout(generalDataFieldPanel);
        generalDataFieldPanel.setLayout(generalDataFieldPanelLayout);
        generalDataFieldPanelLayout.setHorizontalGroup(
            generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(maintenancePlanAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(responsiblePersonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(geographicLocationCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(maintenancePlantAdressMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(responsiblePersonMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(geographicLocationCodeMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(334, Short.MAX_VALUE))
        );
        generalDataFieldPanelLayout.setVerticalGroup(
            generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(maintenancePlantAdressMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maintenancePlanAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(responsiblePersonMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsiblePersonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(geographicLocationCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(geographicLocationCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        administrativeDataPanel.setBackground(new java.awt.Color(238, 244, 254));
        administrativeDataPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        administrativeDataFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        administrativeDataFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        administrativeDataFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        transportTypeIndicatorLabel.setText("Plant Type");
        transportTypeIndicatorLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        inventoryLocationLabel.setText("Managed Material Type");
        inventoryLocationLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        logisticsCenterAddressLabel.setText("Cost Center Code");
        logisticsCenterAddressLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        geographicLocationLabel.setText("Profit Center Code");
        geographicLocationLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout administrativeDataFieldPanelLayout = new javax.swing.GroupLayout(administrativeDataFieldPanel);
        administrativeDataFieldPanel.setLayout(administrativeDataFieldPanelLayout);
        administrativeDataFieldPanelLayout.setHorizontalGroup(
            administrativeDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(administrativeDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(administrativeDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(transportTypeIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventoryLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logisticsCenterAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(geographicLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(administrativeDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plantTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(managedMaterialTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCenterCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profitCenterCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(354, Short.MAX_VALUE))
        );
        administrativeDataFieldPanelLayout.setVerticalGroup(
            administrativeDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(administrativeDataFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(administrativeDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logisticsCenterAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCenterCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(administrativeDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(geographicLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profitCenterCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(administrativeDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(transportTypeIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plantTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(administrativeDataFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inventoryLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(managedMaterialTypeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout administrativeDataPanelLayout = new javax.swing.GroupLayout(administrativeDataPanel);
        administrativeDataPanel.setLayout(administrativeDataPanelLayout);
        administrativeDataPanelLayout.setHorizontalGroup(
            administrativeDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
            .addGroup(administrativeDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(administrativeDataPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(administrativeDataFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        administrativeDataPanelLayout.setVerticalGroup(
            administrativeDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
            .addGroup(administrativeDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, administrativeDataPanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(administrativeDataFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(190, Short.MAX_VALUE)))
        );

        scrollAdministrativeData.setViewportView(administrativeDataPanel);

        MULTITAB.addTab("Administrative Data", scrollAdministrativeData);

        operationAreasPanel.setBackground(new java.awt.Color(238, 244, 254));
        operationAreasPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        operationAreasFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        operationAreasFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        operationAreasFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        materialReceptionAreaLabel.setText("Material Reception Area");
        materialReceptionAreaLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        materialDispatchAreaLabel.setText("Material Dispatch Area");
        materialDispatchAreaLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        storageCapacityLabel.setText("Storage Capacity");
        storageCapacityLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        operatingHoursLabel.setText("Operating Hours");
        operatingHoursLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout operationAreasFieldPanelLayout = new javax.swing.GroupLayout(operationAreasFieldPanel);
        operationAreasFieldPanel.setLayout(operationAreasFieldPanelLayout);
        operationAreasFieldPanelLayout.setHorizontalGroup(
            operationAreasFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operationAreasFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(operationAreasFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(operationAreasFieldPanelLayout.createSequentialGroup()
                        .addGroup(operationAreasFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(storageCapacityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(materialDispatchAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(materialReceptionAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(operationAreasFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(storageCapacityMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(materialDispatchAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(materialReceptionAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(operationAreasFieldPanelLayout.createSequentialGroup()
                        .addComponent(operatingHoursLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(operationHoursMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(133, 133, 133))
        );
        operationAreasFieldPanelLayout.setVerticalGroup(
            operationAreasFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operationAreasFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(operationAreasFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(materialReceptionAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materialReceptionAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(operationAreasFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materialDispatchAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materialDispatchAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(operationAreasFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(storageCapacityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storageCapacityMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(operationAreasFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(operatingHoursLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operationHoursMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout operationAreasPanelLayout = new javax.swing.GroupLayout(operationAreasPanel);
        operationAreasPanel.setLayout(operationAreasPanelLayout);
        operationAreasPanelLayout.setHorizontalGroup(
            operationAreasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, operationAreasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(operationAreasFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        operationAreasPanelLayout.setVerticalGroup(
            operationAreasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operationAreasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(operationAreasFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        scrollOperationalAreas.setViewportView(operationAreasPanel);

        MULTITAB.addTab("Operational Areas", scrollOperationalAreas);

        securityMaintenancePanel.setBackground(new java.awt.Color(238, 244, 254));
        securityMaintenancePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        securityMaintenanceFieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        securityMaintenanceFieldPanel.setColor1(new java.awt.Color(247, 247, 255));
        securityMaintenanceFieldPanel.setColor2(new java.awt.Color(247, 247, 255));

        specialStorageIndicatorLabel.setText("Special Storage Indicator");
        specialStorageIndicatorLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        maintenanceAreaLabel.setText("Maintenance Area");
        maintenanceAreaLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        securityIndicatorLabel.setText("Security Indicator");
        securityIndicatorLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout securityMaintenanceFieldPanelLayout = new javax.swing.GroupLayout(securityMaintenanceFieldPanel);
        securityMaintenanceFieldPanel.setLayout(securityMaintenanceFieldPanelLayout);
        securityMaintenanceFieldPanelLayout.setHorizontalGroup(
            securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityMaintenanceFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(securityMaintenanceFieldPanelLayout.createSequentialGroup()
                        .addComponent(specialStorageIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(specialStorageIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(securityMaintenanceFieldPanelLayout.createSequentialGroup()
                        .addComponent(maintenanceAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(maintenanceAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(securityMaintenanceFieldPanelLayout.createSequentialGroup()
                        .addComponent(securityIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(securityIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        securityMaintenanceFieldPanelLayout.setVerticalGroup(
            securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityMaintenanceFieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(specialStorageIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(specialStorageIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(maintenanceAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maintenanceAreaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(securityMaintenanceFieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(securityIndicatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(securityIndicatorMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout securityMaintenancePanelLayout = new javax.swing.GroupLayout(securityMaintenancePanel);
        securityMaintenancePanel.setLayout(securityMaintenancePanelLayout);
        securityMaintenancePanelLayout.setHorizontalGroup(
            securityMaintenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityMaintenancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(securityMaintenanceFieldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        securityMaintenancePanelLayout.setVerticalGroup(
            securityMaintenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityMaintenancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(securityMaintenanceFieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(241, Short.MAX_VALUE))
        );

        scrollSecurityMantenance.setViewportView(securityMaintenancePanel);

        MULTITAB.addTab("Security & Maintenance", scrollSecurityMantenance);

        descriptionLabel.setText("Description");
        descriptionLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        descriptionTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        descriptionTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        maintenancePlanLabel.setText("Maintenance Plant");
        maintenancePlanLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        maintenancePlantTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        maintenancePlantTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

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
                            .addComponent(maintenancePlanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maintenancePlantTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(maintenancePlantTextField)
                    .addComponent(maintenancePlanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private corex.suite.JPanelRoundedGradient administrativeDataFieldPanel;
    private javax.swing.JPanel administrativeDataPanel;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costCenterCodeMatchCode;
    private corex.suite.JLabelHQUnderlined descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private corex.suite.JPanelRoundedGradient generalDataFieldPanel;
    private javax.swing.JPanel generalDataPanel;
    private corex.suite.JLabelHQUnderlined geographicLocationCodeLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode geographicLocationCodeMatchCode;
    private corex.suite.JLabelHQUnderlined geographicLocationLabel;
    private corex.suite.JLabelHQUnderlined inventoryLocationLabel;
    private javax.swing.JToolBar jToolBar1;
    private corex.suite.JLabelHQUnderlined logisticsCenterAddressLabel;
    private corex.suite.JLabelHQUnderlined maintenanceAreaLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode maintenanceAreaMatchCode;
    private corex.suite.JLabelHQUnderlined maintenancePlanAddressLabel;
    private corex.suite.JLabelHQUnderlined maintenancePlanLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode maintenancePlantAdressMatchCode;
    private javax.swing.JTextField maintenancePlantTextField;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode managedMaterialTypeMatchCode;
    private corex.suite.JLabelHQUnderlined materialDispatchAreaLabel;
    private javax.swing.JTextField materialDispatchAreaMatchCode;
    private corex.suite.JLabelHQUnderlined materialReceptionAreaLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode materialReceptionAreaMatchCode;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private corex.suite.JLabelHQUnderlined operatingHoursLabel;
    private corex.suite.JPanelRoundedGradient operationAreasFieldPanel;
    private javax.swing.JPanel operationAreasPanel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode operationHoursMatchCode;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode plantTypeMatchCode;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode profitCenterCodeMatchCode;
    private corex.suite.JLabelHQUnderlined responsiblePersonLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode responsiblePersonMatchCode;
    private javax.swing.JScrollPane scrollAdministrativeData;
    private javax.swing.JScrollPane scrollGeneralData;
    private javax.swing.JScrollPane scrollOperationalAreas;
    private javax.swing.JScrollPane scrollSecurityMantenance;
    private corex.suite.JLabelHQUnderlined securityIndicatorLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode securityIndicatorMatchCode;
    private corex.suite.JPanelRoundedGradient securityMaintenanceFieldPanel;
    private javax.swing.JPanel securityMaintenancePanel;
    private corex.suite.JLabelHQUnderlined specialStorageIndicatorLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode specialStorageIndicatorMatchCode;
    private corex.suite.JLabelHQUnderlined storageCapacityLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode storageCapacityMatchCode;
    private javax.swing.JPanel topPanel;
    private corex.suite.JLabelHQUnderlined transportTypeIndicatorLabel;
    // End of variables declaration//GEN-END:variables



}
