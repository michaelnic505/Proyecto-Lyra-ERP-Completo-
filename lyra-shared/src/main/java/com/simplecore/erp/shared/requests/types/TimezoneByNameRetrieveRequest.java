

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
public class TimezoneByNameRetrieveRequest extends BaseRequest{

    private final String timezoneName;
    
    public TimezoneByNameRetrieveRequest(String sessionId, int userId,String timezoneName) {
        super(sessionId, userId);
        this.timezoneName = timezoneName;
    }

    public String getTimezoneName() {
        return timezoneName;
    }
    
    @Override
    public RequestType getRequestType() {
        return RequestType.TIMEZONES_BY_NAME;
    }
}
