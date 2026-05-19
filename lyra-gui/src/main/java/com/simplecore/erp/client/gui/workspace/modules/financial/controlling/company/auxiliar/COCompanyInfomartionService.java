
package com.simplecore.erp.client.gui.workspace.modules.financial.controlling.company.auxiliar;

import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.utils.countries.*;
import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.COCompanyDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.COCompanyByCodeRetrieveRequest;
import com.simplecore.erp.shared.requests.types.COCompanyListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.COCompanyByCodeRetrieveResponse;
import com.simplecore.erp.shared.responses.types.COCompanyListRetrieveResponse;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
public class COCompanyInfomartionService {

    private MatchCode matchCodeSearch;
    private TableSelectionListener companyListener;
    private List<Integer> fieldList;
    private ActiveSession session;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerController serverController;
    private TranslationHelper translatorTable;
    private TranslationHelper windowTranslator;
    private SystemMessages notificator;
    

    public COCompanyInfomartionService(Builder builder) {
        this.output = builder.output;
        this.input  = builder.input;
        this.session  = builder.session;
        this.matchCodeSearch = builder.matchCodeSearch;
        this.companyListener = builder.companyListener;
        this.fieldList = builder.fieldList;
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.translatorTable = Workspace.translators(TranslatorType.TABLES);
        this.serverController = new ServerController(output, input);
        this.notificator = new SystemMessages();
        setMatchCodeButtonEvent();
        setMatchCodeTextFieldEvent();
    }
    
    private void setMatchCodeButtonEvent(){
        matchCodeSearch.getButton().addActionListener(e->{
            openCountriesListWindow();
        });
    }
    
    private void setMatchCodeTextFieldEvent() {
        matchCodeSearch.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    companyListener.onRowSelected(new Object[]{null,null,null});
                    return;
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(matchCodeSearch.getTextField().getText().isEmpty()){
                        return;
                    }
                    searchCompanyByCode();
                }
            }
        });
    }

    private void openCountriesListWindow() {
        var searchWindow = new COCompanySearchWindow(
                Workspace.getFrame(), 
                getColumnNames(), 
                getDataSource(),
                companyListener, 
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_INFO_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_INFO_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }

    private String[] getColumnNames() {
        String controllingCompanyCode = translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_CODE.getKey());
        String controllingCompanyName = translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_NAME.getKey());
        String controllingCompanyLegalName = translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_LEGAL_NAME.getKey());
        String controllingCompanyCountryCode = translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_COUNTRY_CODE.getKey());
        String controllingCompanyCurrencyCode = translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_CURRENCY_CODE.getKey());
        String controllingCompanyTimeZone = translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_TIME_ZONE.getKey());
        String controllingCompanyValidFrom = translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_VALID_FROM.getKey());
        String controllingCompanyValidTo = translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_VALID_TO.getKey());
        String controllingCompanyStatus = translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_STATUS.getKey());
        String controllingCompanyCreatedBy = translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_CREATED_BY.getKey());

        return new String[]{
            controllingCompanyCode,
            controllingCompanyName,
            controllingCompanyLegalName,
            controllingCompanyCountryCode,
            controllingCompanyCurrencyCode,
            controllingCompanyTimeZone,
            controllingCompanyValidFrom,
            controllingCompanyValidTo,
            controllingCompanyStatus,
            controllingCompanyCreatedBy
        };
    }

    private Object[][] getDataSource() {
        try {
            String sessionId = session.getSessionId();
            int userId = session.getUserId();
            
            var request  = new COCompanyListRetrieveRequest(sessionId,userId);
            
            Object response = serverController.sendRequest(request);
            if(response instanceof COCompanyListRetrieveResponse companyCO){
                if(companyCO.isSqlError()){
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
                }
                if(companyCO.wasFound()){
                    return companyCO.getDataSource();
                } else {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                }
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SOME_DATA_COULD_NOT_BE_RECOVERED));
        }
        return new Object[0][10];
    }
    
    public boolean searchCompanyByCode(){
        String companyCode = matchCodeSearch.getTextField().getText().trim();
        if(companyCode==null||companyCode.isEmpty()){
            matchCodeSearch.getTextField().requestFocus();
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
            return false;
        }
        try {
            var companyRequest = new COCompanyByCodeRetrieveRequest(session.getSessionId(), session.getUserId(), companyCode);
            Object response = serverController.sendRequest(companyRequest);

            if (response instanceof COCompanyByCodeRetrieveResponse companyResponse) {
               
                if (companyResponse.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
                    companyListener.onRowSelected(new Object[]{null, null, null});
                    return false;
                }
                if (companyResponse.wasFound()) {
                    COCompanyDTO dto = companyResponse.getCompanyDTO();
                    Object[] data = new Object[]{
                        dto.getCompanyCode(),
                        dto.getCompanyName(),
                        dto
                    };
                    companyListener.onRowSelected(data);
                    return true;
                } else {
                    companyListener.onRowSelected(new Object[]{null, null, null});
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                    return false;
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
            Logger.getLogger(COCompanyInfomartionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        companyListener.onRowSelected(new Object[]{null, null, null});
        return false;
    }

    public static class Builder {

        private MatchCode matchCodeSearch;
        private TableSelectionListener companyListener;
        private List<Integer> fieldList;
        private ActiveSession session;
        private ObjectOutputStream output;
        private ObjectInputStream input;


        public Builder withMatchCodeSearch(MatchCode matchCodeSearch) {
            this.matchCodeSearch = matchCodeSearch;
            return this;
        }

        public Builder withCompanyListener(TableSelectionListener countryListener) {
            this.companyListener = countryListener;
            return this;
        }
        
        public Builder withFieldList(List<Integer> fieldList){
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

        public COCompanyInfomartionService build() {
            return new COCompanyInfomartionService(this);
        }
    }
}
