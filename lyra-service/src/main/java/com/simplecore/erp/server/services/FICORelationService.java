

package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.FICORelationManager;
import com.simplecore.erp.shared.requests.types.FICORelationByFICompanyRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICORelationStatusListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICORelationTypesRetriveRequest;
import com.simplecore.erp.shared.responses.types.FICORelationByFICompanyRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICORelationStatusListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICORelationTypesRetriveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICORelationService {

    public static FICORelationStatusListRetrieveResponse getFICORelationStatusList(FICORelationStatusListRetrieveRequest request) {
        return FICORelationManager.getFICORelationStatusList(request);
    }
    
    public static FICORelationTypesRetriveResponse getFICORelationTypes(FICORelationTypesRetriveRequest request){
        return FICORelationManager.getFICORelationTypes(request);
    }
    
    public static FICORelationByFICompanyRetrieveResponse getFICORelationsByFICompany(FICORelationByFICompanyRetrieveRequest request){
        return FICORelationManager.getFICORelationsByFICompany(request);
    }
    
}
