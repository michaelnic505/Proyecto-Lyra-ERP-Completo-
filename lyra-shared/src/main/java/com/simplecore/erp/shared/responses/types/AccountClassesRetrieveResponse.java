

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
public class AccountClassesRetrieveResponse extends BaseResponse {

    private final String[][] accountClasses;

    public AccountClassesRetrieveResponse(String sessionId, ResultType resultType, List<String[]> accountClassesList) {
        super(sessionId, resultType);
        this.accountClasses = accountClassesList.toArray(new String[0][0]);
    }

    public String[][] getAccountClasses() {
        return accountClasses;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_CLASS;
    }
    
}
