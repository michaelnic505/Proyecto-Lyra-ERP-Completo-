
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders;

import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.WorkOrder_Fields;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Modify_Statement;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.WorkOrders;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.WorkOrder_Fields.MaterialsWorkOrder_Fields;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.WorkOrder_Fields.OperationsWorkOrder_Fields;




public class Modify_Work_Order_SQL {

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

    public void modifyWorkOrder() {
        
        try {
            
            List<String> fieldList = new ArrayList();
            
            fieldList.add(WorkOrders.STATUS_CODE.toString());
            fieldList.add(WorkOrders.STATUS_DESCRIPTION.toString());
            fieldList.add(WorkOrders.STOP.toString());
            fieldList.add(WorkOrders.ORDER_TITLE.toString());
            fieldList.add(WorkOrders.EXTENDED_DESCRIPTION.toString());
            fieldList.add(WorkOrders.TYPE_ORDER_CODE.toString());
            fieldList.add(WorkOrders.TYPE_ORDER_DESCRIPTION.toString());
            fieldList.add(WorkOrders.CLASS_ORDER_CODE.toString());
            fieldList.add(WorkOrders.CLASS_ORDER_DESCRIPTION.toString());
            fieldList.add(WorkOrders.CRITICALITY_CODE.toString());
            fieldList.add(WorkOrders.CRITICALITY_DESCRIPTION.toString());
            fieldList.add(WorkOrders.SYSTEM_CODE.toString());
            fieldList.add(WorkOrders.SYSTEM_DESCRIPTION.toString());
            fieldList.add(WorkOrders.COMPONENT_CODE.toString());
            fieldList.add(WorkOrders.COMPONENT_DESCRIPTION.toString());
            fieldList.add(WorkOrders.SYMPTOM_CODE.toString());
            fieldList.add(WorkOrders.SYMPTOM_DESCRIPTION.toString());
            fieldList.add(WorkOrders.EQUIPMENT_CODE.toString());
            fieldList.add(WorkOrders.EQUIPMENT_DESCRIPTION.toString());
            fieldList.add(WorkOrders.UBICATION_CODE.toString());
            fieldList.add(WorkOrders.UBICATION_DESCRIPTION.toString());
            fieldList.add(WorkOrders.ACTUAL_START_DATE.toString());
            fieldList.add(WorkOrders.ACTUAL_END_DATE.toString());
            fieldList.add(WorkOrders.ACTUAL_START_TIME.toString());
            fieldList.add(WorkOrders.ACTUAL_END_TIME.toString());
            fieldList.add(WorkOrders.TOTAL_REAL_TIME.toString());
            fieldList.add(WorkOrders.SCHEDULED_START_DATE.toString());
            fieldList.add(WorkOrders.SCHEDULED_END_DATE.toString());
            fieldList.add(WorkOrders.SCHEDULED_START_TIME.toString());
            fieldList.add(WorkOrders.SCHEDULED_END_TIME.toString());
            fieldList.add(WorkOrders.SCHEDULED_TOTAL_TIME.toString());
            fieldList.add(WorkOrders.CREATION_DATE_ORDER.toString());
            fieldList.add(WorkOrders.APPROVAL_DATE_ORDER.toString());
            fieldList.add(WorkOrders.CREATION_TIME_ORDER.toString());
            fieldList.add(WorkOrders.AUTHORIZATION_TIME_ORDER.toString());
            fieldList.add(WorkOrders.TOTAL_CREATION_HOURS.toString());
            fieldList.add(WorkOrders.GROUP_PLANNING_CODE.toString());
            fieldList.add(WorkOrders.PLANNING_GROUP_DESCRIPTION.toString());
            fieldList.add(WorkOrders.APPLICANT_CODE.toString());
            fieldList.add(WorkOrders.APPLICANT_DESCRIPTION.toString());
            fieldList.add(WorkOrders.RESPONSIBLE_CODE.toString());
            fieldList.add(WorkOrders.DESCRIPTION_OF_RESPONSIBLE.toString());
            fieldList.add(WorkOrders.COMPANY_CODE.toString());
            fieldList.add(WorkOrders.COMPANY_DESCRIPTION.toString());
            fieldList.add(WorkOrders.AREA_CODE.toString());
            fieldList.add(WorkOrders.AREA_DESCRIPTION.toString());
            fieldList.add(WorkOrders.EMPLAZEMENT_CODE.toString());
            fieldList.add(WorkOrders.EMPLAZEMENT_DESCRIPTION.toString());
            fieldList.add(WorkOrders.COST_CENTER_CODE.toString());
            fieldList.add(WorkOrders.COST_CENTER_DESCRIPTION.toString());
            fieldList.add(WorkOrders.ORDER_CREATED_BY.toString());
            fieldList.add(WorkOrders.ORDER_PLANNED_BY.toString());
            fieldList.add(WorkOrders.ORDER_APPROVED_BY.toString());
            fieldList.add(WorkOrders.ORDER_EXECUTED_BY.toString());
            fieldList.add(WorkOrders.ESTIMATED_TIME.toString());
            fieldList.add(WorkOrders.REAL_TIME.toString());
            fieldList.add(WorkOrders.ESTIMATED_COST.toString());
            fieldList.add(WorkOrders.REAL_COST.toString());
            fieldList.add(WorkOrders.COUNTER.toString());
            fieldList.add(WorkOrders.COUNTER_VALUE.toString());
            fieldList.add(WorkOrders.ROAD_SHEET.toString());
            fieldList.add(WorkOrders.PLAN.toString());

            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String modify = SQLKeywords.UPDATE.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SQLKeywords.SET.toSQL()
                    + SQL_Modify_Statement.setModifyFields(fieldList)
                    + SQLKeywords.WHERE.toSQL()
                    + WorkOrder_Fields.ORDER_NUM.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + getNumeroOrden()
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            st = conexion.prepareStatement(modify);
            st.setString(1, getCodigoEstatus());
            st.setString(2, getDescripcionEstatus());
            st.setBoolean(3, isParoEquipo());
            st.setString(4, getTituloOrden());
            st.setString(5, getDescripcionExtendidaOrden());
            st.setString(6, getCodigoTipoOrden());
            st.setString(7, getDescripcionTipoOrden());
            st.setString(8, getCodigoClaseOrden());
            st.setString(9, getDescripcionClaseOrden());
            st.setString(10, getCodigoCriticidad());
            st.setString(11, getDescripcionCriticidad());
            st.setString(12, getCodigoSistema());
            st.setString(13, getDescripcionSistema());
            st.setString(14, getCodigoComponente());
            st.setString(15, getDescripcionComponente());
            st.setString(16, getCodigoSintoma());
            st.setString(17, getDescripcionSintoma());
            st.setString(18, getCodigoEquipo());
            st.setString(19, getDescripcionEquipo());
            st.setString(20, getCodigoUbicacion());
            st.setString(21, getDescripcionUbicacion());
            st.setString(22, getFechaInicioReal());
            st.setString(23, getFechaFinReal());
            st.setString(24, getHoraInicioReal());
            st.setString(25, getHoraFinReal());
            st.setString(26, getTotalHoraReales());
            st.setString(27, getFechaInicioProgramado());
            st.setString(28, getFechaFinProgramado());
            st.setString(29, getHoraInicioProgramada());
            st.setString(30, getHoraFinProgramado());
            st.setString(31, getTotalHorasProgramado());
            st.setString(32, getFechaCreacionOrden());
            st.setString(33, getFechaAutorizacionOrden());
            st.setString(34, getHoraCreacionOrden());
            st.setString(35, getHoraAutorizacionOrden());
            st.setString(36, getTotalHorasCreacionOrden());
            st.setString(37, getCodigoGruoPlan());
            st.setString(38, getDescripcionGrupoPlan());
            st.setString(39, getCodigoSolicitante());
            st.setString(40, getDescripcionSolicitante());
            st.setString(41, getCodigoResponsable());
            st.setString(42, getDescripcionResponsable());
            st.setString(43, getCodigoSociedad());
            st.setString(44, getDescripcionSociedad());
            st.setString(45, getCodigoArea());
            st.setString(46, getDescripcionArea());
            st.setString(47, getCodigoEmplazamiento());
            st.setString(48, getDescripcionEmplazamiento());
            st.setString(49, getCentroCostos());
            st.setString(50, getDescripcionCentroCostos());
            st.setString(51, getOrdenCreadaPor());
            st.setString(52, getOrdenPlaneadaPor());
            st.setString(53, getOrdenAprobadaPor());
            st.setString(54, getOrdenEjecutadaPor());
            st.setString(55, getHorasEstimadas());
            st.setString(56, getHorasReal());
            st.setString(57, getCostoEstimado());
            st.setString(58, getCostoReal());
            st.setString(59, getNumeroContador());
            st.setString(60, getValorContador());
            st.setString(61, getHojaRuta());
            st.setString(62, getNumeroPlan());

            st.executeUpdate();
            
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Modify_Work_Order_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }



//METODOS GET Y SET DE OPERACIONES DE LA ORDEN
    
    public String getNumOperacion() {
        return numOperacion;
    }

    public String getTextoExplicativoOperacion() {
        return textoExplicativoOperacion;
    }

    public String getTituloOperacion() {
        return tituloOperacion;
    }

    public double getHorasTrabajoOp() {
        return horasTrabajoOp;
    }

    public double getCantidadOp() {
        return cantidadOp;
    }

    public double getDuracionOp() {
        return duracionOp;
    }

    public String getCodigoTipoOperacionOp() {
        return codigoTipoOperacionOp;
    }

    public String getDescripcionTipoOperacionOp() {
        return descripcionTipoOperacionOp;
    }

    public double getCostoUnitarioOp() {
        return costoUnitarioOp;
    }

    public String getUnidadMedidaOp() {
        return unidadMedidaOp;
    }

    public double getMontoTotalOp() {
        return montoTotalOp;
    }

    public String getPaqueteMttoOp() {
        return paqueteMttoOp;
    }

    public String getSolicitudPedidoOp() {
        return solicitudPedidoOp;
    }

    public String getMonedaOp() {
        return monedaOp;
    }

    public String getNumOrdenOp() {
        return numOrdenOp;
    }

    public String getNumRegistroOp() {
        return numRegistroOp;
    }

    public void setNumOperacion(String numOperacion) {
        this.numOperacion = numOperacion;
    }

    public void setTextoExplicativoOperacion(String textoExplicativoOperacion) {
        this.textoExplicativoOperacion = textoExplicativoOperacion;
    }

    public void setTituloOperacion(String tituloOperacion) {
        this.tituloOperacion = tituloOperacion;
    }

    public void setHorasTrabajoOp(double horasTrabajoOp) {
        this.horasTrabajoOp = horasTrabajoOp;
    }

    public void setCantidadOp(double cantidadOp) {
        this.cantidadOp = cantidadOp;
    }

    public void setDuracionOp(double duracionOp) {
        this.duracionOp = duracionOp;
    }

    public void setCodigoTipoOperacionOp(String codigoTipoOperacionOp) {
        this.codigoTipoOperacionOp = codigoTipoOperacionOp;
    }

    public void setDescripcionTipoOperacionOp(String descripcionTipoOperacionOp) {
        this.descripcionTipoOperacionOp = descripcionTipoOperacionOp;
    }

    public void setCostoUnitarioOp(double costoUnitarioOp) {
        this.costoUnitarioOp = costoUnitarioOp;
    }

    public void setUnidadMedidaOp(String unidadMedidaOp) {
        this.unidadMedidaOp = unidadMedidaOp;
    }

    public void setMontoTotalOp(double montoTotalOp) {
        this.montoTotalOp = montoTotalOp;
    }

    public void setPaqueteMttoOp(String paqueteMttoOp) {
        this.paqueteMttoOp = paqueteMttoOp;
    }

    public void setSolicitudPedidoOp(String solicitudPedidoOp) {
        this.solicitudPedidoOp = solicitudPedidoOp;
    }

    public void setMonedaOp(String monedaOp) {
        this.monedaOp = monedaOp;
    }

    public void setNumOrdenOp(String numOrdenOp) {
        this.numOrdenOp = numOrdenOp;
    }

    public void setNumRegistroOp(String numRegistroOp) {
        this.numRegistroOp = numRegistroOp;
    }
    
    
    /*variables de OPERACIONES                                                  */
    
    private String numOperacion;
    private String textoExplicativoOperacion;
    private String tituloOperacion;
    private double horasTrabajoOp;
    private double cantidadOp;
    private double duracionOp;
    private String codigoTipoOperacionOp;
    private String descripcionTipoOperacionOp;
    private double costoUnitarioOp;
    private String unidadMedidaOp;
    private double montoTotalOp;
    private String paqueteMttoOp;
    private String solicitudPedidoOp;
    private String monedaOp;
    private String numOrdenOp;    
    private String numRegistroOp;

    public void modifyOperationOfWorkOrder(){

        try {
            List<String> fieldList = new ArrayList();
            
            fieldList.add(OperationsWorkOrder_Fields.OPERATION_NUMBER.toString());
            fieldList.add(OperationsWorkOrder_Fields.EXPLANATORY_TEXT_OPERATION.toString());
            fieldList.add(OperationsWorkOrder_Fields.TITLE_OPERATION.toString());
            fieldList.add(OperationsWorkOrder_Fields.WORKING_HOURS.toString());
            fieldList.add(OperationsWorkOrder_Fields.QUANTITY.toString());
            fieldList.add(OperationsWorkOrder_Fields.DURATION.toString());
            fieldList.add(OperationsWorkOrder_Fields.OPERATION_TYPE_CODE.toString());
            fieldList.add(OperationsWorkOrder_Fields.OPERATION_TYPE_DESCRIPTION.toString());
            fieldList.add(OperationsWorkOrder_Fields.UNIT_COST.toString());
            fieldList.add(OperationsWorkOrder_Fields.MEASUREMENT_UNIT.toString());
            fieldList.add(OperationsWorkOrder_Fields.TOTAL_AMOUNT.toString());
            fieldList.add(OperationsWorkOrder_Fields.MTTO_PACKAGE.toString());
            fieldList.add(OperationsWorkOrder_Fields.ORDER_REQUEST.toString());
            fieldList.add(OperationsWorkOrder_Fields.CURRENCY.toString());
            fieldList.add(OperationsWorkOrder_Fields.NUM_WORK_ORDER.toString());
            
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            setNumRegistroOp(getNumOrdenOp()+"-"+getNumOperacion());
            
            String modify = SQLKeywords.UPDATE.toSQL()
                    + DatabaseTables.OPERATIONS_WORK_ORDERS.tableName()
                    + SQLKeywords.SET.toSQL()
                    + SQL_Modify_Statement.setModifyFields(fieldList)
                    + SQLKeywords.WHERE.toSQL()
                    + OperationsWorkOrder_Fields.REGISTRATION_NUMBER.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + getNumRegistroOp()
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            st = conexion.prepareStatement(modify);

            st.setString(1, getNumOperacion());
            st.setString(2, getTextoExplicativoOperacion());
            st.setString(3, getTituloOperacion());
            st.setDouble(4, getHorasTrabajoOp());
            st.setDouble(5, getCantidadOp());
            st.setDouble(6, getDuracionOp());
            st.setString(7, getCodigoTipoOperacionOp());
            st.setString(8, getDescripcionTipoOperacionOp());
            st.setDouble(9, getCostoUnitarioOp());
            st.setString(10, getUnidadMedidaOp());
            st.setDouble(11, getMontoTotalOp());
            st.setString(12, getPaqueteMttoOp());
            st.setString(13, getSolicitudPedidoOp());
            st.setString(14, getMonedaOp());
            st.setString(15, getNumOrdenOp());

            st.executeUpdate();
            
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Modify_Work_Order_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
 
    //METODOS GET Y SET DE MATERIALES ORDEN
    public String getPosicionMat() {
        return posicionMat;
    }

    public String getCodigoMaterialMat() {
        return codigoMaterialMat;
    }

    public String getDescripcionMaterialMat() {
        return descripcionMaterialMat;
    }

    public double getCantidadMat() {
        return cantidadMat;
    }

    public String getUnidadMedidaMat() {
        return unidadMedidaMat;
    }

    public double getCostoUnitarioMat() {
        return costoUnitarioMat;
    }

    public String getCodigoAlmacenMat() {
        return codigoAlmacenMat;
    }

    public double getMontoTotalMat() {
        return montoTotalMat;
    }

    public String getNumOpMat() {
        return numOpMat;
    }

    public String getPaqueteMttoMat() {
        return paqueteMttoMat;
    }

    public String getNumeroRegistroMat() {
        return numeroRegistroMat;
    }

    public String getNumeroOrdenMat() {
        return numeroOrdenMat;
    }

    public void setPosicionMat(String posicionMat) {
        this.posicionMat = posicionMat;
    }

    public void setCodigoMaterialMat(String codigoMaterialMat) {
        this.codigoMaterialMat = codigoMaterialMat;
    }

    public void setDescripcionMaterialMat(String descripcionMaterialMat) {
        this.descripcionMaterialMat = descripcionMaterialMat;
    }

    public void setCantidadMat(double cantidadMat) {
        this.cantidadMat = cantidadMat;
    }

    public void setUnidadMedidaMat(String unidadMedidaMat) {
        this.unidadMedidaMat = unidadMedidaMat;
    }

    public void setCostoUnitarioMat(double costoUnitarioMat) {
        this.costoUnitarioMat = costoUnitarioMat;
    }

    public void setCodigoAlmacenMat(String codigoAlmacenMat) {
        this.codigoAlmacenMat = codigoAlmacenMat;
    }

    public void setMontoTotalMat(double montoTotalMat) {
        this.montoTotalMat = montoTotalMat;
    }

    public void setNumOpMat(String numOpMat) {
        this.numOpMat = numOpMat;
    }

    public void setPaqueteMttoMat(String paqueteMttoMat) {
        this.paqueteMttoMat = paqueteMttoMat;
    }

    public void setNumeroRegistroMat(String numeroRegistroMat) {
        this.numeroRegistroMat = numeroRegistroMat;
    }

    public void setNumeroOrdenMat(String numeroOrdenMat) {
        this.numeroOrdenMat = numeroOrdenMat;
    }
    
    
    
    
        /*variables de MATERIALES*/
    private String posicionMat;
    private String codigoMaterialMat;
    private String descripcionMaterialMat;
    private double cantidadMat;
    private String unidadMedidaMat;
    private double costoUnitarioMat;
    private String codigoAlmacenMat;
    private double montoTotalMat;
    private String numOpMat;
    private String paqueteMttoMat;
    private String numeroRegistroMat;
    private String numeroOrdenMat;
    
    public void modifyMaterialOfWorkOrder() {
        
        try {
            List<String> fieldList = new ArrayList();
            
            fieldList.add(MaterialsWorkOrder_Fields.POSITION.toString());
            fieldList.add(MaterialsWorkOrder_Fields.MATERIAL_CODE.toString());
            fieldList.add(MaterialsWorkOrder_Fields.MATERIAL_DESCRIPTION.toString());
            fieldList.add(MaterialsWorkOrder_Fields.QUANTITY.toString());
            fieldList.add(MaterialsWorkOrder_Fields.MEASUREMENT_UNIT.toString());
            fieldList.add(MaterialsWorkOrder_Fields.UNIT_COST.toString());
            fieldList.add(MaterialsWorkOrder_Fields.WAREHOUSE_CODE.toString());
            fieldList.add(MaterialsWorkOrder_Fields.TOTAL_AMOUNT.toString());
            fieldList.add(MaterialsWorkOrder_Fields.OPERATION_NUMBER.toString());
            fieldList.add(MaterialsWorkOrder_Fields.MTTO_PACKAGE.toString());
            fieldList.add(MaterialsWorkOrder_Fields.NUM_WORK_ORDER.toString());

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            setNumeroRegistroMat(getNumeroOrdenMat()+"-"+getPosicionMat());
            
            String modify = SQLKeywords.UPDATE.toSQL()
                    + DatabaseTables.MATERIALS_WORK_ORDERS.tableName()
                    + SQLKeywords.SET.toSQL()
                    + SQL_Modify_Statement.setModifyFields(fieldList)
                    + SQLKeywords.WHERE.toSQL()
                    + MaterialsWorkOrder_Fields.REGISTRATION_NUMBER.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + getNumeroRegistroMat()
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            
            st = conexion.prepareStatement(modify);
            
            st.setString(1, getPosicionMat());
            st.setString(2, getCodigoMaterialMat());
            st.setString(3, getDescripcionMaterialMat());
            st.setDouble(4, getCantidadMat());
            st.setString(5, getUnidadMedidaMat());
            st.setDouble(6, getCostoUnitarioMat());
            st.setString(7, getCodigoAlmacenMat());
            st.setDouble(8, getMontoTotalMat());
            st.setString(9, getNumOpMat());
            st.setString(10, getPaqueteMttoMat());
            st.setString(11, getNumeroOrdenMat());

            st.executeUpdate();            
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(Modify_Work_Order_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
