
package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;
import java.io.Serializable;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountingAccountChangeRequest extends BaseRequest {

    private final AccountingAccountDTO accountingAccount;

    public AccountingAccountChangeRequest(String sessionId, int userId,AccountingAccountDTO accountingAccount) {
        super(sessionId, userId);
        this.accountingAccount = accountingAccount;
    }

    public AccountingAccountDTO getAccountingAccount() {
        return accountingAccount;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNTING_ACCOUNT_CHANGE;
    }

}
