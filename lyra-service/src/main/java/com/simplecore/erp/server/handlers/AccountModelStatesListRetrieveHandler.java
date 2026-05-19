

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.managers.AccountModelManager;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountModelStatesListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountModelStatesListRetrieveHandler implements RequestHandler<AccountModelStatesListRetrieveRequest>{

    @Override
    public Object handle(AccountModelStatesListRetrieveRequest request) {
        return AccountModelManager.getModelStatesList(request);
    }

}
