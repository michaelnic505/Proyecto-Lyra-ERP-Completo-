package com.simplecore.erp.modules.controlling.society.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.sociedades;

/**
 *
 * @author user
 */
public class C01_Verificar_Sociedad_CO {


    private static boolean hasCO = false;
    
    public static boolean verificar() {
        
        try {
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                        + sociedades.TIPO.toString()
                        + SQLKeywords.FROM.toSQL()
                        + DatabaseTables.Empresas.tableName() 
                        + SQLKeywords.WHERE.toSQL()
                        + sociedades.ESTADO.toString()
                        + SQLKeywords.EQUALS.toSQL()
                        + SQLKeywords.SINGLE_QUOTE.toSQL()
                        + Status_Companies.A.toString()
                        + SQLKeywords.SINGLE_QUOTE.toSQL()
                        + SQLKeywords.AND.toSQL()
                        + sociedades.TIPO.toString()
                        + SQLKeywords.EQUALS.toSQL()
                        + SQLKeywords.SINGLE_QUOTE.toSQL()
                        + TypesCompanies.CO.toString()
                        + SQLKeywords.SINGLE_QUOTE.toSQL();
                    
            
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet rs = pSt.getResultSet();
            
            if(rs.next()){                
                hasCO = true;
            }else{
                hasCO = false;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(C01_Verificar_Sociedad_CO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hasCO;
    }

}
