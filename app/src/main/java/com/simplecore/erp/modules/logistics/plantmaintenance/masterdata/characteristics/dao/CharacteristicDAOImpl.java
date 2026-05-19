package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.dao;

import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.model.Characteristic;
import com.simplecore.erp.config.database.PooledConnectionService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.Characteristics;

/**
 * Implementation of the CharacteristicDAO interface that provides data access
 * methods for handling characteristics. This class communicates with the
 * database to perform CRUD operations on characteristics data.
 *
 * Author: Michael F. Sánchez
 */
public class CharacteristicDAOImpl implements CharacteristicDAO {

    private Connection connection;

    /**
     * Constructor that initializes the connection to the database.
     *
     * @param connection The database connection to be used for operations.
     */

    public CharacteristicDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts a new characteristic into the database.
     *
     * @param characteristic The characteristic object to be inserted.
     */
    @Override
    public void insert(Characteristic characteristic) {
        // SQL query to insert a new characteristic
        String query = SQLKeywords.INSERT.toSQL()
                + DatabaseTables.CHARACTERISTICS.tableName()
                + SentenceValues.setValues(17);

        // Begin transaction
        PooledConnectionService.beginTransaction(connection);

        try (PreparedStatement st = connection.prepareStatement(query)) {
            // Setting parameters for the insert statement
            st.setString(1, characteristic.getCharacteristic());
            st.setString(2, characteristic.getValidFrom());
            st.setString(3, characteristic.getCharStatus());
            st.setString(4, characteristic.getDescription());
            st.setString(5, characteristic.getCharGroup());
            st.setBoolean(6, characteristic.isSingleValue());
            st.setBoolean(7, characteristic.isMultiplesValues());
            st.setBoolean(8, characteristic.isIntervalsAllowed());
            st.setBoolean(9, characteristic.isNegativeValsAllowed());
            st.setBoolean(10, characteristic.isRestrictable());
            st.setBoolean(11, characteristic.isEntryRequired());
            st.setString(12, characteristic.getDataType());
            st.setInt(13, characteristic.getNumberCharacters());
            st.setInt(14, characteristic.getNumberDecimals());
            st.setString(15, characteristic.getUnitOfMeasure());
            st.setString(16, characteristic.getCurrencySimbols());
            st.setString(17, characteristic.getTemplate());

            // Executing the update query
            st.executeUpdate();

            // Commit the transaction if successful
            PooledConnectionService.commitTransaction(connection);
        } catch (SQLException ex) {
            // Rollback transaction in case of an error
            PooledConnectionService.rollbackTransaction(connection);
            Logger.getLogger(CharacteristicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Rollback transaction to ensure it is in a clean state
            PooledConnectionService.rollbackTransaction(connection);
        }
    }

    /**
     * Updates an existing characteristic based on its unique characteristic
     * code.
     *
     * @param characteristicCode The code of the characteristic to be updated.
     * @param characteristic The characteristic object containing updated
     * values.
     */
    @Override
    public void update(String characteristicCode, Characteristic characteristic) {
        // Begin transaction
        PooledConnectionService.beginTransaction(connection);

        // SQL query to update an existing characteristic
        StringBuilder stringBuilder = new StringBuilder()
                .append(SQLKeywords.UPDATE.toSQL())
                .append(DatabaseTables.CHARACTERISTICS.tableName())
                .append(SQLKeywords.SET.toSQL())
                .append(Characteristics.DESCRIPTION.toString()).append("=?,")
                .append(Characteristics.CHARGROUP.toString()).append("=?,")
                .append(Characteristics.STATUS.toString()).append("=?,")
                .append(Characteristics.SINGLEVALUE.toString()).append("=?,")
                .append(Characteristics.MULTIPLESVALUES.toString()).append("=?,")
                .append(Characteristics.INTERVALSALLOWED.toString()).append("=?,")
                .append(Characteristics.NEGATIVEVALSALLOWED.toString()).append("=?,")
                .append(Characteristics.RESTRICTABLE.toString()).append("=?,")
                .append(Characteristics.ENTRYREQUIRED.toString()).append("=?")
                .append(SQLKeywords.WHERE.toSQL())
                .append(Characteristics.CHARACTERISTIC.toString()).append("=?");

        String query = stringBuilder.toString();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            // Setting parameters for the update statement
            st.setString(1, characteristic.getDescription());
            st.setString(2, characteristic.getCharGroup());
            st.setString(3, characteristic.getCharStatus());
            st.setBoolean(4, characteristic.isSingleValue());
            st.setBoolean(5, characteristic.isMultiplesValues());
            st.setBoolean(6, characteristic.isIntervalsAllowed());
            st.setBoolean(7, characteristic.isNegativeValsAllowed());
            st.setBoolean(8, characteristic.isRestrictable());
            st.setBoolean(9, characteristic.isEntryRequired());
            st.setString(10, characteristicCode);

            // Executing the update query
            st.executeUpdate();

            // Commit the transaction if successful
            PooledConnectionService.commitTransaction(connection);
        } catch (SQLException ex) {
            // Rollback transaction in case of an error
            PooledConnectionService.rollbackTransaction(connection);
            Logger.getLogger(CharacteristicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deletes a characteristic from the database based on its unique
     * characteristic code.
     *
     * @param characteristic The characteristic code of the record to be
     * deleted.
     */
    @Override
    public void delete(String characteristic) {
        // Begin transaction
        PooledConnectionService.beginTransaction(connection);

        // SQL query to delete a characteristic
        String query = new StringBuilder()
                .append(SQLKeywords.DELETE.toSQL())
                .append(SQLKeywords.FROM.toSQL())
                .append(DatabaseTables.CHARACTERISTICS.tableName())
                .append(SQLKeywords.WHERE.toSQL())
                .append(Characteristics.CHARACTERISTIC.toString()).append("=?")
                .toString();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            // Setting parameter for the delete statement
            st.setString(1, characteristic);

            // Executing the delete query
            st.executeUpdate();

            // Commit the transaction if successful
            PooledConnectionService.commitTransaction(connection);
        } catch (SQLException ex) {
            // Rollback transaction in case of an error
            PooledConnectionService.rollbackTransaction(connection);
            Logger.getLogger(CharacteristicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves a characteristic from the database based on its unique
     * characteristic code.
     *
     * @param characteristicCode The code of the characteristic to be fetched.
     * @return The characteristic object containing the fetched data.
     */
    @Override
    public Characteristic getCharacteristicData(String characteristicCode) {
        Characteristic characteristic = null;

        // SQL query to fetch a characteristic by its unique code
        StringBuilder query = new StringBuilder()
                .append(SQLKeywords.SELECT.toSQL())
                .append(Characteristics.DESCRIPTION.name()).append(", ")
                .append(Characteristics.VALIDFROM.name()).append(", ")
                .append(Characteristics.CHARGROUP.name()).append(", ")
                .append(Characteristics.STATUS.name()).append(", ")
                .append(Characteristics.DATATYPE.name()).append(", ")
                .append(Characteristics.SINGLEVALUE.name()).append(", ")
                .append(Characteristics.MULTIPLESVALUES.name()).append(", ")
                .append(Characteristics.INTERVALSALLOWED.name()).append(", ")
                .append(Characteristics.NEGATIVEVALSALLOWED.name()).append(", ")
                .append(Characteristics.RESTRICTABLE.name()).append(", ")
                .append(Characteristics.ENTRYREQUIRED.name()).append(", ")
                .append(Characteristics.NUMBERCHARACTERS.name()).append(", ")
                .append(Characteristics.NUMBERDECIMALS.name()).append(", ")
                .append(Characteristics.UNITOFMEASURE.name()).append(", ")
                .append(Characteristics.CURRENCYSIMBOLS.name()).append(", ")
                .append(Characteristics.TEMPLATE.name())
                .append(SQLKeywords.FROM.toSQL())
                .append(DatabaseTables.CHARACTERISTICS.tableName())
                .append(SQLKeywords.WHERE.toSQL())
                .append(Characteristics.CHARACTERISTIC.name()).append("=?");

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setString(1, characteristicCode);

            // Executing the query and processing the result
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    characteristic = new Characteristic.Builder()
                            .description(rs.getString(1))
                            .validFrom(rs.getString(2))
                            .charGroup(rs.getString(3))
                            .status(rs.getString(4))
                            .dataType(rs.getString(5))
                            .singleValue(rs.getBoolean(6))
                            .multiplesValues(rs.getBoolean(7))
                            .intervalsAllowed(rs.getBoolean(8))
                            .negativeValsAllowed(rs.getBoolean(9))
                            .restrictable(rs.getBoolean(10))
                            .entryRequired(rs.getBoolean(11))
                            .numberCharacters(rs.getInt(12))
                            .numberDecimals(rs.getInt(13))
                            .unitOfMeasure(rs.getString(14))
                            .currencySimbols(rs.getString(15))
                            .template(rs.getString(16))
                            .build();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return characteristic;
    }

    /**
     * Checks if a characteristic exists in the database based on its unique
     * characteristic code.
     *
     * @param characteristicCode The code of the characteristic to be checked.
     * @return true if the characteristic exists, false otherwise.
     */
    @Override
    public boolean exists(String characteristicCode) {
        StringBuilder query = new StringBuilder();
        query.append(SQLKeywords.SELECT.toSQL())
                .append("COUNT(*)")
                .append(SQLKeywords.FROM.toSQL())
                .append(DatabaseTables.CHARACTERISTICS.tableName())
                .append(SQLKeywords.WHERE.toSQL())
                .append(Characteristics.CHARACTERISTIC.toString())
                .append("=?");

        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            st.setString(1, characteristicCode);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1); // Retrieves the result of COUNT(*)
                    return count > 0; // If there is at least one row, it exists
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * Loads all characteristics data into the provided table model.
     *
     * @param model The table model to load data into.
     */
    @Override
    public void loadDataInTable(DefaultTableModel model) {
// SQL query to fetch all characteristics data
        String query = new StringBuilder()
                .append(SQLKeywords.SELECT.toSQL())
                .append("*")
                .append(SQLKeywords.FROM.toSQL())
                .append(DatabaseTables.CHARACTERISTICS.tableName())
                .toString();

        try (PreparedStatement st = connection.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            // Process the result set and load data into the table model
            while (rs.next()) {
                Object[] row = {
                    rs.getString(Characteristics.CHARACTERISTIC.toString()),
                    rs.getString(Characteristics.DESCRIPTION.toString())
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
