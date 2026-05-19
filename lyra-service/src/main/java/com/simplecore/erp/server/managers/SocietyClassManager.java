
package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.recycle.SocietyClassListManager;
import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.SocietyClasses;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.requests.types.SocietyClassListRequest;
import com.simplecore.erp.shared.requests.types.SocietyClassRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.SocietyClassListResponse;
import com.simplecore.erp.shared.responses.types.SocietyClassResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class SocietyClassManager {

    /**
     * Retrieves society class information based on the provided request.
     * (Recupera la información de la clase social basada en la solicitud proporcionada.)
     *
     * @param request SocietyClassRequest containing the class code and session ID.
     *                (Solicitud que contiene el código de clase y el ID de sesión.)
     * @return SocietyClassResponse containing the class ID, class key, and session ID.
     *         (Respuesta que contiene el ID de la clase, la clave de la clase y el ID de sesión.)
     */
    public static SocietyClassResponse getSocietyClass(SocietyClassRequest request) {

        try (Connection conn = PooledConnectionService.getConnection()) {
            // Construct SQL query with filtering condition (Construye la consulta SQL con condición de filtro)
            String query = Q.select(DatabaseTables.SOCIETY_CLASSES.tableName(),
                    SocietyClasses.CLASS_ID.columnName(),
                    SocietyClasses.CLASS_KEY.columnName())
                    .concat(Q.where(SocietyClasses.CLASS_ID.columnName()));

            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, request.getSocietyClassCode()); // Set query parameter (Asigna el parámetro de la consulta)
                try (ResultSet rs = st.executeQuery()) {

                    // If data is found, return a valid response (Si se encuentra, devuelve la respuesta válida)
                    if (rs.next()) {
                        return new SocietyClassResponse(request.getSessionId(),
                                ResultType.FOUND,
                                rs.getString(SocietyClasses.CLASS_ID.columnName()),
                                rs.getString(SocietyClasses.CLASS_KEY.columnName()));
                    }else{
                        return new SocietyClassResponse(request.getSessionId(),
                                ResultType.NOT_FOUND,
                                "",
                                "");
                    }
                }
            }
        } catch (SQLException ex) {
            // Log the error in case of an exception (Registra el error en caso de excepción)
            Logger.getLogger(SocietyClassManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return an empty response if no data is found or an error occurs
        // (Devuelve una respuesta vacía si no se encuentra la clase o hay un error)
        return new SocietyClassResponse( request.getSessionId(),ResultType.SQL_ERROR,"", "");
    }
    
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
                    if(!societyData.isEmpty()){
                        return new SocietyClassListResponse(request.getSessionId(),ResultType.FOUND,societyData); 
                    }else{
                        return new SocietyClassListResponse(request.getSessionId(),ResultType.NOT_FOUND,societyData); 
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SocietyClassListManager.class.getName()).log(Level.SEVERE, null, ex); // Log error // Registra el error
        }

        return new SocietyClassListResponse(request.getSessionId(),ResultType.SQL_ERROR,societyData); // Return response with retrieved data // Retorna la respuesta con los datos recuperados
    }
}
