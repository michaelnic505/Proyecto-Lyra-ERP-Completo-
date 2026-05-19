

package com.simplecore.erp.shared.responses.types;

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
public class ChartOfAccountCreateResponse extends BaseResponse{

    private int chartOfAccountId;

    public ChartOfAccountCreateResponse(String sessionId, ResultType resultType, int chartOfAccountId) {
        super(sessionId, resultType);
        this.chartOfAccountId = chartOfAccountId;
    }

    public int getChartOfAccountId() {
        return chartOfAccountId;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_CHART_OF_ACCOUNT_CREATE;
    }
}
