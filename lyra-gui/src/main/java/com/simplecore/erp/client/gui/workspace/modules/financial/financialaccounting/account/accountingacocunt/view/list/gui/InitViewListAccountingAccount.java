package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.gui;

import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.controllers.workspace.TaskPanel;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.PastedListInterfaces;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.panelcontroller.AccountingAccountPanelController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.ListButtonService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.QueryExecutorButtonHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.QueryService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.TextFieldFilterService;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterVarchar;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

/**
 * This panel is responsible for managing the accounting account query and data
 * filtering GUI. It integrates dynamic filtering logic, custom data injection,
 * session control and request execution.
 *
 * All responsibilities are clearly separated using service classes injected
 * externally, following a modular and maintainable architecture.
 */
public class InitViewListAccountingAccount extends JPanel implements TransactionPanel, TaskPanel {

    // Translation helpers to support internationalization (i18n)
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private String transactionCode;
    // Centralized system notifier for displaying warnings, info, etc.
    private final SystemMessages notificator;

    // Modular services injected from the external initializer (Controller)
    private ListButtonService buttonServices;
    private TextFieldFilterService textFieldService;
    private QueryService queryService;
    private PastedListInterfaces pastedListInterfaces;
    private QueryExecutorButtonHandler executorButtonHandler;

    /**
     * Constructor initializes component appearance and internal translators.
     * All logic-related services are injected later via `injectServices()`.
     */
    public InitViewListAccountingAccount() {
        initComponents(); // Mostly auto-generated layout configs (NetBeans)
        septupUIComponents(); // Additional UI customizations (icons, borders, listeners, etc.)
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.notificator = new SystemMessages();
    }

    /**
     * Injects all required services for this panel. This method allows for
     * clean separation of concerns and testable components.
     *
     * @param interfaces Responsible for managing shared data lists and
     * communication interfaces.
     * @param textFieldService Manages user-typed filters and mutual exclusion
     * with selected filters.
     * @param buttonService Opens the dialog that allows users to select filters
     * using buttons and tables.
     * @param queryService Builds the filter objects dynamically based on
     * current UI state.
     * @param handler Executes the query by preparing and sending the request to
     * the server.
     */

    public void injectServices(PastedListInterfaces interfaces,
            TextFieldFilterService textFieldService,
            ListButtonService buttonService,
            QueryService queryService,
            QueryExecutorButtonHandler handler) {
        this.pastedListInterfaces = interfaces;
        this.textFieldService = textFieldService;
        this.buttonServices = buttonService;
        this.queryService = queryService;
        this.executorButtonHandler = handler;
    }

    /**
     * Initializes the panel controller with session and stream data.This
 method is called by the workspace loader after instantiation.
     *
     * @param transactionCode
     * @param session Active session containing user ID and session ID.
     * @param output Output stream to send requests to the application server.
     * @param input Input stream to receive responses from the server.
     */
    @Override
    public void initialize(String transactionCode,ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.transactionCode = transactionCode;
        // Creates and starts the controller that orchestrates all panel behavior.
        AccountingAccountPanelController controller = new AccountingAccountPanelController(this, session, output, input,transactionCode);
        controller.initializePanel(); // Typically triggers component listeners, UI bindings, etc.
    }

    /**
     * Sets up the necessary UI components for the current screen. This method
     * initializes and configures the filter buttons, applies document filters
     * to the input fields, and sets up the next button.
     */
    private void septupUIComponents() {
        // Sets up filter buttons on the UI
        setupFilterButtons();

        // Applies document filters to various fields to restrict input
        applyDocumentFiltersToFields();

        // Configures the next button's icon
        setupNextButton();
    }

