
package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class U03_Estructura_Ubicaciones extends javax.swing.JPanel {
    
    
    public U03_Estructura_Ubicaciones() {
        initComponents(); 
        cargarArbol();
        addEvents();
    }

    private void addEvents(){

        botonSalir();
        menuItemSalir();

    }
    
    private void cargarArbol(){        
        U03_Estructurar_JTree_Matriz nuevaEstructura = new U03_Estructurar_JTree_Matriz();        
        nuevaEstructura.construirArbol(estructuraUbicaciones);
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            salir();
        });
    }
    
    private void menuItemSalir(){
        MENU_ITEM_SALIR.addActionListener((ActionEvent e) -> {
            salir();
        });
    }

    private void salir() {
        if (treeMenus != null) {
            EventQueue.invokeLater(() -> {
                
                cargarMenuBarPrincipal();
                PanelLoader.loadPanel(treeMenus, mainContainerPanel);
            });
        }
    }

    private void cargarMenuBarPrincipal() {

        JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(this);
        Invoke_JMenuBars.setMenuBar(ventanaPrincipal, ventanaPrincipal.getJMenuBar(),
                 LyraWorkspace.barMenu);

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_U03 = new javax.swing.JMenuBar();
        MENU_CREACION = new javax.swing.JMenu();
        SUB_MENU_E01_CREAR_EQUIPO = new javax.swing.JMenuItem();
        SUB_MENU_E02_MODIFICAR_EQUIPO = new javax.swing.JMenuItem();
        SUB_MENU_E03_VISUALIZAR_EQUIPO = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_ACCIONES = new javax.swing.JMenu();
        SUB_MENU_GUARDAR = new javax.swing.JMenuItem();
        SUB_MENU_ESTATUS = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        SUB_MENU_INSTRUCCIONES = new javax.swing.JMenuItem();
        SUB_MENU_ACERCA_DE_EQUIPOS = new javax.swing.JMenuItem();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        estructuraUbicaciones = new javax.swing.JTree();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        ToolBar = new javax.swing.JToolBar();
        separador1 = new javax.swing.JToolBar.Separator();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 0));
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        MENU_CREACION.setText("Creacion de equipo");

        SUB_MENU_E01_CREAR_EQUIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_E01_CREAR_EQUIPO.setText("E01 - Nuevo Crear equipo");
        MENU_CREACION.add(SUB_MENU_E01_CREAR_EQUIPO);

        SUB_MENU_E02_MODIFICAR_EQUIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_E02_MODIFICAR_EQUIPO.setText("E02 - Nuevo Modificar equipo");
        MENU_CREACION.add(SUB_MENU_E02_MODIFICAR_EQUIPO);

        SUB_MENU_E03_VISUALIZAR_EQUIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_E03_VISUALIZAR_EQUIPO.setText("E03 - Nuevo Visualizar equipo");
        MENU_CREACION.add(SUB_MENU_E03_VISUALIZAR_EQUIPO);
        MENU_CREACION.add(separador);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_CREACION.add(MENU_ITEM_SALIR);

        menuBar_U03.add(MENU_CREACION);

        MENU_ACCIONES.setText("Acciones");

        SUB_MENU_GUARDAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        SUB_MENU_GUARDAR.setText("Crear");
        MENU_ACCIONES.add(SUB_MENU_GUARDAR);

        SUB_MENU_ESTATUS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_ESTATUS.setText("Estatus");
        MENU_ACCIONES.add(SUB_MENU_ESTATUS);

        menuBar_U03.add(MENU_ACCIONES);

        MENU_AYUDA.setText("Ayuda");

        SUB_MENU_INSTRUCCIONES.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        SUB_MENU_INSTRUCCIONES.setText("Manual de instruccion");
        MENU_AYUDA.add(SUB_MENU_INSTRUCCIONES);

        SUB_MENU_ACERCA_DE_EQUIPOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        SUB_MENU_ACERCA_DE_EQUIPOS.setText("Acerca de Equipos");
        MENU_AYUDA.add(SUB_MENU_ACERCA_DE_EQUIPOS);

        menuBar_U03.add(MENU_AYUDA);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        bodyPanel.setOpaque(false);

        estructuraUbicaciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        estructuraUbicaciones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        estructuraUbicaciones.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        estructuraUbicaciones.setExpandsSelectedPaths(false);
        estructuraUbicaciones.setOpaque(false);
        estructuraUbicaciones.setShowsRootHandles(true);
        jScrollPane1.setViewportView(estructuraUbicaciones);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        ToolBar.setBackground(new java.awt.Color(114, 162, 207));
        ToolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ToolBar.setRollover(true);
        ToolBar.setBorderPainted(false);
        ToolBar.setOpaque(false);
        ToolBar.add(separador1);
        ToolBar.add(filler1);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ToolBar.add(btnSalir);

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addComponent(ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ToolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
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
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenu MENU_ACCIONES;
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_CREACION;
    private javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenuItem SUB_MENU_ACERCA_DE_EQUIPOS;
    public static javax.swing.JMenuItem SUB_MENU_E01_CREAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_E02_MODIFICAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_E03_VISUALIZAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_ESTATUS;
    public static javax.swing.JMenuItem SUB_MENU_GUARDAR;
    public static javax.swing.JMenuItem SUB_MENU_INSTRUCCIONES;
    private javax.swing.JToolBar ToolBar;
    private javax.swing.JPanel bodyPanel;
    public static javax.swing.JButton btnSalir;
    private javax.swing.JTree estructuraUbicaciones;
    private javax.swing.Box.Filler filler1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JMenuBar menuBar_U03;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JToolBar.Separator separador1;
    // End of variables declaration//GEN-END:variables
}
