package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services;

import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.auxiliar.FICompanyFields;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.auxiliar.FICompanySearchWindow;
import com.simplecore.erp.client.services.base.AbstractMatchCodeService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.FICompanyByCodeRetrieveRequest;
import com.simplecore.erp.shared.requests.types.FICompanyByListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.FICompanyByCodeRetrieveResponse;
import com.simplecore.erp.shared.responses.types.FICompanyByListRetrieveResponse;
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
public class FICompanyRetrieveService extends AbstractMatchCodeService {

    public FICompanyRetrieveService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        super(session, output, input);
    }

    private String[] getColumns() {
        return new String[]{
            translatorTable.getTranslation(FICompanyFields.FI_COUNTRY_CODE.getKey()),
            translatorTable.getTranslation(FICompanyFields.FI_COMPANY_NAME.getKey()),
            translatorTable.getTranslation(FICompanyFields.FI_LEGAL_NAME.getKey()),
            translatorTable.getTranslation(FICompanyFields.FI_BUSSINES_TYPE.getKey()),
            translatorTable.getTranslation(FICompanyFields.FI_BUSSINES_CLASSIFICATION.getKey()),
            translatorTable.getTranslation(FICompanyFields.FI_COUNTRY_CODE.getKey()),
            translatorTable.getTranslation(FICompanyFields.FI_PHONE.getKey()),
            translatorTable.getTranslation(FICompanyFields.FI_EMAIL.getKey()),
        };
    }

    private Object[][] getDataSource() {
        var request = new FICompanyByListRetrieveRequest(sessionID, userID);
        Object response = serverController.sendData(request);
        if(response instanceof FICompanyByListRetrieveResponse companyList){
            if(companyList.isSqlError()){
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            }else if(companyList.wasFound()){
                return companyList.getDataSource();
            }else{
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
            }
        }
        return emptyData();
    }

    private Object[][] emptyData() {
        return new Object[0][getColumns().length];
    }

    @Override
    public void openSearchWindow(TableSelectionListener listener, List<Integer> fieldList) {
        var searchWindow = new FICompanySearchWindow(
                Workspace.getFrame(),
                getColumns(),
                getDataSource(),
                listener,
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(FICompanyFields.COUNTRY_INFO_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(FICompanyFields.COUNTRY_INFO_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }

    public boolean findCompanyByCode(TableSelectionListener listener, String value) {
        var companyRequest = new FICompanyByCodeRetrieveRequest(sessionID, userID, value);
        Object response = serverController.sendData(companyRequest);
        if (response instanceof FICompanyByCodeRetrieveResponse companyByCode) {
            if (companyByCode.isSqlError()) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            } else if (companyByCode.wasFound()) {
                FICompanyDTO companyDTO = companyByCode.getCompanyDTO();
                listener.onRowSelected(new Object[]{
                    companyDTO.getCompanyCode(),
                    companyDTO.getCompanyName(),
                    companyDTO
                });
                return true;
            } else {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
            }
        }
        return false;
    }

}
