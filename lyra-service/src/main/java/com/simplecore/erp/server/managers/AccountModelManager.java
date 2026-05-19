package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.AccountClasses;
import com.simplecore.erp.server.config.database.tablecolumns.AccountModels;
import com.simplecore.erp.server.config.database.tablecolumns.AccountRanges;
import com.simplecore.erp.server.config.database.tablecolumns.AccountSubclasses;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.requests.types.AccountClassesByModelRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountModelListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountModelRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountModelChangeRequest;
import com.simplecore.erp.shared.requests.types.AccountModelCreateRequest;
import com.simplecore.erp.shared.requests.types.AccountModelStateChangeRequest;
import com.simplecore.erp.shared.requests.types.AccountModelStatesListRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.AccountClassesByModelRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountModelListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountModelRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountModelChangeResponse;
import com.simplecore.erp.shared.responses.types.AccountModelCreateResponse;
import com.simplecore.erp.shared.responses.types.AccountModelStateChangeResponse;
import com.simplecore.erp.shared.responses.types.AccountModelStatesListRetrieveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class AccountModelManager {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountModelManager.class);

    
    public static AccountModelChangeResponse changeAccountModel(AccountModelChangeRequest request){
        String query = Q.update(DatabaseTables.FI_ACCOUNT_MODELS.tableName(),
                AccountModels.DESCRIPTION.getColumnName(),
                AccountModels.STATE.getColumnName(),
                AccountModels.MODIFIED_BY.getColumnName(),
                AccountModels.MODIFIED_AT.getColumnName())
                .concat(Q.where(AccountModels.MODEL_ID.getColumnName()));
        
        Connection conn = null;
        try{
            conn = PooledConnectionService.getConnection();
            
            PooledConnectionService.beginTransaction(conn);
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, request.getModelDescription());
                st.setString(2, request.getSystemState());
                st.setString(3, request.getModifiedBy());
                st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                st.setInt(5, request.getModelId());
                st.executeUpdate();
            }
            PooledConnectionService.commitTransaction(conn);
            return new AccountModelChangeResponse(request.getSessionId(), ResultType.UPDATED);
          
        } catch (SQLException ex) {
            if (conn != null) { // Evitamos rollback si la conexión es nula
                PooledConnectionService.rollbackTransaction(conn);
            }
            logger.info("Error changing Account Model ", ex);
        }
        return new AccountModelChangeResponse(request.getSessionId(), ResultType.SQL_ERROR);
    }

    public static AccountModelRetrieveResponse getAccountModel(AccountModelRetrieveRequest request) {
        String query = Q.selectAll(DatabaseTables.FI_ACCOUNT_MODELS.tableName())
                .concat(Q.where(AccountModels.MODEL_NAME.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, request.getModelName());
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return new AccountModelRetrieveResponse(request.getSessionId(),ResultType.FOUND,
                                rs.getInt(AccountModels.MODEL_ID.getColumnName()),
                                rs.getString(AccountModels.MODEL_NAME.getColumnName()),
                                rs.getString(AccountModels.DESCRIPTION.getColumnName()),
                                rs.getString(AccountModels.CREATED_AT.getColumnName()),
                                rs.getString(AccountModels.CREATED_BY.getColumnName()),
                                rs.getString(AccountModels.MODIFIED_AT.getColumnName()),
                                rs.getString(AccountModels.MODIFIED_BY.getColumnName()),
                                rs.getString(AccountModels.STATE.getColumnName()),
                                true);
                    }
                }
            }

        } catch (SQLException ex) {
            logger.info("Error retrieving Account Model ", ex);
        }
        return new AccountModelRetrieveResponse("Model has not been found : ");
    }

    public static AccountModelListRetrieveResponse getAccountModelList(AccountModelListRetrieveRequest request){
        List<Object[]> accountModelList = new ArrayList<>();
        String query = Q.select(
                DatabaseTables.FI_ACCOUNT_MODELS.tableName(), 
                AccountModels.MODEL_ID.getColumnName(),
                AccountModels.MODEL_NAME.getColumnName(),
                AccountModels.STATE.getColumnName(),
                AccountModels.DESCRIPTION.getColumnName(),
                AccountModels.CREATED_AT.getColumnName(),
                AccountModels.CREATED_BY.getColumnName());
        
        try(Connection conn = PooledConnectionService.getConnection()){
            try (PreparedStatement st = conn.prepareStatement(query)) {
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        accountModelList.add(new Object[]{
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6)});
                    }
                }
            }
        } catch (SQLException ex) {
            logger.info("Error retrieving Account Model List", ex);
        }
        return new AccountModelListRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND,accountModelList,"Models founds");
    }

    public static AccountModelCreateResponse createAccountModel(AccountModelCreateRequest request) {
        int modelId = -1;

        String queryModel = Q.insertInto(DatabaseTables.FI_ACCOUNT_MODELS.tableName(),
                AccountModels.MODEL_NAME.getColumnName(),
                AccountModels.DESCRIPTION.getColumnName());

        String queryRanges = Q.insertInto(DatabaseTables.FI_ACCOUNT_RANGES.tableName(),
                AccountRanges.MODEL_ID.getColumnName(),
                AccountRanges.CLASS_ID.getColumnName(),
                AccountRanges.RANGE_START.getColumnName(),
                AccountRanges.RANGE_END.getColumnName());

        Connection conn = null;
        try {
            conn = PooledConnectionService.getConnection();
            if (modelNameExists(conn, request)) {
                return new AccountModelCreateResponse(request.getSessionId(),ResultType.NOT_CREATED ,modelId, request.getModelName(),
                        "Account model name already exists");
            }

            PooledConnectionService.beginTransaction(conn);

            // Insert into Account Models
            modelId = insertAccountModel(conn, queryModel, request);
            // Verificamos que modelId sea válido
            if (modelId <= 0) {
                PooledConnectionService.rollbackTransaction(conn);  // Rollback si modelId no es válido
                return new AccountModelCreateResponse(request.getSessionId(),ResultType.NOT_CREATED,modelId, request.getModelName(),
                        "Failed to create account model");
            }

            // Insert ranges for each category
            insertAccountRange(conn, queryRanges, modelId, request.getAssetsId(), request.getAssetsFrom(), request.getAssetsTo());
            insertAccountRange(conn, queryRanges, modelId, request.getLiabilitiesId(), request.getLiabilitiesFrom(), request.getLiabilitiesTo());
            insertAccountRange(conn, queryRanges, modelId, request.getEquityId(), request.getEquityFrom(), request.getEquityTo());
            insertAccountRange(conn, queryRanges, modelId, request.getRevenueId(), request.getRevenueFrom(), request.getRevenueTo());
            insertAccountRange(conn, queryRanges, modelId, request.getCostsId(), request.getCostsFrom(), request.getCostsTo());
            insertAccountRange(conn, queryRanges, modelId, request.getExpensesId(), request.getExpensesFrom(), request.getExpensesTo());

            PooledConnectionService.commitTransaction(conn);

            return new AccountModelCreateResponse(request.getSessionId(),ResultType.CREATED, modelId, request.getModelName(),
                    "Account Model Created : "); // Returning the response with the modelId or other necessary data

        } catch (SQLException ex) {
            // Only attempt rollback if connection is open
            if (conn != null) {
                PooledConnectionService.rollbackTransaction(conn);  // Rollback in case of failure
            }
            logger.info("Error while creating account model", ex);
            return new AccountModelCreateResponse(request.getSessionId(),ResultType.SQL_ERROR, modelId, request.getModelName(),
                    "Failed to create account model");
        } finally {
            // Ensure the connection is closed properly
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    logger.info("Error closing connection", closeEx);
                }
            }
        }
    }
    // Helper method for inserting into Account Models
    private static int insertAccountModel(Connection conn, String query, AccountModelCreateRequest request) throws SQLException {
        try (PreparedStatement stModel = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stModel.setString(1, request.getModelName());
            stModel.setString(2, request.getModelDescription());
            stModel.executeUpdate();

            try (ResultSet rs = stModel.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
    // Helper method for inserting account range data
    private static void insertAccountRange(Connection conn, String query, int modelId, int classId, int rangeFrom, int rangeTo) throws SQLException {
        try (PreparedStatement stRange = conn.prepareStatement(query)) {
            stRange.setInt(1, modelId);
            stRange.setInt(2, classId);
            stRange.setInt(3, rangeFrom);
            stRange.setInt(4, rangeTo);
            stRange.executeUpdate();
        }
    }

    public static boolean modelNameExists(Connection conn, AccountModelCreateRequest request) {
        String name = request.getModelName();
        String query = Q.select(DatabaseTables.FI_ACCOUNT_MODELS.tableName(),
                AccountModels.MODEL_NAME.getColumnName()).concat(Q.where(AccountModels.MODEL_NAME.getColumnName()));

        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, name);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            logger.info("Error getting model Name Exists ", ex);
        }
        return false;
    }
    
    public static AccountModelStateChangeResponse changeAccountModelState(AccountModelStateChangeRequest request){
        String query = Q.update(DatabaseTables.FI_ACCOUNT_MODELS.tableName(),
                AccountModels.STATE.getColumnName())
                .concat(Q.where(AccountModels.MODEL_ID.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(query)) {

                PooledConnectionService.beginTransaction(conn);

                st.setString(1, request.getState());
                st.setInt(2, request.getModelId());
                st.executeUpdate();

                PooledConnectionService.commitTransaction(conn);

                return new AccountModelStateChangeResponse(request.getSessionId(),ResultType.UPDATED, request.getState());
                
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(conn);
                logger.info("Error changing Account Model State ", ex);
            }
        } catch (SQLException ex) {
            logger.info("Error connecting to SQL for changing Account Model State ", ex);
        }

        return new AccountModelStateChangeResponse(request.getSessionId(),ResultType.SQL_ERROR, request.getState());
    }

    public static AccountClassesByModelRetrieveResponse getClassesByModelId(AccountClassesByModelRetrieveRequest request) {
        Map<Integer, String> classesList = new HashMap<>();
        String query = Q.selectWithJoin(DatabaseTables.FI_ACCOUNT_SUBCLASSES.tableName(),
                DatabaseTables.FI_ACCOUNT_CLASSES.tableName(),
                AccountSubclasses.CLASS_ID.getColumnName(),
                AccountClasses.CLASS_NAME.getColumnName(),
                AccountClasses.ID.getColumnName()).concat(Q.where(AccountSubclasses.MODEL_ID.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, request.getModelId());

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    classesList.put(rs.getInt(1), rs.getString(2));
                }
            }
            return new AccountClassesByModelRetrieveResponse(request.getSessionId(),ResultType.FOUND, classesList);

        } catch (SQLException ex) {
            logger.info("Error getting ClassesByModelId ", ex);
        }
        return new AccountClassesByModelRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND, classesList);
    }

    public static AccountModelStatesListRetrieveResponse getModelStatesList(AccountModelStatesListRetrieveRequest request) {
        
        List<Object[]> dataSource = new ArrayList<>();
        try (Connection conn = PooledConnectionService.getConnection(); 
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM "
                + DatabaseTables.FI_ACCOUNT_MODELS.tableName()
                + " LIKE '" + AccountModels.STATE.getColumnName() + "'")) {

            if (rs.next()) {
                String enumDef = rs.getString("Type");
                enumDef = enumDef.substring(enumDef.indexOf("(") + 1, enumDef.lastIndexOf(")"));

            // Procesar valores del ENUM y agregarlos a la lista como Object[]
            Arrays.stream(enumDef.split(","))
                    .map(s -> new Object[]{s.replace("'", "").trim()}) // Cada valor en un Object[]
                    .forEach(dataSource::add);

                return new AccountModelStatesListRetrieveResponse(request.getSessionId(),ResultType.FOUND, dataSource);
            }

            return new AccountModelStatesListRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND, dataSource);
        } catch (SQLException ex) {
            logger.error("Error retrieving ENUM values", ex);
        }

        return new AccountModelStatesListRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR, dataSource);
    }

}
