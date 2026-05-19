package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.WorkOrder_Fields;

public class Verificar_Existencia_Orden_SQL {



    private String orden;
    private static boolean exists;

    public static boolean orderExists(String orden) {
        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + WorkOrder_Fields.ORDER_NUM.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + WorkOrder_Fields.ORDER_NUM.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + orden;
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet datos = pSt.getResultSet();
            
            if(datos.next()){
                exists = true;
            }else{
                exists = false;
            }
             
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Verificar_Existencia_Orden_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exists;
    }

}
