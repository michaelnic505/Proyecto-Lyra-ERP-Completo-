

package com.simplecore.erp.client.services.base;

import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.controllers.workspace.TaskPanel;
import com.simplecore.erp.client.dependencies.OperationAware;
import com.simplecore.erp.client.dependencies.OperationType;
import static com.simplecore.erp.client.dependencies.OperationType.CREATE;
import static com.simplecore.erp.client.dependencies.OperationType.MODIFY;
import static com.simplecore.erp.client.dependencies.OperationType.VIEW;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
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
public abstract class AbstractFormPanel extends JPanel implements TransactionPanel, TaskPanel, OperationAware {

    protected TranslationHelper tableTranslator = Workspace.translators(TranslatorType.TABLES);
    protected TranslationHelper windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
    protected SystemMessages notificator = new SystemMessages();

    protected String transactionCode;
    protected boolean isTasking = true;
    protected OperationType operationType;
    
    protected FormState formState;
    protected AbstractSaverController saveController;

    public AbstractFormPanel(OperationType operationType) {
        this.operationType = operationType;
    }
    

    @Override
    public void initialize(String transactionCode, ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.transactionCode = transactionCode;

        initGeneralController(operationType, output, input, session,transactionCode);
        setTasking(operationType);
        // Delegamos a la subclase
        this.formState = provideFormState();
        this.saveController = provideSaveController();
    }

    @Override
    public boolean isTaskRunning() {
        return isTasking;
    }

    @Override
    public ActionListener getOnTaskComplete() {
        return (saveController != null) ? saveController.saveButtonListener() : null;
    }

    @Override
    public String getTransactionCode() {
        return transactionCode;
    }

    @Override
    public FormState getFormState() {
        return formState;
    }

    @Override
    public void setOperationType(OperationType operationType) {
        switch (operationType) {
            case CREATE -> stateComponentsOnCreate();
            case MODIFY -> stateComponentsOnChange();
            case VIEW -> stateComponentsOnView();
        }
    }
    
    private void setTasking(OperationType operationType){
        switch(operationType){
            case CREATE-> isTasking = true;
            case MODIFY ->isTasking = true;
            case VIEW ->  isTasking = false;
        }
    }

    protected abstract void initGeneralController(
            OperationType operationType,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session,
            String transactionCode);

    protected abstract void stateComponentsOnCreate();
    protected abstract void stateComponentsOnChange();
    protected abstract void stateComponentsOnView();
 
    // Subclases deben implementar esto
    protected abstract FormState provideFormState();
    protected abstract AbstractSaverController provideSaveController();
}
