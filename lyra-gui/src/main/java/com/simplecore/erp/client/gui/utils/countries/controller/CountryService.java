

package com.simplecore.erp.client.gui.utils.countries.controller;

import com.simplecore.erp.client.gui.utils.countries.CountryFieldsName;
import com.simplecore.erp.client.gui.utils.countries.CountryMessages;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.utils.currencies.CurrencyInformationSearchWindow;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.services.base.AbstractMatchCodeService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.CountryDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.CountriesListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.CountryRetrieveRequest;
import com.simplecore.erp.shared.responses.types.CountriesListRetrieveResponse;
import com.simplecore.erp.shared.responses.types.CountryRetrieveResponse;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

/**
 * Servicio para la gestión de países, incluyendo búsqueda, traducción y visualización.
 */
public class CountryService extends AbstractMatchCodeService{

    public CountryService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        super(session, output, input);
    }

    private String[] getColumns() {
        return new String[]{
            translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_COUNTRY_ID.getKey()),
            translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_COUNTRY_ALPHA2.getKey()),
            translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_COUNTRY_ALPHA3.getKey()),
            translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_COUNTRY_NAME.getKey()),
            translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_CODE.getKey()),
            translatorTable.getTranslation(CountryFieldsName.COUNTRY_SEARCH_WINDOW_CURRENCY_NAME.getKey())
        };
    }
    
    private Object[][] getDataSource() {
            var request = new CountriesListRetrieveRequest(sessionID, userID);

            Object response = serverController.sendData(request);
            if (response instanceof CountriesListRetrieveResponse countries) {
                if (countries.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                }else if (countries.wasFound()) {
                    translatedCountryNames(countries.getDataSource());
                    return countries.getDataSource();
                } else {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                }
            }
        return emptyData();
    }
    
    private Object[][] emptyData() {
        return new Object[0][getColumns().length];
    }

    private void translatedCountryNames(Object[][] data) {
        for (Object[] row : data) {
            if (row.length > 3 && row[1] != null) {
                row[3] = translatorCountry.getTranslation(row[1].toString());
            }
        }
    }
    private String translatedCountryName(String alpha2){
        return translatorCountry.getTranslation(alpha2);
    }

    /**
     * Busca un país por su código (puede ser Alpha-2 o numérico).
     *
     * @param countryCode     el código del país a buscar
     * @param countryListener el listener que recibirá los resultados
     * @return true si se encontró el país, false en caso contrario
     */
    public boolean findCountryByCode(String countryCode, TableSelectionListener countryListener) {
            var request = new CountryRetrieveRequest(sessionID, userID, countryCode);
            Object response = serverController.sendData(request);

            if (response instanceof CountryRetrieveResponse countryDTO) {
                if (countryDTO.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                } else if (countryDTO.wasFound()) {
                    CountryDTO dto = countryDTO.getCountryDto();
                    countryListener.onRowSelected(new Object[]{
                        dto.getID(),
                        dto.getAlpha2(),
                        dto.getAlpha3(),
                        translatedCountryName(dto.getAlpha2())
                    });
                    return true;
                } else {
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                }
            }
        countryListener.onRowSelected(new Object[]{null, null, null, null});
        return false;
    }

    @Override
    public void openSearchWindow(TableSelectionListener listener, List<Integer> fieldList) {
        var searchWindow = new CurrencyInformationSearchWindow(
                Workspace.getFrame(),
                getColumns(),
                getDataSource(),
                listener,
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(CountryMessages.COUNTRY_INFO_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(CountryMessages.COUNTRY_INFO_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }

}
