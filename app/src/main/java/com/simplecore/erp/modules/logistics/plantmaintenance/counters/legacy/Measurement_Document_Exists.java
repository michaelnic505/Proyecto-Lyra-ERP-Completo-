package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.MeasurementDocuments;

public class Measurement_Document_Exists {

    public static boolean documentExists(String docNumber) {

        boolean exists = false;
        
        try {
            

            List<String> list = new ArrayList();
            list.add(MeasurementDocuments.MEASNUMDOC.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(list)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASUREMENT_DOCUMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasurementDocuments.MEASNUMDOC.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + docNumber
                    + SQLKeywords.AND.toSQL()
                    + MeasurementDocuments.STATUS.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + true;

            st = conexion.prepareStatement(query);
            st.executeQuery();

            ResultSet rs = st.getResultSet();

            if (rs.next()) {
                exists = true;
            } else {
                exists = false;
            }

            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Measurement_Document_Exists.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

}
