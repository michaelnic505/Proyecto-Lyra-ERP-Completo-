

package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.AccountClassesManager;
import com.simplecore.erp.shared.requests.types.AccountClassesRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountClassesRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountClassesService {

    public static AccountClassesRetrieveResponse getAccountClasses(AccountClassesRetrieveRequest request){
        return AccountClassesManager.getFinancialAccountingClasses(request);
    }

}
