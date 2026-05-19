

package com.simplecore.erp.shared.models.dto;

import java.io.Serializable;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountCardData implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long accountID;
    public String chartOfAccountCode;
    public String chartOfAccountName;
    public String className;
    public String subclassCode;
    public String subclassName;
    public String accountName;
    public String accountDescription;
    public String accountStatus;
    public String accountCode;

    public AccountCardData(
            Long accountID,
            String chartOfAccountCode,
            String chartOfAccountName,
            String className,
            String subclassCode,
            String subclassName,
            String accountName,
            String accountDescription,
            String accountStatus,
            String accountCode
    ) {
        this.accountID = accountID;
        this.chartOfAccountCode = chartOfAccountCode;
        this.chartOfAccountName = chartOfAccountName;
        this.className = className;
        this.subclassCode = subclassCode;
        this.subclassName = subclassName;
        this.accountName = accountName;
        this.accountDescription = accountDescription;
        this.accountStatus = accountStatus;
        this.accountCode = accountCode;
    }
}
