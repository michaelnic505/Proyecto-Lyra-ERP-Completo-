package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.Ubications;

/**
 *
 * @author user
 */
public class U01_Verificar_Ubicacion_Nivel_1 {

    private static boolean existeUbicacion = false;

    public static boolean verificar() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + Ubications.NIVEL.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()       
                    + Ubications.NIVEL.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + 1;
            
            st = conexion.prepareStatement(query);
            st.executeQuery();
            
            ResultSet rs =  st.getResultSet();
            
            while(rs.next()){
                existeUbicacion = true;
            }
            
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(U01_Verificar_Ubicacion_Nivel_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return existeUbicacion;
    }

}
