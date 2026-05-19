

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.registrar;

import com.simplecore.erp.client.abstractions.AbstractRegistrar;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services.AccountByChartOfAccountRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form.FinancialAccountSetup;
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
public class FinancialAccountSetupRegistrar extends AbstractRegistrar<FinancialAccountSetup>  {

    private AccountByChartOfAccountRetrieveService accountService;
    
    public FinancialAccountSetupRegistrar(
            FinancialAccountSetup panel, 
            OperationType operationType, 
            ObjectOutputStream output, 
            ObjectInputStream input, 
            ActiveSession session) {
        super(panel, operationType, output, input, session);
    }

    @Override
    protected void initializeServices() {
        accountService = new AccountByChartOfAccountRetrieveService(session, output, input);
    }

    @Override
    protected void registerServices() {
        container.register(AccountByChartOfAccountRetrieveService.class, accountService);
    }

}
