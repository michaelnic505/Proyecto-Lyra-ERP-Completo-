
package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
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
public class AccountingAccountRetrieveResponse extends BaseResponse{

    private final AccountingAccountDTO accountingAccount;

    public AccountingAccountRetrieveResponse(String sessionId,ResultType resultType,AccountingAccountDTO accountingAccount) {
        super(sessionId,resultType);
        this.accountingAccount = accountingAccount;
    }

    public AccountingAccountDTO getAccountingAccount() {
        return accountingAccount;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNTING_ACCOUNT_RETRIEVE;
    }

}
