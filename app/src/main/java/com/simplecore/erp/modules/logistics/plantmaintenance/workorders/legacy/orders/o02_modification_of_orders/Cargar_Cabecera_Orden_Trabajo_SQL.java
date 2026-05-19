package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.WorkOrder_Fields;

public class Cargar_Cabecera_Orden_Trabajo_SQL {

    /*variables de CABECERA DE ORDEN */
    private String numeroOrden;
    private String codigoEstatus;
    private String descripcionEstatus;
    private boolean paroEquipo;
    private String tituloOrden;
    private String descripcionExtendidaOrden;

    private String codigoTipoOrden;
    private String descripcionTipoOrden;
    private String codigoClaseOrden;
    private String descripcionClaseOrden;
    private String codigoCriticidad;
    private String descripcionCriticidad;

    private String codigoSistema;
    private String descripcionSistema;
    private String codigoComponente;
    private String descripcionComponente;
    private String codigoSintoma;
    private String descripcionSintoma;

    private String codigoEquipo;
    private String descripcionEquipo;
    private String codigoUbicacion;
    private String descripcionUbicacion;

    private String fechaInicioReal;
    private String fechaFinReal;

    private String horaInicioReal;
    private String horaFinReal;
    private String totalHoraReales;

    private String fechaInicioProgramado;
    private String fechaFinProgramado;

    private String horaInicioProgramada;
    private String horaFinProgramado;
    private String totalHorasProgramado;

    private String fechaCreacionOrden;
    private String fechaAutorizacionOrden;

    private String horaCreacionOrden;
    private String horaAutorizacionOrden;
    private String totalHorasCreacionOrden;

    private String codigoGruoPlan;
    private String descripcionGrupoPlan;
    private String codigoSolicitante;
    private String descripcionSolicitante;
    private String codigoResponsable;
    private String descripcionResponsable;

    /*variables de DATOS FINANCIEROS*/
    private String codigoSociedad;
    private String descripcionSociedad;
    private String codigoArea;
    private String descripcionArea;
    private String codigoEmplazamiento;
    private String descripcionEmplazamiento;
    private String centroCostos;
    private String descripcionCentroCostos;

    private String ordenCreadaPor;
    private String ordenPlaneadaPor;
    private String ordenAprobadaPor;
    private String ordenEjecutadaPor;

    private String horasEstimadas;
    private String horasReal;
    private String costoEstimado;
    private String costoReal;


    /*variables de DATOS CONTROL*/
    private String numeroContador;
    private String valorContador;
    private String hojaRuta;
    private String numeroPlan;

