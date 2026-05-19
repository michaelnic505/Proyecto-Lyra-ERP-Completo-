

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
public class AccountingStandardsRetrieveResponse extends BaseResponse{

    private final String[] standards;
    
    public AccountingStandardsRetrieveResponse(String sessionId,ResultType resultType,List<String> standards) {
        super(sessionId, resultType);
        this.standards = standards.toArray(String[]::new);
    }

    public String[] getStandards() {
        return standards;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNTING_STANDARDS;
    }

}
