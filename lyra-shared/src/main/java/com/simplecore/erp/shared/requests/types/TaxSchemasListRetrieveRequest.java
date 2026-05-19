
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
public class TaxSchemasListRetrieveRequest extends BaseRequest{

    public TaxSchemasListRetrieveRequest(String sessionId, int userId) {
        super(sessionId, userId);
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_TAX_SCHEMAS_LIST_RETRIEVE;
    }

}
