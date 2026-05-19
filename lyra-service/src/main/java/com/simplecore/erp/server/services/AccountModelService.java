package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.AccountModelManager;
import com.simplecore.erp.shared.requests.types.AccountClassesByModelRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountModelListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountModelRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountModelChangeRequest;
import com.simplecore.erp.shared.requests.types.AccountModelCreateRequest;
import com.simplecore.erp.shared.requests.types.AccountModelStateChangeRequest;
import com.simplecore.erp.shared.requests.types.AccountModelStatesListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountClassesByModelRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountModelListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountModelRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountModelChangeResponse;
import com.simplecore.erp.shared.responses.types.AccountModelCreateResponse;
import com.simplecore.erp.shared.responses.types.AccountModelStateChangeResponse;
import com.simplecore.erp.shared.responses.types.AccountModelStatesListRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountModelService {

    /**
     * Creates a new account model.
     * 
     * @param request the request containing account model details
     * @return the response with the created account model information
     */
    public static AccountModelCreateResponse createAccountModel(AccountModelCreateRequest request) {
        return AccountModelManager.createAccountModel(request);
    }

    /**
     * Retrieves an account model by its ID.
     * 
     * @param request the request containing the account model ID
     * @return the response with the requested account model details
     */
    public static AccountModelRetrieveResponse getAccountModel(AccountModelRetrieveRequest request) {
        return AccountModelManager.getAccountModel(request);
    }

    /**
     * Retrieves a list of all account models.
     * 
     * @param request the request parameters for filtering the list
     * @return the response containing the list of account models
     */
    public static AccountModelListRetrieveResponse getAccountModelList(AccountModelListRetrieveRequest request) {
        return AccountModelManager.getAccountModelList(request);
    }

    /**
     * Modifies an existing account model.
     * 
     * @param request the request containing the changes to be applied
     * @return the response indicating the success or failure of the operation
     */
    public static AccountModelChangeResponse changeAccountModel(AccountModelChangeRequest request) {
        return AccountModelManager.changeAccountModel(request);
    }
    
    
    
    public static AccountModelStateChangeResponse changeAccountModelState(AccountModelStateChangeRequest request){
        return AccountModelManager.changeAccountModelState(request);
    }
    
    
    
    public static AccountClassesByModelRetrieveResponse getAccountClassesByModel(AccountClassesByModelRetrieveRequest request){
        return AccountModelManager.getClassesByModelId(request);
    }
    
    
    
    public static AccountModelStatesListRetrieveResponse getModelStatesList(AccountModelStatesListRetrieveRequest request) {
        return AccountModelManager.getModelStatesList(request);
    }

    
}
