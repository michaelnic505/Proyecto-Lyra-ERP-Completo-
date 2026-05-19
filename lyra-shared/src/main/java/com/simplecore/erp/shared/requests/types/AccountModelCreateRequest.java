
package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountModelCreateRequest extends BaseRequest {

    private final String modelName;
    private final String modelDescription;
    private final int assetsId;
    private final int assetsFrom;
    private final int assetsTo;
    private final int liabilitiesId;
    private final int liabilitiesFrom;
    private final int liabilitiesTo;
    private final int equityId;
    private final int equityFrom;
    private final int equityTo;
    private final int revenueId;
    private final int revenueFrom;
    private final int revenueTo;
    private final int costsId;
    private final int costsFrom;
    private final int costsTo;
    private final int expensesId;
    private final int expensesFrom;
    private final int expensesTo;
    private final String createdBy;

    private AccountModelCreateRequest(Builder builder) {
        super(builder.sessionId, builder.userId); // Llamada al constructor de BaseRequest
        this.modelName = builder.modelName;
        this.modelDescription = builder.modelDescription;
        this.assetsId = builder.assetsId;
        this.assetsFrom = builder.assetsFrom;
        this.assetsTo = builder.assetsTo;
        this.liabilitiesId = builder.liabilitiesId;
        this.liabilitiesFrom = builder.liabilitiesFrom;
        this.liabilitiesTo = builder.liabilitiesTo;
        this.equityId = builder.equityId;
        this.equityFrom = builder.equityFrom;
        this.equityTo = builder.equityTo;
        this.revenueId = builder.revenueId;
        this.revenueFrom = builder.revenueFrom;
        this.revenueTo = builder.revenueTo;
        this.costsId = builder.costsId;
        this.costsFrom = builder.costsFrom;
        this.costsTo = builder.costsTo;
        this.expensesId = builder.expensesId;
        this.expensesFrom = builder.expensesFrom;
        this.expensesTo = builder.expensesTo;
        this.createdBy = builder.createdBy;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNT_MODEL_CREATE;
    }

    // Métodos getter
    public String getModelName() {
        return modelName;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public int getAssetsFrom() {
        return assetsFrom;
    }

    public int getAssetsTo() {
        return assetsTo;
    }

    public int getLiabilitiesFrom() {
        return liabilitiesFrom;
    }

    public int getLiabilitiesTo() {
        return liabilitiesTo;
    }

    public int getEquityFrom() {
        return equityFrom;
    }

    public int getEquityTo() {
        return equityTo;
    }

    public int getRevenueFrom() {
        return revenueFrom;
    }

    public int getRevenueTo() {
        return revenueTo;
    }

    public int getCostsFrom() {
        return costsFrom;
    }

    public int getCostsTo() {
        return costsTo;
    }

    public int getExpensesFrom() {
        return expensesFrom;
    }

    public int getExpensesTo() {
        return expensesTo;
    }
        public int getExpensesId() {
        return expensesId;
    }

    public int getCostsId() {
        return costsId;
    }

    public int getRevenueId() {
        return revenueId;
    }

    public int getEquityId() {
        return equityId;
    }

    public int getLiabilitiesId() {
        return liabilitiesId;
    }

    public int getAssetsId() {
        return assetsId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    // Clase Builder
    public static class Builder {
        private final String sessionId;
        private final int userId;
        private String modelName;
        private String modelDescription;
        private int assetsId;
        private int assetsFrom;
        private int assetsTo;
        private int liabilitiesId;
        private int liabilitiesFrom;
        private int liabilitiesTo;
        private int equityId;
        private int equityFrom;
        private int equityTo;
        private int revenueId;
        private int revenueFrom;
        private int revenueTo;
        private int costsId;
        private int costsFrom;
        private int costsTo;
        private int expensesId;
        private int expensesFrom;
        private int expensesTo;
        private String createdBy;

        public Builder(String sessionId, int userId) {
            this.sessionId = sessionId;
            this.userId = userId;
        }

        public Builder modelName(String modelName) {
            this.modelName = modelName;
            return this;
        }

        public Builder modelDescription(String modelDescription) {
            this.modelDescription = modelDescription;
            return this;
        }
        public Builder assetsId(int assetsId) {
            this.assetsId = assetsId;
            return this;
        }

        public Builder assetsFrom(int assetsFrom) {
            this.assetsFrom = assetsFrom;
            return this;
        }

        public Builder assetsTo(int assetsTo) {
            this.assetsTo = assetsTo;
            return this;
        }
        
        public Builder liabilitiesId(int liabilitiesId) {
            this.liabilitiesId = liabilitiesId;
            return this;
        }

        public Builder liabilitiesFrom(int liabilitiesFrom) {
            this.liabilitiesFrom = liabilitiesFrom;
            return this;
        }

        public Builder liabilitiesTo(int liabilitiesTo) {
            this.liabilitiesTo = liabilitiesTo;
            return this;
        }
        
        public Builder equityId(int equityId) {
            this.equityId = equityId;
            return this;
        }

        public Builder equityFrom(int equityFrom) {
            this.equityFrom = equityFrom;
            return this;
        }

        public Builder equityTo(int equityTo) {
            this.equityTo = equityTo;
            return this;
        }
        
        public Builder revenueId(int revenueId) {
            this.revenueId = revenueId;
            return this;
        }

        public Builder revenueFrom(int revenueFrom) {
            this.revenueFrom = revenueFrom;
            return this;
        }

        public Builder revenueTo(int revenueTo) {
            this.revenueTo = revenueTo;
            return this;
        }
        
        public Builder costsId(int costsId) {
            this.costsId = costsId;
            return this;
        }

        public Builder costsFrom(int costsFrom) {
            this.costsFrom = costsFrom;
            return this;
        }

        public Builder costsTo(int costsTo) {
            this.costsTo = costsTo;
            return this;
        }
        
        public Builder expensesId(int expensesId) {
            this.expensesId = expensesId;
            return this;
        }

        public Builder expensesFrom(int expensesFrom) {
            this.expensesFrom = expensesFrom;
            return this;
        }

        public Builder expensesTo(int expensesTo) {
            this.expensesTo = expensesTo;
            return this;
        }

        public Builder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public AccountModelCreateRequest build() {
            return new AccountModelCreateRequest(this);
        }
    }
}
    
