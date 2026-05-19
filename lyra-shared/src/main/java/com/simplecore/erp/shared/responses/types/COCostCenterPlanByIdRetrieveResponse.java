
package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.COCostCenterPlanDTO;
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
public class COCostCenterPlanByIdRetrieveResponse extends BaseResponse{

    private final COCostCenterPlanDTO planDTO;
    public COCostCenterPlanByIdRetrieveResponse(String sessionId, ResultType resultType,COCostCenterPlanDTO planDTO) {
        super(sessionId, resultType);
        this.planDTO = planDTO;
    }

    public COCostCenterPlanDTO getPlanDTO() {
        return planDTO;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.CO_COST_CENTER_PLAN_BY_ID;
    }

}
