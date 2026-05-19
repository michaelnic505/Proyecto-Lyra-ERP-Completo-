

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services;

import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class InitFinancialAccountSetupFormState extends FormState  {
    
    private String fiCompanyCode;
    private String fiCompanyName;
    private FICompanyDTO companyDTO;

    public InitFinancialAccountSetupFormState(int fieldsCount) {
        super(fieldsCount);
    }
    
    public String getFiCompanyCode() {
        return fiCompanyCode;
    }

    public void setFICompanyCode(String fiCompanyCode) {
        this.fiCompanyCode = fiCompanyCode;
        updateFilledFields(this);
    }

    public String getFiCompanyName() {
        return fiCompanyName;
    }

    public void setFiCompanyName(String fiCompanyName) {
        this.fiCompanyName = fiCompanyName;
        updateFilledFields(this);
    }

    public FICompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(FICompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
        updateFilledFields(this);
    }
}
