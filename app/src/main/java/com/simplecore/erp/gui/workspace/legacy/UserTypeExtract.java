package com.simplecore.erp.gui.workspace.legacy;

import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tablecolumns.UsersSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserTypeExtract {

    private static final Logger LOGGER = Logger.getLogger(UserTypeExtract.class.getName());

    /**
     * Extracts the user type from the database based on the provided username.
     *
     * @param user The username to search for in the database.
     * @return The user type as a String, or null if the user is not found or an error occurs.
     */
    public static String extract(String user) {
        // Build the SQL query
        String query = new StringBuilder()
                .append(SQLKeywords.SELECT.toSQL()).append(" ")
                .append(UsersSystem.USERTYPE.toString()).append(" ")
                .append(SQLKeywords.FROM.toSQL()).append(" ")
                .append(DatabaseTables.USERS_SYSTEM.tableName()).append(" ")
                .append(SQLKeywords.WHERE.toSQL()).append(" ")
                .append(UsersSystem.USERNAME.toString()).append("=?")
                .toString();

        // Use try-with-resources to ensure all resources are closed
        try (Connection conexion = PooledConnectionService.getConnection();
             PreparedStatement st = conexion.prepareStatement(query)) {

            // Set the parameter for the query
            st.setString(1, user);

            // Execute the query and process the result
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred while extracting the user type.", ex);
        }

        // Return null if the user is not found or an error occurs
        return null;
    }
}