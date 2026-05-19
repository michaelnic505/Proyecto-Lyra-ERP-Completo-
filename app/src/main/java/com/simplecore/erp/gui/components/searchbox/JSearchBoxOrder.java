package com.simplecore.erp.gui.components.searchbox;


import javax.swing.JButton;
import javax.swing.JTextField;

public class JSearchBoxOrder extends javax.swing.JPanel {

    public JSearchBoxOrder() {
        initComponents();
        button.setVisible(false);
        this.button.putClientProperty("JButton.buttonType", "roundRect");
    }

    public JButton getButton(){
        return button;
    }
    
    public JTextField getTextBox(){
        return Box;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Box = new javax.swing.JTextField();
        fondoNegro = new com.simplecore.erp.gui.components.searchbox.PanelNegroButtonRedondeado();
        button = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        Box.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BoxFocusLost(evt);
            }
        });

        fondoNegro.setColor1(new java.awt.Color(102, 102, 102));
        fondoNegro.setColor2(new java.awt.Color(0, 0, 0));
        fondoNegro.setOpaque(false);

        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/ventana.png"))); // NOI18N

        javax.swing.GroupLayout fondoNegroLayout = new javax.swing.GroupLayout(fondoNegro);
        fondoNegro.setLayout(fondoNegroLayout);
        fondoNegroLayout.setHorizontalGroup(
            fondoNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );
        fondoNegroLayout.setVerticalGroup(
            fondoNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Box, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(fondoNegro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Box, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fondoNegro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BoxFocusGained
        button.setVisible(true);
    }//GEN-LAST:event_BoxFocusGained

    private void BoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BoxFocusLost
        button.setVisible(false);
    }//GEN-LAST:event_BoxFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Box;
    private com.simplecore.erp.gui.components.labels.JButtonHQ button;
    private com.simplecore.erp.gui.components.searchbox.PanelNegroButtonRedondeado fondoNegro;
    // End of variables declaration//GEN-END:variables
}
