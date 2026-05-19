
package com.simplecore.erp.modules.system.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tables.users;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;


public class AU1_Eliminar_Datos_Usuario {

  
    public JTable getJTABLE() {
        return JTABLE;
    }

 
    public void setJTABLE(JTable JTABLE) {
        this.JTABLE = JTABLE;
    }

    
    public String getLLAVE_PRIMARIA() {
        return LLAVE_PRIMARIA;
    }


    public void setLLAVE_PRIMARIA(String LLAVE_PRIMARIA) {
        this.LLAVE_PRIMARIA = LLAVE_PRIMARIA;
    }
 
    private String LLAVE_PRIMARIA;
    private JTable JTABLE;
    
    public void eliminar_Usuario(){
        
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;
        String query = SQLKeywords.DELETE.toSQL()
                + SQLKeywords.FROM.toSQL()
                + DatabaseTables.USERS_SYSTEM.tableName()
                + SQLKeywords.WHERE.toSQL()
                + users.NOMBRE_USUARIO.toString()
                + SQLKeywords.EQUALS.toSQL()
                + SQLKeywords.SINGLE_QUOTE.toSQL()
                + getLLAVE_PRIMARIA()
                + SQLKeywords.SINGLE_QUOTE.toSQL()
                ;
        
        try {
            pSt = conexion.prepareStatement(query);
            pSt.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AU1_Eliminar_Datos_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
