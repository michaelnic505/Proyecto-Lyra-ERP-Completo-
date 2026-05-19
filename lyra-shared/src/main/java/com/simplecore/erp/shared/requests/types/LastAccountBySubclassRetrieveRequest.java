
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
public class LastAccountBySubclassRetrieveRequest  extends BaseRequest {

    private final int subclassId;
    
    public LastAccountBySubclassRetrieveRequest(String sessionId, int userId,int subclassId) {
        super(sessionId, userId);
        this.subclassId = subclassId;
    }

    public int getSubclassId() {
        return subclassId;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_LAST_ACCOUNT_NUMBER_BY_SUBCLASS;
    }
}
