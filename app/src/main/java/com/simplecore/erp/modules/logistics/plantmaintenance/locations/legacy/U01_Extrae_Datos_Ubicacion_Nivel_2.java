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

public class U01_Extrae_Datos_Ubicacion_Nivel_2 {


    public String getArea() {
        return area;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public String getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getSociedad() {
        return sociedad;
    }

    public String getDescripcionSociedad() {
        return descripcionSociedad;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    public void setDescripcionSociedad(String descripcionSociedad) {
        this.descripcionSociedad = descripcionSociedad;
    }

    private String idUbicacion;
    private String sociedad;
    private String descripcionSociedad;
    private String area;
    private String descripcionArea;
    

    
    public void extraerSociedad() {

        try {
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                        + Ubications.SOCIEDAD.toString()
                        + Ubications.DESCRIPCION_SOCIEDAD.toString()
                        + Ubications.AREA.toString()
                        + Ubications.DESCRIPCION_AREA.toString()
                        + SQLKeywords.FROM.toSQL()
                        + DatabaseTables.LOCATIONS.tableName()
                        + SQLKeywords.WHERE.toSQL()
                        + Ubications.ID_UBICACION.toString()
                        + SQLKeywords.EQUALS.toSQL()
                        + SQLKeywords.SINGLE_QUOTE.toSQL()
                        + idUbicacion
                        + SQLKeywords.SINGLE_QUOTE.toSQL()
                     ;
                    
            
            st = conexion.prepareStatement(query);
            st.executeQuery();
            
            ResultSet data = st.getResultSet();
            
            
            while(data.next()){
                
                setSociedad(data.getString(1));
                setDescripcionSociedad(data.getString(2));
                setArea(data.getString(3));
                setDescripcionArea(data.getString(4));
                
            }
            
            
            st.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(U01_Extrae_Datos_Ubicacion_Nivel_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
