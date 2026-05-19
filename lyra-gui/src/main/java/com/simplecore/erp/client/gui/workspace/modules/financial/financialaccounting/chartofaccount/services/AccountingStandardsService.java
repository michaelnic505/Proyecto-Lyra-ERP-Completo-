
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountingStandardsRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountingStandardsRetrieveResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountingStandardsService {

    private String[]items;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final ActiveSession session;
    private ServerController serverController;
    private SystemMessages notificator = new SystemMessages();

    public AccountingStandardsService(ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session) {
        this.output = output;
        this.input = input;
        this.session = session;
        serverController = new ServerController(output, input);
        getItemList();
    }

    public String[] getListItems() {
        return items;
    }

    private void getItemList() {
        String sessionId = session.getSessionId();
        int userId = session.getUserId();

        try {
            var request = new AccountingStandardsRetrieveRequest(sessionId, userId);
            Object response = serverController.sendRequest(request);
            if (response instanceof AccountingStandardsRetrieveResponse accountingStandards) {
                if (accountingStandards.isSqlError()) {
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                }
                if (accountingStandards.wasFound()) {
                    items = accountingStandards.getStandards();
                    return;
                } else {
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountingStandardsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        items = emptyArray();
    }
    
    private String[]emptyArray(){
        return new String[0];
    }

}
