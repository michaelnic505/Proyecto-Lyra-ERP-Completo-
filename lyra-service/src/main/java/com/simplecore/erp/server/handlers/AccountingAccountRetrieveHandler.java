

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountingAccountService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountingAccountRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountingAccountRetrieveHandler implements RequestHandler<AccountingAccountRetrieveRequest>{

    @Override
    public Object handle(AccountingAccountRetrieveRequest request) {
            return AccountingAccountService.getAccountingAccount(request);
    }

}
