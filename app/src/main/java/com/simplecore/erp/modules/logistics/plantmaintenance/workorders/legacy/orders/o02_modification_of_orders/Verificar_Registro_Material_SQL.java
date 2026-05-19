package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.WorkOrder_Fields.MaterialsWorkOrder_Fields;

public class Verificar_Registro_Material_SQL {

    private static boolean isRegistered = false;

    public static boolean isMaterialRegistered(String numeroRegistro) {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + MaterialsWorkOrder_Fields.REGISTRATION_NUMBER.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MATERIALS_WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MaterialsWorkOrder_Fields.REGISTRATION_NUMBER.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + numeroRegistro
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet set = pSt.getResultSet();
            
            if(set.next()){
                isRegistered = true;
            }else{
                isRegistered = false;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Verificar_Registro_Material_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isRegistered;
    }

}
