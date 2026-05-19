
package com.simplecore.erp.server.main;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import com.simplecore.erp.server.handlers.AccountClassesByModelRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountClassesRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountModelRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountModelListRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountRangesByModelIdRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountSubclassCreateHandler;
import com.simplecore.erp.server.handlers.AccountModelChangeHandler;
import com.simplecore.erp.server.handlers.AccountModelCreateHandler;
import com.simplecore.erp.server.handlers.AccountModelStateChangeHandler;
import com.simplecore.erp.server.handlers.AccountModelStatesListRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountSubclassChangeHandler;
import com.simplecore.erp.server.handlers.AccountSubclassDeleteHandler;
import com.simplecore.erp.server.handlers.AccountSubclassByClassRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountSubclassByIdRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountSubclassListRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountSubclassesByModelRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountingAccountChangeRequestHandler;
import com.simplecore.erp.server.handlers.AccountingAccountCreateRequestHandler;
import com.simplecore.erp.server.handlers.AccountingAccountListRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountingAccountRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountingAccountStatusListRetrieveHandler;
import com.simplecore.erp.server.handlers.AccountsBySubclassRetrieveHandler;
import com.simplecore.erp.server.handlers.AcountingAccountFilteredQueryHandler;
import com.simplecore.erp.server.handlers.COCompanyByCodeRetrieveHandler;
import com.simplecore.erp.server.handlers.COCompanyListRetrieveHandler;
import com.simplecore.erp.server.handlers.COCostCenterPlanByIdRetrieveHandler;
import com.simplecore.erp.server.handlers.COCostCenterPlanByListRetrieveHandler;
import com.simplecore.erp.server.handlers.COCostVariantByCodeRetrieneHandler;
import com.simplecore.erp.server.handlers.COCostVariantByListRetrieveHandler;
import com.simplecore.erp.server.handlers.ChartOfAccountChangeHandler;
import com.simplecore.erp.server.handlers.ChartOfAccountCreateHandler;
import com.simplecore.erp.server.handlers.ChartOfAccountExistsCheckHandler;
import com.simplecore.erp.server.handlers.ChartOfAccountsListRetrieveHandler;
import com.simplecore.erp.server.handlers.ChartOfAccountRetrieveHandler;
import com.simplecore.erp.server.handlers.ChartOfAccountStatusRetrieveHandler;
import com.simplecore.erp.server.handlers.ChartOfAccountsRetrieveHandler;
import com.simplecore.erp.server.handlers.CountryListRetrieneHandler;
import com.simplecore.erp.server.handlers.CountryRetrieveRequestHandler;
import com.simplecore.erp.server.handlers.CurrenciesListRetrieveHandler;
import com.simplecore.erp.server.handlers.CurrencyRetrieveRequestHandler;
import com.simplecore.erp.server.handlers.FICORelationRetrieveHandler;
import com.simplecore.erp.server.handlers.FICORelationStatusListRetrieveHandler;
import com.simplecore.erp.server.handlers.FICORelationTypesRetrieveHandler;
import com.simplecore.erp.server.handlers.FICompanyByCodeRetrieveHandler;
import com.simplecore.erp.server.handlers.FICompanyByListRetrieveHandler;
import com.simplecore.erp.server.handlers.FICompanyCreateHandler;
import com.simplecore.erp.server.handlers.FICompanyDocumentsRetrieveHandler;
import com.simplecore.erp.server.handlers.FICompanyExistenceCheckHandler;
import com.simplecore.erp.server.handlers.FICompanyModifyHandler;
import com.simplecore.erp.server.handlers.FICompanyStatusListRetrieveHandler;
import com.simplecore.erp.server.handlers.FinancialAccountSetupRetrieveHandler;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.server.handlers.LoginHandler;
import com.simplecore.erp.server.handlers.LogoutHandler;
import com.simplecore.erp.server.handlers.LastAccountByParentRetrieveHandler;
import com.simplecore.erp.server.handlers.LastAccountBySubclassRetrieveHandler;
import com.simplecore.erp.server.handlers.SocietyClassHandler;
import com.simplecore.erp.server.handlers.SocietyClassListHandler;
import com.simplecore.erp.server.handlers.SystemUsersListRetrieveHandler;
import com.simplecore.erp.server.handlers.TaxSchemaRetrieveHandler;
import com.simplecore.erp.server.handlers.TaxSchemasListRetrieveHandler;
import com.simplecore.erp.server.handlers.TimezoneByNameRetrieveHandler;
import com.simplecore.erp.server.handlers.TimezonesRetrieveHandler;
import com.simplecore.erp.server.handlers.TransactionHandler;
import com.simplecore.erp.server.services.SessionService;
import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;
import com.simplecore.erp.shared.requests.types.LoginRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.LogoutResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the dispatching of requests to their respective handlers.
 * (Maneja la distribución de solicitudes a sus respectivos manejadores.)
 */
