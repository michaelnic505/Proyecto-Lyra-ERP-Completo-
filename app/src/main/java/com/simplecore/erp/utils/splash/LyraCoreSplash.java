
package com.simplecore.erp.utils.splash;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.gui.workspace.LyraFrame;


 public class LyraCoreSplash extends javax.swing.JFrame {
    
    public LyraCoreSplash() {
         initComponents();
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/connectionsdb/pir20.png")));
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mikeLogoPanel = new com.simplecore.erp.gui.login.SplashPanel();
        msgLabel = new javax.swing.JLabel();
        gradienPanel = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        logoLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        lyraLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();

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

        msgLabel.setFont(new java.awt.Font("Times New Roman", 0, 8)); // NOI18N
        msgLabel.setForeground(new java.awt.Color(0, 0, 153));
        msgLabel.setText("2024 All rigths reserved, Mikel Sanchez® & Copyright.");

        javax.swing.GroupLayout mikeLogoPanelLayout = new javax.swing.GroupLayout(mikeLogoPanel);
        mikeLogoPanel.setLayout(mikeLogoPanelLayout);
        mikeLogoPanelLayout.setHorizontalGroup(
            mikeLogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mikeLogoPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(msgLabel)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        mikeLogoPanelLayout.setVerticalGroup(
            mikeLogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mikeLogoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(msgLabel)
                .addContainerGap())
        );

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/splash/main_logo.png"))); // NOI18N

        lyraLabel.setForeground(new java.awt.Color(224, 224, 224));
        lyraLabel.setText("Lyra");
        lyraLabel.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N

        javax.swing.GroupLayout gradienPanelLayout = new javax.swing.GroupLayout(gradienPanel);
        gradienPanel.setLayout(gradienPanelLayout);
        gradienPanelLayout.setHorizontalGroup(
            gradienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradienPanelLayout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addGroup(gradienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lyraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        gradienPanelLayout.setVerticalGroup(
            gradienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradienPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lyraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gradienPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(mikeLogoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mikeLogoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gradienPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            Thread.sleep(1000);
            this.dispose();
            new LyraFrame().setVisible(true);

        } catch (InterruptedException ex) {
            Logger.getLogger(LyraCoreSplash.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_formWindowOpened
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient gradienPanel;
    private com.simplecore.erp.gui.components.labels.JLabelHQ logoLabel;
    private com.simplecore.erp.gui.components.labels.JLabelHQ lyraLabel;
    private com.simplecore.erp.gui.login.SplashPanel mikeLogoPanel;
    private javax.swing.JLabel msgLabel;
    // End of variables declaration//GEN-END:variables
}
