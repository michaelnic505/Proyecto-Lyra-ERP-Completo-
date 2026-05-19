
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller;

import com.simplecore.erp.client.abstractions.AbstractMatchCodeController;
import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountsService;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyChartOfAccountController extends AbstractMatchCodeController<ChartOfAccountsService>{

    public FICompanyChartOfAccountController(MatchCode matchCode, 
            ChartOfAccountsService service, 
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
        service.searchChartByCode(value, listener);
    }
}
