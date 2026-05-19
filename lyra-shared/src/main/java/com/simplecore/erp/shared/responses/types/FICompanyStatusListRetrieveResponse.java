
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
public class FICompanyStatusListRetrieveResponse extends BaseResponse {

    private final String[]dataSource;
    public FICompanyStatusListRetrieveResponse(String sessionId, ResultType resultType,List<String> dataSource) {
        super(sessionId, resultType);
        this.dataSource = dataSource.toArray(String[]::new);
    }

    public String[] getDataSource() {
        return dataSource;
    }
    

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_COMPANY_STATUS_LIST;
    }
}
