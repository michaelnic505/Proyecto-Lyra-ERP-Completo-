
package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.client.dependencies.ContainerDependencies;
import com.simplecore.erp.client.dependencies.DependencyRegistrar;
import com.simplecore.erp.client.dependencies.OperationAware;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.services.base.AbstractFormPanel;
import java.util.function.Supplier;

/**
 * @author Michael F. Sánchez
 * @param <T> generics for panels 
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public abstract class AbstractFormController<T extends AbstractFormPanel> {

    protected final T panel; // Tipo específico del panel

    protected final ContainerDependencies container;
    protected final DependencyRegistrar registrar;
    protected final OperationType operationType;

    public AbstractFormController(T panel,
            OperationType operationType,
            Supplier<? extends DependencyRegistrar> registrarSupplier) {
        this.panel = panel;
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
    
    public abstract void consumeDependencies();
}
