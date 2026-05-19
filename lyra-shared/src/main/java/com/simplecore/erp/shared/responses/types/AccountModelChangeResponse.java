
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

public class AccountModelChangeResponse extends BaseResponse {

    public AccountModelChangeResponse(String sessionId, ResultType resultType) {
        super(sessionId, resultType);
    }
    
    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_MODEL_CHANGE;
    }
}

