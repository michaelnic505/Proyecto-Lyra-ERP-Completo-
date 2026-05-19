
package com.simplecore.erp.client.gui.utils.timezones;

import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.utils.countries.*;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.services.base.AbstractMatchCodeService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.TimezoneDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.TimezoneByNameRetrieveRequest;
import com.simplecore.erp.shared.requests.types.TimezonesListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.TimezoneByNameRetrieveResponse;
import com.simplecore.erp.shared.responses.types.TimezonesListRetrieveResponse;
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
public class TimezonesService extends AbstractMatchCodeService {

    public TimezonesService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        super(session, output, input);
    }

    @Override
    public void openSearchWindow(TableSelectionListener listener, List<Integer> fieldList) {
               var searchWindow = new CountryInformationSearchWindow(
                Workspace.getFrame(), 
                getColumnNames(), 
                getDataSource(),
                listener, 
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(TimezoneFieldsName.TIMEZONE_INFO_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(TimezoneFieldsName.TIMEZONE_INFO_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }

    public boolean searchTimezoneByName(String value, TableSelectionListener timezoneListener) {
            var timezoneByName = new TimezoneByNameRetrieveRequest(sessionID,userID, value);
            Object response = serverController.sendData(timezoneByName);

            if (response instanceof TimezoneByNameRetrieveResponse timezoneDTO) {
                if (timezoneDTO.isSqlError()) {
                    timezoneListener.onRowSelected(new Object[]{null});
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return false;
                }
                if (timezoneDTO.wasFound()) {
                    TimezoneDTO timezone = timezoneDTO.getTimezoneDTO();
                    Object[] data = new Object[]{
                        timezone.getName()
                    };
                    timezoneListener.onRowSelected(data);
                    return true;
                } else {
                    timezoneListener.onRowSelected(new Object[]{null});
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                    return false;
                }

            }
        timezoneListener.onRowSelected(new Object[]{null,null});
        return false;
    }
    
    private String[] getColumnNames(){
        String id = translatorTable.getTranslation(TimezoneFieldsName.TIMEZONE_ID_TEXT.getKey());
        String name = translatorTable.getTranslation(TimezoneFieldsName.TIMEZONE_NAME_TEXT.getKey());
        
        return new String[]{
            name
            };
    }

    private Object[][] getDataSource() {
            var request = new TimezonesListRetrieveRequest(sessionID, userID);
            Object response = serverController.sendData(request);
            if (response instanceof TimezonesListRetrieveResponse timezones) {
                if (timezones.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
                    return new Object[0][1];
                }
                if (timezones.wasFound()) {
                    return timezones.getDataSource();
                } else {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                    return new Object[0][1];
                }
            }
        return new Object[0][1];
    }
}
