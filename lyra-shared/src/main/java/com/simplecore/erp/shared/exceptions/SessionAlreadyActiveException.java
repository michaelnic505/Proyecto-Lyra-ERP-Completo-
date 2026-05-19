
package com.simplecore.erp.shared.exceptions;

import com.simplecore.erp.shared.models.sessions.ActiveSession;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class SessionAlreadyActiveException extends Exception {
    private final ActiveSession activeSession;

    public SessionAlreadyActiveException(String message, ActiveSession activeSession) {
        super(message);
        this.activeSession = activeSession;
    }

    public ActiveSession getActiveSession() {
        return activeSession;
    }
}