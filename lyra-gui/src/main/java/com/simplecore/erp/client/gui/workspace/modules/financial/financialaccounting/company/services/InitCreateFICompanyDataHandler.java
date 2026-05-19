

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services;

import com.simplecore.erp.client.abstractions.AbstractDataHandler;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.init.InitCreateFICompany;
import com.simplecore.erp.client.utils.validators.FormValidator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class InitCreateFICompanyDataHandler extends AbstractDataHandler<InitCreateFICompany,InitCreateFICompanyFormState>{

    private TableSelectionListener chartOfAccountListener;

    public InitCreateFICompanyDataHandler(InitCreateFICompany panel, 
            InitCreateFICompanyFormState formState) {
        super(panel, formState);
        initCompanyNameListener();;
    }

    private void initCompanyNameListener(){
        panel.getCompanyNameTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String name = panel.getCompanyNameTF().getText().trim();
                formState.setCompanyName(name);
            }
        });
    }
    
    public TableSelectionListener getChartOfAccountListener() {
        return chartOfAccountListener;
    }
   
    @Override
    public boolean areAllComponentsFullyFilled() {
        FormValidator validator = new FormValidator()
                .add(panel.getCompanyNameTF());
        if (!validator.validate(panel.notificator())) {
            Workspace.getSaveButton().setPlayable(false);
            return false;
        }
        updateStateBeforeSave();
        // Actualiza la información de la cuenta una vez validada
        Workspace.getSaveButton().setPlayable(true);
        return true;
    }
    
    public void updateStateBeforeSave() {
        formState.setCompanyName(panel.getCompanyNameTF().getText().trim());
    }
}
