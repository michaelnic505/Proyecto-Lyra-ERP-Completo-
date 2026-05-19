
package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui;

import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import static com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui.CharacteristicsForm.tablaRestricciones;
import static com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui.CharacteristicsForm.tablaValores;


public class DateFormat extends javax.swing.JPanel {
    
    public DateFormat() {
        initComponents();
        addCombo();
        changeComboSelection();
    }

    public JComboBox getComboDateFormat(){
        return templateTB;
    }
    
    private void addCombo(){
        templateTB.addItem("dd.MM.yyyy");
        templateTB.addItem("yyyy.MM.dd");
        templateTB.addItem("MM.dd.yyyy");
        templateTB.addItem("dd.MM.yy");
        templateTB.addItem("yy.MM.dd");
    }
    
    private void changeComboSelection() {

        templateTB.addItemListener((ItemEvent e) -> {
            
            int rows = tablaValores.getRowCount();
            int cols = tablaValores.getColumnCount();
            
            for (int i = 0; i < rows; i++) {
                for (int c = 1; c < cols; c++) {
                    tablaValores.setValueAt(null, i, c);
                }
            }
            
            int rows2 = tablaRestricciones.getRowCount();
            int cols2 = tablaRestricciones.getColumnCount();
            
            for (int i = 0; i < rows2; i++) {
                for (int c = 1; c < cols2; c++) {
                    tablaRestricciones.setValueAt(null, i, c);
                }
            }
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        templateDate = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        templateTB = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(202, 219, 236));
        setMinimumSize(new java.awt.Dimension(382, 198));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(382, 198));

        templateDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        templateDate.setText("Date Template");
        templateDate.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        templateTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(templateDate, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(templateTB, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(templateDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(templateTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(156, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined templateDate;
    public static javax.swing.JComboBox<String> templateTB;
    // End of variables declaration//GEN-END:variables
}
