package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;

public class U01_Crear_Ubicacions {

    public String getGrupoPlanif() {
        return grupoPlanif;
    }

    public String getDescGrupoPlanif() {
        return descGrupoPlanif;
    }

    public void setGrupoPlanif(String grupoPlanif) {
        this.grupoPlanif = grupoPlanif;
    }

    public void setDescGrupoPlanif(String descGrupoPlanif) {
        this.descGrupoPlanif = descGrupoPlanif;
    }

    public String getIdUbicacion() {
        return idUbicacion;
    }

    public String getDescripcionUbic() {
        return descripcionUbic;
    }

    public String getIdUbicacionSup() {
        return idUbicacionSup;
    }

    public String getDescripcionUbicSup() {
        return descripcionUbicSup;
    }

    public boolean isMontajePermitido() {
        return montajePermitido;
    }

    public int getNivel() {
        return nivel;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public String getCentroCosto() {
        return centroCosto;
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

    public String getDescripcionSoc() {
        return descripcionSoc;
    }

    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public void setDescripcionUbic(String descripcionUbic) {
        this.descripcionUbic = descripcionUbic;
    }

    public void setIdUbicacionSup(String idUbicacionSup) {
        this.idUbicacionSup = idUbicacionSup;
    }

    public void setDescripcionUbicSup(String descripcionUbicSup) {
        this.descripcionUbicSup = descripcionUbicSup;
    }

    public void setMontajePermitido(boolean montajePermitido) {
        this.montajePermitido = montajePermitido;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
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

    public void setDescripcionSoc(String descripcionSoc) {
        this.descripcionSoc = descripcionSoc;
    }

    private String idUbicacion;
    private String descripcionUbic;
    private String idUbicacionSup;
    private String descripcionUbicSup;
    private boolean montajePermitido;
    private int nivel;
    private boolean estatus;
    private String tipoRegistro;
    private String centroCosto;
    private String descripcionCC;
    private String emplazamiento;
    private String descripcionEmp;
    private String area;
    private String descripcionArea;
    private String sociedad;
    private String descripcionSoc;
    private String grupoPlanif;
    private String descGrupoPlanif;

    public void crearUbicacion() {

        try {
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SentenceValues.setValues(18);
            
            st = conexion.prepareStatement(query);
            st.setString(1, getIdUbicacion());
            st.setString(2, getDescripcionUbic());
            st.setString(3, getIdUbicacionSup());
            st.setString(4, getDescripcionUbicSup());
            st.setBoolean(5, isMontajePermitido());
            st.setInt(6, getNivel());
            st.setBoolean(7, isEstatus());
            st.setString(8, getTipoRegistro());
            st.setString(9, getCentroCosto());
            st.setString(10, getDescripcionCC());
            st.setString(11, getEmplazamiento());
            st.setString(12, getDescripcionEmp());
            st.setString(13, getArea());
            st.setString(14, getDescripcionArea());
            st.setString(15, getSociedad());
            st.setString(16, getDescripcionSoc());
            st.setString(17, getGrupoPlanif());
            st.setString(18, getDescGrupoPlanif());
            
            st.executeUpdate();
            st.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(U01_Crear_Ubicacions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
