

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.FICOAssociationsDTO;
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
public class FICORelationByFICompanyRetrieveResponse extends BaseResponse{

    private FICOAssociationsDTO associationsDTO;
    public FICORelationByFICompanyRetrieveResponse(String sessionId, ResultType resultType,FICOAssociationsDTO associationsDTO) {
        super(sessionId, resultType);
        this.associationsDTO = associationsDTO;
    }

    public FICOAssociationsDTO getAssociationsDTO() {
        return associationsDTO;
    }
    

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FICO_RELATION_BY_ID_RETRIEVE;
    }

}
