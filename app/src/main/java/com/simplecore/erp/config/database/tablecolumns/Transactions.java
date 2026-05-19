package com.simplecore.erp.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum Transactions {
    TRANSACTION_ID("TRANSACTION_ID"),
    TRANSACTION_NAME("TRANSACTION_NAME"),
    DESCRIPTION("DESCRIPTION"),
    FOLDER_INDEX("FOLDER_INDEX"),
    FOLDER_NAME("FOLDER_NAME"),
    CREATED_AT("CREATED_AT");
    
    private String columnName;

    private Transactions(String columnName) {
        this.columnName = columnName;
    }
    public String columnName(){
        return columnName;
    }
    public int index() {
        return ordinal() + 1; // Devuelve el índice 1-based en lugar de 0-based
    }
}
