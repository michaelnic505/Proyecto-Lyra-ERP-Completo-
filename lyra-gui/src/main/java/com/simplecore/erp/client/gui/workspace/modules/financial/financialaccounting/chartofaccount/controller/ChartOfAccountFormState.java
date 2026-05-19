

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.abstractions.FormState;
import java.sql.Timestamp;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountFormState extends FormState{

    private String chartOfAccountCode;
    private String chartOfAccountName;
    private String currencyCode;
    private String countryCode;
    private String businessType;
    private String businessClass;
    private int fiscalYear;
    private Timestamp fiscalStartDate;
    private Timestamp fiscalEndDate;
    private String accountingStandard;
    private String chartOfAccountStatus;
    private boolean multiCurrencyAllowed;
    private int taxSchemaID;
    private String chartVersionTag;
    private String chartOfAccountNotes;
    private String chartOfAccountDescription;
    private String createdBy;

    public ChartOfAccountFormState(int fieldsCount) {
        super(fieldsCount);
    }

    // Getters y Setters
    public String getChartVersiontag() { return chartVersionTag; }
    public void setChartVersionTag(String chartVersionTag) { this.chartVersionTag = chartVersionTag; updateFilledFields(this);}
    
    public String getChartOfAccountCode() { return chartOfAccountCode; }
    public void setChartOfAccountCode(String chartOfAccountCode) { this.chartOfAccountCode = chartOfAccountCode;updateFilledFields(this); }

    public String getChartOfAccountName() { return chartOfAccountName; }
    public void setChartOfAccountName(String chartOfAccountName) { this.chartOfAccountName = chartOfAccountName;updateFilledFields(this); }

    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; updateFilledFields(this);}

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; updateFilledFields(this);}

    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; updateFilledFields(this);}

    public String getBusinessClass() { return businessClass; }
    public void setBusinessClass(String businessClass) { this.businessClass = businessClass; updateFilledFields(this);}

    public int getFiscalYear() { return fiscalYear; }
    public void setFiscalYear(int fiscalYear) { this.fiscalYear = fiscalYear; }

    public Timestamp getFiscalStartDate() { return fiscalStartDate; }
    public void setFiscalStartDate(Timestamp fiscalStartDate) { this.fiscalStartDate = fiscalStartDate; updateFilledFields(this);}

    public Timestamp getFiscalEndDate() { return fiscalEndDate; }
    public void setFiscalEndDate(Timestamp fiscalEndDate) { this.fiscalEndDate = fiscalEndDate; updateFilledFields(this);}

    public String getAccountingStandard() { return accountingStandard; }
    public void setAccountingStandard(String accountingStandard) { this.accountingStandard = accountingStandard; updateFilledFields(this);}

    public String getChartOfAccountStatus() { return chartOfAccountStatus; }
    public void setChartOfAccountStatus(String chartOfAccountStatus) { this.chartOfAccountStatus = chartOfAccountStatus; updateFilledFields(this);}

    public boolean isMultiCurrencyAllowed() { return multiCurrencyAllowed; }
    public void setMultiCurrencyAllowed(boolean multiCurrencyAllowed) { this.multiCurrencyAllowed = multiCurrencyAllowed; updateFilledFields(this);}

    public String getChartOfAccountNotes() { return chartOfAccountNotes; }
    public void setChartOfAccountNotes(String chartOfAccountNotes) { this.chartOfAccountNotes = chartOfAccountNotes; updateFilledFields(this);}
    
    public String getChartOfAccountDescription() {return chartOfAccountDescription;}
    public void setChartOfAccountDescription(String chartOfAccountDescription) {this.chartOfAccountDescription = chartOfAccountDescription; updateFilledFields(this);}

    public int getTaxSchemaID() { return taxSchemaID; }
    public void setTaxSchemaID(int taxSchemaDescription) { this.taxSchemaID = taxSchemaDescription; updateFilledFields(this);}

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; updateFilledFields(this);}

    
}