    /**
     * Applies specific document filters to input fields to restrict the type
     * and length of input. Filters are applied to JTextFields based on
     * predefined conditions such as length and character restrictions.
     *
     * The filters used are: - DocFilterVarcharWithoutSpace(45) for fields that
     * accept up to 45 characters and disallow spaces. -
     * DocFilterVarcharWithoutSpace(50) for fields that accept up to 50
     * characters and disallow spaces. - DocumentFilterVarchar(100) for fields
     * that accept up to 100 characters without any restrictions on spaces.
     *
     * These filters are applied to the relevant JTextField components.
     */
    private void applyDocumentFiltersToFields() {
        // Create filters for different field lengths
        DocumentFilter filter45 = new DocFilterVarcharWithoutSpace(45);
        DocumentFilter filter50 = new DocFilterVarcharWithoutSpace(50);
        DocumentFilter filter100 = new DocumentFilterVarchar(100);

        // Create a map to associate JTextField components with their respective filters
        Map<JTextField, DocumentFilter> fieldFilters = new HashMap<>();

        // Assign filters to the relevant text fields
        fieldFilters.put(accountNumberFromMatchCode.getTextField(), filter50);
        fieldFilters.put(accountNumberToMatchCode.getTextField(), filter50);
        fieldFilters.put(accountNameFromTextField, filter100);
        fieldFilters.put(accountNameToTextField, filter100);
        fieldFilters.put(parentFromMatchCode.getTextField(), filter50);
        fieldFilters.put(parentToMatchCode.getTextField(), filter50);
        fieldFilters.put(subclassFromMatchCode.getTextField(), filter50);
        fieldFilters.put(subclassToMatchCode.getTextField(), filter50);
        fieldFilters.put(modelFromMatchCode.getTextField(), filter100);
        fieldFilters.put(modelToMatchCode.getTextField(), filter100);
        fieldFilters.put(modelStateFromMatchCode.getTextField(), filter45);
        fieldFilters.put(modelStateToMatchCode.getTextField(), filter45);
        fieldFilters.put(createdByFromMatchCode.getTextField(), filter45);
        fieldFilters.put(createdByToMatchCode.getTextField(), filter45);
        fieldFilters.put(updatedByFromMatchCode.getTextField(), filter45);
        fieldFilters.put(updatedByToMatchCode.getTextField(), filter45);

        // Apply filters to each JTextField by setting their document filter
        fieldFilters.forEach((field, filter)
                -> ((AbstractDocument) field.getDocument()).setDocumentFilter(filter)
        );
    }

