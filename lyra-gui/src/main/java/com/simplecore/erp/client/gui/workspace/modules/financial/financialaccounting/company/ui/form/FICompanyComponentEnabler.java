

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form;

import com.simplecore.erp.client.abstractions.AbstractComponentState;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyComponentEnabler extends AbstractComponentState<FICompanyFormPanel> {

    public FICompanyComponentEnabler(FICompanyFormPanel panel) {
        super(panel);
    }

    @Override
    public void applyCreateMode() {
        setEditable(panel::getCompanyCodeTF, true);
        setEditable(panel::getCompanyNameTF, true);
        setEditable(panel::getLegalNameTF, true);
        setEditable(panel::getBusinessTypeTF, true);
        setEditable(panel::getIndustryClassificationTF, true);
        setEditableMatchText(panel::getCountryCodeMatchCode, true);
        setEnabledMatchButton(panel::getCountryCodeMatchCode, true);
        setEditable(panel::getLegalAddressTF, true);
        setEditable(panel::getPhoneTF, true);
        setEditable(panel::getEmailTF, true);
        setEditable(panel::getOfficialLanguageTF, true);
        setEditableMatchText(panel::getTimezoneMatchCode, true);
        setEnabledMatchButton(panel::getTimezoneMatchCode, true);
        
        setEditableMatchText(panel::getChartOfAccountMatchCode, true);
        setEnabledMatchButton(panel::getChartOfAccountMatchCode, true);
        setEditableMatchText(panel::getCurrencyCodeMatchCode, false);
        setEnabledMatchButton(panel::getCurrencyCodeMatchCode, false);
        setEditable(panel::getFiscalYearTF, false);
        setEnabledDateChooser(panel::getFiscalStartDateChooser, false);
        setEnabledDateChooser(panel::getFiscalEndDateChooser, false);
        setEnabled(panel::getAccountingStandardCombo, false);
        setEnabled(panel::getChartMultiCurrencyCheckbox, false);
        setEditableMatchText(panel::getTaxSchemaMatchCode, false);
        setEnabledMatchButton(panel::getTaxSchemaMatchCode, false);
        
        setEditable(panel::getCompanyCreatedAtTF, false);
        setEditable(panel::getCompanyUpdatedAtTF, false);
        setEditable(panel::getCompanyCreatedByTF, false);
        setEditable(panel::getCompanyUpdatedByTF, false);
        setEnabled(panel::getCompanyStatusCombo, true);
        
        setEditable(panel::getConstitutionActTF, false);
        setEnabled(panel::getConstitutionActButton, true);
        setEnabled(panel::getRemoveConstitutionActButton, true);
        setEnabled(panel::getViewConstitutionActButton, true);
        
        setEditable(panel::getRucCertificateTF, false);
        setEnabled(panel::getRucCertificateButton, true);
        setEnabled(panel::getRemoveRucCertificateButton, true);
        setEnabled(panel::getViewRucCertificateButton, true);
        
        setEditable(panel::getLegalPowerTF, false);
        setEnabled(panel::getLegalPowerButton, true);
        setEnabled(panel::getRemoveLegalPowerButton, true);
        setEnabled(panel::getViewLegalPowerButton, true);
        
        setEditable(panel::getMunicipalLicenseTF, false);
        setEnabled(panel::getMunicipalLicenseButton, true);
        setEnabled(panel::getRemoveMunicipalLicenseButton, true);
        setEnabled(panel::getViewMunicipalLicenseButton, true);
        
        setEditable(panel::getRepresentativeIDTF, false);
        setEnabled(panel::getRepresentativeIDButton, true);
        setEnabled(panel::getRemoveRepresentativeIDButton, true);
        setEnabled(panel::getViewRepresentativeIDButton, true);
        
        setEditable(panel::getEntityRegistrationTF, false);
        setEnabled(panel::getEntityRegistrationButton, true);
        setEnabled(panel::getRemoveEntityRegistrationButton, true);
        setEnabled(panel::getViewEntityRegistrationButton, true);
        
        setEditable(panel::getOtherDocument, false);
        setEnabled(panel::getOtherButton, true);
        setEnabled(panel::getRemoveOtherButton, true);
        setEnabled(panel::getViewOtherButton, true);
    }

    @Override
    public void applyModifyMode() {
        setEditable(panel::getCompanyCodeTF, false);
        setEditable(panel::getCompanyNameTF, true);
        setEditable(panel::getLegalNameTF, true);
        setEditable(panel::getBusinessTypeTF, true);
        setEditable(panel::getIndustryClassificationTF, true);
        setEditableMatchText(panel::getCountryCodeMatchCode, true);
        setEnabledMatchButton(panel::getCountryCodeMatchCode, true);
        setEditable(panel::getLegalAddressTF, true);
        setEditable(panel::getPhoneTF, true);
        setEditable(panel::getEmailTF, true);
        setEditable(panel::getOfficialLanguageTF, true);
        setEditableMatchText(panel::getTimezoneMatchCode, true);
        setEnabledMatchButton(panel::getTimezoneMatchCode, true);
        
        setEditableMatchText(panel::getChartOfAccountMatchCode, true);
        setEnabledMatchButton(panel::getChartOfAccountMatchCode, true);
        setEditableMatchText(panel::getCurrencyCodeMatchCode, false);
        setEnabledMatchButton(panel::getCurrencyCodeMatchCode, false);
        setEditable(panel::getFiscalYearTF, false);
        setEnabledDateChooser(panel::getFiscalStartDateChooser, false);
        setEnabledDateChooser(panel::getFiscalEndDateChooser, false);
        setEnabled(panel::getAccountingStandardCombo, false);
        setEnabled(panel::getChartMultiCurrencyCheckbox, false);
        setEditableMatchText(panel::getTaxSchemaMatchCode, false);
        setEnabledMatchButton(panel::getTaxSchemaMatchCode, false);
        
        setEditable(panel::getCompanyCreatedAtTF, false);
        setEditable(panel::getCompanyUpdatedAtTF, false);
        setEditable(panel::getCompanyCreatedByTF, false);
        setEditable(panel::getCompanyUpdatedByTF, false);
        setEnabled(panel::getCompanyStatusCombo, true);
        
        setEditable(panel::getConstitutionActTF, false);
        setEnabled(panel::getConstitutionActButton, true);
        setEnabled(panel::getRemoveConstitutionActButton, true);
        setEnabled(panel::getViewConstitutionActButton, true);
        
        setEditable(panel::getRucCertificateTF, false);
        setEnabled(panel::getRucCertificateButton, true);
        setEnabled(panel::getRemoveRucCertificateButton, true);
        setEnabled(panel::getViewRucCertificateButton, true);
        
        setEditable(panel::getLegalPowerTF, false);
        setEnabled(panel::getLegalPowerButton, true);
        setEnabled(panel::getRemoveLegalPowerButton, true);
        setEnabled(panel::getViewLegalPowerButton, true);
        
        setEditable(panel::getMunicipalLicenseTF, false);
        setEnabled(panel::getMunicipalLicenseButton, true);
        setEnabled(panel::getRemoveMunicipalLicenseButton, true);
        setEnabled(panel::getViewMunicipalLicenseButton, true);
        
        setEditable(panel::getRepresentativeIDTF, false);
        setEnabled(panel::getRepresentativeIDButton, true);
        setEnabled(panel::getRemoveRepresentativeIDButton, true);
        setEnabled(panel::getViewRepresentativeIDButton, true);
        
        setEditable(panel::getEntityRegistrationTF, false);
        setEnabled(panel::getEntityRegistrationButton, true);
        setEnabled(panel::getRemoveEntityRegistrationButton, true);
        setEnabled(panel::getViewEntityRegistrationButton, true);
        
        setEditable(panel::getOtherDocument, false);
        setEnabled(panel::getOtherButton, true);
        setEnabled(panel::getRemoveOtherButton, true);
        setEnabled(panel::getViewOtherButton, true);
    }

    @Override
    public void applyViewMode() {
        setEditable(panel::getCompanyCodeTF, false);
        setEditable(panel::getCompanyNameTF, false);
        setEditable(panel::getLegalNameTF, false);
        setEditable(panel::getBusinessTypeTF, false);
        setEditable(panel::getIndustryClassificationTF, false);
        setEditableMatchText(panel::getCountryCodeMatchCode, false);
        setEnabledMatchButton(panel::getCountryCodeMatchCode, false);
        setEditable(panel::getLegalAddressTF, false);
        setEditable(panel::getPhoneTF, false);
        setEditable(panel::getEmailTF, false);
        setEditable(panel::getOfficialLanguageTF, false);
        setEditableMatchText(panel::getTimezoneMatchCode, false);
        setEnabledMatchButton(panel::getTimezoneMatchCode, false);
        
        setEditableMatchText(panel::getChartOfAccountMatchCode, false);
        setEnabledMatchButton(panel::getChartOfAccountMatchCode, false);
        setEditableMatchText(panel::getCurrencyCodeMatchCode, false);
        setEnabledMatchButton(panel::getCurrencyCodeMatchCode, false);
        setEditable(panel::getFiscalYearTF, false);
        setEnabledDateChooser(panel::getFiscalStartDateChooser, false);
        setEnabledDateChooser(panel::getFiscalEndDateChooser, false);
        setEnabled(panel::getAccountingStandardCombo, false);
        setEnabled(panel::getChartMultiCurrencyCheckbox, false);
        setEditableMatchText(panel::getTaxSchemaMatchCode, false);
        setEnabledMatchButton(panel::getTaxSchemaMatchCode, false);
        
        setEditable(panel::getCompanyCreatedAtTF, false);
        setEditable(panel::getCompanyUpdatedAtTF, false);
        setEditable(panel::getCompanyCreatedByTF, false);
        setEditable(panel::getCompanyUpdatedByTF, false);
        setEnabled(panel::getCompanyStatusCombo, false);
        
        setEditable(panel::getConstitutionActTF, false);
        setEnabled(panel::getConstitutionActButton, false);
        setEnabled(panel::getRemoveConstitutionActButton, false);
        setEnabled(panel::getViewConstitutionActButton, true);
        
        setEditable(panel::getRucCertificateTF, false);
        setEnabled(panel::getRucCertificateButton, false);
        setEnabled(panel::getRemoveRucCertificateButton, false);
        setEnabled(panel::getViewRucCertificateButton, true);
        
        setEditable(panel::getLegalPowerTF, false);
        setEnabled(panel::getLegalPowerButton, false);
        setEnabled(panel::getRemoveLegalPowerButton, false);
        setEnabled(panel::getViewLegalPowerButton, true);
        
        setEditable(panel::getMunicipalLicenseTF, false);
        setEnabled(panel::getMunicipalLicenseButton, false);
        setEnabled(panel::getRemoveMunicipalLicenseButton, false);
        setEnabled(panel::getViewMunicipalLicenseButton, true);
        
        setEditable(panel::getRepresentativeIDTF, false);
        setEnabled(panel::getRepresentativeIDButton, false);
        setEnabled(panel::getRemoveRepresentativeIDButton, false);
        setEnabled(panel::getViewRepresentativeIDButton, true);
        
        setEditable(panel::getEntityRegistrationTF, false);
        setEnabled(panel::getEntityRegistrationButton, false);
        setEnabled(panel::getRemoveEntityRegistrationButton, false);
        setEnabled(panel::getViewEntityRegistrationButton, true);
        
        setEditable(panel::getOtherDocument, false);
        setEnabled(panel::getOtherButton, false);
        setEnabled(panel::getRemoveOtherButton, false);
        setEnabled(panel::getViewOtherButton, true);
        
        panel.getCOAssociationPanel().getAddButton().setEnabled(false);
    }

}
