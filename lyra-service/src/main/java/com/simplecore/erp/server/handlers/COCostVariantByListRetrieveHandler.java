
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.COCostVariantService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.COCostVariantListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCostVariantByListRetrieveHandler implements RequestHandler<COCostVariantListRetrieveRequest>{

    @Override
    public Object handle(COCostVariantListRetrieveRequest request) {
        return COCostVariantService.getCostVariantList(request);
    }

}
