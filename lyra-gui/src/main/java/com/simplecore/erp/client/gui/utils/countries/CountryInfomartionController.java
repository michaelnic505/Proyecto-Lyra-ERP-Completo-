
package com.simplecore.erp.client.gui.utils.countries;

import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.CountryDTO;
import com.simplecore.erp.shared.models.dto.CurrencyDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.CountriesListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CountryRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CurrenciesListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CurrencyRetrieveRequest;
import com.simplecore.erp.shared.responses.types.CountriesListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CountryRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CurrenciesListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CurrencyRetrieveResponse;
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
public class CountryInfomartionController {

    private MatchCode matchCodeSearch;
    private TableSelectionListener countryListener;
    private List<Integer> fieldList;
    private ActiveSession session;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerController serverController;
    private TranslationHelper translatorTable;
    private TranslationHelper translatorCountry;
    private TranslationHelper windowTranslator;
    private SystemMessages notificator;
    

    public CountryInfomartionController(Builder builder) {
        this.output = builder.output;
        this.input  = builder.input;
        this.session  = builder.session;
        this.matchCodeSearch = builder.matchCodeSearch;
        this.countryListener = builder.countryListener;
        this.fieldList = builder.fieldList;
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.translatorTable = Workspace.translators(TranslatorType.TABLES);
        this.translatorCountry  = Workspace.translators(TranslatorType.COUNTRIES);
        this.serverController = new ServerController(output, input);
        this.notificator = new SystemMessages();
        setMatchCodeButtonEvent(builder.type);
        setMatchCodeTextFieldEvent(builder.type);
    }
    
    private void setMatchCodeButtonEvent(Type type){
        matchCodeSearch.getButton().addActionListener(e->{
            switch(type){
                case COUNTRY->openCountriesListWindow();
                case CURRENCY->openCurrenciesListWindow();
            }
        });
    }
    
