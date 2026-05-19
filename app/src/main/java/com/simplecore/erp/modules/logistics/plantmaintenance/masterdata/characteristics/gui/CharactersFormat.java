package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.Languages;

public class CharactersFormat extends javax.swing.JPanel {


    public CharactersFormat() {
        
        initComponents();
        initEvents();
    }
    
    private void initEvents(){
        numberOfChar();
        templateTextbox();
    }

    private int length=0;

    private void numberOfChar(){
        numCharsTB.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isLetter(c)){
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (numCharsTB.getText().length() > 2) {
                    e.consume();
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        if (numCharsTB.getText().isEmpty()) {
                            templateTB.setText(null);
                        } else {
                            int num = Integer.parseInt(numCharsTB.getText().trim());
                            if (num > 45) {
                                numCharsTB.setText(null);
                                templateTB.setText(null);
                            } else {
                                setLength(num);
                                template();
                            }
                        }
                    }
                }
            }

        });
        numCharsTB.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

                if (!numCharsTB.getText().isEmpty()) {
                   
                    int num = Integer.parseInt(numCharsTB.getText().trim());
                    
                    if (num > 45) {
                        numCharsTB.setText(null);
                        templateTB.setText(null);
                    } else {
                        setLength(num);
                        template();
                    }
                }else{
                    templateTB.setText(null);
                }
            }
        });
    }

    private void templateTextbox(){
        templateTB.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }
            
        });
    }
    private void template() {
        String simbol = "";

        for (int i = 0; i < length; i++) {
            simbol = simbol + "#";
        }
        templateTB.setText(simbol);
    }

    public JTextField numCharsTextBox(){
        return numCharsTB;
    }
    
    public String getTemplate(){
        return templateTB.getText();
    }
    
    
    public int getNumChars() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numberCharLabelCF = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        numCharsTB = new javax.swing.JTextField();
        templateLabelCF = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        templateTB = new javax.swing.JTextField();

        setBackground(new java.awt.Color(202, 219, 236));
        setMinimumSize(new java.awt.Dimension(382, 198));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(382, 198));

        numberCharLabelCF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numberCharLabelCF.setText("Number of Chars");
        numberCharLabelCF.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        templateLabelCF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        templateLabelCF.setText("Template");
        templateLabelCF.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        templateTB.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(templateLabelCF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numberCharLabelCF, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numCharsTB, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(templateTB, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberCharLabelCF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numCharsTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(templateLabelCF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(templateTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField numCharsTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined numberCharLabelCF;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined templateLabelCF;
    public static javax.swing.JTextField templateTB;
    // End of variables declaration//GEN-END:variables
}
