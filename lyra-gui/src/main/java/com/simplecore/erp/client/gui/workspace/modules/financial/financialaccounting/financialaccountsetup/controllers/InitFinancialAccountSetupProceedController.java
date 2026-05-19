

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.controllers;

import com.simplecore.erp.client.abstractions.AbstractProceedButton;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services.InitFinancialAccountSetupDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services.InitFinancialAccountSetupFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form.FinancialAccountSetup;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.init.InitFinancialAccountSetup;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class InitFinancialAccountSetupProceedController extends AbstractProceedButton<
        InitFinancialAccountSetup,
        InitFinancialAccountSetupFormState,
        InitFinancialAccountSetupDataHandler>  {

    public InitFinancialAccountSetupProceedController(Builder builder) {
        super(
            builder.panel,
            builder.formState,
            builder.dataHandler,
            builder.output,
            builder.input,
            builder.session,
            builder.operationType
        );
        this.transactionCode = panel.getTransactionCode();
        setButtonEvents();
    }
    
    private void setButtonEvents() {
        panel.getProceedButton().addActionListener(e -> openNextModule());
    }

    @Override
    protected void openNextModule() {
        if(!dataHandler.areAllComponentsFullyFilled()){
            return;
        }
        
        FICompanyDTO companyDTO = formState.getCompanyDTO();
        FinancialAccountSetup setupForm = new FinancialAccountSetup(operationType,companyDTO);
        setupForm.initialize(transactionCode, session, output, input);
        PanelManager.goToPanel(setupForm);
    }
    
    public static class Builder {

        InitFinancialAccountSetup panel;
        InitFinancialAccountSetupFormState formState;
        InitFinancialAccountSetupDataHandler dataHandler;
        ObjectOutputStream output;
        ObjectInputStream input;
        ActiveSession session;
        OperationType operationType;

        public Builder withPanel(InitFinancialAccountSetup panel) {
            this.panel = panel;
            return this;
        }

        public Builder withFormState(InitFinancialAccountSetupFormState formState) {
            this.formState = formState;
            return this;
        }

        public Builder withDataHandler(InitFinancialAccountSetupDataHandler dataHandler) {
            this.dataHandler = dataHandler;
            return this;
        }

        public Builder withOutputStream(ObjectOutputStream output) {
            this.output = output;
            return this;
        }

        public Builder withInputStream(ObjectInputStream input) {
            this.input = input;
            return this;
        }

        public Builder withSession(ActiveSession session) {
            this.session = session;
            return this;
        }

        public Builder withOperationType(OperationType operationType) {
            this.operationType = operationType;
            return this;
        }

        public InitFinancialAccountSetupProceedController build() {
            return new InitFinancialAccountSetupProceedController(this);
        }

    }
}
