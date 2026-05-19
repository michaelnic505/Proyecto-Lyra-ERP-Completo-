package com.simplecore.erp.modules.controlling.areas.legacy;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;
import java.awt.Frame;


public class A01_Crear_Areas extends javax.swing.JPanel {

    public A01_Crear_Areas() {
        initComponents();
        addEvents();
    }

    private void addEvents() {
        botonSalir();
        botonCrear();
        botonSociedad();
        menuItemSalir();
        componentesAlIniciar();
    }

    public void setMenuBarrra() {

        JFrame frames = (JFrame) SwingUtilities.getRoot(mainContainerPanel);
        frames.setJMenuBar(menuBar_A01);
        frames.repaint();
    }

    private void componentesAlIniciar(){
        
        btnCrear.setEnabled(true);
        btnSalir.setEnabled(true);
        btnSociedad.setEnabled(true);
        
        idArea.setEditable(true);
        descripcionArea.setEditable(true);
        
        idSociedad.setEditable(false);
        descripcionSociedad.setEditable(false);
    }
    
    private void componentesAlGuardar(){
        
        btnCrear.setEnabled(false);
        btnSalir.setEnabled(true);
        btnSociedad.setEnabled(false);
        
        idArea.setEditable(false);
        descripcionArea.setEditable(false);
        
        idSociedad.setEditable(false);
        descripcionSociedad.setEditable(false);
        
    }

    private void botonSalir() {
        
        btnSalir.addActionListener((ActionEvent e) -> {
            salir();
        
        });
    }
    
    private void menuItemSalir(){
        
        menuItemSalir.addActionListener((ActionEvent e) -> {
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

    private void botonCrear(){
        
        btnCrear.addActionListener((ActionEvent e) -> {
          crear_Area();            
       
        });
    }

    
    private void crear_Area() {

        if (!(idArea.getText().isEmpty()
            | descripcionArea.getText().isEmpty()
            | idSociedad.getText().isEmpty()
            | descripcionSociedad.getText().isEmpty())) {

            
            A01_Create_Area newArea = new A01_Create_Area();

            newArea.setIdArea(idArea.getText());
            newArea.setDescriptionArea(descripcionArea.getText());
            newArea.setIdSociety(idSociedad.getText());
            newArea.setDescriptionSociety(descripcionSociedad.getText());

            newArea.createArea();
            
            componentesAlGuardar();
            new SystemMessages(NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.SUCCESS);
            
        } else {
            
            new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.WARNING);
            
        }

    }


    
    private void botonSociedad(){
        
        btnSociedad.addActionListener((ActionEvent e) -> {
             A01_Lista_Sociedades ls = new A01_Lista_Sociedades(getSuperFrame(),true);
             ls.setCodigoTextfield(idSociedad);
             ls.setDescripcionTextfield(descripcionSociedad);
             ls.setTitle(labelSociedad.getText());
             ls.setVisible(true);
        });
    
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_A01 = new javax.swing.JMenuBar();
        MENU_AREAS = new javax.swing.JMenu();
        SUB_MENU_CREAR_OTRA_AREA = new javax.swing.JMenuItem();
        SUB_MENU_MODIFICAR_AREA = new javax.swing.JMenuItem();
        SUB_MENU_VISUALIZAR_AREA = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        menuItemSalir = new javax.swing.JMenuItem();
        MENU_CENTRO_COSTOS = new javax.swing.JMenu();
        SUB_MENU_ASIGNAR_CENTRO_COSTOS = new javax.swing.JMenuItem();
        MENU_NIVELES_AREAS = new javax.swing.JMenu();
        SUB_MENU_ASIGNAR_NIVEL = new javax.swing.JMenuItem();
        MENU_AREA_SUPERIOR = new javax.swing.JMenu();
        SUB_MENU_ASIGNAR_AREA_SUPERIOR = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        MENU_ITEM_MANUAL = new javax.swing.JMenuItem();
        MENU_ITEM_ACERCA_DE = new javax.swing.JMenuItem();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        MULTITAB = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        labelGeneralDataTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        btnSociedad = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        idArea = new javax.swing.JTextField();
        descripcionArea = new javax.swing.JTextField();
        descripcionSociedad = new javax.swing.JTextField();
        idSociedad = new javax.swing.JTextField();
        labelIdArea = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelDescripiconArea = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelSociedad = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        jPanel_Rounded_Corners_Degradado5 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        ToolBar = new javax.swing.JToolBar();
        separador1 = new javax.swing.JToolBar.Separator();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 0));
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnCrear = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        MENU_AREAS.setText("Gestion de Areas");

        SUB_MENU_CREAR_OTRA_AREA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_CREAR_OTRA_AREA.setText("Crear Area");
        MENU_AREAS.add(SUB_MENU_CREAR_OTRA_AREA);

        SUB_MENU_MODIFICAR_AREA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_MODIFICAR_AREA.setText("Modificar Area");
        MENU_AREAS.add(SUB_MENU_MODIFICAR_AREA);

        SUB_MENU_VISUALIZAR_AREA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_VISUALIZAR_AREA.setText("Visualizar Area");
        MENU_AREAS.add(SUB_MENU_VISUALIZAR_AREA);
        MENU_AREAS.add(separador);

        menuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menuItemSalir.setText("Salir");
        MENU_AREAS.add(menuItemSalir);

        menuBar_A01.add(MENU_AREAS);

        MENU_CENTRO_COSTOS.setText("Centro de costos");

        SUB_MENU_ASIGNAR_CENTRO_COSTOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        SUB_MENU_ASIGNAR_CENTRO_COSTOS.setText("Asignar centro de costos");
        MENU_CENTRO_COSTOS.add(SUB_MENU_ASIGNAR_CENTRO_COSTOS);

        menuBar_A01.add(MENU_CENTRO_COSTOS);

        MENU_NIVELES_AREAS.setText("Niveles de areas");

        SUB_MENU_ASIGNAR_NIVEL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        SUB_MENU_ASIGNAR_NIVEL.setText("Asignar un nivel");
        MENU_NIVELES_AREAS.add(SUB_MENU_ASIGNAR_NIVEL);

        menuBar_A01.add(MENU_NIVELES_AREAS);

        MENU_AREA_SUPERIOR.setText("Area superior");

        SUB_MENU_ASIGNAR_AREA_SUPERIOR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        SUB_MENU_ASIGNAR_AREA_SUPERIOR.setText("Asignar area superior");
        MENU_AREA_SUPERIOR.add(SUB_MENU_ASIGNAR_AREA_SUPERIOR);

        menuBar_A01.add(MENU_AREA_SUPERIOR);

        MENU_AYUDA.setText("Ayuda");

        MENU_ITEM_MANUAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MENU_ITEM_MANUAL.setText("Manual Instruccion");
        MENU_AYUDA.add(MENU_ITEM_MANUAL);

        MENU_ITEM_ACERCA_DE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        MENU_ITEM_ACERCA_DE.setText("Acerca de Areas");
        MENU_AYUDA.add(MENU_ITEM_ACERCA_DE);

        menuBar_A01.add(MENU_AYUDA);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        MULTITAB.setBackground(new java.awt.Color(202, 216, 237));
        MULTITAB.setForeground(new java.awt.Color(102, 102, 102));
        MULTITAB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(238, 244, 254));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBackground(new java.awt.Color(202, 219, 236));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        labelGeneralDataTitle.setText("Datos generales");
        labelGeneralDataTitle.setColorBordes(new java.awt.Color(117, 141, 163));
        labelGeneralDataTitle.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelGeneralDataTitle.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        btnSociedad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/general_windows/lupa15.png"))); // NOI18N

