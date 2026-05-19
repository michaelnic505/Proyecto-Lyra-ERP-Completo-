
package com.simplecore.erp.modules.system.access.legacy;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;



public class AU2_Extraer_Columnas_Roles {
    
    
    public void extraer_Columnas_Roles(ArrayList<String> array){
        
        Connection conexion = PooledConnectionService.getConnection();
        PreparedStatement pSt = null;

        String query = SQLKeywords.SELECT_ALL.toSQL()                                                
                        + DatabaseTables.BUSINESS_TRANSACTIONS.tableName();

   
        try {
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet Data = pSt.getResultSet();
            ResultSetMetaData MetaData = Data.getMetaData();
          
            int CANTIDAD_COLUMNAS = MetaData.getColumnCount();
            
            for (int i = 0; i < CANTIDAD_COLUMNAS - 3; i++) {
                array.add(MetaData.getColumnName(i + 4));
            }  
            
            pSt.close();
            
           
        } catch (SQLException ex) {
            Logger.getLogger(AU2_Extraer_Columnas_Roles.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
}
