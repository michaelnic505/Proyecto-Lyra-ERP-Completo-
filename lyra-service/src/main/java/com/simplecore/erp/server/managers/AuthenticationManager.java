package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.Users;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.server.config.dates.UsersTimezones;
import com.simplecore.erp.server.services.SessionService;
import com.simplecore.erp.server.services.TransactionService;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.LoginRequest;
import com.simplecore.erp.shared.responses.types.LoginResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AuthenticationManager {

    public static LoginResponse authenticate(LoginRequest request) {
        try (Connection conn = PooledConnectionService.getConnection()) {
            StringBuilder query = new StringBuilder();
            query.append(Q.selectAll(DatabaseTables.USERS.tableName()))
                    .append(Q.where(
                            Users.USERNAME.columnName(),
                            Users.PASSWORD_HASH.columnName()));

            try (PreparedStatement stmt = conn.prepareStatement(query.toString())) {
                stmt.setString(1, request.getUsername());
                stmt.setString(2, request.getPassword());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt(Users.USER_ID.columnName());
                    // If there is an active session, return that session
                    // Si hay una sesión activa, regresa esa sesión
                    ActiveSession activeSession = SessionService.getActiveSession(userId);
                    if (activeSession != null) {
                        return new LoginResponse("User already has an active session", activeSession);
                    }
                    // If there is no active session, create a new one
                    // Si no hay una sesión activa, crea una nueva
                    String timezone = UsersTimezones.getTimeZoneByUser(conn, request.getUsername());
                    if (timezone == null) {
                        timezone = ZoneId.systemDefault().toString();
                    }
                    
                    String role = TransactionService.getRoleByUserId(conn, userId);
                    ActiveSession newActiveSession = new ActiveSession(
                            userId,
                            rs.getString(Users.USERNAME.columnName()),
                            request.getTerminal(),
                            request.getIpAddress(),
                            true,
                            timezone,
                            role
                    );
                    SessionService.registerSession(newActiveSession);
                    return new LoginResponse(newActiveSession, true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); //  Only capture SQLException for database errors
            //  Solo capturamos SQLException para errores de BD
        }
        return new LoginResponse("Invalid credentials", null);
    }
}
