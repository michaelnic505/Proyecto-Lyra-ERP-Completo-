

package com.simplecore.erp.client.services.base;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractMatchCodeService {

    private final ActiveSession session;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    protected ServerController serverController;
    protected SystemMessages notificator = new SystemMessages();
    
    protected TranslationHelper translatorTable = Workspace.translators(TranslatorType.TABLES);
    protected TranslationHelper translatorCountry = Workspace.translators(TranslatorType.COUNTRIES);
    protected TranslationHelper windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
    
    protected String sessionID;
    protected int userID;

    public AbstractMatchCodeService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.session = session;
        this.output = output;
        this.input = input;
        this.serverController = new ServerController(output, input);
        this.sessionID = session.getSessionId();
        this.userID = session.getUserId();
    }
    
    
    public abstract void openSearchWindow(TableSelectionListener listener, List<Integer> fieldList);
}