    public void cargarOrden(String orden) {
        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + WorkOrder_Fields.ORDER_NUM.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + orden
                    + SQLKeywords.SINGLE_QUOTE.toSQL();

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet datos = pSt.getResultSet();

            if (datos.next()) {

                setNumeroOrden(datos.getString(WorkOrder_Fields.ORDER_NUM.toString()));
               
                setCodigoEstatus(datos.getString(WorkOrder_Fields.STATUS_CODE.toString()));
                setDescripcionEstatus(datos.getString(WorkOrder_Fields.STATUS_DESCRIPTION.toString()));
               
                setParoEquipo(datos.getBoolean(WorkOrder_Fields.STOP.toString()));
                setTituloOrden(datos.getString(WorkOrder_Fields.ORDER_TITLE.toString()));
                setDescripcionExtendidaOrden(datos.getString(WorkOrder_Fields.EXTENDED_DESCRIPTION.toString()));
               
                setCodigoTipoOrden(datos.getString(WorkOrder_Fields.TYPE_ORDER_CODE.toString()));
                setDescripcionTipoOrden(datos.getString(WorkOrder_Fields.TYPE_ORDER_DESCRIPTION.toString()));
               
                setCodigoClaseOrden(datos.getString(WorkOrder_Fields.CLASS_ORDER_CODE.toString()));
                setDescripcionClaseOrden(datos.getString(WorkOrder_Fields.CLASS_ORDER_DESCRIPTION.toString()));
               
                setCodigoCriticidad(datos.getString(WorkOrder_Fields.CRITICALITY_CODE.toString()));
                setDescripcionCriticidad(datos.getString(WorkOrder_Fields.CRITICALITY_DESCRIPTION.toString()));
                
                setCodigoSistema(datos.getString(WorkOrder_Fields.SYSTEM_CODE.toString()));
                setDescripcionSistema(datos.getString(WorkOrder_Fields.SYSTEM_DESCRIPTION.toString()));
                
                setCodigoComponente(datos.getString(WorkOrder_Fields.COMPONENT_CODE.toString()));
                setDescripcionComponente(datos.getString(WorkOrder_Fields.COMPONENT_DESCRIPTION.toString()));
               
                setCodigoSintoma(datos.getString(WorkOrder_Fields.SYMPTOM_CODE.toString()));
                setDescripcionSintoma(datos.getString(WorkOrder_Fields.SYMPTOM_DESCRIPTION.toString()));
                
                setCodigoEquipo(datos.getString(WorkOrder_Fields.EQUIPMENT_CODE.toString()));
                setDescripcionEquipo(datos.getString(WorkOrder_Fields.EQUIPMENT_DESCRIPTION.toString()));
               
                setCodigoUbicacion(datos.getString(WorkOrder_Fields.UBICATION_CODE.toString()));
                setDescripcionUbicacion(datos.getString(WorkOrder_Fields.UBICATION_DESCRIPTION.toString()));
               
                setFechaInicioReal(datos.getString(WorkOrder_Fields.ACTUAL_START_DATE.toString()));
                setFechaFinReal(datos.getString(WorkOrder_Fields.ACTUAL_END_DATE.toString()));
                setHoraInicioReal(datos.getString(WorkOrder_Fields.ACTUAL_START_TIME.toString()));
                setHoraFinReal(datos.getString(WorkOrder_Fields.ACTUAL_END_TIME.toString()));
                setTotalHoraReales(datos.getString(WorkOrder_Fields.TOTAL_REAL_TIME.toString()));
                
                setFechaInicioProgramado(datos.getString(WorkOrder_Fields.SCHEDULED_START_DATE.toString()));
                setFechaFinProgramado(datos.getString(WorkOrder_Fields.SCHEDULED_END_DATE.toString()));
                setHoraInicioProgramada(datos.getString(WorkOrder_Fields.SCHEDULED_START_TIME.toString()));
                setHoraFinProgramado(datos.getString(WorkOrder_Fields.SCHEDULED_END_TIME.toString()));
                setTotalHorasProgramado(datos.getString(WorkOrder_Fields.SCHEDULED_TOTAL_TIME.toString()));
               
                setFechaCreacionOrden(datos.getString(WorkOrder_Fields.CREATION_DATE_ORDER.toString()));
                setFechaAutorizacionOrden(datos.getString(WorkOrder_Fields.APPROVAL_DATE_ORDER.toString()));
                setHoraCreacionOrden(datos.getString(WorkOrder_Fields.CREATION_TIME_ORDER.toString()));
                setHoraAutorizacionOrden(datos.getString(WorkOrder_Fields.AUTHORIZATION_TIME_ORDER.toString()));
                setTotalHorasCreacionOrden(datos.getString(WorkOrder_Fields.TOTAL_CREATION_HOURS.toString()));
               
                setCodigoGruoPlan(datos.getString(WorkOrder_Fields.GROUP_PLANNING_CODE.toString()));
                setDescripcionGrupoPlan(datos.getString(WorkOrder_Fields.PLANNING_GROUP_DESCRIPTION.toString()));
                
                setCodigoSolicitante(datos.getString(WorkOrder_Fields.APPLICANT_CODE.toString()));
                setDescripcionSolicitante(datos.getString(WorkOrder_Fields.APPLICANT_DESCRIPTION.toString()));
               
                setCodigoResponsable(datos.getString(WorkOrder_Fields.RESPONSIBLE_CODE.toString()));
                setDescripcionResponsable(datos.getString(WorkOrder_Fields.DESCRIPTION_OF_RESPONSIBLE.toString()));
                
                setCodigoSociedad(datos.getString(WorkOrder_Fields.COMPANY_CODE.toString()));
                setDescripcionSociedad(datos.getString(WorkOrder_Fields.COMPANY_DESCRIPTION.toString()));
                
                setCodigoArea(datos.getString(WorkOrder_Fields.AREA_CODE.toString()));
                setDescripcionArea(datos.getString(WorkOrder_Fields.AREA_DESCRIPTION.toString()));
               
                setCodigoEmplazamiento(datos.getString(WorkOrder_Fields.EMPLAZEMENT_CODE.toString()));
                setDescripcionEmplazamiento(datos.getString(WorkOrder_Fields.EMPLAZEMENT_DESCRIPTION.toString()));
               
                setCentroCostos(datos.getString(WorkOrder_Fields.COST_CENTER_CODE.toString()));
                setDescripcionCentroCostos(datos.getString(WorkOrder_Fields.COST_CENTER_DESCRIPTION.toString()));
                
                setOrdenCreadaPor(datos.getString(WorkOrder_Fields.ORDER_CREATED_BY.toString()));
                setOrdenPlaneadaPor(datos.getString(WorkOrder_Fields.ORDER_PLANNED_BY.toString()));
                setOrdenAprobadaPor(datos.getString(WorkOrder_Fields.ORDER_APPROVED_BY.toString()));
                setOrdenEjecutadaPor(datos.getString(WorkOrder_Fields.ORDER_EXECUTED_BY.toString()));
                
                setHorasEstimadas(datos.getString(WorkOrder_Fields.ESTIMATED_TIME.toString()));
                setHorasReal(datos.getString(WorkOrder_Fields.REAL_TIME.toString()));
                setCostoEstimado(datos.getString(WorkOrder_Fields.ESTIMATED_COST.toString()));
                setCostoReal(datos.getString(WorkOrder_Fields.REAL_COST.toString()));
                
                setNumeroContador(datos.getString(WorkOrder_Fields.COUNTER.toString()));
                setValorContador(datos.getString(WorkOrder_Fields.COUNTER_VALUE.toString()));
                setHojaRuta(datos.getString(WorkOrder_Fields.ROAD_SHEET.toString()));
                setNumeroPlan(datos.getString(WorkOrder_Fields.PLAN.toString()));

            }
            
            pSt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Cargar_Cabecera_Orden_Trabajo_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public String getCodigoEstatus() {
        return codigoEstatus;
    }

    public String getDescripcionEstatus() {
        return descripcionEstatus;
    }

    public boolean isParoEquipo() {
        return paroEquipo;
    }

    public String getTituloOrden() {
        return tituloOrden;
    }

    public String getDescripcionExtendidaOrden() {
        return descripcionExtendidaOrden;
    }

    public String getCodigoTipoOrden() {
        return codigoTipoOrden;
    }

    public String getDescripcionTipoOrden() {
        return descripcionTipoOrden;
    }

    public String getCodigoClaseOrden() {
        return codigoClaseOrden;
    }

    public String getDescripcionClaseOrden() {
        return descripcionClaseOrden;
    }

    public String getCodigoCriticidad() {
        return codigoCriticidad;
    }

    public String getDescripcionCriticidad() {
        return descripcionCriticidad;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public String getDescripcionSistema() {
        return descripcionSistema;
    }

    public String getCodigoComponente() {
        return codigoComponente;
    }

    public String getDescripcionComponente() {
        return descripcionComponente;
    }

    public String getCodigoSintoma() {
        return codigoSintoma;
    }

    public String getDescripcionSintoma() {
        return descripcionSintoma;
    }

    public String getCodigoEquipo() {
        return codigoEquipo;
    }

    public String getDescripcionEquipo() {
        return descripcionEquipo;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public String getDescripcionUbicacion() {
        return descripcionUbicacion;
    }

    public String getFechaInicioReal() {
        return fechaInicioReal;
    }

    public String getFechaFinReal() {
        return fechaFinReal;
    }

    public String getHoraInicioReal() {
        return horaInicioReal;
    }

    public String getHoraFinReal() {
        return horaFinReal;
    }

    public String getTotalHoraReales() {
        return totalHoraReales;
    }

    public String getFechaInicioProgramado() {
        return fechaInicioProgramado;
    }

    public String getFechaFinProgramado() {
        return fechaFinProgramado;
    }

    public String getHoraInicioProgramada() {
        return horaInicioProgramada;
    }

    public String getHoraFinProgramado() {
        return horaFinProgramado;
    }

    public String getTotalHorasProgramado() {
        return totalHorasProgramado;
    }

    public String getFechaCreacionOrden() {
        return fechaCreacionOrden;
    }

    public String getFechaAutorizacionOrden() {
        return fechaAutorizacionOrden;
    }

    public String getHoraCreacionOrden() {
        return horaCreacionOrden;
    }

    public String getHoraAutorizacionOrden() {
        return horaAutorizacionOrden;
    }

    public String getTotalHorasCreacionOrden() {
        return totalHorasCreacionOrden;
    }

    public String getCodigoGruoPlan() {
        return codigoGruoPlan;
    }

    public String getDescripcionGrupoPlan() {
        return descripcionGrupoPlan;
    }

    public String getCodigoSolicitante() {
        return codigoSolicitante;
    }

    public String getDescripcionSolicitante() {
        return descripcionSolicitante;
    }

    public String getCodigoResponsable() {
        return codigoResponsable;
    }

    public String getDescripcionResponsable() {
        return descripcionResponsable;
    }

    public String getCodigoSociedad() {
        return codigoSociedad;
    }

    public String getDescripcionSociedad() {
        return descripcionSociedad;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public String getCodigoEmplazamiento() {
        return codigoEmplazamiento;
    }

    public String getDescripcionEmplazamiento() {
        return descripcionEmplazamiento;
    }

    public String getCentroCostos() {
        return centroCostos;
    }

    public String getDescripcionCentroCostos() {
        return descripcionCentroCostos;
    }

    public String getOrdenCreadaPor() {
        return ordenCreadaPor;
    }

    public String getOrdenPlaneadaPor() {
        return ordenPlaneadaPor;
    }

    public String getOrdenAprobadaPor() {
        return ordenAprobadaPor;
    }

    public String getOrdenEjecutadaPor() {
        return ordenEjecutadaPor;
    }

    public String getHorasEstimadas() {
        return horasEstimadas;
    }

    public String getHorasReal() {
        return horasReal;
    }

    public String getCostoEstimado() {
        return costoEstimado;
    }

    public String getCostoReal() {
        return costoReal;
    }

    public String getNumeroContador() {
        return numeroContador;
    }

    public String getValorContador() {
        return valorContador;
    }

    public String getHojaRuta() {
        return hojaRuta;
    }

    public String getNumeroPlan() {
        return numeroPlan;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public void setCodigoEstatus(String codigoEstatus) {
        this.codigoEstatus = codigoEstatus;
    }

    public void setDescripcionEstatus(String descripcionEstatus) {
        this.descripcionEstatus = descripcionEstatus;
    }

    public void setParoEquipo(boolean paroEquipo) {
        this.paroEquipo = paroEquipo;
    }

    public void setTituloOrden(String tituloOrden) {
        this.tituloOrden = tituloOrden;
    }

    public void setDescripcionExtendidaOrden(String descripcionExtendidaOrden) {
        this.descripcionExtendidaOrden = descripcionExtendidaOrden;
    }

    public void setCodigoTipoOrden(String codigoTipoOrden) {
        this.codigoTipoOrden = codigoTipoOrden;
    }

    public void setDescripcionTipoOrden(String descripcionTipoOrden) {
        this.descripcionTipoOrden = descripcionTipoOrden;
    }

    public void setCodigoClaseOrden(String codigoClaseOrden) {
        this.codigoClaseOrden = codigoClaseOrden;
    }

    public void setDescripcionClaseOrden(String descripcionClaseOrden) {
        this.descripcionClaseOrden = descripcionClaseOrden;
    }

    public void setCodigoCriticidad(String codigoCriticidad) {
        this.codigoCriticidad = codigoCriticidad;
    }

    public void setDescripcionCriticidad(String descripcionCriticidad) {
        this.descripcionCriticidad = descripcionCriticidad;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    public void setDescripcionSistema(String descripcionSistema) {
        this.descripcionSistema = descripcionSistema;
    }

    public void setCodigoComponente(String codigoComponente) {
        this.codigoComponente = codigoComponente;
    }

    public void setDescripcionComponente(String descripcionComponente) {
        this.descripcionComponente = descripcionComponente;
    }

    public void setCodigoSintoma(String codigoSintoma) {
        this.codigoSintoma = codigoSintoma;
    }

    public void setDescripcionSintoma(String descripcionSintoma) {
        this.descripcionSintoma = descripcionSintoma;
    }

    public void setCodigoEquipo(String codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }

    public void setDescripcionEquipo(String descripcionEquipo) {
        this.descripcionEquipo = descripcionEquipo;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }

    public void setDescripcionUbicacion(String descripcionUbicacion) {
        this.descripcionUbicacion = descripcionUbicacion;
    }

    public void setFechaInicioReal(String fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
    }

    public void setFechaFinReal(String fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }

    public void setHoraInicioReal(String horaInicioReal) {
        this.horaInicioReal = horaInicioReal;
    }

    public void setHoraFinReal(String horaFinReal) {
        this.horaFinReal = horaFinReal;
    }

    public void setTotalHoraReales(String totalHoraReales) {
        this.totalHoraReales = totalHoraReales;
    }

    public void setFechaInicioProgramado(String fechaInicioProgramado) {
        this.fechaInicioProgramado = fechaInicioProgramado;
    }

    public void setFechaFinProgramado(String fechaFinProgramado) {
        this.fechaFinProgramado = fechaFinProgramado;
    }

    public void setHoraInicioProgramada(String horaInicioProgramada) {
        this.horaInicioProgramada = horaInicioProgramada;
    }

    public void setHoraFinProgramado(String horaFinProgramado) {
        this.horaFinProgramado = horaFinProgramado;
    }

    public void setTotalHorasProgramado(String totalHorasProgramado) {
        this.totalHorasProgramado = totalHorasProgramado;
    }

    public void setFechaCreacionOrden(String fechaCreacionOrden) {
        this.fechaCreacionOrden = fechaCreacionOrden;
    }

    public void setFechaAutorizacionOrden(String fechaAutorizacionOrden) {
        this.fechaAutorizacionOrden = fechaAutorizacionOrden;
    }

    public void setHoraCreacionOrden(String horaCreacionOrden) {
        this.horaCreacionOrden = horaCreacionOrden;
    }

    public void setHoraAutorizacionOrden(String horaAutorizacionOrden) {
        this.horaAutorizacionOrden = horaAutorizacionOrden;
    }

    public void setTotalHorasCreacionOrden(String totalHorasCreacionOrden) {
        this.totalHorasCreacionOrden = totalHorasCreacionOrden;
    }

    public void setCodigoGruoPlan(String codigoGruoPlan) {
        this.codigoGruoPlan = codigoGruoPlan;
    }

    public void setDescripcionGrupoPlan(String descripcionGrupoPlan) {
        this.descripcionGrupoPlan = descripcionGrupoPlan;
    }

    public void setCodigoSolicitante(String codigoSolicitante) {
        this.codigoSolicitante = codigoSolicitante;
    }

    public void setDescripcionSolicitante(String descripcionSolicitante) {
        this.descripcionSolicitante = descripcionSolicitante;
    }

    public void setCodigoResponsable(String codigoResponsable) {
        this.codigoResponsable = codigoResponsable;
    }

    public void setDescripcionResponsable(String descripcionResponsable) {
        this.descripcionResponsable = descripcionResponsable;
    }

    public void setCodigoSociedad(String codigoSociedad) {
        this.codigoSociedad = codigoSociedad;
    }

    public void setDescripcionSociedad(String descripcionSociedad) {
        this.descripcionSociedad = descripcionSociedad;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public void setCodigoEmplazamiento(String codigoEmplazamiento) {
        this.codigoEmplazamiento = codigoEmplazamiento;
    }

    public void setDescripcionEmplazamiento(String descripcionEmplazamiento) {
        this.descripcionEmplazamiento = descripcionEmplazamiento;
    }

    public void setCentroCostos(String centroCostos) {
        this.centroCostos = centroCostos;
    }

    public void setDescripcionCentroCostos(String descripcionCentroCostos) {
        this.descripcionCentroCostos = descripcionCentroCostos;
    }

    public void setOrdenCreadaPor(String ordenCreadaPor) {
        this.ordenCreadaPor = ordenCreadaPor;
    }

    public void setOrdenPlaneadaPor(String ordenPlaneadaPor) {
        this.ordenPlaneadaPor = ordenPlaneadaPor;
    }

    public void setOrdenAprobadaPor(String ordenAprobadaPor) {
        this.ordenAprobadaPor = ordenAprobadaPor;
    }

    public void setOrdenEjecutadaPor(String ordenEjecutadaPor) {
        this.ordenEjecutadaPor = ordenEjecutadaPor;
    }

    public void setHorasEstimadas(String horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public void setHorasReal(String horasReal) {
        this.horasReal = horasReal;
    }

    public void setCostoEstimado(String costoEstimado) {
        this.costoEstimado = costoEstimado;
    }

    public void setCostoReal(String costoReal) {
        this.costoReal = costoReal;
    }

    public void setNumeroContador(String numeroContador) {
        this.numeroContador = numeroContador;
    }

    public void setValorContador(String valorContador) {
        this.valorContador = valorContador;
    }

    public void setHojaRuta(String hojaRuta) {
        this.hojaRuta = hojaRuta;
    }

    public void setNumeroPlan(String numeroPlan) {
        this.numeroPlan = numeroPlan;
    }

}
