
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.windows.auxiliar.AuxiliarWindow;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.auxiliarwindows.AccountModelSearchWindow;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.i18n.WindowKeys;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountModelListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountModelListRetrieveResponse;
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
public class AccountModelMatchCodeController extends MatchCodeBaseController implements RowSelectionListener{

    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final MatchCode matchCode;

    public AccountModelMatchCodeController(MatchCode matchCode) {
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
            openAccountModelList(matchCode);
        });
    }
    
    private final List<Integer> columnsToReturn = List.of(1);
    
    private void openAccountModelList(MatchCode matchCode) {
        AccountModelSearchWindow auxWindow = new AccountModelSearchWindow(Workspace.getFrame(), matchCode.getButton(),
                getTableColumnsName(), getDataSource(), this, columnsToReturn);
        
        auxWindow.setWindowTitle(windowTranslator.getTranslation(WindowKeys.FI_ACCOUNT_MODEL_AUXILIAR_WINDOW_TITLE.getKey()));
        auxWindow.setTitlePane(0,windowTranslator.getTranslation(WindowKeys.FI_ACCOUNT_MODEL_AUXILIAR_WINDOW_TITLE_PANE.getKey()));
        auxWindow.setVisible(true);
    }

    private String[] getTableColumnsName() {
        String id = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_ID.getKey());
        String modelName = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_MODEL_NAME.getKey());
        String systemState = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_MODEL_STATE.getKey());
        String modelDescription = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_MODEL_DESCRIPTION.getKey());
        String createdAt = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_MODEL_CREATEDAT.getKey());
        String createdBy = tableTranslator.getTranslation(TableKeys.ACCOUNT_MODEL_TABLE_COLUMN_MODEL_CREATEDBY.getKey());
        

        return new String[]{
            id,
            modelName,
            systemState,
            modelDescription,
            createdAt,
            createdBy
        };
    }

    //Extrae la matriz de datos de modelos en BD
    private Object[][] getDataSource() {
        try {
            String sessionId = session.getSessionId();
            int userId = session.getUserId();
            //Clase de solicitud
            AccountModelListRetrieveRequest request = new AccountModelListRetrieveRequest(sessionId, userId);

            Object response = serverController.sendRequest(request);
            if (response instanceof AccountModelListRetrieveResponse accountListResponse) {
                return accountListResponse.getDataSource();
            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountModelMatchCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void onRowSelected(Object[] selectedData) {
        matchCode.getTextField().setText(String.valueOf(selectedData[0]));
    }
}
