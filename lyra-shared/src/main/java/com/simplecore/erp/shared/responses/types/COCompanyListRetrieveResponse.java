

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
public class COCompanyListRetrieveResponse extends BaseResponse {

    private final Object[][]dataSource;
    public COCompanyListRetrieveResponse(String sessionId, ResultType resultType,List<Object[]>dataSource) {
        super(sessionId, resultType);
        this.dataSource = dataSource.toArray(Object[][]::new);
    }

    public Object[][] getDataSource() {
        return dataSource;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.CO_COMPANY_LIST;
    }
}
