
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

public class AccountModelChangeRequest extends BaseRequest {

    private final int modelId;
    private final String modelDescription;
    private final String systemState;
    private final String modifiedBy;
    private final String modifiedAt;

    public AccountModelChangeRequest(String sessionId, int userId, int modelId, String modelDescription, String systemState, String modifiedBy, String modifiedAt) {
        super(sessionId, userId);
        this.modelId = modelId;
        this.modelDescription = modelDescription;
        this.systemState = systemState;
        this.modifiedBy = modifiedBy;
        this.modifiedAt = modifiedAt;
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public String getSystemState() {
        return systemState;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }
    
    public String getModifiedAt() {
        return modifiedAt;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNT_MODEL_CHANGE;
    }
}
