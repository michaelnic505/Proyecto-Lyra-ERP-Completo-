package com.simplecore.erp.gui.workspace;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.password.ChangePassword;
import com.simplecore.erp.config.database.PooledConnectionService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import com.simplecore.erp.config.date.FormatDates;
import com.simplecore.erp.config.date.TimeZones;
import com.simplecore.erp.i18n.LanguageManager;
import com.simplecore.erp.i18n.TranslationHelper;
import static com.simplecore.erp.gui.workspace.LyraFrame.mainPanel;
import static com.simplecore.erp.gui.workspace.LyraFrame.loginPanel;
import com.simplecore.erp.services.login.SessionService;

public class LyraWorkspace extends javax.swing.JPanel {

    private static LanguageManager languageManager;
    private static TranslationHelper mainMenuTree;
    private static TranslationHelper systemMessages;
    private static TranslationHelper tableColumns;
    private static TranslationHelper systemNotifications;
    private static TranslationHelper systemMenuOptions;
    
    public static TreeMenu treeMenus;    
    public static JPanel panelTransaccion;
    public static Timer dbConnectionChecker;
    
    private final int userId;
    private final String username;
    private boolean isLogged = true;
    private final String basePath = "config/i18n/";

    public LyraWorkspace(LanguageManager languageManager, int userId, String username) {
        LyraWorkspace.languageManager = languageManager;
        this.userId = userId;
        this.username = username;
        setSystemProperties();
        initComponents();
        addEvents();
        loadTreePanel();
        setSystemData(username);
        startConnectionMonitoring();
        appShutdownManager();
    }

    private void setSystemProperties() {
        mainMenuTree = new TranslationHelper(languageManager, basePath + "maintree/maintree");
        systemMessages = new TranslationHelper(languageManager, basePath + "messages/messages");
        systemNotifications = new TranslationHelper(languageManager, basePath + "notifications/notifications");
        systemMenuOptions = new TranslationHelper(languageManager, basePath + "mainmenu/mainmenus");
        tableColumns = new TranslationHelper(languageManager, basePath + "tables/tables");
        
    }
    private void setSystemData(String username) {
        TitleLabel.setText("OpenLyra™ Workspace");
        getTimeZoneByUser();
        setTimeZoneInLabel();
    }

    public static TranslationHelper getMenuTreeTranslator() {
        return mainMenuTree;
    }
    public static TranslationHelper getTranslator() {
        return systemMessages;
    }
    public static TranslationHelper getTableTranslator() {
        return tableColumns;
    }
    public static TranslationHelper getNotificationTranslator() {
        return systemNotifications;
    }
    public static TranslationHelper getMainMenuTranslator() {
        return systemMenuOptions;
    }
    public static LanguageManager getLanguageManager() {
        return languageManager;
    }
    public static JPanel mainModulesContainer(){
        return mainContainerPanel;
    }
    public static JPanel mainTreeMenu(){
        return treeMenus;
    }

