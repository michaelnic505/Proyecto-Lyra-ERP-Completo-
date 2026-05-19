
package com.simplecore.erp.modules.system.access.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.UsersSystem;


public class User_Permissions {

    
    public static boolean verifyUser(String user){
     
        boolean isRegistered = false;
        
        
        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
        
            String consulta = SQLKeywords.SELECT.toSQL()
                    + UsersSystem.NOMBRE_USUARIO.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.USERS_SYSTEM.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + UsersSystem.NOMBRE_USUARIO.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + user
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            st = conexion.prepareStatement(consulta);
            st.executeQuery();
            
            ResultSet rs = st.getResultSet();
            
            if(rs.next()){
                isRegistered = true;
            }else{
                isRegistered = false;
            }
            
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Permissions.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return isRegistered;
    }
    
}
