
package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.services.SessionService;
import com.simplecore.erp.shared.requests.types.LogoutRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.LogoutResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class LogoutManager {

    public static LogoutResponse loginOut(LogoutRequest request){
        SessionService.closeSession(request.getUserId());
        return new LogoutResponse(request.getSessionId(),ResultType.EXECUTED ,true, true,"Your session has been ended by the server");
    }
}
