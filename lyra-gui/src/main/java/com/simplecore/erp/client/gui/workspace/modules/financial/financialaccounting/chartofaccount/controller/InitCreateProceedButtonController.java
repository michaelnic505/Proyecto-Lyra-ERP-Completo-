package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccounExistsCheck;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.form.ChartOfAccountFormPanel;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.utils.validators.FormValidator;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.responses.types.AccountModelRetrieveResponse;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class InitCreateProceedButtonController implements RowSelectionListener{

    private final TranslationHelper windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
    private final TranslationHelper tableTranslator  = Workspace.translators(TranslatorType.TABLES);
    private final SystemMessages notificator = new SystemMessages();
    
    private final String transactionCode;
    private final ActiveSession session;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    
    private final JButton button;
    private final MatchCode accountModelMatchCode;
    private final JTextField chartAccountCodeTextField;
    private final JTextField chartAccountNameTextField;
    private final JLabel accountModelDescriptionLabel;

    private AccountModelListSearchController modelListController;
    private ChartOfAccounExistsCheck chartOfAccountExistsCheck;
    
    private int accountModelID;
    private String accountModelName;
    private String accountModelDescription;
    private String chartOfAccountCode;
    private String chartOfAccountName;
    private OperationType operationType;

    public InitCreateProceedButtonController(Builder builder) {
        this.transactionCode = builder.transactionCode;
        this.session = builder.session;
        this.output = builder.output;
        this.input = builder.input;
        this.button = builder.button;
        this.accountModelMatchCode = builder.accountModelMatchCode;
        this.chartAccountCodeTextField = builder.chartAccountCodeTextField;
        this.chartAccountNameTextField = builder.chartAccountNameTextField;
        this.accountModelDescriptionLabel = builder.accountModelDescriptionLabel;
        this.operationType = builder.operationType;
        this.modelListController = buildAccountModelWindowController();
        this.chartOfAccountExistsCheck = buildChartOfAccountCheckService();
        setButtonEvents();
    }

    public static class Builder {

        private OperationType operationType;
        private String transactionCode;
        private ActiveSession session;
        private ObjectOutputStream output;
        private ObjectInputStream input;
        private JButton button;
        private JTextField chartAccountCodeTextField;
        private JTextField chartAccountNameTextField;
        private JLabel accountModelDescriptionLabel;
        private MatchCode accountModelMatchCode;
        // Métodos para cada atributo (fluent interface)
        public Builder withOperationType(OperationType operationType) {
            this.operationType = operationType;
            return this;
        }
        
        public Builder withAccountModelMatchCode(MatchCode matchCode) {
            this.accountModelMatchCode = matchCode;
            return this;
        }

        public Builder withTransactionCode(String transactionCode) {
            this.transactionCode = transactionCode;
            return this;
        }

        public Builder withSession(ActiveSession session) {
            this.session = session;
            return this;
        }

        public Builder withOutput(ObjectOutputStream output) {
            this.output = output;
            return this;
        }

        public Builder withInput(ObjectInputStream input) {
            this.input = input;
            return this;
        }

        public Builder withButton(JButton button) {
            this.button = button;
            return this;
        }

        public Builder withChartAccountNameTextField(JTextField chartAccountNameTextField) {
            this.chartAccountNameTextField = chartAccountNameTextField;
            return this;
        }

        public Builder withAccountModelDescriptionLabel(JLabel accountModelDescriptionLabel) {
            this.accountModelDescriptionLabel = accountModelDescriptionLabel;
            return this;
        }
        
        public Builder withChartAccountCodeTextField(JTextField chartAccountCodeTextField) {
            this.chartAccountCodeTextField = chartAccountCodeTextField;
            return this;
        }

        // Método build() para crear la instancia final
        public InitCreateProceedButtonController build() {
            return new InitCreateProceedButtonController(this);
        }

    }

    // Configura el evento de acción para el botón
    private void setButtonEvents() {
        button.addActionListener(e -> openNextModule());
    }
     // Lógica para abrir el siguiente módulo, tras verificar que todos los campos están llenos
    private void openNextModule() {
        if (!areAllComponentsFullyFilled()) {
            return;
        }
        if(isChartCodeExists()){
            return;
        }
        ChartOfAccountFormPanel createChart = new ChartOfAccountFormPanel(accountModelID,
                accountModelName,
                accountModelDescription,
                chartOfAccountCode,
                chartOfAccountName,
                operationType);//Aqui se le indica el tipo de la operacion
        createChart.initialize(transactionCode, session, output, input);
        PanelManager.goToPanel(createChart);
    }

    // Método que se invoca cuando una fila es seleccionada
    @Override
    public void onRowSelected(Object[] selectedData) {
        accountModelID = (selectedData[0] == null) ? -1 : Integer.parseInt(selectedData[0].toString());
        accountModelName = (selectedData[1] == null) ? "" : selectedData[1].toString();
        accountModelDescription = (selectedData[2] == null) ? "" : selectedData[2].toString();
        setModelTexts();
    }
    // Actualiza los textos de la cuenta
    private void setChartOfAccountTexts() {
        chartOfAccountCode = chartAccountCodeTextField.getText().trim();
        chartOfAccountName = chartAccountNameTextField.getText().trim();
    }

    // Configura los textos de los modelos
    private void setModelTexts() {
        accountModelMatchCode.getTextField().setText(accountModelName);
        accountModelDescriptionLabel.setText(accountModelDescription);
    }

    // Construye el controlador de la ventana de búsqueda de modelo de cuenta
    private AccountModelListSearchController buildAccountModelWindowController() {
        return new AccountModelListSearchController.Builder()
                .button(accountModelMatchCode)
                .rowSelection(this)
                .columnsToReturn(List.of(0, 1, 3))
                .session(session)
                .output(output)
                .input(input)
                .build();
    }
    
    private ChartOfAccounExistsCheck buildChartOfAccountCheckService(){
        return new ChartOfAccounExistsCheck.Builder()
                .withChartCodeTextField(chartAccountCodeTextField)
                .withOutput(output)
                .withInput(input)
                .withSession(session)
                .build();
    }

    // Método para validar que todos los campos están llenos
    private boolean areAllComponentsFullyFilled() {
        FormValidator validator = new FormValidator()
                .add(accountModelMatchCode.getTextField())
                .add(accountModelDescriptionLabel)
                .add(chartAccountCodeTextField)
                .add(chartAccountNameTextField);

        if (!validator.validate(notificator)) {
            return false;
        }

        setChartOfAccountTexts(); // Actualiza la información de la cuenta una vez validada
        return true;
    }
    
    private boolean isAccountModelExists(){
        AccountModelRetrieveResponse accountModel = modelListController.getAcountModelByName();
        return accountModel != null;
    }
    
    private boolean isChartCodeExists(){
        return chartOfAccountExistsCheck.isChartOfAccountCodeExists();
    }

}
