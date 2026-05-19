
package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JPanel;

/**
 * @author Michael F. Sánchez
 * @param <T>
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class BasePanelController<T extends JPanel> {
    
    protected final T panel;
    protected final ObjectOutputStream output;
    protected final ObjectInputStream input;
    protected final ActiveSession session;
    protected final String transactionCode;

    public BasePanelController(T panel, 
            ObjectOutputStream output, 
            ObjectInputStream input, 
            ActiveSession session, 
            String transactionCode) {
        this.panel = panel;
        this.output = output;
        this.input = input;
        this.session = session;
        this.transactionCode = transactionCode;
    }
    
    public abstract void initializeControllers();
    public abstract void initializeServices();
    
}
