
package com.simplecore.erp.shared.models.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

public class COCompanyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int companyId;
    private String companyCode;
    private String companyName;
    private String legalName;
    private String countryCode;
    private String currencyCode;
    private String language;
    private String timeZone;
    private String controllingType;
    private String versionControl;
    private String assignmentRules;
    private Date validFrom;
    private Date validTo;
    private String status;
    private String createdBy;
    private Timestamp createdAt;
    private String updatedBy;
    private Timestamp updatedAt;

    // Constructor privado para el Builder
    private COCompanyDTO(Builder builder) {
        this.companyId = builder.companyId;
        this.companyCode = builder.companyCode;
        this.companyName = builder.companyName;
        this.legalName = builder.legalName;
        this.countryCode = builder.countryCode;
        this.currencyCode = builder.currencyCode;
        this.language = builder.language;
        this.timeZone = builder.timeZone;
        this.controllingType = builder.controllingType;
        this.versionControl = builder.versionControl;
        this.assignmentRules = builder.assignmentRules;
        this.validFrom = builder.validFrom;
        this.validTo = builder.validTo;
        this.status = builder.status;
        this.createdBy = builder.createdBy;
        this.createdAt = builder.createdAt;
        this.updatedBy = builder.updatedBy;
        this.updatedAt = builder.updatedAt;
    }

    // Getters
    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLegalName() {
        return legalName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getLanguage() {
        return language;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getControllingType() {
        return controllingType;
    }

    public String getVersionControl() {
        return versionControl;
    }

    public String getAssignmentRules() {
        return assignmentRules;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    // Builder
    public static class Builder {
        private int companyId;
        private String companyCode;
        private String companyName;
        private String legalName;
        private String countryCode;
        private String currencyCode;
        private String language;
        private String timeZone;
        private String controllingType;
        private String versionControl;
        private String assignmentRules;
        private Date validFrom;
        private Date validTo;
        private String status;
        private String createdBy;
        private Timestamp createdAt;
        private String updatedBy;
        private Timestamp updatedAt;

        public Builder withCompanyId(int companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder withCompanyCode(String companyCode) {
            this.companyCode = companyCode;
            return this;
        }

        public Builder withCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder withLegalName(String legalName) {
            this.legalName = legalName;
            return this;
        }

        public Builder withCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder withCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        public Builder withLanguage(String language) {
            this.language = language;
            return this;
        }

        public Builder withTimeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public Builder withControllingType(String controllingType) {
            this.controllingType = controllingType;
            return this;
        }

        public Builder withVersionControl(String versionControl) {
            this.versionControl = versionControl;
            return this;
        }

        public Builder withAssignmentRules(String assignmentRules) {
            this.assignmentRules = assignmentRules;
            return this;
        }

        public Builder withValidFrom(Date validFrom) {
            this.validFrom = validFrom;
            return this;
        }

        public Builder withValidTo(Date validTo) {
            this.validTo = validTo;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withCreatedBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder withCreatedAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public Builder withUpdatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        // Método para construir el objeto
        public COCompanyDTO build() {
            return new COCompanyDTO(this);
        }
    }
}
