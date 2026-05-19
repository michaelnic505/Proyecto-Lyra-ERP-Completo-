
package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.ChartOfAccountsManager;
import com.simplecore.erp.shared.requests.types.AccountingStandardsRetrieveRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountChangeRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountCreateRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountExistsRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountRetrieveRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountStatusRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountingStandardsRetrieveResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountChangeResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountCreateResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountExistsResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountsListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountRetrieveResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountStatusRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountsService {
    
    public static AccountingStandardsRetrieveResponse getStandards(AccountingStandardsRetrieveRequest request){
        return ChartOfAccountsManager.getStandards(request);
    }
    
    public static ChartOfAccountStatusRetrieveResponse getStatus(ChartOfAccountStatusRetrieveRequest request) {
        return ChartOfAccountsManager.getStatus(request);
    }
    
    public static ChartOfAccountCreateResponse createChartOfAccount(ChartOfAccountCreateRequest request){
        return ChartOfAccountsManager.createChartOfAccount(request);
    }

    public static ChartOfAccountExistsResponse isChartOfAccountExists(ChartOfAccountExistsRequest request){
        return ChartOfAccountsManager.isChartOfAccountExists(request);
    }
    
    public static ChartOfAccountsListRetrieveResponse getChartOfAccountList(ChartOfAccountListRetrieveRequest request) {
        return ChartOfAccountsManager.getChartOfAccountList(request);
    }
    
    public static ChartOfAccountRetrieveResponse getChartOfAccountByCode(ChartOfAccountRetrieveRequest request){
        return ChartOfAccountsManager.getChartOfAccountByCode(request);
    }
    
    public static ChartOfAccountChangeResponse changeChartOfAccount(ChartOfAccountChangeRequest request){
        return ChartOfAccountsManager.changeChartOfAccount(request);
    }
}
