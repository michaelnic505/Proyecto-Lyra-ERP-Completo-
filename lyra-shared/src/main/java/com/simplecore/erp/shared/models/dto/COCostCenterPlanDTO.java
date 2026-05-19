

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
public class COCostCenterPlanDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private final long costCenterPlanID;
    private final String costCenterPlanName;
    private final String costCenterPlanDescription;
    private final String costCenterPlanCreatedBy;
    private final Timestamp costCenterPlanCreatedAt;
    private final Timestamp costCenterPlanUpdatedAt;
    private final String costCenterPlanStatus;

    // Constructor privado para evitar la creación directa sin usar el builder
    private COCostCenterPlanDTO(Builder builder) {
        this.costCenterPlanID = builder.costCenterPlanID;
        this.costCenterPlanName = builder.costCenterPlanName;
        this.costCenterPlanDescription = builder.costCenterPlanDescription;
        this.costCenterPlanCreatedBy = builder.costCenterPlanCreatedBy;
        this.costCenterPlanCreatedAt = builder.costCenterPlanCreatedAt;
        this.costCenterPlanUpdatedAt = builder.costCenterPlanUpdatedAt;
        this.costCenterPlanStatus = builder.costCenterPlanStatus;
    }

    // Getters
    public long getCostCenterPlanID() {
        return costCenterPlanID;
    }

    public String getCostCenterPlanName() {
        return costCenterPlanName;
    }

    public String getCostCenterPlanDescription() {
        return costCenterPlanDescription;
    }

    public String getCostCenterPlanCreatedBy() {
        return costCenterPlanCreatedBy;
    }

    public Timestamp getCostCenterPlanCreatedAt() {
        return costCenterPlanCreatedAt;
    }

    public Timestamp getCostCenterPlanUpdatedAt() {
        return costCenterPlanUpdatedAt;
    }

    public String getCostCenterPlanStatus() {
        return costCenterPlanStatus;
    }

    // Builder pattern class
    public static class Builder {
        private long costCenterPlanID;
        private String costCenterPlanName;
        private String costCenterPlanDescription;
        private String costCenterPlanCreatedBy;
        private Timestamp costCenterPlanCreatedAt;
        private Timestamp costCenterPlanUpdatedAt;
        private String costCenterPlanStatus;

        // Setter methods for each field
        public Builder setCostCenterPlanID(long costCenterPlanID) {
            this.costCenterPlanID = costCenterPlanID;
            return this;
        }

        public Builder setCostCenterPlanName(String costCenterPlanName) {
            this.costCenterPlanName = costCenterPlanName;
            return this;
        }

        public Builder setCostCenterPlanDescription(String costCenterPlanDescription) {
            this.costCenterPlanDescription = costCenterPlanDescription;
            return this;
        }

        public Builder setCostCenterPlanCreatedBy(String costCenterPlanCreatedBy) {
            this.costCenterPlanCreatedBy = costCenterPlanCreatedBy;
            return this;
        }

        public Builder setCostCenterPlanCreatedAt(Timestamp costCenterPlanCreatedAt) {
            this.costCenterPlanCreatedAt = costCenterPlanCreatedAt;
            return this;
        }

        public Builder setCostCenterPlanUpdatedAt(Timestamp costCenterPlanUpdatedAt) {
            this.costCenterPlanUpdatedAt = costCenterPlanUpdatedAt;
            return this;
        }

        public Builder setCostCenterPlanStatus(String costCenterPlanStatus) {
            this.costCenterPlanStatus = costCenterPlanStatus;
            return this;
        }

        // Method to build the final COCostCenterPlanDTO object
        public COCostCenterPlanDTO build() {
            return new COCostCenterPlanDTO(this);
        }
    }
}

