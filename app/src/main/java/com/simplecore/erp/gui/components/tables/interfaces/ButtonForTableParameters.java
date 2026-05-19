
package com.simplecore.erp.gui.components.tables.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ButtonForTableParameters extends javax.swing.JPanel {

    public ButtonForTableParameters() {
        initComponents();
    }
     
     public void eventoBotonSelection(TableButtonListener eb, int row){
         butt.addActionListener((ActionEvent e)->{
             eb.actionPerformed(row);
         });
        
    }

     public JButton getButton(){
         return butt;
     }
     


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton_HQ1 = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        butt = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        jButton1.setText("jButton1");

        jButton_HQ1.setText("jButton_HQ1");

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());
        add(butt, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JButtonHQ butt;
    private javax.swing.JButton jButton1;
    private com.simplecore.erp.gui.components.labels.JButtonHQ jButton_HQ1;
    // End of variables declaration//GEN-END:variables
}
