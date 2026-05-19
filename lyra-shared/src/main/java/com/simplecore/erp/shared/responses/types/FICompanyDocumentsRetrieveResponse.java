

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.FIDocumentsDTO;
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
public class FICompanyDocumentsRetrieveResponse extends BaseResponse {

    private FIDocumentsDTO documentsDTO;

    public FICompanyDocumentsRetrieveResponse(String sessionId, ResultType resultType, FIDocumentsDTO documentsDTO) {
        super(sessionId, resultType);
        this.documentsDTO = documentsDTO;
    }

    public FIDocumentsDTO getDocumentsDTO() {
        return documentsDTO;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_COMPANY_DOCUMENTS_RETRIEVE;
    }
}
