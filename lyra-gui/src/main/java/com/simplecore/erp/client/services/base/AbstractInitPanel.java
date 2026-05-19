

package com.simplecore.erp.client.services.base;

import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.controllers.workspace.TaskPanel;
import com.simplecore.erp.client.dependencies.OperationAware;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JPanel;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractInitPanel extends JPanel implements TransactionPanel, TaskPanel, OperationAware{

    protected SystemMessages notificator = new SystemMessages();
    protected boolean isTasking = false;
    protected OperationType operationType;
    protected String transactionCode;

    public AbstractInitPanel(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public void initialize(String transactionCode, ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.transactionCode = transactionCode;
        initGeneralController(operationType, output, input, session,transactionCode);
    }

    @Override
    public boolean isTaskRunning() {
        return isTasking;
    }

    @Override
    public ActionListener getOnTaskComplete() {
        return null;
    }

    @Override
    public String getTransactionCode() {
        return transactionCode;
    }

    @Override
    public FormState getFormState() {
        return null;
    }
    
    protected abstract void initGeneralController(
            OperationType operationType,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session,
            String transactionCode);

    @Override
    public void setOperationType(OperationType operationType) {
    }
     
}
