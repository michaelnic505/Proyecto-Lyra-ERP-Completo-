
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.COCostCenterPlanService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.COCostCenterPlanByIdRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCostCenterPlanByIdRetrieveHandler implements RequestHandler<COCostCenterPlanByIdRetrieveRequest>{

    @Override
    public Object handle(COCostCenterPlanByIdRetrieveRequest request) {
        return COCostCenterPlanService.getCostCenterPlanById(request);
    }

}
