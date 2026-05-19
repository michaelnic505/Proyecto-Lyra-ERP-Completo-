
package com.simplecore.erp.client.controllers.workspace;

import com.simplecore.erp.client.abstractions.FormState;
import java.awt.event.ActionListener;

/**
 *
 * @author user
 */
public interface TaskPanel {
    boolean isTaskRunning();
    ActionListener getOnTaskComplete();
    String getTransactionCode();
    FormState getFormState();
}
