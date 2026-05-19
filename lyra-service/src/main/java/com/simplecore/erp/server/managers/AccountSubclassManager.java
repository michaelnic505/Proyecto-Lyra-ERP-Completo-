package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.AccountSubclasses;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.AccountSubclassDTO;
import com.simplecore.erp.shared.requests.types.AccountSubclassChangeRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassByModelRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassCreateRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassDeleteRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassByClassRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassByIdRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassListRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.AccountSubclassChangeResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassCreateResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassDeleteResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassesRetrieveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountSubclassManager {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountSubclassManager.class);
    
    public static AccountSubclassCreateResponse insertSubclasses(AccountSubclassCreateRequest request) {
        List<AccountSubclassDTO> subclassesListRequest = request.getAccountSubclasses();
        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);
            try {
                // Ejecutar inserción en batch y obtener la lista con los IDs generados
                List<AccountSubclassDTO> subclassesListResponse = executeBatchInsert(connection, subclassesListRequest);
                PooledConnectionService.commitTransaction(connection);
                return new AccountSubclassCreateResponse(request.getSessionId(),ResultType.CREATED,subclassesListResponse,  "Batch executed");
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);
                return new AccountSubclassCreateResponse(request.getSessionId(),ResultType.NOT_CREATED , Collections.emptyList(), "The batch could not be executed.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountSubclassManager.class.getName()).log(Level.SEVERE, "Error inserting subclasses", ex);
            return new AccountSubclassCreateResponse(request.getSessionId(),ResultType.SQL_ERROR, Collections.emptyList(),"The batch could not be executed.");
        }
    }

    /**
     * Ejecuta la inserción en batch de AccountModelSubclass y retorna la lista
     * con los IDs generados.
     */
    private static List<AccountSubclassDTO> executeBatchInsert(Connection connection, 
            List<AccountSubclassDTO> subclassesListRequest) throws SQLException {
        List<AccountSubclassDTO> subclassesListResponse = new ArrayList<>();

        String query = Q.insertInto(DatabaseTables.FI_ACCOUNT_SUBCLASSES.tableName(),
                AccountSubclasses.MODEL_ID.getColumnName(),
                AccountSubclasses.CLASS_ID.getColumnName(),
                AccountSubclasses.SUBCLASS_CODE.getColumnName(),
                AccountSubclasses.SUBCLASS_NAME.getColumnName());

        try (PreparedStatement st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (AccountSubclassDTO subclass : subclassesListRequest) {
                st.setInt(1, subclass.getModelId());
                st.setInt(2, subclass.getClassId());
                st.setInt(3, subclass.getSubclassCode());
                st.setString(4, subclass.getSubclassName());
                st.addBatch();
            }

            int[] batchResults = st.executeBatch();
            validateBatchResults(batchResults, subclassesListRequest.size());

            // Obtener y asignar los IDs generados
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {

                Iterator<AccountSubclassDTO> iterator = subclassesListRequest.iterator();
                while (generatedKeys.next() && iterator.hasNext()) {
                    AccountSubclassDTO subclass = iterator.next();
                    subclass.setSubclassId(generatedKeys.getInt(1));
                    subclassesListResponse.add(subclass);
                }
                if (iterator.hasNext()) {
                    throw new SQLException("Mismatch between generated keys and inserted subclasses.");
                }
            }
        }
        return subclassesListResponse;
    }

    /**
     * Valida que el batch se haya ejecutado correctamente.
     */
    private static void validateBatchResults(int[] batchResults, int expectedSize) throws SQLException {
        if (batchResults.length != expectedSize) {
            throw new SQLException("Batch insert mismatch: expected " + expectedSize + " but got " + batchResults.length);
        }
        for (int result : batchResults) {
            if (result == Statement.EXECUTE_FAILED) {
                throw new SQLException("One or more batch insert operations failed.");
            }
        }
    }

    /**
     * Recupera las subclases de cuenta asociadas a un modelo específico
     * utilizando su ID.| Retrieves account subclasses associated with a
     * specific model using its ID.
     *
     * @param request Objeto {@link AccountSubclassByModelRetrieveRequest} que
     * contiene el ID del modelo y la sesión del usuario. |
     * {@link AccountSubclassByModelRetrieveRequest} object containing the
     * model ID and user session.
     * @return {@link AccountSubclassesRetrieveResponse} con la lista de
     * subclases encontradas o una respuesta vacía en caso de error. |
     * {@link AccountSubclassesRetrieveResponse} with the list of found
     * subclasses or an empty response in case of an error.
     */
    public static AccountSubclassesRetrieveResponse getAccountSubclassesByModelId(AccountSubclassByModelRetrieveRequest request) {
        List<AccountSubclassDTO> subclassesList = new ArrayList<>();
        String query = Q.select(DatabaseTables.FI_ACCOUNT_SUBCLASSES.tableName(),
                AccountSubclasses.SUBCLASS_ID.getColumnName(),
                AccountSubclasses.MODEL_ID.getColumnName(),
                AccountSubclasses.CLASS_ID.getColumnName(),
                AccountSubclasses.SUBCLASS_CODE.getColumnName(),
                AccountSubclasses.SUBCLASS_NAME.getColumnName())
                .concat(Q.where(AccountSubclasses.MODEL_ID.getColumnName()));

        try (Connection connection = PooledConnectionService.getConnection(); 
                PreparedStatement st = connection.prepareStatement(query)) {

            // Se establece el ID del modelo en la consulta SQL |  
            // The model ID is set in the SQL query
            st.setInt(1, request.getModelId());

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    subclassesList.add(new AccountSubclassDTO(
                            rs.getInt(AccountSubclasses.MODEL_ID.getColumnName()),
                            rs.getInt(AccountSubclasses.CLASS_ID.getColumnName()),
                            rs.getInt(AccountSubclasses.SUBCLASS_ID.getColumnName()),
                            rs.getInt(AccountSubclasses.SUBCLASS_CODE.getColumnName()),
                            rs.getString(AccountSubclasses.SUBCLASS_NAME.getColumnName()) // Se obtiene el nombre de la subclase |  
                    // Retrieves the subclass name
                    ));
                }
            }
            // Se retorna la respuesta con éxito si se encontraron datos |  
            // Returns a successful response if data was found
            return new AccountSubclassesRetrieveResponse(request.getSessionId(),ResultType.FOUND ,subclassesList);

        } catch (SQLException ex) {
            // Se registra el error en los logs |  
            // Logs the error
            Logger.getLogger(AccountSubclassManager.class.getName()).log(Level.SEVERE,
                    "Database error while retrieving account subclasses", ex);
        }
        // Se devuelve una respuesta indicando el fallo con una lista vacía |  
        // Returns a failure response with an empty list
        return new AccountSubclassesRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, Collections.emptyList());
    }

    public static AccountSubclassDeleteResponse deleteAccountSubclassById(AccountSubclassDeleteRequest request) {
        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);
            try {
                executeBatchDelete(connection, request.getSubclassId(), request.getModelId());
                PooledConnectionService.commitTransaction(connection);
                return new AccountSubclassDeleteResponse(request.getSessionId(), ResultType.DELETED);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);
                return new AccountSubclassDeleteResponse(request.getSessionId(), ResultType.NOT_DELETED);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountSubclassManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new AccountSubclassDeleteResponse(request.getSessionId(), ResultType.SQL_ERROR);
    }

    private static void executeBatchDelete(Connection connection, int[] subclassesId, int modelId) throws SQLException {
        String query = Q.deleteFrom(DatabaseTables.FI_ACCOUNT_SUBCLASSES.tableName(),
                AccountSubclasses.SUBCLASS_ID.getColumnName(),
                AccountSubclasses.MODEL_ID.getColumnName());

        try (PreparedStatement st = connection.prepareStatement(query)) {
            for (int subclassId : subclassesId) {
                st.setInt(1, subclassId);
                st.setInt(2, modelId);
                st.addBatch();
            }
            int[] batchResults = st.executeBatch();
            validateBatchResults(batchResults, subclassesId.length);
        } catch (SQLException ex) {
            Logger.getLogger(AccountSubclassManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static AccountSubclassChangeResponse changeAccountSubclassById(AccountSubclassChangeRequest request) {
        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);
            try {
                executeBatchChange(connection, request.getAccountSubclasses());
                PooledConnectionService.commitTransaction(connection);
                return new AccountSubclassChangeResponse(request.getSessionId(), ResultType.UPDATED);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(AccountSubclassManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountSubclassManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new AccountSubclassChangeResponse(request.getSessionId(), ResultType.SQL_ERROR);
    }

    private static void executeBatchChange(Connection connection, List<AccountSubclassDTO> subclassesListRequest) throws SQLException {
        String query = Q.update(DatabaseTables.FI_ACCOUNT_SUBCLASSES.tableName(),
                AccountSubclasses.SUBCLASS_NAME.getColumnName())
                .concat(Q.where(AccountSubclasses.SUBCLASS_ID.getColumnName(),
                        AccountSubclasses.MODEL_ID.getColumnName()));

        try (PreparedStatement st = connection.prepareStatement(query)) {
            for (AccountSubclassDTO subclass : subclassesListRequest) {
                st.setString(1, subclass.getSubclassName());
                st.setInt(2, subclass.getSubclassId());
                st.setInt(3, subclass.getModelId());
                st.addBatch();
            }
            int[] batchResults = st.executeBatch();
            validateBatchResults(batchResults, subclassesListRequest.size());
        } catch (SQLException ex) {
            Logger.getLogger(AccountSubclassManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves a list of account subclasses based on the given class ID and
     * model ID. This method executes a SQL query to fetch the subclass details
     * and returns them in a response object.
     *
     * @param request The request object containing the model ID, class ID, and
     * session ID.
     * @return An {@link AccountSubclassesRetrieveResponse} object containing
     * the retrieved subclass list and the success status.
     */
    public static AccountSubclassesRetrieveResponse getSubclassesByClassId(AccountSubclassByClassRetrieveRequest request) {
        List<AccountSubclassDTO> subclassesByClassList = new ArrayList<>();
        String query = Q.select(DatabaseTables.FI_ACCOUNT_SUBCLASSES.tableName(),
                AccountSubclasses.SUBCLASS_ID.getColumnName(),
                AccountSubclasses.MODEL_ID.getColumnName(),
                AccountSubclasses.CLASS_ID.getColumnName(),
                AccountSubclasses.SUBCLASS_CODE.getColumnName(),
                AccountSubclasses.SUBCLASS_NAME.getColumnName())
                .concat(Q.where(AccountSubclasses.MODEL_ID.getColumnName(),
                        AccountSubclasses.CLASS_ID.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {

            // Set query parameters from request
            st.setInt(1, request.getModelId());
            st.setInt(2, request.getClassId());

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int modelId = rs.getInt(AccountSubclasses.MODEL_ID.getColumnName());
                    int classId = rs.getInt(AccountSubclasses.CLASS_ID.getColumnName());
                    int subclassId = rs.getInt(AccountSubclasses.SUBCLASS_ID.getColumnName());
                    int subclassCode = rs.getInt(AccountSubclasses.SUBCLASS_CODE.getColumnName());
                    String subclassName = rs.getString(AccountSubclasses.SUBCLASS_NAME.getColumnName());

                    subclassesByClassList.add(new AccountSubclassDTO(modelId, classId, subclassId, subclassCode, subclassName));
                }
            }

            return new AccountSubclassesRetrieveResponse(request.getSessionId(),ResultType.FOUND ,subclassesByClassList);

        } catch (SQLException ex) {
            Logger.getLogger(AccountSubclassManager.class.getName()).log(Level.SEVERE,
                    "Error retrieving subclasses for Class ID: " + request.getClassId() + " in Model ID: " + request.getModelId(), ex);
            return new AccountSubclassesRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR, subclassesByClassList);
        }
    }

    public static AccountSubclassesRetrieveResponse getSubclassesBySubclassId(AccountSubclassByIdRetrieveRequest request) {
        List<AccountSubclassDTO> subclassesByClassList = new ArrayList<>();
        String query = Q.select(DatabaseTables.FI_ACCOUNT_SUBCLASSES.tableName(),
                AccountSubclasses.SUBCLASS_ID.getColumnName(),
                AccountSubclasses.MODEL_ID.getColumnName(),
                AccountSubclasses.CLASS_ID.getColumnName(),
                AccountSubclasses.SUBCLASS_CODE.getColumnName(),
                AccountSubclasses.SUBCLASS_NAME.getColumnName())
                .concat(Q.where(AccountSubclasses.SUBCLASS_ID.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {

            // Set query parameters from request
            st.setInt(1, request.getSubclassId());

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int modelId = rs.getInt(AccountSubclasses.MODEL_ID.getColumnName());
                    int classId = rs.getInt(AccountSubclasses.CLASS_ID.getColumnName());
                    int subclassId = rs.getInt(AccountSubclasses.SUBCLASS_ID.getColumnName());
                    int subclassCode = rs.getInt(AccountSubclasses.SUBCLASS_CODE.getColumnName());
                    String subclassName = rs.getString(AccountSubclasses.SUBCLASS_NAME.getColumnName());

                    subclassesByClassList.add(new AccountSubclassDTO(modelId, classId, subclassId, subclassCode, subclassName));
                }
                if (!subclassesByClassList.isEmpty()) {
                    return new AccountSubclassesRetrieveResponse(request.getSessionId(), ResultType.FOUND, subclassesByClassList);
                } else {
                    return new AccountSubclassesRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, subclassesByClassList);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountSubclassManager.class.getName()).log(Level.SEVERE,
                    "Error retrieving subclasses for Subclass ID: " + request.getSubclassId() + " ", ex);
        }
        return new AccountSubclassesRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, subclassesByClassList);
    }

    public static AccountSubclassListRetrieveResponse getSubclassesList(AccountSubclassListRetrieveRequest request) {
        List<Object[]> dataSource = new ArrayList<>();
        String query = """
                   SELECT
                   sub.SUBCLASS_ID,
                   sub.SUBCLASS_CODE,
                   sub.SUBCLASS_NAME,
                   sub.CLASS_ID,
                   ccl.CLASS_NAME,
                   sub.MODEL_ID,
                   mdl.MODEL_NAME,
                   mdl.DESCRIPTION,
                   mdl.STATE
                   
                   FROM
                   account_subclasses sub
                   JOIN account_classes ccl ON sub.CLASS_ID = ccl.CLASS_ID
                   JOIN account_models mdl ON sub.MODEL_ID = mdl.MODEL_ID""";

        try (Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    dataSource.add(new Object[]{
                        rs.getObject("sub.SUBCLASS_ID"),
                        rs.getObject("sub.SUBCLASS_CODE"),
                        rs.getObject("sub.SUBCLASS_NAME"),
                        rs.getObject("sub.CLASS_ID"),
                        rs.getObject("ccl.CLASS_NAME"),
                        rs.getObject("sub.MODEL_ID"),
                        rs.getObject("mdl.MODEL_NAME"),
                        rs.getObject("mdl.DESCRIPTION"),
                        rs.getObject("mdl.STATE")
                    });
                }
                if(!dataSource.isEmpty()){
                    return new AccountSubclassListRetrieveResponse(request.getSessionId(),ResultType.FOUND,dataSource);
                }else{
                    return new AccountSubclassListRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND,dataSource);
                }
            }
        } catch (SQLException ex) {
            logger.info("Error on account subclass list retrieve on SQL", ex);
        }
        return new AccountSubclassListRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR,dataSource);
    }

}
