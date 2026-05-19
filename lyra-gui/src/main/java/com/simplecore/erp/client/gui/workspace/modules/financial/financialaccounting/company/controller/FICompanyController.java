
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyDataHandler;
import com.simplecore.erp.client.abstractions.AbstractFormController;
import com.simplecore.erp.client.dependencies.InjectDependency;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.utils.countries.controller.CountryService;
import com.simplecore.erp.client.gui.utils.timezones.TimezonesService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.AccountingStandardsService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountsService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.config.FICompanyFieldLimiter;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.registrar.FICompanyRegistrar;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.persistence.FICompanyPersistence;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICORelationRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyDocumentsRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyStatusService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COCompanyCardFactory;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.FICompanyFormPanel;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util.FICORelationDTOInjector;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util.FICompanyDTOInjector;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util.FICompanyDocumentsInjector;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
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
public class FICompanyController extends AbstractFormController<FICompanyFormPanel> {
    
    //--------------------------Dependencies injected by Reflection--------------------------//
    @InjectDependency
    private FICompanyFormState formState;
    
    @InjectDependency
    private COCompanyCardFactory companyCardFactory;
    
    @InjectDependency
    private FICompanyDataHandler dataHandler;
    
    @InjectDependency
    private TimezonesService timezoneService;
    
    @InjectDependency
    private ChartOfAccountsService chartRetriveService;
    
    @InjectDependency
    private CountryService countryServices;
    
    @InjectDependency
    private FICompanyStatusService statusService;
    
    @InjectDependency
    private AccountingStandardsService accountingStandardsService;
    
    @InjectDependency
    private FICompanyPersistence persistence;
    
    @InjectDependency
    private FICORelationRetrieveService ficoRelationService;
    
    @InjectDependency
    private FICompanyDocumentsRetrieveService documentsService;
    
    //--------------------------Controllers--------------------------//
    private FICompanyTimezoneController timezoneController;
    private FICompanyStatusComboController comboStatusController;
    private FICompanyChartOfAccountController chartAccountController;
    private FICompanyCountryController countryController;
    private FICompanyAccountingStandardController standardsController;
    private FICompanyDocumentsController documentsController;
    private FICompanyFieldLimiter limiter;
    private FICORelationDTOInjector relationInjector;
    private FICompanyDocumentsInjector documentInjector;

    public FICompanyController(FICompanyFormPanel panel,
            OperationType operationType,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session) {
        super(panel, operationType, () -> new FICompanyRegistrar(panel, operationType, output, input, session));
    }

    @Override
    public void consumeDependencies() {
        formState.setSysTransaction(panel.getTransactionCode());
     
        countryController = new FICompanyCountryController(
                panel.getCountryCodeMatchCode(),
                countryServices,
                dataHandler.countryListener(),
                List.of(0, 1, 2, 3));

        timezoneController = new FICompanyTimezoneController(
                panel.getTimezoneMatchCode(),//el componente que ejecuta una accion
                timezoneService,//El servicio a consumir
                dataHandler.timezoneListener(),// escuchador de eventos
                List.of(0));// indicador de seleccion de datos

        chartAccountController = new FICompanyChartOfAccountController(
                panel.getChartOfAccountMatchCode(),
                chartRetriveService,
                dataHandler.chartsListener(),
                List.of(0, 1));

        standardsController = new FICompanyAccountingStandardController(
                panel.getAccountingStandardCombo(),
                accountingStandardsService);

        panel.getCOAssociationPanel().setCardFactory(
                companyCardFactory,
                dataHandler.getCOCompanyCardsListener());

        comboStatusController = new FICompanyStatusComboController(
                panel.getCompanyStatusCombo(),
                statusService);

        documentsController = new FICompanyDocumentsController(formState, dataHandler, panel);

        limiter = new FICompanyFieldLimiter(panel)
                .applyFilter();

        relationInjector = new FICORelationDTOInjector(panel);

    }

    public void loadCompanyDataIfNeeded(FICompanyDTO companyDTO) {
        if (operationType == OperationType.MODIFY || operationType == OperationType.VIEW
                && companyDTO != null) {
            long companyID = companyDTO.getCompanyID();

            var injectorDTO = new FICompanyDTOInjector(panel, chartRetriveService, dataHandler,formState);
            injectorDTO.inject(companyDTO);

            var associationDTO = ficoRelationService.getFICOAssociationDTO(companyID);
            relationInjector.inject(associationDTO);

            var documentsDTO = documentsService.getFIDocumentsList(companyID);
            documentInjector = new FICompanyDocumentsInjector(panel, formState);
            documentInjector.inject(documentsDTO);
            
            persistence.setDocumentInjector(documentInjector);
        }
    }
}
