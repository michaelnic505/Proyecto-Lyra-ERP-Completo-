
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.CountryInfoService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.CountriesListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class CountryListRetrieneHandler implements RequestHandler<CountriesListRetrieveRequest>{

    @Override
    public Object handle(CountriesListRetrieveRequest request) {
        return CountryInfoService.getCountryList(request);
    }

}
