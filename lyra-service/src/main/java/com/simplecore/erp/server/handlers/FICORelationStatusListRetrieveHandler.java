
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.FICORelationService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.FICORelationStatusListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICORelationStatusListRetrieveHandler implements RequestHandler<FICORelationStatusListRetrieveRequest>{

    @Override
    public Object handle(FICORelationStatusListRetrieveRequest request) {
        return FICORelationService.getFICORelationStatusList(request);
    }
}
