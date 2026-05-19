
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountModelService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountModelListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountModelListRetrieveHandler implements RequestHandler<AccountModelListRetrieveRequest>{

    @Override
    public Object handle(AccountModelListRetrieveRequest request) {
        return AccountModelService.getAccountModelList(request);
    }

}
