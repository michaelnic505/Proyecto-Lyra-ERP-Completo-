
package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountModelCreateResponse  extends BaseResponse {

    private final String message;
    private final int modelId;
    private final String modelName;

    public AccountModelCreateResponse(String sessionId, ResultType resultType, int modelId, String modelName, String message) {
        super(sessionId, resultType);
        this.modelId = modelId;
        this.message = message;
        this.modelName = modelName;
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }
    
    public String getMessage() {
        return message;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_MODEL_CREATE;
    }

}
