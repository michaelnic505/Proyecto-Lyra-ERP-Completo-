

package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.FICompanyManager;
import com.simplecore.erp.shared.requests.types.FICompanyByCodeRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICompanyCreateRequest;
import com.simplecore.erp.shared.requests.types.FICompanyExistenceCheckRequest;
import com.simplecore.erp.shared.requests.types.FICompanyByListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICompanyDocumentsRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICompanyModifyRequest;
import com.simplecore.erp.shared.requests.types.FICompanyStatusListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.FICompanyByCodeRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICompanyCreateResponse;
import com.simplecore.erp.shared.responses.types.FICompanyExistenceCheckResponse;
import com.simplecore.erp.shared.responses.types.FICompanyByListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICompanyDocumentsRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICompanyModifyResponse;
import com.simplecore.erp.shared.responses.types.FICompanyStatusListRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyService {

    public static FICompanyCreateResponse createFICompany(FICompanyCreateRequest request) {
        return FICompanyManager.createFICompany(request);
    }
    
    public static FICompanyModifyResponse modifyFICompany(FICompanyModifyRequest request){
        return FICompanyManager.modifyFICompany(request);
    }
    
    public static FICompanyByCodeRetrieveResponse getFICompanyByCode(FICompanyByCodeRetrieveRequest request){
        return FICompanyManager.getFICompanyByCode(request);
    }
    
    public static FICompanyByListRetrieveResponse getFICompanyByList(FICompanyByListRetrieveRequest request){
        return FICompanyManager.getFICompanyList(request);
    }

    public static FICompanyStatusListRetrieveResponse getFICompanyStatusList(FICompanyStatusListRetrieveRequest request) {
        return FICompanyManager.getFICompanyStatusList(request);
    }
    
    public static FICompanyExistenceCheckResponse checkFICompanyExistence(FICompanyExistenceCheckRequest request){
        return FICompanyManager.isFICompanyCreated(request);
    }
    
    public static FICompanyDocumentsRetrieveResponse getFICompanyDocuments(FICompanyDocumentsRetrieveRequest request){
        return FICompanyManager.getFICompanyDocuments(request);
    }
}
