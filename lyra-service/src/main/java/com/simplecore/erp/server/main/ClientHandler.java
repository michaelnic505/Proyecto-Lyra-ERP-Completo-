package com.simplecore.erp.server.main;

import com.simplecore.erp.server.services.SessionService;
import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.responses.types.LogoutResponse;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final String serverName;
    private static final int TIMEOUT = 30000;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private boolean isRunning = true;

    /**
     * Constructor that initializes the client handler with the connected
     * client's socket. (Constructor que inicializa el manejador de clientes con
     * el socket del cliente conectado.)
     *
     * @param clientSocket The socket representing the client's connection. (El
     * socket que representa la conexión del cliente.)
     */
    public ClientHandler(Socket clientSocket,String serverName) {
        this.clientSocket = clientSocket;
        this.serverName = serverName;
    }

    /**
     * Runs the client handler to process incoming requests from the client.
     * (Ejecuta el manejador de clientes para procesar las solicitudes entrantes
     * del cliente.)
     */
    public Socket getClientSocket(){
        return clientSocket;
    }
    
    @Override
    public void run() {
        Integer userID = 0;
        try {
            // Primero inicializar ObjectOutputStream para evitar bloqueos
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            output.flush(); // Asegura que el stream esté listo
            input = new ObjectInputStream(clientSocket.getInputStream());

            LyraConsole.logMessage("[Lyra Server]: Client connected: " + clientSocket.getInetAddress());
            clientSocket.setSoTimeout(TIMEOUT); // Set socket timeout (Establece el tiempo de espera del socket)

            while (isRunning && !clientSocket.isClosed()) {
                try {
                    // Read client request (Lee la solicitud del cliente)
                    Object clientRequest = input.readObject();
                    clientSocket.setSoTimeout(TIMEOUT);

                    if (clientRequest instanceof Integer userId) { // If the request is an Integer (Si la solicitud es un Integer)
                        userID = userId; // Store the user ID (Almacena el ID del usuario)
                        sendResponse(serverName);
                        continue; // Skip to the next iteration (Sigue a la siguiente iteración)
                    }
                    if (clientRequest instanceof BaseRequest baseRequest) { // If the request is a base request (Si la solicitud es una solicitud base)
                        // For all other requests, extract sessionId. (Para todas las demás solicitudes, extraer el sessionId.)
                        Object response = RequestDispatcher.dispatch(baseRequest); // Dispatch the request with session ID (Despacha la solicitud con el ID de sesión)
                        sendResponse(response);
                        if(response instanceof LogoutResponse){
                            LyraConsole.closeClientSocket(clientSocket);
                            LyraConsole.logMessage("[Lyra Server]: Client connection closed: " + clientSocket.getInetAddress() + "\n"); // Log client disconnection (Registra la desconexión del cliente)
                            closeConnection();
                            break;
                        }
                    }else { // If the request is invalid (Si la solicitud no es válida)
                        sendResponse("Invalid request format");
                    }

                } catch (SocketTimeoutException e) { // Handle socket timeout exception (Maneja la excepción de tiempo de espera del socket)
                    LyraConsole.logMessage("[Lyra Server]: Timeout reached, closing connection with client: " + clientSocket.getInetAddress()); // Log timeout (Registra el tiempo de espera alcanzado)
                    SessionService.closeSession(userID); // Close the session (Cierra la sesión)
                    closeConnection(); // Close the connection (Cierra la conexión)
                    break; // Exit the loop (Sale del bucle)
                } catch (EOFException e) { // Handle end of file exception (Maneja la excepción de fin de archivo)
                    LyraConsole.logMessage("[Lyra Server]: EOFException: Connection closed by client." + e.getMessage()); // Log client closure (Registra el cierre del cliente)
                    SessionService.closeSession(userID); // Close the session (Cierra la sesión)
                    closeConnection(); // Close the connection (Cierra la conexión)
                    break; // Exit the loop (Sale del bucle)
                } catch (IOException | ClassNotFoundException e) { // Handle other IO or class exceptions (Maneja otras excepciones de IO o clase)
                    LyraConsole.logMessage("[Lyra Server]: Communication terminated: " + e.getMessage());
                    closeConnection(); // Close the connection (Cierra la conexión)
                    break; // Exit the loop (Sale del bucle)
                }
            }
        } catch (IOException e) { // Handle IOException (Maneja la excepción de IO)
           LyraConsole.logMessage("[Lyra Server]: Error initializing streams: " + e.getMessage());
        }finally{
            closeConnection();
            SessionService.closeSession(userID);
        }
    }
   
    /**
     * Close the client connection safely. (Cerrar la conexión del cliente de
     * manera segura.)
     */
    public void closeConnection() {
        isRunning = false;
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (!clientSocket.isClosed()) clientSocket.close();
        } catch (IOException e) {
            LyraConsole.logMessage("[Lyra Server]: Error closing connection: " + e.getMessage());
        }
        
    }

    private void sendResponse(Object response) throws IOException {
        if (output != null) {
            output.writeObject(response);
            output.flush();
        }
    }

}
