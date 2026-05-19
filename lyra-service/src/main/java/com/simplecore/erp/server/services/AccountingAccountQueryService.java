
package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.AccountingAccountQueryManager;
import com.simplecore.erp.shared.requests.types.AcountingAccountFilterQueryRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AcountingAccountFilterQueryRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountingAccountQueryService {

    public static AcountingAccountFilterQueryRetrieveResponse getFilteredQuery(AcountingAccountFilterQueryRetrieveRequest request){
        return AccountingAccountQueryManager.getFilteredQuery(request);
    }
}
