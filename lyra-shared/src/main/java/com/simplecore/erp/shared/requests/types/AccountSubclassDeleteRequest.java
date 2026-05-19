
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

public class AccountSubclassDeleteRequest extends BaseRequest {

    private final int[] subclassId;
    private final int modelId;

    public AccountSubclassDeleteRequest(String sessionId, int userId,int[] subclassId, int modelId) {
        super(sessionId, userId);
        this.subclassId = subclassId;
        this.modelId = modelId;
    }

    public int[] getSubclassId() {
        return subclassId;
    }

    public int getModelId() {
        return modelId;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNT_SUBCLASS_DELETE;
    }
}
