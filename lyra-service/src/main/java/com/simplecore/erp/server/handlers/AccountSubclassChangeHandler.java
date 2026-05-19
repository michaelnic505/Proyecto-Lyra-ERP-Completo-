
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountSubclassService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountSubclassChangeRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountSubclassChangeHandler implements RequestHandler<AccountSubclassChangeRequest>{

    @Override
    public Object handle(AccountSubclassChangeRequest request) {
        return AccountSubclassService.changeSubclassOnModel(request);
    }

}
