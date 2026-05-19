
package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.Ubications;
import static com.simplecore.erp.config.database.DatabaseTables.LOCATIONS;



public class U01_Extrae_centro_costos {

    public String getIdUbicacion() {
        return idUbicacion;
    }

    public String getCentroCostos() {
        return centroCostos;
    }

    public String getDescripcionCC() {
        return descripcionCC;
    }

    public String getEmplazamiento() {
        return emplazamiento;
    }

    public String getDescripcionEmp() {
        return descripcionEmp;
    }

    public String getArea() {
        return area;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public String getSociedad() {
        return sociedad;
    }

    public String getDescripcionSociedad() {
        return descripcionSociedad;
    }

    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public void setCentroCostos(String centroCostos) {
        this.centroCostos = centroCostos;
    }

    public void setDescripcionCC(String descripcionCC) {
        this.descripcionCC = descripcionCC;
    }

    public void setEmplazamiento(String emplazamiento) {
        this.emplazamiento = emplazamiento;
    }

    public void setDescripcionEmp(String descripcionEmp) {
        this.descripcionEmp = descripcionEmp;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    public void setDescripcionSociedad(String descripcionSociedad) {
        this.descripcionSociedad = descripcionSociedad;
    }


    private String idUbicacion;
    private String centroCostos;
    private String descripcionCC;
    private String emplazamiento;
    private String descripcionEmp;
    private String area;
    private String descripcionArea;
    private String sociedad;
    private String descripcionSociedad;
    
    public void extractCCData(){

                    
        try {
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                         + Ubications.CENTRO_COSTOS.toString()
                         + Ubications.DESCRIPCION_CC.toString()
                         + Ubications.EMPLAZAMIENTO.toString()
                         + Ubications.DESCRIPCION_EMP.toString()
                         + Ubications.AREA.toString()
                         + Ubications.DESCRIPCION_AREA.toString()
                         + Ubications.SOCIEDAD.toString()
                         + Ubications.DESCRIPCION_SOCIEDAD.toString()
                         + SQLKeywords.FROM.toSQL()
                         + DatabaseTables.LOCATIONS.tableName()
                         + SQLKeywords.WHERE.toSQL()
                         + Ubications.ID_UBICACION.toString()
                         + SQLKeywords.EQUALS.toSQL()
                         + SQLKeywords.SINGLE_QUOTE.toSQL()
                         + getIdUbicacion()
                         + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            st = conexion.prepareStatement(query);
            st.executeQuery();
            
            ResultSet data = st.getResultSet();
            
            while(data.next()){
                
                setCentroCostos(data.getString(1));
                setDescripcionCC(data.getString(2));
                setEmplazamiento(data.getString(3));
                setDescripcionEmp(data.getString(4));
                setArea(data.getString(5));
                setDescripcionArea(data.getString(6));
                setSociedad(data.getString(7));
                setDescripcionSociedad(data.getString(8));
                
                
            }
            
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(U01_Extrae_centro_costos.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }

    
}
