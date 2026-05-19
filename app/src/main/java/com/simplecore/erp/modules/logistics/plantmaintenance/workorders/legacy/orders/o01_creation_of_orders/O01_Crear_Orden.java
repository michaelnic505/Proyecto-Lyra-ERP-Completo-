package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.StatusOrder;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Clase_Mtto;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Criticidad;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Equipos;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Ordenes_Trabajo;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Ubicaciones;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Verificar_Existencia_Orden_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.Check_Modification_Permission;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class O01_Crear_Orden extends javax.swing.JPanel {

    
    private JFrame frame;
    private JPanel thisPanel = this;
    private static boolean equipoCorrecto = false;
    private static boolean ubicacionCorrecto = false;
    
    private boolean modelOrder = false;
    private String titulo;
    
    private String username;
    
    public O01_Crear_Orden(String username) {

        this.username = username;
        initComponents();
        frame = (JFrame) SwingUtilities.getWindowAncestor(mainContainerPanel);
        addEvents();
      

    }   

    private void addEvents(){

        btnNueva_Orden();
        orderClass();
        criticality();
        
        equipoTextBoxKeyListener();
        equipoTextBoxButton();
        
        ubicacionTextBoxKeyListener();
        ubicacionTextBoxButton();
        
        ordenModelo();
        
        btnSalir();
        textboxNoEditables();
        buscarOrdenKeyListener();
        btnDeNuevo();
        
    }

    public void setTitle() {
        LyraWorkspace.TitleLabel.setText(titulo);
    }

    
    private void btnNueva_Orden() {

        btnNueva_Orden.addActionListener((e) -> {

            if (equipoCorrecto && !ubicacionCorrecto) {
                if (!camposLlenosEquipo()) {
                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                    return;
                }
                if (Check_Modification_Permission.isPermitted(username, "O01", StatusOrder.ST1_ORDER_CREATED.getStatusCode())) {
                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);
                    return;
                }
                abrirAmbienteCreacion();

            } else if (!equipoCorrecto && ubicacionCorrecto) {
                if (!camposLlenosUbicacion()) {
                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                    return;
                }
                if (!Check_Modification_Permission.isPermitted(username, "O01", StatusOrder.ST1_ORDER_CREATED.getStatusCode())) {
                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);
                    return;
                }
                abrirAmbienteCreacion();

            }
        });

    }
    private void btnDeNuevo(){
        btnDeNuevo.addActionListener((e)->{
            

                claseOrdenBox.getTextBox().setText(null);
                criticidadBox.getTextBox().setText(null);
                equipoBox.getTextBox().setText(null);
                ubicacionBox.getTextBox().setText(null);
                grupoPlanBox.setText(null);
                buscarOrden.getTextBox().setText(null);
                
                setNullValues();
                setNullValuesModelOrder();
                
                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.TRY_AGAIN), TypeMessage.INFORMATION);

            
        });
    }
    
    private boolean camposLlenosEquipo(){
        
        boolean itIsAllFilled = false;
        
        if(!(claseOrdenBox.getTextBox().getText().isEmpty()
                |criticidadBox.getTextBox().getText().isEmpty()
                |equipoBox.getTextBox().getText().isEmpty()
                |ubicacionBox.getTextBox().getText().isEmpty()
                |grupoPlanBox.getText().isEmpty())){
            
            itIsAllFilled = true;
        }else{
            itIsAllFilled = false;
        }
        
        return itIsAllFilled;
    }
    private boolean camposLlenosUbicacion() {

        boolean itIsAllFilled = false;

        if (!(claseOrdenBox.getTextBox().getText().isEmpty()
                | criticidadBox.getTextBox().getText().isEmpty()
                | !equipoBox.getTextBox().getText().isEmpty()
                | ubicacionBox.getTextBox().getText().isEmpty()
                | grupoPlanBox.getText().isEmpty())) {

            itIsAllFilled = true;
        } else {
            itIsAllFilled = false;
        }

        return itIsAllFilled;
    }

    private void abrirAmbienteCreacion(){
        
        O01_Creacion_Ordenes_Trabajo co = new O01_Creacion_Ordenes_Trabajo(username);
        co.setPanelAnterior(thisPanel);
        
        //establecer valores de campos
        co.codigoEquipoTB(equipo);
        co.descripcionEquipoLB(denominacion);
        co.codigoUbicacionTB(ubicacion);
        co.descripcionUbicacionLB(descripcionUbicacion);
        co.sociedadTB(sociedad);
        co.descripcionSociedad(denominacionSociedad);
        co.centroCostosTB(centroCostos);
        co.descripcionCentroCosto(denominacionCentroCostos);
        co.emplazamientoTB(emplazamiento);
        co.descripcionEmplazamientro(denominacionEmplazamiento);
        co.areaTB(area);
        co.descripcionArea(denominacionArea);
        co.codigoGrupoPlanTB(grupoPlanif);
        co.descripcionGrupoPlanif(descripcionGrupoPlanif);
        
        //poner codigo de clase orden
        co.setOrderClassDescription(claseOrdenBox.getTextBox().getText());
        
        //poner codigo de criticidad orden
        co.setOrderCriticalityDescription(criticidadBox.getTextBox().getText());
        
        //si tiene orden modelo
        if(modelOrder){
            
            co.paradaCB(parada);
            co.tituloOrdenTB(tituloOrden);
            co.descripcionExtendidaOrdenTB(textoExtendido);
            co.codigoTipoMttoTB(tipoOrden);
            co.descripcionTipoMttoTB(descripcionTipoOrden);
            co.codigoSistemasTB(codigoSistema);
            co.descripcionSistemasTB(descripcionSistema);
            co.codigoComponenteTB(codigoComponente);
            co.descripcionComponenteTB(descripcionComponente);
            co.codigoSintomaTB(codigoSintoma);
            co.descripcionSintomaTB(descripcionSintoma);
            co.codigoSolicitanteTB(solicitante);
            co.descripcionSolicitanteLB(nombreSolicitante);
            co.codigoResponsableTB(responsable);
            co.descripcionResponsableLB(nombreResponsable);

            co.cargarOperaciones(numeroOrdenModelo);
            co.cargarMateriales(numeroOrdenModelo);
        }
        
        PanelLoader.loadPanel(co, mainContainerPanel);
                
    }

    private static String equipo;
    private static String denominacion;
    private static String ubicacion;
    private static String descripcionUbicacion;
    private static String sociedad;
    private static String denominacionSociedad;
    private static String centroCostos;
    private static String denominacionCentroCostos;
    private static String emplazamiento;
    private static String denominacionEmplazamiento;
    private static String area;
    private static String denominacionArea;
    private static String grupoPlanif;
    private static String descripcionGrupoPlanif;
    
    private void setNullValues() {
        equipo = null;
        denominacion = null;
        ubicacion = null;
        descripcionUbicacion = null;
        sociedad = null;
        denominacionSociedad = null;
        centroCostos = null;
        denominacionCentroCostos = null;
        emplazamiento = null;
        denominacionEmplazamiento = null;
        area = null;
        denominacionArea = null;
        grupoPlanif = null;
        descripcionGrupoPlanif = null;
    }
    
    private void textboxNoEditables(){
        claseOrdenBox.getTextBox().setEditable(false);
        criticidadBox.getTextBox().setEditable(false);
        
    }
    private void orderClass(){
        claseOrdenBox.getButton().addActionListener((e)->{
            
            Lista_Clase_Mtto lcm = new Lista_Clase_Mtto(frame, true);
            lcm.setTitle(labelClaseMtto.getText());
            lcm.setLocationRelativeTo(claseOrdenBox);
            lcm.setCampos(claseOrdenBox.getTextBox(), null);
            lcm.setVisible(true);
            
        });
    }
    private void criticality(){
        criticidadBox.getButton().addActionListener((e)->{
            
            Lista_Criticidad lc = new Lista_Criticidad(frame,true);
            lc.setTitle(labelCriticidad.getText());
            lc.setLocationRelativeTo(criticidadBox);
            lc.setCampos(criticidadBox.getTextBox(), null);
            lc.setVisible(true);
            
        });
    }
    
    
    
    private static final JTextField codigoEquipoTB = new JTextField();
    private static final JLabel descripcionEquipoLB1 = new JLabel();
    private static final JTextField codigoUbicacionTB = new JTextField();
    private static final JLabel descripcionUbicacionLB1 = new JLabel();
    private static final JTextField sociedadTB = new JTextField();
    private static final JLabel descripcionSociedad = new JLabel();
    private static final JTextField areaTB = new JTextField();
    private static final JLabel descripcionArea = new JLabel();
    private static final JTextField emplazamientoTB = new JTextField();
    private static final JLabel descripcionEmplazamientro = new JLabel();
    private static final JTextField centroCostosTB = new JTextField();
    private static final JLabel descripcionCentroCosto = new JLabel();
    private static final JTextField codigoGrupoPlanTB = new JTextField();
    private static final JLabel descripcionGrupoPlani = new JLabel();

    
    private void equipoTextBoxKeyListener() {

        equipoBox.getTextBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarEquipo();
                }
            }

        });

    }
    private void equipoTextBoxButton() {
        equipoBox.getButton().addActionListener((e) -> {

            Lista_Equipos le = new Lista_Equipos();
            le.setPanelAnterior(thisPanel);
            le.setCampos(codigoEquipoTB, descripcionEquipoLB1,
                    codigoUbicacionTB, descripcionUbicacionLB1,
                    sociedadTB, descripcionSociedad,
                    areaTB, descripcionArea,
                    emplazamientoTB, descripcionEmplazamientro,
                    centroCostosTB, descripcionCentroCosto,
                    codigoGrupoPlanTB, descripcionGrupoPlani);
            le.setSearch(true);

            PanelLoader.loadPanel(le, mainContainerPanel);

        });
    }
    private void buscarEquipo() {

        equipoCorrecto = false;
        ubicacionCorrecto = false;
        setNullValues();

        if (!equipoBox.getTextBox().getText().isEmpty()) {

            if (Extraer_Equipo.equipmentExists(equipoBox.getTextBox().getText())) {

                Extraer_Equipo ee = new Extraer_Equipo();
                ee.getData(equipoBox.getTextBox().getText());
                equipo = ee.getEquipo();
                denominacion = ee.getDenominacion();
                ubicacion = ee.getUbicacion();
                descripcionUbicacion = ee.getDescripcionUbicacion();
                sociedad = ee.getSociedad();
                denominacionSociedad = ee.getDenominacionSociedad();
                centroCostos = ee.getCentroCostos();
                denominacionCentroCostos = ee.getDenominacionCentroCostos();
                emplazamiento = ee.getEmplazamiento();
                denominacionEmplazamiento = ee.getDenominacionEmplazamiento();
                area = ee.getArea();
                denominacionArea = ee.getDenominacionArea();
                grupoPlanif = ee.getGrupoPlanif();
                descripcionGrupoPlanif = ee.getDescripcionGrupoPlanif();

                descripcionEquipoLB.setText(denominacion);
                ubicacionBox.getTextBox().setText(ubicacion);
                descripcionUbicacionLB.setText(descripcionUbicacion);
                grupoPlanBox.setText(grupoPlanif);

                //equipo correcto
                equipoCorrecto = true;

            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.EQUIPMENT_DOES_NOT_EXIST), TypeMessage.ERROR);

                equipoBox.getTextBox().setText(null);
                descripcionEquipoLB.setText(null);
                ubicacionBox.getTextBox().setText(null);
                descripcionUbicacionLB.setText(null);
                grupoPlanBox.setText(null);

                equipoCorrecto = false;
            }

        }
    }

    
    private void ubicacionTextBoxKeyListener() {

        ubicacionBox.getTextBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarUbicacion();
                }
            }

        });

    }
    private void ubicacionTextBoxButton(){
        ubicacionBox.getButton().addActionListener((e)->{
            
            Lista_Ubicaciones lu  = new Lista_Ubicaciones();
            
            lu.setPanelAnterior(thisPanel);
            
            lu.setCodUbi(codigoUbicacionTB);
            lu.setDescUbi(descripcionUbicacionLB1);
            lu.setSocie(sociedadTB);
            lu.setDescSocie(descripcionSociedad);
            lu.setArea(areaTB);
            lu.setDescArea(descripcionArea);
            lu.setEmpl(emplazamientoTB);
            lu.setDescEmpl(descripcionEmplazamientro);
            lu.setCentroC(centroCostosTB);
            lu.setDescCC(descripcionCentroCosto);
            lu.setGrupoPlanif(codigoGrupoPlanTB);
            lu.setDescripcionGP(descripcionGrupoPlani);
            
            lu.setSearch(true);
            
            PanelLoader.loadPanel(lu, mainContainerPanel);
        });
    }
    private void buscarUbicacion() {

        equipoCorrecto = false;
        ubicacionCorrecto = false;
        setNullValues();
 
        if (!ubicacionBox.getTextBox().getText().isEmpty()) {

            if (Extraer_Ubicacion.locationExists(ubicacionBox.getTextBox().getText())) {

                int l = Extraer_Ubicacion.locationLevel(ubicacionBox.getTextBox().getText());

                if (l >= 4) {
                    Extraer_Ubicacion eu = new Extraer_Ubicacion();
                    eu.getData(ubicacionBox.getTextBox().getText());

                    ubicacion = eu.getUbicacion();
                    descripcionUbicacion = eu.getDescripcionUbicacion();
                    sociedad = eu.getSociedad();
                    denominacionSociedad = eu.getDenominacionSociedad();
                    centroCostos = eu.getCentroCostos();
                    denominacionCentroCostos = eu.getDenominacionCentroCostos();
                    emplazamiento = eu.getEmplazamiento();
                    denominacionEmplazamiento = eu.getDenominacionEmplazamiento();
                    area = eu.getArea();
                    denominacionArea = eu.getDenominacionArea();
                    grupoPlanif = eu.getGrupoPlanif();
                    descripcionGrupoPlanif = eu.getDescripcionGrupoPlanif();

                    ubicacionBox.getTextBox().setText(ubicacion);
                    descripcionUbicacionLB.setText(descripcionUbicacion);
                    grupoPlanBox.setText(grupoPlanif);

                    ubicacionCorrecto = true;
                } else {

                    new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.MOUNTING_NOT_ALLOWED), TypeMessage.ERROR);

                }


            } else {

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.LOCATION_DOES_NOT_EXIST), TypeMessage.ERROR);

                equipoBox.getTextBox().setText(null);
                descripcionEquipoLB.setText(null);
                ubicacionBox.getTextBox().setText(null);
                descripcionUbicacionLB.setText(null);
                grupoPlanBox.setText(null);

                ubicacionCorrecto = false;
            }

        }
    }

    
    public static void pasarDatosEquipo() {

        equipo = codigoEquipoTB.getText();
        denominacion = descripcionEquipoLB1.getText();
        ubicacion = codigoUbicacionTB.getText();
        descripcionUbicacion = descripcionUbicacionLB1.getText();
        sociedad = sociedadTB.getText();
        denominacionSociedad = descripcionSociedad.getText();
        centroCostos =  centroCostosTB.getText();
        denominacionCentroCostos = descripcionCentroCosto.getText();
        emplazamiento = emplazamientoTB.getText();
        denominacionEmplazamiento = descripcionEmplazamientro.getText();
        area =areaTB.getText();        
        denominacionArea = descripcionArea.getText();
        grupoPlanif = codigoGrupoPlanTB.getText();
        descripcionGrupoPlanif = descripcionGrupoPlani.getText();

        equipoBox.getTextBox().setText(equipo);
        descripcionEquipoLB.setText(denominacion);
        ubicacionBox.getTextBox().setText(ubicacion);
        descripcionUbicacionLB.setText(descripcionUbicacion);
        grupoPlanBox.setText(grupoPlanif);

        equipoCorrecto = true;

    }
    public static void pasarDatosUbicacion() {

        ubicacion = codigoUbicacionTB.getText();
        descripcionUbicacion = descripcionUbicacionLB1.getText();
        sociedad = sociedadTB.getText();
        denominacionSociedad = descripcionSociedad.getText();
        centroCostos = areaTB.getText();
        denominacionCentroCostos = descripcionArea.getText();
        emplazamiento = emplazamientoTB.getText();
        denominacionEmplazamiento = descripcionEmplazamientro.getText();
        area = centroCostosTB.getText();
        denominacionArea = descripcionCentroCosto.getText();
        grupoPlanif = codigoGrupoPlanTB.getText();
        descripcionGrupoPlanif = descripcionGrupoPlani.getText();

        equipoBox.getTextBox().setText(equipo);
        descripcionEquipoLB.setText(denominacion);
        ubicacionBox.getTextBox().setText(ubicacion);
        descripcionUbicacionLB.setText(descripcionUbicacion);
        grupoPlanBox.setText(grupoPlanif);

        ubicacionCorrecto = true;

        equipoBox.getTextBox().setText(null);
        descripcionEquipoLB.setText(null);
    }
    
    private boolean parada;
    private String tituloOrden;
    private String textoExtendido;
    private String tipoOrden;
    private String descripcionTipoOrden;
    private String codigoSistema;
    private String descripcionSistema;
    private String codigoComponente;
    private String descripcionComponente;
    private String codigoSintoma;
    private String descripcionSintoma;
    private String solicitante;
    private String nombreSolicitante;
    private String responsable;
    private String nombreResponsable;

    private void ordenModelo(){
        
        buscarOrden.getButton().addActionListener((e)->{
            
            Lista_Ordenes_Trabajo lo = new Lista_Ordenes_Trabajo();
            lo.setPanelAnterior(thisPanel);
            lo.setNumOrderTextBox(buscarOrden.getTextBox());
            
            PanelLoader.loadPanel(lo, mainContainerPanel);
            
        });
        
    }


    private String numeroOrdenModelo;
    
    private void buscarOrdenKeyListener() {
        buscarOrden.getTextBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (!buscarOrden.getTextBox().getText().isEmpty()) {
                   
                        numeroOrdenModelo = buscarOrden.getTextBox().getText();
                        valoresCamposOrdenModelo(numeroOrdenModelo);
                    
                    } else {
                        setNullValuesModelOrder();

                        new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ORDER_MODEL), TypeMessage.SUCCESS);

                    }

                }
            }

        });

        buscarOrden.getTextBox().setToolTipText("Press enter to set the model order");
    }
    private void valoresCamposOrdenModelo(String orden) {

        if (Verificar_Existencia_Orden_SQL.orderExists(orden)) {
            
            ModelOrder_SQL om = new ModelOrder_SQL();
            om.getData(orden);

            parada = om.isParada();
            tituloOrden = om.getTituloOrden();
            textoExtendido = om.getTextoExtendido();
            tipoOrden = om.getTipoOrden();
            descripcionTipoOrden = om.getDescripcionTipoOrden();
            codigoSistema = om.getCodigoSistema();
            descripcionSistema = om.getDescripcionSistema();
            codigoComponente = om.getCodigoComponente();
            descripcionComponente = om.getDescripcionComponente();
            codigoSintoma = om.getCodigoSintoma();
            descripcionSintoma = om.getDescripcionSintoma();
            solicitante = om.getSolicitante();
            nombreSolicitante = om.getNombreSolicitante();
            responsable = om.getResponsable();
            nombreResponsable = om.getNombreResponsable();

            modelOrder = true;

            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.ORDER_MODEL_SET), TypeMessage.SUCCESS);


        } else {

            setNullValuesModelOrder();

            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.ORDER_DOES_NOT_EXIST), TypeMessage.ERROR);

        }

    }
    private void setNullValuesModelOrder() {
        
        numeroOrdenModelo = null;
        tituloOrden = null;
        textoExtendido = null;
        tipoOrden = null;
        descripcionTipoOrden = null;
        codigoSistema = null;
        descripcionSistema = null;
        codigoComponente = null;
        descripcionComponente = null;
        codigoSintoma = null;
        descripcionSintoma = null;
        solicitante = null;
        nombreSolicitante = null;
        responsable = null;
        nombreResponsable = null;

    }
    
    private JFrame getSuperFrame(){
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }
    
    
    private void btnSalir() {
        btnSalir_CrearOrden.addActionListener((e) -> {

                Invoke_JMenuBars.setMenuBar(getSuperFrame(), getSuperFrame().getJMenuBar(),
                        LyraWorkspace.barMenu);

                PanelLoader.loadPanel(treeMenus, mainContainerPanel);            

        });
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_O02 = new javax.swing.JMenuBar();
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
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        generalData = new javax.swing.JPanel();
        labelModel = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        numeroOrdenLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        buscarOrden = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        claseOrdenBox = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        criticidadBox = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        equipoBox = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        ubicacionBox = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        labelEquipo = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelUbicacion = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelGrupoPlanif = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        grupoPlanBox = new javax.swing.JFormattedTextField();
        descripcionEquipoLB = new javax.swing.JLabel();
        labelClaseMtto = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        descripcionUbicacionLB = new javax.swing.JLabel();
        labelCriticidad = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir_CrearOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnNueva_Orden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnDeNuevo = new com.simplecore.erp.gui.components.labels.JButtonHQ();

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

        menuBar_O02.add(menuOrdenTrabajo);

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

        menuBar_O02.add(menuCabeceraOrden);

        menuRecursos.setText("Recursos");

        menuOperaciones.setText("Operaciones");
        menuRecursos.add(menuOperaciones);

        menuMateriales.setText("Materiales");
        menuRecursos.add(menuMateriales);

        menuBar_O02.add(menuRecursos);

        menuCostosOrden.setText("Costes");

        menuCostos.setText("Costes de la orden");
        menuCostosOrden.add(menuCostos);

        menuBar_O02.add(menuCostosOrden);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        generalData.setBackground(new java.awt.Color(238, 244, 254));
        generalData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        labelModel.setText("Model");
        labelModel.setColorBordes(new java.awt.Color(117, 141, 163));
        labelModel.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelModel.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        numeroOrdenLB.setText("Orden");
        numeroOrdenLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout generalDataLayout = new javax.swing.GroupLayout(generalData);
        generalData.setLayout(generalDataLayout);
        generalDataLayout.setHorizontalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addComponent(labelModel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 602, Short.MAX_VALUE))
            .addGroup(generalDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buscarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        generalDataLayout.setVerticalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addComponent(labelModel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        labelEquipo.setText("Equipment");
        labelEquipo.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelUbicacion.setText("Location");
        labelUbicacion.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelGrupoPlanif.setText("Planners group");
        labelGrupoPlanif.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        grupoPlanBox.setEditable(false);
        grupoPlanBox.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        grupoPlanBox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grupoPlanBox.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        descripcionEquipoLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionEquipoLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionEquipoLB.setText(" ");
        descripcionEquipoLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelClaseMtto.setText("Order class");
        labelClaseMtto.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        descripcionUbicacionLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionUbicacionLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionUbicacionLB.setText(" ");
        descripcionUbicacionLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelCriticidad.setText("Criticality");
        labelCriticidad.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generalData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelGrupoPlanif, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelClaseMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCriticidad, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(grupoPlanBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ubicacionBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(equipoBox, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(criticidadBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(claseOrdenBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descripcionUbicacionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionEquipoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(358, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelClaseMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claseOrdenBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCriticidad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(criticidadBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equipoBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionEquipoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubicacionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionUbicacionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelGrupoPlanif, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grupoPlanBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(generalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir_CrearOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir_CrearOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir_CrearOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        btnNueva_Orden.setBackground(new java.awt.Color(226, 210, 144));
        btnNueva_Orden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNueva_Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/new_empty.png"))); // NOI18N

        btnDeNuevo.setBackground(new java.awt.Color(226, 210, 144));
        btnDeNuevo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDeNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/refresh.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnNueva_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDeNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNueva_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnDeNuevo;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnNueva_Orden;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir_CrearOrden;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox buscarOrden;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox claseOrdenBox;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox criticidadBox;
    public static javax.swing.JLabel descripcionEquipoLB;
    public static javax.swing.JLabel descripcionUbicacionLB;
    protected static com.simplecore.erp.gui.components.searchbox.JSearchBox equipoBox;
    private javax.swing.JPanel generalData;
    protected static javax.swing.JFormattedTextField grupoPlanBox;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelClaseMtto;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCriticidad;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelEquipo;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelGrupoPlanif;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelModel;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelUbicacion;
    public static javax.swing.JMenuBar menuBar_O02;
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
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JPopupMenu.Separator separador2;
    private javax.swing.JPopupMenu.Separator separador3;
    private javax.swing.JPopupMenu.Separator separador4;
    protected static com.simplecore.erp.gui.components.searchbox.JSearchBox ubicacionBox;
    // End of variables declaration//GEN-END:variables


}
