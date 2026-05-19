
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
public class ChartOfAccountExistsRequest extends BaseRequest{

    private String chartOfAccountCode;
    
    public ChartOfAccountExistsRequest(String sessionId, int userId,String chartOfAccountCode) {
        super(sessionId, userId);
        this.chartOfAccountCode = chartOfAccountCode;
    }

    public String getChartOfAccountCode() {
        return chartOfAccountCode;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_CHART_OF_ACCOUNT_EXISTS_CHECK;
    }

}
