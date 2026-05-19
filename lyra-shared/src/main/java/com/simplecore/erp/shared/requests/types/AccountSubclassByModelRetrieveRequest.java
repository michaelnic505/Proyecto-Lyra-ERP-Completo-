
package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;
import java.io.Serializable;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountSubclassByModelRetrieveRequest extends BaseRequest {

    private final int modelId;
    public AccountSubclassByModelRetrieveRequest(String sessionId, int userId,int modelId) {
        super(sessionId, userId);
        this.modelId = modelId;
    }

    public int getModelId() {
        return modelId;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNT_SUBCLASSES_BY_MODEL_ID_RETRIEVE;
    }
    
}
