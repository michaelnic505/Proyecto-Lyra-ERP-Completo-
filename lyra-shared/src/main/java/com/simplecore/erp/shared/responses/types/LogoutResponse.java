
package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class LogoutResponse extends BaseResponse{

    private final boolean isSessionClosed;
    private final boolean forceLogout;
    private final String message;

    public LogoutResponse(String sessionId,ResultType resultType ,boolean isSessionClosed, boolean forceLogout,String message) {
        super(sessionId,resultType);
        this.isSessionClosed = isSessionClosed;
        this.forceLogout = forceLogout;
        this.message = message;
    }

    public boolean isSessionClosed() {return isSessionClosed;}
    public boolean isForceLogout() {return forceLogout;}
    public String getMessage() {return message;}
    
    @Override
    public ResponseType getResponseType() {
        return ResponseType.LOGOUT;
    }
    
    
}
