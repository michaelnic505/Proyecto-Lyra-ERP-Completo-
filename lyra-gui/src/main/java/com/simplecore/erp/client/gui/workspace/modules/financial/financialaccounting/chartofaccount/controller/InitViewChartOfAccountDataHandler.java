

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.util.InitViewChartOfAccountsFormState;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.init.InitViewChartOfAccounts;
import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class InitViewChartOfAccountDataHandler {

    private InitViewChartOfAccounts panel;
    private InitViewChartOfAccountsFormState formState;
    private TableSelectionListener chartOfAccountListener;

    // Constructor privado para obligar el uso del builder
    private InitViewChartOfAccountDataHandler(Builder builder) {
        this.panel = builder.panel;
        this.formState = builder.formState;
        chartOfAccountListener();
    }

    private void chartOfAccountListener() {
        chartOfAccountListener = (Object[] data) -> {
            String chartCode = (data[0] == null || data[0].toString().isEmpty()) ? "" : data[0].toString();
            String chartName = (data[1] == null || data[1].toString().isEmpty()) ? "" : data[1].toString();
            ChartOfAccountDTO chartDTO = (data.length > 2 && data[2] instanceof ChartOfAccountDTO)
                    ? (ChartOfAccountDTO) data[2]
                    : null;

            panel.getChartOfAccountMatchCode().getTextField().setText(chartCode);
            panel.getChartOfAccountLb().setText(chartName);
            formState.setDto(chartDTO);
        };
    }

    public TableSelectionListener getChartOfAccountListener() {
        return chartOfAccountListener;
    }

    // Builder
    public static class Builder {

        private InitViewChartOfAccounts panel;
        private InitViewChartOfAccountsFormState formState;

        public Builder withPanel(InitViewChartOfAccounts panel) {
            this.panel = panel;
            return this;
        }

        public Builder withFormState(InitViewChartOfAccountsFormState formState) {
            this.formState = formState;
            return this;
        }

        public InitViewChartOfAccountDataHandler build() {
            return new InitViewChartOfAccountDataHandler(this);
        }
    }
}
