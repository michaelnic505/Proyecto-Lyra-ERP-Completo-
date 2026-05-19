
package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountingAccountCreateRequest extends BaseRequest {

    private final AccountingAccountDTO newAccount;

    public AccountingAccountCreateRequest(String sessionId, int userId, AccountingAccountDTO newAccount) {
        super(sessionId, userId);
        this.newAccount = newAccount;
    }

    public AccountingAccountDTO getNewAccount() {
        return newAccount;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNTING_ACCOUNT_CREATE;
    }
}
