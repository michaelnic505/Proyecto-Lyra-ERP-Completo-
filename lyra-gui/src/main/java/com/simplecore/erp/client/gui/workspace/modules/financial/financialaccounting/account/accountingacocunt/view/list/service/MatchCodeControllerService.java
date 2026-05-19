

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service;

import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountByAuthorMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountByAuthorMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountModelMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountModelMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountModelStatesMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountModelStatesMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountNumberMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountNumberMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountParentMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountParentMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountStatusComboController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountStatusComboController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountSubclassMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.AccountSubclassMatchCodeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.MatchCodeBaseController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes.MatchCodeBaseController;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JComboBox;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class MatchCodeControllerService {
    private final ActiveSession activeSession;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;

    private MatchCode accountNumberFromMatchCode;
    private MatchCode accountNumberToMatchCode;
    private MatchCode parentFromMatchCode;
    private MatchCode parentToMatchCode;
    private MatchCode subclassFromMatchCode;
    private MatchCode subclassToMatchCode;
    private MatchCode modelFromMatchCode;
    private MatchCode modelToMatchCode;
    private MatchCode modelStateFromMatchCode;
    private MatchCode modelStateToMatchCode;
    private MatchCode createdByFromMatchCode;
    private MatchCode createdByToMatchCode;
    private MatchCode updatedByFromMatchCode;
    private MatchCode updatedByToMatchCode;
    private JComboBox<String> statusAccountComboBox;

    private AccountNumberMatchCodeController accountNumber1Controller, accountNumber2Controller;
    private AccountParentMatchCodeController accountParent1Controller, accountParent2Controller;
    private AccountSubclassMatchCodeController accountSubclass1Controller, accountSubclass2Controller;
    private AccountModelMatchCodeController accountModel1Controller, accountModel2Controller;
    private AccountModelStatesMatchCodeController accountModelStates1Controller, accountModelStates2Controller;
    private AccountByAuthorMatchCodeController accountCreatedBy1Controller, accountCreatedBy2Controller;
    private AccountByAuthorMatchCodeController accountUpdatedBy1Controller, accountUpdatedBy2Controller;
    private AccountStatusComboController statusComboController;

    private MatchCodeControllerService(Builder builder) {
        this.activeSession = builder.activeSession;
        this.output = builder.output;
        this.input = builder.input;
        this.accountNumberFromMatchCode = builder.accountNumberFromMatchCode;
        this.accountNumberToMatchCode = builder.accountNumberToMatchCode;
        this.parentFromMatchCode = builder.parentFromMatchCode;
        this.parentToMatchCode = builder.parentToMatchCode;
        this.subclassFromMatchCode = builder.subclassFromMatchCode;
        this.subclassToMatchCode = builder.subclassToMatchCode;
        this.modelFromMatchCode = builder.modelFromMatchCode;
        this.modelToMatchCode = builder.modelToMatchCode;
        this.modelStateFromMatchCode = builder.modelStateFromMatchCode;
        this.modelStateToMatchCode = builder.modelStateToMatchCode;
        this.createdByFromMatchCode = builder.createdByFromMatchCode;
        this.createdByToMatchCode = builder.createdByToMatchCode;
        this.updatedByFromMatchCode = builder.updatedByFromMatchCode;
        this.updatedByToMatchCode = builder.updatedByToMatchCode;
        this.statusAccountComboBox = builder.statusAccountComboBox;

        // Inicialización de los controladores
        initializeControllers();
    }

    private void initializeControllers() {
        this.accountNumber1Controller = createAndInitializeController(new AccountNumberMatchCodeController(accountNumberFromMatchCode));
        this.accountNumber2Controller = createAndInitializeController(new AccountNumberMatchCodeController(accountNumberToMatchCode));
        this.accountParent1Controller = createAndInitializeController(new AccountParentMatchCodeController(parentFromMatchCode));
        this.accountParent2Controller = createAndInitializeController(new AccountParentMatchCodeController(parentToMatchCode));
        this.accountSubclass1Controller = createAndInitializeController(new AccountSubclassMatchCodeController(subclassFromMatchCode));
        this.accountSubclass2Controller = createAndInitializeController(new AccountSubclassMatchCodeController(subclassToMatchCode));
        this.accountModel1Controller = createAndInitializeController(new AccountModelMatchCodeController(modelFromMatchCode));
        this.accountModel2Controller = createAndInitializeController(new AccountModelMatchCodeController(modelToMatchCode));
        this.accountModelStates1Controller = createAndInitializeController(new AccountModelStatesMatchCodeController(modelStateFromMatchCode));
        this.accountModelStates2Controller = createAndInitializeController(new AccountModelStatesMatchCodeController(modelStateToMatchCode));
        this.accountCreatedBy1Controller = createAndInitializeController(new AccountByAuthorMatchCodeController(createdByFromMatchCode));
        this.accountCreatedBy2Controller = createAndInitializeController(new AccountByAuthorMatchCodeController(createdByToMatchCode));
        this.accountUpdatedBy1Controller = createAndInitializeController(new AccountByAuthorMatchCodeController(updatedByFromMatchCode));
        this.accountUpdatedBy2Controller = createAndInitializeController(new AccountByAuthorMatchCodeController(updatedByToMatchCode));
        this.statusComboController = createAndInitializeController(new AccountStatusComboController(statusAccountComboBox));
    }

    private <T extends MatchCodeBaseController> T createAndInitializeController(T controller) {
        controller.initialize(activeSession, output, input);
        return controller;
    }

    public static class Builder {
        private ActiveSession activeSession;
        private ObjectOutputStream output;
        private ObjectInputStream input;

        private MatchCode accountNumberFromMatchCode;
        private MatchCode accountNumberToMatchCode;
        private MatchCode parentFromMatchCode;
        private MatchCode parentToMatchCode;
        private MatchCode subclassFromMatchCode;
        private MatchCode subclassToMatchCode;
        private MatchCode modelFromMatchCode;
        private MatchCode modelToMatchCode;
        private MatchCode modelStateFromMatchCode;
        private MatchCode modelStateToMatchCode;
        private MatchCode createdByFromMatchCode;
        private MatchCode createdByToMatchCode;
        private MatchCode updatedByFromMatchCode;
        private MatchCode updatedByToMatchCode;
        private JComboBox<String> statusAccountComboBox;

        public Builder activeSession(ActiveSession activeSession) {
            this.activeSession = activeSession;
            return this;
        }

        public Builder output(ObjectOutputStream output) {
            this.output = output;
            return this;
        }

        public Builder input(ObjectInputStream input) {
            this.input = input;
            return this;
        }

        public Builder accountNumberFromMatchCode(MatchCode accountNumberFromMatchCode) {
            this.accountNumberFromMatchCode = accountNumberFromMatchCode;
            return this;
        }

        public Builder accountNumberToMatchCode(MatchCode accountNumberToMatchCode) {
            this.accountNumberToMatchCode = accountNumberToMatchCode;
            return this;
        }

        public Builder parentFromMatchCode(MatchCode parentFromMatchCode) {
            this.parentFromMatchCode = parentFromMatchCode;
            return this;
        }

        public Builder parentToMatchCode(MatchCode parentToMatchCode) {
            this.parentToMatchCode = parentToMatchCode;
            return this;
        }

        public Builder subclassFromMatchCode(MatchCode subclassFromMatchCode) {
            this.subclassFromMatchCode = subclassFromMatchCode;
            return this;
        }

        public Builder subclassToMatchCode(MatchCode subclassToMatchCode) {
            this.subclassToMatchCode = subclassToMatchCode;
            return this;
        }

        public Builder modelFromMatchCode(MatchCode modelFromMatchCode) {
            this.modelFromMatchCode = modelFromMatchCode;
            return this;
        }

        public Builder modelToMatchCode(MatchCode modelToMatchCode) {
            this.modelToMatchCode = modelToMatchCode;
            return this;
        }

        public Builder modelStateFromMatchCode(MatchCode modelStateFromMatchCode) {
            this.modelStateFromMatchCode = modelStateFromMatchCode;
            return this;
        }

        public Builder modelStateToMatchCode(MatchCode modelStateToMatchCode) {
            this.modelStateToMatchCode = modelStateToMatchCode;
            return this;
        }

        public Builder createdByFromMatchCode(MatchCode createdByFromMatchCode) {
            this.createdByFromMatchCode = createdByFromMatchCode;
            return this;
        }

        public Builder createdByToMatchCode(MatchCode createdByToMatchCode) {
            this.createdByToMatchCode = createdByToMatchCode;
            return this;
        }

        public Builder updatedByFromMatchCode(MatchCode updatedByFromMatchCode) {
            this.updatedByFromMatchCode = updatedByFromMatchCode;
            return this;
        }

        public Builder updatedByToMatchCode(MatchCode updatedByToMatchCode) {
            this.updatedByToMatchCode = updatedByToMatchCode;
            return this;
        }

        public Builder statusAccountComboBox(JComboBox<String> statusAccountComboBox) {
            this.statusAccountComboBox = statusAccountComboBox;
            return this;
        }

        public MatchCodeControllerService build() {
            return new MatchCodeControllerService(this);
        }
    }
}
