

package com.simplecore.erp.server.handlers;

import com.simplecore.erp.server.services.TimezonesService;
import com.simplecore.erp.shared.requests.handlers.RequestHandler;
import com.simplecore.erp.shared.requests.types.TimezonesListRetrieveRequest;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TimezonesRetrieveHandler implements RequestHandler<TimezonesListRetrieveRequest>{

    @Override
    public Object handle(TimezonesListRetrieveRequest request) {
        return TimezonesService.getTimezones(request);
    }

}
