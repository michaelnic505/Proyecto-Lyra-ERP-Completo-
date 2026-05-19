
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountingAccountQueryService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AcountingAccountFilterQueryRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AcountingAccountFilteredQueryHandler implements RequestHandler<AcountingAccountFilterQueryRetrieveRequest>{

    @Override
    public Object handle(AcountingAccountFilterQueryRetrieveRequest request) {
        return AccountingAccountQueryService.getFilteredQuery(request);
    }

}
