
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.windows.auxiliar.AuxiliarWindow;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.auxiliarwindows.SystemUsersSearchWindow;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.i18n.WindowKeys;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.SystemUsersListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.SystemUsersListRetrieveResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountByAuthorMatchCodeController extends MatchCodeBaseController implements RowSelectionListener{

    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final MatchCode matchCode;

    public AccountByAuthorMatchCodeController(MatchCode matchCode) {
        this.matchCode = matchCode;
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        setMatchCodeEvent(matchCode);
    }

    private ActiveSession session;
    private ServerController serverController;

    @Override
    public void initialize(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.session = session;
        this.serverController = new ServerController(output, input);
    }
    
    private void setMatchCodeEvent(MatchCode matchCode){
        matchCode.getButton().addActionListener(e->{
            openAuxiliarWindowList(matchCode);
        });
    }
    
    private final List<Integer> columnsToReturn = List.of(1);
    
    private void openAuxiliarWindowList(MatchCode accountNumber) {
        SystemUsersSearchWindow auxWindow = new SystemUsersSearchWindow(Workspace.getFrame(), accountNumber.getButton(),
                getTableColumnsName(), getAccountingAccountsDataSource(), this, columnsToReturn);

        auxWindow.setWindowTitle(windowTranslator.getTranslation(WindowKeys.SYSTEM_USERS_AUXILIAR_WINDOW_TITLE.getKey()));
        auxWindow.setTitlePane(0, windowTranslator.getTranslation(WindowKeys.SYSTEM_USERS_AUXILIAR_WINDOW_TITLE_PANE.getKey()));
        auxWindow.setVisible(true);
    }
    //Extrae los titulos de columnas traducido segun idioma

    private String[] getTableColumnsName() {
        String id = tableTranslator.getTranslation(TableKeys.SYSTEM_USERS_ID.getKey());
        String username = tableTranslator.getTranslation(TableKeys.SYSTEM_USERS_USERNAME.getKey());
        String firstName = tableTranslator.getTranslation(TableKeys.SYSTEM_USERS_FIRSTNAME.getKey());
        String lastName = tableTranslator.getTranslation(TableKeys.SYSTEM_USERS_LASTNAME.getKey());
        String email = tableTranslator.getTranslation(TableKeys.SYSTEM_USERS_EMAIL.getKey());
        String position = tableTranslator.getTranslation(TableKeys.SYSTEM_USERS_POSITION.getKey());
        String department = tableTranslator.getTranslation(TableKeys.SYSTEM_USERS_DEPARTMENT.getKey());
        String role = tableTranslator.getTranslation(TableKeys.SYSTEM_USERS_ROLE.getKey());

        return new String[]{
            id,
            username,
            firstName,
            lastName,
            email,
            position,
            department,
            role
        };
    }


    //Extrae la matriz de datos de modelos en BD
    private Object[][] getAccountingAccountsDataSource() {
        try {
            String sessionId = session.getSessionId();
            int userId = session.getUserId();
            //Clase de solicitud
            SystemUsersListRetrieveRequest  request = new SystemUsersListRetrieveRequest(sessionId,userId);

            Object response = serverController.sendRequest(request);
            if (response instanceof SystemUsersListRetrieveResponse results) {
                return results.getSystemUsersListDataSource();
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountByAuthorMatchCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void onRowSelected(Object[] selectedData) {
        matchCode.getTextField().setText(String.valueOf(selectedData[0]));
    }
}
