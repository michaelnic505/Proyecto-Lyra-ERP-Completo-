package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.packages;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MaintenanceStrategiesPackages;
import com.simplecore.erp.config.database.PooledConnectionService;

/**
 *
 * @author user
 */
public class StrategyPackages {

    public static ArrayList<String> getPackageList(String strategyCode) {
        ArrayList<String> packages = new ArrayList<>();

        try {
            // Establish the connection to the database
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            // Create the SQL query to retrieve the extended text for the procedure key
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder
                    .append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceStrategiesPackages.SHORTCYCLETEXT.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES_PACKAGES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesPackages.STRATEGYCODE.toString()).append("=?")
                    .append(SQLKeywords.ORDER_BY.toSQL())
                    .append(MaintenanceStrategiesPackages.PACKAGENUMBER.toString())
                    .append(" ASC ")
                    
                    ;

            String query = queryBuilder.toString();
            st = conexion.prepareStatement(query);
            st.setString(1, strategyCode);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                packages.add(rs.getString(1));
            }

            return packages;


        } catch (SQLException ex) {
            Logger.getLogger(StrategyPackages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
