

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller;

import com.simplecore.erp.client.abstractions.AbstractProceedButton;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitCreateFICompanyDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitCreateFICompanyFormState;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.FICompanyFormPanel;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.init.InitCreateFICompany;
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
public class InitCreateFICompanyProceedController extends AbstractProceedButton
        <InitCreateFICompany,InitCreateFICompanyFormState,InitCreateFICompanyDataHandler>{

    private InitCreateFICompanyProceedController(Builder builder) {
        super(builder.panel,
                builder.formState,
                builder.dataHandler,
                builder.output,
                builder.input,
                builder.session,
                builder.operationType);
        transactionCode = panel.getTransactionCode();
        proceedButtonEvent();
    }
   
    private void proceedButtonEvent() {
        panel.getProceedButton().addActionListener(e -> {
            if (!dataHandler.areAllComponentsFullyFilled()) {
                return;
            }
            openNextModule();
        });
    }

    @Override
    protected void openNextModule() {
        String companyName = formState.getCompanyName();
        
        FICompanyFormPanel fiCompany = new FICompanyFormPanel(operationType);
        fiCompany.getCompanyNameTF().setText(companyName);
        fiCompany.initialize(transactionCode, session, output, input);
        PanelManager.goToPanel(fiCompany);
    }

    public static class Builder {

        private InitCreateFICompany panel;
        private InitCreateFICompanyFormState formState;
        private InitCreateFICompanyDataHandler dataHandler;
        private ObjectOutputStream output;
        private ObjectInputStream input;
        private ActiveSession session;
        private OperationType operationType;

        public Builder withPanel(InitCreateFICompany panel) {
            this.panel = panel;
            return this;
        }

        public Builder withFormState(InitCreateFICompanyFormState formState) {
            this.formState = formState;
            return this;
        }

        public Builder withDataHandler(InitCreateFICompanyDataHandler dataHandler) {
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

        public InitCreateFICompanyProceedController build() {
            return new InitCreateFICompanyProceedController(this);
        }
    }
}
