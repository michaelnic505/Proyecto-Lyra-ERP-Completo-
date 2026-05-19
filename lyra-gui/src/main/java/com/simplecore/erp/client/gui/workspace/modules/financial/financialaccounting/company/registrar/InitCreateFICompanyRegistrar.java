
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.registrar;

import com.simplecore.erp.client.abstractions.AbstractRegistrar;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitCreateFICompanyFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitCreateFICompanyDataHandler;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller.InitCreateFICompanyProceedController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.init.InitCreateFICompany;
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
public class InitCreateFICompanyRegistrar extends AbstractRegistrar<InitCreateFICompany> {

    // ↓↓↓ Nuevos campos para guardar los servicios ↓↓↓
    private InitCreateFICompanyDataHandler dataHandler;
    private InitCreateFICompanyFormState formState;
    private InitCreateFICompanyProceedController proceedController;

    public InitCreateFICompanyRegistrar(
            InitCreateFICompany panel,
            OperationType operationType, 
            ObjectOutputStream output, 
            ObjectInputStream input, 
            ActiveSession session) {
        super(panel, operationType, output, input, session);
    }

    @Override
    protected void initializeServices() {
        formState = new InitCreateFICompanyFormState(1);
        dataHandler = new InitCreateFICompanyDataHandler(panel, formState);
        proceedController = new InitCreateFICompanyProceedController.Builder()
                .withOperationType(OperationType.CREATE)
                .withPanel(panel)
                .withOutputStream(output)
                .withInputStream(input)
                .withSession(session)
                .withFormState(formState)
                .withDataHandler(dataHandler)
                .build();
    }
    
    @Override
    protected void registerServices() {
        container.register(InitCreateFICompanyFormState.class, formState);
        container.register(InitCreateFICompanyDataHandler.class, dataHandler);
        container.register(InitCreateFICompanyProceedController.class, proceedController);
    }
}
