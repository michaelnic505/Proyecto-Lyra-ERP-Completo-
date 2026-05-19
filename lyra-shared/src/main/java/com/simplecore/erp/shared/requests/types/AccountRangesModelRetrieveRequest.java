
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
public class AccountRangesModelRetrieveRequest extends BaseRequest {

    private final int modelId;
    private final int assetsId;
    private final int liabilitiesId;
    private final int equityId;
    private final int revenueId;
    private final int costsId;
    private final int expensesId;

    private AccountRangesModelRetrieveRequest(Builder builder) {
        super(builder.sessionId, builder.userId);
        this.modelId = builder.modelId;
        this.assetsId = builder.assetsId;
        this.liabilitiesId = builder.liabilitiesId;
        this.equityId = builder.equityId;
        this.revenueId = builder.revenueId;
        this.costsId = builder.costsId;
        this.expensesId = builder.expensesId;
    }

    public int getModelId() {
        return modelId;
    }

    public int getAssetsId() {
        return assetsId;
    }

    public int getLiabilitiesId() {
        return liabilitiesId;
    }

    public int getEquityId() {
        return equityId;
    }

    public int getRevenueId() {
        return revenueId;
    }

    public int getCostsId() {
        return costsId;
    }

    public int getExpensesId() {
        return expensesId;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_ACCOUNT_RANGES_BY_MODEL_ID_RETRIEVE;
    }

    public static class Builder {
        private final String sessionId;
        private final int userId;
        private final int modelId;
        private int assetsId;
        private int liabilitiesId;
        private int equityId;
        private int revenueId;
        private int costsId;
        private int expensesId;

        public Builder(String sessionId, int userId, int modelId) {
            this.sessionId = sessionId;
            this.userId = userId;
            this.modelId = modelId;
        }

        public Builder assetsId(int assetsId) {
            this.assetsId = assetsId;
            return this;
        }

        public Builder liabilitiesId(int liabilitiesId) {
            this.liabilitiesId = liabilitiesId;
            return this;
        }

        public Builder equityId(int equityId) {
            this.equityId = equityId;
            return this;
        }

        public Builder revenueId(int revenueId) {
            this.revenueId = revenueId;
            return this;
        }

        public Builder costsId(int costsId) {
            this.costsId = costsId;
            return this;
        }

        public Builder expensesId(int expensesId) {
            this.expensesId = expensesId;
            return this;
        }

        public AccountRangesModelRetrieveRequest build() {
            return new AccountRangesModelRetrieveRequest(this);
        }
    }
}
