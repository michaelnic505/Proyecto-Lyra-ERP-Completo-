package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;


public class NumberFormat extends javax.swing.JPanel {

    public NumberFormat() {
        initComponents();
        initEvents();
    }

    private void initEvents() {

        sbUnitMeasuring.getTextBox().setEditable(false);
        
        numberOfChar();
        numberOfDecimals();
        unitOfMeasureList();

    }


    
    private void numberOfChar() {
        numCharsTB.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (numCharsTB.getText().length() > 2) {
                    e.consume();
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                       
                        int num0 = Integer.parseInt(numCharsTB.getText().trim());
                        
                        if (num0 > 45) {
                            numCharsTB.setText(null);
                        } else {

                            if (!numCharsTB.getText().isEmpty() && !decimalPlacesTB.getText().isEmpty()) {

                                int num = Integer.parseInt(numCharsTB.getText());
                                String t1 = "";

                                for (int i = 0; i < num; i++) {
                                    t1 = t1 + "_";
                                }

                                int num2 = Integer.parseInt(decimalPlacesTB.getText());
                                String t2 = ".";

                                for (int i = 0; i < num2; i++) {
                                    t2 = t2 + "_";
                                }

                                templateTB.setText(t1 + t2);
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

                    int num0 = Integer.parseInt(numCharsTB.getText().trim());

                    if (num0 > 45) {
                        numCharsTB.setText(null);
                    } else {
                        if (!numCharsTB.getText().isEmpty() && !decimalPlacesTB.getText().isEmpty()) {

                            int num = Integer.parseInt(numCharsTB.getText());
                            String t1 = "";

                            for (int i = 0; i < num; i++) {
                                t1 = t1 + "_";
                            }

                            int num2 = Integer.parseInt(decimalPlacesTB.getText());
                            String t2 = ".";

                            for (int i = 0; i < num2; i++) {
                                t2 = t2 + "_";
                            }

                            templateTB.setText(t1 + t2);
                        }
                    }
                }
            }

        });
    }
    private void numberOfDecimals() {
        decimalPlacesTB.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (decimalPlacesTB.getText().length() > 1) {
                    e.consume();
                } 
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(!numCharsTB.getText().isEmpty() && !decimalPlacesTB.getText().isEmpty()){
                        
                        int num = Integer.parseInt(numCharsTB.getText());
                        String t1 = "";
                        
                        for(int i =0; i < num;i++){
                            t1 = t1 + "_";
                        }
                        
                        
                        int num2 = Integer.parseInt(decimalPlacesTB.getText());
                        String t2 = ".";
                        
                        for(int i = 0 ; i < num2;i++){
                            t2 = t2 +"_";
                        }
                        
                        templateTB.setText(t1+t2);
                    }
                }
            }

        });
        decimalPlacesTB.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                    if(!numCharsTB.getText().isEmpty() && !decimalPlacesTB.getText().isEmpty()){
                        
                        int num = Integer.parseInt(numCharsTB.getText());
                        String t1 = "";
                        
                        for(int i =0; i < num;i++){
                            t1 = t1 + "_";
                        }
                        
                        
                        int num2 = Integer.parseInt(decimalPlacesTB.getText());
                        String t2 = ".";
                        
                        for(int i = 0 ; i < num2;i++){
                            t2 = t2 +"_";
                        }
                        
                        templateTB.setText(t1+t2);
                    }
            }

        });
    }
    private void unitOfMeasureList() {
        sbUnitMeasuring.getButton().addActionListener((e) -> {
            
            UnitOfMeasureList lum = new UnitOfMeasureList(getSuperFrame());
            lum.setCampos(sbUnitMeasuring.getTextBox(), unitDescription);
            lum.setVisible(true);
        
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        decimalPlaces = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        decimalPlacesTB = new javax.swing.JTextField();
        templateTB = new javax.swing.JTextField();
        numberOfChars = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        numCharsTB = new javax.swing.JTextField();
        templateNF = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        unitMeasureLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        sbUnitMeasuring = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        unitDescription = new javax.swing.JLabel();

        setBackground(new java.awt.Color(202, 219, 236));
        setMinimumSize(new java.awt.Dimension(382, 198));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(382, 198));

        decimalPlaces.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        decimalPlaces.setText("Decimal places");
        decimalPlaces.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        templateTB.setEditable(false);

        numberOfChars.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numberOfChars.setText("Number of Chars");
        numberOfChars.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        templateNF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        templateNF.setText("Template");
        templateNF.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        unitMeasureLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        unitMeasureLB.setText("Unit of Measure");
        unitMeasureLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        unitDescription.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(unitMeasureLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(decimalPlaces, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numberOfChars, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(templateNF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(numCharsTB, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(templateTB, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(decimalPlacesTB, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 68, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sbUnitMeasuring, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unitDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(numberOfChars, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numCharsTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(decimalPlaces, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decimalPlacesTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(unitMeasureLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbUnitMeasuring, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(templateNF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(templateTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined decimalPlaces;
    public static javax.swing.JTextField decimalPlacesTB;
    public static javax.swing.JTextField numCharsTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined numberOfChars;
    public static com.simplecore.erp.gui.components.searchbox.JSearchBox sbUnitMeasuring;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined templateNF;
    public static javax.swing.JTextField templateTB;
    private javax.swing.JLabel unitDescription;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined unitMeasureLB;
    // End of variables declaration//GEN-END:variables

    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }
}
