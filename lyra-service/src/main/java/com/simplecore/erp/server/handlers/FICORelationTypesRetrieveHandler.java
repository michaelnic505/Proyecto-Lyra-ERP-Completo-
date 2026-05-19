

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.FICORelationService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.FICORelationTypesRetriveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICORelationTypesRetrieveHandler implements RequestHandler<FICORelationTypesRetriveRequest>{

    @Override
    public Object handle(FICORelationTypesRetriveRequest request) {
        return FICORelationService.getFICORelationTypes(request);
    }

}
