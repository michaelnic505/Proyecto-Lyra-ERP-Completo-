

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
public class FICompanyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long companyID;
    private String companyCode;
    private String companyName;
    private String companyLegalName;
    private String companyBussinesType;
    private String companyBussinesClassification;
    private String companyCountryCode;
    private String companyLegalAddress;
    private String companyPhone;
    private String companyEmail;
    private String companyOfficialLanguage;
    private String companyTimeZone;
    private String companyChartOfAccount;
    private String companyCreatedBy;
    private Timestamp companyCreatedAt;
    private String companyUpdatedBy;
    private Timestamp companyUpdatedAt;
    private String companyStatus;
    private String sysTransaction;

    private FICompanyDTO(Builder builder) {
        this.companyID = builder.companyID;
        this.companyCode = builder.companyCode;
        this.companyName = builder.companyName;
        this.companyLegalName = builder.companyLegalName;
        this.companyBussinesType = builder.companyBussinesType;
        this.companyBussinesClassification = builder.companyBussinesClassification;
        this.companyCountryCode = builder.companyCountryCode;
        this.companyLegalAddress = builder.companyLegalAddress;
        this.companyPhone = builder.companyPhone;
        this.companyEmail = builder.companyEmail;
        this.companyOfficialLanguage = builder.companyOfficialLanguage;
        this.companyTimeZone = builder.companyTimeZone;
        this.companyChartOfAccount = builder.companyChartOfAccount;
        this.companyCreatedBy = builder.companyCreatedBy;
        this.companyCreatedAt = builder.companyCreatedAt;
        this.companyUpdatedBy = builder.companyUpdatedBy;
        this.companyUpdatedAt = builder.companyUpdatedAt;
        this.companyStatus = builder.companyStatus;
        this.sysTransaction = builder.sysTransaction;
    }

    public static class Builder {
        private long companyID;
        private String companyCode;
        private String companyName;
        private String companyLegalName;
        private String companyBussinesType;
        private String companyBussinesClassification;
        private String companyCountryCode;
        private String companyLegalAddress;
        private String companyPhone;
        private String companyEmail;
        private String companyOfficialLanguage;
        private String companyTimeZone;
        private String companyChartOfAccount;
        private String companyCreatedBy;
        private Timestamp companyCreatedAt;
        private String companyUpdatedBy;
        private Timestamp companyUpdatedAt;
        private String companyStatus;
        private String sysTransaction;

        public Builder setCompanyID(long companyID) {
            this.companyID = companyID;
            return this;
        }
        
        public Builder setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
            return this;
        }

        public Builder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder setCompanyLegalName(String companyLegalName) {
            this.companyLegalName = companyLegalName;
            return this;
        }

        public Builder setCompanyBussinesType(String companyBussinesType) {
            this.companyBussinesType = companyBussinesType;
            return this;
        }

        public Builder setCompanyBussinesClassification(String companyBussinesClassification) {
            this.companyBussinesClassification = companyBussinesClassification;
            return this;
        }

        public Builder setCompanyCountryCode(String companyCountryCode) {
            this.companyCountryCode = companyCountryCode;
            return this;
        }

        public Builder setCompanyLegalAddress(String companyLegalAddress) {
            this.companyLegalAddress = companyLegalAddress;
            return this;
        }

        public Builder setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
            return this;
        }

        public Builder setCompanyEmail(String companyEmail) {
            this.companyEmail = companyEmail;
            return this;
        }

        public Builder setCompanyOfficialLanguage(String companyOfficialLanguage) {
            this.companyOfficialLanguage = companyOfficialLanguage;
            return this;
        }

        public Builder setCompanyTimeZone(String companyTimeZone) {
            this.companyTimeZone = companyTimeZone;
            return this;
        }

        public Builder setCompanyChartOfAccount(String companyChartOfAccount) {
            this.companyChartOfAccount = companyChartOfAccount;
            return this;
        }

        public Builder setCompanyCreatedBy(String companyCreatedBy) {
            this.companyCreatedBy = companyCreatedBy;
            return this;
        }

        public Builder setCompanyCreatedAt(Timestamp companyCreatedAt) {
            this.companyCreatedAt = companyCreatedAt;
            return this;
        }

        public Builder setCompanyUpdatedBy(String companyUpdatedBy) {
            this.companyUpdatedBy = companyUpdatedBy;
            return this;
        }

        public Builder setCompanyUpdatedAt(Timestamp companyUpdatedAt) {
            this.companyUpdatedAt = companyUpdatedAt;
            return this;
        }

        public Builder setCompanyStatus(String companyStatus) {
            this.companyStatus = companyStatus;
            return this;
        }

        public Builder setSysTransaction(String sysTransaction) {
            this.sysTransaction = sysTransaction;
            return this;
        }

        public FICompanyDTO build() {
            return new FICompanyDTO(this);
        }
    }

    public long getCompanyID() {
        return companyID;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyLegalName() {
        return companyLegalName;
    }

    public String getCompanyBussinesType() {
        return companyBussinesType;
    }

    public String getCompanyBussinesClassification() {
        return companyBussinesClassification;
    }

    public String getCompanyCountryCode() {
        return companyCountryCode;
    }

    public String getCompanyLegalAddress() {
        return companyLegalAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getCompanyOfficialLanguage() {
        return companyOfficialLanguage;
    }

    public String getCompanyTimeZone() {
        return companyTimeZone;
    }

    public String getCompanyChartOfAccount() {
        return companyChartOfAccount;
    }

    public String getCompanyCreatedBy() {
        return companyCreatedBy;
    }

    public Timestamp getCompanyCreatedAt() {
        return companyCreatedAt;
    }

    public String getCompanyUpdatedBy() {
        return companyUpdatedBy;
    }

    public Timestamp getCompanyUpdatedAt() {
        return companyUpdatedAt;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }
    
    public String getSysTransaction(){
        return sysTransaction;
    }
}
