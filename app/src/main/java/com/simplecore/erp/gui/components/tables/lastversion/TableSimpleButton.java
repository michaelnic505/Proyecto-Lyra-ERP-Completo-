
package com.simplecore.erp.gui.components.tables.lastversion;

import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import java.awt.event.ActionEvent;


public class TableSimpleButton extends javax.swing.JPanel {


    public TableSimpleButton() {
        initComponents();
    }
    
     public void eventoBotonSelection(TableEventSimpleButton evt, int row){
         butt.addActionListener((ActionEvent e)->{
             evt.selectionRow(row);
         });
         
     }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        butt = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setOpaque(false);

        butt.setBackground(new java.awt.Color(202, 219, 236));
        butt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(165, 179, 193)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butt, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton butt;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
