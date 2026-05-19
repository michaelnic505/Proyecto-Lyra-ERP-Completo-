

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountSubclassService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountSubclassListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountSubclassListRetrieveHandler implements RequestHandler<AccountSubclassListRetrieveRequest>{

    @Override
    public Object handle(AccountSubclassListRetrieveRequest request) {
        return AccountSubclassService.getSubclassesList(request);
    }

}
