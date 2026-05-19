
package com.simplecore.erp.client.controllers.maintree;

import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.services.login.LogoutController;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.TransactionRequest;
import com.simplecore.erp.shared.responses.types.LogoutResponse;
import com.simplecore.erp.shared.responses.types.TransactionResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TreeTransactionService {

    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final ActiveSession activeSession;

    public TreeTransactionService(ObjectOutputStream output, ObjectInputStream input, ActiveSession activeSession) {
        this.output = output;
        this.input = input;
        this.activeSession = activeSession;
    }

    public boolean checkTransactionAccess(String transaction) {
        String sessionId = activeSession.getSessionId();
        int userId = activeSession.getUserId();
        String role = activeSession.getRole();
        try {
            output.writeObject(new TransactionRequest(role, transaction, sessionId,userId));
            output.flush();
            Object response = input.readObject();
            
            if (response instanceof TransactionResponse transactionResponse) {
                return transactionResponse.hasAccess();
            }
            LogoutController.logoutIfNecessary(response);
        } catch (IOException | ClassNotFoundException | InterruptedException ex) {
            Logger.getLogger(TreeTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    
    
}
