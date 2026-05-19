package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.CharacteristicsValues;

public class CharacteristicValuesID {

    public static ArrayList<Integer> getIdValues(String charact) {
        ArrayList<Integer> id = new ArrayList();

        try (Connection conexion = PooledConnectionService.getConnection()) {
            String query = SQLKeywords.SELECT.toSQL()
                    + CharacteristicsValues.ID.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.CHARACTERISTICS_VALUES.toString()
                    + SQLKeywords.WHERE.toSQL()
                    + CharacteristicsValues.CHARACTERISTIC.toString() + "=?";

            try (PreparedStatement st = conexion.prepareStatement(query)) {
                st.setString(1, charact);
                st.executeQuery();
                try (ResultSet rs = st.getResultSet()) {
                    while (rs.next()) {
                        id.add(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicValuesID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
