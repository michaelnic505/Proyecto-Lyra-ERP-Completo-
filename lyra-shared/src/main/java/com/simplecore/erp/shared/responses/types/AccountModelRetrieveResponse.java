

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

public class AccountModelRetrieveResponse extends BaseResponse{
    private final int modelId;
    private final String modelName;
    private final String modelDescription;
    private final String createdAt;
    private final String createdBy;
    private final String modifiedAt;
    private final String modifiedBy;
    private final String systemState;
    private final boolean foundIt;

    public AccountModelRetrieveResponse(String sessionId,ResultType resultType ,int modelId, String modelName, String modelDescription, String createdAt,
                                String createdBy, String modifiedAt, String modifiedBy, String systemState, boolean foundIt) {
        super(sessionId,resultType);
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelDescription = modelDescription;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.systemState = systemState;
        this.foundIt = foundIt;
    }

    private String message;
    public AccountModelRetrieveResponse(String message) {
        super(null,null);
        this.modelId = 0;
        this.modelName = null;
        this.modelDescription = null;
        this.createdAt = null;
        this.createdBy = null;
        this.modifiedAt = null;
        this.modifiedBy = null;
        this.systemState = null;
        this.foundIt = false;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_MODEL_RETRIEVE;
    }

    public boolean isFoundIt() {
        return foundIt;
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public String getSystemState() {
        return systemState;
    }
}

