

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
public class AccountingAccountStatusListRetrieveResponse extends BaseResponse {

    private final List<String> statusDataSource;

    public AccountingAccountStatusListRetrieveResponse(String sessionId,ResultType resultType , List<String>dataSource) {
        super(sessionId,resultType);
        this.statusDataSource = dataSource;
    }

    public List<String> getStatusDataSource() {
        return statusDataSource;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNTING_ACCOUNT_STATUS_LIST;
    }
}
