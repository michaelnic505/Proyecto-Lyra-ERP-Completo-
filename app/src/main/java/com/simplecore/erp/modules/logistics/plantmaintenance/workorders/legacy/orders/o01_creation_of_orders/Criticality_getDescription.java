package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.OrderCriticality;

public class Criticality_getDescription {

    public static String getDescripcion(String codigo) {

        String descripcion = null;
        
        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + OrderCriticality.DESCRIPCION_CRITICIDAD.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.ORDER_CRITICALITY.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + OrderCriticality.ID_CRITICIDAD.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + codigo
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
      
            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();

            if (rs.next()) {
                descripcion = rs.getString(1);
            }
            
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Criticality_getDescription.class.getName()).log(Level.SEVERE, null, ex);
        }


        return descripcion;
    }

}