    /**
     * Sets up filter buttons by adding a filter icon to each relevant button.
     * Each filter button represents a field that can be filtered by the user.
     *
     * The method calls `setAddFilterIcon` for each filter button to assign the
     * appropriate icon.
     */
    private void setupFilterButtons() {
        // Set the filter icon for each filter button
        setAddFilterIcon(accountNameFilterButton);
        setAddFilterIcon(accountNumberFilterButton);
        setAddFilterIcon(parentFilterButton);
        setAddFilterIcon(subclassFilterButton);
        setAddFilterIcon(modelFilterButton);
        setAddFilterIcon(modelStateFilterButton);
        setAddFilterIcon(createdByFilterButton);
        setAddFilterIcon(createdAtAccountFilterButton);
        setAddFilterIcon(updatedByAccountFilterButton);
        setAddFilterIcon(updatedAtAccountFilterButton);
    }
    /**
     * Sets the specified icon to the given button.
     *
     * This method updates the icon of the button to represent a filter action.
     * The icon is an SVG image loaded from the specified path.
     *
     * @param button The button to which the filter icon will be set.
     */
    private void setAddFilterIcon(JButton button) {
        // Set a filter icon on the provided button
        button.setIcon(new CustomSVGIcon("/icons/svg/filter_add.svg", new Dimension(24, 24)));
    }
    /**
     * Configures the next button's icon to indicate the "next" action. The icon
     * is set to an SVG icon representing the next step in a sequence or
     * workflow.
     */
    private void setupNextButton() {
        // Set the "next" icon for the next button
        nextButton.setIcon(new CustomSVGIcon("/icons/svg/next.svg", new Dimension(24, 24)));
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        panelTitle = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        moduleTitleLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        buttonsPanel = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        jToolBar1 = new javax.swing.JToolBar();
        nextButton = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        bodyPanel = new corex.suite.JPanelRoundedGradient();
        scrollPane = new javax.swing.JScrollPane();
        panelScroll = new corex.suite.JPanelRoundedGradient();
        hierarchyPanel = new corex.suite.JPanelRoundedGradient();
        panelGradient32 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        jLabel2 = new javax.swing.JLabel();
        accountNumberFromMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        accountNumberLb = new corex.suite.JLabelHQUnderlined();
        accountNumberToMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        to1Lb = new corex.suite.JLabelHQUnderlined();
        accountNameLb = new corex.suite.JLabelHQUnderlined();
        to2Lb = new corex.suite.JLabelHQUnderlined();
        accountNameFromTextField = new javax.swing.JTextField();
        accountNameToTextField = new javax.swing.JTextField();
        accountNumberFilterButton = new javax.swing.JButton();
        accountNameFilterButton = new javax.swing.JButton();
        to3Lb = new corex.suite.JLabelHQUnderlined();
        parentFilterButton = new javax.swing.JButton();
        parentFromMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        accountParentLb = new corex.suite.JLabelHQUnderlined();
        parentToMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        to4Lb = new corex.suite.JLabelHQUnderlined();
        subclassFilterButton = new javax.swing.JButton();
        subclassFromMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        accountSubclassLb = new corex.suite.JLabelHQUnderlined();
        subclassToMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        hierarchyPanel1 = new corex.suite.JPanelRoundedGradient();
        panelGradient33 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        jLabel3 = new javax.swing.JLabel();
        createdAtLb = new corex.suite.JLabelHQUnderlined();
        to8Lb = new corex.suite.JLabelHQUnderlined();
        updatedByToMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        updatedByLb = new corex.suite.JLabelHQUnderlined();
        updatedByFromMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        to9Lb = new corex.suite.JLabelHQUnderlined();
        updatedAtLb = new corex.suite.JLabelHQUnderlined();
        to10Lb = new corex.suite.JLabelHQUnderlined();
        createdByLb = new corex.suite.JLabelHQUnderlined();
        createdByFromMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        to7Lb = new corex.suite.JLabelHQUnderlined();
        createdByToMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        updatedByAccountFilterButton = new javax.swing.JButton();
        updatedAtAccountFilterButton = new javax.swing.JButton();
        createdAtAccountFilterButton = new javax.swing.JButton();
        createdByFilterButton = new javax.swing.JButton();
        createdAtFromDateChooser = new com.toedter.calendar.JDateChooser();
        createdAtToDateChooser = new com.toedter.calendar.JDateChooser();
        updatedAtFromDateChooser = new com.toedter.calendar.JDateChooser();
        updatedAtToDateChooser = new com.toedter.calendar.JDateChooser();
        hierarchyPanel2 = new corex.suite.JPanelRoundedGradient();
        panelGradient34 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        jLabel4 = new javax.swing.JLabel();
        accountStatusLb = new corex.suite.JLabelHQUnderlined();
        statusAccountComboBox = new javax.swing.JComboBox<>();
        isClosedAccountCheckBox = new javax.swing.JCheckBox();
        hierarchyPanel3 = new corex.suite.JPanelRoundedGradient();
        panelGradient35 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        jLabel5 = new javax.swing.JLabel();
        modelNameLb = new corex.suite.JLabelHQUnderlined();
        modelFromMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        to5Lb = new corex.suite.JLabelHQUnderlined();
        modelToMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        modelFilterButton = new javax.swing.JButton();
        modelStatusLb = new corex.suite.JLabelHQUnderlined();
        modelStateLb = new corex.suite.JLabelHQUnderlined();
        modelStateFromMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        to6Lb = new corex.suite.JLabelHQUnderlined();
        modelStateToMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        modelStateFilterButton = new javax.swing.JButton();
        modelStatusCheckBox = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("View List of Accounting Account");
        moduleTitleLabel.setFont(new java.awt.Font("IBM Plex Sans", 1, 18)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1010, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
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

        nextButton.setFocusable(false);
        nextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(nextButton);

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(853, Short.MAX_VALUE))
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
        bodyPanel.setColor2(new java.awt.Color(247, 247, 255));

        panelScroll.setColor1(new java.awt.Color(247, 247, 255));
        panelScroll.setColor2(new java.awt.Color(206, 223, 239));

        hierarchyPanel.setColor1(new java.awt.Color(247, 247, 255));
        hierarchyPanel.setColor2(new java.awt.Color(247, 247, 255));

        jLabel2.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        jLabel2.setText("General");

        javax.swing.GroupLayout panelGradient32Layout = new javax.swing.GroupLayout(panelGradient32);
        panelGradient32.setLayout(panelGradient32Layout);
        panelGradient32Layout.setHorizontalGroup(
            panelGradient32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelGradient32Layout.setVerticalGroup(
            panelGradient32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        accountNumberFromMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        accountNumberLb.setForeground(new java.awt.Color(51, 51, 51));
        accountNumberLb.setText("Account Number");
        accountNumberLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        accountNumberToMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        to1Lb.setForeground(new java.awt.Color(51, 51, 51));
        to1Lb.setText("To");
        to1Lb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        accountNameLb.setForeground(new java.awt.Color(51, 51, 51));
        accountNameLb.setText("Account Name");
        accountNameLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        to2Lb.setForeground(new java.awt.Color(51, 51, 51));
        to2Lb.setText("To");
        to2Lb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        accountNameFromTextField.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        accountNameToTextField.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        accountNumberFilterButton.setBackground(new java.awt.Color(226, 210, 144));
        accountNumberFilterButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 176, 176)));

