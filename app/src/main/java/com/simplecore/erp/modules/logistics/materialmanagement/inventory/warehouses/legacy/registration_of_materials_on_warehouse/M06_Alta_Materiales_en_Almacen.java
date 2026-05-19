package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.registration_of_materials_on_warehouse;

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

public class M06_Alta_Materiales_en_Almacen extends javax.swing.JPanel {

    protected static String ESTRATEGIA;   
    M06_Lista_Materiales listaMateriales;
    M06_Lista_Almacenes listaAlmacenes;
    
    public M06_Alta_Materiales_en_Almacen() {
        
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
        botonGuardar();   
        botonBuscarMaterial();
        botonBuscarAlmacen();
        
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
    
    private void botonGuardar() {
        btnGuardar.addActionListener((ActionEvent e) -> {
            crear_Alta_Material();
        });
    }
    
    private void botonBuscarMaterial() {
        btnBuscarMaterial.addActionListener((ActionEvent e) -> {
            listaMateriales = new  M06_Lista_Materiales();
            PanelLoader.loadPanel(listaMateriales, mainContainerPanel);
        });
    }
    
    private void botonBuscarAlmacen() {
        btnBuscarAlmacen.addActionListener((ActionEvent e) -> {
            listaAlmacenes = new  M06_Lista_Almacenes();
            PanelLoader.loadPanel(listaAlmacenes, mainContainerPanel);
        });
    }
    
    private void crear_Alta_Material() {

        if (!(CODIGO_MATERIAL.getText().isEmpty()
                | DESCRIPCION_MATERIAL.getText().isEmpty()
                | SERIE.getText().isEmpty()
                | DESCRIPCION_ALMACEN.getText().isEmpty()
                | PRECIO_UNITARIO.getText().isEmpty())) {

            M06_Crear_Alta_Material nuevaAlta = new M06_Crear_Alta_Material();
            nuevaAlta.setCODIGO_MATERIAL(CODIGO_MATERIAL.getText());
            nuevaAlta.setDESCRIPCION_MATERIAL(DESCRIPCION_MATERIAL.getText());
            nuevaAlta.setCODIGO_ALMACEN(CODIGO_ALMACEN.getText());
            nuevaAlta.setDESCRIPCION_ALMACEN(DESCRIPCION_ALMACEN.getText());
            nuevaAlta.setPRECIO_UNITARIO(PRECIO_UNITARIO.getText());
            nuevaAlta.setID_UM(ID_UM.getText());
            nuevaAlta.setDESCRIPCION_UM(DESCRIPCION_UM.getText());
            nuevaAlta.setMARCA(MARCA.getText());
            nuevaAlta.setSERIE(SERIE.getText());
            nuevaAlta.setMODELO(MODELO.getText());
            nuevaAlta.crear_Alta_Material();

            if (nuevaAlta.getRESULTADO() == 1062) {
                
                CODIGO_MATERIAL.requestFocus();
                JOptionPane.showMessageDialog(this, NOT.msg(NOT.DUPLICATE_RECORD),NOT.msg(NOT.TITLE),JOptionPane.ERROR_MESSAGE);
                

            } else {
                NUM_REGISTRO.setText(nuevaAlta.getNUMERO_ALTA());
                NUM_REGISTRO.setEditable(false);
                PRECIO_UNITARIO.setEditable(false);
                btnBuscarMaterial.setEnabled(false);
                btnBuscarAlmacen.setEnabled(false);
            }

        } else {
            JOptionPane.showMessageDialog(this, NOT.msg(NOT.OPERATION_COMPLETED),NOT.msg(NOT.TITLE), JOptionPane.INFORMATION_MESSAGE);
        }

    }


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_M06 = new javax.swing.JMenuBar();
        MENU_CREACION_MATERIALES = new javax.swing.JMenu();
        MENU_ITEM_GUARDAR = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_NUEVO = new javax.swing.JMenuItem();
        MENU_ITEM_MODIFICAR = new javax.swing.JMenuItem();
        MENU_ITEM_VISUALIZAR = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_CAMPOS = new javax.swing.JMenu();
        MENU_ITEM_BUSCAR_MATERIAL = new javax.swing.JMenuItem();
        MENU_ITEM_BUSCAR_ALMACEN = new javax.swing.JMenuItem();
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
        LABELCODIGOMATERIAL = new javax.swing.JLabel();
        CODIGO_MATERIAL = new javax.swing.JTextField();
        LABELDESCRIPCIONMATERIAL = new javax.swing.JLabel();
        DESCRIPCION_MATERIAL = new javax.swing.JTextField();
        btnBuscarMaterial = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        LABELALMACEN = new javax.swing.JLabel();
        SERIE = new javax.swing.JTextField();
        btnBuscarAlmacen = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        LABELDESCRIPCIONALMACEN = new javax.swing.JLabel();
        DESCRIPCION_ALMACEN = new javax.swing.JTextField();
        PRECIO_UNITARIO = new javax.swing.JTextField();
        LABELPRECIOUNITARIO = new javax.swing.JLabel();
        ID_UM = new javax.swing.JTextField();
        LABELUM = new javax.swing.JLabel();
        DESCRIPCION_UM = new javax.swing.JTextField();
        LABELALTA = new javax.swing.JLabel();
        NUM_REGISTRO = new javax.swing.JTextField();
        LABELSERIE = new javax.swing.JLabel();
        CODIGO_ALMACEN = new javax.swing.JTextField();
        LABELMODELO = new javax.swing.JLabel();
        MODELO = new javax.swing.JTextField();
        LABELMARCA = new javax.swing.JLabel();
        MARCA = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        MENU_CREACION_MATERIALES.setText("Alta de Materiales");

        MENU_ITEM_GUARDAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_GUARDAR.setText("Guardar");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_GUARDAR);
        MENU_CREACION_MATERIALES.add(jSeparator5);

