

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.COCostVariantDTO;
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
public class COCostVariantByCodeRetrieveResponse extends BaseResponse{

    private final COCostVariantDTO costVariantDTO;
    
    public COCostVariantByCodeRetrieveResponse(String sessionId, ResultType resultType, COCostVariantDTO costVariantDTO) {
        super(sessionId, resultType);
        this.costVariantDTO = costVariantDTO; 
    }

    public COCostVariantDTO getCostVariantDTO() {
        return costVariantDTO;
    }
    
    @Override
    public ResponseType getResponseType() {
        return ResponseType.CO_COST_VARIANT_BY_CODE_RETRIEVE;
    }
}
