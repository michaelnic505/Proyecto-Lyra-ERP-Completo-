package com.simplecore.erp.server.managers;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SessionManager handles active user sessions using a concurrent hash map.
 * (SessionManager gestiona las sesiones activas de usuarios utilizando un mapa hash concurrente.)
 */
public class SessionManager {

    private static final Map<Integer, ActiveSession> activeSessions = new ConcurrentHashMap<>();

    /**
     * Registers a new session for a user.
     * (Registra una nueva sesión para un usuario.)
     * 
     * @param session The session to register. (La sesión a registrar.)
     */
    public static void registerSession(ActiveSession session) {
        activeSessions.put(session.getUserId(), session);
    }

    /**
     * Removes the session of a given user.
     * (Elimina la sesión de un usuario dado.)
     * 
     * @param userId The ID of the user. (El ID del usuario.)
     */
    public static void removeSession(int userId) {
        activeSessions.remove(userId);
    }

    /**
     * Retrieves the active session of a given user.
     * (Recupera la sesión activa de un usuario dado.)
     * 
     * @param userId The ID of the user. (El ID del usuario.)
     * @return The active session, or null if no session is found. (La sesión activa, o null si no se encuentra ninguna sesión.)
     */
    public static ActiveSession getSessionByUserId(int userId) {
        return activeSessions.get(userId); 
    }

    
    public static Map<Integer, ActiveSession> getSessionsMap(){
        return activeSessions;
    }
}

