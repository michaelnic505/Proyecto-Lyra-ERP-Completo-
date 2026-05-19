

package com.simplecore.erp.client.services.base;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.responses.base.BaseResponse;
import java.io.IOException;
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
public abstract class AbstractComboService {

    private final ActiveSession session;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    protected ServerController serverController;
    protected String sessionID;
    protected int userID;
    protected SystemMessages notificator = new SystemMessages();

    public AbstractComboService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.session = session;
        this.output = output;
        this.input = input;
        this.serverController = new ServerController(output, input);
        this.sessionID = session.getSessionId();
        this.userID = session.getUserId();
    }
    
    public abstract String[] getDataRequested();
}

