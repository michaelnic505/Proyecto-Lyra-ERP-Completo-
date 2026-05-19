package com.simplecore.erp.modules.system.access.models;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import java.util.Set;

public class Role {
    private final String id;
    private final String name;
    private final Set<Transaction> allowedTransactions;

    private Role(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.allowedTransactions = builder.allowedTransactions;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Set<Transaction> getAllowedTransactions() { return allowedTransactions; }

    public boolean hasAccessTo(Transaction transaction) {
        return allowedTransactions.contains(transaction);
    }

    public static class Builder {
        private String id;
        private String name;
        private Set<Transaction> allowedTransactions;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder allowedTransactions(Set<Transaction> allowedTransactions) {
            this.allowedTransactions = allowedTransactions;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }
}
