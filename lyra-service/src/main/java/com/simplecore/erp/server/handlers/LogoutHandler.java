
package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.LogoutService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.LogoutRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class LogoutHandler  implements RequestHandler<LogoutRequest> {
    @Override
    public Object handle(LogoutRequest request) {
        return LogoutService.loginOut(request);
    }

}
