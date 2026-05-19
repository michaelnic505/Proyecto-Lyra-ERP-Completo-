

package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum Timezones {

    ID("ID"),
    NAME("NAME");
    
    private final String columnName;

    private Timezones(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
    
    
}

