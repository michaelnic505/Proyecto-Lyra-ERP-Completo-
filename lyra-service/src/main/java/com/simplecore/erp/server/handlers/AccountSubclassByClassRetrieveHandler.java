
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.managers.AccountSubclassManager;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountSubclassByClassRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountSubclassByClassRetrieveHandler implements RequestHandler<AccountSubclassByClassRetrieveRequest>{

    @Override
    public Object handle(AccountSubclassByClassRetrieveRequest request) {
        return AccountSubclassManager.getSubclassesByClassId(request);
    }

}