        MENU_ITEM_NUEVO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_NUEVO.setText("Nuevo");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_NUEVO);

        MENU_ITEM_MODIFICAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_MODIFICAR.setText("Modificar");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_MODIFICAR);

        MENU_ITEM_VISUALIZAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_VISUALIZAR.setText("Visualizar");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_VISUALIZAR);
        MENU_CREACION_MATERIALES.add(jSeparator6);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_SALIR);

        menuBar_M06.add(MENU_CREACION_MATERIALES);

        MENU_CAMPOS.setText("Campos");

        MENU_ITEM_BUSCAR_MATERIAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_BUSCAR_MATERIAL.setText("Buscar Material");
        MENU_CAMPOS.add(MENU_ITEM_BUSCAR_MATERIAL);

        MENU_ITEM_BUSCAR_ALMACEN.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MENU_ITEM_BUSCAR_ALMACEN.setText("Buscar Almacen");
        MENU_CAMPOS.add(MENU_ITEM_BUSCAR_ALMACEN);

        menuBar_M06.add(MENU_CAMPOS);

        MENU_AYUDA.setText("Ayuda");

        MENU_ITEM_MANUAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MENU_ITEM_MANUAL.setText("Manual de Instruccion");
        MENU_AYUDA.add(MENU_ITEM_MANUAL);

        MENU_ITEM_ACERCA_DE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        MENU_ITEM_ACERCA_DE.setText("Acerca de Altas");
        MENU_AYUDA.add(MENU_ITEM_ACERCA_DE);

        menuBar_M06.add(MENU_AYUDA);

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
        btnGuardar.setText("Crear");
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

        LABELCODIGOMATERIAL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCODIGOMATERIAL.setText("Codigo Material :");

        LABELDESCRIPCIONMATERIAL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELDESCRIPCIONMATERIAL.setText("Descripción Material :");

        DESCRIPCION_MATERIAL.setEditable(false);

        btnBuscarMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMaterialActionPerformed(evt);
            }
        });

        LABELALMACEN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELALMACEN.setText("Almacén :");

        LABELDESCRIPCIONALMACEN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELDESCRIPCIONALMACEN.setText("Descripción Almacén :");

        DESCRIPCION_ALMACEN.setEditable(false);

        LABELPRECIOUNITARIO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELPRECIOUNITARIO.setText("Precio Unit :");

        ID_UM.setEditable(false);

        LABELUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELUM.setText("UM :");

        DESCRIPCION_UM.setEditable(false);

        LABELALTA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELALTA.setText("Registro Alta:");

        NUM_REGISTRO.setEditable(false);

        LABELSERIE.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELSERIE.setText("Serie :");

        LABELMODELO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELMODELO.setText("Modelo :");

        LABELMARCA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELMARCA.setText("Marca :");

        javax.swing.GroupLayout PESTANADATOSGENERALESLayout = new javax.swing.GroupLayout(PESTANADATOSGENERALES);
        PESTANADATOSGENERALES.setLayout(PESTANADATOSGENERALESLayout);
        PESTANADATOSGENERALESLayout.setHorizontalGroup(
            PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PESTANADATOSGENERALESLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PESTANADATOSGENERALESLayout.createSequentialGroup()
                        .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LABELPRECIOUNITARIO)
                            .addComponent(LABELDESCRIPCIONALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LABELALMACEN)
                            .addComponent(LABELMODELO)
                            .addComponent(LABELSERIE)
                            .addComponent(LABELMARCA)
                            .addComponent(LABELUM)
                            .addComponent(LABELDESCRIPCIONMATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LABELCODIGOMATERIAL)
                            .addComponent(LABELALTA))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(DESCRIPCION_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(DESCRIPCION_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PESTANADATOSGENERALESLayout.createSequentialGroup()
                                    .addComponent(ID_UM, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(DESCRIPCION_UM, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(PRECIO_UNITARIO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PESTANADATOSGENERALESLayout.createSequentialGroup()
                                    .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(CODIGO_MATERIAL, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(NUM_REGISTRO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PESTANADATOSGENERALESLayout.createSequentialGroup()
                                    .addComponent(CODIGO_ALMACEN)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(114, 114, 114)))
                            .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(MODELO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(SERIE, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(MARCA, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 317, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PESTANADATOSGENERALESLayout.setVerticalGroup(
            PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PESTANADATOSGENERALESLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELALTA)
                    .addComponent(NUM_REGISTRO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCODIGOMATERIAL)
                    .addComponent(CODIGO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELDESCRIPCIONMATERIAL)
                    .addComponent(DESCRIPCION_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELUM)
                    .addComponent(ID_UM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_UM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELMARCA)
                    .addComponent(MARCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELSERIE)
                    .addComponent(SERIE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELMODELO)
                    .addComponent(MODELO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELALMACEN)
                    .addComponent(CODIGO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DESCRIPCION_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELDESCRIPCIONALMACEN))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(PRECIO_UNITARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELPRECIOUNITARIO))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Datos Generales", PESTANADATOSGENERALES);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
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

    private void btnBuscarMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarMaterialActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JTextField CODIGO_ALMACEN;
    protected static javax.swing.JTextField CODIGO_MATERIAL;
    protected static javax.swing.JTextField DESCRIPCION_ALMACEN;
    protected static javax.swing.JTextField DESCRIPCION_MATERIAL;
    protected static javax.swing.JTextField DESCRIPCION_UM;
    protected static javax.swing.JTextField ID_UM;
    public static javax.swing.JLabel LABELALMACEN;
    public static javax.swing.JLabel LABELALTA;
    public static javax.swing.JLabel LABELCODIGOMATERIAL;
    public static javax.swing.JLabel LABELDESCRIPCIONALMACEN;
    public static javax.swing.JLabel LABELDESCRIPCIONMATERIAL;
    public static javax.swing.JLabel LABELMARCA;
    public static javax.swing.JLabel LABELMODELO;
    public static javax.swing.JLabel LABELPRECIOUNITARIO;
    public static javax.swing.JLabel LABELSERIE;
    public static javax.swing.JLabel LABELUM;
    protected static javax.swing.JTextField MARCA;
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_CAMPOS;
    public static javax.swing.JMenu MENU_CREACION_MATERIALES;
    private javax.swing.JMenuItem MENU_ITEM_ACERCA_DE;
    public static javax.swing.JMenuItem MENU_ITEM_BUSCAR_ALMACEN;
    public static javax.swing.JMenuItem MENU_ITEM_BUSCAR_MATERIAL;
    public static javax.swing.JMenuItem MENU_ITEM_GUARDAR;
    private javax.swing.JMenuItem MENU_ITEM_MANUAL;
    public static javax.swing.JMenuItem MENU_ITEM_MODIFICAR;
    public static javax.swing.JMenuItem MENU_ITEM_NUEVO;
    public static javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenuItem MENU_ITEM_VISUALIZAR;
    protected static javax.swing.JTextField MODELO;
    public static javax.swing.JTabbedPane MULTITAB;
    protected static javax.swing.JTextField NUM_REGISTRO;
    private javax.swing.JPanel PESTANADATOSGENERALES;
    private javax.swing.JTextField PRECIO_UNITARIO;
    protected static javax.swing.JTextField SERIE;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btnBuscarAlmacen;
    protected static javax.swing.JButton btnBuscarMaterial;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnSalir;
    private javax.swing.JToolBar cintaControles;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    public static javax.swing.JMenuBar menuBar_M06;
    private javax.swing.JScrollPane panelScroll;
    // End of variables declaration//GEN-END:variables
}
