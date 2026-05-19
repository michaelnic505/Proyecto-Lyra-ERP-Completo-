

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;
import java.sql.Timestamp;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountingAccountCreateResponse  extends BaseResponse{

    private final int accountId;
    private final Timestamp createdAt;
    private final String status;
    private final boolean closed;
 
    public AccountingAccountCreateResponse(String sessionId, 
            ResultType resultType, 
            int accountId, 
            Timestamp createdAt, 
            String status, 
            boolean isClosed) {
        super(sessionId, resultType);
        this.accountId = accountId;
        this.createdAt = createdAt;
        this.status = status;
        this.closed = isClosed;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public boolean isClosed() {
        return closed;
    }

    public String getStatus() {
        return status;
    }

    public int getAccountId() {
        return accountId;
    }


    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNTING_ACCOUNT_CREATE;
    }

}
