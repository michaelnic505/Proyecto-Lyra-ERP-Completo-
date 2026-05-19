package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum AccountClasses {
    ID("CLASS_ID"),
    CLASS_CODE("CLASS_CODE"),
    CLASS_NAME("CLASS_NAME"),
    CLASS_KEY("CLASS_KEY");
    
    String columnName;

    private AccountClasses(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    
}
