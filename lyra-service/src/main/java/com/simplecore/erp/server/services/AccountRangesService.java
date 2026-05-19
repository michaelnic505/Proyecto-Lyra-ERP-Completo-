

package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.AccountRangesByModelIdManager;
import com.simplecore.erp.shared.requests.types.AccountRangesModelRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountRangesModelRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountRangesService {
    public static AccountRangesModelRetrieveResponse getRangesByModelId(AccountRangesModelRetrieveRequest request){
        return AccountRangesByModelIdManager.getRangesByModelId(request);
    }
}
