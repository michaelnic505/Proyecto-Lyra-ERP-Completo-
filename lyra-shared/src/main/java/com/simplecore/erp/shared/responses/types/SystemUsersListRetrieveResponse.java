
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
public class SystemUsersListRetrieveResponse extends BaseResponse {

    private final Object[][] systemUsersListDataSource;

    public SystemUsersListRetrieveResponse(String sessionId,ResultType resultType,List<Object[]>dataSource) {
        super(sessionId, resultType);
        this.systemUsersListDataSource = dataSource.toArray(Object[][]::new);
    }

    public Object[][] getSystemUsersListDataSource() {
        return systemUsersListDataSource;
    }
    
    @Override
    public ResponseType getResponseType() {
        return ResponseType.SYSTEM_USERS_LIST;
    }
}
