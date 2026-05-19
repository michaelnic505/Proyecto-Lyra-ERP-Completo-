
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.config.ChartOfAccountField;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.auxiliar.ChartMessages;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.auxiliar.ChartOfAccountsSearchWindow;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.ChartOfAccountListRetrieveRequest;
import com.simplecore.erp.shared.requests.types.ChartOfAccountRetrieveRequest;
import com.simplecore.erp.shared.responses.types.ChartOfAccountRetrieveResponse;
import com.simplecore.erp.shared.responses.types.ChartOfAccountsListRetrieveResponse;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.services.base.AbstractMatchCodeService;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountsService extends AbstractMatchCodeService{

    public ChartOfAccountsService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        super(session, output, input);
    }

    @Override
    public void openSearchWindow(TableSelectionListener listener, List<Integer> fieldList) {
        var searchWindow = new ChartOfAccountsSearchWindow(
                Workspace.getFrame(),
                getColumns(),
                getDataSource(),
                listener,
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(ChartMessages.CHARTS_INFO_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(ChartMessages.CHARTS_INFO_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }

    private String[] getColumns() {
        return new String[]{
            translatorTable.getTranslation(ChartOfAccountField.CODE.getKey()),
            translatorTable.getTranslation(ChartOfAccountField.NAME.getKey()),
            translatorTable.getTranslation(ChartOfAccountField.ACCOUNT_MODEL_ID.getKey()),
            translatorTable.getTranslation(ChartOfAccountField.CURRENCY_CODE.getKey()),
            translatorTable.getTranslation(ChartOfAccountField.COUNTRY_CODE.getKey()),
            translatorTable.getTranslation(ChartOfAccountField.FISCAL_YEAR.getKey()),
            translatorTable.getTranslation(ChartOfAccountField.ACCOUNTING_STANDARD.getKey()),
            translatorTable.getTranslation(ChartOfAccountField.STATUS.getKey()),
            translatorTable.getTranslation(ChartOfAccountField.CREATED_BY.getKey())
        };
    }

    private Object[][] getDataSource() {
        var request = new ChartOfAccountListRetrieveRequest(sessionID, userID);
        Object response = serverController.sendData(request);
        if (response instanceof ChartOfAccountsListRetrieveResponse results) {
            if (results.isSqlError()) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            }
            if (results.wasFound()) {
                return results.getDataSource();
            } else {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
            }
        }
        return new Object[0][9];
    }

    public boolean searchChartByCode(String value, TableSelectionListener listener) {
        var request = new ChartOfAccountRetrieveRequest(sessionID, userID, value);
        Object response = serverController.sendData(request);
        
        if (response instanceof ChartOfAccountRetrieveResponse results) {
            if (results.isSqlError()) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            }
            if (results.wasFound()) {
                ChartOfAccountDTO chartDTO = results.getChartOfAccount();
                listener.onRowSelected(new Object[]{
                    chartDTO.getChartOfAccountCode(),
                    chartDTO.getChartOfAccountName(),
                    chartDTO
                });
                return true;
            } else {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
                return false;
            }
        }
        return false;
    }

}
