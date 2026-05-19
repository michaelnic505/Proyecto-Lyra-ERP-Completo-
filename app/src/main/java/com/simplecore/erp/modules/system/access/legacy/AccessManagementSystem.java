package com.simplecore.erp.modules.system.access.legacy;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.config.database.tables.transacciones;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.modules.system.access.utils.OrderPermissionsControlPanel;
import com.simplecore.erp.modules.system.access.utils.User_Permissions;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class AccessManagementSystem extends javax.swing.JPanel {

    public AccessManagementSystem() {
        initComponents();
        setModeloTablaTransacciones();
        cargar_Datos_Transacciones();
        setValoresBooleanTabla();
        addEvents();

    }


    private void addEvents() {
        btnNuevo.addActionListener((ActionEvent e) -> {
            new AU2_Agregar_Transacciones(new javax.swing.JFrame(), true).setVisible(true);
        });

        btnActualizar.addActionListener((ActionEvent e) -> {
            actualizarTabla();
        });

        btnEditar.addActionListener((ActionEvent e) -> {
            guardarAccesos();
        });

        btnEliminar.addActionListener((ActionEvent e) -> {
            eliminarTransaccion();

        });

        botonSalir();

        goToOrderPermissions();
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
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
    private void setModeloTablaTransacciones() {
        AU2_Modelo_Lista_Permisos.set(accesosTabla);
    }
    private void setValoresBooleanTabla() {

        Tabla_Formato.editableColumn(accesosTabla, 4, 9);

        AU2_Agregar_CheckBox.addCheckBox(4, accesosTabla);
        AU2_Agregar_CheckBox.addCheckBox(5, accesosTabla);
        AU2_Agregar_CheckBox.addCheckBox(6, accesosTabla);
        AU2_Agregar_CheckBox.addCheckBox(7, accesosTabla);
        AU2_Agregar_CheckBox.addCheckBox(8, accesosTabla);
        AU2_Agregar_CheckBox.addCheckBox(9, accesosTabla);
        Tabla_Formato.resizeTable(accesosTabla, 10);

        cargarPermisos();

    }
    private void cargarPermisos() {
        if (accesosTabla.getRowCount() > 0) {
            for (int i = 0; i < accesosTabla.getRowCount(); i++) {

                AU2_Valores_Accesos_Transacciones consulta = new AU2_Valores_Accesos_Transacciones();
                consulta.setTRANSACCION(accesosTabla.getValueAt(i, 1).toString());
                consulta.cargar_Datos();

                accesosTabla.setValueAt(consulta.isL1(), i, 4);
                accesosTabla.setValueAt(consulta.isL2(), i, 5);
                accesosTabla.setValueAt(consulta.isL3(), i, 6);
                accesosTabla.setValueAt(consulta.isL4(), i, 7);
                accesosTabla.setValueAt(consulta.isL5(), i, 8);
                accesosTabla.setValueAt(consulta.isR6(), i, 9);

            }
        }
    }
    private void cargar_Datos_Transacciones() {

        AU2_Cargar_Datos_Transacciones nuevaCarga = new AU2_Cargar_Datos_Transacciones();
        nuevaCarga.setJTABLE(accesosTabla);
        nuevaCarga.cargar_Datos();
        Tabla_Formato.tablaNoEditable(accesosTabla, 10);

    }

    private void actualizarTabla() {

        cargar_Datos_Transacciones();
        setValoresBooleanTabla();

    }
    private void guardarAccesos() {

        int Option = JOptionPane.showConfirmDialog(this, NOT.msg(NOT.ARE_YOU_SURE_YOU_WANT_TO_PROCEED), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

        if (Option == JOptionPane.YES_OPTION) {
            if (accesosTabla.getRowCount() > 0) {

                for (int i = 0; i < accesosTabla.getRowCount(); i++) {

                    AU2_Actualizar_Permisos actualizarPermisos = new AU2_Actualizar_Permisos();
                    actualizarPermisos.setTRANSACCION(accesosTabla.getValueAt(i, 1).toString());

                    actualizarPermisos.setL1((boolean) accesosTabla.getValueAt(i, 4));
                    actualizarPermisos.setL2((boolean) accesosTabla.getValueAt(i, 5));
                    actualizarPermisos.setL3((boolean) accesosTabla.getValueAt(i, 6));
                    actualizarPermisos.setL4((boolean) accesosTabla.getValueAt(i, 7));
                    actualizarPermisos.setL5((boolean) accesosTabla.getValueAt(i, 8));
                    actualizarPermisos.setR6((boolean) accesosTabla.getValueAt(i, 9));
                    actualizarPermisos.actualizarPermisos();

                }

            }

            JOptionPane.showMessageDialog(this, NOT.msg(NOT.OPERATION_COMPLETED), NOT.msg(NOT.TITLE), JOptionPane.INFORMATION_MESSAGE);

        } else if (Option == JOptionPane.NO_OPTION) {
            cargarPermisos();
            JOptionPane.showMessageDialog(this, NOT.msg(NOT.NO_ACTION_EXECUTED), NOT.msg(NOT.TITLE), JOptionPane.WARNING_MESSAGE);
        }

    }
    private void eliminarTransaccion() {

        int filaSeleccionada = accesosTabla.getSelectedRow();

        if (filaSeleccionada > -1) {

            try {
                String transaccion = (String) accesosTabla.getValueAt(accesosTabla.getSelectedRow(), 0);
                Connection conexion = PooledConnectionService.getConnection();
                PreparedStatement st = null;

                String query = SQLKeywords.DELETE.toSQL()
                        + SQLKeywords.FROM.toSQL()
                        + DatabaseTables.BUSINESS_TRANSACTIONS.tableName()
                        + SQLKeywords.WHERE.toSQL()
                        + transacciones.TRANSACCION.toString()
                        + SQLKeywords.EQUALS.toSQL()
                        + SQLKeywords.SINGLE_QUOTE.toSQL()
                        + transaccion
                        + SQLKeywords.SINGLE_QUOTE.toSQL();

                st = conexion.prepareStatement(query);
                st.executeUpdate();


            } catch (SQLException ex) {
                Logger.getLogger(AccessManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
        }

    }
    private void goToOrderPermissions() {
        buttonGoOrders.addActionListener((e) -> {

            entrarPermisos();

        });

        userTFOrders.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    entrarPermisos();
                }
            }

        });
    }
    private void entrarPermisos() {

        if (!userTFOrders.getText().isEmpty()) {

            String user = userTFOrders.getText();
            if (User_Permissions.verifyUser(user)) {

                OrderPermissionsControlPanel panelPermisos = new OrderPermissionsControlPanel();
                panelPermisos.setPanelPrincipal(panelOrden);
                panelPermisos.setPanelAtras(panelUser);

                panelPermisos.setUser(user);
                panelPermisos.cargarO01(user);
                panelPermisos.cargarO02(user);
                panelPermisos.cargarO03(user);

                PanelLoader.loadPanel(panelPermisos, panelOrden);

            } else {

              new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.USER_DOES_NOT_EXIST), TypeMessage.ERROR);

            }
        }
    }

 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_AU2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jSeparator3 = new javax.swing.JSeparator();
        MULTITAB = new javax.swing.JTabbedPane();
        general = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        accesosTabla = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();
        btnEliminar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEditar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnActualizar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnNuevo = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelOrden = new javax.swing.JPanel();
        panelUser = new javax.swing.JPanel();
        userTFOrders = new javax.swing.JTextField();
        buttonGoOrders = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        usernameOrdersLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        title = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();

        jMenu1.setText("File");

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        jMenu1.add(MENU_ITEM_SALIR);

        menuBar_AU2.add(jMenu1);

        jMenu2.setText("Edit");
        menuBar_AU2.add(jMenu2);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/error.png"))); // NOI18N

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jSeparator3))
                .addContainerGap())
        );

        MULTITAB.setBackground(new java.awt.Color(202, 216, 237));
        MULTITAB.setForeground(new java.awt.Color(102, 102, 102));
        MULTITAB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        general.setBackground(new java.awt.Color(238, 244, 254));
        general.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        accesosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        accesosTabla.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jScrollPane1.setViewportView(accesosTabla);

        btnEliminar.setBackground(new java.awt.Color(226, 210, 144));
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/eliminar.png"))); // NOI18N

        btnEditar.setBackground(new java.awt.Color(226, 210, 144));
        btnEditar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/editar.png"))); // NOI18N

        btnActualizar.setBackground(new java.awt.Color(226, 210, 144));
        btnActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/actualizar.png"))); // NOI18N

        btnNuevo.setBackground(new java.awt.Color(226, 210, 144));
        btnNuevo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/orders_icons/check_ok.png"))); // NOI18N

        javax.swing.GroupLayout generalLayout = new javax.swing.GroupLayout(general);
        general.setLayout(generalLayout);
        generalLayout.setHorizontalGroup(
            generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalLayout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        generalLayout.setVerticalGroup(
            generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        MULTITAB.addTab("Transactions", general);

        panelOrden.setBackground(new java.awt.Color(238, 244, 254));
        panelOrden.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelOrden.setLayout(new java.awt.BorderLayout());

        panelUser.setBackground(new java.awt.Color(238, 244, 254));

        buttonGoOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/adelante.png"))); // NOI18N

        usernameOrdersLB.setText("Username");

        title.setForeground(new java.awt.Color(245, 245, 245));
        title.setText("Enter a username   ");
        title.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout panelUserLayout = new javax.swing.GroupLayout(panelUser);
        panelUser.setLayout(panelUserLayout);
        panelUserLayout.setHorizontalGroup(
            panelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usernameOrdersLB, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(userTFOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(buttonGoOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(479, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUserLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelUserLayout.setVerticalGroup(
            panelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUserLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(panelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userTFOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGoOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameOrdersLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(353, Short.MAX_VALUE))
        );

        panelOrden.add(panelUser, java.awt.BorderLayout.CENTER);

        MULTITAB.addTab("Orders", panelOrden);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MULTITAB)
                .addGap(263, 263, 263))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(MULTITAB)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JTabbedPane MULTITAB;
    public static com.simplecore.erp.gui.components.tables.lastversion.LyraTable accesosTabla;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnActualizar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEditar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnEliminar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnNuevo;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonGoOrders;
    private javax.swing.JPanel general;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    public static javax.swing.JMenuBar menuBar_AU2;
    private javax.swing.JPanel panelOrden;
    private javax.swing.JPanel panelUser;
    private com.simplecore.erp.gui.components.labels.JLabelHQFraming title;
    private javax.swing.JTextField userTFOrders;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined usernameOrdersLB;
    // End of variables declaration//GEN-END:variables
}
