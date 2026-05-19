

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.COCostVariantService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.COCostVariantByCodeRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCostVariantByCodeRetrieneHandler implements RequestHandler<COCostVariantByCodeRetrieveRequest>{

    @Override
    public Object handle(COCostVariantByCodeRetrieveRequest request) {
        return COCostVariantService.getCostVariantByCode(request);
    }

}
