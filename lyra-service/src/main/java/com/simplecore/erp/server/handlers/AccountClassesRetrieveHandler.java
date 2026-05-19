

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountClassesService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountClassesRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountClassesRetrieveHandler implements RequestHandler<AccountClassesRetrieveRequest>{
    @Override
    public Object handle(AccountClassesRetrieveRequest request) {
       return AccountClassesService.getAccountClasses(request);
    }

}
