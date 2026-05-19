

package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.services.base.AbstractInitPanel;

/**
 * @author Michael F. Sánchez
 * @param <T>
 * @param <F>
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractDataHandler <T extends AbstractInitPanel,F extends FormState>{

    protected T panel;
    protected F formState;
    protected SystemMessages notificator = new SystemMessages();

    public AbstractDataHandler(T panel, F formState) {
        this.panel = panel;
        this.formState = formState;
    }

    public boolean checkDataForSave() {
        return areAllComponentsFullyFilled();
    }

    public abstract boolean areAllComponentsFullyFilled();
}
