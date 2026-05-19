package com.simplecore.erp.client.gui.login;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import com.simplecore.erp.client.i18n.LanguageManager;
import com.simplecore.erp.client.i18n.LanguageSelector;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.controllers.gui.PanelLoader;
import com.simplecore.erp.shared.exceptions.ServerNotAvailableException;
import javax.swing.JPasswordField;
import static com.simplecore.erp.client.gui.workspace.frontend.MainFrame.mainPanel;
import com.simplecore.erp.client.i18n.LanguageUtil;
import com.simplecore.erp.client.models.connections.ServerConnectionFileReader;
import com.simplecore.erp.client.services.login.LoginService;
import com.simplecore.erp.client.services.connections.ConnectionService;
import com.simplecore.erp.shared.exceptions.InvalidCredentialsException;
import com.simplecore.erp.shared.exceptions.SessionAlreadyActiveException;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends javax.swing.JPanel {

    private LanguageManager languageManager;
    private static int userId;
    private static String username;
    private static String password;
    public static Workspace lyraWorkspace;
    private static File serverConnectionFile;

    
    // Constructor: Initializes the components and sets up events and language
    public Login() {
        initComponents();
        addEvents();    // Add event listeners for user and password text fields
        setPlaceHolders();  // Set placeholder text for input fields
        setLanguageManager();  // Initialize language manager based on selected language
        readServerConnectionFile();
    }

    // Set the echo character for the password field
    private void passwordEchoChar() {
        passtxbox.setEchoChar('•');  // (Establecer el carácter de eco para el campo de contraseña)
    }
    // Adds key event listeners to the user and password text fields
    private void addEvents() {
        usernameTextBoxEvents();  // (Agregar eventos al campo de usuario)
        passwordTextBoxEvents();  // (Agregar eventos al campo de contraseña)
        passwordEchoChar();
    }
    // Set placeholders for user and password fields
    private void setPlaceHolders() {
        usertxbox.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "UserID");  // (Establecer el texto de marcador de posición para el campo de usuario)
        passtxbox.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "AccessKey");  // (Establecer el texto de marcador de posición para el campo de contraseña)
    }
    // Initialize the language manager based on the selected language
    private void setLanguageManager() {
        LanguageSelector ls = new LanguageSelector(languageSelector);  // (Inicializar el selector de idioma)
        languageManager = new LanguageManager(LanguageUtil.getLanguageCode((String) languageSelector.getSelectedItem()));  // (Inicializar el gestor de idioma)

        // Listen for changes in the language selection and update the language manager
        languageSelector.addActionListener((ActionEvent e) -> {
            String selectedLanguage = LanguageUtil.getLanguageCode((String) languageSelector.getSelectedItem());  // (Obtener el código de idioma seleccionado)
            languageManager.setLocaleForLanguage(selectedLanguage);  // (Actualizar la configuración de idioma)
        });
    }
    // Add key listener to the user text box for handling Enter and Down keys
    private void usernameTextBoxEvents() {
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
    private void passwordTextBoxEvents() {
        passtxbox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();  // (Obtener el código de la tecla presionada)

                // If Enter key is pressed, validate input and attempt to connect to the database
                if (keyCode == KeyEvent.VK_ENTER) {

                    String userText = usertxbox.getText();
                    char[] passwordText = passtxbox.getPassword();

                    if (!userText.isEmpty() && passwordText.length > 0) {  // (Verificar si los campos no están vacíos)
                        setLoginData();  // (Procesar los datos de inicio de sesión)
                        connectingToServer();  // (Intentar conectar a la base de datos)
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
    private void setLoginData() {
        username = usertxbox.getText();  // (Obtener el nombre de usuario)
        char[] passArray = passtxbox.getPassword();
        password = new String(passArray);  // (Convertir el array de caracteres de la contraseña a String)
    }
    // Clear the text fields after a login attempt
    private void clearFields() {
        usertxbox.setText(null);  // (Limpiar el campo de usuario)
        passtxbox.setText(null);  // (Limpiar el campo de contraseña)
    }

    public static void setServerConnectionFile(File aDatabaseConfigFile) {
        serverConnectionFile = aDatabaseConfigFile;
    }

    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private void startSocket() throws IOException {
        socket = new Socket(serverAddress, serverPort);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());

    }

    private void closeSocket() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                socket = null;
            }

        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (output != null) {
                output.close();
                output = null;
            }

        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (input != null) {
                input.close();
                input = null;
            }

        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    private void readServerConnectionFile() {
        ServerConnectionFileReader serverConnection = ConnectionService.readConnectionFile(serverConnectionFile);
        serverAddress = serverConnection.getHostname();
        serverPort = serverConnection.getPort();
    }

    /**
     * Attempts to establish a connection with the server and authenticate the user. (Intenta establecer una conexión con el servidor y autenticar al usuario).
     */
    private void connectingToServer(){
        // Validate that both username and password are provided (Valida que tanto el usuario como la contraseña sean ingresados)
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.",
                    "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
            
        try {
            startSocket();
            // Create a login service instance with the server's address and port (Crea una instancia del servicio de inicio de sesión con la dirección y puerto del servidor)
            LoginService loginService = new LoginService(input,output);
            // Authenticate the user and retrieve the active session (Autentica al usuario y recupera la sesión activa)
            ActiveSession activeSession = loginService.authenticate(username, password);
            // If the session is new, load the workspace (Si la sesión es nueva, carga el espacio de trabajo)
            
            if (activeSession.isNewSession()) {
                loadLyraWorkSpace(activeSession, output, input);
            }
            
        } catch (SessionAlreadyActiveException ex) {
            // Handle case where the user already has an active session (Maneja el caso donde el usuario ya tiene una sesión activa)
            showActiveSessionMsg(ex.getActiveSession());
            closeSocket();
        } catch (InvalidCredentialsException e) {
            // Handle case where the username or password is incorrect (Maneja el caso donde el usuario o la contraseña son incorrectos)
            showInavlidCredentialsException(e);
            closeSocket();
        } catch (ServerNotAvailableException e) {
            // Handle case where the server is not reachable (Maneja el caso donde el servidor no está disponible)
            showServerNotAvailableMsg();
            closeSocket();
        } catch (IOException ex) {
            showServerNotAvailableMsg();
        } 
    }

    // Load the Lyra workspace after a successful login
    private void loadLyraWorkSpace(ActiveSession activeSession,ObjectOutputStream output, ObjectInputStream input) {
        // Run UI updates in the EventQueue to ensure thread safety
        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            FlatLightLaf.registerCustomDefaultsSource("style");
            FlatLightLaf.setup();
            FlatLaf.updateUI();

            JFrame mainFrame = (JFrame) SwingUtilities.getRoot(this);  // (Obtener el marco principal de la aplicación)
            // Initialize Lyra workspace and execute the load panel
            lyraWorkspace = new Workspace(languageManager, activeSession,output,input);  // (Instanciar el espacio de trabajo Lyra)
            PanelLoader.loadPanel(lyraWorkspace, mainPanel);  // (Ejecutar el panel de carga)
            // Set up the menu bar and refresh the main frame
            mainFrame.revalidate();
            mainFrame.repaint();
            // End the UI snapshot animation

            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }

    
    private void showInavlidCredentialsException(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(),
                "Authentication Failed", JOptionPane.WARNING_MESSAGE);
    }
    private void showServerNotAvailableMsg() {
        JOptionPane.showMessageDialog(this, "Unable to connect to server", "Connection Error", JOptionPane.ERROR_MESSAGE);
    }
    private void showActiveSessionMsg(ActiveSession session) {
        JOptionPane.showMessageDialog(this,
                """
                The user already has an active session.
                Session ID: """ + session.getSessionId() + "\n"
                + "Terminal: " + session.getTerminal() + "\n"
                + "IP Address: " + session.getIpAddress() + "\n"
                + "Login Time: " + session.getLoginTime(),
                "Active Session", JOptionPane.WARNING_MESSAGE);
    }

    public JPasswordField getPasswordField() {
        return passtxbox;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDegradado = new com.simplecore.erp.client.gui.login.LoginGradientPanel();
        logoPrincipal = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        usertxbox = new javax.swing.JTextField();
        logo1 = new javax.swing.JLabel();
        passtxbox = new javax.swing.JPasswordField();
        gradientLabel2 = new com.simplecore.erp.client.gui.components.labels.GradientLabel();
        languageSelector = new javax.swing.JComboBox<>();
        gradientLabel3 = new com.simplecore.erp.client.gui.components.labels.GradientLabel();

        setRequestFocusEnabled(false);

        logoPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/splash/main_logo.png"))); // NOI18N
        logoPrincipal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        usertxbox.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        usertxbox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usertxbox.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        logo1.setFont(new java.awt.Font("Caveat", 0, 14)); // NOI18N
        logo1.setForeground(new java.awt.Color(221, 221, 221));
        logo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo1.setText("We've evolved. It's time for the next phase.");

        passtxbox.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        passtxbox.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        gradientLabel2.setText("Lyra");
        gradientLabel2.setEndColor(new java.awt.Color(229, 229, 229));
        gradientLabel2.setFont(new java.awt.Font("Poppins", 0, 26)); // NOI18N
        gradientLabel2.setStartColor(new java.awt.Color(160, 160, 160));

        languageSelector.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        gradientLabel3.setText("ERP");
        gradientLabel3.setEndColor(new java.awt.Color(229, 229, 229));
        gradientLabel3.setFont(new java.awt.Font("Poppins SemiBold", 0, 26)); // NOI18N
        gradientLabel3.setStartColor(new java.awt.Color(160, 160, 160));

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
                    .addGroup(panelDegradadoLayout.createSequentialGroup()
                        .addComponent(gradientLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(gradientLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(languageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(313, Short.MAX_VALUE))
        );
        panelDegradadoLayout.setVerticalGroup(
            panelDegradadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDegradadoLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(logoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelDegradadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gradientLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gradientLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(logo1)
                .addGap(10, 10, 10)
                .addComponent(usertxbox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(passtxbox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(languageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
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
    private com.simplecore.erp.client.gui.components.labels.GradientLabel gradientLabel2;
    private com.simplecore.erp.client.gui.components.labels.GradientLabel gradientLabel3;
    private javax.swing.JComboBox<String> languageSelector;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logoPrincipal;
    private com.simplecore.erp.client.gui.login.LoginGradientPanel panelDegradado;
    protected static javax.swing.JPasswordField passtxbox;
    public static javax.swing.JTextField usertxbox;
    // End of variables declaration//GEN-END:variables
}
