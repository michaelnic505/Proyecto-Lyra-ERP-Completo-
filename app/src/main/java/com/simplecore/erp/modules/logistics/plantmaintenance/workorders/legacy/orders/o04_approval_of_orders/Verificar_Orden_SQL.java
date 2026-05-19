package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.WorkOrders;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.StatusOrder;

public class Verificar_Orden_SQL {

    public static boolean isOrderUnderApproval(String order) {

        boolean isUnderApproval = false;

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
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + SQLKeywords.AND.toSQL()
                    + WorkOrders.STATUS_CODE.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + StatusOrder.ST3_ORDER_UNDER_APPROVAL.getStatusCode()
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet set = pSt.getResultSet();

            if (set.next()) {
                isUnderApproval = true;
            } else {
                isUnderApproval = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Verificar_Orden_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isUnderApproval;
    }

}
