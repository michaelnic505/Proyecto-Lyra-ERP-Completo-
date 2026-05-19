

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;
import java.io.Serializable;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountSubclassListRetrieveResponse extends BaseResponse {

    private final Object[][] subclassesListDataSource;

    public AccountSubclassListRetrieveResponse(String sessionId, ResultType resultType, List<Object[]> dataSource) {
        super(sessionId, resultType);
        this.subclassesListDataSource = dataSource.toArray(new Object[0][0]);
    }

    public Object[][] getSubclassesListDataSource() {
        return subclassesListDataSource;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_SUBCLASSES_LIST;
    }

}
