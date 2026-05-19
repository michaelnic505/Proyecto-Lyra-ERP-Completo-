package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.CharacteristicsValues;

public class DeleteCharacteristicValuesById {

    public static void deleteValue(int id) {
        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);
            String query = SQLKeywords.DELETE.toSQL()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.CHARACTERISTICS_VALUES.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + CharacteristicsValues.ID.toString() + "=?";

            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setInt(1, id);
                st.executeUpdate();
                PooledConnectionService.commitTransaction(connection);
            } catch (SQLException ex) {
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(DeleteCharacteristicValuesById.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                PooledConnectionService.rollbackTransaction(connection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeleteCharacteristicValuesById.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
