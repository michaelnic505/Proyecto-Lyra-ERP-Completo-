package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.materials;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.RoutineSheetMaterials;
import com.simplecore.erp.config.database.PooledConnectionService;

/**
 *
 * @Michael F. Sanchez
 */
public class RoutineSheetMaterialDAO {

    public void insertMaterials(List<RoutineSheetMaterial> materials){

        try (Connection connection = PooledConnectionService.getConnection()) {

            // Begin the transaction
            PooledConnectionService.beginTransaction(connection);

            // Building the query with StringBuilder
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.INSERT.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_MATERIALS.tableName())
                    .append(SentenceValues.setValues(11));

            String query = queryBuilder.toString();

            try (PreparedStatement st = connection.prepareStatement(query)) {
                for (RoutineSheetMaterial detail : materials) {
                    st.setString(1, detail.getRoutineSheet());
                    st.setInt(2, detail.getCounter());
                    st.setString(3, detail.getPosition());
                    st.setString(4, detail.getMaterial());
                    st.setString(5, detail.getComponentDenomination());
                    st.setDouble(6, detail.getQuantity());
                    st.setString(7, detail.getUnitOfMeasure());
                    st.setDouble(8, detail.getUnitCost());
                    st.setString(9, detail.getWarehouse());
                    st.setDouble(10, detail.getTotalAmount());
                    st.setString(11, detail.getClassification());

                    st.addBatch();  // Add to batch
                }

                st.executeBatch();  // Execute batch
                PooledConnectionService.commitTransaction(connection);  // Commit the transaction
            } catch (SQLException ex) {
                // Rollback the transaction if an error occurs
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(RoutineSheetMaterialDAO.class.getName()).log(Level.SEVERE,
                        "Error inserting materials", ex);
                throw ex;  // Rethrow the exception
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetMaterialDAO.class.getName()).log(Level.SEVERE,
                    "Error inserting materials", ex);
        }
    }

    public void update(String routineSheet, int counter, String operation, List<RoutineSheetMaterial> materials) {

        try (Connection connection = PooledConnectionService.getConnection()) {

            PooledConnectionService.beginTransaction(connection);

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.UPDATE.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_MATERIALS.tableName())
                    .append(SQLKeywords.SET.toSQL())
                    .append(RoutineSheetMaterials.QUANTITY.name()).append("=?").append(",")
                    .append(RoutineSheetMaterials.WAREHOUSE.name()).append("=?")
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(RoutineSheetMaterials.ROUTINESHEET.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetMaterials.COUNTER.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetMaterials.OPERATION.name()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = connection.prepareStatement(query)) {
                for (RoutineSheetMaterial detail : materials) {
                    st.setDouble(1, detail.getQuantity());
                    st.setString(2, detail.getWarehouse());
                    st.setString(3, routineSheet);
                    st.setInt(4, counter);
                    st.setString(5, operation);

                    st.addBatch();
                }
                st.executeBatch();  // Execute batch
                PooledConnectionService.commitTransaction(connection);  // Commit the transaction
            } catch (SQLException ex) {
                // Rollback the transaction if an error occurs
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(RoutineSheetMaterialDAO.class.getName()).log(Level.SEVERE,
                        "Error updating materials for routine sheet: " + routineSheet + ", counter: " + counter, ex);
                throw ex;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetMaterialDAO.class.getName()).log(Level.SEVERE,
                    "Error updating materials", ex);
        }
    }

    public void delete(String routineSheet, int counter, String operation, List<String> position) {

        try (Connection connection = PooledConnectionService.getConnection()) {

            PooledConnectionService.beginTransaction(connection);

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.DELETE.toSQL())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_MATERIALS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(RoutineSheetMaterials.ROUTINESHEET.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetMaterials.COUNTER.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetMaterials.OPERATION.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetMaterials.POSITION.name()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = connection.prepareStatement(query)) {
                for (String posit : position) {
                    st.setString(1, routineSheet);
                    st.setInt(2, counter);
                    st.setString(3, operation);
                    st.setString(4, posit);
                    st.addBatch();
                }

                st.executeBatch();
                PooledConnectionService.commitTransaction(connection);

            } catch (SQLException ex) {
                // Rollback the transaction if an error occurs
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(RoutineSheetMaterialDAO.class.getName()).log(Level.SEVERE,
                        "Error deleting materials for routine sheet: " + routineSheet, ex);
                throw ex;
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetMaterialDAO.class.getName()).log(Level.SEVERE,
                    "Error deleting materials", ex);
        }

    }

    public static boolean positionExists(String routineSheet, int counter, String operation, String position) {

        try (Connection connection = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append("COUNT(*)")
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_MATERIALS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(RoutineSheetMaterials.ROUTINESHEET.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetMaterials.COUNTER.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetMaterials.OPERATION.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetMaterials.POSITION.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = connection.prepareStatement(query)) {

                st.setString(1, routineSheet);
                st.setInt(2, counter);
                st.setString(3, operation);
                st.setString(4, position);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0; // Return true if COUNT(*) > 0
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(RoutineSheetMaterialDAO.class.getName()).log(Level.SEVERE,
                        "Error checking position existence for routine sheet: " + routineSheet, ex);
                throw ex;
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetMaterialDAO.class.getName()).log(Level.SEVERE,
                    "Error checking position existence", ex);
        }
        return false;
    }

    public static Set<String> getMaterialPositionsByOperation(String routineSheet, int counter, String operation) {

        Set<String> positions = new HashSet<>();

        try (Connection connection = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(RoutineSheetMaterials.POSITION.name())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_MATERIALS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(RoutineSheetMaterials.ROUTINESHEET.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetMaterials.COUNTER.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetMaterials.OPERATION.name()).append("=?");

            String query = queryBuilder.toString();

            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, routineSheet);  // Set parameter for routine sheet
                st.setInt(2, counter);  // Set parameter for counter
                st.setString(3, operation);  // Set parameter for operation

                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        positions.add(rs.getString(1));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoutineSheetMaterialDAO.class.getName()).log(Level.SEVERE,
                        "Error fetching material positions for routine sheet: " + routineSheet, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetMaterialDAO.class.getName()).log(Level.SEVERE,
                    "Error fetching material positions", ex);
        }
        return positions;
    }
}
