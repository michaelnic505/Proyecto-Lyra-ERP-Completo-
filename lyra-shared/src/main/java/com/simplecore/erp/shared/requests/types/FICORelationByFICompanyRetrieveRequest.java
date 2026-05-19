

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
public class FICORelationByFICompanyRetrieveRequest extends BaseRequest{

    private final long companyIdFI;
    public FICORelationByFICompanyRetrieveRequest(String sessionId, int userId,long companyIdFI) {
        super(sessionId, userId);
        this.companyIdFI = companyIdFI;
    }

    public long getCompanyIdFI() {
        return companyIdFI;
    }

    @Override
    public RequestType getRequestType() {
         return RequestType.FICO_RELATION_BY_FI_COMPANY_RETRIEVE;
    }
}
