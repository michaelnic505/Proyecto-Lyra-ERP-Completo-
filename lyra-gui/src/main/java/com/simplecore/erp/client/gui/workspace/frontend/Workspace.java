package com.simplecore.erp.client.gui.workspace.frontend;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.simplecore.erp.client.controllers.gui.PanelLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import com.simplecore.erp.client.i18n.LanguageManager;
import com.simplecore.erp.client.i18n.TranslationHelper;
import static com.simplecore.erp.client.gui.workspace.frontend.MainFrame.mainPanel;
import static com.simplecore.erp.client.gui.workspace.frontend.MainFrame.loginPanel;
import com.simplecore.erp.client.config.dates.FormatDates;
import com.simplecore.erp.client.controllers.servicebuttons.ButtonServices;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.utils.sound.Sound;
import com.simplecore.erp.shared.requests.types.LogoutRequest;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Workspace extends javax.swing.JPanel {

    private static LanguageManager languageManager;
    private static TranslationHelper treeTranslator;
    private static TranslationHelper messagesTranslator;
    private static TranslationHelper tablesTranslator;
    private static TranslationHelper notificationsTranslator;
    private static TranslationHelper menuTranslator;
    private static TranslationHelper countriesTranslator;
    private static final Map<TranslatorType,TranslationHelper> translator = new HashMap();
    
    public static TreeMenu treeMenus;    
    public static JPanel panelTransaccion;
    public static Timer dbConnectionChecker;
    
    private final ActiveSession activeSession;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    

    public Workspace(LanguageManager languageManager,ActiveSession activeSession,
            ObjectOutputStream output, ObjectInputStream input) {
        
        Workspace.languageManager = languageManager;
        this.activeSession = activeSession;
        this.input = input;
        this.output = output;
        initSystemProperties();
        initComponents();
        initEvents();
        initTree();
        initServerMonitoring();
        appShutdownManager();
        
    }
    private void initSystemProperties() {
        treeTranslator = new TranslationHelper(languageManager, TranslatorType.TREE);
        messagesTranslator = new TranslationHelper(languageManager, TranslatorType.MESSAGES);
        notificationsTranslator = new TranslationHelper(languageManager, TranslatorType.NOTIFICATIONS);
        menuTranslator = new TranslationHelper(languageManager, TranslatorType.MAIN_MENU);
        tablesTranslator = new TranslationHelper(languageManager, TranslatorType.TABLES);
        countriesTranslator = new TranslationHelper(languageManager, TranslatorType.COUNTRIES);

        translator.put(TranslatorType.TREE, treeTranslator);
        translator.put(TranslatorType.MESSAGES, messagesTranslator);
        translator.put(TranslatorType.NOTIFICATIONS, notificationsTranslator);
        translator.put(TranslatorType.MAIN_MENU, menuTranslator);
        translator.put(TranslatorType.TABLES, tablesTranslator);
        translator.put(TranslatorType.COUNTRIES, countriesTranslator);
        
    }
    public static TranslationHelper translators(TranslatorType key){
        return translator.get(key);
    }
    public static LanguageManager languageManager() {
        return languageManager;
    }

    private void showCurrentConnections(String message) {
        transactionLabel.setText(message);
    }
    
    private void initServerMonitoring() {
        dbConnectionChecker = new Timer(3000, (ActionEvent e) -> {
            new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() {
                    if (sessionClosed) {
                        return false;
                    }
                    return sendPingToServer();
                }

                @Override
                protected void done() {
                    try {
                        boolean isConnected = get(); // Obtiene el resultado de doInBackground
                        if (!isConnected) {
                            // Manejar la desconexión
                            dbConnectionChecker.stop();
                            showCurrentConnections("Server disconnected...");
                            Thread.sleep(3000);
                            logoutSession();
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(Workspace.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }.execute();
        });

        dbConnectionChecker.start();
    }
    
    private String serverName;
    
    private boolean sendPingToServer() {
        try {
            output.writeObject(activeSession.getUserId()); // Enviar el userId como un objeto Integer
            output.flush(); // Asegurarse de que los datos se envíen
            Object response = input.readObject(); // Leer la respuesta del servidor
            serverName = (String) response;
            showCurrentConnections("Linked to :>> " + serverName);

            return true; // Procesar la respuesta
        } catch (ClassNotFoundException | IOException ex) {
            return false;
        }
    }

    private boolean sessionClosed = false;

    private void sendToCloseSession() {
        try {
            output.writeObject(new LogoutRequest(true,activeSession.getSessionId(), activeSession.getUserId()));
            output.flush();
            showCurrentConnections("Server disconnected...");
        } catch (IOException ex) {
            Logger.getLogger(Workspace.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            sessionClosed = true;
        }
    }

    private void appShutdownManager() {
        // Registering a shutdown hook to execute code when the JVM shuts down.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
               if(!sessionClosed){
                   if(dbConnectionChecker.isRunning()){
                       dbConnectionChecker.stop();
                   }
                   sendToCloseSession();
               }
            }
        });
    }
    
    private void initTree() {
        treeMenus = new TreeMenu(activeSession,output,input);
        PanelLoader.loadPanel(treeMenus, mainContainerPanel);
    }
    private void setTimezoneInLabel() {
        String timezoneName = FormatDates.getZoneDescription(activeSession.getTimezone());
        Instant instant = FormatDates.getInstantCurrentTimeFromNTPServers();
        String time = FormatDates.getFormattedInstant(instant, FormatDates.DATE_AMPM_HOUR_SEC, activeSession.getTimezone());
        timezoneLabel.setText(timezoneName + " " + time);
    }
    private void initEvents() {
        initMenuBar();
        setTimezoneInLabel();
        configureMenuToolBar();
    }

    public void menuCloseSession() {
        // Adding an ActionListener to the close session menu item.
        menuItemCloseSession.addActionListener((ActionEvent e) -> {
            // Ensuring the event runs on the Event Dispatch Thread (EDT) for UI updates.
            sendToCloseSession();
        });
    }
    public void logoutSession() {
        EventQueue.invokeLater(() -> {
            try {
                FlatAnimatedLafChange.showSnapshot();// Snapshot for animated UI theme change (if any).
                removeMenuBar();
                returnToLoginPanel();
                // Stop the database connection checker service.
                
                FlatMacDarkLaf.setup();// Setting up the new LookAndFeel (Mac Dark theme).
                FlatMacDarkLaf.repaintAllFramesAndDialogs();
                FlatLaf.updateUI(); // Update UI to reflect the theme change.
                FlatAnimatedLafChange.hideSnapshotWithAnimation();

            } catch (Exception ex) {
                // Handle any unexpected exceptions that might occur during the session closure.
                Logger.getLogger(Workspace.class.getName()).log(Level.SEVERE, "Error during session close", ex);
            }
        });
    }
    private void menuExit(){
        menuItemSalir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }
    
    private void returnToLoginPanel(){
        PanelManager.clearStack();
        PanelLoader.loadPanel(loginPanel, mainPanel);// Load the login panel into the main panel.
    }
    private void removeMenuBar() {
        // Get the main JFrame from the current panel (this).
        JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(mainPanel);
        // Removing the menu bar and refreshing the window.
        ventanaPrincipal.setJMenuBar(null);
        ventanaPrincipal.revalidate(); // Revalidate the layout.
        ventanaPrincipal.repaint(); // Repaint the window to apply changes.
    }

    private void initMenuBar() {
        JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(mainPanel);
        ventanaPrincipal.setJMenuBar(barMenu);
        ventanaPrincipal.revalidate();
        ventanaPrincipal.repaint();

        menuCloseSession();
        menuExit();
        UIManager.put("MenuBar.height", 50);
    }
    
    private void configureMenuToolBar() {
        transactionButton.setIcon(new CustomSVGIcon("/icons/svg/open_transaction_icon.svg",new Dimension(24,24)));
      
        saveButton.setIcon(new CustomSVGIcon("/icons/svg/save_button_icon.svg",new Dimension(22,22)));
        saveButton.setSound(Sound.DONE);
        saveButton.setEnabled(false);
        
        backButton.setIcon(new CustomSVGIcon("/icons/svg/back_panel_icon.svg",new Dimension(24,24)));
        backButton.setSound(Sound.CLOSE_DIALOG);

        closeButton.setIcon(new CustomSVGIcon("/icons/svg/close_transaction_icon.svg",new Dimension(24,24)));
        closeButton.setSound(Sound.CLOSE);
        
        cancelButton.setIcon(new CustomSVGIcon("/icons/svg/cancel_button_icon.svg",new Dimension(24,24)));
        cancelButton.setSound(Sound.CLOSE_WINDOW);
        
        serverConnectionLb.setIcon(new CustomSVGIcon("/icons/svg/dbconnection.svg",new Dimension(24,24)));
    }
    
 
    public static JPanel getMainContainer(){
        return mainContainerPanel;
    }
    public static JPanel getMainTreeMenu(){
        return treeMenus;
    }
    public static ButtonServices getSaveButton(){
        return saveButton;
    }
    public static ButtonServices getBackButton(){
        return backButton;
    }
    public static ButtonServices getCloseButton(){
        return closeButton;
    }
    public static ButtonServices getCancelButton(){
        return cancelButton;
    }
    public static JLabel getTransactionLb(){
        return transactionLb;
    }
    public static JFrame getFrame(){
        return (JFrame)SwingUtilities.getRoot(mainContainerPanel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barMenu = new javax.swing.JMenuBar();
        homeMenu = new javax.swing.JMenu();
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
        buttonServices1 = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        titlePane = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        timezoneLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        menuToolBar = new javax.swing.JToolBar();
        saveButton = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        separator = new javax.swing.JToolBar.Separator();
        backButton = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        closeButton = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        cancelButton = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        toolBar = new javax.swing.JToolBar();
        homeButton = new com.simplecore.erp.client.gui.components.labels.JButtonHQ();
        printerButton = new com.simplecore.erp.client.gui.components.labels.JButtonHQ();
        newWindowButton = new com.simplecore.erp.client.gui.components.labels.JButtonHQ();
        helpButton = new com.simplecore.erp.client.gui.components.labels.JButtonHQ();
        consoleToolBar = new javax.swing.JToolBar();
        consoleLb = new javax.swing.JLabel();
        transactionButton = new javax.swing.JButton();
        transactionTextBox = new javax.swing.JTextField();
        mainContainerPanel = new javax.swing.JPanel();
        panelInferior = new javax.swing.JPanel();
        panelNotification = new javax.swing.JPanel();
        NotificationLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQBackground();
        panelLogo = new javax.swing.JPanel();
        logoLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        panelConnection = new javax.swing.JPanel();
        transactionLb = new javax.swing.JLabel();
        serverConnectionLb = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        transactionLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();

        barMenu.setBackground(new java.awt.Color(102, 102, 102));

        homeMenu.setText("Home");

        MENU_ITEM_NUEVA_VENTANA.setText("New window");
        homeMenu.add(MENU_ITEM_NUEVA_VENTANA);
        homeMenu.add(jSeparator6);

        menuConfiguraciones.setText("Setting");
        homeMenu.add(menuConfiguraciones);

        menuItemCloseSession.setText("Logout");
        homeMenu.add(menuItemCloseSession);

        menuItemSalir.setText("Exit");
        homeMenu.add(menuItemSalir);

        barMenu.add(homeMenu);

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

        buttonServices1.setText("buttonServices1");

        setBackground(new java.awt.Color(252, 254, 254));
        setMinimumSize(new java.awt.Dimension(700, 300));
        setRequestFocusEnabled(false);

        titlePane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(222, 229, 235), 1, true));
        titlePane.setColor1(new java.awt.Color(247, 247, 255));
        titlePane.setColor2(new java.awt.Color(247, 247, 255));
        titlePane.setMinimumSize(new java.awt.Dimension(0, 40));
        titlePane.setName(""); // NOI18N
        titlePane.setPreferredSize(new java.awt.Dimension(149, 40));

        timezoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        timezoneLabel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N

        menuToolBar.setRollover(true);
        menuToolBar.setOpaque(false);

        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuToolBar.add(saveButton);
        menuToolBar.add(separator);

        backButton.setFocusable(false);
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuToolBar.add(backButton);

        closeButton.setFocusable(false);
        closeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        closeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuToolBar.add(closeButton);

        cancelButton.setFocusable(false);
        cancelButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuToolBar.add(cancelButton);

        toolBar.setBackground(new java.awt.Color(202, 219, 236));
        toolBar.setRollover(true);
        toolBar.setOpaque(false);

        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/main_window.png"))); // NOI18N
        homeButton.setFocusable(false);
        homeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(homeButton);

        printerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/printer_1.png"))); // NOI18N
        printerButton.setFocusable(false);
        printerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printerButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(printerButton);

        newWindowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/new_window_1.png"))); // NOI18N
        newWindowButton.setFocusable(false);
        newWindowButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newWindowButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(newWindowButton);

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/help.png"))); // NOI18N
        helpButton.setFocusable(false);
        helpButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        helpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(helpButton);

        menuToolBar.add(toolBar);

        consoleToolBar.setRollover(true);
        consoleToolBar.setOpaque(false);

        consoleLb.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        consoleLb.setText("Console");
        consoleToolBar.add(consoleLb);

        transactionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        transactionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        consoleToolBar.add(transactionButton);
        consoleToolBar.add(transactionTextBox);

        javax.swing.GroupLayout titlePaneLayout = new javax.swing.GroupLayout(titlePane);
        titlePane.setLayout(titlePaneLayout);
        titlePaneLayout.setHorizontalGroup(
            titlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(consoleToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addGap(127, 127, 127)
                .addComponent(timezoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );
        titlePaneLayout.setVerticalGroup(
            titlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePaneLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(titlePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(timezoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consoleToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainContainerPanel.setBackground(new java.awt.Color(204, 204, 204));
        mainContainerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainContainerPanel.setLayout(new java.awt.BorderLayout());

        panelInferior.setBackground(new java.awt.Color(247, 247, 255));
        panelInferior.setMinimumSize(new java.awt.Dimension(0, 30));
        panelInferior.setOpaque(false);
        panelInferior.setPreferredSize(new java.awt.Dimension(1059, 30));
        panelInferior.setLayout(new java.awt.BorderLayout());

        panelNotification.setMinimumSize(new java.awt.Dimension(169, 21));
        panelNotification.setOpaque(false);
        panelNotification.setPreferredSize(new java.awt.Dimension(400, 32));
        panelNotification.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 1));

        NotificationLabel.setForeground(new java.awt.Color(243, 243, 243));
        NotificationLabel.setText("");
        NotificationLabel.setFont(new java.awt.Font("Roboto Medium", 1, 13)); // NOI18N
        NotificationLabel.setPreferredSize(new java.awt.Dimension(300, 28));
        panelNotification.add(NotificationLabel);

        panelInferior.add(panelNotification, java.awt.BorderLayout.WEST);

        panelLogo.setOpaque(false);
        panelLogo.setPreferredSize(new java.awt.Dimension(100, 27));
        panelLogo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 1));

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/pir25.png"))); // NOI18N
        logoLabel.setPreferredSize(new java.awt.Dimension(27, 28));
        panelLogo.add(logoLabel);

        panelInferior.add(panelLogo, java.awt.BorderLayout.CENTER);

        panelConnection.setOpaque(false);
        panelConnection.setPreferredSize(new java.awt.Dimension(400, 27));
        panelConnection.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 1));

        transactionLb.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        transactionLb.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        transactionLb.setText("Workspace |");
        transactionLb.setMinimumSize(new java.awt.Dimension(150, 17));
        transactionLb.setPreferredSize(new java.awt.Dimension(150, 28));
        panelConnection.add(transactionLb);

        serverConnectionLb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lyraworkspace/database.png"))); // NOI18N
        panelConnection.add(serverConnectionLb);

        transactionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        transactionLabel.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        transactionLabel.setMinimumSize(new java.awt.Dimension(200, 17));
        transactionLabel.setPreferredSize(new java.awt.Dimension(200, 28));
        panelConnection.add(transactionLabel);

        panelInferior.add(panelConnection, java.awt.BorderLayout.EAST);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePane, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
            .addComponent(panelInferior, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(mainContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                    .addGap(16, 16, 16)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePane, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 483, Short.MAX_VALUE)
                .addComponent(panelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(mainContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addGap(42, 42, 42)))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenu MENUACTIONS;
    public static javax.swing.JMenuItem MENUPARAMETROSMTTO;
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_DETALLES;
    public static javax.swing.JMenu MENU_FAVORITOS;
    private javax.swing.JMenuItem MENU_ITEM_NUEVA_VENTANA;
    public static javax.swing.JMenu MENU_SISTEMA;
    public static com.simplecore.erp.client.gui.components.labels.JLabelHQBackground NotificationLabel;
    private static com.simplecore.erp.client.controllers.servicebuttons.ButtonServices backButton;
    public static javax.swing.JMenuBar barMenu;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices buttonServices1;
    private static com.simplecore.erp.client.controllers.servicebuttons.ButtonServices cancelButton;
    private static com.simplecore.erp.client.controllers.servicebuttons.ButtonServices closeButton;
    private javax.swing.JLabel consoleLb;
    private javax.swing.JToolBar consoleToolBar;
    private com.simplecore.erp.client.gui.components.labels.JButtonHQ helpButton;
    private com.simplecore.erp.client.gui.components.labels.JButtonHQ homeButton;
    public static javax.swing.JMenu homeMenu;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ logoLabel;
    public static javax.swing.JPanel mainContainerPanel;
    public static javax.swing.JMenuItem menuChangePassword;
    public static javax.swing.JMenuItem menuConfiguraciones;
    private javax.swing.JMenuItem menuItemCloseSession;
    public static javax.swing.JMenuItem menuItemSalir;
    public static javax.swing.JMenuItem menuSetup;
    private javax.swing.JToolBar menuToolBar;
    private com.simplecore.erp.client.gui.components.labels.JButtonHQ newWindowButton;
    private javax.swing.JPanel panelConnection;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JPanel panelNotification;
    public static javax.swing.JMenuItem preferencesMenu;
    private com.simplecore.erp.client.gui.components.labels.JButtonHQ printerButton;
    private static com.simplecore.erp.client.controllers.servicebuttons.ButtonServices saveButton;
    private javax.swing.JToolBar.Separator separator;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ serverConnectionLb;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ timezoneLabel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient titlePane;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JButton transactionButton;
    public static com.simplecore.erp.client.gui.components.labels.JLabelHQ transactionLabel;
    public static javax.swing.JLabel transactionLb;
    private javax.swing.JTextField transactionTextBox;
    // End of variables declaration//GEN-END:variables
}
