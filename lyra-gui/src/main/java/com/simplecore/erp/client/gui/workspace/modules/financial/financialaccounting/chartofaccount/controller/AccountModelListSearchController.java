package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.windows.auxiliar.AuxiliarWindow;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.auxiliarwindows.AccountModelSearchWindow;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.subclasses.InitManageAccountingSubclasses;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.i18n.WindowKeys;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountModelListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountModelRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountModelListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountModelRetrieveResponse;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountModelListSearchController{

    private MatchCode modelMatchCode;
    private JLabel modelDescriptionLb;
    private RowSelectionListener rowSelection;
    private List<Integer> columnsToReturn; 
    private ActiveSession session;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerController serverController;

    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;

    public AccountModelListSearchController(Builder builder) {
        // Campos del Builder
        this.modelMatchCode = builder.modelMatchCode;
        this.modelDescriptionLb = builder.modelDescriptionLb;
        this.rowSelection = builder.rowSelection;
        this.columnsToReturn = builder.columnsToReturn;
        this.session = builder.session;
        this.output = builder.output;
        this.input = builder.input;
        this.serverController = new ServerController(output, input);
        // Campos preexistentes (traducciones)
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.notificator = new SystemMessages();
        setButtonEvent();
        setSearchTextFieldEvent();
    }
    
    private void setButtonEvent(){
        modelMatchCode.getButton().addActionListener(e->{
            openAccountModelList();
        });
    }
    
    private void setSearchTextFieldEvent(){
        modelMatchCode.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    rowSelection.onRowSelected(new Object[]{-1, null, null});
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(modelMatchCode.getTextField().getText()==null||modelMatchCode.getTextField().getText().isEmpty()){
                        return;
                    }
                    
                    AccountModelRetrieveResponse accountModel = getAcountModelByName();
                    if (accountModel == null) {
                        return;
                    }
                    rowSelection.onRowSelected(new Object[]{
                        accountModel.getModelId(),
                        accountModel.getModelName(),
                        accountModel.getModelDescription()});
                }
            }
        });
    }
    

    private void openAccountModelList() {
        AccountModelSearchWindow auxWindow = new AccountModelSearchWindow(Workspace.getFrame(), modelMatchCode.getButton(),
                getTableColumnsName(), getDataSource(), rowSelection, columnsToReturn);

        auxWindow.setWindowTitle(windowTranslator.getTranslation(WindowKeys.FI_ACCOUNT_MODEL_AUXILIAR_WINDOW_TITLE.getKey()));
        auxWindow.setTitlePane(0, windowTranslator.getTranslation(WindowKeys.FI_ACCOUNT_MODEL_AUXILIAR_WINDOW_TITLE_PANE.getKey()));
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

    private Object[][] getDataSource() {
        try {
            var request = new AccountModelListRetrieveRequest(session.getSessionId(), session.getUserId());
            Object response = serverController.sendRequest(request);

            if (response instanceof AccountModelListRetrieveResponse accountListResponse) {
                return accountListResponse.getDataSource();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AuxiliarWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public AccountModelRetrieveResponse getAcountModelByName() {
        String modelName = modelMatchCode.getTextField().getText().toUpperCase();
        try {
            String sessionId = session.getSessionId();
            int userId = session.getUserId();
            Object response = serverController.sendRequest(new AccountModelRetrieveRequest(sessionId, userId, modelName));
            if (response instanceof AccountModelRetrieveResponse accountModel) {
                if(accountModel.isSqlError()){
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return null;
                }
                if(accountModel.wasFound()){
                    return accountModel;
                }else{
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND)+" "+modelName);
                    return null;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(InitManageAccountingSubclasses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static class Builder {

        private MatchCode modelMatchCode;
        private JLabel modelDescriptionLb;
        private RowSelectionListener rowSelection;
        private List<Integer> columnsToReturn; // Asumo que es List<String>, ajusta según necesidad
        private ActiveSession session;
        private ObjectOutputStream output;
        private ObjectInputStream input;

        // Métodos para cada campo (fluent interface)
        public Builder button(MatchCode matchCode) {
            this.modelMatchCode = matchCode;
            return this;
        }
        public Builder modelDescriptionLb(JLabel modelDescriptionLb) {
            this.modelDescriptionLb = modelDescriptionLb;
            return this;
        }

        public Builder rowSelection(RowSelectionListener rowSelection) {
            this.rowSelection = rowSelection;
            return this;
        }

        public Builder columnsToReturn(List<Integer> columnsToReturn) {
            this.columnsToReturn = columnsToReturn;
            return this;
        }

        public Builder session(ActiveSession session) {
            this.session = session;
            return this;
        }

        public Builder output(ObjectOutputStream output) {
            this.output = output;
            return this;
        }

        public Builder input(ObjectInputStream input) {
            this.input = input;
            return this;
        }

        // Método build() para crear la instancia final
        public AccountModelListSearchController build() {
            // Validaciones opcionales (ej: campos obligatorios)
            if (modelMatchCode == null || session == null) {
                throw new IllegalStateException("Button & Session are required");
            }
            return new AccountModelListSearchController(this);
        }
    }

}
