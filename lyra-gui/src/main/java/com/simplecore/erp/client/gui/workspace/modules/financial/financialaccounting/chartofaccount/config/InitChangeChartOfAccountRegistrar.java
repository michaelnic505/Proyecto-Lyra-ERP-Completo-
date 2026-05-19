
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.config;

import com.simplecore.erp.client.dependencies.ContainerDependencies;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountsService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.InitModifyChartOfAccountDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.util.InitCreateChartOfAccountsFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.init.InitModifyChartOfAccounts;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.InitModifyProceedButtonController;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.simplecore.erp.client.dependencies.DependencyRegistrar;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class InitChangeChartOfAccountRegistrar implements DependencyRegistrar{
    
    private final ContainerDependencies container;
    private final InitModifyChartOfAccounts panel;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final ActiveSession session;

    // ↓↓↓ Nuevos campos para guardar los servicios ↓↓↓
    private ChartOfAccountsService chartRetriveService;
    private InitModifyChartOfAccountDataHandler dataHandler;
    private InitCreateChartOfAccountsFormState formState;
    private InitModifyProceedButtonController proceedController;
    
    public InitChangeChartOfAccountRegistrar(
            InitModifyChartOfAccounts panel,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session) {
        this.output = output;
        this.input = input;
        this.session = session;
        this.container = new ContainerDependencies();
        this.panel = panel;
    }

    @Override
    public void registerDependencies() {
        initializeServices();
        registerServices();
    }

    @Override
    public ContainerDependencies container(){
        return container;
    }
    
    private void initializeServices() {
        
        formState = new InitCreateChartOfAccountsFormState(1);

        dataHandler = new InitModifyChartOfAccountDataHandler.Builder()
                .withFormState(formState)
                .withPanel(panel)
                .build();

        chartRetriveService = new ChartOfAccountsService(session, output, input);
        
        proceedController = new InitModifyProceedButtonController.Builder()
                .withOutput(output)
                .withInput(input)
                .withSession(session)
                .withPanel(panel)
                .withFormState(formState)
                .withRetrieveService(chartRetriveService)
                .build();
        
    }
    
    private void registerServices() {
        container.register(InitCreateChartOfAccountsFormState.class, formState);
        container.register(InitModifyChartOfAccountDataHandler.class, dataHandler);
        container.register(ChartOfAccountsService.class, chartRetriveService);
        container.register(InitModifyProceedButtonController.class, proceedController);
        
        proceedController.setDataHandler(dataHandler);
    }
}
