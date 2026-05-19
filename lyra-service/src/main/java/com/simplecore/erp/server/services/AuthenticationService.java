    package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.AuthenticationManager;
import com.simplecore.erp.shared.requests.types.LoginRequest;
import com.simplecore.erp.shared.responses.types.LoginResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AuthenticationService {

    /**
     * Authenticates a user by checking the provided credentials.
     *
     * Autentica a un usuario verificando las credenciales proporcionadas.
     *
     * @param request The login request containing username and password.
     *                La solicitud de inicio de sesión que contiene el usuario y la contraseña.
     * @return LoginResponse indicating the authentication result.
     *         LoginResponse indicando el resultado de la autenticación.
     */
    public static LoginResponse authenticate(LoginRequest request) {
        return AuthenticationManager.authenticate(request);
    }
}
