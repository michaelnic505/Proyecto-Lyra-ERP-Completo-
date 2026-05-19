package com.simplecore.erp.server.services;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import com.simplecore.erp.server.managers.SessionManager;
import com.simplecore.erp.shared.models.sessions.ActiveSession;

/**
 * SessionService provides methods to manage user sessions.
 * (SessionService proporciona métodos para gestionar sesiones de usuario.)
 */
public class SessionService {

    /**
     * Retrieves the active session of a given user.
     * (Recupera la sesión activa de un usuario dado.)
     * 
     * @param userId The ID of the user. (El ID del usuario.)
     * @return The active session, or null if no session is found. (La sesión activa, o null si no se encuentra ninguna sesión.)
     */
    public static ActiveSession getActiveSession(int userId) {
        return SessionManager.getSessionByUserId(userId);
    }

    /**
     * Registers a new session for a user.
     * (Registra una nueva sesión para un usuario.)
     * 
     * @param session The session to register. (La sesión a registrar.)
     */
    public static void registerSession(ActiveSession session) {
        SessionManager.registerSession(session);
    }

    /**
     * Closes the active session of a user.
     * (Cierra la sesión activa de un usuario.)
     * 
     * @param userId The ID of the user. (El ID del usuario.)
     */
    public static void closeSession(int userId) {
        SessionManager.removeSession(userId);
    }
    
    
        /**
     * Validates if the session ID is correct for the given user.
     * Valida si el ID de sesión es correcto para el usuario dado.
     *
     * @param sessionId The session ID to validate. // El ID de sesión a validar.
     * @param userId The user ID associated with the session. // El ID de usuario asociado a la sesión.
     * @return True if the session is valid, false otherwise. // True si la sesión es válida, false en caso contrario.
     */
    public static boolean isSessionValid(String sessionId, int userId) {
        ActiveSession activeSession = SessionService.getActiveSession(userId);
        if (activeSession == null) {
            return false; // Session does not exist // La sesión no existe
        }
        return activeSession.getSessionId().equals(sessionId); // Compare session ID // Compara el ID de sesión
    }
    
}
