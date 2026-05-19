
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountModelService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountModelRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountModelRetrieveHandler implements RequestHandler<AccountModelRetrieveRequest> {

    @Override
    public Object handle(AccountModelRetrieveRequest request) {
        return AccountModelService.getAccountModel(request);
    }

}
