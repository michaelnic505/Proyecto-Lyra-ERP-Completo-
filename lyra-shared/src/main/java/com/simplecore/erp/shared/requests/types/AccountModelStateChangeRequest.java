

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
public class AccountModelStateChangeRequest  extends BaseRequest {
    
    private final String state;
    private final int modelId;
    
    public AccountModelStateChangeRequest(String sessionId, int userId,int modelId,String state) {
        super(sessionId, userId);
        this.modelId = modelId;
        this.state = state;
    }

    public int getModelId() {
        return modelId;
    }
    
    public String getState() {
        return state;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNT_MODEL_STATE_CHANGE;
    }
}
