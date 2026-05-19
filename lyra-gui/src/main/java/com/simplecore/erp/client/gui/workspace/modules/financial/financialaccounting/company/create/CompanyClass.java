
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.create;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum CompanyClass {
    CONTROLLING("CO"),
    FINANCIAL_ACCOUNTING("FI");
    
    String companyClass;

    private CompanyClass(String companyClass) {
        this.companyClass = companyClass;
    }
    public String getCompanyClass() {
        return companyClass;
    }
    
}
