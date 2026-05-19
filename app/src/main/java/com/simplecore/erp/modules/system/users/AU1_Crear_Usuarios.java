package com.simplecore.erp.modules.system.users;

import com.simplecore.erp.modules.system.users.utils.AU1_Gestion_de_Usuarios_SQL;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class AU1_Crear_Usuarios extends javax.swing.JDialog {


    public AU1_Crear_Usuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarCOMBOHOST();
        cargarIconos();
        addEvents();
    }

    private void cargarIconos() {
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lyra/access/main_class/icons/pir20.png")));
        
    }
    
    private void addEvents(){
        btnAceptar.addActionListener((ActionEvent e)->{
            guardarDatosUsuarios();
        });
        
        btnCancelar.addActionListener((ActionEvent e)->{
            dispose();
        });
        
        cargarComboTipoUsuarios();
    }
    

    private void cargarCOMBOHOST() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.Hosts.tableName();

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet Datos = pSt.getResultSet();

            while (Datos.next()) {
                COMBOHOST.addItem(Datos.getString(2));
            }

            pSt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AU1_Crear_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void cargarComboTipoUsuarios(){
        
        AU1_Cargar_Combo_Tipo_Usuario nuevaCarga = new AU1_Cargar_Combo_Tipo_Usuario();
        nuevaCarga.setComboTipoUsuarios(COMBO_TIPO_USUARIO);
        nuevaCarga.cargarCombo();
        
        COMBO_TIPO_USUARIO.setSelectedItem(null);
    }

    private void inhabilitarCampos() {
        
        NOMBRE_USUARIO_TXTBOX.setEditable(false);
        PRIMER_NOMBRE_TXTBOX.setEditable(false);
        SEGUNDO_NOMBRE_TXTBOX.setEditable(false);
        PRIMER_APELLIDO_TXTBOX.setEditable(false);
        SEGUNDO_APELLIDO_TXTBOX.setEditable(false);
        CORREO_TXTBOX.setEditable(false);
        CARGO_TXTBOX.setEditable(false);
        ORGANIZACIÓN_TXTBOX.setEditable(false);
        COMBO_TIPO_USUARIO.setEnabled(false);
        CONTRASENA_TXTBOX.setEditable(false);

    }

    private void guardarDatosUsuarios() {

        if (!(NOMBRE_USUARIO_TXTBOX.getText().isEmpty()
                | CONTRASENA_TXTBOX.getText().isEmpty()
                | PRIMER_NOMBRE_TXTBOX.getText().isEmpty()
                | PRIMER_APELLIDO_TXTBOX.getText().isEmpty()
                | SEGUNDO_NOMBRE_TXTBOX.getText().isEmpty()
                | SEGUNDO_APELLIDO_TXTBOX.getText().isEmpty()
                | CORREO_TXTBOX.getText().isEmpty()
                | CARGO_TXTBOX.getText().isEmpty()
                | ORGANIZACIÓN_TXTBOX.getText().isEmpty()
                | COMBO_TIPO_USUARIO.getSelectedItem() == null
                | COMBOHOST.getSelectedItem() == null)) {

            AU1_Crear_Usuario nuevoUsuario = new AU1_Crear_Usuario();
            nuevoUsuario.setTABLA_SQL(DatabaseTables.USERS_SYSTEM.tableName());
            nuevoUsuario.setNOMBRE_USUARIO(NOMBRE_USUARIO_TXTBOX.getText());
            nuevoUsuario.setPRIMER_NOMBRE(PRIMER_NOMBRE_TXTBOX.getText());
            nuevoUsuario.setSEGUNDO_NOMBRE(SEGUNDO_NOMBRE_TXTBOX.getText());
            nuevoUsuario.setPRIMER_APELLIDO(PRIMER_APELLIDO_TXTBOX.getText());
            nuevoUsuario.setSEGUNDO_APELLIDO(SEGUNDO_APELLIDO_TXTBOX.getText());
            nuevoUsuario.setCORREO(CORREO_TXTBOX.getText());
            nuevoUsuario.setCARGO(CARGO_TXTBOX.getText());
            nuevoUsuario.setORGANIZACIÓN(ORGANIZACIÓN_TXTBOX.getText());
            nuevoUsuario.setTIPO_USUARIO(COMBO_TIPO_USUARIO.getSelectedItem().toString());
            nuevoUsuario.setCONTRASENA(CONTRASENA_TXTBOX.getText());
            nuevoUsuario.setHOST(COMBOHOST.getSelectedItem().toString());
            nuevoUsuario.crear_Usuario();

            if (nuevoUsuario.getRESULTADO() == 1062) {

                JOptionPane.showMessageDialog(this, NOT.msg(NOT.DUPLICATE_RECORD), NOT.msg(NOT.TITLE), JOptionPane.INFORMATION_MESSAGE);

            } else {
                AU1_Gestion_de_Usuarios_SQL.CREATE_USER(NOMBRE_USUARIO_TXTBOX.getText(),
                        CONTRASENA_TXTBOX.getText(), COMBOHOST.getSelectedItem().toString());

                JOptionPane.showMessageDialog(this, NOT.msg(NOT.OPERATION_COMPLETED),NOT.msg(NOT.TITLE),JOptionPane.INFORMATION_MESSAGE);
                inhabilitarCampos();
                DefaultTableModel modelo = (DefaultTableModel) AU1_Administracion_Usuarios.usuariosTabla.getModel();
                Object[] fila = {
                    NOMBRE_USUARIO_TXTBOX.getText(),
                    PRIMER_NOMBRE_TXTBOX.getText(),
                    SEGUNDO_NOMBRE_TXTBOX.getText(),
                    PRIMER_APELLIDO_TXTBOX.getText(),
                    SEGUNDO_APELLIDO_TXTBOX.getText(),
                    CORREO_TXTBOX.getText(),
                    CARGO_TXTBOX.getText(),
                    ORGANIZACIÓN_TXTBOX.getText(),
                    COMBO_TIPO_USUARIO.getSelectedItem().toString(),
                    CONTRASENA_TXTBOX.getText()
                };

                modelo.addRow(fila);
                AU1_Administracion_Usuarios.usuariosTabla.setModel(modelo);
                this.dispose();
            }

        } else {
            JOptionPane.showMessageDialog(this, NOT.msg(NOT.EMPTY_FIELDS),NOT.msg(NOT.TITLE),JOptionPane.WARNING_MESSAGE);
        }

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        LABELUSER = new javax.swing.JLabel();
        NOMBRE_USUARIO_TXTBOX = new javax.swing.JTextField();
        LABELNAME1 = new javax.swing.JLabel();
        PRIMER_NOMBRE_TXTBOX = new javax.swing.JTextField();
        LABELLASTNAME = new javax.swing.JLabel();
        SEGUNDO_NOMBRE_TXTBOX = new javax.swing.JTextField();
        LABELNAME2 = new javax.swing.JLabel();
        PRIMER_APELLIDO_TXTBOX = new javax.swing.JTextField();
        LABELLASTNAME2 = new javax.swing.JLabel();
        SEGUNDO_APELLIDO_TXTBOX = new javax.swing.JTextField();
        CORREO_TXTBOX = new javax.swing.JTextField();
        LABELMAIL = new javax.swing.JLabel();
        LABELPOSITION = new javax.swing.JLabel();
        CARGO_TXTBOX = new javax.swing.JTextField();
        LABELORGANIZACION = new javax.swing.JLabel();
        ORGANIZACIÓN_TXTBOX = new javax.swing.JTextField();
        LABELPASS = new javax.swing.JLabel();
        CONTRASENA_TXTBOX = new javax.swing.JTextField();
        LABELUSERTYPE = new javax.swing.JLabel();
        COMBOHOST = new javax.swing.JComboBox<>();
        LABELHOST = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        COMBO_TIPO_USUARIO = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelPrincipal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LABELUSER.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELUSER.setText("User:");

        NOMBRE_USUARIO_TXTBOX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LABELNAME1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELNAME1.setText("First name:");

        PRIMER_NOMBRE_TXTBOX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LABELLASTNAME.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELLASTNAME.setText("Last name:");

        SEGUNDO_NOMBRE_TXTBOX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LABELNAME2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELNAME2.setText("Middle name:");

        PRIMER_APELLIDO_TXTBOX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LABELLASTNAME2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELLASTNAME2.setText("Second last name:");

        SEGUNDO_APELLIDO_TXTBOX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        CORREO_TXTBOX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LABELMAIL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELMAIL.setText("Mail:");

        LABELPOSITION.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELPOSITION.setText("Position:");

        CARGO_TXTBOX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LABELORGANIZACION.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELORGANIZACION.setText("Organization:");

        ORGANIZACIÓN_TXTBOX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LABELPASS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELPASS.setText("Pass:");

        CONTRASENA_TXTBOX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LABELUSERTYPE.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELUSERTYPE.setText("User type:");

        COMBOHOST.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LABELHOST.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELHOST.setText("Host :");

        btnCancelar.setText("Cancel");

        btnAceptar.setText("Accept");

        COMBO_TIPO_USUARIO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        COMBO_TIPO_USUARIO.setToolTipText("");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                            .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LABELLASTNAME)
                                .addComponent(LABELNAME1)
                                .addComponent(LABELUSER)
                                .addComponent(LABELLASTNAME2)
                                .addComponent(LABELNAME2)
                                .addComponent(LABELPASS)
                                .addComponent(LABELORGANIZACION)
                                .addComponent(LABELPOSITION)
                                .addComponent(LABELMAIL)
                                .addComponent(LABELUSERTYPE)
                                .addComponent(LABELHOST))
                            .addGap(18, 18, 18)
                            .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(COMBOHOST, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(COMBO_TIPO_USUARIO, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ORGANIZACIÓN_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SEGUNDO_APELLIDO_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                    .addComponent(PRIMER_APELLIDO_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SEGUNDO_NOMBRE_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NOMBRE_USUARIO_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CORREO_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CONTRASENA_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PRIMER_NOMBRE_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CARGO_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(68, 68, 68))
                        .addComponent(jSeparator2)))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(COMBOHOST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELHOST))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELUSER)
                    .addComponent(NOMBRE_USUARIO_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELPASS)
                    .addComponent(CONTRASENA_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELNAME1)
                    .addComponent(PRIMER_NOMBRE_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELLASTNAME)
                    .addComponent(SEGUNDO_NOMBRE_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELNAME2)
                    .addComponent(PRIMER_APELLIDO_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELLASTNAME2)
                    .addComponent(SEGUNDO_APELLIDO_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELORGANIZACION)
                    .addComponent(ORGANIZACIÓN_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELUSERTYPE)
                    .addComponent(COMBO_TIPO_USUARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELPOSITION)
                    .addComponent(CARGO_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELMAIL)
                    .addComponent(CORREO_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CARGO_TXTBOX;
    private javax.swing.JComboBox<String> COMBOHOST;
    private javax.swing.JComboBox<String> COMBO_TIPO_USUARIO;
    private javax.swing.JTextField CONTRASENA_TXTBOX;
    private javax.swing.JTextField CORREO_TXTBOX;
    private javax.swing.JLabel LABELHOST;
    public static javax.swing.JLabel LABELLASTNAME;
    public static javax.swing.JLabel LABELLASTNAME2;
    public static javax.swing.JLabel LABELMAIL;
    public static javax.swing.JLabel LABELNAME1;
    public static javax.swing.JLabel LABELNAME2;
    public static javax.swing.JLabel LABELORGANIZACION;
    public static javax.swing.JLabel LABELPASS;
    public static javax.swing.JLabel LABELPOSITION;
    public static javax.swing.JLabel LABELUSER;
    public static javax.swing.JLabel LABELUSERTYPE;
    private javax.swing.JTextField NOMBRE_USUARIO_TXTBOX;
    private javax.swing.JTextField ORGANIZACIÓN_TXTBOX;
    private javax.swing.JTextField PRIMER_APELLIDO_TXTBOX;
    private javax.swing.JTextField PRIMER_NOMBRE_TXTBOX;
    private javax.swing.JTextField SEGUNDO_APELLIDO_TXTBOX;
    private javax.swing.JTextField SEGUNDO_NOMBRE_TXTBOX;
    public static javax.swing.JButton btnAceptar;
    public static javax.swing.JButton btnCancelar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
