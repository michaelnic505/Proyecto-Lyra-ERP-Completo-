package com.simplecore.erp.modules.system.users;

import com.simplecore.erp.modules.system.users.utils.AU1_Gestion_de_Usuarios_SQL;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.config.database.utils.ClearTable;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.config.database.DatabaseTables;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class AU1_Administracion_Usuarios extends javax.swing.JPanel {
   
    String VistaSQL;
    AU1_Editar_Usuario edicionUsuario;

    public AU1_Administracion_Usuarios() {
        initComponents();
        addEvents();
        cargarDatosUsuarios();
    }

    
    private void cargarDatosUsuarios(){
        
        AU1_Cargar_Datos_Usuarios nuevaCarga = new AU1_Cargar_Datos_Usuarios();
        nuevaCarga.setTABLA_SQL(DatabaseTables.USERS_SYSTEM.tableName());
        nuevaCarga.setJTABLE(usuariosTabla);
        nuevaCarga.cargar_Datos_Usuarios();        
        Tabla_Formato.tablaNoEditable(usuariosTabla,10);        
    }
    
    private void actualizarTabla(){
        
        ClearTable.clear(usuariosTabla);        
        AU1_Cargar_Datos_Usuarios nuevaCarga = new AU1_Cargar_Datos_Usuarios();
        nuevaCarga.setTABLA_SQL(VistaSQL);
        nuevaCarga.setJTABLE(usuariosTabla);
        nuevaCarga.cargar_Datos_Usuarios();
        
        Tabla_Formato.tablaNoEditable(usuariosTabla,10);
    }

    private void editarUsuario() {
        
        int fila = usuariosTabla.getSelectedRow();

        if (fila > -1) {

            edicionUsuario = new AU1_Editar_Usuario(new javax.swing.JFrame(), true); 
            
            AU1_Editar_Usuario.COMBOHOST.setSelectedItem(usuariosTabla.getValueAt(fila, 10).toString());
            AU1_Editar_Usuario.NOMBRE_USUARIO_TXTBOX.setText(usuariosTabla.getValueAt(fila, 0).toString());
            AU1_Editar_Usuario.PRIMER_NOMBRE_TXTBOX.setText(usuariosTabla.getValueAt(fila, 1).toString());
            AU1_Editar_Usuario.SEGUNDO_NOMBRE_TXTBOX.setText(usuariosTabla.getValueAt(fila, 2).toString());
            AU1_Editar_Usuario.PRIMER_APELLIDO_TXTBOX.setText(usuariosTabla.getValueAt(fila, 3).toString());
            AU1_Editar_Usuario.SEGUNDO_APELLIDO_TXTBOX.setText(usuariosTabla.getValueAt(fila, 4).toString());
            AU1_Editar_Usuario.CORREO_TXTBOX.setText(usuariosTabla.getValueAt(fila, 5).toString());
            AU1_Editar_Usuario.CARGO_TXTBOX.setText(usuariosTabla.getValueAt(fila, 6).toString());
            AU1_Editar_Usuario.ORGANIZACIÓN_TXTBOX.setText(usuariosTabla.getValueAt(fila, 7).toString());
            AU1_Editar_Usuario.COMBO_TIPO_USUARIO.setSelectedItem(usuariosTabla.getValueAt(fila, 8).toString());
            AU1_Editar_Usuario.CONTRASENA_TXTBOX.setText(usuariosTabla.getValueAt(fila, 9).toString());
            
            edicionUsuario.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, NOT.msg(NOT.SELECT_ROW),NOT.msg(NOT.TITLE),JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void eliminarUsuario() {

        if (usuariosTabla.getSelectedRow() > -1) {

            String NOMBRE_USUARIO = usuariosTabla.getValueAt(usuariosTabla.getSelectedRow(), 0).toString();
            String HOST = usuariosTabla.getValueAt(usuariosTabla.getSelectedRow(), 10).toString();

            AU1_Eliminar_Datos_Usuario eliminarUsuario = new AU1_Eliminar_Datos_Usuario();
            eliminarUsuario.setLLAVE_PRIMARIA(NOMBRE_USUARIO);
            eliminarUsuario.eliminar_Usuario();

            AU1_Gestion_de_Usuarios_SQL.DROP_USER(NOMBRE_USUARIO, HOST);

            DefaultTableModel modelo = (DefaultTableModel) usuariosTabla.getModel();
            modelo.removeRow(usuariosTabla.getSelectedRow());
            usuariosTabla.setModel(modelo);

        } else {
            JOptionPane.showMessageDialog(this, NOT.msg(NOT.SELECT_ROW),NOT.msg(NOT.TITLE),JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void addEvents() {
        botonSalir();
        btnNuevo();
        btnActualizar();
        btnEditar();
        btnEliminar();
        
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

    
    private void btnNuevo(){
        btnNuevo.addActionListener((ActionEvent e) -> {
            new AU1_Crear_Usuarios(new javax.swing.JFrame(), true).setVisible(true);
        });
    }

    private void btnActualizar() {
        btnActualizar.addActionListener((ActionEvent e) -> {
            actualizarTabla();
        });
    }
    
    private void btnEditar(){
        btnEditar.addActionListener((ActionEvent e) -> {
            editarUsuario();
        });
    }

    private void btnEliminar() {
        btnEliminar.addActionListener((ActionEvent e) -> {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to execute this action?", "Deleting...", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                eliminarUsuario();
            }
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_AU1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        titlePanel = new javax.swing.JPanel();
        LABELTITULOMODULO = new javax.swing.JLabel();
        logoSuperior = new javax.swing.JLabel();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        usuariosTabla = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();
        jPanel_Rounded_Corners_Degradado2 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        cintaControles = new javax.swing.JToolBar();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnNuevo = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnActualizar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEditar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnEliminar = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        jMenu1.setText("File");

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        jMenu1.add(MENU_ITEM_SALIR);

        menuBar_AU1.add(jMenu1);

        jMenu2.setText("Edit");
        menuBar_AU1.add(jMenu2);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        titlePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LABELTITULOMODULO.setFont(new java.awt.Font("Barlow Condensed", 3, 18)); // NOI18N
        LABELTITULOMODULO.setForeground(new java.awt.Color(0, 153, 153));
        LABELTITULOMODULO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LABELTITULOMODULO.setText("AU1 - Users Management");
        LABELTITULOMODULO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        logoSuperior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/general_windows/ficha20.png"))); // NOI18N

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(logoSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LABELTITULOMODULO, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, titlePanelLayout.createSequentialGroup()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LABELTITULOMODULO, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(logoSuperior, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );

        usuariosTabla.setBackground(new java.awt.Color(202, 219, 236));
        usuariosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(usuariosTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(usuariosTabla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel_Rounded_Corners_Degradado2.setColor1(new java.awt.Color(241, 246, 252));
        jPanel_Rounded_Corners_Degradado2.setColor2(new java.awt.Color(202, 216, 237));

        cintaControles.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cintaControles.setRollover(true);
        cintaControles.setOpaque(false);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/general_windows/regresar15.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnSalir);
        cintaControles.add(jSeparator1);

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/orders_icons/OKverde20.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setFocusable(false);
        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnNuevo);

        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setFocusable(false);
        btnActualizar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnActualizar);

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnEditar);
        cintaControles.add(jSeparator2);

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setFocusable(false);
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnEliminar);

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado2Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado2);
        jPanel_Rounded_Corners_Degradado2.setLayout(jPanel_Rounded_Corners_Degradado2Layout);
        jPanel_Rounded_Corners_Degradado2Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cintaControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_Rounded_Corners_Degradado2Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cintaControles, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE)
            .addComponent(jPanel_Rounded_Corners_Degradado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel LABELTITULOMODULO;
    private javax.swing.JMenuItem MENU_ITEM_SALIR;
    private javax.swing.JPanel bodyPanel;
    public static javax.swing.JButton btnActualizar;
    public static javax.swing.JButton btnEditar;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnSalir;
    private javax.swing.JToolBar cintaControles;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JLabel logoSuperior;
    public static javax.swing.JMenuBar menuBar_AU1;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPanel titlePanel;
    protected static com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable usuariosTabla;
    // End of variables declaration//GEN-END:variables
}
