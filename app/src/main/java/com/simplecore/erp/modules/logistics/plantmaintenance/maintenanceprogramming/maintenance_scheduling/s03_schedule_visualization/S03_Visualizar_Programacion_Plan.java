package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceprogramming.maintenance_scheduling.s03_schedule_visualization;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class S03_Visualizar_Programacion_Plan extends javax.swing.JPanel {
    
    S03_Lista_Programacion_Plan nuevaLista;
    
    public S03_Visualizar_Programacion_Plan() {
        initComponents(); 
        addEvents();
    }

    
    private void addEvents(){
        botonSalir();        
        btnEjecutar();
        MENU_ITEM_SALIR();
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            salir();
        });
    }

    private void MENU_ITEM_SALIR() {
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
    
    
     private void btnEjecutar(){
         btnEjecutar.addActionListener((ActionEvent e) -> {
             nuevaLista = new S03_Lista_Programacion_Plan();
             PanelLoader.loadPanel(nuevaLista, mainContainerPanel);
        });
     }

    private void cargarMenuBarPrincipal() {

        JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(this);
        Invoke_JMenuBars.setMenuBar(ventanaPrincipal, ventanaPrincipal.getJMenuBar(),
                 LyraWorkspace.barMenu);

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_S03 = new javax.swing.JMenuBar();
        MENU_MODIFICACION = new javax.swing.JMenu();
        SUB_MENU_E01_CREAR_EQUIPO = new javax.swing.JMenuItem();
        SUB_MENU_E02_MODIFICAR_EQUIPO = new javax.swing.JMenuItem();
        SUB_MENU_E03_VISUALIZAR_EQUIPO = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_ACCIONES = new javax.swing.JMenu();
        SUB_MENU_GUARDAR = new javax.swing.JMenuItem();
        SUB_MENU_ESTATUS = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        MENU_ITEM_MANUAL = new javax.swing.JMenuItem();
        MENU_ITEM_ACERCA_DE = new javax.swing.JMenuItem();
        cintaControles = new javax.swing.JToolBar();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEjecutar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        LABELFILTROS = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        LABELNUMEROPLAN = new javax.swing.JLabel();
        NUMERO_PLAN1 = new javax.swing.JTextField();
        LABELA = new javax.swing.JLabel();
        NUMERO_PLAN2 = new javax.swing.JTextField();
        LABELNUMEROPLAN1 = new javax.swing.JLabel();
        NUMERO_PLAN3 = new javax.swing.JTextField();
        LABELA1 = new javax.swing.JLabel();
        NUMERO_PLAN4 = new javax.swing.JTextField();
        LABELNUMEROPLAN2 = new javax.swing.JLabel();
        NUMERO_PLAN5 = new javax.swing.JTextField();
        LABELA2 = new javax.swing.JLabel();
        NUMERO_PLAN6 = new javax.swing.JTextField();
        LABELNUMEROPLAN3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        LABELA3 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        LABELNUMEROPLAN4 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        LABELA4 = new javax.swing.JLabel();
        LABELNUMEROPLAN5 = new javax.swing.JLabel();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        LABELA5 = new javax.swing.JLabel();
        LABELNUMEROPLAN6 = new javax.swing.JLabel();
        LABELA6 = new javax.swing.JLabel();
        NUMERO_PLAN7 = new javax.swing.JTextField();
        NUMERO_PLAN8 = new javax.swing.JTextField();

        MENU_MODIFICACION.setText("Visualizacion de equipo");

        SUB_MENU_E01_CREAR_EQUIPO.setText("Nuevo Crear equipo");
        MENU_MODIFICACION.add(SUB_MENU_E01_CREAR_EQUIPO);

        SUB_MENU_E02_MODIFICAR_EQUIPO.setText("Nuevo Modificar equipo");
        MENU_MODIFICACION.add(SUB_MENU_E02_MODIFICAR_EQUIPO);

        SUB_MENU_E03_VISUALIZAR_EQUIPO.setText("Nuevo Visualizar equipo");
        MENU_MODIFICACION.add(SUB_MENU_E03_VISUALIZAR_EQUIPO);
        MENU_MODIFICACION.add(jSeparator2);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_MODIFICACION.add(MENU_ITEM_SALIR);

        menuBar_S03.add(MENU_MODIFICACION);

        MENU_ACCIONES.setText("Acciones");

        SUB_MENU_GUARDAR.setText("Visualizar");
        MENU_ACCIONES.add(SUB_MENU_GUARDAR);

        SUB_MENU_ESTATUS.setText("Estatus");
        MENU_ACCIONES.add(SUB_MENU_ESTATUS);

        menuBar_S03.add(MENU_ACCIONES);

        MENU_AYUDA.setText("Ayuda");

        MENU_ITEM_MANUAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        MENU_ITEM_MANUAL.setText("Manual Instruccion");
        MENU_AYUDA.add(MENU_ITEM_MANUAL);

        MENU_ITEM_ACERCA_DE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MENU_ITEM_ACERCA_DE.setText("Acerca de Equipos");
        MENU_AYUDA.add(MENU_ITEM_ACERCA_DE);

        menuBar_S03.add(MENU_AYUDA);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        cintaControles.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cintaControles.setRollover(true);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnSalir);

        btnEjecutar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEjecutar.setText("Ejecutar");
        btnEjecutar.setFocusable(false);
        btnEjecutar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnEjecutar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnEjecutar);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LABELFILTROS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LABELFILTROS.setText("Filtros");

        LABELNUMEROPLAN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELNUMEROPLAN.setText("Numero programacion");

        LABELA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELA.setText("a");

        LABELNUMEROPLAN1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELNUMEROPLAN1.setText("Numero plan");

        LABELA1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELA1.setText("a");

        LABELNUMEROPLAN2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELNUMEROPLAN2.setText("Equipo");

        LABELA2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELA2.setText("a");

        LABELNUMEROPLAN3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELNUMEROPLAN3.setText("Fecha progamada");

        LABELA3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELA3.setText("a");

        LABELNUMEROPLAN4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELNUMEROPLAN4.setText("Fecha orden");

        LABELA4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELA4.setText("a");

        LABELNUMEROPLAN5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELNUMEROPLAN5.setText("Fecha cierre");

        LABELA5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELA5.setText("a");

        LABELNUMEROPLAN6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELNUMEROPLAN6.setText("Punto medida");

        LABELA6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELA6.setText("a");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(LABELFILTROS)
                        .addGap(570, 570, 570))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LABELNUMEROPLAN2)
                            .addComponent(LABELNUMEROPLAN1)
                            .addComponent(LABELNUMEROPLAN)
                            .addComponent(LABELNUMEROPLAN3)
                            .addComponent(LABELNUMEROPLAN4)
                            .addComponent(LABELNUMEROPLAN5)
                            .addComponent(LABELNUMEROPLAN6))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NUMERO_PLAN8)
                            .addComponent(NUMERO_PLAN5)
                            .addComponent(NUMERO_PLAN3)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(NUMERO_PLAN1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LABELA1)
                                .addComponent(LABELA)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LABELA2)
                                    .addComponent(LABELA3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(LABELA4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LABELA5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LABELA6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NUMERO_PLAN7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NUMERO_PLAN6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NUMERO_PLAN4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NUMERO_PLAN2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LABELFILTROS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELNUMEROPLAN)
                    .addComponent(NUMERO_PLAN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELA)
                    .addComponent(NUMERO_PLAN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELNUMEROPLAN1)
                    .addComponent(NUMERO_PLAN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELA1)
                    .addComponent(NUMERO_PLAN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELNUMEROPLAN2)
                    .addComponent(NUMERO_PLAN5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELA2)
                    .addComponent(NUMERO_PLAN6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELNUMEROPLAN3)
                    .addComponent(LABELA3)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELNUMEROPLAN4)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELA4)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELNUMEROPLAN5)
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELA5)
                    .addComponent(jDateChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELNUMEROPLAN6)
                    .addComponent(NUMERO_PLAN8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELA6)
                    .addComponent(NUMERO_PLAN7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(424, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        panelScroll.setViewportView(bodyPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cintaControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cintaControles, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LABELA;
    private javax.swing.JLabel LABELA1;
    private javax.swing.JLabel LABELA2;
    private javax.swing.JLabel LABELA3;
    private javax.swing.JLabel LABELA4;
    private javax.swing.JLabel LABELA5;
    private javax.swing.JLabel LABELA6;
    private javax.swing.JLabel LABELFILTROS;
    private javax.swing.JLabel LABELNUMEROPLAN;
    private javax.swing.JLabel LABELNUMEROPLAN1;
    private javax.swing.JLabel LABELNUMEROPLAN2;
    private javax.swing.JLabel LABELNUMEROPLAN3;
    private javax.swing.JLabel LABELNUMEROPLAN4;
    private javax.swing.JLabel LABELNUMEROPLAN5;
    private javax.swing.JLabel LABELNUMEROPLAN6;
    public static javax.swing.JMenu MENU_ACCIONES;
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenuItem MENU_ITEM_ACERCA_DE;
    public static javax.swing.JMenuItem MENU_ITEM_MANUAL;
    private javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenu MENU_MODIFICACION;
    private javax.swing.JTextField NUMERO_PLAN1;
    private javax.swing.JTextField NUMERO_PLAN2;
    private javax.swing.JTextField NUMERO_PLAN3;
    private javax.swing.JTextField NUMERO_PLAN4;
    private javax.swing.JTextField NUMERO_PLAN5;
    private javax.swing.JTextField NUMERO_PLAN6;
    private javax.swing.JTextField NUMERO_PLAN7;
    private javax.swing.JTextField NUMERO_PLAN8;
    public static javax.swing.JMenuItem SUB_MENU_E01_CREAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_E02_MODIFICAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_E03_VISUALIZAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_ESTATUS;
    public static javax.swing.JMenuItem SUB_MENU_GUARDAR;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btnEjecutar;
    public static javax.swing.JButton btnSalir;
    private javax.swing.JToolBar cintaControles;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    public static javax.swing.JMenuBar menuBar_S03;
    private javax.swing.JScrollPane panelScroll;
    // End of variables declaration//GEN-END:variables
}
