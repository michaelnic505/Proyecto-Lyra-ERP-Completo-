
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitCreateFICompanyDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.InitCreateFICompanyFormState;
import com.simplecore.erp.client.abstractions.AbstractInitFormController;
import com.simplecore.erp.client.dependencies.InjectDependency;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.registrar.InitCreateFICompanyRegistrar;
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
public class InitCreateFICompanyController extends AbstractInitFormController<InitCreateFICompany> {
    
    @InjectDependency
    private InitCreateFICompanyDataHandler dataHandler;
    
    @InjectDependency
    private InitCreateFICompanyFormState formState;
    
    @InjectDependency
    private InitCreateFICompanyProceedController proceedController;
    
    public InitCreateFICompanyController(InitCreateFICompany panel,
            OperationType operationType,
            ObjectOutputStream output,
            ObjectInputStream input,
            ActiveSession session) {
        super(panel, operationType, output,input,session,
                () -> new InitCreateFICompanyRegistrar(panel, operationType, output, input, session));
    }

}
