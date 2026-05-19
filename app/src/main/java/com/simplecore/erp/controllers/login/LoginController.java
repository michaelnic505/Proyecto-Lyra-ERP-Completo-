package com.simplecore.erp.controllers.login;

import com.simplecore.erp.models.login.ActiveSession;
import com.simplecore.erp.models.login.User;
import com.simplecore.erp.services.login.SessionService;
import com.simplecore.erp.services.login.UserService;
/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
/**
 * LoginController handles user authentication and session management.
 * It verifies user credentials, checks for active sessions, and manages session creation and termination.
 * 
 * LoginController maneja la autenticación de usuarios y la gestión de sesiones.
 * Verifica las credenciales del usuario, comprueba sesiones activas y administra la creación y terminación de sesiones.
 */
public class LoginController {
    private UserService userService;
    private SessionService sessionService;

    /**
     * Constructor to initialize services.
     * 
     * Constructor para inicializar los servicios.
     * 
     * @param userService Service to handle user authentication.
     *                    Servicio para manejar la autenticación de usuarios.
     * @param sessionService Service to manage user sessions.
     *                       Servicio para gestionar sesiones de usuario.
     */
    public LoginController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    /**
     * Handles user login process.
     * 
     * Maneja el proceso de inicio de sesión de usuario.
     * 
     * @param username The username provided by the user.
     *                 El nombre de usuario proporcionado por el usuario.
     * @param passwordHash The hashed password for authentication.
     *                     La contraseña encriptada para la autenticación.
     * @param terminal The name of the terminal where the user is logging in.
     *                 El nombre del terminal donde el usuario inicia sesión.
     * @param ipAddress The IP address of the terminal.
     *                 La dirección IP del terminal.
     */
    public void login(String username, String passwordHash, String terminal, String ipAddress) {
        User user = userService.authenticateUser(username, passwordHash);
        
        if (user == null) {
            System.out.println("Incorrect username or password.");
            return;
        }

        ActiveSession existingSession = sessionService.getActiveSession(user.getId());
        if (existingSession != null) {
            System.out.println("The user already has an active session on: " + existingSession.getTerminal());
            return;
        }

        String sessionId = java.util.UUID.randomUUID().toString();
        sessionService.createSession(user.getId(), sessionId, terminal, ipAddress);
        System.out.println("Session started successfully. Session ID: " + sessionId);
    }

    /**
     * Handles user logout process.
     * 
     * Maneja el proceso de cierre de sesión de usuario.
     * 
     * @param userId The ID of the user whose session will be terminated.
     *               El ID del usuario cuya sesión será terminada.
     */
    public void logout(int userId) {
        sessionService.deleteSession(userId);
        System.out.println("✅ Session closed successfully.");
    }
}

