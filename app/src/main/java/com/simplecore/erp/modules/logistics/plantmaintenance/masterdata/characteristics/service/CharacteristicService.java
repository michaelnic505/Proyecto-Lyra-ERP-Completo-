package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.service;

import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.dao.CharacteristicDAO;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.dao.CharacteristicDAOImpl;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.model.Characteristic;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;

/**
 * Service class responsible for handling operations related to Characteristics.
 * This class serves as an intermediary between the DAO layer and the application.
 * It provides methods for inserting, updating, deleting, and retrieving characteristics.
 * Connection management is also handled here, ensuring the connection is closed after each operation.
 * 
 * @see CharacteristicDAO
 * @see CharacteristicDAOImpl
 */
public class CharacteristicService {

    private Connection connection;
    private CharacteristicDAO characteristicDAO;

    /**
     * Default constructor. Establishes a connection to the database and initializes the DAO.
     */
    public CharacteristicService() {
        this.connection = PooledConnectionService.getConnection();
        this.characteristicDAO = new CharacteristicDAOImpl(connection);
    }

    /**
     * Closes the database connection.
     * This method ensures that the connection is properly closed after all operations.
     */
    public void closeService() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close(); // Close the connection when all operations are done
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inserts a new characteristic into the database.
     * 
     * @param characteristic The characteristic to be inserted.
     */
    public void insertCharacteristic(Characteristic characteristic) {
        characteristicDAO.insert(characteristic); // Insert characteristic via DAO
    }

    /**
     * Updates an existing characteristic in the database.
     * 
     * @param characteristicCode The unique code of the characteristic to be updated.
     * @param characteristic The updated characteristic data.
     */
    public void updateCharacteristic(String characteristicCode, Characteristic characteristic) {
        characteristicDAO.update(characteristicCode, characteristic); // Update characteristic via DAO
    }

    /**
     * Deletes a characteristic from the database.
     * 
     * @param characteristicCode The unique code of the characteristic to be deleted.
     */
    public void deleteCharacteristic(String characteristicCode) {
        characteristicDAO.delete(characteristicCode); // Delete characteristic via DAO
    }

    /**
     * Retrieves characteristic data based on its unique code.
     * 
     * @param characteristicCode The unique code of the characteristic to be retrieved.
     * @return The characteristic associated with the given code.
     */
    public Characteristic getCharacteristicData(String characteristicCode) {
        return characteristicDAO.getCharacteristicData(characteristicCode); // Get characteristic data via DAO
    }

    /**
     * Checks if a characteristic exists in the database.
     * 
     * @param characteristicCode The unique code of the characteristic to check.
     * @return True if the characteristic exists, false otherwise.
     */
    public boolean doesCharacteristicExist(String characteristicCode) {
        return characteristicDAO.exists(characteristicCode); // Check existence via DAO
    }

    /**
     * Loads characteristic data into the provided table.
     * 
     * @param table The table where the data will be loaded.
     */
    public void loadDataInTable(DefaultTableModel table) {
        characteristicDAO.loadDataInTable(table); // Load data into the table via DAO
    }
}
