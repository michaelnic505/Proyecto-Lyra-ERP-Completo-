
package com.simplecore.erp.modules.system.access.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tables.tipos_usuario;
import com.simplecore.erp.config.database.utils.SQLKeywords;


public class AU2_Cargar_Tipos_Usuarios {


    /**
     * @return the TABLA_SQL
     */
    public String getTABLA_SQL() {
        return TABLA_SQL;
    }

    /**
     * @param TABLA_SQL the TABLA_SQL to set
     */
    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }

    private String TABLA_SQL;
    private String DESCRIPCION_TIPO_USUARIO;

    public void cargar_Tipos_Usuarios() {


        try (Connection conexion = PooledConnectionService.getConnection()) {
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT.toSQL()
                    + tipos_usuario.DESCRIPCION.toString()
                    + SQLKeywords.FROM.toSQL()
                    + getTABLA_SQL();

            try {
                pSt = conexion.prepareStatement(query);
                pSt.executeQuery();

                ResultSet Data = pSt.getResultSet();

                while (Data.next()) {

                }

            } catch (SQLException ex) {
                Logger.getLogger(AU2_Cargar_Tipos_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AU2_Cargar_Tipos_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
