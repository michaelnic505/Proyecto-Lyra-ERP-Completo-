

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.CountryInfoService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.CountryRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class CountryRetrieveRequestHandler implements RequestHandler<CountryRetrieveRequest>{

    @Override
    public Object handle(CountryRetrieveRequest request) {
        return CountryInfoService.getCountryInfo(request);
    }

}
