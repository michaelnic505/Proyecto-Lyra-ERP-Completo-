package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.Locations;

public class Extraer_Ubicacion {

    private String ubicacion;
    private String descripcionUbicacion;
    private String sociedad;
    private String denominacionSociedad;
    private String centroCostos;
    private String denominacionCentroCostos;
    private String emplazamiento;
    private String denominacionEmplazamiento;
    private String area;
    private String denominacionArea;
    private String grupoPlanif;
    private String descripcionGrupoPlanif;

    public void getData(String ubicacion) {

        try {
            this.setUbicacion(ubicacion);
            
            List<String> lista = new ArrayList();
            lista.add(Locations.ID_UBICACION.toString());
            lista.add(Locations.DENOMINACION_UBICACION.toString());
            lista.add(Locations.SOCIEDAD.toString());
            lista.add(Locations.DESCRIPCION_SOCIEDAD.toString());
            lista.add(Locations.CENTRO_COSTOS.toString());
            lista.add(Locations.DESCRIPCION_CC.toString());
            lista.add(Locations.EMPLAZAMIENTO.toString());
            lista.add(Locations.DESCRIPCION_EMP.toString());
            lista.add(Locations.AREA.toString());
            lista.add(Locations.DESCRIPCION_AREA.toString());
            lista.add(Locations.GRUPO_PLANIF.toString());
            lista.add(Locations.DESCRIPCION_G_PLANIF.toString());
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Locations.ID_UBICACION.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + ubicacion
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            
            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();

            if (rs.next()) {

                setDescripcionUbicacion(rs.getString(Locations.DENOMINACION_UBICACION.toString()));
                setSociedad(rs.getString(Locations.SOCIEDAD.toString()));
                setDenominacionSociedad(rs.getString(Locations.DESCRIPCION_SOCIEDAD.toString()));
                setCentroCostos(rs.getString(Locations.CENTRO_COSTOS.toString()));
                setDenominacionCentroCostos(rs.getString(Locations.DESCRIPCION_CC.toString()));
                setEmplazamiento(rs.getString(Locations.EMPLAZAMIENTO.toString()));
                setDenominacionEmplazamiento(rs.getString(Locations.DESCRIPCION_EMP.toString()));
                setArea(rs.getString(Locations.AREA.toString()));
                setDenominacionArea(rs.getString(Locations.DESCRIPCION_AREA.toString()));
                setGrupoPlanif(rs.getString(Locations.GRUPO_PLANIF.toString()));
                setDescripcionGrupoPlanif(rs.getString(Locations.DESCRIPCION_G_PLANIF.toString()));

            }
            
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Extraer_Ubicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcionUbicacion() {
        return descripcionUbicacion;
    }

    public String getSociedad() {
        return sociedad;
    }

    public String getDenominacionSociedad() {
        return denominacionSociedad;
    }

    public String getCentroCostos() {
        return centroCostos;
    }

    public String getDenominacionCentroCostos() {
        return denominacionCentroCostos;
    }

    public String getEmplazamiento() {
        return emplazamiento;
    }

    public String getDenominacionEmplazamiento() {
        return denominacionEmplazamiento;
    }

    public String getArea() {
        return area;
    }

    public String getDenominacionArea() {
        return denominacionArea;
    }

    public String getGrupoPlanif() {
        return grupoPlanif;
    }

    public String getDescripcionGrupoPlanif() {
        return descripcionGrupoPlanif;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setDescripcionUbicacion(String descripcionUbicacion) {
        this.descripcionUbicacion = descripcionUbicacion;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    public void setDenominacionSociedad(String denominacionSociedad) {
        this.denominacionSociedad = denominacionSociedad;
    }

    public void setCentroCostos(String centroCostos) {
        this.centroCostos = centroCostos;
    }

    public void setDenominacionCentroCostos(String denominacionCentroCostos) {
        this.denominacionCentroCostos = denominacionCentroCostos;
    }

    public void setEmplazamiento(String emplazamiento) {
        this.emplazamiento = emplazamiento;
    }

    public void setDenominacionEmplazamiento(String denominacionEmplazamiento) {
        this.denominacionEmplazamiento = denominacionEmplazamiento;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDenominacionArea(String denominacionArea) {
        this.denominacionArea = denominacionArea;
    }

    public void setGrupoPlanif(String grupoPlanif) {
        this.grupoPlanif = grupoPlanif;
    }

    public void setDescripcionGrupoPlanif(String descripcionGrupoPlanif) {
        this.descripcionGrupoPlanif = descripcionGrupoPlanif;
    }

    public static boolean locationExists(String ubicacion) {

        boolean exists = false;

        try {

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + Locations.ID_UBICACION.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Locations.ID_UBICACION.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + ubicacion
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();
            if (rs.next()) {
                exists = true;
            }

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Extraer_Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    public static int locationLevel(String ubicacion) {

        int level = 0;

        try {

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + Locations.NIVEL.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Locations.ID_UBICACION.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + ubicacion
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();
            if (rs.next()) {
                level = rs.getInt(1);
            }

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Extraer_Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return level;
    }

}
