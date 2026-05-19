
package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;

public class CreateCharacteristicValues {

    public void insertValues(String charact, String charValue, String description) {

        try (Connection connection = PooledConnectionService.getConnection()) {
            PooledConnectionService.beginTransaction(connection);
            
            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.CHARACTERISTICS_VALUES.tableName()
                    + SentenceValues.setValues(4);

            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, "0");
                st.setString(2, charact);
                st.setString(3, charValue);
                st.setString(4, description);

                st.executeUpdate();
                
                PooledConnectionService.commitTransaction(connection);

            }catch(SQLException ex){
                PooledConnectionService.rollbackTransaction(connection);
                Logger.getLogger(CreateCharacteristicValues.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                PooledConnectionService.rollbackTransaction(connection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateCharacteristicValues.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
