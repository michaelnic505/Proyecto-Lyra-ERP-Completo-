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
import com.simplecore.erp.config.database.DatabaseTables.MaintenanceStrategiesHeaders;

public class MaintenanceStrategyHeader {
    
    private String strategyCode;
    private String strategyDescription;       // varchar(45)
    private String strategyUnit;              // varchar(5)
    private int apertureHorizon;              // int
    private int delayFactorDelayedConclusion; // int
    private int toleranceOnLateCompletion;    // int
    private int delayFactorEarlyConclusion;   // int
    private int toleranceOnEarlyCompletion;   // int

    
    public void executeHeaderCreationSQL() {
        
        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.INSERT.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES.tableName())
                    .append(SentenceValues.setValues(8));

            String query = queryBuilder.toString();
            
            st = conexion.prepareStatement(query);
            st.setString(1, getStrategyCode());
            st.setString(2, getStrategyDescription());
            st.setString(3, getStrategyUnit());
            st.setInt(4, getApertureHorizon());
            st.setInt(5, getDelayFactorDelayedConclusion());
            st.setInt(6, getToleranceOnLateCompletion());
            st.setInt(7, getDelayFactorEarlyConclusion());
            st.setInt(8, getToleranceOnEarlyCompletion());
            
            st.executeUpdate();

            
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceStrategyHeader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void executeHeaderUpdateSQL() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.UPDATE.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES.tableName())
                    .append(SQLKeywords.SET.toSQL())
                    .append(MaintenanceStrategiesHeaders.STRATEGYDESCRIPTION.toString()).append("=?,")
                    .append(MaintenanceStrategiesHeaders.APERTUREHORIZON.toString()).append("=?,")
                    .append(MaintenanceStrategiesHeaders.DELAYFACTORDELAYEDCONCLUSION.toString()).append("=?,")
                    .append(MaintenanceStrategiesHeaders.TOLERANCEONLATECOMPLETION.toString()).append("=?,")
                    .append(MaintenanceStrategiesHeaders.DELAYFACTOREARLYCONCLUSION.toString()).append("=?,")
                    .append(MaintenanceStrategiesHeaders.TOLERANCEONEARLYCOMPLETION.toString()).append("=?")
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesHeaders.STRATEGYCODE.toString()).append("=?");
            
            String query = queryBuilder.toString();
            
            st = conexion.prepareStatement(query);
            st.setString(1, getStrategyDescription());
            st.setInt(2, getApertureHorizon());
            st.setInt(3, getDelayFactorDelayedConclusion());
            st.setInt(4, getToleranceOnLateCompletion());
            st.setInt(5, getDelayFactorEarlyConclusion());
            st.setInt(6, getToleranceOnEarlyCompletion());
            st.setString(7, getStrategyCode());
            
            st.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceStrategyHeader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public String getStrategyCode() {
        return strategyCode;
    }

    public void setStrategyCode(String strategyCode) {
        this.strategyCode = strategyCode;
    }

    public String getStrategyDescription() {
        return strategyDescription;
    }

    public String getStrategyUnit() {
        return strategyUnit;
    }

    public int getApertureHorizon() {
        return apertureHorizon;
    }

    public int getDelayFactorDelayedConclusion() {
        return delayFactorDelayedConclusion;
    }

    public int getToleranceOnLateCompletion() {
        return toleranceOnLateCompletion;
    }

    public int getDelayFactorEarlyConclusion() {
        return delayFactorEarlyConclusion;
    }

    public int getToleranceOnEarlyCompletion() {
        return toleranceOnEarlyCompletion;
    }

    public void setStrategyDescription(String strategyDescription) {
        this.strategyDescription = strategyDescription;
    }

    public void setStrategyUnit(String strategyUnit) {
        this.strategyUnit = strategyUnit;
    }

    public void setApertureHorizon(int apertureHorizon) {
        this.apertureHorizon = apertureHorizon;
    }

    public void setDelayFactorDelayedConclusion(int delayFactorDelayedConclusion) {
        this.delayFactorDelayedConclusion = delayFactorDelayedConclusion;
    }

    public void setToleranceOnLateCompletion(int toleranceOnLateCompletion) {
        this.toleranceOnLateCompletion = toleranceOnLateCompletion;
    }

    public void setDelayFactorEarlyConclusion(int delayFactorEarlyConclusion) {
        this.delayFactorEarlyConclusion = delayFactorEarlyConclusion;
    }

    public void setToleranceOnEarlyCompletion(int toleranceOnEarlyCompletion) {
        this.toleranceOnEarlyCompletion = toleranceOnEarlyCompletion;
    }


}
