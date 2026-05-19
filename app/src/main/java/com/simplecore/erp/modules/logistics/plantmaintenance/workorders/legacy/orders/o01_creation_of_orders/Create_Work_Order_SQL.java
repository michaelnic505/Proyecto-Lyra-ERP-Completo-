package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;

public class Create_Work_Order_SQL {

    public String getNumeroOrdenMat() {
        return numeroOrdenMat;
    }

    public void setNumeroOrdenMat(String numeroOrdenMat) {
        this.numeroOrdenMat = numeroOrdenMat;
    }

    public String getNumOrdenOp() {
        return numOrdenOp;
    }

    public void setNumOrdenOp(String numOrdenOp) {
        this.numOrdenOp = numOrdenOp;
    }

    /*METODOS GET Y SET PARA LA CREACION DE ORDEN DE TRABAJO                    */
    
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
    
    /* METODO PARA CREAR LA ORDEN DE TRABAJO                                                       */
    
    public void createWorkOrder() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String insert = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.WORK_ORDERS.tableName()
                    + SentenceValues.setValues(63);
            
            st = conexion.prepareStatement(insert,java.sql.Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1,"0");
            st.setString (2, getCodigoEstatus());
            st.setString (3, getDescripcionEstatus());
            
            st.setBoolean(4, isParoEquipo());
            st.setString(5, getTituloOrden());
            st.setString(6, getDescripcionExtendidaOrden());
            
            st.setString(7, getCodigoTipoOrden());
            st.setString(8, getDescripcionTipoOrden());
            
            st.setString(9, getCodigoClaseOrden());
            st.setString(10, getDescripcionClaseOrden());
            
            st.setString(11, getCodigoCriticidad());
            st.setString(12, getDescripcionCriticidad());
            
            st.setString(13, getCodigoSistema());
            st.setString(14, getDescripcionSistema());
            
            st.setString(15, getCodigoComponente());
            st.setString(16, getDescripcionComponente());
            
            st.setString(17, getCodigoSintoma());
            st.setString(18, getDescripcionSintoma());
            
            st.setString(19, getCodigoEquipo());
            st.setString(20, getDescripcionEquipo());
            
            st.setString(21, getCodigoUbicacion());
            st.setString(22, getDescripcionUbicacion());
            
            st.setString(23, getFechaInicioReal());
            st.setString(24, getFechaFinReal());
            
            st.setString(25, getHoraInicioReal());
            st.setString(26, getHoraFinReal());
            
            st.setString(27, getTotalHoraReales());
            st.setString(28, getFechaInicioProgramado());
            st.setString(29, getFechaFinProgramado());
            
            st.setString(30, getHoraInicioProgramada());
            st.setString(31, getHoraFinProgramado());
            st.setString(32, getTotalHorasProgramado());
            
            st.setString(33, getFechaCreacionOrden());
            st.setString(34, getFechaAutorizacionOrden());
            
            st.setString(35, getHoraCreacionOrden());
            st.setString(36, getHoraAutorizacionOrden());
            
            st.setString(37, getTotalHorasCreacionOrden());
            
            st.setString(38, getCodigoGruoPlan());
            st.setString(39, getDescripcionGrupoPlan());
            st.setString(40, getCodigoSolicitante());
            st.setString(41, getDescripcionSolicitante());
            st.setString(42, getCodigoResponsable());
            st.setString(43, getDescripcionResponsable());

            st.setString(44, getCodigoSociedad());
            st.setString(45, getDescripcionSociedad());
            st.setString(46, getCodigoArea());
            st.setString(47, getDescripcionArea());
            st.setString(48, getCodigoEmplazamiento());
            st.setString(49, getDescripcionEmplazamiento());
            st.setString(50, getCentroCostos());
            st.setString(51, getDescripcionCentroCostos());
            st.setString(52, getOrdenCreadaPor());
            st.setString(53, getOrdenPlaneadaPor());
            st.setString(54, getOrdenAprobadaPor());
            st.setString(55, getOrdenEjecutadaPor());
            st.setString(56, getHorasEstimadas());
            st.setString(57, getHorasReal());
            st.setString(58, getCostoEstimado());
            st.setString(59, getCostoReal());

            st.setString(60, getNumeroContador());
            st.setString(61, getValorContador());
            st.setString(62, getHojaRuta());
            st.setString(63, getNumeroPlan());

            st.executeUpdate();
            
            ResultSet set = st.getGeneratedKeys();

            if(set.next()){
                setNumeroOrden(set.getString(1));
            }

