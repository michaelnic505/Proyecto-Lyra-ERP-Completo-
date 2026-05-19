package com.simplecore.erp.config.date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tablecolumns.TimeZonesByUser;

public class TimeZones {

    public static String getTimeZoneByUser(String user) {

        try (Connection conexion = PooledConnectionService.getConnection()) {
            
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL()).append(" ")
                    .append(TimeZonesByUser.TIMEZONEID.name()).append(" ")
                    .append(SQLKeywords.FROM.toSQL()).append(" ")
                    .append(DatabaseTables.TIME_ZONES_BY_USERS.tableName()).append(" ")
                    .append(SQLKeywords.WHERE.toSQL()).append(" ")
                    .append(TimeZonesByUser.USERNAME.name()).append("=?");

            String query = queryBuilder.toString();

            try (PreparedStatement st = conexion.prepareStatement(query)) {

                st.setString(1, user);
                st.executeQuery();

                ResultSet rs = st.getResultSet();
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeZones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
