package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.WorkOrders;

public class VerificarOrdenCorrecta_SQL {

    public static boolean isValidOrder(String order) {

        boolean isValid = false;

        try {

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + WorkOrders.ORDER_NUM.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + WorkOrders.ORDER_NUM.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + order
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet set = pSt.getResultSet();

            if (set.next()) {
                isValid = true;
            } else {
                isValid = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(VerificarOrdenCorrecta_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isValid;
    }

}
