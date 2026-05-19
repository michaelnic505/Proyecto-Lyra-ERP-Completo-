package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Statements;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.Equipments;

public class Extraer_Equipo {

    public String getEquipo() {
        return equipo;
    }

    public String getDenominacion() {
        return denominacion;
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

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
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

    private String equipo;
    private String denominacion;
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
    
    
    

    public void getData(String equipo) {

        try {
            this.setEquipo(equipo);
            
            List<String> lista = new ArrayList();
            lista.add(Equipments.EQUIPMENT_ID.toString());
            lista.add(Equipments.NAME.toString());
            lista.add(Equipments.LOCATION.toString());
            lista.add(Equipments.LOCATION_NAME.toString());
            lista.add(Equipments.SOCIETY.toString());
            lista.add(Equipments.SOCIETY_NAME.toString());
            lista.add(Equipments.COST_CENTER.toString());
            lista.add(Equipments.COST_CENTER_NAME.toString());
            lista.add(Equipments.EMPLAZEMENT_CENTER.toString());
            lista.add(Equipments.EMPLAZEMENT_CENTER_NAME.toString());
            lista.add(Equipments.AREA_ID.toString());
            lista.add(Equipments.AREA_NAME.toString());
            lista.add(Equipments.G_PLANIFICACION.toString());
            lista.add(Equipments.DESCRIPCION_G_PLANIF.toString());
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;
            
            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(lista)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.EQUIPMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Equipments.EQUIPMENT_ID.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + equipo;
            
            
            pst = conexion.prepareStatement(query);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();

            if (rs.next()) {

                setDenominacion(rs.getString(Equipments.NAME.toString()));
                setUbicacion(rs.getString(Equipments.LOCATION.toString()));
                setDescripcionUbicacion(rs.getString(Equipments.LOCATION_NAME.toString()));
                setSociedad(rs.getString(Equipments.SOCIETY.toString()));
                setDenominacionSociedad(rs.getString(Equipments.SOCIETY_NAME.toString()));
                setCentroCostos(rs.getString(Equipments.COST_CENTER.toString()));
                setDenominacionCentroCostos(rs.getString(Equipments.COST_CENTER_NAME.toString()));
                setEmplazamiento(rs.getString(Equipments.EMPLAZEMENT_CENTER.toString()));
                setDenominacionEmplazamiento(rs.getString(Equipments.EMPLAZEMENT_CENTER_NAME.toString()));
                setArea(rs.getString(Equipments.AREA_ID.toString()));
                setDenominacionArea(rs.getString(Equipments.AREA_NAME.toString()));
                setGrupoPlanif(rs.getString(Equipments.G_PLANIFICACION.toString()));
                setDescripcionGrupoPlanif(rs.getString(Equipments.DESCRIPCION_G_PLANIF.toString()));

            }
            
            pst.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Extraer_Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public static boolean equipmentExists(String equipo) {

        boolean exists = false;

        try {

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pst = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + Equipments.EQUIPMENT_ID.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.EQUIPMENTS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + Equipments.EQUIPMENT_ID.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + equipo;

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

}
