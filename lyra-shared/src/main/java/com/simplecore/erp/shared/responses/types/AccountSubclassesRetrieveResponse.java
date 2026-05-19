
package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.AccountSubclassDTO;
import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountSubclassesRetrieveResponse extends BaseResponse{

    private final List<AccountSubclassDTO> subclassesList;
    
    public AccountSubclassesRetrieveResponse(String sessionId,ResultType resultType,List<AccountSubclassDTO> subclassesList) {
        super(sessionId, resultType);
        this.subclassesList = subclassesList;
    }

    public List<AccountSubclassDTO> getSubclassesList() {
        return subclassesList;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_SUBCLASSES_RETRIEVE;
    }
}
