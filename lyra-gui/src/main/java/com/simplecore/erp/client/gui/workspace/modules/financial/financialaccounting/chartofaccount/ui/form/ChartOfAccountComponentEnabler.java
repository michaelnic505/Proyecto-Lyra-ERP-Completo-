
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.form;

import com.simplecore.erp.client.abstractions.AbstractComponentState;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountComponentEnabler extends AbstractComponentState<ChartOfAccountFormPanel> {

    public ChartOfAccountComponentEnabler(ChartOfAccountFormPanel panel) {
        super(panel);
    }
    // Modo CREACIÓN (formularios vacíos, editable todo lo necesario)
    @Override
    public void applyCreateMode() {
        setEditable(panel::getCharOfAccountCodeTF, false);
        setEditable(panel::getChartOfAccountNameTF, true);
        setEditable(panel::getBusinessTypeTF, true);
        setEditable(panel::getBusinessClassificationTF, true);
        setEditable(panel::getFiscalYearTF, true);
        setEditable(panel::getVersionTagTF, true);
        setEditable(panel::getChartOfAccountNotes, true);
        setEditable(panel::getChartOfAccountDescription, true);

        setEditable(panel.getCurrencyCodeMatchCode()::getTextField, true);
        setEnabled(panel.getCurrencyCodeMatchCode()::getButton, true);
        setEditable(panel.getCountryCodeMatchCode()::getTextField, true);
        setEnabled(panel.getCountryCodeMatchCode()::getButton, true);
        setEditable(panel.getTaxSchemaMatchCode()::getTextField, true);
        setEnabled(panel.getTaxSchemaMatchCode()::getButton, true);

        setEnabled(panel::getAccountingStandardCombo, true);
        setEnabled(panel::getChartOfAccountStatusCombo, true);
        setEnabled(panel::getChartMultiCurrencyCheckbox, true);
        setEnabledDateChooser(panel::getFiscalStartDateChooser, true);
        setEnabledDateChooser(panel::getFiscalEndDateChooser, true);

        // Campos informativos (no se editan)
        setEditable(panel::getChartCreatedByTF, false);
        setEditable(panel::getChartCreatedAtTF, false);
        setEditable(panel::getChartUpdatedByTF, false);
        setEditable(panel::getChartUpdatedAtTF, false);
    }

    @Override
    public void applyModifyMode() {
        setEditable(panel::getCharOfAccountCodeTF, false);
        setEditable(panel::getChartOfAccountNameTF, true);
        setEditable(panel::getBusinessTypeTF, true);
        setEditable(panel::getBusinessClassificationTF, true);
        setEditable(panel::getFiscalYearTF, true);
        setEditable(panel::getVersionTagTF, true);
        setEditable(panel::getChartOfAccountNotes, true);
        setEditable(panel::getChartOfAccountDescription, true);

        setEditable(panel.getCurrencyCodeMatchCode()::getTextField, true);
        setEnabled(panel.getCurrencyCodeMatchCode()::getButton, true);
        setEditable(panel.getCountryCodeMatchCode()::getTextField, false);
        setEnabled(panel.getCountryCodeMatchCode()::getButton, false);
        setEditable(panel.getTaxSchemaMatchCode()::getTextField, true);
        setEnabled(panel.getTaxSchemaMatchCode()::getButton, true);

        setEnabled(panel::getAccountingStandardCombo, true);
        setEnabled(panel::getChartOfAccountStatusCombo, true);
        setEnabled(panel::getChartMultiCurrencyCheckbox, true);
        setEnabledDateChooser(panel::getFiscalStartDateChooser, true);
        setEnabledDateChooser(panel::getFiscalEndDateChooser, true);

        // Campos informativos (no se editan)
        setEditable(panel::getChartCreatedByTF, false);
        setEditable(panel::getChartCreatedAtTF, false);
        setEditable(panel::getChartUpdatedByTF, false);
        setEditable(panel::getChartUpdatedAtTF, false);
    }

    @Override
    public void applyViewMode() {
        setEditable(panel::getCharOfAccountCodeTF, false);
        setEditable(panel::getChartOfAccountNameTF, false);
        setEditable(panel::getBusinessTypeTF, false);
        setEditable(panel::getBusinessClassificationTF, false);
        setEditable(panel::getFiscalYearTF, false);
        setEditable(panel::getVersionTagTF, false);
        setEditable(panel::getChartOfAccountNotes, false);
        setEditable(panel::getChartOfAccountDescription, false);

        setEditable(panel.getCurrencyCodeMatchCode()::getTextField, false);
        setEnabled(panel.getCurrencyCodeMatchCode()::getButton, false);
        setEditable(panel.getCountryCodeMatchCode()::getTextField, false);
        setEnabled(panel.getCountryCodeMatchCode()::getButton, false);
        setEditable(panel.getTaxSchemaMatchCode()::getTextField, false);
        setEnabled(panel.getTaxSchemaMatchCode()::getButton, false);

        setEnabled(panel::getAccountingStandardCombo, false);
        setEnabled(panel::getChartOfAccountStatusCombo, false);
        setEnabled(panel::getChartMultiCurrencyCheckbox, false);
        setEnabledDateChooser(panel::getFiscalStartDateChooser, false);
        setEnabledDateChooser(panel::getFiscalEndDateChooser, false);

        // Campos informativos (no se editan)
        setEditable(panel::getChartCreatedByTF, false);
        setEditable(panel::getChartCreatedAtTF, false);
        setEditable(panel::getChartUpdatedByTF, false);
        setEditable(panel::getChartUpdatedAtTF, false);

    }
}
