
package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.activity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.OperationTypeMaintenanceOrder;

public class OperationTypesMaint {

    public static class OperationDetails {
        public String description;
        public double unitCost;
        public String unitOfMeasure;
        public String currency;

        public OperationDetails(String description, double unitCost, String unitOfMeasure, String currency) {
            this.description = description;
            this.unitCost = unitCost;
            this.unitOfMeasure = unitOfMeasure;
            this.currency = currency;
        }
    }

    public static OperationDetails getOperationDetails(String opCode) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(OperationTypeMaintenanceOrder.ACTIVITYMAINTENANCEDESCRIPTION.toString()).append(", ")
                    .append(OperationTypeMaintenanceOrder.UNITCOST.toString()).append(", ")
                    .append(OperationTypeMaintenanceOrder.UNITOFMEASURE.toString()).append(", ")
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
                        return new OperationDetails(
                                rs.getString(1),
                                rs.getDouble(2),
                                rs.getString(3),
                                rs.getString(4)
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperationTypesMaint.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean operationExists(String opCode) {
        return getOperationDetails(opCode) != null;
    }
}
