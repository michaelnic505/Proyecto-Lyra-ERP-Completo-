package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.TreeMenu.transactionPanel;

public class Texto_Explicativo extends javax.swing.JPanel {

    private TableRowSorter<DefaultTableModel> filtro;

    public Texto_Explicativo() {

        initComponents();
        botonSalir();
        textAreaProperties();
        textAreaPane.requestFocus();
    }

    private void textAreaProperties() {
        
        textAreaPane.setMargin(new Insets(20, 50, 100, 850));
        
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            PanelLoader.loadPanel(transactionPanel, mainContainerPanel);

        });
    }
    
    
    LyraTable table;
    int row;
    int column;

    public void setTableCell(LyraTable table, int row, int column) {
       
        this.table = table;
        this.row = row;
        this.column = column;
    
    }
    
    public void setText(String text){
        textAreaPane.setText(text);
    }
    
    JPanel panelAnterior;
    public void setPanelAnterior(JPanel panel){
        this.panelAnterior = panel;
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir2 = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jPanel_Rounded_Corners_Degradado7 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        LABELTITULOMODULO = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaPane = new javax.swing.JTextArea();
        btnOkOperacion = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnEliminarLineaOperacion = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir2.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N
        btnSalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addComponent(btnSalir2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel_Rounded_Corners_Degradado7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado7.setColor1(new java.awt.Color(207, 222, 237));
        jPanel_Rounded_Corners_Degradado7.setColor2(new java.awt.Color(172, 200, 225));

        LABELTITULOMODULO.setFont(new java.awt.Font("Barlow Condensed", 1, 18)); // NOI18N
        LABELTITULOMODULO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LABELTITULOMODULO.setText("Text Editor");
        LABELTITULOMODULO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado7Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado7);
        jPanel_Rounded_Corners_Degradado7.setLayout(jPanel_Rounded_Corners_Degradado7Layout);
        jPanel_Rounded_Corners_Degradado7Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LABELTITULOMODULO, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado7Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LABELTITULOMODULO)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(238, 245, 250));

        textAreaPane.setColumns(20);
        textAreaPane.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        textAreaPane.setLineWrap(true);
        textAreaPane.setRows(5);
        textAreaPane.setWrapStyleWord(true);
        jScrollPane1.setViewportView(textAreaPane);

        btnOkOperacion.setBackground(new java.awt.Color(226, 210, 144));
        btnOkOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnOkOperacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N
        btnOkOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkOperacionActionPerformed(evt);
            }
        });

        btnEliminarLineaOperacion.setBackground(new java.awt.Color(226, 210, 144));
        btnEliminarLineaOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminarLineaOperacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/delete_trash.png"))); // NOI18N
        btnEliminarLineaOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLineaOperacionActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 445, Short.MAX_VALUE)
                .addComponent(btnOkOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarLineaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 486, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnOkOperacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarLineaOperacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel_Rounded_Corners_Degradado7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarLineaOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLineaOperacionActionPerformed

        textAreaPane.setText(null);

    }//GEN-LAST:event_btnEliminarLineaOperacionActionPerformed

    private void btnOkOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkOperacionActionPerformed

        if (textAreaPane.getText().isEmpty()) {

          new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.WARNING);


            table.setValueAt(textAreaPane.getText(), row, column);
            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

        } else {

            table.setValueAt(textAreaPane.getText(), row, column);
            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
            table.editCellAt(row, column);

        }


    }//GEN-LAST:event_btnOkOperacionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir2ActionPerformed
      PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
    }//GEN-LAST:event_btnSalir2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel LABELTITULOMODULO;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnEliminarLineaOperacion;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnOkOperacion;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir2;
    private javax.swing.JPanel jPanel2;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JTextArea textAreaPane;
    // End of variables declaration//GEN-END:variables
}
