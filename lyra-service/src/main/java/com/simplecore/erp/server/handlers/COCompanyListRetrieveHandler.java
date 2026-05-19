

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.COCompanyService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.COCompanyListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCompanyListRetrieveHandler implements RequestHandler<COCompanyListRetrieveRequest>{

    @Override
    public Object handle(COCompanyListRetrieveRequest request) {
         return COCompanyService.getCOCompanyList(request);
    }

}
