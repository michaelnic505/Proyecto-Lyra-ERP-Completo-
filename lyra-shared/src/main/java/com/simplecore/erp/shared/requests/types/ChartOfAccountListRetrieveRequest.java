

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
public class ChartOfAccountListRetrieveRequest extends BaseRequest{

    public ChartOfAccountListRetrieveRequest(String sessionId, int userId) {
        super(sessionId, userId);
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_CHART_OF_ACCOUNTS_LIST_RETRIEVE;
    }
}
