

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
public class FICompanyDocumentsRetrieveRequest extends BaseRequest {

    private final long companyID;
    public FICompanyDocumentsRetrieveRequest(String sessionId, int userId, long companyID) {
        super(sessionId, userId);
        this.companyID = companyID;
    }

    public long getCompanyID() {
        return companyID;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_COMPANY_DOCUMENTS_RETRIEVE;
    }
}
