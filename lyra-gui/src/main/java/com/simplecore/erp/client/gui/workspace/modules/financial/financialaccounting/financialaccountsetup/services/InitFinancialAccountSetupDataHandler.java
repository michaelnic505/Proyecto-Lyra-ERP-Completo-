

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services;

import com.simplecore.erp.client.abstractions.AbstractDataHandler;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.init.InitFinancialAccountSetup;
import com.simplecore.erp.client.utils.validators.FormValidator;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class InitFinancialAccountSetupDataHandler extends AbstractDataHandler<
        InitFinancialAccountSetup,
        InitFinancialAccountSetupFormState>{

    private TableSelectionListener fiCompanyListener;
    private FICompanyRetrieveService fiCompanyRetrieveService;
    
    public InitFinancialAccountSetupDataHandler(InitFinancialAccountSetup panel, InitFinancialAccountSetupFormState formState) {
        super(panel, formState);
        initListeners();
    }

    private void initListeners(){
        initFICompanyListener();
    }
    
    private void initFICompanyListener(){
        fiCompanyListener = (Object[] data)->{
            String code = getValueFrom(0, data);
            String companyName  = getValueFrom(1, data);
            
            panel.getCompanyCodeMatchCode().getTextField().setText(code);
            panel.getCompanyNameLB().setText(companyName);
            
            if(data.length<=2 || data[2]==null){
                findFICompanyByCode();
                return;
            }
            
            FICompanyDTO dto = (FICompanyDTO) data[2];
            formState.setFICompanyCode(code);
            formState.setFiCompanyName(companyName);
            formState.setCompanyDTO(dto);
        };
    }
    
    private String getValueFrom(int index, Object[] data) {
        return (data.length <= index || data[index] == null) ? "" : data[index].toString();
    }

    public void findFICompanyByCode() {
        String value = panel.getCompanyCodeMatchCode().getTextField().getText().trim();
        if (value.isEmpty()) {
            return;
        }
        fiCompanyRetrieveService.findCompanyByCode(fiCompanyListener, value);
    }

    @Override
    public boolean areAllComponentsFullyFilled() {
        FormValidator validator = new FormValidator()
                .add(panel.getCompanyCodeMatchCode().getTextField())
                .add(panel.getCompanyNameLB());

        String value = panel.getCompanyCodeMatchCode().getTextField().getText().trim();
        fiCompanyRetrieveService.findCompanyByCode(fiCompanyListener, value);
        return validator.validate(notificator);
    }

    public void setFICompanyRetrieveService(FICompanyRetrieveService fiCompanyRetrieveService) {
        this.fiCompanyRetrieveService = fiCompanyRetrieveService;
    }
    
    public TableSelectionListener getFICompanyListener(){return fiCompanyListener;};
}
