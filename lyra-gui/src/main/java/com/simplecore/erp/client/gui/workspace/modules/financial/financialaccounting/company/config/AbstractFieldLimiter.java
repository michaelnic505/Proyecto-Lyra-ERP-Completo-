
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.config;

import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

/**
 * @author Michael F. Sánchez
 * @param <Component>
 * @param <Self>
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractFieldLimiter<Component, Self extends AbstractFieldLimiter<Component, Self>> {

    protected Component component;

    public AbstractFieldLimiter(Component component) {
        this.component = component;
    }

    public abstract Self applyFilter();

    protected void apply(JTextComponent field, DocumentFilter filter) {
        ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
    }
}
