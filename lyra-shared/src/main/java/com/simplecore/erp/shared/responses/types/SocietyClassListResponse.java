
package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class SocietyClassListResponse extends BaseResponse{

    private final String[][] societies;

    public SocietyClassListResponse( String sessionId,ResultType resultType,List<String[]> societyList) {
        super(sessionId, resultType);
        this.societies = societyList.toArray(new String[0][0]);
    }

    public String[][] getSocieties() {
        return societies;
    }

    @Override
    public ResponseType getResponseType() {
       return ResponseType.SOCIETY_CLASS_LIST;
    }
}
