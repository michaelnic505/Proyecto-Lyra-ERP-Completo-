
package com.simplecore.erp.client.gui.workspace.modules.financial.controlling.services.costcenterplan;

import com.simplecore.erp.client.abstractions.AbstractMatchCodeController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class CostCenterPlanController extends AbstractMatchCodeController<CostCenterPlanService>{

    public CostCenterPlanController(MatchCode matchCode, 
            CostCenterPlanService service, 
            TableSelectionListener listener,
            List<Integer> visibleColumns) {
        super(matchCode, service, listener, visibleColumns);
    }

    @Override
    protected void openWindow() {
        service.openSearchWindow(listener, visibleColumns);
    }

    @Override
    protected void onSearch(String value) {
        service.findCostCenterPlanByID(listener, Long.parseLong(value));
    }

}
