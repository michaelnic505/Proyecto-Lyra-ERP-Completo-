package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values;

import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.CharacteristicsValues;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.utils.SQLKeywords;

public class ValueCreated {

    public static boolean valueExists(String charact, String value) {
        boolean exist = false;
        try (Connection conexion = PooledConnectionService.getConnection()) {
            String query = SQLKeywords.SELECT.toSQL()
                    + CharacteristicsValues.CHARACTERISTIC.toString() + ", "
                    + CharacteristicsValues.CHARVALUE.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.CHARACTERISTICS_VALUES.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + CharacteristicsValues.CHARACTERISTIC.toString()+"=?"
                    + SQLKeywords.AND.toSQL()
                    + CharacteristicsValues.CHARVALUE.toString()+"=?";

            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, charact);
                st.setString(1, value);
                st.executeQuery();

                try (ResultSet rs = st.getResultSet()) {
                    if (rs.next()) {
                        exist = true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValueCreated.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exist;
    }

}
