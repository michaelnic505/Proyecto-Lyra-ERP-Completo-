

package com.simplecore.erp.shared.models.dto;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TaxSchemaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private String status; // en lugar de enum
    private Date startDate;
    private Date endDate;
    private Boolean isExempt;
    private String countryCode;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private String taxCode;

    private TaxSchemaDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.status = builder.status;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.isExempt = builder.isExempt;
        this.countryCode = builder.countryCode;
        this.createdAt = builder.createdAt;
        this.createdBy = builder.createdBy;
        this.updatedAt = builder.updatedAt;
        this.updatedBy = builder.updatedBy;
        this.taxCode = builder.taxCode;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String status;
        private Date startDate;
        private Date endDate;
        private Boolean isExempt;
        private String countryCode;
        private Timestamp createdAt;
        private String createdBy;
        private Timestamp updatedAt;
        private String updatedBy;
        private String taxCode;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder isExempt(Boolean isExempt) {
            this.isExempt = isExempt;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder updatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public Builder taxCode(String taxCode) {
            this.taxCode = taxCode;
            return this;
        }

        public TaxSchemaDTO build() {
            return new TaxSchemaDTO(this);
        }
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Boolean getIsExempt() {
        return isExempt;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getTaxCode() {
        return taxCode;
    }
}
