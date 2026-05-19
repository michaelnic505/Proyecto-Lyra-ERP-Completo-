
package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;
import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountRetrieveResponse extends BaseResponse{

    private ChartOfAccountDTO chartOfAccount;
    
    public ChartOfAccountRetrieveResponse(String sessionId, ResultType resultType,ChartOfAccountDTO chartOfAccount) {
        super(sessionId, resultType);
        this.chartOfAccount = chartOfAccount;
    }

    public ChartOfAccountDTO getChartOfAccount() {
        return chartOfAccount;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_CHART_OF_ACCOUNT_RETRIEVE;
    }
}
