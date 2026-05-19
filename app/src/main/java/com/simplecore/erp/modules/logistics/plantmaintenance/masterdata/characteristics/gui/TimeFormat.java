package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui;


public class TimeFormat extends javax.swing.JPanel {

    public TimeFormat() {
        initComponents();
        addItemCombo();
    }
    
    private void addItemCombo(){
        templateTB.addItem("24h HH:mm");
        templateTB.addItem("12h hh:mm aa");
    }

    public String getFormatTime() {

        String temp = "";

        if (templateTB.getSelectedIndex() == 0) {

            int len = templateTB.getSelectedItem().toString().length();

            temp = templateTB.getSelectedItem().toString().substring(5, len);

        } else if (templateTB.getSelectedIndex() == 1) {

            int len = templateTB.getSelectedItem().toString().length();

            temp = templateTB.getSelectedItem().toString().substring(5, len);
        }

        return temp;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        templateTF = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        templateTB = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(202, 219, 236));
        setMinimumSize(new java.awt.Dimension(382, 198));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(382, 198));

        templateTF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        templateTF.setText("Template");
        templateTF.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(templateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(templateTB, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(templateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(templateTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(156, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> templateTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined templateTF;
    // End of variables declaration//GEN-END:variables
}
