package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.models.create;

import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.classes.ViewAccountClasses;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterNumeric;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountClassesRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountModelCreateRequest;
import com.simplecore.erp.shared.responses.types.AccountClassesRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountModelCreateResponse;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import com.simplecore.erp.client.controllers.workspace.TaskPanel;
import com.simplecore.erp.client.utils.documentfilters.DocFilterVarcharWithoutSpace;

public class CreateAccountModel extends JPanel implements TransactionPanel, TaskPanel{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final SystemMessages notificator;
    private final TranslationHelper windowTranslator;

    public CreateAccountModel() {
        initComponents();
        initEvents();
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
        requestAccountClassesList();
        
    }

    private int assetsId;
    private int liabilitiesId;
    private int equityId;
    private int revenueId;
    private int costsId;
    private int expensesId;
    
    private String assetsDescription;
    private String liabilitiesDescription;
    private String equityDescription;
    private String revenueDescription;
    private String costsDescription;
    private String expensesDescription;
    
    private void requestAccountClassesList() {
        try {
            output.writeObject(new AccountClassesRetrieveRequest(activeSession.getSessionId(), activeSession.getUserId()));
            output.flush();
            Object response = input.readObject();
            if (response instanceof AccountClassesRetrieveResponse accountClassesResponse) {
                String[][] matrix = accountClassesResponse.getAccountClasses();

                assetsId = Integer.parseInt(matrix[0][0]);
                assetsDescription = descriptionClass(matrix[0][3]);

                liabilitiesId = Integer.parseInt(matrix[1][0]);
                liabilitiesDescription = descriptionClass(matrix[1][3]);

                equityId = Integer.parseInt(matrix[2][0]);
                equityDescription = descriptionClass(matrix[2][3]);

                revenueId = Integer.parseInt(matrix[3][0]);
                revenueDescription = descriptionClass(matrix[3][3]);

                costsId = Integer.parseInt(matrix[4][0]);
                costsDescription = descriptionClass(matrix[4][3]);

                expensesId = Integer.parseInt(matrix[5][0]);
                expensesDescription = descriptionClass(matrix[5][3]);
            }
            setAccountDataInTextFields();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ViewAccountClasses.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setAccountDataInTextFields() {
        assetsTag.setText("(" + assetsId + ") " + assetsDescription);
        liabilitiesTag.setText("(" + liabilitiesId + ") " + liabilitiesDescription);
        revenueTag.setText("(" + revenueId + ") " + revenueDescription);
        equityTag.setText("(" + equityId + ") " + equityDescription);
        costsTag.setText("(" + costsId + ") " + costsDescription);
        expensesTag.setText("(" + expensesId + ") " + expensesDescription);
    }

    private String descriptionClass(String key) {
        return tableTranslator.getTranslation(key);
    }
    
    private void initEvents() {
        
        setSaveButtonListener();
        initializeTextFields();
        setButtonCommandConfigure();
        rangesDelimitations();
        setInitialButtonStates();
    }

    private void setButtonCommandConfigure() {
        proceedButton.setIcon(new CustomSVGIcon("/icons/svg/ok_button_icon.svg", new Dimension(24, 24)));
    }

    private void initializeTextFields() {
        ((AbstractDocument)modelNameTextField.getDocument()).setDocumentFilter(new DocFilterVarcharWithoutSpace(45));
        // Configuración inicial: solo el primer FROM es editable
        configureTextField(assetsFromTextField, true);
        JTextField[] textFields = {
            assetsToTextField, liabilitiesFromTextField, liabilitiesToTextField,
            equityFromTextField, equityToTextField, revenueFromTextField, revenueToTextField,
            costsFromTextField, costsToTextField, expensesFromTextField, expensesToTextField
        };

        for (JTextField textField : textFields) {
            configureTextField(textField, false);
        }
    }

    private void configureTextField(JTextField textField, boolean editable) {
        textField.setEditable(editable);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilterNumeric(12));
    }

    private void setInitialButtonStates() {
        confirmAssetsButton.setEnabled(true);
        confirmLiabilitiesButton.setEnabled(false);
        confirmEquityButton.setEnabled(false);
        confirmRevenueButton.setEnabled(false);
        confirmCostsButton.setEnabled(false);
        confirmExpensesButton.setEnabled(false);
    }
    
    private void rangesDelimitations() {
        confirmDataAccountClass();
        confirmLiabilitiesAccountClass();
        confirmEquityAccountClass();
        confirmRevenueAccountClass();
        confirmCostsAccountClass();
        confirmExpensesAccountClass();
    }

    private void confirmDataAccountClass() {
        confirmAssetsButton.addActionListener(e -> {
            processAccountClass(assetsFromTextField, assetsToTextField, null, 
                    liabilitiesFromTextField,confirmAssetsButton,confirmLiabilitiesButton);
        });
    }
    private void confirmLiabilitiesAccountClass() {
        confirmLiabilitiesButton.addActionListener(e -> {
            processAccountClass(liabilitiesFromTextField, liabilitiesToTextField, 
                    assetsToTextField, equityFromTextField,confirmLiabilitiesButton,confirmEquityButton);
        });
    }
    private void confirmEquityAccountClass() {
        confirmEquityButton.addActionListener(e -> { // CORREGIDO: confirmEquityButton en lugar de confirmLiabilitiesButton
            processAccountClass(equityFromTextField, equityToTextField, 
                    liabilitiesToTextField, revenueFromTextField,confirmEquityButton,confirmRevenueButton);
        });
    }
    private void confirmRevenueAccountClass() {
        confirmRevenueButton.addActionListener(e -> { // CORREGIDO: confirmRevenueButton en lugar de confirmLiabilitiesButton
            processAccountClass(revenueFromTextField, revenueToTextField,
                    equityToTextField, costsFromTextField,confirmRevenueButton,confirmCostsButton);
        });
    }
    private void confirmCostsAccountClass() {
        confirmCostsButton.addActionListener(e -> { // CORREGIDO: confirmCostsButton en lugar de confirmLiabilitiesButton
            processAccountClass(costsFromTextField, costsToTextField, 
                    revenueToTextField, expensesFromTextField,confirmCostsButton,confirmExpensesButton); // CORREGIDO: ahora habilita expensesFromTextField en lugar de reutilizar costsFromTextField
        });
    }
    private void confirmExpensesAccountClass() {
        confirmExpensesButton.addActionListener(e -> { // CORREGIDO: confirmExpensesButton en lugar de confirmLiabilitiesButton
            processAccountClass(expensesFromTextField, expensesToTextField, costsToTextField, null,confirmExpensesButton,null);
        });
    }

    private void processAccountClass(JTextField fromField, JTextField toField, JTextField previousToField,
            JTextField nextFromField, JButton currentButton, JButton nextButton) {
        String fromText = fromField.getText().trim();

        if (fromText.isEmpty() || fromText.length() <= 4) {
            fromField.setText("");
            toField.setText("");
            fromField.requestFocus();
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.ENTERED_VALUE_IS_LESS));
            return;
        }

