package com.simplecore.erp.gui.workspace.legacy.password;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.utils.notifications.NOT;


public class Introduzca_Contrasena_Inicial extends javax.swing.JPanel {

    public static String USER = null;
    String PASSWORD = null;

    public Introduzca_Contrasena_Inicial() {
        initComponents();
        cargarIconos();
        setEchoChar();
        cargarDatosUsuario();
    }
    
    private void cargarIconos(){
        btnNext.putClientProperty("JButton.buttonType", "roundRect");
        btnsalir.putClientProperty("JButton.buttonType", "roundRect");
        CURRENT_PASSWORD.putClientProperty("JComponent.roundRect", true);
    }
    
    private void setEchoChar() {
        CURRENT_PASSWORD.setEchoChar('•');
    }
    
    
    private void cargarDatosUsuario(){
        

        
  //      USER = MainLyra.getUser();
    //    PASSWORD = MainLyra.getPassword();
        
        LABELUSER.setText(USER);
        
    }

    private void validarContrasena() {

        if (CURRENT_PASSWORD.getPassword().length > 0) {
            char[] passArray = CURRENT_PASSWORD.getPassword();
            String CONTRASENA="";

            for (int x = 0; x < passArray.length; x++) {
                CONTRASENA += passArray[x];
            }
            
            if(CONTRASENA.equals(PASSWORD)){
                
                Nueva_Contrasena nueva = new Nueva_Contrasena();
                ChangePassword.cargarPanel(nueva);                
            }else{
                JOptionPane.showMessageDialog(this, NOT.msg(NOT.ACCESS_DENIED),NOT.msg(NOT.TITLE),JOptionPane.ERROR_MESSAGE);
                CURRENT_PASSWORD.setText(null);
            }
            
        }else{
            new NotificacionEscribirContrasena().setVisible(true);
        }

    }
    
    private void cerrarVentana() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        LABELCURRENTPASS = new javax.swing.JLabel();
        LABELTITLE = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        LABELMSG = new javax.swing.JLabel();
        LABELUSER = new javax.swing.JLabel();
        CURRENT_PASSWORD = new javax.swing.JPasswordField();

        jPanel2.setMaximumSize(new java.awt.Dimension(541, 210));
        jPanel2.setPreferredSize(new java.awt.Dimension(541, 240));

        LABELCURRENTPASS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LABELCURRENTPASS.setText("Current password");

        LABELTITLE.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        LABELTITLE.setText("Change your password");

        btnsalir.setText("Exit");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        LABELMSG.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LABELMSG.setText("First, confirm your current password.");

        LABELUSER.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LABELUSER.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LABELUSER.setText("Usuario");

        CURRENT_PASSWORD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LABELMSG, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LABELUSER, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(LABELCURRENTPASS, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CURRENT_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(LABELTITLE, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LABELTITLE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LABELMSG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LABELUSER)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCURRENTPASS)
                    .addComponent(CURRENT_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir)
                    .addComponent(btnNext))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

       cerrarVentana();
        
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        validarContrasena();
    }//GEN-LAST:event_btnNextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JPasswordField CURRENT_PASSWORD;
    private javax.swing.JLabel LABELCURRENTPASS;
    private javax.swing.JLabel LABELMSG;
    private javax.swing.JLabel LABELTITLE;
    private javax.swing.JLabel LABELUSER;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnsalir;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
