

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.util;

import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class InitViewChartOfAccountsFormState extends FormState {

    private ChartOfAccountDTO dto;

    public InitViewChartOfAccountsFormState(int fieldsCount) {
        super(fieldsCount);
    }

    public ChartOfAccountDTO getDto() {return dto;}
    public void setDto(ChartOfAccountDTO dto) {this.dto = dto;updateFilledFields(this);}
}
