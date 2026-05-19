
package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.models.queries.QueryFilters;
import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AcountingAccountFilterQueryRetrieveRequest  extends BaseRequest{

    private final List<QueryFilters> filters;

    public AcountingAccountFilterQueryRetrieveRequest(String sessionId, int userId,List<QueryFilters> filters) {
        super(sessionId, userId);
        this.filters = filters;
    }

    public List<QueryFilters> getFilters() {
        return filters;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNTING_ACCOUNT_FILTER_QUERY_RETRIEVE;
    }

}
