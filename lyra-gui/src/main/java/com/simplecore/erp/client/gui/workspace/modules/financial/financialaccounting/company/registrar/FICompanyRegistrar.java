package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.registrar;

import com.simplecore.erp.client.abstractions.AbstractRegistrar;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyFormState;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.AccountingStandardsService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountsService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller.FICompanyCOCardFactory;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller.FICompanySaveController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.persistence.FICompanyPersistence;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COCompanyCardFactory;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.FICompanyFormPanel;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.simplecore.erp.client.gui.utils.countries.controller.CountryService;
import com.simplecore.erp.client.gui.utils.timezones.TimezonesService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICORelationRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyDocumentsRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyStatusService;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class FICompanyRegistrar extends AbstractRegistrar<FICompanyFormPanel> {

    //Services & Controller to inject as dependencies
    private FICompanyFormState formState;
    private FICompanyDataHandler dataHandler;
    private TimezonesService timezoneService;
    private FICompanyStatusService statusService;
    private COCompanyCardFactory companyCardFactory;
    private CountryService countryServices;
    private ChartOfAccountsService chartRetriveService;
    private AccountingStandardsService accountingStandardsService;
    private FICompanyPersistence persistence;
    private FICompanySaveController saveController;
    private FICORelationRetrieveService ficoRelations;
    private FICompanyDocumentsRetrieveService documentsService;

    public FICompanyRegistrar(FICompanyFormPanel panel,
            OperationType operationType,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session) {
        super(panel, operationType, output, input, session);
    }

    @Override
    protected void initializeServices() {
        formState = new FICompanyFormState(39);
        dataHandler = new FICompanyDataHandler(panel, formState);
        timezoneService = new TimezonesService(session, output, input);
        statusService = new FICompanyStatusService(session, output, input);
        chartRetriveService = new ChartOfAccountsService(session, output, input);
        countryServices = new CountryService(session, output, input);
        accountingStandardsService = new AccountingStandardsService(output, input, session);
        companyCardFactory = () -> {
            var controller = new FICompanyCOCardFactory(session, output, input);
            return controller.buildCard();
        };
        persistence = new FICompanyPersistence(formState, output, input, session, operationType, panel.getTransactionCode());
        saveController = new FICompanySaveController(dataHandler, persistence);
        ficoRelations = new FICORelationRetrieveService(session, output, input);
        documentsService = new FICompanyDocumentsRetrieveService(session, output, input);
    }

    @Override
    protected void registerServices() {
        container.register(FICompanyFormState.class, formState);
        container.register(FICompanyDataHandler.class, dataHandler);
        container.register(TimezonesService.class, timezoneService);
        container.register(FICompanyStatusService.class, statusService);
        container.register(FICompanyPersistence.class, persistence);
        container.register(CountryService.class, countryServices);
        container.register(AccountingStandardsService.class, accountingStandardsService);
        container.register(ChartOfAccountsService.class, chartRetriveService);
        container.register(COCompanyCardFactory.class, companyCardFactory);
        container.register(FICompanySaveController.class, saveController);
        container.register(FICORelationRetrieveService.class, ficoRelations);
        container.register(FICompanyDocumentsRetrieveService.class, documentsService);

        dataHandler.setChartOfAccountService(chartRetriveService);
        dataHandler.setCountryService(countryServices);
        dataHandler.setTimezoneServoice(timezoneService);
    }
}
