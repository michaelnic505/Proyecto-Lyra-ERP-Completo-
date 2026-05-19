package com.simplecore.erp.client.services.login;

import com.simplecore.erp.shared.exceptions.ServerNotAvailableException;
import com.simplecore.erp.shared.exceptions.SessionAlreadyActiveException;
import com.simplecore.erp.shared.requests.types.LoginRequest;
import com.simplecore.erp.shared.responses.types.LoginResponse;
import com.simplecore.erp.shared.exceptions.InvalidCredentialsException;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */

/**
 * Service class for handling user authentication requests. (Clase de servicio para manejar solicitudes de autenticación de usuarios.)
 */
public class LoginService {

    private final ObjectInputStream input;
    private final ObjectOutputStream output;

    /**
     * Constructor to initialize the server connection details. (Constructor para inicializar los detalles de conexión al servidor.)
     * @param serverAddress Server IP or hostname (Dirección IP o nombre del servidor)
     * @param serverPort Server communication port (Puerto de comunicación del servidor)
     */
    public LoginService(ObjectInputStream input,ObjectOutputStream output) {
        this.input = input;
        this.output = output;
    }

    /**
     * Authenticates a user by sending login credentials to the server. (Autentica a un usuario enviando credenciales de inicio de sesión al servidor.)
     * @param username User's login name (Nombre de usuario)
     * @param password User's password (Contraseña del usuario)
     * @return ActiveSession if authentication is successful (Sesión activa si la autenticación es exitosa)
     * @throws ServerNotAvailableException If the server is unreachable (Si el servidor no está disponible)
     * @throws SessionAlreadyActiveException If the user already has an active session (Si el usuario ya tiene una sesión activa)
     * @throws InvalidCredentialsException If the login credentials are incorrect (Si las credenciales de inicio de sesión son incorrectas)
     */
    public ActiveSession authenticate(String username, String password)
            throws ServerNotAvailableException, SessionAlreadyActiveException, InvalidCredentialsException {

        try {
            // Open a connection to the server (Abre una conexión con el servidor)
            String ipAddress = InetAddress.getLocalHost().getHostAddress(); // Get client IP address (Obtiene la dirección IP del cliente)
            String terminalName = InetAddress.getLocalHost().getHostName(); // Get client terminal name (Obtiene el nombre del terminal del cliente)
            // Send login request (Enviar la solicitud de login)
            LoginRequest loginRequest = new LoginRequest(username, password, terminalName, ipAddress);
            output.writeObject(loginRequest);
            output.flush();
            // Receive server response (Recibir la respuesta del servidor)
            Object response = input.readObject();
            if (response instanceof LoginResponse loginResponse) {
                if (loginResponse.isSuccess()) { // Check if authentication was successful (Verifica si la autenticación fue exitosa)
                    if (loginResponse.isNewSession()) {
                        return loginResponse.getActiveSession();
                    }
                }
                if (loginResponse.getActiveSession() != null) {
                    throw new SessionAlreadyActiveException("User already has an active session", loginResponse.getActiveSession());
                }
                throw new InvalidCredentialsException("Invalid username or password.");
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new ServerNotAvailableException("Unexpected error occurred.");// (Ocurrió un error inesperado.)
    }

}
