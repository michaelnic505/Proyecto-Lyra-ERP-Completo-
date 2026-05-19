
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
public class AccountModelListRetrieveResponse extends BaseResponse {
    
    private final Object[][] dataSource;
    private final String message;

    public AccountModelListRetrieveResponse(String sessionId, ResultType resultType, List<Object[]> dataSource, String message) {
        super(sessionId, resultType);
        this.dataSource = dataSource.toArray(new Object[0][0]);
        this.message = message;
    }

    public Object[][] getDataSource() {
        return dataSource;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_MODEL_LIST;
    }

}
