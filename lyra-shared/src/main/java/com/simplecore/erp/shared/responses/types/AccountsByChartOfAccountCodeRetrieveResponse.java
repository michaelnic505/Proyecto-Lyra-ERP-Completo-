

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.models.dto.AccountCardData;
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
public class AccountsByChartOfAccountCodeRetrieveResponse extends BaseResponse {

    private final List<AccountCardData> accountDataList;

    public AccountsByChartOfAccountCodeRetrieveResponse(String sessionId, ResultType resultType, List<AccountCardData> accountDataList) {
        super(sessionId, resultType);
        this.accountDataList = accountDataList;
    }

    public List<AccountCardData> getAccountDataList() {
        return accountDataList;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNTS_BY_CHART_OF_ACCOUNT_CODE;
    }
}
