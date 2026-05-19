

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountModelService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountModelStateChangeRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountModelStateChangeHandler implements RequestHandler<AccountModelStateChangeRequest>{

    @Override
    public Object handle(AccountModelStateChangeRequest request) {
        return AccountModelService.changeAccountModelState(request);
    }

}
