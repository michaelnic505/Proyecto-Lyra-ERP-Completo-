
package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.client.dependencies.ContainerDependencies;
import com.simplecore.erp.client.dependencies.DependencyRegistrar;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JPanel;

/**
 * @author Michael F. Sánchez
 * @param <FormPanel>
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractRegistrar <FormPanel extends JPanel> implements DependencyRegistrar{

    protected final FormPanel panel;
    protected final OperationType operationType;
    protected final ContainerDependencies container;
    protected final ObjectOutputStream output;
    protected final ObjectInputStream input;
    protected final ActiveSession session;

    public AbstractRegistrar(FormPanel panel, 
            OperationType operationType, 
            ObjectOutputStream output, 
            ObjectInputStream input, 
            ActiveSession session) {
        this.operationType = operationType;
        this.container = new ContainerDependencies();
        this.panel = panel;
        this.output = output;
        this.input = input;
        this.session = session;
    }

    @Override
    public void registerDependencies() {
        initializeServices();
        registerServices();
    }

    @Override
    public ContainerDependencies container() {
        return container;
    }

    protected abstract void initializeServices();
    protected abstract void registerServices();
}
