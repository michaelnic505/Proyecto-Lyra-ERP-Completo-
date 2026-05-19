

package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;
/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

public class AccountRangesModelRetrieveResponse extends BaseResponse{

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

    private AccountRangesModelRetrieveResponse(Builder builder) {
        super(builder.sessionId,builder.resultType);
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
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.FI_ACCOUNT_RANGES_BY_MODEL_ID;
    }

    public int getAssetsId() { return assetsId; }
    public int getAssetsFrom() { return assetsFrom; }
    public int getAssetsTo() { return assetsTo; }
    public int getLiabilitiesId() { return liabilitiesId; }
    public int getLiabilitiesFrom() { return liabilitiesFrom; }
    public int getLiabilitiesTo() { return liabilitiesTo; }
    public int getEquityId() { return equityId; }
    public int getEquityFrom() { return equityFrom; }
    public int getEquityTo() { return equityTo; }
    public int getRevenueId() { return revenueId; }
    public int getRevenueFrom() { return revenueFrom; }
    public int getRevenueTo() { return revenueTo; }
    public int getCostsId() { return costsId; }
    public int getCostsFrom() { return costsFrom; }
    public int getCostsTo() { return costsTo; }
    public int getExpensesId() { return expensesId; }
    public int getExpensesFrom() { return expensesFrom; }
    public int getExpensesTo() { return expensesTo; }

    public static class Builder {
        private String sessionId;
        private ResultType resultType;
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

        public Builder(String sessionId) {
            this.sessionId = sessionId;
        }
        public Builder(ResultType resultType) {
            this.resultType = resultType;
        }

        public Builder assets(int id, int from, int to) {
            this.assetsId = id;
            this.assetsFrom = from;
            this.assetsTo = to;
            return this;
        }

        public Builder liabilities(int id, int from, int to) {
            this.liabilitiesId = id;
            this.liabilitiesFrom = from;
            this.liabilitiesTo = to;
            return this;
        }

        public Builder equity(int id, int from, int to) {
            this.equityId = id;
            this.equityFrom = from;
            this.equityTo = to;
            return this;
        }

        public Builder revenue(int id, int from, int to) {
            this.revenueId = id;
            this.revenueFrom = from;
            this.revenueTo = to;
            return this;
        }

        public Builder costs(int id, int from, int to) {
            this.costsId = id;
            this.costsFrom = from;
            this.costsTo = to;
            return this;
        }

        public Builder expenses(int id, int from, int to) {
            this.expensesId = id;
            this.expensesFrom = from;
            this.expensesTo = to;
            return this;
        }

        public AccountRangesModelRetrieveResponse build() {
            return new AccountRangesModelRetrieveResponse(this);
        }
    }
}