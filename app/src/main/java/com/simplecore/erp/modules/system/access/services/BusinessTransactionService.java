package com.simplecore.erp.modules.system.access.services;

import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.controllers.maintree.ModulesController;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.models.login.User;
import com.simplecore.erp.models.maintree.BusinessTransactionsEnum;
import com.simplecore.erp.modules.system.access.dao.RoleDAOImpl;
import com.simplecore.erp.modules.system.access.dao.TransactionDAOImpl;
import com.simplecore.erp.modules.system.access.dao.UserDAOImpl;
import com.simplecore.erp.modules.system.access.exceptions.DatabaseException;
import com.simplecore.erp.modules.system.access.models.Role;
import com.simplecore.erp.modules.system.access.models.Transaction;
import com.simplecore.erp.utils.notifications.NOT;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Set;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
/**
 * Service class to handle business transactions, user roles, and their associated operations.
 * Esta clase de servicio maneja las transacciones comerciales, los roles de usuario y sus operaciones asociadas.
 */
public class BusinessTransactionService {

    private UserDAOImpl userDAO; // DAO for user operations
    private RoleDAOImpl roleDAO; // DAO for role operations
    private Connection connection; // Database connection object
    private TransactionDAOImpl transactionDAO; // DAO for transaction operations
    private ModulesController modulesController;
    private SystemMessages systemMsg;

    /**
     * Constructor to initialize the service with necessary DAOs and a database connection.Constructor para inicializar el servicio con los DAOs necesarios y una conexión a la base de datos.
     * @param connection
     */
    public BusinessTransactionService(Connection connection) {
        this.connection = connection; // Database connection
        this.userDAO = new UserDAOImpl(connection); // Initialize UserDAO
        this.roleDAO = new RoleDAOImpl(connection); // Initialize RoleDAO
        this.transactionDAO = new TransactionDAOImpl(connection); // Initialize TransactionDAO
        this.modulesController = new ModulesController();
        this.systemMsg = new SystemMessages();
    }

    /**
     * Fetch a user by their ID.
     * @param userId The ID of the user to fetch.
     * @return The user with the provided ID.
     * @throws DatabaseException If the user is not found in the database.
     * Obtiene un usuario por su ID.
     * @param userId El ID del usuario a obtener.
     * @return El usuario con el ID proporcionado.
     * @throws DatabaseException Si no se encuentra el usuario en la base de datos.
     */
    public User getUserById(int userId) throws DatabaseException {
        return userDAO.getUserById(userId)
                .orElseThrow(() -> new DatabaseException("User with ID " + userId + " not found"));
    }

    /**
     * Fetch a user by their username.
     * @param username The username of the user to fetch.
     * @return The user with the provided username.
     * @throws DatabaseException If the user is not found in the database.
     * Obtiene un usuario por su nombre de usuario.
     * @param username El nombre de usuario del usuario a obtener.
     * @return El usuario con el nombre de usuario proporcionado.
     * @throws DatabaseException Si no se encuentra el usuario en la base de datos.
     */
    public User getUserByUsername(String username) throws DatabaseException {
        return userDAO.getUserByUsername(username)
                .orElseThrow(() -> new DatabaseException("User with ID " + username + " not found"));
    }

    /**
     * Assign a role to a user.
     * @param userId The ID of the user.
     * @param role The role to assign to the user.
     * @throws DatabaseException If there is an error during the database operation.
     * Asigna un rol a un usuario.
     * @param userId El ID del usuario.
     * @param role El rol que se asignará al usuario.
     * @throws DatabaseException Si hay un error durante la operación en la base de datos.
     */
    public void assigRoleToUser(int userId, Role role) throws DatabaseException {
        try {
            PooledConnectionService.beginTransaction(connection); // Begin transaction
            userDAO.assignRole(userId, role); // Assign role to user
            PooledConnectionService.commitTransaction(connection); // Commit transaction
        } catch (DatabaseException ex) {
            PooledConnectionService.rollbackTransaction(connection); // Rollback transaction in case of error
            throw ex;
        }
    }

