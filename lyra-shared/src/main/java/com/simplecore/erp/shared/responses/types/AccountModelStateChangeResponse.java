

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
public class AccountModelStateChangeResponse extends BaseResponse{

    private final String state;
    
    public AccountModelStateChangeResponse(String sessionId, ResultType resultType, String state) {
        super(sessionId, resultType);
        this.state = state;
    }

    public String getState() {
        return state;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_MODEL_STATE_CHANGE;
    }

}
