

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.controllers;

import com.simplecore.erp.client.abstractions.AbstractInitFormController;
import com.simplecore.erp.client.abstractions.Controller;
import com.simplecore.erp.client.dependencies.DependencyRegistrar;
import com.simplecore.erp.client.dependencies.InjectDependency;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.auxiliar.FICompanyRetrieveController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.registrar.InitFinancialAccountSetupRegistrar;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services.InitFinancialAccountSetupDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services.InitFinancialAccountSetupFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.init.InitFinancialAccountSetup;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class InitFinancialAccountSetupController extends AbstractInitFormController<InitFinancialAccountSetup> {

    @InjectDependency
    private FICompanyRetrieveService companyRetrieveService;
    @InjectDependency
    private InitFinancialAccountSetupDataHandler dataHandler;
    @InjectDependency
    private InitFinancialAccountSetupFormState formState;
    @Controller
    private FICompanyRetrieveController companyRetrieveControl;
    @Controller
    private InitFinancialAccountSetupProceedController proceedButtonControl;
    
    public InitFinancialAccountSetupController(
            InitFinancialAccountSetup panel,
            OperationType operationType,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session) {
        super(panel, operationType, output, input, session, 
                () -> new InitFinancialAccountSetupRegistrar(panel, operationType, output, input, session));
    }

    @Override
    public void consumeDependencies() {
        
        companyRetrieveControl = new FICompanyRetrieveController(
                panel.getCompanyCodeMatchCode(),
                companyRetrieveService,
                dataHandler.getFICompanyListener(),
                List.of(0, 1));

        proceedButtonControl = new InitFinancialAccountSetupProceedController.Builder()
                .withDataHandler(dataHandler)
                .withFormState(formState)
                .withPanel(panel)
                .withOutputStream(output)
                .withInputStream(input)
                .withSession(session)
                .withOperationType(operationType)
                .build();
    }
}
