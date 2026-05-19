
package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.AccountModels;
import com.simplecore.erp.server.config.database.tablecolumns.ChartOfAccounts;
import com.simplecore.erp.server.config.database.tablecolumns.CountriesInfo;
import com.simplecore.erp.server.config.database.tablecolumns.Currencies;
import com.simplecore.erp.server.config.database.tablecolumns.TaxSchemas;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;
import com.simplecore.erp.shared.requests.types.AccountingStandardsRetrieveRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountChangeRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountCreateRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountExistsRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountRetrieveRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountStatusRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.AccountingStandardsRetrieveResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountChangeResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountCreateResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountExistsResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountsListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountRetrieveResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountStatusRetrieveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountsManager {
    
    private static final Logger logger = LoggerFactory.getLogger(ChartOfAccountsManager.class);
 
    public static AccountingStandardsRetrieveResponse getStandards(AccountingStandardsRetrieveRequest request) {
     
        List<String> dataSource = new ArrayList<>();

        try (Connection conn = PooledConnectionService.getConnection();
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM "
                + DatabaseTables.FI_CHART_OF_ACCOUNTS.tableName()
                + " LIKE '" + ChartOfAccounts.ACCOUNTING_STANDARD.getColumnName() + "'")) {

            if (rs.next()) {
                String enumDef = rs.getString("Type");
                enumDef = enumDef.substring(enumDef.indexOf("(") + 1, enumDef.lastIndexOf(")"));

                // Procesar valores del ENUM y agregarlos a la lista como Object[]
                Arrays.stream(enumDef.split(","))
                        .map(s -> s.replace("'", "").trim()) // Cada valor en un Object[]
                        .forEach(dataSource::add);
                return new AccountingStandardsRetrieveResponse(request.getSessionId(),ResultType.FOUND, dataSource);
            }
            return new AccountingStandardsRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND, dataSource);

        } catch (SQLException ex) {
            logger.error("Error retrieving ENUM values", ex);
        }
        return new AccountingStandardsRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR, dataSource);
    }
    
    public static ChartOfAccountStatusRetrieveResponse getStatus(ChartOfAccountStatusRetrieveRequest request) {
       
        List<String> dataSource = new ArrayList<>();
        
        try (Connection conn = PooledConnectionService.getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM "
                + DatabaseTables.FI_CHART_OF_ACCOUNTS.tableName()
                + " LIKE '" + ChartOfAccounts.STATUS.getColumnName() + "'")) {

            if (rs.next()) {
                String enumDef = rs.getString("Type");
                enumDef = enumDef.substring(enumDef.indexOf("(") + 1, enumDef.lastIndexOf(")"));

                // Procesar valores del ENUM y agregarlos a la lista como Object[]
                Arrays.stream(enumDef.split(","))
                        .map(s -> s.replace("'", "").trim()) // Cada valor en un Object[]
                        .forEach(dataSource::add);
                return new ChartOfAccountStatusRetrieveResponse(request.getSessionId(),ResultType.FOUND, dataSource);
            }

            return new ChartOfAccountStatusRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND, dataSource);
        } catch (SQLException ex) {
            logger.error("Error retrieving ENUM values", ex);
        }
        return new ChartOfAccountStatusRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR, dataSource);
    }

    public static ChartOfAccountCreateResponse createChartOfAccount(ChartOfAccountCreateRequest request) {

        String query = Q.insertInto(DatabaseTables.FI_CHART_OF_ACCOUNTS.tableName(),
                ChartOfAccounts.CODE.getColumnName(),
                ChartOfAccounts.NAME.getColumnName(),
                ChartOfAccounts.ACCOUNT_MODEL_ID.getColumnName(),
                ChartOfAccounts.CURRENCY_CODE.getColumnName(),
                ChartOfAccounts.COUNTRY_CODE.getColumnName(),
                ChartOfAccounts.BUSINESS_TYPE.getColumnName(),
                ChartOfAccounts.INDUSTRY_CLASSIFICATION.getColumnName(),
                ChartOfAccounts.FISCAL_YEAR.getColumnName(),
                ChartOfAccounts.FISCAL_START_DATE.getColumnName(),
                ChartOfAccounts.FISCAL_END_DATE.getColumnName(),
                ChartOfAccounts.ACCOUNTING_STANDARD.getColumnName(),
                ChartOfAccounts.STATUS.getColumnName(),
                ChartOfAccounts.MULTICURRENCY_SUPPORT.getColumnName(),
                ChartOfAccounts.TAX_SCHEMA.getColumnName(),
                ChartOfAccounts.VERSION_TAG.getColumnName(),
                ChartOfAccounts.NOTES.getColumnName(),
                ChartOfAccounts.DESCRIPTION.getColumnName(),
                ChartOfAccounts.CREATED_BY.getColumnName(),
                ChartOfAccounts.SYS_TRANSACTION.getColumnName());

        try (Connection conn = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(conn);
            try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ChartOfAccountDTO dto = request.getChartOfAccountDTO();

                // Asignar los parámetros a la consulta SQL
                st.setString(1, dto.getChartOfAccountCode());
                st.setString(2, dto.getChartOfAccountName());
                st.setInt(3, dto.getAccountModelID());
                st.setString(4, dto.getCurrencyCode());
                st.setString(5, dto.getCountryCode());
                st.setString(6, dto.getBusinessType());
                st.setString(7, dto.getBusinessClass());
                st.setInt(8, dto.getFiscalYear());
                st.setTimestamp(9, dto.getFiscalStartDate());
                st.setTimestamp(10, dto.getFiscalEndDate());
                st.setString(11, dto.getAccountingStandard());
                st.setString(12, dto.getChartOfAccountStatus());
                st.setBoolean(13, dto.isMultiCurrencyAllowed());
                st.setInt(14, dto.getTaxSchemaID());
                st.setString(15, dto.getChartVersionTag());
                st.setString(16, dto.getChartOfAccountNotes());
                st.setString(17, dto.getChartOfAccountDescription());
                st.setString(18, dto.getCreatedBy());
                st.setString(19, dto.getTransactionCode());

                // Ejecutar la inserción
                int affectedRows = st.executeUpdate();
                if (affectedRows == 0) {
                    // No se insertó ninguna fila
                    PooledConnectionService.rollbackTransaction(conn);
                    return new ChartOfAccountCreateResponse(request.getSessionId(), ResultType.NOT_CREATED, -1);
                }
                // Obtener las claves generadas (si las hubiera) para el nuevo registro
                try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1); // Asumimos que el ID generado es la primera columna
                        // Commit de la transacción
                        PooledConnectionService.commitTransaction(conn);
                        return new ChartOfAccountCreateResponse(request.getSessionId(), ResultType.CREATED, generatedId);
                    } else {
                        // No se pudo obtener la clave generada
                        PooledConnectionService.rollbackTransaction(conn);
                        return new ChartOfAccountCreateResponse(request.getSessionId(), ResultType.NOT_CREATED, -1);
                    }
                }

            }

        } catch (SQLException ex) {
            logger.error("Failed to create the chart of accounts due to SQL issues.", ex);
        }
        return new ChartOfAccountCreateResponse(request.getSessionId(), ResultType.SQL_ERROR, -1);
    }
    
    public static ChartOfAccountChangeResponse changeChartOfAccount(ChartOfAccountChangeRequest request){
        String query = Q.update(DatabaseTables.FI_CHART_OF_ACCOUNTS.tableName(),
                ChartOfAccounts.NAME.getColumnName(),
                ChartOfAccounts.CURRENCY_CODE.getColumnName(),
                ChartOfAccounts.BUSINESS_TYPE.getColumnName(),
                ChartOfAccounts.INDUSTRY_CLASSIFICATION.getColumnName(),
                ChartOfAccounts.FISCAL_YEAR.getColumnName(),
                ChartOfAccounts.FISCAL_START_DATE.getColumnName(),
                ChartOfAccounts.FISCAL_END_DATE.getColumnName(),
                ChartOfAccounts.ACCOUNTING_STANDARD.getColumnName(),
                ChartOfAccounts.STATUS.getColumnName(),
                ChartOfAccounts.MULTICURRENCY_SUPPORT.getColumnName(),
                ChartOfAccounts.TAX_SCHEMA.getColumnName(),
                ChartOfAccounts.VERSION_TAG.getColumnName(),
                ChartOfAccounts.NOTES.getColumnName(),
                ChartOfAccounts.DESCRIPTION.getColumnName(),
                ChartOfAccounts.UPDATED_BY.getColumnName(),
                ChartOfAccounts.SYS_TRANSACTION.getColumnName())
                .concat(Q.where(ChartOfAccounts.CODE.getColumnName()));
        
        try(Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query)){
            
            PooledConnectionService.beginTransaction(conn);
            ChartOfAccountDTO dtoChange = request.getDtoChange();

            st.setString(1, dtoChange.getChartOfAccountName());
            st.setString(2, dtoChange.getCurrencyCode());
            st.setString(3, dtoChange.getBusinessType());
            st.setString(4, dtoChange.getBusinessClass());
            st.setInt(5, dtoChange.getFiscalYear());
            st.setTimestamp(6, dtoChange.getFiscalStartDate());
            st.setTimestamp(7, dtoChange.getFiscalEndDate());
            st.setString(8, dtoChange.getAccountingStandard());
            st.setString(9, dtoChange.getChartOfAccountStatus());
            st.setBoolean(10, dtoChange.isMultiCurrencyAllowed());
            st.setInt(11, dtoChange.getTaxSchemaID());
            st.setString(12, dtoChange.getChartVersionTag());
            st.setString(13, dtoChange.getChartOfAccountNotes());
            st.setString(14, dtoChange.getChartOfAccountDescription());
            st.setString(15, dtoChange.getUpdatedBy());
            st.setString(16, dtoChange.getTransactionCode());
            st.setString(17, dtoChange.getChartOfAccountCode());

            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                PooledConnectionService.rollbackTransaction(conn);
                return new ChartOfAccountChangeResponse(request.getSessionId(), ResultType.NOT_UPDATED);
            }
            PooledConnectionService.commitTransaction(conn);
            return new ChartOfAccountChangeResponse(request.getSessionId(), ResultType.UPDATED);

        } catch (SQLException ex) {
            logger.error("Failed to update Chart of Account: SQL exception occurred", ex);
        }
        return new ChartOfAccountChangeResponse(request.getSessionId(), ResultType.SQL_ERROR);
    }

    public static ChartOfAccountExistsResponse isChartOfAccountExists(ChartOfAccountExistsRequest request) {

        String query = Q.select(DatabaseTables.FI_CHART_OF_ACCOUNTS.tableName(),
                ChartOfAccounts.CODE.getColumnName())
                .concat(Q.where(ChartOfAccounts.CODE.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, request.getChartOfAccountCode());

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new ChartOfAccountExistsResponse(request.getSessionId(), ResultType.FOUND);
                }
                return new ChartOfAccountExistsResponse(request.getSessionId(), ResultType.NOT_FOUND);

            }
        } catch (SQLException ex) {
            logger.error("Failed to retrieve the chart of account code", ex);
        }
        return new ChartOfAccountExistsResponse(request.getSessionId(), ResultType.SQL_ERROR);
    }

    public static ChartOfAccountsListRetrieveResponse getChartOfAccountList(ChartOfAccountListRetrieveRequest request) {

        String chartOfAccounts = DatabaseTables.FI_CHART_OF_ACCOUNTS.tableName();
        String accountModel = DatabaseTables.FI_ACCOUNT_MODELS.tableName();
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(Q.selectF(
                Q.fieldAlias("choa", ChartOfAccounts.CODE.getColumnName()),
                Q.fieldAlias("choa", ChartOfAccounts.NAME.getColumnName()),
                Q.fieldAlias("mdl", AccountModels.MODEL_NAME.getColumnName()),
                Q.fieldAlias("choa", ChartOfAccounts.CURRENCY_CODE.getColumnName()),
                Q.fieldAlias("choa", ChartOfAccounts.COUNTRY_CODE.getColumnName()),
                Q.fieldAlias("choa", ChartOfAccounts.FISCAL_YEAR.getColumnName()),
                Q.fieldAlias("choa", ChartOfAccounts.ACCOUNTING_STANDARD.getColumnName()),
                Q.fieldAlias("choa", ChartOfAccounts.STATUS.getColumnName()),
                Q.fieldAlias("choa", ChartOfAccounts.CREATED_BY.getColumnName())
        ))
        .append(Q.fromAlias("choa", chartOfAccounts))
        .append(Q.join(Q.fromAlias("mdl", accountModel)))
        .append(Q.onEqual(
                "choa", ChartOfAccounts.ACCOUNT_MODEL_ID.getColumnName(),
                "mdl", AccountModels.MODEL_ID.getColumnName()
        ));

        String query = queryBuilder.toString();
        
        List<Object[]> dataSource = new ArrayList<>();

        try (Connection conn = PooledConnectionService.getConnection();      
                PreparedStatement st = conn.prepareStatement(query); 
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                dataSource.add(new Object[]{
                    rs.getObject(ChartOfAccounts.CODE.getColumnName()),
                    rs.getObject(ChartOfAccounts.NAME.getColumnName()),
                    rs.getObject(AccountModels.MODEL_NAME.getColumnName()),
                    rs.getObject(ChartOfAccounts.CURRENCY_CODE.getColumnName()),
                    rs.getObject(ChartOfAccounts.COUNTRY_CODE.getColumnName()),
                    rs.getObject(ChartOfAccounts.FISCAL_YEAR.getColumnName()),
                    rs.getObject(ChartOfAccounts.ACCOUNTING_STANDARD.getColumnName()),
                    rs.getObject(ChartOfAccounts.STATUS.getColumnName()),
                    rs.getObject(ChartOfAccounts.CREATED_BY.getColumnName())
                });
            }
            if(!dataSource.isEmpty()){
                return new ChartOfAccountsListRetrieveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
            }
            return new ChartOfAccountsListRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);

        } catch (SQLException ex) {
            logger.error("Error retrieving chart of accounts list", ex);
        }
        return new ChartOfAccountsListRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, dataSource);
    }
    
    public static ChartOfAccountRetrieveResponse getChartOfAccountByCode(ChartOfAccountRetrieveRequest request) {
      
        String chartOfAccounts = DatabaseTables.FI_CHART_OF_ACCOUNTS.tableName();
        String accountModel = DatabaseTables.FI_ACCOUNT_MODELS.tableName();
        String taxSchemas = DatabaseTables.FI_TAX_SCHEMAS.tableName();
        String countries = DatabaseTables.COUNTRIES.tableName();
        String currencies = DatabaseTables.CURRENCIES.tableName();
        
        String chao = "chao";
        String mdl = "mdl";
        String tax = "tax";
        String coun = "coun";
        String curr = "curr";

        StringBuilder query = new StringBuilder();
        query.append(Q.selectF(
                Q.fieldAlias(chao, ChartOfAccounts.ACCOUNT_MODEL_ID.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.SYS_TRANSACTION.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.CODE.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.NAME.getColumnName()),
                Q.fieldAlias(mdl, AccountModels.MODEL_NAME.getColumnName()),
                Q.fieldAlias(mdl, AccountModels.DESCRIPTION.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.CURRENCY_CODE.getColumnName()),
                Q.fieldAlias(curr, Currencies.CURRENCY_NAME.getColumnName()),//currency
                Q.fieldAlias(chao, ChartOfAccounts.COUNTRY_CODE.getColumnName()),
                Q.fieldAlias(coun, CountriesInfo.COUNTRY_NAME.getColumnName()),//country
                Q.fieldAlias(chao, ChartOfAccounts.BUSINESS_TYPE.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.INDUSTRY_CLASSIFICATION.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.FISCAL_YEAR.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.FISCAL_START_DATE.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.FISCAL_END_DATE.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.ACCOUNTING_STANDARD.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.STATUS.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.MULTICURRENCY_SUPPORT.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.TAX_SCHEMA.getColumnName()),
                Q.fieldAlias(tax, TaxSchemas.NAME.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.VERSION_TAG.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.NOTES.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.DESCRIPTION.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.CREATED_BY.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.CREATED_AT.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.UPDATED_BY.getColumnName()),
                Q.fieldAlias(chao, ChartOfAccounts.UPDATED_AT.getColumnName())))
                
                .append(Q.fromAlias(chao, chartOfAccounts))
                .append(Q.join(Q.fromAlias(mdl, accountModel)))
                .append(Q.onEqual(
                        chao, ChartOfAccounts.ACCOUNT_MODEL_ID.getColumnName(),
                        mdl, AccountModels.MODEL_ID.getColumnName()
                ))
                
                .append(Q.join(Q.fromAlias(tax, taxSchemas)))
                .append(Q.onEqual(
                        chao, ChartOfAccounts.TAX_SCHEMA.getColumnName(),
                        tax, TaxSchemas.ID.getColumnName()
                ))
                
                .append(Q.join(Q.fromAlias(curr, currencies)))
                .append(Q.onEqual(
                        chao, ChartOfAccounts.CURRENCY_CODE.getColumnName(),
                        curr, Currencies.CURRENCY_CODE.getColumnName()
                ))
                
                .append(Q.join(Q.fromAlias(coun, countries)))
                .append(Q.onEqual(
                        chao, ChartOfAccounts.COUNTRY_CODE.getColumnName(),
                        coun, CountriesInfo.ALPHA_2.getColumnName()
                ))
                
                .append(Q.where(ChartOfAccounts.CODE.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection();        
                PreparedStatement st = conn.prepareStatement(query.toString())){
            st.setString(1, request.getChartOfAccountCode());
            try(ResultSet rs = st.executeQuery()){
                if(rs.next()) {
                    ChartOfAccountDTO dto = new ChartOfAccountDTO.Builder()
                            .accountModelID(rs.getInt(ChartOfAccounts.ACCOUNT_MODEL_ID.getColumnName()))
                            .accountModelName(rs.getString(AccountModels.MODEL_NAME.getColumnName()))
                            .accountModelDescription(rs.getString(AccountModels.DESCRIPTION.getColumnName()))
                            .transactionCode(rs.getString(ChartOfAccounts.SYS_TRANSACTION.getColumnName()))
                            .chartOfAccountCode(rs.getString(ChartOfAccounts.CODE.getColumnName()))
                            .chartOfAccountName(rs.getString(ChartOfAccounts.NAME.getColumnName()))
                            .currencyCode(rs.getString(ChartOfAccounts.CURRENCY_CODE.getColumnName()))
                            .currencyDescription(rs.getString(Currencies.CURRENCY_NAME.getColumnName()))
                            .countryCode(rs.getString(ChartOfAccounts.COUNTRY_CODE.getColumnName()))
                            .countryDescription(rs.getString(CountriesInfo.COUNTRY_NAME.getColumnName()))
                            .businessType(rs.getString(ChartOfAccounts.BUSINESS_TYPE.getColumnName()))
                            .businessClass(rs.getString(ChartOfAccounts.INDUSTRY_CLASSIFICATION.getColumnName()))
                            .fiscalYear(rs.getInt(ChartOfAccounts.FISCAL_YEAR.getColumnName()))
                            .fiscalStartDate(rs.getTimestamp(ChartOfAccounts.FISCAL_START_DATE.getColumnName()))
                            .fiscalEndDate(rs.getTimestamp(ChartOfAccounts.FISCAL_END_DATE.getColumnName()))
                            .accountingStandard(rs.getString(ChartOfAccounts.ACCOUNTING_STANDARD.getColumnName()))
                            .chartOfAccountStatus(rs.getString(ChartOfAccounts.STATUS.getColumnName()))
                            .multiCurrencyAllowed(rs.getBoolean(ChartOfAccounts.MULTICURRENCY_SUPPORT.getColumnName()))
                            .taxSchemaID(rs.getInt(ChartOfAccounts.TAX_SCHEMA.getColumnName()))
                            .taxSchemaDescription(rs.getString(TaxSchemas.NAME.getColumnName()))
                            .chartVersionTag(rs.getString(ChartOfAccounts.VERSION_TAG.getColumnName()))
                            .chartOfAccountNotes(rs.getString(ChartOfAccounts.NOTES.getColumnName()))
                            .chartOfAccountDescription(rs.getString(ChartOfAccounts.DESCRIPTION.getColumnName()))
                            .createdBy(rs.getString(ChartOfAccounts.CREATED_BY.getColumnName()))
                            .createdAt(rs.getTimestamp(ChartOfAccounts.CREATED_AT.getColumnName()))
                            .updatedBy(rs.getString(ChartOfAccounts.UPDATED_BY.getColumnName()))
                            .updateAt(rs.getTimestamp(ChartOfAccounts.UPDATED_AT.getColumnName()))
                            .build();
                    
                    return new ChartOfAccountRetrieveResponse(request.getSessionId(), ResultType.FOUND, dto);
                }
                return new ChartOfAccountRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
            }
        } catch (SQLException ex) {
            logger.error("",ex);
        }
        return new ChartOfAccountRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, null);
    }

}
