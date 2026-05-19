

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.registrar;

import com.simplecore.erp.client.abstractions.AbstractRegistrar;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services.InitFinancialAccountSetupDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services.InitFinancialAccountSetupFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.init.InitFinancialAccountSetup;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class InitFinancialAccountSetupRegistrar extends AbstractRegistrar<InitFinancialAccountSetup> {

    private FICompanyRetrieveService fiCompanyRetrieveService;
    private InitFinancialAccountSetupFormState formState;
    private InitFinancialAccountSetupDataHandler dataHandler;
    
    public InitFinancialAccountSetupRegistrar(
            InitFinancialAccountSetup panel, 
            OperationType operationType, 
            ObjectOutputStream output, 
            ObjectInputStream input, 
            ActiveSession session) {
        super(panel, operationType, output, input, session);
    }

    @Override
    protected void initializeServices() {
        formState = new InitFinancialAccountSetupFormState(3);
        fiCompanyRetrieveService = new FICompanyRetrieveService(session, output, input);
        dataHandler = new InitFinancialAccountSetupDataHandler(panel, formState);
    }

    @Override
    protected void registerServices() {
        container.register(InitFinancialAccountSetupFormState.class, formState);
        container.register(FICompanyRetrieveService.class, fiCompanyRetrieveService);
        container.register(InitFinancialAccountSetupDataHandler.class, dataHandler);
        
        dataHandler.setFICompanyRetrieveService(fiCompanyRetrieveService);
    }
}
