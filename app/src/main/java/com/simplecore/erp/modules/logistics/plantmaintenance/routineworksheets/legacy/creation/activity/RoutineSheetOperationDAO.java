package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.RoutineSheetOperations;
import com.simplecore.erp.config.database.PooledConnectionService;

public class RoutineSheetOperationDAO {

    // Insert a new routine sheet operation record into the database
    public void insert(List<RoutineSheetOperation> details) {

        try (Connection connection = PooledConnectionService.getConnection()) {

            // Begin the transaction
            PooledConnectionService.beginTransaction(connection);

            // Build the SQL query for insertion
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.INSERT.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_OPERATIONS.tableName())
                    .append(SentenceValues.setValues(15));

            String query = queryBuilder.toString();
            try (PreparedStatement st = connection.prepareStatement(query)) {

                for (RoutineSheetOperation detail : details) {
                    st.setString(1, detail.getRoutineSheet());
                    st.setInt(2, detail.getCounter());
                    st.setString(3, detail.getOperation());
                    st.setString(4, detail.getProcedure());
                    st.setString(5, detail.getOperationDescription());
                    st.setDouble(6, detail.getWork());
                    st.setDouble(7, detail.getQuantity());
                    st.setDouble(8, detail.getDuration());
                    st.setString(9, detail.getOperationTypeCode());
                    st.setString(10, detail.getOperationType());
                    st.setDouble(11, detail.getUnitCost());
                    st.setString(12, detail.getUnitOfMeasure());
                    st.setDouble(13, detail.getTotalAmount());
                    st.setString(14, detail.getOrderRequest());
                    st.setString(15, detail.getCurrency());
                
                    st.addBatch();
                }
                
                st.executeBatch();
                PooledConnectionService.commitTransaction(connection);  // Commit the transaction
            }catch(SQLException ex){
                PooledConnectionService.rollbackTransaction(connection);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetOperationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Update an existing routine sheet operation record in the database
    public void update(List<RoutineSheetOperation> details) {
        // Start a new transaction

        try (Connection connection = PooledConnectionService.getConnection()) {

            // Build the SQL query for updating a routine sheet operation record
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("UPDATE ")
                    .append(DatabaseTables.ROUTINE_SHEET_OPERATIONS.tableName())
                    .append(" SET ")
                    .append(RoutineSheetOperations.ROUTINESHEET.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.COUNTER.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.OPERATION.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.PROCEDURES.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.OPERATIONDESCRIPTION.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.WORK.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.QUANTITY.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.DURATION.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.OPERATIONTYPECODE.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.OPERATIONTYPE.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.UNITCOST.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.UNITOFMEASURE.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.TOTALAMOUNT.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.ORDERREQUEST.name()).append(" = ?, ")
                    .append(RoutineSheetOperations.CURRENCY.name()).append(" = ? ")
                    .append(SQLKeywords.WHERE.toString())
                    .append(RoutineSheetOperations.ROUTINESHEET.name()).append(" = ? AND ")
                    .append(RoutineSheetOperations.COUNTER.name()).append(" = ?");

            String query = queryBuilder.toString();

            try (PreparedStatement st = connection.prepareStatement(query)) {
                for (RoutineSheetOperation detail : details) {
                    // Use getter methods from the RoutineSheetOperation object to get values
                    st.setString(1, detail.getRoutineSheet());
                    st.setInt(2, detail.getCounter());
                    st.setString(3, detail.getOperation());
                    st.setString(4, detail.getProcedure());
                    st.setString(5, detail.getOperationDescription());
                    st.setDouble(6, detail.getWork());
                    st.setDouble(7, detail.getQuantity());
                    st.setDouble(8, detail.getDuration());
                    st.setString(9, detail.getOperationTypeCode());
                    st.setString(10, detail.getOperationType());
                    st.setDouble(11, detail.getUnitCost());
                    st.setString(12, detail.getUnitOfMeasure());
                    st.setDouble(13, detail.getTotalAmount());
                    st.setString(14, detail.getOrderRequest());
                    st.setString(15, detail.getCurrency());

                    // Parameters to identify the record to be updated
                    st.setString(17, detail.getRoutineSheet());
                    st.setInt(18, detail.getCounter());

                    st.addBatch();
                }

                st.executeBatch();
                // If everything went well, commit the transaction
                PooledConnectionService.commitTransaction(connection);  // Commit the changes
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);  // Rollback changes in case of error
            }

        } catch (SQLException ex) {
            // If an error occurs, perform a rollback
            Logger.getLogger(RoutineSheetOperationDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    // Delete a specific operation from the routine sheet
    public void deleteOperation(String routineSheet, int counter, List<String> operationCodes) {
        // Start a new transaction

        try (Connection connection = PooledConnectionService.getConnection()) {

            PooledConnectionService.beginTransaction(connection);  // Begin the transaction

            // Build the SQL query to delete only the specified operation
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.DELETE.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_OPERATIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(RoutineSheetOperations.ROUTINESHEET.name()).append(" = ?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetOperations.COUNTER.name()).append(" = ?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetOperations.OPERATION.name()).append(" = ?"); // Filter by operation code

            String query = queryBuilder.toString();

            try (PreparedStatement st = connection.prepareStatement(query)) {
                for (String opCode : operationCodes) {

                    // Set the parameters for the query
                    st.setString(1, routineSheet);
                    st.setInt(2, counter);
                    st.setString(3, opCode); // Set operation code parameter

                    st.addBatch();
                }
                st.executeBatch();
                // If everything went well, commit the transaction
                PooledConnectionService.commitTransaction(connection);  // Commit the changes
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);  // Rollback changes in case of error    
            }

        } catch (SQLException ex) {
            // If an error occurs, perform a rollback
            Logger.getLogger(RoutineSheetOperationDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    // Check if a specific operation exists in the routine sheet
    public static boolean operationExists(String routineSheet, int counter, String operation) {

        boolean exists = false;

        // Get the connection
        try (Connection connection = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(" COUNT(*) ")
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_OPERATIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(RoutineSheetOperations.ROUTINESHEET.toString()).append("=? ")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetOperations.COUNTER.toString()).append("=? ")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetOperations.OPERATION.toString()).append("=?");

            String query = queryBuilder.toString();

            // Use try-with-resources to handle PreparedStatement and ResultSet
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, routineSheet);
                st.setInt(2, counter);
                st.setString(3, operation);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        exists = rs.getInt(1) > 0; // Return true if COUNT(*) > 0
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoutineSheetOperationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetOperationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    // Get a set of operation codes from a specific routine sheet
    public static Set<String> getOperations(String routineSheet, int counter) {
        // Start a new transaction
        Set<String> operations = new HashSet<>();

        try (Connection connection = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(RoutineSheetOperations.OPERATION.name())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_OPERATIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(RoutineSheetOperations.ROUTINESHEET.toString()).append(" = ?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetOperations.COUNTER.toString()).append(" = ?");

            String query = queryBuilder.toString();

            // Use try-with-resources to handle PreparedStatement and ResultSet
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, routineSheet);  // Set parameter for routine sheet filter
                st.setInt(2, counter);  // Set parameter for counter filter

                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        operations.add(rs.getString(1));  // Add operation to the set
                    }
                }
                // Commit the transaction if everything is fine
            } catch (SQLException ex) {
                // If an error occurs, perform a rollback
                Logger.getLogger(RoutineSheetOperationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            // If an error occurs, perform a rollback
            Logger.getLogger(RoutineSheetOperationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return operations;  // Return the set of operations
    }

}
