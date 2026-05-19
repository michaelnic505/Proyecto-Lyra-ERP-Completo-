
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
public class ChartOfAccountStatusRetrieveResponse extends BaseResponse {

    private final String[] status;

    public ChartOfAccountStatusRetrieveResponse(String sessionId, ResultType resultType, List<String> status) {
        super(sessionId, resultType);
        this.status = status.toArray(String[]::new);
    }

    public String[] getStatus() {
        return status;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_CHART_OF_ACCOUNT_STATUS;
    }

}
