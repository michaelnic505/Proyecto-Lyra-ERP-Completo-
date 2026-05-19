package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.creation;

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

public class M01_Creacion_Materiales extends javax.swing.JPanel {

    protected static String ESTRATEGIA;
    
    public M01_Creacion_Materiales() {
        
        initComponents(); 
        addEvents();
        componentesAlIniciar();
        
    }

    protected static void componentesAlIniciar(){
      
        btnSalir.setEnabled(true);
        btnGuardar.setEnabled(true);        
    }
    
    protected static void componentesDespuesGuardar(){
       
        btnSalir.setEnabled(true);
        btnGuardar.setEnabled(false);       

    }
    
    private void addEvents(){
        botonSalir();
        botonCrear();
        botonNuevo(); 
        btnTipoMaterial();
        btnClaseMaterial();
        btnUnidadMedida();
        btnEstatusMaterial();
        btnAreaMaterial();
        
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
             crear_Material(); 
        });
    }
    
    private void botonNuevo() {
        btnNuevo.addActionListener((ActionEvent e) -> {
          
        });
    }
    
    private void btnTipoMaterial() {
        btnTipoMaterial.addActionListener((ActionEvent e) -> {
          new M01_Lista_Tipo_Material(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
        
    private void btnClaseMaterial() {
        btnClaseMaterial.addActionListener((ActionEvent e) -> {
          new M01_Lista_Clase_Material(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
            
    private void btnUnidadMedida() {
        btnUnidadMedida.addActionListener((ActionEvent e) -> {
          new M01_Lista_UM(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
                
    private void btnEstatusMaterial() {
        btnEstatusMaterial.addActionListener((ActionEvent e) -> {
          new M01_Lista_Estatus(new javax.swing.JFrame(), true).setVisible(true);
        });
    }
                    
    private void btnAreaMaterial() {
        btnAreaMaterial.addActionListener((ActionEvent e) -> {
          new M01_Lista_Areas(new javax.swing.JFrame(), true).setVisible(true);           
        });
    }
    
    private void crear_Material() {

        if (!(DESCRIPCION_MATERIAL.getText().isEmpty()
                | ID_TIPO_MATERIAL.getText().isEmpty()
                | DESCRIPCION_TIPO_MATERIAL.getText().isEmpty()
                | ID_CLASE_MATERIAL.getText().isEmpty()
                | DESCRIPCION_CLASE_MATERIAL.getText().isEmpty()
                | ID_UM.getText().isEmpty()
                | DESCRIPCION_UM.getText().isEmpty()
                | MARCA.getText().isEmpty()
                | MODELO.getText().isEmpty()
                | SERIE.getText().isEmpty()
                | ID_ESTATUS_MATERIAL.getText().isEmpty()
                | DESCRIPCION_ESTATUS.getText().isEmpty()
                | ID_AREA_MATERIAL.getText().isEmpty()
                | DESCRIPCION_AREA_MATERIAL.getText().isEmpty())) {

            M01_Crear_Material nuevoMaterial = new M01_Crear_Material();
            nuevoMaterial.setDESCRIPCION_MATERIAL(DESCRIPCION_MATERIAL.getText());
            nuevoMaterial.setID_TIPO_MATERIAL(ID_TIPO_MATERIAL.getText());
            nuevoMaterial.setDESCRIPCION_TIPO_MATERIAL(DESCRIPCION_TIPO_MATERIAL.getText());
            nuevoMaterial.setID_CLASE_MATERIAL(ID_CLASE_MATERIAL.getText());
            nuevoMaterial.setDESCRIPCION_CLASE_MATERIAL(DESCRIPCION_CLASE_MATERIAL.getText());
            nuevoMaterial.setID_UM(ID_UM.getText());
            nuevoMaterial.setDESCRIPCION_UM(DESCRIPCION_UM.getText());
            nuevoMaterial.setMARCA(MARCA.getText());
            nuevoMaterial.setMODELO(MODELO.getText());
            nuevoMaterial.setSERIE(SERIE.getText());
            nuevoMaterial.setID_ESTATUS_MATERIAL(ID_ESTATUS_MATERIAL.getText());
            nuevoMaterial.setDESCRIPCION_ESTATUS(DESCRIPCION_ESTATUS.getText());
            nuevoMaterial.setID_AREA_MATERIAL(ID_AREA_MATERIAL.getText());
            nuevoMaterial.setDESCRIPCION_AREA_MATERIAL(DESCRIPCION_AREA_MATERIAL.getText());
            nuevoMaterial.setFECHA_FAB(FECHA_FABRICACION.getText());
            nuevoMaterial.Crear_Material();

            CODIGO_MATERIAL.setText(nuevoMaterial.getCODIGO_MATERIAL());

            DESCRIPCION_MATERIAL.setEditable(false);
            MARCA.setEditable(false);
            MODELO.setEditable(false);
            SERIE.setEditable(false);
            btnAreaMaterial.setEnabled(false);
            btnClaseMaterial.setEnabled(false);
            btnEstatusMaterial.setEnabled(false);
            btnTipoMaterial.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnUnidadMedida.setEnabled(false);
            
        } else {
            JOptionPane.showMessageDialog(this, NOT.msg(NOT.EMPTY_FIELDS),NOT.msg(NOT.TITLE),JOptionPane.ERROR_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_M01 = new javax.swing.JMenuBar();
        MENU_CREACION_MATERIALES = new javax.swing.JMenu();
        MENU_ITEM_GUARDAR = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_NUEVO_MATERIAL = new javax.swing.JMenuItem();
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
        btnSalir = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        MULTITAB = new javax.swing.JTabbedPane();
        PESTANADATOSGENERALES1 = new javax.swing.JPanel();
        LABELCODIGOMATERIAL = new javax.swing.JLabel();
        CODIGO_MATERIAL = new javax.swing.JTextField();
        LABELDESCRIPCIONMATERIAL = new javax.swing.JLabel();
        DESCRIPCION_MATERIAL = new javax.swing.JTextField();
        LABELAREAMATERIAL = new javax.swing.JLabel();
        LABELCRITICIDAD = new javax.swing.JLabel();
        LABELAREA = new javax.swing.JLabel();
        LABELESTATUS = new javax.swing.JLabel();
        LABELMARCA = new javax.swing.JLabel();
        MARCA = new javax.swing.JTextField();
        LABELMODELO = new javax.swing.JLabel();
        MODELO = new javax.swing.JTextField();
        LABELSERIE = new javax.swing.JLabel();
        SERIE = new javax.swing.JTextField();
        LABELFECHAFABRICACION = new javax.swing.JLabel();
        FECHA_FABRICACION = new javax.swing.JTextField();
        ID_TIPO_MATERIAL = new javax.swing.JTextField();
        DESCRIPCION_TIPO_MATERIAL = new javax.swing.JTextField();
        btnTipoMaterial = new javax.swing.JButton();
        btnClaseMaterial = new javax.swing.JButton();
        ID_CLASE_MATERIAL = new javax.swing.JTextField();
        DESCRIPCION_CLASE_MATERIAL = new javax.swing.JTextField();
        btnEstatusMaterial = new javax.swing.JButton();
        ID_ESTATUS_MATERIAL = new javax.swing.JTextField();
        DESCRIPCION_ESTATUS = new javax.swing.JTextField();
        btnAreaMaterial = new javax.swing.JButton();
        ID_AREA_MATERIAL = new javax.swing.JTextField();
        DESCRIPCION_AREA_MATERIAL = new javax.swing.JTextField();
        LABELCRITICIDAD3 = new javax.swing.JLabel();
        ID_UM = new javax.swing.JTextField();
        DESCRIPCION_UM = new javax.swing.JTextField();
        btnUnidadMedida = new javax.swing.JButton();

        MENU_CREACION_MATERIALES.setText("Creacion de Materiales");

        MENU_ITEM_GUARDAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_GUARDAR.setText("Guardar Material");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_GUARDAR);
        MENU_CREACION_MATERIALES.add(jSeparator4);

        MENU_ITEM_NUEVO_MATERIAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_NUEVO_MATERIAL.setText("Nuevo Material");
        MENU_CREACION_MATERIALES.add(MENU_ITEM_NUEVO_MATERIAL);

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

        menuBar_M01.add(MENU_CREACION_MATERIALES);

        MENU_CAMPOS.setText("Campos");

        MENU_ITEM_TIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_TIPO.setText("Tipo");
        MENU_CAMPOS.add(MENU_ITEM_TIPO);

        MENU_ITEM_CLASE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MENU_ITEM_CLASE.setText("Clase");
        MENU_CAMPOS.add(MENU_ITEM_CLASE);

        MENU_ITEM_UM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_UM.setText("Unidades de medida");
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

        menuBar_M01.add(MENU_CAMPOS);

        MENU_AYUDA.setText("Ayuda");

        MENU_ITEM_MANUAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MENU_ITEM_MANUAL.setText("Manual de Instruccion");
        MENU_AYUDA.add(MENU_ITEM_MANUAL);

        MENU_ITEM_ACERCA_DE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        MENU_ITEM_ACERCA_DE.setText("Acerca de Materiales");
        MENU_AYUDA.add(MENU_ITEM_ACERCA_DE);

        menuBar_M01.add(MENU_AYUDA);

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

        PESTANADATOSGENERALES1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PESTANADATOSGENERALES1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        LABELCODIGOMATERIAL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCODIGOMATERIAL.setText("Codigo Material:");

        CODIGO_MATERIAL.setEditable(false);
        CODIGO_MATERIAL.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        LABELDESCRIPCIONMATERIAL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELDESCRIPCIONMATERIAL.setText("Descripción :");

        LABELAREAMATERIAL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELAREAMATERIAL.setText("Tipo Material :");

        LABELCRITICIDAD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCRITICIDAD.setText("Clase Material :");

        LABELAREA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELAREA.setText("Area :");

        LABELESTATUS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELESTATUS.setText("Estatus:");

        LABELMARCA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELMARCA.setText("Marca:");

        LABELMODELO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELMODELO.setText("Modelo:");

        LABELSERIE.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELSERIE.setText("Serie:");

        LABELFECHAFABRICACION.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELFECHAFABRICACION.setText("Fecha fab.:");

        ID_TIPO_MATERIAL.setEditable(false);

        DESCRIPCION_TIPO_MATERIAL.setEditable(false);

        ID_CLASE_MATERIAL.setEditable(false);

        DESCRIPCION_CLASE_MATERIAL.setEditable(false);

        ID_ESTATUS_MATERIAL.setEditable(false);

        DESCRIPCION_ESTATUS.setEditable(false);

        ID_AREA_MATERIAL.setEditable(false);

        DESCRIPCION_AREA_MATERIAL.setEditable(false);

        LABELCRITICIDAD3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCRITICIDAD3.setText("Unidad Medida :");

        ID_UM.setEditable(false);

        DESCRIPCION_UM.setEditable(false);

        javax.swing.GroupLayout PESTANADATOSGENERALES1Layout = new javax.swing.GroupLayout(PESTANADATOSGENERALES1);
        PESTANADATOSGENERALES1.setLayout(PESTANADATOSGENERALES1Layout);
        PESTANADATOSGENERALES1Layout.setHorizontalGroup(
            PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PESTANADATOSGENERALES1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LABELCRITICIDAD3)
                    .addComponent(LABELCODIGOMATERIAL)
                    .addComponent(LABELCRITICIDAD)
                    .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(LABELAREAMATERIAL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LABELDESCRIPCIONMATERIAL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(LABELMARCA)
                    .addComponent(LABELMODELO)
                    .addComponent(LABELSERIE))
                .addGap(18, 18, 18)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MARCA, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MODELO, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PESTANADATOSGENERALES1Layout.createSequentialGroup()
                        .addComponent(ID_UM, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DESCRIPCION_UM, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SERIE, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PESTANADATOSGENERALES1Layout.createSequentialGroup()
                        .addComponent(ID_TIPO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DESCRIPCION_TIPO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PESTANADATOSGENERALES1Layout.createSequentialGroup()
                        .addComponent(ID_CLASE_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DESCRIPCION_CLASE_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CODIGO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PESTANADATOSGENERALES1Layout.createSequentialGroup()
                        .addComponent(btnTipoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LABELFECHAFABRICACION)
                            .addComponent(LABELAREA)
                            .addComponent(LABELESTATUS))
                        .addGap(18, 18, 18)
                        .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PESTANADATOSGENERALES1Layout.createSequentialGroup()
                                .addComponent(ID_ESTATUS_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DESCRIPCION_ESTATUS, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEstatusMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PESTANADATOSGENERALES1Layout.createSequentialGroup()
                                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(FECHA_FABRICACION, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PESTANADATOSGENERALES1Layout.createSequentialGroup()
                                        .addComponent(ID_AREA_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DESCRIPCION_AREA_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAreaMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnClaseMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        PESTANADATOSGENERALES1Layout.setVerticalGroup(
            PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PESTANADATOSGENERALES1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCODIGOMATERIAL)
                    .addComponent(CODIGO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELESTATUS)
                    .addComponent(ID_ESTATUS_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_ESTATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstatusMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELDESCRIPCIONMATERIAL)
                    .addComponent(DESCRIPCION_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELAREA)
                    .addComponent(ID_AREA_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_AREA_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAreaMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELAREAMATERIAL)
                    .addComponent(ID_TIPO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTipoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELFECHAFABRICACION)
                    .addComponent(FECHA_FABRICACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_TIPO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCRITICIDAD)
                    .addComponent(ID_CLASE_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_CLASE_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClaseMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCRITICIDAD3)
                    .addComponent(ID_UM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_UM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELMODELO)
                    .addComponent(MODELO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELSERIE)
                    .addComponent(SERIE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(PESTANADATOSGENERALES1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELMARCA)
                    .addComponent(MARCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );

        MULTITAB.addTab("Datos Generales", PESTANADATOSGENERALES1);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(MULTITAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        panelScroll.setViewportView(bodyPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cintaControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelScroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cintaControles, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CODIGO_MATERIAL;
    protected static javax.swing.JTextField DESCRIPCION_AREA_MATERIAL;
    protected static javax.swing.JTextField DESCRIPCION_CLASE_MATERIAL;
    protected static javax.swing.JTextField DESCRIPCION_ESTATUS;
    private javax.swing.JTextField DESCRIPCION_MATERIAL;
    protected static javax.swing.JTextField DESCRIPCION_TIPO_MATERIAL;
    protected static javax.swing.JTextField DESCRIPCION_UM;
    private javax.swing.JTextField FECHA_FABRICACION;
    protected static javax.swing.JTextField ID_AREA_MATERIAL;
    protected static javax.swing.JTextField ID_CLASE_MATERIAL;
    protected static javax.swing.JTextField ID_ESTATUS_MATERIAL;
    protected static javax.swing.JTextField ID_TIPO_MATERIAL;
    protected static javax.swing.JTextField ID_UM;
    public static javax.swing.JLabel LABELAREA;
    public static javax.swing.JLabel LABELAREAMATERIAL;
    public static javax.swing.JLabel LABELCODIGOMATERIAL;
    public static javax.swing.JLabel LABELCRITICIDAD;
    public static javax.swing.JLabel LABELCRITICIDAD3;
    public static javax.swing.JLabel LABELDESCRIPCIONMATERIAL;
    public static javax.swing.JLabel LABELESTATUS;
    public static javax.swing.JLabel LABELFECHAFABRICACION;
    public static javax.swing.JLabel LABELMARCA;
    public static javax.swing.JLabel LABELMODELO;
    public static javax.swing.JLabel LABELSERIE;
    private javax.swing.JTextField MARCA;
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
    public static javax.swing.JMenuItem MENU_ITEM_NUEVO_MATERIAL;
    public static javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenuItem MENU_ITEM_TIPO;
    public static javax.swing.JMenuItem MENU_ITEM_UM;
    public static javax.swing.JMenuItem MENU_ITEM_VISUALIZAR;
    private javax.swing.JTextField MODELO;
    public static javax.swing.JTabbedPane MULTITAB;
    private javax.swing.JPanel PESTANADATOSGENERALES1;
    private javax.swing.JTextField SERIE;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btnAreaMaterial;
    private javax.swing.JButton btnClaseMaterial;
    private javax.swing.JButton btnEstatusMaterial;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTipoMaterial;
    private javax.swing.JButton btnUnidadMedida;
    private javax.swing.JToolBar cintaControles;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    public static javax.swing.JMenuBar menuBar_M01;
    private javax.swing.JScrollPane panelScroll;
    // End of variables declaration//GEN-END:variables
}
