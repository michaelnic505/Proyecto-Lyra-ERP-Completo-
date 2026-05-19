

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.registrar;

import com.simplecore.erp.client.abstractions.AbstractRegistrar;
import com.simplecore.erp.client.dependencies.ContainerDependencies;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitModifyFICompanyDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitModifyFICompanyFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.init.InitModifyFICompany;
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
public class InitModifyFICompanyRegistrar extends AbstractRegistrar<InitModifyFICompany>{

    private InitModifyFICompanyFormState formState;
    private InitModifyFICompanyDataHandler dataHandler;
    private FICompanyRetrieveService fiCompanyRetrieveService;
    
    public InitModifyFICompanyRegistrar(InitModifyFICompany panel, 
            OperationType operationType, 
            ObjectOutputStream output, 
            ObjectInputStream input, 
            ActiveSession session) {
        super(panel, operationType, output, input, session);
    }

    @Override
    protected void initializeServices() {
        formState = new InitModifyFICompanyFormState(3);
        dataHandler = new InitModifyFICompanyDataHandler(panel, formState);
        fiCompanyRetrieveService = new FICompanyRetrieveService(session, output, input);
    }

    @Override
    protected void registerServices() {
        container.register(InitModifyFICompanyFormState.class, formState);
        container.register(InitModifyFICompanyDataHandler.class, dataHandler);
        container.register(FICompanyRetrieveService.class, fiCompanyRetrieveService);
        dataHandler.setFICompanyRetrieveService(fiCompanyRetrieveService);
    }
}
