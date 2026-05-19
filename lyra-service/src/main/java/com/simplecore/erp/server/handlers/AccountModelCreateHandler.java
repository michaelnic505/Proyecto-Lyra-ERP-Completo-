
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountModelService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountModelCreateRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountModelCreateHandler implements RequestHandler<AccountModelCreateRequest> {

    @Override
    public Object handle(AccountModelCreateRequest request) {
        return AccountModelService.createAccountModel(request);
    }

}
