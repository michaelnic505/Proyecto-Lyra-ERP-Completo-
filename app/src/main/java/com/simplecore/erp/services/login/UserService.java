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
import com.simplecore.erp.config.database.tablecolumns.Users;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.models.login.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {
    private Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
    }

    public User authenticateUser(String username, String passwordHash) {
        StringBuilder query = new StringBuilder();
                query.append(SQLKeywords.SELECT.toSQL()).append(" ")
                .append(Users.USER_ID.name()).append(" ,")
                .append(Users.USERNAME.name()).append(" ,")
                .append(Users.PASSWORD_HASH.name()).append(", ")
                .append(Users.FIRST_NAME.name()).append(" ,")
                .append(Users.LAST_NAME.name()).append(" ,")
                .append(Users.EMAIL.name()).append(" ,")
                .append(Users.POSITION.name()).append(" ,")
                .append(Users.DEPARTMENT.name()).append(" ,")
                .append(Users.ROLE.name()).append(" ")
                .append(SQLKeywords.FROM.toSQL()).append(" ")
                .append(DatabaseTables.USERS.tableName()).append(" ")
                .append(SQLKeywords.WHERE.toSQL()).append(" ")
                .append(Users.USERNAME.name()).append(" = ?").append(" ")
                .append(SQLKeywords.AND.toSQL()).append(" ")
                .append(Users.PASSWORD_HASH.name()).append(" = ?");
        
        try (PreparedStatement stmt = connection.prepareStatement(query.toString())) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt(Users.USER_ID.index()),
                        rs.getString(Users.PASSWORD_HASH.index()),
                        rs.getString(Users.USERNAME.index()),
                        rs.getString(Users.FIRST_NAME.index()),
                        rs.getString(Users.LAST_NAME.index()),
                        rs.getString(Users.EMAIL.index()),
                        rs.getString(Users.POSITION.index()),
                        rs.getString(Users.DEPARTMENT.index()),
                        rs.getString(Users.ROLE.index())
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Usuario no encontrado o error
    }
}
