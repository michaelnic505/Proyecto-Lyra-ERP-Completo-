

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
public class AccountSubclassCreateResponse extends BaseResponse{

    private final List<AccountSubclassDTO> subclassesList;
    private final String messages;
    
    public AccountSubclassCreateResponse( String sessionId,
            ResultType resultType,
            List<AccountSubclassDTO> subclassesList,
            String messages) {
        super(sessionId,resultType);
        this.subclassesList = subclassesList;
        this.messages = messages;
    }

    public String getMessages() {
        return messages;
    }
    
    public List<AccountSubclassDTO> getSubclassesList() {
        return subclassesList;
    }
        
    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_SUBCLASS_CREATE;
    }
}
