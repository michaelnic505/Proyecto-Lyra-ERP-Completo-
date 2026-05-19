package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.WorkOrder_Fields.MaterialsWorkOrder_Fields;

public class Delete_Material_Work_Order {

    
    public void deleteMaterial(String numeroRegistro) {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String delete = SQLKeywords.DELETE.toSQL()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.MATERIALS_WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + MaterialsWorkOrder_Fields.REGISTRATION_NUMBER.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + numeroRegistro
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            st = conexion.prepareStatement(delete);
            st.executeUpdate();
            
            st.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Delete_Material_Work_Order.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
        
    }

}
