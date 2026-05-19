
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
public class LastAccountByParentRetrieveRequest  extends BaseRequest {

    private final int parentAccountId;

    public LastAccountByParentRetrieveRequest(String sessionId, int userId,int parentAccountId) {
        super(sessionId, userId);
        this.parentAccountId = parentAccountId;
    }

    public int getParentAccountId() {
        return parentAccountId;
    }
    

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_LAST_ACCOUNT_NUMBER_BY_PARENT;
    }
}