    private void showCurrentConnections(int activeConnections) {
        String activity = "Activities: " + activeConnections + " connections";
        transactionLabel.setText(activity);
    }
    private void startConnectionMonitoring() {
        dbConnectionChecker = new Timer(5000, (ActionEvent e) -> {
            new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws SQLException {
                    // Ensure the pool is running before getting a connection
                    if (!PooledConnectionService.isPoolRunning()) {
                        return false;
                    }

                    try (Connection conn = PooledConnectionService.getConnection(); 
                            PreparedStatement stmt = conn.prepareStatement("SELECT 1"); 
                            ResultSet rs = stmt.executeQuery()) {

                        int activeConnections = PooledConnectionService.getActiveConnections(); // Obtener conexiones activas
                        showCurrentConnections(activeConnections);
                        new SessionService(conn).updateSessionActivity(userId);
                        
                        return rs.next();

                    } catch (SQLException ex) {
                        Logger.getLogger(LyraWorkspace.class.getName()).log(Level.SEVERE, "Error verifying connection", ex);
                        return false;
                    }

                }

                @Override
                protected void done() {
                    try {
                        if (!get()) { // If the connection is not valid
                            int response = JOptionPane.showConfirmDialog(null,
                                    "The connection was lost. Do you want to try reconnecting?",
                                    "Connection Lost", JOptionPane.YES_NO_OPTION);

                            if (response == JOptionPane.YES_OPTION) {
                                reconnect();
                            } else {
                                JOptionPane.showMessageDialog(null, "Shutting down the system...");
                                System.exit(0);
                            }
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(LyraWorkspace.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.execute();
        });

        dbConnectionChecker.start();
    }
    private void reconnect() {
        try {
            if (!PooledConnectionService.isPoolRunning()) {
                JOptionPane.showMessageDialog(null, "Database connection pool is down. Restarting the system...");
                System.exit(0);
            }

            try (Connection conn = PooledConnectionService.getConnection()) {
                if (conn != null && conn.isValid(2)) {
                    JOptionPane.showMessageDialog(null, "Reconnection successful.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to reconnect to the database.");
                    System.exit(0);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LyraWorkspace.class.getName()).log(Level.SEVERE, "Error attempting to reconnect", ex);
            JOptionPane.showMessageDialog(null, "Error attempting to reconnect to the database.");
            System.exit(0);
        }
    }
    private void appShutdownManager() {
        // Registering a shutdown hook to execute code when the JVM shuts down.
        // Registramos un shutdown hook para ejecutar código cuando la JVM se apaga.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if(isLogged){
                    cleanSession();
                }
            }
        });
    }
    private void cleanSession() {
        try (Connection connection = PooledConnectionService.getConnection()) {
            // Calling the SessionService to delete the user session before shutdown.
            // Llamamos al servicio SessionService para borrar la sesión del usuario antes del apagado.
            new SessionService(connection).deleteSession(userId);
        } catch (SQLException ex) {
            // Logging the exception if there's an error during session deletion.
            // Registramos la excepción si ocurre un error durante la eliminación de la sesión.
            Logger.getLogger(LyraWorkspace.class.getName()).log(Level.SEVERE, "Error closing session", ex);
        }
    }

    public void setTitle(String title){
        TitleLabel.setText(title);
    }

    private void loadTreePanel() {
        treeMenus = new TreeMenu(languageManager,userId ,username);
        PanelLoader.loadPanel(treeMenus, mainContainerPanel);

    }

    private static String timeZone;
    private void getTimeZoneByUser() {
        timeZone = TimeZones.getTimeZoneByUser(username);
    }
    public static String getTimeZoned() {
        return timeZone;
    }
    private void setTimeZoneInLabel() {

        String timeZoneName = FormatDates.getZoneDescription(getTimeZoned());
        Instant instant = FormatDates.getInstantCurrentTimeFromNTPServers();
        String time = FormatDates.getFormattedInstant(instant, FormatDates.DATE_AMPM_HOUR_SEC, getTimeZoned());
        
        timeZoneLabel.setText(timeZoneName + " " + time);

    }

    private void addEvents() {
        menuSetup();
        menuChangePassword();
        menuCloseSession();
        menuExit();
    }
    
    private void menuSetup() {
        menuSetup.addActionListener((ActionEvent e) -> {

        });
    }
    private void menuChangePassword() {
        menuChangePassword.addActionListener((ActionEvent e) -> {
            new ChangePassword().setVisible(true);
        });
    }
    private void menuCloseSession() {
        // Adding an ActionListener to the close session menu item.
        menuItemCloseSession.addActionListener((ActionEvent e) -> {
            // Ensuring the event runs on the Event Dispatch Thread (EDT) for UI updates.
            EventQueue.invokeLater(() -> {
                try {
                    FlatAnimatedLafChange.showSnapshot();// Snapshot for animated UI theme change (if any).

                    cleanSession();// Cleaning up the session (e.g., clearing user data).
                    clearPassword();

                    removeMenuBar();
                    returnToLoginPanel();

                    dbConnectionChecker.stop();// Stop the database connection checker service.
                    PooledConnectionService.closePool();// Close the connection pool to release resources.
                    
                    isLogged = false;
                  
                    FlatMacDarkLaf.setup();// Setting up the new LookAndFeel (Mac Dark theme).
                    FlatMacDarkLaf.repaintAllFramesAndDialogs();
                    FlatLaf.updateUI(); // Update UI to reflect the theme change.
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();

                } catch (Exception ex) {
                    // Handle any unexpected exceptions that might occur during the session closure.
                    Logger.getLogger(LyraWorkspace.class.getName()).log(Level.SEVERE, "Error during session close", ex);
                }
            });
        });
    }
    private void menuExit(){
        menuItemSalir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }
    
    private void returnToLoginPanel(){
        PanelLoader.loadPanel(loginPanel, mainPanel);// Load the login panel into the main panel.
    }
    private void removeMenuBar() {
        // Get the main JFrame from the current panel (this).
        JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(this);
        // Removing the menu bar and refreshing the window.
        ventanaPrincipal.setJMenuBar(null);
        ventanaPrincipal.revalidate(); // Revalidate the layout.
        ventanaPrincipal.repaint(); // Repaint the window to apply changes.
    }
    private void clearPassword() {
        // Clear and focus on the password field of the login panel.
        loginPanel.getPasswordField().setText(null);
        loginPanel.getPasswordField().requestFocus();
    }

    public static JLabel getTransactionLabe(){
        return transactionLabel;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barMenu = new javax.swing.JMenuBar();
        MENUHOME = new javax.swing.JMenu();
        MENU_ITEM_NUEVA_VENTANA = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        menuConfiguraciones = new javax.swing.JMenuItem();
        menuItemCloseSession = new javax.swing.JMenuItem();
        menuItemSalir = new javax.swing.JMenuItem();
        MENUACTIONS = new javax.swing.JMenu();
        menuSetup = new javax.swing.JMenuItem();
        menuChangePassword = new javax.swing.JMenuItem();
        MENUPARAMETROSMTTO = new javax.swing.JMenuItem();
        preferencesMenu = new javax.swing.JMenuItem();
        MENU_FAVORITOS = new javax.swing.JMenu();
        MENU_DETALLES = new javax.swing.JMenu();
        MENU_SISTEMA = new javax.swing.JMenu();
        MENU_AYUDA = new javax.swing.JMenu();
        panelCentral = new javax.swing.JPanel();
        titlePane = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        TitleLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        jToolBar1 = new javax.swing.JToolBar();
        homeButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        printerButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        newWindowButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        helpButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        timeZoneLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        mainContainerPanel = new javax.swing.JPanel();
        panelInferior = new javax.swing.JPanel();
        logoDb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        separator = new javax.swing.JSeparator();
        transactionLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        NotificationLabel = new com.simplecore.erp.gui.components.labels.JLabelHQBackground();
        logoLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();

        barMenu.setBackground(new java.awt.Color(102, 102, 102));

        MENUHOME.setText("Home");

        MENU_ITEM_NUEVA_VENTANA.setText("New window");
        MENUHOME.add(MENU_ITEM_NUEVA_VENTANA);
        MENUHOME.add(jSeparator6);

        menuConfiguraciones.setText("Setting");
        MENUHOME.add(menuConfiguraciones);

        menuItemCloseSession.setText("Logout");
        MENUHOME.add(menuItemCloseSession);

        menuItemSalir.setText("Exit");
        MENUHOME.add(menuItemSalir);

        barMenu.add(MENUHOME);

        MENUACTIONS.setText("Actions");

        menuSetup.setText("Configuraciones de red");
        MENUACTIONS.add(menuSetup);

        menuChangePassword.setText("Cambio de Contraseña");
        MENUACTIONS.add(menuChangePassword);

        MENUPARAMETROSMTTO.setText("Parametros de mantenimiento");
        MENUACTIONS.add(MENUPARAMETROSMTTO);

        preferencesMenu.setText("Preferencias del usuario");
        MENUACTIONS.add(preferencesMenu);

        barMenu.add(MENUACTIONS);

        MENU_FAVORITOS.setText("Favorites");
        barMenu.add(MENU_FAVORITOS);

        MENU_DETALLES.setText("Details");
        barMenu.add(MENU_DETALLES);

        MENU_SISTEMA.setText("System");
        barMenu.add(MENU_SISTEMA);

        MENU_AYUDA.setText("Help");
        barMenu.add(MENU_AYUDA);

        setBackground(new java.awt.Color(252, 254, 254));
        setRequestFocusEnabled(false);

        panelCentral.setBackground(new java.awt.Color(204, 204, 255));
        panelCentral.setLayout(new java.awt.BorderLayout());

        titlePane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        titlePane.setColor1(new java.awt.Color(207, 222, 237));
        titlePane.setColor2(new java.awt.Color(172, 200, 225));
        titlePane.setMinimumSize(new java.awt.Dimension(0, 40));
        titlePane.setName(""); // NOI18N
        titlePane.setPreferredSize(new java.awt.Dimension(149, 40));

        TitleLabel.setForeground(new java.awt.Color(77, 83, 91));
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TitleLabel.setToolTipText("");
        TitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 20)); // NOI18N
        TitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        TitleLabel.setIconTextGap(10);

