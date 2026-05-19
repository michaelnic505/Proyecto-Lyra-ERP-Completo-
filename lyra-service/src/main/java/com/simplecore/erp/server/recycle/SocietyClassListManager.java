
package com.simplecore.erp.server.recycle;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.SocietyClasses;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.server.services.SessionService;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.SocietyClassListRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.LogoutResponse;
import com.simplecore.erp.shared.responses.types.SocietyClassListResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class SocietyClassListManager {
    /**
     * Retrieves the list of society classes from the database.
     * Recupera la lista de clases de sociedad desde la base de datos.
     *
     * @param request The request containing session and user details. // La solicitud que contiene los detalles de sesión y usuario.
     * @return A response containing the list of society classes or an error message if the session is invalid.
     *         Una respuesta que contiene la lista de clases de sociedad o un mensaje de error si la sesión es inválida.
     */
    public static Object getSocietyClassList(SocietyClassListRequest request) {
        List<String[]> societyData = new ArrayList<>();
        String query = Q.selectAll(DatabaseTables.SOCIETY_CLASSES.tableName()); // Query to fetch all data // Consulta para obtener todos los datos

        try (Connection conn = PooledConnectionService.getConnection()) { // Establish database connection // Establece la conexión con la base de datos
            try (PreparedStatement st = conn.prepareStatement(query)) { // Prepare SQL statement // Prepara la consulta SQL
                try (ResultSet rs = st.executeQuery()) { // Execute query // Ejecuta la consulta
                    while (rs.next()) {
                        societyData.add(new String[]{
                                rs.getString(SocietyClasses.CLASS_ID.columnName()), // Retrieve CLASS_ID // Recupera CLASS_ID
                                rs.getString(SocietyClasses.CLASS_KEY.columnName())  // Retrieve CLASS_KEY // Recupera CLASS_KEY
                        });
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SocietyClassListManager.class.getName()).log(Level.SEVERE, null, ex); // Log error // Registra el error
        }

        return new SocietyClassListResponse(request.getSessionId(),ResultType.SQL_ERROR,societyData); // Return response with retrieved data // Retorna la respuesta con los datos recuperados
    }


}
