
package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.COCostCenterPlanManager;
import com.simplecore.erp.shared.requests.types.COCostCenterPlanByIdRetrieveRequest;
import com.simplecore.erp.shared.requests.types.COCostCenterPlanByListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.COCostCenterPlanByIdRetrieveResponse;
import com.simplecore.erp.shared.responses.types.COCostCenterPlanByListRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCostCenterPlanService {

    public static COCostCenterPlanByListRetrieveResponse getCostCenterPlanByList(COCostCenterPlanByListRetrieveRequest request){
        return COCostCenterPlanManager.getCostCenterPlanByList(request);
    }
    
    public static COCostCenterPlanByIdRetrieveResponse getCostCenterPlanById(COCostCenterPlanByIdRetrieveRequest request){
        return COCostCenterPlanManager.getCostCenterPlanById(request);
    }
}
