

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.FICompanyDTO;
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
public class FICompanyByCodeRetrieveResponse extends BaseResponse{

    private final FICompanyDTO companyDTO;
    public FICompanyByCodeRetrieveResponse(String sessionId, ResultType resultType,FICompanyDTO companyDTO) {
        super(sessionId, resultType);
        this.companyDTO = companyDTO;
    }

    public FICompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_COMPANY_BY_CODE_RETRIEVE;
    }

}
