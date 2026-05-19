package com.simplecore.erp.modules.system.access.dao;

import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tablecolumns.Users;
import com.simplecore.erp.config.database.tablecolumns.UsersRoles;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.models.login.User;
import com.simplecore.erp.modules.system.access.exceptions.DatabaseException;
import com.simplecore.erp.modules.system.access.models.Role;
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
 * Implementation of the UserDAO interface, responsible for database operations
 * related to users, such as retrieving user data and managing roles.
 */
public class UserDAOImpl implements UserDAO {
    
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());
    private Connection connection;

    /**
     * Constructs a UserDAOImpl with a database connection.
     *
     * @param connection The database connection.
     */
    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A User object if found, otherwise null.
     */
    @Override
    public Optional<User> getUserById(int userId) throws DatabaseException{
        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.select(
                        DatabaseTables.USERS.tableName(),
                        Users.USER_ID.columnName(),
                        Users.USERNAME.columnName(),
                        Users.PASSWORD_HASH.columnName(),
                        Users.FIRST_NAME.columnName(),
                        Users.LAST_NAME.columnName(),
                        Users.EMAIL.columnName(),
                        Users.POSITION.columnName(),
                        Users.DEPARTMENT.columnName(),
                        Users.ROLE.columnName(),
                        Users.IS_ACTIVE.columnName()))
                .append(SQLKeywords.where(Users.USER_ID.name()));

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setInt(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    LOGGER.log(Level.INFO, "User with ID {0} not found", userId);
                    return Optional.empty(); // No user found
                }
                return Optional.of(new User(
                        rs.getInt(Users.USER_ID.columnName()),
                        rs.getString(Users.USERNAME.columnName()),
                        rs.getString(Users.PASSWORD_HASH.columnName()),
                        rs.getString(Users.FIRST_NAME.columnName()),
                        rs.getString(Users.LAST_NAME.columnName()),
                        rs.getString(Users.EMAIL.columnName()),
                        rs.getString(Users.POSITION.columnName()),
                        rs.getString(Users.DEPARTMENT.columnName()),
                        rs.getString(Users.ROLE.columnName())
                ));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Database error while retrieving user with ID " + userId, ex);
            throw new DatabaseException("Error retrieving user by ID: " + userId, ex);
        }
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return A User object if found, otherwise null.
     */
    @Override
    public Optional<User> getUserByUsername(String username) throws DatabaseException{
        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.select(
                        DatabaseTables.USERS.tableName(),
                        Users.USER_ID.columnName(),
                        Users.USERNAME.columnName(),
                        Users.PASSWORD_HASH.columnName(),
                        Users.FIRST_NAME.columnName(),
                        Users.LAST_NAME.columnName(),
                        Users.EMAIL.columnName(),
                        Users.POSITION.columnName(),
                        Users.DEPARTMENT.columnName(),
                        Users.ROLE.columnName(),
                        Users.IS_ACTIVE.columnName()))
                .append(SQLKeywords.where(Users.USERNAME.name()));

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setString(1, username);
            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty(); // No role found
                }
                return Optional.of(new User(
                        rs.getInt(Users.USER_ID.columnName()),
                        rs.getString(Users.USERNAME.columnName()),
                        rs.getString(Users.PASSWORD_HASH.columnName()),
                        rs.getString(Users.FIRST_NAME.columnName()),
                        rs.getString(Users.LAST_NAME.columnName()),
                        rs.getString(Users.EMAIL.columnName()),
                        rs.getString(Users.POSITION.columnName()),
                        rs.getString(Users.DEPARTMENT.columnName()),
                        rs.getString(Users.ROLE.columnName())
                ));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Database error while retrieving user with USERNAME " + username, ex);
            throw new DatabaseException("Error retrieving user by USERNAME: " + username, ex);
        }
    }

    /**
     * Assigns a role to a user.
     *
     * @param userId The ID of the user.
     * @param role The role to assign.
     */
    @Override
    public void assignRole(int userId, Role role) throws DatabaseException {
        String query = SQLKeywords.insertInto(
                DatabaseTables.USERS_ROLES.tableName(),
                UsersRoles.USER_ID.columnName(),
                UsersRoles.ROLE_ID.columnName(),
                UsersRoles.ROLE_NAME.columnName()
        );
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, userId);
            st.setString(2, role.getId());
            st.setString(3, role.getName());
            st.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "The role could not be assigned to user ID: " + userId, ex);
            throw new DatabaseException("The role could not be assigned.", ex);
        }
    }


    /**
     * Removes all roles assigned to a user.
     *
     * @param userId The ID of the user whose roles should be removed.
     * @param role
     */
    @Override
    public void removeRole(int userId, Role role) throws DatabaseException {
        StringBuilder query = new StringBuilder();
        query.append(
                SQLKeywords.deleteFrom(
                        DatabaseTables.USERS_ROLES.tableName(),
                        UsersRoles.USER_ID.columnName()
                ))
                .append(SQLKeywords.where(role.getId()));

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setInt(1, userId);
            st.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to remove role." + userId, ex);
            throw new DatabaseException("Failed to remove role." + userId, ex);
        }
    }
}
