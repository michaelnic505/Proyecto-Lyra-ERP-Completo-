package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import com.simplecore.erp.gui.components.labels.JLabelHQ;



public class Nodo_JTree extends javax.swing.JPanel {

    public Nodo_JTree() {
        initComponents();
    }
    
    public void eventoBotonDesplegar(JTree_Event event, int row){

    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textoNodo = new JLabelHQ();
        descripcion_nodo = new JLabelHQ();
        btnDesplegar = new com.simplecore.erp.gui.components.labels.JLabelHQ();

        setFocusable(false);
        setOpaque(false);

        textoNodo.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N

        descripcion_nodo.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        descripcion_nodo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnDesplegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/locations/tree/deploy.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(textoNodo, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(descripcion_nodo, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDesplegar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoNodo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcion_nodo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesplegar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static com.simplecore.erp.gui.components.labels.JLabelHQ btnDesplegar;
    public javax.swing.JLabel descripcion_nodo;
    public javax.swing.JLabel textoNodo;
    // End of variables declaration//GEN-END:variables

}
