

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.FinancialAccountSetupServices;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountsByChartOfAccountCodeRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FinancialAccountSetupRetrieveHandler implements RequestHandler<AccountsByChartOfAccountCodeRetrieveRequest> {

    @Override
    public Object handle(AccountsByChartOfAccountCodeRetrieveRequest request) {
        return FinancialAccountSetupServices.getAccountsByChartOfAccountCode(request);
    }

}
