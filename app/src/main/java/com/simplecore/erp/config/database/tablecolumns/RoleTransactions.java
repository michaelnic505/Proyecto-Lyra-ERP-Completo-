package com.simplecore.erp.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum RoleTransactions {
    ROLE_ID("ROLE_ID"),
    ROLE_NAME("ROLE_NAME"),
    TRANSACTION_ID("TRANSACTION_ID"),
    TRANSACTION_NAME("TRANSACTION_NAME"),
    ASSIGNED_AT("ASSIGNED_AT");
    
    private String columnName;

    private RoleTransactions(String columnName) {
        this.columnName = columnName;
    }

    public String columnName(){
        return columnName;
    }
            
    public int index() {
        return ordinal() + 1; // Devuelve el índice 1-based en lugar de 0-based
    }
}
