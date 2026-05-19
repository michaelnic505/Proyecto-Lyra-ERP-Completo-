package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form;

import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.dependencies.InjectDependency;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.model.ComboItem;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller.FICompanyController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller.FICompanySaveController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyFormState;
import com.simplecore.erp.client.services.base.AbstractFormPanel;
import com.simplecore.erp.client.services.base.AbstractSaverController;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FICompanyFormPanel extends AbstractFormPanel {

    @InjectDependency
    private FICompanyFormState concreteFormState;

    @InjectDependency
    private FICompanySaveController saverController;

    private FICompanyComponentEnabler stateEnabler;
    
    private FICompanyDTO companyDTO;
    
    public FICompanyFormPanel(OperationType operationType) {
        super(operationType);
        initComponents();
        stateEnabler = new FICompanyComponentEnabler(this);
    }
    
    public FICompanyFormPanel(OperationType operationType,FICompanyDTO companyDTO) {
        super(operationType);
        initComponents();
        this.companyDTO = companyDTO;
        stateEnabler = new FICompanyComponentEnabler(this);
        cOAssociationPanel.setOperationType(operationType);
    }
    
    @Override
    protected void initGeneralController(OperationType operationType, ObjectOutputStream output, ObjectInputStream input, ActiveSession session,String transactionCOde) {
        FICompanyController controller = new FICompanyController(this, operationType, output, input, session);
        controller.initialize();
        controller.consumeDependencies();
        controller.loadCompanyDataIfNeeded(companyDTO);
    }

    @Override
    protected void stateComponentsOnCreate() {
        stateEnabler.applyCreateMode();
    }

    @Override
    protected void stateComponentsOnChange() {
        stateEnabler.applyModifyMode();
    }

    @Override
    protected void stateComponentsOnView() {
        stateEnabler.applyViewMode();
    }
    
    @Override
    protected FormState provideFormState() {
        return concreteFormState;
    }

    @Override
    protected AbstractSaverController provideSaveController() {
        return saverController;
    }

    // ----- Exposed UI Components for Data Handler -----//
    public JTextField getCompanyCodeTF(){return companyCodeTF;};
    public JTextField getCompanyNameTF(){return companyNameTF;};
    public COAssociationPanel getCOAssociationPanel(){return cOAssociationPanel;};
    public JTextField getLegalNameTF(){return legalNameTF;};
    public JTextField getBusinessTypeTF(){return businessTypeTF;};
    public JTextField getIndustryClassificationTF(){return industryClassificationTF;};
    public MatchCode getCountryCodeMatchCode(){return countryCodeMatchCode;};
    public JLabel getCountryNameLb(){return countryNameLb;};
    public JTextField getLegalAddressTF(){return legalAddressTF;};
    public JTextField getPhoneTF(){return phoneTF;};
    public JTextField getEmailTF(){return emailTF;};
    public JTextField getOfficialLanguageTF(){return officialLanguageTF;};
    public MatchCode getTimezoneMatchCode(){return timezoneMatchCode;};
    public MatchCode getChartOfAccountMatchCode(){return chartOfAccountMatchCode;};
    public JLabel getChartOfAccountNameLb(){return chartOfAccountNameLb;};
    public MatchCode getCurrencyCodeMatchCode(){return currencyCodeMatchCode;};
    public JLabel getCurrencyNameLb(){return currencyNameLb;};
    public JTextField getFiscalYearTF(){return fiscalYearTF;};
    public JDateChooser getFiscalStartDateChooser(){return fiscalStartDateChooser;};
    public JDateChooser getFiscalEndDateChooser(){return fiscalEndDateChooser;};
    public JComboBox getAccountingStandardCombo(){return accountingStandardCombo;};
    public JCheckBox getChartMultiCurrencyCheckbox(){return chartMultiCurrencyCheckbox;};
    public MatchCode getTaxSchemaMatchCode(){return taxSchemaMatchCode;};
    public JLabel getTaxSchemaNamelb(){return taxSchemaNamelb;};
    public JTextField getCompanyCreatedAtTF(){return companyCreatedAtTF;};
    public JTextField getCompanyCreatedByTF(){return companyCreatedByTF;};
    public JTextField getCompanyUpdatedAtTF(){return companyUpdatedAtTF;};
    public JTextField getCompanyUpdatedByTF(){return companyUpdatedByTF;};
    public JComboBox getCompanyStatusCombo(){return companyStatusCombo;};
    
    public JTextField getConstitutionActTF(){return constitutionActTF;};
    public JTextField getRucCertificateTF(){return rucCertificateTF;};
    public JTextField getLegalPowerTF(){return legalPowerTF;};
    public JTextField getMunicipalLicenseTF(){return municipalLicenseTF;};
    public JTextField getRepresentativeIDTF(){return representativeIDTF;};
    public JTextField getEntityRegistrationTF(){return entityRegistrationTF;};
    public JTextField getOtherDocument(){return otherTF;};
    
    public JButton getConstitutionActButton() { return addConstitutionActButton; }
    public JButton getRemoveConstitutionActButton() { return removeConstitutionActButton; }
    public JButton getViewConstitutionActButton() { return viewConstitutionActButton; }
    
    public JButton getRucCertificateButton() { return addRucCertificateButton; }
    public JButton getRemoveRucCertificateButton() { return removeRucCertificateButton; }
    public JButton getViewRucCertificateButton() { return viewRucCertificateButton; }
    
    public JButton getLegalPowerButton() { return addLegalPowerButton; }
    public JButton getRemoveLegalPowerButton() { return removeLegalPowerButton; }
    public JButton getViewLegalPowerButton() { return viewLegalPowerButton; }
    
    public JButton getMunicipalLicenseButton() { return addMunicipalLicenseButton; }
    public JButton getRemoveMunicipalLicenseButton() { return removeMunicipalLicenseButton; }
    public JButton getViewMunicipalLicenseButton() { return viewMunicipalLicenseButton; }
    
    public JButton getRepresentativeIDButton() { return addRepresentativeIDButton; }
    public JButton getRemoveRepresentativeIDButton() { return removeRepresentativeIDButton; }
    public JButton getViewRepresentativeIDButton() { return viewRepresentativeIDButton; }
    
    public JButton getEntityRegistrationButton() { return addEntityRegistrationButton; }
    public JButton getRemoveEntityRegistrationButton() { return removeEntityRegistrationButton; }
    public JButton getViewEntityRegistrationButton() { return viewEntityRegistrationButton; }
    
    public JButton getOtherButton() { return addOtherButton; }
    public JButton getRemoveOtherButton() { return removeOtherButton; }
    public JButton getViewOtherButton() { return viewOtherButton; }

    public SystemMessages notificator(){return notificator;};
    
    
    
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
        companyTabbedPane = new javax.swing.JTabbedPane();
        scrollGeneralSettings = new javax.swing.JScrollPane();
        generalSettingPanel = new javax.swing.JPanel();
        companyPane = new corex.suite.JPanelRoundedGradient();
        companyTitlePanel = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        companyIdentificationLb = new javax.swing.JLabel();
        legalNameLb = new corex.suite.JLabelHQUnderlined();
        businessTypeLb = new corex.suite.JLabelHQUnderlined();
        businessTypeTF = new javax.swing.JTextField();
        industryClassificationLb = new corex.suite.JLabelHQUnderlined();
        industryClassificationTF = new javax.swing.JTextField();
        legalNameTF = new javax.swing.JTextField();
        locationPane = new corex.suite.JPanelRoundedGradient();
        locationTitlePanel = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        locationContactTitleLb = new javax.swing.JLabel();
        countryNameLb = new javax.swing.JLabel();
        countryCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        countryCodeLb = new corex.suite.JLabelHQUnderlined();
        legalAddressLb = new corex.suite.JLabelHQUnderlined();
        legalAddressTF = new javax.swing.JTextField();
        companyPhoneLb = new corex.suite.JLabelHQUnderlined();
        phoneTF = new javax.swing.JTextField();
        companyEmailLb = new corex.suite.JLabelHQUnderlined();
        emailTF = new javax.swing.JTextField();
        officialLanguageLb = new corex.suite.JLabelHQUnderlined();
        officialLanguageTF = new javax.swing.JTextField();
        timeZoneLb = new corex.suite.JLabelHQUnderlined();
        timezoneMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        scrollAccounting = new javax.swing.JScrollPane();
        accountingPanel = new javax.swing.JPanel();
        financialConfigPanel = new corex.suite.JPanelRoundedGradient();
        financialTitle = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        financialTitleLb = new javax.swing.JLabel();
        chartOfAccountNameLb = new javax.swing.JLabel();
        chartOfAccountMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        chartOfAccountLb = new corex.suite.JLabelHQUnderlined();
        currencyCodeLb = new corex.suite.JLabelHQUnderlined();
        currencyCodeMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        currencyNameLb = new javax.swing.JLabel();
        fiscalYearLb = new corex.suite.JLabelHQUnderlined();
        fiscalYearTF = new javax.swing.JTextField();
        fiscalStartDateLb = new corex.suite.JLabelHQUnderlined();
        fiscalStartDateChooser = new com.toedter.calendar.JDateChooser();
        fiscalEndDateLb = new corex.suite.JLabelHQUnderlined();
        fiscalEndDateChooser = new com.toedter.calendar.JDateChooser();
        taxationPanel = new corex.suite.JPanelRoundedGradient();
        taxationTitlePanel = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        taxationTitleLb = new javax.swing.JLabel();
        taxSchemaMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        taxSchemaNamelb = new javax.swing.JLabel();
        taxSchemaLb = new corex.suite.JLabelHQUnderlined();
        accountingStandardLb = new corex.suite.JLabelHQUnderlined();
        accountingStandardCombo = new javax.swing.JComboBox<>();
        chartMultiCurrencyCheckbox = new javax.swing.JCheckBox();
        scrollCostAccounting = new javax.swing.JScrollPane();
        ficoPanel = new javax.swing.JPanel();
        cardsPanel = new corex.suite.JPanelRoundedGradient();
        COCardTitlePanel = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        COCompanyAssociationTitleLb = new javax.swing.JLabel();
        cOAssociationPanel = new com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COAssociationPanel();
        scrollStatusAudit = new javax.swing.JScrollPane();
        statusAuditPanel = new javax.swing.JPanel();
        companyStatusPanel = new corex.suite.JPanelRoundedGradient();
        companyStatusTitlePanel = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        companyStatusTitleLb = new javax.swing.JLabel();
        createdAtLb = new corex.suite.JLabelHQUnderlined();
        createdByLb = new corex.suite.JLabelHQUnderlined();
        companyCreatedByTF = new javax.swing.JTextField();
        companyCreatedAtTF = new javax.swing.JTextField();
        updatedAtLb = new corex.suite.JLabelHQUnderlined();
        companyUpdatedAtTF = new javax.swing.JTextField();
        chartUpdatedByLb = new corex.suite.JLabelHQUnderlined();
        companyUpdatedByTF = new javax.swing.JTextField();
        companyStatusCombo = new javax.swing.JComboBox<>();
        companyStatusLb = new corex.suite.JLabelHQUnderlined();
        scrollRelatedDocuments = new javax.swing.JScrollPane();
        relatedDocumentPanel = new javax.swing.JPanel();
        attachmentPanel = new corex.suite.JPanelRoundedGradient();
        attachmentTitlePanel = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        attachmentTitleLb = new javax.swing.JLabel();
        constitutionActTF = new javax.swing.JTextField();
        addConstitutionActButton = new javax.swing.JButton();
        constitutionActLb = new corex.suite.JLabelHQUnderlined();
        rucCertificateLb = new corex.suite.JLabelHQUnderlined();
        rucCertificateTF = new javax.swing.JTextField();
        addRucCertificateButton = new javax.swing.JButton();
        legalPowerTF = new javax.swing.JTextField();
        addLegalPowerButton = new javax.swing.JButton();
        legalPowerLb = new corex.suite.JLabelHQUnderlined();
        municipalLicenseTF = new javax.swing.JTextField();
        addMunicipalLicenseButton = new javax.swing.JButton();
        municipalLicenseLb = new corex.suite.JLabelHQUnderlined();
        representativeIDTF = new javax.swing.JTextField();
        addRepresentativeIDButton = new javax.swing.JButton();
        representaIDLb = new corex.suite.JLabelHQUnderlined();
        entityRegistrationLb = new corex.suite.JLabelHQUnderlined();
        entityRegistrationTF = new javax.swing.JTextField();
        addEntityRegistrationButton = new javax.swing.JButton();
        otherLb = new corex.suite.JLabelHQUnderlined();
        otherTF = new javax.swing.JTextField();
        addOtherButton = new javax.swing.JButton();
        removeConstitutionActButton = new javax.swing.JButton();
        removeRepresentativeIDButton = new javax.swing.JButton();
        removeRucCertificateButton = new javax.swing.JButton();
        removeEntityRegistrationButton = new javax.swing.JButton();
        removeLegalPowerButton = new javax.swing.JButton();
        removeOtherButton = new javax.swing.JButton();
        removeMunicipalLicenseButton = new javax.swing.JButton();
        viewConstitutionActButton = new javax.swing.JButton();
        viewRepresentativeIDButton = new javax.swing.JButton();
        viewRucCertificateButton = new javax.swing.JButton();
        viewEntityRegistrationButton = new javax.swing.JButton();
        viewLegalPowerButton = new javax.swing.JButton();
        viewOtherButton = new javax.swing.JButton();
        viewMunicipalLicenseButton = new javax.swing.JButton();
        companyCodeLb = new corex.suite.JLabelHQUnderlined();
        companyCodeTF = new javax.swing.JTextField();
        companyNameLb = new corex.suite.JLabelHQUnderlined();
        companyNameTF = new javax.swing.JTextField();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("FI Company");
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

        companyTabbedPane.setBackground(new java.awt.Color(202, 216, 237));
        companyTabbedPane.setForeground(new java.awt.Color(102, 102, 102));
        companyTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        companyTabbedPane.setFont(new java.awt.Font("IBM Plex Sans Medium", 1, 13)); // NOI18N

        scrollGeneralSettings.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        generalSettingPanel.setBackground(new java.awt.Color(238, 244, 254));
        generalSettingPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        companyPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        companyPane.setColor1(new java.awt.Color(247, 247, 255));
        companyPane.setColor2(new java.awt.Color(247, 247, 255));
        companyPane.setPreferredSize(new java.awt.Dimension(800, 163));

        companyIdentificationLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        companyIdentificationLb.setText("Company Identification");

        javax.swing.GroupLayout companyTitlePanelLayout = new javax.swing.GroupLayout(companyTitlePanel);
        companyTitlePanel.setLayout(companyTitlePanelLayout);
        companyTitlePanelLayout.setHorizontalGroup(
            companyTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyTitlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(companyIdentificationLb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        companyTitlePanelLayout.setVerticalGroup(
            companyTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(companyIdentificationLb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        legalNameLb.setText("Legal name");
        legalNameLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        businessTypeLb.setText("Business Type");
        businessTypeLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        businessTypeTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        industryClassificationLb.setText("Insutry Classification");
        industryClassificationLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        industryClassificationTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        legalNameTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        javax.swing.GroupLayout companyPaneLayout = new javax.swing.GroupLayout(companyPane);
        companyPane.setLayout(companyPaneLayout);
        companyPaneLayout.setHorizontalGroup(
            companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(companyTitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(companyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(companyPaneLayout.createSequentialGroup()
                        .addComponent(legalNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(legalNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(companyPaneLayout.createSequentialGroup()
                        .addComponent(businessTypeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(businessTypeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(companyPaneLayout.createSequentialGroup()
                        .addComponent(industryClassificationLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(industryClassificationTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(214, Short.MAX_VALUE))
        );
        companyPaneLayout.setVerticalGroup(
            companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, companyPaneLayout.createSequentialGroup()
                .addComponent(companyTitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(legalNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(legalNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(businessTypeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(businessTypeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(industryClassificationLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(industryClassificationTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        locationPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        locationPane.setColor1(new java.awt.Color(247, 247, 255));
        locationPane.setColor2(new java.awt.Color(247, 247, 255));
        locationPane.setPreferredSize(new java.awt.Dimension(800, 132));

        locationContactTitleLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        locationContactTitleLb.setText(" Location and Contact");

        javax.swing.GroupLayout locationTitlePanelLayout = new javax.swing.GroupLayout(locationTitlePanel);
        locationTitlePanel.setLayout(locationTitlePanelLayout);
        locationTitlePanelLayout.setHorizontalGroup(
            locationTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationTitlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(locationContactTitleLb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        locationTitlePanelLayout.setVerticalGroup(
            locationTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(locationContactTitleLb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        countryNameLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        countryCodeMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        countryCodeLb.setText("Country Code");
        countryCodeLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        legalAddressLb.setText("Legal Address");
        legalAddressLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        legalAddressTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyPhoneLb.setText("Phone");
        companyPhoneLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        phoneTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyEmailLb.setText("Email");
        companyEmailLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        emailTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        officialLanguageLb.setText("Official Language");
        officialLanguageLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        officialLanguageTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        timeZoneLb.setText("Timezone");
        timeZoneLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        timezoneMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        javax.swing.GroupLayout locationPaneLayout = new javax.swing.GroupLayout(locationPane);
        locationPane.setLayout(locationPaneLayout);
        locationPaneLayout.setHorizontalGroup(
            locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(locationTitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(locationPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(locationPaneLayout.createSequentialGroup()
                        .addComponent(countryCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(countryCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(countryNameLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(locationPaneLayout.createSequentialGroup()
                        .addGroup(locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(locationPaneLayout.createSequentialGroup()
                                .addComponent(legalAddressLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(legalAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(locationPaneLayout.createSequentialGroup()
                                .addComponent(companyPhoneLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(locationPaneLayout.createSequentialGroup()
                                .addComponent(companyEmailLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(locationPaneLayout.createSequentialGroup()
                        .addComponent(officialLanguageLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(officialLanguageTF, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                        .addComponent(timeZoneLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(timezoneMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        locationPaneLayout.setVerticalGroup(
            locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, locationPaneLayout.createSequentialGroup()
                .addComponent(locationTitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(countryCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(legalAddressLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(legalAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyPhoneLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyEmailLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(officialLanguageLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(officialLanguageTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(locationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(timezoneMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(timeZoneLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout generalSettingPanelLayout = new javax.swing.GroupLayout(generalSettingPanel);
        generalSettingPanel.setLayout(generalSettingPanelLayout);
        generalSettingPanelLayout.setHorizontalGroup(
            generalSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalSettingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(companyPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        generalSettingPanelLayout.setVerticalGroup(
            generalSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalSettingPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(companyPane, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(locationPane, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        scrollGeneralSettings.setViewportView(generalSettingPanel);

        companyTabbedPane.addTab("General Information", scrollGeneralSettings);

        scrollAccounting.setToolTipText("");
        scrollAccounting.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        accountingPanel.setBackground(new java.awt.Color(238, 244, 254));
        accountingPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        financialConfigPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        financialConfigPanel.setColor1(new java.awt.Color(247, 247, 255));
        financialConfigPanel.setColor2(new java.awt.Color(247, 247, 255));
        financialConfigPanel.setPreferredSize(new java.awt.Dimension(800, 114));

        financialTitleLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        financialTitleLb.setText("Financial Configuration");

        javax.swing.GroupLayout financialTitleLayout = new javax.swing.GroupLayout(financialTitle);
        financialTitle.setLayout(financialTitleLayout);
        financialTitleLayout.setHorizontalGroup(
            financialTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financialTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(financialTitleLb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        financialTitleLayout.setVerticalGroup(
            financialTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(financialTitleLb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        chartOfAccountNameLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartOfAccountMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        chartOfAccountLb.setText("Chart of Account");
        chartOfAccountLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        currencyCodeLb.setText("Currency Code");
        currencyCodeLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        currencyCodeMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        currencyNameLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalYearLb.setText("Fiscal year");
        fiscalYearLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalYearTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalStartDateLb.setText("Fiscal start date");
        fiscalStartDateLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalStartDateChooser.setDateFormatString("yyyy.MM.dd HH:mm:ss");
        fiscalStartDateChooser.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalEndDateLb.setText("Fiscal end date");
        fiscalEndDateLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        fiscalEndDateChooser.setDateFormatString("yyyy.MM.dd HH:mm:ss");
        fiscalEndDateChooser.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        javax.swing.GroupLayout financialConfigPanelLayout = new javax.swing.GroupLayout(financialConfigPanel);
        financialConfigPanel.setLayout(financialConfigPanelLayout);
        financialConfigPanelLayout.setHorizontalGroup(
            financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(financialTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(financialConfigPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fiscalYearLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalStartDateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartOfAccountLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(financialConfigPanelLayout.createSequentialGroup()
                        .addGroup(financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chartOfAccountMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currencyCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currencyNameLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chartOfAccountNameLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(financialConfigPanelLayout.createSequentialGroup()
                        .addGroup(financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fiscalYearTF, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(financialConfigPanelLayout.createSequentialGroup()
                                .addComponent(fiscalStartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fiscalEndDateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(fiscalEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 92, Short.MAX_VALUE)))
                .addContainerGap())
        );
        financialConfigPanelLayout.setVerticalGroup(
            financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financialConfigPanelLayout.createSequentialGroup()
                .addComponent(financialTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chartOfAccountLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartOfAccountMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartOfAccountNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currencyCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyCodeMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fiscalYearLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalYearTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(financialConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(fiscalStartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalEndDateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalStartDateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fiscalEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        taxationPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        taxationPanel.setColor1(new java.awt.Color(247, 247, 255));
        taxationPanel.setColor2(new java.awt.Color(247, 247, 255));
        taxationPanel.setPreferredSize(new java.awt.Dimension(800, 114));

        taxationTitleLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        taxationTitleLb.setText("Taxation");

        javax.swing.GroupLayout taxationTitlePanelLayout = new javax.swing.GroupLayout(taxationTitlePanel);
        taxationTitlePanel.setLayout(taxationTitlePanelLayout);
        taxationTitlePanelLayout.setHorizontalGroup(
            taxationTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taxationTitlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taxationTitleLb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        taxationTitlePanelLayout.setVerticalGroup(
            taxationTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taxationTitleLb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        taxSchemaMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        taxSchemaNamelb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        taxSchemaLb.setText("Tax Schema");
        taxSchemaLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        accountingStandardLb.setText("Accounting standard");
        accountingStandardLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        accountingStandardCombo.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartMultiCurrencyCheckbox.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        chartMultiCurrencyCheckbox.setText("Multi - currency");

        javax.swing.GroupLayout taxationPanelLayout = new javax.swing.GroupLayout(taxationPanel);
        taxationPanel.setLayout(taxationPanelLayout);
        taxationPanelLayout.setHorizontalGroup(
            taxationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taxationTitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(taxationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(taxationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(taxationPanelLayout.createSequentialGroup()
                        .addComponent(accountingStandardLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(accountingStandardCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(taxationPanelLayout.createSequentialGroup()
                        .addComponent(taxSchemaLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(taxSchemaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(taxationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(taxationPanelLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(chartMultiCurrencyCheckbox))
                    .addGroup(taxationPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(taxSchemaNamelb, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        taxationPanelLayout.setVerticalGroup(
            taxationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taxationPanelLayout.createSequentialGroup()
                .addComponent(taxationTitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(taxationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(accountingStandardLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(taxationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(accountingStandardCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chartMultiCurrencyCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(taxationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(taxSchemaMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taxSchemaLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taxSchemaNamelb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout accountingPanelLayout = new javax.swing.GroupLayout(accountingPanel);
        accountingPanel.setLayout(accountingPanelLayout);
        accountingPanelLayout.setHorizontalGroup(
            accountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financialConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taxationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        accountingPanelLayout.setVerticalGroup(
            accountingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountingPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(financialConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taxationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        scrollAccounting.setViewportView(accountingPanel);

        companyTabbedPane.addTab("Accounting and Tax Settings", scrollAccounting);

        scrollCostAccounting.setToolTipText("");
        scrollCostAccounting.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        ficoPanel.setBackground(new java.awt.Color(238, 244, 254));
        ficoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cardsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        cardsPanel.setColor1(new java.awt.Color(247, 247, 255));
        cardsPanel.setColor2(new java.awt.Color(247, 247, 255));
        cardsPanel.setPreferredSize(new java.awt.Dimension(800, 114));

        COCompanyAssociationTitleLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        COCompanyAssociationTitleLb.setText("CO Company Association");

        javax.swing.GroupLayout COCardTitlePanelLayout = new javax.swing.GroupLayout(COCardTitlePanel);
        COCardTitlePanel.setLayout(COCardTitlePanelLayout);
        COCardTitlePanelLayout.setHorizontalGroup(
            COCardTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(COCardTitlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(COCompanyAssociationTitleLb)
                .addContainerGap(646, Short.MAX_VALUE))
        );
        COCardTitlePanelLayout.setVerticalGroup(
            COCardTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(COCompanyAssociationTitleLb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        cOAssociationPanel.setBackground(new java.awt.Color(247, 247, 255));

        javax.swing.GroupLayout cardsPanelLayout = new javax.swing.GroupLayout(cardsPanel);
        cardsPanel.setLayout(cardsPanelLayout);
        cardsPanelLayout.setHorizontalGroup(
            cardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(COCardTitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(cardsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cOAssociationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        cardsPanelLayout.setVerticalGroup(
            cardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardsPanelLayout.createSequentialGroup()
                .addComponent(COCardTitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cOAssociationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ficoPanelLayout = new javax.swing.GroupLayout(ficoPanel);
        ficoPanel.setLayout(ficoPanelLayout);
        ficoPanelLayout.setHorizontalGroup(
            ficoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ficoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ficoPanelLayout.setVerticalGroup(
            ficoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ficoPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cardsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        scrollCostAccounting.setViewportView(ficoPanel);

        companyTabbedPane.addTab("Cost Accounting Link (FI–CO)", scrollCostAccounting);

        scrollStatusAudit.setToolTipText("");
        scrollStatusAudit.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        statusAuditPanel.setBackground(new java.awt.Color(238, 244, 254));
        statusAuditPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        companyStatusPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        companyStatusPanel.setColor1(new java.awt.Color(247, 247, 255));
        companyStatusPanel.setColor2(new java.awt.Color(247, 247, 255));
        companyStatusPanel.setPreferredSize(new java.awt.Dimension(800, 114));

        companyStatusTitleLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        companyStatusTitleLb.setText("Company Status");

        javax.swing.GroupLayout companyStatusTitlePanelLayout = new javax.swing.GroupLayout(companyStatusTitlePanel);
        companyStatusTitlePanel.setLayout(companyStatusTitlePanelLayout);
        companyStatusTitlePanelLayout.setHorizontalGroup(
            companyStatusTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyStatusTitlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(companyStatusTitleLb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        companyStatusTitlePanelLayout.setVerticalGroup(
            companyStatusTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(companyStatusTitleLb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        createdAtLb.setText("Created at");
        createdAtLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        createdByLb.setText("Created by");
        createdByLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyCreatedByTF.setEditable(false);
        companyCreatedByTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyCreatedAtTF.setEditable(false);
        companyCreatedAtTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        updatedAtLb.setText("Updated at");
        updatedAtLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyUpdatedAtTF.setEditable(false);
        companyUpdatedAtTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        chartUpdatedByLb.setText("Updated by");
        chartUpdatedByLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyUpdatedByTF.setEditable(false);
        companyUpdatedByTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyStatusCombo.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyStatusLb.setText("Status");
        companyStatusLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        javax.swing.GroupLayout companyStatusPanelLayout = new javax.swing.GroupLayout(companyStatusPanel);
        companyStatusPanel.setLayout(companyStatusPanelLayout);
        companyStatusPanelLayout.setHorizontalGroup(
            companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(companyStatusTitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(companyStatusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(companyStatusPanelLayout.createSequentialGroup()
                        .addGroup(companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createdAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createdByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(companyCreatedAtTF, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(companyCreatedByTF, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(companyStatusPanelLayout.createSequentialGroup()
                        .addComponent(companyStatusLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(companyStatusCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(companyStatusPanelLayout.createSequentialGroup()
                        .addComponent(chartUpdatedByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(companyUpdatedByTF, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(companyStatusPanelLayout.createSequentialGroup()
                        .addComponent(updatedAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(companyUpdatedAtTF, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        companyStatusPanelLayout.setVerticalGroup(
            companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, companyStatusPanelLayout.createSequentialGroup()
                .addComponent(companyStatusTitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createdAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyCreatedAtTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyUpdatedAtTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chartUpdatedByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(companyUpdatedByTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(createdByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(companyCreatedByTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(companyStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(companyStatusLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyStatusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout statusAuditPanelLayout = new javax.swing.GroupLayout(statusAuditPanel);
        statusAuditPanel.setLayout(statusAuditPanelLayout);
        statusAuditPanelLayout.setHorizontalGroup(
            statusAuditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusAuditPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(companyStatusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        statusAuditPanelLayout.setVerticalGroup(
            statusAuditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusAuditPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(companyStatusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        scrollStatusAudit.setViewportView(statusAuditPanel);

        companyTabbedPane.addTab("Status and Audit", scrollStatusAudit);

        scrollRelatedDocuments.setToolTipText("");
        scrollRelatedDocuments.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        relatedDocumentPanel.setBackground(new java.awt.Color(238, 244, 254));
        relatedDocumentPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        attachmentPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        attachmentPanel.setColor1(new java.awt.Color(247, 247, 255));
        attachmentPanel.setColor2(new java.awt.Color(247, 247, 255));
        attachmentPanel.setPreferredSize(new java.awt.Dimension(800, 114));

        attachmentTitleLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        attachmentTitleLb.setText("Attachments");

        javax.swing.GroupLayout attachmentTitlePanelLayout = new javax.swing.GroupLayout(attachmentTitlePanel);
        attachmentTitlePanel.setLayout(attachmentTitlePanelLayout);
        attachmentTitlePanelLayout.setHorizontalGroup(
            attachmentTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attachmentTitlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(attachmentTitleLb)
                .addContainerGap(716, Short.MAX_VALUE))
        );
        attachmentTitlePanelLayout.setVerticalGroup(
            attachmentTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(attachmentTitleLb, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        constitutionActTF.setEditable(false);

        addConstitutionActButton.setIcon(new CustomSVGIcon("/icons/svg/upload.svg", new Dimension(20,20))
        );

        constitutionActLb.setText("Constitution Act");
        constitutionActLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        rucCertificateLb.setText("Ruc Certificate");
        rucCertificateLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        rucCertificateTF.setEditable(false);

        addRucCertificateButton.setIcon(new CustomSVGIcon("/icons/svg/upload.svg", new Dimension(20,20)));

        legalPowerTF.setEditable(false);

        addLegalPowerButton.setIcon(new CustomSVGIcon("/icons/svg/upload.svg", new Dimension(20,20)));

        legalPowerLb.setText("Legal Power");
        legalPowerLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        municipalLicenseTF.setEditable(false);

        addMunicipalLicenseButton.setIcon(new CustomSVGIcon("/icons/svg/upload.svg", new Dimension(20,20)));

        municipalLicenseLb.setText("Municipal License");
        municipalLicenseLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        representativeIDTF.setEditable(false);

        addRepresentativeIDButton.setIcon(new CustomSVGIcon("/icons/svg/upload.svg", new Dimension(20,20)));

        representaIDLb.setText("Representative ID");
        representaIDLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        entityRegistrationLb.setText("Entity Registration");
        entityRegistrationLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        entityRegistrationTF.setEditable(false);

        addEntityRegistrationButton.setIcon(new CustomSVGIcon("/icons/svg/upload.svg", new Dimension(20,20)));

        otherLb.setText("Other");
        otherLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        otherTF.setEditable(false);

        addOtherButton.setIcon(new CustomSVGIcon("/icons/svg/upload.svg", new Dimension(20,20)));

        removeConstitutionActButton.setIcon(new CustomSVGIcon("/icons/svg/trash.svg", new Dimension(20,20))
        );

        removeRepresentativeIDButton.setIcon(new CustomSVGIcon("/icons/svg/trash.svg", new Dimension(20,20)));

        removeRucCertificateButton.setIcon(new CustomSVGIcon("/icons/svg/trash.svg", new Dimension(20,20)));

        removeEntityRegistrationButton.setIcon(new CustomSVGIcon("/icons/svg/trash.svg", new Dimension(20,20)));

        removeLegalPowerButton.setIcon(new CustomSVGIcon("/icons/svg/trash.svg", new Dimension(20,20)));

        removeOtherButton.setIcon(new CustomSVGIcon("/icons/svg/trash.svg", new Dimension(20,20)));

        removeMunicipalLicenseButton.setIcon(new CustomSVGIcon("/icons/svg/trash.svg", new Dimension(20,20)));

        viewConstitutionActButton.setIcon(new CustomSVGIcon("/icons/svg/pdf_icon.svg", new Dimension(20,20))
        );

        viewRepresentativeIDButton.setIcon(new CustomSVGIcon("/icons/svg/pdf_icon.svg", new Dimension(20,20)));

        viewRucCertificateButton.setIcon(new CustomSVGIcon("/icons/svg/pdf_icon.svg", new Dimension(20,20)));

        viewEntityRegistrationButton.setIcon(new CustomSVGIcon("/icons/svg/pdf_icon.svg", new Dimension(20,20)));

        viewLegalPowerButton.setIcon(new CustomSVGIcon("/icons/svg/pdf_icon.svg", new Dimension(20,20)));

        viewOtherButton.setIcon(new CustomSVGIcon("/icons/svg/pdf_icon.svg", new Dimension(20,20)));

        viewMunicipalLicenseButton.setIcon(new CustomSVGIcon("/icons/svg/pdf_icon.svg", new Dimension(20,20)));

        javax.swing.GroupLayout attachmentPanelLayout = new javax.swing.GroupLayout(attachmentPanel);
        attachmentPanel.setLayout(attachmentPanelLayout);
        attachmentPanelLayout.setHorizontalGroup(
            attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(attachmentTitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(attachmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(attachmentPanelLayout.createSequentialGroup()
                        .addComponent(constitutionActLb, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(constitutionActTF, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addConstitutionActButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(attachmentPanelLayout.createSequentialGroup()
                        .addComponent(rucCertificateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(rucCertificateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addRucCertificateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(attachmentPanelLayout.createSequentialGroup()
                        .addComponent(legalPowerLb, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(legalPowerTF, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addLegalPowerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(attachmentPanelLayout.createSequentialGroup()
                        .addComponent(municipalLicenseLb, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(municipalLicenseTF, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addMunicipalLicenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(attachmentPanelLayout.createSequentialGroup()
                        .addComponent(representaIDLb, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(representativeIDTF, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addRepresentativeIDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(attachmentPanelLayout.createSequentialGroup()
                        .addComponent(entityRegistrationLb, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(entityRegistrationTF, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addEntityRegistrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(attachmentPanelLayout.createSequentialGroup()
                        .addComponent(otherLb, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(otherTF, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addOtherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeConstitutionActButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeRucCertificateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeLegalPowerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeMunicipalLicenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeRepresentativeIDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeEntityRegistrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeOtherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewConstitutionActButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewRucCertificateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewLegalPowerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewMunicipalLicenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewRepresentativeIDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewEntityRegistrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewOtherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        attachmentPanelLayout.setVerticalGroup(
            attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attachmentPanelLayout.createSequentialGroup()
                .addComponent(attachmentTitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(constitutionActTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addConstitutionActButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(constitutionActLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeConstitutionActButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewConstitutionActButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(rucCertificateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addRucCertificateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rucCertificateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeRucCertificateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewRucCertificateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(legalPowerTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addLegalPowerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(legalPowerLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeLegalPowerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewLegalPowerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(municipalLicenseTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMunicipalLicenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(municipalLicenseLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeMunicipalLicenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewMunicipalLicenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(representativeIDTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addRepresentativeIDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(representaIDLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeRepresentativeIDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewRepresentativeIDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(entityRegistrationTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addEntityRegistrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entityRegistrationLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeEntityRegistrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewEntityRegistrationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(otherTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addOtherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otherLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeOtherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewOtherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout relatedDocumentPanelLayout = new javax.swing.GroupLayout(relatedDocumentPanel);
        relatedDocumentPanel.setLayout(relatedDocumentPanelLayout);
        relatedDocumentPanelLayout.setHorizontalGroup(
            relatedDocumentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, relatedDocumentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(attachmentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        relatedDocumentPanelLayout.setVerticalGroup(
            relatedDocumentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(relatedDocumentPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(attachmentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        scrollRelatedDocuments.setViewportView(relatedDocumentPanel);

        companyTabbedPane.addTab("Related Documents", scrollRelatedDocuments);

        companyCodeLb.setText("Code");
        companyCodeLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyCodeTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyNameLb.setText("Company Name");
        companyNameLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        companyNameTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

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
                                .addComponent(companyCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(companyCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addComponent(companyNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(companyNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(companyTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyCodeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyTabbedPane)
                .addContainerGap())
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 COCardTitlePanel;
    private javax.swing.JLabel COCompanyAssociationTitleLb;
    private javax.swing.JPanel accountingPanel;
    private javax.swing.JComboBox<ComboItem> accountingStandardCombo;
    private corex.suite.JLabelHQUnderlined accountingStandardLb;
    private javax.swing.JButton addConstitutionActButton;
    private javax.swing.JButton addEntityRegistrationButton;
    private javax.swing.JButton addLegalPowerButton;
    private javax.swing.JButton addMunicipalLicenseButton;
    private javax.swing.JButton addOtherButton;
    private javax.swing.JButton addRepresentativeIDButton;
    private javax.swing.JButton addRucCertificateButton;
    private corex.suite.JPanelRoundedGradient attachmentPanel;
    private javax.swing.JLabel attachmentTitleLb;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 attachmentTitlePanel;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private corex.suite.JLabelHQUnderlined businessTypeLb;
    private javax.swing.JTextField businessTypeTF;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COAssociationPanel cOAssociationPanel;
    private corex.suite.JPanelRoundedGradient cardsPanel;
    private javax.swing.JCheckBox chartMultiCurrencyCheckbox;
    private corex.suite.JLabelHQUnderlined chartOfAccountLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode chartOfAccountMatchCode;
    private javax.swing.JLabel chartOfAccountNameLb;
    private corex.suite.JLabelHQUnderlined chartUpdatedByLb;
    private corex.suite.JLabelHQUnderlined companyCodeLb;
    private javax.swing.JTextField companyCodeTF;
    private javax.swing.JTextField companyCreatedAtTF;
    private javax.swing.JTextField companyCreatedByTF;
    private corex.suite.JLabelHQUnderlined companyEmailLb;
    private javax.swing.JLabel companyIdentificationLb;
    private corex.suite.JLabelHQUnderlined companyNameLb;
    private javax.swing.JTextField companyNameTF;
    private corex.suite.JPanelRoundedGradient companyPane;
    private corex.suite.JLabelHQUnderlined companyPhoneLb;
    private javax.swing.JComboBox<ComboItem> companyStatusCombo;
    private corex.suite.JLabelHQUnderlined companyStatusLb;
    private corex.suite.JPanelRoundedGradient companyStatusPanel;
    private javax.swing.JLabel companyStatusTitleLb;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 companyStatusTitlePanel;
    public static javax.swing.JTabbedPane companyTabbedPane;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 companyTitlePanel;
    private javax.swing.JTextField companyUpdatedAtTF;
    private javax.swing.JTextField companyUpdatedByTF;
    private corex.suite.JLabelHQUnderlined constitutionActLb;
    private javax.swing.JTextField constitutionActTF;
    private corex.suite.JLabelHQUnderlined countryCodeLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode countryCodeMatchCode;
    private javax.swing.JLabel countryNameLb;
    private corex.suite.JLabelHQUnderlined createdAtLb;
    private corex.suite.JLabelHQUnderlined createdByLb;
    private corex.suite.JLabelHQUnderlined currencyCodeLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode currencyCodeMatchCode;
    private javax.swing.JLabel currencyNameLb;
    private javax.swing.JTextField emailTF;
    private corex.suite.JLabelHQUnderlined entityRegistrationLb;
    private javax.swing.JTextField entityRegistrationTF;
    private javax.swing.JPanel ficoPanel;
    private corex.suite.JPanelRoundedGradient financialConfigPanel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 financialTitle;
    private javax.swing.JLabel financialTitleLb;
    private com.toedter.calendar.JDateChooser fiscalEndDateChooser;
    private corex.suite.JLabelHQUnderlined fiscalEndDateLb;
    private com.toedter.calendar.JDateChooser fiscalStartDateChooser;
    private corex.suite.JLabelHQUnderlined fiscalStartDateLb;
    private corex.suite.JLabelHQUnderlined fiscalYearLb;
    private javax.swing.JTextField fiscalYearTF;
    private javax.swing.JPanel generalSettingPanel;
    private corex.suite.JLabelHQUnderlined industryClassificationLb;
    private javax.swing.JTextField industryClassificationTF;
    private javax.swing.JToolBar jToolBar1;
    private corex.suite.JLabelHQUnderlined legalAddressLb;
    private javax.swing.JTextField legalAddressTF;
    private corex.suite.JLabelHQUnderlined legalNameLb;
    private javax.swing.JTextField legalNameTF;
    private corex.suite.JLabelHQUnderlined legalPowerLb;
    private javax.swing.JTextField legalPowerTF;
    private javax.swing.JLabel locationContactTitleLb;
    private corex.suite.JPanelRoundedGradient locationPane;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 locationTitlePanel;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private corex.suite.JLabelHQUnderlined municipalLicenseLb;
    private javax.swing.JTextField municipalLicenseTF;
    private corex.suite.JLabelHQUnderlined officialLanguageLb;
    private javax.swing.JTextField officialLanguageTF;
    private corex.suite.JLabelHQUnderlined otherLb;
    private javax.swing.JTextField otherTF;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private javax.swing.JTextField phoneTF;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private javax.swing.JPanel relatedDocumentPanel;
    private javax.swing.JButton removeConstitutionActButton;
    private javax.swing.JButton removeEntityRegistrationButton;
    private javax.swing.JButton removeLegalPowerButton;
    private javax.swing.JButton removeMunicipalLicenseButton;
    private javax.swing.JButton removeOtherButton;
    private javax.swing.JButton removeRepresentativeIDButton;
    private javax.swing.JButton removeRucCertificateButton;
    private corex.suite.JLabelHQUnderlined representaIDLb;
    private javax.swing.JTextField representativeIDTF;
    private corex.suite.JLabelHQUnderlined rucCertificateLb;
    private javax.swing.JTextField rucCertificateTF;
    private javax.swing.JScrollPane scrollAccounting;
    private javax.swing.JScrollPane scrollCostAccounting;
    private javax.swing.JScrollPane scrollGeneralSettings;
    private javax.swing.JScrollPane scrollRelatedDocuments;
    private javax.swing.JScrollPane scrollStatusAudit;
    private javax.swing.JPanel statusAuditPanel;
    private corex.suite.JLabelHQUnderlined taxSchemaLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode taxSchemaMatchCode;
    private javax.swing.JLabel taxSchemaNamelb;
    private corex.suite.JPanelRoundedGradient taxationPanel;
    private javax.swing.JLabel taxationTitleLb;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 taxationTitlePanel;
    private corex.suite.JLabelHQUnderlined timeZoneLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode timezoneMatchCode;
    private javax.swing.JPanel topPanel;
    private corex.suite.JLabelHQUnderlined updatedAtLb;
    private javax.swing.JButton viewConstitutionActButton;
    private javax.swing.JButton viewEntityRegistrationButton;
    private javax.swing.JButton viewLegalPowerButton;
    private javax.swing.JButton viewMunicipalLicenseButton;
    private javax.swing.JButton viewOtherButton;
    private javax.swing.JButton viewRepresentativeIDButton;
    private javax.swing.JButton viewRucCertificateButton;
    // End of variables declaration//GEN-END:variables

}
