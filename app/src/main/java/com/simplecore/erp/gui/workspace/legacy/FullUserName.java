package com.simplecore.erp.gui.workspace.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tablecolumns.UsersSystem;

public class FullUserName {

    private static final Logger LOGGER = Logger.getLogger(FullUserName.class.getName());

    /**
     * Retrieves the full name of a user from the database based on the provided
     * username.
     *
     * @param user The username to search for in the database.
     * @return The full name of the user as a concatenated String, or an empty
     * string if the user is not found or an error occurs.
     */
//    public static String getName(String user) {
//        // Build the SQL query
//        String query = new StringBuilder()
//                .append(SQLKeywords.SELECT.toSQL()).append(" ")
//                .append(UsersSystem.FIRSTNAME.name()).append(", ")
//                .append(UsersSystem.SECONDNAME.name()).append(", ")
//                .append(UsersSystem.FIRSTLASTNAME.name()).append(", ")
//                .append(UsersSystem.SECONDLASTNAME.name()).append(" ")
//                .append(SQLKeywords.FROM.toSQL()).append(" ")
//                .append(DatabaseTables.USERS_SYSTEM.getName()).append(" ")
//                .append(SQLKeywords.WHERE.toSQL()).append(" ")
//                .append(UsersSystem.USERNAME.name()).append("=?")
//                .toString();
//
//        // Use try-with-resources to ensure all resources are closed
//        try (Connection conexion = PooledConnectionService.getConnection(); PreparedStatement st = conexion.prepareStatement(query)) {
//
//            // Set the parameter for the query
//            st.setString(1, user);
//
//            // Execute the query and process the result
//            try (ResultSet rs = st.executeQuery()) {
//                if (rs.next()) {
//                    // Concatenate the full name
//                    return rs.getString(1) + " " + rs.getString(2) + " "
//                            + rs.getString(3) + " " + rs.getString(4);
//                }
//            }
//
//        } catch (SQLException ex) {
//            LOGGER.log(Level.SEVERE, "An error occurred while retrieving the user's full name.", ex);
//        }
//
//        // Return an empty string if the user is not found or an error occurs
//        return "";
//    }
}
