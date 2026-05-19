

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util;

import com.simplecore.erp.client.abstractions.AbstractDTOFormInjector;
import com.simplecore.erp.client.abstractions.DTOInjector;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountsService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyDataHandler;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.FICompanyFormPanel;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyDTOInjector extends AbstractDTOFormInjector<FICompanyFormPanel> 
        implements DTOInjector<FICompanyDTO>{

    private ChartOfAccountsService chartRetriveService;
    private FICompanyDataHandler dataHandler;
    private FICompanyFormState formState;
    
    public FICompanyDTOInjector(
            FICompanyFormPanel panel,
            ChartOfAccountsService chartRetriveService,
            FICompanyDataHandler dataHandler,
            FICompanyFormState formState) {
        super(panel);
        this.chartRetriveService = chartRetriveService;
        this.dataHandler = dataHandler;
        this.formState = formState;
    }

    @Override
    public void inject(FICompanyDTO dto) {
        setValue(panel::getCompanyCodeTF, dto.getCompanyCode());
        setValue(panel::getCompanyNameTF, dto.getCompanyName());
        setValue(panel::getLegalNameTF, dto.getCompanyLegalName());
        setValue(panel::getBusinessTypeTF ,dto.getCompanyBussinesType());
        setValue(panel::getIndustryClassificationTF, dto.getCompanyBussinesClassification());
        setValue(()->panel.getCountryCodeMatchCode().getTextField(), dto.getCompanyCountryCode());
        setValue(panel::getLegalAddressTF, dto.getCompanyLegalAddress());
        setValue(panel::getPhoneTF, dto.getCompanyPhone());
        setValue(panel::getEmailTF, dto.getCompanyEmail());
        setValue(panel::getOfficialLanguageTF, dto.getCompanyOfficialLanguage());
        setValue(()->panel.getTimezoneMatchCode().getTextField(), dto.getCompanyTimeZone());
        setValue(()->panel.getChartOfAccountMatchCode().getTextField(), dto.getCompanyChartOfAccount());
        setValue(panel::getCompanyCreatedAtTF, dto.getCompanyCreatedAt());
        setValue(panel::getCompanyCreatedByTF, dto.getCompanyCreatedBy());
        setValue(panel::getCompanyUpdatedAtTF, dto.getCompanyUpdatedAt());
        setValue(panel::getCompanyUpdatedByTF, dto.getCompanyUpdatedBy());
        setValueC(panel::getCompanyStatusCombo, dto.getCompanyStatus());
        
        storeRetrievedData(dto);
        dataHandler.findChartByCode();
        dataHandler.findCountryByCode();
    }

    private void storeRetrievedData(FICompanyDTO dto){
        formState.setCompanyID(dto.getCompanyID());
        formState.setCompanyCode(dto.getCompanyCode());
        formState.setCompanyName(dto.getCompanyName());
        formState.setCompanyLegalName(dto.getCompanyLegalName());
        formState.setCompanyBussinesType(dto.getCompanyBussinesType());
        formState.setCompanyBussinesClassification(dto.getCompanyBussinesClassification());
        formState.setCompanyCountryCode(dto.getCompanyCountryCode());
        formState.setCompanyLegalAddress(dto.getCompanyLegalAddress());
        formState.setCompanyPhone(dto.getCompanyPhone());
        formState.setCompanyEmail(dto.getCompanyEmail());
        formState.setCompanyOfficialLanguage(dto.getCompanyOfficialLanguage());
        formState.setCompanyTimeZone(dto.getCompanyTimeZone());
        formState.setCompanyChartOfAccount(dto.getCompanyChartOfAccount());
        formState.setCompanyCreatedAt(dto.getCompanyCreatedAt());
        formState.setCompanyCreatedBy(dto.getCompanyCreatedBy());
        formState.setCompanyUpdatedAt(dto.getCompanyUpdatedAt());
        formState.setCompanyUpdatedBy(dto.getCompanyUpdatedBy());
        formState.setCompanyStatus(dto.getCompanyStatus());
    }
    
}
