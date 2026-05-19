
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.COCompanyService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.COCompanyByCodeRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCompanyByCodeRetrieveHandler implements RequestHandler<COCompanyByCodeRetrieveRequest>{

    @Override
    public Object handle(COCompanyByCodeRetrieveRequest request) {
        return COCompanyService.getCOCompanyByCode(request);
    }

}