            st.close();
            

        
        } catch (SQLException ex) {
            Logger.getLogger(Create_Work_Order_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
    
    /*METODOS GET Y SET DE OPERACIONES                                                                   */
    
    public String getNumOperacion() {
        return numOperacion;
    }

    public String getTextoExplicativoOperacion() {
        return textoExplicativoOperacion;
    }

    public String getTituloOperacion() {
        return tituloOperacion;
    }

    public double getHorasTrabajo() {
        return horasTrabajo;
    }

    public double getCantidadOp() {
        return cantidadOp;
    }

    public double getDuracionOp() {
        return duracionOp;
    }

    public String getCodigoTipoOperacion() {
        return codigoTipoOperacion;
    }

    public String getDescripcionTipoOperacion() {
        return descripcionTipoOperacion;
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

    public String getSolicitudPedido() {
        return solicitudPedido;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getNumRegistro() {
        return numRegistro;
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

    public void setHorasTrabajo(double horasTrabajo) {
        this.horasTrabajo = horasTrabajo;
    }

    public void setCantidadOp(double cantidadOp) {
        this.cantidadOp = cantidadOp;
    }

    public void setDuracionOp(double duracionOp) {
        this.duracionOp = duracionOp;
    }

    public void setCodigoTipoOperacion(String codigoTipoOperacion) {
        this.codigoTipoOperacion = codigoTipoOperacion;
    }

    public void setDescripcionTipoOperacion(String descripcionTipoOperacion) {
        this.descripcionTipoOperacion = descripcionTipoOperacion;
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

    public void setSolicitudPedido(String solicitudPedido) {
        this.solicitudPedido = solicitudPedido;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setNumRegistro(String numRegistro) {
        this.numRegistro = numRegistro;
    }
 
    
    /*variables de OPERACIONES                                                  */
    
    private String numOperacion;
    private String textoExplicativoOperacion;
    private String tituloOperacion;
    private double horasTrabajo;
    private double cantidadOp;
    private double duracionOp;
    private String codigoTipoOperacion;
    private String descripcionTipoOperacion;
    private double costoUnitarioOp;
    private String unidadMedidaOp;
    private double montoTotalOp;
    private String paqueteMttoOp;
    private String solicitudPedido;
    private String moneda;
    private String numOrdenOp;
    
    private String numRegistro;
    
/* METODO PARA CREAR EL REGISTRO DE OPERACIONES                                                       */
    
    public void createOperationOfWorkOrder() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String insert = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.OPERATIONS_WORK_ORDERS.tableName()
                    + SentenceValues.setValues(16);
            
            st = conexion.prepareStatement(insert);
            
            setNumRegistro(getNumOrdenOp()+"-"+getNumOperacion());
            
            st.setString(1, getNumRegistro());
            st.setString(2, getNumOperacion());
            st.setString(3, getTextoExplicativoOperacion());
            st.setString(4, getTituloOperacion());
            st.setDouble(5, getHorasTrabajo());
            st.setDouble(6, getCantidadOp());
            st.setDouble(7, getDuracionOp());
            st.setString(8, getCodigoTipoOperacion());
            st.setString(9, getDescripcionTipoOperacion());
            st.setDouble(10, getCostoUnitarioOp());
            st.setString(11, getUnidadMedidaOp());
            st.setDouble(12, getMontoTotalOp());
            st.setString(13, getPaqueteMttoOp());
            st.setString(14, getSolicitudPedido());
            st.setString(15, getMoneda());
            st.setString(16, getNumOrdenOp());

            st.executeUpdate();
        
            st.close();
        
        
        } catch (SQLException ex) {
            Logger.getLogger(Create_Work_Order_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

    /*variables de MATERIALES*/
    private String posicionMat;
    private String codigoMaterial;
    private String descripcionMaterial;
    private double cantidadMat;
    private String unidadMedida;
    private double costoUnitarioMat;
    private String codigoAlmacen;
    private double montoTotalMat;
    private String numOpMat;
    private String paqueteMttoMat;
    private String numeroRegistro;
    private String numeroOrdenMat;
    
    
    /* METODO PARA CREAR EL REGISTRO DE MATERIAL                                                        */
    
    public void createMaterialOfWorkOrder() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String insert = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.MATERIALS_WORK_ORDERS.tableName()
                    + SentenceValues.setValues(12);
            
            st = conexion.prepareStatement(insert);
            
            setNumeroRegistro(getNumeroOrdenMat()+"-"+getPosicionMat());
            
            st.setString(1, getNumeroRegistro());
            st.setString(2, getPosicionMat());
            st.setString(3, getCodigoMaterial());
            st.setString(4, getDescripcionMaterial());
            st.setDouble(5, getCantidadMat());
            st.setString(6, getUnidadMedida());
            st.setDouble(7, getCostoUnitarioMat());
            st.setString(8, getCodigoAlmacen());
            st.setDouble(9, getMontoTotalMat());
            st.setString(10, getNumOpMat());
            st.setString(11, getPaqueteMttoMat());
            st.setString(12, getNumeroOrdenMat());

            st.executeUpdate();
            st.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Create_Work_Order_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    /*METODOS SET Y GET DE MATERIALES                                                                   */

    public String getPosicionMat() {
        return posicionMat;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public String getDescripcionMaterial() {
        return descripcionMaterial;
    }

    public double getCantidadMat() {
        return cantidadMat;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public double getCostoUnitarioMat() {
        return costoUnitarioMat;
    }

    public String getCodigoAlmacen() {
        return codigoAlmacen;
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

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setPosicionMat(String posicionMat) {
        this.posicionMat = posicionMat;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public void setDescripcionMaterial(String descripcionMaterial) {
        this.descripcionMaterial = descripcionMaterial;
    }

    public void setCantidadMat(double cantidadMat) {
        this.cantidadMat = cantidadMat;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void setCostoUnitarioMat(double costoUnitarioMat) {
        this.costoUnitarioMat = costoUnitarioMat;
    }

    public void setCodigoAlmacen(String codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public void setMontoTotalMat(double montoTotalMat) {
        this.montoTotalMat = montoTotalMat;
    }

    public void setNumOpMat(String numOp) {
        this.numOpMat = numOp;
    }

    public void setPaqueteMttoMat(String paqueteMttoMat) {
        this.paqueteMttoMat = paqueteMttoMat;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }



}
