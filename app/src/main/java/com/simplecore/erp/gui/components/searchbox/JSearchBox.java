
package com.simplecore.erp.gui.components.searchbox;

import javax.swing.JButton;
import javax.swing.JTextField;

public class JSearchBox extends javax.swing.JPanel {

    public JSearchBox() {
        initComponents(); 
        this.button.putClientProperty("JButton.buttonType", "roundRect" );
        this.button.setVisible(false);
    }

     public JButton getButton(){
        return this.button;
    }
    
    public JTextField getTextBox(){
        return this.Box;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Box = new javax.swing.JTextField();
        fondoNegro = new com.simplecore.erp.gui.components.searchbox.PanelNegroButtonRedondeado();
        button = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(115, 25));

        Box.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        Box.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Box.setMinimumSize(new java.awt.Dimension(10, 21));
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

        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/open_window.png"))); // NOI18N

        javax.swing.GroupLayout fondoNegroLayout = new javax.swing.GroupLayout(fondoNegro);
        fondoNegro.setLayout(fondoNegroLayout);
        fondoNegroLayout.setHorizontalGroup(
            fondoNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(Box, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(fondoNegro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Box, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
            .addComponent(fondoNegro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BoxFocusGained
        this.button.setVisible(true);
    }//GEN-LAST:event_BoxFocusGained

    private void BoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BoxFocusLost
         this.button.setVisible(false);
    }//GEN-LAST:event_BoxFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Box;
    private com.simplecore.erp.gui.components.labels.JButtonHQ button;
    private com.simplecore.erp.gui.components.searchbox.PanelNegroButtonRedondeado fondoNegro;
    // End of variables declaration//GEN-END:variables
}
