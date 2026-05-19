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

public class DeleteCharacteristicValues {

    public void deleteValue(String caract, String values) {

        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);
            
            String query = SQLKeywords.DELETE.toSQL()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.CHARACTERISTICS_VALUES.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + CharacteristicsValues.CHARACTERISTIC.toString() + "=?"
                    + SQLKeywords.AND.toSQL()
                    + CharacteristicsValues.CHARVALUE.toString() + "=?";

            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, caract);
                st.setString(2, values);
                st.executeUpdate();
                PooledConnectionService.commitTransaction(connection);
            }catch(SQLException ex){
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(DeleteCharacteristicValues.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                PooledConnectionService.rollbackTransaction(connection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeleteCharacteristicValues.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
