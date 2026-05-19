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
import com.simplecore.erp.config.database.DatabaseTables.OperationTypeMaintenanceOrder;

public class OperationTypesMaintenance {

    public String[] getColumnNames(String language) {
        String[] columns = switch (language) {
            case "es" ->
                new String[]{"IDActMtto", "Descripción Actividad Mantenimiento", "Costo Unitario", "Unidad Medida", "Moneda"};
            case "en" ->
                new String[]{"ActMaintID", "Activity Maintenance Description", "Unit Cost", "Unit Of Measure", "Currency"};
            case "fr" ->
                new String[]{"IDActEntretien", "Description Activité Entretien", "Coût Unitaire", "Unité De Mesure", "Devise"};
            case "pt" ->
                new String[]{"IDAtivManut", "Descrição Atividade Manutenção", "Custo Unitário", "Unidade Medida", "Moeda"};
            default ->
                new String[]{"ActMaintID", "Activity Maintenance Description", "Unit Cost", "Unit Of Measure", "Currency"};
        };
        return columns;
    }

    public static String getDescriptionByCode(String code) {
        try (Connection connection = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(OperationTypeMaintenanceOrder.ACTIVITYMAINTENANCEDESCRIPTION.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.OPERATION_TYPE_MAINTENANCE_ORDER.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(OperationTypeMaintenanceOrder.ACTIVITYMAINTENANCEID.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(OperationTypeMaintenanceOrder.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, code);
                st.setBoolean(2, true);

                ResultSet rs = st.executeQuery();
                String result = null;
                if (rs.next()) {
                    result = rs.getString(1);
                }
                return result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperationTypesMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<List<Object>> getOperationTypes(boolean status) {

        try (Connection connection = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(OperationTypeMaintenanceOrder.ACTIVITYMAINTENANCEID.toString()).append(", ")
                    .append(OperationTypeMaintenanceOrder.ACTIVITYMAINTENANCEDESCRIPTION.toString()).append(", ")
                    .append(OperationTypeMaintenanceOrder.UNITCOST.toString()).append(", ")
                    .append(OperationTypeMaintenanceOrder.UNITOFMEASURE.toString()).append(", ")
                    .append(OperationTypeMaintenanceOrder.CURRENCY.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.OPERATION_TYPE_MAINTENANCE_ORDER.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(OperationTypeMaintenanceOrder.STATUS.toString()).append("=?");

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
            Logger.getLogger(OperationTypesMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean operationExists(String opCode) {

        try (Connection connection = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(OperationTypeMaintenanceOrder.ACTIVITYMAINTENANCEID.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.OPERATION_TYPE_MAINTENANCE_ORDER.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(OperationTypeMaintenanceOrder.ACTIVITYMAINTENANCEID.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(OperationTypeMaintenanceOrder.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, opCode);
                st.setBoolean(2, true);

                try (ResultSet rs = st.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperationTypesMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static double getUnitCost(String opCode) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(OperationTypeMaintenanceOrder.UNITCOST.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.OPERATION_TYPE_MAINTENANCE_ORDER.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(OperationTypeMaintenanceOrder.ACTIVITYMAINTENANCEID.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(OperationTypeMaintenanceOrder.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, opCode);
                st.setBoolean(2, true);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble(1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperationTypesMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    public static String getUnitOfMeasure(String opCode) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(OperationTypeMaintenanceOrder.UNITOFMEASURE.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.OPERATION_TYPE_MAINTENANCE_ORDER.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(OperationTypeMaintenanceOrder.ACTIVITYMAINTENANCEID.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(OperationTypeMaintenanceOrder.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, opCode);
                st.setBoolean(2, true);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperationTypesMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String getCurrencyCode(String opCode) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(OperationTypeMaintenanceOrder.CURRENCY.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.OPERATION_TYPE_MAINTENANCE_ORDER.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(OperationTypeMaintenanceOrder.ACTIVITYMAINTENANCEID.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(OperationTypeMaintenanceOrder.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, opCode);
                st.setBoolean(2, true);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperationTypesMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
