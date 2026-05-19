package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum UsersRoles {
    USER_ID("USER_ID"),
    ROLE_ID("ROLE_ID"),
    ROLE_NAME("ROLE_NAME"),
    ASSIGNED_AT("ASSIGNED_AT");
    
    private String columnName;

    private UsersRoles(String columnName) {
        this.columnName = columnName;
    }
    public String columnName(){
        return columnName;
    }

    public int index() {
        return ordinal() + 1; // Devuelve el índice 1-based en lugar de 0-based
    }
}
