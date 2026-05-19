

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.AccountRangesService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.AccountRangesModelRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountRangesByModelIdRetrieveHandler implements RequestHandler<AccountRangesModelRetrieveRequest>{

    @Override
    public Object handle(AccountRangesModelRetrieveRequest request) {
        return AccountRangesService.getRangesByModelId(request);
    }

}
