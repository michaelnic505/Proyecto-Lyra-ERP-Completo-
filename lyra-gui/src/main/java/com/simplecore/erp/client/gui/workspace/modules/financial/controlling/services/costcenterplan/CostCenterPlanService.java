

package com.simplecore.erp.client.gui.workspace.modules.financial.controlling.services.costcenterplan;

import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.services.base.AbstractMatchCodeService;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.COCostCenterPlanByIdRetrieveRequest;
import com.simplecore.erp.shared.requests.types.COCostCenterPlanByListRetrieveRequest;
import com.simplecore.erp.shared.responses.types.COCostCenterPlanByIdRetrieveResponse;
import com.simplecore.erp.shared.responses.types.COCostCenterPlanByListRetrieveResponse;
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
public class CostCenterPlanService extends AbstractMatchCodeService {

    public CostCenterPlanService(ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        super(session, output, input);
    }

    @Override
    public void openSearchWindow(TableSelectionListener listener, List<Integer> fieldList) {
        var searchWindow = new CostCenterPlanSearchWindow(
                Workspace.getFrame(),
                getColumns(),
                getDataSource(),
                listener,
                fieldList);
        searchWindow.setWindowTitle(windowTranslator.getTranslation(CostCenterPlanFields.COST_CENTER_PLAN_WINDOW_TITLE.getKey()));
        searchWindow.setTitlePane(0, windowTranslator.getTranslation(CostCenterPlanFields.COST_CENTER_PLAN_WINDOW_TITLE_PANE.getKey()));
        searchWindow.setVisible(true);
    }
    
    public void findCostCenterPlanByID(TableSelectionListener listener,long ID){
        
        var request = new COCostCenterPlanByIdRetrieveRequest(sessionID, userID, ID);
        Object response = serverController.sendData(request);
        if(response instanceof COCostCenterPlanByIdRetrieveResponse planByID){
            if(planByID.isSqlError()){
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            }else if(planByID.wasFound()){
                var dto = planByID.getPlanDTO();
                listener.onRowSelected(new Object[]{
                    dto.getCostCenterPlanID(),
                    dto.getCostCenterPlanName(),
                    dto
                });
                return;
            }else{
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
            }
            listener.onRowSelected(new Object[]{null,null,null});
        }
    }
    
    

    private String[] getColumns() {
        return new String[]{
            translatorTable.getTranslation(CostCenterPlanFields.COST_CENTER_PLAN_ID.getKey()),
            translatorTable.getTranslation(CostCenterPlanFields.COST_CENTER_PLAN_NAME.getKey()),
            translatorTable.getTranslation(CostCenterPlanFields.COST_CENTER_PLAN_DESCRIPTION.getKey()),
            translatorTable.getTranslation(CostCenterPlanFields.COST_CENTER_PLAN_STATUS.getKey()),
            translatorTable.getTranslation(CostCenterPlanFields.COST_CENTER_PLAN_CREATED_AT.getKey()),
            translatorTable.getTranslation(CostCenterPlanFields.COST_CENTER_PLAN_CREATED_BY.getKey()),
            translatorTable.getTranslation(CostCenterPlanFields.COST_CENTER_PLAN_UPDATED_AT.getKey())
        };
    }

    private Object[][] getDataSource() {
        var request = new COCostCenterPlanByListRetrieveRequest(sessionID, userID);
        Object response = serverController.sendData(request);
        if(response instanceof COCostCenterPlanByListRetrieveResponse centerList){
            if(centerList.isSqlError()){
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
            }else if(centerList.wasFound()){
                return centerList.getDataSource();
            }else{
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.NOT_FOUND));
            }
        }
        return new Object[0][getColumns().length];
    }
}
