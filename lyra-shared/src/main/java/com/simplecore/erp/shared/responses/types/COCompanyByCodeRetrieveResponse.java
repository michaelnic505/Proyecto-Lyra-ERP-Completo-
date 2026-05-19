

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.COCompanyDTO;
import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCompanyByCodeRetrieveResponse extends BaseResponse{

    private final COCompanyDTO companyDTO;
    public COCompanyByCodeRetrieveResponse(String sessionId, ResultType resultType,COCompanyDTO companyDTO) {
        super(sessionId, resultType);
        this.companyDTO = companyDTO;
    }

    public COCompanyDTO getCompanyDTO() {
        return companyDTO;
    }
    

    @Override
    public ResponseType getResponseType() {
        return ResponseType.CO_COMPANY_BY_CODE;
    }
}
