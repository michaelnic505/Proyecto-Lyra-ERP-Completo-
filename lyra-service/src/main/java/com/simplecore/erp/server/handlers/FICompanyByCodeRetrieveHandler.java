

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.FICompanyService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.FICompanyByCodeRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyByCodeRetrieveHandler implements RequestHandler<FICompanyByCodeRetrieveRequest>{

    @Override
    public Object handle(FICompanyByCodeRetrieveRequest request) {
        return FICompanyService.getFICompanyByCode(request);
    }

}