        accountNameFilterButton.setBackground(new java.awt.Color(226, 210, 144));
        accountNameFilterButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 176, 176)));

        to3Lb.setForeground(new java.awt.Color(51, 51, 51));
        to3Lb.setText("To");
        to3Lb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        parentFilterButton.setBackground(new java.awt.Color(226, 210, 144));
        parentFilterButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 176, 176)));

        parentFromMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        accountParentLb.setForeground(new java.awt.Color(51, 51, 51));
        accountParentLb.setText("Parent");
        accountParentLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        parentToMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        to4Lb.setForeground(new java.awt.Color(51, 51, 51));
        to4Lb.setText("To");
        to4Lb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        subclassFilterButton.setBackground(new java.awt.Color(226, 210, 144));
        subclassFilterButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 176, 176)));

        subclassFromMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        accountSubclassLb.setForeground(new java.awt.Color(51, 51, 51));
        accountSubclassLb.setText("Subclass");
        accountSubclassLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        subclassToMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        javax.swing.GroupLayout hierarchyPanelLayout = new javax.swing.GroupLayout(hierarchyPanel);
        hierarchyPanel.setLayout(hierarchyPanelLayout);
        hierarchyPanelLayout.setHorizontalGroup(
            hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradient32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(hierarchyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hierarchyPanelLayout.createSequentialGroup()
                        .addComponent(accountNumberLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(accountNumberFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hierarchyPanelLayout.createSequentialGroup()
                        .addComponent(accountNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(accountNameFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hierarchyPanelLayout.createSequentialGroup()
                        .addComponent(accountParentLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(parentFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hierarchyPanelLayout.createSequentialGroup()
                        .addComponent(accountSubclassLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(subclassFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(hierarchyPanelLayout.createSequentialGroup()
                            .addComponent(to3Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(parentToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(86, 86, 86)
                            .addComponent(parentFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(hierarchyPanelLayout.createSequentialGroup()
                            .addComponent(to1Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(accountNumberToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(86, 86, 86)
                            .addComponent(accountNumberFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(hierarchyPanelLayout.createSequentialGroup()
                            .addComponent(to2Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(accountNameToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(accountNameFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(hierarchyPanelLayout.createSequentialGroup()
                        .addComponent(to4Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(subclassToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(subclassFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        hierarchyPanelLayout.setVerticalGroup(
            hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hierarchyPanelLayout.createSequentialGroup()
                .addComponent(panelGradient32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(accountNumberLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountNumberFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to1Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountNumberToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountNumberFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(accountNameFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountNameFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountNameToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to2Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(accountParentLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parentFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to3Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parentToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parentFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(hierarchyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(accountSubclassLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subclassFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to4Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subclassToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subclassFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        hierarchyPanel1.setColor1(new java.awt.Color(247, 247, 255));
        hierarchyPanel1.setColor2(new java.awt.Color(247, 247, 255));

        jLabel3.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        jLabel3.setText("Authors");

        javax.swing.GroupLayout panelGradient33Layout = new javax.swing.GroupLayout(panelGradient33);
        panelGradient33.setLayout(panelGradient33Layout);
        panelGradient33Layout.setHorizontalGroup(
            panelGradient33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient33Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelGradient33Layout.setVerticalGroup(
            panelGradient33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        createdAtLb.setForeground(new java.awt.Color(51, 51, 51));
        createdAtLb.setText("Created At");
        createdAtLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        to8Lb.setForeground(new java.awt.Color(51, 51, 51));
        to8Lb.setText("To");
        to8Lb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        updatedByToMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        updatedByLb.setForeground(new java.awt.Color(51, 51, 51));
        updatedByLb.setText("Updated By");
        updatedByLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        updatedByFromMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        to9Lb.setForeground(new java.awt.Color(51, 51, 51));
        to9Lb.setText("To");
        to9Lb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        updatedAtLb.setForeground(new java.awt.Color(51, 51, 51));
        updatedAtLb.setText("Updated At");
        updatedAtLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        to10Lb.setForeground(new java.awt.Color(51, 51, 51));
        to10Lb.setText("To");
        to10Lb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        createdByLb.setForeground(new java.awt.Color(51, 51, 51));
        createdByLb.setText("Created By");
        createdByLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        createdByFromMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        to7Lb.setForeground(new java.awt.Color(51, 51, 51));
        to7Lb.setText("To");
        to7Lb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        createdByToMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        updatedByAccountFilterButton.setBackground(new java.awt.Color(226, 210, 144));
        updatedByAccountFilterButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 176, 176)));

        updatedAtAccountFilterButton.setBackground(new java.awt.Color(226, 210, 144));
        updatedAtAccountFilterButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 176, 176)));

        createdAtAccountFilterButton.setBackground(new java.awt.Color(226, 210, 144));
        createdAtAccountFilterButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 176, 176)));

        createdByFilterButton.setBackground(new java.awt.Color(226, 210, 144));
        createdByFilterButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 176, 176)));

        createdAtFromDateChooser.setDateFormatString("yyyy.MM.dd HH:mm:ss");
        createdAtFromDateChooser.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        createdAtToDateChooser.setDateFormatString("yyyy.MM.dd HH:mm:ss");
        createdAtToDateChooser.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        updatedAtFromDateChooser.setDateFormatString("yyyy.MM.dd HH:mm:ss");
        updatedAtFromDateChooser.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        updatedAtToDateChooser.setDateFormatString("yyyy.MM.dd HH:mm:ss");
        updatedAtToDateChooser.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        javax.swing.GroupLayout hierarchyPanel1Layout = new javax.swing.GroupLayout(hierarchyPanel1);
        hierarchyPanel1.setLayout(hierarchyPanel1Layout);
        hierarchyPanel1Layout.setHorizontalGroup(
            hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradient33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(hierarchyPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(hierarchyPanel1Layout.createSequentialGroup()
                        .addComponent(createdByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(createdByFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hierarchyPanel1Layout.createSequentialGroup()
                        .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updatedByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updatedAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updatedByFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updatedAtFromDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(hierarchyPanel1Layout.createSequentialGroup()
                        .addComponent(createdAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(createdAtFromDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(hierarchyPanel1Layout.createSequentialGroup()
                        .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(to9Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(to10Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updatedByToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updatedAtToDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(hierarchyPanel1Layout.createSequentialGroup()
                        .addComponent(to7Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(createdByToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hierarchyPanel1Layout.createSequentialGroup()
                        .addComponent(to8Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(createdAtToDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createdByFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedByAccountFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedAtAccountFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdAtAccountFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        hierarchyPanel1Layout.setVerticalGroup(
            hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hierarchyPanel1Layout.createSequentialGroup()
                .addComponent(panelGradient33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(createdByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdByFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to7Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdByToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdByFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(createdAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to8Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdAtAccountFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdAtFromDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdAtToDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(updatedByToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedByAccountFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to9Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedByFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedByLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(hierarchyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(updatedAtAccountFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedAtLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to10Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedAtFromDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedAtToDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        hierarchyPanel2.setColor1(new java.awt.Color(247, 247, 255));
        hierarchyPanel2.setColor2(new java.awt.Color(247, 247, 255));

        jLabel4.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        jLabel4.setText("Account Status");

        javax.swing.GroupLayout panelGradient34Layout = new javax.swing.GroupLayout(panelGradient34);
        panelGradient34.setLayout(panelGradient34Layout);
        panelGradient34Layout.setHorizontalGroup(
            panelGradient34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelGradient34Layout.setVerticalGroup(
            panelGradient34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        accountStatusLb.setForeground(new java.awt.Color(51, 51, 51));
        accountStatusLb.setText("Status");
        accountStatusLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        statusAccountComboBox.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        isClosedAccountCheckBox.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        isClosedAccountCheckBox.setText("Is Closed");
        isClosedAccountCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout hierarchyPanel2Layout = new javax.swing.GroupLayout(hierarchyPanel2);
        hierarchyPanel2.setLayout(hierarchyPanel2Layout);
        hierarchyPanel2Layout.setHorizontalGroup(
            hierarchyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradient34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(hierarchyPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accountStatusLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(statusAccountComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(isClosedAccountCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        hierarchyPanel2Layout.setVerticalGroup(
            hierarchyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hierarchyPanel2Layout.createSequentialGroup()
                .addComponent(panelGradient34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(hierarchyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(statusAccountComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountStatusLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isClosedAccountCheckBox))
                .addGap(12, 12, 12))
        );

        hierarchyPanel3.setColor1(new java.awt.Color(247, 247, 255));
        hierarchyPanel3.setColor2(new java.awt.Color(247, 247, 255));

        jLabel5.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        jLabel5.setText("Model");

        javax.swing.GroupLayout panelGradient35Layout = new javax.swing.GroupLayout(panelGradient35);
        panelGradient35.setLayout(panelGradient35Layout);
        panelGradient35Layout.setHorizontalGroup(
            panelGradient35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(861, Short.MAX_VALUE))
        );
        panelGradient35Layout.setVerticalGroup(
            panelGradient35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        modelNameLb.setForeground(new java.awt.Color(51, 51, 51));
        modelNameLb.setText("Model");
        modelNameLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        modelFromMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        to5Lb.setForeground(new java.awt.Color(51, 51, 51));
        to5Lb.setText("To");
        to5Lb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        modelToMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        modelFilterButton.setBackground(new java.awt.Color(226, 210, 144));
        modelFilterButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 176, 176)));

        modelStatusLb.setForeground(new java.awt.Color(51, 51, 51));
        modelStatusLb.setText("Status");
        modelStatusLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        modelStateLb.setForeground(new java.awt.Color(51, 51, 51));
        modelStateLb.setText("State");
        modelStateLb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        modelStateFromMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        to6Lb.setForeground(new java.awt.Color(51, 51, 51));
        to6Lb.setText("To");
        to6Lb.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        modelStateToMatchCode.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 14)); // NOI18N

        modelStateFilterButton.setBackground(new java.awt.Color(226, 210, 144));
        modelStateFilterButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 176, 176)));

        modelStatusCheckBox.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N
        modelStatusCheckBox.setText("Active");
        modelStatusCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout hierarchyPanel3Layout = new javax.swing.GroupLayout(hierarchyPanel3);
        hierarchyPanel3.setLayout(hierarchyPanel3Layout);
        hierarchyPanel3Layout.setHorizontalGroup(
            hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradient35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(hierarchyPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modelNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelStatusLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelStateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hierarchyPanel3Layout.createSequentialGroup()
                        .addGroup(hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modelFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modelStateFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addGroup(hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(to5Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(to6Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(modelStateToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modelToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addGroup(hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modelStateFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modelFilterButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(modelStatusCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(178, 178, 178))
        );
        hierarchyPanel3Layout.setVerticalGroup(
            hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hierarchyPanel3Layout.createSequentialGroup()
                .addComponent(panelGradient35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(modelFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to5Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(modelStatusCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelStatusLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hierarchyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(modelStateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelStateFromMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to6Lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelStateToMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelStateFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelScrollLayout = new javax.swing.GroupLayout(panelScroll);
        panelScroll.setLayout(panelScrollLayout);
        panelScrollLayout.setHorizontalGroup(
            panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScrollLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hierarchyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hierarchyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hierarchyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hierarchyPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        panelScrollLayout.setVerticalGroup(
            panelScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScrollLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hierarchyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hierarchyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hierarchyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hierarchyPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPane.setViewportView(panelScroll);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accountNameFilterButton;
    private javax.swing.JTextField accountNameFromTextField;
    private corex.suite.JLabelHQUnderlined accountNameLb;
    private javax.swing.JTextField accountNameToTextField;
    private javax.swing.JButton accountNumberFilterButton;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode accountNumberFromMatchCode;
    private corex.suite.JLabelHQUnderlined accountNumberLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode accountNumberToMatchCode;
    private corex.suite.JLabelHQUnderlined accountParentLb;
    private corex.suite.JLabelHQUnderlined accountStatusLb;
    private corex.suite.JLabelHQUnderlined accountSubclassLb;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private javax.swing.JButton createdAtAccountFilterButton;
    private com.toedter.calendar.JDateChooser createdAtFromDateChooser;
    private corex.suite.JLabelHQUnderlined createdAtLb;
    private com.toedter.calendar.JDateChooser createdAtToDateChooser;
    private javax.swing.JButton createdByFilterButton;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode createdByFromMatchCode;
    private corex.suite.JLabelHQUnderlined createdByLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode createdByToMatchCode;
    private corex.suite.JPanelRoundedGradient hierarchyPanel;
    private corex.suite.JPanelRoundedGradient hierarchyPanel1;
    private corex.suite.JPanelRoundedGradient hierarchyPanel2;
    private corex.suite.JPanelRoundedGradient hierarchyPanel3;
    private javax.swing.JCheckBox isClosedAccountCheckBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton modelFilterButton;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode modelFromMatchCode;
    private corex.suite.JLabelHQUnderlined modelNameLb;
    private javax.swing.JButton modelStateFilterButton;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode modelStateFromMatchCode;
    private corex.suite.JLabelHQUnderlined modelStateLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode modelStateToMatchCode;
    private javax.swing.JCheckBox modelStatusCheckBox;
    private corex.suite.JLabelHQUnderlined modelStatusLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode modelToMatchCode;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices nextButton;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient32;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient33;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient34;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient35;
    private corex.suite.JPanelRoundedGradient panelScroll;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private javax.swing.JButton parentFilterButton;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode parentFromMatchCode;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode parentToMatchCode;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JComboBox<String> statusAccountComboBox;
    private javax.swing.JButton subclassFilterButton;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode subclassFromMatchCode;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode subclassToMatchCode;
    private corex.suite.JLabelHQUnderlined to10Lb;
    private corex.suite.JLabelHQUnderlined to1Lb;
    private corex.suite.JLabelHQUnderlined to2Lb;
    private corex.suite.JLabelHQUnderlined to3Lb;
    private corex.suite.JLabelHQUnderlined to4Lb;
    private corex.suite.JLabelHQUnderlined to5Lb;
    private corex.suite.JLabelHQUnderlined to6Lb;
    private corex.suite.JLabelHQUnderlined to7Lb;
    private corex.suite.JLabelHQUnderlined to8Lb;
    private corex.suite.JLabelHQUnderlined to9Lb;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton updatedAtAccountFilterButton;
    private com.toedter.calendar.JDateChooser updatedAtFromDateChooser;
    private corex.suite.JLabelHQUnderlined updatedAtLb;
    private com.toedter.calendar.JDateChooser updatedAtToDateChooser;
    private javax.swing.JButton updatedByAccountFilterButton;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode updatedByFromMatchCode;
    private corex.suite.JLabelHQUnderlined updatedByLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode updatedByToMatchCode;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isTaskRunning() {
        return false;
    }

    @Override
    public ActionListener getOnTaskComplete() {
        return null;
    }
    @Override
    public String getTransactionCode() {
        return transactionCode;
    }
    
    public TranslationHelper getTableTranslator() {return tableTranslator;}
    public TranslationHelper getWindowTranslator() {return windowTranslator;}
    public SystemMessages getNotificator() {return notificator;}
    public JCheckBox getModelStatusCheckBox(){return modelStatusCheckBox;}
    public JCheckBox getAccountIsClosedCheckBox(){return isClosedAccountCheckBox;}
        
    //GETTERS de botones 
    public JButton getAccountNumberFilterButton() {return accountNumberFilterButton;}
    public JButton getAccountNameFilterButton() {return accountNameFilterButton;}
    public JButton getParentFilterButton() {return parentFilterButton;}
    public JButton getSubclassFilterButton() {return subclassFilterButton;}
    public JButton getModelFilterButton() {return modelFilterButton;}
    public JButton getModelStateFilterButton() {return modelStateFilterButton;}
    public JButton getCreatedByFilterButton() {return createdByFilterButton;}
    public JButton getCreatedAtAccountFilterButton() {return createdAtAccountFilterButton;}
    public JButton getUpdatedByAccountFilterButton() {return updatedByAccountFilterButton;}
    public JButton getUpdatedAtAccountFilterButton() {return updatedAtAccountFilterButton;}

    //FIN
    //GETTERS MatchCodes
    public MatchCode getAccountNumberFromMatchCode() {return accountNumberFromMatchCode;}
    public MatchCode getAccountNumberToMatchCode() {return accountNumberToMatchCode;}
    public JTextField getAccountNameFromTextField() {return accountNameFromTextField;}
    public JTextField getAccountNameToTextField() {return accountNameToTextField;}
    public MatchCode getParentFromMatchCode() {return parentFromMatchCode;}
    public MatchCode getParentToMatchCode() {return parentToMatchCode;}
    public MatchCode getSubclassFromMatchCode() {return subclassFromMatchCode;}
    public MatchCode getSubclassToMatchCode() {return subclassToMatchCode;}
    public MatchCode getModelFromMatchCode() {return modelFromMatchCode;}
    public MatchCode getModelToMatchCode() {return modelToMatchCode;}
    public MatchCode getModelStateFromMatchCode() {return modelStateFromMatchCode;}
    public MatchCode getModelStateToMatchCode() {return modelStateToMatchCode;}
    public MatchCode getCreatedByFromMatchCode() {return createdByFromMatchCode;}
    public MatchCode getCreatedByToMatchCode() {return createdByToMatchCode;}
    public MatchCode getUpdatedByFromMatchCode() {return updatedByFromMatchCode;}
    public MatchCode getUpdatedByToMatchCode() {return updatedByToMatchCode;}

    public JDateChooser getCreatedAtFromDateChooser() {return createdAtFromDateChooser;}
    public JDateChooser getCreatedAtToDateChooser() {return createdAtToDateChooser;}
    public JDateChooser getUpdatedAtFromDateChooser() {return updatedAtFromDateChooser;}
    public JDateChooser getUpdatedAtToDateChooser() {return updatedAtToDateChooser;}
    //FIN
    
    public JComboBox<String> getStatusAccountComboBox(){return statusAccountComboBox;}
    // Getters para los labels
    public String getAccountNumberLabel() {return accountNumberLb.getText();}
    public String getAccountNameLabel() {return accountNameLb.getText();}
    public String getParentLabel() {return accountParentLb.getText();}
    public String getSubclassLabel() {return accountSubclassLb.getText();}
    public String getModelLabel() {return modelNameLb.getText();}
    public String getModelStateLabel() {return modelStateLb.getText();}
    public String getCreatedByLabel() {return createdByLb.getText();}
    public String getCreatedAtLabel() {return createdAtLb.getText();}
    public String getUpdatedByLabel() {return updatedByLb.getText();}
    public String getUpdatedAtLabel() {return updatedAtLb.getText();}
    //FIN
    
    public JButton getNextButton(){return nextButton;}

    @Override
    public FormState getFormState() {
        return null;
    }

}
