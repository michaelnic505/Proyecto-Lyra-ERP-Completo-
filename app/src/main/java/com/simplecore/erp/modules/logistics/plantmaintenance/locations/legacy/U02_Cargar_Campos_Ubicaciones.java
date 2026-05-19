
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


public class U02_Cargar_Campos_Ubicaciones {

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getIdUbicacion() {
        return idUbicacion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public boolean isMontaje() {
        return montaje;
    }

    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public void setMontaje(boolean montaje) {
        this.montaje = montaje;
    }

    private String idUbicacion;
    private int nivel;
    private boolean estatus;
    private boolean montaje;
    

    public void cargar() {

        try {

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT.toSQL()
                    + Ubications.NIVEL.toString()
                    + Ubications.MONTAJE_PERMITIDO.toString()
                    + Ubications.ESTATUS.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Ubications.ID_UBICACION.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + idUbicacion
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            ResultSet Datos = pSt.getResultSet();

            while (Datos.next()) {
                
                setNivel(Datos.getInt(1));
                setMontaje(Datos.getBoolean(2));
                setEstatus(Datos.getBoolean(3));
                
            }
            
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(U02_Cargar_Campos_Ubicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
