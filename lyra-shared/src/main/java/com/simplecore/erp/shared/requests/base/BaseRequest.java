
package com.simplecore.erp.shared.requests.base;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import java.io.Serializable;

public abstract class BaseRequest implements Request, Serializable {
    
    private static final long serialVersionUID = 1L;
    private final String sessionId;  
    private final int userId;
   
    public BaseRequest(String sessionId, int userId) {
        this.sessionId = sessionId;
        this.userId=userId; 
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }
}