
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
public class AccountModelRetrieveRequest extends BaseRequest {

    private final String modelName;

    public AccountModelRetrieveRequest(String sessionId, int userId, String modelName) {
        super(sessionId, userId);
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNT_MODEL_RETRIEVE;
    }
}
