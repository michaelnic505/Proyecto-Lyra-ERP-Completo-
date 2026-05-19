package com.simplecore.erp.modules.logistics.plantmaintenance.strategies.legacy.news;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MaintenanceStrategiesPermissions;

public class MaintStratPerms {

    public static boolean canCreate(String user) {

        boolean r = false;

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            StringBuilder queryBuilder = new StringBuilder();

            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceStrategiesPermissions.CREATES.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES_PERMISSIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesPermissions.USERS.toString())
                    .append("=?");

            String query = queryBuilder.toString();
            

            st = conexion.prepareStatement(query);
            st.setString(1, user);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                r = rs.getBoolean(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaintStratPerms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public static boolean canChange(String user) {

        boolean r = false;

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            StringBuilder queryBuilder = new StringBuilder();

            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceStrategiesPermissions.CHANGES.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES_PERMISSIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesPermissions.USERS.toString())
                    .append("=?");

            String query = queryBuilder.toString();

            st = conexion.prepareStatement(query);
            st.setString(1, user);

            st.executeQuery();

            ResultSet rs = st.getResultSet();

            if (rs.next()) {
                r = rs.getBoolean(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaintStratPerms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public static boolean canView(String user) {

        boolean r = false;

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            StringBuilder queryBuilder = new StringBuilder();

            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceStrategiesPermissions.VIEWS.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES_PERMISSIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesPermissions.USERS.toString())
                    .append("=?");

            String query = queryBuilder.toString();

            st = conexion.prepareStatement(query);
            st.setString(1, user);

            st.executeQuery();

            ResultSet rs = st.getResultSet();

            if (rs.next()) {
                r = rs.getBoolean(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaintStratPerms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public static boolean canDelete(String user) {

        boolean r = false;

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            StringBuilder queryBuilder = new StringBuilder();

            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceStrategiesPermissions.DELETES.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES_PERMISSIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesPermissions.USERS.toString())
                    .append("=?");

            String query = queryBuilder.toString();

            st = conexion.prepareStatement(query);
            st.setString(1, user);

            st.executeQuery();

            ResultSet rs = st.getResultSet();

            if (rs.next()) {
                r = rs.getBoolean(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaintStratPerms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

}
