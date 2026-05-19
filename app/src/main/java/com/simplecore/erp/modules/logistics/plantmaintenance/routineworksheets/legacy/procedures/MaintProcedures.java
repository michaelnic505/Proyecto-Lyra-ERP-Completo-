package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.procedures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MaintenanceProcedures1;
import com.simplecore.erp.config.database.PooledConnectionService;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import com.simplecore.erp.config.database.DatabaseTables.MaintenanceProceduresAurotizations;

public class MaintProcedures {

    public List<List<Object>> getMaintStrategies() {

        try (Connection conexion = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceProcedures1.PROCEDUREKEY.toString()).append(", ")
                    .append(MaintenanceProcedures1.SHORDESCRIPTION.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_PROCEDURES.tableName());

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {

                try (ResultSet rs = st.executeQuery()) {

                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    List<List<Object>> data = new ArrayList<>();

                    while (rs.next()) {
                        List<Object> row = new ArrayList<>();
                        for (int col = 1; col <= columnCount; col++) {
                            row.add(rs.getObject(col));
                        }
                        data.add(row);
                    }

                    return data;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void createProcedureText(String procedureKey, String shortDesc, String extendedText) {
        try (Connection connection = PooledConnectionService.getConnection()) {
            
            PooledConnectionService.beginTransaction(connection);
            
            // Build the SQL query to insert procedure key and extended text into the table
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.INSERT.toSQL())
                    .append(DatabaseTables.MAINTENANCE_PROCEDURES.tableName())
                    .append(SentenceValues.setValues(3)); // Two values to insert

            String query = queryBuilder.toString();
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, procedureKey);  // Set the procedure key
                st.setString(2, shortDesc);  // Set the extended text
                st.setString(3, extendedText);  // Set the extended text

                // Execute the insert query
                st.executeUpdate();
                PooledConnectionService.commitTransaction(connection);
            }catch(SQLException ex){
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                PooledConnectionService.rollbackTransaction(connection);
            }
        } catch (SQLException ex) {
            // Log any SQL exceptions
            Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateProcedureText(String procedureKey, String shortDesc, String extendedText) {
        try (Connection connection = PooledConnectionService.getConnection()) {
            // Establish the database connection

            // Build the SQL query to update the procedure text
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.UPDATE.toSQL())
                    .append(DatabaseTables.MAINTENANCE_PROCEDURES.tableName())
                    .append(SQLKeywords.SET.toSQL())
                    .append(MaintenanceProcedures1.PROCEDURETEXT.toString()).append("=?").append(", ")
                    .append(MaintenanceProcedures1.SHORDESCRIPTION.toString()).append("=?")
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceProcedures1.PROCEDUREKEY.toString()).append("=?");

            String query = queryBuilder.toString();

            // Prepare the SQL statement
            try (PreparedStatement st = connection.prepareStatement(query)) {

                // Set the values for the query
                st.setString(1, extendedText); // Set the new procedure text
                st.setString(2, shortDesc); // Set the procedure key to identify the record
                st.setString(3, procedureKey); // Set the procedure key to identify the record

                // Execute the update query
                st.executeUpdate();
                PooledConnectionService.commitTransaction(connection);
            }catch(SQLException ex){
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                PooledConnectionService.rollbackTransaction(connection);
            }
        } catch (SQLException ex) {
            // Log any SQL exceptions
            Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteProcedure(String procedureKey) {
        try (Connection connection = PooledConnectionService.getConnection()) {
            // Establish the database connection

            // Build the SQL query to delete the procedure
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.DELETE.toSQL())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_PROCEDURES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceProcedures1.PROCEDUREKEY.toString()).append("=?");

            String query = queryBuilder.toString();

            // Prepare the SQL statement
            try (PreparedStatement st = connection.prepareStatement(query)) {

                // Set the value for the query (procedure key)
                st.setString(1, procedureKey); // Identify the record to delete by its key

                st.executeUpdate();
                PooledConnectionService.commitTransaction(connection);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                PooledConnectionService.rollbackTransaction(connection);
            }
        } catch (SQLException ex) {
            // Log any SQL exceptions
            Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getShortDescriptionByKey(String procedureKey) {
        String shortDescription = null;

        try (Connection conexion = PooledConnectionService.getConnection()) {

            // Create the SQL query to retrieve the extended text for the procedure key
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder
                    .append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceProcedures1.SHORDESCRIPTION.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_PROCEDURES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceProcedures1.PROCEDUREKEY.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {

                // Set the procedure key to the query parameter
                st.setString(1, procedureKey);

                // Execute the query and obtain the results
                try (ResultSet resultSet = st.executeQuery()) {

                    // If the procedure is found, retrieve the extended text
                    if (resultSet.next()) {
                        shortDescription = resultSet.getString(1);
                    }
                }
            }
        } catch (SQLException ex) {
            // Log any SQL exceptions
            Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the extended text or null if not found
        return shortDescription;
    }
    public static String getProcedureTextByKey(String procedureKey) {
        String extendedText = null;

        try (Connection conexion = PooledConnectionService.getConnection()) {
            // Establish the connection to the database

            // Create the SQL query to retrieve the extended text for the procedure key
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder
                    .append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceProcedures1.PROCEDURETEXT.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_PROCEDURES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceProcedures1.PROCEDUREKEY.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {

                // Set the procedure key to the query parameter
                st.setString(1, procedureKey);

                // Execute the query and obtain the results
                try (ResultSet resultSet = st.executeQuery()) {

                    // If the procedure is found, retrieve the extended text
                    if (resultSet.next()) {
                        extendedText = resultSet.getString(1);
                    }
                }
            }
        } catch (SQLException ex) {
            // Log any SQL exceptions
            Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the extended text or null if not found
        return extendedText;
    }

    public static boolean keyExists(String keyProcedure) {
        try (Connection conexion = PooledConnectionService.getConnection()) {
            // Establish the database connection

            // Build the SQL query to check if the procedure key exists
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceProcedures1.PROCEDUREKEY.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_PROCEDURES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceProcedures1.PROCEDUREKEY.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, keyProcedure);

                // Execute the query and check if the procedure key exists
                try (ResultSet rs = st.executeQuery()) {
                    return rs.next(); // Return true if the record exists, otherwise false
                }
            }
        } catch (SQLException ex) {
            // Log any SQL exceptions
            Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false; // Return false if an exception occurred
    }

    private static boolean canPerformAction(String user, String action) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            // Build the SQL query based on the action (CREATE, UPDATE, DELETE)
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(action) // Pass the action: CREATE, UPDATE or DELETE
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_PROCEDURES_AUTHORIZATIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceProceduresAurotizations.USER.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, user);

                // Execute the query and retrieve the result
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return rs.getBoolean(1); // Return the boolean value (authorization)
                    }
                }
            }
        } catch (SQLException ex) {
            // Log any SQL exceptions
            Logger.getLogger(MaintProcedures.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false; // Return false if no authorization found or if an exception occurred
    }
    public static boolean canCreate(String user) {
        // Check if the user is authorized to create a procedure
        return canPerformAction(user, MaintenanceProceduresAurotizations.CREATING.toString());
    }
    public static boolean canUpdate(String user) {
        // Check if the user is authorized to update a procedure
        return canPerformAction(user, MaintenanceProceduresAurotizations.UPDATING.toString());
    }
    public static boolean canDelete(String user) {
        // Check if the user is authorized to delete a procedure
        return canPerformAction(user, MaintenanceProceduresAurotizations.DELETING.toString());
    }
    public static boolean canView(String user) {
        // Check if the user is authorized to delete a procedure
        return canPerformAction(user, MaintenanceProceduresAurotizations.VIEWING.toString());
    }

}
