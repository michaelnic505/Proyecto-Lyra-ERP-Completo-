
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
public class AccountClassesByModelRetrieveRequest extends BaseRequest{

    private final int modelId;
    
    public AccountClassesByModelRetrieveRequest(String sessionId, int userId,int modelId) {
        super(sessionId, userId);
        this.modelId = modelId;
    }

    public int getModelId() {
        return modelId;
    }
    
    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNT_CLASS_BY_MODEL_RETRIEVE;
    }
}