    /**
     * Remove a role from a user.
     * @param userId The ID of the user.
     * @param role The role to remove from the user.
     * @throws DatabaseException If there is an error during the database operation.
     * Elimina un rol de un usuario.
     * @param userId El ID del usuario.
     * @param role El rol que se eliminará del usuario.
     * @throws DatabaseException Si hay un error durante la operación en la base de datos.
     */
    public void removeRoleToUser(int userId, Role role) throws DatabaseException {
        try {
            PooledConnectionService.beginTransaction(connection); // Begin transaction
            userDAO.removeRole(userId, role); // Remove role from user
            PooledConnectionService.commitTransaction(connection); // Commit transaction
        } catch (DatabaseException ex) {
            PooledConnectionService.rollbackTransaction(connection); // Rollback transaction in case of error
            throw ex;
        }
    }

    /**
     * Fetch a role by its ID.
     * @param roleId The ID of the role to fetch.
     * @return The role with the provided ID.
     * @throws DatabaseException If the role is not found in the database.
     * Obtiene un rol por su ID.
     * @param roleId El ID del rol a obtener.
     * @return El rol con el ID proporcionado.
     * @throws DatabaseException Si no se encuentra el rol en la base de datos.
     */
    public Role getRoleById(String roleId) throws DatabaseException {
        return roleDAO.getRoleById(roleId)
                .orElseThrow(() -> new DatabaseException("Role with ID: " + roleId + " not found"));
    }

    /**
     * Fetch a role by its name.
     * @param roleName The name of the role to fetch.
     * @return The role with the provided name.
     * @throws DatabaseException If the role is not found in the database.
     * Obtiene un rol por su nombre.
     * @param roleName El nombre del rol a obtener.
     * @return El rol con el nombre proporcionado.
     * @throws DatabaseException Si no se encuentra el rol en la base de datos.
     */
    public Role getRoleByName(String roleName) throws DatabaseException {
        return roleDAO.getRoleByName(roleName)
                .orElseThrow(() -> new DatabaseException("Role with NAME: " + roleName + " not found"));
    }

    /**
     * Get all transactions associated with a specific role ID.
     * @param roleId The ID of the role to fetch transactions for.
     * @return A set of transactions associated with the role.
     * @throws DatabaseException If there is an error during the database operation.
     * Obtiene todas las transacciones asociadas con un ID de rol específico.
     * @param roleId El ID del rol para el cual obtener las transacciones.
     * @return Un conjunto de transacciones asociadas con el rol.
     * @throws DatabaseException Si hay un error durante la operación en la base de datos.
     */
    public Set<Transaction> getTransactionsForRoleId(String roleId) throws DatabaseException {
        return roleDAO.getTransactionsForRole(roleId); // Get transactions for the given role ID
    }

    /**
     * Assign a transaction to a role.
     * @param role The role to assign the transaction to.
     * @param transaction The transaction to assign.
     * @throws DatabaseException If there is an error during the database operation.
     * Asigna una transacción a un rol.
     * @param role El rol al que se asignará la transacción.
     * @param transaction La transacción a asignar.
     * @throws DatabaseException Si hay un error durante la operación en la base de datos.
     */
    public void assignTransactionToRole(Role role, Transaction transaction) throws DatabaseException {
        try {
            PooledConnectionService.beginTransaction(connection); // Begin transaction
            roleDAO.assignTransactionToRole(role, transaction); // Assign transaction to role
            PooledConnectionService.commitTransaction(connection); // Commit transaction
        } catch (DatabaseException ex) {
            PooledConnectionService.rollbackTransaction(connection); // Rollback transaction in case of error
            throw ex;
        }
    }

