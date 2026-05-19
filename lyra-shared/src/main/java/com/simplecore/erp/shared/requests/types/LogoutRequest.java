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
public class LogoutRequest  extends BaseRequest {

    private final boolean logoutRequest;

    public LogoutRequest(boolean logoutRequest, String sessionId, int userId) {
        super(sessionId, userId);
        this.logoutRequest = logoutRequest;
    }

    public boolean isLogoutRequest() {
        return logoutRequest;
    }
    @Override
    public RequestType getRequestType() {
        return RequestType.LOGOUT;
    }
    

}
