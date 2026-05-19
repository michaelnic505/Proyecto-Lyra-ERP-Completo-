
package com.simplecore.erp.client.gui.workspace.modules.financial.controlling.company.auxiliar;

import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.services.base.AbstractMatchCodeService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.COCompanyDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.COCompanyByCodeRetrieveRequest;
import com.simplecore.erp.shared.requests.types.COCompanyListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.COCompanyByCodeRetrieveResponse;
import com.simplecore.erp.shared.responses.types.COCompanyListRetrieveResponse;
import java.io.IOException;
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
public class COCompanyService extends AbstractMatchCodeService {

    public COCompanyService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        super(session, output, input);
    }

    @Override
    public void openSearchWindow(TableSelectionListener listener, List<Integer> fieldList) {
        var searchWindow = new COCompanySearchWindow(
                Workspace.getFrame(),
                getColumnNames(),
                getDataSource(),
                listener,
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_INFO_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_INFO_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }

    public boolean findCompanyByCode(TableSelectionListener companyListener,String companyCode) {
        try {
            var companyRequest = new COCompanyByCodeRetrieveRequest(sessionID, userID,companyCode);
            Object response = serverController.sendRequest(companyRequest);

            if (response instanceof COCompanyByCodeRetrieveResponse companyResponse) {
                if (companyResponse.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
                }else if (companyResponse.wasFound()) {
                    COCompanyDTO dto = companyResponse.getCompanyDTO();
                    Object[] data = new Object[]{
                        dto.getCompanyCode(),
                        dto.getCompanyName(),
                        dto
                    };
                    companyListener.onRowSelected(data);
                    return true;
                } else {
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
        }
        companyListener.onRowSelected(new Object[]{null, null, null});
        return false;
    }
    
    private String[] getColumnNames() {
        return new String[]{
            translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_CODE.getKey()),
            translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_NAME.getKey()),
            translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_LEGAL_NAME.getKey()),
            translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_COUNTRY_CODE.getKey()),
            translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_CURRENCY_CODE.getKey()),
            translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_TIME_ZONE.getKey()),
            translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_VALID_FROM.getKey()),
            translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_VALID_TO.getKey()),
            translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_STATUS.getKey()),
            translatorTable.getTranslation(CompanyFieldsName.CONTROLLING_COMPANY_CREATED_BY.getKey())
        };
    }
    
    private Object[][] getDataSource() {
            var request  = new COCompanyListRetrieveRequest(sessionID, userID);
            Object response = serverController.sendData(request);
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
        return new Object[0][10];
    }

}
