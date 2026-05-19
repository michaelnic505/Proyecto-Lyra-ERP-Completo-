package com.simplecore.erp.gui.login;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.simplecore.erp.config.database.DatabaseConfig;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import com.simplecore.erp.i18n.LanguageManager;
import com.simplecore.erp.i18n.LanguageSelector;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.barMenu;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.notifications.NOT;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPasswordField;
import static com.simplecore.erp.gui.workspace.LyraFrame.mainPanel;
import com.simplecore.erp.models.dbconnections.DatabaseConnectionFileReader;
import com.simplecore.erp.models.login.ActiveSession;
import com.simplecore.erp.models.login.User;
import com.simplecore.erp.services.dbconnections.ConnectionService;
import com.simplecore.erp.services.login.SessionService;
import com.simplecore.erp.services.login.UserService;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Login extends javax.swing.JPanel {

    private LanguageManager languageManager;
    private static int userId;
    private static String username;
    private static String password;
    public static LyraWorkspace lyraWorkspace;
    private static File databaseConfigFile;

    // Constructor: Initializes the components and sets up events and language
    public Login() {
        initComponents();
        setEchoChar();  // Set the echo character for the password field
        addEvents();    // Add event listeners for user and password text fields
        setPlaceHolders();  // Set placeholder text for input fields
        setLanguageManager();  // Initialize language manager based on selected language
    }

    // Set the echo character for the password field
    private void setEchoChar() {
        passtxbox.setEchoChar('•');  // (Establecer el carácter de eco para el campo de contraseña)
    }

    // Adds key event listeners to the user and password text fields
    private void addEvents() {
        userTextBox();  // (Agregar eventos al campo de usuario)
        passwordTextBox();  // (Agregar eventos al campo de contraseña)
    }

    // Set placeholders for user and password fields
    private void setPlaceHolders() {
        usertxbox.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "UserID");  // (Establecer el texto de marcador de posición para el campo de usuario)
        passtxbox.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "AccessKey");  // (Establecer el texto de marcador de posición para el campo de contraseña)
    }

    // Initialize the language manager based on the selected language
    private void setLanguageManager() {
        LanguageSelector ls = new LanguageSelector(languageSelector);  // (Inicializar el selector de idioma)
        languageManager = new LanguageManager(ls.getLanguageCode((String) languageSelector.getSelectedItem()));  // (Inicializar el gestor de idioma)

        // Listen for changes in the language selection and update the language manager
        languageSelector.addActionListener((ActionEvent e) -> {
            String selectedLanguage = ls.getLanguageCode((String) languageSelector.getSelectedItem());  // (Obtener el código de idioma seleccionado)
            languageManager.setLocaleForLanguage(selectedLanguage);  // (Actualizar la configuración de idioma)
        });
    }

    // Load the Lyra workspace after a successful login
    private void loadLyraWorkSpace(int userId,String username) {
        // Run UI updates in the EventQueue to ensure thread safety
        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            FlatLightLaf.registerCustomDefaultsSource("style");
            FlatLightLaf.setup();
            FlatLaf.updateUI();

            JFrame mainFrame = (JFrame) SwingUtilities.getRoot(this);  // (Obtener el marco principal de la aplicación)
            // Initialize Lyra workspace and execute the load panel
            lyraWorkspace = new LyraWorkspace(languageManager, userId, username);  // (Instanciar el espacio de trabajo Lyra)
            PanelLoader.loadPanel(lyraWorkspace, mainPanel);  // (Ejecutar el panel de carga)
            // Set up the menu bar and refresh the main frame
            mainFrame.setJMenuBar(barMenu);
            mainFrame.revalidate();
            mainFrame.repaint();
            // End the UI snapshot animation
            
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }

    // Add key listener to the user text box for handling Enter and Down keys
    private void userTextBox() {
        usertxbox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();  // (Obtener el código de la tecla presionada)

                // If Enter or Down key is pressed, move focus to the password field
                if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_DOWN) {
                    passtxbox.requestFocus();  // (Cambiar el foco al campo de contraseña)
                }
            }
        });
    }

    // Add key listener to the password text box for handling Enter and Up keys
    private void passwordTextBox() {
        passtxbox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();  // (Obtener el código de la tecla presionada)

                // If Enter key is pressed, validate input and attempt to connect to the database
                if (keyCode == KeyEvent.VK_ENTER) {
                    String userText = usertxbox.getText();
                    char[] passwordText = passtxbox.getPassword();

                    if (!userText.isEmpty() && passwordText.length > 0) {  // (Verificar si los campos no están vacíos)
                        processLoginData();  // (Procesar los datos de inicio de sesión)
                        connectingToDataBase();  // (Intentar conectar a la base de datos)
                    } else {
                        // Show error message if fields are empty
                        JOptionPane.showMessageDialog(null, "The login attempt was unsuccessful. Please check your credentials or ensure that the database connection is available.", "Login Failed", JOptionPane.ERROR_MESSAGE);  // (Mostrar mensaje de error si los campos están vacíos)
                        usertxbox.requestFocus();  // (Recuperar el foco en el campo de usuario)
                    }
                } else if (keyCode == KeyEvent.VK_UP) {
                    usertxbox.requestFocus();  // (Cambiar el foco al campo de usuario si se presiona la tecla Arriba)
                }
            }
        });
    }

    // Process the login data and set the user and password fields
    private void processLoginData() {
        username = usertxbox.getText();  // (Obtener el nombre de usuario)
        char[] passArray = passtxbox.getPassword();
        password = new String(passArray);  // (Convertir el array de caracteres de la contraseña a String)
    }

    // Clear the text fields after a login attempt
    private void clearFields() {
        usertxbox.setText(null);  // (Limpiar el campo de usuario)
        passtxbox.setText(null);  // (Limpiar el campo de contraseña)
    }

    // Show an error message if the connection to the database fails
    private void showErrorConnectionMsg() {
        JOptionPane.showMessageDialog(this, NOT.msg(NOT.ACCESS_DENIED), NOT.msg(NOT.TITLE), JOptionPane.WARNING_MESSAGE);  // (Mostrar mensaje de error de conexión)
        usertxbox.requestFocus();  // (Recuperar el foco en el campo de usuario)
    }
    
    public static void setDatabaseConfigFile(File aDatabaseConfigFile) {
        databaseConfigFile = aDatabaseConfigFile;
    }

    // Attempt to connect to the database using the provided credentials
    private void connectingToDataBase() {
        DatabaseConnectionFileReader readFile = ConnectionService.readConnectionFile(databaseConfigFile);
        Connection dataBaseConnection = null;

        try {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Reading database configuration from file...");
            DatabaseConfig dbConfig = new DatabaseConfig(
                    username,
                    password,
                    readFile.getConnector(),
                    readFile.getHostname(),
                    readFile.getPort(),
                    readFile.getDatabase()
            );
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Database configuration loaded successfully: {0}", dbConfig);
            // Inicializar el pool de conexiones con los datos de configuración
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Initializing connection pool...");
            PooledConnectionService.initializeConnection(dbConfig);

            // Try to obtain a connection
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Attempting to obtain a database connection...");
            dataBaseConnection = PooledConnectionService.getConnection();

            // Verify that the connection is valid
            if (dataBaseConnection != null && !dataBaseConnection.isClosed() && PooledConnectionService.isPoolRunning()) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Database connection established successfully.");

                // Crear instancias de servicios
                UserService userService = new UserService(dataBaseConnection);
                SessionService sessionService = new SessionService(dataBaseConnection);

                // Autenticar usuario
                User authenticatedUser = userService.authenticateUser(username, password);
                if (authenticatedUser == null) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Invalid username or password.");
                    showErrorAuthenticationMsg();
                    return;
                }

                userId = authenticatedUser.getId();
                // Verificar si ya tiene una sesión activa
                ActiveSession existingSession = sessionService.getActiveSession(userId);
                if (existingSession != null) {
                    Logger.getLogger(getClass().getName()).log(Level.WARNING, "User already has an active session on terminal: {0}", existingSession.getTerminal());
                    showActiveSessionMsg(existingSession); // Mostrar mensaje con información de la sesión activa
                    // Cerrar conexión antes de salir
                    PooledConnectionService.closeConnection(dataBaseConnection);
                    return;
                }

                // Registrar nueva sesión
                String sessionId = java.util.UUID.randomUUID().toString();
                String[] terminalInfo = getTerminalInfo();
                String terminal = terminalInfo[0];
                String ip = terminalInfo[1];
                sessionService.createSession(userId, sessionId, terminal, ip);
                
                // Proceed with loading the workspace
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Loading the Lyra workspace...");
                loadLyraWorkSpace(userId,username); // Load workspace if connection is successful

                // Clear input fields
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Clearing input fields...");
                clearFields(); // Clear input fields
            } else {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Database connection failed or pool is not running.");
                showErrorConnectionMsg(); // Show error if connection is not valid
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error validating the database connection.", ex);
            showErrorConnectionMsg();
        } finally {
            // Always return the connection to the pool
            if (dataBaseConnection != null) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Returning connection to the pool...");
                PooledConnectionService.closeConnection(dataBaseConnection);
            }
        }
    }

    private void showErrorAuthenticationMsg() {
        JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showActiveSessionMsg(ActiveSession session) {
        JOptionPane.showMessageDialog(this,
                "The user already has an active session.\n"
                + "Session ID: " + session.getSessionId() + "\n"
                + "Terminal: " + session.getTerminal() + "\n"
                + "IP Address: " + session.getIpAddress() + "\n"
                + "Login Time: " + session.getLoginTime(),
                "Active Session", JOptionPane.WARNING_MESSAGE);
    }

    private String[] getTerminalInfo() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String terminalName = localHost.getHostName();
            String ipAddress = localHost.getHostAddress();
            return new String[]{terminalName, ipAddress};
        } catch (UnknownHostException e) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Could not retrieve terminal information.", e);
            return new String[]{"Unknown", "Unknown"};
        }
    }

    public JPasswordField getPasswordField() {
        return passtxbox;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDegradado = new com.simplecore.erp.gui.login.LoginGradientPanel();
        logoPrincipal = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        usertxbox = new javax.swing.JTextField();
        logo1 = new javax.swing.JLabel();
        passtxbox = new javax.swing.JPasswordField();
        gradientLabel2 = new com.simplecore.erp.gui.components.labels.GradientLabel();
        languageSelector = new javax.swing.JComboBox<>();

        setRequestFocusEnabled(false);

        logoPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/splash/main_logo.png"))); // NOI18N
        logoPrincipal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        usertxbox.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        usertxbox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usertxbox.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        logo1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        logo1.setForeground(new java.awt.Color(221, 221, 221));
        logo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo1.setText("América Systems Development");

        passtxbox.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        passtxbox.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        gradientLabel2.setText("OpenLyra™");
        gradientLabel2.setEndColor(new java.awt.Color(229, 229, 229));
        gradientLabel2.setFont(new java.awt.Font("JetBrains Mono", 1, 26)); // NOI18N
        gradientLabel2.setStartColor(new java.awt.Color(160, 160, 160));

        languageSelector.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelDegradadoLayout = new javax.swing.GroupLayout(panelDegradado);
        panelDegradado.setLayout(panelDegradadoLayout);
        panelDegradadoLayout.setHorizontalGroup(
            panelDegradadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDegradadoLayout.createSequentialGroup()
                .addContainerGap(313, Short.MAX_VALUE)
                .addGroup(panelDegradadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(passtxbox, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usertxbox, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gradientLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(languageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(313, Short.MAX_VALUE))
        );
        panelDegradadoLayout.setVerticalGroup(
            panelDegradadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDegradadoLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(logoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(gradientLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(logo1)
                .addGap(19, 19, 19)
                .addComponent(usertxbox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(passtxbox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(languageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDegradado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDegradado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.GradientLabel gradientLabel2;
    private javax.swing.JComboBox<String> languageSelector;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logoPrincipal;
    private com.simplecore.erp.gui.login.LoginGradientPanel panelDegradado;
    protected static javax.swing.JPasswordField passtxbox;
    public static javax.swing.JTextField usertxbox;
    // End of variables declaration//GEN-END:variables
}
