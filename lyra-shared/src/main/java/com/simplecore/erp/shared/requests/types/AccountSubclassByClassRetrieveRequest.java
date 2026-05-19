
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
public class AccountSubclassByClassRetrieveRequest extends BaseRequest {

    private final int modelId;
    private final int classId;

    public AccountSubclassByClassRetrieveRequest(String sessionId, int userId, int modelId, int classId) {
        super(sessionId, userId);
        this.modelId = modelId;
        this.classId = classId;
    }

    public int getModelId() {
        return modelId;
    }

    public int getClassId() {
        return classId;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNT_SUBCLASS_BY_CLASS_RETRIEVE;
    }
}
