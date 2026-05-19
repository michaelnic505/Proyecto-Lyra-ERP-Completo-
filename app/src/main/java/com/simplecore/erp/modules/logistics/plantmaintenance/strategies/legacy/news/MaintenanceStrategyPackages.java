package com.simplecore.erp.modules.logistics.plantmaintenance.strategies.legacy.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MaintenanceStrategiesPackages;

public class MaintenanceStrategyPackages {

    private int packageNumber;
    private int cycleDuration;
    private String measUnit;
    private String maintenanceCycleText;
    private String shortCycleText;
    private int hierarchy;
    private String shortHierarchyText;
    private int offset;

    
    public void executePackageCreationSQL(String strategyCode) {

        try {

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.INSERT.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES_PACKAGES.tableName())
                    .append(SentenceValues.setValues(10));

            String query = queryBuilder.toString();

            st = conexion.prepareStatement(query);

            st.setInt(1, getPackageNumber());
            st.setInt(2, getCycleDuration());
            st.setString(3, getMeasUnit());
            st.setString(4, getMaintenanceCycleText());
            st.setString(5, getShortCycleText());
            st.setInt(6, getHierarchy());
            st.setString(7, getShortHierarchyText());
            st.setInt(8, getOffset());
            st.setString(9, strategyCode);
            st.setString(10, strategyCode+"-"+getPackageNumber());

            st.executeUpdate();

            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceStrategyHeader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void executePackadeUpdateSQL(String strategyCode) {
        
        String id = strategyCode+"-"+getPackageNumber();
        
        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.UPDATE.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES_PACKAGES.tableName())
                    .append(SQLKeywords.SET.toSQL())
                    .append(MaintenanceStrategiesPackages.MAINTENANCECYCLETEXT.toString())
                    .append("=?")
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesPackages.ID.toString())
                    .append("=?");
            
            String query = queryBuilder.toString();
            
            st = conexion.prepareStatement(query);
            st.setString(1, getMaintenanceCycleText());
            st.setString(2, id);
            
            st.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceStrategyPackages.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }


    public int getPackageNumber() {
        return packageNumber;
    }

    public int getCycleDuration() {
        return cycleDuration;
    }

    public String getMeasUnit() {
        return measUnit;
    }

    public String getMaintenanceCycleText() {
        return maintenanceCycleText;
    }

    public String getShortCycleText() {
        return shortCycleText;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public String getShortHierarchyText() {
        return shortHierarchyText;
    }

    public int getOffset() {
        return offset;
    }

    public void setPackageNumber(int packageNumber) {
        this.packageNumber = packageNumber;
    }

    public void setCycleDuration(int cycleDuration) {
        this.cycleDuration = cycleDuration;
    }

    public void setMeasUnit(String measUnit) {
        this.measUnit = measUnit;
    }

    public void setMaintenanceCycleText(String maintenanceCycleText) {
        this.maintenanceCycleText = maintenanceCycleText;
    }

    public void setShortCycleText(String shortCycleText) {
        this.shortCycleText = shortCycleText;
    }

    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }

    public void setShortHierarchyText(String shortHierarchyText) {
        this.shortHierarchyText = shortHierarchyText;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    

}
