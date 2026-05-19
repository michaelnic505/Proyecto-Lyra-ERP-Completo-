
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
public class TaxSchemaRetrieveRequest extends BaseRequest{

    private int schemaId;
    
    public TaxSchemaRetrieveRequest(String sessionId, int userId,String schemaId) {
        super(sessionId, userId);
        this.schemaId = Integer.parseInt(schemaId);
    }

    public int getSchedaId() {
        return schemaId;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_TAX_SCHEMA_RETRIEVE;
    }

}
