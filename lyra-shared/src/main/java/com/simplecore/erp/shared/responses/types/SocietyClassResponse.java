        

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
public class SocietyClassResponse extends BaseResponse{
    
    private final String societyClassCode;
    private final String societyClassKey;

    public SocietyClassResponse( String sessionId,ResultType resultType,String societyClassCode, String societyClassKey) {
        super(sessionId, resultType);
        this.societyClassCode = societyClassCode;
        this.societyClassKey = societyClassKey;
    }

    public String getSocietyClassCode() {
        return societyClassCode;
    }

    public String getSocietyClassKey() {
        return societyClassKey;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.SOCIETY_CLASS;
    }
    
}
