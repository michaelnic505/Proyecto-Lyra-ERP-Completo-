
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.config;

import com.simplecore.erp.client.dependencies.ContainerDependencies;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.utils.countries.CountryInfomartionController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.AccountingStandardsService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.ChartOfAccountDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.ChartOfAccountDocumentFilters;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.ChartOfAccountFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.persistence.ChartOfAccountPersistenceService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller.ChartOfAccountSaveController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountStatusService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.form.ChartOfAccountFormPanel;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.TaxSchemasService;
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
public class ChartOfAccountRegistrar implements DependencyRegistrar{
    
    private final OperationType operationType;
    
    private final ContainerDependencies container;
    private final ChartOfAccountFormPanel panel;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final ActiveSession session;
    private ChartOfAccountFormState chartOfAccountState;

    // ↓↓↓ Nuevos campos para guardar los servicios ↓↓↓
    private ChartOfAccountDataHandler dataHandler;
    private CountryInfomartionController countryService;
    private CountryInfomartionController currencyService;
    private AccountingStandardsService accountingStandardsService;
    private ChartOfAccountStatusService chartOfAccountStatusService;
    private TaxSchemasService taxSchemasService;
    private ChartOfAccountDocumentFilters documentFilterController;
    private ChartOfAccountPersistenceService persistenceService;
    private ChartOfAccountSaveController saverController;
    
    public ChartOfAccountRegistrar(
            ChartOfAccountFormPanel panel,
            OperationType operationType,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session) {
        this.output = output;
        this.input = input;
        this.session = session;
        this.container = new ContainerDependencies();
        this.panel = panel;
        this.operationType = operationType;
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
        chartOfAccountState = new ChartOfAccountFormState(17);
        
        dataHandler = new ChartOfAccountDataHandler.Builder()
                .withPanel(panel)
                .withFormState(chartOfAccountState)
                .withTransactionCode(panel.getSysTransactionCode())
                .build();

        countryService = new CountryInfomartionController.Builder()
                .withType(CountryInfomartionController.Type.COUNTRY)
                .withFieldList(List.of(0, 1, 2, 3))
                .withSession(session)
                .withOutputStream(output)
                .withInputStream(input)
                .withMatchCodeSearch(panel.getCountryCodeMatchCode())
                .withCountryListener(dataHandler.countryListener())
                .build();

        currencyService = new CountryInfomartionController.Builder()
                .withType(CountryInfomartionController.Type.CURRENCY)
                .withFieldList(List.of(0, 1, 2, 3))
                .withSession(session)
                .withOutputStream(output)
                .withInputStream(input)
                .withMatchCodeSearch(panel.getCurrencyCodeMatchCode())
                .withCountryListener(dataHandler.currencyListener())
                .build();

        accountingStandardsService = new AccountingStandardsService(output, input, session);

        chartOfAccountStatusService = new ChartOfAccountStatusService.Builder()
                .withOutput(output)
                .withInput(input)
                .withSession(session)
                .withComboItem(panel.getChartOfAccountStatusCombo())
                .build();

        taxSchemasService = new TaxSchemasService.Builder()
                .withOutputStream(output)
                .withInputStream(input)
                .withSession(session)
                .withTaxSchemaListener(dataHandler.taxSchemaListener())
                .withMatchCodeSearch(panel.getTaxSchemaMatchCode())
                .withFieldList(List.of(0, 1))
                .build();

        documentFilterController = new ChartOfAccountDocumentFilters(panel);

        persistenceService = new ChartOfAccountPersistenceService.Builder()
                .withOperationType(operationType)
                .withOutput(output)
                .withInput(input)
                .withSession(session)
                .withModelID(panel.modelId())
                .withTransactionCode(panel.getSysTransactionCode())
                .withCreatedBy(session.getUsername())
                .withUpdatedBy(session.getUsername())
                .withFormState(chartOfAccountState)
                .build();

        saverController = new ChartOfAccountSaveController(dataHandler, persistenceService);
    }

    private void registerServices() {
        container.register(ChartOfAccountFormState.class, chartOfAccountState);
        container.register(ChartOfAccountDataHandler.class, dataHandler);
        container.register("country", CountryInfomartionController.class, countryService);
        container.register("currency", CountryInfomartionController.class, currencyService);
        container.register(AccountingStandardsService.class, accountingStandardsService);
        container.register(ChartOfAccountStatusService.class, chartOfAccountStatusService);
        container.register(TaxSchemasService.class, taxSchemasService);
        container.register(ChartOfAccountDocumentFilters.class, documentFilterController);
        container.register(ChartOfAccountPersistenceService.class, persistenceService);
        container.register(ChartOfAccountSaveController.class, saverController);

        // Set reverse dependencies (bidireccionales)
        dataHandler.setCountryServices(countryService);
        dataHandler.setCurrencyServices(currencyService);
        dataHandler.setTaxSchemasService(taxSchemasService);
    }
}
