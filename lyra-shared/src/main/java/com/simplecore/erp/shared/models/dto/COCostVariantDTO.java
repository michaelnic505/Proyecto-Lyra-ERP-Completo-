

package com.simplecore.erp.shared.models.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

public class COCostVariantDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final Long variantId;
    private final String variantCode;
    private final String variantName;
    private final String variantDescription;
    private final Date validFrom;
    private final Date validTo;
    private final Long valuationVariantId;
    private final Long versionId;
    private final Boolean isActive;
    private final Date createdAt;
    private final String createdBy;
    private final Date updatedAt;
    private final String updatedBy;

    private COCostVariantDTO(Builder builder) {
        this.variantId = builder.variantId;
        this.variantCode = builder.variantCode;
        this.variantName = builder.variantName;
        this.variantDescription = builder.variantDescription;
        this.validFrom = builder.validFrom;
        this.validTo = builder.validTo;
        this.valuationVariantId = builder.valuationVariantId;
        this.versionId = builder.versionId;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
        this.createdBy = builder.createdBy;
        this.updatedAt = builder.updatedAt;
        this.updatedBy = builder.updatedBy;
    }

    // Getters
    public Long getVariantId() {
        return variantId;
    }

    public String getVariantCode() {
        return variantCode;
    }

    public String getVariantName() {
        return variantName;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public Long getValuationVariantId() {
        return valuationVariantId;
    }

    public Long getVersionId() {
        return versionId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    // Builder class
    public static class Builder {
        private Long variantId;
        private String variantCode;
        private String variantName;
        private String variantDescription;
        private Date validFrom;
        private Date validTo;
        private Long valuationVariantId;
        private Long versionId;
        private Boolean isActive;
        private Date createdAt;
        private String createdBy;
        private Date updatedAt;
        private String updatedBy;

        public Builder withVariantId(Long variantId) {
            this.variantId = variantId;
            return this;
        }

        public Builder withVariantCode(String variantCode) {
            this.variantCode = variantCode;
            return this;
        }

        public Builder withVariantName(String variantName) {
            this.variantName = variantName;
            return this;
        }

        public Builder withVariantDescription(String variantDescription) {
            this.variantDescription = variantDescription;
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

        public Builder withValuationVariantId(Long valuationVariantId) {
            this.valuationVariantId = valuationVariantId;
            return this;
        }

        public Builder withVersionId(Long versionId) {
            this.versionId = versionId;
            return this;
        }

        public Builder withIsActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder withCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withCreatedBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder withUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder withUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public COCostVariantDTO build() {
            return new COCostVariantDTO(this);
        }
    }
}