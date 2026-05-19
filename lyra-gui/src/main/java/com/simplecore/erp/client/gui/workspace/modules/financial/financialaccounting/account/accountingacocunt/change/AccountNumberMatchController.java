package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.change;

import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.auxiliarwindows.AccountingAccountSearchWindow;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.i18n.WindowKeys;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterVarchar;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.AccountSubclassDTO;
import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountSubclassByIdRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountingAccountListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountingAccountRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountSubclassesRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountingAccountListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountingAccountRetrieveResponse;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.text.AbstractDocument;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountNumberMatchController {

    private final MatchCode accountNumberMatchCode;
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;
    private final List<Integer> columnsToReturn;
    private final RowSelectionListener rowsSelection;
    private ActiveSession activeSession;
    private AccountingAccountDTO accountingAccount;
    private int modelId;
    private int classId;

    public AccountNumberMatchController(MatchCode accountNumberMatchCode,RowSelectionListener rowsSelection,List<Integer> columnsToReturn) {
        this.accountNumberMatchCode = accountNumberMatchCode;
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.notificator = new SystemMessages();
        this.rowsSelection = rowsSelection;
        this.columnsToReturn = columnsToReturn;
        matchCodeLimits();
        matchCodeSocietyEvents();
    }
    
    private ServerController serverController;
    private JLabel labdelDescription;
    
    public void initialize(ActiveSession activeSession,ObjectOutputStream output, ObjectInputStream input,JLabel labelDescription) {
        this.activeSession = activeSession;
        this.serverController = new ServerController(output, input);
        this.labdelDescription = labelDescription;
    }
  
    private void matchCodeLimits(){
        ((AbstractDocument)accountNumberMatchCode.getTextField().getDocument()).setDocumentFilter(new DocumentFilterVarchar(50));
    }
    
    private void matchCodeSocietyEvents() {
        accountNumberMatchCode.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    clearValues();
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (areEmptyComponents()) {
                        clearValues();
                        return;
                    }
                    refreshView();
                }
            }
        });
        accountNumberMatchCode.getButton().addActionListener(e -> {
            openAccountModelList();
        });
    }
    public void refreshView() {
        String accountCode = accountNumberMatchCode.getTextField().getText().trim();
        getAccountingAccountByCode(accountCode);
    }

    private void clearValues() {
        accountingAccount = null;
        setModelId(-1);
        setClassId(-1);
    }

    private void getValuesFromSubclass(int subclassId) {
        String sessionId = activeSession.getSessionId();
        int userId = activeSession.getUserId();
        try {
            Object response = serverController.sendRequest(new AccountSubclassByIdRetrieveRequest(sessionId, userId, subclassId));
            if (response instanceof AccountSubclassesRetrieveResponse subclasses) {

                if (subclasses.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return;
                }

                if (!subclasses.wasFound()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                    return;
                }

                for (AccountSubclassDTO subclass : subclasses.getSubclassesList()) {
                    if (subclass.getSubclassId() == subclassId) {
                        setModelId(subclass.getModelId());
                        setClassId(subclass.getClassId());
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountNumberMatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean areEmptyComponents() {
        return (accountNumberMatchCode.getTextField().getText() == null
                || accountNumberMatchCode.getTextField().getText().isEmpty());
    }
    
    public void setValuesInComponents(Object[] values){
        String accountCode = String.valueOf(values[0]);
        String accountName = String.valueOf(values[1]);
        
        accountNumberMatchCode.getTextField().setText(accountCode);
        labdelDescription.setText(accountName);
        getAccountingAccountByCode(accountCode);
    }

    private void openAccountModelList() {
        AccountingAccountSearchWindow auxWindow = new AccountingAccountSearchWindow(Workspace.getFrame(), accountNumberMatchCode.getButton(),
                getTableColumnsName(), getAccountingAccountsDataSource(), rowsSelection, columnsToReturn);

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
            String sessionId = activeSession.getSessionId();
            int userId = activeSession.getUserId();
            
            //Clase de solicitud
            AccountingAccountListRetrieveRequest request = new AccountingAccountListRetrieveRequest(sessionId,userId);

            Object response = serverController.sendRequest(request);
            if (response instanceof AccountingAccountListRetrieveResponse accountListResponse) {
                return accountListResponse.getAccountListDataSource();
            }
  
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountNumberMatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void getAccountingAccountByCode(String accountCode) {
        try {
            String sessionId = activeSession.getSessionId();
            int userId = activeSession.getUserId();
            AccountingAccountRetrieveRequest accountRetrieve = new AccountingAccountRetrieveRequest(sessionId, userId, accountCode);

            Object response = serverController.sendRequest(accountRetrieve);
            if (response instanceof AccountingAccountRetrieveResponse accountingAccountResponse) {

                if (accountingAccountResponse.isSqlError()) {
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return;
                }

                if (!accountingAccountResponse.wasFound()) {
                    clearValues();
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                    return;
                }

                accountingAccount = accountingAccountResponse.getAccountingAccount();
                accountNumberMatchCode.getTextField().setText(accountCode);
                labdelDescription.setText(accountingAccount.getAccountName());
                getValuesFromSubclass(accountingAccount.getSubclassId());
            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountNumberMatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AccountingAccountDTO getAccountingAccount() {
        return accountingAccount;
    }

    public int getModelId() {
        return modelId;
    }
    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
    public int getClassId() {
        return classId;
    }
    public void setClassId(int classId) {
        this.classId = classId;
    }
}
