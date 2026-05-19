
package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum CountriesInfo {
   
    ID("ID"),
    ALPHA_2("ALPHA_2"),
    ALPHA_3("ALPHA_3"),
    COUNTRY_NAME("COUNTRY"),
    CURRENCY_CODE("CURRENCY_CODE"),
    CURRENCY_NAME("CURRENCY_NAME"),
    CURRENCY_SYMBOL("SYMBOL"),
    CURRENCY_SYMBOL_NAME("SYMBOL_NAME");

    private String columnName;

    private CountriesInfo(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

}
