
package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.AccountingAccounts;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import com.simplecore.erp.shared.requests.types.AccountingAccountChangeRequest;
import com.simplecore.erp.shared.requests.types.AccountingAccountCreateRequest;
import com.simplecore.erp.shared.requests.types.AccountingAccountListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountingAccountRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountingAccountStatusListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountsBySubclassRetrieveRequest;
import com.simplecore.erp.shared.requests.types.LastAccountByParentRetrieveRequest;
import com.simplecore.erp.shared.requests.types.LastAccountBySubclassRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.AccountingAccountChangeResponse;
import com.simplecore.erp.shared.responses.types.AccountingAccountCreateResponse;
import com.simplecore.erp.shared.responses.types.AccountingAccountListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountingAccountRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountingAccountStatusListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountsBySubclassRetrieveResponse;
import com.simplecore.erp.shared.responses.types.LastAccountByParentRetrieveResponse;
import com.simplecore.erp.shared.responses.types.LastAccountBySubclassRetrieveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
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
public class AccountingAccountManager {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountingAccountManager.class);
    
    public static AccountsBySubclassRetrieveResponse getAccountsBySubclass(AccountsBySubclassRetrieveRequest request) {
        List<AccountingAccountDTO> accountsList = new ArrayList<>();
        // Construcción de la consulta SQL
        String query = Q.select(DatabaseTables.FI_ACCOUNTING_ACCOUNTS.tableName(),
                AccountingAccounts.ACCOUNT_ID.getColumnName(),
                AccountingAccounts.ACCOUNT_CODE.getColumnName(),
                AccountingAccounts.ACCOUNT_NAME.getColumnName(),
                AccountingAccounts.ACCOUNT_DESCRIPTION.getColumnName(),
                AccountingAccounts.CREATED_BY.getColumnName(),
                AccountingAccounts.CREATED_AT.getColumnName(),
                AccountingAccounts.UPDATED_BY.getColumnName(),
                AccountingAccounts.UPDATED_AT.getColumnName(),
                AccountingAccounts.ACCOUNT_STATUS.getColumnName(),
                AccountingAccounts.IS_CLOSED.getColumnName(),
                AccountingAccounts.PARENT_ACCOUNT_ID.getColumnName(),
                AccountingAccounts.SUBCLASS_ID.getColumnName())
                .concat(" WHERE " + AccountingAccounts.SUBCLASS_ID.getColumnName() + " = ? AND "
                        + AccountingAccounts.PARENT_ACCOUNT_ID.getColumnName() + " IS NULL");

        // Ejecutar la consulta y manejar la respuesta
        try (Connection conn = PooledConnectionService.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {

            // Establecer el valor del parámetro SUBCLASS_ID
            st.setInt(1, request.getSubclassId());
            try (ResultSet rs = st.executeQuery()) {

                while (rs.next()) {

                    int accountId = rs.getInt(AccountingAccounts.ACCOUNT_ID.getColumnName());
                    String accountCode = rs.getString(AccountingAccounts.ACCOUNT_CODE.getColumnName());
                    String accountName = rs.getString(AccountingAccounts.ACCOUNT_NAME.getColumnName());
                    String accountDescription = rs.getString(AccountingAccounts.ACCOUNT_DESCRIPTION.getColumnName());
                    String createdBy = rs.getString(AccountingAccounts.CREATED_BY.getColumnName());
                    Timestamp createdAt = rs.getTimestamp(AccountingAccounts.CREATED_AT.getColumnName());
                    String updatedBy = rs.getString(AccountingAccounts.UPDATED_BY.getColumnName());
                    Timestamp updatedAt = rs.getTimestamp(AccountingAccounts.UPDATED_AT.getColumnName());
                    String accountStatus = rs.getString(AccountingAccounts.ACCOUNT_STATUS.getColumnName());
                    boolean isClosed = rs.getBoolean(AccountingAccounts.IS_CLOSED.getColumnName());
                    Integer parentAccountId = rs.getInt(AccountingAccounts.PARENT_ACCOUNT_ID.getColumnName());
                    Integer subclassId = rs.getInt(AccountingAccounts.SUBCLASS_ID.getColumnName());

                    AccountingAccountDTO account = new AccountingAccountDTO.Builder()
                            .accountId(accountId)
                            .accountCode(accountCode)
                            .accountName(accountName)
                            .accountDescription(accountDescription)
                            .createdBy(createdBy)
                            .createdAt(createdAt)
                            .updatedBy(updatedBy)
                            .updatedAt(updatedAt)
                            .accountStatus(accountStatus)
                            .isClosed(isClosed)
                            .parentAccountId(parentAccountId)
                            .subclassId(subclassId)
                            .build();

                    accountsList.add(account);
                }

                if(!accountsList.isEmpty()){
                    return new AccountsBySubclassRetrieveResponse(request.getSessionId(),ResultType.FOUND, accountsList);
                }else{
                    return new AccountsBySubclassRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND, accountsList);
                }
            } catch (SQLException innerEx) {
                // Log the error without propagating the exception
                logger.info("Error executing query to retrieve accounts by SUBCLASS_ID: " + request.getSubclassId());
            }

        } catch (SQLException outerEx) {
            // Log the connection error without propagating the exception
            logger.info("Error obtaining connection to retrieve accounts", outerEx);

        }
        return new AccountsBySubclassRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR, accountsList);
    }
    /**
     * Creates a new accounting account and retrieves its details.
     *
     * @param request The request object containing the new account data.
     * @return AccountingAccountCreateResponse containing the status and details
     * of the created account.
     */
    public static AccountingAccountCreateResponse createAccountingAccount(AccountingAccountCreateRequest request) {
        // Construct the SQL INSERT statement
        String query = Q.insertInto(DatabaseTables.FI_ACCOUNTING_ACCOUNTS.tableName(),
                AccountingAccounts.ACCOUNT_CODE.getColumnName(),
                AccountingAccounts.ACCOUNT_NAME.getColumnName(),
                AccountingAccounts.ACCOUNT_DESCRIPTION.getColumnName(),
                AccountingAccounts.CREATED_BY.getColumnName(),
                AccountingAccounts.PARENT_ACCOUNT_ID.getColumnName(),
                AccountingAccounts.SUBCLASS_ID.getColumnName());

        try (Connection conn = PooledConnectionService.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                PooledConnectionService.beginTransaction(conn);
                AccountingAccountDTO newAccount = request.getNewAccount();

                // Set values for the prepared statement
                st.setString(1, newAccount.getAccountCode());
                st.setString(2, newAccount.getAccountName());
                st.setString(3, newAccount.getAccountDescription());
                st.setString(4, newAccount.getCreatedBy());
                setNullableInt(st, 5, newAccount.getParentAccountId());
                setNullableInt(st, 6, newAccount.getSubclassId());
                st.executeUpdate();

                // Retrieve the generated account ID
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        int accountId = rs.getInt(1);

                        // Commit the transaction before retrieving account details
                        PooledConnectionService.commitTransaction(conn);

                        // Retrieve the newly created account
                        AccountingAccountRetrieveResponse accountResponse
                                = getAccountingAccount(new AccountingAccountRetrieveRequest(
                                        request.getSessionId(), request.getUserId(), newAccount.getAccountCode()));

                        // If account retrieval was successful, return response with details
                        if (accountResponse.getAccountingAccount() != null) {
                            Timestamp createdAt = accountResponse.getAccountingAccount().getCreatedAt();
                            String status = accountResponse.getAccountingAccount().getAccountStatus();
                            boolean isClosed = accountResponse.getAccountingAccount().isClosed();

                            return new AccountingAccountCreateResponse(
                                    request.getSessionId(), ResultType.CREATED, accountId, createdAt, status, isClosed);
                        }
                    }
                }
            } catch (SQLException ex) {
                // Rollback transaction in case of failure
                PooledConnectionService.rollbackTransaction(conn);
                logger.info("Error creating accounting account", query, ex);
            }
        } catch (SQLException ex) {
            logger.info("Database connection error while creating accounting account", query, ex);
        }

        // Return failure response if any error occurs
        return new AccountingAccountCreateResponse(request.getSessionId(), ResultType.SQL_ERROR, -1, null, null, false);
    }

    private static void setNullableInt(PreparedStatement st, int parameterIndex, Integer value) throws SQLException {
        if (value != null) {
            st.setInt(parameterIndex, value);
        } else {
            st.setNull(parameterIndex, Types.INTEGER);
        }
    }

    public static AccountingAccountChangeResponse changeAccountingAccount(AccountingAccountChangeRequest request) {
        String query = Q.update(DatabaseTables.FI_ACCOUNTING_ACCOUNTS.tableName(),
                AccountingAccounts.ACCOUNT_NAME.getColumnName(),
                AccountingAccounts.ACCOUNT_DESCRIPTION.getColumnName(),
                AccountingAccounts.ACCOUNT_STATUS.getColumnName(),
                AccountingAccounts.IS_CLOSED.getColumnName(),
                AccountingAccounts.UPDATED_BY.getColumnName())
                .concat(Q.where(AccountingAccounts.ACCOUNT_ID.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection()) {

            try (PreparedStatement st = conn.prepareStatement(query)) {
                PooledConnectionService.beginTransaction(conn);

                AccountingAccountDTO accountingAccount = request.getAccountingAccount();
                int accountId = accountingAccount.getAccountId();

                st.setString(1, accountingAccount.getAccountName());
                st.setString(2, accountingAccount.getAccountDescription());
                st.setString(3, accountingAccount.getAccountStatus());
                st.setBoolean(4, accountingAccount.isClosed());
                st.setString(5, accountingAccount.getUpdatedBy());
                st.setInt(6, accountId);
                st.executeUpdate();

                PooledConnectionService.commitTransaction(conn);
                
                return new AccountingAccountChangeResponse(request.getSessionId(), ResultType.UPDATED);
                
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(conn);
                logger.error("Error while updating accounting account. Transaction rolled back.", ex);
            }

        } catch (SQLException ex) {
            logger.error("Database connection error.", ex);
        }
        return new AccountingAccountChangeResponse(request.getSessionId(), ResultType.SQL_ERROR);
    }
    
    public static AccountingAccountRetrieveResponse getAccountingAccount(AccountingAccountRetrieveRequest request) {
        String query = Q.select(DatabaseTables.FI_ACCOUNTING_ACCOUNTS.tableName(),
                AccountingAccounts.ACCOUNT_ID.getColumnName(),
                AccountingAccounts.ACCOUNT_CODE.getColumnName(),
                AccountingAccounts.ACCOUNT_NAME.getColumnName(),
                AccountingAccounts.ACCOUNT_DESCRIPTION.getColumnName(),
                AccountingAccounts.CREATED_BY.getColumnName(),
                AccountingAccounts.CREATED_AT.getColumnName(),
                AccountingAccounts.UPDATED_BY.getColumnName(),
                AccountingAccounts.UPDATED_AT.getColumnName(),
                AccountingAccounts.ACCOUNT_STATUS.getColumnName(),
                AccountingAccounts.IS_CLOSED.getColumnName(),
                AccountingAccounts.PARENT_ACCOUNT_ID.getColumnName(),
                AccountingAccounts.SUBCLASS_ID.getColumnName())
                .concat(Q.where(AccountingAccounts.ACCOUNT_CODE.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, request.getAccountingAccountCode());
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    AccountingAccountDTO accountingAccount = new AccountingAccountDTO.Builder()
                            .accountId(rs.getInt(AccountingAccounts.ACCOUNT_ID.getColumnName()))
                            .accountCode(rs.getString(AccountingAccounts.ACCOUNT_CODE.getColumnName()))
                            .accountName(rs.getString(AccountingAccounts.ACCOUNT_NAME.getColumnName()))
                            .accountDescription(rs.getString(AccountingAccounts.ACCOUNT_DESCRIPTION.getColumnName()))
                            .createdBy(rs.getString(AccountingAccounts.CREATED_BY.getColumnName()))
                            .createdAt(rs.getTimestamp(AccountingAccounts.CREATED_AT.getColumnName()))
                            .updatedBy(rs.getString(AccountingAccounts.UPDATED_BY.getColumnName()))
                            .updatedAt(rs.getTimestamp(AccountingAccounts.UPDATED_AT.getColumnName()))
                            .accountStatus(rs.getString(AccountingAccounts.ACCOUNT_STATUS.getColumnName()))
                            .isClosed(rs.getBoolean(AccountingAccounts.IS_CLOSED.getColumnName()))
                            .parentAccountId(rs.getInt(AccountingAccounts.PARENT_ACCOUNT_ID.getColumnName()))
                            .subclassId(rs.getInt(AccountingAccounts.SUBCLASS_ID.getColumnName()))
                            .build();

                    return new AccountingAccountRetrieveResponse(request.getSessionId(), ResultType.FOUND, accountingAccount);
                }
                return new AccountingAccountRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
            }

        } catch (SQLException ex) {
            logger.info(
                    "An error occurred while retrieving the accounting account. Query: " + query
                    + " | Account ID: " + request.getAccountingAccountCode()
                    + " | Error: " + ex.getMessage(), ex);
        }

        return new AccountingAccountRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR ,null);
    }

    public static LastAccountByParentRetrieveResponse getLastAccountByParentId(LastAccountByParentRetrieveRequest request) {
        String query = Q.select(DatabaseTables.FI_ACCOUNTING_ACCOUNTS.tableName(),
                AccountingAccounts.ACCOUNT_CODE.getColumnName())
                .concat(Q.where(AccountingAccounts.PARENT_ACCOUNT_ID.getColumnName()))
                .concat(Q.orderBy(AccountingAccounts.ACCOUNT_CODE.getColumnName()))
                .concat(Q.DESC.toSQL() + " " + Q.limit(1));

        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query)) {

            // Establecer el parámetro de parentAccountId en la consulta
            st.setInt(1, request.getParentAccountId());

            try (ResultSet rs = st.executeQuery()) {
                // Si se encuentra un resultado, obtener el accountNumber
                if (rs.next()) {
                    String accountNumber = rs.getString(AccountingAccounts.ACCOUNT_CODE.getColumnName());  // Aquí accedemos al primer valor de la columna MAX()
                    return new LastAccountByParentRetrieveResponse(request.getSessionId(), ResultType.FOUND, accountNumber);
                }
                // Si no se encuentra un resultado, devolver 0
                return new LastAccountByParentRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
            }
        } catch (SQLException ex) {
            // Logueamos el error con detalles adicionales para facilitar la depuración
            logger.info("Error retrieving last account number for parent ID: " + request.getParentAccountId(), ex);
        }

        // Si ocurre algún error, devolver la respuesta con el estado de error
        return new LastAccountByParentRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR, null);
    }

    public static LastAccountBySubclassRetrieveResponse getLastAccountBySubclassId(LastAccountBySubclassRetrieveRequest request) {
        String query = Q.select(DatabaseTables.FI_ACCOUNTING_ACCOUNTS.tableName(),
                AccountingAccounts.ACCOUNT_CODE.getColumnName())
                .concat(Q.where(AccountingAccounts.SUBCLASS_ID.getColumnName()))
                .concat(Q.orderBy(AccountingAccounts.ACCOUNT_CODE.getColumnName()))
                .concat(Q.DESC.toSQL() + " " + Q.limit(1));

        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query)) {

            st.setInt(1, request.getSubclassId());
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String accountNumber = rs.getString(AccountingAccounts.ACCOUNT_CODE.getColumnName());  // Aquí accedemos al primer valor de la columna MAX()
                    return new LastAccountBySubclassRetrieveResponse(request.getSessionId(), ResultType.FOUND, accountNumber);
                }
                return new LastAccountBySubclassRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
            }

        } catch (SQLException ex) {
            logger.info("Error retrieving last account by subclass ID", ex);
        }
        return new LastAccountBySubclassRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, null);
    }

    public static AccountingAccountListRetrieveResponse getAccountingAccountListDataSource(AccountingAccountListRetrieveRequest request){
        String query = Q.select(DatabaseTables.FI_ACCOUNTING_ACCOUNTS.tableName(), 
                AccountingAccounts.ACCOUNT_CODE.getColumnName(),
                AccountingAccounts.ACCOUNT_NAME.getColumnName(),
                AccountingAccounts.ACCOUNT_DESCRIPTION.getColumnName(),
                AccountingAccounts.CREATED_BY.getColumnName(),
                AccountingAccounts.ACCOUNT_STATUS.getColumnName(),
                AccountingAccounts.IS_CLOSED.getColumnName());
        
        List<Object[]> dataSource = new ArrayList<>();
        
        try(Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query);
                ResultSet rs = st.executeQuery()){
            while(rs.next()){
                dataSource.add(new Object[]{
                    rs.getString(AccountingAccounts.ACCOUNT_CODE.getColumnName()),
                    rs.getString(AccountingAccounts.ACCOUNT_NAME.getColumnName()),
                    rs.getString(AccountingAccounts.ACCOUNT_DESCRIPTION.getColumnName()),
                    rs.getString(AccountingAccounts.CREATED_BY.getColumnName()),
                    rs.getString(AccountingAccounts.ACCOUNT_STATUS.getColumnName()),
                    rs.getBoolean(AccountingAccounts.IS_CLOSED.getColumnName())});
            }
            if(!dataSource.isEmpty()){
                return new AccountingAccountListRetrieveResponse(request.getSessionId(),ResultType.FOUND,dataSource);
            }else{
                return new AccountingAccountListRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND,dataSource);
            }
        } catch (SQLException ex) {
            logger.info("Error while retrieving accounting account list from database. Query: " + query, ex);
        }
        return new AccountingAccountListRetrieveResponse( request.getSessionId(),ResultType.NOT_FOUND,dataSource);
    }
    
    public static AccountingAccountStatusListRetrieveResponse getAccountingAccountStatusList(AccountingAccountStatusListRetrieveRequest request) {
        List<String> dataSource = new ArrayList<>();

        try (Connection conn = PooledConnectionService.getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM "
                + DatabaseTables.FI_ACCOUNTING_ACCOUNTS.tableName()
                + " LIKE '" + AccountingAccounts.ACCOUNT_STATUS.getColumnName() + "'")) {

            if (rs.next()) {
                String enumDef = rs.getString("Type");
                enumDef = enumDef.substring(enumDef.indexOf("(") + 1, enumDef.lastIndexOf(")"));

                // Procesar valores del ENUM y agregarlos a la lista como Object[]
                Arrays.stream(enumDef.split(","))
                        .map(s -> s.replace("'", "").trim()) // Cada valor en un Object[]
                        .forEach(dataSource::add);
                return new AccountingAccountStatusListRetrieveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
            } else {
                return new AccountingAccountStatusListRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);
            }
        } catch (SQLException ex) {
            logger.error("Error retrieving ENUM values", ex);
        }
        return new AccountingAccountStatusListRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, dataSource);
    }
}
