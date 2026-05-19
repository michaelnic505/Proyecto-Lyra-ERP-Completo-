package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o03_display_of_orders;

import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.Check_Modification_Permission;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Ubicaciones;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Texto_Explicativo;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Equipos;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Sistemas;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Tipo_Mtto;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Sintomas;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Personal;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Criticidad;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Componentes;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Clase_Mtto;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import com.simplecore.erp.gui.components.tables.table3.LyraCellRender.AlineacionCeldas;
import com.simplecore.erp.gui.components.tables.table3.LyraCellRender.DimensionesCeldas;
import com.simplecore.erp.gui.components.tablecelleditors.CellEditorNumbers;
import com.simplecore.erp.gui.components.tables.lastversion.CellEditorLyraTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.FullUserName;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.ControlStatusComponentOrder;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.ControlStatusMenuBarOrder;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.StatusOrder;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.materiales_orden_lista_materiales.Cargar_Informacion_Material_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.materiales_orden_lista_materiales.CellEditorSearchButton;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.materiales_orden_lista_materiales.CellRenderSearchButton;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Materiales;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.materiales_orden_lista_materiales.SearchButtonInterface;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.operaciones_orden_texto_explicativo.TableTextoExpEvent;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.operaciones_orden_texto_explicativo.TextoExplicativo_Editor;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.operaciones_orden_texto_explicativo.TextoExplicativo_Render;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.CalculoHorasFechas;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.Combo_Tipo_Operacion_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.Create_Work_Order_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.Datos_Tipo_Operacion_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.Modify_Work_Order_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.Orden_Costos_Model;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.Orden_Materiales_Model;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.Orden_Operaciones_Model;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.TimeSelector;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders.Cargar_Cabecera_Orden_Trabajo_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders.Cargar_Materiales_Orden_Trabajo_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders.Cargar_Operaciones_Orden_Trabajo_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders.Delete_Material_Work_Order;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders.Delete_Operation_Work_Order;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders.Verificar_Registro_Material_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders.Verificar_Registro_Operacion_SQL;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class O03_Visualizacion_Ordenes_Trabajo extends javax.swing.JPanel {

    private JFrame frame;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy.MM.dd");
    private final SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
    private int diasCriticidad = 1;
    private JPanel thisPanel = this;
    private String username;
    private String fullname;

    
    public O03_Visualizacion_Ordenes_Trabajo(String username) {
        this.username=username;
//        this.fullname = FullUserName.getName(username);
        initComponents();
        setTablesModels();
        panelDescripcionExt.setVisible(false);
        setBarraMenu();

    }


    private void setTablesModels() {
        setOperationTableModel();
        setMaterialesTableModel();
        setTotalesTableModel();
    }

    public void addEvents() {

        funcionesBarraMenu();

        funcionesBotonesTablaOperaciones();
        funcionesBotonesTablaMateriales();
        funcionesBotonesTablaCostos();
        formatDate();

        btnTipoMtto();
        btnClaseMtto();
        btnCriticidadMtto();
        btnSalir();
        btnModificarOrden();
        btnEquipo();
        btnUbicacion();

        btnSistema();
        btnComponente();
        btnSintoma();

        btnHoraInicio();
        btnHoraFin();
        btnHoraProgramada();
        btnHoraCierre();

        btnSolicitante();
        btnResponsable();

        btnAgregarLineaOperacion();
        btnOkOperacion();

        btnEditarLineaOperacion();
        comboTipoManoObraOperacion();
        btnEliminarLineaOperacion();

        tablaOperacionesEdicionEvent();
        celdasNoEditableOperaciones();

        btnAgregarLineaMaterial();
        btnOkMaterial();

        btnEditarLineaMaterial();
        btnEliminarLineaMaterial();

        tablaMaterialesEdicionEvent();

        carcularMontosMultiTab();
        // valoresCamposIniciales();

        eventosFechas();
        setTitle();

        //menus de estado de ordenes
        funcionesEstatosOrdenMenu();

    }

    //Funciones de opciones de la barra de menu
    private void funcionesBarraMenu() {

        menuGuardarOrden.addActionListener(modificarOrden);
        menuModificarOrden.addActionListener((e) -> {

        });
        menuNuevaOrden.addActionListener((e) -> {

        });

        menuSalir.addActionListener((e) -> {
            int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_LEAVE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

            }
        });

        menuEquipo.addActionListener(buscarEquipo);
        menuUbicaciones.addActionListener(buscarUbicacion);
        menuTipoMantenimiento.addActionListener(tipoMtto);
        menuClasesMantenimiento.addActionListener(claseMtto);
        menuPrioridades.addActionListener(criticidadMtto);
        menuSistemas.addActionListener(sistemaEquipo);
        menuComponentes.addActionListener(componenteEquipo);
        menuSintomas.addActionListener(sintomaEquipo);

        menuOperaciones.addActionListener((e) -> {
            MULTITAB.setSelectedIndex(1);
        });
        menuMateriales.addActionListener((e) -> {
            MULTITAB.setSelectedIndex(2);
        });
        menuCostos.addActionListener((e) -> {
            MULTITAB.setSelectedIndex(3);
        });

    }

    private void setBarraMenu() {

        frame = (JFrame) SwingUtilities.getRoot(mainContainerPanel);
        frame.setJMenuBar(menuBar_O03);
        frame.repaint();

    }

    public void cargarDatosCabeceraOrden(String orden) {

        try {
            Cargar_Cabecera_Orden_Trabajo_SQL ordenMod = new Cargar_Cabecera_Orden_Trabajo_SQL();
            ordenMod.cargarOrden(orden);

            numeroOrdenTB.setText(orden);

            codigoEstatusTB.setText(ordenMod.getCodigoEstatus());
            descripcionEstatusTB.setText(ordenMod.getDescripcionEstatus());

            tituloOrdenTB.setText(ordenMod.getTituloOrden());
            paradaCB.setSelected(ordenMod.isParoEquipo());
            descripcionExtendidaOrdenTB.setText(ordenMod.getDescripcionExtendidaOrden());

            codigoTipoMttoTB.setText(ordenMod.getCodigoTipoOrden());
            descripcionTipoMttoTB.setText(ordenMod.getDescripcionTipoOrden());

            codigoClaseOrdenTB.setText(ordenMod.getCodigoClaseOrden());
            descripcionClaseOrdenTB.setText(ordenMod.getDescripcionClaseOrden());

            codigoCriticidadTB.setText(ordenMod.getCodigoCriticidad());
            descripcionCriticidadTB.setText(ordenMod.getDescripcionCriticidad());

            codigoSistemasTB.setText(ordenMod.getCodigoSistema());
            descripcionSistemasTB.setText(ordenMod.getDescripcionSistema());

            codigoComponenteTB.setText(ordenMod.getCodigoComponente());
            descripcionComponenteTB.setText(ordenMod.getDescripcionComponente());

            codigoSintomaTB.setText(ordenMod.getCodigoSintoma());
            descripcionSintomaTB.setText(ordenMod.getDescripcionSintoma());

            codigoEquipoTB.setText(ordenMod.getCodigoEquipo());
            descripcionEquipoLB.setText(ordenMod.getDescripcionEquipo());

            codigoUbicacionTB.setText(ordenMod.getCodigoUbicacion());
            descripcionUbicacionLB.setText(ordenMod.getDescripcionUbicacion());

            fechaInicioReal.setDate(formatoFecha.parse(ordenMod.getFechaInicioReal()));
            fechaFinReal.setDate(formatoFecha.parse(ordenMod.getFechaFinReal()));
            horaInicioReal.setText(ordenMod.getHoraInicioReal());
            horaFinReal.setText(ordenMod.getHoraFinReal());
            totalHoraReales.setText(ordenMod.getTotalHoraReales());

            fechaInicioProgramado.setDate(formatoFecha.parse(ordenMod.getFechaInicioProgramado()));
            fechaFinProgramado.setDate(formatoFecha.parse(ordenMod.getFechaFinProgramado()));
            horaInicioProgramada.setText(ordenMod.getHoraInicioProgramada());
            horaFinProgramado.setText(ordenMod.getHoraFinProgramado());

            totalHorasProgramado.setText(ordenMod.getTotalHorasProgramado());

            fechaCreacionOrden.setDate(formatoFecha.parse(ordenMod.getFechaCreacionOrden()));
            if (ordenMod.getFechaAutorizacionOrden() != null) {
                fechaAutorizacionOrden.setDate(formatoFecha.parse(ordenMod.getFechaAutorizacionOrden()));
            } else {
                fechaAutorizacionOrden.setDate(null);
            }

            horaCreacionOrden.setText(ordenMod.getHoraCreacionOrden());
            horaAutorizacionOrden.setText(ordenMod.getHoraAutorizacionOrden());
            totalHorasCreacionOrden.setText(ordenMod.getTotalHorasCreacionOrden());

            codigoGrupoPlanTB.setText(ordenMod.getCodigoGruoPlan());
            descripcionGrupoPlanif.setText(ordenMod.getDescripcionGrupoPlan());

            codigoSolicitanteTB.setText(ordenMod.getCodigoSolicitante());
            descripcionSolicitanteLB.setText(ordenMod.getDescripcionSolicitante());

            codigoResponsableTB.setText(ordenMod.getCodigoResponsable());
            descripcionResponsableLB.setText(ordenMod.getDescripcionResponsable());

            sociedadTB.setText(ordenMod.getCodigoSociedad());
            descripcionSociedad.setText(ordenMod.getDescripcionSociedad());

            areaTB.setText(ordenMod.getCodigoArea());
            descripcionArea.setText(ordenMod.getDescripcionArea());

            emplazamientoTB.setText(ordenMod.getCodigoEmplazamiento());
            descripcionEmplazamientro.setText(ordenMod.getDescripcionEmplazamiento());

            centroCostosTB.setText(ordenMod.getCentroCostos());
            descripcionCentroCosto.setText(ordenMod.getDescripcionCentroCostos());

            labelUsuarioCreador.setText(ordenMod.getOrdenCreadaPor());
            labelUsuarioPlaneador.setText(ordenMod.getOrdenPlaneadaPor());
            labelUsuarioAprobador.setText(ordenMod.getOrdenAprobadaPor());
            labelUsuarioEjecutor.setText(ordenMod.getOrdenEjecutadaPor());

            labelHorasEstimadas.setText(ordenMod.getHorasEstimadas());
            labelHorasReal.setText(ordenMod.getHorasReal());
            labelCostoEstimado.setText(ordenMod.getCostoEstimado());
            labelCostoReal.setText(ordenMod.getCostoReal());

            contadorTB.setText(ordenMod.getNumeroContador());
            valorContadorTB.setText(ordenMod.getValorContador());
            hojaRutaTB.setText(ordenMod.getHojaRuta());
            planTB.setText(ordenMod.getNumeroPlan());

            establecerEstadoComponentes();

        } catch (ParseException ex) {
            Logger.getLogger(O03_Visualizacion_Ordenes_Trabajo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarListaOperaciones(String orden) {

        Cargar_Operaciones_Orden_Trabajo_SQL cOp = new Cargar_Operaciones_Orden_Trabajo_SQL();
        cOp.cargarOperaciones(orden, tablaOperaciones);

    }

    public void cargarListaMateriales(String orden) {

        Cargar_Materiales_Orden_Trabajo_SQL cMat = new Cargar_Materiales_Orden_Trabajo_SQL();
        cMat.cargarMateriales(orden, tablaMateriales);

        // actualizacion de lista de combobox
        int rows = tablaOperaciones.getRowCount();
        box.removeAllItems();

        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                if (tablaMateriales.getRowCount() > -1) {
                    box.addItem(tablaOperaciones.getValueAt(i, 1).toString());
                }
            }

            tablaMateriales.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(box));
        }

    }

    //Action Listener boton Creacion de Orden
    private boolean camposLlenos() {

        boolean isFilled = false;

        if (!(codigoEstatusTB.getText().isEmpty()
                | descripcionEstatusTB.getText().isEmpty()
                | tituloOrdenTB.getText().isEmpty()
                | codigoTipoMttoTB.getText().isEmpty()
                | descripcionTipoMttoTB.getText().isEmpty()
                | codigoClaseOrdenTB.getText().isEmpty()
                | descripcionClaseOrdenTB.getText().isEmpty()
                | codigoCriticidadTB.getText().isEmpty()
                | descripcionCriticidadTB.getText().isEmpty()
                | codigoSistemasTB.getText().isEmpty()
                | descripcionSistemasTB.getText().isEmpty()
                | codigoComponenteTB.getText().isEmpty()
                | descripcionComponenteTB.getText().isEmpty()
                | codigoSintomaTB.getText().isEmpty()
                | descripcionSintomaTB.getText().isEmpty()
                | fechaInicioProgramado.getDate() == null
                | fechaFinProgramado.getDate() == null
                | horaInicioProgramada.getText().isEmpty()
                | horaFinProgramado.getText().isEmpty()
                | totalHorasProgramado.getText().isEmpty()
                | fechaCreacionOrden.getDate() == null
                | horaCreacionOrden.getText().isEmpty()
                | codigoGrupoPlanTB.getText().isEmpty()
                | codigoSolicitanteTB.getText().isEmpty()
                | codigoResponsableTB.getText().isEmpty()
                | sociedadTB.getText().isEmpty()
                | areaTB.getText().isEmpty()
                | emplazamientoTB.getText().isEmpty()
                | centroCostosTB.getText().isEmpty())) {

            if (codigoEquipoTB.getText().isEmpty() && !codigoUbicacionTB.getText().isEmpty()) {

                isFilled = true;

            } else if (!codigoEquipoTB.getText().isEmpty() && !codigoUbicacionTB.getText().isEmpty()) {

                isFilled = true;

            }

        } else {
            isFilled = false;
        }

        return isFilled;
    }

    private final ActionListener modificarOrden = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (camposLlenos()) {

                modificarOrdenTrabajo();

                if (tablaOperaciones.getRowCount() > 0) {
                    if (verificarOperaciones()) {
                        SQLTaskOperation();
                    }
                }

                if (tablaMateriales.getRowCount() > 0) {
                    if (verificarMateriales()) {
                        SQLTaskMaterial();
                    }
                }

                SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.ORDER_SAVED) + ": " + numeroOrdenTB.getText(), TypeMessage.INFORMATION);

            } else {

                SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);

            }
        }

    };
    private final ActionListener msgSystem = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String codigoSt = codigoEstatusTB.getText();

            if (codigoSt.equals(StatusOrder.ST3_ORDER_UNDER_APPROVAL.getStatusCode())) {

                String status = StatusOrder.ST3_ORDER_UNDER_APPROVAL.getDescription();

                SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, status, TypeMessage.WARNING);

            } else if (codigoSt.equals(StatusOrder.ST8_CLOSED_ORDER.getStatusCode())) {

                String status = StatusOrder.ST8_CLOSED_ORDER.getDescription();

                SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, status, TypeMessage.WARNING);

            } else if (codigoSt.equals(StatusOrder.ST9_ORDER_REJECTED.getStatusCode())) {

                String status = StatusOrder.ST9_ORDER_REJECTED.getDescription();

                SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, status, TypeMessage.WARNING);

            } else if (codigoSt.equals(StatusOrder.ST10_ORDER_CANCELED.getStatusCode())) {

                String status = StatusOrder.ST10_ORDER_CANCELED.getDescription();

                SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, status, TypeMessage.WARNING);

            }
        }

    };

    private void btnModificarOrden() {
        if (codigoEstatusTB.getText().equals(StatusOrder.ST1_ORDER_CREATED.getStatusCode())
                | codigoEstatusTB.getText().equals(StatusOrder.ST2_ORDER_IN_PLANNING.getStatusCode())
                | codigoEstatusTB.getText().equals(StatusOrder.ST4_ORDER_APPROVED.getStatusCode())
                | codigoEstatusTB.getText().equals(StatusOrder.ST5_SCHEDULED_ORDER.getStatusCode())
                | codigoEstatusTB.getText().equals(StatusOrder.ST6_ORDER_IN_EXECUTION.getStatusCode())
                | codigoEstatusTB.getText().equals(StatusOrder.ST7_ORDER_EXECUTED.getStatusCode())) {

            btnGuardar_Orden.addActionListener(modificarOrden);

        } else {

            btnGuardar_Orden.addActionListener(msgSystem);

        }

    }

    private void modificarOrdenTrabajo() {

        Modify_Work_Order_SQL modifyOrder = new Modify_Work_Order_SQL();

        modifyOrder.setNumeroOrden(numeroOrdenTB.getText());
        /*datos de cabecera de la orden trabajo */
        modifyOrder.setCodigoEstatus(codigoEstatusTB.getText());
        modifyOrder.setDescripcionEstatus(descripcionEstatusTB.getText());
        modifyOrder.setParoEquipo(paradaCB.isSelected());
        modifyOrder.setTituloOrden(tituloOrdenTB.getText());
        modifyOrder.setDescripcionExtendidaOrden(descripcionExtendidaOrdenTB.getText());
        modifyOrder.setCodigoTipoOrden(codigoTipoMttoTB.getText());
        modifyOrder.setDescripcionTipoOrden(descripcionTipoMttoTB.getText());
        modifyOrder.setCodigoClaseOrden(codigoClaseOrdenTB.getText());
        modifyOrder.setDescripcionClaseOrden(descripcionClaseOrdenTB.getText());
        modifyOrder.setCodigoCriticidad(codigoCriticidadTB.getText());
        modifyOrder.setDescripcionCriticidad(descripcionCriticidadTB.getText());
        modifyOrder.setCodigoSistema(codigoSistemasTB.getText());
        modifyOrder.setDescripcionSistema(descripcionSistemasTB.getText());
        modifyOrder.setCodigoComponente(codigoComponenteTB.getText());
        modifyOrder.setDescripcionComponente(descripcionComponenteTB.getText());
        modifyOrder.setCodigoSintoma(codigoSintomaTB.getText());
        modifyOrder.setDescripcionSintoma(descripcionSintomaTB.getText());
        modifyOrder.setCodigoEquipo(codigoEquipoTB.getText());
        modifyOrder.setDescripcionEquipo(descripcionEquipoLB.getText());
        modifyOrder.setCodigoUbicacion(codigoUbicacionTB.getText());
        modifyOrder.setDescripcionUbicacion(descripcionUbicacionLB.getText());

        /*hora y fecha de inicio real de orden */
        modifyOrder.setFechaInicioReal(formatoFecha.format(fechaInicioReal.getDate()));
        if (fechaFinReal.getDate() != null) {
            modifyOrder.setFechaFinReal(formatoFecha.format(fechaFinReal.getDate()));
        } else {
            modifyOrder.setFechaFinReal(null);
        }

        modifyOrder.setHoraInicioReal(horaInicioReal.getText());
        modifyOrder.setHoraFinReal(horaFinReal.getText());
        modifyOrder.setTotalHoraReales(totalHoraReales.getText());

        /*hora y fecha de programacion de orden */
        modifyOrder.setFechaInicioProgramado(formatoFecha.format(fechaInicioProgramado.getDate()));
        modifyOrder.setFechaFinProgramado(formatoFecha.format(fechaFinProgramado.getDate()));
        modifyOrder.setHoraInicioProgramada(horaInicioProgramada.getText());
        modifyOrder.setHoraFinProgramado(horaFinProgramado.getText());
        modifyOrder.setTotalHorasProgramado(totalHorasProgramado.getText());

        /*hora y fecha de creacion de orden */
        modifyOrder.setFechaCreacionOrden(formatoFecha.format(fechaCreacionOrden.getDate()));
        if (fechaAutorizacionOrden.getDate() != null) {
            modifyOrder.setFechaAutorizacionOrden(formatoFecha.format(fechaAutorizacionOrden.getDate()));
        } else {
            modifyOrder.setFechaAutorizacionOrden(null);
        }

        String horaCreacion = formatoHora.format(Calendar.getInstance().getTime());
        horaCreacionOrden.setText(horaCreacion);
        modifyOrder.setHoraCreacionOrden(horaCreacion);
        modifyOrder.setHoraAutorizacionOrden(horaAutorizacionOrden.getText());
        modifyOrder.setTotalHorasCreacionOrden(totalHorasCreacionOrden.getText());

        /*grupo plan, responsable y solicitante*/
        modifyOrder.setCodigoGruoPlan(codigoGrupoPlanTB.getText());
        modifyOrder.setDescripcionGrupoPlan(descripcionGrupoPlanif.getText());
        modifyOrder.setCodigoSolicitante(codigoSolicitanteTB.getText());
        modifyOrder.setDescripcionSolicitante(descripcionSolicitanteLB.getText());
        modifyOrder.setCodigoResponsable(codigoResponsableTB.getText());
        modifyOrder.setDescripcionResponsable(descripcionResponsableLB.getText());

        /*datos financieros y de control*/
        modifyOrder.setCodigoSociedad(sociedadTB.getText());
        modifyOrder.setDescripcionSociedad(descripcionSociedad.getText());
        modifyOrder.setCodigoArea(areaTB.getText());
        modifyOrder.setDescripcionArea(descripcionArea.getText());
        modifyOrder.setCodigoEmplazamiento(emplazamientoTB.getText());
        modifyOrder.setDescripcionEmplazamiento(descripcionEmplazamientro.getText());
        modifyOrder.setCentroCostos(centroCostosTB.getText());
        modifyOrder.setDescripcionCentroCostos(descripcionCentroCosto.getText());

        modifyOrder.setOrdenCreadaPor(fullname);

        if (labelUsuarioPlaneador.getText() != null) {
            modifyOrder.setOrdenPlaneadaPor(labelUsuarioPlaneador.getText());
        } else {
            modifyOrder.setOrdenPlaneadaPor(null);
        }

        if (labelUsuarioAprobador.getText() != null) {
            modifyOrder.setOrdenAprobadaPor(labelUsuarioAprobador.getText());
        } else {
            modifyOrder.setOrdenAprobadaPor(null);
        }

        if (labelUsuarioEjecutor.getText() != null) {
            modifyOrder.setOrdenEjecutadaPor(labelUsuarioEjecutor.getText());
        } else {
            modifyOrder.setOrdenEjecutadaPor(null);
        }

        modifyOrder.setHorasEstimadas(totalHorasProgramado.getText());
        modifyOrder.setHorasReal(totalHoraReales.getText());
        modifyOrder.setCostoEstimado(labelCostoEstimado.getText());
        modifyOrder.setCostoReal(labelCostoReal.getText());

        modifyOrder.setNumeroContador(contadorTB.getText());
        modifyOrder.setValorContador(valorContadorTB.getText());
        modifyOrder.setHojaRuta(hojaRutaTB.getText());
        modifyOrder.setNumeroPlan(planTB.getText());

        modifyOrder.modifyWorkOrder();

    }

    private void establecerEstadoComponentes() {

        if (!codigoEstatusTB.getText().isEmpty()) {

            establecerEstadoBarraMenu(codigoEstatusTB.getText());
            establecerEstadoComponentes(codigoEstatusTB.getText());

        }

    }

    private void establecerEstadoBarraMenu(String estatus) {

        ControlStatusMenuBarOrder ctrl = new ControlStatusMenuBarOrder();
        ctrl.setMenuOrdenTrabajo(menuOrdenTrabajo);
        ctrl.setMenuGuardarOrden(menuGuardarOrden);
        ctrl.setMenuNuevaOrden(menuNuevaOrden);
        ctrl.setMenuModificarOrden(menuModificarOrden);
        ctrl.setMenuVisualizarOrden(menuVisualizarOrden);

        ctrl.setMenuEstatus(menuEstatus);
        ctrl.setMenuListadoEstatus(menuListadoEstatus);

        ctrl.setMenuEstatusPlaneacion(menuEstatusPlaneacion);
        ctrl.setMenuEstatusAprobacion(menuEstatusAprobacion);
        ctrl.setMenuEstatusAprobada(menuEstatusAprobada);
        ctrl.setMenuEstatusProgramada(menuEstatusProgramada);
        ctrl.setMenuEstatusEjecucion(menuEstatusEjecucion);
        ctrl.setMenuEstatusEjecutada(menuEstatusEjecutada);
        ctrl.setMenuEstatusCerrada(menuEstatusCerrada);
        ctrl.setMenuEstatusRechazado(menuEstatusRechazado);
        ctrl.setMenuEstatusCancelada(menuEstatusCancelada);

        ctrl.setMenuSalir(menuSalir);

        ctrl.setMenuCabeceraOrden(menuCabeceraOrden);
        ctrl.setMenuEquipo(menuEquipo);
        ctrl.setMenuUbicaciones(menuUbicaciones);
        ctrl.setMenuTipoMantenimiento(menuTipoMantenimiento);
        ctrl.setMenuClasesMantenimiento(menuClasesMantenimiento);
        ctrl.setMenuPrioridades(menuPrioridades);

        ctrl.setMenuSistemas(menuSistemas);
        ctrl.setMenuComponentes(menuComponentes);
        ctrl.setMenuSintomas(menuSintomas);

        ctrl.setMenuRecursos(menuRecursos);
        ctrl.setMenuOperaciones(menuOperaciones);
        ctrl.setMenuMateriales(menuMateriales);

        ctrl.setMenuCostosOrden(menuCostosOrden);
        ctrl.setMenuCostos(menuCostos);

        ctrl.setStatusComponent(estatus);
    }

    private void establecerEstadoComponentes(String estatus) {

        panelDescripcionExt.setVisible(false);

        ControlStatusComponentOrder ctrl = new ControlStatusComponentOrder();

        ctrl.setNumeroOrdenTB(numeroOrdenTB);
        ctrl.setCodigoEstatusTB(codigoEstatusTB);
        ctrl.setDescripcionEstatusTB(descripcionEstatusTB);

        ctrl.setParadaCB(paradaCB);
        ctrl.setTituloOrdenTB(tituloOrdenTB);

        ctrl.setBtnDescripcionExtendida(btnDescripcionExtendida);
        ctrl.setDescripcionExtendidaOrdenTB(descripcionExtendidaOrdenTB);

        ctrl.setCodigoTipoMttoTB(codigoTipoMttoTB);
        ctrl.setDescripcionTipoMttoTB(descripcionTipoMttoTB);
        ctrl.setBtnTipoMtto(btnTipoMtto);

        ctrl.setCodigoClaseOrdenTB(codigoClaseOrdenTB);
        ctrl.setDescripcionClaseOrdenTB(descripcionClaseOrdenTB);
        ctrl.setBtnClaseMtto(btnClaseMtto);

        ctrl.setCodigoCriticidadTB(codigoCriticidadTB);
        ctrl.setDescripcionCriticidadTB(descripcionCriticidadTB);
        ctrl.setBtnCriticidadMtto(btnCriticidadMtto);

        ctrl.setCodigoSistemasTB(codigoSistemasTB);
        ctrl.setDescripcionSistemasTB(descripcionSistemasTB);
        ctrl.setBtnSistema(btnSistema);

        ctrl.setCodigoComponenteTB(codigoComponenteTB);
        ctrl.setDescripcionComponenteTB(descripcionComponenteTB);
        ctrl.setBtnComponente(btnComponente);

        ctrl.setCodigoSintomaTB(codigoSintomaTB);
        ctrl.setDescripcionSintomaTB(descripcionSintomaTB);
        ctrl.setBtnSintoma(btnSintoma);

        ctrl.setCodigoEquipoTB(codigoEquipoTB);
        ctrl.setBtnEquipo(btnEquipo);

        ctrl.setCodigoUbicacionTB(codigoUbicacionTB);
        ctrl.setBtnUbicacion(btnUbicacion);

        ctrl.setFechaInicioReal(fechaInicioReal);
        ctrl.setFechaFinReal(fechaFinReal);

        ctrl.setHoraInicioReal(horaInicioReal);
        ctrl.setBtnHoraInicioReal(btnHoraInicioReal);
        ctrl.setHoraFinReal(horaFinReal);
        ctrl.setBtnHoraFinReal(btnHoraFinReal);
        ctrl.setTotalHoraReales(totalHoraReales);

        ctrl.setFechaInicioProgramado(fechaInicioProgramado);
        ctrl.setFechaFinProgramado(fechaFinProgramado);

        ctrl.setHoraInicioProgramada(horaInicioProgramada);
        ctrl.setBtnHoraProgramada(btnHoraProgramada);
        ctrl.setHoraFinProgramado(horaFinProgramado);
        ctrl.setBtnHoraCierreProg(btnHoraCierreProg);
        ctrl.setTotalHorasProgramado(totalHorasProgramado);

        ctrl.setFechaCreacionOrden(fechaCreacionOrden);
        ctrl.setFechaAutorizacionOrden(fechaAutorizacionOrden);

        ctrl.setHoraCreacionOrden(horaCreacionOrden);
        ctrl.setBtnHoraCreacion(btnHoraCreacion);
        ctrl.setHoraAutorizacionOrden(horaAutorizacionOrden);
        ctrl.setBtnHoraAutorizada(btnHoraAutorizada);
        ctrl.setTotalHorasCreacionOrden(totalHorasCreacionOrden);

        ctrl.setCodigoGrupoPlanTB(codigoEquipoTB);

        ctrl.setCodigoSolicitanteTB(codigoSolicitanteTB);
        ctrl.setBtnSolicitante(btnSolicitante);
        ctrl.setCodigoResponsableTB(codigoResponsableTB);
        ctrl.setBtnResponsable(btnResponsable);

        ctrl.setBtnAgregarLineaOperacion(btnAgregarLineaOperacion);
        ctrl.setBtnOkOperacion(btnOkOperacion);
        ctrl.setBtnEditarLineaOperacion(btnEditarLineaOperacion);
        ctrl.setBtnEliminarLineaOperacion(btnEliminarLineaOperacion);

        ctrl.setBtnAgregarLineaMaterial(btnAgregarLineaMaterial);
        ctrl.setBtnOkMaterial(btnOkMaterial);
        ctrl.setBtnEditarLineaMaterial(btnEditarLineaMaterial);
        ctrl.setBtnEliminarLineaMaterial(btnEliminarLineaMaterial);

        ctrl.setSociedadTB(sociedadTB);
        ctrl.setAreaTB(areaTB);
        ctrl.setCentroCostosTB(centroCostosTB);
        ctrl.setEmplazamientoTB(emplazamientoTB);

        ctrl.setContadorTB(contadorTB);
        ctrl.setValorContadorTB(valorContadorTB);
        ctrl.setHojaRutaTB(hojaRutaTB);
        ctrl.setPlanTB(planTB);

        ctrl.setStatusComponent(estatus);
    }

    private void setTitle() {

        String title = LyraWorkspace.TitleLabel.getText();

        String title2 = title + ": " + MULTITAB.getTitleAt(MULTITAB.getSelectedIndex());

        LyraWorkspace.TitleLabel.setText(title2);

        MULTITAB.addChangeListener((ChangeEvent e) -> {
            LyraWorkspace.TitleLabel.setText(title + ": " + MULTITAB.getTitleAt(MULTITAB.getSelectedIndex()));
        });
    }

    private void limpiarCampos() {

        numeroOrdenTB.setText(null);

        tituloOrdenTB.setText(null);
        descripcionExtendidaOrdenTB.setText(null);

        codigoTipoMttoTB.setText(null);
        descripcionTipoMttoTB.setText(null);

        codigoClaseOrdenTB.setText(null);
        descripcionClaseOrdenTB.setText(null);

        codigoCriticidadTB.setText(null);
        descripcionCriticidadTB.setText(null);

        codigoSistemasTB.setText(null);
        descripcionSistemasTB.setText(null);

        codigoComponenteTB.setText(null);
        descripcionComponenteTB.setText(null);

        codigoSintomaTB.setText(null);
        descripcionSintomaTB.setText(null);

        codigoEquipoTB.setText(null);
        descripcionEquipoLB.setText(null);

        codigoUbicacionTB.setText(null);
        descripcionUbicacionLB.setText(null);

        codigoGrupoPlanTB.setText(null);
        descripcionGrupoPlanif.setText(null);
        codigoSolicitanteTB.setText(null);
        descripcionSolicitanteLB.setText(null);
        codigoResponsableTB.setText(null);
        descripcionResponsableLB.setText(null);

        sociedadTB.setText(null);
        descripcionSociedad.setText(null);
        areaTB.setText(null);
        descripcionArea.setText(null);
        emplazamientoTB.setText(null);
        descripcionEmplazamientro.setText(null);
        centroCostosTB.setText(null);
        descripcionCentroCosto.setText(null);

        labelUsuarioPlaneador.setText(null);
        labelUsuarioAprobador.setText(null);
        labelUsuarioEjecutor.setText(null);

        contadorTB.setText(null);
        hojaRutaTB.setText(null);
        valorContadorTB.setText(null);
        planTB.setText(null);

        limpiarFilasOperacion();
        limpiarFilasMateriales();

    }

    private void formatDate() {

        fechaInicioReal.setDateFormatString("yyyy.MM.dd");
        ((JTextFieldDateEditor) fechaInicioReal.getDateEditor()).setEditable(false);

        fechaFinReal.setDateFormatString("yyyy.MM.dd");
        ((JTextFieldDateEditor) fechaFinReal.getDateEditor()).setEditable(false);

        fechaInicioProgramado.setDateFormatString("yyyy.MM.dd");
        ((JTextFieldDateEditor) fechaInicioProgramado.getDateEditor()).setEditable(false);

        fechaFinProgramado.setDateFormatString("yyyy.MM.dd");
        ((JTextFieldDateEditor) fechaFinProgramado.getDateEditor()).setEditable(false);

        fechaCreacionOrden.setDateFormatString("yyyy.MM.dd");
        ((JTextFieldDateEditor) fechaCreacionOrden.getDateEditor()).setEditable(false);

        fechaAutorizacionOrden.setDateFormatString("yyyy.MM.dd");
        ((JTextFieldDateEditor) fechaAutorizacionOrden.getDateEditor()).setEditable(false);
    }

    private void funcionesBotonesTablaOperaciones() {

        TableEventSimpleButton eO = (int row) -> {

            if (tablaOperaciones.getSelectedRow() == row) {

                tablaOperaciones.clearSelection();

            }
        };

        tablaOperaciones.getColumnModel().getColumn(0).setCellEditor(new CellEditorLyraTable(eO));

    }

    private void funcionesBotonesTablaMateriales() {

        TableEventSimpleButton eM = (int row) -> {

            if (tablaOperaciones.getSelectedRow() == row) {

                tablaOperaciones.clearSelection();

            }
        };

        tablaMateriales.getColumnModel().getColumn(0).setCellEditor(new CellEditorLyraTable(eM));

    }

    private void funcionesBotonesTablaCostos() {

        TableEventSimpleButton eM = (int row) -> {

            if (tablaResumenCostos.getSelectedRow() == row) {
                tablaResumenCostos.getSelectionModel().clearSelection();

            } else {
                tablaResumenCostos.getSelectionModel().setSelectionInterval(row, row);
            }
        };

        tablaResumenCostos.getColumnModel().getColumn(0).setCellEditor(new CellEditorLyraTable(eM));

    }

    private JComboBox combo;

    private void setOperationTableModel() {

        Orden_Operaciones_Model.set(tablaOperaciones);

        DimensionesCeldas.setWidth(tablaOperaciones, 1, 50);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 1);

        DimensionesCeldas.setWidth(tablaOperaciones, 2, 50);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 2);

        DimensionesCeldas.setWidth(tablaOperaciones, 3, 0);

        DimensionesCeldas.setWidth(tablaOperaciones, 4, 400);
        AlineacionCeldas.alinearIzquierda(tablaOperaciones, 4);

        DimensionesCeldas.setWidth(tablaOperaciones, 5, 80);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 5);

        DimensionesCeldas.setWidth(tablaOperaciones, 6, 80);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 6);

        DimensionesCeldas.setWidth(tablaOperaciones, 7, 80);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 7);

        DimensionesCeldas.setWidth(tablaOperaciones, 8, 100);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 8);

        DimensionesCeldas.setWidth(tablaOperaciones, 9, 150);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 9);

        DimensionesCeldas.setWidth(tablaOperaciones, 10, 60);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 10);

        DimensionesCeldas.setWidth(tablaOperaciones, 11, 80);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 11);

        DimensionesCeldas.setWidth(tablaOperaciones, 12, 100);
        AlineacionCeldas.alinearDerecha(tablaOperaciones, 12);

        DimensionesCeldas.setWidth(tablaOperaciones, 13, 80);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 13);

        DimensionesCeldas.setWidth(tablaOperaciones, 14, 80);
        AlineacionCeldas.alinearCentro(tablaOperaciones, 14);

        combo = new JComboBox();
        Combo_Tipo_Operacion_SQL.cargaCombo(combo);

        tablaOperaciones.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(combo));

    }

    private void setMaterialesTableModel() {

        Orden_Materiales_Model.set(tablaMateriales);

        DimensionesCeldas.setWidth(tablaMateriales, 1, 50);
        AlineacionCeldas.alinearCentro(tablaMateriales, 1);

        DimensionesCeldas.setWidth(tablaMateriales, 2, 120);
        AlineacionCeldas.alinearCentro(tablaMateriales, 2);

        DimensionesCeldas.setWidth(tablaMateriales, 3, 33);
        AlineacionCeldas.alinearCentro(tablaMateriales, 3);

        DimensionesCeldas.setWidth(tablaMateriales, 4, 300);
        AlineacionCeldas.alinearIzquierda(tablaMateriales, 4);

        DimensionesCeldas.setWidth(tablaMateriales, 5, 80);
        AlineacionCeldas.alinearCentro(tablaMateriales, 5);

        DimensionesCeldas.setWidth(tablaMateriales, 6, 80);
        AlineacionCeldas.alinearCentro(tablaMateriales, 6);

        DimensionesCeldas.setWidth(tablaMateriales, 7, 80);
        AlineacionCeldas.alinearCentro(tablaMateriales, 7);

        DimensionesCeldas.setWidth(tablaMateriales, 8, 100);
        AlineacionCeldas.alinearCentro(tablaMateriales, 8);

        DimensionesCeldas.setWidth(tablaMateriales, 9, 100);
        AlineacionCeldas.alinearDerecha(tablaMateriales, 9);

        DimensionesCeldas.setWidth(tablaMateriales, 10, 50);
        AlineacionCeldas.alinearCentro(tablaMateriales, 10);

        DimensionesCeldas.setWidth(tablaMateriales, 11, 80);
        AlineacionCeldas.alinearCentro(tablaMateriales, 11);

    }

    private void setTotalesTableModel() {

        Orden_Costos_Model.set(tablaResumenCostos);
        Orden_Costos_Model.addRowResumentCost(tablaResumenCostos);

        DimensionesCeldas.setWidth(tablaResumenCostos, 1, 50);
        AlineacionCeldas.alinearCentro(tablaResumenCostos, 1);

        DimensionesCeldas.setWidth(tablaResumenCostos, 2, 300);
        AlineacionCeldas.alinearIzquierda(tablaResumenCostos, 2);

        DimensionesCeldas.setWidth(tablaResumenCostos, 3, 150);
        AlineacionCeldas.alinearDerecha(tablaResumenCostos, 3);

        DimensionesCeldas.setWidth(tablaResumenCostos, 4, 150);
        AlineacionCeldas.alinearDerecha(tablaResumenCostos, 4);

        DimensionesCeldas.setWidth(tablaResumenCostos, 5, 150);
        AlineacionCeldas.alinearDerecha(tablaResumenCostos, 5);

    }

    private void carcularMontosMultiTab() {
        MULTITAB.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (MULTITAB.getSelectedComponent() == costos) {
                    datosFinancieros();
                }
            }

        });
    }

    private void valoresCamposIniciales() {

        try {

            String fH = formatoFecha.format(Calendar.getInstance().getTime());
            Date fechaHoy = formatoFecha.parse(fH);

            fechaInicioReal.setDate(fechaHoy);
            fechaInicioProgramado.setDate(fechaHoy);
            fechaCreacionOrden.setDate(fechaHoy);

            fechaFinReal.setDate(fechaHoy);
            fechaFinProgramado.setDate(fechaHoy);

            Date hora = null;
            hora = formatoHora.parse("00:00 AM");

            horaInicioReal.setText(formatoHora.format(hora));
            horaFinReal.setText(formatoHora.format(hora));

            horaInicioProgramada.setText(formatoHora.format(hora));
            horaFinProgramado.setText(formatoHora.format(hora));

            horaCreacionOrden.setText(formatoHora.format(hora));
            horaAutorizacionOrden.setText(null);

//            totalHoraReales.setText(CG15_Double_Formato.setFormat(0));
  //          totalHorasProgramado.setText(CG15_Double_Formato.setFormat(0));
    //        totalHorasCreacionOrden.setText(CG15_Double_Formato.setFormat(0));

      //      tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(0), 0, 4);
        //    tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(0), 1, 4);
          //  tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(0), 2, 4);
          //  tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(0), 3, 4);

//            tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(0), 0, 5);
//            tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(0), 1, 5);
//            tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(0), 2, 5);
//            tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(0), 3, 5);

            codigoEstatusTB.setText(StatusOrder.ST1_ORDER_CREATED.getStatusCode());
            descripcionEstatusTB.setText(StatusOrder.ST1_ORDER_CREATED.getDescription());

            labelUsuarioCreador.setText(null);
            labelUsuarioPlaneador.setText(null);
            labelUsuarioAprobador.setText(null);
            labelUsuarioEjecutor.setText(null);

//            labelHorasEstimadas.setText(CG15_Double_Formato.setFormat(0));
//            labelHorasReal.setText(CG15_Double_Formato.setFormat(0));
//            labelCostoEstimado.setText(CG15_Double_Formato.setFormat(0));
//            labelCostoReal.setText(CG15_Double_Formato.setFormat(0));

        } catch (ParseException ex) {
            Logger.getLogger(O03_Visualizacion_Ordenes_Trabajo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void eventosFechas() {

        fechaInicioReal.getDateEditor().addPropertyChangeListener((e) -> {
            if ("date".equals(e.getPropertyName())) {

                calcularHorasReal();

            }
        });

        fechaFinReal.getDateEditor().addPropertyChangeListener((e) -> {
            if ("date".equals(e.getPropertyName())) {

                calcularHorasReal();

            }
        });

        fechaInicioProgramado.getDateEditor().addPropertyChangeListener((e) -> {
            if ("date".equals(e.getPropertyName())) {

                calcularHorasProgramadas();

            }
        });

        fechaFinProgramado.getDateEditor().addPropertyChangeListener((e) -> {
            if ("date".equals(e.getPropertyName())) {

                calcularHorasProgramadas();

            }
        });
    }

    private void btnHoraInicio() {

        btnHoraInicioReal.addActionListener((e) -> {

            TimeSelector ts = new TimeSelector(frame, true);
            ts.setTitle(labelHoraInicio.getText());
            ts.setTxtBox(horaInicioReal);
            ts.setLocationRelativeTo(btnHoraInicioReal);
            ts.setVisible(true);

        });

        horaInicioReal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calcularHorasReal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });

    }

    private void btnHoraFin() {

        btnHoraFinReal.addActionListener((e) -> {

            TimeSelector ts = new TimeSelector(frame, true);
            ts.setTxtBox(horaFinReal);
            ts.setTitle(labelHoraFin.getText());
            ts.setLocationRelativeTo(btnHoraFinReal);
            ts.setVisible(true);

        });

        horaFinReal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calcularHorasReal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });

    }

    private void btnHoraProgramada() {
        btnHoraProgramada.addActionListener((e) -> {

            TimeSelector ts = new TimeSelector(frame, true);
            ts.setTxtBox(horaInicioProgramada);
            ts.setTitle(labelHoraProgramada.getText());
            ts.setLocationRelativeTo(btnHoraProgramada);
            ts.setVisible(true);

        });

        horaInicioProgramada.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calcularHorasProgramadas();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });
    }

    private void btnHoraCierre() {

        btnHoraCierreProg.addActionListener((e) -> {

            TimeSelector ts = new TimeSelector(frame, true);
            ts.setTxtBox(horaFinProgramado);
            ts.setTitle(labelHoraCierre.getText());
            ts.setLocationRelativeTo(btnHoraCierreProg);
            ts.setVisible(true);

        });

        horaFinProgramado.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calcularHorasProgramadas();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });

    }

    public void calcularHorasProgramadas() {

        if (fechaInicioProgramado.getDate() != null
                && fechaFinProgramado.getDate() != null
                && !horaInicioProgramada.getText().isEmpty()
                && !horaFinProgramado.getText().isEmpty()) {

            if (CalculoHorasFechas.fechaFinalMenorInicial(fechaInicioProgramado.getDate(), fechaFinProgramado.getDate(),
                    horaInicioProgramada.getText(), horaFinProgramado.getText())) {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.EARLIER_DATE), TypeMessage.WARNING);
                fechaFinProgramado.setDate(null);

            } else {

                double cantH = CalculoHorasFechas.getHourCount(fechaInicioProgramado.getDate(), fechaFinProgramado.getDate(), horaInicioProgramada.getText(), horaFinProgramado.getText());
                totalHorasProgramado.setText(String.valueOf(cantH));

            }
        }

    }

    public void calcularHorasReal() {

        if (fechaInicioReal.getDate() != null
                && fechaFinReal.getDate() != null
                && !horaInicioReal.getText().isEmpty()
                && !horaFinReal.getText().isEmpty()) {

            if (CalculoHorasFechas.fechaFinalMenorInicial(fechaInicioReal.getDate(), fechaFinReal.getDate(),
                    horaInicioReal.getText(), horaFinReal.getText())) {

                new SystemMessages(NOT.msg(NOT.EARLIER_DATE), TypeMessage.ERROR);
                fechaFinReal.setDate(null);

            } else {

                double cantH = CalculoHorasFechas.getHourCount(fechaInicioReal.getDate(), fechaFinReal.getDate(), horaInicioReal.getText(), horaFinReal.getText());
                totalHoraReales.setText(String.valueOf(cantH));
            }

        }

    }

    private void datosFinancieros() {

        int opRows = tablaOperaciones.getRowCount();
        int matRows = tablaMateriales.getRowCount();

        double montoOp = 0;
        double montoMat = 0;
        double montoOpExt = 0;

        double total = 0;

        if (opRows > 0) {

            for (int i = 0; i < opRows; i++) {
                if (tablaOperaciones.getValueAt(i, 8) != null && tablaOperaciones.getValueAt(i, 8).toString().equals("EXT")) {
                    montoOpExt = montoOpExt + Double.parseDouble(tablaOperaciones.getValueAt(i, 12).toString().replaceAll(",", ""));
                } else if (tablaOperaciones.getValueAt(i, 8) != null) {
                    montoOp = montoOp + Double.parseDouble(tablaOperaciones.getValueAt(i, 12).toString().replaceAll(",", ""));
                }

            }

//            tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(montoOp), 0, 3);
//            tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(montoOpExt), 2, 3);
        } else {
//            tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(montoOp), 0, 3);
//            tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(montoOpExt), 2, 3);
        }

        if (matRows > 0) {

            for (int i = 0; i < matRows; i++) {
                if (tablaMateriales.getValueAt(i, 9) != null) {
                    montoMat = montoMat + Double.parseDouble(tablaMateriales.getValueAt(i, 9).toString().replaceAll(",", ""));
                }

            }

      //      tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(montoMat), 1, 3);
        } else {
      //      tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(montoMat), 1, 3);
        }

        total = montoOp + montoOpExt + montoMat;
        //tablaResumenCostos.setValueAt(CG15_Double_Formato.setFormat(total), 3, 3);

        labelHorasEstimadas.setText(totalHorasProgramado.getText());
        labelHorasReal.setText(totalHoraReales.getText());

        //labelCostoEstimado.setText(CG15_Double_Formato.setFormat(total));
        //labelCostoReal.setText(CG15_Double_Formato.setFormat(0));

    }

    private void comboTipoManoObraOperacion() {

        combo.addItemListener((ItemEvent e) -> {

            if (combo.getSelectedItem() != null) {

                String data = combo.getSelectedItem().toString();

                Datos_Tipo_Operacion_SQL op = new Datos_Tipo_Operacion_SQL();
                op.setIdTipo(data);
                op.extraerDatos();

                String descTipo = op.getDescripcionTipo();
                String costoUnit = op.getCostoUnitario();
                String unidMed = op.getUnidadMedida();
                String moneda = op.getMoneda();

                tablaOperaciones.setValueAt(descTipo, tablaOperaciones.getSelectedRow(), 9);
                tablaOperaciones.setValueAt(costoUnit, tablaOperaciones.getSelectedRow(), 10);
                tablaOperaciones.setValueAt(unidMed, tablaOperaciones.getSelectedRow(), 11);
                tablaOperaciones.setValueAt(moneda, tablaOperaciones.getSelectedRow(), 15);

                double valor = Double.parseDouble(tablaOperaciones.getValueAt(tablaOperaciones.getSelectedRow(), 10).toString().replaceAll(",", ""));

                if (valor == 0) {

                    LyraTableModel m = (LyraTableModel) tablaOperaciones.getModel();
                    m.setColumnNoEditable(new int[]{1, 3, 5, 9, 11, 12, 13, 15});
                    tablaOperaciones.setModel(m);

                } else {

                    LyraTableModel m = (LyraTableModel) tablaOperaciones.getModel();
                    m.setColumnNoEditable(new int[]{1, 3, 5, 9, 10, 11, 12, 13, 15});
                    tablaOperaciones.setModel(m);

                }

                if (tablaOperaciones.getValueAt(tablaOperaciones.getSelectedRow(), 5) != null) {

                    int fila = tablaOperaciones.getSelectedRow();

                    double trabajo = Double.parseDouble(tablaOperaciones.getValueAt(fila, 5).toString().replaceAll(",", ""));
                    double unitCost = Double.parseDouble(tablaOperaciones.getValueAt(fila, 10).toString().replaceAll(",", ""));
                    double total = trabajo * unitCost;

             //       tablaOperaciones.setValueAt(CG15_Double_Formato.setFormat(total), fila, 12);
                }

            }

        });

    }

    private JPanel panelAnterior;

    public void setPanelAnterior(JPanel panel) {
        this.panelAnterior = panel;
    }

    private void btnSalir() {

        btnSalir_CrearOrden.addActionListener((e) -> {

            int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_LEAVE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                ((O03_Visualizar_Orden) panelAnterior).setTitle();

                PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
            }

        });
    }

    private final ActionListener tipoMtto = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Lista_Tipo_Mtto lt = new Lista_Tipo_Mtto(frame, true);
            lt.setCampos(codigoTipoMttoTB, descripcionTipoMttoTB);
            lt.setTitleWindow(labelTipoMtto.getText());
            lt.setUbicacionComponente(btnTipoMtto);
            lt.setVisible(true);
        }

    };

    private void btnTipoMtto() {
        btnTipoMtto.addActionListener(tipoMtto);
    }

    private final ActionListener claseMtto = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Lista_Clase_Mtto lm = new Lista_Clase_Mtto(frame, true);
            lm.setCampos(codigoClaseOrdenTB, descripcionClaseOrdenTB);
            lm.setTitleWindow(labelClaseMtto.getText());
            lm.setUbicacionComponente(btnClaseMtto);
            lm.setVisible(true);
        }

    };

    private void btnClaseMtto() {
        btnClaseMtto.addActionListener(claseMtto);
    }

    private final ActionListener criticidadMtto = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Lista_Criticidad lc = new Lista_Criticidad(frame, true);
            lc.setCampos(codigoCriticidadTB, descripcionCriticidadTB);
            lc.setDiaVariable(diasCriticidad, fechaInicioProgramado);
            lc.setTitleWindow(labelCriticidad.getText());
            lc.setUbicacionComponente(btnCriticidadMtto);
            lc.setVisible(true);
        }

    };

    private void btnCriticidadMtto() {
        btnCriticidadMtto.addActionListener(criticidadMtto);
    }

    private final ActionListener sistemaEquipo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Lista_Sistemas ls = new Lista_Sistemas(frame, true);
            ls.setCampos(codigoSistemasTB, descripcionSistemasTB);
            ls.setTitleWindow(labelSistema.getText());
            ls.setUbicacionComponente(btnSistema);
            ls.setVisible(true);
        }

    };

    private void btnSistema() {
        btnSistema.addActionListener(sistemaEquipo);
    }

    private final ActionListener componenteEquipo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Lista_Componentes lc = new Lista_Componentes(frame, true);
            lc.setCampos(codigoComponenteTB, descripcionComponenteTB);
            lc.setTitleWindow(labelComponente.getText());
            lc.setUbicacionComponente(btnComponente);
            lc.setVisible(true);
        }

    };

    private void btnComponente() {
        btnComponente.addActionListener(componenteEquipo);
    }

    private final ActionListener sintomaEquipo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Lista_Sintomas ls = new Lista_Sintomas(frame, true);
            ls.setCampos(codigoSintomaTB, descripcionSintomaTB);
            ls.setTitleWindow(labelSistema.getText());
            ls.setUbicacionComponente(btnSintoma);
            ls.setVisible(true);
        }

    };

    private void btnSintoma() {
        btnSintoma.addActionListener(sintomaEquipo);
    }

    private final ActionListener buscarEquipo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            codigoEquipoTB.setText(null);
            descripcionEquipoLB.setText(null);

            codigoUbicacionTB.setText(null);
            descripcionUbicacionLB.setText(null);

            sociedadTB.setText(null);
            descripcionSociedad.setText(null);

            areaTB.setText(null);
            descripcionArea.setText(null);

            emplazamientoTB.setText(null);
            descripcionEmplazamientro.setText(null);

            centroCostosTB.setText(null);
            descripcionCentroCosto.setText(null);

            codigoGrupoPlanTB.setText(null);
            descripcionGrupoPlanif.setText(null);

            Lista_Equipos le = new Lista_Equipos();
            le.setCampos(codigoEquipoTB, descripcionEquipoLB,
                    codigoUbicacionTB, descripcionUbicacionLB,
                    sociedadTB, descripcionSociedad, areaTB, descripcionArea,
                    emplazamientoTB, descripcionEmplazamientro,
                    centroCostosTB, descripcionCentroCosto, codigoGrupoPlanTB, descripcionGrupoPlanif);

            le.setPanelAnterior(thisPanel);

            PanelLoader.loadPanel(le, mainContainerPanel);
        }

    };

    private void btnEquipo() {
        btnEquipo.addActionListener(buscarEquipo);
    }

    private final ActionListener buscarUbicacion = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            codigoEquipoTB.setText(null);
            descripcionEquipoLB.setText(null);

            codigoUbicacionTB.setText(null);
            descripcionUbicacionLB.setText(null);

            sociedadTB.setText(null);
            descripcionSociedad.setText(null);

            areaTB.setText(null);
            descripcionArea.setText(null);

            emplazamientoTB.setText(null);
            descripcionEmplazamientro.setText(null);

            centroCostosTB.setText(null);
            descripcionCentroCosto.setText(null);

            codigoGrupoPlanTB.setText(null);
            descripcionGrupoPlanif.setText(null);

            Lista_Ubicaciones ub = new Lista_Ubicaciones();

            ub.setCodUbi(codigoUbicacionTB);
            ub.setDescUbi(descripcionUbicacionLB);

            ub.setCentroC(centroCostosTB);
            ub.setDescCC(descripcionCentroCosto);

            ub.setEmpl(emplazamientoTB);
            ub.setDescEmpl(descripcionEmplazamientro);

            ub.setArea(areaTB);
            ub.setDescArea(descripcionArea);

            ub.setSocie(sociedadTB);
            ub.setDescSocie(descripcionSociedad);

            ub.setGrupoPlanif(codigoGrupoPlanTB);
            ub.setDescripcionGP(descripcionGrupoPlanif);

            ub.setPanelAnterior(thisPanel);

            PanelLoader.loadPanel(ub, mainContainerPanel);
        }

    };

    private void btnUbicacion() {
        btnUbicacion.addActionListener(buscarUbicacion);
    }

    private final ActionListener listaSolicitante = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Lista_Personal sol = new Lista_Personal(frame, true);
            sol.setTitles(labelSolicitante.getText());
            sol.setTextFields(codigoSolicitanteTB, descripcionSolicitanteLB);
            sol.setTitleWindow(labelSolicitante.getText());
            sol.setUbicacionComponente(btnSolicitante);
            sol.setVisible(true);
        }

    };

    private void btnSolicitante() {
        btnSolicitante.addActionListener(listaSolicitante);
    }

    private final ActionListener listaResponsable = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Lista_Personal sol = new Lista_Personal(frame, true);
            sol.setTitles(labelResponsable.getText());
            sol.setTextFields(codigoResponsableTB, descripcionResponsableLB);
            sol.setTitleWindow(labelResponsable.getText());
            sol.setUbicacionComponente(btnResponsable);
            sol.setVisible(true);
        }

    };

    private void btnResponsable() {
        btnResponsable.addActionListener(listaResponsable);

    }

    private void btnAgregarLineaOperacion() {

        btnAgregarLineaOperacion.addActionListener((e) -> {

            int filas = tablaOperaciones.getRowCount();

            if (filas == 0) {

                LyraTableModel dataModel = (LyraTableModel) tablaOperaciones.getModel();
                Object[] celdas = new Object[dataModel.getColumnCount()];

                for (int c = 0; c < dataModel.getColumnCount(); c++) {

                    celdas[c] = null;
                }

                dataModel.addRow(celdas);
                tablaOperaciones.setModel(dataModel);
                tablaOperaciones.setValueAt("0" + 10, 0, 1);

                tablaOperaciones.setRowSelectionInterval(filas, filas);
                editarLineaOperacion(filas);

            } else if (filas > 0) {

                int cantCol = tablaOperaciones.getColumnCount() - 3;
                boolean camposCompletos = false;

                for (int i = 0; i < cantCol; i++) {

                    if (i + 3 == 12) {
                        i = 12;
                    }

                    if (tablaOperaciones.getValueAt(filas - 1, i + 3) == null) {

                            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS) + ": "
                                    + tablaOperaciones.getColumnName(i + 3), TypeMessage.WARNING);

                        camposCompletos = false;
                        break;

                    } else {
                        camposCompletos = true;
                    }

                }
                if (camposCompletos) {

                    cerrarEdicionLineaOperacion();

                    LyraTableModel dataModel = (LyraTableModel) tablaOperaciones.getModel();
                    Object[] celdas = new Object[dataModel.getColumnCount()];

                    for (int c = 0; c < dataModel.getColumnCount(); c++) {

                        celdas[c] = null;
                    }

                    dataModel.addRow(celdas);
                    tablaOperaciones.setModel(dataModel);

                    int value = Integer.parseInt(tablaOperaciones.getValueAt(filas - 1, 1).toString());
                    value = value + 10;

                    tablaOperaciones.setValueAt("0" + value, tablaOperaciones.getRowCount() - 1, 1);

                    tablaOperaciones.setRowSelectionInterval(filas, filas);
                    tablaOperaciones.setColumnSelectionInterval(4, 4);
                    editarLineaOperacion(filas);

                }

            }

            int rows = tablaOperaciones.getRowCount();
            box.removeAllItems();

            if (rows > 0) {
                for (int i = 0; i < rows; i++) {
                    if (tablaMateriales.getRowCount() > -1) {
                        box.addItem(tablaOperaciones.getValueAt(i, 1).toString());
                    }
                }

                tablaMateriales.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(box));
            }

        });

    }

    private void btnOkOperacion() {

        btnOkOperacion.addActionListener((e) -> {

            int fila = tablaOperaciones.getSelectedRow();

            cerrarEdicionLineaOperacion();
            //trabajo
            if (tablaOperaciones.getValueAt(fila, 6) != null && tablaOperaciones.getValueAt(fila, 7) != null) {

                double cant = Double.parseDouble(tablaOperaciones.getValueAt(fila, 6).toString());
                double dur = Double.parseDouble(tablaOperaciones.getValueAt(fila, 7).toString());
                double trabajo = cant * dur;

                tablaOperaciones.setValueAt(trabajo, fila, 5);
            }

            //total costos
            if (tablaOperaciones.getValueAt(fila, 5) != null) {

                double trabajo = Double.parseDouble(tablaOperaciones.getValueAt(fila, 5).toString());
                double costUnit = Double.parseDouble(tablaOperaciones.getValueAt(fila, 10).toString());
                double total = trabajo * costUnit;

//                tablaOperaciones.setValueAt(CG15_Double_Formato.setFormat(total), fila, 12);
            }

        });

    }

    private void btnEditarLineaOperacion() {

        btnEditarLineaOperacion.addActionListener((e) -> {

            int linea = tablaOperaciones.getSelectedRow();
            if (linea > -1) {
                editarLineaOperacion(linea);
            }

        });

    }

    private void btnEliminarLineaOperacion() {
        btnEliminarLineaOperacion.addActionListener((e) -> {

            int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                int fila = tablaOperaciones.getSelectedRow();
                String operacion = tablaOperaciones.getValueAt(fila, 1).toString();

                if (materialesAsociadosOperacion(operacion)) {

                    int delMat = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.ASSOCIATED_MATERIALS), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

                    if (delMat == JOptionPane.YES_OPTION) {

                        int filas = tablaMateriales.getRowCount();

                        for (int i = 0; i < filas; i++) {
                            if (tablaMateriales.getValueAt(i, 10).toString().equals(operacion)) {

                                String numeroRegistro = numeroOrdenTB.getText() + "-" + tablaMateriales.getValueAt(i, 1).toString();
                                eliminarMaterialSQL(numeroRegistro);

                            }
                        }

                        quitarOperacionOrdenSQL();
                        updateOpCombo();

                        limpiarFilasMaterial();
                        cargarListaMateriales(numeroOrdenTB.getText());

                    } else {

                        SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

                    }

                } else {

                    quitarOperacionOrdenSQL();
                    updateOpCombo();

                }

            } else {

                SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

            }

        });
    }

    private void quitarOperacionOrdenSQL() {

        int row = tablaOperaciones.getSelectedRow();

        tablaOperaciones.clearSelection();

        String numeroRegistro = numeroOrdenTB.getText() + "-" + tablaOperaciones.getValueAt(row, 1).toString();

        if (Verificar_Registro_Operacion_SQL.isOperationRegistered(numeroRegistro)) {

            eliminarOperacionSQL(numeroRegistro);

            SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.SUCCESS);

        }

        ((LyraTableModel) tablaOperaciones.getModel()).removeRow(row);

    }

    private void updateOpCombo() {
        int rows = tablaOperaciones.getRowCount();
        box.removeAllItems();

        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                if (tablaMateriales.getRowCount() > -1) {
                    box.addItem(tablaOperaciones.getValueAt(i, 1).toString());
                }
            }

            tablaMateriales.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(box));
        } else {
            box.removeAllItems();
            tablaMateriales.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(box));
        }
    }

    private void limpiarFilasOperacion() {

        if (tablaOperaciones.getRowCount() > 0) {
            for (int i = 0; i < tablaOperaciones.getRowCount(); i++) {
                ((LyraTableModel) tablaOperaciones.getModel()).removeRow(i);

                i -= 1;
            }
        }

    }

    private void limpiarFilasMaterial() {

        if (tablaMateriales.getRowCount() > 0) {
            for (int i = 0; i < tablaMateriales.getRowCount(); i++) {
                ((LyraTableModel) tablaMateriales.getModel()).removeRow(i);

                i -= 1;
            }
        }

    }

    private void editarLineaOperacion(int linea) {

        LyraTableModel m = (LyraTableModel) tablaOperaciones.getModel();
        m.setColumnNoEditable(new int[]{1, 3, 5, 9, 10, 11, 12, 13, 14, 15});
        m.setRowEditable(linea);

        tablaOperaciones.setModel(m);
        tablaOperaciones.setRowToImageIcon(linea);
        tablaOperaciones.setButtonImage(new ImageIcon(getClass().getResource("/lyra/access/main_class/icons/modificar.png")));

    }

    private void cerrarEdicionLineaOperacion() {

        LyraTableModel m = (LyraTableModel) tablaOperaciones.getModel();
        m.setRowEditable(-1);
        tablaOperaciones.setModel(m);
        tablaOperaciones.setButtonImage(new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/chekOk.png")));

    }

    private void tablaOperacionesEdicionEvent() {

        tablaOperaciones.setSurrendersFocusOnKeystroke(true);
        /**
         * ********************************************************************************************************************
         */
        TableTextoExpEvent e = (int row) -> {

            Texto_Explicativo te = new Texto_Explicativo();
            te.setPanelAnterior(thisPanel);
            te.textAreaPane.setEditable(false);
            te.btnOkOperacion.setEnabled(false);
            te.btnEliminarLineaOperacion.setEnabled(false);
            te.setTableCell(tablaOperaciones, row, 3);

            if (tablaOperaciones.getValueAt(row, 3) != null) {
                te.setText(tablaOperaciones.getValueAt(row, 3).toString());
            }

            PanelLoader.loadPanel(te, mainContainerPanel);
        };

        tablaOperaciones.getColumnModel().getColumn(2).setCellRenderer(new TextoExplicativo_Render());
        tablaOperaciones.getColumnModel().getColumn(2).setCellEditor(new TextoExplicativo_Editor(e));

        /**
         * ********************************************************************************************************************
         */
        tablaOperaciones.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                int col = tablaOperaciones.getSelectedColumn();

                if (col == 6 | col == 7 | col == 10) {
                    char c = e.getKeyChar();
                    if (Character.isLetter(c)) {
                        e.consume();
                    }
                }

            }

        });
        /**
         * ********************************************************************************************************************
         */
        tablaOperaciones.getDefaultEditor(Object.class).addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                if (tablaOperaciones.getSelectedColumn() == 4) {
                    tablaOperaciones.editCellAt(tablaOperaciones.getSelectedRow(), 6);
                }

            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }

        });

        /**
         * ********************************************************************************************************************
         */
        tablaOperaciones.getColumnModel().getColumn(6).setCellEditor(new CellEditorNumbers());
        tablaOperaciones.getColumnModel().getColumn(7).setCellEditor(new CellEditorNumbers());
        tablaOperaciones.getColumnModel().getColumn(10).setCellEditor(new CellEditorNumbers());
        /**
         * ********************************************************************************************************************
         */
        tablaOperaciones.getColumnModel().getColumn(6).getCellEditor().addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {

                int fila = tablaOperaciones.getSelectedRow();

                if (tablaOperaciones.getValueAt(fila, 6) != null) {
                    if (tablaOperaciones.getValueAt(fila, 6).toString().isEmpty()) {
                        tablaOperaciones.setValueAt(0, fila, 6);
                    }
                }

                if (tablaOperaciones.getValueAt(fila, 6) != null && tablaOperaciones.getValueAt(fila, 7) != null && tablaOperaciones.getValueAt(fila, 10) == null) {

                    calcularTrabajo(fila);

                } else if (tablaOperaciones.getValueAt(fila, 6) != null && tablaOperaciones.getValueAt(fila, 7) != null && tablaOperaciones.getValueAt(fila, 10) != null) {

                    calcularTrabajo(fila);
                    calcularMonto(fila);

                }

                tablaOperaciones.editCellAt(fila, 7);
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }

        });
        /**
         * ********************************************************************************************************************
         */
        tablaOperaciones.getColumnModel().getColumn(7).getCellEditor().addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {

                int fila = tablaOperaciones.getSelectedRow();

                if (tablaOperaciones.getValueAt(fila, 7) != null) {
                    if (tablaOperaciones.getValueAt(fila, 7).toString().isEmpty()) {
                        tablaOperaciones.setValueAt(0, fila, 7);
                    }
                }

                if (tablaOperaciones.getValueAt(fila, 7) != null && tablaOperaciones.getValueAt(fila, 6) != null && tablaOperaciones.getValueAt(fila, 10) == null) {

                    calcularTrabajo(fila);

                } else if (tablaOperaciones.getValueAt(fila, 7) != null && tablaOperaciones.getValueAt(fila, 6) != null && tablaOperaciones.getValueAt(fila, 10) != null) {
                    calcularTrabajo(fila);
                    calcularMonto(fila);

                }

                tablaOperaciones.editCellAt(tablaOperaciones.getSelectedRow(), 8);
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }

        });
        /**
         * ********************************************************************************************************************
         */
        tablaOperaciones.getColumnModel().getColumn(10).getCellEditor().addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {

                int fila = tablaOperaciones.getSelectedRow();

                if (tablaOperaciones.getValueAt(fila, 10) != null) {
                    if (tablaOperaciones.getValueAt(fila, 10).toString().isEmpty()) {
                        tablaOperaciones.setValueAt(0, fila, 10);
                    }
                }

                if (tablaOperaciones.getValueAt(fila, 10) != null && tablaOperaciones.getValueAt(fila, 5) != null) {

                    double trabajo = Double.parseDouble(tablaOperaciones.getValueAt(fila, 5).toString());
                    double costUnit = Double.parseDouble(tablaOperaciones.getValueAt(fila, 10).toString());
                    double total = trabajo * costUnit;

//                    tablaOperaciones.setValueAt(CG15_Double_Formato.setFormat(total), fila, 12);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }

        });

    }

    private void celdasNoEditableOperaciones() {

        tablaOperaciones.getColumnModel().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int col = tablaOperaciones.getSelectedColumn();

            if (col == 0) {
                tablaOperaciones.setColumnSelectionInterval(1, 1);
            } else if (e.getLastIndex() == 4 && col == 3) {
                tablaOperaciones.setColumnSelectionInterval(1, 1);
            } else if (e.getFirstIndex() == 1 && col == 2) {
                tablaOperaciones.setColumnSelectionInterval(4, 4);
            }
        });

    }

    private void calcularTrabajo(int fila) {

        double cant = Double.parseDouble(tablaOperaciones.getValueAt(fila, 6).toString().replaceAll(",", ""));
        double dur = Double.parseDouble(tablaOperaciones.getValueAt(fila, 7).toString().replaceAll(",", ""));
        double trabajo = cant * dur;

//        tablaOperaciones.setValueAt(CG15_Double_Formato.setFormat(trabajo), fila, 5);

    }

    private void calcularMonto(int fila) {

        double trabajo = Double.parseDouble(tablaOperaciones.getValueAt(fila, 5).toString().replaceAll(",", ""));
        double costUnit = Double.parseDouble(tablaOperaciones.getValueAt(fila, 10).toString().replaceAll(",", ""));
        double total = trabajo * costUnit;

//        tablaOperaciones.setValueAt(CG15_Double_Formato.setFormat(total), fila, 12);

    }

    private void eliminarOperacionSQL(String numeroRegistroOp) {

        Delete_Operation_Work_Order del = new Delete_Operation_Work_Order();
        del.deleteOperation(numeroRegistroOp);

    }

    private boolean materialesAsociadosOperacion(String operacion) {

        boolean hasAsociation = false;

        if (tablaMateriales.getRowCount() > 0) {
            for (int i = 0; i < tablaMateriales.getRowCount(); i++) {

                if (tablaMateriales.getValueAt(i, 10).toString().equals(operacion)) {
                    hasAsociation = true;
                }
            }
        }

        return hasAsociation;
    }

    private void btnAgregarLineaMaterial() {

        btnAgregarLineaMaterial.addActionListener((e) -> {

            int filas = tablaMateriales.getRowCount();

            if (filas == 0) {

                LyraTableModel dataModel = (LyraTableModel) tablaMateriales.getModel();
                Object[] celdas = new Object[dataModel.getColumnCount()];

                for (int c = 0; c < dataModel.getColumnCount(); c++) {

                    celdas[c] = null;
                }

                dataModel.addRow(celdas);
                tablaMateriales.setModel(dataModel);
                tablaMateriales.setValueAt("0" + 10, 0, 1);

                tablaMateriales.setRowSelectionInterval(filas, filas);
                editarLineaMateriales(filas);

            } else if (filas > 0) {

                int cantCol = tablaMateriales.getColumnCount() - 1;
                boolean camposCompletos = false;

                for (int i = 0; i < cantCol; i++) {

                    if (i == 0) {
                        i = 1;
                    } else if (i == 3) {
                        i = 4;
                    } else if (i == 11) {
                        break;
                    }

                    if (tablaMateriales.getValueAt(filas - 1, i) == null) {

                        SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS) + ": "
                                + tablaMateriales.getColumnName(i + 1), TypeMessage.WARNING);

                        camposCompletos = false;
                        break;

                    } else {
                        camposCompletos = true;
                    }

                }

                if (camposCompletos) {

                    cerrarEdicionLineaMateriales();

                    LyraTableModel dataModel = (LyraTableModel) tablaMateriales.getModel();
                    Object[] celdas = new Object[dataModel.getColumnCount()];

                    for (int c = 0; c < dataModel.getColumnCount(); c++) {

                        celdas[c] = null;
                    }

                    dataModel.addRow(celdas);
                    tablaMateriales.setModel(dataModel);

                    int value = Integer.parseInt(tablaMateriales.getValueAt(filas - 1, 1).toString());
                    value = value + 10;

                    tablaMateriales.setValueAt("0" + value, tablaMateriales.getRowCount() - 1, 1);

                    tablaMateriales.setRowSelectionInterval(filas, filas);
                    tablaMateriales.setColumnSelectionInterval(3, 3);
                    editarLineaMateriales(filas);

                }

            }

            int rows = tablaOperaciones.getRowCount();

            box.removeAllItems();

            if (rows > -1) {
                for (int i = 0; i < rows; i++) {
                    box.addItem(tablaOperaciones.getValueAt(i, 1).toString());
                }
            }

        });
    }

    private void btnOkMaterial() {

        btnOkMaterial.addActionListener((e) -> {

            int fila = tablaMateriales.getSelectedRow();

            cerrarEdicionLineaMateriales();

            if (tablaMateriales.getValueAt(fila, 5) != null && tablaMateriales.getValueAt(fila, 7) != null) {

                double cantidad = Double.parseDouble(tablaMateriales.getValueAt(fila, 5).toString());
                double precioUnit = Double.parseDouble(tablaMateriales.getValueAt(fila, 7).toString());
                double total = cantidad * precioUnit;

//                tablaMateriales.setValueAt(CG15_Double_Formato.setFormat(total), fila, 9);
            }

        });

    }

    private void btnEditarLineaMaterial() {
        btnEditarLineaMaterial.addActionListener((e) -> {

            int linea = tablaMateriales.getSelectedRow();
            if (linea > -1) {
                editarLineaMateriales(linea);
            }

        });
    }

    private void btnEliminarLineaMaterial() {
        btnEliminarLineaMaterial.addActionListener((e) -> {

            int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                int row = tablaMateriales.getSelectedRow();
                if (row > -1) {

                    String numeroRegistro = numeroOrdenTB.getText() + "-"
                            + tablaMateriales.getValueAt(tablaMateriales.getSelectedRow(), 1).toString();

                    if (Verificar_Registro_Material_SQL.isMaterialRegistered(numeroRegistro)) {

                        eliminarMaterialSQL(numeroRegistro);

                        new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.SUCCESS);

                    }

                    ((LyraTableModel) tablaMateriales.getModel()).removeRow(row);
                    tablaMateriales.clearSelection();
                }
            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

            }

        });
    }

    private void limpiarFilasMateriales() {

        if (tablaMateriales.getRowCount() > 0) {
            for (int i = 0; i < tablaMateriales.getRowCount(); i++) {
                ((LyraTableModel) tablaMateriales.getModel()).removeRow(i);

                i -= 1;
            }
        }

    }

    private void editarLineaMateriales(int linea) {

        LyraTableModel m = (LyraTableModel) tablaMateriales.getModel();
        m.setColumnNoEditable(new int[]{1, 4, 6, 7, 8, 9, 11});
        m.setRowEditable(linea);

        tablaMateriales.setModel(m);
        tablaMateriales.setRowToImageIcon(linea);
        tablaMateriales.setButtonImage(new ImageIcon(getClass().getResource("/lyra/access/main_class/icons/modificar.png")));

    }

    private void cerrarEdicionLineaMateriales() {

        LyraTableModel m = (LyraTableModel) tablaMateriales.getModel();
        m.setRowEditable(-1);
        tablaMateriales.setModel(m);
        tablaMateriales.setButtonImage(new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/chekOk.png")));

    }

    private final JComboBox box = new JComboBox();

    private void tablaMaterialesEdicionEvent() {

        tablaMateriales.setSurrendersFocusOnKeystroke(true);
        /**
         * ********************************************************************************************************************
         */
        SearchButtonInterface button = new SearchButtonInterface() {
            @Override
            public void buscarMaterial(int row, int column) {
//
//                tablaMateriales.setValueAt(null, row, 2);
//                tablaMateriales.setValueAt(null, row, 4);
//                tablaMateriales.setValueAt(null, row, 5);
//                tablaMateriales.setValueAt(null, row, 6);
//                tablaMateriales.setValueAt(null, row, 7);
//                tablaMateriales.setValueAt(null, row, 8);
//                tablaMateriales.setValueAt(null, row, 9);
//

                Lista_Materiales lm = new Lista_Materiales(frame, true);
                lm.setCamposAExtraer(tablaMateriales, row);
                lm.setTitle(MULTITAB.getTitleAt(2));
                lm.setVisible(true);
            }
        };

        tablaMateriales.getColumnModel().getColumn(3).setCellRenderer(new CellRenderSearchButton());
        tablaMateriales.getColumnModel().getColumn(3).setCellEditor(new CellEditorSearchButton(button));

        /**
         * ********************************************************************************************************************
         */
        JTextField tf = new JTextField();

        tf.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                if (Character.isLetter(e.getKeyChar())) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (!tf.getText().isEmpty()) {

                        int codigo = Integer.parseInt(tf.getText().trim());

                        Cargar_Informacion_Material_SQL bc = new Cargar_Informacion_Material_SQL();
                        bc.cargar(codigo);

                        int fila = tablaMateriales.getSelectedRow();

                        if (bc.isExiste()) {

                            tablaMateriales.setValueAt(bc.getDescripcionMaterial(), fila, 4);
                            tablaMateriales.setValueAt(bc.getUm(), fila, 6);
                            tablaMateriales.setValueAt(bc.getPrecioUnit(), fila, 7);
                            tablaMateriales.setValueAt(bc.getAlmacen(), fila, 8);

                            if (tablaMateriales.getValueAt(fila, 5) != null) {

                                double cant = Double.parseDouble(tablaMateriales.getValueAt(fila, 5).toString());
                                double precioUnit = Double.parseDouble(tablaMateriales.getValueAt(fila, 7).toString());
                                double total = cant * precioUnit;

//                                tablaMateriales.setValueAt(CG15_Double_Formato.setFormat(total), fila, 9);
                            }

                        } else {

//                            new SystemMessages(NOT.msg(NOT.MATERIAL_DOES_NOT_EXIST), TypeMessage.ERROR);
                            tf.setText(null);
                            tablaMateriales.setValueAt(null, fila, 4);
                            tablaMateriales.setValueAt(null, fila, 6);
                            tablaMateriales.setValueAt(null, fila, 7);
                            tablaMateriales.setValueAt(null, fila, 8);
                            tablaMateriales.setValueAt(null, fila, 9);
                        }

                    }
                }
            }

        });

        tablaMateriales.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(tf));
        tablaMateriales.getColumnModel().getColumn(5).setCellEditor(new CellEditorNumbers());
        tablaMateriales.getColumnModel().getColumn(5).getCellEditor().addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                int fila = tablaMateriales.getSelectedRow();

                if (tablaMateriales.getValueAt(fila, 5).toString().isEmpty()) {
                    tablaMateriales.setValueAt(null, fila, 5);
                    tablaMateriales.setValueAt(null, fila, 9);
                }

                if (tablaMateriales.getValueAt(fila, 5) != null && tablaMateriales.getValueAt(fila, 7) != null) {

                    double cant = Double.parseDouble(tablaMateriales.getValueAt(fila, 5).toString());
                    double precioUnit = Double.parseDouble(tablaMateriales.getValueAt(fila, 7).toString());
                    double total = cant * precioUnit;

//                    tablaMateriales.setValueAt(CG15_Double_Formato.setFormat(total), fila, 9);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }

        });
        /**
         * ********************************************************************************************************************
         */

        tablaMateriales.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(box));

        /**
         * ********************************************************************************************************************
         */
        tablaMateriales.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                int col = tablaMateriales.getSelectedColumn();

                if (col == 5 | col == 2) {
                    char c = e.getKeyChar();
                    if (Character.isLetter(c)) {
                        e.consume();
                    }
                }

            }

        });

        celdasNoEditableMateriales();
    }

    private void celdasNoEditableMateriales() {

        tablaMateriales.getColumnModel().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int col = tablaMateriales.getSelectedColumn();

            if (col == 0) {
                tablaMateriales.setColumnSelectionInterval(1, 1);
            } else if (e.getLastIndex() == 4 && col == 3) {
                tablaMateriales.setColumnSelectionInterval(2, 2);
            } else if (e.getFirstIndex() == 2 && col == 3) {
                tablaMateriales.setColumnSelectionInterval(4, 4);
            }
        });

    }

    private void eliminarMaterialSQL(String numeroRegistro) {

        Delete_Material_Work_Order del = new Delete_Material_Work_Order();
        del.deleteMaterial(numeroRegistro);

    }

    private int columnaOp = 0;

    private boolean verificarOperaciones() {

        boolean checkOperations = false;

        int filasOp = tablaOperaciones.getRowCount();
        int cols = tablaOperaciones.getColumnCount();

        for (int i = 0; i < filasOp; i++) {

            for (int c = 1; c < cols; c++) {

                if (c == 2) {
                    c = 3;
                } else if (c == 13) {
                    c = 15;
                }

                if (tablaOperaciones.getValueAt(i, c) == null) {
                    checkOperations = false;
                    columnaOp = c;
                    break;

                } else {
                    checkOperations = true;
                }
            }

        }

        return checkOperations;
    }

    private int columnaMat = 0;

    private boolean verificarMateriales() {

        int filasMat = tablaMateriales.getRowCount();
        boolean completoMat = false;

        int col = tablaMateriales.getColumnCount() - 1;

        for (int i = 0; i < filasMat; i++) {

            for (int c = 1; c < col; c++) {

                if (c == 3) {
                    c = 4;
                }

                if (tablaMateriales.getValueAt(i, c) == null) {
                    columnaMat = c;
                    completoMat = false;
                    break;

                } else {
                    completoMat = true;
                }
            }

        }

        return completoMat;
    }

    private void SQLTaskOperation() {

        String numOrden = numeroOrdenTB.getText();

        for (int i = 0; i < tablaOperaciones.getRowCount(); i++) {

            String numeroRegistro = numOrden + "-" + tablaOperaciones.getValueAt(i, 1).toString();

            if (Verificar_Registro_Operacion_SQL.isOperationRegistered(numeroRegistro)) {

                Modify_Work_Order_SQL modifyOrder = new Modify_Work_Order_SQL();

                modifyOrder.setNumOrdenOp(numOrden);
                modifyOrder.setNumOperacion(tablaOperaciones.getValueAt(i, 1).toString());
                modifyOrder.setTextoExplicativoOperacion(tablaOperaciones.getValueAt(i, 3).toString());
                modifyOrder.setTituloOperacion(tablaOperaciones.getValueAt(i, 4).toString());
                modifyOrder.setHorasTrabajoOp(Double.parseDouble(tablaOperaciones.getValueAt(i, 5).toString().replaceAll(",", "")));
                modifyOrder.setCantidadOp(Double.parseDouble(tablaOperaciones.getValueAt(i, 6).toString().replaceAll(",", "")));
                modifyOrder.setDuracionOp(Double.parseDouble(tablaOperaciones.getValueAt(i, 7).toString().replaceAll(",", "")));
                modifyOrder.setCodigoTipoOperacionOp(tablaOperaciones.getValueAt(i, 8).toString());
                modifyOrder.setDescripcionTipoOperacionOp(tablaOperaciones.getValueAt(i, 9).toString());
                modifyOrder.setCostoUnitarioOp(Double.parseDouble(tablaOperaciones.getValueAt(i, 10).toString().replaceAll(",", "")));
                modifyOrder.setUnidadMedidaOp(tablaOperaciones.getValueAt(i, 11).toString());
                modifyOrder.setMontoTotalOp(Double.parseDouble(tablaOperaciones.getValueAt(i, 12).toString().replaceAll(",", "")));

                //si no hay un paquete entonces lo pone en null
                if (tablaOperaciones.getValueAt(i, 13) != null) {
                    modifyOrder.setPaqueteMttoOp(tablaOperaciones.getValueAt(i, 13).toString());
                } else {
                    modifyOrder.setPaqueteMttoOp(null);
                }

                //si no hay una solicitud de pedido entonces la pone en null
                if (tablaOperaciones.getValueAt(i, 14) != null) {
                    modifyOrder.setSolicitudPedidoOp(tablaOperaciones.getValueAt(i, 14).toString());
                } else {
                    modifyOrder.setSolicitudPedidoOp(null);
                }

                modifyOrder.setMonedaOp(tablaOperaciones.getValueAt(i, 15).toString());
                modifyOrder.modifyOperationOfWorkOrder();
            } else {

                Create_Work_Order_SQL newOrder = new Create_Work_Order_SQL();

                newOrder.setNumOrdenOp(numOrden);
                newOrder.setNumOperacion(tablaOperaciones.getValueAt(i, 1).toString());
                newOrder.setTextoExplicativoOperacion(tablaOperaciones.getValueAt(i, 3).toString());
                newOrder.setTituloOperacion(tablaOperaciones.getValueAt(i, 4).toString());
                newOrder.setHorasTrabajo(Double.parseDouble(tablaOperaciones.getValueAt(i, 5).toString().replaceAll(",", "")));
                newOrder.setCantidadOp(Double.parseDouble(tablaOperaciones.getValueAt(i, 6).toString().replaceAll(",", "")));
                newOrder.setDuracionOp(Double.parseDouble(tablaOperaciones.getValueAt(i, 7).toString().replaceAll(",", "")));
                newOrder.setCodigoTipoOperacion(tablaOperaciones.getValueAt(i, 8).toString());
                newOrder.setDescripcionTipoOperacion(tablaOperaciones.getValueAt(i, 9).toString());
                newOrder.setCostoUnitarioOp(Double.parseDouble(tablaOperaciones.getValueAt(i, 10).toString().replaceAll(",", "")));
                newOrder.setUnidadMedidaOp(tablaOperaciones.getValueAt(i, 11).toString());
                newOrder.setMontoTotalOp(Double.parseDouble(tablaOperaciones.getValueAt(i, 12).toString().replaceAll(",", "")));

                //si no hay un paquete entonces lo pone en null
                if (tablaOperaciones.getValueAt(i, 13) != null) {
                    newOrder.setPaqueteMttoOp(tablaOperaciones.getValueAt(i, 13).toString());
                } else {
                    newOrder.setPaqueteMttoOp(null);
                }

                //si no hay una solicitud de pedido entonces la pone en null
                if (tablaOperaciones.getValueAt(i, 14) != null) {
                    newOrder.setSolicitudPedido(tablaOperaciones.getValueAt(i, 14).toString());
                } else {
                    newOrder.setSolicitudPedido(null);
                }

                newOrder.setMoneda(tablaOperaciones.getValueAt(i, 15).toString());

                /*Metodo de creacion de operaciones asociadas a la orden, la iteracion acaba cuando ya se recorrio todas las filas*/
                newOrder.createOperationOfWorkOrder();
            }
        }

    }

    private void SQLTaskMaterial() {

        String numOrden = numeroOrdenTB.getText();

        for (int i = 0; i < tablaMateriales.getRowCount(); i++) {

            String numeroRegistro = numOrden + "-" + tablaMateriales.getValueAt(i, 1).toString();

            if (Verificar_Registro_Material_SQL.isMaterialRegistered(numeroRegistro)) {

                Modify_Work_Order_SQL modifyOrder = new Modify_Work_Order_SQL();

                modifyOrder.setNumeroOrdenMat(numOrden);
                modifyOrder.setPosicionMat(tablaMateriales.getValueAt(i, 1).toString());
                modifyOrder.setCodigoMaterialMat(tablaMateriales.getValueAt(i, 2).toString());
                modifyOrder.setDescripcionMaterialMat(tablaMateriales.getValueAt(i, 4).toString());
                modifyOrder.setCantidadMat(Double.parseDouble(tablaMateriales.getValueAt(i, 5).toString().replaceAll(",", "")));
                modifyOrder.setUnidadMedidaMat(tablaMateriales.getValueAt(i, 6).toString());
                modifyOrder.setCostoUnitarioMat(Double.parseDouble(tablaMateriales.getValueAt(i, 7).toString().replaceAll(",", "")));
                modifyOrder.setCodigoAlmacenMat(tablaMateriales.getValueAt(i, 8).toString());
                modifyOrder.setMontoTotalMat(Double.parseDouble(tablaMateriales.getValueAt(i, 9).toString().replaceAll(",", "")));
                modifyOrder.setNumOpMat(tablaMateriales.getValueAt(i, 10).toString());

                //si no hay un paquete entonces lo pone en null
                if (tablaMateriales.getValueAt(i, 11) != null) {
                    modifyOrder.setPaqueteMttoMat(tablaMateriales.getValueAt(i, 11).toString());
                } else {
                    modifyOrder.setPaqueteMttoMat(null);
                }


                /*Metodo para la creacion de registro de material asociado a la orden*/
                modifyOrder.modifyMaterialOfWorkOrder();

            } else {

                Create_Work_Order_SQL newOrder = new Create_Work_Order_SQL();

                newOrder.setNumeroOrdenMat(numOrden);
                newOrder.setPosicionMat(tablaMateriales.getValueAt(i, 1).toString());
                newOrder.setCodigoMaterial(tablaMateriales.getValueAt(i, 2).toString());
                newOrder.setDescripcionMaterial(tablaMateriales.getValueAt(i, 4).toString());
                newOrder.setCantidadMat(Double.parseDouble(tablaMateriales.getValueAt(i, 5).toString().replaceAll(",", "")));
                newOrder.setUnidadMedida(tablaMateriales.getValueAt(i, 6).toString());
                newOrder.setCostoUnitarioMat(Double.parseDouble(tablaMateriales.getValueAt(i, 7).toString().replaceAll(",", "")));
                newOrder.setCodigoAlmacen(tablaMateriales.getValueAt(i, 8).toString());
                newOrder.setMontoTotalMat(Double.parseDouble(tablaMateriales.getValueAt(i, 9).toString().replaceAll(",", "")));
                newOrder.setNumOpMat(tablaMateriales.getValueAt(i, 10).toString());

                //si no hay un paquete entonces lo pone en null
                if (tablaMateriales.getValueAt(i, 11) != null) {
                    newOrder.setPaqueteMttoMat(tablaMateriales.getValueAt(i, 11).toString());
                } else {
                    newOrder.setPaqueteMttoMat(null);
                }


                /*Metodo para la creacion de registro de material asociado a la orden*/
                newOrder.createMaterialOfWorkOrder();

            }

        }

    }

    //Gestion de estatus de la orden
    private final ActionListener estadoPlaneacion = (ActionEvent e) -> {
        setOrderInPlanning();
    };

    private void setOrderInPlanning() {

        int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            //VERIFICA PRIMERO SI EL USUARUI TIENE PERMISOS PARA ESTA ACCION
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST2_ORDER_IN_PLANNING.getStatusCode())) {

                if (camposLlenos()) {

                    codigoEstatusTB.setText(StatusOrder.ST2_ORDER_IN_PLANNING.getStatusCode());
                    descripcionEstatusTB.setText(StatusOrder.ST2_ORDER_IN_PLANNING.getDescription());

                    labelUsuarioPlaneador.setText(fullname);

                    modificarOrdenTrabajo();

                    PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

                    String mensaje = StatusOrder.ST2_ORDER_IN_PLANNING.getDescription() + ":" + numeroOrdenTB.getText();

                    SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, mensaje, TypeMessage.SUCCESS);

                } else {

                    SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);

                }
            } else {

                SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);

            }
        } else {

            SystemMessages msg = new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.WARNING);

        }

    }

    private final ActionListener estadoAprobacion = (ActionEvent e) -> {
        setOrderInApproval();
    };

    private void setOrderInApproval() {

        int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            //verifica si el usuario tiene permiso para la accion
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST3_ORDER_UNDER_APPROVAL.getStatusCode())) {

                if (camposLlenos()) {

                    if (tablaOperaciones.getRowCount() > 0 && tablaMateriales.getRowCount() == 0) {

                        if (verificarOperaciones()) {

                            //se ejecuta el codigo de guardado cabecera y operaciones si las operaciones estan correctamente llenadas
                            codigoEstatusTB.setText(StatusOrder.ST3_ORDER_UNDER_APPROVAL.getStatusCode());
                            descripcionEstatusTB.setText(StatusOrder.ST3_ORDER_UNDER_APPROVAL.getDescription());

                            modificarOrdenTrabajo();
                            SQLTaskOperation();

                            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

                            String msg1 = StatusOrder.ST3_ORDER_UNDER_APPROVAL.getDescription() + ":" + numeroOrdenTB.getText();

                            new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.SUCCESS);

                        } else {

                            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_OPERATIONS) + ": " + columnaOp, TypeMessage.SUCCESS);

                        }

                    } else if (tablaOperaciones.getRowCount() > 0 && tablaMateriales.getRowCount() > 0) {

                        if (verificarOperaciones() && verificarMateriales()) {

                            //se ejecuta el codigo de guardado cabecera, operaciones y materiales si las operaciones y materiales estan correctamente llenadas                        
                            codigoEstatusTB.setText(StatusOrder.ST3_ORDER_UNDER_APPROVAL.getStatusCode());
                            descripcionEstatusTB.setText(StatusOrder.ST3_ORDER_UNDER_APPROVAL.getDescription());

                            modificarOrdenTrabajo();
                            SQLTaskOperation();
                            SQLTaskMaterial();

                            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

                            String msg1 = StatusOrder.ST3_ORDER_UNDER_APPROVAL.getDescription() + ":" + numeroOrdenTB.getText();

                            new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.SUCCESS);

                        } else {

                            if (columnaOp > 0) {

                                String msg1 = NOT.msg(NOT.INCOMPLETE_OPERATIONS) + ":" + tablaOperaciones.getColumnName(columnaOp);

                                new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.ERROR);

                            }

                            if (columnaMat > 0) {

                                String msg1 = NOT.msg(NOT.INCOMPLETE_MATERIALS) + ":" + tablaMateriales.getColumnName(columnaMat);

                                new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.ERROR);

                            }
                        }

                    } else if (tablaOperaciones.getRowCount() == 0 && tablaMateriales.getRowCount() == 0) {

                        new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_OPERATIONS), TypeMessage.ERROR);

                        MULTITAB.setSelectedIndex(1);
                    }

                } else {

                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.WARNING);

                }
            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);

            }

        } else {

            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.WARNING);

        }
    }

    private final ActionListener estadoAprobada = (ActionEvent e) -> {
        setOrderApproved();
    };

    private void setOrderApproved() {

        int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            //verifica si el usuario tiene permiso para aprobar la orden
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST4_ORDER_APPROVED.getStatusCode())) {

                if (camposLlenos()) {

                    codigoEstatusTB.setText(StatusOrder.ST4_ORDER_APPROVED.getStatusCode());
                    descripcionEstatusTB.setText(StatusOrder.ST4_ORDER_APPROVED.getDescription());

                    labelUsuarioAprobador.setText(fullname);

                    modificarOrdenTrabajo();

                    PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

                    String msg1 = StatusOrder.ST4_ORDER_APPROVED.getDescription() + ":" + numeroOrdenTB.getText();

                    new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.SUCCESS);

                } else {

                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);

                }
            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);

            }
        } else {

            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

        }
    }

    private final ActionListener estadoProgramada = (ActionEvent e) -> {
        setOrderScheduled();
    };

    private void setOrderScheduled() {
        int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            //verifica los permisos del usuario
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST5_SCHEDULED_ORDER.getStatusCode())) {

                if (camposLlenos()) {

                    codigoEstatusTB.setText(StatusOrder.ST5_SCHEDULED_ORDER.getStatusCode());
                    descripcionEstatusTB.setText(StatusOrder.ST5_SCHEDULED_ORDER.getDescription());

                    modificarOrdenTrabajo();

                    PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

                    String msg1 = StatusOrder.ST5_SCHEDULED_ORDER.getDescription() + ":" + numeroOrdenTB.getText();

                    new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.SUCCESS);

                } else {

                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);

                }
            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);

            }

        } else {

            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

        }
    }

    private final ActionListener estadoEnEjecucion = (ActionEvent e) -> {
        setOrderInExecution();
    };

    private void setOrderInExecution() {
        int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            //verifica los permisos del usuario
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST6_ORDER_IN_EXECUTION.getStatusCode())) {
                if (camposLlenos()) {

                    codigoEstatusTB.setText(StatusOrder.ST6_ORDER_IN_EXECUTION.getStatusCode());
                    descripcionEstatusTB.setText(StatusOrder.ST6_ORDER_IN_EXECUTION.getDescription());

                    modificarOrdenTrabajo();

                    PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

                    String msg1 = StatusOrder.ST6_ORDER_IN_EXECUTION.getDescription() + ":" + numeroOrdenTB.getText();

                    new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.SUCCESS);

                } else {

                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);

                }
            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);

            }
        } else {

            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

        }
    }

    private final ActionListener estadoEjecutada = (ActionEvent e) -> {
        setOrderExecutted();
    };

    private void setOrderExecutted() {
        int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            //verifica los permisos del usuario
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST7_ORDER_EXECUTED.getStatusCode())) {

                if (camposLlenos()) {

                    codigoEstatusTB.setText(StatusOrder.ST7_ORDER_EXECUTED.getStatusCode());
                    descripcionEstatusTB.setText(StatusOrder.ST7_ORDER_EXECUTED.getDescription());

//                    labelUsuarioEjecutor.setText(FullUserName.getName(username));

                    modificarOrdenTrabajo();

                    PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

                    String msg1 = StatusOrder.ST7_ORDER_EXECUTED.getDescription() + ":" + numeroOrdenTB.getText();

                    new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.SUCCESS);

                } else {

                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);

                }
                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);

            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);

            }
        } else {

            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

        }
    }

    private final ActionListener estadoCerrada = (ActionEvent e) -> {
        setOrderClosed();
    };

    private void setOrderClosed() {

        int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            //verifica los permisos del usuario
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST8_CLOSED_ORDER.getStatusCode())) {

                if (camposLlenos()) {

                    codigoEstatusTB.setText(StatusOrder.ST8_CLOSED_ORDER.getStatusCode());
                    descripcionEstatusTB.setText(StatusOrder.ST8_CLOSED_ORDER.getDescription());

                    modificarOrdenTrabajo();

                    PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

                    String msg1 = StatusOrder.ST8_CLOSED_ORDER.getDescription() + ":" + numeroOrdenTB.getText();

                    new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.SUCCESS);

                } else {

                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);

                }
            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);

            }
        } else {

            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

        }
    }

    private final ActionListener estadoRechazado = (ActionEvent e) -> {
        setOrderRejected();
    };

    private void setOrderRejected() {

        int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            //verifica los permisos del usuario
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST9_ORDER_REJECTED.getStatusCode())) {

                if (camposLlenos()) {

                    codigoEstatusTB.setText(StatusOrder.ST9_ORDER_REJECTED.getStatusCode());
                    descripcionEstatusTB.setText(StatusOrder.ST9_ORDER_REJECTED.getDescription());

                    modificarOrdenTrabajo();

                    PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

                    String msg1 = StatusOrder.ST9_ORDER_REJECTED.getDescription() + ":" + numeroOrdenTB.getText();

                    new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.SUCCESS);

                } else {

                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);

                }
            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);

            }
        } else {

            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

        }
    }

    private final ActionListener estadoCancelada = (ActionEvent e) -> {
        setOrderCanceled();
    };

    private void setOrderCanceled() {

        int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.WANT_TO_CONTINUE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            //verifica los permisos del usuario
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST10_ORDER_CANCELED.getStatusCode())) {

                if (camposLlenos()) {

                    codigoEstatusTB.setText(StatusOrder.ST10_ORDER_CANCELED.getStatusCode());
                    descripcionEstatusTB.setText(StatusOrder.ST10_ORDER_CANCELED.getDescription());

                    modificarOrdenTrabajo();

                    PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

                    String msg1 = StatusOrder.ST10_ORDER_CANCELED.getDescription() + ":" + numeroOrdenTB.getText();

                    new SystemMessages(LyraWorkspace.NotificationLabel, msg1, TypeMessage.SUCCESS);

                } else {

                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);

                }
            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);

            }
        } else {

            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

        }
    }

    private void funcionesEstatosOrdenMenu() {

        //Orden en planeacion
        menuEstatusPlaneacion.addActionListener(estadoPlaneacion);
        //Orden en aprobacion
        menuEstatusAprobacion.addActionListener(estadoAprobacion);
        //Orden aprobada
        menuEstatusAprobada.addActionListener(estadoAprobada);
        //Orden Programada
        menuEstatusProgramada.addActionListener(estadoProgramada);
        //Orden En Ejecucion
        menuEstatusEjecucion.addActionListener(estadoEnEjecucion);
        //Orden En Ejecutada
        menuEstatusEjecutada.addActionListener(estadoEjecutada);
        //Orden Cerrada
        menuEstatusCerrada.addActionListener(estadoCerrada);
        //Orden Rechazado
        menuEstatusRechazado.addActionListener(estadoRechazado);
        //Orden Cancelada
        menuEstatusCancelada.addActionListener(estadoCancelada);
        botonesEstadosOrdenes();
    }

    private void botonesEstadosOrdenes() {

        btn_OIPNN.addActionListener(estadoPlaneacion);
        btn_OUAPP.addActionListener(estadoAprobacion);
        btn_OAPPV.addActionListener(estadoAprobada);
        btn_OSCHD.addActionListener(estadoProgramada);
        btn_OIEXN.addActionListener(estadoEnEjecucion);
        btn_OEXTD.addActionListener(estadoEjecutada);

        btn_OCLSD.addActionListener(estadoCerrada);

        btn_ORJTD.addActionListener(estadoRechazado);
        btn_OCCLD.addActionListener(estadoCancelada);
    }

    public void disableComponentsOnVisualizingModule() {

        disableAllComponents();
        disableAllJMenusComponents();
        enabledExtendedTextOperations();

    }

    private void disableAllComponents() {

        btnGuardar_Orden.setEnabled(false);
        btnNueva_Orden.setEnabled(false);

        btn_OIPNN.setEnabled(false);
        btn_OUAPP.setEnabled(false);
        btn_OAPPV.setEnabled(false);
        btn_OSCHD.setEnabled(false);
        btn_OIEXN.setEnabled(false);
        btn_OEXTD.setEnabled(false);
        btn_OCLSD.setEnabled(false);
        btn_ORJTD.setEnabled(false);
        btn_OCCLD.setEnabled(false);

        numeroOrdenTB.setEditable(false);
        codigoEstatusTB.setEditable(false);
        descripcionEstatusTB.setEditable(false);
        paradaCB.setEnabled(false);
        tituloOrdenTB.setEditable(false);
        btnDescripcionExtendida.setEnabled(true);
        descripcionExtendidaOrdenTB.setEditable(false);

        codigoTipoMttoTB.setEditable(false);
        descripcionTipoMttoTB.setEditable(false);
        btnTipoMtto.setEnabled(false);

        codigoClaseOrdenTB.setEditable(false);
        descripcionClaseOrdenTB.setEditable(false);
        btnClaseMtto.setEnabled(false);

        codigoCriticidadTB.setEditable(false);
        descripcionCriticidadTB.setEditable(false);
        btnCriticidadMtto.setEnabled(false);

        codigoSistemasTB.setEditable(false);
        descripcionSistemasTB.setEditable(false);
        btnSistema.setEnabled(false);

        codigoComponenteTB.setEditable(false);
        descripcionComponenteTB.setEditable(false);
        btnComponente.setEnabled(false);

        codigoSintomaTB.setEditable(false);
        descripcionSintomaTB.setEditable(false);
        btnSintoma.setEnabled(false);

        codigoEquipoTB.setEditable(false);
        btnEquipo.setEnabled(false);

        codigoUbicacionTB.setEditable(false);
        btnUbicacion.setEnabled(false);

        fechaInicioReal.getCalendarButton().setEnabled(false);
        ((JTextFieldDateEditor) fechaInicioReal.getDateEditor()).setEditable(false);

        fechaFinReal.getCalendarButton().setEnabled(false);
        ((JTextFieldDateEditor) fechaFinReal.getDateEditor()).setEditable(false);

        horaInicioReal.setEditable(false);
        btnHoraInicioReal.setEnabled(false);
        horaFinReal.setEditable(false);
        btnHoraFinReal.setEnabled(false);
        totalHoraReales.setEditable(false);

        fechaInicioProgramado.getCalendarButton().setEnabled(false);
        ((JTextFieldDateEditor) fechaInicioProgramado.getDateEditor()).setEditable(false);

        fechaFinProgramado.getCalendarButton().setEnabled(false);
        ((JTextFieldDateEditor) fechaFinProgramado.getDateEditor()).setEditable(false);

        horaInicioProgramada.setEditable(false);
        btnHoraProgramada.setEnabled(false);
        horaFinProgramado.setEditable(false);
        btnHoraCierreProg.setEnabled(false);
        totalHorasProgramado.setEditable(false);

        fechaCreacionOrden.getCalendarButton().setEnabled(false);
        ((JTextFieldDateEditor) fechaCreacionOrden.getDateEditor()).setEditable(false);

        fechaAutorizacionOrden.getCalendarButton().setEnabled(false);
        ((JTextFieldDateEditor) fechaAutorizacionOrden.getDateEditor()).setEditable(false);

        horaCreacionOrden.setEditable(false);
        btnHoraCreacion.setEnabled(false);
        horaAutorizacionOrden.setEditable(false);
        btnHoraAutorizada.setEnabled(false);
        totalHorasCreacionOrden.setEditable(false);

        codigoGrupoPlanTB.setEditable(false);
        codigoSolicitanteTB.setEditable(false);
        btnSolicitante.setEnabled(false);

        codigoResponsableTB.setEditable(false);
        btnResponsable.setEnabled(false);

        btnAgregarLineaOperacion.setEnabled(false);
        btnOkOperacion.setEnabled(false);
        btnEditarLineaOperacion.setEnabled(false);
        btnEliminarLineaOperacion.setEnabled(false);

        btnAgregarLineaMaterial.setEnabled(false);
        btnOkMaterial.setEnabled(false);
        btnEditarLineaMaterial.setEnabled(false);
        btnEliminarLineaMaterial.setEnabled(false);

        sociedadTB.setEditable(false);
        areaTB.setEditable(false);
        emplazamientoTB.setEditable(false);

        contadorTB.setEditable(false);
        valorContadorTB.setEditable(false);
        hojaRutaTB.setEditable(false);
        planTB.setEditable(false);

    }

    private void disableAllJMenusComponents() {

        menuOrdenTrabajo.setEnabled(true);
        menuGuardarOrden.setEnabled(false);
        menuNuevaOrden.setEnabled(false);
        menuModificarOrden.setEnabled(false);
        menuVisualizarOrden.setEnabled(false);

        menuEstatus.setEnabled(true);
        menuListadoEstatus.setEnabled(true);

        menuEstatusPlaneacion.setEnabled(false);
        menuEstatusAprobacion.setEnabled(false);
        menuEstatusAprobada.setEnabled(false);
        menuEstatusProgramada.setEnabled(false);
        menuEstatusEjecucion.setEnabled(false);
        menuEstatusEjecutada.setEnabled(false);
        menuEstatusCerrada.setEnabled(false);
        menuEstatusRechazado.setEnabled(false);
        menuEstatusCancelada.setEnabled(false);

        menuSalir.setEnabled(true);

        menuCabeceraOrden.setEnabled(true);
        menuEquipo.setEnabled(false);
        menuUbicaciones.setEnabled(false);
        menuTipoMantenimiento.setEnabled(false);
        menuClasesMantenimiento.setEnabled(false);
        menuPrioridades.setEnabled(false);
        menuSistemas.setEnabled(false);
        menuComponentes.setEnabled(false);
        menuSintomas.setEnabled(false);

        menuRecursos.setEnabled(true);
        menuOperaciones.setEnabled(true);
        menuMateriales.setEnabled(true);

        menuCostosOrden.setEnabled(true);
        menuCostos.setEnabled(true);

    }

    private void enabledExtendedTextOperations() {

        tablaOperaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaOperaciones.getSelectedRow();

                if (fila > -1) {

                    LyraTableModel mce = (LyraTableModel) tablaOperaciones.getModel();
                    mce.setRowEditable(-1);
                    tablaOperaciones.setModel(mce);

                    LyraTableModel mae = (LyraTableModel) tablaOperaciones.getModel();
                    mae.setColumnNoEditable(new int[]{1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
                    mae.setRowEditable(fila);
                    tablaOperaciones.setModel(mae);
                }
            }

        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_O03 = new javax.swing.JMenuBar();
        menuOrdenTrabajo = new javax.swing.JMenu();
        menuGuardarOrden = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menuNuevaOrden = new javax.swing.JMenuItem();
        menuModificarOrden = new javax.swing.JMenuItem();
        menuVisualizarOrden = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        menuEstatus = new javax.swing.JMenu();
        menuListadoEstatus = new javax.swing.JMenu();
        menuEstatusPlaneacion = new javax.swing.JMenuItem();
        menuEstatusAprobacion = new javax.swing.JMenuItem();
        menuEstatusAprobada = new javax.swing.JMenuItem();
        menuEstatusProgramada = new javax.swing.JMenuItem();
        menuEstatusEjecucion = new javax.swing.JMenuItem();
        menuEstatusEjecutada = new javax.swing.JMenuItem();
        menuEstatusCerrada = new javax.swing.JMenuItem();
        menuEstatusRechazado = new javax.swing.JMenuItem();
        menuEstatusCancelada = new javax.swing.JMenuItem();
        separador2 = new javax.swing.JPopupMenu.Separator();
        menuSalir = new javax.swing.JMenuItem();
        menuCabeceraOrden = new javax.swing.JMenu();
        menuEquipo = new javax.swing.JMenuItem();
        menuUbicaciones = new javax.swing.JMenuItem();
        separador3 = new javax.swing.JPopupMenu.Separator();
        menuTipoMantenimiento = new javax.swing.JMenuItem();
        menuClasesMantenimiento = new javax.swing.JMenuItem();
        menuPrioridades = new javax.swing.JMenuItem();
        separador4 = new javax.swing.JPopupMenu.Separator();
        menuSistemas = new javax.swing.JMenuItem();
        menuComponentes = new javax.swing.JMenuItem();
        menuSintomas = new javax.swing.JMenuItem();
        menuRecursos = new javax.swing.JMenu();
        menuOperaciones = new javax.swing.JMenuItem();
        menuMateriales = new javax.swing.JMenuItem();
        menuCostosOrden = new javax.swing.JMenu();
        menuCostos = new javax.swing.JMenuItem();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        numeroOrdenLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        numeroOrdenTB = new javax.swing.JTextField();
        tituloOrdenTB = new javax.swing.JTextField();
        estatusLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoEstatusTB = new javax.swing.JTextField();
        descripcionEstatusTB = new javax.swing.JTextField();
        descripcionOrdenLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        MULTITAB = new javax.swing.JTabbedPane();
        general = new javax.swing.JPanel();
        generalData = new javax.swing.JPanel();
        labelTaxonomia = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelTipoMtto = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoTipoMttoTB = new javax.swing.JTextField();
        labelClaseMtto = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoClaseOrdenTB = new javax.swing.JTextField();
        labelCriticidad = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoCriticidadTB = new javax.swing.JTextField();
        labelSistema = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoSistemasTB = new javax.swing.JTextField();
        labelComponente = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoComponenteTB = new javax.swing.JTextField();
        labelSintoma = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoSintomaTB = new javax.swing.JTextField();
        descripcionTipoMttoTB = new javax.swing.JTextField();
        btnTipoMtto = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        descripcionClaseOrdenTB = new javax.swing.JTextField();
        descripcionCriticidadTB = new javax.swing.JTextField();
        btnClaseMtto = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnCriticidadMtto = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        descripcionSistemasTB = new javax.swing.JTextField();
        descripcionComponenteTB = new javax.swing.JTextField();
        descripcionSintomaTB = new javax.swing.JTextField();
        btnSistema = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnComponente = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnSintoma = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        provisioning = new javax.swing.JPanel();
        labelCronologia = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelFechaInicio = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fechaInicioReal = new com.toedter.calendar.JDateChooser();
        labelFechaFin = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fechaFinReal = new com.toedter.calendar.JDateChooser();
        fechaFinProgramado = new com.toedter.calendar.JDateChooser();
        labelFechaProgramada = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fechaInicioProgramado = new com.toedter.calendar.JDateChooser();
        labelFechaCierre = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fechaCreacionOrden = new com.toedter.calendar.JDateChooser();
        labelFechaAutorizada = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        fechaAutorizacionOrden = new com.toedter.calendar.JDateChooser();
        labelFechaCreacionOrden = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelHoraInicio = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelHoraFin = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelHoraCierre = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelHoraProgramada = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelHoraCreacion = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelHoraAutorizada = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelDurInicioFin = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelTotalProg = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelDurCreacion = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        totalHoraReales = new javax.swing.JFormattedTextField();
        totalHorasProgramado = new javax.swing.JFormattedTextField();
        totalHorasCreacionOrden = new javax.swing.JFormattedTextField();
        btnHoraInicioReal = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnHoraFinReal = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnHoraCierreProg = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnHoraProgramada = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnHoraCreacion = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnHoraAutorizada = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        horaInicioReal = new javax.swing.JTextField();
        horaFinReal = new javax.swing.JTextField();
        horaInicioProgramada = new javax.swing.JTextField();
        horaFinProgramado = new javax.swing.JTextField();
        horaCreacionOrden = new javax.swing.JTextField();
        horaAutorizacionOrden = new javax.swing.JTextField();
        manufacturingData = new javax.swing.JPanel();
        labelObjetosTecnicos = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelEquipo = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoEquipoTB = new javax.swing.JTextField();
        btnEquipo = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        descripcionEquipoLB = new javax.swing.JLabel();
        labelUbicacion = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoUbicacionTB = new javax.swing.JTextField();
        btnUbicacion = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        descripcionUbicacionLB = new javax.swing.JLabel();
        manufacturingData5 = new javax.swing.JPanel();
        labelGestionOrden = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelSolicitante = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoSolicitanteTB = new javax.swing.JTextField();
        btnSolicitante = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        descripcionSolicitanteLB = new javax.swing.JLabel();
        labelResponsable = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoResponsableTB = new javax.swing.JTextField();
        btnResponsable = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        descripcionResponsableLB = new javax.swing.JLabel();
        labelGrupoPlanif = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoGrupoPlanTB = new javax.swing.JFormattedTextField();
        descripcionGrupoPlanif = new javax.swing.JLabel();
        operaciones = new javax.swing.JPanel();
        btnAgregarLineaOperacion = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEditarLineaOperacion = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEliminarLineaOperacion = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnOkOperacion = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaOperaciones = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();
        materiales = new javax.swing.JPanel();
        btnAgregarLineaMaterial = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEditarLineaMaterial = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEliminarLineaMaterial = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaMateriales = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();
        btnOkMaterial = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        costos = new javax.swing.JPanel();
        manufacturingData1 = new javax.swing.JPanel();
        labelTiempoEstimado = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelHorasEstimadas = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelTiempoReal = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelHorasReal = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCostosEstimados = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCostoEstimado = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCostosReales = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCostoReal = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCreadaPor = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelPlaneadaPor = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelAprobadaPor = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelEjecutadaPor = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelUsuarioCreador = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        labelUsuarioPlaneador = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        labelUsuarioAprobador = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        labelUsuarioEjecutor = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        labelTotales = new com.simplecore.erp.gui.components.labels.JLabelHQLongFraming();
        labelAprobaciones = new com.simplecore.erp.gui.components.labels.JLabelHQLongFraming();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResumenCostos = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();
        manufacturingData4 = new javax.swing.JPanel();
        labelOrganizacion = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelCentroCostos = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelArea = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        areaTB = new javax.swing.JTextField();
        centroCostosTB = new javax.swing.JTextField();
        descripcionCentroCosto = new javax.swing.JLabel();
        descripcionArea = new javax.swing.JLabel();
        labelSociedad = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        sociedadTB = new javax.swing.JTextField();
        descripcionSociedad = new javax.swing.JLabel();
        labelEmplazamiento = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        emplazamientoTB = new javax.swing.JTextField();
        descripcionEmplazamientro = new javax.swing.JLabel();
        costos1 = new javax.swing.JPanel();
        manufacturingData3 = new javax.swing.JPanel();
        labelControl = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelValorContador = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        valorContadorTB = new javax.swing.JTextField();
        labelContador = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        contadorTB = new javax.swing.JTextField();
        labelHojaRuta = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        hojaRutaTB = new javax.swing.JTextField();
        labelPlan = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        planTB = new javax.swing.JTextField();
        paradaCB = new javax.swing.JCheckBox();
        panelDescripcionExt = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        descripcionExtendidaOrdenTB = new javax.swing.JTextArea();
        btnDescripcionExtendida = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir_CrearOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnGuardar_Orden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btn_OIPNN = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btn_OUAPP = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btn_OAPPV = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btn_OSCHD = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btn_OIEXN = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btn_OEXTD = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btn_OCLSD = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btn_ORJTD = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btn_OCCLD = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnNueva_Orden = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        menuOrdenTrabajo.setText("Orden de trabajo");

        menuGuardarOrden.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuGuardarOrden.setText("Guardar Orden");
        menuOrdenTrabajo.add(menuGuardarOrden);
        menuOrdenTrabajo.add(jSeparator4);

        menuNuevaOrden.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuNuevaOrden.setText("Nueva Orden");
        menuOrdenTrabajo.add(menuNuevaOrden);

        menuModificarOrden.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuModificarOrden.setText("Modificar Orden");
        menuOrdenTrabajo.add(menuModificarOrden);

        menuVisualizarOrden.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuVisualizarOrden.setText("Visualizar Orden");
        menuOrdenTrabajo.add(menuVisualizarOrden);
        menuOrdenTrabajo.add(separador);

        menuEstatus.setText("Gestionar Estatus");

        menuListadoEstatus.setText("Cambio Estatus");

        menuEstatusPlaneacion.setText("Pasar a En Planeación");
        menuEstatusPlaneacion.setEnabled(false);
        menuListadoEstatus.add(menuEstatusPlaneacion);

        menuEstatusAprobacion.setText("Pasar a En Aprobación");
        menuEstatusAprobacion.setEnabled(false);
        menuListadoEstatus.add(menuEstatusAprobacion);

        menuEstatusAprobada.setText("Pasar a Aprobada");
        menuEstatusAprobada.setEnabled(false);
        menuListadoEstatus.add(menuEstatusAprobada);

        menuEstatusProgramada.setText("Pasar a Programada");
        menuEstatusProgramada.setEnabled(false);
        menuListadoEstatus.add(menuEstatusProgramada);

        menuEstatusEjecucion.setText("Pasar a En Ejecución");
        menuEstatusEjecucion.setEnabled(false);
        menuListadoEstatus.add(menuEstatusEjecucion);

        menuEstatusEjecutada.setText("Pasar a Ejecutada");
        menuEstatusEjecutada.setEnabled(false);
        menuListadoEstatus.add(menuEstatusEjecutada);

        menuEstatusCerrada.setText("Pasar a Cerrada");
        menuEstatusCerrada.setEnabled(false);
        menuListadoEstatus.add(menuEstatusCerrada);

        menuEstatusRechazado.setText("Pasar a Rechazada");
        menuEstatusRechazado.setEnabled(false);
        menuListadoEstatus.add(menuEstatusRechazado);

        menuEstatusCancelada.setText("Pasar a Cancelada");
        menuEstatusCancelada.setEnabled(false);
        menuListadoEstatus.add(menuEstatusCancelada);

        menuEstatus.add(menuListadoEstatus);

        menuOrdenTrabajo.add(menuEstatus);
        menuOrdenTrabajo.add(separador2);

        menuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menuSalir.setText("Salir");
        menuOrdenTrabajo.add(menuSalir);

        menuBar_O03.add(menuOrdenTrabajo);

        menuCabeceraOrden.setText("Cabecera de orden");

        menuEquipo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuEquipo.setText("Equipo");
        menuCabeceraOrden.add(menuEquipo);

        menuUbicaciones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuUbicaciones.setText("Ubicaciones");
        menuCabeceraOrden.add(menuUbicaciones);
        menuCabeceraOrden.add(separador3);

        menuTipoMantenimiento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuTipoMantenimiento.setText("Tipos de mantenimiento");
        menuCabeceraOrden.add(menuTipoMantenimiento);

        menuClasesMantenimiento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuClasesMantenimiento.setText("Clases de mantenimiento");
        menuCabeceraOrden.add(menuClasesMantenimiento);

        menuPrioridades.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuPrioridades.setText("Prioridades");
        menuCabeceraOrden.add(menuPrioridades);
        menuCabeceraOrden.add(separador4);

        menuSistemas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuSistemas.setText("Sistemas de Equipo");
        menuCabeceraOrden.add(menuSistemas);

        menuComponentes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuComponentes.setText("Componentes");
        menuCabeceraOrden.add(menuComponentes);

        menuSintomas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuSintomas.setText("Sintomas");
        menuCabeceraOrden.add(menuSintomas);

        menuBar_O03.add(menuCabeceraOrden);

        menuRecursos.setText("Recursos");

        menuOperaciones.setText("Operaciones");
        menuRecursos.add(menuOperaciones);

        menuMateriales.setText("Materiales");
        menuRecursos.add(menuMateriales);

        menuBar_O03.add(menuRecursos);

        menuCostosOrden.setText("Costes");

        menuCostos.setText("Costes de la orden");
        menuCostosOrden.add(menuCostos);

        menuBar_O03.add(menuCostosOrden);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        numeroOrdenLB.setText("Orden");
        numeroOrdenLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        numeroOrdenTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        tituloOrdenTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        estatusLB.setText("Estatus");
        estatusLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoEstatusTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        descripcionEstatusTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        descripcionOrdenLB.setText("Descripcion");
        descripcionOrdenLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        MULTITAB.setBackground(new java.awt.Color(202, 216, 237));
        MULTITAB.setForeground(new java.awt.Color(102, 102, 102));
        MULTITAB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        general.setBackground(new java.awt.Color(238, 244, 254));
        general.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        generalData.setBackground(new java.awt.Color(202, 219, 236));
        generalData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        labelTaxonomia.setText("Taxonomía");
        labelTaxonomia.setColorBordes(new java.awt.Color(117, 141, 163));
        labelTaxonomia.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelTaxonomia.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelTipoMtto.setText("Tipo Mtto");
        labelTipoMtto.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoTipoMttoTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoTipoMttoTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelClaseMtto.setText("Clase Orden");
        labelClaseMtto.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoClaseOrdenTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoClaseOrdenTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelCriticidad.setText("Criticidad");
        labelCriticidad.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoCriticidadTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoCriticidadTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelSistema.setText("Sistema");
        labelSistema.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoSistemasTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoSistemasTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelComponente.setText("Componente");
        labelComponente.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoComponenteTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoComponenteTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelSintoma.setText("Síntoma");
        labelSintoma.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoSintomaTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoSintomaTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        descripcionTipoMttoTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionTipoMttoTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        btnTipoMtto.setBackground(new java.awt.Color(226, 210, 144));
        btnTipoMtto.setText("...");

        descripcionClaseOrdenTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionClaseOrdenTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        descripcionCriticidadTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionCriticidadTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        btnClaseMtto.setBackground(new java.awt.Color(226, 210, 144));
        btnClaseMtto.setText("...");

        btnCriticidadMtto.setBackground(new java.awt.Color(226, 210, 144));
        btnCriticidadMtto.setText("...");

        descripcionSistemasTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionSistemasTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        descripcionComponenteTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionComponenteTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        descripcionSintomaTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionSintomaTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        btnSistema.setBackground(new java.awt.Color(226, 210, 144));
        btnSistema.setText("...");

        btnComponente.setBackground(new java.awt.Color(226, 210, 144));
        btnComponente.setText("...");

        btnSintoma.setBackground(new java.awt.Color(226, 210, 144));
        btnSintoma.setText("...");

        javax.swing.GroupLayout generalDataLayout = new javax.swing.GroupLayout(generalData);
        generalData.setLayout(generalDataLayout);
        generalDataLayout.setHorizontalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addComponent(labelTaxonomia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(734, 734, 734))
            .addGroup(generalDataLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCriticidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelClaseMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelTipoMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codigoTipoMttoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(codigoCriticidadTB, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(codigoClaseOrdenTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(descripcionTipoMttoTB, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(descripcionClaseOrdenTB, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripcionCriticidadTB))
                .addGap(0, 0, 0)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTipoMtto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClaseMtto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCriticidadMtto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codigoSintomaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoComponenteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoSistemasTB, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addComponent(descripcionSintomaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addComponent(descripcionComponenteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addComponent(descripcionSistemasTB, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        generalDataLayout.setVerticalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addComponent(labelTaxonomia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelTipoMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoTipoMttoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoSistemasTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionTipoMttoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTipoMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionSistemasTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelClaseMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoClaseOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoComponenteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionClaseOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClaseMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionComponenteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCriticidad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoCriticidadTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoSintomaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionCriticidadTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCriticidadMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionSintomaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        provisioning.setBackground(new java.awt.Color(202, 219, 236));
        provisioning.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));
        provisioning.setForeground(new java.awt.Color(221, 221, 221));

        labelCronologia.setText("Cronología");
        labelCronologia.setColorBordes(new java.awt.Color(117, 141, 163));
        labelCronologia.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelCronologia.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelFechaInicio.setText("Fecha inicio");
        labelFechaInicio.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        fechaInicioReal.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelFechaFin.setText("Fecha fin");
        labelFechaFin.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        fechaFinReal.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        fechaFinProgramado.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelFechaProgramada.setText("Fecha programada");
        labelFechaProgramada.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        fechaInicioProgramado.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelFechaCierre.setText("Fecha cierre");
        labelFechaCierre.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        fechaCreacionOrden.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelFechaAutorizada.setText("Fecha autorizada");
        labelFechaAutorizada.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        fechaAutorizacionOrden.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelFechaCreacionOrden.setText("Fecha Cr. Orden");
        labelFechaCreacionOrden.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelHoraInicio.setText("Hora inicio");
        labelHoraInicio.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelHoraFin.setText("Hora fin");
        labelHoraFin.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelHoraCierre.setText("Hora cierre");
        labelHoraCierre.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelHoraProgramada.setText("Hora programada");
        labelHoraProgramada.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelHoraCreacion.setText("Hora creacion");
        labelHoraCreacion.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelHoraAutorizada.setText("Hora autorizada");
        labelHoraAutorizada.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelDurInicioFin.setText("Total");
        labelDurInicioFin.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelTotalProg.setText("Total");
        labelTotalProg.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelDurCreacion.setText("Total");
        labelDurCreacion.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        totalHoraReales.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        totalHoraReales.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalHoraReales.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        totalHorasProgramado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        totalHorasProgramado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalHorasProgramado.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        totalHorasCreacionOrden.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        totalHorasCreacionOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalHorasCreacionOrden.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        btnHoraInicioReal.setBackground(new java.awt.Color(226, 210, 144));
        btnHoraInicioReal.setText("...");

        btnHoraFinReal.setBackground(new java.awt.Color(226, 210, 144));
        btnHoraFinReal.setText("...");

        btnHoraCierreProg.setBackground(new java.awt.Color(226, 210, 144));
        btnHoraCierreProg.setText("...");

        btnHoraProgramada.setBackground(new java.awt.Color(226, 210, 144));
        btnHoraProgramada.setText("...");

        btnHoraCreacion.setBackground(new java.awt.Color(226, 210, 144));
        btnHoraCreacion.setText("...");

        btnHoraAutorizada.setBackground(new java.awt.Color(226, 210, 144));
        btnHoraAutorizada.setText("...");

        horaInicioReal.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        horaInicioReal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        horaFinReal.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        horaFinReal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        horaInicioProgramada.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        horaInicioProgramada.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        horaFinProgramado.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        horaFinProgramado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        horaCreacionOrden.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        horaCreacionOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        horaAutorizacionOrden.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        horaAutorizacionOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout provisioningLayout = new javax.swing.GroupLayout(provisioning);
        provisioning.setLayout(provisioningLayout);
        provisioningLayout.setHorizontalGroup(
            provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, provisioningLayout.createSequentialGroup()
                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(provisioningLayout.createSequentialGroup()
                        .addComponent(labelCronologia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(692, 692, 692))
                    .addGroup(provisioningLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(provisioningLayout.createSequentialGroup()
                                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(provisioningLayout.createSequentialGroup()
                                        .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fechaInicioReal, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                            .addComponent(fechaFinReal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(provisioningLayout.createSequentialGroup()
                                        .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(horaInicioReal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(horaFinReal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnHoraInicioReal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnHoraFinReal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(totalHoraReales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(provisioningLayout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelFechaCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelFechaProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fechaInicioProgramado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(fechaFinProgramado, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(provisioningLayout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(provisioningLayout.createSequentialGroup()
                                                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelHoraProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(labelHoraCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, 0)
                                                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(horaFinProgramado, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(horaInicioProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnHoraProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnHoraCierreProg, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(provisioningLayout.createSequentialGroup()
                                                .addComponent(labelTotalProg, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(totalHorasProgramado, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(labelDurInicioFin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(provisioningLayout.createSequentialGroup()
                                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelFechaCreacionOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelFechaAutorizada, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fechaCreacionOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fechaAutorizacionOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(provisioningLayout.createSequentialGroup()
                                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelHoraAutorizada, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelHoraCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(horaCreacionOrden)
                                    .addComponent(horaAutorizacionOrden, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnHoraCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHoraAutorizada, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(provisioningLayout.createSequentialGroup()
                                .addComponent(labelDurCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(totalHorasCreacionOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        provisioningLayout.setVerticalGroup(
            provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(provisioningLayout.createSequentialGroup()
                .addComponent(labelCronologia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaInicioReal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFechaProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaInicioProgramado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFechaCreacionOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaCreacionOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaFinReal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFechaCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaFinProgramado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFechaAutorizada, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaAutorizacionOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoraProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoraCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHoraInicioReal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHoraProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHoraCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaInicioReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaInicioProgramada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaCreacionOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoraCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoraAutorizada, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHoraFinReal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHoraCierreProg, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHoraAutorizada, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaFinReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaFinProgramado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaAutorizacionOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(provisioningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelDurInicioFin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotalProg, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDurCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalHoraReales, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalHorasProgramado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalHorasCreacionOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        manufacturingData.setBackground(new java.awt.Color(202, 219, 236));
        manufacturingData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));
        manufacturingData.setForeground(new java.awt.Color(221, 221, 221));

        labelObjetosTecnicos.setText("Objetos técnicos");
        labelObjetosTecnicos.setColorBordes(new java.awt.Color(117, 141, 163));
        labelObjetosTecnicos.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelObjetosTecnicos.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelEquipo.setText("Equipo");
        labelEquipo.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoEquipoTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoEquipoTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnEquipo.setBackground(new java.awt.Color(226, 210, 144));
        btnEquipo.setText("...");

        descripcionEquipoLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionEquipoLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionEquipoLB.setText(" ");
        descripcionEquipoLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelUbicacion.setText("Location");
        labelUbicacion.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoUbicacionTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoUbicacionTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnUbicacion.setBackground(new java.awt.Color(226, 210, 144));
        btnUbicacion.setText("...");

        descripcionUbicacionLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionUbicacionLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionUbicacionLB.setText(" ");
        descripcionUbicacionLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout manufacturingDataLayout = new javax.swing.GroupLayout(manufacturingData);
        manufacturingData.setLayout(manufacturingDataLayout);
        manufacturingDataLayout.setHorizontalGroup(
            manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingDataLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codigoUbicacionTB)
                    .addComponent(codigoEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descripcionEquipoLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descripcionUbicacionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
            .addGroup(manufacturingDataLayout.createSequentialGroup()
                .addComponent(labelObjetosTecnicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(746, 746, 746))
        );
        manufacturingDataLayout.setVerticalGroup(
            manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingDataLayout.createSequentialGroup()
                .addComponent(labelObjetosTecnicos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionEquipoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(manufacturingDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoUbicacionTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionUbicacionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        manufacturingData5.setBackground(new java.awt.Color(202, 219, 236));
        manufacturingData5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));
        manufacturingData5.setForeground(new java.awt.Color(221, 221, 221));

        labelGestionOrden.setText("Gestión de trabajo");
        labelGestionOrden.setColorBordes(new java.awt.Color(117, 141, 163));
        labelGestionOrden.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelGestionOrden.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelSolicitante.setText("Solicitante");
        labelSolicitante.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoSolicitanteTB.setEditable(false);
        codigoSolicitanteTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoSolicitanteTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnSolicitante.setBackground(new java.awt.Color(226, 210, 144));
        btnSolicitante.setText("...");

        descripcionSolicitanteLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionSolicitanteLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionSolicitanteLB.setText(" ");
        descripcionSolicitanteLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelResponsable.setText("Responsable");
        labelResponsable.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoResponsableTB.setEditable(false);
        codigoResponsableTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoResponsableTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnResponsable.setBackground(new java.awt.Color(226, 210, 144));
        btnResponsable.setText("...");

        descripcionResponsableLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionResponsableLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionResponsableLB.setText(" ");
        descripcionResponsableLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelGrupoPlanif.setText("Grupo planif.");
        labelGrupoPlanif.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoGrupoPlanTB.setEditable(false);
        codigoGrupoPlanTB.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        codigoGrupoPlanTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        codigoGrupoPlanTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        descripcionGrupoPlanif.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionGrupoPlanif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionGrupoPlanif.setText(" ");
        descripcionGrupoPlanif.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout manufacturingData5Layout = new javax.swing.GroupLayout(manufacturingData5);
        manufacturingData5.setLayout(manufacturingData5Layout);
        manufacturingData5Layout.setHorizontalGroup(
            manufacturingData5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingData5Layout.createSequentialGroup()
                .addGroup(manufacturingData5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(manufacturingData5Layout.createSequentialGroup()
                        .addComponent(labelGestionOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(653, 653, 653))
                    .addGroup(manufacturingData5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(manufacturingData5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelGrupoPlanif, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(manufacturingData5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(manufacturingData5Layout.createSequentialGroup()
                                .addComponent(codigoGrupoPlanTB, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(descripcionGrupoPlanif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(manufacturingData5Layout.createSequentialGroup()
                                .addComponent(codigoResponsableTB, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(descripcionResponsableLB, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(manufacturingData5Layout.createSequentialGroup()
                                .addComponent(codigoSolicitanteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(descripcionSolicitanteLB, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(93, 93, 93))
        );
        manufacturingData5Layout.setVerticalGroup(
            manufacturingData5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingData5Layout.createSequentialGroup()
                .addComponent(labelGestionOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(manufacturingData5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelGrupoPlanif, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoGrupoPlanTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionGrupoPlanif, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(manufacturingData5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoSolicitanteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionSolicitanteLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(manufacturingData5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoResponsableTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionResponsableLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout generalLayout = new javax.swing.GroupLayout(general);
        general.setLayout(generalLayout);
        generalLayout.setHorizontalGroup(
            generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generalData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manufacturingData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(provisioning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manufacturingData5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(463, 463, 463))
        );
        generalLayout.setVerticalGroup(
            generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manufacturingData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(provisioning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manufacturingData5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MULTITAB.addTab("Cabecera", general);

        operaciones.setBackground(new java.awt.Color(238, 244, 254));
        operaciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAgregarLineaOperacion.setBackground(new java.awt.Color(226, 210, 144));
        btnAgregarLineaOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAgregarLineaOperacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/agregar.png"))); // NOI18N

        btnEditarLineaOperacion.setBackground(new java.awt.Color(226, 210, 144));
        btnEditarLineaOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEditarLineaOperacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/main_class/icons/modificar.png"))); // NOI18N

        btnEliminarLineaOperacion.setBackground(new java.awt.Color(226, 210, 144));
        btnEliminarLineaOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminarLineaOperacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/main_class/icons/borrar.png"))); // NOI18N

        btnOkOperacion.setBackground(new java.awt.Color(226, 210, 144));
        btnOkOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnOkOperacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/chekOk.png"))); // NOI18N

        tablaOperaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaOperaciones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaOperaciones.setCellSelectionEnabled(true);
        tablaOperaciones.setColorOnEdit(new java.awt.Color(255, 255, 255));
        tablaOperaciones.setColorTextOnEdit(new java.awt.Color(51, 0, 204));
        tablaOperaciones.setColorUnselectText(new java.awt.Color(51, 51, 51));
        tablaOperaciones.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        tablaOperaciones.setGridColor(new java.awt.Color(51, 51, 51));
        tablaOperaciones.setRowHeight(22);
        tablaOperaciones.setShowGrid(true);
        jScrollPane5.setViewportView(tablaOperaciones);

        javax.swing.GroupLayout operacionesLayout = new javax.swing.GroupLayout(operaciones);
        operaciones.setLayout(operacionesLayout);
        operacionesLayout.setHorizontalGroup(
            operacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(operacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(operacionesLayout.createSequentialGroup()
                        .addComponent(btnAgregarLineaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnOkOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnEditarLineaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnEliminarLineaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1274, Short.MAX_VALUE))
                .addContainerGap())
        );
        operacionesLayout.setVerticalGroup(
            operacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(operacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarLineaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarLineaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarLineaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOkOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Operaciones", operaciones);

        materiales.setBackground(new java.awt.Color(238, 244, 254));
        materiales.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAgregarLineaMaterial.setBackground(new java.awt.Color(226, 210, 144));
        btnAgregarLineaMaterial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAgregarLineaMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/agregar.png"))); // NOI18N

        btnEditarLineaMaterial.setBackground(new java.awt.Color(226, 210, 144));
        btnEditarLineaMaterial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEditarLineaMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/main_class/icons/modificar.png"))); // NOI18N

        btnEliminarLineaMaterial.setBackground(new java.awt.Color(226, 210, 144));
        btnEliminarLineaMaterial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminarLineaMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/main_class/icons/borrar.png"))); // NOI18N

        tablaMateriales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaMateriales.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaMateriales.setCellSelectionEnabled(true);
        tablaMateriales.setColorOnEdit(new java.awt.Color(255, 255, 255));
        tablaMateriales.setColorTextOnEdit(new java.awt.Color(51, 0, 204));
        tablaMateriales.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        tablaMateriales.setRowHeight(22);
        jScrollPane2.setViewportView(tablaMateriales);

        btnOkMaterial.setBackground(new java.awt.Color(226, 210, 144));
        btnOkMaterial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnOkMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/chekOk.png"))); // NOI18N

        javax.swing.GroupLayout materialesLayout = new javax.swing.GroupLayout(materiales);
        materiales.setLayout(materialesLayout);
        materialesLayout.setHorizontalGroup(
            materialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(materialesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(materialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(materialesLayout.createSequentialGroup()
                        .addComponent(btnAgregarLineaMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnOkMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnEditarLineaMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnEliminarLineaMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1136, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        materialesLayout.setVerticalGroup(
            materialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(materialesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(materialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarLineaMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarLineaMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarLineaMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOkMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Materiales", materiales);

        costos.setBackground(new java.awt.Color(238, 244, 254));
        costos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        manufacturingData1.setBackground(new java.awt.Color(202, 219, 236));
        manufacturingData1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));
        manufacturingData1.setForeground(new java.awt.Color(221, 221, 221));

        labelTiempoEstimado.setText("Tiempo Estimado (Hrs)");

        labelHorasEstimadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelTiempoReal.setText("Tiempo Real (Hrs)");

        labelHorasReal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelCostosEstimados.setText("Costo Estimado ($)");

        labelCostoEstimado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelCostosReales.setText("Costo Real ($)");

        labelCostoReal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelCreadaPor.setText("Orden Creada......Por");

        labelPlaneadaPor.setText("Orden Planeada...Por");

        labelAprobadaPor.setText("Orden Aprobada...Por");

        labelEjecutadaPor.setText("Orden Ejecutada..Por");

        labelUsuarioCreador.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelUsuarioPlaneador.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelUsuarioAprobador.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelUsuarioEjecutor.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelTotales.setText("Totales");
        labelTotales.setColor1(new java.awt.Color(202, 216, 237));
        labelTotales.setColor2(new java.awt.Color(123, 160, 193));
        labelTotales.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelAprobaciones.setText("Aprobaciones");
        labelAprobaciones.setColor1(new java.awt.Color(202, 216, 237));
        labelAprobaciones.setColor2(new java.awt.Color(123, 160, 193));
        labelAprobaciones.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout manufacturingData1Layout = new javax.swing.GroupLayout(manufacturingData1);
        manufacturingData1.setLayout(manufacturingData1Layout);
        manufacturingData1Layout.setHorizontalGroup(
            manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manufacturingData1Layout.createSequentialGroup()
                .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(manufacturingData1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEjecutadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAprobadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPlaneadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCreadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUsuarioCreador, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUsuarioPlaneador, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUsuarioAprobador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelUsuarioEjecutor, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelAprobaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(manufacturingData1Layout.createSequentialGroup()
                        .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTiempoReal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCostosEstimados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCostosReales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelCostoEstimado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelHorasReal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelHorasEstimadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelCostoReal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addComponent(labelTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        manufacturingData1Layout.setVerticalGroup(
            manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingData1Layout.createSequentialGroup()
                .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAprobaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCreadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuarioCreador, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHorasEstimadas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTiempoReal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHorasReal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPlaneadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuarioPlaneador, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCostosEstimados, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCostoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAprobadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuarioAprobador, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(manufacturingData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCostosReales, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCostoReal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEjecutadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuarioEjecutor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        tablaResumenCostos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaResumenCostos.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        tablaResumenCostos.setGridColor(new java.awt.Color(153, 153, 153));
        tablaResumenCostos.setShowGrid(true);
        tablaResumenCostos.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(tablaResumenCostos);

        manufacturingData4.setBackground(new java.awt.Color(202, 219, 236));
        manufacturingData4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));
        manufacturingData4.setForeground(new java.awt.Color(221, 221, 221));

        labelOrganizacion.setText("Organizacion");
        labelOrganizacion.setColorBordes(new java.awt.Color(117, 141, 163));
        labelOrganizacion.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelOrganizacion.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelCentroCostos.setText("Cost Center");
        labelCentroCostos.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelArea.setText("Area");
        labelArea.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        areaTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        areaTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        centroCostosTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        centroCostosTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        descripcionCentroCosto.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionCentroCosto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionCentroCosto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        descripcionArea.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionArea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionArea.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelSociedad.setText("Sociedad");
        labelSociedad.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        sociedadTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        sociedadTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        descripcionSociedad.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionSociedad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionSociedad.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelEmplazamiento.setText("Emplazamiento");
        labelEmplazamiento.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        emplazamientoTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        emplazamientoTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        descripcionEmplazamientro.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionEmplazamientro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionEmplazamientro.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout manufacturingData4Layout = new javax.swing.GroupLayout(manufacturingData4);
        manufacturingData4.setLayout(manufacturingData4Layout);
        manufacturingData4Layout.setHorizontalGroup(
            manufacturingData4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingData4Layout.createSequentialGroup()
                .addGroup(manufacturingData4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(manufacturingData4Layout.createSequentialGroup()
                        .addComponent(labelOrganizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(682, 682, 682))
                    .addGroup(manufacturingData4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(manufacturingData4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelArea, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(manufacturingData4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sociedadTB, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(centroCostosTB, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emplazamientoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(areaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(manufacturingData4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descripcionSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionCentroCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionEmplazamientro, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionArea, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64))
        );
        manufacturingData4Layout.setVerticalGroup(
            manufacturingData4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingData4Layout.createSequentialGroup()
                .addComponent(labelOrganizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(manufacturingData4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sociedadTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(manufacturingData4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(areaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(manufacturingData4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelEmplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emplazamientoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionEmplazamientro, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(manufacturingData4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(centroCostosTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionCentroCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout costosLayout = new javax.swing.GroupLayout(costos);
        costos.setLayout(costosLayout);
        costosLayout.setHorizontalGroup(
            costosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(costosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(manufacturingData4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manufacturingData1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(384, Short.MAX_VALUE))
        );
        costosLayout.setVerticalGroup(
            costosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manufacturingData4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manufacturingData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MULTITAB.addTab("Datos financieros", costos);

        costos1.setBackground(new java.awt.Color(238, 244, 254));
        costos1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        manufacturingData3.setBackground(new java.awt.Color(202, 219, 236));
        manufacturingData3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));
        manufacturingData3.setForeground(new java.awt.Color(221, 221, 221));

        labelControl.setText("Control de planificacion");
        labelControl.setColorBordes(new java.awt.Color(117, 141, 163));
        labelControl.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelControl.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        labelValorContador.setText("Valor contador");
        labelValorContador.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        valorContadorTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelContador.setText("Contador");
        labelContador.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        contadorTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelHojaRuta.setText("Hoja de rutina");
        labelHojaRuta.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        hojaRutaTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelPlan.setText("Plan");
        labelPlan.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        planTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout manufacturingData3Layout = new javax.swing.GroupLayout(manufacturingData3);
        manufacturingData3.setLayout(manufacturingData3Layout);
        manufacturingData3Layout.setHorizontalGroup(
            manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingData3Layout.createSequentialGroup()
                .addGroup(manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(manufacturingData3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelValorContador, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelContador, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valorContadorTB, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contadorTB, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelHojaRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(planTB, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hojaRutaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(275, Short.MAX_VALUE))
        );
        manufacturingData3Layout.setVerticalGroup(
            manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manufacturingData3Layout.createSequentialGroup()
                .addComponent(labelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(manufacturingData3Layout.createSequentialGroup()
                        .addGroup(manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelContador, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contadorTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelValorContador, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valorContadorTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(manufacturingData3Layout.createSequentialGroup()
                        .addGroup(manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelHojaRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hojaRutaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(manufacturingData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(planTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout costos1Layout = new javax.swing.GroupLayout(costos1);
        costos1.setLayout(costos1Layout);
        costos1Layout.setHorizontalGroup(
            costos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manufacturingData3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(387, Short.MAX_VALUE))
        );
        costos1Layout.setVerticalGroup(
            costos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manufacturingData3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(410, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Control", costos1);

        paradaCB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        paradaCB.setText("Paro");
        paradaCB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        panelDescripcionExt.setBackground(new java.awt.Color(202, 219, 236));
        panelDescripcionExt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        descripcionExtendidaOrdenTB.setColumns(20);
        descripcionExtendidaOrdenTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionExtendidaOrdenTB.setLineWrap(true);
        descripcionExtendidaOrdenTB.setRows(5);
        descripcionExtendidaOrdenTB.setWrapStyleWord(true);
        scroll.setViewportView(descripcionExtendidaOrdenTB);

        javax.swing.GroupLayout panelDescripcionExtLayout = new javax.swing.GroupLayout(panelDescripcionExt);
        panelDescripcionExt.setLayout(panelDescripcionExtLayout);
        panelDescripcionExtLayout.setHorizontalGroup(
            panelDescripcionExtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
        );
        panelDescripcionExtLayout.setVerticalGroup(
            panelDescripcionExtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );

        btnDescripcionExtendida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/show_details.png"))); // NOI18N
        btnDescripcionExtendida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescripcionExtendidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addComponent(numeroOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(estatusLB, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(codigoEstatusTB, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descripcionEstatusTB))
                            .addComponent(tituloOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panelDescripcionExt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(paradaCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDescripcionExtendida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(467, Short.MAX_VALUE))
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estatusLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoEstatusTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionEstatusTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paradaCB))
                .addGap(2, 2, 2)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDescripcionExtendida, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDescripcionExt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(MULTITAB)
                .addContainerGap())
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir_CrearOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir_CrearOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir_CrearOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        btnGuardar_Orden.setBackground(new java.awt.Color(226, 210, 144));
        btnGuardar_Orden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGuardar_Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N

        btn_OIPNN.setBackground(new java.awt.Color(226, 210, 144));
        btn_OIPNN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_OIPNN.setText("OIPNN");
        btn_OIPNN.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N

        btn_OUAPP.setBackground(new java.awt.Color(226, 210, 144));
        btn_OUAPP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_OUAPP.setText("OUAPP");
        btn_OUAPP.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N

        btn_OAPPV.setBackground(new java.awt.Color(226, 210, 144));
        btn_OAPPV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_OAPPV.setText("OAPPV");
        btn_OAPPV.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N

        btn_OSCHD.setBackground(new java.awt.Color(226, 210, 144));
        btn_OSCHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_OSCHD.setText("OSCHD");
        btn_OSCHD.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N

        btn_OIEXN.setBackground(new java.awt.Color(226, 210, 144));
        btn_OIEXN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_OIEXN.setText("OIEXN");
        btn_OIEXN.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N

        btn_OEXTD.setBackground(new java.awt.Color(226, 210, 144));
        btn_OEXTD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_OEXTD.setText("OEXTD");
        btn_OEXTD.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N

        btn_OCLSD.setBackground(new java.awt.Color(226, 210, 144));
        btn_OCLSD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_OCLSD.setText("OCLSD");
        btn_OCLSD.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N

        btn_ORJTD.setBackground(new java.awt.Color(226, 210, 144));
        btn_ORJTD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_ORJTD.setText("ORJTD");
        btn_ORJTD.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N

        btn_OCCLD.setBackground(new java.awt.Color(226, 210, 144));
        btn_OCCLD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_OCCLD.setText("OCCLD");
        btn_OCCLD.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N

        btnNueva_Orden.setBackground(new java.awt.Color(226, 210, 144));
        btnNueva_Orden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNueva_Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/new_empty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnGuardar_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnNueva_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                    .addGap(315, 315, 315)
                    .addComponent(btn_OIPNN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(btn_OUAPP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(btn_OAPPV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(btn_OSCHD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(btn_OIEXN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(btn_OEXTD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(47, 47, 47)
                    .addComponent(btn_OCLSD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(57, 57, 57)
                    .addComponent(btn_ORJTD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(btn_OCCLD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(316, Short.MAX_VALUE)))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNueva_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(btn_OIPNN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_OUAPP, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_OAPPV, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_OSCHD, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_OIEXN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_OEXTD, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_OCLSD, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_ORJTD, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_OCCLD, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(9, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
            .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDescripcionExtendidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescripcionExtendidaActionPerformed
        if (panelDescripcionExt.isVisible()) {
            panelDescripcionExt.setVisible(false);
        } else {
            panelDescripcionExt.setVisible(true);
        }
    }//GEN-LAST:event_btnDescripcionExtendidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTabbedPane MULTITAB;
    protected static javax.swing.JTextField areaTB;
    private javax.swing.JPanel bodyPanel;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnAgregarLineaMaterial;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnAgregarLineaOperacion;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnClaseMtto;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnComponente;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnCriticidadMtto;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnDescripcionExtendida;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEditarLineaMaterial;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEditarLineaOperacion;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEliminarLineaMaterial;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEliminarLineaOperacion;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEquipo;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnGuardar_Orden;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnHoraAutorizada;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnHoraCierreProg;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnHoraCreacion;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnHoraFinReal;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnHoraInicioReal;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnHoraProgramada;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnNueva_Orden;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnOkMaterial;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnOkOperacion;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnResponsable;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir_CrearOrden;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnSintoma;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnSistema;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnSolicitante;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnTipoMtto;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnUbicacion;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btn_OAPPV;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btn_OCCLD;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btn_OCLSD;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btn_OEXTD;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btn_OIEXN;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btn_OIPNN;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btn_ORJTD;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btn_OSCHD;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btn_OUAPP;
    protected static javax.swing.JTextField centroCostosTB;
    protected static javax.swing.JTextField codigoClaseOrdenTB;
    protected static javax.swing.JTextField codigoComponenteTB;
    protected static javax.swing.JTextField codigoCriticidadTB;
    protected static javax.swing.JTextField codigoEquipoTB;
    private javax.swing.JTextField codigoEstatusTB;
    protected static javax.swing.JFormattedTextField codigoGrupoPlanTB;
    protected static javax.swing.JTextField codigoResponsableTB;
    protected static javax.swing.JTextField codigoSintomaTB;
    protected static javax.swing.JTextField codigoSistemasTB;
    private javax.swing.JTextField codigoSolicitanteTB;
    protected static javax.swing.JTextField codigoTipoMttoTB;
    protected static javax.swing.JTextField codigoUbicacionTB;
    private javax.swing.JTextField contadorTB;
    private javax.swing.JPanel costos;
    private javax.swing.JPanel costos1;
    public static javax.swing.JLabel descripcionArea;
    public static javax.swing.JLabel descripcionCentroCosto;
    protected static javax.swing.JTextField descripcionClaseOrdenTB;
    protected static javax.swing.JTextField descripcionComponenteTB;
    protected static javax.swing.JTextField descripcionCriticidadTB;
    public static javax.swing.JLabel descripcionEmplazamientro;
    public static javax.swing.JLabel descripcionEquipoLB;
    private javax.swing.JTextField descripcionEstatusTB;
    private javax.swing.JTextArea descripcionExtendidaOrdenTB;
    protected static javax.swing.JLabel descripcionGrupoPlanif;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined descripcionOrdenLB;
    public static javax.swing.JLabel descripcionResponsableLB;
    protected static javax.swing.JTextField descripcionSintomaTB;
    protected static javax.swing.JTextField descripcionSistemasTB;
    public static javax.swing.JLabel descripcionSociedad;
    private javax.swing.JLabel descripcionSolicitanteLB;
    protected static javax.swing.JTextField descripcionTipoMttoTB;
    public static javax.swing.JLabel descripcionUbicacionLB;
    protected static javax.swing.JTextField emplazamientoTB;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined estatusLB;
    private com.toedter.calendar.JDateChooser fechaAutorizacionOrden;
    private com.toedter.calendar.JDateChooser fechaCreacionOrden;
    private com.toedter.calendar.JDateChooser fechaFinProgramado;
    private com.toedter.calendar.JDateChooser fechaFinReal;
    private com.toedter.calendar.JDateChooser fechaInicioProgramado;
    private com.toedter.calendar.JDateChooser fechaInicioReal;
    private javax.swing.JPanel general;
    private javax.swing.JPanel generalData;
    private javax.swing.JTextField hojaRutaTB;
    private javax.swing.JTextField horaAutorizacionOrden;
    private javax.swing.JTextField horaCreacionOrden;
    private javax.swing.JTextField horaFinProgramado;
    private javax.swing.JTextField horaFinReal;
    private javax.swing.JTextField horaInicioProgramada;
    private javax.swing.JTextField horaInicioReal;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    public static com.simplecore.erp.gui.components.labels.JLabelHQLongFraming labelAprobaciones;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelAprobadaPor;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelArea;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCentroCostos;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelClaseMtto;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelComponente;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelContador;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelControl;
    protected static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCostoEstimado;
    protected static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCostoReal;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCostosEstimados;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCostosReales;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCreadaPor;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCriticidad;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelCronologia;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelDurCreacion;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelDurInicioFin;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelEjecutadaPor;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelEmplazamiento;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelEquipo;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelFechaAutorizada;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelFechaCierre;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelFechaCreacionOrden;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelFechaFin;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelFechaInicio;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelFechaProgramada;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelGestionOrden;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelGrupoPlanif;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelHojaRuta;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelHoraAutorizada;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelHoraCierre;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelHoraCreacion;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelHoraFin;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelHoraInicio;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelHoraProgramada;
    protected static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelHorasEstimadas;
    protected static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelHorasReal;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelObjetosTecnicos;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelOrganizacion;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelPlan;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelPlaneadaPor;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelResponsable;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSintoma;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSistema;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSociedad;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSolicitante;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelTaxonomia;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelTiempoEstimado;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelTiempoReal;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelTipoMtto;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelTotalProg;
    public static com.simplecore.erp.gui.components.labels.JLabelHQLongFraming labelTotales;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelUbicacion;
    public static com.simplecore.erp.gui.components.labels.JLabelHQ labelUsuarioAprobador;
    public static com.simplecore.erp.gui.components.labels.JLabelHQ labelUsuarioCreador;
    public static com.simplecore.erp.gui.components.labels.JLabelHQ labelUsuarioEjecutor;
    public static com.simplecore.erp.gui.components.labels.JLabelHQ labelUsuarioPlaneador;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelValorContador;
    private javax.swing.JPanel manufacturingData;
    private javax.swing.JPanel manufacturingData1;
    private javax.swing.JPanel manufacturingData3;
    private javax.swing.JPanel manufacturingData4;
    private javax.swing.JPanel manufacturingData5;
    private javax.swing.JPanel materiales;
    public static javax.swing.JMenuBar menuBar_O03;
    public static javax.swing.JMenu menuCabeceraOrden;
    public static javax.swing.JMenuItem menuClasesMantenimiento;
    public static javax.swing.JMenuItem menuComponentes;
    public static javax.swing.JMenuItem menuCostos;
    public static javax.swing.JMenu menuCostosOrden;
    public static javax.swing.JMenuItem menuEquipo;
    public static javax.swing.JMenu menuEstatus;
    public static javax.swing.JMenuItem menuEstatusAprobacion;
    public static javax.swing.JMenuItem menuEstatusAprobada;
    public static javax.swing.JMenuItem menuEstatusCancelada;
    public static javax.swing.JMenuItem menuEstatusCerrada;
    public static javax.swing.JMenuItem menuEstatusEjecucion;
    public static javax.swing.JMenuItem menuEstatusEjecutada;
    public static javax.swing.JMenuItem menuEstatusPlaneacion;
    public static javax.swing.JMenuItem menuEstatusProgramada;
    public static javax.swing.JMenuItem menuEstatusRechazado;
    public static javax.swing.JMenuItem menuGuardarOrden;
    public static javax.swing.JMenu menuListadoEstatus;
    public static javax.swing.JMenuItem menuMateriales;
    public static javax.swing.JMenuItem menuModificarOrden;
    public static javax.swing.JMenuItem menuNuevaOrden;
    public static javax.swing.JMenuItem menuOperaciones;
    public static javax.swing.JMenu menuOrdenTrabajo;
    public static javax.swing.JMenuItem menuPrioridades;
    public static javax.swing.JMenu menuRecursos;
    public static javax.swing.JMenuItem menuSalir;
    public static javax.swing.JMenuItem menuSintomas;
    public static javax.swing.JMenuItem menuSistemas;
    public static javax.swing.JMenuItem menuTipoMantenimiento;
    public static javax.swing.JMenuItem menuUbicaciones;
    public static javax.swing.JMenuItem menuVisualizarOrden;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined numeroOrdenLB;
    private javax.swing.JTextField numeroOrdenTB;
    private javax.swing.JPanel operaciones;
    private javax.swing.JPanel panelDescripcionExt;
    private javax.swing.JScrollPane panelScroll;
    public static javax.swing.JCheckBox paradaCB;
    private javax.swing.JTextField planTB;
    private javax.swing.JPanel provisioning;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JPopupMenu.Separator separador2;
    private javax.swing.JPopupMenu.Separator separador3;
    private javax.swing.JPopupMenu.Separator separador4;
    protected static javax.swing.JTextField sociedadTB;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaMateriales;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaOperaciones;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaResumenCostos;
    private javax.swing.JTextField tituloOrdenTB;
    private javax.swing.JFormattedTextField totalHoraReales;
    private javax.swing.JFormattedTextField totalHorasCreacionOrden;
    private javax.swing.JFormattedTextField totalHorasProgramado;
    private javax.swing.JTextField valorContadorTB;
    // End of variables declaration//GEN-END:variables

}
