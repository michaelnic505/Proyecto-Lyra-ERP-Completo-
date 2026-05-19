package com.simplecore.erp.modules.system.users;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;

public class AU1_Editar_Usuario extends javax.swing.JDialog {


    public AU1_Editar_Usuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        addEvents();
       
    }

    private void addEvents() {
        btnAceptar.addActionListener((ActionEvent e) -> {
            guardarDatosUsuarios();
        });

        btnCancelar.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
        
        cargarCOMBOHOST();
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

    private void cargarComboTipoUsuarios() {

        AU1_Cargar_Combo_Tipo_Usuario nuevaCarga = new AU1_Cargar_Combo_Tipo_Usuario();
        nuevaCarga.setComboTipoUsuarios(COMBO_TIPO_USUARIO);
        nuevaCarga.cargarCombo();
        
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
        COMBOHOST.setEnabled(false);
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
                | ORGANIZACIÓN_TXTBOX.getText().isEmpty())) {

            AU1_Editar_Datos_Usuarios nuevaEdicionUsuario = new AU1_Editar_Datos_Usuarios();
            nuevaEdicionUsuario.setTABLA_SQL(DatabaseTables.USERS_SYSTEM.tableName());
            nuevaEdicionUsuario.setNOMBRE_USUARIO(NOMBRE_USUARIO_TXTBOX.getText());
            nuevaEdicionUsuario.setPRIMER_NOMBRE(PRIMER_NOMBRE_TXTBOX.getText());
            nuevaEdicionUsuario.setSEGUNDO_NOMBRE(SEGUNDO_NOMBRE_TXTBOX.getText());
            nuevaEdicionUsuario.setPRIMER_APELLIDO(PRIMER_APELLIDO_TXTBOX.getText());
            nuevaEdicionUsuario.setSEGUNDO_APELLIDO(SEGUNDO_APELLIDO_TXTBOX.getText());
            nuevaEdicionUsuario.setCORREO(CORREO_TXTBOX.getText());
            nuevaEdicionUsuario.setCARGO(CARGO_TXTBOX.getText());
            nuevaEdicionUsuario.setORGANIZACIÓN(ORGANIZACIÓN_TXTBOX.getText());
            nuevaEdicionUsuario.setTIPO_USUARIO(COMBO_TIPO_USUARIO.getSelectedItem().toString());
            nuevaEdicionUsuario.setCONTRASENA(CONTRASENA_TXTBOX.getText());
            nuevaEdicionUsuario.setHOST(COMBOHOST.getSelectedItem().toString());

            nuevaEdicionUsuario.editar_Datos_Usuarios();
            inhabilitarCampos();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, NOT.msg(NOT.OPERATION_COMPLETED),NOT.msg(NOT.TITLE), JOptionPane.INFORMATION_MESSAGE);
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
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        COMBOHOST = new javax.swing.JComboBox<>();
        LABELHOST = new javax.swing.JLabel();
        COMBO_TIPO_USUARIO = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();

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

        btnAceptar.setText("Accept");

        btnCancelar.setText("Cancel");

        COMBOHOST.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LABELHOST.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELHOST.setText("Host :");

        COMBO_TIPO_USUARIO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        COMBO_TIPO_USUARIO.setToolTipText("");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAceptar)
                        .addGap(3, 3, 3)
                        .addComponent(btnCancelar))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LABELORGANIZACION)
                                    .addComponent(LABELNAME1)
                                    .addComponent(LABELPOSITION)
                                    .addComponent(LABELUSER)
                                    .addComponent(LABELMAIL)
                                    .addComponent(LABELLASTNAME2)
                                    .addComponent(LABELUSERTYPE)
                                    .addComponent(LABELNAME2)
                                    .addComponent(LABELPASS)
                                    .addComponent(LABELLASTNAME))
                                .addGap(18, 18, 18)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PRIMER_NOMBRE_TXTBOX, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(SEGUNDO_APELLIDO_TXTBOX)
                                    .addComponent(PRIMER_APELLIDO_TXTBOX)
                                    .addComponent(SEGUNDO_NOMBRE_TXTBOX)
                                    .addComponent(ORGANIZACIÓN_TXTBOX)
                                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(NOMBRE_USUARIO_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CONTRASENA_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CARGO_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(COMBO_TIPO_USUARIO, javax.swing.GroupLayout.Alignment.LEADING, 0, 184, Short.MAX_VALUE)
                                            .addComponent(CORREO_TXTBOX, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(LABELHOST)
                                .addGap(84, 84, 84)
                                .addComponent(COMBOHOST, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(60, 60, 60))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(COMBOHOST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELHOST))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELUSER)
                    .addComponent(NOMBRE_USUARIO_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELPASS)
                    .addComponent(CONTRASENA_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(PRIMER_NOMBRE_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELNAME1))
                .addGap(3, 3, 3)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELLASTNAME)
                    .addComponent(SEGUNDO_NOMBRE_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELNAME2)
                    .addComponent(PRIMER_APELLIDO_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELLASTNAME2)
                    .addComponent(SEGUNDO_APELLIDO_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELORGANIZACION)
                    .addComponent(ORGANIZACIÓN_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELUSERTYPE)
                    .addComponent(COMBO_TIPO_USUARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELPOSITION)
                    .addComponent(CARGO_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELMAIL)
                    .addComponent(CORREO_TXTBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JTextField CARGO_TXTBOX;
    protected static javax.swing.JComboBox<String> COMBOHOST;
    protected static javax.swing.JComboBox<String> COMBO_TIPO_USUARIO;
    protected static javax.swing.JTextField CONTRASENA_TXTBOX;
    protected static javax.swing.JTextField CORREO_TXTBOX;
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
    protected static javax.swing.JTextField NOMBRE_USUARIO_TXTBOX;
    protected static javax.swing.JTextField ORGANIZACIÓN_TXTBOX;
    protected static javax.swing.JTextField PRIMER_APELLIDO_TXTBOX;
    protected static javax.swing.JTextField PRIMER_NOMBRE_TXTBOX;
    protected static javax.swing.JTextField SEGUNDO_APELLIDO_TXTBOX;
    protected static javax.swing.JTextField SEGUNDO_NOMBRE_TXTBOX;
    public static javax.swing.JButton btnAceptar;
    public static javax.swing.JButton btnCancelar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
