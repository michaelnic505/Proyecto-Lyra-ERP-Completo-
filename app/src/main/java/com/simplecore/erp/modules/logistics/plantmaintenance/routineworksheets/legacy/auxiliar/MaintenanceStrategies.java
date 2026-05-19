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
import com.simplecore.erp.config.database.DatabaseTables.MaintenanceStrategiesHeaders;

public class MaintenanceStrategies {

    public String[] getColumnNames(String language) {
        String[] columns = switch (language) { 
            case "es" ->
                new String[]{"CódigoEstrategia", "Descripción Estrategia", "Unidad de Estrategia", "Horizonte de Apertura", "Factor Retraso Conclusiones Retrasadas", "Tolerancia Finalización Tardía", "Factor Retraso Conclusiones Anticipadas", "Tolerancia Finalización Anticipada"};
            case "en" ->
                new String[]{"StrategyCode", "Strategy Description", "Strategy Unit", "Aperture Horizon", "Delay Factor for Delayed Conclusion", "Tolerance Late Completion", "Delay Factor for Early Conclusion", "Tolerance Early Completion"};
            case "fr" ->
                new String[]{"CodeStratégie", "Description Stratégie", "Unité Stratégie", "Horizon Ouverture", "Facteur de Retard pour Conclusion Retardée", "Tolérance Fin Tardive", "Facteur Retard Conclusion Anticipée", "Tolérance Fin Anticipée"};
            case "pt" ->
                new String[]{"CodigoEstrategia", "Descrição Estratégia", "Unidade de Estratégia", "Horizonte de Abertura", "Fator de Retardo para Conclusão Atrasada", "Tolerância Conclusão Tardia", "Fator de Retardo para Conclusão Adiantada", "Tolerância Conclusão Adiantada"};
            default ->
                new String[]{"StrategyCode", "Strategy Description", "Strategy Unit", "Aperture Horizon", "Delay Factor for Delayed Conclusion", "Tolerance Late Completion", "Delay Factor for Early Conclusion", "Tolerance Early Completion"};
        };
        return columns;
    }

    public static String getDescriptionByCode(String code) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceStrategiesHeaders.STRATEGYDESCRIPTION.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesHeaders.STRATEGYCODE.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(MaintenanceStrategiesHeaders.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, code);
                st.setBoolean(2, true);

                try (ResultSet rs = st.executeQuery()) {

                    String result = null;
                    if (rs.next()) {
                        result = rs.getString(1);
                    }
                    return result;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceStrategies.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<List<Object>> getMaintStrategies(boolean status) {

        try (Connection connection = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaintenanceStrategiesHeaders.STRATEGYCODE.toString()).append(", ")
                    .append(MaintenanceStrategiesHeaders.STRATEGYDESCRIPTION.toString()).append(", ")
                    .append(MaintenanceStrategiesHeaders.STRATEGYUNIT.toString()).append(", ")
                    .append(MaintenanceStrategiesHeaders.APERTUREHORIZON.toString()).append(", ")
                    .append(MaintenanceStrategiesHeaders.DELAYFACTORDELAYEDCONCLUSION.toString()).append(", ")
                    .append(MaintenanceStrategiesHeaders.TOLERANCEONLATECOMPLETION.toString()).append(", ")
                    .append(MaintenanceStrategiesHeaders.DELAYFACTOREARLYCONCLUSION.toString()).append(", ")
                    .append(MaintenanceStrategiesHeaders.TOLERANCEONEARLYCOMPLETION.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MAINTENANCE_STRATEGIES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaintenanceStrategiesHeaders.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = connection.prepareStatement(query)) {
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
            Logger.getLogger(MaintenanceStrategies.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
