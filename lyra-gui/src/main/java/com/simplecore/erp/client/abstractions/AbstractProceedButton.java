

package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.services.base.AbstractInitPanel;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Michael F. Sánchez
 * @param <P>
 * @param <F>
 * @param <D>
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractProceedButton<
        P extends AbstractInitPanel,
        F extends FormState, 
        D extends AbstractDataHandler> {

    protected P panel;
    protected F formState;
    protected D dataHandler;
    
    protected ObjectOutputStream output;
    protected ObjectInputStream input;
    protected ActiveSession session;
    protected String transactionCode;
    protected OperationType operationType;
    protected SystemMessages notificator = new SystemMessages();

    public AbstractProceedButton(P panel, F formState, D dataHandler, ObjectOutputStream output, ObjectInputStream input, ActiveSession session, OperationType operationType) {
        this.panel = panel;
        this.formState = formState;
        this.dataHandler = dataHandler;
        this.output = output;
        this.input = input;
        this.session = session;
        this.operationType = operationType;
    }
    protected abstract void openNextModule();
}
