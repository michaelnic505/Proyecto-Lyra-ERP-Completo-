
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountingAccountStatusListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountingAccountStatusListRetrieveResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountStatusComboController extends MatchCodeBaseController{

    private JComboBox<String> comboBox;

    public AccountStatusComboController(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    private ActiveSession session;
    private ServerController serverController;
    @Override
    public void initialize(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.session = session;
        this.serverController = new ServerController(output, input);
        initComboBox();
    }
    
    private void initComboBox() {
        var statusList = getStatusList();
        if (statusList == null || statusList.isEmpty()) {
            return;
        }
        statusList.forEach(comboBox::addItem);
    }

    private List<String> getStatusList() {
        try {
            String sessionId = session.getSessionId();
            int userId = session.getUserId();

            var request = new AccountingAccountStatusListRetrieveRequest(sessionId, userId);
            Object response = serverController.sendRequest(request);
            if (response instanceof AccountingAccountStatusListRetrieveResponse result) {
                if (result.wasFound()) {
                    return result.getStatusDataSource();
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountStatusComboController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.emptyList();
    }

}
