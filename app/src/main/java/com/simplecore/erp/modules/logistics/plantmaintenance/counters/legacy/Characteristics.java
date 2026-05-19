package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class Characteristics {

    private static int numChars;

    public static int getNumberChars(String characteristic) {

        try {
            List<String> lista = new ArrayList();
            lista.add(DatabaseTables.Characteristics.NUMBERCHARACTERS.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.CHARACTERISTICS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + DatabaseTables.Characteristics.CHARACTERISTIC.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + characteristic
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();

            if (rs.next()) {
                
                numChars = rs.getInt(1);
            }

            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Characteristics.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numChars;
    }

}
