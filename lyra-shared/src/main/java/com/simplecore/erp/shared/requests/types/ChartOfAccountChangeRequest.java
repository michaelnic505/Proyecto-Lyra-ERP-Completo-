
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
public class ChartOfAccountChangeRequest extends BaseRequest{

    private ChartOfAccountDTO dtoChange;
    
    public ChartOfAccountChangeRequest(String sessionId, int userId, ChartOfAccountDTO dtoChange) {
        super(sessionId, userId);
        this.dtoChange = dtoChange;
    }

    public ChartOfAccountDTO getDtoChange() {
        return dtoChange;
    }
    
    @Override
    public RequestType getRequestType() {
        return RequestType.FI_CHART_OF_ACCOUNT_CHANGE;
    }
}
