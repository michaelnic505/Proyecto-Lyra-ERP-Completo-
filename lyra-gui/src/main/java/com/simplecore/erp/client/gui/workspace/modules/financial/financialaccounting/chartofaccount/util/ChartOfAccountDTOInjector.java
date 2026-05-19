

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.util;

import com.simplecore.erp.client.abstractions.AbstractDTOFormInjector;
import com.simplecore.erp.client.abstractions.DTOInjector;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.form.ChartOfAccountFormPanel;
import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;
/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountDTOInjector extends AbstractDTOFormInjector<ChartOfAccountFormPanel> 
        implements DTOInjector<ChartOfAccountDTO>{

    public ChartOfAccountDTOInjector(ChartOfAccountFormPanel panel) {
        super(panel);   
    }

    @Override
    public void inject(ChartOfAccountDTO chartDTO) {
        if (chartDTO == null) {
            return;
        }
        setValue(panel::getCharOfAccountCodeTF, chartDTO.getChartOfAccountCode());
        setValue(panel::getChartOfAccountNameTF, chartDTO.getChartOfAccountName());
        setLabelValue(panel::getModelAccountNameValuelb, chartDTO.getAccountModelName());
        setLabelValue(panel::getModelAccountDescriptionLb, chartDTO.getAccountModelDescription());
        setValue(()->panel.getCurrencyCodeMatchCode().getTextField(), chartDTO.getCurrencyCode());
        setLabelValue(panel::getCurrencyNameLb, chartDTO.getCurrencyDescription());
        setValue(()->panel.getCountryCodeMatchCode().getTextField(), chartDTO.getCountryCode());
        setLabelValue(panel::getCountryNameLb, chartDTO.getCountryDescription());
        setValue(panel::getBusinessTypeTF, chartDTO.getBusinessType());
        setValue(panel::getBusinessClassificationTF, chartDTO.getBusinessClass());
        setValue(panel::getFiscalYearTF, chartDTO.getFiscalYear());
        setDateValue(panel::getFiscalStartDateChooser, chartDTO.getFiscalStartDate());
        setDateValue(panel::getFiscalEndDateChooser, chartDTO.getFiscalEndDate());
        setValueC(panel::getAccountingStandardCombo, chartDTO.getAccountingStandard());
        setValueC(panel::getChartOfAccountStatusCombo, chartDTO.getChartOfAccountStatus());
        setValue(()->panel.getTaxSchemaMatchCode().getTextField(), chartDTO.getTaxSchemaID());
        setLabelValue(panel::getTaxSchemaNamelb, chartDTO.getTaxSchemaDescription());
        setValue(panel::getVersionTagTF, chartDTO.getChartVersionTag());
        setCheckValue(panel::getChartMultiCurrencyCheckbox, chartDTO.isMultiCurrencyAllowed());
        setValue(panel::getChartOfAccountNotes, chartDTO.getChartOfAccountNotes());
        setValue(panel::getChartOfAccountDescription, chartDTO.getAccountModelDescription());
        setValue(panel::getChartCreatedAtTF, chartDTO.getCreatedAt());
        setValue(panel::getChartCreatedByTF, chartDTO.getCreatedBy());
        setValue(panel::getChartUpdatedAtTF, chartDTO.getUpdatedAt());
        setValue(panel::getChartUpdatedByTF, chartDTO.getUpdatedBy());
    }
}
