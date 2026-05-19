

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.create;

import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountingAccountCreateRequest;
import com.simplecore.erp.shared.responses.types.AccountingAccountCreateResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class PreparedAccountCreateRequest {

    private final ActiveSession activeSession;
    private final SystemMessages notificator;
    private final String modelName;
    private final String modelDescription;
    private final ServerController serverConn;

    public PreparedAccountCreateRequest(ActiveSession activeSession,String modelName,String modelDescription,
            ObjectOutputStream output, ObjectInputStream input) {
        this.modelName = modelName;
        this.modelDescription = modelDescription;
        this.activeSession = activeSession;
        this.serverConn = new ServerController(output, input);
        this.notificator = new SystemMessages();
    }

    private JTextField accountNumberTf;
    private MatchCode accountModelMatchCode;
    private JLabel modelDescriptionLabel;
    private JTextField createdByTextField;
    private JComboBox statusCombo;
    private JTextField createdAtTextField;
    private JCheckBox isClosedCheckbox;
    
    public void initializeComponents(JTextField accountNumberTf, MatchCode accountModelMatchCode, JLabel modelDescriptionLabel, JTextField createdByTextField, JComboBox<String> statusCombo, JTextField createdAtTextField, JCheckBox isClosedCheckbox) {
        this.accountNumberTf = accountNumberTf;
        this.accountModelMatchCode = accountModelMatchCode;
        this.modelDescriptionLabel = modelDescriptionLabel;
        this.createdByTextField = createdByTextField;
        this.statusCombo = statusCombo;
        this.createdAtTextField = createdAtTextField;
        this.isClosedCheckbox = isClosedCheckbox;
    }

    public void sendAccountingAccountCreateRequest(String accountNumber,AccountingAccountDTO newAccount) throws IOException, ClassNotFoundException{
     
        String sessionId = activeSession.getSessionId();
        String username = activeSession.getUsername();
        int userId = activeSession.getUserId();

        Object response = serverConn.sendRequest(new AccountingAccountCreateRequest(sessionId, userId, newAccount));
        if (response instanceof AccountingAccountCreateResponse createResponse) {

            if(createResponse.isSqlError()){
                notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                return;
            }
            
            if (createResponse.wasCreated()) {
                accountNumberTf.setText(accountNumber);
                accountModelMatchCode.getTextField().setText(modelName);
                modelDescriptionLabel.setText(modelDescription);
                createdByTextField.setText(username);
                statusCombo.setSelectedItem(createResponse.getStatus());
                createdAtTextField.setText(createResponse.getCreatedAt().toString());
                isClosedCheckbox.setSelected(createResponse.isClosed());
                notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.CREATED) + " " + accountNumber);
                PanelManager.goBack();
            }else{
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_CREATED) + " " + accountNumber);
            }
        }
    }
}
