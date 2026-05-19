

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services;

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
public class InitViewFICompanyFormState extends FormState {

    private String fiCompanyCode;
    private String fiCompanyName;
    private FICompanyDTO companyDTO;

    public InitViewFICompanyFormState(int fieldsCount) {
        super(fieldsCount);
    }

    /**
     * @return the fiCompanyCode
     */
    public String getFiCompanyCode() {
        return fiCompanyCode;
    }

    /**
     * @param fiCompanyCode the fiCompanyCode to set
     */
    public void setFiCompanyCode(String fiCompanyCode) {
        this.fiCompanyCode = fiCompanyCode;
        updateFilledFields(this);
    }

    /**
     * @return the fiCompanyName
     */
    public String getFiCompanyName() {
        return fiCompanyName;
    }

    /**
     * @param fiCompanyName the fiCompanyName to set
     */
    public void setFiCompanyName(String fiCompanyName) {
        this.fiCompanyName = fiCompanyName;
        updateFilledFields(this);
    }

    /**
     * @return the companyDTO
     */
    public FICompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    /**
     * @param companyDTO the companyDTO to set
     */
    public void setCompanyDTO(FICompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
        updateFilledFields(this);
    }

}
