package com.simplecore.erp.modules.system.access.dao;

import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tablecolumns.RoleTransactions;
import com.simplecore.erp.config.database.tablecolumns.Transactions;
import com.simplecore.erp.config.database.tablecolumns.UsersRoles;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.modules.system.access.exceptions.DatabaseException;
import com.simplecore.erp.modules.system.access.models.Role;
import com.simplecore.erp.modules.system.access.models.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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
public class RoleDAOImpl implements RoleDAO {

    static final Logger LOGGER = Logger.getLogger(RoleDAOImpl.class.getName());
    private Connection connection;

    public RoleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a role by its unique identifier. (Recupera un rol por su
     * identificador único.)
     *
     * @param roleId The unique identifier of the role. (El identificador único
     * del rol.)
     * @return An Optional containing the Role if found, otherwise empty. (Un
     * Optional que contiene el rol si se encuentra, de lo contrario vacío.)
     * @throws DatabaseException If an error occurs while accessing the
     * database. (Si ocurre un error al acceder a la base de datos.)
     */
    @Override
    public Optional<Role> getRoleById(String roleId) throws DatabaseException {
        // Validate roleId (Validar roleId)
        if (roleId == null || roleId.trim().isEmpty()) {
            throw new IllegalArgumentException("The roleId cannot be null or empty.");
        }

        // Construct SQL query (Construir consulta SQL)
        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.select(
                        DatabaseTables.ROLE_TRANSACTIONS.tableName(),
                        RoleTransactions.ROLE_ID.columnName(),
                        RoleTransactions.ROLE_NAME.columnName(),
                        RoleTransactions.TRANSACTION_ID.columnName(),
                        RoleTransactions.TRANSACTION_NAME.columnName()))
                .append(
                        SQLKeywords.where(
                                RoleTransactions.ROLE_ID.columnName()
                        ));

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setString(1, roleId);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty(); // No role found (No se encontró el rol)
                }

                // Retrieve role details (Recuperar detalles del rol)
                String roleIdRetrieved = rs.getString(RoleTransactions.ROLE_ID.columnName());
                String roleNameRetrieved = rs.getString(RoleTransactions.ROLE_NAME.columnName());
                Set<Transaction> allowedTransactions = new HashSet<>();

                // Process transactions associated with the role (Procesar transacciones asociadas al rol)
                do {
                    allowedTransactions.add(new Transaction.Builder()
                            .id(rs.getString(RoleTransactions.TRANSACTION_ID.columnName()))
                            .name(rs.getString(RoleTransactions.TRANSACTION_NAME.columnName()))
                            .build());
                } while (rs.next());

                return Optional.of(new Role.Builder()
                        .id(roleIdRetrieved)
                        .name(roleNameRetrieved)
                        .allowedTransactions(allowedTransactions)
                        .build());
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving role by ID: " + roleId, ex);
            throw new DatabaseException("Error retrieving role by ID: " + roleId, ex);
        }
    }

    /**
     * Retrieves a role by its unique name. (Recupera un rol por su nombre
     * único.)
     *
     * @param roleName The unique name of the role. (El nombre único del rol.)
     * @return An Optional containing the Role if found, otherwise empty. (Un
     * Optional que contiene el rol si se encuentra, de lo contrario vacío.)
     * @throws DatabaseException If an error occurs while accessing the
     * database. (Si ocurre un error al acceder a la base de datos.)
     */
    @Override
    public Optional<Role> getRoleByName(String roleName) throws DatabaseException {
        // Validate roleName (Validar roleName)
        if (roleName == null || roleName.trim().isEmpty()) {
            throw new IllegalArgumentException("The role name cannot be null or empty.");
        }

        // Construct SQL query (Construir consulta SQL)
        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.select(
                        DatabaseTables.ROLE_TRANSACTIONS.tableName(),
                        RoleTransactions.ROLE_ID.columnName(),
                        RoleTransactions.ROLE_NAME.columnName(),
                        RoleTransactions.TRANSACTION_ID.columnName(),
                        RoleTransactions.TRANSACTION_NAME.columnName()))
                .append(
                        SQLKeywords.where(
                                RoleTransactions.ROLE_NAME.columnName()
                        ));

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setString(1, roleName);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty(); // No role found (No se encontró el rol)
                }

                // Retrieve role details (Recuperar detalles del rol)
                String roleIdRetrieved = rs.getString(RoleTransactions.ROLE_ID.columnName());
                String roleNameRetrieved = rs.getString(RoleTransactions.ROLE_NAME.columnName());
                Set<Transaction> allowedTransactions = new HashSet<>();

                // Process transactions associated with the role (Procesar transacciones asociadas al rol)
                do {
                    allowedTransactions.add(new Transaction.Builder()
                            .id(rs.getString(RoleTransactions.TRANSACTION_ID.columnName()))
                            .name(rs.getString(RoleTransactions.TRANSACTION_NAME.columnName()))
                            .build());
                } while (rs.next());

                return Optional.of(new Role.Builder()
                        .id(roleIdRetrieved)
                        .name(roleNameRetrieved)
                        .allowedTransactions(allowedTransactions)
                        .build());
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving role by name: " + roleName, ex);
            throw new DatabaseException("Error retrieving role by name: " + roleName, ex);
        }
    }

    /**
     * Retrieves the set of transactions associated with a given role ID.
     * (Recupera el conjunto de transacciones asociadas con un ID de rol dado.)
     *
     * @param roleId The unique identifier of the role. (El identificador único
     * del rol.)
     * @return A set of transactions allowed for the role. (Un conjunto de
     * transacciones permitidas para el rol.)
     * @throws DatabaseException If an error occurs while accessing the
     * database. (Si ocurre un error al acceder a la base de datos.)
     */
    @Override
    public Set<Transaction> getTransactionsForRole(String roleId) throws DatabaseException {
        // Validate roleId (Validar roleId)
        if (roleId == null || roleId.trim().isEmpty()) {
            throw new IllegalArgumentException("The roleId cannot be null or empty."); // (El roleId no puede ser nulo o vacío)
        }
        Set<Transaction> allowedTransactions = new HashSet<>();

        // Construct SQL query using StringBuilder (Construir consulta SQL usando StringBuilder)
        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.select(
                        DatabaseTables.ROLE_TRANSACTIONS.tableName(),
                        RoleTransactions.TRANSACTION_ID.columnName(),
                        RoleTransactions.TRANSACTION_NAME.columnName()))
                .append(
                        SQLKeywords.where(
                                RoleTransactions.ROLE_ID.name()
                        ));

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setString(1, roleId);
            try (ResultSet rs = st.executeQuery()) {
                // Process transactions associated with the role (Procesar transacciones asociadas al rol)
                while (rs.next()) {
                    allowedTransactions.add(new Transaction.Builder()
                            .id(rs.getString(RoleTransactions.TRANSACTION_ID.columnName()))
                            .name(rs.getString(RoleTransactions.TRANSACTION_NAME.columnName()))
                            .build());
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving transactions for role: " + roleId, ex);
            throw new DatabaseException("Error retrieving transactions for role: " + roleId, ex);
        }
        return allowedTransactions;
    }

    /**
     * Assigns a transaction to a role in the database. (Asigna una transacción
     * a un rol en la base de datos.)
     *
     * @param role The role to which the transaction will be assigned. (El rol
     * al que se asignará la transacción.)
     * @param transaction The transaction to be assigned to the role. (La
     * transacción que se asignará al rol.)
     * @throws DatabaseException If an error occurs while accessing the
     * database. (Si ocurre un error al acceder a la base de datos.)
     */
    @Override
    public void assignTransactionToRole(Role role, Transaction transaction) throws DatabaseException {
        // Construct SQL query using StringBuilder (Construir consulta SQL usando StringBuilder)
        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.insertInto(
                        DatabaseTables.ROLE_TRANSACTIONS.tableName(),
                        RoleTransactions.ROLE_ID.columnName(),
                        RoleTransactions.ROLE_NAME.columnName(),
                        Transactions.TRANSACTION_ID.columnName(),
                        Transactions.TRANSACTION_NAME.columnName()));

        // Begin database transaction (Iniciar transacción en base de datos)

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setString(1, role.getId());
            st.setString(2, role.getName());
            st.setString(3, transaction.getId());
            st.setString(4, transaction.getName());

            st.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error assigning transaction to role: " + role.getId(), ex);
            throw new DatabaseException("Error assigning transaction to role: " + role.getId(), ex);
        }
    }

    /**
     * Removes a transaction from a role in the database. (Elimina una
     * transacción de un rol en la base de datos.)
     *
     * @param roleId The ID of the role from which the transaction will be
     * removed. (El ID del rol del que se eliminará la transacción.)
     * @param transactionCode The code of the transaction to be removed. (El
     * código de la transacción que se eliminará.)
     * @throws DatabaseException If an error occurs while accessing the
     * database. (Si ocurre un error al acceder a la base de datos.)
     */
    @Override
    public void removeTransactionFromRole(String roleId, String transactionCode) throws DatabaseException {
        // Validate roleId (Validar roleId)
        if (roleId == null || roleId.trim().isEmpty()) {
            throw new IllegalArgumentException("roleId cannot be null or empty."); // (roleId no puede ser nulo o vacío)
        }

        // Validate transactionCode (Validar transactionCode)
        if (transactionCode == null || transactionCode.trim().isEmpty()) {
            throw new IllegalArgumentException("transactionCode cannot be null or empty."); // (transactionCode no puede ser nulo o vacío)
        }
        // Construct SQL query using StringBuilder (Construir consulta SQL usando StringBuilder)
        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.deleteFrom(DatabaseTables.ROLE_TRANSACTIONS.tableName(),
                        RoleTransactions.ROLE_ID.columnName(),
                        RoleTransactions.TRANSACTION_ID.columnName()));

        // Begin database transaction (Iniciar transacción en base de datos)
        PooledConnectionService.beginTransaction(connection);

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setString(1, roleId);
            st.setString(2, transactionCode);

            // Execute update (Ejecutar actualización)
            st.executeUpdate();

            // Commit transaction (Confirmar transacción)
            PooledConnectionService.commitTransaction(connection);
        } catch (SQLException ex) {
            // Rollback transaction on error (Revertir transacción en caso de error)
            PooledConnectionService.rollbackTransaction(connection);
            LOGGER.log(Level.SEVERE, "Error removing transaction from role: " + roleId, ex);
            throw new DatabaseException("Error removing transaction from role: " + roleId, ex);
        }
    }

    /**
     * Retrieves the role assigned to a specific user by user ID. (Recupera el
     * rol asignado a un usuario específico por su ID de usuario.)
     *
     * @param userId The ID of the user whose role is to be retrieved. (El ID
     * del usuario cuyo rol se recuperará.)
     * @return An Optional containing the role ID if found, or an empty Optional
     * if not. (Un Optional con el ID del rol si se encuentra, o un Optional
     * vacío si no.)
     * @throws DatabaseException If an error occurs while accessing the
     * database. (Si ocurre un error al acceder a la base de datos.)
     */
    @Override
    public Optional<String> getRoleByUserId(int userId) throws DatabaseException {

        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.select(
                        DatabaseTables.USERS_ROLES.tableName(),
                        UsersRoles.ROLE_ID.columnName()))
                .append(
                        SQLKeywords.where(
                                UsersRoles.USER_ID.columnName()
                        ));

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setInt(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(rs.getString(1));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving role by UserID: " + userId, ex);
            throw new DatabaseException("Error retrieving role by UserID: " + userId, ex);
        }
    }

}
