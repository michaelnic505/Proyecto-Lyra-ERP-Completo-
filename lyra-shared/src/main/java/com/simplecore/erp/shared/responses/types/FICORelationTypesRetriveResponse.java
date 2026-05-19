

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICORelationTypesRetriveResponse extends BaseResponse{

    private final String[] relationTypes;
    public FICORelationTypesRetriveResponse(String sessionId, ResultType resultType,List<String> relationTypes) {
        super(sessionId, resultType);
        this.relationTypes = relationTypes.toArray(String[]::new);
    }

    public String[] getRelationTypes() {
        return relationTypes;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FICO_RELATION_TYPES;
    }
}
