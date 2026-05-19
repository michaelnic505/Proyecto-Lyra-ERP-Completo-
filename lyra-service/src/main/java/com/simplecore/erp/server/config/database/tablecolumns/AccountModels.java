package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum AccountModels {

    MODEL_ID("MODEL_ID"),
    MODEL_KEY("MODEL_KEY"),
    MODEL_NAME("MODEL_NAME"),
    DESCRIPTION("DESCRIPTION"),
    CREATED_AT("CREATED_AT"),
    CREATED_BY("CREATED_BY"),
    STATUS("STATUS"),
    MODIFIED_BY("MODIFIED_BY"),
    MODIFIED_AT("MODIFIED_AT"),
    STATE("STATE");

    String columnName;

    private AccountModels(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

}
