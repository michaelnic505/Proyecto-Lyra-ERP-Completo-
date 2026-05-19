
package com.simplecore.erp.modules.system.access.dao;

/**
 *
 * @author user
 */
import com.simplecore.erp.modules.system.access.exceptions.DatabaseException;
import com.simplecore.erp.modules.system.access.models.Role;
import com.simplecore.erp.modules.system.access.models.Transaction;
import java.util.Optional;
import java.util.Set;

public interface RoleDAO {
    
    Optional<String> getRoleByUserId(int userId)throws DatabaseException;

    /**
     * Retrieves a role by its ID.
     * @param roleId The ID of the role.
     * @return The Role object if found, otherwise null.
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
    Optional<Role> getRoleById(String roleId)throws DatabaseException;

    /**
     * Retrieves a role by its name.
     * @param roleName The name of the role.
     * @return The Role object if found, otherwise null.
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
   Optional<Role> getRoleByName(String roleName)throws DatabaseException;

    /**
     * Gets all the transactions allowed for a specific role.
     * @param roleId The ID of the role.
     * @return A set of allowed transactions.
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
    Set<Transaction> getTransactionsForRole(String roleId)throws DatabaseException;

    /**
     * Assigns a transaction to a role.
     * @param role The ID of the role.
     * @param transaction The transaction to assign.
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
    void assignTransactionToRole(Role role, Transaction transaction)throws DatabaseException;

    /**
     * Removes a transaction from a role.
     * @param roleId The ID of the role.
     * @param transactionCode The transaction code to remove.
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
    void removeTransactionFromRole(String roleId, String transactionCode)throws DatabaseException;
}
