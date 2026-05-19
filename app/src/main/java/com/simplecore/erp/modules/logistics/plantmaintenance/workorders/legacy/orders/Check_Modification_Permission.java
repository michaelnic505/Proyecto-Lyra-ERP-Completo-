package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.PermissionsModificationOrder;

public class Check_Modification_Permission {

    public static boolean isPermitted(String user, String transaction, String action) {

        boolean isPermitted = false;
        
        
        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + action
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.PERMISSIONS_MODIFICATION_ORDER.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + PermissionsModificationOrder.USER.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + user
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + SQLKeywords.AND.toSQL()
                    + PermissionsModificationOrder.TRANSACTION.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + transaction
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            st = conexion.prepareStatement(query);
            st.executeQuery();
            
            ResultSet rs = st.getResultSet();
            
            if(rs.next()){
                isPermitted = rs.getBoolean(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Check_Modification_Permission.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return isPermitted;
    }

}
