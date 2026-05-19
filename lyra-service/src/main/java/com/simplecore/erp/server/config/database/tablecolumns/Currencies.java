

package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum Currencies {

    CURRENCY_CODE("CURRENCY_CODE"),
    CURRENCY_NAME("CURRENCY_NAME"),
    SYMBOL("SYMBOL"),
    SYMBOL_NAME("SYMBOL_NAME");

    private String columnName;

    private Currencies(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

}
