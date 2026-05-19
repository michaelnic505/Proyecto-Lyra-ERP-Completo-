

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.COCostCenterPlanService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.COCostCenterPlanByListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCostCenterPlanByListRetrieveHandler implements RequestHandler<COCostCenterPlanByListRetrieveRequest>{

    @Override
    public Object handle(COCostCenterPlanByListRetrieveRequest request) {
       return COCostCenterPlanService.getCostCenterPlanByList(request);
    }

}
