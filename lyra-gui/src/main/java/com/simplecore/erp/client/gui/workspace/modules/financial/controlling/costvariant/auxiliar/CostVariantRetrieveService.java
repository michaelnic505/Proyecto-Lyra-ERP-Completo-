
package com.simplecore.erp.client.gui.workspace.modules.financial.controlling.costvariant.auxiliar;

import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.services.base.AbstractMatchCodeService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.COCostVariantByCodeRetrieveRequest;
import com.simplecore.erp.shared.requests.types.COCostVariantListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.COCostVariantByCodeRetrieveResponse;
import com.simplecore.erp.shared.responses.types.COCostVariantListRetrieveResponse;
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
public class CostVariantRetrieveService extends AbstractMatchCodeService{

    public CostVariantRetrieveService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        super(session, output, input);
    }

    @Override
    public void openSearchWindow(TableSelectionListener listener, List<Integer> fieldList) {
        var searchWindow = new CostVariantSearchWindow(
                Workspace.getFrame(),
                getColumns(),
                getDataSource(),
                listener,
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(CostVariantFields.COST_VARIANT_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(CostVariantFields.COST_VARIANT_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }

    public void findCostVariantByCode(TableSelectionListener listener, String code) {
        var request = new COCostVariantByCodeRetrieveRequest(sessionID, userID, code);
        Object response = serverController.sendData(request);
       
        if (response instanceof COCostVariantByCodeRetrieveResponse variantByCode) {
            if (variantByCode.isSqlError()) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                listener.onRowSelected(new Object[]{null, null, null});
            } else if (variantByCode.wasFound()) {
                listener.onRowSelected(new Object[]{
                    variantByCode.getCostVariantDTO().getVariantCode(),
                    variantByCode.getCostVariantDTO().getVariantName(),
                    variantByCode.getCostVariantDTO()
                });
            } else {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                listener.onRowSelected(new Object[]{null, null, null});
            }
        }
    }
    
    private String[] getColumns() {
        return new String[]{
            translatorTable.getTranslation(CostVariantFields.COST_VARIANT_CODE.getKey()),
            translatorTable.getTranslation(CostVariantFields.COST_VARIANT_NAME.getKey()),
            translatorTable.getTranslation(CostVariantFields.COST_VARIANT_DESCRIPTION.getKey()),
            translatorTable.getTranslation(CostVariantFields.COST_VARIANT_VALID_FROM.getKey()),
            translatorTable.getTranslation(CostVariantFields.COST_VARIANT_VALID_TO.getKey()),
            translatorTable.getTranslation(CostVariantFields.COST_VARIANT_IS_ACTIVE.getKey()),
            translatorTable.getTranslation(CostVariantFields.COST_VARIANT_CREATED_AT.getKey()),
            translatorTable.getTranslation(CostVariantFields.COST_VARIANT_CREATED_BY.getKey()),
            translatorTable.getTranslation(CostVariantFields.COST_VARIANT_UPDATED_AT.getKey()),
            translatorTable.getTranslation(CostVariantFields.COST_VARIANT_UPDATED_BY.getKey())
        };
    }

    private Object[][] getDataSource() {
        var request = new COCostVariantListRetrieveRequest(sessionID, userID);
        Object response = serverController.sendData(request);
        if (response instanceof COCostVariantListRetrieveResponse costVariant) {
            if (costVariant.isSqlError()) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            } else if (costVariant.wasFound()) {
                return costVariant.getDataSource();
            } else {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
            }
        }
        return new Object[0][getColumns().length];
    }

}
