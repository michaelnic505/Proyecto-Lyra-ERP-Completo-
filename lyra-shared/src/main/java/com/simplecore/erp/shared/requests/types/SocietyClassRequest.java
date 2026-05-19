
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
public class SocietyClassRequest extends BaseRequest {

    private final String societyClassCode;

    public SocietyClassRequest(String societyClassCode, String sessionId, int userId) {
        super(sessionId, userId);
        this.societyClassCode = societyClassCode;
    }
   

    public String getSocietyClassCode() {
        return societyClassCode;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.SOCIETY_CLASS;
    }
}
