package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.RoleTransactions;
import com.simplecore.erp.server.config.database.tablecolumns.UsersRoles;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.requests.types.TransactionRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.TransactionResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class TransactionManager {

    public static Object checkTransactionAccess(TransactionRequest request) {
        try (Connection conn = PooledConnectionService.getConnection()) { // Intenta obtener una conexión a la base de datos / Attempts to get a connection to the database
            StringBuilder query = new StringBuilder();
            query.append(Q.select(DatabaseTables.ROLE_TRANSACTIONS.tableName(),
                    RoleTransactions.TRANSACTION_ID.columnName())) // Prepara la consulta SQL para verificar los permisos de transacción / Prepares the SQL query to check transaction permissions
                    .append(Q.where(RoleTransactions.ROLE_ID.columnName(),
                            RoleTransactions.TRANSACTION_ID.columnName())); // Añade la condición WHERE a la consulta / Adds the WHERE clause to the query

            try (PreparedStatement ps = conn.prepareStatement(query.toString())) { // Prepara la consulta para ejecutarla / Prepares the query for execution
                ps.setString(1, request.getRole()); // Establece el rol como parámetro de la consulta / Sets the role as a parameter in the query
                ps.setString(2, request.getTransaction());
                ResultSet rs = ps.executeQuery(); // Ejecuta la consulta y obtiene el resultado / Executes the query and retrieves the result
                if (rs.next()) { // Si hay un resultado en la base de datos, se concede el acceso / If there is a result, access is granted
                    return new TransactionResponse(request.getSessionId(), ResultType.FOUND,true ,"Access granted to the transaction."); // Retorna respuesta positiva / Returns a positive response
                } else { // Si no hay un resultado en la base de datos, se deniega el acceso / If there is no result, access is denied
                    return new TransactionResponse(request.getSessionId(), ResultType.NOT_FOUND,false ,"Access denied. Role doesn't have permission."); // Retorna respuesta negativa / Returns a negative response
                }
            }

        } catch (SQLException ex) { // Si hay un error en la consulta, se captura la excepción / If there is an error in the query, catch the exception
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, ex); // Registra el error / Logs the error
        }
        return new TransactionResponse(request.getSessionId(), ResultType.SQL_ERROR,false, "Error accessing transaction."); // Si ocurre un error no esperado, retorna un error genérico / Returns a generic error response if an unexpected error occurs
    }

    public static String getRoleByUserId(Connection conn, int userId) {
        StringBuilder query = new StringBuilder();
        query.append(Q.select(
                DatabaseTables.USERS_ROLES.tableName(),
                UsersRoles.ROLE_ID.columnName()))
                .append(Q.where(UsersRoles.USER_ID.columnName()));

        try (PreparedStatement st = conn.prepareStatement(query.toString())) {
            st.setInt(1, userId);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
