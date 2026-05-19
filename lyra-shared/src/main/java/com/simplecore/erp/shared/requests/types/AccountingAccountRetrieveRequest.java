

package com.simplecore.erp.shared.requests.types;

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
public class AccountingAccountRetrieveRequest extends BaseRequest {

    private final String accountingAccountCode;

    public AccountingAccountRetrieveRequest(String sessionId, int userId,String accountingAccountCode) {
        super(sessionId, userId);
        this.accountingAccountCode = accountingAccountCode;
    }

    public String getAccountingAccountCode() {
        return accountingAccountCode;
    }

    
    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNTING_ACCOUNT_RETRIEVE;
    }

}
