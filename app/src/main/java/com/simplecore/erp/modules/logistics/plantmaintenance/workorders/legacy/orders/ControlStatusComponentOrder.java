package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ControlStatusComponentOrder {

    private JTextField numeroOrdenTB;
    private JTextField codigoEstatusTB;
    private JTextField descripcionEstatusTB;
    private JCheckBox paradaCB;
    private JTextField tituloOrdenTB;
    private JButton btnDescripcionExtendida;
    private JTextArea descripcionExtendidaOrdenTB;

    private JTextField codigoTipoMttoTB;
    private JTextField descripcionTipoMttoTB;
    private JButton btnTipoMtto;

    private JTextField codigoClaseOrdenTB;
    private JTextField descripcionClaseOrdenTB;
    private JButton btnClaseMtto;

    private JTextField codigoCriticidadTB;
    private JTextField descripcionCriticidadTB;
    private JButton btnCriticidadMtto;

    private JTextField codigoSistemasTB;
    private JTextField descripcionSistemasTB;
    private JButton btnSistema;

    private JTextField codigoComponenteTB;
    private JTextField descripcionComponenteTB;
    private JButton btnComponente;

    private JTextField codigoSintomaTB;
    private JTextField descripcionSintomaTB;
    private JButton btnSintoma;

    private JTextField codigoEquipoTB;
    private JButton btnEquipo;

    private JTextField codigoUbicacionTB;
    private JButton btnUbicacion;

    private JDateChooser fechaInicioReal;
    private JDateChooser fechaFinReal;

    private JTextField horaInicioReal;
    private JButton btnHoraInicioReal;
    private JTextField horaFinReal;
    private JButton btnHoraFinReal;
    private JTextField totalHoraReales;

    private JDateChooser fechaInicioProgramado;
    private JDateChooser fechaFinProgramado;

    private JTextField horaInicioProgramada;
    private JButton btnHoraProgramada;
    private JTextField horaFinProgramado;
    private JButton btnHoraCierreProg;
    private JTextField totalHorasProgramado;

    private JDateChooser fechaCreacionOrden;
    private JDateChooser fechaAutorizacionOrden;

    private JTextField horaCreacionOrden;
    private JButton btnHoraCreacion;

    private JTextField horaAutorizacionOrden;
    private JButton btnHoraAutorizada;
    private JTextField totalHorasCreacionOrden;

    private JTextField codigoGrupoPlanTB;

    private JTextField codigoSolicitanteTB;
    private JButton btnSolicitante;

    private JTextField codigoResponsableTB;
    private JButton btnResponsable;

    private JButton btnAgregarLineaOperacion;
    private JButton btnOkOperacion;
    private JButton btnEditarLineaOperacion;
    private JButton btnEliminarLineaOperacion;

    private JButton btnAgregarLineaMaterial;
    private JButton btnOkMaterial;
    private JButton btnEditarLineaMaterial;
    private JButton btnEliminarLineaMaterial;

    private JTextField sociedadTB;
    private JTextField areaTB;
    private JTextField emplazamientoTB;
    private JTextField centroCostosTB;

    private JTextField contadorTB;
    private JTextField valorContadorTB;
    private JTextField hojaRutaTB;
    private JTextField planTB;

     
    public void setStatusComponent(String status) {

        switch (status) {

            // ST1_ORDER_CREATED  -  estatus si la orden esta siendo creada
            case "OCRTD" -> {
                
                getNumeroOrdenTB().setEditable(false);

                getCodigoEstatusTB().setEditable(false);
                getDescripcionEstatusTB().setEditable(false);

                getParadaCB().setEnabled(true);
                getTituloOrdenTB().setEditable(true);

                getBtnDescripcionExtendida().setEnabled(true);
                getDescripcionExtendidaOrdenTB().setEditable(true);

                getCodigoTipoMttoTB().setEditable(false);
                getDescripcionTipoMttoTB().setEditable(false);
                getBtnTipoMtto().setEnabled(true);

                getCodigoClaseOrdenTB().setEditable(false);
                getDescripcionClaseOrdenTB().setEditable(false);
                getBtnClaseMtto().setEnabled(true);

                getCodigoCriticidadTB().setEditable(false);
                getDescripcionCriticidadTB().setEditable(false);
                getBtnCriticidadMtto().setEnabled(true);

                getCodigoSistemasTB().setEditable(false);
                getDescripcionSistemasTB().setEditable(false);
                getBtnSistema().setEnabled(true);

                getCodigoComponenteTB().setEditable(false);
                getDescripcionComponenteTB().setEditable(false);
                getBtnComponente().setEnabled(true);

                getCodigoSintomaTB().setEditable(false);
                getDescripcionSintomaTB().setEditable(false);
                getBtnSintoma().setEnabled(true);

                getCodigoEquipoTB().setEditable(false);
                getBtnEquipo().setEnabled(true);

                getCodigoUbicacionTB().setEditable(false);
                getBtnUbicacion().setEnabled(true);

                ((JTextFieldDateEditor) getFechaInicioReal().getDateEditor()).setEditable(false);
                getFechaInicioReal().getCalendarButton().setEnabled(true);
                
                ((JTextFieldDateEditor) getFechaFinReal().getDateEditor()).setEditable(false);
                getFechaFinReal().getCalendarButton().setEnabled(true);
                
                getHoraInicioReal().setEditable(false);
                getBtnHoraInicioReal().setEnabled(true);
                getHoraFinReal().setEditable(false);
                getBtnHoraFinReal().setEnabled(true);
                getTotalHoraReales().setEditable(false);

                ((JTextFieldDateEditor) getFechaInicioProgramado().getDateEditor()).setEditable(false);
                getFechaInicioProgramado().getCalendarButton().setEnabled(true);
                
                ((JTextFieldDateEditor) getFechaFinProgramado().getDateEditor()).setEditable(false);
                getFechaFinProgramado().getCalendarButton().setEnabled(true);
                
                getHoraInicioProgramada().setEditable(false);
                getBtnHoraProgramada().setEnabled(true);
                
                getHoraFinProgramado().setEditable(false);
                getBtnHoraCierreProg().setEnabled(true);
                
                getTotalHorasProgramado().setEditable(false);

                ((JTextFieldDateEditor) getFechaCreacionOrden().getDateEditor()).setEditable(false);
                getFechaCreacionOrden().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaAutorizacionOrden().getDateEditor()).setEditable(false);
                getFechaAutorizacionOrden().getCalendarButton().setEnabled(false);

                getHoraCreacionOrden().setEditable(false);
                getBtnHoraCreacion().setEnabled(false);
                
                getHoraAutorizacionOrden().setEditable(false);
                getBtnHoraAutorizada().setEnabled(false);
                
                getTotalHorasCreacionOrden().setEditable(false);

                getCodigoGrupoPlanTB().setEditable(false);
                getCodigoSolicitanteTB().setEditable(false);
                getBtnSolicitante().setEnabled(true);

                getCodigoResponsableTB().setEditable(false);
                getBtnResponsable().setEnabled(true);

                getBtnAgregarLineaOperacion().setEnabled(false);
                getBtnOkOperacion().setEnabled(false);
                getBtnEditarLineaOperacion().setEnabled(false);
                getBtnEliminarLineaOperacion().setEnabled(false);

                getBtnAgregarLineaMaterial().setEnabled(false);
                getBtnOkMaterial().setEnabled(false);
                getBtnEditarLineaMaterial().setEnabled(false);
                getBtnEliminarLineaMaterial().setEnabled(false);

                getSociedadTB().setEditable(false);
                getAreaTB().setEditable(false);
                getEmplazamientoTB().setEditable(false);
                getCentroCostosTB().setEditable(false);

                getContadorTB().setEditable(false);
                getValorContadorTB().setEditable(false);
                getHojaRutaTB().setEditable(false);
                getPlanTB().setEditable(false);
            }

            // ST2_ORDER_IN_PLANNING  -  estatus si la orden esta en planeacion
            case "OIPNN" -> {

                getNumeroOrdenTB().setEditable(false);

                getCodigoEstatusTB().setEditable(false);
                getDescripcionEstatusTB().setEditable(false);

                getParadaCB().setEnabled(true);
                getTituloOrdenTB().setEditable(true);

                getBtnDescripcionExtendida().setEnabled(true);
                getDescripcionExtendidaOrdenTB().setEditable(true);

                getCodigoTipoMttoTB().setEditable(false);
                getDescripcionTipoMttoTB().setEditable(false);
                getBtnTipoMtto().setEnabled(true);

                getCodigoClaseOrdenTB().setEditable(false);
                getDescripcionClaseOrdenTB().setEditable(false);
                getBtnClaseMtto().setEnabled(true);

                getCodigoCriticidadTB().setEditable(false);
                getDescripcionCriticidadTB().setEditable(false);
                getBtnCriticidadMtto().setEnabled(true);

                getCodigoSistemasTB().setEditable(false);
                getDescripcionSistemasTB().setEditable(false);
                getBtnSistema().setEnabled(true);

                getCodigoComponenteTB().setEditable(false);
                getDescripcionComponenteTB().setEditable(false);
                getBtnComponente().setEnabled(true);

                getCodigoSintomaTB().setEditable(false);
                getDescripcionSintomaTB().setEditable(false);
                getBtnSintoma().setEnabled(true);

                getCodigoEquipoTB().setEditable(false);
                getBtnEquipo().setEnabled(false);

                getCodigoUbicacionTB().setEditable(false);
                getBtnUbicacion().setEnabled(false);

                ((JTextFieldDateEditor) getFechaInicioReal().getDateEditor()).setEditable(false);
                getFechaInicioReal().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaFinReal().getDateEditor()).setEditable(false);
                getFechaFinReal().getCalendarButton().setEnabled(false);

                getHoraInicioReal().setEditable(false);
                getBtnHoraInicioReal().setEnabled(true);
                getHoraFinReal().setEditable(false);
                getBtnHoraFinReal().setEnabled(true);
                getTotalHoraReales().setEditable(false);

                ((JTextFieldDateEditor) getFechaInicioProgramado().getDateEditor()).setEditable(false);
                getFechaInicioProgramado().getCalendarButton().setEnabled(true);
                
                ((JTextFieldDateEditor) getFechaFinProgramado().getDateEditor()).setEditable(false);
                getFechaFinProgramado().getCalendarButton().setEnabled(true);

                getHoraInicioProgramada().setEditable(false);
                getBtnHoraProgramada().setEnabled(true);
                getHoraFinProgramado().setEditable(false);
                getBtnHoraCierreProg().setEnabled(true);
                getTotalHorasProgramado().setEditable(false);

                ((JTextFieldDateEditor) getFechaCreacionOrden().getDateEditor()).setEditable(false);
                getFechaCreacionOrden().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaAutorizacionOrden().getDateEditor()).setEditable(false);
                getFechaAutorizacionOrden().getCalendarButton().setEnabled(false);
                
                getHoraCreacionOrden().setEditable(false);
                getBtnHoraCreacion().setEnabled(false);
                getHoraAutorizacionOrden().setEditable(false);
                getBtnHoraAutorizada().setEnabled(false);
                getTotalHorasCreacionOrden().setEditable(false);

                getCodigoGrupoPlanTB().setEditable(false);
                
                getCodigoSolicitanteTB().setEditable(false);
                getBtnSolicitante().setEnabled(false);

                getCodigoResponsableTB().setEditable(false);
                getBtnResponsable().setEnabled(false);

                getBtnAgregarLineaOperacion().setEnabled(true);
                getBtnOkOperacion().setEnabled(true);
                getBtnEditarLineaOperacion().setEnabled(true);
                getBtnEliminarLineaOperacion().setEnabled(true);

                getBtnAgregarLineaMaterial().setEnabled(true);
                getBtnOkMaterial().setEnabled(true);
                getBtnEditarLineaMaterial().setEnabled(true);
                getBtnEliminarLineaMaterial().setEnabled(true);

                getSociedadTB().setEditable(false);
                getAreaTB().setEditable(false);
                getEmplazamientoTB().setEditable(false);
                getCentroCostosTB().setEditable(false);

                getContadorTB().setEditable(false);
                getValorContadorTB().setEditable(false);
                getHojaRutaTB().setEditable(false);
                getPlanTB().setEditable(false);
            }

            //ST3_ORDER_UNDER_APPROVAL  -  Orden en estatus Orden en proceso de aprobacion
            case "OUAPP" -> {

                getNumeroOrdenTB().setEditable(false);

                getCodigoEstatusTB().setEditable(false);
                getDescripcionEstatusTB().setEditable(false);

                getParadaCB().setEnabled(false);
                getTituloOrdenTB().setEditable(false);

                getBtnDescripcionExtendida().setEnabled(true);
                getDescripcionExtendidaOrdenTB().setEditable(false);

                getCodigoTipoMttoTB().setEditable(false);
                getDescripcionTipoMttoTB().setEditable(false);
                getBtnTipoMtto().setEnabled(false);

                getCodigoClaseOrdenTB().setEditable(false);
                getDescripcionClaseOrdenTB().setEditable(false);
                getBtnClaseMtto().setEnabled(false);

                getCodigoCriticidadTB().setEditable(false);
                getDescripcionCriticidadTB().setEditable(false);
                getBtnCriticidadMtto().setEnabled(false);

                getCodigoSistemasTB().setEditable(false);
                getDescripcionSistemasTB().setEditable(false);
                getBtnSistema().setEnabled(false);

                getCodigoComponenteTB().setEditable(false);
                getDescripcionComponenteTB().setEditable(false);
                getBtnComponente().setEnabled(false);

                getCodigoSintomaTB().setEditable(false);
                getDescripcionSintomaTB().setEditable(false);
                getBtnSintoma().setEnabled(false);

                getCodigoEquipoTB().setEditable(false);
                getBtnEquipo().setEnabled(false);

                getCodigoUbicacionTB().setEditable(false);
                getBtnUbicacion().setEnabled(false);
                

                ((JTextFieldDateEditor) getFechaInicioReal().getDateEditor()).setEditable(false);
                getFechaInicioReal().getCalendarButton().setEnabled(false);
                
                
                ((JTextFieldDateEditor) getFechaFinReal().getDateEditor()).setEditable(false);
                getFechaFinReal().getCalendarButton().setEnabled(false);
                

                getHoraInicioReal().setEditable(false);
                getBtnHoraInicioReal().setEnabled(false);
                getHoraFinReal().setEditable(false);
                getBtnHoraFinReal().setEnabled(false);
                getTotalHoraReales().setEditable(false);

                ((JTextFieldDateEditor) getFechaInicioProgramado().getDateEditor()).setEditable(false);
                getFechaInicioProgramado().getCalendarButton().setEnabled(false);
                
                
                ((JTextFieldDateEditor) getFechaFinProgramado().getDateEditor()).setEditable(false);
                getFechaFinProgramado().getCalendarButton().setEnabled(false);
                

                getHoraInicioProgramada().setEditable(false);
                getBtnHoraProgramada().setEnabled(false);
                getHoraFinProgramado().setEditable(false);
                getBtnHoraCierreProg().setEnabled(false);
                getTotalHorasProgramado().setEditable(false);

                ((JTextFieldDateEditor) getFechaCreacionOrden().getDateEditor()).setEditable(false);
                getFechaCreacionOrden().getCalendarButton().setEnabled(false);
                
                
                ((JTextFieldDateEditor) getFechaAutorizacionOrden().getDateEditor()).setEditable(false);
                getFechaAutorizacionOrden().getCalendarButton().setEnabled(false);
                

                getHoraCreacionOrden().setEditable(false);
                getBtnHoraCreacion().setEnabled(false);
                getHoraAutorizacionOrden().setEditable(false);
                getBtnHoraAutorizada().setEnabled(false);
                getTotalHorasCreacionOrden().setEditable(false);

                getCodigoGrupoPlanTB().setEditable(false);
                getCodigoSolicitanteTB().setEditable(false);
                getBtnSolicitante().setEnabled(false);

                getCodigoResponsableTB().setEditable(false);
                getBtnResponsable().setEnabled(false);

                getBtnAgregarLineaOperacion().setEnabled(false);
                getBtnOkOperacion().setEnabled(false);
                getBtnEditarLineaOperacion().setEnabled(false);
                getBtnEliminarLineaOperacion().setEnabled(false);

                getBtnAgregarLineaMaterial().setEnabled(false);
                getBtnOkMaterial().setEnabled(false);
                getBtnEditarLineaMaterial().setEnabled(false);
                getBtnEliminarLineaMaterial().setEnabled(false);

                getSociedadTB().setEditable(false);
                getAreaTB().setEditable(false);
                getEmplazamientoTB().setEditable(false);
                getCentroCostosTB().setEditable(false);

                getContadorTB().setEditable(false);
                getValorContadorTB().setEditable(false);
                getHojaRutaTB().setEditable(false);
                getPlanTB().setEditable(false);
            }

            //ST4_ORDER_APPROVED  -  Orden en estatus Orden ya aprobada
            case "OAPPV" -> {

                getNumeroOrdenTB().setEditable(false);

                getCodigoEstatusTB().setEditable(false);
                getDescripcionEstatusTB().setEditable(false);

                getParadaCB().setEnabled(false);
                getTituloOrdenTB().setEditable(false);

                getBtnDescripcionExtendida().setEnabled(true);
                getDescripcionExtendidaOrdenTB().setEditable(false);

                getCodigoTipoMttoTB().setEditable(false);
                getDescripcionTipoMttoTB().setEditable(false);
                getBtnTipoMtto().setEnabled(false);

                getCodigoClaseOrdenTB().setEditable(false);
                getDescripcionClaseOrdenTB().setEditable(false);
                getBtnClaseMtto().setEnabled(false);

                getCodigoCriticidadTB().setEditable(false);
                getDescripcionCriticidadTB().setEditable(false);
                getBtnCriticidadMtto().setEnabled(false);

                getCodigoSistemasTB().setEditable(false);
                getDescripcionSistemasTB().setEditable(false);
                getBtnSistema().setEnabled(false);

                getCodigoComponenteTB().setEditable(false);
                getDescripcionComponenteTB().setEditable(false);
                getBtnComponente().setEnabled(false);

                getCodigoSintomaTB().setEditable(false);
                getDescripcionSintomaTB().setEditable(false);
                getBtnSintoma().setEnabled(false);

                getCodigoEquipoTB().setEditable(false);
                getBtnEquipo().setEnabled(false);

                getCodigoUbicacionTB().setEditable(false);
                getBtnUbicacion().setEnabled(false);

                ((JTextFieldDateEditor) getFechaInicioReal().getDateEditor()).setEditable(false);
                getFechaInicioReal().getCalendarButton().setEnabled(true);
                
                
                ((JTextFieldDateEditor) getFechaFinReal().getDateEditor()).setEditable(false);
                getFechaFinReal().getCalendarButton().setEnabled(true);
                

                getHoraInicioReal().setEditable(false);
                getBtnHoraInicioReal().setEnabled(true);
                getHoraFinReal().setEditable(false);
                getBtnHoraFinReal().setEnabled(true);
                getTotalHoraReales().setEditable(false);

                ((JTextFieldDateEditor) getFechaInicioProgramado().getDateEditor()).setEditable(false);
                getFechaInicioProgramado().getCalendarButton().setEnabled(true);
                
                
                ((JTextFieldDateEditor) getFechaFinProgramado().getDateEditor()).setEditable(false);
                getFechaFinProgramado().getCalendarButton().setEnabled(true);
                

                getHoraInicioProgramada().setEditable(false);
                getBtnHoraProgramada().setEnabled(true);
                getHoraFinProgramado().setEditable(false);
                getBtnHoraCierreProg().setEnabled(true);
                getTotalHorasProgramado().setEditable(false);

                ((JTextFieldDateEditor) getFechaCreacionOrden().getDateEditor()).setEditable(false);
                getFechaCreacionOrden().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaAutorizacionOrden().getDateEditor()).setEditable(false);
                getFechaAutorizacionOrden().getCalendarButton().setEnabled(false);
                

                getHoraCreacionOrden().setEditable(false);
                getBtnHoraCreacion().setEnabled(false);
                getHoraAutorizacionOrden().setEditable(false);
                getBtnHoraAutorizada().setEnabled(false);
                getTotalHorasCreacionOrden().setEditable(false);

                getCodigoGrupoPlanTB().setEditable(false);
                getCodigoSolicitanteTB().setEditable(false);
                getBtnSolicitante().setEnabled(false);

                getCodigoResponsableTB().setEditable(false);
                getBtnResponsable().setEnabled(false);

                getBtnAgregarLineaOperacion().setEnabled(false);
                getBtnOkOperacion().setEnabled(false);
                getBtnEditarLineaOperacion().setEnabled(false);
                getBtnEliminarLineaOperacion().setEnabled(false);

                getBtnAgregarLineaMaterial().setEnabled(false);
                getBtnOkMaterial().setEnabled(false);
                getBtnEditarLineaMaterial().setEnabled(false);
                getBtnEliminarLineaMaterial().setEnabled(false);

                getSociedadTB().setEditable(false);
                getAreaTB().setEditable(false);
                getEmplazamientoTB().setEditable(false);
                getCentroCostosTB().setEditable(false);

                getContadorTB().setEditable(false);
                getValorContadorTB().setEditable(false);
                getHojaRutaTB().setEditable(false);
                getPlanTB().setEditable(false);
            }

            //ST5_SCHEDULED_ORDER - Orden en estatus programada a la espera de ejecucion
            case "OSCHD" -> {

                getNumeroOrdenTB().setEditable(false);

                getCodigoEstatusTB().setEditable(false);
                getDescripcionEstatusTB().setEditable(false);

                getParadaCB().setEnabled(true);
                getTituloOrdenTB().setEditable(false);

                getBtnDescripcionExtendida().setEnabled(true);
                getDescripcionExtendidaOrdenTB().setEditable(false);

                getCodigoTipoMttoTB().setEditable(false);
                getDescripcionTipoMttoTB().setEditable(false);
                getBtnTipoMtto().setEnabled(false);

                getCodigoClaseOrdenTB().setEditable(false);
                getDescripcionClaseOrdenTB().setEditable(false);
                getBtnClaseMtto().setEnabled(false);

                getCodigoCriticidadTB().setEditable(false);
                getDescripcionCriticidadTB().setEditable(false);
                getBtnCriticidadMtto().setEnabled(false);

                getCodigoSistemasTB().setEditable(false);
                getDescripcionSistemasTB().setEditable(false);
                getBtnSistema().setEnabled(false);

                getCodigoComponenteTB().setEditable(false);
                getDescripcionComponenteTB().setEditable(false);
                getBtnComponente().setEnabled(false);

                getCodigoSintomaTB().setEditable(false);
                getDescripcionSintomaTB().setEditable(false);
                getBtnSintoma().setEnabled(false);

                getCodigoEquipoTB().setEditable(false);
                getBtnEquipo().setEnabled(false);

                getCodigoUbicacionTB().setEditable(false);
                getBtnUbicacion().setEnabled(false);

                ((JTextFieldDateEditor) getFechaInicioReal().getDateEditor()).setEditable(false);
                getFechaInicioReal().getCalendarButton().setEnabled(true);
                
                ((JTextFieldDateEditor) getFechaFinReal().getDateEditor()).setEditable(false);
                getFechaFinReal().getCalendarButton().setEnabled(true);
                

                getHoraInicioReal().setEditable(false);
                getBtnHoraInicioReal().setEnabled(true);
                getHoraFinReal().setEditable(false);
                getBtnHoraFinReal().setEnabled(true);
                getTotalHoraReales().setEditable(false);

                ((JTextFieldDateEditor) getFechaInicioProgramado().getDateEditor()).setEditable(false);
                getFechaInicioProgramado().setEnabled(true);
                
                ((JTextFieldDateEditor) getFechaFinProgramado().getDateEditor()).setEditable(false);
                getFechaFinProgramado().setEnabled(true);
                

                getHoraInicioProgramada().setEditable(false);
                getBtnHoraProgramada().setEnabled(true);
                getHoraFinProgramado().setEditable(false);
                getBtnHoraCierreProg().setEnabled(true);
                getTotalHorasProgramado().setEditable(false);

                ((JTextFieldDateEditor) getFechaCreacionOrden().getDateEditor()).setEditable(false);
                getFechaCreacionOrden().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaAutorizacionOrden().getDateEditor()).setEditable(false);
                getFechaAutorizacionOrden().getCalendarButton().setEnabled(false);
                

                getHoraCreacionOrden().setEditable(false);
                getBtnHoraCreacion().setEnabled(false);
                getHoraAutorizacionOrden().setEditable(false);
                getBtnHoraAutorizada().setEnabled(false);
                getTotalHorasCreacionOrden().setEditable(false);

                getCodigoGrupoPlanTB().setEditable(false);
                getCodigoSolicitanteTB().setEditable(false);
                getBtnSolicitante().setEnabled(false);

                getCodigoResponsableTB().setEditable(false);
                getBtnResponsable().setEnabled(false);

                getBtnAgregarLineaOperacion().setEnabled(false);
                getBtnOkOperacion().setEnabled(false);
                getBtnEditarLineaOperacion().setEnabled(false);
                getBtnEliminarLineaOperacion().setEnabled(false);

                getBtnAgregarLineaMaterial().setEnabled(false);
                getBtnOkMaterial().setEnabled(false);
                getBtnEditarLineaMaterial().setEnabled(false);
                getBtnEliminarLineaMaterial().setEnabled(false);

                getSociedadTB().setEditable(false);
                getAreaTB().setEditable(false);
                getEmplazamientoTB().setEditable(false);
                getCentroCostosTB().setEditable(false);

                getContadorTB().setEditable(false);
                getValorContadorTB().setEditable(false);
                getHojaRutaTB().setEditable(false);
                getPlanTB().setEditable(false);
            }

            //ST6_ORDER_IN_EXECUTION  -  Orden en estatus de eejecucion
            case "OIEXN" -> {

                getNumeroOrdenTB().setEditable(false);

                getCodigoEstatusTB().setEditable(false);
                getDescripcionEstatusTB().setEditable(false);

                getParadaCB().setEnabled(true);
                getTituloOrdenTB().setEditable(false);

                getBtnDescripcionExtendida().setEnabled(true);
                getDescripcionExtendidaOrdenTB().setEditable(false);

                getCodigoTipoMttoTB().setEditable(false);
                getDescripcionTipoMttoTB().setEditable(false);
                getBtnTipoMtto().setEnabled(false);

                getCodigoClaseOrdenTB().setEditable(false);
                getDescripcionClaseOrdenTB().setEditable(false);
                getBtnClaseMtto().setEnabled(false);

                getCodigoCriticidadTB().setEditable(false);
                getDescripcionCriticidadTB().setEditable(false);
                getBtnCriticidadMtto().setEnabled(false);

                getCodigoSistemasTB().setEditable(false);
                getDescripcionSistemasTB().setEditable(false);
                getBtnSistema().setEnabled(false);

                getCodigoComponenteTB().setEditable(false);
                getDescripcionComponenteTB().setEditable(false);
                getBtnComponente().setEnabled(false);

                getCodigoSintomaTB().setEditable(false);
                getDescripcionSintomaTB().setEditable(false);
                getBtnSintoma().setEnabled(false);

                getCodigoEquipoTB().setEditable(false);
                getBtnEquipo().setEnabled(false);

                getCodigoUbicacionTB().setEditable(false);
                getBtnUbicacion().setEnabled(false);

                ((JTextFieldDateEditor) getFechaInicioReal().getDateEditor()).setEditable(false);
                getFechaInicioReal().getCalendarButton().setEnabled(true);
                
                ((JTextFieldDateEditor) getFechaFinReal().getDateEditor()).setEditable(false);
                getFechaFinReal().getCalendarButton().setEnabled(true);
                

                getHoraInicioReal().setEditable(false);
                getBtnHoraInicioReal().setEnabled(true);
                getHoraFinReal().setEditable(false);
                getBtnHoraFinReal().setEnabled(true);
                getTotalHoraReales().setEditable(false);

                ((JTextFieldDateEditor) getFechaInicioProgramado().getDateEditor()).setEditable(false);
                getFechaInicioProgramado().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaFinProgramado().getDateEditor()).setEditable(false);
                getFechaFinProgramado().getCalendarButton().setEnabled(false);
                

                getHoraInicioProgramada().setEditable(false);
                getBtnHoraProgramada().setEnabled(false);
                getHoraFinProgramado().setEditable(false);
                getBtnHoraCierreProg().setEnabled(false);
                getTotalHorasProgramado().setEditable(false);

                ((JTextFieldDateEditor) getFechaCreacionOrden().getDateEditor()).setEditable(false);
                getFechaCreacionOrden().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaAutorizacionOrden().getDateEditor()).setEditable(false);
                getFechaAutorizacionOrden().getCalendarButton().setEnabled(false);
                

                getHoraCreacionOrden().setEditable(false);
                getBtnHoraCreacion().setEnabled(false);
                getHoraAutorizacionOrden().setEditable(false);
                getBtnHoraAutorizada().setEnabled(false);
                getTotalHorasCreacionOrden().setEditable(false);

                getCodigoGrupoPlanTB().setEditable(false);
                getCodigoSolicitanteTB().setEditable(false);
                getBtnSolicitante().setEnabled(false);

                getCodigoResponsableTB().setEditable(false);
                getBtnResponsable().setEnabled(false);

                getBtnAgregarLineaOperacion().setEnabled(false);
                getBtnOkOperacion().setEnabled(false);
                getBtnEditarLineaOperacion().setEnabled(false);
                getBtnEliminarLineaOperacion().setEnabled(false);

                getBtnAgregarLineaMaterial().setEnabled(false);
                getBtnOkMaterial().setEnabled(false);
                getBtnEditarLineaMaterial().setEnabled(false);
                getBtnEliminarLineaMaterial().setEnabled(false);

                getSociedadTB().setEditable(false);
                getAreaTB().setEditable(false);
                getEmplazamientoTB().setEditable(false);
                getCentroCostosTB().setEditable(false);

                getContadorTB().setEditable(false);
                getValorContadorTB().setEditable(false);
                getHojaRutaTB().setEditable(false);
                getPlanTB().setEditable(false);
            }

            //ST7_ORDER_EXECUTED  - Estatus orden ejeutada
            case "OEXTD" -> {

                getNumeroOrdenTB().setEditable(false);

                getCodigoEstatusTB().setEditable(false);
                getDescripcionEstatusTB().setEditable(false);

                getParadaCB().setEnabled(false);
                getTituloOrdenTB().setEditable(false);

                getBtnDescripcionExtendida().setEnabled(true);
                getDescripcionExtendidaOrdenTB().setEditable(false);

                getCodigoTipoMttoTB().setEditable(false);
                getDescripcionTipoMttoTB().setEditable(false);
                getBtnTipoMtto().setEnabled(false);

                getCodigoClaseOrdenTB().setEditable(false);
                getDescripcionClaseOrdenTB().setEditable(false);
                getBtnClaseMtto().setEnabled(false);

                getCodigoCriticidadTB().setEditable(false);
                getDescripcionCriticidadTB().setEditable(false);
                getBtnCriticidadMtto().setEnabled(false);

                getCodigoSistemasTB().setEditable(false);
                getDescripcionSistemasTB().setEditable(false);
                getBtnSistema().setEnabled(false);

                getCodigoComponenteTB().setEditable(false);
                getDescripcionComponenteTB().setEditable(false);
                getBtnComponente().setEnabled(false);

                getCodigoSintomaTB().setEditable(false);
                getDescripcionSintomaTB().setEditable(false);
                getBtnSintoma().setEnabled(false);

                getCodigoEquipoTB().setEditable(false);
                getBtnEquipo().setEnabled(false);

                getCodigoUbicacionTB().setEditable(false);
                getBtnUbicacion().setEnabled(false);

                ((JTextFieldDateEditor) getFechaInicioReal().getDateEditor()).setEditable(false);
                getFechaInicioReal().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaFinReal().getDateEditor()).setEditable(false);
                getFechaFinReal().getCalendarButton().setEnabled(false);
                

                getHoraInicioReal().setEditable(false);
                getBtnHoraInicioReal().setEnabled(false);
                getHoraFinReal().setEditable(false);
                getBtnHoraFinReal().setEnabled(false);
                getTotalHoraReales().setEditable(false);

                ((JTextFieldDateEditor) getFechaInicioProgramado().getDateEditor()).setEditable(false);
                getFechaInicioProgramado().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaFinProgramado().getDateEditor()).setEditable(false);
                getFechaFinProgramado().getCalendarButton().setEnabled(false);
                

                getHoraInicioProgramada().setEditable(false);
                getBtnHoraProgramada().setEnabled(false);
                getHoraFinProgramado().setEditable(false);
                getBtnHoraCierreProg().setEnabled(false);
                getTotalHorasProgramado().setEditable(false);

                ((JTextFieldDateEditor) getFechaCreacionOrden().getDateEditor()).setEditable(false);
                getFechaCreacionOrden().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaAutorizacionOrden().getDateEditor()).setEditable(false);
                getFechaAutorizacionOrden().getCalendarButton().setEnabled(false);
                

                getHoraCreacionOrden().setEditable(false);
                getBtnHoraCreacion().setEnabled(false);
                getHoraAutorizacionOrden().setEditable(false);
                getBtnHoraAutorizada().setEnabled(false);
                getTotalHorasCreacionOrden().setEditable(false);

                getCodigoGrupoPlanTB().setEditable(false);
                getCodigoSolicitanteTB().setEditable(false);
                getBtnSolicitante().setEnabled(false);

                getCodigoResponsableTB().setEditable(false);
                getBtnResponsable().setEnabled(false);

                getBtnAgregarLineaOperacion().setEnabled(false);
                getBtnOkOperacion().setEnabled(false);
                getBtnEditarLineaOperacion().setEnabled(false);
                getBtnEliminarLineaOperacion().setEnabled(false);

                getBtnAgregarLineaMaterial().setEnabled(false);
                getBtnOkMaterial().setEnabled(false);
                getBtnEditarLineaMaterial().setEnabled(false);
                getBtnEliminarLineaMaterial().setEnabled(false);

                getSociedadTB().setEditable(false);
                getAreaTB().setEditable(false);
                getEmplazamientoTB().setEditable(false);
                getCentroCostosTB().setEditable(false);

                getContadorTB().setEditable(false);
                getValorContadorTB().setEditable(false);
                getHojaRutaTB().setEditable(false);
                getPlanTB().setEditable(false);
            }

            //ST8_CLOSED_ORDER - Orden en estatus cerrada
            case "OCLSD" -> {

                getNumeroOrdenTB().setEditable(false);

                getCodigoEstatusTB().setEditable(false);
                getDescripcionEstatusTB().setEditable(false);

                getParadaCB().setEnabled(false);
                getTituloOrdenTB().setEditable(false);

                getBtnDescripcionExtendida().setEnabled(true);
                getDescripcionExtendidaOrdenTB().setEditable(false);

                getCodigoTipoMttoTB().setEditable(false);
                getDescripcionTipoMttoTB().setEditable(false);
                getBtnTipoMtto().setEnabled(false);

                getCodigoClaseOrdenTB().setEditable(false);
                getDescripcionClaseOrdenTB().setEditable(false);
                getBtnClaseMtto().setEnabled(false);

                getCodigoCriticidadTB().setEditable(false);
                getDescripcionCriticidadTB().setEditable(false);
                getBtnCriticidadMtto().setEnabled(false);

                getCodigoSistemasTB().setEditable(false);
                getDescripcionSistemasTB().setEditable(false);
                getBtnSistema().setEnabled(false);

                getCodigoComponenteTB().setEditable(false);
                getDescripcionComponenteTB().setEditable(false);
                getBtnComponente().setEnabled(false);

                getCodigoSintomaTB().setEditable(false);
                getDescripcionSintomaTB().setEditable(false);
                getBtnSintoma().setEnabled(false);

                getCodigoEquipoTB().setEditable(false);
                getBtnEquipo().setEnabled(false);

                getCodigoUbicacionTB().setEditable(false);
                getBtnUbicacion().setEnabled(false);

                ((JTextFieldDateEditor) getFechaInicioReal().getDateEditor()).setEditable(false);
                getFechaInicioReal().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaFinReal().getDateEditor()).setEditable(false);
                getFechaFinReal().getCalendarButton().setEnabled(false);
                

                getHoraInicioReal().setEditable(false);
                getBtnHoraInicioReal().setEnabled(false);
                getHoraFinReal().setEditable(false);
                getBtnHoraFinReal().setEnabled(false);
                getTotalHoraReales().setEditable(false);

                ((JTextFieldDateEditor) getFechaInicioProgramado().getDateEditor()).setEditable(false);
                getFechaInicioProgramado().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaFinProgramado().getDateEditor()).setEditable(false);
                getFechaFinProgramado().getCalendarButton().setEnabled(false);
                

                getHoraInicioProgramada().setEditable(false);
                getBtnHoraProgramada().setEnabled(false);
                getHoraFinProgramado().setEditable(false);
                getBtnHoraCierreProg().setEnabled(false);
                getTotalHorasProgramado().setEditable(false);

                
                ((JTextFieldDateEditor) getFechaCreacionOrden().getDateEditor()).setEditable(false);
                getFechaCreacionOrden().getCalendarButton().setEnabled(false);
                
                
                ((JTextFieldDateEditor) getFechaAutorizacionOrden().getDateEditor()).setEditable(false);
                getFechaAutorizacionOrden().getCalendarButton().setEnabled(false);
                

                getHoraCreacionOrden().setEditable(false);
                getBtnHoraCreacion().setEnabled(false);
                getHoraAutorizacionOrden().setEditable(false);
                getBtnHoraAutorizada().setEnabled(false);
                getTotalHorasCreacionOrden().setEditable(false);

                getCodigoGrupoPlanTB().setEditable(false);
                getCodigoSolicitanteTB().setEditable(false);
                getBtnSolicitante().setEnabled(false);

                getCodigoResponsableTB().setEditable(false);
                getBtnResponsable().setEnabled(false);

                getBtnAgregarLineaOperacion().setEnabled(false);
                getBtnOkOperacion().setEnabled(false);
                getBtnEditarLineaOperacion().setEnabled(false);
                getBtnEliminarLineaOperacion().setEnabled(false);

                getBtnAgregarLineaMaterial().setEnabled(false);
                getBtnOkMaterial().setEnabled(false);
                getBtnEditarLineaMaterial().setEnabled(false);
                getBtnEliminarLineaMaterial().setEnabled(false);

                getSociedadTB().setEditable(false);
                getAreaTB().setEditable(false);
                getEmplazamientoTB().setEditable(false);
                getCentroCostosTB().setEditable(false);

                getContadorTB().setEditable(false);
                getValorContadorTB().setEditable(false);
                getHojaRutaTB().setEditable(false);
                getPlanTB().setEditable(false);
            }

            //ST9_ORDER_REJECTED  -  Orden en estatus rechazada
            case "ORJTD" -> {

                getNumeroOrdenTB().setEditable(false);

                getCodigoEstatusTB().setEditable(false);
                getDescripcionEstatusTB().setEditable(false);

                getParadaCB().setEnabled(false);
                getTituloOrdenTB().setEditable(false);

                getBtnDescripcionExtendida().setEnabled(true);
                getDescripcionExtendidaOrdenTB().setEditable(false);

                getCodigoTipoMttoTB().setEditable(false);
                getDescripcionTipoMttoTB().setEditable(false);
                getBtnTipoMtto().setEnabled(false);

                getCodigoClaseOrdenTB().setEditable(false);
                getDescripcionClaseOrdenTB().setEditable(false);
                getBtnClaseMtto().setEnabled(false);

                getCodigoCriticidadTB().setEditable(false);
                getDescripcionCriticidadTB().setEditable(false);
                getBtnCriticidadMtto().setEnabled(false);

                getCodigoSistemasTB().setEditable(false);
                getDescripcionSistemasTB().setEditable(false);
                getBtnSistema().setEnabled(false);

                getCodigoComponenteTB().setEditable(false);
                getDescripcionComponenteTB().setEditable(false);
                getBtnComponente().setEnabled(false);

                getCodigoSintomaTB().setEditable(false);
                getDescripcionSintomaTB().setEditable(false);
                getBtnSintoma().setEnabled(false);

                getCodigoEquipoTB().setEditable(false);
                getBtnEquipo().setEnabled(false);

                getCodigoUbicacionTB().setEditable(false);
                getBtnUbicacion().setEnabled(false);

                ((JTextFieldDateEditor) getFechaInicioReal().getDateEditor()).setEditable(false);
                getFechaInicioReal().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaFinReal().getDateEditor()).setEditable(false);
                getFechaFinReal().getCalendarButton().setEnabled(false);
                

                getHoraInicioReal().setEditable(false);
                getBtnHoraInicioReal().setEnabled(false);
                getHoraFinReal().setEditable(false);
                getBtnHoraFinReal().setEnabled(false);
                getTotalHoraReales().setEditable(false);

                ((JTextFieldDateEditor) getFechaInicioProgramado().getDateEditor()).setEditable(false);
                getFechaInicioProgramado().getCalendarButton().setEnabled(false);
                
                ((JTextFieldDateEditor) getFechaFinProgramado().getDateEditor()).setEditable(false);
                getFechaFinProgramado().getCalendarButton().setEnabled(false);
                

                getHoraInicioProgramada().setEditable(false);
                getBtnHoraProgramada().setEnabled(false);
                getHoraFinProgramado().setEditable(false);
                getBtnHoraCierreProg().setEnabled(false);
                getTotalHorasProgramado().setEditable(false);

                ((JTextFieldDateEditor) getFechaCreacionOrden().getDateEditor()).setEditable(false);
                getFechaCreacionOrden().getCalendarButton().setEnabled(true);
                
                ((JTextFieldDateEditor) getFechaAutorizacionOrden().getDateEditor()).setEditable(false);
                getFechaAutorizacionOrden().getCalendarButton().setEnabled(true);
                

                getHoraCreacionOrden().setEditable(false);
                getBtnHoraCreacion().setEnabled(false);
                getHoraAutorizacionOrden().setEditable(false);
                getBtnHoraAutorizada().setEnabled(false);
                getTotalHorasCreacionOrden().setEditable(false);

                getCodigoGrupoPlanTB().setEditable(false);
                getCodigoSolicitanteTB().setEditable(false);
                getBtnSolicitante().setEnabled(false);

                getCodigoResponsableTB().setEditable(false);
                getBtnResponsable().setEnabled(false);

                getBtnAgregarLineaOperacion().setEnabled(false);
                getBtnOkOperacion().setEnabled(false);
                getBtnEditarLineaOperacion().setEnabled(false);
                getBtnEliminarLineaOperacion().setEnabled(false);

                getBtnAgregarLineaMaterial().setEnabled(false);
                getBtnOkMaterial().setEnabled(false);
                getBtnEditarLineaMaterial().setEnabled(false);
                getBtnEliminarLineaMaterial().setEnabled(false);

                getSociedadTB().setEditable(false);
                getAreaTB().setEditable(false);
                getEmplazamientoTB().setEditable(false);
                getCentroCostosTB().setEditable(false);

                getContadorTB().setEditable(false);
                getValorContadorTB().setEditable(false);
                getHojaRutaTB().setEditable(false);
                getPlanTB().setEditable(false);
            }

            //ST10_ORDER_CANCELED - Orden cancelada
            case "OCCLD" -> {

                getNumeroOrdenTB().setEditable(false);

                getCodigoEstatusTB().setEditable(false);
                getDescripcionEstatusTB().setEditable(false);

                getParadaCB().setEnabled(false);
                getTituloOrdenTB().setEditable(false);

                getBtnDescripcionExtendida().setEnabled(false);
                getDescripcionExtendidaOrdenTB().setEditable(false);

                getCodigoTipoMttoTB().setEditable(false);
                getDescripcionTipoMttoTB().setEditable(false);
                getBtnTipoMtto().setEnabled(false);

                getCodigoClaseOrdenTB().setEditable(false);
                getDescripcionClaseOrdenTB().setEditable(false);
                getBtnClaseMtto().setEnabled(false);

                getCodigoCriticidadTB().setEditable(false);
                getDescripcionCriticidadTB().setEditable(false);
                getBtnCriticidadMtto().setEnabled(false);

                getCodigoSistemasTB().setEditable(false);
                getDescripcionSistemasTB().setEditable(false);
                getBtnSistema().setEnabled(false);

                getCodigoComponenteTB().setEditable(false);
                getDescripcionComponenteTB().setEditable(false);
                getBtnComponente().setEnabled(false);

                getCodigoSintomaTB().setEditable(false);
                getDescripcionSintomaTB().setEditable(false);
                getBtnSintoma().setEnabled(false);

                getCodigoEquipoTB().setEditable(false);
                getBtnEquipo().setEnabled(false);

                getCodigoUbicacionTB().setEditable(false);
                getBtnUbicacion().setEnabled(false);

                ((JTextFieldDateEditor) getFechaInicioReal().getDateEditor()).setEditable(false);
                ((JTextFieldDateEditor) getFechaFinReal().getDateEditor()).setEditable(false);

                getHoraInicioReal().setEditable(false);
                getBtnHoraInicioReal().setEnabled(false);
                getHoraFinReal().setEditable(false);
                getBtnHoraFinReal().setEnabled(false);
                getTotalHoraReales().setEditable(false);

                ((JTextFieldDateEditor) getFechaInicioProgramado().getDateEditor()).setEditable(false);
                ((JTextFieldDateEditor) getFechaFinProgramado().getDateEditor()).setEditable(false);

                getHoraInicioProgramada().setEditable(false);
                getBtnHoraProgramada().setEnabled(false);
                getHoraFinProgramado().setEditable(false);
                getBtnHoraCierreProg().setEnabled(false);
                getTotalHorasProgramado().setEditable(false);

                ((JTextFieldDateEditor) getFechaCreacionOrden().getDateEditor()).setEditable(false);
                ((JTextFieldDateEditor) getFechaAutorizacionOrden().getDateEditor()).setEditable(false);

                getHoraCreacionOrden().setEditable(false);
                getBtnHoraCreacion().setEnabled(false);
                getHoraAutorizacionOrden().setEditable(false);
                getBtnHoraAutorizada().setEnabled(false);
                getTotalHorasCreacionOrden().setEditable(false);

                getCodigoGrupoPlanTB().setEditable(false);
                getCodigoSolicitanteTB().setEditable(false);
                getBtnSolicitante().setEnabled(false);

                getCodigoResponsableTB().setEditable(false);
                getBtnResponsable().setEnabled(false);

                getBtnAgregarLineaOperacion().setEnabled(false);
                getBtnOkOperacion().setEnabled(false);
                getBtnEditarLineaOperacion().setEnabled(false);
                getBtnEliminarLineaOperacion().setEnabled(false);

                getBtnAgregarLineaMaterial().setEnabled(false);
                getBtnOkMaterial().setEnabled(false);
                getBtnEditarLineaMaterial().setEnabled(false);
                getBtnEliminarLineaMaterial().setEnabled(false);

                getSociedadTB().setEditable(false);
                getAreaTB().setEditable(false);
                getEmplazamientoTB().setEditable(false);
                getCentroCostosTB().setEditable(false);

                getContadorTB().setEditable(false);
                getValorContadorTB().setEditable(false);
                getHojaRutaTB().setEditable(false);
                getPlanTB().setEditable(false);
            }

        }

    }

    public JTextField getNumeroOrdenTB() {
        return numeroOrdenTB;
    }

    public JTextField getCodigoEstatusTB() {
        return codigoEstatusTB;
    }

    public JTextField getDescripcionEstatusTB() {
        return descripcionEstatusTB;
    }

    public JCheckBox getParadaCB() {
        return paradaCB;
    }

    public JTextField getTituloOrdenTB() {
        return tituloOrdenTB;
    }

    public JButton getBtnDescripcionExtendida() {
        return btnDescripcionExtendida;
    }

    public JTextArea getDescripcionExtendidaOrdenTB() {
        return descripcionExtendidaOrdenTB;
    }

    public JTextField getCodigoTipoMttoTB() {
        return codigoTipoMttoTB;
    }

    public JTextField getDescripcionTipoMttoTB() {
        return descripcionTipoMttoTB;
    }

    public JButton getBtnTipoMtto() {
        return btnTipoMtto;
    }

    public JTextField getCodigoClaseOrdenTB() {
        return codigoClaseOrdenTB;
    }

    public JTextField getDescripcionClaseOrdenTB() {
        return descripcionClaseOrdenTB;
    }

    public JButton getBtnClaseMtto() {
        return btnClaseMtto;
    }

    public JTextField getCodigoCriticidadTB() {
        return codigoCriticidadTB;
    }

    public JTextField getDescripcionCriticidadTB() {
        return descripcionCriticidadTB;
    }

    public JButton getBtnCriticidadMtto() {
        return btnCriticidadMtto;
    }

    public JTextField getCodigoSistemasTB() {
        return codigoSistemasTB;
    }

    public JTextField getDescripcionSistemasTB() {
        return descripcionSistemasTB;
    }

    public JButton getBtnSistema() {
        return btnSistema;
    }

    public JTextField getCodigoComponenteTB() {
        return codigoComponenteTB;
    }

    public JTextField getDescripcionComponenteTB() {
        return descripcionComponenteTB;
    }

    public JButton getBtnComponente() {
        return btnComponente;
    }

    public JTextField getCodigoSintomaTB() {
        return codigoSintomaTB;
    }

    public JTextField getDescripcionSintomaTB() {
        return descripcionSintomaTB;
    }

    public JButton getBtnSintoma() {
        return btnSintoma;
    }

    public JTextField getCodigoEquipoTB() {
        return codigoEquipoTB;
    }

    public JButton getBtnEquipo() {
        return btnEquipo;
    }

    public JTextField getCodigoUbicacionTB() {
        return codigoUbicacionTB;
    }

    public JButton getBtnUbicacion() {
        return btnUbicacion;
    }

    public JDateChooser getFechaInicioReal() {
        return fechaInicioReal;
    }

    public JDateChooser getFechaFinReal() {
        return fechaFinReal;
    }

    public JTextField getHoraInicioReal() {
        return horaInicioReal;
    }

    public JButton getBtnHoraInicioReal() {
        return btnHoraInicioReal;
    }

    public JTextField getHoraFinReal() {
        return horaFinReal;
    }

    public JButton getBtnHoraFinReal() {
        return btnHoraFinReal;
    }

    public JTextField getTotalHoraReales() {
        return totalHoraReales;
    }

    public JDateChooser getFechaInicioProgramado() {
        return fechaInicioProgramado;
    }

    public JDateChooser getFechaFinProgramado() {
        return fechaFinProgramado;
    }

    public JTextField getHoraInicioProgramada() {
        return horaInicioProgramada;
    }

    public JButton getBtnHoraProgramada() {
        return btnHoraProgramada;
    }

    public JTextField getHoraFinProgramado() {
        return horaFinProgramado;
    }

    public JButton getBtnHoraCierreProg() {
        return btnHoraCierreProg;
    }

    public JTextField getTotalHorasProgramado() {
        return totalHorasProgramado;
    }

    public JDateChooser getFechaCreacionOrden() {
        return fechaCreacionOrden;
    }

    public JDateChooser getFechaAutorizacionOrden() {
        return fechaAutorizacionOrden;
    }

    public JTextField getHoraCreacionOrden() {
        return horaCreacionOrden;
    }

    public JButton getBtnHoraCreacion() {
        return btnHoraCreacion;
    }

    public JTextField getHoraAutorizacionOrden() {
        return horaAutorizacionOrden;
    }

    public JButton getBtnHoraAutorizada() {
        return btnHoraAutorizada;
    }

    public JTextField getTotalHorasCreacionOrden() {
        return totalHorasCreacionOrden;
    }

    public JTextField getCodigoGrupoPlanTB() {
        return codigoGrupoPlanTB;
    }

    public JTextField getCodigoSolicitanteTB() {
        return codigoSolicitanteTB;
    }

    public JButton getBtnSolicitante() {
        return btnSolicitante;
    }

    public JTextField getCodigoResponsableTB() {
        return codigoResponsableTB;
    }

    public JButton getBtnResponsable() {
        return btnResponsable;
    }

    public JButton getBtnAgregarLineaOperacion() {
        return btnAgregarLineaOperacion;
    }

    public JButton getBtnOkOperacion() {
        return btnOkOperacion;
    }

    public JButton getBtnEditarLineaOperacion() {
        return btnEditarLineaOperacion;
    }

    public JButton getBtnEliminarLineaOperacion() {
        return btnEliminarLineaOperacion;
    }

    public JButton getBtnAgregarLineaMaterial() {
        return btnAgregarLineaMaterial;
    }

    public JButton getBtnOkMaterial() {
        return btnOkMaterial;
    }

    public JButton getBtnEditarLineaMaterial() {
        return btnEditarLineaMaterial;
    }

    public JButton getBtnEliminarLineaMaterial() {
        return btnEliminarLineaMaterial;
    }

    public JTextField getSociedadTB() {
        return sociedadTB;
    }

    public JTextField getAreaTB() {
        return areaTB;
    }

    public JTextField getEmplazamientoTB() {
        return emplazamientoTB;
    }

    public JTextField getCentroCostosTB() {
        return centroCostosTB;
    }

    public JTextField getContadorTB() {
        return contadorTB;
    }

    public JTextField getValorContadorTB() {
        return valorContadorTB;
    }

    public JTextField getHojaRutaTB() {
        return hojaRutaTB;
    }

    public JTextField getPlanTB() {
        return planTB;
    }

    public void setNumeroOrdenTB(JTextField numeroOrdenTB) {
        this.numeroOrdenTB = numeroOrdenTB;
    }

    public void setCodigoEstatusTB(JTextField codigoEstatusTB) {
        this.codigoEstatusTB = codigoEstatusTB;
    }

    public void setDescripcionEstatusTB(JTextField descripcionEstatusTB) {
        this.descripcionEstatusTB = descripcionEstatusTB;
    }

    public void setParadaCB(JCheckBox paradaCB) {
        this.paradaCB = paradaCB;
    }

    public void setTituloOrdenTB(JTextField tituloOrdenTB) {
        this.tituloOrdenTB = tituloOrdenTB;
    }

    public void setBtnDescripcionExtendida(JButton btnDescripcionExtendida) {
        this.btnDescripcionExtendida = btnDescripcionExtendida;
    }

    public void setDescripcionExtendidaOrdenTB(JTextArea descripcionExtendidaOrdenTB) {
        this.descripcionExtendidaOrdenTB = descripcionExtendidaOrdenTB;
    }

    public void setCodigoTipoMttoTB(JTextField codigoTipoMttoTB) {
        this.codigoTipoMttoTB = codigoTipoMttoTB;
    }

    public void setDescripcionTipoMttoTB(JTextField descripcionTipoMttoTB) {
        this.descripcionTipoMttoTB = descripcionTipoMttoTB;
    }

    public void setBtnTipoMtto(JButton btnTipoMtto) {
        this.btnTipoMtto = btnTipoMtto;
    }

    public void setCodigoClaseOrdenTB(JTextField codigoClaseOrdenTB) {
        this.codigoClaseOrdenTB = codigoClaseOrdenTB;
    }

    public void setDescripcionClaseOrdenTB(JTextField descripcionClaseOrdenTB) {
        this.descripcionClaseOrdenTB = descripcionClaseOrdenTB;
    }

    public void setBtnClaseMtto(JButton btnClaseMtto) {
        this.btnClaseMtto = btnClaseMtto;
    }

    public void setCodigoCriticidadTB(JTextField codigoCriticidadTB) {
        this.codigoCriticidadTB = codigoCriticidadTB;
    }

    public void setDescripcionCriticidadTB(JTextField descripcionCriticidadTB) {
        this.descripcionCriticidadTB = descripcionCriticidadTB;
    }

    public void setBtnCriticidadMtto(JButton btnCriticidadMtto) {
        this.btnCriticidadMtto = btnCriticidadMtto;
    }

    public void setCodigoSistemasTB(JTextField codigoSistemasTB) {
        this.codigoSistemasTB = codigoSistemasTB;
    }

    public void setDescripcionSistemasTB(JTextField descripcionSistemasTB) {
        this.descripcionSistemasTB = descripcionSistemasTB;
    }

    public void setBtnSistema(JButton btnSistema) {
        this.btnSistema = btnSistema;
    }

    public void setCodigoComponenteTB(JTextField codigoComponenteTB) {
        this.codigoComponenteTB = codigoComponenteTB;
    }

    public void setDescripcionComponenteTB(JTextField descripcionComponenteTB) {
        this.descripcionComponenteTB = descripcionComponenteTB;
    }

    public void setBtnComponente(JButton btnComponente) {
        this.btnComponente = btnComponente;
    }

    public void setCodigoSintomaTB(JTextField codigoSintomaTB) {
        this.codigoSintomaTB = codigoSintomaTB;
    }

    public void setDescripcionSintomaTB(JTextField descripcionSintomaTB) {
        this.descripcionSintomaTB = descripcionSintomaTB;
    }

    public void setBtnSintoma(JButton btnSintoma) {
        this.btnSintoma = btnSintoma;
    }

    public void setCodigoEquipoTB(JTextField codigoEquipoTB) {
        this.codigoEquipoTB = codigoEquipoTB;
    }

    public void setBtnEquipo(JButton btnEquipo) {
        this.btnEquipo = btnEquipo;
    }

    public void setCodigoUbicacionTB(JTextField codigoUbicacionTB) {
        this.codigoUbicacionTB = codigoUbicacionTB;
    }

    public void setBtnUbicacion(JButton btnUbicacion) {
        this.btnUbicacion = btnUbicacion;
    }

    public void setFechaInicioReal(JDateChooser fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
    }

    public void setFechaFinReal(JDateChooser fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }

    public void setHoraInicioReal(JTextField horaInicioReal) {
        this.horaInicioReal = horaInicioReal;
    }

    public void setBtnHoraInicioReal(JButton btnHoraInicioReal) {
        this.btnHoraInicioReal = btnHoraInicioReal;
    }

    public void setHoraFinReal(JTextField horaFinReal) {
        this.horaFinReal = horaFinReal;
    }

    public void setBtnHoraFinReal(JButton btnHoraFinReal) {
        this.btnHoraFinReal = btnHoraFinReal;
    }

    public void setTotalHoraReales(JTextField totalHoraReales) {
        this.totalHoraReales = totalHoraReales;
    }

    public void setFechaInicioProgramado(JDateChooser fechaInicioProgramado) {
        this.fechaInicioProgramado = fechaInicioProgramado;
    }

    public void setFechaFinProgramado(JDateChooser fechaFinProgramado) {
        this.fechaFinProgramado = fechaFinProgramado;
    }

    public void setHoraInicioProgramada(JTextField horaInicioProgramada) {
        this.horaInicioProgramada = horaInicioProgramada;
    }

    public void setBtnHoraProgramada(JButton btnHoraProgramada) {
        this.btnHoraProgramada = btnHoraProgramada;
    }

    public void setHoraFinProgramado(JTextField horaFinProgramado) {
        this.horaFinProgramado = horaFinProgramado;
    }

    public void setBtnHoraCierreProg(JButton btnHoraCierreProg) {
        this.btnHoraCierreProg = btnHoraCierreProg;
    }

    public void setTotalHorasProgramado(JTextField totalHorasProgramado) {
        this.totalHorasProgramado = totalHorasProgramado;
    }

    public void setFechaCreacionOrden(JDateChooser fechaCreacionOrden) {
        this.fechaCreacionOrden = fechaCreacionOrden;
    }

    public void setFechaAutorizacionOrden(JDateChooser fechaAutorizacionOrden) {
        this.fechaAutorizacionOrden = fechaAutorizacionOrden;
    }

    public void setHoraCreacionOrden(JTextField horaCreacionOrden) {
        this.horaCreacionOrden = horaCreacionOrden;
    }

    public void setBtnHoraCreacion(JButton btnHoraCreacion) {
        this.btnHoraCreacion = btnHoraCreacion;
    }

    public void setHoraAutorizacionOrden(JTextField horaAutorizacionOrden) {
        this.horaAutorizacionOrden = horaAutorizacionOrden;
    }

    public void setBtnHoraAutorizada(JButton btnHoraAutorizada) {
        this.btnHoraAutorizada = btnHoraAutorizada;
    }

    public void setTotalHorasCreacionOrden(JTextField totalHorasCreacionOrden) {
        this.totalHorasCreacionOrden = totalHorasCreacionOrden;
    }

    public void setCodigoGrupoPlanTB(JTextField codigoGrupoPlanTB) {
        this.codigoGrupoPlanTB = codigoGrupoPlanTB;
    }

    public void setCodigoSolicitanteTB(JTextField codigoSolicitanteTB) {
        this.codigoSolicitanteTB = codigoSolicitanteTB;
    }

    public void setBtnSolicitante(JButton btnSolicitante) {
        this.btnSolicitante = btnSolicitante;
    }

    public void setCodigoResponsableTB(JTextField codigoResponsableTB) {
        this.codigoResponsableTB = codigoResponsableTB;
    }

    public void setBtnResponsable(JButton btnResponsable) {
        this.btnResponsable = btnResponsable;
    }

    public void setBtnAgregarLineaOperacion(JButton btnAgregarLineaOperacion) {
        this.btnAgregarLineaOperacion = btnAgregarLineaOperacion;
    }

    public void setBtnOkOperacion(JButton btnOkOperacion) {
        this.btnOkOperacion = btnOkOperacion;
    }

    public void setBtnEditarLineaOperacion(JButton btnEditarLineaOperacion) {
        this.btnEditarLineaOperacion = btnEditarLineaOperacion;
    }

    public void setBtnEliminarLineaOperacion(JButton btnEliminarLineaOperacion) {
        this.btnEliminarLineaOperacion = btnEliminarLineaOperacion;
    }

    public void setBtnAgregarLineaMaterial(JButton btnAgregarLineaMaterial) {
        this.btnAgregarLineaMaterial = btnAgregarLineaMaterial;
    }

    public void setBtnOkMaterial(JButton btnOkMaterial) {
        this.btnOkMaterial = btnOkMaterial;
    }

    public void setBtnEditarLineaMaterial(JButton btnEditarLineaMaterial) {
        this.btnEditarLineaMaterial = btnEditarLineaMaterial;
    }

    public void setBtnEliminarLineaMaterial(JButton btnEliminarLineaMaterial) {
        this.btnEliminarLineaMaterial = btnEliminarLineaMaterial;
    }

    public void setSociedadTB(JTextField sociedadTB) {
        this.sociedadTB = sociedadTB;
    }

    public void setAreaTB(JTextField areaTB) {
        this.areaTB = areaTB;
    }

    public void setEmplazamientoTB(JTextField emplazamientoTB) {
        this.emplazamientoTB = emplazamientoTB;
    }

    public void setCentroCostosTB(JTextField centroCostosTB) {
        this.centroCostosTB = centroCostosTB;
    }

    public void setContadorTB(JTextField contadorTB) {
        this.contadorTB = contadorTB;
    }

    public void setValorContadorTB(JTextField valorContadorTB) {
        this.valorContadorTB = valorContadorTB;
    }

    public void setHojaRutaTB(JTextField hojaRutaTB) {
        this.hojaRutaTB = hojaRutaTB;
    }

    public void setPlanTB(JTextField planTB) {
        this.planTB = planTB;
    }

}
