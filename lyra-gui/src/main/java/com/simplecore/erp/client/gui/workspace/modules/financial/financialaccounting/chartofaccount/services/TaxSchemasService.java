
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.auxiliar.TaxSchemaKeys;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.auxiliar.TaxSchemaListener;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.auxiliar.TaxSchemasSearchWindow;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.auxiliar.TaxWindowMessages;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountStatusService;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.TaxSchemaDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.TaxSchemaRetrieveRequest;
import com.simplecore.erp.shared.requests.types.TaxSchemasListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.TaxSchemaRetrieveResponse;
import com.simplecore.erp.shared.responses.types.TaxSchemasListRetrieveResponse;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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

public class TaxSchemasService {

    private MatchCode matchCodeSearch;
    private List<Integer> fieldList;
    private TaxSchemaListener taxSchemaListener;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final ActiveSession session;
    private final ServerController serverController;
    private TranslationHelper translatorTable;
    private TranslationHelper windowTranslator;
    private SystemMessages notificator;

    // Constructor privado, solo accesible a través del Builder
    private TaxSchemasService(Builder builder) {
        this.output = builder.output;
        this.input = builder.input;
        this.session = builder.session;
        this.matchCodeSearch = builder.matchCodeSearch;
        this.taxSchemaListener = builder.taxSchemaListener;
        this.fieldList = builder.fieldList;
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.translatorTable = Workspace.translators(TranslatorType.TABLES);
        this.serverController = new ServerController(output, input);
        this.notificator = new SystemMessages();
        setMatchCodeButtonEvent();
        setMatchCodeTextFieldEvent();
    }
    public void setTaxSchemaListener(TaxSchemaListener taxSchemaListener){
        this.taxSchemaListener = taxSchemaListener;
    }
    
    private void setMatchCodeButtonEvent(){
        matchCodeSearch.getButton().addActionListener(e->openCountriesListWindow());
    }
    private void setMatchCodeTextFieldEvent() {
        matchCodeSearch.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    taxSchemaListener.onRowSelected(new Object[]{null,null});
                    return;
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(matchCodeSearch.getTextField().getText().isEmpty()){
                        return;
                    }
                   searchSchemaById();
                }
            }
        });
    }

    private void openCountriesListWindow() {
        var searchWindow = new TaxSchemasSearchWindow(
                Workspace.getFrame(),
                getColumnNames(),
                getDataSource(),
                taxSchemaListener,
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(TaxWindowMessages.TAX_SCHEMAS_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(TaxWindowMessages.TAX_SCHEMAS_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);

    }

    private String[] getColumnNames() {
        List<String> cols = new ArrayList<>();
        for(TaxSchemaKeys column:TaxSchemaKeys.values()){
            cols.add(translatorTable.getTranslation(column.getKey()));
        }
        return cols.toArray(String[]::new);
    }

    private Object[][] getDataSource() {
        String sessionId = session.getSessionId();
        int userId = session.getUserId();

        try {
            var request = new TaxSchemasListRetrieveRequest(sessionId, userId);
            Object response = serverController.sendRequest(request);
            if (response instanceof TaxSchemasListRetrieveResponse taxSchemas) {
                return taxSchemas.getDataSource();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ChartOfAccountStatusService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Object[0][0];
    }
    
    public boolean searchSchemaById() {
        String schemaId = matchCodeSearch.getTextField().getText().trim();
        if (schemaId == null || schemaId.isEmpty()) {
            matchCodeSearch.getTextField().requestFocus();
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
            return false;
        }
        try {

            var schema = new TaxSchemaRetrieveRequest(session.getSessionId(), session.getUserId(), schemaId);
            Object response = serverController.sendRequest(schema);
            if(response instanceof TaxSchemaRetrieveResponse taxSchema){
                if(taxSchema.isSqlError()){
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return false;
                }
                if (taxSchema.wasFound()) {
                    TaxSchemaDTO dto = taxSchema.getTaxSchema();
                    Object[] data = new Object[]{
                        dto.getId(),
                        dto.getName()
                    };

                    taxSchemaListener.onRowSelected(data);
                    return true;
                }else{
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND)+" "+schemaId);
                    return false;
                }
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
        }
        notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SOME_DATA_COULD_NOT_BE_RECOVERED));
        taxSchemaListener.onRowSelected(new Object[]{null,null});
        return false;
    }

    public static class Builder {

        private MatchCode matchCodeSearch;
        private TaxSchemaListener taxSchemaListener;
        private List<Integer> fieldList;
        private ActiveSession session;
        private ObjectOutputStream output;
        private ObjectInputStream input;

        public Builder withMatchCodeSearch(MatchCode matchCodeSearch) {
            this.matchCodeSearch = matchCodeSearch;
            return this;
        }
        
        public Builder withTaxSchemaListener(TaxSchemaListener taxSchemaListener){
            this.taxSchemaListener = taxSchemaListener;
            return this;
        }

        public Builder withFieldList(List<Integer> fieldList) {
            this.fieldList = fieldList;
            return this;
        }

        public Builder withSession(ActiveSession session) {
            this.session = session;
            return this;
        }

        public Builder withOutputStream(ObjectOutputStream output) {
            this.output = output;
            return this;
        }

        public Builder withInputStream(ObjectInputStream input) {
            this.input = input;
            return this;
        }
        public TaxSchemasService build(){
            return new TaxSchemasService(this);
        }
    }

}
