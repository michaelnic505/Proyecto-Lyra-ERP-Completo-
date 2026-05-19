

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.util.InitCreateChartOfAccountsFormState;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountsService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.init.InitModifyChartOfAccounts;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.form.ChartOfAccountFormPanel;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JTextField;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class InitModifyProceedButtonController {

    private InitModifyChartOfAccounts panel;
    private InitCreateChartOfAccountsFormState formState;
    private InitModifyChartOfAccountDataHandler dataHandler;
    private ChartOfAccountsService retrieveService;
    private ActiveSession session;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private SystemMessages notificator = new SystemMessages();

    private InitModifyProceedButtonController(Builder builder) {
        this.panel = builder.panel;
        this.formState = builder.formState;
        this.retrieveService = builder.retrieveService;
        this.session = builder.session;
        this.output = builder.output;
        this.input = builder.input;
        proceedButtonEvent();
    }
    
    public void setDataHandler(InitModifyChartOfAccountDataHandler dataHandler){
        this.dataHandler = dataHandler;
    }
    
    private void proceedButtonEvent() {
        JTextField textF = panel.getChartOfAccountMatchCode().getTextField();
        panel.getProceedButton().addActionListener(e -> {
            if (textF.getText().trim().isEmpty()) {
                textF.requestFocus();
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
                return;
            }
            String value = panel.getChartOfAccountMatchCode().getTextField().getText();
            if(retrieveService.searchChartByCode(value,dataHandler.getChartOfAccountListener())){
                openNextModule(formState.getDto());
            }
        });
    }

    private void openNextModule(ChartOfAccountDTO chartDTO) {
        ChartOfAccountFormPanel createChart = new ChartOfAccountFormPanel(chartDTO,OperationType.MODIFY);
        createChart.initialize(panel.getTransactionCode(), session, output, input);
        PanelManager.goToPanel(createChart);
    }

    public static class Builder {

        private InitModifyChartOfAccounts panel;
        private InitCreateChartOfAccountsFormState formState;
        private ChartOfAccountsService retrieveService;
        private ActiveSession session;
        private ObjectOutputStream output;
        private ObjectInputStream input;

        public Builder withPanel(InitModifyChartOfAccounts panel) {
            this.panel = panel;
            return this;
        }

        public Builder withFormState(InitCreateChartOfAccountsFormState formState) {
            this.formState = formState;
            return this;
        }

        public Builder withRetrieveService(ChartOfAccountsService retrieveService) {
            this.retrieveService = retrieveService;
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

        public InitModifyProceedButtonController build() {
            return new InitModifyProceedButtonController(this);
        }
    }

}
