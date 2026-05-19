
package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.AccountSubclassManager;
import com.simplecore.erp.shared.requests.types.AccountSubclassChangeRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassCreateRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassDeleteRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassByClassRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassByIdRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassByModelRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountSubclassChangeResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassCreateResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassDeleteResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassesRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountSubclassService {
    /**
     * Inserts subclasses into an account model.
     * 
     * @param request the request containing the subclasses to be inserted
     * @return the response containing the inserted subclasses
     */
    public static AccountSubclassCreateResponse insertSubclasses(AccountSubclassCreateRequest request) {
        return AccountSubclassManager.insertSubclasses(request);
    }
    
    public static AccountSubclassesRetrieveResponse getSubclassesByModelId(AccountSubclassByModelRetrieveRequest request){
        return AccountSubclassManager.getAccountSubclassesByModelId(request);
    }
    
    public static AccountSubclassDeleteResponse deleteSubclassOnModel(AccountSubclassDeleteRequest request){
        return AccountSubclassManager.deleteAccountSubclassById(request);
    }
    
    public static AccountSubclassChangeResponse changeSubclassOnModel(AccountSubclassChangeRequest request){
        return AccountSubclassManager.changeAccountSubclassById(request);
    }
    
    public static AccountSubclassesRetrieveResponse getSubclassesByClassId(AccountSubclassByClassRetrieveRequest request){
        return AccountSubclassManager.getSubclassesByClassId(request);
    }
    
    public static AccountSubclassesRetrieveResponse getSubclassesBySubclassId(AccountSubclassByIdRetrieveRequest request){
        return AccountSubclassManager.getSubclassesBySubclassId(request);
    }
    
    public static AccountSubclassListRetrieveResponse getSubclassesList(AccountSubclassListRetrieveRequest request){
        return AccountSubclassManager.getSubclassesList(request);
    }
    
}
