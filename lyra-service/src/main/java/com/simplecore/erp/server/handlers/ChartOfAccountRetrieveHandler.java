

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.ChartOfAccountsService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.ChartOfAccountRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountRetrieveHandler implements RequestHandler<ChartOfAccountRetrieveRequest>{

    @Override
    public Object handle(ChartOfAccountRetrieveRequest request) {
        return ChartOfAccountsService.getChartOfAccountByCode(request);
    }
}
