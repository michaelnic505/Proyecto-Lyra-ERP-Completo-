
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.operaciones_orden_texto_explicativo;



public class TextoExplicativo extends javax.swing.JPanel {


    public TextoExplicativo() {
        initComponents();
    }
    
     public void eventosBotonTextoExp(TableTextoExpEvent event, int row) {

        buttonTexto.addActionListener((e) -> {
            
            event.agregarTextoExplicativo(row);
        
        });


    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonTexto = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        buttonTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/to_draft.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonTexto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonTexto;
    // End of variables declaration//GEN-END:variables
}
