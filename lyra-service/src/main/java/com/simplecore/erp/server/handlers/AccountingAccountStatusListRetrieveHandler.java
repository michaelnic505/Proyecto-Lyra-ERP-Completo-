
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountingAccountService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountingAccountStatusListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountingAccountStatusListRetrieveHandler implements RequestHandler<AccountingAccountStatusListRetrieveRequest>{

    @Override
    public Object handle(AccountingAccountStatusListRetrieveRequest request) {
        return AccountingAccountService.getAccountingAccountStatusList(request);
    }

}
