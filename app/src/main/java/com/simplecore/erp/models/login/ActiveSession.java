package com.simplecore.erp.models.login;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

import java.sql.Timestamp;


public class ActiveSession {
    private int id;
    private int userId;
    private String sessionId;
    private String terminal;
    private String ipAddress;
    private Timestamp loginTime;

    // Constructor
    public ActiveSession(int id, int userId, String sessionId, String terminal,String ipAddress ,Timestamp loginTime) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
        this.terminal = terminal;
        this.ipAddress = ipAddress;
        this.loginTime = loginTime;
    }

    // Getters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getSessionId() { return sessionId; }
    public String getTerminal() { return terminal; }
    public String getIpAddress() { return ipAddress; }
    public Timestamp getLoginTime() { return loginTime; }
}