    /**
     * Remove a transaction from a role.
     * @param roleId The ID of the role.
     * @param transactionCode The code of the transaction to remove.
     * @throws DatabaseException If there is an error during the database operation.
     * Elimina una transacción de un rol.
     * @param roleId El ID del rol.
     * @param transactionCode El código de la transacción a eliminar.
     * @throws DatabaseException Si hay un error durante la operación en la base de datos.
     */
    public void removeTransactionFromRole(String roleId, String transactionCode) throws DatabaseException {
        try {
            PooledConnectionService.beginTransaction(connection); // Begin transaction
            roleDAO.removeTransactionFromRole(roleId, transactionCode); // Remove transaction from role
            PooledConnectionService.commitTransaction(connection); // Commit transaction
        } catch (DatabaseException ex) {
            PooledConnectionService.rollbackTransaction(connection); // Rollback transaction in case of error
            throw ex;
        }
    }

    /**
     * Fetch the role associated with a user ID.
     * @param userId The ID of the user to fetch the role for.
     * @return The role associated with the user.
     * @throws DatabaseException If the role is not found for the user.
     * Obtiene el rol asociado con un ID de usuario.
     * @param userId El ID del usuario para obtener el rol.
     * @return El rol asociado con el usuario.
     * @throws DatabaseException Si no se encuentra el rol para el usuario.
     */
    public String getRoleByUserId(int userId) throws DatabaseException {
        return roleDAO.getRoleByUserId(userId)
                .orElseThrow(() -> new DatabaseException("Role not found by UserId: " + userId));
    }

    /**
     * Fetch a transaction by its ID.
     * @param transactionId The ID of the transaction to fetch.
     * @return The transaction with the provided ID.
     * @throws DatabaseException If the transaction is not found in the database.
     * Obtiene una transacción por su ID.
     * @param transactionId El ID de la transacción a obtener.
     * @return La transacción con el ID proporcionado.
     * @throws DatabaseException Si no se encuentra la transacción en la base de datos.
     */
    public Transaction getTransactionById(String transactionId) throws DatabaseException {
        return transactionDAO.getTransactionById(transactionId)
                .orElseThrow(() -> new DatabaseException("Transaction not found by ID: " + transactionId));
    }

    /**
     * Check if a role has access to a specific transaction.
     * @param roleId The ID of the role.
     * @param transactionId The ID of the transaction.
     * @return true if the role has access to the transaction, false otherwise.
     * @throws DatabaseException If there is an error during the database operation.
     * Verifica si un rol tiene acceso a una transacción específica.
     * @param roleId El ID del rol.
     * @param transactionId El ID de la transacción.
     * @return true si el rol tiene acceso a la transacción, false de lo
     * contrario.
     * @throws DatabaseException Si hay un error durante la operación en la base
     * de datos.
     */
    public boolean hasRoleAccessToTransaction(String roleId, String transactionId) throws DatabaseException {
        return transactionDAO.hasRoleAccessToTransaction(roleId, transactionId);
    }

    public void openTransaction(String transactionCode, int userId,String username) {
        BusinessTransactionsEnum transaction = getTransactionByCode(transactionCode);
        if (transaction == null) {
            systemMsg.showErrorMsg(NOT.msg(NOT.TRANSACTION_NOT_EXISTS)+": "+transactionCode);
            return;
        }
        try {
            String userRole = getRoleByUserId(userId);
            boolean hasAccess = hasRoleAccessToTransaction(userRole, transactionCode);
            if (hasAccess) {
                modulesController.openTransaction(transaction);
            } else {
                systemMsg.showWarningMsg(NOT.msg(NOT.NO_PERMISSIONS)+" "+username);
            }

        } catch (DatabaseException ex) {
            systemMsg.showErrorMsg(ex.getMessage());
        }

    }

    private static BusinessTransactionsEnum getTransactionByCode(String code) {
        return Arrays.stream(BusinessTransactionsEnum.values())
                .filter(transaction -> transaction.getKey().equals(code))
                .findFirst()
                .orElse(null);  // Devuelve null si no encuentra la transacción
    }

}
