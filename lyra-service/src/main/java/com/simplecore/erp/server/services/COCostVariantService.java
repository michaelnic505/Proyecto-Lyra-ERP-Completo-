
package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.COCostVariantManager;
import com.simplecore.erp.shared.requests.types.COCostVariantByCodeRetrieveRequest;
import com.simplecore.erp.shared.requests.types.COCostVariantListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.COCostVariantByCodeRetrieveResponse;
import com.simplecore.erp.shared.responses.types.COCostVariantListRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCostVariantService {

    public static COCostVariantListRetrieveResponse getCostVariantList(COCostVariantListRetrieveRequest request){
        return COCostVariantManager.getCostVariantList(request);
    }
    
    public static COCostVariantByCodeRetrieveResponse getCostVariantByCode(COCostVariantByCodeRetrieveRequest request){
        return COCostVariantManager.getCostVariantByCode(request);
    }
    
}
