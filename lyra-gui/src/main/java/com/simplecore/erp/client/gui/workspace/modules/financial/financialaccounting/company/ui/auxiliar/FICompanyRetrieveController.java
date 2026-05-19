

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.auxiliar;

import com.simplecore.erp.client.abstractions.AbstractMatchCodeController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyRetrieveService;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyRetrieveController extends AbstractMatchCodeController<FICompanyRetrieveService>{

    public FICompanyRetrieveController(MatchCode matchCode, 
            FICompanyRetrieveService service, 
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
        service.findCompanyByCode(listener, value);
    }
}
