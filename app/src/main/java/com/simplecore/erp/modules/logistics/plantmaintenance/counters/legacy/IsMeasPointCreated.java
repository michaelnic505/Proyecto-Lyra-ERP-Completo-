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
import com.simplecore.erp.config.database.DatabaseTables.MeasuringPoints;

public class IsMeasPointCreated {

    public static boolean measPointExists(String meas) {

        boolean exist = false;

        try {

            List<String> fieldList = new ArrayList();

            fieldList.add(MeasuringPoints.MEASURE_POINT.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(fieldList)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MEASURING_POINTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MeasuringPoints.MEASURE_POINT.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + meas
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            st = conexion.prepareStatement(query);
            st.executeQuery();

            ResultSet rs = st.getResultSet();

            if (rs.next()) {
                exist = true;
            }

            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(IsMeasPointCreated.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exist;
    }

}
