package com.simplecore.erp.gui.dbconnections;

import com.simplecore.erp.models.dbconnections.DatabaseConnection;
import com.simplecore.erp.services.dbconnections.ConnectionService;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import javax.swing.JOptionPane;

public class ConnectionEditor extends javax.swing.JDialog {

    private final File url;
    private int pX, pY;

    public ConnectionEditor(java.awt.Frame parent, boolean modal, File url) {
        super(parent, modal);
        this.url = url;
        initComponents();
        setupUI();
        initializeEvents();
    }


    private void setupUI() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/connectionsdb/pir20.png")));
        this.setTitle("Connections");
        connectorTF.setText("jdbc:mysql://");
    }

    private void initializeEvents() {

        btnCancelar.addActionListener((ActionEvent e) -> dispose());
        btnExit.addActionListener((ActionEvent e) -> dispose());
        btnAceptar.addActionListener((ActionEvent e) -> handleAccept());

        panelMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pX = e.getX();
                pY = e.getY();
            }
        });

        panelMenu.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(getLocation().x + e.getX() - pX, getLocation().y + e.getY() - pY);
            }
        });
    }

    private void handleAccept() {
        if (isValidInput()) {
            DatabaseConnection databaseConnection = new DatabaseConnection.Builder()
                    .setConnector(connectorTF.getText())
                    .setPort(portTF.getText())
                    .setHostname(hostnameTF.getText())
                    .setDb(dataBaseTF.getText())
                    .setFilename(nameFileTF.getText())
                    .build();
            
            ConnectionService.createConnectionFile(url, databaseConnection);

            JOptionPane.showMessageDialog(this, "Action complete", "Complete", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Empty fields", "Fields", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidInput() {
        return !connectorTF.getText().isEmpty()
                && !portTF.getText().isEmpty()
                && !hostnameTF.getText().isEmpty()
                && !dataBaseTF.getText().isEmpty()
                && !nameFileTF.getText().isEmpty();
    }

    public void setConnector(String connector){
        connectorTF.setText(connector);
    }
    public void setHostName(String hostname){
        hostnameTF.setText(hostname);
    }
    public void setPort(String port){
        portTF.setText(port);
    }
    public void setDataBase(String dataBase){
        dataBaseTF.setText(dataBase);
    }
    public void setFileName(String fileName){
        nameFileTF.setText(fileName);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new com.simplecore.erp.gui.components.panels.JPanelRoundedCorners();
        jPanel2 = new javax.swing.JPanel();
        panelMenu = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnExit = new com.simplecore.erp.gui.components.labels.JButtonCustom();
        jLabel_HQ1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        panelBase = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        logo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel_HQ2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        connectorTF = new javax.swing.JTextField();
        jLabel_HQ3 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        portTF = new javax.swing.JTextField();
        jLabel_HQ4 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        hostnameTF = new javax.swing.JTextField();
        jLabel_HQ5 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        dataBaseTF = new javax.swing.JTextField();
        btnHelp = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnCancelar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnAceptar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jLabel_HQ6 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        nameFileTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelPrincipal.setBackground(new java.awt.Color(40, 75, 138));
        panelPrincipal.setEsquinaInfDerecha(10);
        panelPrincipal.setEsquinaInfIzquierda(10);
        panelPrincipal.setEsquinaSupDerecha(10);
        panelPrincipal.setEsquinaSupIzquierda(10);

        jPanel2.setBackground(new java.awt.Color(238, 245, 250));

        panelMenu.setColor1(new java.awt.Color(55, 103, 191));
        panelMenu.setColor2(new java.awt.Color(40, 75, 138));
        panelMenu.setEsquinaInfDerecha(10);
        panelMenu.setEsquinaInfIzquierda(10);

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connectionsdb/close_window.png"))); // NOI18N

        jLabel_HQ1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel_HQ1.setText("Add New Connection");
        jLabel_HQ1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_HQ1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_HQ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7))
        );

        panelBase.setEsquinaInfDerecha(5);
        panelBase.setEsquinaInfIzquierda(5);
        panelBase.setEsquinaSupDerecha(5);
        panelBase.setEsquinaSupIzquierda(5);

        logo.setFont(new java.awt.Font("Barlow Condensed", 3, 36)); // NOI18N
        logo.setForeground(new java.awt.Color(221, 221, 221));
        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setText("Lyra");

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel_HQ2.setText("MySQL >>");
        jLabel_HQ2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel_HQ2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        connectorTF.setEditable(false);

        jLabel_HQ3.setText("Port :");
        jLabel_HQ3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel_HQ4.setText("Hostname :");
        jLabel_HQ4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel_HQ5.setText("Data base :");
        jLabel_HQ5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnHelp.setText("Help");

        btnCancelar.setText("Cancel");

        btnAceptar.setText("Accept");

        jLabel_HQ6.setText("Name :");
        jLabel_HQ6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel1.setText(".ly");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_HQ6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_HQ5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_HQ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_HQ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataBaseTF)
                            .addComponent(hostnameTF)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(connectorTF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)
                                        .addComponent(jLabel_HQ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(nameFileTF, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel_HQ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_HQ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_HQ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hostnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_HQ5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataBaseTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_HQ6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameFileTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBaseLayout = new javax.swing.GroupLayout(panelBase);
        panelBase.setLayout(panelBaseLayout);
        panelBaseLayout.setHorizontalGroup(
            panelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaseLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBaseLayout.setVerticalGroup(
            panelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBaseLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(logo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnAceptar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnCancelar;
    private com.simplecore.erp.gui.components.labels.JButtonCustom btnExit;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnHelp;
    protected static javax.swing.JTextField connectorTF;
    protected static javax.swing.JTextField dataBaseTF;
    protected static javax.swing.JTextField hostnameTF;
    private javax.swing.JLabel jLabel1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ3;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ4;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ5;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel logo;
    protected static javax.swing.JTextField nameFileTF;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBase;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelMenu;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedCorners panelPrincipal;
    protected static javax.swing.JTextField portTF;
    // End of variables declaration//GEN-END:variables
}
