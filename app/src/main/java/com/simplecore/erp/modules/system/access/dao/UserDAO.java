
package com.simplecore.erp.modules.system.access.dao;

import com.simplecore.erp.models.login.User;
import com.simplecore.erp.modules.system.access.exceptions.DatabaseException;
import com.simplecore.erp.modules.system.access.models.Role;
import java.util.Optional;
/**
 *
 * @author user
 */
public interface UserDAO {
    /**
     * Finds a user by its ID.
     * @param userId The ID of the user.
     * @return The User object if found, otherwise null.
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
    Optional<User> getUserById(int userId)throws DatabaseException ;

    /**
     * Finds a user by its username.
     * @param username The username of the user.
     * @return The User object if found, otherwise null.
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
    Optional<User> getUserByUsername(String username) throws DatabaseException;

    /**
     * Assigns a role to a user.
     * @param userId The ID of the user.
     * @param role The role to be assigned.
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
    void assignRole(int userId, Role role) throws DatabaseException;

    /**
     * Removes a role from a user.
     * @param userId The ID of the user.
     * @param roleId
     * @throws com.simplecore.erp.modules.system.access.exceptions.DatabaseException
     */
    void removeRole(int userId,Role roleId) throws DatabaseException;
}
