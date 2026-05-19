

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.config;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.FICompanyFormPanel;
import com.simplecore.erp.client.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterVarchar;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyFieldLimiter extends AbstractFieldLimiter<FICompanyFormPanel,FICompanyFieldLimiter> {

    public FICompanyFieldLimiter(FICompanyFormPanel component) {
        super(component);
    }

    @Override
    public FICompanyFieldLimiter applyFilter() {
        apply(component.getCompanyCodeTF(),new DocFilterVarcharWithoutSpace(18));
        apply(component.getLegalNameTF(), new DocumentFilterVarchar(145));
        apply(component.getBusinessTypeTF(), new DocumentFilterVarchar(45));
        apply(component.getIndustryClassificationTF(), new DocumentFilterVarchar(45));
        apply(component.getCountryCodeMatchCode().getTextField(), new DocumentFilterVarchar(2));
        apply(component.getLegalAddressTF(), new DocumentFilterVarchar(150));
        apply(component.getPhoneTF(), new DocumentFilterVarchar(30));
        apply(component.getEmailTF(), new DocumentFilterVarchar(100));
        apply(component.getOfficialLanguageTF(), new DocumentFilterVarchar(10));
        apply(component.getTimezoneMatchCode().getTextField(), new DocumentFilterVarchar(50));
        apply(component.getChartOfAccountMatchCode().getTextField(), new DocumentFilterVarchar(50));
        return this;
    }
}
