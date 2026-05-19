package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o02_modification_of_orders;


import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Verificar_Existencia_Orden_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Ordenes_Trabajo;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class O02_Modificar_Orden extends javax.swing.JPanel {

    JFrame frame;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy.MM.dd");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
    String numeroOrden;
    JPanel thisPanel = this;
    String titulo;

    private String username;
    
    public O02_Modificar_Orden(String username) {

        this.username = username;
        initComponents();
        frame = (JFrame) SwingUtilities.getWindowAncestor(mainContainerPanel);
        addEvents();

    }
    private void addEvents(){

        btnSalir();
        buscarOrden();
        abrirListaOrdenes();
        buttonAbrirOrden();
        
    }

    public void setTitle() {
        LyraWorkspace.TitleLabel.setText(titulo);
    }


    private void btnSalir() {

        btnSalir_CrearOrden.addActionListener((e) -> {
                JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(this);
                Invoke_JMenuBars.setMenuBar(ventanaPrincipal, ventanaPrincipal.getJMenuBar(),
                        LyraWorkspace.barMenu);
                PanelLoader.loadPanel(treeMenus, mainContainerPanel);
        });
    }

    
    private void buscarOrden(){

        cajaBusquedaOrden.getTextBox().addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                if(Character.isLetter(e.getKeyChar())){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (!cajaBusquedaOrden.getTextBox().getText().isEmpty()) {

                        String orden = cajaBusquedaOrden.getTextBox().getText();
                       
                        if (Verificar_Existencia_Orden_SQL.orderExists(orden)) {
                            ambienteModificacion();
                        } else {
                            new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.ORDER_DOES_NOT_EXIST), TypeMessage.ERROR);
                        }

                    }

                }
            }
            
        });
    }
    private void abrirListaOrdenes(){
        cajaBusquedaOrden.getButton().addActionListener((e)->{
            
            Lista_Ordenes_Trabajo lo = new Lista_Ordenes_Trabajo();
            lo.setPanelAnterior(thisPanel);
            lo.setNumOrderTextBox(cajaBusquedaOrden.getTextBox());
            
            PanelLoader.loadPanel(lo, mainContainerPanel);
            
        });
    }
    private void ambienteModificacion() {

        String orden = cajaBusquedaOrden.getTextBox().getText();
        
        O02_Modificacion_Ordenes_Trabajo mod = new O02_Modificacion_Ordenes_Trabajo(username);       
        mod.setPanelAnterior(thisPanel);
        mod.cargarDatosCabeceraOrden(orden);
        mod.cargarListaOperaciones(orden);
        mod.cargarListaMateriales(orden);
        mod.addEvents();
        PanelLoader.loadPanel(mod, mainContainerPanel);
     
    }

    private void buttonAbrirOrden() {
        btnAbrirOrden.addActionListener((e) -> {

            if (!cajaBusquedaOrden.getTextBox().getText().isEmpty()) {
                String orden = cajaBusquedaOrden.getTextBox().getText();

                if (Verificar_Existencia_Orden_SQL.orderExists(orden)) {

                    ambienteModificacion();

                } else {

                   new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.ORDER_DOES_NOT_EXIST), TypeMessage.ERROR);

                }
            }
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
        numeroOrdenLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelIngresarOrden = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        cajaBusquedaOrden = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir_CrearOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnAbrirOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
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

        numeroOrdenLB.setText("Orden");
        numeroOrdenLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelIngresarOrden.setText("Ingrese el numero de orden a tratar");
        labelIngresarOrden.setColorBordes(new java.awt.Color(117, 141, 163));
        labelIngresarOrden.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelIngresarOrden.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout generalDataLayout = new javax.swing.GroupLayout(generalData);
        generalData.setLayout(generalDataLayout);
        generalDataLayout.setHorizontalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cajaBusquedaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(labelIngresarOrden, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
        );
        generalDataLayout.setVerticalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addComponent(labelIngresarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaBusquedaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(generalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(444, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(generalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(393, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir_CrearOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir_CrearOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir_CrearOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close.png"))); // NOI18N

        btnAbrirOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnAbrirOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAbrirOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N

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
                .addComponent(btnAbrirOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnNueva_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNueva_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbrirOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnAbrirOrden;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnNueva_Orden;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir_CrearOrden;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox cajaBusquedaOrden;
    private javax.swing.JPanel generalData;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelIngresarOrden;
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
    // End of variables declaration//GEN-END:variables


}
