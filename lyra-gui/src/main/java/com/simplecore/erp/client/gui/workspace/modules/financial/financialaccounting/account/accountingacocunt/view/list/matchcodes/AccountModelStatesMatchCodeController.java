
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.windows.auxiliar.AuxiliarWindow;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.auxiliarwindows.AccountModelStateSearchWindow;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.i18n.WindowKeys;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountModelStatesListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountModelStatesListRetrieveResponse;
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
public class AccountModelStatesMatchCodeController extends MatchCodeBaseController implements RowSelectionListener{

    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final MatchCode matchCode;

    public AccountModelStatesMatchCodeController(MatchCode matchCode) {
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
    
    private final List<Integer> columnsToReturn = List.of(0);
    
    private void openAuxiliarWindowList(MatchCode accountNumber) {
        AccountModelStateSearchWindow auxWindow = new AccountModelStateSearchWindow(Workspace.getFrame(), accountNumber.getButton(),
                getTableColumnsName(), getAccountingAccountsDataSource(), this, columnsToReturn);

        auxWindow.setWindowTitle(windowTranslator.getTranslation(WindowKeys.AC18_ACCOUNT_MODEL_STATES_TITLE.getKey()));
        auxWindow.setTitlePane(0, windowTranslator.getTranslation(WindowKeys.AC18_ACCOUNT_ACCOUNT_AUXILIAR_WINDOW_TITLE_PANE.getKey()));
        auxWindow.setVisible(true);
    }
    //Extrae los titulos de columnas traducido segun idioma

    private String[] getTableColumnsName() {
        String states = tableTranslator.getTranslation(TableKeys.AC18_ACCOUNT_MODEL_COLUMN_STATE.getKey());

        return new String[]{
            states,
        };
    }

    //Extrae la matriz de datos de modelos en BD
    private Object[][] getAccountingAccountsDataSource() {
        try {
            String sessionId = session.getSessionId();
            int userId = session.getUserId();
            //Clase de solicitud
            AccountModelStatesListRetrieveRequest request = new AccountModelStatesListRetrieveRequest(sessionId,userId);

            Object response = serverController.sendRequest(request);
            if (response instanceof AccountModelStatesListRetrieveResponse results) {
                return results.getStatesDataSource();
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountModelStatesMatchCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void onRowSelected(Object[] selectedData) {
        matchCode.getTextField().setText(String.valueOf(selectedData[0]));
    }
}
