
package com.simplecore.erp.shared.models.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    private final int accountModelID;
    private final String accountModelName;
    private final String accountModelDescription;
    private final String transactionCode;
    private final String chartOfAccountCode;
    private final String chartOfAccountName;
    private final String currencyCode;
    private final String currencyDescription;
    private final String countryCode;
    private final String countryDescription;
    private final String businessType;
    private final String businessClass;
    private final int fiscalYear;
    private final Timestamp fiscalStartDate;
    private final Timestamp fiscalEndDate;
    private final String accountingStandard;
    private final String chartOfAccountStatus;
    private final boolean multiCurrencyAllowed;
    private final int taxSchemaID;
    private final String taxSchemaDescription;
    private final String chartVersionTag;
    private final String chartOfAccountNotes;
    private final String chartOfAccountDescription;
    private final String createdBy;
    private final Timestamp createdAt;
    private final String updatedBy;
    private final Timestamp updatedAt;

    private ChartOfAccountDTO(Builder builder) {
        this.accountModelID = builder.accountModelID;
        this.accountModelName = builder.accountModelName;
        this.accountModelDescription = builder.accountModelDescription;
        this.transactionCode = builder.transactionCode;
        this.chartOfAccountCode = builder.chartOfAccountCode;
        this.chartOfAccountName = builder.chartOfAccountName;
        this.currencyCode = builder.currencyCode;
        this.currencyDescription = builder.currencyDescription;
        this.countryCode = builder.countryCode;
        this.countryDescription = builder.countryDescription;
        this.businessType = builder.businessType;
        this.businessClass = builder.businessClass;
        this.fiscalYear = builder.fiscalYear;
        this.fiscalStartDate = builder.fiscalStartDate;
        this.fiscalEndDate = builder.fiscalEndDate;
        this.accountingStandard = builder.accountingStandard;
        this.chartOfAccountStatus = builder.chartOfAccountStatus;
        this.multiCurrencyAllowed = builder.multiCurrencyAllowed;
        this.taxSchemaID = builder.taxSchemaID;
        this.taxSchemaDescription = builder.taxSchemaDescription;
        this.chartVersionTag = builder.chartVersionTag;
        this.chartOfAccountNotes = builder.chartOfAccountNotes;
        this.chartOfAccountDescription = builder.chartOfAccountDescription;
        this.createdBy = builder.createdBy;
        this.createdAt = builder.createdAt;
        this.updatedBy = builder.updatedBy;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {

        private int accountModelID;
        private String accountModelName;
        private String accountModelDescription;
        private String transactionCode;
        private String chartOfAccountCode;
        private String chartOfAccountName;
        private String currencyCode;
        private String currencyDescription;
        private String countryCode;
        private String countryDescription;
        private String businessType;
        private String businessClass;
        private int fiscalYear;
        private Timestamp fiscalStartDate;
        private Timestamp fiscalEndDate;
        private String accountingStandard;
        private String chartOfAccountStatus;
        private boolean multiCurrencyAllowed;
        private int taxSchemaID;
        private String taxSchemaDescription;
        private String chartVersionTag;
        private String chartOfAccountNotes;
        private String chartOfAccountDescription;
        private String createdBy;
        private Timestamp createdAt;
        private String updatedBy;
        private Timestamp updatedAt;

        public Builder accountModelID(int value) {
            this.accountModelID = value;
            return this;
        }
        
        public Builder accountModelName(String value) {
            this.accountModelName = value;
            return this;
        }
        
        public Builder accountModelDescription(String value) {
            this.accountModelDescription = value;
            return this;
        }

        public Builder transactionCode(String value) {
            this.transactionCode = value;
            return this;
        }

        public Builder chartOfAccountCode(String value) {
            this.chartOfAccountCode = value;
            return this;
        }

        public Builder chartOfAccountName(String value) {
            this.chartOfAccountName = value;
            return this;
        }

        public Builder currencyCode(String value) {
            this.currencyCode = value;
            return this;
        }
        
        public Builder currencyDescription(String value) {
            this.currencyDescription = value;
            return this;
        }

        public Builder countryCode(String value) {
            this.countryCode = value;
            return this;
        }
        
        public Builder countryDescription(String value) {
            this.countryDescription = value;
            return this;
        }

        public Builder businessType(String value) {
            this.businessType = value;
            return this;
        }

        public Builder businessClass(String value) {
            this.businessClass = value;
            return this;
        }

        public Builder fiscalYear(int value) {
            this.fiscalYear = value;
            return this;
        }

        public Builder fiscalStartDate(Timestamp value) {
            this.fiscalStartDate = value;
            return this;
        }

        public Builder fiscalEndDate(Timestamp value) {
            this.fiscalEndDate = value;
            return this;
        }

        public Builder accountingStandard(String value) {
            this.accountingStandard = value;
            return this;
        }

        public Builder chartOfAccountStatus(String value) {
            this.chartOfAccountStatus = value;
            return this;
        }

        public Builder multiCurrencyAllowed(boolean value) {
            this.multiCurrencyAllowed = value;
            return this;
        }

        public Builder taxSchemaID(int value) {
            this.taxSchemaID = value;
            return this;
        }

        public Builder taxSchemaDescription(String value) {
            this.taxSchemaDescription = value;
            return this;
        }
        
        public Builder chartVersionTag(String value) {
            this.chartVersionTag = value;
            return this;
        }

        public Builder chartOfAccountNotes(String value) {
            this.chartOfAccountNotes = value;
            return this;
        }

        public Builder chartOfAccountDescription(String value) {
            this.chartOfAccountDescription = value;
            return this;
        }

        public Builder createdBy(String value) {
            this.createdBy = value;
            return this;
        }

        public Builder createdAt(Timestamp value) {
            this.createdAt = value;
            return this;
        }

        public Builder updatedBy(String value) {
            this.updatedBy = value;
            return this;
        }

        public Builder updateAt(Timestamp value) {
            this.updatedAt = value;
            return this;
        }

        public ChartOfAccountDTO build() {
            return new ChartOfAccountDTO(this);
        }
    }

    // Getters opcionales si necesitás acceso inmutable
    public int getAccountModelID() { return accountModelID; }
    public String getTransactionCode() { return transactionCode; }
    public String getChartOfAccountCode() { return chartOfAccountCode; }
    public String getChartOfAccountName() { return chartOfAccountName; }
    public String getCurrencyCode() { return currencyCode; }
    public String getCountryCode() { return countryCode; }
    public String getBusinessType() { return businessType; }
    public String getBusinessClass() { return businessClass; }
    public int getFiscalYear() { return fiscalYear; }
    public Timestamp getFiscalStartDate() { return fiscalStartDate; }
    public Timestamp getFiscalEndDate() { return fiscalEndDate; }
    public String getAccountingStandard() { return accountingStandard; }
    public String getChartOfAccountStatus() { return chartOfAccountStatus; }
    public boolean isMultiCurrencyAllowed() { return multiCurrencyAllowed; }
    public int getTaxSchemaID() { return taxSchemaID; }
    public String getChartVersionTag() { return chartVersionTag; }
    public String getChartOfAccountNotes() { return chartOfAccountNotes; }
    public String getChartOfAccountDescription() { return chartOfAccountDescription; }
    public String getCreatedBy() { return createdBy; }
    public Timestamp getCreatedAt() { return createdAt; }
    public String getUpdatedBy() { return updatedBy; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public String getAccountModelName() {return accountModelName;}
    public String getAccountModelDescription() {return accountModelDescription;}
    public String getCurrencyDescription() {return currencyDescription;}
    public String getCountryDescription() {return countryDescription;}
    public String getTaxSchemaDescription() {return taxSchemaDescription;}
}
