package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.gui.components.tables.newversions.TableForParameters;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.CharacteristicsValues;

public class CharacteristicsValuesView {

    public void getValues(TableForParameters table, String caracteristica) {
        try (Connection conexion = PooledConnectionService.getConnection()) {
            StringBuilder stringBuilder = new StringBuilder()
                    .append(SQLKeywords.SELECT_ALL.toSQL())
                    .append(DatabaseTables.CHARACTERISTICS_VALUES.tableName())
                    .append(SQLKeywords.WHERE.toSQL())
                    .append(CharacteristicsValues.CHARACTERISTIC.toString()).append("=?");

            try (PreparedStatement st = conexion.prepareStatement(stringBuilder.toString())) {
                st.setString(1, caracteristica);
                st.executeQuery();
                try (ResultSet rs = st.getResultSet()) {

                    int fila = 0;
                    while (rs.next()) {

                        table.getModel().setValueAt(rs.getString(CharacteristicsValues.CHARVALUE.toString()), fila, 1);
                        table.getModel().setValueAt(rs.getString(CharacteristicsValues.DESCRIPTION.toString()), fila, 2);

                        fila = fila + 1;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharacteristicsValues.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
