
package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.COCompanyManager;
import com.simplecore.erp.shared.requests.types.COCompanyByCodeRetrieveRequest;
import com.simplecore.erp.shared.requests.types.COCompanyListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.COCompanyByCodeRetrieveResponse;
import com.simplecore.erp.shared.responses.types.COCompanyListRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class COCompanyService {
    
    public static COCompanyListRetrieveResponse getCOCompanyList(COCompanyListRetrieveRequest request){
        return COCompanyManager.getCOCompanyList(request);
    }
    
    public static COCompanyByCodeRetrieveResponse getCOCompanyByCode(COCompanyByCodeRetrieveRequest request){
        return COCompanyManager.getCOCompanyByCode(request);
    }
}
