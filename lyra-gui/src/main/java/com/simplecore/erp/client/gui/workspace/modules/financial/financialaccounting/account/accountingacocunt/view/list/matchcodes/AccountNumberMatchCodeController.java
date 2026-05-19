
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.matchcodes;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.auxiliarwindows.AccountingAccountSearchWindow;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.i18n.WindowKeys;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountingAccountListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountingAccountListRetrieveResponse;
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
public class AccountNumberMatchCodeController extends MatchCodeBaseController implements RowSelectionListener{

    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final MatchCode accountNumberMatchCode;

    public AccountNumberMatchCodeController(MatchCode accountNumberMatchCode) {
        this.accountNumberMatchCode = accountNumberMatchCode;
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        setMatchCodeEvent(accountNumberMatchCode);
    }

    private ActiveSession session;
    private ServerController serverController;

    @Override
    public void initialize(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.session = session;
        this.serverController = new ServerController(output, input);
    }
    
    private void setMatchCodeEvent(MatchCode accountNumber){
        accountNumber.getButton().addActionListener(e->{
            openAuxiliarWindowList(accountNumber);
        });
    }
    
    private final List<Integer> columnsToReturn = List.of(0);
    
    private void openAuxiliarWindowList(MatchCode accountNumber) {
        AccountingAccountSearchWindow auxWindow = new AccountingAccountSearchWindow(Workspace.getFrame(), accountNumber.getButton(),
                getTableColumnsName(), getAccountingAccountsDataSource(), this, columnsToReturn);

        auxWindow.setWindowTitle(windowTranslator.getTranslation(WindowKeys.AC16_ACCOUNT_AUXILIAR_WINDOW_TITLE.getKey()));
        auxWindow.setTitlePane(0, windowTranslator.getTranslation(WindowKeys.AC16_ACCOUNT_ACCOUNT_AUXILIAR_WINDOW_TITLE_PANE.getKey()));
        auxWindow.setVisible(true);
    }
    //Extrae los titulos de columnas traducido segun idioma
    private String[] getTableColumnsName() {
        String accountNumber = tableTranslator.getTranslation(TableKeys.AC16_ACCOUNT_NUMBER.getKey());
        String accountName = tableTranslator.getTranslation(TableKeys.AC16_ACCOUNT_NAME.getKey());
        String accountDescription = tableTranslator.getTranslation(TableKeys.AC16_ACCOUNT_DESCRIPTION.getKey());
        String createdBy = tableTranslator.getTranslation(TableKeys.AC16_ACCOUNT_CREATEDBY.getKey());
        String accountStatus = tableTranslator.getTranslation(TableKeys.AC16_ACCOUNT_STATUS.getKey());
        String isClosed = tableTranslator.getTranslation(TableKeys.AC16_ACCOUNT_IS_CLOSED.getKey());

        return new String[]{
            accountNumber,
            accountName,
            accountDescription,
            createdBy,
            accountStatus,
            isClosed
        };
    }
    //Extrae la matriz de datos de modelos en BD
    private Object[][] getAccountingAccountsDataSource() {
        try {
            String sessionId = session.getSessionId();
            int userId = session.getUserId();
            //Clase de solicitud
            AccountingAccountListRetrieveRequest request = new AccountingAccountListRetrieveRequest(sessionId,userId);

            Object response = serverController.sendRequest(request);
            if (response instanceof AccountingAccountListRetrieveResponse accountListResponse) {
                return accountListResponse.getAccountListDataSource();
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountNumberMatchCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void onRowSelected(Object[] selectedData) {
        accountNumberMatchCode.getTextField().setText(String.valueOf(selectedData[0]));
    }
}
