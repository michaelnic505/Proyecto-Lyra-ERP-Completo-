

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountModelService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountModelChangeRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountModelChangeHandler implements RequestHandler<AccountModelChangeRequest>{

    @Override
    public Object handle(AccountModelChangeRequest request) {
        return AccountModelService.changeAccountModel(request);
    }

}
