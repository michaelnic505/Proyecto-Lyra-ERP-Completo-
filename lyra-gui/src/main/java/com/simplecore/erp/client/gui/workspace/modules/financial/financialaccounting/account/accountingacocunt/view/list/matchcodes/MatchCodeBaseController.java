
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes;

import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class MatchCodeBaseController {
    protected ActiveSession activeSession;
    protected ObjectOutputStream output;
    protected ObjectInputStream input;

    public void initialize(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.activeSession = session;
        this.output = output;
        this.input = input;
    }
}
