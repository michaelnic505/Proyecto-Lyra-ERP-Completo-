package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.tables.Ubications;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class U03_Descripcion_Ubicacion {

    private static String DESCRIPCION_UBICACION;

    public static String extraerDescripcion(String CODIGO_UBICACION) {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT.toSQL()
                    + Ubications.DENOMINACION_UBICACION.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Ubications.ID_UBICACION.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + CODIGO_UBICACION
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet Datos = pSt.getResultSet();
            while (Datos.next()) {
                DESCRIPCION_UBICACION = Datos.getString(1);
            }

            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(U03_Descripcion_Ubicacion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DESCRIPCION_UBICACION;
    }


}
