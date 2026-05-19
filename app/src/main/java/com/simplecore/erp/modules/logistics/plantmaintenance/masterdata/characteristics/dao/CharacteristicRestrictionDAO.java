package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.dao;

import com.simplecore.erp.gui.components.tables.newversions.TableForParameters;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.CharacteristicsRestrictions;
import com.simplecore.erp.config.database.PooledConnectionService;

/**
 *
 * @Michael F. Sanchez
 */
public class CharacteristicRestrictionDAO {

    public void insertRestriction(String type, String charact) {

        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);

            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.CHARACTERISTICS_RESTRICTIONS.tableName()
                    + SentenceValues.setValues(3);

            try (PreparedStatement st = connection.prepareStatement(query)) {

                st.setString(1, "0");
                st.setString(2, type);
                st.setString(3, charact);

                st.executeUpdate();

                PooledConnectionService.commitTransaction(connection);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(CharacteristicRestrictionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicRestrictionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRestriction(String characteristic, String type) {
        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);

            StringBuilder query = new StringBuilder()
                    .append(SQLKeywords.DELETE.toSQL())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.CHARACTERISTICS_RESTRICTIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(CharacteristicsRestrictions.CHARACTERISTIC.toString())
                    .append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(CharacteristicsRestrictions.TYPE.toString())
                    .append("=?");

            try (PreparedStatement st = connection.prepareStatement(query.toString())) {
                st.setString(1, characteristic);
                st.setString(2, type);
                st.executeUpdate();
                PooledConnectionService.commitTransaction(connection);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection); // Se realiza el rollback si ocurre una excepción
                Logger.getLogger(CharacteristicRestrictionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicRestrictionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getRestrictions(TableForParameters table, String characteristic) {
        StringBuilder queryBuilder = new StringBuilder()
                .append(SQLKeywords.SELECT_ALL.toSQL())
                .append(DatabaseTables.CHARACTERISTICS_RESTRICTIONS.tableName())
                .append(SQLKeywords.WHERE.toSQL())
                .append(CharacteristicsRestrictions.CHARACTERISTIC.toString())
                .append("=?");

        try (Connection conexion = PooledConnectionService.getConnection(); PreparedStatement st = conexion.prepareStatement(queryBuilder.toString())) {

            st.setString(1, characteristic);

            try (ResultSet rs = st.executeQuery()) {
                int fila = 0;
                while (rs.next()) {
                    table.getModel().setValueAt(rs.getString(1), fila, 1);
                    fila++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicRestrictionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean exists(String charact, String restCode) {

        try (Connection conexion = PooledConnectionService.getConnection()) {
            String query = new StringBuilder()
                    .append(SQLKeywords.SELECT.toSQL())
                    .append("COUNT(*)") // Usamos COUNT(*) para contar las filas
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.CHARACTERISTICS_RESTRICTIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(CharacteristicsRestrictions.CHARACTERISTIC.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(CharacteristicsRestrictions.TYPE.toString()).append("=?")
                    .toString();

            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, charact);
                st.setString(2, restCode);

                try (ResultSet rs = st.executeQuery()) {
                    return rs.next() && rs.getInt(1) > 0; // Si COUNT(*) > 0, significa que existe
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicRestrictionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Default return false if there is an exception
    }

    public static List<Integer> getIdRestrictions(String charact) {
        List<Integer> id = new ArrayList<>();

        StringBuilder query = new StringBuilder()
                .append(SQLKeywords.SELECT.toSQL())
                .append(CharacteristicsRestrictions.ID.toString())
                .append(SQLKeywords.FROM.toSQL())
                .append(DatabaseTables.CHARACTERISTICS_RESTRICTIONS.tableName())
                .append(SQLKeywords.WHERE.toSQL())
                .append(CharacteristicsRestrictions.CHARACTERISTIC.toString())
                .append("=?");

        try (Connection conexion = PooledConnectionService.getConnection()) {
            try (PreparedStatement st = conexion.prepareStatement(query.toString())) {
                st.setString(1, charact);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        id.add(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicRestrictionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public static void deleteById(int id) {

        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);

            String query = SQLKeywords.DELETE.toSQL()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.CHARACTERISTICS_RESTRICTIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + CharacteristicsRestrictions.ID.toString() + "=?";

            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setInt(1, id);
                st.executeUpdate();
                PooledConnectionService.commitTransaction(connection);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(CharacteristicRestrictionDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                PooledConnectionService.rollbackTransaction(connection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicRestrictionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
