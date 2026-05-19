
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
public class LastAccountBySubclassRetrieveResponse extends BaseResponse{

    private final String lastAccountNumber;

    public LastAccountBySubclassRetrieveResponse(String sessionId,ResultType resultType,String lastAccountNumber) {
        super(sessionId, resultType);
        this.lastAccountNumber = lastAccountNumber;
    }

    public String getLastAccountNumber() {
        return lastAccountNumber;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_LAST_ACCOUNT_NUMBER_BY_SUBCLASS;
    }

}
