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
public class TransactionRequest extends BaseRequest {

    private final String role;
    private final String transaction;

    public TransactionRequest(String role, String transaction, String sessionId, int userId) {
        super(sessionId, userId);
        this.role = role;
        this.transaction = transaction;
    }
    
    public String getRole() {
        return role;
    }

    public String getTransaction() {
        return transaction;
    }
    
    @Override
    public RequestType getRequestType() {
        return RequestType.TRANSACTION_PERMISSIONS;
    }

}
