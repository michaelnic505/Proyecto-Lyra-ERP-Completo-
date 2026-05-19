package com.simplecore.erp.server.handlers;

import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.server.services.AuthenticationService;
import com.simplecore.erp.shared.requests.types.LoginRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
// Handles login requests by delegating authentication (Maneja las solicitudes de inicio de sesión delegando la autenticación)
public class LoginHandler implements RequestHandler<LoginRequest> {
    @Override
    public Object handle(LoginRequest request) {
        // Authenticate user with the provided credentials (Autentica al usuario con las credenciales proporcionadas)
        return AuthenticationService.authenticate(request);
    }
}