public class RequestDispatcher {
    // Stores the mapping between request types and their handlers (Almacena la relación entre tipos de solicitudes y sus manejadores)
    private static final Map<RequestType, RequestHandler<?>> handlersMap = new HashMap<>();

    static {
        // Register the login request handler (Registra el manejador de solicitudes de inicio de sesión)
        handlersMap.put(RequestType.LOGIN, new LoginHandler());
        handlersMap.put(RequestType.LOGOUT, new LogoutHandler());
        handlersMap.put(RequestType.TRANSACTION_PERMISSIONS, new TransactionHandler());
        handlersMap.put(RequestType.SOCIETY_CLASS, new SocietyClassHandler());
        handlersMap.put(RequestType.SOCIETY_CLASS_LIST, new SocietyClassListHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_CLASS_RETRIEVE, new AccountClassesRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_MODEL_CREATE, new AccountModelCreateHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_MODEL_RETRIEVE, new AccountModelRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_MODEL_LIST_RETRIEVE, new AccountModelListRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_RANGES_BY_MODEL_ID_RETRIEVE, new AccountRangesByModelIdRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_MODEL_CHANGE, new AccountModelChangeHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_SUBCLASSES_CREATE, new AccountSubclassCreateHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_SUBCLASSES_BY_MODEL_ID_RETRIEVE, new AccountSubclassesByModelRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_SUBCLASS_DELETE, new AccountSubclassDeleteHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_SUBCLASS_CHANGE, new AccountSubclassChangeHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_MODEL_STATE_CHANGE, new AccountModelStateChangeHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_CLASS_BY_MODEL_RETRIEVE, new AccountClassesByModelRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_SUBCLASS_BY_CLASS_RETRIEVE, new AccountSubclassByClassRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNTS_BY_SUBCLASS_RETRIEVE, new AccountsBySubclassRetrieveHandler());
        handlersMap.put(RequestType.FI_LAST_ACCOUNT_NUMBER_BY_PARENT, new LastAccountByParentRetrieveHandler());
        handlersMap.put(RequestType.FI_LAST_ACCOUNT_NUMBER_BY_SUBCLASS, new LastAccountBySubclassRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNTING_ACCOUNT_CREATE, new AccountingAccountCreateRequestHandler());
        handlersMap.put(RequestType.FI_ACCOUNTING_ACCOUNT_CHANGE, new AccountingAccountChangeRequestHandler());
        handlersMap.put(RequestType.FI_ACCOUNTING_ACCOUNT_RETRIEVE, new AccountingAccountRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNTING_ACCOUNT_LIST_RETRIEVE, new AccountingAccountListRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_SUBCLASS_BY_ID_RETRIEVE, new AccountSubclassByIdRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_SUBCLASSES_LIST, new AccountSubclassListRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNT_MODEL_STATES_LIST, new AccountModelStatesListRetrieveHandler());
        handlersMap.put(RequestType.SYSTEM_USERS_LIST, new SystemUsersListRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNTING_ACCOUNT_STATUS_LIST, new AccountingAccountStatusListRetrieveHandler());
        handlersMap.put(RequestType.FI_ACCOUNTING_ACCOUNT_FILTER_QUERY_RETRIEVE, new AcountingAccountFilteredQueryHandler());
        handlersMap.put(RequestType.COUNTRIES_LIST, new CountryListRetrieneHandler());
        handlersMap.put(RequestType.CURRENCIES_LIST, new CurrenciesListRetrieveHandler());
        handlersMap.put(RequestType.COUNTRY_BY_CODE, new CountryRetrieveRequestHandler());
        handlersMap.put(RequestType.CURRENCY_BY_CODE, new CurrencyRetrieveRequestHandler());
        handlersMap.put(RequestType.FI_ACCOUNTING_STANDARDS_RETRIEVE, new ChartOfAccountsRetrieveHandler());
        handlersMap.put(RequestType.FI_CHART_OF_ACCOUNT_STATUS_RETRIEVE, new ChartOfAccountStatusRetrieveHandler());
        handlersMap.put(RequestType.FI_TAX_SCHEMAS_LIST_RETRIEVE, new TaxSchemasListRetrieveHandler());
        handlersMap.put(RequestType.FI_TAX_SCHEMA_RETRIEVE, new TaxSchemaRetrieveHandler());
        handlersMap.put(RequestType.FI_CHART_OF_ACCOUNT_CREATE, new ChartOfAccountCreateHandler());
        handlersMap.put(RequestType.FI_CHART_OF_ACCOUNT_EXISTS_CHECK, new ChartOfAccountExistsCheckHandler());
        handlersMap.put(RequestType.FI_CHART_OF_ACCOUNTS_LIST_RETRIEVE, new ChartOfAccountsListRetrieveHandler());
        handlersMap.put(RequestType.FI_CHART_OF_ACCOUNT_RETRIEVE, new ChartOfAccountRetrieveHandler());
        handlersMap.put(RequestType.FI_CHART_OF_ACCOUNT_CHANGE, new ChartOfAccountChangeHandler());
        handlersMap.put(RequestType.TIMEZONES_LIST, new TimezonesRetrieveHandler());
        handlersMap.put(RequestType.TIMEZONES_BY_NAME, new TimezoneByNameRetrieveHandler());
        handlersMap.put(RequestType.FI_COMPANY_STATUS_LIST, new FICompanyStatusListRetrieveHandler());
        handlersMap.put(RequestType.CO_COMPANY_LIST, new COCompanyListRetrieveHandler());
        handlersMap.put(RequestType.CO_COMPANY_BY_CODE, new COCompanyByCodeRetrieveHandler());
        handlersMap.put(RequestType.FICO_RELATION_STATUS_LIST, new FICORelationStatusListRetrieveHandler());
        handlersMap.put(RequestType.CO_COST_VARIANT_LIST_RETRIEVE, new COCostVariantByListRetrieveHandler());
        handlersMap.put(RequestType.CO_COST_VARIANT_BY_CODE_RETRIEVE, new COCostVariantByCodeRetrieneHandler());
        handlersMap.put(RequestType.CO_COST_CENTER_PLAN_BY_LIST, new COCostCenterPlanByListRetrieveHandler());
        handlersMap.put(RequestType.CO_COST_CENTER_PLAN_BY_ID, new COCostCenterPlanByIdRetrieveHandler());
        handlersMap.put(RequestType.FICO_RELATION_TYPES_LIST, new FICORelationTypesRetrieveHandler());
        handlersMap.put(RequestType.FI_COMPANY_CREATE, new FICompanyCreateHandler());
        handlersMap.put(RequestType.FI_COMPANY_EXISTS, new FICompanyExistenceCheckHandler());
        handlersMap.put(RequestType.FI_COMPANY_BY_LIST_RETRIEVE, new FICompanyByListRetrieveHandler());
        handlersMap.put(RequestType.FI_COMPANY_BY_CODE_RETRIEVE, new FICompanyByCodeRetrieveHandler());
        handlersMap.put(RequestType.FICO_RELATION_BY_FI_COMPANY_RETRIEVE, new FICORelationRetrieveHandler());
        handlersMap.put(RequestType.FI_COMPANY_DOCUMENTS_RETRIEVE, new FICompanyDocumentsRetrieveHandler());
        handlersMap.put(RequestType.FI_COMPANY_MODIFY, new FICompanyModifyHandler());
        handlersMap.put(RequestType.FI_ACCOUNTS_BY_CHART_OF_ACCOUNT_CODE, new FinancialAccountSetupRetrieveHandler());
    }
    
    /**
     * Dispatches the incoming request to the appropriate handler.(Distribuye la solicitud entrante al manejador adecuado.)
     *
     * @param request  The request to be handled (La solicitud a manejar)
     * @return The response from the handler (La respuesta del manejador)
     */
    public static Object dispatch(BaseRequest request) {
        // Validate session before processing (Valida la sesión antes de procesar)
        if (request instanceof LoginRequest login) {
            if (SessionService.getActiveSession(login.getUserId()) != null) {
                if (!SessionService.isSessionValid(request.getSessionId(), request.getUserId())) {
                    return new LogoutResponse(request.getSessionId(),ResultType.UNAUTHORIZED ,true, true, "Invalid SessionID");
                }
            }
        }
        
        // Retrieve the handler for the given request type (Obtiene el manejador correspondiente al tipo de solicitud)
        RequestHandler<?> requestHandler = handlersMap.get(request.getRequestType());
        if (requestHandler != null) {
            // Process the request using the found handler (Procesa la solicitud usando el manejador encontrado)
            return ((RequestHandler<BaseRequest>) requestHandler).handle(request);
        }
        // Return a message for unknown request types (Devuelve un mensaje para tipos de solicitudes desconocidos)
        return "Unknown request type";
    }
}
