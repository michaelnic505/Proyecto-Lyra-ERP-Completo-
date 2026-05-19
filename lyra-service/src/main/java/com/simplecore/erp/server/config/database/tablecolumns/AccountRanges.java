package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum AccountRanges {
    ID("RANGE_ID"),
    MODEL_ID("MODEL_ID"),
    CLASS_ID("CLASS_ID"),
    RANGE_START("RANGE_START"),
    RANGE_END("RANGE_END");

    String columnName;

    private AccountRanges(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

}
