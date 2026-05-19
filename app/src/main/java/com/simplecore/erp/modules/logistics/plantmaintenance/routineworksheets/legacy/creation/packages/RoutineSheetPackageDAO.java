package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.packages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.RoutineSheetPackages;
import com.simplecore.erp.config.database.PooledConnectionService;

/**
 *
 * @Michael F. Sanchez
 */
public class RoutineSheetPackageDAO {

// Method to insert a new package into the database
    public void insert(RoutineSheetPackage routineSheetPackage) {
                
        try (Connection connection = PooledConnectionService.getConnection()) {

            PooledConnectionService.beginTransaction(connection);
            
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.INSERT.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_PACKAGES.tableName())
                    .append(SentenceValues.setValues(5)); // Because there are 5 columns

            String query = queryBuilder.toString();

            try (PreparedStatement st = connection.prepareStatement(query)) {
                // Using the getter methods of the RoutineSheetPackage object to get the values
                st.setString(1, routineSheetPackage.getRoutineSheet());
                st.setInt(2, routineSheetPackage.getCounter());
                st.setString(3, routineSheetPackage.getOperation());
                st.setString(4, routineSheetPackage.getPackageCode());
                st.setString(5, routineSheetPackage.getMaintenanceStrategy());

                st.executeUpdate();
                PooledConnectionService.commitTransaction(connection);

            }catch(SQLException ex){
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(RoutineSheetPackageDAO.class.getName()).log(Level.SEVERE, null,ex);
            }finally{
                PooledConnectionService.rollbackTransaction(connection);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetPackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Method to delete a package from the database
    public void delete(String routineSheet, int counter, String operation, String packageName) {
        
        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.DELETE.toSQL())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_PACKAGES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(RoutineSheetPackages.ROUTINESHEET.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetPackages.COUNTER.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetPackages.OPERATION.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetPackages.PACKAGE.name()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = connection.prepareStatement(query)) {

                // Assign values to the corresponding columns
                st.setString(1, routineSheet);
                st.setInt(2, counter);
                st.setString(3, operation);
                st.setString(4, packageName); // Assign the value for PACKAGE

                st.executeUpdate();
                PooledConnectionService.commitTransaction(connection);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(RoutineSheetPackageDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                PooledConnectionService.rollbackTransaction(connection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetPackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Method to check if a package exists in the database
    public boolean packageExists(String routineSheet, int counter, String operation, String packageName) {

        try (Connection connection = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append("COUNT(*)")
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_PACKAGES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(RoutineSheetPackages.ROUTINESHEET.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetPackages.COUNTER.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetPackages.OPERATION.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetPackages.PACKAGE.name()).append("=?");

            String query = queryBuilder.toString();

            try (PreparedStatement st = connection.prepareStatement(query)) {
                // Assign values to the corresponding columns
                st.setString(1, routineSheet);
                st.setInt(2, counter);
                st.setString(3, operation);
                st.setString(4, packageName); // Assign value to PACKAGE

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0; // Returns true if COUNT(*) > 0
                    }else{
                        return false;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoutineSheetPackageDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetPackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // If there's an error or no match is found
    }

    public static Set<String> getPackages(String routineSheet, int counter, String operation) {
        Set<String> packages = new HashSet<>();
        try (Connection conexion = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(RoutineSheetPackages.PACKAGE.name())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_PACKAGES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(RoutineSheetPackages.ROUTINESHEET.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetPackages.COUNTER.name()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(RoutineSheetPackages.OPERATION.name()).append("=?");

            String query = queryBuilder.toString();

            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, routineSheet);  // Establecer parámetro para filtro de rutina
                st.setInt(2, counter);  // Establecer parámetro para filtro de counter
                st.setString(3, operation);

                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        packages.add(rs.getString(1));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RoutineSheetPackageDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetPackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return packages;
    }

}
