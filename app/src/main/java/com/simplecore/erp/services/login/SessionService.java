package com.simplecore.erp.services.login;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tablecolumns.ActiveSessions;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.models.login.ActiveSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionService {
    private Connection connection;

    public SessionService(Connection connection) {
        this.connection = connection;
    }

    public ActiveSession getActiveSession(int userId) {
        StringBuilder query = new StringBuilder()
                .append(SQLKeywords.SELECT.toSQL()).append(" ")
                .append(ActiveSessions.ID.name()).append(", ")
                .append(ActiveSessions.USERID.name()).append(", ")
                .append(ActiveSessions.SESSIONID.name()).append(", ")
                .append(ActiveSessions.TERMINAL.name()).append(", ")
                .append(ActiveSessions.IPADDRESS.name()).append(", ")
                .append(ActiveSessions.LONGINTIME.name()).append(" ")
                .append(SQLKeywords.FROM.toSQL()).append(" ")
                .append(DatabaseTables.ACTIVE_SESSIONS.tableName()).append(" ")
                .append(SQLKeywords.WHERE.toSQL()).append(" ")
                .append(ActiveSessions.USERID.name()).append(" = ?");

        try (PreparedStatement stmt = connection.prepareStatement(query.toString())) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ActiveSession(
                            rs.getInt(ActiveSessions.ID.name()),
                            rs.getInt(ActiveSessions.USERID.name()),
                            rs.getString(ActiveSessions.SESSIONID.name()),
                            rs.getString(ActiveSessions.TERMINAL.name()),
                            rs.getString(ActiveSessions.IPADDRESS.name()),
                            rs.getTimestamp(ActiveSessions.LONGINTIME.name())
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // No hay sesión activa
    }

    public void createSession(int userId, String sessionId, String terminal, String ipAddress) {
        StringBuilder query = new StringBuilder()
                .append(SQLKeywords.INSERT.toSQL()).append(" ")
                .append(DatabaseTables.ACTIVE_SESSIONS.tableName()).append(" (")
                .append(ActiveSessions.USERID.name()).append(", ")
                .append(ActiveSessions.SESSIONID.name()).append(", ")
                .append(ActiveSessions.TERMINAL.name()).append(", ")
                .append(ActiveSessions.IPADDRESS.name()).append(", ")
                .append(ActiveSessions.LONGINTIME.name()).append(") ")
                .append("VALUES (?, ?, ?, ?, NOW())");
        
        try (PreparedStatement stmt = connection.prepareStatement(query.toString())) {
            stmt.setInt(1, userId);
            stmt.setString(2, sessionId);
            stmt.setString(3, terminal);
            stmt.setString(4, ipAddress);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSession(int userId) {
        StringBuilder query = new StringBuilder()
                .append(SQLKeywords.DELETE.toSQL()).append(" ")
                .append(SQLKeywords.FROM.toSQL()).append(" ")
                .append(DatabaseTables.ACTIVE_SESSIONS.tableName()).append(" ")
                .append(SQLKeywords.WHERE.toSQL()).append(" ")
                .append(ActiveSessions.USERID.name()).append(" = ?");

        try (PreparedStatement stmt = connection.prepareStatement(query.toString())) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSessionActivity(int userId) {
        StringBuilder query = new StringBuilder()
                .append(SQLKeywords.UPDATE.toSQL()).append(" ")
                .append(DatabaseTables.ACTIVE_SESSIONS.tableName()).append(" ")
                .append(SQLKeywords.SET.toSQL()).append(" ")
                .append(ActiveSessions.LASTACTIVITY.name()).append(" = NOW() ")
                .append(SQLKeywords.WHERE.toSQL()).append(" ")
                .append(ActiveSessions.USERID.name()).append(" = ?");
        
        try (PreparedStatement stmt = connection.prepareStatement(query.toString())) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Failed to update session activity.", e);
        }
    }
    
    

}
