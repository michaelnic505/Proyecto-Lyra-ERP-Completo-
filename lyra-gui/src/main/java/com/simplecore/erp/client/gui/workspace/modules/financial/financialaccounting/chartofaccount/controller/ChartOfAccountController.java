
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountStatusService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.AccountingStandardsService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.persistence.ChartOfAccountPersistenceService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.form.ChartOfAccountFormPanel;
import com.simplecore.erp.client.abstractions.AbstractFormController;
import com.simplecore.erp.client.abstractions.Controller;
import com.simplecore.erp.client.gui.utils.countries.CountryInfomartionController;
import com.simplecore.erp.client.dependencies.InjectDependency;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.config.ChartOfAccountRegistrar;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.TaxSchemasService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller.FICompanyAccountingStandardController;
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
public class ChartOfAccountController extends AbstractFormController<ChartOfAccountFormPanel>{

    @InjectDependency
    private ChartOfAccountFormState chartOfAccountState;
    
    @InjectDependency
    private ChartOfAccountStatusService statusService;
    
    @InjectDependency
    private ChartOfAccountDataHandler dataController;
    
    @InjectDependency(name = "country")
    private CountryInfomartionController countryController;
    
    @InjectDependency(name = "currency")
    private CountryInfomartionController currencyController;
    
    @InjectDependency
    private AccountingStandardsService accountinStandardsServices;
    
    @InjectDependency
    private ChartOfAccountPersistenceService persistenceServices;
    
    @InjectDependency
    private TaxSchemasService taxesService;
    
    @InjectDependency
    private ChartOfAccountSaveController saveController;
    
    @Controller
    private FICompanyAccountingStandardController standardsController;
    

    public ChartOfAccountController(ChartOfAccountFormPanel panel,
            OperationType operationType,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session) {
        super(panel,operationType ,() ->new ChartOfAccountRegistrar(panel, operationType, output, input, session));
    }
    
    public void consumeDependencies() {
                standardsController = new FICompanyAccountingStandardController(
                panel.getAccountingStandardCombo(), 
                accountinStandardsServices);
    }
    
}
