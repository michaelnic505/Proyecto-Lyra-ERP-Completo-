

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.TimezonesService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.TimezoneByNameRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TimezoneByNameRetrieveHandler implements RequestHandler<TimezoneByNameRetrieveRequest>{

    @Override
    public Object handle(TimezoneByNameRetrieveRequest request) {
        return TimezonesService.getTimezoneByName(request);
                
    }
}
