
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
public class FICompanyCreateResponse extends BaseResponse{

    private final int generatedKey;
    
    public FICompanyCreateResponse(String sessionId,int generatedKey ,ResultType resultType) {
        super(sessionId, resultType);
        this.generatedKey = generatedKey;
    }

    public int getGeneratedKey() {
        return generatedKey;
    }
    
    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_COMPANY_CREATE;
    }
}
