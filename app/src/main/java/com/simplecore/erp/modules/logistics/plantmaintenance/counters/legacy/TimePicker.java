package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TimePicker extends javax.swing.JDialog {

    public TimePicker(java.awt.Frame parent, JButton button) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(button);
        BorderFactory.createLineBorder(Color.BLACK);
    }

    private JTextField jtf;
    private String timer; 

    public void setJTextField(JTextField jtf) {
        
        this.jtf = jtf;
        this.timer = jtf.getText();

        hours.setValue(timer.substring(0,2));
        minutes.setValue(timer.substring(3, 5));
        ampm.setSelectedItem(timer.substring(6, 8));
        
    }

    DateFormat df = new SimpleDateFormat("hh:mm a");

    private void setTime() {

        try {
            String hour = hours.getValue().toString();
            String minute = minutes.getValue().toString();
            String ampms = ampm.getSelectedItem().toString();

            Date times = df.parse(hour + ":" + minute + " " + ampms);
            
            jtf.setText(df.format(times));

        } catch (ParseException ex) {
            Logger.getLogger(TimePicker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hours = new javax.swing.JSpinner();
        minutes = new javax.swing.JSpinner();
        ampm = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        hours.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        hours.setModel(new javax.swing.SpinnerListModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));

        minutes.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        minutes.setModel(new javax.swing.SpinnerListModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));

        ampm.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        ampm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ampm, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ampm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        setTime();
        this.dispose();
    }//GEN-LAST:event_formWindowDeactivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ampm;
    private javax.swing.JSpinner hours;
    private javax.swing.JSpinner minutes;
    // End of variables declaration//GEN-END:variables
}
