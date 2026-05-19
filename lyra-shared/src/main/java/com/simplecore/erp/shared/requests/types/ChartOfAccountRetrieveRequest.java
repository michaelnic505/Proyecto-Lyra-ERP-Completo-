
package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountRetrieveRequest extends BaseRequest{

    private final String chartOfAccountCode;
    
    public ChartOfAccountRetrieveRequest(String sessionId, int userId,String chartOfAccountCode) {
        super(sessionId, userId);
        this.chartOfAccountCode = chartOfAccountCode;
    }

    public String getChartOfAccountCode() {
        return chartOfAccountCode;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_CHART_OF_ACCOUNT_RETRIEVE;
    }
}
