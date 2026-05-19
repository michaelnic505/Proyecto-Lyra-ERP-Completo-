package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.PlanningGroupSql;

public class PlanningGroups {

    public static String getDescriptionByCode(String code) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(PlanningGroupSql.PGROUPDENOMINATION.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.PLANNING_GROUPS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(PlanningGroupSql.PLANNINGROUPCODE.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(PlanningGroupSql.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, code);
                st.setBoolean(2, true);

                try (ResultSet rs = st.executeQuery()) {

                    if (rs.next()) {
                        return rs.getString(1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanningGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<List<Object>> getGroups(boolean status) {

        try (Connection conexion = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(PlanningGroupSql.PLANNINGROUPCODE.toString()).append(", ")
                    .append(PlanningGroupSql.PGROUPDENOMINATION.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.PLANNING_GROUPS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(PlanningGroupSql.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setBoolean(1, status);

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
            Logger.getLogger(PlanningGroups.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