        jToolBar1.setBackground(new java.awt.Color(202, 219, 236));
        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/main_window.png"))); // NOI18N
        homeButton.setFocusable(false);
        homeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(homeButton);

        printerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/printer_1.png"))); // NOI18N
        printerButton.setFocusable(false);
        printerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printerButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(printerButton);

        newWindowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/new_window_1.png"))); // NOI18N
        newWindowButton.setFocusable(false);
        newWindowButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newWindowButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(newWindowButton);

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/help.png"))); // NOI18N
        helpButton.setFocusable(false);
        helpButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        helpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(helpButton);

        timeZoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        timeZoneLabel.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        javax.swing.GroupLayout titlePaneLayout = new javax.swing.GroupLayout(titlePane);
        titlePane.setLayout(titlePaneLayout);
        titlePaneLayout.setHorizontalGroup(
            titlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(timeZoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        titlePaneLayout.setVerticalGroup(
            titlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePaneLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(titlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(titlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(timeZoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCentral.add(titlePane, java.awt.BorderLayout.NORTH);

        mainContainerPanel.setBackground(new java.awt.Color(204, 204, 204));
        mainContainerPanel.setLayout(new java.awt.BorderLayout());
        panelCentral.add(mainContainerPanel, java.awt.BorderLayout.CENTER);

        panelInferior.setBackground(new java.awt.Color(252, 254, 254));
        panelInferior.setMinimumSize(new java.awt.Dimension(0, 30));
        panelInferior.setPreferredSize(new java.awt.Dimension(1059, 30));

        logoDb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/database.png"))); // NOI18N

        separator.setForeground(new java.awt.Color(102, 102, 102));
        separator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        transactionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        transactionLabel.setText("O01");
        transactionLabel.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        NotificationLabel.setForeground(new java.awt.Color(243, 243, 243));
        NotificationLabel.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/pir25.png"))); // NOI18N

        javax.swing.GroupLayout panelInferiorLayout = new javax.swing.GroupLayout(panelInferior);
        panelInferior.setLayout(panelInferiorLayout);
        panelInferiorLayout.setHorizontalGroup(
            panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInferiorLayout.createSequentialGroup()
                .addComponent(NotificationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
                .addComponent(logoDb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transactionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelInferiorLayout.setVerticalGroup(
            panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoDb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInferiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(transactionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInferiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(separator)
                .addContainerGap())
            .addComponent(NotificationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInferiorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelCentral.add(panelInferior, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenu MENUACTIONS;
    public static javax.swing.JMenu MENUHOME;
    public static javax.swing.JMenuItem MENUPARAMETROSMTTO;
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_DETALLES;
    public static javax.swing.JMenu MENU_FAVORITOS;
    private javax.swing.JMenuItem MENU_ITEM_NUEVA_VENTANA;
    public static javax.swing.JMenu MENU_SISTEMA;
    public static com.simplecore.erp.gui.components.labels.JLabelHQBackground NotificationLabel;
    public static com.simplecore.erp.gui.components.labels.JLabelHQ TitleLabel;
    public static javax.swing.JMenuBar barMenu;
    private com.simplecore.erp.gui.components.labels.JButtonHQ helpButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ homeButton;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ logoDb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ logoLabel;
    public static javax.swing.JPanel mainContainerPanel;
    public static javax.swing.JMenuItem menuChangePassword;
    public static javax.swing.JMenuItem menuConfiguraciones;
    private javax.swing.JMenuItem menuItemCloseSession;
    public static javax.swing.JMenuItem menuItemSalir;
    public static javax.swing.JMenuItem menuSetup;
    private com.simplecore.erp.gui.components.labels.JButtonHQ newWindowButton;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JPanel panelInferior;
    public static javax.swing.JMenuItem preferencesMenu;
    private com.simplecore.erp.gui.components.labels.JButtonHQ printerButton;
    private javax.swing.JSeparator separator;
    private com.simplecore.erp.gui.components.labels.JLabelHQ timeZoneLabel;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient titlePane;
    public static com.simplecore.erp.gui.components.labels.JLabelHQ transactionLabel;
    // End of variables declaration//GEN-END:variables
}
