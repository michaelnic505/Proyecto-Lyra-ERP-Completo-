package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.Tipo_Operacion_Orden_Mtto;

/**
 *
 * @author user
 */
public class Combo_Tipo_Operacion_SQL {

    public static void cargaCombo(JComboBox combo) {


        try {
            
            Connection conexion = PooledConnectionService.getConnection();

            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT.toSQL()
                    + Tipo_Operacion_Orden_Mtto.ID_ACTIVIDAD_MTTO.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.OPERATION_TYPE_MAINTENANCE_ORDER.tableName();

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet rs = pSt.getResultSet();
            
            while(rs.next()){                
                combo.addItem(rs.getString(1));                
            }
            
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Combo_Tipo_Operacion_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
