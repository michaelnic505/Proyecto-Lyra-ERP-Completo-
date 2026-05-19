
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
public class LastAccountByParentRetrieveResponse  extends BaseResponse{

    private final String lasAccountNumber;
    
    public LastAccountByParentRetrieveResponse(String sessionId,ResultType resultType,String lastAccountNumber) {
        super(sessionId, resultType);
        this.lasAccountNumber = lastAccountNumber;
    }

    public String getLasAccountNumber() {
        return lasAccountNumber;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_LAST_ACCOUNT_NUMBER_BY_PARENT;
    }
}
