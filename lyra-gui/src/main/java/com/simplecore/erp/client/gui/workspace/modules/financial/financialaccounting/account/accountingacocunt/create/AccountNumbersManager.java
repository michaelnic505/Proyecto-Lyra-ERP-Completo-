package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.create;

import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.models.AcModComboItem;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.exceptions.AccountNumberLimitExceededException;
import com.simplecore.erp.shared.models.dto.AccountSubclassDTO;
import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.LastAccountByParentRetrieveRequest;
import com.simplecore.erp.shared.requests.types.LastAccountBySubclassRetrieveRequest;
import com.simplecore.erp.shared.responses.types.LastAccountByParentRetrieveResponse;
import com.simplecore.erp.shared.responses.types.LastAccountBySubclassRetrieveResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JComboBox;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountNumbersManager {

    private final SystemMessages notificator;
    private final ActiveSession activeSession;
    private final ServerController serverConn;

    public AccountNumbersManager(ActiveSession activeSession, ObjectOutputStream output, ObjectInputStream input) {
        this.activeSession = activeSession;
        this.serverConn = new ServerController(output, input);
        this.notificator = new SystemMessages();
    }

    private JComboBox subclassCombo;
    private JComboBox parentCombo;

    public void initializeCombos(JComboBox subclassCombo, JComboBox parentCombo) {
        this.subclassCombo = subclassCombo;
        this.parentCombo = parentCombo;
    }

    public String getNextAccountingAccountNumber() throws IOException, ClassNotFoundException {
        AcModComboItem parentItem = (AcModComboItem) parentCombo.getSelectedItem();
        if (!parentItem.toString().trim().isEmpty()) {

            try {
                AccountingAccountDTO account = (AccountingAccountDTO) parentItem.getObjectClass();
                int parentAccountId = account.getAccountId();

                String lastAccountNumber = getLastAccountByParentAccountId(parentAccountId);
                String baseNumber = account.getAccountCode();

                return AccountNumberGenerator.getNextAccountNumberByParent(lastAccountNumber, baseNumber);

            } catch (AccountNumberLimitExceededException ex) {
                notificator.showErrorMsg(ex.getMessage());
            }

        } else {
            AcModComboItem subclassItem = (AcModComboItem) subclassCombo.getSelectedItem();
            if (!subclassItem.toString().trim().isEmpty()) {

                try {
                    AccountSubclassDTO subclass = (AccountSubclassDTO) subclassItem.getObjectClass();
                    int subclassId = subclass.getSubclassId();

                    String lastAccountNumber = getLastAccountCodeBySubclass(subclassId);
                    int baseNumber = subclass.getSubclassCode();

                    return AccountNumberGenerator.getNextAccountNumber(lastAccountNumber, baseNumber);

                } catch (AccountNumberLimitExceededException ex) {
                    notificator.showErrorMsg(ex.getMessage());
                }

            }

        }
        return null;
    }

    private String getLastAccountByParentAccountId(int accountId) throws IOException, ClassNotFoundException {
        String sessionId = activeSession.getSessionId();
        int userId = activeSession.getUserId();

        Object response = serverConn.sendRequest(new LastAccountByParentRetrieveRequest(sessionId, userId, accountId));
        if (response instanceof LastAccountByParentRetrieveResponse nextAccountResponse) {
            if (nextAccountResponse.isSqlError()) {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                return null;
            }

            if (nextAccountResponse.wasFound()) {
                return nextAccountResponse.getLasAccountNumber();
            } else {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND) + " " + accountId);
                return null;
            }
        }

        return null;
    }

    private String getLastAccountCodeBySubclass(int subclass) throws IOException, ClassNotFoundException {
        String sessionId = activeSession.getSessionId();
        int userId = activeSession.getUserId();

        Object response = serverConn.sendRequest(new LastAccountBySubclassRetrieveRequest(sessionId, userId, subclass));
        if (response instanceof LastAccountBySubclassRetrieveResponse lastAccountResponse) {
            if (lastAccountResponse.isSqlError()) {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                return null;
            }
            if (lastAccountResponse.wasFound()) {
                return lastAccountResponse.getLastAccountNumber();
            } else {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND)+" "+subclass);
                return null;
            }

        }
        return null;
    }
}
