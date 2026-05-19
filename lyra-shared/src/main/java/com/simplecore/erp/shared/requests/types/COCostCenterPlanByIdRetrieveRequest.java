

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
public class COCostCenterPlanByIdRetrieveRequest extends BaseRequest {

    private final long costCenterPlanID;
    public COCostCenterPlanByIdRetrieveRequest(String sessionId, int userId, long costCenterPlanID) {
        super(sessionId, userId);
        this.costCenterPlanID = costCenterPlanID;
    }

    public long getCostCenterPlanID() {
        return costCenterPlanID;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.CO_COST_CENTER_PLAN_BY_ID;
    }
}
