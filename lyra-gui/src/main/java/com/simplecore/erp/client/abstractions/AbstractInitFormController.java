

package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.client.dependencies.ContainerDependencies;
import com.simplecore.erp.client.dependencies.DependencyRegistrar;
import com.simplecore.erp.client.dependencies.OperationAware;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.services.base.AbstractInitPanel;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.Supplier;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractInitFormController <T extends AbstractInitPanel>{
    protected final T panel; // Tipo específico del panel

    protected final ContainerDependencies container;
    protected final DependencyRegistrar registrar;
    protected final OperationType operationType;
    protected final ObjectOutputStream output;
    protected final ObjectInputStream input;
    protected final ActiveSession session;

    public AbstractInitFormController(T panel,
            OperationType operationType,
            ObjectOutputStream output, 
            ObjectInputStream input, 
            ActiveSession session,
            Supplier<? extends DependencyRegistrar> registrarSupplier) {
        this.panel = panel;
        this.output = output;
        this.input = input;
        this.session = session;
        this.registrar = registrarSupplier.get();
        this.container = registrar.container();
        this.operationType = operationType;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void initialize() {
        registrar.registerDependencies();
        container.injectDependencies(this);//Inyeccion en controller
        container.injectDependencies(panel);//Inyeccion en panel GUI, baby.
        if (panel instanceof OperationAware) {
            ((OperationAware)panel).setOperationType(operationType);
        }
    }
    public void consumeDependencies(){
    }
}
