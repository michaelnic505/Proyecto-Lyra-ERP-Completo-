
package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.AccountingAccountManager;
import com.simplecore.erp.shared.requests.types.AccountingAccountChangeRequest;
import com.simplecore.erp.shared.requests.types.AccountingAccountCreateRequest;
import com.simplecore.erp.shared.requests.types.AccountingAccountListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountingAccountRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountingAccountStatusListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountsBySubclassRetrieveRequest;
import com.simplecore.erp.shared.requests.types.LastAccountByParentRetrieveRequest;
import com.simplecore.erp.shared.requests.types.LastAccountBySubclassRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountingAccountChangeResponse;
import com.simplecore.erp.shared.responses.types.AccountingAccountCreateResponse;
import com.simplecore.erp.shared.responses.types.AccountingAccountListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountingAccountRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountingAccountStatusListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountsBySubclassRetrieveResponse;
import com.simplecore.erp.shared.responses.types.LastAccountByParentRetrieveResponse;
import com.simplecore.erp.shared.responses.types.LastAccountBySubclassRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountingAccountService {

    public static AccountsBySubclassRetrieveResponse getAccountsBySubclass(AccountsBySubclassRetrieveRequest request){
        return AccountingAccountManager.getAccountsBySubclass(request);
    }
    
    public static LastAccountByParentRetrieveResponse getLastAccountNumberByParentId(LastAccountByParentRetrieveRequest request){
        return AccountingAccountManager.getLastAccountByParentId(request);
    }
    
    public static AccountingAccountCreateResponse createAccountingAccount(AccountingAccountCreateRequest request){
        return AccountingAccountManager.createAccountingAccount(request);
    }
    
    public static LastAccountBySubclassRetrieveResponse getLastAccountBySubclassId(LastAccountBySubclassRetrieveRequest request){
        return AccountingAccountManager.getLastAccountBySubclassId(request);
    }
    
    public static AccountingAccountRetrieveResponse getAccountingAccount(AccountingAccountRetrieveRequest request){
        return AccountingAccountManager.getAccountingAccount(request);
    }
    
    public static AccountingAccountListRetrieveResponse getAccountingAccountListDataSource(AccountingAccountListRetrieveRequest request){
        return AccountingAccountManager.getAccountingAccountListDataSource(request);
    }
    
    public static AccountingAccountChangeResponse changeAccountingAccount(AccountingAccountChangeRequest request){
        return AccountingAccountManager.changeAccountingAccount(request);
    }
    
    public static AccountingAccountStatusListRetrieveResponse getAccountingAccountStatusList(AccountingAccountStatusListRetrieveRequest request) {
        return AccountingAccountManager.getAccountingAccountStatusList(request);
    }
}
