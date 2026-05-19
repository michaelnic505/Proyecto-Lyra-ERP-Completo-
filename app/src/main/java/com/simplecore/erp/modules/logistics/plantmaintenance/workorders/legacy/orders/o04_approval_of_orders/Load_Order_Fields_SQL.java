package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders;

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
import com.simplecore.erp.config.database.DatabaseTables.WorkOrders;

public class Load_Order_Fields_SQL {

    public String getCodSolicitante() {
        return codSolicitante;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public String getCodResponsable() {
        return codResponsable;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setCodSolicitante(String codSolicitante) {
        this.codSolicitante = codSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public void setCodResponsable(String codResponsable) {
        this.codResponsable = codResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    private String orden;
    private String codigoEstatus;
    private String descripcionEstatus;

    private String titulo;
    private String textoExtendido;
    private String equipo;
    private String denominaconEquipo;
    private String ubicación;
    private String denominaconUbicación;
    private String centroCostos;
    private String denominacionCC;
    private String costoEstimado;
    private String tiempoEstimado;
    private String tipo;
    private String clase;
    private String prioridad;
    private String sistema;
    private String componente;
    private String sintoma;
    private String planeadoPor;
    private String codSolicitante;
    private String nombreSolicitante;
    private String codResponsable;
    private String nombreResponsable;

    public void loadOrder(String orden) {

        try {
            this.setOrden(orden);

            List<String> fieldList = new ArrayList();
            fieldList.add(WorkOrders.ORDER_NUM.toString());
            fieldList.add(WorkOrders.STATUS_CODE.toString());
            fieldList.add(WorkOrders.STATUS_DESCRIPTION.toString());

            fieldList.add(WorkOrders.ORDER_TITLE.toString());
            fieldList.add(WorkOrders.EXTENDED_DESCRIPTION.toString());
            fieldList.add(WorkOrders.EQUIPMENT_CODE.toString());
            fieldList.add(WorkOrders.EQUIPMENT_DESCRIPTION.toString());
            fieldList.add(WorkOrders.UBICATION_CODE.toString());
            fieldList.add(WorkOrders.UBICATION_DESCRIPTION.toString());
            fieldList.add(WorkOrders.COST_CENTER_CODE.toString());
            fieldList.add(WorkOrders.COST_CENTER_DESCRIPTION.toString());
            fieldList.add(WorkOrders.ESTIMATED_COST.toString());
            fieldList.add(WorkOrders.ESTIMATED_TIME.toString());
            fieldList.add(WorkOrders.TYPE_ORDER_DESCRIPTION.toString());
            fieldList.add(WorkOrders.CLASS_ORDER_DESCRIPTION.toString());
            fieldList.add(WorkOrders.CRITICALITY_DESCRIPTION.toString());
            fieldList.add(WorkOrders.SYSTEM_DESCRIPTION.toString());
            fieldList.add(WorkOrders.COMPONENT_DESCRIPTION.toString());
            fieldList.add(WorkOrders.SYMPTOM_DESCRIPTION.toString());
            fieldList.add(WorkOrders.ORDER_PLANNED_BY.toString());
            fieldList.add(WorkOrders.APPLICANT_CODE.toString());
            fieldList.add(WorkOrders.APPLICANT_DESCRIPTION.toString());
            fieldList.add(WorkOrders.RESPONSIBLE_CODE.toString());
            fieldList.add(WorkOrders.DESCRIPTION_OF_RESPONSIBLE.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            String query = SQLKeywords.SELECT.toSQL()
                    + SQL_Statements.Select(fieldList)
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + WorkOrders.ORDER_NUM.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + orden;

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
             ResultSet rs = pSt.getResultSet();
             
            if (rs.next()) {

                setCodigoEstatus(rs.getString(WorkOrders.STATUS_CODE.toString()));
                setDescripcionEstatus(rs.getString(WorkOrders.STATUS_DESCRIPTION.toString()));

                setTitulo(rs.getString(WorkOrders.ORDER_TITLE.toString()));
                setTextoExtendido(rs.getString(WorkOrders.EXTENDED_DESCRIPTION.toString()));
                setEquipo(rs.getString(WorkOrders.EQUIPMENT_CODE.toString()));
                setDenominaconEquipo(rs.getString(WorkOrders.EQUIPMENT_DESCRIPTION.toString()));
                setUbicación(rs.getString(WorkOrders.UBICATION_CODE.toString()));
                setDenominaconUbicación(rs.getString(WorkOrders.UBICATION_DESCRIPTION.toString()));
                setCentroCostos(rs.getString(WorkOrders.COST_CENTER_CODE.toString()));
                setDenominacionCC(rs.getString(WorkOrders.COST_CENTER_DESCRIPTION.toString()));
                setCostoEstimado(rs.getString(WorkOrders.ESTIMATED_COST.toString()));
                setTiempoEstimado(rs.getString(WorkOrders.ESTIMATED_TIME.toString()));
                setTipo(rs.getString(WorkOrders.TYPE_ORDER_DESCRIPTION.toString()));
                setClase(rs.getString(WorkOrders.CLASS_ORDER_DESCRIPTION.toString()));
                setPrioridad(rs.getString(WorkOrders.CRITICALITY_DESCRIPTION.toString()));
                setSistema(rs.getString(WorkOrders.SYSTEM_DESCRIPTION.toString()));
                setComponente(rs.getString(WorkOrders.COMPONENT_DESCRIPTION.toString()));
                setSintoma(rs.getString(WorkOrders.SYMPTOM_DESCRIPTION.toString()));
                setPlaneadoPor(rs.getString(WorkOrders.ORDER_PLANNED_BY.toString()));
                setCodSolicitante(rs.getString(WorkOrders.APPLICANT_CODE.toString()));
                setNombreSolicitante(rs.getString(WorkOrders.APPLICANT_DESCRIPTION.toString()));
                setCodResponsable(rs.getString(WorkOrders.RESPONSIBLE_CODE.toString()));
                setNombreResponsable(rs.getString(WorkOrders.DESCRIPTION_OF_RESPONSIBLE.toString()));
            }

            pSt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Load_Order_Fields_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getOrden() {
        return orden;
    }

    public String getCodigoEstatus() {
        return codigoEstatus;
    }

    public String getDescripcionEstatus() {
        return descripcionEstatus;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTextoExtendido() {
        return textoExtendido;
    }

    public String getEquipo() {
        return equipo;
    }

    public String getDenominaconEquipo() {
        return denominaconEquipo;
    }

    public String getUbicación() {
        return ubicación;
    }

    public String getDenominaconUbicación() {
        return denominaconUbicación;
    }

    public String getCentroCostos() {
        return centroCostos;
    }

    public String getDenominacionCC() {
        return denominacionCC;
    }

    public String getCostoEstimado() {
        return costoEstimado;
    }

    public String getTiempoEstimado() {
        return tiempoEstimado;
    }

    public String getTipo() {
        return tipo;
    }

    public String getClase() {
        return clase;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getSistema() {
        return sistema;
    }

    public String getComponente() {
        return componente;
    }

    public String getSintoma() {
        return sintoma;
    }

    public String getPlaneadoPor() {
        return planeadoPor;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public void setCodigoEstatus(String codigoEstatus) {
        this.codigoEstatus = codigoEstatus;
    }

    public void setDescripcionEstatus(String descripcionEstatus) {
        this.descripcionEstatus = descripcionEstatus;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTextoExtendido(String textoExtendido) {
        this.textoExtendido = textoExtendido;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setDenominaconEquipo(String denominaconEquipo) {
        this.denominaconEquipo = denominaconEquipo;
    }

    public void setUbicación(String ubicación) {
        this.ubicación = ubicación;
    }

    public void setDenominaconUbicación(String denominaconUbicación) {
        this.denominaconUbicación = denominaconUbicación;
    }

    public void setCentroCostos(String centroCostos) {
        this.centroCostos = centroCostos;
    }

    public void setDenominacionCC(String denominacionCC) {
        this.denominacionCC = denominacionCC;
    }

    public void setCostoEstimado(String costoEstimado) {
        this.costoEstimado = costoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public void setPlaneadoPor(String planeadoPor) {
        this.planeadoPor = planeadoPor;
    }

}
