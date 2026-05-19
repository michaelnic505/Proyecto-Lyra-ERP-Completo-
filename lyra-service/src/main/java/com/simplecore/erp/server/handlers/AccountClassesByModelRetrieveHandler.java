
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountModelService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountClassesByModelRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountClassesByModelRetrieveHandler implements RequestHandler<AccountClassesByModelRetrieveRequest>{

    @Override
    public Object handle(AccountClassesByModelRetrieveRequest request) {
        return AccountModelService.getAccountClassesByModel(request);
    }

}
