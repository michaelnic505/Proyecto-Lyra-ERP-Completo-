
package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountClassesByModelRetrieveResponse extends BaseResponse {

    private Map<Integer,String> classesList = new HashMap<>();
    
    public AccountClassesByModelRetrieveResponse(String sessionId,ResultType resultType,Map<Integer,String> classesList) {
        super(sessionId, resultType);
        this.classesList = classesList;
    }

    public Map<Integer, String> getClassesList() {
        return classesList;
    }
    
    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_CLASS_BY_MODEL;
    }
}
