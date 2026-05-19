

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.services;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form.AccountTableUtil;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form.FinancialAccountSetup;
import com.simplecore.erp.shared.models.dto.AccountCardData;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountDTOInjector {
    
    private FinancialAccountSetup panel;
    private AccountByChartOfAccountRetrieveService accountRetrieveService;

    public AccountDTOInjector(AccountByChartOfAccountRetrieveService accountRetrieveService,FinancialAccountSetup panel) {
        this.accountRetrieveService = accountRetrieveService;
        this.panel = panel;
    }
    
    public void inject(FICompanyDTO companyDTO) {
        if (companyDTO == null) {
            return;
        }
        String chartOfAccountCode = companyDTO.getCompanyChartOfAccount();
        if (chartOfAccountCode == null || chartOfAccountCode.isBlank()) {
            return;
        }

        List<AccountCardData> accountList = retrieveAccounts(chartOfAccountCode);
        processList(accountList);
    }

    private List<AccountCardData> retrieveAccounts(String chartOfAccountCode) {
        return accountRetrieveService.getListOfAccountsToConfig(chartOfAccountCode);
    }

    private void processList(List<AccountCardData> accountList) {
        if(accountList.isEmpty()){
            return;
        }
                
        Object[][] data = convertData(accountList);
        new AccountTableUtil(panel).injectTDataSourceInTable(data);
    }
    
    private Object[][] convertData(List<AccountCardData> accountList) {
        List<Object[]> dataSource = new ArrayList<>();

        for (AccountCardData data : accountList) {
            dataSource.add(new Object[]{
                data.accountID,
                data.chartOfAccountCode,
                data.chartOfAccountName,
                data.className,
                data.subclassCode,
                data.subclassName,
                data.accountName,
                data.accountDescription,
                data.accountStatus,
                data.accountCode});
        }
        return Optional.ofNullable(dataSource.toArray(Object[][]::new)).orElse(new Object[0][10]);
    }
}