    private void setMatchCodeTextFieldEvent(Type type) {
        matchCodeSearch.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    countryListener.onRowSelected(new Object[]{null,null,null,null});
                    return;
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(matchCodeSearch.getTextField().getText().isEmpty()){
                        return;
                    }
                    
                    switch(type){
                        case COUNTRY->searchCountryByCode();
                        case CURRENCY->searchCurrencyByCode();
                    }
                }
            }
        });
    }

    private void openCountriesListWindow() {
        var searchWindow = new CountryInformationSearchWindow(
                Workspace.getFrame(), 
                getCountriesColumnNames(), 
                getCountriesDataSource(),
                countryListener, 
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(CountryMessages.COUNTRY_INFO_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(CountryMessages.COUNTRY_INFO_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }
    
    private void openCurrenciesListWindow() {
        var searchWindow = new CountryInformationSearchWindow(
                Workspace.getFrame(), 
                getCurrenciesColumnNames(), 
                getCurrenciesDataSource(),
                countryListener, 
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(CountryMessages.CURRENCY_INFO_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(CountryMessages.CURRENCY_INFO_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }
    
    private String[] getCountriesColumnNames(){
        
        String countryID = translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_COUNTRY_ID.getKey());
        String alphaCode2 = translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_COUNTRY_ALPHA2.getKey());
        String alphaCode3 = translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_COUNTRY_ALPHA3.getKey());
        String countryName = translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_COUNTRY_NAME.getKey());
        String currencyCode = translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_CODE.getKey());
        String currencyName = translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_NAME.getKey());
        
        return new String[]{
            countryID,
            alphaCode2,
            alphaCode3,
            countryName,
            currencyCode,
            currencyName};
    }

    private String[] getCurrenciesColumnNames() {
        String currencyCode = translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_CODE.getKey());
        String currencyName = translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_NAME.getKey());
        String currencySymbol = translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_SYMBOL.getKey());
        String symbolName = translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_SYMBOL_NAME.getKey());

        return new String[]{
            currencyCode,
            currencyName,
            currencySymbol,
            symbolName};
    }
    
    private Object[][] getCountriesDataSource(){
        try {
            String sessionId = session.getSessionId();
            int userId = session.getUserId();
            
            var request  = new CountriesListRetrieveRequest(sessionId,userId);
            
            Object response = serverController.sendRequest(request);
            if(response instanceof CountriesListRetrieveResponse countries){
                if(countries.isSqlError()){
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
                    return new Object[0][0];
                }
                if(countries.wasFound()){
                    return countries.getDataSource();
                } else {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                    return new Object[0][0];
                }
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SOME_DATA_COULD_NOT_BE_RECOVERED));
        }
        return new Object[0][0];
    }

    private Object[][] getCurrenciesDataSource() {
        try {
            String sessionId = session.getSessionId();
            int userId = session.getUserId();

            var request = new CurrenciesListRetrieveRequest(sessionId, userId);
            Object response = serverController.sendRequest(request);
           
            if (response instanceof CurrenciesListRetrieveResponse currencies) {
                if (currencies.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
                    return new Object[0][0];
                }
                if (currencies.wasFound()) {
                    return currencies.getDataSource();
                } else {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                    return new Object[0][0];
                }
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SOME_DATA_COULD_NOT_BE_RECOVERED));
        }
        return new Object[0][0];
    }
    
    public boolean searchCountryByCode(){
        String countryCode = matchCodeSearch.getTextField().getText().trim();
        if(countryCode==null||countryCode.isEmpty()){
            matchCodeSearch.getTextField().requestFocus();
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
            return false;
        }
        try {
            var countryByCode = new CountryRetrieveRequest(session.getSessionId(),session.getUserId(), countryCode);
            Object response = serverController.sendRequest(countryByCode);

            if (response instanceof CountryRetrieveResponse countryDTO) {
               
                if (countryDTO.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
                    countryListener.onRowSelected(new Object[]{null, null, null, null});
                    return false;
                }
                if (countryDTO.wasFound()) {
                    CountryDTO country = countryDTO.getCountryDto();
                    Object[] data = new Object[]{
                        country.getID(),
                        country.getAlpha2(),
                        country.getAlpha3(),
                        country.getCountryName()
                    };
                    countryListener.onRowSelected(data);
                    return true;
                } else {
                    countryListener.onRowSelected(new Object[]{null, null, null, null});
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                    return false;
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
            Logger.getLogger(CountryInfomartionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        countryListener.onRowSelected(new Object[]{null, null, null, null});
        return false;
    }

    public boolean searchCurrencyByCode() {
        String currencyCode = matchCodeSearch.getTextField().getText().trim();
        if (currencyCode == null || currencyCode.isEmpty()) {
            matchCodeSearch.getTextField().requestFocus();
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
            return false;
        }
        try {
            var currencyByCode = new CurrencyRetrieveRequest(session.getSessionId(), session.getUserId(), currencyCode);
            Object response = serverController.sendRequest(currencyByCode);

            if (response instanceof CurrencyRetrieveResponse currencyDTO) {

                if (currencyDTO.isSqlError()) {
                    countryListener.onRowSelected(new Object[]{null, null, null, null});
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return false;
                }
                if (currencyDTO.wasFound()) {
                    CurrencyDTO country = currencyDTO.getCurrencyDto();
                    Object[] data = new Object[]{
                        country.getCurrencyCode(),
                        country.getCurrencyName(),
                        country.getSymbol(),
                        country.getSymbolName()
                    };
                    countryListener.onRowSelected(data);
                    return true;
                } else {
                    countryListener.onRowSelected(new Object[]{null, null, null, null});
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                    return false;
                }

            }
        } catch (IOException | ClassNotFoundException ex) {
            notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
            Logger.getLogger(CountryInfomartionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        countryListener.onRowSelected(new Object[]{null,null,null,null});
        return false;
    }

    public static class Builder {

        private Type type;
        private MatchCode matchCodeSearch;
        private TableSelectionListener countryListener;
        private List<Integer> fieldList;
        private ActiveSession session;
        private ObjectOutputStream output;
        private ObjectInputStream input;
        
        public Builder withType(Type type){
            this.type = type;
            return this;
        }

        public Builder withMatchCodeSearch(MatchCode matchCodeSearch) {
            this.matchCodeSearch = matchCodeSearch;
            return this;
        }

        public Builder withCountryListener(TableSelectionListener countryListener) {
            this.countryListener = countryListener;
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

        public CountryInfomartionController build() {
            return new CountryInfomartionController(this);
        }
    }

    public enum Type{
        COUNTRY,
        CURRENCY
    }
}
