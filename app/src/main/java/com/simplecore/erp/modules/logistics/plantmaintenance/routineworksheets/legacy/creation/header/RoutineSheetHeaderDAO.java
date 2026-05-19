package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.header;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.RoutineSheetsHeader;
import com.simplecore.erp.config.database.PooledConnectionService;

public class RoutineSheetHeaderDAO {

    public void insert(RoutineSheetHeader header) {
        try (Connection connection = PooledConnectionService.getConnection()) {
            
            PooledConnectionService.beginTransaction(connection);

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.INSERT.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_HEADERS.tableName())
                    .append(SentenceValues.setValues(10));

            String query = queryBuilder.toString();
            try (PreparedStatement st = connection.prepareStatement(query)) {

                // Usamos los métodos getter del objeto RoutineSheetHeader para obtener los valores
                st.setString(1, header.getRoutineSheet());
                st.setInt(2, header.getCounter());
                st.setString(3, header.getPositionName());
                st.setString(4, header.getPlanningGroup());
                st.setString(5, header.getOperationType());
                st.setBoolean(6, header.isStatus());
                st.setInt(7, header.getOperatingContext());
                st.setString(8, header.getMaintenanceStrategy());
                st.setInt(9, header.getUsage());
                st.setDate(10, header.getScheduledDay());

                st.executeUpdate();
                
                PooledConnectionService.commitTransaction(connection);
                
            }catch(SQLException ex){
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(RoutineSheetHeaderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                PooledConnectionService.rollbackTransaction(connection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetHeaderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getRoutineCounter(String routineSheet, boolean status) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(RoutineSheetsHeader.COUNTER.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.ROUTINE_SHEET_HEADERS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(DatabaseTables.RoutineSheetsHeader.ROUTINESHEET.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(DatabaseTables.RoutineSheetsHeader.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();

            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, routineSheet);
                st.setBoolean(2, status);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) + 1;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoutineSheetHeaderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
}
