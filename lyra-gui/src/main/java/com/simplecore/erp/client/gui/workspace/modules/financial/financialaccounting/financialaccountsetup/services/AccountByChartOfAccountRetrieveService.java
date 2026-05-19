

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services;

import com.simplecore.erp.client.abstractions.AbstractDataSourceService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.AccountCardData;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountsByChartOfAccountCodeRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.AccountsByChartOfAccountCodeRetrieveResponse;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountByChartOfAccountRetrieveService extends AbstractDataSourceService{

    public AccountByChartOfAccountRetrieveService(
            ActiveSession session, 
            ObjectOutputStream output, 
            ObjectInputStream input) {
        super(session, output, input);
    }

    private List<AccountCardData> getAccountsByChart(String chartCode) {
        var request = new AccountsByChartOfAccountCodeRetrieveRequest(sessionID, userID, chartCode);
        Object response = serverController.sendData(request);

        if (response instanceof AccountsByChartOfAccountCodeRetrieveResponse accounts) {
            if (accounts.isSqlError()) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            } else if (accounts.wasFound()) {
                return accounts.getAccountDataList();
            } else {
                notificator.showInfoMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
            }
        }
        return Collections.emptyList();
    }

    public List<AccountCardData> getListOfAccountsToConfig(String chartCode) {
        return getAccountsByChart(chartCode);
    }
}
