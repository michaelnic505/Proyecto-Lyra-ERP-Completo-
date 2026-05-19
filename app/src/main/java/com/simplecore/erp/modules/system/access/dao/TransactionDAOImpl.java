package com.simplecore.erp.modules.system.access.dao;

import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tablecolumns.RoleTransactions;
import com.simplecore.erp.config.database.tablecolumns.Transactions;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.modules.system.access.exceptions.DatabaseException;
import com.simplecore.erp.modules.system.access.models.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
/**
 * Implementation of the TransactionDAO interface for database operations
 * related to transactions. (Implementación de la interfaz TransactionDAO para
 * operaciones de base de datos relacionadas con transacciones.)
 */
public class TransactionDAOImpl implements TransactionDAO {

    /**
     * Logger instance for logging errors and important events. (Instancia de
     * Logger para registrar errores y eventos importantes.)
     */
    private static final Logger LOGGER = Logger.getLogger(TransactionDAOImpl.class.getName());
    /**
     * Database connection instance. (Instancia de conexión a la base de datos.)
     */
    private Connection connection;

    /**
     * Constructor that initializes the database connection. (Constructor que
     * inicializa la conexión a la base de datos.)
     *
     * @param connection the database connection (la conexión a la base de
     * datos)
     */
    public TransactionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a transaction by its ID. (Recupera una transacción por su ID.)
     *
     * @param transactionId the ID of the transaction (el ID de la transacción)
     * @return an Optional containing the Transaction if found, otherwise empty
     * (Un Optional que contiene la transacción si se encuentra, de lo contrario
     * está vacío.)
     * @throws DatabaseException if a database error occurs (si ocurre un error
     * en la base de datos)
     */
    @Override
    public Optional<Transaction> getTransactionById(String transactionId) throws DatabaseException {
        if (transactionId == null || transactionId.trim().isEmpty()) {
            throw new IllegalArgumentException("The transactionId cannot be null or empty.");
        }
        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.select(
                        DatabaseTables.TRANSACTIONS.tableName(),
                        Transactions.TRANSACTION_ID.columnName(),
                        Transactions.TRANSACTION_NAME.columnName(),
                        Transactions.DESCRIPTION.columnName(),
                        Transactions.FOLDER_INDEX.columnName(),
                        Transactions.FOLDER_NAME.columnName()))
                .append(SQLKeywords.where(Transactions.TRANSACTION_ID.name()));

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setString(1, transactionId);
            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(new Transaction.Builder()
                        .id(rs.getString(Transactions.TRANSACTION_ID.columnName()))
                        .name(rs.getString(Transactions.TRANSACTION_NAME.columnName()))
                        .description(rs.getString(Transactions.DESCRIPTION.columnName()))
                        .folderindex(rs.getString(Transactions.FOLDER_INDEX.columnName()))
                        .foldername(rs.getString(Transactions.FOLDER_NAME.columnName()))
                        .build());
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve the transaction by ID:" + transactionId, ex);
            throw new DatabaseException("Unable to retrieve the transaction by ID:" + transactionId, ex);
        }

    }

    /**
     * Checks if a given role has access to a specific transaction. (Verifica si
     * un rol determinado tiene acceso a una transacción específica.)
     *
     * @param roleId the ID of the role (el ID del rol)
     * @param transactionId the ID of the transaction (el ID de la transacción)
     * @return true if the role has access, otherwise false (true si el rol
     * tiene acceso, de lo contrario false.)
     * @throws DatabaseException if a database error occurs (si ocurre un error
     * en la base de datos)
     */

    @Override
    public boolean hasRoleAccessToTransaction(String roleId, String transactionId) throws DatabaseException {
        if (roleId == null || roleId.trim().isEmpty()) {
            throw new IllegalArgumentException("The roleId cannot be null or empty.");
        }
        if (transactionId == null || transactionId.trim().isEmpty()) {
            throw new IllegalArgumentException("The transactionId cannot be null or empty.");
        }
        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.select(
                        DatabaseTables.ROLE_TRANSACTIONS.tableName(),
                        RoleTransactions.TRANSACTION_ID.columnName()))
                .append(
                        SQLKeywords.where(
                                RoleTransactions.ROLE_ID.columnName(),
                                RoleTransactions.TRANSACTION_ID.columnName()));

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setString(1, roleId);
            st.setString(2, transactionId);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve the permission for:" + roleId + " and " + transactionId, ex);
            throw new DatabaseException("Unable to retrieve the permission for:" + roleId + " and " + transactionId, ex);
        }
    }

}
