
package com.simplecore.erp.modules.system.access.models;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class Transaction {
    private final String id;
    private final String name;
    private final String description;
    private final String folderindex;
    private final String foldername;

    private Transaction(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.folderindex = builder.folderindex;
        this.foldername = builder.foldername;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getFolderIndex() { return folderindex; }
    public String getFolderName() { return foldername; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transaction that = (Transaction) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private String folderindex;
        private String foldername;

        public Builder id(String code) {
            this.id = code;
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

        public Builder folderindex(String folderindex) {
            this.folderindex = folderindex;
            return this;
        }

        public Builder foldername(String foldername) {
            this.foldername = foldername;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
