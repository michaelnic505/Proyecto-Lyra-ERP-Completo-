

package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.FinancialAccountSetupManager;
import com.simplecore.erp.shared.requests.types.AccountsByChartOfAccountCodeRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountsByChartOfAccountCodeRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FinancialAccountSetupServices {

    public static AccountsByChartOfAccountCodeRetrieveResponse getAccountsByChartOfAccountCode(AccountsByChartOfAccountCodeRetrieveRequest request) {
        return FinancialAccountSetupManager.getAccountsByChartOfAccountCode(request);
    }
}
