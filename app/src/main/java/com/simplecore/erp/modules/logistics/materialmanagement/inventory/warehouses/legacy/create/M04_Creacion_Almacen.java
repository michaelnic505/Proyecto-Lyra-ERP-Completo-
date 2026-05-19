package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.create;


import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class M04_Creacion_Almacen extends javax.swing.JPanel {

    protected static String ESTRATEGIA;    
    
    public M04_Creacion_Almacen() {
        
        initComponents(); 
        addEvents();
        componentesAlIniciar();        
    }

  
    protected static void componentesAlIniciar(){
      
        btnSalir.setEnabled(true);
        btnGuardar.setEnabled(true); 
        btnNuevo.setEnabled(false);
    }
    
    protected static void componentesDespuesGuardar(){
       
        btnSalir.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(true);
    }
    
    private void addEvents(){
        botonSalir();
        botonCrear();   
        btnNuevo();
        btnTipoAlmacen() ;
        btnClaseAlmacen();
        btnEstatusAlmacen();
        btnArea();
        btnCompania();
        
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

    private void cargarMenuBarPrincipal() {

        JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(this);

        Invoke_JMenuBars.setMenuBar(ventanaPrincipal, ventanaPrincipal.getJMenuBar(),
                LyraWorkspace.barMenu);

    }

    private void botonCrear() {
        btnGuardar.addActionListener((ActionEvent e) -> {
             crear_Almacen();
        });
    }

    private void btnNuevo() {
        btnNuevo.addActionListener((ActionEvent e) -> {
            
            CODIGO_ALMACEN.setEditable(true);
            CODIGO_ALMACEN.setText(null);
            DESCRIPCION_ALMACEN.setText(null);
            ID_TIPO_ALMACEN.setText(null);
            DESCRIPCION_TIPO_ALMACEN.setText(null);
            ID_CLASE_ALMACEN.setText(null);
            DESCRIPCION_CLASE_ALMACEN.setText(null);
            ID_ESTATUS_ALMACEN.setText(null);
            DESCRIPCION_ESTATUS.setText(null);
            ID_AREA_ALMACEN.setText(null);
            DESCRIPCION_AREA_ALMACEN.setText(null);
            ID_COMPANIA.setText(null);
            DESCRIPCION_COMPANIA.setText(null);
            
            componentesAlIniciar();
        });
    }
    
    private void btnTipoAlmacen() {
        btnTipoAlmacen.addActionListener((ActionEvent e) -> {
             new M04_Lista_Tipo(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
    
    private void btnClaseAlmacen() {
        btnClaseAlmacen.addActionListener((ActionEvent e) -> {
             new M04_Lista_Clases(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
        
    private void btnEstatusAlmacen() {
        btnEstatusAlmacen.addActionListener((ActionEvent e) -> {
             new M04_Lista_Estatus(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
            
    private void btnArea() {
        btnArea.addActionListener((ActionEvent e) -> {
             new M04_Lista_Areas(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
                
    private void btnCompania() {
        btnCompania.addActionListener((ActionEvent e) -> {
             new M04_Lista_Empresas(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
    
    private void crear_Almacen() {

        if (!(CODIGO_ALMACEN.getText().isEmpty()
                | DESCRIPCION_ALMACEN.getText().isEmpty()
                | ID_TIPO_ALMACEN.getText().isEmpty()
                | DESCRIPCION_TIPO_ALMACEN.getText().isEmpty()
                | ID_CLASE_ALMACEN.getText().isEmpty()
                | DESCRIPCION_CLASE_ALMACEN.getText().isEmpty()
                | ID_ESTATUS_ALMACEN.getText().isEmpty()
                | DESCRIPCION_ESTATUS.getText().isEmpty()
                | ID_AREA_ALMACEN.getText().isEmpty()
                | DESCRIPCION_AREA_ALMACEN.getText().isEmpty())) {

            M04_Crear_Almacen nuevoAlmacen = new M04_Crear_Almacen();
            nuevoAlmacen.setCODIGO_ALMACEN(CODIGO_ALMACEN.getText());
            nuevoAlmacen.setDESCRIPCION_ALMACEN(DESCRIPCION_ALMACEN.getText());
            nuevoAlmacen.setID_TIPO_ALMACEN(ID_TIPO_ALMACEN.getText());
            nuevoAlmacen.setDESCRIPCION_TIPO_ALMACEN(DESCRIPCION_TIPO_ALMACEN.getText());
            nuevoAlmacen.setID_CLASE_ALMACEN(ID_CLASE_ALMACEN.getText());
            nuevoAlmacen.setDESCRIPCION_CLASE_ALMACEN(DESCRIPCION_CLASE_ALMACEN.getText());
            nuevoAlmacen.setID_ESTATUS_ALMACEN(ID_ESTATUS_ALMACEN.getText());
            nuevoAlmacen.setDESCRIPCION_ESTATUS(DESCRIPCION_ESTATUS.getText());
            nuevoAlmacen.setID_AREA_ALMACEN(ID_AREA_ALMACEN.getText());
            nuevoAlmacen.setDESCRIPCION_AREA_ALMACEN(DESCRIPCION_AREA_ALMACEN.getText());
            nuevoAlmacen.setID_COMPANIA(ID_COMPANIA.getText());
            nuevoAlmacen.setDESCRIPCION_COMPANIA(DESCRIPCION_COMPANIA.getText());
            nuevoAlmacen.crear_Almacen();

            btnGuardar.setEnabled(false);
            CODIGO_ALMACEN.setEditable(false);
            DESCRIPCION_ALMACEN.setEditable(false);

            if (nuevoAlmacen.getCODIGO_ERROR() == 1062) {

                JOptionPane.showMessageDialog(this,NOT.msg(NOT.DUPLICATE_RECORD),NOT.msg(NOT.TITLE),JOptionPane.WARNING_MESSAGE);
                
                btnGuardar.setEnabled(true);
                CODIGO_ALMACEN.setEditable(true);
                CODIGO_ALMACEN.setText(null);
                DESCRIPCION_ALMACEN.setText(null);
                ID_TIPO_ALMACEN.setText(null);
                DESCRIPCION_TIPO_ALMACEN.setText(null);
                ID_CLASE_ALMACEN.setText(null);
                DESCRIPCION_CLASE_ALMACEN.setText(null);
                ID_ESTATUS_ALMACEN.setText(null);
                DESCRIPCION_ESTATUS.setText(null);
                ID_AREA_ALMACEN.setText(null);
                DESCRIPCION_AREA_ALMACEN.setText(null);
                ID_COMPANIA.setText(null);
                DESCRIPCION_COMPANIA.setText(null);

            }
            
            componentesDespuesGuardar();

        } else {
           JOptionPane.showMessageDialog(this, NOT.msg(NOT.EMPTY_FIELDS),NOT.msg(NOT.TITLE),JOptionPane.ERROR_MESSAGE);
        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_M04 = new javax.swing.JMenuBar();
        MENU_CREACION_MATERIALES = new javax.swing.JMenu();
        MENU_ITEM_GUARDAR = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_NUEVO = new javax.swing.JMenuItem();
        MENU_ITEM_MODIFICAR = new javax.swing.JMenuItem();
        MENU_ITEM_VISUALIZAR = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_CAMPOS = new javax.swing.JMenu();
        MENU_ITEM_TIPO = new javax.swing.JMenuItem();
        MENU_ITEM_CLASE = new javax.swing.JMenuItem();
        MENU_ITEM_UM = new javax.swing.JMenuItem();
        MENU_ESTATUS = new javax.swing.JMenu();
        MENU_ITEM_ACTIVO = new javax.swing.JMenuItem();
        MENU_ITEM_INACTIVO = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_AREAS = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        MENU_ITEM_MANUAL = new javax.swing.JMenuItem();
        MENU_ITEM_ACERCA_DE = new javax.swing.JMenuItem();
        cintaControles = new javax.swing.JToolBar();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnGuardar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnNuevo = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        MULTITAB = new javax.swing.JTabbedPane();
        PESTANADATOSGENERALES = new javax.swing.JPanel();
        LABELCODIGOALMACEN = new javax.swing.JLabel();
        CODIGO_ALMACEN = new javax.swing.JTextField();
        LABELDESCRIPCIONALMACEN = new javax.swing.JLabel();
        DESCRIPCION_ALMACEN = new javax.swing.JTextField();
        LABELTIPOALMACEN = new javax.swing.JLabel();
        LABELCLASEALMACEN = new javax.swing.JLabel();
        LABELAREA = new javax.swing.JLabel();
        LABELESTATUS = new javax.swing.JLabel();
        ID_TIPO_ALMACEN = new javax.swing.JTextField();
        DESCRIPCION_TIPO_ALMACEN = new javax.swing.JTextField();
        btnTipoAlmacen = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnClaseAlmacen = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        ID_CLASE_ALMACEN = new javax.swing.JTextField();
        DESCRIPCION_CLASE_ALMACEN = new javax.swing.JTextField();
        btnEstatusAlmacen = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        ID_ESTATUS_ALMACEN = new javax.swing.JTextField();
        DESCRIPCION_ESTATUS = new javax.swing.JTextField();
        btnArea = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        ID_AREA_ALMACEN = new javax.swing.JTextField();
        DESCRIPCION_AREA_ALMACEN = new javax.swing.JTextField();
        LABELCOMPANIA = new javax.swing.JLabel();
        DESCRIPCION_COMPANIA = new javax.swing.JTextField();
        ID_COMPANIA = new javax.swing.JTextField();
        btnCompania = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        MENU_CREACION_MATERIALES.setText("Creacion de Almacen");

        MENU_ITEM_GUARDAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_GUARDAR.setText("Guardar Almacen");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_GUARDAR);
        MENU_CREACION_MATERIALES.add(jSeparator4);

        MENU_ITEM_NUEVO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_NUEVO.setText("Nuevo");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_NUEVO);

        MENU_ITEM_MODIFICAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_MODIFICAR.setText("Modificar");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_MODIFICAR);

        MENU_ITEM_VISUALIZAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_VISUALIZAR.setText("Visualizar");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_VISUALIZAR);
        MENU_CREACION_MATERIALES.add(jSeparator3);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_SALIR);

        menuBar_M04.add(MENU_CREACION_MATERIALES);

        MENU_CAMPOS.setText("Campos");

        MENU_ITEM_TIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_TIPO.setText("Tipo");
        MENU_CAMPOS.add(MENU_ITEM_TIPO);

        MENU_ITEM_CLASE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MENU_ITEM_CLASE.setText("Clase");
        MENU_CAMPOS.add(MENU_ITEM_CLASE);

        MENU_ITEM_UM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_UM.setText("Compañia");
        MENU_CAMPOS.add(MENU_ITEM_UM);

        MENU_ESTATUS.setText("Estatus");

        MENU_ITEM_ACTIVO.setText("Activo");
        MENU_ESTATUS.add(MENU_ITEM_ACTIVO);

        MENU_ITEM_INACTIVO.setText("Inactivo");
        MENU_ESTATUS.add(MENU_ITEM_INACTIVO);

        MENU_CAMPOS.add(MENU_ESTATUS);
        MENU_CAMPOS.add(jSeparator5);

        MENU_ITEM_AREAS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_AREAS.setText("Areas");
        MENU_CAMPOS.add(MENU_ITEM_AREAS);

        menuBar_M04.add(MENU_CAMPOS);

        MENU_AYUDA.setText("Ayuda");

        MENU_ITEM_MANUAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MENU_ITEM_MANUAL.setText("Manual de Instruccion");
        MENU_AYUDA.add(MENU_ITEM_MANUAL);

        MENU_ITEM_ACERCA_DE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        MENU_ITEM_ACERCA_DE.setText("Acerca de Almacenes");
        MENU_AYUDA.add(MENU_ITEM_ACERCA_DE);

        menuBar_M04.add(MENU_AYUDA);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        cintaControles.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cintaControles.setRollover(true);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnSalir);
        cintaControles.add(jSeparator2);

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setText("Modificar");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cintaControles.add(btnGuardar);

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setFocusable(false);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnNuevo);
        cintaControles.add(jSeparator1);

        MULTITAB.setForeground(new java.awt.Color(102, 102, 102));
        MULTITAB.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        PESTANADATOSGENERALES.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PESTANADATOSGENERALES.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        LABELCODIGOALMACEN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCODIGOALMACEN.setText("Codigo Almacén:");

        CODIGO_ALMACEN.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        LABELDESCRIPCIONALMACEN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELDESCRIPCIONALMACEN.setText("Descripción :");

        LABELTIPOALMACEN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELTIPOALMACEN.setText("Tipo :");

        LABELCLASEALMACEN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCLASEALMACEN.setText("Clase :");

        LABELAREA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELAREA.setText("Area :");

        LABELESTATUS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELESTATUS.setText("Estatus:");

        ID_TIPO_ALMACEN.setEditable(false);

        DESCRIPCION_TIPO_ALMACEN.setEditable(false);

        ID_CLASE_ALMACEN.setEditable(false);

        DESCRIPCION_CLASE_ALMACEN.setEditable(false);

        ID_ESTATUS_ALMACEN.setEditable(false);

        DESCRIPCION_ESTATUS.setEditable(false);

        ID_AREA_ALMACEN.setEditable(false);

        DESCRIPCION_AREA_ALMACEN.setEditable(false);

        LABELCOMPANIA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCOMPANIA.setText("Compañia :");

        DESCRIPCION_COMPANIA.setEditable(false);

        ID_COMPANIA.setEditable(false);

        javax.swing.GroupLayout PESTANADATOSGENERALESLayout = new javax.swing.GroupLayout(PESTANADATOSGENERALES);
        PESTANADATOSGENERALES.setLayout(PESTANADATOSGENERALESLayout);
        PESTANADATOSGENERALESLayout.setHorizontalGroup(
            PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PESTANADATOSGENERALESLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LABELCODIGOALMACEN)
                    .addComponent(LABELDESCRIPCIONALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELTIPOALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELCLASEALMACEN)
                    .addComponent(LABELESTATUS)
                    .addComponent(LABELAREA)
                    .addComponent(LABELCOMPANIA))
                .addGap(18, 18, 18)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CODIGO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PESTANADATOSGENERALESLayout.createSequentialGroup()
                        .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ID_TIPO_ALMACEN)
                            .addComponent(ID_CLASE_ALMACEN)
                            .addComponent(ID_ESTATUS_ALMACEN)
                            .addComponent(ID_AREA_ALMACEN)
                            .addComponent(ID_COMPANIA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PESTANADATOSGENERALESLayout.createSequentialGroup()
                                .addComponent(DESCRIPCION_COMPANIA, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCompania, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PESTANADATOSGENERALESLayout.createSequentialGroup()
                                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(DESCRIPCION_TIPO_ALMACEN)
                                    .addComponent(DESCRIPCION_AREA_ALMACEN)
                                    .addComponent(DESCRIPCION_CLASE_ALMACEN, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DESCRIPCION_ESTATUS))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnClaseAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnEstatusAlmacen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnTipoAlmacen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(DESCRIPCION_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(298, Short.MAX_VALUE))
        );
        PESTANADATOSGENERALESLayout.setVerticalGroup(
            PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PESTANADATOSGENERALESLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCODIGOALMACEN)
                    .addComponent(CODIGO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELDESCRIPCIONALMACEN)
                    .addComponent(DESCRIPCION_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELTIPOALMACEN)
                    .addComponent(ID_TIPO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_TIPO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTipoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCLASEALMACEN)
                    .addComponent(ID_CLASE_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_CLASE_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClaseAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELESTATUS)
                    .addComponent(ID_ESTATUS_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_ESTATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstatusAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELAREA)
                    .addComponent(ID_AREA_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_AREA_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCOMPANIA)
                    .addComponent(ID_COMPANIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_COMPANIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCompania, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Datos Generales", PESTANADATOSGENERALES);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(188, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
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
    protected static javax.swing.JTextField CODIGO_ALMACEN;
    protected static javax.swing.JTextField DESCRIPCION_ALMACEN;
    protected static javax.swing.JTextField DESCRIPCION_AREA_ALMACEN;
    protected static javax.swing.JTextField DESCRIPCION_CLASE_ALMACEN;
    protected static javax.swing.JTextField DESCRIPCION_COMPANIA;
    protected static javax.swing.JTextField DESCRIPCION_ESTATUS;
    protected static javax.swing.JTextField DESCRIPCION_TIPO_ALMACEN;
    protected static javax.swing.JTextField ID_AREA_ALMACEN;
    protected static javax.swing.JTextField ID_CLASE_ALMACEN;
    protected static javax.swing.JTextField ID_COMPANIA;
    protected static javax.swing.JTextField ID_ESTATUS_ALMACEN;
    protected static javax.swing.JTextField ID_TIPO_ALMACEN;
    public static javax.swing.JLabel LABELAREA;
    public static javax.swing.JLabel LABELCLASEALMACEN;
    public static javax.swing.JLabel LABELCODIGOALMACEN;
    public static javax.swing.JLabel LABELCOMPANIA;
    public static javax.swing.JLabel LABELDESCRIPCIONALMACEN;
    public static javax.swing.JLabel LABELESTATUS;
    public static javax.swing.JLabel LABELTIPOALMACEN;
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_CAMPOS;
    public static javax.swing.JMenu MENU_CREACION_MATERIALES;
    public static javax.swing.JMenu MENU_ESTATUS;
    private javax.swing.JMenuItem MENU_ITEM_ACERCA_DE;
    public static javax.swing.JMenuItem MENU_ITEM_ACTIVO;
    public static javax.swing.JMenuItem MENU_ITEM_AREAS;
    public static javax.swing.JMenuItem MENU_ITEM_CLASE;
    public static javax.swing.JMenuItem MENU_ITEM_GUARDAR;
    public static javax.swing.JMenuItem MENU_ITEM_INACTIVO;
    private javax.swing.JMenuItem MENU_ITEM_MANUAL;
    public static javax.swing.JMenuItem MENU_ITEM_MODIFICAR;
    public static javax.swing.JMenuItem MENU_ITEM_NUEVO;
    public static javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenuItem MENU_ITEM_TIPO;
    public static javax.swing.JMenuItem MENU_ITEM_UM;
    public static javax.swing.JMenuItem MENU_ITEM_VISUALIZAR;
    public static javax.swing.JTabbedPane MULTITAB;
    private javax.swing.JPanel PESTANADATOSGENERALES;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btnArea;
    private javax.swing.JButton btnClaseAlmacen;
    private javax.swing.JButton btnCompania;
    private javax.swing.JButton btnEstatusAlmacen;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTipoAlmacen;
    private javax.swing.JToolBar cintaControles;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    public static javax.swing.JMenuBar menuBar_M04;
    private javax.swing.JScrollPane panelScroll;
    // End of variables declaration//GEN-END:variables
}
