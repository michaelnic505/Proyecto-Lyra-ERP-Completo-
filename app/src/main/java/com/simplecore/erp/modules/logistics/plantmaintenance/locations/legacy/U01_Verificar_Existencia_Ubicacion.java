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

public class U01_Verificar_Existencia_Ubicacion {

    public String getDescripcionUbicacion() {
        return descripcionUbicacion;
    }

    public void setDescripcionUbicacion(String descripcionUbicacion) {
        this.descripcionUbicacion = descripcionUbicacion;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }

    private String codigoUbicacion;
    private String descripcionUbicacion;
    private boolean existe;

    public void verificar() {

        try {

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT.toSQL()
                    + Ubications.ID_UBICACION.toString()
                    + Ubications.DENOMINACION_UBICACION.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Ubications.ID_UBICACION.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + codigoUbicacion
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            ResultSet Datos = pSt.getResultSet();
            
            while(Datos.next()){
                if(codigoUbicacion.equals(Datos.getString(1))){
                    setCodigoUbicacion(Datos.getString(1));
                    setDescripcionUbicacion(Datos.getString(2));
                    existe = true;
                }else{
                    existe = false;
                }
            }
            
            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(U01_Verificar_Existencia_Ubicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
