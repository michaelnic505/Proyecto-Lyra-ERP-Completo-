
package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;
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
public class ChartOfAccountCreateRequest extends BaseRequest{

    private ChartOfAccountDTO chartOfAccountDTO;
            
    public ChartOfAccountCreateRequest(String sessionId, int userId,ChartOfAccountDTO chartOfAccountDTO) {
        super(sessionId, userId);
        this.chartOfAccountDTO = chartOfAccountDTO;
    }

    public ChartOfAccountDTO getChartOfAccountDTO() {
        return chartOfAccountDTO;
    }
    
    @Override
    public RequestType getRequestType() {
        return RequestType.FI_CHART_OF_ACCOUNT_CREATE;
    }

}
