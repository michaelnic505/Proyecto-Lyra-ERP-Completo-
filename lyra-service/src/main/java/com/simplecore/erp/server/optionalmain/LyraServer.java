package com.simplecore.erp.server.optionalmain;

import com.google.gson.JsonObject;
import com.simplecore.erp.server.config.json.ConfigManager;
import com.simplecore.erp.server.main.ClientHandler;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.ansi;
import org.fusesource.jansi.AnsiConsole;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class LyraServer {

    private static final Logger logger = LoggerFactory.getLogger(LyraServer.class);
    private static final String CONFIG_FILE = "config.json";
    private static volatile boolean running = true; // Flag to control server shutdown / Bandera para controlar el apagado del servidor
    private static final int MAX_THREADS = 100; // Maximum number of threads in the thread pool / Número máximo de hilos en el pool de hilos
    private static ExecutorService threadPool; // Thread pool to manage client connections / Pool de hilos para gestionar las conexiones de los clientes
    private static ServerSocket serverSocket; // Server socket to listen for incoming connections / Socket del servidor para escuchar conexiones entrantes
    private static Thread shutdownThread; // Thread to handle server shutdown / Hilo para manejar el apagado del servidor
    private static final Scanner scanner = new Scanner(System.in); // Global scanner to read user input / Escáner global para leer entradas del usuario

    // Method to start the server / Método para iniciar el servidor
    private static void startServer() {

        showHeader();
        running = true;

        System.out.println("[Lyra Server]: Starting Lyra Server Services...");
        System.out.println("[Lyra Server]: Configuration file detected. Starting server..."); // Server starting message / Mensaje de inicio del servidor
        JsonObject config = ConfigManager.readConfig(); // Reading the configuration file / Leyendo el archivo de configuración
        if (config == null) {
            logger.error("[Lyra Server]: Failed to load config. Exiting...");// Error message if config loading fails / Mensaje de error si falla la carga de la configuración
            return;
        }

        String serverName = config.getAsJsonObject("server").get("server_name").getAsString();
        int port = config.getAsJsonObject("server").get("server_port").getAsInt(); // Getting port from config / Obteniendo el puerto desde la configuración
        System.out.println("[Lyra Server]: Welcome to " + serverName + " server.");
        System.out.println("[Lyra Server]: Running server on port " + port + "..."); // Confirming server port / Confirmando el puerto del servidor

        if (shutdownThread == null || !shutdownThread.isAlive()) {
            shutdownThread = new Thread(LyraServer::listenForShutdownCommand); // Starting the shutdown listener thread / Iniciando el hilo que escucha el apagado
            shutdownThread.start();
        }
        //int clientConnectionsLimit = config.getAsJsonObject("clients").get("threads").getAsInt();
        threadPool = Executors.newFixedThreadPool(MAX_THREADS); // Initializing thread pool / Inicializando el pool de hilos
        try {
            serverSocket = new ServerSocket(port); // Starting server socket / Iniciando el socket del servidor
            while (running) {
                Socket clientSocket = serverSocket.accept(); // Accepting new client connections / Aceptando nuevas conexiones de clientes
                System.out.println("[Lyra Server]: New connection accepted: " + clientSocket.getInetAddress()); // Client connected message / Mensaje de conexión de cliente
                threadPool.submit(new ClientHandler(clientSocket, serverName)); // Submitting client handler task / Enviando la tarea del manejador del cliente al pool de hilos
            }

        } catch (IOException e) {
            if (running) { // Only log errors if the server is running / Solo registrar errores si el servidor está en ejecución
                logger.error("[Lyra Server]: Error on the server: ", e.getMessage());
            }
        }

    }
    //Method to clean the console
    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
            }
            
        } catch (IOException | InterruptedException e) {
            logger.error("Failed to clear screen", e);
        }
    }
    //Show the company header to the users
    private static void showHeader() {
        // Mostrar el encabezado
        System.out.println("\n");
        System.out.println(
                ansi().fg(Color.GREEN).a("======================================================\n").reset()
                        .fg(Color.BLUE).a("              Lyra Server - Control Panel             \n").reset()
                        .fg(Color.GREEN).a("======================================================\n").reset()
                        .fg(Color.CYAN).a("  Version 1.0.0 - Release 2023                        \n").reset()
                        .fg(Color.CYAN).a("  (c) 2023 SimpleCore Technologies. All rights reserved.\n").reset()
                        .fg(Color.CYAN).a("  Licensed Materials - Property of SimpleCore.        \n").reset()
                        .fg(Color.CYAN).a("  Unauthorized use, duplication, or distribution      \n").reset()
                        .fg(Color.CYAN).a("  is strictly prohibited by law.                      \n").reset()
                        .fg(Color.GREEN).a("------------------------------------------------------\n").reset()
                        .fg(Color.GREEN).a("  Design by Michael F. Sánchez                        \n").reset()
                        .fg(Color.GREEN).a("  Republic of Nicaragua                               \n").reset()
                        .fg(Color.GREEN).a("  Built with cutting-edge technology for reliability. \n").reset()
                        .fg(Color.CYAN).a("------------------------------------------------------\n").reset()
                        .fg(Color.CYAN).a("  For support, contact: support@simplecoretech.com    \n").reset()
                        .fg(Color.CYAN).a("  Visit us at: www.simplecoretech.com                 \n").reset()
                        .fg(Color.CYAN).a("======================================================\n").reset()
        );

    }
    // Method to listen for the shutdown command / Método para escuchar el comando de apagado
    private static void listenForShutdownCommand() {
        try {
            while (running) {
                System.out.println("[Lyra Server]: Server is running...");
                System.out.print("[Lyra Server]: (R) Restart Server / (S) Stop Server / (E) Stop Server & Exit Application :_> ");
                String command = scanner.nextLine().trim(); // Reading command input / Leyendo la entrada del comando
                if (command.equals("R")) {
                    restartServer(); // Calls method to restart the server / Llama al método para reiniciar el servidor
                    break;
                } else if (command.equals("S")) {

                    showHeader();
                    stopServer(); // Calls method to stop the server / Llama al método para detener el servidor
                    break;
                } else if (command.equals("E")) {
                    stopServerAndExit(); // Calls method to stop and exit the server / Llama al método para detener y salir del servidor
                    break;
                }

            }
        } catch (Exception e) {
            if (running) {
                logger.error("[Lyra Server]: Error in shutdown command listener: " + e.getMessage()); // Error message if something fails / Mensaje de error si algo falla
            }
        }
    }
    // Method to restart the server by shutting it down and starting it again / Método para reiniciar el servidor apagándolo y luego iniciándolo de nuevo
    private static void restartServer() {
        try {
            shutdownServer(); // Shutdown the server first / Apagar el servidor primero
            clearScreen();
            Thread.sleep(2000);
            startServer();
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(LyraServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Method to stop the server and ask whether to restart or exit / Método para detener el servidor y preguntar si se debe reiniciar o salir
    private static void stopServer() {
        shutdownServer(); // Shutdown the server / Apagar el servidor
        askForRestarOrExit(); // Ask whether to restart or exit / Preguntar si se debe reiniciar o salir
    }
    // Method to stop the server and exit the application / Método para apagar el servidor y salir de la aplicación
    private static void stopServerAndExit() {
        shutdownServer(); // Shutdown the server / Apagar el servidor
        System.exit(0); // Exit the application / Salir de la aplicación
    }
    // Method to stop the server gracefully / Método para apagar el servidor de manera controlada
    public static void shutdownServer() {
        System.out.println("[Lyra Server]: Shutting down server..."); // Shutdown initiated message / Mensaje de inicio de apagado
        running = false; // Stop accepting new connections / Dejar de aceptar nuevas conexiones

        // Cerramos los hilos activos
        if (threadPool != null && threadPool instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor executor = (ThreadPoolExecutor) threadPool;
            for (Runnable task : executor.getQueue()) {
                if (task instanceof ClientHandler) {
                    ClientHandler handler = (ClientHandler) task;
                    handler.closeConnection(); // Cerrar la conexión de cada cliente
                }
            }
        }

        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close(); // Closing the server socket / Cerrando el socket del servidor
                logger.error("[Lyra Server]: ServerSocket closed."); // Server socket closed message / Mensaje de cierre del socket del servidor
            }
            Thread.sleep(2000); // Wait for the socket to close / Esperar para que el socket se cierre
            if (threadPool != null) {
                threadPool.shutdown(); // Shutting down thread pool / Apagando el pool de hilos
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    threadPool.shutdownNow(); // Force shutdown if not terminated in time / Forzar apagado si no se termina a tiempo
                }
            }
            threadPool = Executors.newFixedThreadPool(MAX_THREADS); // Re-initialize thread pool / Re-inicializar el pool de hilos

            if (shutdownThread != null && shutdownThread.isAlive()) {
                shutdownThread.interrupt(); // Interrupting the shutdown thread / Interrumpiendo el hilo de apagado
                logger.error("[Lyra Server]: Shutdown thread interrupted."); // Shutdown thread interrupted message / Mensaje de interrupción del hilo de apagado
            }
            shutdownThread = null; // Reset the shutdown thread / Reiniciar el hilo de apagado

        } catch (InterruptedException | IOException e) {
            logger.error("[Lyra Server]: Error during shutdown: " + e); // Error message during shutdown / Mensaje de error durante el apagado
        }
        logger.error("[Lyra Server]: Server shutdown completed."); // Server shutdown completed message / Mensaje de apagado completo del servidor
    }
    // Method to ask the user whether to restart the server or exit the application / Método para preguntar al usuario si desea reiniciar el servidor o salir de la aplicación
    private static void askForRestarOrExit() {
        while (true) {
            System.out.println("[Lyra Server]: Server is stopped.");
            System.out.print("[Lyra Server]: (R) Restart Server / (E) Exit :_> "); // Prompt for restart or exit / Solicitar reiniciar o salir
            String command = scanner.nextLine().trim().toUpperCase(); // Reading command input / Leyendo la entrada del comando
            if (command.equals("R")) {
                restartServer();
                break;
            } else if (command.equals("E")) {
                System.exit(0); // Exit the application / Salir de la aplicación
                break;
            }
        }
    }
    // Method to configure the server settings / Método para configurar los ajustes del servidor
    private static void configureServer() {
        System.out.print("Enter server name: "); // Asking for server name / Preguntar por el nombre del servidor
        String serverName = scanner.nextLine();

        System.out.print("Enter server port: "); // Asking for server port / Preguntar por el puerto del servidor
        int port = scanner.nextInt();
        scanner.nextLine(); // Consume newline character / Consumir el carácter de nueva línea

        System.out.print("Enter database host: "); // Asking for database host / Preguntar por el host de la base de datos
        String dbHost = scanner.nextLine();

        System.out.print("Enter database port: "); // Asking for database port / Preguntar por el puerto de la base de datos
        int dbPort = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter database name: "); // Asking for database name / Preguntar por el nombre de la base de datos
        String dbName = scanner.nextLine();

        System.out.print("Enter database user: "); // Asking for database user / Preguntar por el usuario de la base de datos
        String dbUser = scanner.nextLine();

        System.out.print("Enter database password: "); // Asking for database password / Preguntar por la contraseña de la base de datos
        String dbPassword = scanner.nextLine();

        JSONObject config = new JSONObject();

        JSONObject serverConfig = new JSONObject();
        serverConfig.put("server_name", serverName);
        serverConfig.put("server_port", port); // Adding server port to config / Añadir el puerto del servidor a la configuración

        JSONObject dbConfig = new JSONObject();
        dbConfig.put("dbhost", dbHost);
        dbConfig.put("dbport", dbPort);
        dbConfig.put("dbname", dbName);
        dbConfig.put("dbuser", dbUser);
        dbConfig.put("dbpassword", dbPassword);

        config.put("server", serverConfig);
        config.put("database", dbConfig);

        saveConfig(config); // Save the configuration / Guardar la configuración
        System.out.println("\n[LyraServer] Configuration saved successfully. You can now start the server.\n"); // Configuration saved message / Mensaje de configuración guardada
        askForRunServer();

    }
    //Method to ask for run server for the first time
    private static void askForRunServer() {

        System.out.println("[Lyra Server]: Server is redy to work.");
        System.out.print("[Lyra Server]: (S) Start server / (E) Exit :_> ");
        String command = scanner.nextLine().trim().toUpperCase();
        if (command.equals("S")) {
            try {
                clearScreen();
                Thread.sleep(20000);
                startServer();
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(LyraServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (command.equals("E")) {
            System.exit(0);
        }
    }
    // Method to save the configuration to a file / Método para guardar la configuración en un archivo
    private static void saveConfig(JSONObject config) {
        try (FileWriter file = new FileWriter(CONFIG_FILE)) {
            file.write(config.toString(4)); // Indentación para mejor lectura
        } catch (IOException e) {
            logger.error("[Lyra Server]: Error saving configuration: " + e.getMessage());// Error while saving config / Error al guardar la configuración
        }
    }
    // Main method to run the server / Método principal para ejecutar el servidor
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        if (!Files.exists(Paths.get(CONFIG_FILE))) {
        System.out.println(ansi().fg(Color.CYAN).a("""
                               
                               =======================================================
                                   Welcome to Lyra Core+ Server                   
                               =======================================================
                                  
                               =======================================
                                 Version 1.0.0 - Release 2023         
                               =======================================
                               (c) 2023 SimpleCore Technologies.     
                               All rights reserved.                   
                               Licensed Materials - Property of SimpleCore.
                               Unauthorized use, duplication, or distribution
                               is strictly prohibited by law.          
                               =======================================
                               
                               No configuration file found.            
                               Starting initial setup...               
                               Please provide the necessary settings.
                               
                               =======================================================
                               For support, contact: support@simplecoretech.com    
                               Visit us at: www.simplecoretech.com             
                               =======================================================
                               """).reset());
            configureServer();// Configure server / Configurar el servidor
        } else {
            clearScreen();
            startServer();
        }
    }

}
