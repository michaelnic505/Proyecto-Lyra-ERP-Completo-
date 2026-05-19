

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
public class FICORelationStatusListRetrieveResponse extends BaseResponse{

    private String[]dataSource;
    
    public FICORelationStatusListRetrieveResponse(String sessionId, ResultType resultType,List<String>statusList) {
        super(sessionId, resultType);
        dataSource = statusList.toArray(String[]::new);
    }

    public String[] getDataSource() {
        return dataSource;
    }
    
    @Override
    public ResponseType getResponseType() {
        return ResponseType.FICO_RELATION_STATUS_LIST;
    }
}
