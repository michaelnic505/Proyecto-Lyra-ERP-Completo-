
package com.simplecore.erp.client.gui.utils.currencies;

import com.simplecore.erp.client.gui.utils.countries.CountryFieldsName;
import com.simplecore.erp.client.gui.utils.countries.CountryInfomartionController;
import com.simplecore.erp.client.gui.utils.countries.CountryInformationSearchWindow;
import com.simplecore.erp.client.gui.utils.countries.CountryMessages;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.services.base.AbstractMatchCodeService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.CurrencyDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.CurrenciesListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CurrencyRetrieveRequest;
import com.simplecore.erp.shared.responses.types.CurrenciesListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CurrencyRetrieveResponse;
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
public class CurrencyService extends AbstractMatchCodeService{

    public CurrencyService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        super(session, output, input);
    }

    @Override
    public void openSearchWindow(TableSelectionListener listener, List<Integer> fieldList) {
        var searchWindow = new CountryInformationSearchWindow(
                Workspace.getFrame(),
                getCurrenciesColumnNames(),
                getCurrenciesDataSource(),
                listener,
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(CountryMessages.CURRENCY_INFO_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(CountryMessages.CURRENCY_INFO_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }

    public boolean searchCurrencyByCode(TableSelectionListener countryListener, String currencyCode) {
        var currencyByCode = new CurrencyRetrieveRequest(sessionID, userID, currencyCode);
        Object response = serverController.sendData(currencyByCode);
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
        countryListener.onRowSelected(new Object[]{null, null, null, null});
        return false;
    }

    private String[] getCurrenciesColumnNames() {
        return new String[]{
            translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_CODE.getKey()),
            translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_NAME.getKey()),
            translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_SYMBOL.getKey()),
            translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_SYMBOL_NAME.getKey())
        };
    }

    private Object[][] getCurrenciesDataSource() {
            var request = new CurrenciesListRetrieveRequest(sessionID, userID);
            Object response = serverController.sendData(request);

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
        return new Object[0][0];
    }
}
