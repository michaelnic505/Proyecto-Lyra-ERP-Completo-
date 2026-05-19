package com.simplecore.erp.server.config.dates;

import com.simplecore.erp.server.config.PooledConnectionService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.TimeZonesByUser;

public class UsersTimezones {

    public static String getTimeZoneByUser(Connection connection,String username) {
        try {
            StringBuilder query = new StringBuilder();
            query.append(Q.select(
                    DatabaseTables.TIME_ZONES_BY_USERS.tableName(),
                    TimeZonesByUser.TIMEZONEID.name()))
                    .append(Q.where(TimeZonesByUser.USERNAME.name()));
            
            PreparedStatement st = connection.prepareStatement(query.toString());
            st.setString(1, username);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersTimezones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
