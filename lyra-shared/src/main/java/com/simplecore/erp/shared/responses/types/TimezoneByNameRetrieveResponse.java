

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.TimezoneDTO;
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
public class TimezoneByNameRetrieveResponse extends BaseResponse{

    private final TimezoneDTO timezoneDTO;
    
    public TimezoneByNameRetrieveResponse(String sessionId, ResultType resultType,TimezoneDTO timezoneDTO) {
        super(sessionId, resultType);
        this.timezoneDTO = timezoneDTO;
    }

    public TimezoneDTO getTimezoneDTO() {
        return timezoneDTO;
    }
    
    @Override
    public ResponseType getResponseType() {
        return ResponseType.TIMEZONES_BY_NAME;
    }
}
