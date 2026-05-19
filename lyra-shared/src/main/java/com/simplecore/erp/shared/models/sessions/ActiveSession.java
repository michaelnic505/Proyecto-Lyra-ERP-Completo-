package com.simplecore.erp.shared.models.sessions;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP  
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class ActiveSession implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final String sessionId;
    private final int userId;
    private final String username;
    private final String terminal;
    private final String ipAddress;
    private final boolean isNewSession;
    private final LocalDateTime loginTime;
    private final String timezone;
    private final String role;

    public ActiveSession(int userId, String username, String terminal, String ipAddress,boolean isNewSession,String timezone,String role) {
        this.sessionId = UUID.randomUUID().toString(); // Genera un identificador único
        this.userId = userId;
        this.username = username;
        this.terminal = terminal;
        this.ipAddress = ipAddress;
        this.loginTime = LocalDateTime.now();
        this.isNewSession = isNewSession;
        this.timezone = timezone;
        this.role = role;
    }

    // Getters
    public String getSessionId() { return sessionId; }
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getTerminal() { return terminal; }
    public String getIpAddress() { return ipAddress; }
    public String getTimezone() { return timezone; }
    public LocalDateTime getLoginTime() { return loginTime; }
    public boolean isNewSession() { return isNewSession; }
    public String getRole() { return role; }
}
