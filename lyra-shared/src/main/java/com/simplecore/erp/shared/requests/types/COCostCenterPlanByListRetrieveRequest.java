

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
public class COCostCenterPlanByListRetrieveRequest extends BaseRequest{

    public COCostCenterPlanByListRetrieveRequest(String sessionId, int userId) {
        super(sessionId, userId);
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.CO_COST_CENTER_PLAN_BY_LIST;
    }

}
