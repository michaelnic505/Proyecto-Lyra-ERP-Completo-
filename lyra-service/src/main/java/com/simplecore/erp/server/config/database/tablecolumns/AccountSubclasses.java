package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum AccountSubclasses {
    SUBCLASS_ID("SUBCLASS_ID"),
    MODEL_ID("MODEL_ID"),
    CLASS_ID("CLASS_ID"),
    SUBCLASS_CODE("SUBCLASS_CODE"),
    SUBCLASS_KEY("SUBCLASS_KEY"),
    SUBCLASS_NAME("SUBCLASS_NAME"),
    CREATED_AT("CREATED_AT");

    String columnName;

    private AccountSubclasses(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

}