        try {
            int fromNum = Integer.parseInt(fromText);
            int previousToNum = 0; // Asumimos 0 si no hay un campo previo

            if (previousToField != null) {
                String previousToText = previousToField.getText().trim();
                if (!previousToText.isEmpty() && previousToText.matches("\\d+")) { // Validamos que sean solo números
                    previousToNum = Integer.parseInt(previousToText);
                }
            }

            if (fromNum <= previousToNum) {
                fromField.setText("");
                toField.setText("");
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.ENTERED_VALUE_IS_LESS));
                return;
            }

            int numDigits = fromText.length();
            int rangeWidth = (int) Math.pow(10, numDigits - 1);
            int toValue = fromNum + rangeWidth - 1;

            toField.setText(String.valueOf(toValue));
            toField.setEditable(false);

            // Bloquear el campo `fromField` para evitar modificaciones
            fromField.setEditable(false);

            // Deshabilitar el botón actual de confirmación
            if (currentButton != null) {
                currentButton.setEnabled(false);
            }

            // Habilitar el siguiente campo de entrada
            if (nextFromField != null) {
                nextFromField.setEditable(true);
            }

            // Habilitar el siguiente botón de confirmación si existe
            if (nextButton != null) {
                nextButton.setEnabled(true);
            }

        } catch (NumberFormatException ex) {
            fromField.setText(null);
        }
    }

    private boolean isDataFullyFilled(){
        if (!validateField(modelNameTextField)) return false;
        if (!validateField(modelDescriptionTextField)) return false;
        if (!validateField(assetsFromTextField)) return false;
        if (!validateField(assetsToTextField)) return false;
        if (!validateField(liabilitiesFromTextField)) return false;
        if (!validateField(liabilitiesToTextField)) return false;
        if (!validateField(equityFromTextField)) return false;
        if (!validateField(equityToTextField)) return false;
        if (!validateField(revenueFromTextField)) return false;
        if (!validateField(revenueToTextField)) return false;
        if (!validateField(costsFromTextField)) return false;
        if (!validateField(costsToTextField)) return false;
        if (!validateField(expensesFromTextField)) return false;
        if (!validateField(expensesToTextField)) return false;
        return true;
    }

    private boolean validateField(JTextField field) {
        if (field.getText().trim().isEmpty()) {
            Workspace.getSaveButton().setPlayable(false);//Codigo del boton 
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
            field.requestFocus();
            return false;
        }
        Workspace.getSaveButton().setPlayable(true);//Codigo del boton 
        return true;
    }

    private void sendCreateModelRequest() {
        if (isDataFullyFilled()) {
            try {
                String sessionId = activeSession.getSessionId();
                int userId = activeSession.getUserId();
                AccountModelCreateRequest createModelRequest = new AccountModelCreateRequest.Builder(sessionId, userId)
                        .assetsId(assetsId)
                        .liabilitiesId(liabilitiesId)
                        .equityId(revenueId)
                        .revenueId(equityId)
                        .costsId(costsId)
                        .expensesId(expensesId)
                        .modelName(modelNameTextField.getText().trim())
                        .modelDescription(modelDescriptionTextField.getText().trim())
                        .assetsFrom(Integer.parseInt(assetsFromTextField.getText()))
                        .assetsTo(Integer.parseInt(assetsToTextField.getText()))
                        .liabilitiesFrom(Integer.parseInt(liabilitiesFromTextField.getText()))
                        .liabilitiesTo(Integer.parseInt(liabilitiesToTextField.getText()))
                        .equityFrom(Integer.parseInt(equityFromTextField.getText()))
                        .equityTo(Integer.parseInt(equityToTextField.getText()))
                        .revenueFrom(Integer.parseInt(revenueFromTextField.getText()))
                        .revenueTo(Integer.parseInt(revenueToTextField.getText()))
                        .costsFrom(Integer.parseInt(costsFromTextField.getText()))
                        .costsTo(Integer.parseInt(costsToTextField.getText()))
                        .expensesFrom(Integer.parseInt(expensesFromTextField.getText()))
                        .expensesTo(Integer.parseInt(expensesToTextField.getText()))
                        .createdBy(activeSession.getUsername())
                        .build();
                
                output.writeObject(createModelRequest);
                output.flush();
                Object response = input.readObject();
                if (response instanceof AccountModelCreateResponse model) {
                    if (model.getModelId() == -1) {
                        notificator.showWarningMsg(model.getMessage() + model.getModelName());
                        modelNameTextField.requestFocus();
                        return;
                    }
                    diableTextFields();
                    notificator.showSuccessMsg(model.getMessage() + model.getModelName() + model.getModelId());
                    PanelManager.goBack();
                }
                
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CreateAccountModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    private void diableTextFields(){
        modelNameTextField.setEditable(false);
        modelDescriptionTextField.setEditable(false);
    }
    
    
    private ActionListener setSaveButtonListener(){
        return (ActionEvent e) -> {
            sendCreateModelRequest();
        };
    }
    @Override
    public boolean isTaskRunning() {
        return true;
    }
    
    @Override
    public ActionListener getOnTaskComplete() {
        return setSaveButtonListener();
    }
    
    @Override
    public String getTransactionCode() {
        return transactionCode;
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
        modelNameLabel = new corex.suite.JLabelHQUnderlined();
        modelNameTextField = new javax.swing.JTextField();
        modelDescriptionLabel = new corex.suite.JLabelHQUnderlined();
        modelDescriptionTextField = new javax.swing.JTextField();
        generalDataFieldPanel1 = new corex.suite.JPanelRoundedGradient();
        fromLb1 = new corex.suite.JLabelHQUnderlined();
        assetsFromTextField = new javax.swing.JTextField();
        toLb1 = new corex.suite.JLabelHQUnderlined();
        assetsToTextField = new javax.swing.JTextField();
        assetsTag = new javax.swing.JTextField();
        liabilitiesToTextField = new javax.swing.JTextField();
        toLb2 = new corex.suite.JLabelHQUnderlined();
        liabilitiesTag = new javax.swing.JTextField();
        liabilitiesFromTextField = new javax.swing.JTextField();
        fromLb2 = new corex.suite.JLabelHQUnderlined();
        fromLb3 = new corex.suite.JLabelHQUnderlined();
        equityFromTextField = new javax.swing.JTextField();
        equityTag = new javax.swing.JTextField();
        toLb3 = new corex.suite.JLabelHQUnderlined();
        equityToTextField = new javax.swing.JTextField();
        toLb4 = new corex.suite.JLabelHQUnderlined();
        fromLb4 = new corex.suite.JLabelHQUnderlined();
        revenueToTextField = new javax.swing.JTextField();
        revenueTag = new javax.swing.JTextField();
        revenueFromTextField = new javax.swing.JTextField();
        costsFromTextField = new javax.swing.JTextField();
        costsTag = new javax.swing.JTextField();
        costsToTextField = new javax.swing.JTextField();
        fromLb5 = new corex.suite.JLabelHQUnderlined();
        toLb5 = new corex.suite.JLabelHQUnderlined();
        toLb6 = new corex.suite.JLabelHQUnderlined();
        fromLb6 = new corex.suite.JLabelHQUnderlined();
        expensesTag = new javax.swing.JTextField();
        expensesFromTextField = new javax.swing.JTextField();
        expensesToTextField = new javax.swing.JTextField();
        confirmAssetsButton = new javax.swing.JButton();
        confirmLiabilitiesButton = new javax.swing.JButton();
        confirmRevenueButton = new javax.swing.JButton();
        confirmEquityButton = new javax.swing.JButton();
        confirmCostsButton = new javax.swing.JButton();
        confirmExpensesButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Create Account Model");
        moduleTitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
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
                .addContainerGap(805, Short.MAX_VALUE))
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

        modelNameLabel.setText("Model Name");
        modelNameLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        modelNameTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        modelNameTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        modelDescriptionLabel.setText("Description");
        modelDescriptionLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        modelDescriptionTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        modelDescriptionTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        generalDataFieldPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        generalDataFieldPanel1.setColor1(new java.awt.Color(247, 247, 255));
        generalDataFieldPanel1.setColor2(new java.awt.Color(247, 247, 255));

        fromLb1.setText("From");
        fromLb1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        toLb1.setText("To");
        toLb1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        assetsTag.setEditable(false);
        assetsTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        assetsTag.setText("Assets");

        toLb2.setText("To");
        toLb2.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        liabilitiesTag.setEditable(false);
        liabilitiesTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        liabilitiesTag.setText("Liabilities");

        fromLb2.setText("From");
        fromLb2.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        fromLb3.setText("From");
        fromLb3.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        equityTag.setEditable(false);
        equityTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        equityTag.setText("Equity");

        toLb3.setText("To");
        toLb3.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        toLb4.setText("To");
        toLb4.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        fromLb4.setText("From");
        fromLb4.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        revenueTag.setEditable(false);
        revenueTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        revenueTag.setText("Revenue");

        costsTag.setEditable(false);
        costsTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        costsTag.setText("Costs");

        fromLb5.setText("From");
        fromLb5.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        toLb5.setText("To");
        toLb5.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        toLb6.setText("To");
        toLb6.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        fromLb6.setText("From");
        fromLb6.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        expensesTag.setEditable(false);
        expensesTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        expensesTag.setText("Expenses");

        confirmAssetsButton.setIcon(new CustomSVGIcon("/icons/svg/one_check.svg",new Dimension(22,22)));

        confirmLiabilitiesButton.setIcon(new CustomSVGIcon("/icons/svg/one_check.svg",new Dimension(22,22)));

        confirmRevenueButton.setIcon(new CustomSVGIcon("/icons/svg/one_check.svg",new Dimension(22,22)));

        confirmEquityButton.setIcon(new CustomSVGIcon("/icons/svg/one_check.svg",new Dimension(22,22)));

        confirmCostsButton.setIcon(new CustomSVGIcon("/icons/svg/one_check.svg",new Dimension(22,22)));

        confirmExpensesButton.setIcon(new CustomSVGIcon("/icons/svg/one_check.svg",new Dimension(22,22)));

        javax.swing.GroupLayout generalDataFieldPanel1Layout = new javax.swing.GroupLayout(generalDataFieldPanel1);
        generalDataFieldPanel1.setLayout(generalDataFieldPanel1Layout);
        generalDataFieldPanel1Layout.setHorizontalGroup(
            generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(assetsTag, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(liabilitiesTag, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equityTag, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenueTag, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costsTag, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expensesTag, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                        .addComponent(fromLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(assetsFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(toLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(assetsToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmAssetsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                        .addComponent(fromLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(liabilitiesFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(toLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(liabilitiesToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmLiabilitiesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                                .addComponent(fromLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(equityFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(toLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(equityToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                                .addComponent(fromLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(revenueFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(toLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(revenueToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmEquityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmRevenueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                                .addComponent(fromLb5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(costsFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(toLb5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(costsToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                                .addComponent(fromLb6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(expensesFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(toLb6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(expensesToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmCostsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmExpensesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        generalDataFieldPanel1Layout.setVerticalGroup(
            generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataFieldPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(assetsTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assetsToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assetsFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmAssetsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(liabilitiesFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(liabilitiesTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(liabilitiesToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmLiabilitiesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(confirmEquityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equityFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equityToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equityTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(revenueFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenueTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenueToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmRevenueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(costsToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costsTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costsFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmCostsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLb5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromLb5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generalDataFieldPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(confirmExpensesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expensesFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expensesTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromLb6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLb6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expensesToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generalDataFieldPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(modelDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modelNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modelDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modelNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modelNameTextField)
                    .addComponent(modelNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(modelDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(generalDataFieldPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField assetsFromTextField;
    private javax.swing.JTextField assetsTag;
    private javax.swing.JTextField assetsToTextField;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private javax.swing.JButton confirmAssetsButton;
    private javax.swing.JButton confirmCostsButton;
    private javax.swing.JButton confirmEquityButton;
    private javax.swing.JButton confirmExpensesButton;
    private javax.swing.JButton confirmLiabilitiesButton;
    private javax.swing.JButton confirmRevenueButton;
    private javax.swing.JTextField costsFromTextField;
    private javax.swing.JTextField costsTag;
    private javax.swing.JTextField costsToTextField;
    private javax.swing.JTextField equityFromTextField;
    private javax.swing.JTextField equityTag;
    private javax.swing.JTextField equityToTextField;
    private javax.swing.JTextField expensesFromTextField;
    private javax.swing.JTextField expensesTag;
    private javax.swing.JTextField expensesToTextField;
    private corex.suite.JLabelHQUnderlined fromLb1;
    private corex.suite.JLabelHQUnderlined fromLb2;
    private corex.suite.JLabelHQUnderlined fromLb3;
    private corex.suite.JLabelHQUnderlined fromLb4;
    private corex.suite.JLabelHQUnderlined fromLb5;
    private corex.suite.JLabelHQUnderlined fromLb6;
    private corex.suite.JPanelRoundedGradient generalDataFieldPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField liabilitiesFromTextField;
    private javax.swing.JTextField liabilitiesTag;
    private javax.swing.JTextField liabilitiesToTextField;
    private corex.suite.JLabelHQUnderlined modelDescriptionLabel;
    private javax.swing.JTextField modelDescriptionTextField;
    private corex.suite.JLabelHQUnderlined modelNameLabel;
    private javax.swing.JTextField modelNameTextField;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private javax.swing.JTextField revenueFromTextField;
    private javax.swing.JTextField revenueTag;
    private javax.swing.JTextField revenueToTextField;
    private corex.suite.JLabelHQUnderlined toLb1;
    private corex.suite.JLabelHQUnderlined toLb2;
    private corex.suite.JLabelHQUnderlined toLb3;
    private corex.suite.JLabelHQUnderlined toLb4;
    private corex.suite.JLabelHQUnderlined toLb5;
    private corex.suite.JLabelHQUnderlined toLb6;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public FormState getFormState() {
        return null;
    }


}
