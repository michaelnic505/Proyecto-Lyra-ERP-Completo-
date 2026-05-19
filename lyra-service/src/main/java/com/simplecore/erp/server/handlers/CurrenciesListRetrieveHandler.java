
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.CountryInfoService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.CurrenciesListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class CurrenciesListRetrieveHandler implements RequestHandler<CurrenciesListRetrieveRequest>{

    @Override
    public Object handle(CurrenciesListRetrieveRequest request) {
        return CountryInfoService.getCurrenciesInfo(request);
    }

}
