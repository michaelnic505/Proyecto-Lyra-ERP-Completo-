

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.FICompanyService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.FICompanyDocumentsRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyDocumentsRetrieveHandler implements RequestHandler<FICompanyDocumentsRetrieveRequest>{

    @Override
    public Object handle(FICompanyDocumentsRetrieveRequest request) {
        return FICompanyService.getFICompanyDocuments(request);
    }

}
