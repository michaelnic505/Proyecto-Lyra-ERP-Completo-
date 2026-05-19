package com.simplecore.erp.server.main;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
import com.google.gson.JsonObject;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import com.simplecore.erp.server.config.json.ConfigManager;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import org.json.JSONObject;

public class LyraConsole {

    private static final int PORT = 12345;
    private static final String CONFIG_FILE = "config.json";
    private static volatile boolean running = true;
    private static final int MAX_THREADS = 100;
    private static ExecutorService threadPool;
    private static ServerSocket serverSocket;
    public static MultiWindowTextGUI textGUI;
    public static Screen screen;
    public static Panel panel;
    public static TextBox logBox;
    public static SwingTerminalFrame terminalFrame;
    private static final List<ClientHandler> activeHandlers = Collections.synchronizedList(new ArrayList<>());


    public static void main(String[] args) {
        // Verificar si ya hay una instancia en ejecución
        if (isAppRunning()) {
            restoreWindow();
            System.exit(0); // Salir de la nueva instancia
        } else {
            SwingTerminal();
        }
    }

        // Método para verificar si la aplicación ya está en ejecución
    private static boolean isAppRunning() {
        try{
            ServerSocket serverSocketApp = new ServerSocket(PORT, 1, InetAddress.getLocalHost());
            // Escuchar conexiones entrantes en un hilo separado
            new Thread(() -> {
                while (true) {
                    try (Socket socket = serverSocketApp.accept()) {
                        // Restaurar la ventana cuando se recibe una conexión
                        terminalFrame.setVisible(true);
                        terminalFrame.toFront();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return false; // No hay otra instancia en ejecución
        } catch (IOException e) {
            return true; // Ya hay una instancia en ejecución
        }
    }
    
        // Método para restaurar la ventana de la instancia en ejecución
    private static void restoreWindow() {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), PORT)) {
            // Enviar un mensaje a la instancia en ejecución para restaurar la ventana
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(1); // Enviar un byte (puede ser cualquier dato)
            outputStream.flush(); // Asegúrate de enviar el mensaje
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void SwingTerminal() {
        try {

            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setInitialTerminalSize(new TerminalSize(120, 50));

            terminalFrame = terminalFactory.createSwingTerminal();
            terminalFrame.setIconImage(new ImageIcon(LyraConsole.class.getResource("/icon/console.png")).getImage());
            terminalFrame.setTitle("Lyra Server Operations");
            terminalFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            terminalFrame.setVisible(true);

            screen = new TerminalScreen(terminalFrame);
            screen.startScreen();

            // Paso 2: Creación de la ventana y el panel
            textGUI = new MultiWindowTextGUI(screen);
            BasicWindow window = new BasicWindow("LYRA SERVER ENVIROMENT - SIMPLE CORE");
            panel = new Panel();

            // -----> Agregar aquí el área de logs
            logBox = new TextBox().setReadOnly(true);
            logBox.setPreferredSize(new TerminalSize(80, 15)); // Define tamaño del área de logs

            panel.addComponent(new Label("Server Logs:"));
            panel.addComponent(logBox);
            window.setComponent(panel);

            showWelcomeMessage();

            panel.addComponent(logBox);
            textGUI.addWindowAndWait(window);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final int MAX_LOG_LINES = 10; // Número máximo de líneas permitidas

    public static void logMessage(String message) {
        String currentText = logBox.getText();
        String[] lines = currentText.split("\n");

        // Si supera el límite, eliminar las primeras líneas
        if (lines.length >= MAX_LOG_LINES) {
            currentText = String.join("\n", Arrays.copyOfRange(lines, lines.length - MAX_LOG_LINES + 1, lines.length));
        }

        // Agregar la nueva línea y actualizar el log
        logBox.setText(currentText + "\n" + message);

        try {
            textGUI.updateScreen(); // Refrescar la interfaz
        } catch (IOException ex) {
            Logger.getLogger(LyraConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void showWelcomeMessage() {
        if (!Files.exists(Paths.get(CONFIG_FILE))) {
            panel.removeAllComponents();
            Label label = new Label("""
                               
                               =======================================================
                                            Welcome to Lyra Core+ Server                   
                               =======================================================
                                  
                               =======================================================
                                            Version 1.0.0 - Release 2023         
                               =======================================================
                               (c) 2023 SimpleCore Technologies.     
                               All rights reserved.                   
                               Licensed Materials - Property of SimpleCore.
                               Unauthorized use, duplication, or distribution
                               is strictly prohibited by law.          
                               =======================================================
                               
                               No configuration file found.            
                               Starting initial setup...               
                               Please provide the necessary settings.
                               
                               =======================================================
                               For support, contact: support@simplecoretech.com    
                               Visit us at: www.simplecoretech.com             
                               =======================================================
                               """);
            panel.addComponent(label);

            Button configureServerButton = new Button("CONFIGURE THE SERVER", LyraConsole::configureServer);
            Button exitButton = new Button("QUIT", LyraConsole::exitApplication);

            panel.addComponent(configureServerButton);
            panel.addComponent(exitButton);
            try {
                // Actualizar la pantalla
                textGUI.updateScreen();
            } catch (IOException ex) {
                Logger.getLogger(LyraConsole.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            panel.removeAllComponents();
            Label header = new Label("""
                               ======================================================
                                             Lyra Server - Control Panel             
                               ======================================================
                                 Version 1.0.0 - Release 2023                        
                                 (c) 2023 SimpleCore Technologies. All rights reserved.
                                 Licensed Materials - Property of SimpleCore.        
                                 Unauthorized use, duplication, or distribution      
                                 is strictly prohibited by law.                      
                               ------------------------------------------------------
                                 Design by Michael F. Sánchez                        
                                 Republic of Nicaragua                               
                                 Built with cutting-edge technology for reliability. 
                               ------------------------------------------------------
                                 For support, contact: support@simplecoretech.com    
                                 Visit us at: www.simplecoretech.com                 
                               ======================================================
                               """);

            header.setPosition(TerminalPosition.OFFSET_1x1);

            panel.addComponent(header);

            startServerButton = new Button("Start Server", LyraConsole::startServer);
            restartSeverButton = new Button("Restart server", LyraConsole::restartServer);
            stopServerButton = new Button("Stop server", LyraConsole::stopServer);
            exitServerButton = new Button("Exit", LyraConsole::exitApplication);

            restartSeverButton.setEnabled(false);
            stopServerButton.setEnabled(false);

            panel.addComponent(startServerButton);
            panel.addComponent(restartSeverButton);
            panel.addComponent(stopServerButton);
            panel.addComponent(exitServerButton);


        }
    }
    static Button stopServerButton;
    static Button startServerButton;
    static Button restartSeverButton;
    static Button exitServerButton;

    private static void startServer() {

        running = true;
        exitServerButton.setEnabled(false);
        startServerButton.setEnabled(false);
        restartSeverButton.setEnabled(true);
        stopServerButton.setEnabled(true);

        logMessage("[Lyra Server]: Starting Lyra Server Services...");
        logMessage("[Lyra Server]: Configuration file detected. Starting server..."); // Server starting message / Mensaje de inicio del servidor
        JsonObject config = ConfigManager.readConfig(); // Reading the configuration file / Leyendo el archivo de configuración
        if (config == null) {
            // Error message if config loading fails / Mensaje de error si falla la carga de la configuración
            logMessage("[Lyra Server]: Failed to load config. Exiting...");
            return;
        }

        String serverName = config.getAsJsonObject("server").get("server_name").getAsString();
        int port = config.getAsJsonObject("server").get("server_port").getAsInt(); // Getting port from config / Obteniendo el puerto desde la configuración
        logMessage("[Lyra Server]: Welcome to " + serverName + " server.");
        logMessage("[Lyra Server]: Running server on port " + port + "..."); // Confirming server port / Confirmando el puerto del servidor

        //int clientConnectionsLimit = config.getAsJsonObject("clients").get("threads").getAsInt();
        threadPool = Executors.newFixedThreadPool(MAX_THREADS); // Initializing thread pool / Inicializando el pool de hilos

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port); // Starting server socket / Iniciando el socket del servidor
                while (running) {
                    Socket clientSocket = serverSocket.accept(); // Accepting new client connections / Aceptando nuevas conexiones de clientes
                    logMessage("[Lyra Server]: New connection accepted: " + clientSocket.getInetAddress()); // Client connected message / Mensaje de conexión de cliente
                    ClientHandler clientHandler = new ClientHandler(clientSocket, serverName);
                    activeHandlers.add(clientHandler);
                    threadPool.submit(clientHandler); // Submitting client handler task / Enviando la tarea del manejador del cliente al pool de hilos
                }

            } catch (IOException e) {
                if (running) { // Only log errors if the server is running / Solo registrar errores si el servidor está en ejecución
                    logMessage("[Lyra Server]: Error on the server: " + e.getMessage());
                }
            }

        }).start();

    }
    
    public static void closeClientSocket(Socket clientSocket) {
        for (ClientHandler handler : activeHandlers) {
            if (handler.getClientSocket() == clientSocket) {
                activeHandlers.remove(handler);
                logMessage("[Lyra Server]: The client has logged out :" + clientSocket.getInetAddress());
            }
        }
    }

    private static void restartServer() {

        boolean confirmed = new MessageDialogBuilder()
                .setTitle("Confirmation")
                .setText("Are you sure you want RESTART the server?")
                .addButton(MessageDialogButton.Yes)
                .addButton(MessageDialogButton.No)
                .build()
                .showDialog(textGUI) == MessageDialogButton.Yes;
        if (confirmed) {

            shutdownServer();
            startServer();

            exitServerButton.setEnabled(false);
            startServerButton.setEnabled(false);
            restartSeverButton.setEnabled(true);
            stopServerButton.setEnabled(true);
        }

    }

    private static void stopServer() {
        boolean confirmed = new MessageDialogBuilder()
                .setTitle("Confirmation")
                .setText("Are you sure you want STOP the server?")
                .addButton(MessageDialogButton.Yes)
                .addButton(MessageDialogButton.No)
                .build()
                .showDialog(textGUI) == MessageDialogButton.Yes;
        if (confirmed) {

            exitServerButton.setEnabled(true);
            startServerButton.setEnabled(false);
            restartSeverButton.setEnabled(true);
            stopServerButton.setEnabled(false);

            shutdownServer();
            panel.removeAllComponents();

            Label header = new Label(headerMsg);
            panel.addComponent(header);

            panel.addComponent(startServerButton);
            panel.addComponent(restartSeverButton);
            panel.addComponent(stopServerButton);
            panel.addComponent(exitServerButton);

            panel.addComponent(logBox);

            try {
                // Actualizar la pantalla
                textGUI.updateScreen();
            } catch (IOException ex) {
                Logger.getLogger(LyraConsole.class.getName()).log(Level.SEVERE, null, ex);
            }

            logMessage("[Lyra Server]: Server is stopped.");
        }
    }

    private static void exitApplication() {
        boolean confirmed = new MessageDialogBuilder()
                .setTitle("Confirmation")
                .setText("Are you sure you want to EXIT?")
                .addButton(MessageDialogButton.Yes)
                .addButton(MessageDialogButton.No)
                .build()
                .showDialog(textGUI) == MessageDialogButton.Yes;
        if (confirmed) {
            System.exit(0);
        }

    }

    private static void configureServer() {
        panel.removeAllComponents();

        TextBox serverNameTB = new TextBox();
        Label serverName = new Label("Enter server name: ");
        panel.addComponent(serverName);
        panel.addComponent(serverNameTB);

        TextBox serverPortTB = new TextBox();
        Label serverPortName = new Label("Enter server port: ");
        panel.addComponent(serverPortName);
        panel.addComponent(serverPortTB);

        TextBox dbHostTB = new TextBox();
        Label dbHostName = new Label("Enter database host: ");
        panel.addComponent(dbHostName);
        panel.addComponent(dbHostTB);

        TextBox dbPortTB = new TextBox();
        Label dbPortName = new Label("Enter database port: ");
        panel.addComponent(dbPortName);
        panel.addComponent(dbPortTB);

        TextBox dbNameTB = new TextBox();
        Label dbName = new Label("Enter database name: ");
        panel.addComponent(dbName);
        panel.addComponent(dbNameTB);

        TextBox dbUserTB = new TextBox();
        Label dbUser = new Label("Enter database user: ");
        panel.addComponent(dbUser);
        panel.addComponent(dbUserTB);

        TextBox dbPasswordTB = new TextBox();
        Label dbPassword = new Label("Enter database password: ");
        panel.addComponent(dbPassword);
        panel.addComponent(dbPasswordTB);

        Button submitButton = new Button("Save", () -> {
            if (areAllTextBoxesFilled(serverNameTB, serverPortTB, dbHostTB, dbPortTB, dbNameTB, dbUserTB, dbPasswordTB)) {
                // Mostrar cuadro de confirmación
                boolean confirmed = new MessageDialogBuilder()
                        .setTitle("Confirmation")
                        .setText("Are you sure you want to continue?")
                        .addButton(MessageDialogButton.Yes)
                        .addButton(MessageDialogButton.No)
                        .build()
                        .showDialog(textGUI) == MessageDialogButton.Yes;

                if (confirmed) {

                    String serverNames = serverNameTB.getText();
                    int serverPort = Integer.parseInt(serverPortTB.getText());
                    String dbHost = dbHostTB.getText();
                    int dbPort = Integer.parseInt(dbPortTB.getText());
                    String dbNames = dbNameTB.getText();
                    String dbUsers = dbUserTB.getText();
                    String dbPasswords = dbPasswordTB.getText();

                    JSONObject config = new JSONObject();
                    JSONObject serverConfig = new JSONObject();
                    serverConfig.put("server_name", serverNames);
                    serverConfig.put("server_port", serverPort); // Adding server port to config / Añadir el puerto del servidor a la configuración

                    JSONObject dbConfig = new JSONObject();
                    dbConfig.put("dbhost", dbHost);
                    dbConfig.put("dbport", dbPort);
                    dbConfig.put("dbname", dbNames);
                    dbConfig.put("dbuser", dbUsers);
                    dbConfig.put("dbpassword", dbPasswords);

                    config.put("server", serverConfig);
                    config.put("database", dbConfig);

                    saveConfig(config);

                    boolean wannaRun = new MessageDialogBuilder()
                            .setTitle("Confirmation")
                            .setText("Do you want to Run the server?")
                            .addButton(MessageDialogButton.Yes)
                            .addButton(MessageDialogButton.No)
                            .build()
                            .showDialog(textGUI) == MessageDialogButton.Yes;
                    if (wannaRun) {
                        showWelcomeMessage();
                        panel.addComponent(logBox);
                        try {
                            textGUI.updateScreen();
                        } catch (IOException ex) {
                            Logger.getLogger(LyraConsole.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                showWarningMessage(); // Mostrar advertencia si hay campos vacíos
            }

        });

        Button backButton = new Button("Back", LyraConsole::showWelcomeMessage);

        panel.addComponent(submitButton);
        panel.addComponent(backButton);
        try {
            textGUI.updateScreen();
        } catch (IOException ex) {
            Logger.getLogger(LyraConsole.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void showWarningMessage() {
        MessageDialogBuilder dialog = new MessageDialogBuilder()
                .setTitle("Warning")
                .setText("All fields must be filled before continuing.")
                .addButton(MessageDialogButton.OK);
        dialog.build().showDialog(textGUI);
    }

    private static boolean areAllTextBoxesFilled(TextBox... textBoxes) {
        for (TextBox textBox : textBoxes) {
            if (textBox.getText().trim().isEmpty()) {
                return false; // Si hay al menos uno vacío, retorna falso
            }
        }
        return true; // Todos están llenos
    }

    private static void saveConfig(JSONObject config) {
        try (FileWriter file = new FileWriter(CONFIG_FILE)) {
            file.write(config.toString(4)); // Indentación para mejor lectura
        } catch (IOException ex) {
            Logger.getLogger(LyraConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void shutdownServer() {
        logMessage("[Lyra Server]: Shutting down server..."); // Shutdown initiated message / Mensaje de inicio de apagado
        running = false; // Stop accepting new connections / Dejar de aceptar nuevas conexiones
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close(); // Closing the server socket / Cerrando el socket del servidor
                logMessage("[Lyra Server]: ServerSocket closed."); // Server socket closed message / Mensaje de cierre del socket del servidor
            }
            if (threadPool != null) {
                synchronized (activeHandlers) {
                    Iterator<ClientHandler> iterator = activeHandlers.iterator();
                    while (iterator.hasNext()) {
                        ClientHandler handler = iterator.next();
                        try {
                            handler.closeConnection();
                            logMessage("[Lyra Server]: Connection closed for : " + handler);
                        } catch (Exception e) {
                            logMessage("[Lyra Server] No se pudo cerrar conexión de: " + handler + " - " + e.getMessage());
                        }
                        iterator.remove(); // Eliminar el manejador de la lista después de cerrarlo
                    }
                }
                
                threadPool.shutdown(); // Shutting down thread pool / Apagando el pool de hilos
                if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                    threadPool.shutdownNow(); // Force shutdown if not terminated in time / Forzar apagado si no se termina a tiempo
                }
                logMessage("[Lyra Server]: Thread pool closed.");
            }
        } catch (IOException e) {
            threadPool.shutdownNow();
            logMessage("[Lyra Server]: Error during shutdown: " + e); // Error message during shutdown / Mensaje de error durante el apagado
        } catch (InterruptedException ex) {
            logMessage("[Lyra Server]: Error during shutdown: " + ex);
            threadPool.shutdownNow();
        }

        logMessage("[Lyra Server]: Server shutdown completed."); // Server shutdown completed message / Mensaje de apagado completo del servidor
    }

    static String headerMsg = """
                               ======================================================
                                             Lyra Server - Control Panel             
                               ======================================================
                                 Version 1.0.0 - Release 2023                        
                                 (c) 2023 SimpleCore Technologies. All rights reserved.
                                 Licensed Materials - Property of SimpleCore.        
                                 Unauthorized use, duplication, or distribution      
                                 is strictly prohibited by law.                      
                               ------------------------------------------------------
                                 Design by Michael F. Sánchez                        
                                 Republic of Nicaragua                               
                                 Built with cutting-edge technology for reliability. 
                               ------------------------------------------------------
                                 For support, contact: support@simplecoretech.com    
                                 Visit us at: www.simplecoretech.com                 
                               ======================================================
                               """;

}
