
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountsService;
import com.simplecore.erp.client.abstractions.AbstractFormController;
import com.simplecore.erp.client.abstractions.AbstractInitFormController;
import com.simplecore.erp.client.dependencies.InjectDependency;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.config.ViewChartOfAccountRegistrar;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.init.InitViewChartOfAccounts;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller.FICompanyChartOfAccountController;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
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
public class InitViewChartOfAccountController extends AbstractInitFormController<InitViewChartOfAccounts> {

    @InjectDependency
    private InitViewChartOfAccountDataHandler dataHandler;

    @InjectDependency
    private ChartOfAccountsService chartRetriveService;

    private FICompanyChartOfAccountController chartController;

    public InitViewChartOfAccountController(InitViewChartOfAccounts panel,
            OperationType operationType,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session) {
        super(panel, operationType, output,input,session,
                () -> new ViewChartOfAccountRegistrar(panel, output, input, session));
    }

    public void injectDependencies() {
        chartController = new FICompanyChartOfAccountController(
                panel.getChartOfAccountMatchCode(),
                chartRetriveService,
                dataHandler.getChartOfAccountListener(),
                 List.of(0, 1));
    }

}
