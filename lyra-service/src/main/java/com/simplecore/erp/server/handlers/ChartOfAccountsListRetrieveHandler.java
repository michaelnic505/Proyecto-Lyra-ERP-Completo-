

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.ChartOfAccountsService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.ChartOfAccountListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountsListRetrieveHandler implements RequestHandler<ChartOfAccountListRetrieveRequest>{

    @Override
    public Object handle(ChartOfAccountListRetrieveRequest request) {
        return ChartOfAccountsService.getChartOfAccountList(request);
    }

}
