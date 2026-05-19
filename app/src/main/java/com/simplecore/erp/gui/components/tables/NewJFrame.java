/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.simplecore.erp.gui.components.tables;

import com.simplecore.erp.gui.components.tables.interfaces.TableButtonListener;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author user
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        limpiartabla();
        setbuttons();
        dynamicTableButtons1.setSelectedRowsList(selectedRows);
    }

    ArrayList<Integer> selectedRows = new ArrayList<>();
    
    
     private void limpiartabla(){
         dynamicTableButtons1.addMouseListener(new MouseAdapter(){
             @Override
             public void mousePressed(MouseEvent e) {
                  if (dynamicTableButtons1.columnAtPoint(e.getPoint()) != 0) {
                    selectedRows.clear();
                  } 
             }
             
         });
     }
    
    private void setbuttons(){
        dynamicTableButtons1.addTableButtonListener(new TableButtonListener() {
            @Override
            public void actionPerformed(int row) {
                if (selectedRows.contains(row)) { 
                    selectedRows.remove(Integer.valueOf(row));
                } else {
                    selectedRows.add(row);
                }
                dynamicTableButtons1.clearSelection();
                for (int selectedRow : selectedRows) {
                    dynamicTableButtons1.addRowSelectionInterval(selectedRow, selectedRow);
                    dynamicTableButtons1.addColumnSelectionInterval(1, dynamicTableButtons1.getColumnCount() - 1);
                }
                dynamicTableButtons1.getDefaultEditor(Object.class).stopCellEditing();
            }
        });

        dynamicTableButtons1.getColumnModel().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedColumn = dynamicTableButtons1.getSelectedColumn();
                if (selectedColumn == 0) {
                    // Si se selecciona la columna 0, cambiamos la selección a la columna 1
                    dynamicTableButtons1.changeSelection(dynamicTableButtons1.getSelectedRow(), 1, false, false);
                }
            }
        });

    }


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        dynamicTableButtons1 = new com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable1.setShowGrid(true);
        jTable1.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton1");

        jButton3.setText("jButton1");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton1");

        jButton5.setText("jButton1");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        dynamicTableButtons1.setCellSelectionEnabled(true);
        dynamicTableButtons1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(dynamicTableButtons1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(439, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        int row = 0;

        if (selectedRows.contains(row)) {
            selectedRows.remove(Integer.valueOf(row));
        } else {
            selectedRows.add(row);
        }
        jTable1.clearSelection();

        for (int selectedRow : selectedRows) {

            jTable1.addRowSelectionInterval(selectedRow, selectedRow);
                        jTable1.addColumnSelectionInterval(0, 0);
            jTable1.addColumnSelectionInterval(2, jTable1.getColumnCount() - 1);
//            
//            
//                        jTable1.setColumnSelectionInterval(0, 1);
//            jTable1.setColumnSelectionInterval(2, jTable1.getColumnCount() - 1);

        }

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        
        int row = 2;

        if (selectedRows.contains(row)) {
            selectedRows.remove(Integer.valueOf(row));
        } else {
            selectedRows.add(row);
        }
        jTable1.clearSelection();

        for (int selectedRow : selectedRows) {

            jTable1.addRowSelectionInterval(selectedRow, selectedRow);
                        jTable1.addColumnSelectionInterval(0, 0);
            jTable1.addColumnSelectionInterval(2, jTable1.getColumnCount() - 1);
            
//            
//                        jTable1.setColumnSelectionInterval(0, 1);
//            jTable1.setColumnSelectionInterval(2, jTable1.getColumnCount() - 1);

        }

        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        int row = 4;

        if (selectedRows.contains(row)) {
            selectedRows.remove(Integer.valueOf(row));
        } else {
            selectedRows.add(row);
        }
        jTable1.clearSelection();

        for (int selectedRow : selectedRows) {

            jTable1.addRowSelectionInterval(selectedRow, selectedRow);
            jTable1.addColumnSelectionInterval(0, 0);
            jTable1.addColumnSelectionInterval(2, jTable1.getColumnCount() - 1);
            
            
         //   jTable1.setColumnSelectionInterval(0, 0);
         //   jTable1.setColumnSelectionInterval(2, jTable1.getColumnCount() - 1);

        }

        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons dynamicTableButtons1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
