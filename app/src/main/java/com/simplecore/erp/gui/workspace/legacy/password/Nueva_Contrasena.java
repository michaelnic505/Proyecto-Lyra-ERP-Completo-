package com.simplecore.erp.gui.workspace.legacy.password;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.utils.notifications.NOT;

public class Nueva_Contrasena extends javax.swing.JPanel {

    String NUEVA_CONTRASENA;
    String CONFIRM_CONTRASENA;

    public Nueva_Contrasena() {
        initComponents();
        setEchoChar();

    }
    
    private void setEchoChar(){
        
        NEW_PASSWORD.setEchoChar('•');
        CONFIRM_PASSWORD.setEchoChar('•');
        
    }

    private void nuevaContrasena() {
        char[] nuevaContrasena = NEW_PASSWORD.getPassword();
        NUEVA_CONTRASENA = "";
        for (int x = 0; x < nuevaContrasena.length; x++) {
            NUEVA_CONTRASENA += nuevaContrasena[x];
        }
    }

    private void verificarMatchContrasena() {

        char[] confirContrasena = CONFIRM_PASSWORD.getPassword();
        CONFIRM_CONTRASENA = "";
        for (int x = 0; x < confirContrasena.length; x++) {
            CONFIRM_CONTRASENA += confirContrasena[x];
        }

    }

    private void cerrarJFRame() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
    }

    private void cambiarContrasena() {

        if (NEW_PASSWORD.getPassword().length > 0 && CONFIRM_PASSWORD.getPassword().length > 0) {
            if (NUEVA_CONTRASENA.equals(CONFIRM_CONTRASENA)) {
                String USER = Introduzca_Contrasena_Inicial.USER;
                String PASS = CONFIRM_CONTRASENA;

                Connection conexion = PooledConnectionService.getConnection();
                PreparedStatement pSt = null;
                String query = "SET PASSWORD FOR '" + USER + "'@'localhost' = '" + PASS + "'";

                try {
                    pSt = conexion.prepareStatement(query);
                    pSt.executeUpdate();
                    pSt.close();
                    JOptionPane.showMessageDialog(this, NOT.msg(NOT.OPERATION_COMPLETED),NOT.msg(NOT.TITLE), JOptionPane.INFORMATION_MESSAGE);
                    cerrarJFRame();
                   

                } catch (SQLException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(this, NOT.msg(NOT.INCORRECT_DATA),NOT.msg(NOT.TITLE),JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, NOT.EMPTY_FIELDS,NOT.TITLE,JOptionPane.ERROR_MESSAGE);
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btnCambiarContrasena = new javax.swing.JButton();
        NEW_PASSWORD = new javax.swing.JPasswordField();
        CONFIRM_PASSWORD = new javax.swing.JPasswordField();

        jPanel2.setMaximumSize(new java.awt.Dimension(541, 210));
        jPanel2.setPreferredSize(new java.awt.Dimension(541, 240));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("New password :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Confirm password :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setText("Change your password");

        btnsalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsalir.setText("Exit");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnCambiarContrasena.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCambiarContrasena.setText("Finish");
        btnCambiarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarContrasenaActionPerformed(evt);
            }
        });

        NEW_PASSWORD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NEW_PASSWORD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        NEW_PASSWORD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NEW_PASSWORDFocusLost(evt);
            }
        });

        CONFIRM_PASSWORD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CONFIRM_PASSWORD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CONFIRM_PASSWORD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CONFIRM_PASSWORDKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnCambiarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CONFIRM_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(NEW_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(NEW_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(CONFIRM_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir)
                    .addComponent(btnCambiarContrasena))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

        cerrarJFRame();

    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnCambiarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarContrasenaActionPerformed

        cambiarContrasena();
        
    }//GEN-LAST:event_btnCambiarContrasenaActionPerformed

    private void CONFIRM_PASSWORDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CONFIRM_PASSWORDKeyReleased

        verificarMatchContrasena();

    }//GEN-LAST:event_CONFIRM_PASSWORDKeyReleased

    private void NEW_PASSWORDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NEW_PASSWORDFocusLost

        nuevaContrasena();

    }//GEN-LAST:event_NEW_PASSWORDFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField CONFIRM_PASSWORD;
    private javax.swing.JPasswordField NEW_PASSWORD;
    private javax.swing.JButton btnCambiarContrasena;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
