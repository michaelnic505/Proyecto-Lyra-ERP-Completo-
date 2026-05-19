package com.simplecore.erp.modules.controlling.society.legacy;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.config.database.DatabaseTables;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class C01_Crear_Nueva_Compania extends javax.swing.JPanel {
    
        
    String matriz;
    String descripcionMatriz;
    
    public C01_Crear_Nueva_Compania() {
        
        initComponents(); 
        addEvents();
        grupoButton.add(btnFI);
        grupoButton.add(btnCO);
        verificarSociedadCO();

    }

    
    private void addEvents() {

        botonSalir();
        botonCrear();
        botonPais();
        menuItemSalir();

    }

    public void setMenuBarrra() {

        JFrame frames = (JFrame) SwingUtilities.getRoot(mainContainerPanel);
        frames.setJMenuBar(menuBar_C01);
        frames.repaint();
    }

    private void verificarSociedadCO(){
        
        if(C01_Verificar_Sociedad_CO.verificar()){
            
            btnCO.setEnabled(false);
            btnFI.setSelected(true);
            btnFI.setEnabled(false);
            
            C01_Extrae_Sociedad_CO ex = new C01_Extrae_Sociedad_CO();
            ex.extraer();
            
            matriz = ex.getMatriz();
            descripcionMatriz = ex.getDescripcionMatriz();
            
        }else{
        
            new SystemMessages(NOT.msg(NOT.SOCIETY_CO_NOT_FOUND), TypeMessage.WARNING);
            btnCO.setSelected(true);
            btnFI.setEnabled(false);
        }
        
        
        
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
    
    private void botonPais(){
        
        buscarPais.addActionListener((ActionEvent e) -> {
            new C01_Lista_Pais(new javax.swing.JFrame(), true).setVisible(true);  
    
        });
    
    }

    private void botonCrear() {
        btnCrear.addActionListener((ActionEvent e) -> {
            guardarCompañia();
            
        });
    }

    private void guardarCompañia(){
        
        if(!(idEmpresa.getText().isEmpty()
                | descripcionEmpresa.getText().isEmpty()
                | idPais.getText().isEmpty()
                | descripcionPais.getText().isEmpty())){
            
            
        C01_Crear_Compania  nuevaCompania = new C01_Crear_Compania();        
        nuevaCompania.setTABLA_SQL(DatabaseTables.Empresas.tableName());
        nuevaCompania.setID_EMPRESA(idEmpresa.getText());
        nuevaCompania.setDESCRIPCION_EMPRESA(descripcionEmpresa.getText());
        
        nuevaCompania.setID_PAIS(idPais.getText());        
        nuevaCompania.setDESCRIPCION_PAIS(descripcionPais.getText());
        
        nuevaCompania.setESTADO(Status_Companies.A.toString());
        
        nuevaCompania.setMatriz(matriz);
        nuevaCompania.setDenominacionMatriz(descripcionMatriz);
        
        
        if(btnFI.isSelected()){
            nuevaCompania.setTIPO(TypesCompanies.FI.toString());        
        }else if(btnCO.isSelected()){
            nuevaCompania.setTIPO(TypesCompanies.CO.toString());        
        }
        
        
        nuevaCompania.crear_Empresa(); 
        
        
        
        btnCrear.setEnabled(false);
        buscarPais.setEnabled(false);
        idEmpresa.setEditable(false);
        descripcionEmpresa.setEditable(false);
        idPais.setEditable(false);
        descripcionPais.setEditable(false);
        btnFI.setEnabled(false);
        btnCO.setEnabled(false);
        
            if (nuevaCompania.getCODIGO_ERROR() == 1062) {

                btnCrear.setEnabled(true);
                buscarPais.setEnabled(true);
                idEmpresa.setEditable(true);
                descripcionEmpresa.setEditable(true);
                idPais.setEditable(true);
                descripcionPais.setEditable(true);

            }
        
        }else{
         
            JOptionPane.showMessageDialog(this, NOT.msg(NOT.EMPTY_FIELDS), NOT.msg(NOT.TITLE), JOptionPane.ERROR_MESSAGE);
         
        }
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_C01 = new javax.swing.JMenuBar();
        MENU_EMPRESAS = new javax.swing.JMenu();
        SUB_MENU_CREAR_COMPANIA = new javax.swing.JMenuItem();
        SUB_MENU_MODIFICAR_COMPANIA = new javax.swing.JMenuItem();
        SUB_MENU_VISUALIZAR_COMPANIA = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        menuItemSalir = new javax.swing.JMenuItem();
        MENU_PAISES = new javax.swing.JMenu();
        SUB_MENU_ASIGNAR_PAIS = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        MENU_ITEM_MANUAL = new javax.swing.JMenuItem();
        MENU_ITEM_ACERCA_DE = new javax.swing.JMenuItem();
        grupoButton = new javax.swing.ButtonGroup();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        MULTITAB = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        labelGeneralDataTitle = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        labelIdEmpresa = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        idEmpresa = new javax.swing.JTextField();
        labelDescripcionEmpresa = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        descripcionEmpresa = new javax.swing.JTextField();
        labelPais = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        idPais = new javax.swing.JTextField();
        descripcionPais = new javax.swing.JTextField();
        buscarPais = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnFI = new javax.swing.JRadioButton();
        btnCO = new javax.swing.JRadioButton();
        jPanel_Rounded_Corners_Degradado5 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        ToolBar = new javax.swing.JToolBar();
        separador1 = new javax.swing.JToolBar.Separator();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 0));
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnCrear = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        MENU_EMPRESAS.setText("Compañias");

        SUB_MENU_CREAR_COMPANIA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_CREAR_COMPANIA.setText("Crear Compañia");
        MENU_EMPRESAS.add(SUB_MENU_CREAR_COMPANIA);

        SUB_MENU_MODIFICAR_COMPANIA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_MODIFICAR_COMPANIA.setText("Modificar Compañia");
        MENU_EMPRESAS.add(SUB_MENU_MODIFICAR_COMPANIA);

        SUB_MENU_VISUALIZAR_COMPANIA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_VISUALIZAR_COMPANIA.setText("Visualizar Compañia");
        MENU_EMPRESAS.add(SUB_MENU_VISUALIZAR_COMPANIA);
        MENU_EMPRESAS.add(separador);

        menuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menuItemSalir.setText("Salir");
        MENU_EMPRESAS.add(menuItemSalir);

        menuBar_C01.add(MENU_EMPRESAS);

        MENU_PAISES.setText("Paises");

        SUB_MENU_ASIGNAR_PAIS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_ASIGNAR_PAIS.setText("Asignar un pais");
        MENU_PAISES.add(SUB_MENU_ASIGNAR_PAIS);

        menuBar_C01.add(MENU_PAISES);

        MENU_AYUDA.setText("Ayuda");

        MENU_ITEM_MANUAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MENU_ITEM_MANUAL.setText("Manual Instruccion");
        MENU_AYUDA.add(MENU_ITEM_MANUAL);

        MENU_ITEM_ACERCA_DE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        MENU_ITEM_ACERCA_DE.setText("Acerca de Compañias");
        MENU_AYUDA.add(MENU_ITEM_ACERCA_DE);

        menuBar_C01.add(MENU_AYUDA);

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

        labelIdEmpresa.setText("ID Empresa");
        labelIdEmpresa.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelDescripcionEmpresa.setText("Descripcion");
        labelDescripcionEmpresa.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelPais.setText("País");
        labelPais.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        idPais.setEditable(false);

        descripcionPais.setEditable(false);

        buscarPais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/general_windows/lupa15.png"))); // NOI18N

        btnFI.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        btnFI.setText("FI");

        btnCO.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        btnCO.setText("CO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelGeneralDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelDescripcionEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelIdEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPais, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(idPais, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(descripcionPais))
                            .addComponent(descripcionEmpresa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarPais, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(idEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnFI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCO)
                        .addGap(0, 201, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelGeneralDataTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFI)
                    .addComponent(btnCO))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelDescripcionEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelPais, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarPais, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(300, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Datos Generales", jPanel2);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(351, Short.MAX_VALUE))
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
            .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
            .addComponent(jPanel_Rounded_Corners_Degradado5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_EMPRESAS;
    public static javax.swing.JMenuItem MENU_ITEM_ACERCA_DE;
    public static javax.swing.JMenuItem MENU_ITEM_MANUAL;
    public static javax.swing.JMenu MENU_PAISES;
    public static javax.swing.JTabbedPane MULTITAB;
    public static javax.swing.JMenuItem SUB_MENU_ASIGNAR_PAIS;
    public static javax.swing.JMenuItem SUB_MENU_CREAR_COMPANIA;
    private javax.swing.JMenuItem SUB_MENU_MODIFICAR_COMPANIA;
    private javax.swing.JMenuItem SUB_MENU_VISUALIZAR_COMPANIA;
    private javax.swing.JToolBar ToolBar;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JRadioButton btnCO;
    public static javax.swing.JButton btnCrear;
    private javax.swing.JRadioButton btnFI;
    public static javax.swing.JButton btnSalir;
    protected static javax.swing.JButton buscarPais;
    protected static javax.swing.JTextField descripcionEmpresa;
    protected static javax.swing.JTextField descripcionPais;
    private javax.swing.Box.Filler filler1;
    private javax.swing.ButtonGroup grupoButton;
    protected static javax.swing.JTextField idEmpresa;
    protected static javax.swing.JTextField idPais;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado5;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelDescripcionEmpresa;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelGeneralDataTitle;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelIdEmpresa;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelPais;
    public static javax.swing.JMenuBar menuBar_C01;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JToolBar.Separator separador1;
    // End of variables declaration//GEN-END:variables
}
