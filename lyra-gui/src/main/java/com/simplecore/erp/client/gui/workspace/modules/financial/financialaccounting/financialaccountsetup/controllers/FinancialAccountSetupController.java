

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.controllers;

import com.simplecore.erp.client.abstractions.AbstractFormController;
import com.simplecore.erp.client.dependencies.InjectDependency;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.registrar.FinancialAccountSetupRegistrar;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services.AccountByChartOfAccountRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services.AccountDTOInjector;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form.FinancialAccountSetup;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
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
public class FinancialAccountSetupController extends AbstractFormController<FinancialAccountSetup>{

    @InjectDependency
    private AccountByChartOfAccountRetrieveService accountRetrieveService;
    
    public FinancialAccountSetupController(
            FinancialAccountSetup panel, 
            OperationType operationType, 
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session
            ) {
        super(panel, operationType, ()->new FinancialAccountSetupRegistrar(panel, operationType, output, input, session));
    }

    @Override
    public void consumeDependencies() {

    }

    public void injectFICompanyDTO(FICompanyDTO companyDTO) {
        new AccountDTOInjector(accountRetrieveService, panel).inject(companyDTO);
    }
}
