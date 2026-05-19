package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.models.dto.AccountSubclassDTO;
import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;
import java.util.List;
/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountSubclassCreateRequest extends BaseRequest {

    private final List<AccountSubclassDTO> accountSubclasses;

    public AccountSubclassCreateRequest(String sessionId, int userId,List<AccountSubclassDTO> accountSubclasses) {
        super(sessionId, userId);
        this.accountSubclasses = accountSubclasses;
    }

    public List<AccountSubclassDTO> getAccountSubclasses() {
        return accountSubclasses;
    }
    
    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNT_SUBCLASSES_CREATE;
    }
}
