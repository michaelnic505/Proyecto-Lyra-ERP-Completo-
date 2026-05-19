package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.dao;

import com.simplecore.erp.gui.components.tables.newversions.TableForParameters;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.CharacteristicsDescriptions;
import com.simplecore.erp.config.database.PooledConnectionService;

public class CharacteristicDescriptionDAO {

    public void insert(String characteristic, String language, String description) {

        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);

            String query = new StringBuilder()
                    .append(SQLKeywords.INSERT.toSQL())
                    .append(DatabaseTables.CHARACTERISTICS_DESCRIPTIONS.tableName())
                    .append(SentenceValues.setValues(4))
                    .toString();

            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, "0");
                st.setString(2, language);
                st.setString(3, description);
                st.setString(4, characteristic);
                st.executeUpdate();

                PooledConnectionService.commitTransaction(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
                PooledConnectionService.rollbackTransaction(connection);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(String characteristic, String language, String description) {

        try (Connection connection = PooledConnectionService.getConnection()) {

            PooledConnectionService.beginTransaction(connection);

            String query = new StringBuilder()
                    .append(SQLKeywords.UPDATE.toSQL())
                    .append(DatabaseTables.CHARACTERISTICS_DESCRIPTIONS.tableName())
                    .append(SQLKeywords.SET.toSQL())
                    .append(CharacteristicsDescriptions.DESCRIPTION.toString()).append("=?")
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(CharacteristicsDescriptions.CHARACTERISTIC.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(CharacteristicsDescriptions.LANGUAGE.toString()).append("=?")
                    .toString();

            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, description);
                st.setString(2, characteristic);
                st.setString(3, language);
                st.executeUpdate();

                PooledConnectionService.commitTransaction(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
                PooledConnectionService.rollbackTransaction(connection);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String characteristic, String language) {

        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);

            String query = new StringBuilder()
                    .append(SQLKeywords.DELETE.toSQL())
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.CHARACTERISTICS_DESCRIPTIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(CharacteristicsDescriptions.CHARACTERISTIC.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(CharacteristicsDescriptions.LANGUAGE.toString()).append("=?")
                    .toString();

            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, characteristic);
                st.setString(2, language);
                st.executeUpdate();

                PooledConnectionService.commitTransaction(connection);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean exists(String lang, String charact) {
        try (Connection conexion = PooledConnectionService.getConnection()) {
            String query = new StringBuilder()
                    .append(SQLKeywords.SELECT.toSQL())
                    .append("COUNT(*)")
                    .append(SQLKeywords.FROM.toSQL())
                    .append(DatabaseTables.CHARACTERISTICS_DESCRIPTIONS.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(CharacteristicsDescriptions.LANGUAGE.toString()).append("=?")
                    .append(SQLKeywords.AND.toSQL())
                    .append(CharacteristicsDescriptions.CHARACTERISTIC.toString()).append("=?")
                    .toString();

            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, lang);
                st.setString(2, charact);

                try (ResultSet rs = st.executeQuery()) {
                    return rs.next() && rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<Integer> getIdDescriptions(String charact) {
        List<Integer> ids = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder()
                .append(SQLKeywords.SELECT.toSQL())
                .append(CharacteristicsDescriptions.ID.toString())
                .append(SQLKeywords.FROM.toSQL())
                .append(DatabaseTables.CHARACTERISTICS_DESCRIPTIONS.tableName())
                .append(SQLKeywords.WHERE.toSQL())
                .append(CharacteristicsDescriptions.CHARACTERISTIC.toString()).append("=?");

        try (Connection conexion = PooledConnectionService.getConnection();
                PreparedStatement st = conexion.prepareStatement(queryBuilder.toString())) {

            st.setString(1, charact);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }

    public static void delete(int id) {
        StringBuilder queryBuilder = new StringBuilder()
                .append(SQLKeywords.DELETE.toSQL())
                .append(SQLKeywords.FROM.toSQL())
                .append(DatabaseTables.CHARACTERISTICS_DESCRIPTIONS.tableName())
                .append(SQLKeywords.WHERE.toSQL())
                .append(CharacteristicsDescriptions.ID.toString()).append("=?");

        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);

            try (PreparedStatement st = connection.prepareStatement(queryBuilder.toString())) {
                st.setInt(1, id);
                st.executeUpdate();

                PooledConnectionService.commitTransaction(connection);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDescriptions(TableForParameters table, String caracteristica) {

        String query = new StringBuilder()
                .append(SQLKeywords.SELECT_ALL.toSQL())
                .append(DatabaseTables.CHARACTERISTICS_DESCRIPTIONS.tableName())
                .append(SQLKeywords.WHERE.toSQL())
                .append(CharacteristicsDescriptions.CHARACTERISTIC.toString()).append("=?")
                .toString();

        // Manejo individual de recursos con try-with-resources
        try (Connection conexion = PooledConnectionService.getConnection()) {

            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, caracteristica);

                try (ResultSet rs = st.executeQuery()) {
                    int fila = 0;
                    while (rs.next()) {
                        table.getModel().setValueAt(rs.getString(CharacteristicsDescriptions.LANGUAGE.toString()), fila, 1);
                        table.getModel().setValueAt(rs.getString(CharacteristicsDescriptions.DESCRIPTION.toString()), fila, 2);
                        fila++;
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicDescriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
