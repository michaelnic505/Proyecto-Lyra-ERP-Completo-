

package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.client.gui.notifications.SystemMessages;
import javax.swing.JComboBox;

/**
 * @author Michael F. Sánchez
 * @param <S>
 * @param <I>
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractComboController<S,I> {

    protected final JComboBox<I> combo;
    protected final S service;
    protected final SystemMessages messages = new SystemMessages();

    public AbstractComboController(JComboBox<I> combo, S service) {
        this.combo = combo;
        this.service = service;
        initComboBox();
    }
    protected abstract void initComboBox();
}

