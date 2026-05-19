package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class CurrencyFormat extends javax.swing.JPanel {


    public CurrencyFormat() {
        initComponents();
        initEvents();
    }

    private void initEvents() {

        sbCurrencySimbol.getTextBox().setEditable(false);
        currencyList();
    }
    private void currencyList(){
        sbCurrencySimbol.getButton().addActionListener((e)->{
            
            CurrencyList lm = new CurrencyList(getSuperFrame());
            lm.setTitles(currencySimbolCF.getText());
            lm.setTabbedTitle("Restrictions");
            lm.setCampos(sbCurrencySimbol.getTextBox(), null);
            lm.setVisible(true);
            
        });

        sbCurrencySimbol.getTextBox().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                templateTB.setText("#,###.##");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                templateTB.setText(null);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                templateTB.setText("#,###.##");
            }

        });

    }

    public JTextField getCurrencySimbolTF(){
        return sbCurrencySimbol.getTextBox();
    }
    
    public String getTemplate(){
        return "#,###.##";
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        templateCurrencyCF = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        templateTB = new javax.swing.JTextField();
        currencySimbolCF = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        sbCurrencySimbol = new com.simplecore.erp.gui.components.searchbox.JSearchBox();

        setBackground(new java.awt.Color(202, 219, 236));
        setMinimumSize(new java.awt.Dimension(382, 198));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(382, 198));

        templateCurrencyCF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        templateCurrencyCF.setText("Template");
        templateCurrencyCF.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        templateTB.setEditable(false);

        currencySimbolCF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        currencySimbolCF.setText("Currency simbol");
        currencySimbolCF.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(templateCurrencyCF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currencySimbolCF, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(templateTB, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbCurrencySimbol, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currencySimbolCF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbCurrencySimbol, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(templateCurrencyCF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(templateTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined currencySimbolCF;
    public static com.simplecore.erp.gui.components.searchbox.JSearchBox sbCurrencySimbol;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined templateCurrencyCF;
    public static javax.swing.JTextField templateTB;
    // End of variables declaration//GEN-END:variables

    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }
    
}
