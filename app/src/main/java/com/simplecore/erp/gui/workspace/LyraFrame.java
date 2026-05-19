package com.simplecore.erp.gui.workspace;

import com.simplecore.erp.gui.login.Login;
import com.simplecore.erp.controllers.gui.PanelLoader;

public class LyraFrame extends javax.swing.JFrame {
    // Declare login object (Declara el objeto login)
    public static Login loginPanel;

    // Constructor initializes components and sets up the login (El constructor inicializa los componentes y configura el login)
    public LyraFrame() {
        initComponents();
        loadLoginPanel();  // Initialize login process (Inicia el proceso de login)
    }

    // Load login panel (Carga el panel de login)
    private void loadLoginPanel() {
        // Check if login already exists and load it (Verifica si el login ya existe y lo carga)
        if (loginPanel != null) {
            PanelLoader.loadPanel(loginPanel, mainPanel);  // Execute load panel for login (Ejecuta el panel de carga para el login)
        } else {
            // If login doesn't exist, create a new one (Si no existe el login, crea uno nuevo)
            loginPanel = new Login();
            PanelLoader.loadPanel(loginPanel, mainPanel);  // Execute load panel for the new login (Ejecuta el panel de carga para el nuevo login)
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OpenLyra™ Workspace");
        setBackground(new java.awt.Color(153, 153, 153));
        setIconImage(getIconImage());
        setName("framePrincipal"); // NOI18N

        mainPanel.setBackground(new java.awt.Color(153, 153, 153));
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1219, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
