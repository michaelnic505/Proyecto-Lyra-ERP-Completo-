package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form;

import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.services.base.AbstractFormPanel;
import com.simplecore.erp.client.services.base.AbstractSaverController;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.awt.Dimension;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AccountSetup extends AbstractFormPanel {
    
    private FICompanyDTO companyDTO;
    
    public AccountSetup(OperationType operationType, FICompanyDTO companyDTO) {
        super(operationType);
        initComponents();
        this.companyDTO = companyDTO;
    }

    @Override
    protected void initGeneralController(OperationType operationType, ObjectOutputStream output, ObjectInputStream input, ActiveSession session, String transactionCOde) {
      
    }

    @Override
    protected void stateComponentsOnCreate() {

    }

    @Override
    protected void stateComponentsOnChange() {

    }

    @Override
    protected void stateComponentsOnView() {

    }

    @Override
    protected FormState provideFormState() {
        return null;
    }

    @Override
    protected AbstractSaverController provideSaveController() {
        return null;
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
        accountSetupContainerPanel1 = new com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form.AccountSetupContainerPanel();
        generalData = new javax.swing.JPanel();
        jLabelHQUnderlined1 = new corex.suite.JLabelHQUnderlined();
        jTextField1 = new javax.swing.JTextField();
        jLabelHQUnderlined2 = new corex.suite.JLabelHQUnderlined();
        jTextField2 = new javax.swing.JTextField();
        jLabelHQFraming1 = new corex.suite.JLabelHQFraming();
        companyTabbedPane = new javax.swing.JTabbedPane();
        scrollAccounting = new javax.swing.JScrollPane();
        accountingPanel = new javax.swing.JPanel();
        generalData1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabelHQUnderlined3 = new corex.suite.JLabelHQUnderlined();
        companyCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        companyNameLB = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabelHQUnderlined4 = new corex.suite.JLabelHQUnderlined();
        companyCodeMatchCode1 = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        companyNameLB1 = new javax.swing.JLabel();
        jLabelHQUnderlined5 = new corex.suite.JLabelHQUnderlined();
        jTextField3 = new javax.swing.JTextField();
        jLabelHQUnderlined6 = new corex.suite.JLabelHQUnderlined();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabelHQUnderlined7 = new corex.suite.JLabelHQUnderlined();
        jLabelHQFraming2 = new corex.suite.JLabelHQFraming();
        scrollAccounting1 = new javax.swing.JScrollPane();
        accountingPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Setup");
        moduleTitleLabel.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 969, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
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
                .addContainerGap(812, Short.MAX_VALUE))
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

        generalData.setBackground(new java.awt.Color(202, 219, 236));
        generalData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        jLabelHQUnderlined1.setText("Company ID");
        jLabelHQUnderlined1.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        jLabelHQUnderlined2.setText("Chart Of Account Code");
        jLabelHQUnderlined2.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        jLabelHQFraming1.setText("Hierarchy");
        jLabelHQFraming1.setColorBordes(new java.awt.Color(117, 141, 163));
        jLabelHQFraming1.setColorRelleno(new java.awt.Color(136, 175, 198));
        jLabelHQFraming1.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N

        javax.swing.GroupLayout generalDataLayout = new javax.swing.GroupLayout(generalData);
        generalData.setLayout(generalDataLayout);
        generalDataLayout.setHorizontalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addComponent(jLabelHQUnderlined1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addComponent(jLabelHQUnderlined2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(generalDataLayout.createSequentialGroup()
                .addComponent(jLabelHQFraming1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        generalDataLayout.setVerticalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addComponent(jLabelHQFraming1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelHQUnderlined1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelHQUnderlined2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        companyTabbedPane.setBackground(new java.awt.Color(202, 216, 237));
        companyTabbedPane.setForeground(new java.awt.Color(102, 102, 102));
        companyTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        companyTabbedPane.setFont(new java.awt.Font("IBM Plex Sans Medium", 1, 13)); // NOI18N

        scrollAccounting.setToolTipText("");
        scrollAccounting.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        accountingPanel.setBackground(new java.awt.Color(238, 244, 254));
        accountingPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        generalData1.setBackground(new java.awt.Color(202, 219, 236));
        generalData1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        jCheckBox1.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N
        jCheckBox1.setText("Multi-Currency Enabled");
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox1.setIconTextGap(10);

        jLabelHQUnderlined3.setText("Chart Of Account Code");
        jLabelHQUnderlined3.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        companyCodeMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        companyNameLB.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        jCheckBox2.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N
        jCheckBox2.setText("Is Reconsilable");
        jCheckBox2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox2.setIconTextGap(10);

        jCheckBox3.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N
        jCheckBox3.setText("Is Control Account");
        jCheckBox3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox3.setIconTextGap(10);

        jCheckBox4.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N
        jCheckBox4.setText("Cost Center Required");
        jCheckBox4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox4.setIconTextGap(10);

        jLabelHQUnderlined4.setText("Tax Schema");
        jLabelHQUnderlined4.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        companyCodeMatchCode1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        companyNameLB1.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        jLabelHQUnderlined5.setText("Usage proporse");
        jLabelHQUnderlined5.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        jTextField3.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N

        jLabelHQUnderlined6.setText("Notes");
        jLabelHQUnderlined6.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        jTextField4.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N

        jLabelHQUnderlined7.setText("Status");
        jLabelHQUnderlined7.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        jLabelHQFraming2.setText("Settings");
        jLabelHQFraming2.setColorBordes(new java.awt.Color(117, 141, 163));
        jLabelHQFraming2.setColorRelleno(new java.awt.Color(136, 175, 198));
        jLabelHQFraming2.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N

        javax.swing.GroupLayout generalData1Layout = new javax.swing.GroupLayout(generalData1);
        generalData1.setLayout(generalData1Layout);
        generalData1Layout.setHorizontalGroup(
            generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalData1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(generalData1Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox4))
                    .addGroup(generalData1Layout.createSequentialGroup()
                        .addComponent(jLabelHQUnderlined4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(companyCodeMatchCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(companyNameLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(generalData1Layout.createSequentialGroup()
                        .addComponent(jLabelHQUnderlined5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField3))
                    .addGroup(generalData1Layout.createSequentialGroup()
                        .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelHQUnderlined6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelHQUnderlined7, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4)
                            .addGroup(generalData1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(generalData1Layout.createSequentialGroup()
                        .addComponent(jLabelHQUnderlined3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(companyCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(companyNameLB, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(generalData1Layout.createSequentialGroup()
                .addComponent(jLabelHQFraming2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        generalData1Layout.setVerticalGroup(
            generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalData1Layout.createSequentialGroup()
                .addComponent(jLabelHQFraming2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox2)
                        .addComponent(jCheckBox3)
                        .addComponent(jCheckBox4))
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(companyCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHQUnderlined3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyNameLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(companyCodeMatchCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHQUnderlined4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyNameLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHQUnderlined5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelHQUnderlined6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHQUnderlined7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout accountingPanelLayout = new javax.swing.GroupLayout(accountingPanel);
        accountingPanel.setLayout(accountingPanelLayout);
        accountingPanelLayout.setHorizontalGroup(
            accountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        accountingPanelLayout.setVerticalGroup(
            accountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollAccounting.setViewportView(accountingPanel);

        companyTabbedPane.addTab("General", scrollAccounting);

        scrollAccounting1.setToolTipText("");
        scrollAccounting1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        accountingPanel1.setBackground(new java.awt.Color(238, 244, 254));
        accountingPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout accountingPanel1Layout = new javax.swing.GroupLayout(accountingPanel1);
        accountingPanel1.setLayout(accountingPanel1Layout);
        accountingPanel1Layout.setHorizontalGroup(
            accountingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 837, Short.MAX_VALUE)
        );
        accountingPanel1Layout.setVerticalGroup(
            accountingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );

        scrollAccounting1.setViewportView(accountingPanel1);

        companyTabbedPane.addTab("Currencies Allowed", scrollAccounting1);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generalData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(companyTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE))
                .addGap(85, 85, 85)
                .addComponent(accountSetupContainerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(accountSetupContainerPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form.AccountSetupContainerPanel accountSetupContainerPanel1;
    private javax.swing.JPanel accountingPanel;
    private javax.swing.JPanel accountingPanel1;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode companyCodeMatchCode;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode companyCodeMatchCode1;
    private javax.swing.JLabel companyNameLB;
    private javax.swing.JLabel companyNameLB1;
    public static javax.swing.JTabbedPane companyTabbedPane;
    private javax.swing.JPanel generalData;
    private javax.swing.JPanel generalData1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JComboBox<String> jComboBox1;
    private corex.suite.JLabelHQFraming jLabelHQFraming1;
    private corex.suite.JLabelHQFraming jLabelHQFraming2;
    private corex.suite.JLabelHQUnderlined jLabelHQUnderlined1;
    private corex.suite.JLabelHQUnderlined jLabelHQUnderlined2;
    private corex.suite.JLabelHQUnderlined jLabelHQUnderlined3;
    private corex.suite.JLabelHQUnderlined jLabelHQUnderlined4;
    private corex.suite.JLabelHQUnderlined jLabelHQUnderlined5;
    private corex.suite.JLabelHQUnderlined jLabelHQUnderlined6;
    private corex.suite.JLabelHQUnderlined jLabelHQUnderlined7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private javax.swing.JScrollPane scrollAccounting;
    private javax.swing.JScrollPane scrollAccounting1;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
