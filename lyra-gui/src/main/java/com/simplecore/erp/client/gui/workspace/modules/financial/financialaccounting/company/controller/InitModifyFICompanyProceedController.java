

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller;

import com.simplecore.erp.client.abstractions.AbstractProceedButton;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitModifyFICompanyDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitModifyFICompanyFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.FICompanyFormPanel;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.init.InitModifyFICompany;
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
public class InitModifyFICompanyProceedController extends AbstractProceedButton<
        InitModifyFICompany,
        InitModifyFICompanyFormState,
        InitModifyFICompanyDataHandler> {
    
    private FICompanyRetrieveService companyRetrieveService;

    private InitModifyFICompanyProceedController(Builder builder) {
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
        
        FICompanyFormPanel formPanel = new FICompanyFormPanel(operationType,companyDTO);
        formPanel.initialize(transactionCode, session, output, input);
        PanelManager.goToPanel(formPanel);
    }

    public static class Builder {
        private InitModifyFICompany panel;
        private InitModifyFICompanyFormState formState;
        private InitModifyFICompanyDataHandler dataHandler;
        private ObjectOutputStream output;
        private ObjectInputStream input;
        private ActiveSession session;
        private OperationType operationType;

        public Builder withPanel(InitModifyFICompany panel) {
            this.panel = panel;
            return this;
        }

        public Builder withFormState(InitModifyFICompanyFormState formState) {
            this.formState = formState;
            return this;
        }

        public Builder withDataHandler(InitModifyFICompanyDataHandler dataHandler) {
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

        public InitModifyFICompanyProceedController build() {
            return new InitModifyFICompanyProceedController(this);
        }
    }
}
