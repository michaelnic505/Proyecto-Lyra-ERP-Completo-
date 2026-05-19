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
import com.simplecore.erp.config.database.DatabaseTables.MaterialWareHouseRegistration;

public class RegisteredWarehouseMaterials {

    public static class MaterialDetails {

        public String description;
        public double unitCost;
        public String unitOfMeasure;
        public String warehouseCode;

        public MaterialDetails(String description, double unitCost, String unitOfMeasure, String warehouseCode) {
            this.description = description;
            this.unitCost = unitCost;
            this.unitOfMeasure = unitOfMeasure;
            this.warehouseCode = warehouseCode;
        }
    }

    public static MaterialDetails getMaterialDetails(String matCode, String warehouseCode) {

        try (Connection conexion = PooledConnectionService.getConnection()) {

            // Building the SQL query (Construcción de la consulta SQL)
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaterialWareHouseRegistration.MATERIALDESCRIPTION.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.UNITPRICE.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.UNITMEASUREID.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.WAREHOUSECODE.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MATERIAL_WAREHOUSE_REGISTRATION.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaterialWareHouseRegistration.MATERIALCODE.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(MaterialWareHouseRegistration.WAREHOUSECODE.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(MaterialWareHouseRegistration.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();

            // Preparing the statement (Preparación de la declaración)
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, matCode);
                st.setString(2, warehouseCode);
                st.setBoolean(3, true);

                // Executing the query and returning the result (Ejecutando la consulta y retornando el resultado)
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return new MaterialDetails(
                                rs.getString(1),
                                rs.getDouble(2),
                                rs.getString(3),
                                rs.getString(4)
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredWarehouseMaterials.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String getDescriptionByCode(String materialCode) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            // Building the SQL query (Construcción de la consulta SQL)
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaterialWareHouseRegistration.MATERIALDESCRIPTION.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MATERIAL_WAREHOUSE_REGISTRATION.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaterialWareHouseRegistration.MATERIALCODE.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(MaterialWareHouseRegistration.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();

            // Preparing the statement (Preparación de la declaración)
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, materialCode);
                st.setBoolean(2, true);

                // Executing the query and returning the result (Ejecutando la consulta y retornando el resultado)
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredWarehouseMaterials.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<List<Object>> getMaterialsList(boolean status) {

        try (Connection conexion = PooledConnectionService.getConnection()) {

            // Building the SQL query (Construcción de la consulta SQL)
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaterialWareHouseRegistration.MATERIALCODE.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.MATERIALDESCRIPTION.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.WAREHOUSECODE.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.WAREHOUSEDESCRIPTION.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.UNITPRICE.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.UNITMEASUREID.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.SERIES.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.MODEL.toString()).append(", ")
                    .append(MaterialWareHouseRegistration.BRAND.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MATERIAL_WAREHOUSE_REGISTRATION.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaterialWareHouseRegistration.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();

            // Preparing the statement (Preparación de la declaración)
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setBoolean(1, status);

                // Executing the query and getting the result (Ejecutando la consulta y obteniendo el resultado)
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
            Logger.getLogger(RegisteredWarehouseMaterials.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean materialExists(String materialCode) {

        try (Connection conexion = PooledConnectionService.getConnection()) {

            // Building the SQL query (Construcción de la consulta SQL)
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaterialWareHouseRegistration.MATERIALCODE.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MATERIAL_WAREHOUSE_REGISTRATION.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaterialWareHouseRegistration.MATERIALCODE.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(MaterialWareHouseRegistration.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();

            // Preparing the statement (Preparación de la declaración)
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, materialCode);
                st.setBoolean(2, true);

                // Executing the query and checking if the material exists (Ejecutando la consulta y verificando si el material existe)
                try (ResultSet rs = st.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredWarehouseMaterials.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static double getUnitCost(String materialCode) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            // Building the SQL query (Construcción de la consulta SQL)
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaterialWareHouseRegistration.UNITPRICE.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MATERIAL_WAREHOUSE_REGISTRATION.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaterialWareHouseRegistration.MATERIALCODE.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(MaterialWareHouseRegistration.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();

            // Preparing the statement (Preparación de la declaración)
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, materialCode);
                st.setBoolean(2, true);

                // Executing the query and returning the result (Ejecutando la consulta y retornando el resultado)
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble(1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredWarehouseMaterials.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    public static String getUnitOfMeasure(String materialCode) {
        try (Connection conexion = PooledConnectionService.getConnection()) {

            // Building the SQL query (Construcción de la consulta SQL)
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(SQLKeywords.SELECT.toSQL())
                    .append(MaterialWareHouseRegistration.UNITMEASUREID.toString())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.MATERIAL_WAREHOUSE_REGISTRATION.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(MaterialWareHouseRegistration.MATERIALCODE.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(MaterialWareHouseRegistration.STATUS.toString()).append("=?");

            String query = queryBuilder.toString();

            // Preparing the statement (Preparación de la declaración)
            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, materialCode);
                st.setBoolean(2, true);

                // Executing the query and returning the result (Ejecutando la consulta y retornando el resultado)
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredWarehouseMaterials.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
