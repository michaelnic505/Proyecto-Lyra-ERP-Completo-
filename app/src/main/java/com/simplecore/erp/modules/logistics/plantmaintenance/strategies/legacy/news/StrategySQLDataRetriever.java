package com.simplecore.erp.modules.logistics.plantmaintenance.strategies.legacy.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.UnitsOfMeasurement;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MaintenanceStrategiesHeaders;
import com.simplecore.erp.config.database.DatabaseTables.MaintenanceStrategiesPackages;

public class StrategySQLDataRetriever {

    private String strategyCode;
    private String strategyDescription;
    private String strategyUnit;
    private int apertureHorizon;
    private int delayFactorDelayedConclusion;
    private int toleranceOnLateCompletion;
    private int delayFactorEarlyConclusion;
    private int toleranceOnEarlyCompletion;

    public void getHeaderData(String strategyCode) {

        try {
            String[] columns = {
                MaintenanceStrategiesHeaders.STRATEGYCODE.toString(),
                MaintenanceStrategiesHeaders.STRATEGYDESCRIPTION.toString(),
                MaintenanceStrategiesHeaders.STRATEGYUNIT.toString(),
                MaintenanceStrategiesHeaders.APERTUREHORIZON.toString(),
                MaintenanceStrategiesHeaders.DELAYFACTORDELAYEDCONCLUSION.toString(),
                MaintenanceStrategiesHeaders.TOLERANCEONLATECOMPLETION.toString(),
                MaintenanceStrategiesHeaders.DELAYFACTOREARLYCONCLUSION.toString(),
                MaintenanceStrategiesHeaders.TOLERANCEONEARLYCOMPLETION.toString()
            };

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(String.join(", ", columns))
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesHeaders.STRATEGYCODE).append("=?");

            String query = queryBuilder.toString();

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = conexion.prepareStatement(query);

            st.setString(1, strategyCode);
            st.executeQuery();

            ResultSet rs = st.getResultSet();

            while (rs.next()) {

                setStrategyCode(rs.getString(MaintenanceStrategiesHeaders.STRATEGYCODE.toString()));
                setStrategyDescription(rs.getString(MaintenanceStrategiesHeaders.STRATEGYDESCRIPTION.toString()));
                setStrategyUnit(rs.getString(MaintenanceStrategiesHeaders.STRATEGYUNIT.toString()));
                setApertureHorizon(rs.getInt(MaintenanceStrategiesHeaders.APERTUREHORIZON.toString()));
                setDelayFactorDelayedConclusion(rs.getInt(MaintenanceStrategiesHeaders.DELAYFACTORDELAYEDCONCLUSION.toString()));
                setToleranceOnLateCompletion(rs.getInt(MaintenanceStrategiesHeaders.TOLERANCEONLATECOMPLETION.toString()));
                setDelayFactorEarlyConclusion(rs.getInt(MaintenanceStrategiesHeaders.DELAYFACTOREARLYCONCLUSION.toString()));
                setToleranceOnEarlyCompletion(rs.getInt(MaintenanceStrategiesHeaders.TOLERANCEONEARLYCOMPLETION.toString()));

            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(StrategySQLDataRetriever.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int packageNumber;
    private int cycleDuration;
    private String measUnit;
    private String maintenanceCycleText;
    private String shortCycleText;
    private int hierarchy;
    private String shortHierarchyText;
    private int offset;

    public void loadPackagesData(DynamicTableModel model, String strategyCode) {

        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT ")
                    .append("PACKAGENUMBER, ")
                    .append("CYCLEDURATION, ")
                    .append("MEASUNIT, ")
                    .append("MAINTENANCECYCLETEXT, ")
                    .append("SHORTCYCLETEXT, ")
                    .append("HIERARCHY, ")
                    .append("SHORTHIERARCHYTEXT, ")
                    .append("OFFSET ")
                    .append("FROM MAINTENANCE_STRATEGIES_PACKAGES ")
                    .append("WHERE STRATEGYCODE =? ");

            String query = queryBuilder.toString();

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = conexion.prepareStatement(query);

            st.setString(1, strategyCode);
            st.executeQuery();

            ResultSet rs = st.getResultSet();

            model.clearTable();

            int row = 0;
            while (rs.next()) {

                packageNumber = rs.getInt("PACKAGENUMBER");
                cycleDuration = rs.getInt("CYCLEDURATION");
                measUnit = rs.getString("MEASUNIT");
                maintenanceCycleText = rs.getString("MAINTENANCECYCLETEXT");
                shortCycleText = rs.getString("SHORTCYCLETEXT");
                hierarchy = rs.getInt("HIERARCHY");
                shortHierarchyText = rs.getString("SHORTHIERARCHYTEXT");
                offset = rs.getInt("OFFSET");

                model.setValueAt(packageNumber, row, 1);
                model.setValueAt(cycleDuration, row, 2);
                model.setValueAt(measUnit, row, 3);
                model.setValueAt(UnitsOfMeasurement.getDescriptionByCode(measUnit), row, 4);
                model.setValueAt(maintenanceCycleText, row, 5);
                model.setValueAt(shortCycleText, row, 6);
                model.setValueAt(hierarchy, row, 7);
                model.setValueAt(shortHierarchyText, row, 8);
                model.setValueAt(offset, row, 9);

                row++;

            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(StrategySQLDataRetriever.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean packageExists(String numPkg, String strategyCode) {

        try {
            String id = strategyCode + "-" + numPkg;

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceStrategiesPackages.ID.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES_PACKAGES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesPackages.ID.toString())
                    .append("=?");

            String query = queryBuilder.toString();

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = conexion.prepareStatement(query);

            st.setString(1, id);
            st.executeQuery();

            ResultSet rs = st.getResultSet();

            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(StrategySQLDataRetriever.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    
    
    
    public String getStrategyCode() {
        return strategyCode;
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

    public void setStrategyCode(String strategyCode) {
        this.strategyCode = strategyCode;
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
