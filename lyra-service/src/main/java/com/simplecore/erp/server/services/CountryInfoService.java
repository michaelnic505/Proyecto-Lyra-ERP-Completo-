

package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.CountryInfoManager;
import com.simplecore.erp.shared.requests.types.CountriesListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CountryRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CurrenciesListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CurrencyRetrieveRequest;
import com.simplecore.erp.shared.responses.types.CountriesListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CountryRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CurrenciesListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CurrencyRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class CountryInfoService {
   
    public static CountriesListRetrieveResponse getCountryList(CountriesListRetrieveRequest request){
        return CountryInfoManager.getCountriesList(request);
    }
    
    public static CurrenciesListRetrieveResponse getCurrenciesInfo(CurrenciesListRetrieveRequest request) {
        return CountryInfoManager.getCurrenciesInfo(request);
    }
    
    public static CountryRetrieveResponse getCountryInfo(CountryRetrieveRequest request){
        return CountryInfoManager.getCountryInfo(request);
    }
    
    public static CurrencyRetrieveResponse getCurrencyInfo(CurrencyRetrieveRequest request){
        return CountryInfoManager.getCurrencyInfo(request);
    }

}
