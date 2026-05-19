
package com.simplecore.erp.client.utils.splash;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.client.gui.workspace.frontend.MainFrame;


 public class LyraCoreSplash extends javax.swing.JFrame {
    
    public LyraCoreSplash() {
         initComponents();
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/connectionsdb/pir20.png")));
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gradienPanel = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        splashVolcano1 = new com.simplecore.erp.client.gui.login.SplashVolcano();
        gradientLabel2 = new com.simplecore.erp.client.gui.components.labels.GradientLabel();
        gradientLabel3 = new com.simplecore.erp.client.gui.components.labels.GradientLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setName("splash"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        gradienPanel.setColor1(new java.awt.Color(28, 127, 163));
        gradienPanel.setColor2(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout splashVolcano1Layout = new javax.swing.GroupLayout(splashVolcano1);
        splashVolcano1.setLayout(splashVolcano1Layout);
        splashVolcano1Layout.setHorizontalGroup(
            splashVolcano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );
        splashVolcano1Layout.setVerticalGroup(
            splashVolcano1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gradientLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gradientLabel2.setText("Lyra Project ");
        gradientLabel2.setEndColor(new java.awt.Color(229, 229, 229));
        gradientLabel2.setFont(new java.awt.Font("Poppins ExtraLight", 0, 24)); // NOI18N
        gradientLabel2.setStartColor(new java.awt.Color(160, 160, 160));

        gradientLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gradientLabel3.setText("Development");
        gradientLabel3.setEndColor(new java.awt.Color(229, 229, 229));
        gradientLabel3.setFont(new java.awt.Font("Poppins ExtraLight", 0, 14)); // NOI18N
        gradientLabel3.setStartColor(new java.awt.Color(160, 160, 160));

        javax.swing.GroupLayout gradienPanelLayout = new javax.swing.GroupLayout(gradienPanel);
        gradienPanel.setLayout(gradienPanelLayout);
        gradienPanelLayout.setHorizontalGroup(
            gradienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradienPanelLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(gradientLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gradientLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(splashVolcano1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        gradienPanelLayout.setVerticalGroup(
            gradienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splashVolcano1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradienPanelLayout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addGroup(gradienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gradientLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gradientLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradienPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradienPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            Thread.sleep(7000);
            this.dispose();
            new MainFrame().setVisible(true);

        } catch (InterruptedException ex) {
            Logger.getLogger(LyraCoreSplash.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_formWindowOpened
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient gradienPanel;
    private com.simplecore.erp.client.gui.components.labels.GradientLabel gradientLabel2;
    private com.simplecore.erp.client.gui.components.labels.GradientLabel gradientLabel3;
    private com.simplecore.erp.client.gui.login.SplashVolcano splashVolcano1;
    // End of variables declaration//GEN-END:variables
}
