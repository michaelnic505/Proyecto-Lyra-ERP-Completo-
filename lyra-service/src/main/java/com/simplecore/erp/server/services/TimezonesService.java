
package com.simplecore.erp.server.services;

import com.simplecore.erp.server.managers.TimezonesManager;
import com.simplecore.erp.shared.requests.types.TimezoneByNameRetrieveRequest;
import com.simplecore.erp.shared.requests.types.TimezonesListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.TimezoneByNameRetrieveResponse;
import com.simplecore.erp.shared.responses.types.TimezonesListRetrieveResponse;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TimezonesService {

    public static TimezonesListRetrieveResponse getTimezones(TimezonesListRetrieveRequest request) {
        return TimezonesManager.getTimezones(request);
    }
    
    public static TimezoneByNameRetrieveResponse getTimezoneByName(TimezoneByNameRetrieveRequest request){
        return TimezonesManager.getTimezoneByName(request);
    }
}
