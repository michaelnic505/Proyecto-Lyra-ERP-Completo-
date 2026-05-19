

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.ChartOfAccountsService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.ChartOfAccountCreateRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountCreateHandler implements RequestHandler<ChartOfAccountCreateRequest>{
    @Override
    public Object handle(ChartOfAccountCreateRequest request) {
        return ChartOfAccountsService.createChartOfAccount(request);
    }
}