        labelIdArea.setText("ID Area :");
        labelIdArea.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelDescripiconArea.setText("Descripcion Area :");
        labelDescripiconArea.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelSociedad.setText("Sociedad");
        labelSociedad.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelGeneralDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDescripiconArea, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIdArea, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idArea, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(descripcionArea, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(idSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(descripcionSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(btnSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelGeneralDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelIdArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelDescripiconArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(313, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Datos Generales", jPanel2);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(339, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(MULTITAB)
                .addContainerGap())
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado5.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado5.setColor2(new java.awt.Color(202, 216, 237));

        ToolBar.setBackground(new java.awt.Color(114, 162, 207));
        ToolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ToolBar.setRollover(true);
        ToolBar.setBorderPainted(false);
        ToolBar.setOpaque(false);
        ToolBar.add(separador1);
        ToolBar.add(filler1);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/general_windows/regresar15.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ToolBar.add(btnSalir);

        btnCrear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/general_windows/guardar15.png"))); // NOI18N
        btnCrear.setText("Crear");
        btnCrear.setFocusable(false);
        btnCrear.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnCrear.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ToolBar.add(btnCrear);

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado5Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado5);
        jPanel_Rounded_Corners_Degradado5.setLayout(jPanel_Rounded_Corners_Degradado5Layout);
        jPanel_Rounded_Corners_Degradado5Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado5Layout.createSequentialGroup()
                .addComponent(ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado5Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ToolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll)
            .addComponent(jPanel_Rounded_Corners_Degradado5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenu MENU_AREAS;
    public static javax.swing.JMenu MENU_AREA_SUPERIOR;
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_CENTRO_COSTOS;
    public static javax.swing.JMenuItem MENU_ITEM_ACERCA_DE;
    public static javax.swing.JMenuItem MENU_ITEM_MANUAL;
    public static javax.swing.JMenu MENU_NIVELES_AREAS;
    public static javax.swing.JTabbedPane MULTITAB;
    public static javax.swing.JMenuItem SUB_MENU_ASIGNAR_AREA_SUPERIOR;
    public static javax.swing.JMenuItem SUB_MENU_ASIGNAR_CENTRO_COSTOS;
    public static javax.swing.JMenuItem SUB_MENU_ASIGNAR_NIVEL;
    public static javax.swing.JMenuItem SUB_MENU_CREAR_OTRA_AREA;
    public static javax.swing.JMenuItem SUB_MENU_MODIFICAR_AREA;
    public static javax.swing.JMenuItem SUB_MENU_VISUALIZAR_AREA;
    private javax.swing.JToolBar ToolBar;
    private javax.swing.JPanel bodyPanel;
    public static javax.swing.JButton btnCrear;
    public static javax.swing.JButton btnSalir;
    protected static javax.swing.JButton btnSociedad;
    protected static javax.swing.JTextField descripcionArea;
    private static javax.swing.JTextField descripcionSociedad;
    private javax.swing.Box.Filler filler1;
    protected static javax.swing.JTextField idArea;
    private javax.swing.JTextField idSociedad;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado5;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelDescripiconArea;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelGeneralDataTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelIdArea;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSociedad;
    public static javax.swing.JMenuBar menuBar_A01;
    public static javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JToolBar.Separator separador1;
    // End of variables declaration//GEN-END:variables

    private Frame getSuperFrame() {
        return (Frame) SwingUtilities.getWindowAncestor(mainContainerPanel);
    }

}
