
package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum AccountingAccounts {
    ACCOUNT_ID("ACCOUNT_ID"),
    ACCOUNT_CODE("ACCOUNT_CODE"),
    ACCOUNT_NAME("ACCOUNT_NAME"),
    ACCOUNT_DESCRIPTION("ACCOUNT_DESCRIPTION"),
    CREATED_BY("CREATED_BY"),
    CREATED_AT("CREATED_AT"),
    UPDATED_BY("UPDATED_BY"),
    UPDATED_AT("UPDATED_AT"),
    ACCOUNT_STATUS("ACCOUNT_STATUS"),
    IS_CLOSED("IS_CLOSED"),
    PARENT_ACCOUNT_ID("PARENT_ACCOUNT_ID"),
    SUBCLASS_ID("SUBCLASS_ID");

    private final String columnName;

    AccountingAccounts(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}

