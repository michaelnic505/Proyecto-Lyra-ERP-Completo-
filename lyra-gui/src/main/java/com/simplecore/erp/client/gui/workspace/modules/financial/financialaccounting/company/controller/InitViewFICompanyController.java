

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller;

import com.simplecore.erp.client.abstractions.AbstractInitFormController;
import com.simplecore.erp.client.abstractions.Controller;
import com.simplecore.erp.client.dependencies.InjectDependency;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.registrar.InitViewFICompanyRegistrar;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitViewFICompanyDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitViewFICompanyFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.auxiliar.FICompanyRetrieveController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.init.InitViewFICompany;
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
public class InitViewFICompanyController extends AbstractInitFormController<InitViewFICompany>{
    
    @InjectDependency
    private InitViewFICompanyDataHandler dataHandler;
    
    @InjectDependency
    private InitViewFICompanyFormState formState;
    
    @InjectDependency
    private FICompanyRetrieveService fiCompanyRetrieveService;
    
    @Controller
    private FICompanyRetrieveController fiCompanyRetrieveController;
    
    @Controller
    private InitViewFICompanyProceedController proceedButtonController;
    
    public InitViewFICompanyController(
            InitViewFICompany panel, 
            OperationType operationType,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session) {
        super(panel, operationType, output,input,session,() -> new InitViewFICompanyRegistrar(panel, operationType, output, input, session));
    }

    @Override
    public void consumeDependencies() {
        fiCompanyRetrieveController = new FICompanyRetrieveController(
                panel.getCompanyCodeMatchCode(), 
                fiCompanyRetrieveService, 
                dataHandler.getFICompanyListener(), 
                List.of(0,1));
        
        proceedButtonController = new InitViewFICompanyProceedController.Builder()
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
