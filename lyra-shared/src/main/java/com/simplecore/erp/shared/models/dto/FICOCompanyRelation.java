

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
import java.sql.Date;
import java.sql.Timestamp;

public class FICOCompanyRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Long ficoRelationID;
    private final long fiCompanyId;
    private final long coCompanyId;
    private final String coCompanyCode;
    private final String costingVariant;
    private final String costCurrency;
    private final boolean allowInternalOrders;
    private final boolean requireCostCenter;
    private final String costCenterPlan;
    private final String relationType;
    private final Date validFrom;
    private final Date validTo;
    private final String status;
    private final String createdBy;
    private final Timestamp createdAt;
    private final String updatedBy;
    private final Timestamp updatedAt;

    private FICOCompanyRelation(Builder builder) {
        this.ficoRelationID = builder.ficoRelationID;
        this.fiCompanyId = builder.fiCompanyId;
        this.coCompanyId = builder.coCompanyId;
        this.coCompanyCode = builder.coCompanyCode;
        this.costingVariant = builder.costingVariant;
        this.costCurrency = builder.costCurrency;
        this.allowInternalOrders = builder.allowInternalOrders;
        this.requireCostCenter = builder.requireCostCenter;
        this.costCenterPlan = builder.costCenterPlan;
        this.relationType = builder.relationType;
        this.validFrom = builder.validFrom;
        this.validTo = builder.validTo;
        this.status = builder.status;
        this.createdBy = builder.createdBy;
        this.createdAt = builder.createdAt;
        this.updatedBy = builder.updatedBy;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private Long ficoRelationID;
        private long fiCompanyId;
        private long coCompanyId;
        private String coCompanyCode;
        private String costingVariant;
        private String costCurrency;
        private boolean allowInternalOrders;
        private boolean requireCostCenter;
        private String costCenterPlan;
        private String relationType;
        private Date validFrom;
        private Date validTo;
        private String status;
        private String createdBy;
        private Timestamp createdAt;
        private String updatedBy;
        private Timestamp updatedAt;

        public Builder withFICORelationID(Long relationID) {
            this.ficoRelationID = relationID;
            return this;
        }

        public Builder withFICompanyId(long fiCompanyId) {
            this.fiCompanyId = fiCompanyId;
            return this;
        }

        public Builder withCOCompanyId(long coCompanyId) {
            this.coCompanyId = coCompanyId;
            return this;
        }

        public Builder withCOCompanyCode(String coCompanyCode) {
            this.coCompanyCode = coCompanyCode;
            return this;
        }

        public Builder withCostingVariant(String costingVariant) {
            this.costingVariant = costingVariant;
            return this;
        }

        public Builder withCostCurrency(String costCurrency) {
            this.costCurrency = costCurrency;
            return this;
        }

        public Builder withAllowInternalOrders(boolean allowInternalOrders) {
            this.allowInternalOrders = allowInternalOrders;
            return this;
        }

        public Builder withRequireCostCenter(boolean requireCostCenter) {
            this.requireCostCenter = requireCostCenter;
            return this;
        }

        public Builder withCostCenterPlan(String costCenterPlan) {
            this.costCenterPlan = costCenterPlan;
            return this;
        }

        public Builder withRelationType(String relationType) {
            this.relationType = relationType;
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

        public FICOCompanyRelation build() {
            return new FICOCompanyRelation(this);
        }
    }

    // Getters
    public Long getFICORelationID(){return ficoRelationID;};
    public long getFiCompanyId() { return fiCompanyId; }
    public long getCoCompanyId() { return coCompanyId; }
    public String getCoCompanyCode() { return coCompanyCode; }
    public String getCostingVariant() { return costingVariant; }
    public String getCostCurrency() { return costCurrency; }
    public boolean isAllowInternalOrders() { return allowInternalOrders; }
    public boolean isRequireCostCenter() { return requireCostCenter; }
    public String getCostCenterPlan() {return costCenterPlan;}
    public String getRelationType() { return relationType; }
    public Date getValidFrom() { return validFrom; }
    public Date getValidTo() { return validTo; }
    public String getStatus() { return status; }
    public String getCreatedBy() { return createdBy; }
    public Timestamp getCreatedAt() { return createdAt; }
    public String getUpdatedBy() { return updatedBy; }
    public Timestamp getUpdatedAt() { return updatedAt; }
}
