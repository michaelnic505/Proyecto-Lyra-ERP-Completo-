
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
public class COCompanyByCodeRetrieveRequest extends BaseRequest{

    private final String companyCode;
    
    public COCompanyByCodeRetrieveRequest(String sessionId, int userId,String companyCode) {
        super(sessionId, userId);
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }
    

    @Override
    public RequestType getRequestType() {
        return RequestType.CO_COMPANY_BY_CODE;
    }
}
