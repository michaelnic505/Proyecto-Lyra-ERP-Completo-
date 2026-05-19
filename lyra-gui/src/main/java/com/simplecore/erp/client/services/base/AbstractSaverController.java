

package com.simplecore.erp.client.services.base;

import java.awt.event.ActionListener;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractSaverController {

    public abstract void onSaveButtonClick();

    public abstract ActionListener saveButtonListener();
}
