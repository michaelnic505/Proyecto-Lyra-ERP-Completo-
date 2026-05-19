
package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;
import java.io.Serializable;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountsBySubclassRetrieveResponse extends BaseResponse {

    private final List<AccountingAccountDTO> accountsList;

    public AccountsBySubclassRetrieveResponse(String sessionId, ResultType resultType, List<AccountingAccountDTO> accountsList) {
        super(sessionId, resultType);
        this.accountsList = accountsList;
    }

    public List<AccountingAccountDTO> getAccountsList() {
        return accountsList;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNTS_BY_SUBCLASS_RETRIEVE;
    }
}
