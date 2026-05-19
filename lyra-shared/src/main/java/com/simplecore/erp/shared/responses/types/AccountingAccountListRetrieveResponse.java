
package com.simplecore.erp.shared.responses.types;

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
public class AccountingAccountListRetrieveResponse extends BaseResponse {
    
    private final Object[][] accountListDataSource;

    public AccountingAccountListRetrieveResponse(String sessionId,ResultType resultType,List<Object[]> dataSource) {
        super(sessionId, resultType);
        this.accountListDataSource = dataSource.toArray(new Object[0][0]);
    }

    public Object[][] getAccountListDataSource() {
        return accountListDataSource;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNTING_ACCOUNT_LIST_RETRIEVE;
    }
}
