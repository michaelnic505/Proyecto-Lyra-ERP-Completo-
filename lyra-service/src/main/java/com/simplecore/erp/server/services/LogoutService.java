

package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.LogoutManager;
import com.simplecore.erp.shared.requests.types.LogoutRequest;
import com.simplecore.erp.shared.responses.types.LogoutResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class LogoutService {

    public static LogoutResponse loginOut(LogoutRequest request){
        return LogoutManager.loginOut(request);
    }
}
