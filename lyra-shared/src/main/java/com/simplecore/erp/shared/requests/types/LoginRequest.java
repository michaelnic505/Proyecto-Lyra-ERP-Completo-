
package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
/**
 * Represents a login request sent from the client to the server. (Representa una solicitud de inicio de sesión enviada desde el cliente al servidor.)
 */
public class LoginRequest extends BaseRequest {

    private final String username; // User's username (Nombre de usuario del usuario)
    private final String password; // User's password (Contraseña del usuario)
    private final String terminal; // Terminal name from which the request is made (Nombre del terminal desde donde se realiza la solicitud)
    private final String ipAddress; // IP address of the requesting device (Dirección IP del dispositivo solicitante)

    /**
     * Constructs a login request with the provided credentials and device information. 
     * (Construye una solicitud de inicio de sesión con las credenciales y la información del dispositivo proporcionadas.)
     * 
     * @param username  User's username (Nombre de usuario del usuario)
     * @param password  User's password (Contraseña del usuario)
     * @param terminal  Terminal name (Nombre del terminal)
     * @param ipAddress IP address of the device (Dirección IP del dispositivo)
     */
    public LoginRequest(String username, String password, String terminal, String ipAddress) {
        super(null,0);  // Login has no session yet (Login no tiene sesión aún)
        this.username = username;
        this.password = password;
        this.terminal = terminal;
        this.ipAddress = ipAddress;
    }

    /**
     * Gets the username. (Obtiene el nombre de usuario.)
     * @return username (nombre de usuario)
     */
    public String getUsername() { return username; }

    /**
     * Gets the password. (Obtiene la contraseña.)
     * @return password (contraseña)
     */
    public String getPassword() { return password; }

    /**
     * Gets the terminal name. (Obtiene el nombre del terminal.)
     * @return terminal (nombre del terminal)
     */
    public String getTerminal() { return terminal; }

    /**
     * Gets the IP address. (Obtiene la dirección IP.)
     * @return ipAddress (dirección IP)
     */
    public String getIpAddress() { return ipAddress; }

    /**
     * Gets the request type, which in this case is LOGIN. (Obtiene el tipo de solicitud, que en este caso es LOGIN.)
     * @return RequestType.LOGIN (Tipo de solicitud LOGIN)
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.LOGIN;
    }
}
