package com.simplecore.erp.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum Users {
    USER_ID("USER_ID"),
    USERNAME("USERNAME"),
    PASSWORD_HASH("PASSWORD_HASH"),
    FIRST_NAME("FIRST_NAME"),
    LAST_NAME("LAST_NAME"),
    EMAIL("EMAIL"),
    POSITION("POSITION"),
    DEPARTMENT("DEPARTMENT"),
    ROLE("ROLE"),
    IS_ACTIVE("IS_ACTIVE"),
    CREATED_AT("CREATED_AT");
    
    private String columnName;

    private Users(String columnName) {
        this.columnName = columnName;
    }
    public String columnName(){
        return columnName;
    }
        
    public int index() {
        return ordinal() + 1; // Devuelve el índice 1-based en lugar de 0-based
    }
}
