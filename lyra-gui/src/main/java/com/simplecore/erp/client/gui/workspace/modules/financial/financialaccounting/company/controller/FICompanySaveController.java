

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.persistence.FICompanyPersistence;
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
public class FICompanySaveController extends AbstractSaverController{

    private FICompanyDataHandler dataHandler;
    private FICompanyPersistence persistence;

    public FICompanySaveController(FICompanyDataHandler dataHandler,
            FICompanyPersistence persistence) {
        this.dataHandler = dataHandler;
        this.persistence = persistence;
    }

    @Override
    public void onSaveButtonClick() {
        if (!dataHandler.checkDataForSave()) {
            return;
        }
        persistence.persistData();
    }

    @Override
    public ActionListener saveButtonListener() {
        return (ActionEvent e) -> onSaveButtonClick();
    }
}
