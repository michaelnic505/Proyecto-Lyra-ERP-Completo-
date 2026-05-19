
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountingAccountService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountingAccountListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountingAccountListRetrieveHandler implements RequestHandler<AccountingAccountListRetrieveRequest>{

    @Override
    public Object handle(AccountingAccountListRetrieveRequest request) {
        return AccountingAccountService.getAccountingAccountListDataSource(request);
    }

}
