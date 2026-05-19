

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
public class AccountingAccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @return the accountId
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * @return the accountCode
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @return the accountDescription
     */
    public String getAccountDescription() {
        return accountDescription;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @return the createdAt
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @return the updatedAt
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return the accountStatus
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * @return the isClosed
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * @return the parentAccountId
     */
    public Integer getParentAccountId() {
        return parentAccountId;
    }

    /**
     * @return the subclassId
     */
    public Integer getSubclassId() {
        return subclassId;
    }
    private Integer accountId;
    private String accountCode;
    private String accountName;
    private String accountDescription;
    private String createdBy;
    private Timestamp createdAt;
    private String updatedBy;
    private Timestamp updatedAt;
    private String accountStatus;
    private boolean isClosed;
    private Integer parentAccountId;
    private Integer subclassId;

    private AccountingAccountDTO(Builder builder) {
        this.accountId = builder.accountId;
        this.accountCode = builder.accountCode;
        this.accountName = builder.accountName;
        this.accountDescription = builder.accountDescription;
        this.createdBy = builder.createdBy;
        this.createdAt = builder.createdAt;
        this.updatedBy = builder.updatedBy;
        this.updatedAt = builder.updatedAt;
        this.accountStatus = builder.accountStatus;
        this.isClosed = builder.isClosed;
        this.parentAccountId = builder.parentAccountId;
        this.subclassId = builder.subclassId;
    }

    public static class Builder {
        private Integer accountId;
        private String accountCode;
        private String accountName;
        private String accountDescription;
        private String createdBy;
        private Timestamp createdAt;
        private String updatedBy;
        private Timestamp updatedAt;
        private String accountStatus = "ACTIVE";
        private boolean isClosed = false;
        private Integer parentAccountId;
        private Integer subclassId;

        public Builder accountId(Integer accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder accountCode(String accountCode) {
            this.accountCode = accountCode;
            return this;
        }

        public Builder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public Builder accountDescription(String accountDescription) {
            this.accountDescription = accountDescription;
            return this;
        }

        public Builder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public Builder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder accountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
            return this;
        }

        public Builder isClosed(boolean isClosed) {
            this.isClosed = isClosed;
            return this;
        }

        public Builder parentAccountId(Integer parentAccountId) {
            this.parentAccountId = parentAccountId;
            return this;
        }

        public Builder subclassId(Integer subclassId) {
            this.subclassId = subclassId;
            return this;
        }

        public AccountingAccountDTO build() {
            return new AccountingAccountDTO(this);
        }
    }

    @Override
    public String toString() {
        return "AccountingAccount{" +
                "accountId=" + getAccountId() +
                ", accountCode='" + getAccountCode() + '\'' +
                ", accountName='" + getAccountName() + '\'' +
                ", accountDescription='" + getAccountDescription() + '\'' +
                ", createdBy='" + getCreatedBy() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", updatedBy='" + getUpdatedBy() + '\'' +
                ", updatedAt=" + getUpdatedAt() +
                ", accountStatus='" + getAccountStatus() + '\'' +
                ", isClosed=" + isClosed() +
                ", parentAccountId=" + getParentAccountId() +
                ", subclassId=" + getSubclassId() +
                '}';
    }
}

