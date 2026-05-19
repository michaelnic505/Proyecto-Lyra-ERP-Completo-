
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountMapper {

    // Método para mapear FormState a ChartOfAccountDTO
    public static ChartOfAccountDTO mapToDTOToCreate(ChartOfAccountFormState state,
            String transactionCode,
            String createdBy,
            int accountModelID) {
        return new ChartOfAccountDTO.Builder()
                .accountModelID(accountModelID)
                .chartOfAccountCode(state.getChartOfAccountCode())
                .chartOfAccountName(state.getChartOfAccountName())
                .currencyCode(state.getCurrencyCode())
                .countryCode(state.getCountryCode())
                .businessType(state.getBusinessType())
                .businessClass(state.getBusinessClass())
                .fiscalYear(state.getFiscalYear())
                .fiscalStartDate(state.getFiscalStartDate())
                .fiscalEndDate(state.getFiscalEndDate())
                .accountingStandard(state.getAccountingStandard())
                .chartOfAccountStatus(state.getChartOfAccountStatus())
                .multiCurrencyAllowed(state.isMultiCurrencyAllowed())
                .taxSchemaID(state.getTaxSchemaID())
                .chartVersionTag(state.getChartVersiontag())
                .chartOfAccountNotes(state.getChartOfAccountNotes())
                .chartOfAccountDescription(state.getChartOfAccountDescription())
                .createdBy(createdBy)
                .transactionCode(transactionCode)
                .build();
    }

    public static ChartOfAccountDTO mapToDTOToChange(ChartOfAccountFormState state,
            String transactionCode,
            String updatedBy) {
        return new ChartOfAccountDTO.Builder()
                .chartOfAccountCode(state.getChartOfAccountCode())
                .chartOfAccountName(state.getChartOfAccountName())
                .currencyCode(state.getCurrencyCode())
                .businessType(state.getBusinessType())
                .businessClass(state.getBusinessClass())
                .fiscalYear(state.getFiscalYear())
                .fiscalStartDate(state.getFiscalStartDate())
                .fiscalEndDate(state.getFiscalEndDate())
                .accountingStandard(state.getAccountingStandard())
                .chartOfAccountStatus(state.getChartOfAccountStatus())
                .multiCurrencyAllowed(state.isMultiCurrencyAllowed())
                .taxSchemaID(state.getTaxSchemaID())
                .chartVersionTag(state.getChartVersiontag())
                .chartOfAccountNotes(state.getChartOfAccountNotes())
                .chartOfAccountDescription(state.getChartOfAccountDescription())
                .updatedBy(updatedBy)
                .transactionCode(transactionCode)
                .build();
    }

}
