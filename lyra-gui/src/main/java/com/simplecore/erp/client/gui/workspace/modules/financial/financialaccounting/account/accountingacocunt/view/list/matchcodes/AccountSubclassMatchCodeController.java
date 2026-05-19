
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.auxiliarwindows.AccountSubclassSearchWindow;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.i18n.WindowKeys;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountSubclassListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountSubclassListRetrieveResponse;
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
public class AccountSubclassMatchCodeController extends MatchCodeBaseController implements RowSelectionListener{

    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final MatchCode matchCode;

    public AccountSubclassMatchCodeController(MatchCode matchCode) {
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
        AccountSubclassSearchWindow auxWindow = new AccountSubclassSearchWindow(Workspace.getFrame(), accountNumber.getButton(),
                getTableColumnsName(), getAccountingAccountsDataSource(), this, columnsToReturn);

        auxWindow.setWindowTitle(windowTranslator.getTranslation(WindowKeys.AC18_ACCOUNT_AUXILIAR_WINDOW_TITLE.getKey()));
        auxWindow.setTitlePane(0, windowTranslator.getTranslation(WindowKeys.AC18_ACCOUNT_ACCOUNT_AUXILIAR_WINDOW_TITLE_PANE.getKey()));
        auxWindow.setVisible(true);
    }
    //Extrae los titulos de columnas traducido segun idioma

    private String[] getTableColumnsName() {
        String subclassId = tableTranslator.getTranslation(TableKeys.AC18_ACCOUNT_SUBCLASS_SUBCLASS_ID.getKey());
        String subclassCode = tableTranslator.getTranslation(TableKeys.AC18_ACCOUNT_SUBCLASS_SUBCLASS_CODE.getKey());
        String subclassName = tableTranslator.getTranslation(TableKeys.AC18_ACCOUNT_SUBCLASS_SUBCLASS_NAME.getKey());
        String classId = tableTranslator.getTranslation(TableKeys.AC18_ACCOUNT_SUBCLASS_CLASS_ID.getKey());
        String className = tableTranslator.getTranslation(TableKeys.AC18_ACCOUNT_SUBCLASS_CLASS_NAME.getKey());
        String modelId = tableTranslator.getTranslation(TableKeys.AC18_ACCOUNT_SUBCLASS_MODEL_ID.getKey());
        String modelName = tableTranslator.getTranslation(TableKeys.AC18_ACCOUNT_SUBCLASS_MODEL_NAME.getKey());
        String modelDescription = tableTranslator.getTranslation(TableKeys.AC18_ACCOUNT_SUBCLASS_MODEL_DESCRIPTION.getKey());
        String modelState = tableTranslator.getTranslation(TableKeys.AC18_ACCOUNT_SUBCLASS_MODE_STATE.getKey());

        return new String[]{
            subclassId,
            subclassCode,
            subclassName,
            classId,
            className,
            modelId,
            modelName,
            modelDescription,
            modelState
        };
    }

    //Extrae la matriz de datos de modelos en BD
    private Object[][] getAccountingAccountsDataSource() {
        try {
            String sessionId = session.getSessionId();
            int userId = session.getUserId();
            //Clase de solicitud
            AccountSubclassListRetrieveRequest request = new AccountSubclassListRetrieveRequest(sessionId,userId);

            Object response = serverController.sendRequest(request);
            if (response instanceof AccountSubclassListRetrieveResponse subclasesList) {
                return subclasesList.getSubclassesListDataSource();
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountSubclassMatchCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void onRowSelected(Object[] selectedData) {
        matchCode.getTextField().setText(String.valueOf(selectedData[0]));
    }
}
