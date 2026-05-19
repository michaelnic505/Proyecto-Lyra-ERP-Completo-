
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.persistence.ChartOfAccountPersistenceService;
import com.simplecore.erp.client.services.base.AbstractSaverController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountSaveController extends AbstractSaverController{

    private ChartOfAccountDataHandler dataHandler;
    private ChartOfAccountPersistenceService persistenceService;

    public ChartOfAccountSaveController(ChartOfAccountDataHandler dataHandler, 
            ChartOfAccountPersistenceService persistenceService) {
        this.dataHandler = dataHandler;
        this.persistenceService = persistenceService;
    }
    
    @Override
    public void onSaveButtonClick(){
        if(!dataHandler.checkDataForSave()){
            return;
        }
        persistenceService.persistData();
    }
    
    @Override
    public ActionListener saveButtonListener(){
        return (ActionEvent e) -> onSaveButtonClick();
    }
}
