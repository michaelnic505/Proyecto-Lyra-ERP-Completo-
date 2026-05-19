
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.ChartOfAccountsService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.ChartOfAccountStatusRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountStatusRetrieveHandler implements RequestHandler<ChartOfAccountStatusRetrieveRequest>{

    @Override
    public Object handle(ChartOfAccountStatusRetrieveRequest request) {
        return ChartOfAccountsService.getStatus(request);
    }

}
