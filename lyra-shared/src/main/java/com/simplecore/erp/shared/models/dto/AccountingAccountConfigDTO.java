

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
public class AccountingAccountConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final Long id;
    private final Long fiCompanyId;
    private final Long chartOfAccountId;
    private final Integer accountingAccountId;

    private final boolean multicurrencyEnabled;
    private final String baseCurrencyCode;

    private final boolean isReconciliable;
    private final boolean isControlAccount;
    private final boolean requiresAuxiliary;
    private final boolean requiresCostCenter;

    private final Long defaultTaxSchemaId;

    private final String usagePurpose;
    private final String notes;

    private final String status;

    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    // Opcionales para UI o reportes
    private final String companyName;
    private final String chartOfAccountName;
    private final String accountingAccountName;
    
        // Constructor privado: solo accesible por Builder
    private AccountingAccountConfigDTO(Builder builder) {
        this.id = builder.id;
        this.fiCompanyId = builder.fiCompanyId;
        this.chartOfAccountId = builder.chartOfAccountId;
        this.accountingAccountId = builder.accountingAccountId;
        this.multicurrencyEnabled = builder.multicurrencyEnabled;
        this.baseCurrencyCode = builder.baseCurrencyCode;
        this.isReconciliable = builder.isReconciliable;
        this.isControlAccount = builder.isControlAccount;
        this.requiresAuxiliary = builder.requiresAuxiliary;
        this.requiresCostCenter = builder.requiresCostCenter;
        this.defaultTaxSchemaId = builder.defaultTaxSchemaId;
        this.usagePurpose = builder.usagePurpose;
        this.notes = builder.notes;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.companyName = builder.companyName;
        this.chartOfAccountName = builder.chartOfAccountName;
        this.accountingAccountName = builder.accountingAccountName;
    }
    
        // Getters (puedes generar solo los que uses realmente)
    public Long getId() { return id; }
    public Long getFiCompanyId() { return fiCompanyId; }
    public Long getChartOfAccountId() { return chartOfAccountId; }
    public Integer getAccountingAccountId() { return accountingAccountId; }
    public boolean isMulticurrencyEnabled() { return multicurrencyEnabled; }
    public String getBaseCurrencyCode() { return baseCurrencyCode; }
    public boolean isReconciliable() { return isReconciliable; }
    public boolean isControlAccount() { return isControlAccount; }
    public boolean isRequiresAuxiliary() { return requiresAuxiliary; }
    public boolean isRequiresCostCenter() { return requiresCostCenter; }
    public Long getDefaultTaxSchemaId() { return defaultTaxSchemaId; }
    public String getUsagePurpose() { return usagePurpose; }
    public String getNotes() { return notes; }
    public String getStatus() { return status; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public String getCompanyName() { return companyName; }
    public String getChartOfAccountName() { return chartOfAccountName; }
    public String getAccountingAccountName() { return accountingAccountName; }

    // Builder interno
    public static class Builder {
        private Long id;
        private Long fiCompanyId;
        private Long chartOfAccountId;
        private Integer accountingAccountId;

        private boolean multicurrencyEnabled;
        private String baseCurrencyCode;

        private boolean isReconciliable;
        private boolean isControlAccount;
        private boolean requiresAuxiliary;
        private boolean requiresCostCenter;

        private Long defaultTaxSchemaId;

        private String usagePurpose;
        private String notes;

        private String status;

        private Timestamp createdAt;
        private Timestamp updatedAt;

        private String companyName;
        private String chartOfAccountName;
        private String accountingAccountName;

        public Builder fiCompanyId(Long fiCompanyId) {
            this.fiCompanyId = fiCompanyId;
            return this;
        }

        public Builder chartOfAccountId(Long chartOfAccountId) {
            this.chartOfAccountId = chartOfAccountId;
            return this;
        }

        public Builder accountingAccountId(Integer accountingAccountId) {
            this.accountingAccountId = accountingAccountId;
            return this;
        }

        public Builder multicurrencyEnabled(boolean multicurrencyEnabled) {
            this.multicurrencyEnabled = multicurrencyEnabled;
            return this;
        }

        public Builder baseCurrencyCode(String baseCurrencyCode) {
            this.baseCurrencyCode = baseCurrencyCode;
            return this;
        }

        public Builder isReconciliable(boolean isReconciliable) {
            this.isReconciliable = isReconciliable;
            return this;
        }

        public Builder isControlAccount(boolean isControlAccount) {
            this.isControlAccount = isControlAccount;
            return this;
        }

        public Builder requiresAuxiliary(boolean requiresAuxiliary) {
            this.requiresAuxiliary = requiresAuxiliary;
            return this;
        }

        public Builder requiresCostCenter(boolean requiresCostCenter) {
            this.requiresCostCenter = requiresCostCenter;
            return this;
        }

        public Builder defaultTaxSchemaId(Long defaultTaxSchemaId) {
            this.defaultTaxSchemaId = defaultTaxSchemaId;
            return this;
        }

        public Builder usagePurpose(String usagePurpose) {
            this.usagePurpose = usagePurpose;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder chartOfAccountName(String chartOfAccountName) {
            this.chartOfAccountName = chartOfAccountName;
            return this;
        }

        public Builder accountingAccountName(String accountingAccountName) {
            this.accountingAccountName = accountingAccountName;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public AccountingAccountConfigDTO build() {
            return new AccountingAccountConfigDTO(this);
        }
    }
    
}
