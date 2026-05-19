
package com.simplecore.erp.shared.responses.types;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */


import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.Serializable;


public class LoginResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private final boolean success;
    private final String message;
    private final ActiveSession activeSession;
    private final boolean isNewSession; // 🔹 Indica si la sesión es nueva

    // 🔹 Constructor para respuesta exitosa con sesión (nueva o existente)
    public LoginResponse(ActiveSession activeSession, boolean isNewSession) {
        this.success = true;
        this.message = "Login successful";
        this.activeSession = activeSession;
        this.isNewSession = isNewSession;
    }

    // 🔹 Constructor para errores o sesión ya existente
    public LoginResponse(String message, ActiveSession activeSession) {
        this.success = false;
        this.message = message;
        this.activeSession = activeSession;
        this.isNewSession = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ActiveSession getActiveSession() {
        return activeSession;
    }

    public boolean isNewSession() {
        return isNewSession;
    }
}
